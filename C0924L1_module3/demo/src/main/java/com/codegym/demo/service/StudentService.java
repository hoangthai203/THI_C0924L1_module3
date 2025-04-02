package com.codegym.demo.service;

import com.codegym.demo.model.Student;

import java.util.List;
import java.util.ArrayList;

public class StudentService {
    private List<Student> students = new ArrayList<>();

    public List<Student> getAllStudents() {
        return students;
    }

    public void addStudent(Student student) {
        students.add(student);
    }
}

