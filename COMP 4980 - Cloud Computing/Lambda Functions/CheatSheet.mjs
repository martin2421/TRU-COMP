// EVENT OBJECT PROPERTIES
export const handler = async (event) => {

     // returns "GET" or "POST"
     event.requestContext.http.method; 

     // returns "/path"
     event.rawPath; // from HTTP API
     event.path; // from REST API

     // returns the host
     event.headers["host"];

     // returns query as object {name: value, name2: value2}
     event.queryStringParameters;

     // returns query as string (name=value&name2=value2)
     event.rawQueryString;

     // returns body of the event
     event.body;

     // returns body of a POST request decoded from base64
     // querystring.parse() is used to convert the string to an object
     querystring.parse(atob(event.body));

}