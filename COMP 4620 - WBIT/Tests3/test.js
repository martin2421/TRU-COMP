const express = require('express');  // "Express" framework
const app = express();
const port = 8022;  // Use your port number.

// Allow Cross-domain requests, i.e., CORS (Cross-Origin Resource Sharing)
//   Why do we need this?
app.all('/*', function (req, res, next) {  // all(): any HTTP method; /*: any routes
     res.header("Access-Control-Allow-Origin", "*");
     res.header("Access-Control-Allow-Headers", "X-Requested-With");
     res.header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE");  // Default: only GET and POST
     next();  // Express middleware function; To continue to next operations
});

// GET method route
app.get('/', (req, res) => {
     res.send("GET: Martin's Hello World!")  // send(): combination of write() and end()
})

// POST method route
app.post('/', (req, res) => {
     res.write("POST: Martin's Hello World!")
     res.end()
})

app.listen(port, () => {
     console.log(`Example app listening on port ${port}`)
})