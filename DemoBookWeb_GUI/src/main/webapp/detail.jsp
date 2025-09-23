<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="vi">
<head>
  <meta charset="UTF-8">
  <title>Chi tiết sách</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">
<div class="container mt-4">
  <div class="card shadow-sm">
    <div class="row g-0">
      <div class="col-md-4 text-center p-3">
        <c:choose>
          <c:when test="${not empty book.imagePath}">
            <img src="${pageContext.request.contextPath}/${book.imagePath}" 
                 alt="${book.title}" 
                 class="img-fluid rounded">
          </c:when>
          <c:otherwise>
            <img src="${pageContext.request.contextPath}/static/images/1.jpg" 
                 alt="No image" 
                 class="img-fluid rounded">
          </c:otherwise>
        </c:choose>
      </div>
      <div class="col-md-8">
        <div class="card-body">
          <h3 class="card-title">${book.title}</h3>
          <p class="card-text"><strong>Tác giả:</strong> ${book.author}</p>
          <p class="card-text"><strong>Giá:</strong> ${book.price} ₫</p>
          <a href="${pageContext.request.contextPath}/books" class="btn btn-secondary mt-3">Quay lại danh sách</a>
        </div>
      </div>
    </div>
  </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
