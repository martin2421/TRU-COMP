var http = require("http");  // reference: Node.js HTTP Module
var server = http.createServer((request, response) => {
  response.writeHead(200, {"Content-Type": "text/plain"});
  response.write("<h1>Hello World! - server1.js</h1>");
  response.end();
});
server.listen(8022);  // Use your port number instead of 8080.