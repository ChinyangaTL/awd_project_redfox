<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.github.chinyangatl.redfox.model.beans.Movie" %><%--
  Created by IntelliJ IDEA.
  User: les_miserables
  Date: 05/12/22
  Time: 03:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <% request.setAttribute("movie", request.getParameter("movie"));%>
    <c:set var="movie" >
        ${movie.movieTitle}
    </c:set>
</body>
</html>
