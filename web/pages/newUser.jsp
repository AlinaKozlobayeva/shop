<%--
  Created by IntelliJ IDEA.
  User: Alina
  Date: 04.11.2015
  Time: 14:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>sign up page</title>
    <script type="text/javascript" src="../js/newUser.js"></script>
</head>
<body>
<div>
    <form name="newUser" action="signUp" method="post" onsubmit="return validName()">
        First name: <input type="text" name="firstName" datatype="text">
        <br>
        Last name: <input type="text" name="lastName">
        <br>
        age: <input type="number" name="age" value="0" min="0" max="100" maxlength="4">
        <br>
        email: <input type="email" name="email">
        <br>
        login:<input type="text" name="login">
        <br>
        password: <input type="text" name="password">
        <br>
        confirm password: <input type="text" name="confirmPassword">
        <br>
        <input type="submit" value="Submit">
    </form>
</div>
</body>
</html>
