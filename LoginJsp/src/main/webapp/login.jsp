<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="vi">
<head>
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>Login</title>
  <!-- Link CSS ngoài -->
  <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/login.css">
</head>
<body>

  <div class="modal">
    <form class="modal-content"
          action="${pageContext.request.contextPath}/login"
          method="post">
      <div class="imgcontainer">
        <img src="${pageContext.request.contextPath}/static/img/img_avatar2.png" alt="Avatar" class="avatar">
        <div class="title">Đăng nhập</div>
        <div class="subtitle">Mời nhập thông tin tài khoản của bạn</div>
      </div>

      <div class="container">
        <c:if test="${not empty param.error}">
          <div class="error">${param.error}</div>
        </c:if>

        <label for="uname"><b>Username</b></label>
        <input type="text" placeholder="Enter Username" name="username" id="uname" required>

        <label for="psw"><b>Password</b></label>
        <input type="password" placeholder="Enter Password" name="password" id="psw" required>

        <label class="remember">
          <input type="checkbox" name="remember" checked="checked"> Remember me
        </label>

        <button type="submit">Login</button>
      </div>

      <div class="container footer">
        <span class="psw">Forgot <a href="#">password?</a></span>
      </div>
    </form>
  </div>

</body>
</html>
