package com.codegym.demo.controller;

import com.codegym.demo.model.Book;
import com.codegym.demo.model.Student;
import com.codegym.demo.service.BookService;
import com.codegym.demo.service.BorrowService;
import com.codegym.demo.service.StudentService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.List;

public class BorrowController extends HttpServlet {

    private BorrowService borrowService = new BorrowService();
    private BookService bookService = new BookService();
    private StudentService studentService = new StudentService();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String maMuon = request.getParameter("maMuon");
        String maSach = request.getParameter("maSach");
        String maHS = request.getParameter("maHS");

        if (maMuon == null || maMuon.isEmpty() || maSach == null || maSach.isEmpty() || maHS == null || maHS.isEmpty()) {
            request.setAttribute("errorMessage", "Vui lòng điền đầy đủ thông tin bắt buộc.");
            showBorrowForm(request, response, maSach);
            return;
        }

        try {
            String ngayMuonStr = request.getParameter("ngayMuon");
            String ngayTraStr = request.getParameter("ngayTra");

            if (ngayMuonStr == null || ngayTraStr == null || ngayMuonStr.isEmpty() || ngayTraStr.isEmpty()) {
                request.setAttribute("errorMessage", "Vui lòng chọn ngày mượn và ngày trả.");
                showBorrowForm(request, response, maSach);
                return;
            }

            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

            Date ngayMuon = null;
            Date ngayTra = null;
            try {
                ngayMuon = new Date(sdf.parse(ngayMuonStr).getTime());
                ngayTra = new Date(sdf.parse(ngayTraStr).getTime());
            } catch (ParseException e) {
                request.setAttribute("errorMessage", "Định dạng ngày không hợp lệ. Vui lòng sử dụng định dạng dd/MM/yyyy.");
                showBorrowForm(request, response, maSach);
                return;
            }

            if (ngayTra.before(ngayMuon)) {
                request.setAttribute("errorMessage", "Ngày trả phải sau ngày mượn.");
                showBorrowForm(request, response, maSach);
                return;
            }

            boolean result = borrowService.borrowBook(maMuon, maSach, maHS, ngayMuon, ngayTra);
            if (result) {
                response.sendRedirect("borrowed-books");
            } else {
                request.setAttribute("errorMessage", "Có lỗi xảy ra trong quá trình mượn sách. Vui lòng thử lại.");
                showBorrowForm(request, response, maSach);
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Có lỗi xảy ra: " + e.getMessage());
            showBorrowForm(request, response, maSach);
        }
    }

    private void showBorrowForm(HttpServletRequest request, HttpServletResponse response, String maSach)
            throws ServletException, IOException {
        // Lấy sách cần mượn từ dịch vụ
        Book book = bookService.getBookById(maSach);
        if (book == null) {
            response.sendRedirect("error.jsp");
            return;
        }

        List<Student> students = studentService.getAllStudents();

        request.setAttribute("book", book);
        request.setAttribute("students", students);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/borrowForm.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String maSach = request.getParameter("maSach");

        if (maSach == null || maSach.isEmpty()) {
            response.sendRedirect("error.jsp");
            return;
        }

        showBorrowForm(request, response, maSach);
    }
}