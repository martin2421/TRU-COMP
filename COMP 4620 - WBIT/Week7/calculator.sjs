const proceed = function (_GET, _POST, callback) {

     // Addition - GET
     if (_GET != undefined && _GET['operation'] == 'add') {
          var result = Number(_GET['op1']) + Number(_GET['op2']);
          callback(result);
     }
     // Subtraction - GET
     if (_GET != undefined && _GET['operation'] == 'sub') {
          var result = Number(_GET['op1']) - Number(_GET['op2']);
          callback(result);
     }
     // Multiplication - GET
     if (_GET != undefined && _GET['operation'] == 'mult') {
          var result = Number(_GET['op1']) * Number(_GET['op2']);
          callback(result);
     }
     // Division - GET
     if (_GET != undefined && _GET['operation'] == 'div') {
          var result = Number(_GET['op1']) / Number(_GET['op2']);
          callback(result);
     }

     // Addition - POST
     if (_POST != undefined && _POST['operation'] == 'add') {
          var result = Number(_POST['op1']) + Number(_POST['op2'])
          callback(result)
     }
     // Subtraction - POST
     if (_POST != undefined && _POST['operation'] == 'sub') {
          var result = Number(_POST['op1']) - Number(_POST['op2'])
          callback(result)
     }
     // Multiplication - POST
     if (_POST != undefined && _POST['operation'] == 'mult') {
          var result = Number(_POST['op1']) * Number(_POST['op2'])
          callback(result)
     }
     // Division - POST
     if (_POST != undefined && _POST['operation'] == 'div') {
          var result = Number(_POST['op1']) / Number(_POST['op2'])
          callback(result)
     }

}

exports.proceed = proceed;