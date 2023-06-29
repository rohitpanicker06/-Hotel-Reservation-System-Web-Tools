<%-- 
    Document   : viewHotelRooms
    Created on : Jun 28, 2023, 1:15:11 PM
    Author     : rohitpanicker
--%>

<%@page import="java.util.List"%>
<%@page import="com.mycompany.hotel_reservation_system.pojo.Room"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
            List<Room> roomList = (List<Room>)request.getAttribute("allRoomsList");
            out.println(roomList.get(0).getId());
            %>
            
            Cover Image: <img src="/covers/<%= roomList.get(0).getId() %>.jpg"/><br>
    </body>
</html>
