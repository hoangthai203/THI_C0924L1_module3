<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Mượn sách</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-5">
    <h2 class="text-center">Mượn sách</h2>

    <!-- Hiển thị thông báo lỗi nếu có -->
    <c:if test="${not empty errorMessage}">
        <div class="alert alert-danger" role="alert">
                ${errorMessage}
        </div>
    </c:if>

    <form action="borrow" method="post">
        <div class="mb-3">
            <label for="maMuon" class="form-label">Mã mượn sách</label>
            <input type="text" class="form-control" id="maMuon" name="maMuon" required pattern="MS-\d{4}" title="Mã mượn sách phải theo định dạng MS-XXXX (X là số nguyên dương)">
        </div>
        <div class="mb-3">
            <label for="tenSach" class="form-label">Tên sách</label>
            <input type="text" class="form-control" id="tenSach" value="${book.tenSach}" readonly>
            <input type="hidden" name="maSach" value="${book.maSach}">
        </div>
        <div class="mb-3">
            <label for="maHS" class="form-label">Tên học sinh</label>
            <select class="form-select" id="maHS" name="maHS" required>
                <option value="">-- Chọn học sinh --</option>
                <c:forEach var="student" items="${students}">
                    <option value="${student.maHs}">${student.hoTen}</option>
                </c:forEach>
            </select>
        </div>
        <div class="mb-3">
            <label for="ngayMuon" class="form-label">Ngày mượn sách</label>
            <input type="text" class="form-control" id="ngayMuon" name="ngayMuon" value="<%= new java.text.SimpleDateFormat("dd/MM/yyyy").format(new java.util.Date()) %>" readonly>
        </div>
        <div class="mb-3">
            <label for="ngayTra" class="form-label">Ngày trả sách</label>
            <input type="text" class="form-control" id="ngayTra" name="ngayTra" required>
        </div>
        <button type="submit" class="btn btn-primary">Mượn sách</button>
        <a href="book-list" class="btn btn-secondary">Trở về danh sách</a>
    </form>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>