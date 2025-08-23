<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
<meta charset="UTF-8">
<title>Chi tiết tin tức</title>
</head>
<body>
	<h2>${news.title}</h2>
	<p>${news.content}</p>
	<br>
	<a href="${pageContext.request.contextPath}/news">Quay lại danh sách</a>
</body>
</html>