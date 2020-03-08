<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="soa.ksiegaGosci.Comment" %><%--
  Created by IntelliJ IDEA.
  User: wojci
  Date: 07.03.2020
  Time: 17:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Ksiega gosci</title>
</head>
<body>

    <% java.util.List<Comment> x = soa.ksiegaGosci.LoginServlet.getComments();
    %>
    <c:set var="n" value="<%=x%>"/>

    ${imie}, witamy ponownie!
    <br/>
    <br/>
    <br/>

    <c:forEach items="<%=x%>" var="element">
        <c:out value="${element.toString()}"/>
        <br/>
        <br/>
        <br/>
    </c:forEach>

    <textarea form="CommentForm" name="comment">Tu wpisz komentarz</textarea>
    <form action="LoginServlet" method="get" id="CommentForm">
        <input type="submit" value="Wyślij"/>
    </form>
    <form action="LoginServlet" method="get">
        <input type="submit" value="Odśwież"/>
    </form>
    <form action="LoginServlet" method="get">
        <input type="hidden" name="logout" value="true"/>
        <input type="submit" value="Wyloguj"/>
    </form>
</body>
</html>
