<?php
require_once 'settings.php';



if (isset($_POST["submit"])) {

  if (isset($_POST["window"])) {
    $window=$_POST["window"];
    setcookie("window", $window, time() + 60*60*24*365 , "/");
  }

  if (isset($_POST["distance"])) {
    $distance=htmlentities($_POST["distance"]);
    if ($distance >= 0 && $distance <= 500) {
      setcookie("distance", $distance , time() + 60*60*24*365 , "/");
    }
  }
  header("location: settings.php");
} else {
  header("location: settings.php");
  exit();
}
