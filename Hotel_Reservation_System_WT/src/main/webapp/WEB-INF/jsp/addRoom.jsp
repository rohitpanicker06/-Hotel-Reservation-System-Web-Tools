<%-- 
    Document   : addRoom
    Created on : Jun 26, 2023, 12:47:34 PM
    Author     : rohitpanicker
--%>

<%@page import="com.mycompany.hotel_reservation_system.pojo.UserAccount"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add Room</title>
        <style>
            body {
                font-family: Arial, sans-serif;
                margin: 20px;
            }

            h1 {
                text-align: center;
            }

            form {
                margin: 20px auto;
                max-width: 400px;
                padding: 20px;
                background-color: #f7f7f7;
                border: 1px solid #e3e3e3;
                border-radius: 5px;
            }

            label {
                display: block;
                margin-bottom: 5px;
                font-weight: bold;
            }

            input[type="text"], input[type="file"], input[type="submit"] {
                width: 100%;
                padding: 8px;
                margin-bottom: 10px;
                border: 1px solid #ccc;
                border-radius: 4px;
                box-sizing: border-box;
            }

            input[type="submit"] {
                background-color: #4CAF50;
                color: #fff;
                cursor: pointer;
            }

            .error {
                color: red;
                margin-top: 5px;
            }

            .header {
                background-color: #333;
                color: #fff;
                padding: 20px;
                display: flex;
                justify-content: space-between;
                align-items: center;
                text-align: center
            }

            .logout-container {
                margin-left: auto;
            }

            .logout-button {
                padding: 10px 20px;
                background-color: #4CAF50;
                color: #fff;
                border: none;
                border-radius: 5px;
                cursor: pointer;
            }

        </style>


    </head>
    <body>
        <h2 class="header">
            Welcome, Please enter the details to add a new Hotel Room
            <% boolean value = Boolean.parseBoolean((String) session.getAttribute("isLoggedIn"));
                UserAccount userAccount = (UserAccount) session.getAttribute("userAccount");
                Integer id = null;
                if (userAccount != null) {
                    id = userAccount.getId();
                }
                if (value) {
            %>
            <div class="logout-container">
                <button onclick="logout()" class="logout-button">Log Out</button>
            </div>
            <%
                }
            %>
        </h2>


        <form:form modelAttribute="room" method="post" enctype="multipart/form-data" >
            <label for="id">ID:</label>
            <form:input path="id" />
            <form:errors path="id" cssClass="error" />


            <label for="hotelName">Hotel Name:</label>
            <form:input path="hotelName" />
            <form:errors path="hotelName" cssClass="error" />

            <label for="address">Address:</label>
            <form:input path="address" />
            <form:errors path="address" cssClass="error" />

            <label for="pincode">Pin Code:</label>
            <form:input path="pincode" />
            <form:errors path="pincode" cssClass="error" />

            <label for="pincode">Capacity:</label>
            <form:input path="capacity" />
            <form:errors path="capacity" cssClass="error" />

            <label for="pincode">Cost Per Day:</label>
            <form:input path="costPerDay" />
            <form:errors path="costPerDay" cssClass="error" />

            <label for="description">Description:</label>
            <form:input path="description" />
            <form:errors path="description" cssClass="error" />

            <label for="photo">Upload picture:</label>
            <input type="file" name="photo" required="required" />
            <form:errors path="photo" cssClass="error" />

            <input type="submit" value="Submit"/>
        </form:form>
    </body>
    
    
    <script>
        
        function logout() {
   
     const xhttp = new XMLHttpRequest();
        xhttp.open("POST", "http://localhost:8080/Hotel_Reservation_System_WT/room/logout.htm", true);
        xhttp.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");

        xhttp.onreadystatechange = function () {
            if (this.readyState == 4 && this.status == 200) {
               
                alert("You have been logged out");
        window.location.href = "http://localhost:8080/Hotel_Reservation_System_WT/room/login.htm";
            }
        };
        xhttp.send();
   
        
   
   
   
   
   
                
    }

        </script>
</html>
