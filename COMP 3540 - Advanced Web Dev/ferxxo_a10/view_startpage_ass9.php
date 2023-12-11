<!DOCTYPE html>

<html>
<head>
    <title>TRU CS Messenger</title>
    <style>
        /*Layout*/
        #layout-main {
            position:relative; top:0; left:0;
            width:100vw; height:100vh; 
        }
        
        #layout-main-left {
            position:absolute; top:0; left:0;
            width:50%; height:100%; 
            background-color:LightGray; 
        }
        
        #layout-main-right {
            position:absolute; 
            top:0; left:50%;
            width:50%; height:100%; 
            background-color:SkyBlue; 
        }
        /*Content Layout*/
        #content-left{
            position: absolute;
            width: 90%;
            left: calc((100% - 90%)/2);

        }
        #content-right{
            position: absolute;
            width: 90%;
            left: calc((100% - 90%)/2);
        }
        /*blanket*/
        #blanket{
            display: none; /* not displayed */
            position: absolute;
            top: 0;
            left: 0;
            width: 100%;
            height: 100%;
            z-index: 9;
            opacity: 0.5;
            background-color:Grey;
        }
    



        #login{
            display: block;
            width: 100%;
            padding-top: 1em;
            padding-bottom: 1em;
            font-size: small;
            
        }
        #signup{
            display: block;
            width: 100%;
            padding-top: 1em;
            padding-bottom: 1em;
            font-size: small;

        }
        


        /*Modal windows*/
        .modal-window {
            width:400px; height:250px;
            border:1px solid black;
            
            display:none;
            background-color:White;
            position:absolute;
            top:calc((100% - 250px)/2);  left:calc(50vw - 200px);
            z-index:999;
        }

        #input{
            position: absolute;
            width: 90%;
            left: 2em;
        }
        input{
            width: 50%;
            left: calc((100% - 50%)/2);
            position: absolute;
        }
        
    </style>
</head>

<body style='margin:0'>

    <!-- Page Layout -->
    
    <div id='layout-main'>
        <div id='layout-main-left'>
            <div id='content-left' >
                <h2>Hear what people are questioning about</h2>
                <h2 >Join the conversation</h2>
                <button id="login">Login</button>
                <br>
                <button id="signup">Signup</button>

            </div>
        </div>
        <div id='layout-main-right'>
            <div id='content-right' style='position:absolute'>

                <img src='One TRU Logo.png' width='200px' height='50px' style='margin-left:50px'>
                <br>
                <h1 >See what's happening in the world</h1>
                <br>
                <h2>Join TRU Messenger</h2>
            </div>
        </div>
    </div>
    
    <!-- Modal Windows -->
    
    <div id='modal-login' class='modal-window'>
        <h2 style='text-align:center'>Login to TRU Messenger</h2>
        <br>
        <div id="input">
        <form action="controller_ass9.php" method="post">
        <input type="hidden" name="page" value="start_page">
        <input type="hidden" name="command" value="login">
        Username: <input type="text" name="username" required><?php if (!empty($error_msg_username)) { echo "<br><span class='Invalid username!'>Invalid username!</span>"; }else{"";} // Display error message if there is ?><br>
        <br>
        Password: <input type="password" name="password" required><?php if (!empty($error_msg_password)) { echo "<br><span class='Invalid password'>Invalid password!</span>"; }else{"";} // Display error message if there is ?><br>
        
    </div>
    <button type="reset" id='cancel-modal-login' style='position:absolute; bottom:10px; right:2em'>Cancel</button>
    <button type="submit" style='position:absolute; bottom:10px; left:2em'>Submit</button>
    </form>
    </div>
    <div id='modal-signup' class='modal-window'>
        <h2 style='text-align:center'>Signup to TRU Messenger</h2>
        <br>
        <div id="input">
            <form action="controller_ass9.php" method="post">
                <input type="hidden" name="page" value="start_page">
                <input type="hidden" name="command" value="signup">
                Username: <input type="text" name="username" required><?php if (!empty($error_msg_username_signup)) { echo "<br><span class='Invalid username!'>account already exists!</span>"; }else{"";} // Display error message if there is ?><br>
                <br>
                Password: <input type="password" name="password" required><br>
                <br>
                Email: <input type="email" name="email" required><br>
                
            </div>
                <button type="reset" id='cancel-modal-signup' style='position:absolute; bottom:10px; right:2em'>Cancel</button>
                <button type="submit" style='position:absolute; bottom:10px; left:2em'>Submit</button>
            </form>
        </div>
    <div id='blanket'>
    </div>



    
</body>
</html>

<script>

function show_signin() {
        document.getElementById('modal-login').style.display = 'block';
    }
    function show_signout() {
        document.getElementById('modal-login').style.display = 'none';
    }
    window.addEventListener('load', function() {
        
        <?php  // Can you study this code carefully?
            if ($display_modal_window == 'no-modal-window'){
                echo 'show_signout();';
            }  // The variable, $display_modal_window, is set in controller.php before include('view_startpage.php');
                
            else if ($display_modal_window == 'signin')
                echo 'show_signin();';
            else ;
        ?>
    });

    function show_signup() {
        document.getElementById('modal-signup').style.display = 'block';
    }
    function show_signout() {
        document.getElementById('modal-signup').style.display = 'none';
    }
    window.addEventListener('load', function() {
        
        <?php  // Can you study this code carefully?
            if ($display_modal_window == 'no-modal-window'){
                echo 'show_signout();';
            }  // The variable, $display_modal_window, is set in controller.php before include('view_startpage.php');
                
            else if ($display_modal_window == 'signup')
                echo 'show_signup();';
            else ;
        ?>
    });

    var contentLeft = document.getElementById('content-left');
    var contentRight = document.getElementById('content-right');
    var h = window.getComputedStyle(contentLeft).height;
    var h2 = window.getComputedStyle(contentRight).height;
    var t = 'calc((100% - '+ h +')/2)';
    var t2 = 'calc((100% - '+ h2 +')/2)';
    contentLeft.style.top = t;
    contentRight.style.top = t2;



    document.getElementById("login").addEventListener("click", function() {
        document.getElementById("modal-login").style.display = "block";
        document.getElementById("blanket").style.display = "block";
    });

    document.getElementById("signup").addEventListener("click", function() {
        document.getElementById("modal-signup").style.display = "block";
        document.getElementById("blanket").style.display = "block";
    });

    document.getElementById("cancel-modal-login").addEventListener("click", function() {
        document.getElementById("modal-login").style.display = "none";
        document.getElementById("blanket").style.display = "none";
    });

    document.getElementById("cancel-modal-signup").addEventListener("click", function() {
        document.getElementById("modal-signup").style.display = "none";
        document.getElementById("blanket").style.display = "none";
    });

    document.getElementById('blanket').addEventListener("click", function(){
        document.getElementById("modal-login").style.display = "none";
        document.getElementById("modal-signup").style.display = "none";
        blanket.style.display = 'none';
   })



</script>