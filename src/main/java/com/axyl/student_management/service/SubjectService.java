package com.axyl.student_management.service;

import com.axyl.student_management.entity.Subject;
import com.axyl.student_management.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubjectService {
    @Autowired
    private SubjectRepository subjectRepository;

    public List<Subject> GetAllSubjects() {
        return subjectRepository.findAll();
    }
}
