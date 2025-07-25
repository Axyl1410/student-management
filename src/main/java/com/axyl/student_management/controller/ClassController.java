package com.axyl.student_management.controller;

import com.axyl.student_management.entity.Class;
import com.axyl.student_management.service.ClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/class")
class ClassController {
    @Autowired
    private ClassService classService;

    @GetMapping
    public ResponseEntity<List<Class>> GetAllClasses() {
        return ResponseEntity.ok(classService.GetAllClasses());
    }

}
