<?php

    $conn = mysqli_connect('localhost', 'f3matanacio', 'f3matanacio136', 'C354_f3matanacio');

    function is_valid($u, $p) {
        global $conn;
        $sql = "SELECT Username, Password FROM Users WHERE(Username = '$u' AND Password = '$p')";
        $result = mysqli_query($conn, $sql);
        if (mysqli_num_rows($result) > 0)
            return true;
        else
            return false;
    }

    function register($u, $p, $email) {
        global $conn;
        $sql = "SELECT Username FROM Users WHERE(Username = '$u')";
        $result = mysqli_query($conn, $sql);
        if (mysqli_num_rows($result) > 0) {
            return false;
        }
        else {
            $current_date = date("Ymd");
            $sql2 = "INSERT INTO Users VALUES (NULL, '$u', '$p', '$email', $current_date)";
            $result2 = mysqli_query($conn, $sql2);
            return $result2;
        }
    }

    function postQuestion($q, $u) {
        global $conn;
        $current_date = date("Ymd");
        $sql3 = "INSERT INTO Questions VALUES (NULL, '$q', '$u', $current_date)";
        $result3 = mysqli_query($conn, $sql3);

        if($result3) {
            return true;
        }
        else {
            return false;
        }
        
        // return $result3;
    }

    function searchQuestion($search_term) {
        global $conn;
        $sql4 = "SELECT * FROM Questions WHERE Question LIKE '%$search_term%'";
        $result4 = mysqli_query($conn, $sql4);
        $questions = [];
        $i = 0;
        while ($row = mysqli_fetch_assoc($result4)) {
            $questions[$i] = $row;
            $i++;
        }
        return $questions;
    }


?>