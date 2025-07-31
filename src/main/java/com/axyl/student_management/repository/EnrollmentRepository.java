package com.axyl.student_management.repository;

import com.axyl.student_management.entity.Enrollment;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EnrollmentRepository extends MongoRepository<Enrollment, String> {
    List<Enrollment> findByStudentId(String studentId);

    List<Enrollment> findBySubjectId(String subjectId);

    List<Enrollment> findByTeacherId(String teacherId);

    List<Enrollment> findBySemester(String semester);
}
