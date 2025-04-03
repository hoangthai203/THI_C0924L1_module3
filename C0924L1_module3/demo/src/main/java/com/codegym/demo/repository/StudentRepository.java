package com.codegym.demo.repository;

import com.codegym.demo.model.Student;
import com.codegym.demo.util.BaseRepository;

import java.sql.*;
import java.util.*;

public class StudentRepository implements IStudentRepository {

    public List<Student> getAllStudents() {
        List<Student> students = new ArrayList<>();
        String sql = "SELECT * FROM hoc_sinh";

        try (Connection connection = BaseRepository.getConnectDB();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            while (resultSet.next()) {
                Student student = new Student(
                        resultSet.getString("ma_hs"),
                        resultSet.getString("ho_ten"),
                        resultSet.getString("lop")
                );
                students.add(student);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return students;
    }

    public Student getStudentById(String maHS) {
        String sql = "SELECT * FROM hoc_sinh WHERE ma_hs = ?";
        try (Connection connection = BaseRepository.getConnectDB();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, maHS);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return new Student(
                        resultSet.getString("ma_hs"),
                        resultSet.getString("ho_ten"),
                        resultSet.getString("lop")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
