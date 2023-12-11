<?php

function addition($num1, $num2) {
    return $num1 + $num2;
}
function subtraction($num1, $num2) {
    return $num1 - $num2;
}
function multiplication($num1, $num2) {
    return $num1 * $num2;
}
function division($num1, $num2) {
    return $num1 / $num2;
}


if ($_GET['operation'] == 'addition')
    echo addition($_GET['op1'], $_GET['op2']); 

if ($_GET['operation'] == 'subtraction')
    echo subtraction($_GET['op1'], $_GET['op2']);

if ($_GET['operation'] == 'multiplication')
    echo multiplication($_GET['op1'], $_GET['op2']);

if ($_GET['operation'] == 'division')
    echo division($_GET['op1'], $_GET['op2']);


?>