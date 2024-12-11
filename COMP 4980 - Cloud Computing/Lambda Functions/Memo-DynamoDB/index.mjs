import * as fs from 'node:fs';
import querystring from 'node:querystring';
import { DynamoDBClient } from '@aws-sdk/client-dynamodb';
import { DynamoDBDocumentClient, GetCommand, PutCommand, UpdateCommand, ExecuteStatementCommand, DeleteCommand } from '@aws-sdk/lib-dynamodb';

const client = new DynamoDBClient({});
const docClient = DynamoDBDocumentClient.from(client);

const html = fs.readFileSync('memo-dynamodb.html', {
  encoding: 'utf8'
});

let proceed_post = async (event) => {
  const postData = querystring.parse(atob(event.body));

  // ADD COMMAND
  if (postData.command == 'ADD') {

    // GET COMMAND - RETREIVE KEY
    let command = new GetCommand({
      TableName: 'Memos',
      Key: {
        ID: -1,
      },
    });

    // SEND COMMAND AND RECEIVE RESPONSE ITEM
    let response = await docClient.send(command);
    let item = response.Item;

    // PUT COMMAND - ADD ITEM TO THE TABLE
    command = new PutCommand({
      TableName: 'Memos',
      Item: {
        ID: item.NextID,
        Memo: postData.memo,
      }
    });

    // SEND COMMAND
    response = await docClient.send(command);

    // UPDATE COMMAND - UPDATE TABLE WITH NEW VALUE
    command = new UpdateCommand({
      TableName: 'Memos',
      Key: { ID: -1, },
      UpdateExpression: "set NextID = :nextid",
      ExpressionAttributeValues: { ":nextid": item.NextID + 1 },
      ReturnValues: "ALL_NEW",
    });

    // SEND COMMAND
    response = await docClient.send(command);

    // PROMISE OBJECT TO INDICATE SUCCESSFUL EVENT
    return new Promise((resolve, reject) => {
      resolve({
        statusCode: 200,
        headers: {
          'Content-Type': 'text/html',
        },
        body: 'ADD OK',
      });
    });

  }

  // SEARCH COMMAND
  else if (postData.command == 'SEARCH') {

    // EXECUTESTATEMENT COMMAND - SEARCH IN TABLE
    let command = new ExecuteStatementCommand({
      Statement: "SELECT * FROM Memos WHERE contains(Memo, ?)",
      Parameters: [postData.term],
    });

    // SEND COMMAND AND RECEIVE RESPONSE ITEM
    let response = await docClient.send(command);
    let items = response.Items;

    // PROMISE OBJECT TO INDICATE SUCCESSFUL EVENT
    return new Promise((resolve, reject) => {
      resolve({
        statusCode: 200,
        headers: {
          'Content-Type': 'text/html',
        },
        body: JSON.stringify(items),
      });
    });

  }

  // DELETE COMMAND
  else if (postData.command == 'DELETE') {

    // DELETECOMMAND - DELETE AN ITEM
    let command = new DeleteCommand({
      TableName: "Memos",
      Key: {
        ID: Number(postData.id),
      }
    });

    // SEND COMMAND
    let response = await docClient.send(command);

    // PROMISE OBJECT TO INDICATE SUCCESSFUL EVENT
    return new Promise((resolve, reject) => {
      resolve({
        statusCode: 200,
        headers: {
          'Content-Type': 'text/html',
        },
        body: "ITEM DELETED",
      });
    });

  }

  // UNKNOWN COMMAND
  else {

  }

}





// HANDLER
export const handler = async (event) => {

  // GET METHOD
  if (event.requestContext.http.method == 'GET') {
    const response = {
      statusCode: 200,
      headers: {
        'Content-Type': 'text/html',
      },
      body: html,
    };
    return new Promise((resolve, reject) => {
      resolve(response);
    });
  }

  // POST METHOD
  else if (event.requestContext.http.method == 'POST') {
    return proceed_post(event);
  }

  // UNKNOWN METHOD
  else {
    const response = {
      statusCode: 200,
      headers: {
        'Content-Type': 'text/plain',
      },
      body: 'unknown',
    }
    return new Promise((res, rej) => {
      res(response);
    });

  }

}

