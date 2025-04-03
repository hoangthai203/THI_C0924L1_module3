package com.codegym.demo.repository;

import com.codegym.demo.model.BorrowRecord;

import java.sql.Date;
import java.util.List;

public interface IBorrowRepository {
    String getBookIdByBorrowRecord(String maMuon);

    boolean borrowBook(String maMuon, String maSach, String maHS, Date ngayMuon, Date ngayTra);

    boolean returnBook(String maMuon);

    List<BorrowRecord> getAllBorrowRecords();
}
