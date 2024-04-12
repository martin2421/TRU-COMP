var PORT_NO = 8022;  // Use your port number, not 8080
var http = require('http');
function start() {
    var server = http.createServer(
        (request, response) => {  // request: from the client; response: to the client
            response.writeHead(200, {"Content-Type": "text/plain"});
            response.write("<h1>Hello World! - server2.js</h1>");
            response.end();
        }
    );
    server.listen(PORT_NO);
}
// Something more
exports.start = start;