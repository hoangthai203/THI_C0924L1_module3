<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Danh sách sách đã mượn</title>
    <style>
        table {
            width: 100%;
            border-collapse: collapse;
        }
        th, td {
            border: 1px solid #ddd;
            padding: 8px;
            text-align: left;
        }
        th {
            background-color: #f2f2f2;
        }
    </style>
</head>
<body>
<h1>Danh sách sách đã mượn</h1>

<c:if test="${empty borrowRecords}">
    <p>Không có sách nào được mượn.</p>
</c:if>

<c:if test="${not empty borrowRecords}">
    <table>
        <thead>
        <tr>
            <th>Mã mượn</th>
            <th>Tên sách</th>
            <th>Học sinh</th>
            <th>Ngày mượn</th>
            <th>Ngày trả</th>
            <th>Trạng thái</th>
            <th>Hành động</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="record" items="${borrowRecords}">
            <tr>
                <td>${record.maMuon}</td>
                <td>${record.tenSach}</td>
                <td>${record.hoTenHS}</td>
                <td><fmt:formatDate value="${record.ngayMuon}" pattern="dd/MM/yyyy"/></td>
                <td><fmt:formatDate value="${record.ngayTra}" pattern="dd/MM/yyyy"/></td>
                <td>
                    <c:choose>
                        <c:when test="${record.trangThai}">Đang mượn</c:when>
                        <c:otherwise>Đã trả</c:otherwise>
                    </c:choose>
                </td>
                <td>
                    <c:if test="${record.trangThai}">
                        <a href="return-book?maMuon=${record.maMuon}">Trả sách</a>
                    </c:if>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</c:if>

<p><a href="book-list">Quay lại danh sách sách</a></p>
</body>
</html>