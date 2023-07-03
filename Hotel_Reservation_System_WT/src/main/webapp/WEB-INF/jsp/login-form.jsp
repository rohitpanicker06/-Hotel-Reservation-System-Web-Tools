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
        <title>Login Page</title>
    </head>
    <body>
        <br> <br>
        <h3 style="text-align: center; font-weight: bold"> Welcome to Hotel Reservation System </h3>
        <div class="container">
            <div class="row justify-content-center mt-5">
                <div class="col-md-6">
                    <div class="card">
                        <div class="card-header bg-primary text-white">
                            <h4 class="m-0">Login</h4>
                        </div>
                        <div class="card-body">
                            <form>
                                <div class="mb-3">
                                    <label for="email" class="form-label">Email address</label>
                                    <input type="email" class="form-control" id="email" required>
                                </div>
                                <label for="errorEmail" id ="errorEmail" class="form-label" hidden> Email Cannot be Empty</label>
                                <div class="mb-3">
                                    <label for="password" class="form-label">Password</label>
                                    <input type="password" class="form-control" id="password" required>
                                </div>
                                <label for="errorPassword" id ="errorPassword" class="form-label" hidden>Email Cannot be Empty</label>
                                <div class="mb-3">
                                    <label for="role" class="form-label">Select Role</label>
                                    <select class="form-select" id="role" required>
                                        <option value="user">Customer</option>
                                        <option value="admin">Administrator</option>
                                        <option value="frontdesk">Front Desk Staff</option>
                                        <option value="housekeeping">Housekeeping Staff</option>
                                    </select>
                                </div>
                                <button type="button" id="buttonB" class="btn btn-primary">Login</button>
                                <a href="signup.htm" class="btn btn-primary">Sign Up</a>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-geWF76RCwLtnZ8qwWowPQNguL3RmwHVBC9FhGdlKrxdiJJigb/j/68SIy3Te4Bkz" crossorigin="anonymous"></script>
        <script>
            var button = document.getElementById("buttonB");
            button.addEventListener("click", function () {
                var us = document.getElementById("email").value;
                var pass = document.getElementById("password").value;
                var role = document.getElementById("role").value;





                if (us === "") {
                    var errorEmailNode = document.getElementById("errorEmail");
                    errorEmailNode.style.color = "red"; // Set the color to red
                    errorEmailNode.removeAttribute("hidden"); // Remove the "hidden" attribute
                    
                    return;
                }

                if (pass === "") {
                    var errorPassword = document.getElementById("errorPassword");
                    errorPassword.style.color = "red"; // Set the color to red
                    errorPassword.removeAttribute("hidden"); // Remove the "hidden" attribute
                    return;
                }

                const xhttp = new XMLHttpRequest();
                xhttp.open("POST", "http://localhost:8080/Hotel_Reservation_System_WT/room/checkLoginRest.htm", true);
                xhttp.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");

                xhttp.onreadystatechange = function () {
                    if (this.readyState == 4 && this.status == 200) {
                        alert(xhttp.responseText);
                        const obj = JSON.parse(xhttp.responseText);

                        if (obj.checkLoginResult === "true") {
                            alert("Login Successful");
                            if (obj.role === "admin") {
                                window.location.href = "http://localhost:8080/Hotel_Reservation_System_WT/room/add.htm";

                            } else if (obj.role === "user") {
                                window.location.href = "http://localhost:8080/Hotel_Reservation_System_WT/room/searchRooms.htm";
                            } else if (obj.role === "frontdesk") {
                                window.location.href = "http://localhost:8080/Hotel_Reservation_System_WT/room/frontdesk.htm";
                            } else if (obj.role === "housekeeping") {
                                // Redirect to housekeeping page
                            }
                        } else {
                            alert("Your entered credentials are wrong or the selected role is incorrect. Please try again.");
                        }
                    }
                };
                xhttp.send("param1=" + us + "&param2=" + pass + "&param3=" + role);
            });
        </script>
    </body>
</html>
