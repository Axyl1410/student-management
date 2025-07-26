package com.axyl.student_management.controller;

import com.axyl.student_management.dto.ApiResponse;
import com.axyl.student_management.entity.Teacher;
import com.axyl.student_management.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/teachers")
@CrossOrigin(origins = "*")
public class TeacherController {
    @Autowired
    private TeacherService teacherService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<Teacher>>> getAllTeachers() {
        try {
            List<Teacher> teachers = teacherService.getAllTeachers();
            return ResponseEntity.ok(ApiResponse.success("Teachers retrieved successfully", teachers));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                .body(ApiResponse.error("Failed to retrieve teachers: " + e.getMessage()));
        }
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Teacher>> getTeacherById(@PathVariable String id) {
        try {
            return teacherService.getTeacherById(id)
                .map(teacher -> ResponseEntity.ok(ApiResponse.success(teacher)))
                .orElse(ResponseEntity.notFound().build());
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                .body(ApiResponse.error("Failed to retrieve teacher: " + e.getMessage()));
        }
    }
    
    @GetMapping("/teacher-id/{teacherId}")
    public ResponseEntity<ApiResponse<Teacher>> getTeacherByTeacherId(@PathVariable String teacherId) {
        try {
            return teacherService.getTeacherByTeacherId(teacherId)
                .map(teacher -> ResponseEntity.ok(ApiResponse.success(teacher)))
                .orElse(ResponseEntity.notFound().build());
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                .body(ApiResponse.error("Failed to retrieve teacher: " + e.getMessage()));
        }
    }
    
    @GetMapping("/email/{email}")
    public ResponseEntity<ApiResponse<Teacher>> getTeacherByEmail(@PathVariable String email) {
        try {
            return teacherService.getTeacherByEmail(email)
                .map(teacher -> ResponseEntity.ok(ApiResponse.success(teacher)))
                .orElse(ResponseEntity.notFound().build());
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                .body(ApiResponse.error("Failed to retrieve teacher: " + e.getMessage()));
        }
    }
    
    @GetMapping("/department/{department}")
    public ResponseEntity<ApiResponse<List<Teacher>>> getTeachersByDepartment(@PathVariable String department) {
        try {
            List<Teacher> teachers = teacherService.getTeachersByDepartment(department);
            return ResponseEntity.ok(ApiResponse.success("Teachers retrieved successfully", teachers));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                .body(ApiResponse.error("Failed to retrieve teachers: " + e.getMessage()));
        }
    }
    
    @GetMapping("/status/{status}")
    public ResponseEntity<ApiResponse<List<Teacher>>> getTeachersByStatus(@PathVariable String status) {
        try {
            List<Teacher> teachers = teacherService.getTeachersByStatus(status);
            return ResponseEntity.ok(ApiResponse.success("Teachers retrieved successfully", teachers));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                .body(ApiResponse.error("Failed to retrieve teachers: " + e.getMessage()));
        }
    }
    
    @GetMapping("/search")
    public ResponseEntity<ApiResponse<List<Teacher>>> searchTeachers(@RequestParam String keyword) {
        try {
            List<Teacher> teachers = teacherService.searchTeachers(keyword);
            return ResponseEntity.ok(ApiResponse.success("Teachers found successfully", teachers));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                .body(ApiResponse.error("Failed to search teachers: " + e.getMessage()));
        }
    }
    
    @PostMapping
    public ResponseEntity<ApiResponse<Teacher>> createTeacher(@RequestBody Teacher teacher) {
        try {
            Teacher createdTeacher = teacherService.createTeacher(teacher);
            return ResponseEntity.ok(ApiResponse.success("Teacher created successfully", createdTeacher));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                .body(ApiResponse.error("Failed to create teacher: " + e.getMessage()));
        }
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Teacher>> updateTeacher(@PathVariable String id, @RequestBody Teacher teacherDetails) {
        try {
            Teacher updatedTeacher = teacherService.updateTeacher(id, teacherDetails);
            return ResponseEntity.ok(ApiResponse.success("Teacher updated successfully", updatedTeacher));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                .body(ApiResponse.error("Failed to update teacher: " + e.getMessage()));
        }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> deleteTeacher(@PathVariable String id) {
        try {
            teacherService.deleteTeacher(id);
            return ResponseEntity.ok(ApiResponse.success("Teacher deleted successfully", "Teacher with ID " + id + " has been deleted"));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                .body(ApiResponse.error("Failed to delete teacher: " + e.getMessage()));
        }
    }
}
