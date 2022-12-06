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
  <%
    String favCategory = "All";

    Cookie[] theCookies = request.getCookies();

    if (theCookies != null) {

      for (Cookie tempCookie : theCookies) {

        if ("favCategory".equals(tempCookie.getName())) {
          favCategory = tempCookie.getValue();
          request.setAttribute("favCategory", favCategory);
          break;
        }
      }
    }
  %>

  <section class="menu section">

    <div class="title">
      <h2>RedFox</h2>
      <div class="underline"></div>
    </div>

    <nav>
      <c:set var="user" value='<%= session.getAttribute("client") %>'/>
        <p>Welcome ${user.getFirstName()}</p>

      <form action="ClientController" method="get">
        <input type="hidden" name="command" value="GET_FAVS" />
        <input type="submit" value="My Fav Five">
      </form>

    </nav>

    <jsp:include page="components/categories.jsp"/>

    <section class="section-center">
      <c:if test="${favCategory != 'All'}">
        <c:forEach var="movie" items="${movie_list}">
          <c:if test="${favCategory.toLowerCase() == movie.genre.toLowerCase()}">
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

          </c:if>
        </c:forEach>
      </c:if>

      <c:if test="${not favCategory != 'All'}">
        <a href="cookies-personalize-form.html">Personalize your experience</a>
      </c:if>
    </section>

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
