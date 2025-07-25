package com.axyl.student_management.controller;

import com.axyl.student_management.entity.Subject;
import com.axyl.student_management.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/subject")
class SubjectController {
    @Autowired
    private SubjectService subjectService;

    @GetMapping
    public ResponseEntity<List<Subject>> GetAllSubjects() {
        return ResponseEntity.ok(subjectService.GetAllSubjects());
    }
}
