package com.codegym.demo.service;

import com.codegym.demo.model.Book;
import com.codegym.demo.repository.BookRepository;

import java.util.*;

import java.util.*;

public class BookService {

    private BookRepository bookRepository = new BookRepository();

    public List<Book> getAllBooks() {
        return bookRepository.getAllBooks();
    }

    public Book getBookById(String maSach) {
        return bookRepository.getBookById(maSach);
    }

    public boolean updateBookQuantity(String maSach, int soLuong) {
        return bookRepository.updateBookQuantity(maSach, soLuong);
    }
}

