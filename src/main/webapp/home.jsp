<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Movies</title>

  <link rel="stylesheet" href="./styles/globalStyles.css">
  <link rel="stylesheet" href="./styles/components.css">
  <link rel="stylesheet" href="./styles/clientHome.css">
</head>
<body>
<main>
  <section class="section">
    <div class='section-center'>

      <c:forEach var="movie" items="${movie_list}">
        <article class='menu-item'>
          <img src=${movie.imgUrl} alt=${movie.movieTitle} class='photo' />
          <div class='item-info'>
            <header>
              <h4>${movie.movieTitle}</h4>
              <h4 class='price'>${movie.rating}</h4>
            </header>
            <p class='item-text'>${movie.description}</p>
          </div>
        </article>
      </c:forEach>

    </div>
  </section>
</main>

</body>
</html>
