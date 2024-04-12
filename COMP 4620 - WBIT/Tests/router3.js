// var url = require("url"); // reference: Node.js URL Module
// var tilde = require('tilde-expansion');
// var path = require('path');

// function route(request, response) {

//     var pathname = decodeURI(url.parse(request.url).pathname); // decodeURI(): a JS built-in function
//                                                                // reference: JavaScript decodeURI()

//     var words = pathname.split('/'); // words[0] is '' and words[1] is '~...' because pathname starts with '/'.

//     // Case 1
//     if(words[1] != undefined && words[1][0] == '~') {
//         tilde(words[1], function(expanded) {
//             var new_pathname = path.join(expanded, 'public_html'); // Use path.join to construct the new pathname
//             for(var i = 2; i < words.length; i++) {
//                 new_pathname = path.join(new_pathname, words[i]); // Use path.join for joining pathname segments
//             }
//             proceed_with_resolved_pathname(request, response, new_pathname);
//         });
//     }

//     // Case 2
//     else {
//         var new_pathname = "/var/www/html" + pathname;
//         proceed_with_resolved_pathname(request, response, new_pathname);
//     }

//     const proceed_with_resolved_pathname = function(request, response, pathname) {
//         response.writeHead(200, {'Content-Type': 'text/html'});  // Plain text, not html text, will be sent back to the client
//         response.write("<h1>Hello World! - router3.js</h1><br>");
//         response.write(pathname + "<br>");
//         response.end();
//     }
    
// }
// exports.route = route;





const url = require('url');
const tilde = require('tilde-expansion');
const path = require("path");
const fs = require('fs');
exports.route = route;

function route(request, response)
{
    var pathname = decodeURI(url.parse(request.url).pathname);
    var words = pathname.split('/'); // words[0] is '' and words[1] is '~...' because pathname starts with '/'.
    if (words[1] != undefined && words[1][0] == '~') {  // e.g., /~tom/...
        tilde(words[1], function(expanded) {  // Note that a callback function is used.
            var new_pathname = expanded + '/public_html';  // 'public_html' should be included.
            for (var i = 2; i < words.length; i++)
                new_pathname += '/' + words[i];
            proceed_with_resolved_pathname(request, response, new_pathname);
        });
    }
    else {
        var new_pathname = "/var/www/html" + pathname;
        proceed_with_resolved_pathname(request, response, new_pathname);
    }
}

function proceed_with_resolved_pathname(req, res, pathname) {

    // Ends with '/'
    if (pathname.endsWith("/"))
        pathname = pathname + "index.html";

    let fileordirname = path.basename(pathname);
    let words = fileordirname.split(".");
    let extension;
    
    // Ends with an extension
    if (words.length > 1) {
        extension = words[words.length - 1];
    } else {
        pathname += '/index.html';
        extension = 'html';
    }


    // Non .sjs or .php file case
    if(extension != 'sjs' && extension != 'php') {
        let content_type = decideContentType(extension);
        console.log("PATHNAME: " + pathname);

        fs.readFile(pathname, 'utf8', function(error, content) {
            if (error) {
                console.log("You can send a error message back.");
                res.writeHead(404, {'Content-type': content_type});  // 4xx: client error
                res.write("Pathname: " + pathname + "<br>");
                res.write("content_type: " + content_type + "<br>");

                res.end("<html><body><h1>Not Found</h1><p>The requested URL was not found on this server.</p><hr></body></html>");
            }
            else {
                console.log("You can send the file content back.");
                console.log(content);
                res.writeHead(200, {'Content-type': content_type});  // 200: OK; important
                res.write(content);
                res.write("Pathname: " + pathname + "<br>");
                res.write("content_type: " + content_type + "<br>");
                res.end();
            }
        });
    }
 

    // finish
    // res.end();
}

function decideContentType(extension) 
    {
        switch (extension) {
            case "js":
                return "application/js";
            case "json":
                return "application/json";
            case "xml":
                return "application/xml";
            case "pdf":
                return "application/pdf";
            case "zip":
                return "application/zip";
            case "css":
                return "text/css";
            case "html":
                return "text/html";
            case "bmp":
                return "image/bmp";
            case "jpeg":
                // In many cases, "jpg" is more commonly used instead of "jpeg"
                return "image/jpeg";
            // If the extension is not recognized, default to plain text
            default:
                return "text/plain";
        }
    }


