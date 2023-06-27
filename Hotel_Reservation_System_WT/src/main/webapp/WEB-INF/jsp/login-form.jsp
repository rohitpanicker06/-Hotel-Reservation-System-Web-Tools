<%-- 
    Document   : login-form
    Created on : Jun 27, 2023, 4:17:27 PM
    Author     : rohitpanicker
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Login Form</h1>
        <form action="login.htm" method="post">
            Username: <input type="text" name="username" /><!-- comment -->
            Password: <input type="password" name="password"/>
            <input type="submit" value="Login"/><!-- comment -->
        </form>
    </body>
</html>
