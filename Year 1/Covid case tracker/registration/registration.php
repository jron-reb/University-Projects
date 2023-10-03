<?php
  session_start();
?>

<!DOCTYPE html>
<html lang="en" dir="ltr">
  <head>
    <meta charset="utf-8">
    <title>COVID-CT: Registration</title>
    <link rel="stylesheet" href="styles.css">
    <link rel="stylesheet" href="../css/master.css">
  </head>
  <body>
    <div class="content-container">

    <div class="h1-container">
      <h1 class="header">COVID - 19 Contact Tracing</h1>
    </div>

    <div class="watermark">
        <img class="watermark" src="../images/watermark.png" alt="Image of covid molecule">
    </div>

    <div class="registration-form-container">

    <form id="registration-form" class="registration-form" action="registration-handler.php" method="post">
      <div class="registration-form-grid-container">

        <div class="firstname-container">
          <label>
            <input type="text" class="input-box firstname" name="firstname" placeholder="Name" required>
          </label>
        </div>

        <div class="lastname-container">
          <label>
            <input type="text" class="input-box lastname" name="lastname" placeholder="Lastname">
          </label>
        </div>

        <div class="username-container">
          <label>
            <input type="text" class="input-box username" name="username" placeholder="Username" required>
          </label>

        </div>

        <div class="password-container">
          <label>
            <input type="password" class="input-box password" name="password" placeholder="Password" required>
          </label>

        </div>

        <div class="register-container">
          <button type="submit" name="submit" id="register-button" class="button register-button">Register</button>

        </div>

      </div>

    </form>
    </div>

  </div>

  </body>
</html>
