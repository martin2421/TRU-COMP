// E1
// let pathname0 = '/csclub';  // => "/var/www/html/csclub"
// let words = pathname0.split('/');
// console.log(words);

// let pathname1 = '/~comp3540test/';  // => "/home2/students/comp3540/public_html"
// console.log(pathname1.includes('~'));


// E2
// let tilde = require('tilde-expansion');

// let pathname1 = '/~comp3540test/';  // => "/home2/students/comp3540/public_html"
// let new_pathname;

// console.log(pathname1);
// let words = pathname1.split('/');
// console.log(words);
// if (words[1] != undefined && words[1][0] == '~') {  // you will need to check words[???]
//     tilde(words[1], expanded => {
//         new_pathname = expanded + '/public_html';  // by default, all resources are under public_html
//         for (let i = 2; i < words.length; i++)  // why?
//             new_pathname += "/" + words[i];
//     });
// }
// else 
//     ;
// console.log(new_pathname);


// E3
// let tilde = require('tilde-expansion');

// let pathname0 = '/csclub';  // => "/var/www/html/csclub"
// let pathname1 = '/~comp3540test/';  // => "/home2/students/comp3540/public_html"
// let new_pathname;

// // test with pathname0 and pathname1
// console.log(pathname0);
// let words = pathname0.split('/');
// console.log(words);
// if (words[1] != undefined && words[1][0] == '~') {
//     tilde(words[1], expanded => {
//         new_pathname = expanded + "/public_html"; 
//         for (let i = 2; i < words.length; i++)
//             new_pathname += "/" + words[i];
//     });
// }
// else {
//     new_pathname = '/var/www/html' + pathname0;  // under the document root directory
// }
// console.log(new_pathname);
                            

// E4
// let tilde = require('tilde-expansion');

// let pathname0 = '/csclub';  // => "/var/www/html/csclub"
// let pathname1 = '/~comp3540test/';  // => "/home2/students/comp3540/public_html"

// // test with pathname0 and pathname1
// console.log(pathname0);
// let words = pathname0.split('/');
// console.log("words[1]: " + words[1]);
// if (words[1] != undefined && words[1][0] == '~') {
//     tilde(words[1], expanded => {
//         let new_pathname = expanded + "/public_html";
//         for (let i = 2; i < words.length; i++)
//             new_pathname += "/" + words[i];
//             proceed_with_new_pathname(new_pathname);
//     });
// }
// else {
//     let new_pathname = '/var/www/html' + pathname0;
//     proceed_with_new_pathname(new_pathname);
// }

// function proceed_with_new_pathname(pathname) {
//     console.log(pathname);
// }
                            

// E5
// let pathname0 = '/var/www/html/csclub';  // => "/var/www/html/csclub"
// let pathname1 = '/home2/students/comp3540/public_html/';  // => "/home2/students/comp3540/public_html/index.html"

// // test with pathname0 and pathname1
// let pathname = pathname0;
// console.log(pathname);
// if(pathname.endsWith('/'))
//     pathname += 'index.html';
// console.log(pathname);


// E6
// const path = require("path");  // reference: Node.js Path Module

// let pathname0 = '/var/www/html/csclub';  // => "/var/www/html/csclub/index.html"
// let pathname1 = '/home2/students/comp3540/public_html/test.html';  // => "/home2/students/comp3540/public_html/test.html"

// // test with pathname0 and pathname1
// let pathname = pathname1;
// console.log(pathname);

// let fileordirname = path.basename(pathname);
// console.log(fileordirname);
// let words = fileordirname.split('.');  // to see if '.' is in the basename
// console.log("words: " + words)
// let extension;
// if (words.length == 1) {  // if no '.',
//     pathname += '/index.html';
//     extension = 'html';
// } else
//     extension = words[-1];  // the last one
// console.log(pathname);
// console.log(extension);


// E7
// let extension = 'html';  // test with others as well, such as js, css and bmp.
// let content_type = decideContentType(extension);
// console.log(extension + ": " + content_type);

// function decideContentType(extension) 
// {
//     switch (extension) {
//         case "js":
//             return "application/js";
//         case "json":
//             return "application/json";
//         case "xml":
//             return "application/xml";
//         case "pdf":
//             return "application/pdf";
//         case "zip":
//             return "application/zip";
//         case "css":
//             return "text/css";
//         case "html":
//             return "text/html";
//         case "bmp":
//             return "image/bmp";
//         case "jpeg":
//             // In many cases, "jpg" is more commonly used instead of "jpeg"
//             return "image/jpeg";
//         // If the extension is not recognized, default to plain text
//         default:
//             return "text/plain";
//     }
// }
             

// E8
// let fs = require('fs');

// let pathname = 'test.js';  // for testing

// fs.readFile(pathname, 'utf8', function(error, content) {
//     if (error)
//         console.log(error);
//     else
//         console.log(content);
// });

                            
 
// E9
// let fs = require('fs');

// let pathname = 'test.js'; 

// fs.readFile(pathname, 'utf8', function(error, content) {
//     if (error) {
//         console.log("You can send a error message back.");
//         /*
//             response.writeHead(404, {'Content-type': content_type});  // 4xx: client error
//             response.end("<html><body><h1>Not Found</h1><p>The requested URL was not found on this server.</p><hr></body></html>");
//         */
//     }
//     else {
//         console.log("You can send the file content back.");
//         console.log(content);
//         /*
//             response.writeHead(200, {'Content-type': content_type});  // 200: OK; important
//             response.write(content);
//             response.end();
//         */
//     }
// });