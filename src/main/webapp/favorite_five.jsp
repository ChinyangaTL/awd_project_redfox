<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>My Fav Five</title>
    <link rel="stylesheet" href="./styles/globalStyles.css">
    <link rel="stylesheet" href="./styles/components.css">

    <style>
        table {
            font-family: arial, sans-serif;
            border-collapse: collapse;
            width: 100%;
        }

        td, th {
            border: 1px solid #dddddd;
            text-align: left;
            padding: 8px;
        }

        tr:nth-child(even) {
            background-color: #dddddd;
        }
    </style>
</head>
<body>

<table>
    <tr>
        <th>Movie Name</th>
        <th>Release Year</th>
        <th>Genre</th>
        <th>Director</th>
    </tr>
    <c:forEach var="favoriteMovies" items='<%= session.getAttribute("favoriteMovies")%>'>
        <tr>
            <td>${favoriteMovies.movieTitle}</td>
            <td>${favoriteMovies.releaseDate}</td>
            <td>${favoriteMovies.genre}</td>
            <td>${favoriteMovies.director.firstName} ${favoriteMovies.director.lastName}</td>
        </tr>
    </c:forEach>
</table>

</body>
</html>
