package com.codegym.demo.controller;

import com.codegym.demo.model.BorrowRecord;
import com.codegym.demo.service.BorrowService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class BorrowedBookController extends HttpServlet {
    private BorrowService borrowService = new BorrowService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<BorrowRecord> borrowRecords = borrowService.getAllBorrowRecords();

        request.setAttribute("borrowRecords", borrowRecords);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/borrowed-books.jsp");
        dispatcher.forward(request, response);
    }
}