import * as fs from 'node:fs';

async function view() {
    const html = fs.readFileSync('memo-dynamodb.html', {
        encoding: 'utf8'
    });

    return new Promise((resolve, reject) => {
        resolve({
            statusCode: 200,
            headers: {
                'Content-Type': 'text/html',
            },
            body: html,
        });
    });
}

export { view };