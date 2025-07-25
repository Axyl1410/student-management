package com.axyl.student_management.service;

import com.axyl.student_management.entity.Enrollment;
import com.axyl.student_management.repository.EnrollmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnrollmentService {
    @Autowired
    private EnrollmentRepository enrollmentRepository;

    public List<Enrollment> GetAllEnrollments() {
        return enrollmentRepository.findAll();
    }
}
