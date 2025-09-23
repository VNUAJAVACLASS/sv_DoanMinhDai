<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="vi">
<head>
<meta charset="UTF-8">
<title>Quản lý đầu sách</title>
<!-- Bootstrap CSS -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/static/css/list.css">
</head>
<body>
	<!-- Sidebar -->
	<div class="sidebar">
		<h4>Admin</h4>
		<a href="#">Bảng điều khiển</a> <a href="#">Quản lý nhân viên</a> <a
			href="#">Quản lý khách hàng</a> <a href="#">Quản lý sản phẩm</a> <a
			href="#">Quản lý đơn hàng</a> <a href="#">Báo cáo</a> <a href="#">Cài
			đặt</a>
	</div>

	<!-- Content -->
	<div class="content">
		<h2 class="mb-3">Danh sách đầu sách</h2>
		<a href="books?action=create" class="btn btn-primary mb-3">+ Thêm
			sách</a>

		<table class="table table-striped table-bordered align-middle">
			<thead class="table-dark">
				<tr>
					<th>ID</th>
					<th>Tiêu đề</th>
					<th>Tác giả</th>
					<th>Giá</th>
					<th>Hình ảnh</th>
					<th>Hành động</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="books" items="${booksList}">
					<tr>
						<td>${books.bookId}</td>
						<td>${books.title}</td>
						<td>${books.author}</td>
						<td>${books.price}</td>
						<td><c:choose>
								<c:when test="${not empty books.imagePath}">
									<img
										src="${pageContext.request.contextPath}/${books.imagePath}"
										alt="${books.title}"
										style="width: 60px; height: 60px; object-fit: cover;"
										class="img-thumbnail">
								</c:when>
								<c:otherwise>
									<img
										src="${pageContext.request.contextPath}/static/images/1.jpg"
										alt="No image"
										style="width: 60px; height: 60px; object-fit: cover;"
										class="img-thumbnail">
								</c:otherwise>
							</c:choose></td>

						<td><a href="books?action=edit&id=${books.bookId}"
							class="btn btn-sm btn-warning">Sửa</a> <a
							href="books?action=delete&id=${books.bookId}"
							class="btn btn-sm btn-danger"
							onclick="return confirm('Xoá sách này?');"> Xoá </a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>

	<!-- Bootstrap JS -->
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>

