// If Model were revised, the cached Model module may need to be deleted.
// delete require.cache[require.resolve("./chat_model.js")];
const model = require("./chat_model.js");

const proceed = function (_GET, _POST, callback) {

     if (_POST['command'] == 'Join') {

          model.ready((result) => {
               if (result) {
                    model.usernameExists(_POST['username'], (result) => {
                         if (result) {
                              callback("Username already in use");
                         } else {
                              model.registerUser(_POST['username'], _POST['password'], _POST['email'], (result) => {
                                   if (result) {
                                        callback("Joined");
                                   } else callback("Something went wrong");
                              });
                         }
                    });
               }
          });

     } else if (_POST['command'] == 'SignIn') {

          model.ready((result) => {
               if (result) {
                    model.validateUsernamePassword(_POST['username'], _POST['password'], (result) => {
                         if (result) {
                              callback(_POST['username'] + " exists in the database");
                         } else callback("Username or Password is invalid.");
                    });
               }
          });

     } else if (_POST['command'] == 'Delete') {

          model.ready((result) => {
               if (result) {
                    model.validateUsernamePassword(_POST['username'], _POST['password'], (result) => {
                         if (result) {
                              model.deleteUser(_POST['username'], _POST['password'], (result) => {
                                   callback("User was deleted.");
                              });
                         } else {
                              callback("Username or Password is invalid. Not deleted!");
                         }
                    });
               }
          });

     } else if (_POST['command'] == 'Close') {

          model.ready((result) => {
               if (result) {
                    model.close();
                    callback("Closed");
               }
          });

     }
}

exports.proceed = proceed;