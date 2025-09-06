<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
request.setAttribute("isEdit", request.getAttribute("book") != null);
%>
<html>
<head>
<meta charset="UTF-8">
<title>${isEdit ? 'Sửa thông tin sách' : 'Thêm sách'}</title>
</head>
<body>
	<h2>${isEdit ? 'Sửa thông tin sách' : 'Thêm sách'}</h2>
	<form action="books" method="post">
		<c:if test="${isEdit}">
			<input type="hidden" name="id" value="${book.bookId}">
		</c:if>
		Tiêu đề: <br> <input type="text" name="title"
			value="${book.title}" required><br>
		Tác giả: <br> <input type="text" name="author" 
			value="${book.author}" required><br>
		Giá: <br> <input type="text" name="price" value="${book.price}"
			required><br> Hình ảnh: <br> <input type="text"
			name="imagePath" value="${book.imagePath}" required><br> <br>
		<input type="submit" value="${isEdit ? 'Cập nhật' : 'Tạo mới'}">
	</form>
	<br>
	<a href="books">Quay lại danh sách</a>
</body>
</html>