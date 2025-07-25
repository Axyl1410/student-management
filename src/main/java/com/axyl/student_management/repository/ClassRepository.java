package com.axyl.student_management.repository;

import com.axyl.student_management.entity.Class;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClassRepository extends MongoRepository<Class, String> {
}
