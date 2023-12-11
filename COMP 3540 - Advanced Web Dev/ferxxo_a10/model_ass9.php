<?php
$conn = mysqli_connect('localhost', 'w3fferrufino', 'w3fferrufino136', 'C354_w3fferrufino');


function username_password_valid($u, $p) {
    global $conn;
    $sql = "select * from Users where Username = '$u' and Password = '$p' "; 
    $result = mysqli_query($conn, $sql);
    $row = mysqli_num_rows($result);
    if ($row>0){
        return true;
    }
    else{
        return false;
    }

}

function username_exists($u){
    global $conn;
    $sql = "select * from Users where Username = '$u' "; 
    $result = mysqli_query($conn, $sql);
    $row = mysqli_num_rows($result);
    if ($row>0){
        return true;
    }
    else{
        return false;
    }
}

function signup_a_new_user($u, $p, $e){
    global $conn;
    $current_date = date("Ymd");
    $sql = "INSERT INTO Users VALUES (null, '$u', '$p', '$e', $current_date)";  
    $result = mysqli_query($conn,$sql);
    return $result;

}

// Model



function searchUser($search_term) {
    global $conn;
    $sql = "SELECT * FROM Users WHERE Username Like '%$search_term%'";
    $result = mysqli_query($conn, $sql);
    $users = [];
    $i = 0;
    while ($row = mysqli_fetch_assoc($result)) {
        $users[$i] = $row;
        $i++;
    }
    return $users;
}

function save_message($sender, $receiver, $message) {
    global $conn;
    $current_date = date("Ymd");
    $sql = "INSERT INTO Messages (Sender, Receiver, Message, Date, ReadOrNot) 
            VALUES ('$sender', '$receiver', '$message', $current_date, 0)";
    $result = mysqli_query($conn, $sql);
    return $result;
}

function read_messages($receiver) {
    global $conn;
    $sql = "SELECT * FROM Messages WHERE Receiver='$receiver' AND ReadOrNot=0";
    $result = mysqli_query($conn, $sql);
    $messages = [];
    $i = 0;
    while ($row = mysqli_fetch_assoc($result)) {
        $messages[$i] = $row;
        $i++;
    }
    // Mark selected rows as 'read'
    $sql = "UPDATE Messages SET ReadOrNot=1 WHERE Receiver='$receiver' AND ReadOrNot=0";
    mysqli_query($conn, $sql);
    return $messages;
}





?>
