<%--
  Created by IntelliJ IDEA.
  User: les_miserables
  Date: 04/12/22
  Time: 19:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
  <form action="AdminController" method="POST">
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
  </form>
</body>
</html>
