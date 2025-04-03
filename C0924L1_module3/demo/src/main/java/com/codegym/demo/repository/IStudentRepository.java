package com.codegym.demo.repository;

import com.codegym.demo.model.Student;
import java.util.List;

public interface IStudentRepository {
    List<Student> getAllStudents();

    Student getStudentById(String maHS);
}
