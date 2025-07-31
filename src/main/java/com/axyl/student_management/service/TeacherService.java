package com.axyl.student_management.service;

import com.axyl.student_management.entity.Teacher;
import com.axyl.student_management.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class TeacherService {
    @Autowired
    private TeacherRepository teacherRepository;

    public List<Teacher> getAllTeachers() {
        return teacherRepository.findAll();
    }
    
    public Optional<Teacher> getTeacherById(String id) {
        return teacherRepository.findById(id);
    }
    
    public Optional<Teacher> getTeacherByTeacherId(String teacherId) {
        return teacherRepository.findByTeacherId(teacherId);
    }
    
    public Optional<Teacher> getTeacherByEmail(String email) {
        return teacherRepository.findByEmail(email);
    }
    
    public List<Teacher> getTeachersByDepartment(String department) {
        return teacherRepository.findByDepartment(department);
    }
    
    public List<Teacher> getTeachersByStatus(String status) {
        return teacherRepository.findByStatus(status);
    }
    
    public Teacher createTeacher(Teacher teacher) {
        // Validate required fields
        if (teacher.getFullName() == null || teacher.getFullName().trim().isEmpty()) {
            throw new RuntimeException("Full name is required");
        }
        if (teacher.getEmail() == null || teacher.getEmail().trim().isEmpty()) {
            throw new RuntimeException("Email is required");
        }
        if (teacher.getTeacherId() == null || teacher.getTeacherId().trim().isEmpty()) {
            throw new RuntimeException("Teacher ID is required");
        }
        
        // Check if teacher ID already exists
        if (teacherRepository.findByTeacherId(teacher.getTeacherId()).isPresent()) {
            throw new RuntimeException("Teacher ID already exists");
        }
        
        // Check if email already exists
        if (teacherRepository.findByEmail(teacher.getEmail()).isPresent()) {
            throw new RuntimeException("Email already exists");
        }
        
        // Set default values
        if (teacher.getStatus() == null || teacher.getStatus().trim().isEmpty()) {
            teacher.setStatus("ACTIVE");
        }
        teacher.setCreatedAt(new Date());
        teacher.setUpdatedAt(new Date());
        
        return teacherRepository.save(teacher);
    }
    
    public Teacher updateTeacher(String id, Teacher teacherDetails) {
        Teacher teacher = teacherRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Teacher not found"));
        
        // Update fields
        if (teacherDetails.getFullName() != null && !teacherDetails.getFullName().trim().isEmpty()) {
            teacher.setFullName(teacherDetails.getFullName());
        }
        if (teacherDetails.getDateOfBirth() != null) {
            teacher.setDateOfBirth(teacherDetails.getDateOfBirth());
        }
        if (teacherDetails.getGender() != null) {
            teacher.setGender(teacherDetails.getGender());
        }
        if (teacherDetails.getEmail() != null && !teacherDetails.getEmail().trim().isEmpty()) {
            // Check if email is already taken by another teacher
            Optional<Teacher> existingTeacher = teacherRepository.findByEmail(teacherDetails.getEmail());
            if (existingTeacher.isPresent() && !existingTeacher.get().getId().equals(id)) {
                throw new RuntimeException("Email already exists");
            }
            teacher.setEmail(teacherDetails.getEmail());
        }
        if (teacherDetails.getPhoneNumber() != null) {
            teacher.setPhoneNumber(teacherDetails.getPhoneNumber());
        }
        if (teacherDetails.getAddress() != null) {
            teacher.setAddress(teacherDetails.getAddress());
        }
        if (teacherDetails.getDepartment() != null) {
            teacher.setDepartment(teacherDetails.getDepartment());
        }
        if (teacherDetails.getStatus() != null) {
            teacher.setStatus(teacherDetails.getStatus());
        }
        
        teacher.setUpdatedAt(new Date());
        return teacherRepository.save(teacher);
    }
    
    public void deleteTeacher(String id) {
        if (!teacherRepository.existsById(id)) {
            throw new RuntimeException("Teacher not found");
        }
        teacherRepository.deleteById(id);
    }
    
    public List<Teacher> searchTeachers(String keyword) {
        return teacherRepository.findByFullNameContainingIgnoreCaseOrEmailContainingIgnoreCaseOrTeacherIdContainingIgnoreCase(
            keyword, keyword, keyword);
    }
}
