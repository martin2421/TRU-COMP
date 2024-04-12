const express = require('express');
const bodyParser = require('body-parser');
const model = require('./model.js');

const app = express();

const server = app.listen(8021, function () {  // User your port number
    let host = server.address().address;
    let port = server.address().port;
    console.log("Listening at http://%s:%s", host, port)
})

// for POST, PUT, and DELETE query
app.use(bodyParser.urlencoded({ extended: true }));

// To support CORS
app.all('/*', function(req, res, next) {  // /*: any routes
    res.header("Access-Control-Allow-Origin", "*");
    res.header("Access-Control-Allow-Headers", "X-Requested-With");
    res.header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE");  // Default: only GET and POST
    next();  // Express middleware function; To continue to next operations
});

// Route operations

app.get('/', function (req, res) {  // HTTP GET method
    res.send("Welcome to TRU MongoDB Web Service!");
})

// SignUp (Create/Register a user)
//   Request - POST /users?username=...&password=...
//   Response - '{"result":"true"|"false", "explanation":"..."}'
app.post('/users', function (req, res) {  // HTTP POST method
    var username = req.body.username;
    var password = req.body.password;
    console.log("POST /users: username = %s, password = %s", username, password);
    model.ready(function(result) {
        if (result) {
            model.registerUser(username, password, function(result) {
                if (result)
                    res.send(JSON.stringify({result:true, explanation:""}));
                else
                    res.send(JSON.stringify({result:false, explanation:"Username exists"}));
            });
        }
        else
            res.send(JSON.stringify({result:false, explanation:"Connection error"}));
    });
});