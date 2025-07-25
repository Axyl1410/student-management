package com.axyl.student_management.service;

import com.axyl.student_management.entity.Teacher;
import com.axyl.student_management.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherService {
    @Autowired
    private TeacherRepository teacherRepository;

    public List<Teacher> GetAllTeachers() {
        return teacherRepository.findAll();
    }
}
