<?php
session_start();
require_once '../php/functions.php';
require_once '../php/database_handler.php';

  $date =htmlentities($_POST['date']);
  $time =htmlentities($_POST['time']);
  $usersId = $_SESSION['usersId'];

  $dateSplitArray= explode("/", $date);
  $timeSplitArray= explode(":", $time);

  if (0 < $dateSplitArray[0] && $dateSplitArray[0] <=31 && 0 < $dateSplitArray[1] && $dateSplitArray[1] <= 12 && 2018 < $dateSplitArray[2] && $dateSplitArray[2]<=2100) {
    if (0 <= $timeSplitArray[0] && $timeSplitArray[0] <= 23 && 0 <= $timeSplitArray[1] && $timeSplitArray[1] <= 59) {
      reportInfection($conn, $usersId, $date, $time);
    } else {
      header("location: report.php?error=wrongtimeformat");
    }
  } else{
    header("location: report.php?error=wrongdateformat");
  }
