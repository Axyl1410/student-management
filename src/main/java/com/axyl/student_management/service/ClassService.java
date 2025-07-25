package com.axyl.student_management.service;

import com.axyl.student_management.entity.Class;
import com.axyl.student_management.repository.ClassRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClassService {
    @Autowired
    private ClassRepository classRepository;

    public List<Class> GetAllClasses() {
        return classRepository.findAll();
    }
}
