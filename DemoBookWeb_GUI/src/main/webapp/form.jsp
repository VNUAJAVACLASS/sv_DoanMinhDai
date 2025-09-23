<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
    request.setAttribute("isEdit", request.getAttribute("book") != null);
%>
<!DOCTYPE html>
<html lang="vi">
<head>
  <meta charset="UTF-8">
  <title>${isEdit ? "Sửa thông tin sách" : "Thêm sách"}</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">
<div class="container mt-4">
  <div class="card shadow-sm">
    <div class="card-header bg-primary text-white">
      <h4 class="mb-0">${isEdit ? "Sửa thông tin sách" : "Thêm sách"}</h4>
    </div>
    <div class="card-body">
      <form action="books" method="post">
        <c:if test="${isEdit}">
          <input type="hidden" name="id" value="${book.bookId}">
        </c:if>

        <div class="mb-3">
          <label class="form-label">Tiêu đề</label>
          <input type="text" name="title" value="${book.title}" class="form-control" required>
        </div>

        <div class="mb-3">
          <label class="form-label">Tác giả</label>
          <input type="text" name="author" value="${book.author}" class="form-control" required>
        </div>

        <div class="mb-3">
          <label class="form-label">Giá</label>
          <input type="number" name="price" value="${book.price}" class="form-control" required>
        </div>

        <div class="mb-3">
          <label class="form-label">Link hình ảnh</label>
          <input type="text" name="imagePath" value="${book.imagePath}" class="form-control">
          <div class="form-text">Nếu để trống sẽ dùng ảnh mặc định.</div>
        </div>

        <button type="submit" class="btn btn-success">
          ${isEdit ? "Cập nhật" : "Tạo mới"}
        </button>
        <a href="books" class="btn btn-secondary">Quay lại danh sách</a>
      </form>
    </div>
  </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
