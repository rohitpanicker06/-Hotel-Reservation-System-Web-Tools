<%-- 
    Document   : login-form
    Created on : Jun 27, 2023, 4:17:27 PM
    Author     : rohitpanicker
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/css/bootstrap.min.css">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-geWF76RCwLtnZ8qwWowPQNguL3RmwHVBC9FhGdlKrxdiJJigb/j/68SIy3Te4Bkz" crossorigin="anonymous"></script>
        <title>Login Page</title>
    </head>
    <body>

        <div class="container">
            <div class="row justify-content-center mt-5">
                <div class="col-md-6">
                    <div class="card">
                        <div class="card-header">
                            <h4>Login</h4>
                        </div>
                        <div class="card-body">

                            <div class="mb-3">
                                <label for="email" class="form-label">Email address</label>
                                <input type="email" class="form-control" id="email" required>
                            </div>
                            <div class="mb-3">
                                <label for="password" class="form-label">Password</label>
                                <input type="password" class="form-control" id="password" required>
                            </div>
                            <button type="button" id="buttonB" class="btn btn-primary">login</button>

                        </div>
                    </div>
                </div>
            </div>
        </div>


        <script>
            // Get the button element by its id



            var button = document.getElementById("buttonB");

            // Add the onclick event listener
            button.addEventListener("click", function () {
                // Code to be executed when the button is clicked

                var us = document.getElementById("email").value;
                var pass = document.getElementById("password").value;



                const xhttp = new XMLHttpRequest();
                xhttp.open("POST", "http://localhost:8080/Hotel_Reservation_System_WT/room/checkLoginRest.htm", true);
                xhttp.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");

                xhttp.onreadystatechange = function () {
                    if (this.readyState == 4 && this.status == 200) {
                        // Typical action to be performed when the document is ready:

                        const obj = JSON.parse(xhttp.responseText);
                        //alert(typeof obj.checkLoginResult);

                        if (obj.checkLoginResult === "true")
                        {
                            alert("Login Succesful");
                            window.location.href = "http://localhost:8080/Hotel_Reservation_System_WT/room/searchRooms.htm";
                        }else{
                            alert("Your entered Credentials are wrong, Please Try Again");
                        }
                    }
                };
                xhttp.send("param1=" + us + "&param2=" + pass);



            });
        </script>
    </body>
</html>
