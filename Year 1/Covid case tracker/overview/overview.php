<?php
  session_start();
?>

<!DOCTYPE html>
<html lang="en" dir="ltr">
  <head>
    <meta charset="utf-8">
    <title>COVID-CT: Visits Overview</title>
    <link rel="stylesheet" href="../css/master.css">
    <link rel="stylesheet" href="../overview/styles.css">
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
            <li class="selected"><a href="../overview/overview.php">Overview</a></li>
            <li class="not-selected"><a href="../add_visit/add-visit.php">Add Visit</a></li>
            <li class="not-selected"><a href="../report/report.php">Report</a></li>
            <li class="not-selected"><a href="../settings/settings.php">Settings</a></li>
            <li class="end not-selected"><a href="../php/logout.php">Logout</a></li>
          </ul>

        </nav>
      </div>

      <div class="table">
        <table>
          <tr>
            <th class="date">Date</th>
            <th class="time">Time</th>
            <th class="duration">Duration</th>
            <th class="X">X</th>
            <th class="Y">Y</th>
          </tr>

          <tr>
            <td class="date">01/01/2020</td>
            <td class="time">10:00</td>
            <td class="duration">60</td>
            <td class="X">50</td>
            <td class="Y">30</td>
          </tr>

          <tr>
            <td class="date">01/01/2020</td>
            <td class="time">16:00</td>
            <td class="duration">30</td>
            <td class="X">40</td>
            <td class="Y">60</td>
          </tr>

          <tr>
            <td class="date">08/01/2020</td>
            <td class="time">12:00</td>
            <td class="duration">45</td>
            <td class="X">50</td>
            <td class="Y">40</td>
          </tr>

          <tr>
            <td class="date">15/01/2020</td>
            <td class="time">18:00</td>
            <td class="duration">60</td>
            <td class="X">40</td>
            <td class="Y">40</td>
          </tr>

          <tr>
            <td class="date">20/01/2020</td>
            <td class="time">09:00</td>
            <td class="duration">40</td>
            <td class="X">30</td>
            <td class="Y">20</td>
          </tr>

        </table>

      </div>

    </div>


  </body>
</html>
