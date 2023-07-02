<%-- 
    Document   : AccessErrorPage
    Created on : Jun 30, 2023, 12:49:14 PM
    Author     : rohitpanicker
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Access Error</title>
</head>
<body>
    <h1>Access Error</h1>
    <p>Sorry, you do not have permission to access this page.</p>
    <button onclick="goBack()">Go Back</button>

    <script>
        function goBack() {
            window.history.back();
        }
    </script>
</body>
</html>

