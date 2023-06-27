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
            
        Server Version: <%= application.getServerInfo() %><br>
Servlet Version: <%= application.getMajorVersion() %>.<%= application.getMinorVersion() %>
JSP Version: <%= JspFactory.getDefaultFactory().getEngineInfo().getSpecificationVersion() %> <br>

        <form:form modelAttribute="room"  method="post" enctype="multipart/form-data">
            id: <form:input path="id" /> <form:errors path="id" /> <br/>
            description:<form:input path="description"/>  <form:errors path="description" /> <br>
            Upload picture : <input type="file" name="photo" required="required" /> <form:errors path="photo" /> <br>
            <input type="submit"/>
        </form:form>

   

    </body>
</html>
