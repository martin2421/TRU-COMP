// hello_http.js
var http = require('http');  // Require 'http' module; We will study it in detail in the next section.
var server = http.createServer((req, res) => {  // HTTP server object to deal with HTTP requests from clients
    res.writeHead(200);  // Sends the head to the client with the reponse number 200
    res.end('Martin\'s Hello World! - hello_http.js');  // Sends the message back to the client
});
server.listen(8022);  // The server listens to the port number 8080.