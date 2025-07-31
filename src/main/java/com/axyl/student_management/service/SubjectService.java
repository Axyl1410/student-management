package com.axyl.student_management.service;

import com.axyl.student_management.entity.Subject;
import com.axyl.student_management.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class SubjectService {
    @Autowired
    private SubjectRepository subjectRepository;

    public List<Subject> getAllSubjects() {
        return subjectRepository.findAll();
    }

    public Optional<Subject> getSubjectById(String id) {
        return subjectRepository.findById(id);
    }

    public Optional<Subject> getSubjectBySubjectName(String subjectName) {
        return subjectRepository.findBySubjectName(subjectName);
    }

    public List<Subject> getSubjectsByDepartment(String department) {
        return subjectRepository.findByDepartment(department);
    }

    public Subject createSubject(Subject subject) {
        // Validate required fields
        if (subject.getSubjectName() == null || subject.getSubjectName().trim().isEmpty()) {
            throw new RuntimeException("Subject name is required");
        }

        // Check if subject name already exists
        if (subjectRepository.findBySubjectName(subject.getSubjectName()).isPresent()) {
            throw new RuntimeException("Subject name already exists");
        }

        // Set default values
        if (subject.getCredits() == null) {
            subject.setCredits(3); // Default credits
        }
        subject.setCreatedAt(new Date());
        subject.setUpdatedAt(new Date());

        return subjectRepository.save(subject);
    }

    public Subject updateSubject(String id, Subject subjectDetails) {
        Subject subject = subjectRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Subject not found"));

        // Update fields
        if (subjectDetails.getSubjectName() != null && !subjectDetails.getSubjectName().trim().isEmpty()) {
            // Check if subject name is already taken by another subject
            Optional<Subject> existingSubject = subjectRepository.findBySubjectName(subjectDetails.getSubjectName());
            if (existingSubject.isPresent() && !existingSubject.get().getId().equals(id)) {
                throw new RuntimeException("Subject name already exists");
            }
            subject.setSubjectName(subjectDetails.getSubjectName());
        }
        if (subjectDetails.getCredits() != null) {
            subject.setCredits(subjectDetails.getCredits());
        }
        if (subjectDetails.getDepartment() != null) {
            subject.setDepartment(subjectDetails.getDepartment());
        }

        subject.setUpdatedAt(new Date());
        return subjectRepository.save(subject);
    }

    public void deleteSubject(String id) {
        if (!subjectRepository.existsById(id)) {
            throw new RuntimeException("Subject not found");
        }
        subjectRepository.deleteById(id);
    }

    public List<Subject> searchSubjects(String keyword) {
        return subjectRepository.findBySubjectNameContainingIgnoreCaseOrDepartmentContainingIgnoreCase(
                keyword, keyword);
    }
}
