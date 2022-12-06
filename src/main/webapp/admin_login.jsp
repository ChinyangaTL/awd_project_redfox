<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Admin Login</title>
  <link rel="stylesheet" href="./styles/globalStyles.css">
  <link rel="stylesheet" href="./styles/components.css">
</head>
<body>
<main>
  <form action="AdminController" method="POST">
    <table>
      <tbody>
      <tr>
        <td><label>Email:</label></td>
        <td><input required type="email" name="email" /></td>
      </tr>

      <tr>
        <td><label>Password:</label></td>
        <td><input required type="password" name="password" /></td>
      </tr>

      <tr>
        <td><label></label></td>
        <td><input type="submit" value="Login" /></td>
      </tr>

      </tbody>
    </table>
    ${message}
    <a href="employee_login.jsp">Employee Login</a> <br/>
    <a href="login.jsp">User Login</a>
  </form>
</main>

</body>
</html>
