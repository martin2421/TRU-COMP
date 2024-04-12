// $tjq library
const $tjq = x => {

    // Check if object is a DOCUMENT
    if (x == document) return {
        ready: function (callback) {
            document.addEventListener('DOMContentLoaded', () => {
                callback()
            })
        },
    }

    // Check if object is a STRING
    else if (typeof x == 'string') return {

        // click() function
        click: function (callback) {
            document.querySelector(x).addEventListener('click', (obj) => {
                callback(obj);
            });
        },

        // val() function
        val: function (val) {
            if (val !== undefined) {
                document.querySelector(x).value = val;
            } else {
                return document.querySelector(x).value;
            }
        },

        // css() function
        css: function (property, value) {
            if (value !== undefined) {
                document.querySelector(x).style[property] = value;
            } else {
                return document.querySelector(x).style[property];
            }
        },

        // html() function
        html: function (content) {
            if (content !== undefined) {
                document.querySelector(x).innerHTML = content;
            } else {
                return document.querySelector(x).innerHTML;
            }
        },

        // load() function (this is synchronous)
        load: function (url) {
            let xhttp = new XMLHttpRequest();
            xhttp.addEventListener('load', () => {
                document.querySelector(x).innerHTML = xhttp.responseText;
            });
            xhttp.open('GET', url);
            xhttp.send();
        }
    }
    
}

// get() (this is asynchronous)
$tjq.get = function (url, cb) {
    let xhttp = new XMLHttpRequest();
    xhttp.addEventListener('load', () => {
        console.log(typeof cb);
        cb(xhttp.responseText);
    });
    xhttp.open('GET', url);
    xhttp.send();
}

