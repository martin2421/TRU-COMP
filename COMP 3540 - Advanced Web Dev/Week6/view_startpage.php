<!DOCTYPE html>
<html>

<head>
    <title>Login Page</title>
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
        position: absolute;
        width: 80px;
        top: 15px;
        left: calc(50% - 40px);
    }

    #nav-buttons>button {
        display: inline-block;
        width: 80px;
    }

    #blanket {
        display: none;
        position: absolute;
        top: 0;
        left: 0;
        width: 100%;
        height: 100%;
        background-color: LightGrey;
        opacity: 0.5;
        z-index: 998;
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

    .modal label {
        display: inline-block;
        width: 80px;
        position: relative;
        left: 5px;
    }

    #modal-signin #cancel-signin {
        position: absolute;
        left: 5px;
        bottom: 5px;
    }

    #modal-signin #submit-signin {
        position: absolute;
        right: 5px;
        bottom: 5px;
    }
</style>

<body>
    <div id="layout-title">
        <h1>TRU Questions and Answers</h1>
    </div>

    <div id="layout-left">
        <div id="nav-buttons">
            <button class="button-nav" id="button-signin">Sign In</button><br><br>
            <button class="button-nav" id="button-signup">Sign Up</button>
        </div>
    </div>

    <div id="layout-right">
        <div id="blanket"></div>
        <div class="modal" id="modal-signin">
            <h2 style="text-align:center">Sign In</h2>
            <hr>
            <br>
            <form method="post" action="controller.php">
                <input type="hidden" name="page" value="StartPage">
                <input type="hidden" name="command" value="SignIn">
                <label for="signin-username">Username:</label>
                <input type="text" name="Username"><br><br>
                <label for="signin-password">Password:</label>
                <input type="password" name="Password"><br>
                <input id="cancel-signin" type="button" value="Cancel">
                <input id="submit-signin" type="submit">
            </form>
        </div>
    </div>
</body>

<script>

    document.getElementById("button-signin").addEventListener("click", function () {
        document.getElementById("blanket").style.display = "block";
        document.getElementById("modal-signin").style.display = "block";
    });

    document.getElementById("cancel-signin").addEventListener("click", function () {
        document.getElementById("blanket").style.display = "none";
        document.getElementById("modal-signin").style.display = "none";
    });

    document.getElementById("blanket").addEventListener("click", function () {
        document.getElementById("blanket").style.display = "none";
        document.getElementById("modal-signin").style.display = "none";
        //document.getElementById("modal-signup").style.display = "none";
    });

    function show_signin_modal_window() {
        document.getElementById("modal-signin").style.display = "block";
        document.getElementById("blanket").style.display = "block";
    }

    <?php
    if ($display_modal_window == 'StartPage') {

    } else if ($display_modal_window == 'sign-in') {
        echo "show_signin_modal_window();";
    }

    ?>

</script>

</html>