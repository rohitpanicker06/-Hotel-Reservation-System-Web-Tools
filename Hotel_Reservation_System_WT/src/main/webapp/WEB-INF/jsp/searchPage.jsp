<%-- 
    Document   : searchPage
    Created on : Jun 28, 2023, 5:03:50 PM
    Author     : rohitpanicker
--%>

<%@page import="com.mycompany.hotel_reservation_system.pojo.UserAccount"%>
<%@page import="java.util.List"%>
<%@page import="com.mycompany.hotel_reservation_system.pojo.Room"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Hotel Search</title>
        <!-- Include CSS for styling -->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/jqueryui/1.12.1/jquery-ui.min.css">
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
                justify-content: space-between; /* Added CSS */
                align-items: center; /* Added CSS */
            }

            .header button {
                margin-left: auto; /* Added CSS */
            }

            .container {
                max-width: 800px;
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
                margin-top:10px;
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
        <div class="header">
    <h1>Welcome to Hotel Reservation System</h1>
    <% boolean value = Boolean.parseBoolean((String) session.getAttribute("isLoggedIn")); 
       UserAccount userAccount = (UserAccount) session.getAttribute("userAccount");
       Integer id = userAccount.getId();
    if(value)
    {
    %>
    <button onclick="viewMyBookings(<%= id %>)">Manage Bookings</button>
    <button onclick="logout()">Log Out</button>
    
    <%
        }
        %>
</div>

        <div class="container">
            <form class="search-form" method="POST">
                <input type="text" class="search-input" placeholder="Enter Address" name="address">
                <input type="text" class="date-picker" placeholder="Select Dates" name="date">
                <input type="number" class="capacity-picker" placeholder="Select Capacity" name="capacity">
                <button type="submit" class="search-button">Search</button>
            </form>

            <%
                String bookingDate = (String) request.getAttribute("bookingDate");

                List<Room> roomList = (List<Room>) request.getAttribute("searchResults"); %>


            <%
                if (bookingDate != null) {%>
            <h4> Booking Date = <%= bookingDate%> <br/>

                <%
                        } %>
                <%
            if (roomList != null) {
                for (Room room : roomList) {%>
                <div class="card">
                    <img src="/covers/<%= room.getId()%>.jpg" alt="Room Photo" />
                    <br>
                    <label>Id:</label> <%= room.getId()%> <br/>
                    <label>Hotel Name:</label> <%= room.getHotelName()%><br/>
                    <label>Address:</label> <%= room.getAddress()%><br/>
                    <label>Pin Code:</label> <%= room.getPincode()%><br/>
                    <label>Capacity:</label> <%= room.getCapacity()%><br/>
                    <label>Cost Per Day:</label> <%= room.getCostPerDay()%><br/>
                    <label>Description:</label> <%= room.getDescription()%><br/>
                    <button class="book-button" onclick="bookRoom(<%= room.getId()%>)">Book</button>
                </div>
                <% }
            }%>

        </div>
            
            <script>
                
                function viewMyBookings(id){
                    
                const xhttp = new XMLHttpRequest();
                xhttp.open("POST", "http://localhost:8080/Hotel_Reservation_System_WT/room/viewBookings.htm", true);
                xhttp.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");

                xhttp.onreadystatechange = function () {
                    if (this.readyState == 4 && this.status == 200) {
                        // Typical action to be performed when the document is ready:

                       
                            window.location.href = "http://localhost:8080/Hotel_Reservation_System_WT/room/searchRooms.htm";
                     
                    }
                };
                xhttp.send("param1=" + id);
                              
                    
                }
                
                </script>

            <script>
                
                function logout()
                {
                   alert("logout clicked"); 
                   
                <%
                   
                    
                    %>
                      window.location.href = "http://localhost:8080/Hotel_Reservation_System_WT/room/login.htm";       
    }
                </script>
            
            
        <!-- Include JavaScript libraries -->
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jqueryui/1.12.1/jquery-ui.min.js"></script>
        <script>
                            $(function () {
                                // Initialize date picker
                                $('.date-picker').datepicker();

                                // Other JavaScript logic goes here
                            });

                            function bookRoom(roomId) {
                                // Logic to handle booking the room with the given ID
                                // You can use JavaScript or make an AJAX call to the server
                                alert("hello");
                                var bookingDate = "<%= bookingDate%>";
                                
                                const xhttp = new XMLHttpRequest();
                xhttp.open("POST", "http://localhost:8080/Hotel_Reservation_System_WT/room/bookRoom.htm", true);
                xhttp.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");

                xhttp.onreadystatechange = function () {
                    if (this.readyState == 4 && this.status == 200) {
                        // Typical action to be performed when the document is ready:

                        const obj = JSON.parse(xhttp.responseText);
                        //alert(typeof obj.checkLoginResult);

                        if (obj.added === "true")
                        {
                            alert("Booking Successful");
                            var uniqueId = obj.uniqueId;
                            window.location.href = "http://localhost:8080/Hotel_Reservation_System_WT/room/report.pdf?bookingDate="+bookingDate+ "&roomId=" +roomId+ "&uniqueId=" +uniqueId+ " ";
                        }else{
                            alert("Booking Unsuccessful");
                        }
                    }
                };
                xhttp.send("param1=" + bookingDate + "&param2=" + roomId);
                                



                            }
        </script>
    </body>
</html>
