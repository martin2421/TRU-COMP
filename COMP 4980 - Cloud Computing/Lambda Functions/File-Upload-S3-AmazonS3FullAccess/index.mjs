import * as fs from "node:fs";
import { S3Client, PutObjectCommand } from "@aws-sdk/client-s3";
import { getSignedUrl } from "@aws-sdk/s3-request-presigner";
import querystring from "node:querystring";

export const handler = async (event) => {

  if (event.rawPath == "/") {  // API from Gateway: "GET /"
    const html = fs.readFileSync('file-upload-s3.html', { encoding: 'utf8' }) +
      `<script>
            const API_GATEWAY_URL = "https://4vvx1mjgjg.execute-api.us-east-1.amazonaws.com/";
       </script>`;

    return new Promise((resolve, reject) => {
      resolve({
        statusCode: 200,
        headers: { 'Content-type': 'text/html' },
        body: html,
      });
    });

    // API from Gateway: "GET /uploads"
  } else return getUploadURL(event);

}

const getUploadURL = async function (event) {
  const query = querystring.parse(event.rawQueryString);
  const randomID = parseInt(Math.random() * 10000000)
  const key = `${randomID}-${query.name}`;
  const type = query.type;

  const client = new S3Client();

  const putObjectParams = {
    Bucket: process.env.UploadBucket,  // environment variable
    Key: key,  // the object [path-]name to be used in UploadBucket, not the primary key in DB
    ContentType: type,
  }

  const command = new PutObjectCommand(putObjectParams);
  const signedUrl = await getSignedUrl(client, command, { expiresIn: 3600 });

  return new Promise((resolve, reject) => {
    resolve(JSON.stringify({ uploadURL: signedUrl }));
  });
}

