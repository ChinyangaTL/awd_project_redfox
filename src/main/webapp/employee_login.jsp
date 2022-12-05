<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
  <form action="EmployeeController" method="POST">
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
