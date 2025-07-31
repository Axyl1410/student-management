package com.axyl.student_management.repository;

import com.axyl.student_management.entity.Teacher;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TeacherRepository extends MongoRepository<Teacher, String> {
    Optional<Teacher> findByTeacherId(String teacherId);
    Optional<Teacher> findByEmail(String email);
    List<Teacher> findByDepartment(String department);
    List<Teacher> findByStatus(String status);
    List<Teacher> findByFullNameContainingIgnoreCaseOrEmailContainingIgnoreCaseOrTeacherIdContainingIgnoreCase(
        String fullName, String email, String teacherId);
    boolean existsByTeacherId(String teacherId);
    boolean existsByEmail(String email);
}
