<%-- 
    Document   : frontDeskView
    Created on : Jun 30, 2023, 8:20:44 PM
    Author     : rohitpanicker
--%>
<%@page import="com.mycompany.hotel_reservation_system.pojo.UserAccount"%>
<%@page import="com.mycompany.hotel_reservation_system.pojo.Room"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
        <script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/js/all.min.js"></script>

        <title>Hotel Front Desk</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
        <style>
            /* Style for the scrollable table */
            .table-container {
                height: 300px;
                overflow-y: scroll;
                border: 1px solid #ccc;
            }

            table {
                width: 100%;
                border-collapse: collapse;
            }

            th, td {
                padding: 8px;
                text-align: left;
                border-bottom: 1px solid #ddd;
            }

            th {
                background-color: #f2f2f2;
            }

            /* Style for selected rows */
            tr.selected {
                background-color: #ffffcc;
            }

            /* Style for the action button */
            .action-button {
                margin-top: 10px;
                padding: 8px 16px;
                background-color: #007bff;
                color: #fff;
                border: none;
                border-radius: 4px;
                cursor: pointer;
            }
        </style>
    </head>
    <body>
        
        
           
            
      
        <div class="container">
            <br><!-- comment -->
            <div style="display: flex; justify-content: space-between;">
        <h3>Welcome to Hotel Reservation Front Desk Operations</h3>
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
    </div>
            <% List<Room> roomList = (List<Room>) request.getAttribute("hotelRoomsList"); %>
            <div class="table-container">
                <table class="table table-striped">
                    <thead>
                        <tr>
                            <th>Select</th>
                            <th>Room ID</th>
                            <th>Name</th>
                            <th>Capacity</th>
                            <th>Cost per Day</th>
                            <!-- Add more column headers as needed -->
                        </tr>
                    </thead>
                    <tbody>
                        <% for (Room room : roomList) {%>
                        <tr>
                            <td><input type="checkbox"></td>
                            <td><%= room.getId()%></td>
                            <td><%= room.getHotelName()%></td>
                            <td><%= room.getCapacity()%></td>
                            <td><%= room.getCostPerDay()%></td>
                            <!-- Add more rows and columns as needed -->
                        </tr>
                        <% }%>
                    </tbody>
                </table>
            </div>

            <button id="action-button" class="btn btn-primary action-button" disabled>
                <i class="fas fa-list"></i> View All Bookings
            </button>
            <button id="checkin-button" class="btn btn-primary action-button">
                <i class="fas fa-sign-in-alt"></i> Check In Guest
            </button>
            <button id="checkout-button" class="btn btn-primary action-button">
                <i class="fas fa-sign-out-alt"></i> Check Out Guest
            </button>
        </div>

        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
        <script>
            $(document).ready(function () {
                // Function to update the checkbox event listener
                function updateCheckboxEventListeners() {
                    $('input[type="checkbox"]').on('click', function () {
                        $(this).closest('tr').toggleClass('selected');
                        var anySelected = $('tr.selected').length > 0;
                        $('#action-button').prop('disabled', !anySelected);
                    });
                }

                // Add click event listener to checkboxes in the table
                updateCheckboxEventListeners();

                // Add click event listener to the action button
                $('#action-button').on('click', function () {
                    var selectedRows = $('tr.selected');
                    selectedRows.each(function () {
                        // Access the data in each selected row and perform action
                        var roomId = $(this).find('td:eq(1)').text();
                        var name = $(this).find('td:eq(2)').text();
                        var capacity = $(this).find('td:eq(3)').text();
                        var costPerDay = $(this).find('td:eq(4)').text();

                        // ...

                        const xhttp = new XMLHttpRequest();
                        xhttp.open("GET", "http://localhost:8080/Hotel_Reservation_System_WT/room/getAllBookings.htm?hotelId=" + roomId, true);
                        xhttp.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");

                        xhttp.onreadystatechange = function () {
                            if (this.readyState == 4 && this.status == 200) {
                                // Clear existing table headers
                                $('thead tr').empty();

                                // Create new table headers
                                var headers = ["Select", "BookingID", "Hotel ID", "UserID", "Checkin Date", "Checkout Date", "Number of Days", "Total Cost"];
                                headers.forEach(function (header) {
                                    var th = $("<th>").text(header);
                                    $('thead tr').append(th);
                                });

                                // Clear existing table body
                                $('tbody').empty();

                                // Parse the JSON response
                                const bookings = JSON.parse(xhttp.responseText);

                                // Iterate over each booking
                                bookings.forEach(function (booking) {
                                    // Create a new table row
                                    var row = $("<tr>");

                                    // Add checkbox to the row
                                    var checkboxCell = $("<td>").html('<input type="checkbox">');
                                    row.append(checkboxCell);

                                    // Populate the cells with booking data
                                    var bookingIdCell = $("<td>").text(booking.id);
                                    var hotelIdCell = $("<td>").text(booking.hotelId);
                                    var userIdCell = $("<td>").text(booking.userId);
                                    var bookingDateCell = $("<td>").text(booking.bookingDate);
                                    var checkoutDateCell = $("<td>").text(booking.checkoutDate);
                                    var numberOfDaysCell = $("<td>").text(booking.numberOfDays);
                                    var totalCostCell = $("<td>").text(booking.totalCost);

                                    // Append the cells to the row
                                    row.append(bookingIdCell, hotelIdCell, userIdCell, bookingDateCell, checkoutDateCell, numberOfDaysCell, totalCostCell);

                                    // Append the row to the table body
                                    $("tbody").append(row);
                                });

                                // Update the checkbox event listeners for the updated table
                                updateCheckboxEventListeners();
                            }
                        };
                        xhttp.send();
                    });
                });

                // Add click event listener to the Check In Guest button
                $('#checkin-button').on('click', function () {

                    var selectedRows = $('tr.selected');
                    selectedRows.each(function () {
                        // Access the data in each selected row and perform check-in action
                        var bookId = $(this).find('td:eq(1)').text();
                       



                        const xhttp = new XMLHttpRequest();
                        xhttp.open("POST", "http://localhost:8080/Hotel_Reservation_System_WT/room/checkInGuest.htm", true);
                        xhttp.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");

                        xhttp.onreadystatechange = function () {
                            if (this.readyState == 4 && this.status == 200) {
                                // Handle the response after successful check-in
                                const res = JSON.parse(xhttp.responseText);
                                if(res.success === "true")
                                {
                                alert("Guest checked in successfully!");
                            }else{
                                 alert("Guest might be already checked In!");
                            }
                                // Add any necessary logic or UI updates after check-in
                            }
                        };

                        // Convert the payload to JSON and send the request
                        xhttp.send("param1=" + bookId + " ");

                    });
                });


                $('#checkout-button').on('click', function () {

                   
                    var selectedRows = $('tr.selected');
                    selectedRows.each(function () {
                        // Access the data in each selected row and perform check-in action
                        var bookId = $(this).find('td:eq(1)').text();
                       // alert(bookId);



                        const xhttp = new XMLHttpRequest();
                        xhttp.open("POST", "http://localhost:8080/Hotel_Reservation_System_WT/room/checkOutGuest.htm", true);
                        xhttp.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");

                        xhttp.onreadystatechange = function () {
                            if (this.readyState == 4 && this.status == 200) {
                                // Handle the response after successful check-in
                                const res = JSON.parse(xhttp.responseText);
                                if(res.success === "true")
                                {
                                alert("Guest checked out successfully!");
                            }else{
                                alert("No");
                                 alert("Guest might be already checkedOut");
                            }
                                // Add any necessary logic or UI updates after check-in
                            }
                        };

                        // Convert the payload to JSON and send the request
                        xhttp.send("param1=" + bookId + " ");

                    });
                });


            });
        </script>
        
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


    </body>
</html>
