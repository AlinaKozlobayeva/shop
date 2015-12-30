<%--
  Created by IntelliJ IDEA.
  User: Alina
  Date: 02.08.2015
  Time: 19:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="java.util.*,entity.User" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title></title>
    <link rel="stylesheet" type="text/css" href="../css/main.css">
</head>
<body>
<div class="container">

    <div class="formContainer">
        <form action="login" method="post">
            Login:<br> <input type="text" name="login" required>
            <br>
            Password:<br> <input type="text" name="password" required>
            <br>
            <input type="submit" value="Log In">

        </form>
        <input type="button" value="Sign Up" onclick="location.href='/pages/newUser.jsp'"/>
        <br>
        <input type="button" value="admin" onclick="location.href='/pages/adminPage.jsp'"/>
    </div>
</div>


</body>
</html>
