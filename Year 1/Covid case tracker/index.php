<?php

if (isset($_COOKIE['is_logged_in']) && $_COOKIE['is_logged_in']==='true') {
  header('location: home/home.php');
} else {
  header('location: login/login.php');
}
