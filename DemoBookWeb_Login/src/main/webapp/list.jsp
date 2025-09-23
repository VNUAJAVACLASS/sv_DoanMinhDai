<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta charset="UTF-8">
<title>Danh sách đầu sách</title>
</head>
<body>
	<p> Xin chào: ${sessionScope.username} | <a href="login.jsp"> Đăng xuất</a></p>
	<hr>
	<h2>Danh sách đầu sách</h2>
	<a href="clientHome?action=create">Thêm sách</a>
	<br>
	<br>
	<table border="1" cellpadding="5">
		<tr>
			<th>ID</th>
			<th>Tiêu đề</th>
			<th>Tác giả</th>
			<th>Giá</th>
			<th>Hình ảnh</th>
			<th>Hành động</th>
		</tr>
		<c:forEach var="books" items="${booksList}">
			<tr>
				<td>${books.bookId}</td>
				<td>${books.title}</td>
				<td>${books.author}</td>
				<td>${books.price}</td>
				<td>${books.imagePath}</td>
				<td>
					<!-- Khi click sẽ gửi yêu cầu về servlet trả lại trang form.jsp -->
					<a href="clientHome?action=edit&id=${books.bookId}">Sửa</a> | <a
					href="clientHome?action=delete&id=${books.bookId}"
					onclick="return confirm('Xoá sách này?');">Xoá</a>
				</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>