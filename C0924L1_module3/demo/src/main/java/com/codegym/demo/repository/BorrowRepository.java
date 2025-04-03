package com.codegym.demo.repository;

import com.codegym.demo.model.BorrowRecord;
import com.codegym.demo.util.BaseRepository;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BorrowRepository implements IBorrowRepository{

    public String getBookIdByBorrowRecord(String maMuon) {
        String maSach = null;
        String query = "SELECT ma_sach FROM the_muon WHERE ma_muon = ?";
        try (Connection connection = BaseRepository.getConnectDB();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, maMuon);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    maSach = resultSet.getString("ma_sach");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return maSach;
    }

    public boolean borrowBook(String maMuon, String maSach, String maHS, Date ngayMuon, Date ngayTra) {
        String sql = "INSERT INTO the_muon (ma_muon, ma_sach, ma_hs, ngay_muon, ngay_tra, trang_thai) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection connection = BaseRepository.getConnectDB();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, maMuon);
            statement.setString(2, maSach);
            statement.setString(3, maHS);
            statement.setDate(4, new java.sql.Date(ngayMuon.getTime()));
            statement.setDate(5, new java.sql.Date(ngayTra.getTime()));
            statement.setBoolean(6, true); // Trạng thái đang mượn

            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean returnBook(String maMuon) {
        String sql = "UPDATE the_muon SET trang_thai = FALSE WHERE ma_muon = ?";
        try (Connection connection = BaseRepository.getConnectDB();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, maMuon);
            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<BorrowRecord> getAllBorrowRecords() {
        List<BorrowRecord> borrowRecords = new ArrayList<>();
        String sql = "SELECT tm.*, s.ten_sach, hs.ho_ten " +
                "FROM the_muon tm " +
                "JOIN sach s ON tm.ma_sach = s.ma_sach " +
                "JOIN hoc_sinh hs ON tm.ma_hs = hs.ma_hs";

        try (Connection connection = BaseRepository.getConnectDB();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            while (resultSet.next()) {
                BorrowRecord borrowRecord = new BorrowRecord(
                        resultSet.getString("ma_muon"),
                        resultSet.getString("ma_sach"),
                        resultSet.getString("ma_hs"),
                        resultSet.getDate("ngay_muon"),
                        resultSet.getDate("ngay_tra"),
                        resultSet.getBoolean("trang_thai"),
                        resultSet.getString("ten_sach"),
                        resultSet.getString("ho_ten")
                );
                borrowRecords.add(borrowRecord);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return borrowRecords;
    }
}

