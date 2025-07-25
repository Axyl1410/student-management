package com.axyl.student_management.repository;

import com.axyl.student_management.entity.Subject;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SubjectRepository extends MongoRepository<Subject, String> {
}
