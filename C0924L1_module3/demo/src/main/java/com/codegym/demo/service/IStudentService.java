package com.codegym.demo.service;

import com.codegym.demo.model.Student;
import java.util.List;

public interface IStudentService {
    List<Student> getAllStudents();
    Student getStudentById(String maHS);
}
