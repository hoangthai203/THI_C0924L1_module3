package com.codegym.demo.controller;

import com.codegym.demo.model.Book;
import com.codegym.demo.service.BookService;
import com.codegym.demo.service.BorrowService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.text.ParseException;

public class BorrowController extends HttpServlet {

    private BorrowService borrowService = new BorrowService();
    private BookService bookService = new BookService();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String maMuon = request.getParameter("maMuon");
        String maSach = request.getParameter("maSach");
        String maHS = request.getParameter("maHS");

        // Kiểm tra nếu các tham số bắt buộc được gửi lên
        if (maMuon == null || maMuon.isEmpty() || maSach == null || maSach.isEmpty() || maHS == null || maHS.isEmpty()) {
            response.sendRedirect("error.jsp");
            return;
        }

        try {
            // Lấy giá trị ngày mượn và ngày trả
            String ngayMuonStr = request.getParameter("ngayMuon");
            String ngayTraStr = request.getParameter("ngayTra");

            // Kiểm tra nếu ngày mượn hoặc ngày trả bị thiếu
            if (ngayMuonStr == null || ngayTraStr == null || ngayMuonStr.isEmpty() || ngayTraStr.isEmpty()) {
                response.sendRedirect("error.jsp");
                return;
            }

            // Chuyển đổi ngày mượn và ngày trả sang kiểu Date
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

            // Kiểm tra và phân tích ngày mượn
            Date ngayMuon = null;
            Date ngayTra = null;
            try {
                ngayMuon = new Date(sdf.parse(ngayMuonStr).getTime());
                ngayTra = new Date(sdf.parse(ngayTraStr).getTime());
            } catch (ParseException e) {
                response.sendRedirect("error.jsp");
                return;
            }

            // Kiểm tra điều kiện ngày trả phải không trước ngày mượn
            if (ngayTra.before(ngayMuon)) {
                response.sendRedirect("error.jsp");
                return;
            }

            // Gọi service để mượn sách
            boolean result = borrowService.borrowBook(maMuon, maSach, maHS, ngayMuon, ngayTra);
            if (result) {
                response.sendRedirect("book-list");  // Quay lại danh sách sách
            } else {
                response.sendRedirect("borrow-fail");  // Thông báo mượn sách thất bại
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp");  // Nếu có lỗi khác, redirect về trang lỗi
        }
    }

    // Đảm bảo nếu dùng GET để hiển thị form mượn sách, bạn cũng phải xử lý đúng
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String maSach = request.getParameter("maSach");

        if (maSach == null || maSach.isEmpty()) {
            response.sendRedirect("error.jsp");
            return;
        }

        // Lấy sách cần mượn từ dịch vụ
        Book book = bookService.getBookById(maSach);
        if (book == null) {
            response.sendRedirect("error.jsp");
            return;
        }

        // Gửi dữ liệu tới trang mượn sách
        request.setAttribute("book", book);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/borrowForm.jsp");
        dispatcher.forward(request, response);
    }
}
