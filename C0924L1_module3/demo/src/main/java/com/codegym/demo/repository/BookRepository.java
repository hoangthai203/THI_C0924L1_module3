package com.codegym.demo.repository;
import com.codegym.demo.model.Book;
import com.codegym.demo.util.BaseRepository;

import java.sql.*;
import java.util.*;

import java.sql.*;
import java.util.*;

public class BookRepository {

    public List<Book> getAllBooks() {
        List<Book> books = new ArrayList<>();
        String sql = "SELECT * FROM sach";

        try (Connection connection = BaseRepository.getConnectDB();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            while (resultSet.next()) {
                Book book = new Book(
                        resultSet.getString("ma_sach"),
                        resultSet.getString("ten_sach"),
                        resultSet.getString("tac_gia"),
                        resultSet.getString("mo_ta"),
                        resultSet.getInt("so_luong")
                );
                books.add(book);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return books;
    }

    public Book getBookById(String maSach) {
        String sql = "SELECT * FROM sach WHERE ma_sach = ?";
        try (Connection connection = BaseRepository.getConnectDB();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, maSach);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return new Book(
                        resultSet.getString("ma_sach"),
                        resultSet.getString("ten_sach"),
                        resultSet.getString("tac_gia"),
                        resultSet.getString("mo_ta"),
                        resultSet.getInt("so_luong")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean updateBookQuantity(String maSach, int soLuong) {
        String sql = "UPDATE sach SET so_luong = ? WHERE ma_sach = ?";
        try (Connection connection = BaseRepository.getConnectDB();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, soLuong);
            statement.setString(2, maSach);
            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}

