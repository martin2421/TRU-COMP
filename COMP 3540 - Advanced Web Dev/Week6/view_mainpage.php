<!DOCTYPE html>
<html lang="en">

<head>
     <meta charset="UTF-8">
     <meta name="viewport" content="width=device-width, initial-scale=1.0">
     <title>Main Page</title>
</head>

<style>
     #layout-title {
          position: absolute;
          width: 100%;
          height: 80px;
          top: 0;
          left: 0;
          text-align: center;
          background-color: Beige;
     }

     #layout-left {
          position: absolute;
          top: 80px;
          left: 0;
          width: 100px;
          height: calc(100vh - 80px);
          background-color: AliceBlue;
     }

     #layout-right {
          position: absolute;
          top: 80px;
          left: 100px;
          width: calc(100vw - 100px);
          height: calc(100vh - 80px);
     }

     .modal {
          display: none;
          position: absolute;
          width: 400px;
          height: 300px;
          top: calc(50% - 150px);
          left: calc(50% - 200px);
          border: 1px solid black;
          background-color: White;
          z-index: 999;
     }
</style>

<body>
     <div id="layout-title">
          <h1>TRU Questions and Answers - Main Page</h1>
     </div>

     <div id="layout-left">

     </div>

     <div id="layout-right">
          <div id="blanket"></div>
          <div class="modal" id="modal-signin">
          </div>
     </div>
</body>

</html>