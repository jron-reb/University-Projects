<?php
require_once 'database_handler.php';

function emptyInputSignup($firstname, $username, $password){
  $result;
  if (empty($firstname) || empty($username) || empty($password)) {
    $result = false;
  } else {
    $result = true;
  }
  return $result;
}

function passwordVerifyCharacters($password) {
  $result;
  /*checks if password contains any characters not a-z, A-Z or 0-9*/
  if (!preg_match("/^[a-zA-Z0-9]*$/", $password)) {
    $result=false;
  } else {
    $result=true;
  }
  return $result;
}

function passwordVerifyLength($password) {
  $result;
  if (strlen($password) < 8) {
    $result = false;
  } else {
    $result = true;
  }
  return $result;
}

function uidExists($conn, $username) {
  $sql = "SELECT * FROM users WHERE usersUid = ?;";
  $stmt = mysqli_stmt_init($conn);

  if (!mysqli_stmt_prepare($stmt, $sql)) {
    ("location: registration.php?error=stmtfailed");
    exit();
  }

  mysqli_stmt_bind_param($stmt, "s", $username);
  mysqli_stmt_execute($stmt);

  $resultData = mysqli_stmt_get_result($stmt);

  if ($row = mysqli_fetch_assoc($resultData)) {
    return $row;
  } else {
    $result = false;
    return $result;
  }

  mysqli_stmt_close($stmt);
}


function createUser($conn, $name, $username, $password) {
  $sql = "INSERT INTO users (usersName, usersUid, usersPwd) VALUES (?, ?, ?);";
  $stmt = mysqli_stmt_init($conn);

  if (!mysqli_stmt_prepare($stmt, $sql)) {
    header("location: registration.php?error=stmtfailed");
    exit();
  }

  $options = [
    'cost' => 10,
  ];

  $hashedPassword = password_hash($password, PASSWORD_BCRYPT, $options);

  mysqli_stmt_bind_param($stmt, "sss", $name, $username, $hashedPassword);
  mysqli_stmt_execute($stmt);
  mysqli_stmt_close($stmt);
  /*settings expiry set to 1 year
  Settings have been set to default values of 1 week and a eculidean distance of 100*/
  setcookie("window", "1 week", time() + 60*60*24*365 , "/");
  setcookie("distance", 100 , time() + 60*60*24*365 , "/");
  header("Location: ../login/login.php?");
  exit();
}

function emptyInputLogin($username, $password){
  $result;
  if (empty($username) || empty($password)) {
    $result = false;
  } else {
    $result = true;
  }
  return $result;
}

function logInUser($conn, $username, $password){
  $uidExists = uidExists($conn, $username);

  if ($uidExists === false) {
    header("location: login.php?error=false_login");
    exit();
  }

  $passwordHashed = $uidExists["usersPwd"];


  $checkpassword = password_verify($password, $passwordHashed);

  if ($checkpassword === false) {
    header("location: login.php?error=false_login");
    exit();
  } elseif ($checkpassword === true) {
    /*expiry time set for a day*/
    setcookie("is_logged_in", "true", time() + 60*60*24 , "/");

    session_start();
    /*creating session variable*/
    $_SESSION['usersname'] = $uidExists["usersName"];
    $_SESSION['usersId'] = $uidExists["usersId"];

    header("location: ../home/home.php");
    exit();
  }
}

function reportInfection($conn, $usersId, $date, $time){
  $sql = "INSERT INTO infections (usersId, usersDate, usersTime) VALUES (?, ?, ?);";
  $stmt = mysqli_stmt_init($conn);

  if (!mysqli_stmt_prepare($stmt, $sql)) {
    header("location: report.php?error=stmtfailed");
    exit();
  }

  mysqli_stmt_bind_param($stmt, "iss", $usersId, $date, $time);
  mysqli_stmt_execute($stmt);
  mysqli_stmt_close($stmt);
  header("location: ../report/report.php");
  exit();
}

function addVisit($conn, $usersId, $date, $time, $duration, $xCoord, $yCoord){
  $sql = "INSERT INTO visits (usersId, visitDate, visitTime, visitDuration, visitXCoord, visitYCoord) VALUES (?, ?, ?, ?, ?, ?);";
  $stmt = mysqli_stmt_init($conn);

  if (!mysqli_stmt_prepare($stmt, $sql)) {
    header("location: report.php?error=stmtfailed");
    exit();
  }

  mysqli_stmt_bind_param($stmt, "isssii", $usersId, $date, $time, $duration, $xCoord, $yCoord);
  mysqli_stmt_execute($stmt);
  mysqli_stmt_close($stmt);
  header("location: add-visit.php");
  exit();
}
