<%@ page import="java.util.Date" %><%--
  Created by IntelliJ IDEA.
  User: dolawojc
  Date: 02.03.20
  Time: 17:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>Pierwszy servlet</title>
  </head>
  <body>
  Witam serdecznie
  <%
    Date tmp = new Date();
    out.println("<h2> " + tmp.toString() + " </h2>");
  %>
  </body>
</html>
