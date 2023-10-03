<?php

if (isset($_POST['submit'])) {

require_once '../php/functions.php';
require_once '../php/database_handler.php';
/*test if user got into the file through the button*/
$firstname =htmlentities($_POST['firstname']);
$lastname =htmlentities($_POST['lastname']);
$username =htmlentities($_POST['username']);
$password =htmlentities($_POST['password']);

if (isset($lastname)) {
  $name = $firstname. " " .$lastname;
}

if (emptyInputSignup($firstname, $username, $password) !==true) {
  /*redirect back to registration, implement error message later*/
  header("location: registration.php?error=empty_input_signup");
  exit();/*for safety reasons*/
}

if (passwordVerifyLength($password) !== true || passwordVerifyCharacters($password) !== true) {
  /*redirect back to registration, implement error message later*/
  header("location: registration.php?error=password_format_incorrect");
  exit();/*for safety reasons*/
}

if (uidExists($conn, $username) !== false){
  header("location: registration.php?username_exists");
  exit();
}

createUser($conn, $name, $username, $password);

} else {
  header("location: registration.php");
}
