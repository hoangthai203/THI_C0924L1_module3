package com.codegym.demo.repository;

import com.codegym.demo.model.Book;
import java.util.List;

public interface IBookRepository {
    List<Book> getAllBooks();
    Book getBookById(String maSach);
    boolean updateBookQuantity(String maSach, int soLuong);
}