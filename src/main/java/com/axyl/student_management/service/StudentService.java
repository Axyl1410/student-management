package com.axyl.student_management.service;

import com.axyl.student_management.entity.Student;
import com.axyl.student_management.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;

    public List<Student> GetAllStudents() {
        return studentRepository.findAll();
    }
}
