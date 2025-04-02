package com.codegym.demo.service;

import com.codegym.demo.model.Book;
import com.codegym.demo.repository.BorrowRepository;

import java.sql.Date;

public class BorrowService {

    private BorrowRepository borrowRepository = new BorrowRepository();
    private BookService bookService = new BookService();

    public boolean borrowBook(String maMuon, String maSach, String maHS, Date ngayMuon, Date ngayTra) {
        if (ngayTra.before(ngayMuon)) {
            return false;
        }

        Book book = bookService.getBookById(maSach);
        if (book != null && book.getSoLuong() > 0) {
            boolean result = borrowRepository.borrowBook(maMuon, maSach, maHS, ngayMuon, ngayTra);
            if (result) {
                bookService.updateBookQuantity(maSach, book.getSoLuong() - 1);  // Giảm số lượng sách
            }
            return result;
        }
        return false;
    }

    public boolean returnBook(String maMuon) {
        String maSach = getBookIdByBorrowRecord(maMuon);
        if (maSach == null || maSach.isEmpty()) {
            return false;
        }

        boolean result = borrowRepository.returnBook(maMuon);
        if (result) {

            Book book = bookService.getBookById(maSach);
            if (book != null) {
                bookService.updateBookQuantity(maSach, book.getSoLuong() + 1);  // Tăng số lượng sách
            }
        }
        return result;
    }

    private String getBookIdByBorrowRecord(String maMuon) {
        return borrowRepository.getBookIdByBorrowRecord(maMuon);
    }
}
