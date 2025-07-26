package com.axyl.student_management.service;

import com.axyl.student_management.entity.Student;
import com.axyl.student_management.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }
    
    public Optional<Student> getStudentById(String id) {
        return studentRepository.findById(id);
    }
    
    public Optional<Student> getStudentByStudentId(String studentId) {
        return studentRepository.findByStudentId(studentId);
    }
    
    public Optional<Student> getStudentByEmail(String email) {
        return studentRepository.findByEmail(email);
    }
    
    public List<Student> getStudentsByClassId(String classId) {
        return studentRepository.findByClassId(classId);
    }
    
    public List<Student> getStudentsByStatus(String status) {
        return studentRepository.findByStatus(status);
    }
    
    public Student createStudent(Student student) {
        // Validate required fields
        if (student.getFullName() == null || student.getFullName().trim().isEmpty()) {
            throw new RuntimeException("Full name is required");
        }
        if (student.getEmail() == null || student.getEmail().trim().isEmpty()) {
            throw new RuntimeException("Email is required");
        }
        if (student.getStudentId() == null || student.getStudentId().trim().isEmpty()) {
            throw new RuntimeException("Student ID is required");
        }
        
        // Check if student ID already exists
        if (studentRepository.findByStudentId(student.getStudentId()).isPresent()) {
            throw new RuntimeException("Student ID already exists");
        }
        
        // Check if email already exists
        if (studentRepository.findByEmail(student.getEmail()).isPresent()) {
            throw new RuntimeException("Email already exists");
        }
        
        // Set default values
        if (student.getStatus() == null || student.getStatus().trim().isEmpty()) {
            student.setStatus("ACTIVE");
        }
        if (student.getEnrollmentDate() == null) {
            student.setEnrollmentDate(new Date());
        }
        student.setCreatedAt(new Date());
        student.setUpdatedAt(new Date());
        
        return studentRepository.save(student);
    }
    
    public Student updateStudent(String id, Student studentDetails) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found"));
        
        // Update fields
        if (studentDetails.getFullName() != null && !studentDetails.getFullName().trim().isEmpty()) {
            student.setFullName(studentDetails.getFullName());
        }
        if (studentDetails.getDateOfBirth() != null) {
            student.setDateOfBirth(studentDetails.getDateOfBirth());
        }
        if (studentDetails.getGender() != null) {
            student.setGender(studentDetails.getGender());
        }
        if (studentDetails.getEmail() != null && !studentDetails.getEmail().trim().isEmpty()) {
            // Check if email is already taken by another student
            Optional<Student> existingStudent = studentRepository.findByEmail(studentDetails.getEmail());
            if (existingStudent.isPresent() && !existingStudent.get().getId().equals(id)) {
                throw new RuntimeException("Email already exists");
            }
            student.setEmail(studentDetails.getEmail());
        }
        if (studentDetails.getPhoneNumber() != null) {
            student.setPhoneNumber(studentDetails.getPhoneNumber());
        }
        if (studentDetails.getAddress() != null) {
            student.setAddress(studentDetails.getAddress());
        }
        if (studentDetails.getClassId() != null) {
            student.setClassId(studentDetails.getClassId());
        }
        if (studentDetails.getStatus() != null) {
            student.setStatus(studentDetails.getStatus());
        }
        
        student.setUpdatedAt(new Date());
        return studentRepository.save(student);
    }
    
    public void deleteStudent(String id) {
        if (!studentRepository.existsById(id)) {
            throw new RuntimeException("Student not found");
        }
        studentRepository.deleteById(id);
    }
    
    public List<Student> searchStudents(String keyword) {
        return studentRepository.findByFullNameContainingIgnoreCaseOrEmailContainingIgnoreCaseOrStudentIdContainingIgnoreCase(
            keyword, keyword, keyword);
    }
}
