package com.codegym.demo.service;

import com.codegym.demo.model.Student;
import com.codegym.demo.repository.StudentRepository;

import java.util.List;

public class StudentService implements IStudentService {
    private StudentRepository studentRepository = new StudentRepository();

    public List<Student> getAllStudents() {
        return studentRepository.getAllStudents();
    }

    public Student getStudentById(String maHS) {
        return studentRepository.getStudentById(maHS);
    }
}