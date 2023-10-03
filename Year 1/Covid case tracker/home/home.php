<?php
  session_start();
?>

<!DOCTYPE html>
<html lang="en" dir="ltr">
  <head>
    <meta charset="utf-8">
    <title>COVID-CT: Home Page</title>
    <link rel="stylesheet" href="../css/master.css">
    <link rel="stylesheet" href="styles.css">
  </head>
  <body>
    <div class="content-container">
      <div class="h1-container">
        <h1>COVID - 19 Contact Tracing</h1>
      </div>

      <div class="watermark">
          <img class="watermark" src="../images/watermark.png" alt="Covid molecule">
      </div>

      <div class="navigation-bar-container">
        <nav class="navigation-bar">
          <ul class="navigation-bar">
            <li class="selected"><a href="../home/home.php">Home</a></li>
            <li class="not-selected"><a href="../overview/overview.php">Overview</a></li>
            <li class="not-selected"><a href="../add_visit/add-visit.php">Add Visit</a></li>
            <li class="not-selected"><a href="../report/report.php">Report</a></li>
            <li class="not-selected"><a href="../settings/settings.php">Settings</a></li>
            <li class="end not-selected"><a href="../php/logout.php">Logout</a></li>
          </ul>

        </nav>
      </div>

      <div class="status">
        <h3>Status</h3>
        <hr>
      </div>
      <div class="text infection-text">
        Hi <?php
        $name = $_SESSION['usersname'];
        echo "$name";
        ?>
        you might have had a connection to an infected person at the location shown in red.</div>
      <div class="text marker-text"> Click on the marker to see more details of infection.</div>

      <div class="map">
          <img class="exeter" src="../images/exeter.jpg" alt="Map of exeter">
      </div>

      <div class="marker">
          <img class="black_marker" src="../images/marker_black.png" alt="Black Marker">
      </div>

</div>

  </body>
</html>
