<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Sách đang mượn</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-5">
    <h2 class="text-center">Danh sách sách đang mượn</h2>
    <table class="table table-bordered mt-4">
        <thead>
        <tr>
            <th>Mã sách</th>
            <th>Tên sách</th>
            <th>Tên học sinh</th>
            <th>Ngày mượn</th>
            <th>Ngày trả</th>
            <th>Chức năng</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="borrowedBook" items="${borrowedBooks}">
            <tr>
                <td>${borrowedBook.maSach}</td>
                <td>${borrowedBook.tenSach}</td>
                <td>${borrowedBook.tenHocSinh}</td>
                <td>${borrowedBook.ngayMuon}</td>
                <td>${borrowedBook.ngayTra}</td>
                <td>
                    <a href="return-book?maMuon=${borrowedBook.maMuon}" class="btn btn-success">Trả sách</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
