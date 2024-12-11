import * as fs from 'node:fs';
import express from 'express';  // for RESTful API
import serverless from 'serverless-http';
import { DynamoDBClient } from "@aws-sdk/client-dynamodb";
import {
    GetCommand, PutCommand, UpdateCommand, DeleteCommand,
    ExecuteStatementCommand, DynamoDBDocumentClient
} from "@aws-sdk/lib-dynamodb";

const client = new DynamoDBClient({});
const docClient = DynamoDBDocumentClient.from(client);

const app = express();
app.use(express.urlencoded({ extended: true }));
app.use(express.json());

export const handler = serverless(app);

app.post('/memos', async (request, response) => {
    let memo = request.body.memo;

    // GET COMMAND - RETREIVE KEY
    let command = new GetCommand({
        TableName: 'Memos',
        Key: {
            ID: -1,
        },
    });

    // SEND COMMAND AND RECEIVE RESPONSE ITEM
    let res = await docClient.send(command);
    
    let item = res.Item;

    // PUT COMMAND - ADD ITEM TO THE TABLE
    command = new PutCommand({
        TableName: 'Memos',
        Item: {
            ID: item.NextID,
            Memo: memo,
        }
    });

    // SEND COMMAND
    res = await docClient.send(command);

    // UPDATE COMMAND - UPDATE TABLE WITH NEW VALUE
    command = new UpdateCommand({
        TableName: 'Memos',
        Key: { ID: -1, },
        UpdateExpression: "set NextID = :nextid",
        ExpressionAttributeValues: { ":nextid": item.NextID + 1 },
        ReturnValues: "ALL_NEW",
    });

    // SEND COMMAND
    await docClient.send(command);
    response.send("Result: OK");

});

app.get('/terms', async (request, response) => {
    let term = request.query.term;

    // EXECUTESTATEMENT COMMAND - SEARCH IN TABLE
    let command = new ExecuteStatementCommand({
        Statement: "SELECT * FROM Memos WHERE contains(Memo, ?)",
        Parameters: [term],
    });

    // SEND COMMAND AND RECEIVE RESPONSE ITEM
    let query_response = await docClient.send(command);
    response.send(JSON.stringify(query_response.Items));

});

app.delete('/memos/:id', async (request, response) => {
    let id = request.params.id;

    // DELETECOMMAND - DELETE AN ITEM
    let command = new DeleteCommand({
        TableName: "Memos",
        Key: {
            ID: Number(id),
        }
    });

    // SEND COMMAND
    await docClient.send(command);

    response.send("DELETE: OK");
});