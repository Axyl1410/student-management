package com.axyl.student_management.service;

import com.axyl.student_management.entity.Class;
import com.axyl.student_management.repository.ClassRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ClassService {
    @Autowired
    private ClassRepository classRepository;

    public List<Class> getAllClasses() {
        return classRepository.findAll();
    }
    
    public Optional<Class> getClassById(String id) {
        return classRepository.findById(id);
    }
    
    public Optional<Class> getClassByClassName(String className) {
        return classRepository.findByClassName(className);
    }
    
    public List<Class> getClassesByTeacherId(String teacherId) {
        return classRepository.findByTeacherId(teacherId);
    }
    
    public List<Class> getClassesByStatus(String status) {
        return classRepository.findByStatus(status);
    }
    
    public Class createClass(Class classEntity) {
        // Validate required fields
        if (classEntity.getClassName() == null || classEntity.getClassName().trim().isEmpty()) {
            throw new RuntimeException("Class name is required");
        }
        
        // Check if class name already exists
        if (classRepository.findByClassName(classEntity.getClassName()).isPresent()) {
            throw new RuntimeException("Class name already exists");
        }
        
        // Set default values
        if (classEntity.getStatus() == null || classEntity.getStatus().trim().isEmpty()) {
            classEntity.setStatus("ACTIVE");
        }
        classEntity.setCreatedAt(new Date());
        classEntity.setUpdatedAt(new Date());
        
        return classRepository.save(classEntity);
    }
    
    public Class updateClass(String id, Class classDetails) {
        Class classEntity = classRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Class not found"));
        
        // Update fields
        if (classDetails.getClassName() != null && !classDetails.getClassName().trim().isEmpty()) {
            // Check if class name is already taken by another class
            Optional<Class> existingClass = classRepository.findByClassName(classDetails.getClassName());
            if (existingClass.isPresent() && !existingClass.get().getId().equals(id)) {
                throw new RuntimeException("Class name already exists");
            }
            classEntity.setClassName(classDetails.getClassName());
        }
        if (classDetails.getDescription() != null) {
            classEntity.setDescription(classDetails.getDescription());
        }
        if (classDetails.getTeacherId() != null) {
            classEntity.setTeacherId(classDetails.getTeacherId());
        }
        if (classDetails.getStatus() != null) {
            classEntity.setStatus(classDetails.getStatus());
        }
        
        classEntity.setUpdatedAt(new Date());
        return classRepository.save(classEntity);
    }
    
    public void deleteClass(String id) {
        if (!classRepository.existsById(id)) {
            throw new RuntimeException("Class not found");
        }
        classRepository.deleteById(id);
    }
    
    public List<Class> searchClasses(String keyword) {
        return classRepository.findByClassNameContainingIgnoreCaseOrDescriptionContainingIgnoreCase(
            keyword, keyword);
    }
}
