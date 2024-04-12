const MongoClient = require('mongodb').MongoClient;

// This function should pass a string message back through callback.
const proceed = async function (_GET, _POST, callback) {  
     try {

          // mongodb://username:password@127.0.0.1[:port]/databasename
          const conn = await MongoClient.connect("mongodb://w4matanacio:w4matanacio136@127.0.0.1:27017/COMP4620_w4matanacio");
          callback("MongoDB connected using async/await");
          conn.close();  // It is a must. Otherwise, the max number of connections will be fed up.

     }
     catch (err) {
          callback('Connection error');
     }
}

exports.proceed = proceed;