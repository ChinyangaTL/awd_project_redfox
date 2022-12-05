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

    <link rel="stylesheet" href="./styles/globalStyles.css">
    <link rel="stylesheet" href="./styles/components.css">
    <link rel="stylesheet" href="./styles/singleMovie.css">
</head>
<body>

<c:set var="movie" value='<%= request.getAttribute("currentMovie") %>'>

</c:set>
    

<article class='cocktail'>
    <div class='img-container'>
        <img src=${movie.imgUrl} alt=${movie.movieTitle} />
    </div>
    <div class='cocktail-footer'>
        <h3>${movie.movieTitle}</h3>
        <h4>Directed By: ${movie.director.firstName} ${movie.director.lastName}</h4>
        <p style="display: inline">Starring: </p>
        <c:forEach var="actor" items="${movie.actors}">
            <p style="display: inline"> ${actor.firstName} ${actor.lastName}</p>,
        </c:forEach>

        <p>Description: ${movie.description}</p>
    </div>
</article>

<div>
    <p>Seen this movie? Give it a rating</p>
    <form method="GET" action="ClientController">
        <input type="number" name="rating" min="0" max="5">
        <input type="hidden" name="command" value="RATE_MOVIE" />
        <input type="hidden" name="movieId" value="${movie.id}" />
        <input type="submit" value="Rate" />
    </form>
</div>

<div>
    <form method="GET" action="ClientController">
        <input type="hidden" name="command" value="ADD_TO_FAVS" />
        <input type="hidden" name="movieId" value="${movie.id}" />
        <input type="submit" value="Add To Favs" />
    </form>
</div>

</body>
</html>
