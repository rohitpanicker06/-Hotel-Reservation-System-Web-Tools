<%--
    Document   : addRoomSuccess
    Created on : Jun 28, 2023, 4:31:51 PM
    Author     : rohitpanicker
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Room Added Successfully</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 20px;
        }

        h3 {
            text-align: center;
        }

        .room-details {
            max-width: 500px;
            margin: 20px auto;
            background-color: #f7f7f7;
            padding: 20px;
            border: 1px solid #e3e3e3;
            border-radius: 5px;
        }

        label {
            font-weight: bold;
        }

        img {
            max-width: 100%;
            margin-top: 10px;
        }
    </style>
</head>
<body>
    <div class="room-details">
        <h3>Hotel has been added successfully</h3>
        <label>Id:</label> ${requestScope.room.id} <br/>
        <label>Hotel Name:</label> ${requestScope.room.hotelName}<br/>
        <label>Address:</label> ${requestScope.room.address}<br/>
        <label>Pin Code:</label> ${requestScope.room.pincode}<br/>
        <label>Capacity:</label> ${requestScope.room.capacity}<br/>
        <label>Cost Per Day:</label> ${requestScope.room.costPerDay}<br/>
        <label>Description:</label> ${requestScope.room.description}<br/>
        <label>Photo:</label> <br>
        <img src="/covers/${requestScope.room.id}.jpg" alt="Room Photo"/><br>
    </div>
</body>
</html>
