<?php

session_start();

if (empty($_GET['page'])) {
    $display_modal_window = 'no-modal-window';
    include('w7_view_startpage_student.php');
    exit();
}

include('Model.php');  // include Model

if ($_GET['page'] == 'StartPage') 
{
    $command = $_GET['command'];
    switch($command) {
        case 'SignIn':
            $username = $_GET['Username'];
            $password = $_GET['Password'];
            if (is_valid($username, $password)) {
                $_SESSION['signedin'] = "YES";
                $_SESSION['username'] = $username;
                include('view_mainpage.php');
            }
            else {
                $display_modal_window = 'signin';
                $error_msg_username_signin = '* Non-existing username or';
                $error_msg_password_signin = '* wrong password';
                include('w7_view_startpage_student.php');
            }
            break;
            
        case 'SignUp':
            $username = $_GET['Username'];
            $password = $_GET['Password'];
            $email = $_GET['Email'];

            if(register($username, $password, $email)) {
                $display_modal_window = 'signin';
                include('w7_view_startpage_student.php');
            } else {
                $_SESSION['username'] = $username; // ???????
                $display_modal_window = 'signup';
                $error_msg_username_signup = '<br>* Username already exists';
                include('w7_view_startpage_student.php');
            }

            break;
            
        default:
            break;
    }
    exit();
}

else if ($_GET['page'] == 'MainPage') 
{
    // if(!isset($_SESSION['username'])) {
    //     include('w7_view_startpage_student.php');
    //     exit();
    // }

    $command = $_GET['command'];
    switch ($command) {
        case 'SignOut':
            session_unset();
            session_destroy();
            $display_modal_window = 'no-modal-window';
            include('w7_view_startpage_student.php');
            break;

        case 'PostAQuestion':
            $username = $_SESSION['username'];
            $question = $_GET['Question'];
            $data_to_be_displayed = postQuestion($question, $username);
            include('view_mainpage.php');
            break;

        case 'SearchQuestions':
            $search_term = $_GET['Search-Question'];
            $rows = searchQuestion($search_term);
            $data_to_be_displayed = json_encode($rows);
            include('view_mainpage.php');
            exit();
            break;
        
        default:
            break;
    }
    exit();
}

else {
    echo 'Unknown page error!';
    exit();
}
?>