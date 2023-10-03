<?php
setcookie("is_logged_in", "true", time() -1 , "/");

session_start();
session_unset();
session_destroy();
header("location: ../login/login.php");
exit();
