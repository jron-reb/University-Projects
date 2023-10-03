<?php
$server = "localhost";
$user = "root";
$passwd = "";
$db = "phpproject01";

//Create connection
$conn = mysqli_connect($server, $user, $passwd, $db);

if (!$conn) {
  die("Connection failed: " .mysqli_connect_error());
}
