<%@ page contentType="text/html; charset=UTF-8" %>
<%
  String user = (String) session.getAttribute("user");
  if (user == null) {
      response.sendRedirect(request.getContextPath() + "/login.jsp?error=Please+login+first");
      return;
  }
%>
<!DOCTYPE html>
<html>
<head><title>Home</title></head>
<body>
  <h2>Welcome, <%= user %>!</h2>
  <p>This is Home page.</p>
</body>
</html>
