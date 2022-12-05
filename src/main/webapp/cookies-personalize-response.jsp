<html>

<head><title>Confirmation</title></head>

<%
  String favCategory = request.getParameter("favCategory");

  Cookie theCookie = new Cookie("favCategory", favCategory);
  theCookie.setMaxAge(60*60*24);

  response.addCookie(theCookie);
%>
<body>

Thanks! We set your favorite movie category to: ${param.favCategory}

<br/><br/>

<a href="ClientController">Return to homepage.</a>

</body>

</html>








