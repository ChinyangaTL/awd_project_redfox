<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

  <c:forEach var="favoriteMovies" items='<%= session.getAttribute("favoriteMovies")%>'>
    <p>${favoriteMovies.movieTitle}</p>
  </c:forEach>
</body>
</html>
