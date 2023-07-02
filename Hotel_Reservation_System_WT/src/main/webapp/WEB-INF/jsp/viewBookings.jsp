<%-- 
    Document   : viewBookings
    Created on : Jun 28, 2023, 10:56:07 PM
    Author     : rohitpanicker
--%>

<%@page import="com.mycompany.hotel_reservation_system.pojo.ManageBooking"%>
<%@page import="java.util.List"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>JSP Page</title>

    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/jqueryui/1.12.1/jquery-ui.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/jqueryui/1.12.1/jquery-ui.min.css">
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="https://code.jquery.com/ui/1.13.0/jquery-ui.min.js"></script>


    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f2f2f2;
        }

        .header {
            background-color: #333;
            color: #fff;
            padding: 20px;
            display: flex;
            justify-content: space-between;
            align-items: center;
        }

        .header button {
            margin-left: auto;
        }

        .container {
            max-width: 800px;
            margin: 0 auto;
            padding: 20px;
            background-color: #fff;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            border-radius: 5px;
        }

        .card {
        margin-bottom: 20px;
        padding: 20px;
        background-color: #f7f7f7;
        border: 1px solid #e3e3e3;
        border-radius: 5px;
        display: flex;
        flex-wrap: wrap;
        align-items: center;
        justify-content: space-between;
    }

    .card img {
        max-width: 400px;
        height: auto;
        margin-top: 10px;
    }

    .card label {
        font-weight: bold;
        margin-right: 5px;
    }

    .card-info {
        display: flex;
        align-items: center;
    }

    .card-info-item {
        display: flex;
        align-items: center;
        margin-right: 20px;
    }

    .card-info-item:last-child {
        margin-right: 0;
    }

    .card-info-item label {
        margin-right: 5px;
    }

        .book-button {
            margin-top: 10px;
            padding: 10px 20px;
            background-color: #4CAF50;
            color: #fff;
            border: none;
            border-radius: 5px;
            cursor: pointer;
        }

        h2 {
            text-align: center;
            margin-bottom: 20px;
        }
    </style>
</head>
<body>
<%
    List<ManageBooking> manageBookingList = (List<ManageBooking>) request.getAttribute("manageBookings");
%>

<h2>Your Bookings</h2>
<%
    if (manageBookingList != null) {
        for (ManageBooking mb : manageBookingList) {%>
<div class="card">
    <img src="/covers/<%= mb.getRoom().getId()%>.jpg" alt="Room Photo" />
    <div class="card-info">
        <div class="card-info-item">
            <label>Check in Date:</label>
            <%= mb.getBookingDetails().getBookingDate()%>
        </div>
        <div class="card-info-item">
            <label>Check out Date:</label>
            <%= mb.getBookingDetails().getCheckoutDate()%>
        </div>
        <div class="card-info-item">
            <label>Hotel Name:</label>
            <%= mb.getRoom().getHotelName()%>
        </div>
        <div class="card-info-item">
            <label>Address:</label>
            <%= mb.getRoom().getAddress()%>
        </div>
        <div class="card-info-item">
            <label>Pin Code:</label>
            <%= mb.getRoom().getPincode()%>
        </div>
        <div class="card-info-item">
            <label>Capacity:</label>
            <%= mb.getRoom().getCapacity()%>
        </div>
        <div class="card-info-item">
            <label>Total Booking Cost:</label>
            $<%=mb.getBookingDetails().getTotalCost()%>
        </div>
        <div class="card-info-item">
            <button class="book-button" onclick="cancelBooking(<%= mb.getBookingDetails().getId()%>, <%= mb.getBookingDetails().getUserId()%>)">Cancel Booking</button>
        </div>
        <div class="card-info-item">
            <button class="book-button" onclick="changeBookingDate(<%= mb.getBookingDetails().getId()%>, <%= mb.getBookingDetails().getUserId()%>)">Change Dates</button>
        </div>
    </div>
</div>
<% }
}%>
<div id="datePickersContainer"></div>
<script>
    function changeBookingDate(bookingId, userId) {
        var container = document.createElement("div");
        container.classList.add("card");

        var checkinLabel = document.createElement("label");
        checkinLabel.innerHTML = "Check-in Date: ";
        container.appendChild(checkinLabel);

        var checkinPicker = document.createElement("input");
        checkinPicker.setAttribute("type", "text");
        checkinPicker.setAttribute("class", "date-picker");
        container.appendChild(checkinPicker);

        var checkoutLabel = document.createElement("label");
        checkoutLabel.innerHTML = "Check-out Date: ";
        container.appendChild(checkoutLabel);

        var checkoutPicker = document.createElement("input");
        checkoutPicker.setAttribute("type", "text");
        checkoutPicker.setAttribute("class", "date-picker");
        container.appendChild(checkoutPicker);

        var submitButton = document.createElement("button");
        submitButton.innerHTML = "Submit New Dates";
        submitButton.setAttribute("class", "book-button");
        submitButton.addEventListener("click", function () {
            var newcheckinDate = checkinPicker.value;
            var newcheckoutDate = checkoutPicker.value;

            // Perform actions with the selected dates, such as submitting them to the server via AJAX

            // Example AJAX code (you may need to customize it based on your server-side implementation):
            var xhttp = new XMLHttpRequest();
            xhttp.open("POST", "http://localhost:8080/Hotel_Reservation_System_WT/room/changeBookingDate.htm", true);
            xhttp.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
            xhttp.onreadystatechange = function () {
                if (this.readyState == 4 && this.status == 200) {
                    // Handle the response from the server
                    alert("Dates have been changed successfully.");
                            <%
                                request.removeAttribute("manageBookings");
                                request.getSession().removeAttribute("manageBookings");
                                %>
                    window.location.href = "http://localhost:8080/Hotel_Reservation_System_WT/room/manageBookings.htm?userId=" + userId + " ";
                }
            };
            var requestBody = "bookingId=" + bookingId + "&checkinDate=" + newcheckinDate + "&checkoutDate=" + newcheckoutDate + " ";
            xhttp.send(requestBody);
        });

        container.appendChild(submitButton);

        var datePickersContainer = document.getElementById("datePickersContainer");
        datePickersContainer.innerHTML = "";
        datePickersContainer.appendChild(container);

        $(checkinPicker).datepicker();
        $(checkoutPicker).datepicker();
    }

    function cancelBooking(bookingId, userId) {
        alert("cancel Booking Alert");
        const xhttp = new XMLHttpRequest();
        xhttp.open("POST", "http://localhost:8080/Hotel_Reservation_System_WT/room/manageBookings.htm?bookingId=" + bookingId + " ", true);
        xhttp.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");

        xhttp.onreadystatechange = function () {
            if (this.readyState == 4 && this.status == 200) {
                // Typical action to be performed when the document is ready:

                const obj = JSON.parse(xhttp.responseText);
                //alert(typeof obj.checkLoginResult);
                alert(xhttp.responseText);
                if (obj.deletedCount > 0) {
                    alert("Booking has been canceled successfully");
                    window.location.href = "http://localhost:8080/Hotel_Reservation_System_WT/room/manageBookings.htm?userId=" + userId + " ";

                } else {
                    alert("Booking Unsuccessful");
                }
            }
        };
        xhttp.send();
    }
</script>
