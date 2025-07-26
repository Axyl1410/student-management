package com.axyl.student_management.repository;

import com.axyl.student_management.entity.Subject;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SubjectRepository extends MongoRepository<Subject, String> {
    Optional<Subject> findBySubjectName(String subjectName);

    List<Subject> findByDepartment(String department);

    List<Subject> findBySubjectNameContainingIgnoreCaseOrDepartmentContainingIgnoreCase(
            String subjectName, String department);

    boolean existsBySubjectName(String subjectName);
}
