<%-- 
    Document   : signup
    Created on : Jun 27, 2023, 6:49:10 PM
    Author     : rohitpanicker
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>

        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-geWF76RCwLtnZ8qwWowPQNguL3RmwHVBC9FhGdlKrxdiJJigb/j/68SIy3Te4Bkz" crossorigin="anonymous"></script>
    </head>
    <body>


        <!--  <h1>Login Form</h1>
        <form action="signup.htm" method="post">
            Username: <input type="text" name="userName" />
            Password: <input type="password" name="password"/>
            Email Address : <input type="text" name="email" />
            Role: <input type="text" name="role"/>
            <input type="submit" value="Sign Up"/>
        </form>!-->

        <section class="vh-100" style="background-color: #eee;">
            <div class="container h-100">
                <div class="row d-flex justify-content-center align-items-center h-100">
                    <div class="col-lg-12 col-xl-11">
                        <div class="card text-black" style="border-radius: 25px;">
                            <div class="card-body p-md-5">
                                <div class="row justify-content-center">
                                    <div class="col-md-10 col-lg-6 col-xl-5 order-2 order-lg-1">

                                        <p class="text-center h1 fw-bold mb-5 mx-1 mx-md-4 mt-4">Sign up</p>

                                        <form class="mx-1 mx-md-4">

                                            <div class="d-flex flex-row align-items-center mb-4">
                                                <i class="fas fa-user fa-lg me-3 fa-fw"></i>
                                                <div class="form-outline flex-fill mb-0">
                                                    <input type="text" id="username" class="form-control" />
                                                    <label class="form-label" for="username">User Name</label>
                                                </div>
                                            </div>

                                            <div class="d-flex flex-row align-items-center mb-4">
                                                <i class="fas fa-envelope fa-lg me-3 fa-fw"></i>
                                                <div class="form-outline flex-fill mb-0">
                                                    <input type="email" id="email" class="form-control" />
                                                    <label class="form-label" for="email">Your Email</label>
                                                </div>
                                            </div>

                                            <div class="d-flex flex-row align-items-center mb-4">
                                                <i class="fas fa-lock fa-lg me-3 fa-fw"></i>
                                                <div class="form-outline flex-fill mb-0">
                                                    <input type="password" id="password" class="form-control" />
                                                    <label class="form-label" for="password">Password</label>
                                                </div>
                                            </div>

                                            <div class="d-flex flex-row align-items-center mb-4">
                                                <i class="fas fa-key fa-lg me-3 fa-fw"></i>
                                                <div class="form-outline flex-fill mb-0">
                                                    <input type="password" id="confirmPassword" class="form-control" />
                                                    <label class="form-label" for="confirmPassword">Repeat your password</label>
                                                </div>
                                            </div>

                                            <div class="mb-3">
                                                <label for="role" class="form-label">Select Role</label>
                                                <select class="form-select" id="role" required>
                                                    <option value="user">Customer</option>
                                                    <option value="admin">Administrator</option>
                                                    <option value="frontdesk">Front Desk Staff</option>
                                                    <option value="housekeeping">Housekeeping Staff</option>
                                                </select>
                                            </div>

                                            <div class="form-check d-flex justify-content-center mb-5">
                                                <input class="form-check-input me-2" type="checkbox" value="" id="form2Example3c" required />
                                                <label class="form-check-label" for="form2Example3">
                                                    I agree all statements in <a href="#!">Terms of service</a>
                                                </label>
                                            </div>

                                            <div class="d-flex justify-content-center mx-4 mb-3 mb-lg-4">
                                                <button type="button" class="btn btn-primary btn-lg" onclick="signUp()">Sign Up</button>
                                            </div>

                                        </form>

                                    </div>
                                    <div class="col-md-10 col-lg-6 col-xl-7 d-flex align-items-center order-1 order-lg-2">

                                        <img src="https://mdbcdn.b-cdn.net/img/Photos/new-templates/bootstrap-registration/draw1.webp"
                                             class="img-fluid" alt="Sample image">

                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>


        <script>

            function signUp()
            {
                const userName = document.getElementById("username").value;
                const password = document.getElementById("password").value;
                const email = document.getElementById("email").value;
                const confirmPass = document.getElementById("confirmPassword").value;
                const role = document.getElementById("role").value;
                const checkBox = document.getElementById("form2Example3c");

               

                if (userName.length < 6) {
                    alert("Username should be at least six characters");
                    return;
                }

                if (!validateEmail(email)) {
                    alert("Please enter a valid email address");
                    return;
                }

                if (!validatePassword(password)) {
                    alert("Password should have at least 12 characters, a mixture of uppercase and lowercase letters, numbers, and special characters");
                    return;
                }

                if (password !== confirmPass) {
                    alert("Password and confirm password do not match");
                    return;
                }

                if (!checkBox.checked) {
                    alert("Please agree to the Terms of Service");
                    return;
                }







                const xhttp = new XMLHttpRequest();
                xhttp.open("GET", "http://localhost:8080/Hotel_Reservation_System_WT/room/sendOtp.htm?emailAddress=" + email + " ", true);
                xhttp.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");

                xhttp.onreadystatechange = function () {
                    if (this.readyState == 4 && this.status == 200) {
                        // Typical action to be performed when the document is ready:

                        const obj = JSON.parse(xhttp.responseText);

                      
                        if (obj.success === "true")
                        {

                            var otp = prompt("Please enter the OTP that has been sent to your email:");
                            if (otp === obj.otp)
                            {

                                const newhhtp = new XMLHttpRequest();
                                newhhtp.open("POST", "http://localhost:8080/Hotel_Reservation_System_WT/room/signup.htm", false);
                                newhhtp.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");

                                newhhtp.onreadystatechange = function () {
                                    if (this.readyState == 4 && this.status == 200) {
                                        const obj = JSON.parse(xhttp.responseText);
                                        if (obj.success === "true") {

                                            alert("Sign Up successfull");

                                            window.location.href = "http://localhost:8080/Hotel_Reservation_System_WT/room/login.htm";
                                        } else {
                                            alert("Booking Unsuccessful");
                                        }
                                    }
                                };
                                newhhtp.send("userName=" + userName + "&emailAddress=" + email + "&password=" + password + "&email=" + email + "&role=" + role);





                            } else {
                                alert("Entered OTP does Not Match, please signup again");
                            }

                            // window.location.href = "http://localhost:8080/Hotel_Reservation_System_WT/room/login.htm";
                        } else {
                            alert("SignUp Unsuccessful, please Try again");
                        }
                    }
                };

                xhttp.send();


            }

            function validateEmail(email) {
                const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
                return emailRegex.test(email);
            }


            function validatePassword(password) {
                const passwordRegex = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@!#?])[A-Za-z\d@!#?]{12,}$/;
                return passwordRegex.test(password);
            }

        </script>
    </body>
</html>
