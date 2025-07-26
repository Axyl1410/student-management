package com.axyl.student_management.controller;

import com.axyl.student_management.dto.ApiResponse;
import com.axyl.student_management.entity.Enrollment;
import com.axyl.student_management.service.EnrollmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/enrollments")
@CrossOrigin(origins = "*")
public class EnrollmentController {
    @Autowired
    private EnrollmentService enrollmentService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<Enrollment>>> getAllEnrollments() {
        try {
            List<Enrollment> enrollments = enrollmentService.getAllEnrollments();
            return ResponseEntity.ok(ApiResponse.success("Enrollments retrieved successfully", enrollments));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body(ApiResponse.error("Failed to retrieve enrollments: " + e.getMessage()));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Enrollment>> getEnrollmentById(@PathVariable String id) {
        try {
            return enrollmentService.getEnrollmentById(id)
                    .map(enrollment -> ResponseEntity.ok(ApiResponse.success(enrollment)))
                    .orElse(ResponseEntity.notFound().build());
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body(ApiResponse.error("Failed to retrieve enrollment: " + e.getMessage()));
        }
    }

    @GetMapping("/student/{studentId}")
    public ResponseEntity<ApiResponse<List<Enrollment>>> getEnrollmentsByStudentId(@PathVariable String studentId) {
        try {
            List<Enrollment> enrollments = enrollmentService.getEnrollmentsByStudentId(studentId);
            return ResponseEntity.ok(ApiResponse.success("Enrollments retrieved successfully", enrollments));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body(ApiResponse.error("Failed to retrieve enrollments: " + e.getMessage()));
        }
    }

    @GetMapping("/subject/{subjectId}")
    public ResponseEntity<ApiResponse<List<Enrollment>>> getEnrollmentsBySubjectId(@PathVariable String subjectId) {
        try {
            List<Enrollment> enrollments = enrollmentService.getEnrollmentsBySubjectId(subjectId);
            return ResponseEntity.ok(ApiResponse.success("Enrollments retrieved successfully", enrollments));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body(ApiResponse.error("Failed to retrieve enrollments: " + e.getMessage()));
        }
    }

    @GetMapping("/teacher/{teacherId}")
    public ResponseEntity<ApiResponse<List<Enrollment>>> getEnrollmentsByTeacherId(@PathVariable String teacherId) {
        try {
            List<Enrollment> enrollments = enrollmentService.getEnrollmentsByTeacherId(teacherId);
            return ResponseEntity.ok(ApiResponse.success("Enrollments retrieved successfully", enrollments));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body(ApiResponse.error("Failed to retrieve enrollments: " + e.getMessage()));
        }
    }

    @GetMapping("/semester/{semester}")
    public ResponseEntity<ApiResponse<List<Enrollment>>> getEnrollmentsBySemester(@PathVariable String semester) {
        try {
            List<Enrollment> enrollments = enrollmentService.getEnrollmentsBySemester(semester);
            return ResponseEntity.ok(ApiResponse.success("Enrollments retrieved successfully", enrollments));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body(ApiResponse.error("Failed to retrieve enrollments: " + e.getMessage()));
        }
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Enrollment>> createEnrollment(@RequestBody Enrollment enrollment) {
        try {
            Enrollment createdEnrollment = enrollmentService.createEnrollment(enrollment);
            return ResponseEntity.ok(ApiResponse.success("Enrollment created successfully", createdEnrollment));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body(ApiResponse.error("Failed to create enrollment: " + e.getMessage()));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Enrollment>> updateEnrollment(@PathVariable String id,
            @RequestBody Enrollment enrollmentDetails) {
        try {
            Enrollment updatedEnrollment = enrollmentService.updateEnrollment(id, enrollmentDetails);
            return ResponseEntity.ok(ApiResponse.success("Enrollment updated successfully", updatedEnrollment));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body(ApiResponse.error("Failed to update enrollment: " + e.getMessage()));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> deleteEnrollment(@PathVariable String id) {
        try {
            enrollmentService.deleteEnrollment(id);
            return ResponseEntity.ok(ApiResponse.success("Enrollment deleted successfully",
                    "Enrollment with ID " + id + " has been deleted"));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body(ApiResponse.error("Failed to delete enrollment: " + e.getMessage()));
        }
    }
}
