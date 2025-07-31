package com.axyl.student_management.controller;

import com.axyl.student_management.dto.ApiResponse;
import com.axyl.student_management.entity.Subject;
import com.axyl.student_management.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/subjects")
@CrossOrigin(origins = "*")
public class SubjectController {
    @Autowired
    private SubjectService subjectService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<Subject>>> getAllSubjects() {
        try {
            List<Subject> subjects = subjectService.getAllSubjects();
            return ResponseEntity.ok(ApiResponse.success("Subjects retrieved successfully", subjects));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body(ApiResponse.error("Failed to retrieve subjects: " + e.getMessage()));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Subject>> getSubjectById(@PathVariable String id) {
        try {
            return subjectService.getSubjectById(id)
                    .map(subject -> ResponseEntity.ok(ApiResponse.success(subject)))
                    .orElse(ResponseEntity.notFound().build());
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body(ApiResponse.error("Failed to retrieve subject: " + e.getMessage()));
        }
    }

    @GetMapping("/name/{subjectName}")
    public ResponseEntity<ApiResponse<Subject>> getSubjectBySubjectName(@PathVariable String subjectName) {
        try {
            return subjectService.getSubjectBySubjectName(subjectName)
                    .map(subject -> ResponseEntity.ok(ApiResponse.success(subject)))
                    .orElse(ResponseEntity.notFound().build());
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body(ApiResponse.error("Failed to retrieve subject: " + e.getMessage()));
        }
    }

    @GetMapping("/department/{department}")
    public ResponseEntity<ApiResponse<List<Subject>>> getSubjectsByDepartment(@PathVariable String department) {
        try {
            List<Subject> subjects = subjectService.getSubjectsByDepartment(department);
            return ResponseEntity.ok(ApiResponse.success("Subjects retrieved successfully", subjects));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body(ApiResponse.error("Failed to retrieve subjects: " + e.getMessage()));
        }
    }

    @GetMapping("/search")
    public ResponseEntity<ApiResponse<List<Subject>>> searchSubjects(@RequestParam String keyword) {
        try {
            List<Subject> subjects = subjectService.searchSubjects(keyword);
            return ResponseEntity.ok(ApiResponse.success("Subjects found successfully", subjects));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body(ApiResponse.error("Failed to search subjects: " + e.getMessage()));
        }
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Subject>> createSubject(@RequestBody Subject subject) {
        try {
            Subject createdSubject = subjectService.createSubject(subject);
            return ResponseEntity.ok(ApiResponse.success("Subject created successfully", createdSubject));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body(ApiResponse.error("Failed to create subject: " + e.getMessage()));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Subject>> updateSubject(@PathVariable String id,
            @RequestBody Subject subjectDetails) {
        try {
            Subject updatedSubject = subjectService.updateSubject(id, subjectDetails);
            return ResponseEntity.ok(ApiResponse.success("Subject updated successfully", updatedSubject));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body(ApiResponse.error("Failed to update subject: " + e.getMessage()));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> deleteSubject(@PathVariable String id) {
        try {
            subjectService.deleteSubject(id);
            return ResponseEntity.ok(
                    ApiResponse.success("Subject deleted successfully", "Subject with ID " + id + " has been deleted"));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body(ApiResponse.error("Failed to delete subject: " + e.getMessage()));
        }
    }
}
