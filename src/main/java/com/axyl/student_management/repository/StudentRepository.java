package com.axyl.student_management.repository;

import com.axyl.student_management.entity.Student;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface StudentRepository extends MongoRepository<Student, String> {
}
