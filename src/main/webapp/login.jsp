<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
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

    <a href="register.jsp"></a>

    </tbody>
  </table>
</form>
</body>
</html>
