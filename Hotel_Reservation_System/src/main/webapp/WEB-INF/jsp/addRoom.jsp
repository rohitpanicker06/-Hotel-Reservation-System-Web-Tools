<%-- 
    Document   : addRoom
    Created on : Jun 26, 2023, 12:47:34 PM
    Author     : rohitpanicker
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Welcome, Please enter the details to add a new Hotel Room</h1>


        <form:form modelAttribute="room"  method="post">
            id: <form:input path="id" /> <form:errors path="description" /> <br/>
            description:<form:input path="description"/>  <form:errors path="description" /> <br>
            <input type="submit"/>
        </form:form>

   

    </body>
</html>
