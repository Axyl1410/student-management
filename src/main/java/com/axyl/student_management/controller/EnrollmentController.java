package com.axyl.student_management.controller;

import com.axyl.student_management.entity.Enrollment;
import com.axyl.student_management.service.EnrollmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/enrollment")
class EnrollmentController {
    @Autowired
    private EnrollmentService enrollmentService;

    @GetMapping
    public ResponseEntity<List<Enrollment>> GetAllEnrollments() {
        return ResponseEntity.ok(enrollmentService.GetAllEnrollments());
    }
}
