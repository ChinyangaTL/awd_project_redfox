<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Dashboard</title>
    <link rel="stylesheet" href="./styles/globalStyles.css">
    <link rel="stylesheet" href="./styles/components.css">
    <link rel="stylesheet" href="./styles/employees.css">
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
<main>
    <section>
        <input type="button" value="Add New Actor"
               onclick="window.location.href='add_actor_form.jsp'; return false;"
        />
        <input type="button" value="Add New Director"
               onclick="window.location.href='add_director_form.jsp'; return false;"
        />

        <input type="button" value="Add Movie"
               onclick="window.location.href='add_movie_form.jsp'; return false;"
        />
        <table>
            <tr>
                <th>Movie Name</th>
                <th>Release Year</th>
                <th>Genre</th>
            </tr>
            <c:forEach var="movie" items="${movie_list}">
            <tr>
                <td>${movie.movieTitle}</td>
                <td>${movie.releaseDate}</td>
                <td>${movie.genre}</td>
            </tr>
            </c:forEach>
        </table>



<%--            <article class='employee-item'>--%>
<%--                <p class='title'></p>--%>
<%--&lt;%&ndash;                <c:forEach var="actor" items="${movie.actors}">&ndash;%&gt;--%>
<%--&lt;%&ndash;                    <p>${actor.firstName}</p>&ndash;%&gt;--%>
<%--&lt;%&ndash;                </c:forEach>&ndash;%&gt;--%>
<%--                <p>$</p>--%>
<%--                <div class='btn-container'>--%>
<%--                    <button--%>
<%--                            type='button'--%>
<%--                            class='edit-btn'--%>
<%--                    >--%>
<%--                        Edit--%>
<%--                    </button>--%>
<%--                    <button--%>
<%--                            type='button'--%>
<%--                            class='delete-btn'--%>

<%--                    >--%>
<%--                        Delete--%>
<%--                    </button>--%>
<%--                </div>--%>
<%--            </article>--%>

    </section>
</main>
</body>
</html>
