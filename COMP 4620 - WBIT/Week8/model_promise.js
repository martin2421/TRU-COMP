// connect() function that uses dbname, username, password, and callback
// This function makes a connection to your MongoDB and returns the connection stub through the callback function.
// Let's use async and await.

const MongoClient = require('mongodb').MongoClient;

const connect = async function (dbname, username, password, callback) {

     return new Promise((resolve, reject) => {
          try {
               const conn = MongoClient.connect(`mongodb://${username}:${password}@127.0.0.1:27017/${dbname}`);  // connection stub
               resolve(conn);  // return conn through callback 
          }
          catch (err) {
               reject(null)  // return null through callback
          }
     });

     
}

// add another function, close(conn), that closes the connection
const close = function (conn) {
     if (conn != null) {
          conn.close();
     }
}

// add another function, db(conn, callback), that returns the db stub through callback
const db = function (conn, callback) {
     if (conn != null) {
          callback(conn.db());
     } else {
          callback(null);
     }
}

// add another function, collection(db, collectionname, callback), that returns the connection stub through callback
const collection = async function (db, collectionname, callback) {
     let list = await db.listCollections().toArray();
     let exist = false;

     for (let i = 0; i < list.length; i++) {
          if (list[i].name == collectionname) {
               exist = true;
               break;
          }
     }
     if (!exist)
          await db.createCollection(collectionname);

     // collectin stub
     let collection = db.collection(collectionname);

     callback(collection);
}

// add another function, insert(collection, doc, callback), that returns the empty callback
const insertDoc = async function (collection, doc, callback) {
     await collection.insertOne(doc);
     callback();
}

// add another function, find(collection, filter, callback), that returns the empty callback
const find = async function (collection, filter, callback) {
     let lists = await collection.find(filter).toArray();
     callback(lists);
}

// exports connect
exports.connect = connect;
exports.close = close;
exports.db = db;
exports.collection = collection;
exports.insertDoc = insertDoc;
exports.find = find;