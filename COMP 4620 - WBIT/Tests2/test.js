// // E7 Trial E1
// var url = require('url');
// var querystring = require('querystring');

// var uri = 'http://test.com/start.html?command=Login&username=foo&password=topsecret';
// var pathname = decodeURI(url.parse(uri).pathname);
// console.log("pathname: " + pathname);
// var query = decodeURI(url.parse(uri).query);
// console.log("query: " + query);
// var _GET = querystring.parse(decodeURI(url.parse(uri).query));
// console.log("_GET: ", _GET);


// // E7 Trial E2
// // For testing
// var events = require('events');
// var req = new events.EventEmitter();

// // Node WebServer
// var querystring = require('querystring');

// var query = "";
// var _POST = {};
// req.on('data', function(chunk) {
//     query += chunk;  // add chunk to query
// });
// req.on('end', function() {
//     _POST = querystring.parse(query);
// });
// console.log("_POST: ", _POST);

// // For testing
// var queryinURL = "operation=add&op1=30&op2=40";
// req.emit('data', queryinURL);
// req.emit('end');

// E7 Trial E2.5
// For testing
var events = require('events');
var req = new events.EventEmitter();

// Node WebServer
var querystring = require('querystring');

var query = "";
var _POST = {};
req.on('data', function(chunk) {
    query += chunk;  // add chunk to query
});
req.on('end', function() {
    _POST = querystring.parse(query)
    console.log("_POST: ", _POST);
});

// For testing
var queryinURL = "operation=add&op1=30&op2=40";
req.emit('data', queryinURL);
req.emit('end');
                            
                                                       