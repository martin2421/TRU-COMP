const express = require('express');  // "Express" framefork
const app = express();
const port = 8021;  // Use your port number.

// Allow Cross-domain requests, i.e., CORS (Cross-Origin Resource Sharing)
//   Why do we need this?
app.all('/*', function(req, res, next) {  // all(): any HTTP method; /*: any routes
    res.header("Access-Control-Allow-Origin", "*");
    res.header("Access-Control-Allow-Headers", "X-Requested-With");
    res.header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE");  // Default: only GET and POST
    next();  // Express middleware function; To continue to next operations
});

// A GET method route
app.get('/', (req, res) => {
  res.send('GET: Hello World!')  // send(): combination of write() and end()
})

// A POST method route
app.post('/', (req, res) => {
  res.write('POST: Hello World!')
  res.end()
})

app.listen(port, () => {
  console.log(`Example app listening on port ${port}`)
})