package com.axyl.student_management.repository;

import com.axyl.student_management.entity.Class;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClassRepository extends MongoRepository<Class, String> {
    Optional<Class> findByClassName(String className);
    List<Class> findByTeacherId(String teacherId);
    List<Class> findByStatus(String status);
    List<Class> findByClassNameContainingIgnoreCaseOrDescriptionContainingIgnoreCase(
        String className, String description);
    boolean existsByClassName(String className);
}
