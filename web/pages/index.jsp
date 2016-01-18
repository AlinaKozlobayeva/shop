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
    <script type="text/javascript" src="../js/dynamicTable.js"></script>

</head>
<body>
<header>
    <input type="button" value="Sign Up" onclick="location.href='/pages/newUser.jsp'"/>
    <br>
    <input type="button" value="admin" onclick="location.href='/pages/adminPage.jsp'"/>
</header>


<div class="container">

    <div class="table_body" id="tableArea">


    </div>
    <div id="login-form">
        <h3>Login</h3>
        <fieldset>

            <div class="formContainer">
                <form action="login" method="post">
                    Login: <input type="login" name="login" required>
                    <br>
                    Password: <input type="password" name="password" required>
                    <input type="submit" value="Log In">
                </form>
            </div>

            <%--<footer class="clearfix">--%>
            <%--<p><span class="info">?</span><a href="#">Forgot Password</a></p>--%>
            <%--</footer>--%>
        </fieldset>
    </div>
</div>

<footer class="main_footer">
    <p>Produced by Alina Kozlobaeva</p>
    <p>Mail Us: aaaa@gmail.com</p>
</footer>
</body>
</html>
