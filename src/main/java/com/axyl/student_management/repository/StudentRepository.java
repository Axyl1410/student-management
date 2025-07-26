package com.axyl.student_management.repository;

import com.axyl.student_management.entity.Student;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends MongoRepository<Student, String> {
    Optional<Student> findByStudentId(String studentId);
    Optional<Student> findByEmail(String email);
    List<Student> findByClassId(String classId);
    List<Student> findByStatus(String status);
    List<Student> findByFullNameContainingIgnoreCaseOrEmailContainingIgnoreCaseOrStudentIdContainingIgnoreCase(
        String fullName, String email, String studentId);
    boolean existsByStudentId(String studentId);
    boolean existsByEmail(String email);
}
