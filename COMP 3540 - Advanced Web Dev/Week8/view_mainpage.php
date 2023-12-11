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

     #nav-buttons {
            position:absolute;
            width:80px;
            top:15px; left:calc(50% - 40px);
        }
        #nav-buttons > button {
            display:inline-block;
            width:80px;
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
  
     #blanket {
          display:none;
          position:absolute;
          top:0; left:0;
          width:100%; height:100%;
          background-color:LightGrey;
          opacity:0.5;
          z-index:998;
     }
      
     .modal label {
          display:inline-block; 
          width:80px;
          position:relative;
          left:5px;
     }

     .modal .cancel {
          position:absolute;
          left:5px; bottom:5px;
     }

     .modal .submit {
          position:absolute;
          right:5px; bottom:5px;
     }

</style>

<body>
     <div id="layout-title">
          <h1>TRU Questions and Answers - Main Page</h1>
     </div>

     <div id='layout-left'>
        <div id='nav-buttons'>
             <form id="signout-form" method="get" action="w7_controller_student.php" style="display:none"> 
               <input type="hidden" name="page" value="MainPage">
               <input type="hidden" name="command" value="SignOut">
             </form>
            <button class='button-nav' id='button-signout'>Sign Out</button><br><br>
            <button class='button-nav' id='button-postaquestion'>Post A Question</button><br><br>
            <button class='button-nav' id='button-searchquestions'>Search Questions</button><br><br>
        </div>
    </div>

     <div id="layout-right">
          <div id="blanket"></div>
          
          <!-- Post A Question -->
          <div class='modal' id='modal-postaquestion'>
            <h2 style='text-align:center'>Post A Question</h2>
            <hr>
            <br>
            <form method='get' action='w7_controller_student.php'>
                <input type='hidden' name='page' value='MainPage'>
                <input type='hidden' name='command' value='PostAQuestion'>
                <label for='question'>Question:</label>
                <input id='question' type='text' name='Question'> 
                
                <input class='cancel' id='cancel-question' type='button' value='Cancel'>
                <input class='submit' id='submit-question' type='submit'>
            </form>
        </div>

        <!-- Search Questions -->
        <div class='modal' id='modal-searchquestions'>
            <h2 style='text-align:center'>Search Questions</h2>
            <hr>
            <br>
            <form method='get' action='w7_controller_student.php'>
                <input type='hidden' name='page' value='MainPage'>
                <input type='hidden' name='command' value='SearchQuestions'>
                <label for='search-question'>Search:</label>
                <input id='search-question' type='text' name='Search-Question'> 
                
                <input class='cancel' id='cancel-search' type='button' value='Cancel'>
                <input class='submit' id='submit-search' type='submit'>
            </form>
        </div>

          <?php 
               if(!empty($data_to_be_displayed)) {
                    echo $data_to_be_displayed;
               }
          ?>

     </div>

</body>

<script>

     // Variables
     const signout_btn = document.getElementById('button-signout');
     const form = document.getElementById('signout-form');
     const postQuestion_btn = document.getElementById('button-postaquestion');
     const cancel_postQuestion_btn = document.getElementById('cancel-question');
     const searchQuestions_btn = document.getElementById('button-searchquestions');
     const cancel_searchQuestions_btn = document.getElementById('cancel-search');
     const blanket = document.getElementById('blanket');
     const postQuestion_modal = document.getElementById('modal-postaquestion');
     const searchQuestions_modal = document.getElementById('modal-searchquestions');

     // Timeout
     var timer = setTimeout(timeout, 10000);

     window.addEventListener('mousemove', event_listener_mousemove_or_keydown);
     window.addEventListener('keydown', event_listener_mousemove_or_keydown);
     window.addEventListener('unload', () => {
          form.submit();
     });

     function event_listener_mousemove_or_keydown() {
          clearTimeout(timer);
          timer = setTimeout(timeout, 10000);
     }

     function timeout() {
          form.submit();
     }
     
     // Blanket Event Listener
     blanket.addEventListener('click', () => {
        hide_searchquestions_modal_window();
        hide_postaquestion_modal_window();
     });

     // Submit Form
     signout_btn.addEventListener('click', () => {
          form.submit();
     });

     // Post A Question
     postQuestion_btn.addEventListener('click', () => {
          hide_searchquestions_modal_window();
          show_postaquestion_modal_window();
     });
     cancel_postQuestion_btn.addEventListener('click', () => {
          hide_postaquestion_modal_window();
     });

     // Search Questions
     searchQuestions_btn.addEventListener('click', () => {
          hide_postaquestion_modal_window();
          show_searchquestions_modal_window();
     });
     cancel_searchQuestions_btn.addEventListener('click', () => {
          hide_searchquestions_modal_window();
     });

     // Functions for Modal Windows
     function show_postaquestion_modal_window() {
          blanket.style.display = 'block';
          postQuestion_modal.style.display = 'block';
     }

     function hide_postaquestion_modal_window() {
          blanket.style.display = 'none';
          postQuestion_modal.style.display = 'none';
     }

     function show_searchquestions_modal_window() {
          blanket.style.display = 'block';
          searchQuestions_modal.style.display = 'block';
     }      

     function hide_searchquestions_modal_window() {
          blanket.style.display = 'none';
          searchQuestions_modal.style.display = 'none';
     }
    




</script>

</html>