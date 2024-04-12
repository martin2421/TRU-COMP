// All functions pass back a Boolean value - true or false.

let MongoClient;
let conn;  // connection stub
let db;  // db stub
let collection;  // collection stub
let connected = false;  // flag to see if a connection is made

// Prepares conn, db, collection, connected
// Passes true|false back through a callback function
const ready = async function (callback) {

     if (connected) {
          callback(true);
     } else {
          try {

               // connection
               MongoClient = require("mongodb").MongoClient;
               conn = await MongoClient.connect("mongodb://w4matanacio:w4matanacio136@127.0.0.1:27017/COMP4620_w4matanacio");

               // 1 sec timer to close
               timerid = setTimeout(() => {
                    close();
               }, 1000 * 60 * 2);

               // db stub
               db = conn.db();

               // If the "Users" collection does not exist, let's create it.
               let list = await db.listCollections().toArray();
               let exist = false;
               for (let i = 0; i < list.length; i++) {
                    if (list[i].name == "Users") {
                         exist = true;
                         break;
                    }
               }
               if (!exist)
                    await db.createCollection("Users");

               // collection stub
               collection = db.collection("Users");

               connected = true;
               callback(true);
          }
          catch (err) {
               connected = false;
               callback(false);
          }
     }
}

const close = function () {
     if (conn != null) {
          connected = false;
          conn.close();

          // clear the timer
          clearTimeout(timerid);
     }
}

const usernameExists = async function (u, callback) {
     try {
          let user = await collection.findOne({ username: u });

          if (user !== null)
               callback(true);
          else
               callback(false);
     }
     catch (err) {
          callback(false);
     }
}

const validateUsernamePassword = async function (u, p, callback) {
     try {
          let user = await collection.findOne({ username: u, password: p });

          if (user !== null)
               callback(true);
          else
               callback(false);
     }
     catch (err) {
          callback(false);
     }
}

const registerUser = async function (u, p, e, callback) {
     try {
          await collection.insertOne({ username: u, password: p, email: e });
          callback(true);
     } catch (err) {
          callback(false);
     }
}

const deleteUser = async function (u, p, callback) {
     try {
          await collection.deleteOne({ username: u, password: p });
          callback(true);
     } catch (err) {
          callback(false);
     }
}


exports.ready = ready;
exports.close = close;
exports.usernameExists = usernameExists;
exports.validateUsernamePassword = validateUsernamePassword;
exports.registerUser = registerUser;
exports.deleteUser = deleteUser;