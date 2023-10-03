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
            <li class="not-selected"><a href="../add_visit/add-visit.php">Add Visit</a></li>
            <li class="selected"><a href="../report/report.php">Report</a></li>
            <li class="not-selected"><a href="../settings/settings.php">Settings</a></li>
            <li class="end not-selected"><a href="../php/logout.php">Logout</a></li>
          </ul>

        </nav>
      </div>

      <div class="report-infection">
        <h3>Report an Infection</h3>
        <hr>
      </div>

      <div class="text please-report-message">
        Please report date and time when you were tested positive for COVID-19.
      </div>

      <div class="report-infection-form-container">
      <form id="add-visit-form" class="add-visit-form" action="report-infection.php" method="post">

        <div class="add-visit-form-grid">

        <div class="date-container">

            <label>
              <input type="text" class="input-box date" name="date" placeholder="Date" required>
            </label>
          </div>

          <div class="time-container">
            <label>
              <input type="text" class="input-box time" name="time" placeholder="Time" required>
            </label>
          </div>

          <div class="report-container">
            <button id="report-button" class="button report-button">Report</button>

          </div>

          <div class="reset-container">
            <label>
              <input type="reset" class="button reset-button" name="cancel" value="Cancel">
            </label>

          </div>

          </div>


      </form>

    </div>

    </div>

  </body>
</html>
