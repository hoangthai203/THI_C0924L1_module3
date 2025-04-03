package com.codegym.demo.service;

import com.codegym.demo.model.BorrowRecord;
import java.sql.Date;
import java.util.List;

public interface IBorrowService {
    boolean borrowBook(String maMuon, String maSach, String maHS, Date ngayMuon, Date ngayTra);
    boolean returnBook(String maMuon);
    List<BorrowRecord> getAllBorrowRecords();
}