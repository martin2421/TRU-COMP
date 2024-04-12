// require test_model.js
const model = require('./model_promise.js');

const proceed = async function () {
     model.connect('COMP4620_w4matanacio', 'w4matanacio', 'w4matanacio136', (conn) => {
          console.log("Connected");
          model.db(conn, (db) => {
               console.log("Db stub obtained");
               model.collection(db, "Users", (collection) => {
                    model.insertDoc(collection, { username: "martin2", password: "password2" }, () => {
                         console.log("Document inserted");
                         console.log("Collection stub obtained");
                         model.find(collection, { username: "martin2" }, (found) => {
                              console.log(found);
                              model.close(conn);
                              console.log("Closed");
                         });
                    });
               });
          })
     });
}

proceed();