<!DOCTYPE html>
<html>
<head>
    <style>
        #layout-title {
            position:absolute;
            width:100%; height:80px;
            top:0; left:0;
            text-align:center;
            background-color:Beige;
        }
        #layout-left {
            position:absolute;
            top:80px; left:0;
            width:100px; height:calc(100vh - 80px);
            background-color:AliceBlue;
        }
        #layout-right {
            position:absolute;
            top:80px; left:100px;
            width:calc(100vw - 100px); height:calc(100vh - 80px);
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
        #blanket {
            display:none;
            position:absolute;
            top:0; left:0;
            width:100%; height:100%;
            background-color:LightGrey;
            opacity:0.5;
            z-index:998;
        }
        .modal {
            display:none;
            position:absolute;
            width:400px; height:300px;
            top:calc(50% - 150px); left:calc(50% - 200px);
            border:1px solid black;
            background-color:White;
            z-index:999;
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
</head>
<body>
    <div id='layout-title'>
        <h1>TRU Questions and Answers</h1>
    </div>
    
    <div id='layout-left'>
        <div id='nav-buttons'>
            <button class='button-nav' id='button-signin'>Sign In</button><br><br>
            <button class='button-nav' id='button-signup'>Sign Up</button>
        </div>
    </div>
    
    <div id='layout-right'>
        <div id='blanket'></div>

        <!-- SignIn -->
        <div class='modal' id='modal-signin'>
            <h2 style='text-align:center'>Sign In</h2>
            <hr>
            <br>
            <form method='get' action='w7_controller_student.php'>
                <input type='hidden' name='page' value='StartPage'>
                <input type='hidden' name='command' value='SignIn'>
                <label for='signin-username'>Username:</label>
                <input id='signin-username' type='text' name='Username'> 
                <?php if (!empty($error_msg_username_signin)) echo $error_msg_username_signin; ?><br><br>
                <label for='signin-password'>Password:</label>
                <input id='signin-password' type='password' name='Password'> 
                <?php if (!empty($error_msg_password_signin)) echo $error_msg_password_signin; ?><br>
                <input class='cancel' id='cancel-signin' type='button' value='Cancel'>
                <input class='submit' id='submit-signin' type='submit'>
            </form>
        </div>

        <!-- SignUp -->
        <div class='modal' id='modal-signup'>
            <h2 style='text-align:center'>Sign Up</h2>
            <hr>
            <br>
            <form method='get' action='w7_controller_student.php'>
                <input type='hidden' name='page' value='StartPage'>
                <input type='hidden' name='command' value='SignUp'>
                <label for='signup-username'>Username:</label>
                <input id='signup-username' type='text' name='Username'> 
                <?php if (!empty($error_msg_username_signup)) echo $error_msg_username_signup; ?><br><br>
                <label for='signup-password'>Password:</label>
                <input id='signup-password' type='password' name='Password'> <br><br>
                <label for='signup-email'>Email:</label>
                <input id='signup-email' type='text' name='Email'> 
                <input class='cancel' id='cancel-signup' type='button' value='Cancel'>
                <input class='submit' id='submit-signup' type='submit'>
            </form>
        </div>

    </div>
</body>

<script>

    // Sign In
    document.getElementById('button-signin').addEventListener('click', function() {
        hide_signup_modal_window();
        show_signin_modal_window();
    });
    document.getElementById('cancel-signin').addEventListener('click', function() {
        hide_signin_modal_window();
    });

    // Sign Up
    document.getElementById('button-signup').addEventListener('click', function() {
        hide_signin_modal_window();
        show_signup_modal_window();
    });
    document.getElementById('cancel-signup').addEventListener('click', function() {
        hide_signup_modal_window();
    });

    document.getElementById('blanket').addEventListener('click', function() {
        hide_signin_modal_window();
        hide_signup_modal_window();
    });

    // Sign In
    function show_signin_modal_window() {
        document.getElementById('blanket').style.display = 'block';
        document.getElementById('modal-signin').style.display = 'block';
    }
    
    function hide_signin_modal_window() {
        document.getElementById('blanket').style.display = 'none';
        document.getElementById('modal-signin').style.display = 'none';
    }
    
    // Sign Up
    function show_signup_modal_window() {
        document.getElementById('blanket').style.display = 'block';
        document.getElementById('modal-signup').style.display = 'block';
    }
    
    function hide_signup_modal_window() {
        document.getElementById('blanket').style.display = 'none';
        document.getElementById('modal-signup').style.display = 'none';
    }
    
    <?php
        if (!empty($display_modal_window)) {
            if ($display_modal_window == 'signin')
                echo 'show_signin_modal_window();';  // echo JavaScript code
            else if ($display_modal_window == 'signup')
                echo 'show_signup_modal_window();';
        }
    ?>
</script>

</html>