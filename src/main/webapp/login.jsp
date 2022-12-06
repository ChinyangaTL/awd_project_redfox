<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Redfox | Login</title>
  <link rel="stylesheet" href="./styles/globalStyles.css">
  <link rel="stylesheet" href="./styles/components.css">
</head>
<body>
<main>
  <form action="ClientController" method="POST">
    <input type="hidden" name="command" value="LOGIN" />
    <table>
      <tbody>
      <tr>
        <td><label>Email:</label></td>
        <td><input type="email" name="email" /></td>
      </tr>

      <tr>
        <td><label>Password:</label></td>
        <td><input type="password" name="password" /></td>
      </tr>

      <tr>
        <td><label></label></td>
        <td><input type="submit" value="Login" /></td>
      </tr>



      </tbody>
    </table>
    <a href="register.jsp">Don't have an account? Login</a>  <br/>
    <a href="employee_login.jsp">Employee Login</a>
  </form>
</main>
</body>
</html>
