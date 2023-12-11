<?php

session_start();

if (empty($_POST['page'])) {  
  // If 'page' value is not sent using the 'post' method,
  $display_modal_window = 'no-modal-window';
  include ('view_startpage_ass9.php');
  exit();
} else {
  // Include the model file
  include("model_ass9.php");

  if ($_POST['page'] == 'start_page') {
    $command = $_POST['command'];

    // using switch command
    switch($command) {
      case 'login':
        $username = $_POST['username'];
        $password = $_POST['password'];

        // validating the user name and password
        $result = username_password_valid($username, $password);
        if ($result) {
          $_SESSION['username'] = $username;
          include ('view_mainpage_ass9.php');
          exit();

        } else {
          // set the error message
          $error_msg_username = "error";
          $error_msg_password = "error";
          $display_modal_window = 'signin';
          include ('view_startpage_ass9.php');
        }
        break;

      case 'signup':
        $username = $_POST['username'];
        $password = $_POST['password'];
        $email = $_POST['email'];

        // validating the user name and password
        $result = username_exists($username);
        if ($result) {
          $error_msg_username_signup = "account already exist";
          $display_modal_window = 'signup';
          include ('view_startpage_ass9.php');

        } else {
          signup_a_new_user($username, $password, $email);
          $_SESSION['username'] = $username;
          $display_modal_window = 'signin';
          include ('view_startpage_ass9.php');
          exit();
        }
        break;

      default:
    }
  } else if ($_POST['page'] == 'main_page') {
    if (!isset($_SESSION['username'])) {
      include ('view_startpage_ass9.php');
      exit();
    }

    $command = $_POST['command'];
    $username = $_SESSION['username'];

    // using switch command
    switch($command) {
      case 'signOut':
        session_unset();
        session_destroy();
        $display_modal_window = 'signout';
        include ('view_startpage_ass9.php');
        exit();

      case 'SearchFriends':
        $search_term = $_POST['term'];
        $rows = searchUser($search_term); // get the linear array of rows in the Users table
        include('view_mainpage_ass9.php');
        exit();

      case 'SendMessage':
        $receiver = $_POST['receiver'];
        $message = $_POST['message'];

        // save message
        $result = save_message($username, $receiver, $message);
        echo $result;
        exit();

      case 'ReadMessages':
        // read messages
        $receiver = $username;
        $result = read_messages($receiver);
        var_dump($result);
        exit();

      default:
    }

  }
}
?>
