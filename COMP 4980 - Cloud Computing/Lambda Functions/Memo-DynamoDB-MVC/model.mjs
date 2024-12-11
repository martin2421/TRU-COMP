import { DynamoDBClient } from '@aws-sdk/client-dynamodb';
import { DynamoDBDocumentClient, GetCommand, PutCommand, UpdateCommand, ExecuteStatementCommand, DeleteCommand } from '@aws-sdk/lib-dynamodb';

const client = new DynamoDBClient({});
const docClient = DynamoDBDocumentClient.from(client);

async function addMemo(memo) {

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
            Memo: memo,
        }
    });

    // SEND COMMAND
    response = await docClient.send(command);

    // UPDATE COMMAND - UPDATE TABLE WITH NEW VALUE
    command = new UpdateCommand({
        TableName: 'Memos',
        Key: { ID: -1, },
        UpdateExpression: "set NextID = :nextid",
        ExpressionAttributeValues: { 
            ":nextid": item.NextID + 1 
        },
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
            body: 'ADD OK - ' + memo,
        });
    });

}

async function searchMemos(term) {

    // EXECUTESTATEMENT COMMAND - SEARCH IN TABLE
    let command = new ExecuteStatementCommand({
        Statement: "SELECT * FROM Memos WHERE contains(Memo, ?)",
        Parameters: [term],
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

async function deleteMemo(id) {

    // DELETECOMMAND - DELETE AN ITEM
    let command = new DeleteCommand({
        TableName: "Memos",
        Key: {
            ID: Number(id),
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

export { addMemo, searchMemos, deleteMemo };