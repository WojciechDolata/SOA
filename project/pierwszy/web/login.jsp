<%@ page import="java.util.Vector" %>
<%@ page import="soa.ksiegaGosci.DaneOsobowe" %><%--
  Created by IntelliJ IDEA.
  User: dolawojc
  Date: 07.03.20
  Time: 11:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
<form action="LoginServlet" method="post">
    Login:
    <input type="text" name="login"/>
    <br/>
    Hasło:
    <input type="password" name="password"/>
    <input type="submit" name="Wyślij"/>
</form>
</body>
</html>
