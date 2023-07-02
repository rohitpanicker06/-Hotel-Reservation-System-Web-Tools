<%-- 
    Document   : searchPage
    Created on : Jun 28, 2023, 5:03:50 PM
    Author     : rohitpanicker
--%>

<%@page import="com.mycompany.hotel_reservation_system.dao.RoomDao"%>
<%@page import="com.mycompany.hotel_reservation_system.pojo.UserAccount"%>
<%@page import="java.util.List"%>
<%@page import="com.mycompany.hotel_reservation_system.pojo.Room"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Hotel Search</title>
    <!-- Include CSS for styling -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM" crossorigin="anonymous">
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
        }

        .container {
            max-width: 1200px;
            margin: 0 auto;
            padding: 20px;
            background-color: #fff;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            border-radius: 5px;
            
        }

        .search-form {
            display: flex;
            align-items: center;
            justify-content: center;
            margin-bottom: 20px;
        }

        .search-input {
            padding: 10px;
            width: 300px;
            margin-right: 10px;
            border: 1px solid #ccc;
            border-radius: 5px;
            outline: none;
        }

        .date-picker {
            padding: 10px;
            margin-right: 10px;
            border: 1px solid #ccc;
            border-radius: 5px;
            outline: none;
        }

        .capacity-picker {
            padding: 10px;
            border: 1px solid #ccc;
            border-radius: 5px;
            outline: none;
        }

        .search-button {
            padding: 10px 20px;
            background-color: #007bff;
            color: #fff;
            border: none;
            border-radius: 5px;
            cursor: pointer;
        }

        .card {
            margin-bottom: 20px;
           
            padding: 20px;
            background-color: #f7f7f7;
            border: 1px solid #e3e3e3;
            border-radius: 5px;
        }

        .card img {
            max-width: 200px;
            height: auto;
            margin-top: 10px;
        }

        .book-button {
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
<div class="header d-flex justify-content-between align-items-center">
    <h1>Welcome to Hotel Reservation System</h1>
    <% boolean value = Boolean.parseBoolean((String) session.getAttribute("isLoggedIn"));
        UserAccount userAccount = (UserAccount) session.getAttribute("userAccount");
        Integer id = null;
        if(userAccount!=null){
         id = userAccount.getId();
        }
        if (value) {
    %>
    <div class="text-end">
        <button onclick="viewMyBookings(<%= id%>)" class="btn btn-primary btn-sm">Manage Bookings</button>
        <button onclick="logout()" class="btn btn-danger btn-sm">Log Out</button>
    </div>
    <%
        }
    %>
</div>

    <%
       Long totalHotelRoomsCount = new RoomDao().getTotalHotelRoomsCount();
       Integer totalPages = (int) Math.ceil(totalHotelRoomsCount / (double) 3);
        %>
<div class="container">
    <form class="search-form" method="POST">
         <input type="hidden" name="page" value="1"> <!-- Page number -->
    <input type="hidden" name="pageSize" value="3"> <!-- Page size -->
        <input type="text" class="search-input" placeholder="Enter Destination" name="address" autocomplete="off">
        <input type="text" class="date-picker" placeholder="Select Check-In Date" name="date" autocomplete="off">
        <input type="text" class="date-picker" placeholder="Select Check-Out Date" name="outdate" autocomplete="off">
        <input type="number" class="capacity-picker" placeholder="Select Capacity" name="capacity" autocomplete="off">
        <button type="submit" class="search-button">Search</button>
    </form>

    <% String bookingDate = (String) request.getAttribute("bookingDate");
        String checkoutDate = (String) request.getAttribute("checkoutDate");
        Long numberOfDays = (Long) request.getAttribute("noOfDays");

       List<Room> roomList = (List<Room>) request.getAttribute("searchResults"); %>

    <% if (bookingDate != null) {%>
    <h6>Check-In Date: <%= bookingDate%></h6>
    <h6>Check-Out Date: <%= checkoutDate%></h6>
    <% } %>

    <div class="row">
        <% if (roomList != null) {
      for (Room room : roomList) {%>
        <div class="col-lg-4 col-md-6 mb-4">
            <div class="card h-100">
                <img src="/covers/<%= room.getId()%>.jpg" alt="Room Photo" class="card-img-top">
                <div class="card-body">
                    <h5 class="card-title"><%= room.getHotelName()%></h5>
                    <p class="card-text">
                        <strong>Address:</strong> <%= room.getAddress()%><br>
                        <strong>Pin Code:</strong> <%= room.getPincode()%><br>
                        <strong>Capacity:</strong> <%= room.getCapacity()%><br>
                        <strong>Cost Per Day:</strong> $<%= room.getCostPerDay()%><br>
                        <strong>Description:</strong> <%= room.getDescription()%>
                    </p>
                    <button class="btn btn-primary" onclick="bookRoom(<%= room.getId()%>, '<%= bookingDate%>', <%= numberOfDays%>, <%= room.getCostPerDay()%>)">Book</button>
                </div>
            </div>
        </div>
                
               
        <% } %>
        <div class="pagination">
    <% for (int i = 1; i <= totalPages; i++) { %>
        <a href="#" onclick="changePage(<%= i %>,'<%= bookingDate%>', '<%= checkoutDate%>')"><%= i %></a> &nbsp;&nbsp;
    <% } %>
</div>
<%
  }%>
   
    </div>
</div>

<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jqueryui/1.12.1/jquery-ui.min.js"></script>



<script>
    function changePage(pageNumber,  checkInDate, checkoutDate) {
         var form = document.querySelector('.search-form');
         var pageNumberInput = form.querySelector('input[name="page"]');
         var checkinDateInput = form.querySelector('input[name="date"]');
        var checkoutDateInput = form.querySelector('input[name="outdate"]');


        pageNumberInput.value = pageNumber;
        alert(checkInDate);
        checkinDateInput.value = checkInDate
        checkoutDateInput.value = checkoutDate
        
         form.submit();
    }
</script>



<script>
    $(function () {
        // Initialize date picker
        $('.date-picker').datepicker();

        // Other JavaScript logic goes here
    });

    function viewMyBookings(id) {
        window.location.href = "http://localhost:8080/Hotel_Reservation_System_WT/room/manageBookings.htm?userId=" + id + " ";
    }

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

    function bookRoom(roomId, checkinDate, numberOfDays, costPerDay) {
        var bookingDate = "<%= bookingDate%>";
        var checkOutDATE = "<%= checkoutDate%>";
        const xhttp = new XMLHttpRequest();
        xhttp.open("POST", "http://localhost:8080/Hotel_Reservation_System_WT/room/bookRoom.htm", false);
        xhttp.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");

        xhttp.onreadystatechange = function () {
            if (this.readyState == 4 && this.status == 200) {
                const obj = JSON.parse(xhttp.responseText);
                if (obj.added === "true") {
                    var uniqueId = obj.uniqueId;
                    alert("Your Booking has been Successful");
                    alert("You will be redirected to your reservation Report");
                    window.location.href = "http://localhost:8080/Hotel_Reservation_System_WT/room/report.pdf?bookingDate=" + bookingDate + "&roomId=" + roomId + "&uniqueId=" + uniqueId + "&checkoutDate=" + checkOutDATE;
                } else {
                    alert("Booking Unsuccessful");
                }
            }
        };
        xhttp.send("param1=" + bookingDate + "&param2=" + roomId + "&param3=" + checkOutDATE + "&param4=" + numberOfDays + "&param5=" + costPerDay);
    }
</script>
</body>
</html>
