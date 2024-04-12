const url = require('url');
const tilde = require('tilde-expansion');
const path = require("path");
const fs = require('fs');
const querystring = require('querystring');

exports.route = route;

function route(request, response) {
    var pathname = decodeURI(url.parse(request.url).pathname);
    var words = pathname.split('/'); // words[0] is '' and words[1] is '~...' because pathname starts with '/'.
    if (words[1] != undefined && words[1][0] == '~') {  // e.g., /~tom/...
        tilde(words[1], function (expanded) {  // Note that a callback function is used.
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
    if (extension != 'sjs' && extension != 'php') {
        let content_type = decideContentType(extension);
        console.log("PATHNAME: " + pathname);

        fs.readFile(pathname, 'utf8', function (error, content) {
            if (error) {
                console.log("You can send a error message back.");
                res.writeHead(404, { 'Content-type': content_type });  // 4xx: client error
                res.write("Pathname: " + pathname + "<br>");
                res.write("content_type: " + content_type + "<br>");

                res.end("<html><body><h1>Not Found</h1><p>The requested URL was not found on this server.</p><hr></body></html>");
            }
            else {
                console.log("You can send the file content back.");
                console.log(content);
                res.writeHead(200, { 'Content-type': content_type });  // 200: OK; important
                res.write(content);
                res.write("Pathname: " + pathname + "<br>");
                res.write("content_type: " + content_type + "<br>");
                res.end();
            }
        });
    }
    else if (extension == 'sjs') {

        var _POST = {};
        var _GET = {};

        if (req.method == "GET") {
            var query = decodeURI(url.parse(req.url).query);
            console.log("query: " + query);
            var _GET = querystring.parse(decodeURI(url.parse(req.url).query));
            console.log("_GET: ", _GET);
            proceed_sjs(_GET, _POST, pathname)
        }
        else if (req.method == "POST") {

            var query = "";

            req.on('data', function (chunk) {
                query += chunk;  // add chunk to query
            });

            req.on('end', function () {
                _POST = querystring.parse(query)
                console.log(_POST)
                proceed_sjs(_GET, _POST, pathname)
            });

        }

        function proceed_sjs(_GET, _POST, pathname) {
            var ended = false;
            let str;

            try {
                var sjs = require(pathname);

                sjs.proceed(_GET, _POST, function (message) {
                    
                    if (!ended) {

                        delete require.cache[require.resolve(pathname)];
                        ended = true;

                        console.log(message)
                        str = message;
                        msg = JSON.stringify(str)
                        res.write(msg)
                        res.end()

                    }
                });
            }
            catch (err) {
                console.log("Internal error");
                delete require.cache[require.resolve(pathname)];
            }

        }

    }


    // finish
    // res.end();
}

function decideContentType(extension) {
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


