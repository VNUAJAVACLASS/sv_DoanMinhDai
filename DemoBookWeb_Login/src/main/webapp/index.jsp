<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<table border="1" cellpadding="5">
    <tr>
        <th>ID</th>
        <th>Tiêu đề</th>
        <th>--</th>
    </tr>

    <c:forEach var="books" items="${bookList}">
        <tr>
            <td>${books.bookId}</td>
            <td>
                <h4>${books.title}</h4>
            </td>
            <td>
                <a href="clientHome?action=detail&id=${books.bookId}">Xem chi tiết</a>
            </td>
        </tr>
    </c:forEach>
</table>
	
</body>
</html>