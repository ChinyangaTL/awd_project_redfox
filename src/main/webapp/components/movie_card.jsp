<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: les_miserables
  Date: 05/12/22
  Time: 10:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<form method="get" action="SingleMovie">
    <input hidden name="singleMovieId" value=${movie.id} />
    <article class='menu-item'>
        <img src=${movie.imgUrl} alt=${movie.movieTitle} class='photo' />
        <div class='item-info'>
            <header>
                <h4>${movie.movieTitle}</h4>
                <h4 class='price'>${movie.rating}</h4>
            </header>
            <p class='item-text'>${movie.description}</p>


            <input type="submit" value="View More" />
        </div>
    </article>
</form>

</body>
</html>
