// Trial 7.5

// const MongoClient = require('mongodb').MongoClient;

// (async function () {
//      try {
//           // connection stub
//           const conn = await MongoClient.connect("mongodb://w4matanacio:w4matanacio136@127.0.0.1:27017/COMP4620_w4matanacio");
//           console.log("MongoDB connected");

//           // db stub
//           let db = conn.db();

//           // If the "Users" collection does not exist, let's create it.
//           let list = await db.listCollections().toArray();  // https://mongodb.github.io/node-mongodb-native/2.2/api/Db.html#listCollections
//           let exist = false;
//           for (let i = 0; i < list.length; i++) {
//                if (list[i].name == "Users") {
//                     exist = true;
//                     break;
//                }
//           }
//           if (!exist)
//                await db.createCollection("Users");

//           // collectin stub
//           let collection = db.collection("Users");

//           // find all documents in the collection
//           let lists = await collection.find({}).toArray();  // or just find()
//           console.log(lists);

//           // close the connection to MongoDB
//           conn.close();
//      }
//      catch (err) {
//           console.log(err);
//      }
// })();  // self invocation of an anonymous function






// Trial 8

// const MongoClient = require('mongodb').MongoClient;

// (async function (username, password) {
//      try {
//           const conn = await MongoClient.connect("mongodb://w4matanacio:w4matanacio136@127.0.0.1:27017/COMP4620_w4matanacio");
//           const db = conn.db();
//           let collection = db.collection("Users");  // You need to make sure this collection exists. 
//           // See Trial 7.5 how to create a collection if necessary.
//           await collection.insertOne({ username: username, password: password });
//           let lists = await collection.find({ username: { $exists: true } }).toArray();  // for testing
//           console.log(lists);  // for testing
//           conn.close();
//      }
//      catch (err) {
//           console.log(err);
//      }
// })("John", "secretpassword");  // self invocation of an anonymous function









// Trial 8.5

const MongoClient = require('mongodb').MongoClient;

const registerUser = async function (u, p, callback)  // You need to include the next code with the connection to MongoDB.
{
     try {
          const conn = await MongoClient.connect("mongodb://w4matanacio:w4matanacio136@127.0.0.1:27017/COMP4620_w4matanacio");
          const db = conn.db();

          let collection = db.collection("Users");  // Where is db defined?
          // You need to make sure this collection exists.
          await collection.insertOne({ u: u, p: p });
          var result = await collection.find({ u: { $exists: true } }).toArray();
          callback(result);
          conn.close();
     }
     catch (err) {
          callback(false);
     };
}

registerUser("john", "topoftheworld", (result) => {
     console.log(result);
});