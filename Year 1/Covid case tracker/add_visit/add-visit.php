<?php
  session_start();
?>

<!DOCTYPE html>
<html lang="en" dir="ltr">
  <head>
    <meta charset="utf-8">
    <title>COVID-CT: Visits Overview</title>
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
            <li class="not-selected"><a href="../home/home.php">Home</a></li>
            <li class="not-selected"><a href="../overview/overview.php">Overview</a></li>
            <li class="selected"><a href="../add_visit/add-visit.php">Add Visit</a></li>
            <li class="not-selected"><a href="../report/report.php">Report</a></li>
            <li class="not-selected"><a href="../settings/settings.php">Settings</a></li>
            <li class="end not-selected"><a href="../php/logout.php">Logout</a></li>
          </ul>

        </nav>
      </div>

      <div class="add-visit">
        <h3>Add a new Visit</h3>
        <hr>
      </div>

      <div class="add-visit-form-container">
      <form id="add-visit-form" class="add-visit-form" action="add-visit-handler.php" method="post">

        <div class="add-visit-form-grid">

        <div class="date-container">

            <label>
              <input type="text" class="input-box date" name="date" placeholder="Date">
            </label>
          </div>

          <div class="time-container">
            <label>
              <input type="text" class="input-box time" name="time" placeholder="Time">
            </label>
          </div>

          <div class="duration-container">
            <label>
              <input type="text" class="input-box duration" name="duration" placeholder="Duration">
            </label>

          </div>

          <div class="add-container">
            <button id="add-button" name="submit" class="button add-button">Add</button>

          </div>

          <div class="reset-container">
            <label>
              <input type="reset" class="button reset-button" name="cancel" value="Cancel">
            </label>

          </div>

          </div>


      </form>

    </div>

      <div class="map">
          <img class="map" src="../images/exeter.jpg" alt="Map of exeter">
      </div>

      <div class="marker">
          <img class="black_marker" src="../images/marker_black.png" alt="Black Marker">
      </div>

    </div>

  </body>
</html>
