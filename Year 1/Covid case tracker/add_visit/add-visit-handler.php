<?php
session_start();

require_once '../php/functions.php';
require_once '../php/database_handler.php';

if (isset($_POST["submit"])) {
  $usersId = $_SESSION['usersId'];

  $date=htmlentities($_POST["date"]);
  $time=htmlentities($_POST["time"]);
  $duration=htmlentities($_POST["duration"]);

  $xCoord=100;
  $yCoord=100;

  $dateSplitArray= explode("/", $date);
  $timeSplitArray= explode(":", $time);

  if (0 < $dateSplitArray[0] && $dateSplitArray[0] <=31 && 0 < $dateSplitArray[1] && $dateSplitArray[1] <= 12 && 2018 < $dateSplitArray[2] && $dateSplitArray[2]<=2100) {
    if (0 <= $timeSplitArray[0] && $timeSplitArray[0] <= 23 && 0 <= $timeSplitArray[1] && $timeSplitArray[1] <= 59) {
      addVisit($conn, $usersId, $date, $time, $duration, $xCoord, $yCoord);
    } else {
      header("location: add-visit.php?error=wrongtimeformat");
    }
  } else{
    header("location: add-visit.php?error=wrongdateformat");
  }

} else {
  header("location: add-visit.php");
  exit();
}
