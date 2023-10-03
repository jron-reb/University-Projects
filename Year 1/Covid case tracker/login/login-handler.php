<?php
require_once '../php/functions.php';
require_once '../php/database_handler.php';

if (isset($_POST["submit"])) {

  $username=htmlentities($_POST["username"]);
  $password=htmlentities($_POST["password"]);


  if (emptyInputLogin($username, $password) !==true) {
    /*redirect back to registration, implement error message later*/
    header("location: login.php?error=empty_input_login");
    exit();/*for safety reasons*/
  }

  logInUser($conn, $username, $password);


} else {
  header("location: login.php");
  exit();
}
