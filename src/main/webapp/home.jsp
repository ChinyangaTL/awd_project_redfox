<%@ page import="java.net.http.HttpRequest" %>
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
  <section class="menu section">

    <div className="title">
      <h2>RedFox</h2>
      <div className="underline"></div>
    </div>

    <jsp:include page="components/categories.jsp"/>




    <section class="section-center">



      <c:forEach var="movie" items="${movie_list}">
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
      </c:forEach>


    </section>




  </section>
</main>

</body>
</html>
