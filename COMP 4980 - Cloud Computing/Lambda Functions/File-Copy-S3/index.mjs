const BACKUP_BUCKET_NAME = "backup-uploads-comp4980";  // your backup bucket
//   NOT your source bucket

import { S3Client, GetObjectCommand, HeadObjectCommand } from '@aws-sdk/client-s3';
import { Upload } from '@aws-sdk/lib-storage';
const s3client = new S3Client();

// event: notification from a S3 bucket, uploads-tru-comp4980acc, when a file is uploaded
export const handler = async (event, context) => {
  // the source bucket name
  const sourceBucket = event.Records[0].s3.bucket.name;
  // the path of the object (or file)
  const key = decodeURIComponent(event.Records[0].s3.object.key.replace(/\+/g, ' '));

  // Just in case,
  if (BACKUP_BUCKET_NAME == sourceBucket) {
    console.log("Warning: the source bucket is the same as the backup bucket.");
    return;
  }

  // Read a file from sourceBucket and upload it to the backup bucket
  const response = await readFileFromS3Bucket(sourceBucket, key);

  if (response.result)
    await uploadFileToS3Bucket(BACKUP_BUCKET_NAME, key, response.body); // response.body
  else
    console.log("File reading unsuccessful!");
};

async function readFileFromS3Bucket(bucket, key) {
  try {
    // get the object (or file) from the source s3 bucket
    const params = {
      Bucket: bucket,  // the bucket name
      Key: key  // the path of object (or file)
    }
    const command = new GetObjectCommand(params);
    const response = await s3client.send(command);  // response.Body

    return { result: true, body: response.Body };
  }
  catch (err) {
    return { result: false, body: err };
  }

}

async function uploadFileToS3Bucket(bucket, key, body)
{

  // check if the object/file already exists in the backup bucket
  try {
    const { ContentType } = await s3client.send(new HeadObjectCommand({
      Bucket: bucket, 
      Key: key
    }));
    console.log("The file already exists.");
  }
  // If the object/file does not exist, then upload it.
  catch (err) {
    const params = {
      Bucket: bucket,  // the bucket name
      Key: key,  // the path of object (or file)
      Body: body  // the object (or file) content
    };
    /* It does not work. It makes errors. Wondering why?
    const command = new PutObjectCommand(params);
    const response = await s3client.send(command);
    */
    const uploads3 = new Upload({
      client: s3client,
      params: params
    });
    const response = await uploads3.done();
  }

}

