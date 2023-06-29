<%-- 
    Document   : intialSearchPage
    Created on : Jun 28, 2023, 4:55:34 PM
    Author     : rohitpanicker
--%>

<%@page import="java.util.List"%>
<%@page import="com.mycompany.hotel_reservation_system.pojo.Room"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Search Results</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 20px;
        }

        .card {
            margin-bottom: 20px;
            padding: 20px;
            background-color: #f7f7f7;
            border: 1px solid #e3e3e3;
            border-radius: 5px;
        }

        label {
            font-weight: bold;
        }

        .card img {
            max-width: 200px;
            height: auto;
            margin-top:10px;
        }
    </style>
</head>
<body>
    <% List<Room> roomList = (List<Room>) request.getAttribute("allRoomsList"); %>

    <% for (Room room : roomList) { %>
        <div class="card">
            <img src="/covers/<%= room.getId() %>.jpg" alt="Room Photo" />
            <br>
            <label>Id:</label> <%= room.getId() %> <br/>
            <label>Hotel Name:</label> <%= room.getHotelName() %><br/>
            <label>Address:</label> <%= room.getAddress() %><br/>
            <label>Pin Code:</label> <%= room.getPincode() %><br/>
            <label>Capacity:</label> <%= room.getCapacity() %><br/>
            <label>Cost Per Day:</label> <%= room.getCostPerDay() %><br/>
            <label>Description:</label> <%= room.getDescription() %><br/>
        </div>
    <% } %>
</body>
</html>
