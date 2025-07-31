package com.axyl.student_management.controller;

import com.axyl.student_management.dto.ApiResponse;
import com.axyl.student_management.entity.Class;
import com.axyl.student_management.service.ClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/classes")
@CrossOrigin(origins = "*")
public class ClassController {
    @Autowired
    private ClassService classService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<Class>>> getAllClasses() {
        try {
            List<Class> classes = classService.getAllClasses();
            return ResponseEntity.ok(ApiResponse.success("Classes retrieved successfully", classes));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body(ApiResponse.error("Failed to retrieve classes: " + e.getMessage()));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Class>> getClassById(@PathVariable String id) {
        try {
            return classService.getClassById(id)
                    .map(classEntity -> ResponseEntity.ok(ApiResponse.success(classEntity)))
                    .orElse(ResponseEntity.notFound().build());
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body(ApiResponse.error("Failed to retrieve class: " + e.getMessage()));
        }
    }

    @GetMapping("/name/{className}")
    public ResponseEntity<ApiResponse<Class>> getClassByClassName(@PathVariable String className) {
        try {
            return classService.getClassByClassName(className)
                    .map(classEntity -> ResponseEntity.ok(ApiResponse.success(classEntity)))
                    .orElse(ResponseEntity.notFound().build());
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body(ApiResponse.error("Failed to retrieve class: " + e.getMessage()));
        }
    }

    @GetMapping("/teacher/{teacherId}")
    public ResponseEntity<ApiResponse<List<Class>>> getClassesByTeacherId(@PathVariable String teacherId) {
        try {
            List<Class> classes = classService.getClassesByTeacherId(teacherId);
            return ResponseEntity.ok(ApiResponse.success("Classes retrieved successfully", classes));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body(ApiResponse.error("Failed to retrieve classes: " + e.getMessage()));
        }
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<ApiResponse<List<Class>>> getClassesByStatus(@PathVariable String status) {
        try {
            List<Class> classes = classService.getClassesByStatus(status);
            return ResponseEntity.ok(ApiResponse.success("Classes retrieved successfully", classes));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body(ApiResponse.error("Failed to retrieve classes: " + e.getMessage()));
        }
    }

    @GetMapping("/search")
    public ResponseEntity<ApiResponse<List<Class>>> searchClasses(@RequestParam String keyword) {
        try {
            List<Class> classes = classService.searchClasses(keyword);
            return ResponseEntity.ok(ApiResponse.success("Classes found successfully", classes));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body(ApiResponse.error("Failed to search classes: " + e.getMessage()));
        }
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Class>> createClass(@RequestBody Class classEntity) {
        try {
            Class createdClass = classService.createClass(classEntity);
            return ResponseEntity.ok(ApiResponse.success("Class created successfully", createdClass));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body(ApiResponse.error("Failed to create class: " + e.getMessage()));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Class>> updateClass(@PathVariable String id, @RequestBody Class classDetails) {
        try {
            Class updatedClass = classService.updateClass(id, classDetails);
            return ResponseEntity.ok(ApiResponse.success("Class updated successfully", updatedClass));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body(ApiResponse.error("Failed to update class: " + e.getMessage()));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> deleteClass(@PathVariable String id) {
        try {
            classService.deleteClass(id);
            return ResponseEntity
                    .ok(ApiResponse.success("Class deleted successfully", "Class with ID " + id + " has been deleted"));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body(ApiResponse.error("Failed to delete class: " + e.getMessage()));
        }
    }
}
