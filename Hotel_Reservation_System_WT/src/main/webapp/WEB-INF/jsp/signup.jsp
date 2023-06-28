<%-- 
    Document   : signup
    Created on : Jun 27, 2023, 6:49:10 PM
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
        <h1>Sign Up : Please Enter your Details</h1>
        
          <h1>Login Form</h1>
        <form action="signup.htm" method="post">
            Username: <input type="text" name="userName" /><!-- comment -->
            Password: <input type="password" name="password"/>
            Email Address : <input type="text" name="email" />
            Role: <input type="text" name="role"/>
            <input type="submit" value="Sign Up"/><!-- comment -->
        </form>
    </body>
</html>
