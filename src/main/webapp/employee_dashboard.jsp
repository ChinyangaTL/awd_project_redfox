<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Dashboard</title>
</head>
<body>
<main>
    <section class="section-center">
        <input type="button" value="Add New Actor"
               onclick="window.location.href='add_actor_form.jsp'; return false;"
        />
        <input type="button" value="Add New Director"
               onclick="window.location.href='add_director_form.jsp'; return false;"
        />

        <input type="button" value="Add Movie"
               onclick="window.location.href='add_movie_form.jsp'; return false;"
        />


        <c:forEach var="movie" items="${movie_list}">
            <article class='employee-item'>
                <p class='title'>${movie.movieTitle}</p>
                <c:forEach var="actor" items="${movie.actors}">
                    <p>${actor.firstName}</p>
                </c:forEach>
                <div class='btn-container'>
                    <button
                            type='button'
                            class='edit-btn'
                    >
                        Edit
                    </button>
                    <button
                            type='button'
                            class='delete-btn'

                    >
                        Delete
                    </button>
                </div>
            </article>
        </c:forEach>
    </section>
</main>
</body>
</html>
