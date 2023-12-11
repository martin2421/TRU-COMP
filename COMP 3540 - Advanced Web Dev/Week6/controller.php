<?php

$page = $_POST['page'];

if (empty($_POST['page'])) {
     $display_modal_window = 'StartPage';
     include('view_startpage.php');
     exit();
} else if ($page == 'StartPage') { // If the data came from StartPage

     $command = $_POST['command'];

     switch ($command) {
          case 'SignIn':
               if (is_valid($_POST['Username'], $_POST['Password'])) {
		          $display_modal_window = 'main-page';
                    include("view_mainpage.php");
                    exit();
               } else {
                    $display_modal_window = 'sign-in';
                    include("view_startpage.php");
                    exit();
               }
          default:
               echo 'Unknown command<br>';
               exit();
     }
}
else {
	$page = $_POST['page'];
}

function is_valid($u, $p)
{
     return $u == 'comp3540' and $p == 'topsecretpassword';
}

?>