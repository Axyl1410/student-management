package com.axyl.student_management.service;

import com.axyl.student_management.entity.Enrollment;
import com.axyl.student_management.repository.EnrollmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class EnrollmentService {
    @Autowired
    private EnrollmentRepository enrollmentRepository;

    public List<Enrollment> getAllEnrollments() {
        return enrollmentRepository.findAll();
    }

    public Optional<Enrollment> getEnrollmentById(String id) {
        return enrollmentRepository.findById(id);
    }

    public List<Enrollment> getEnrollmentsByStudentId(String studentId) {
        return enrollmentRepository.findByStudentId(studentId);
    }

    public List<Enrollment> getEnrollmentsBySubjectId(String subjectId) {
        return enrollmentRepository.findBySubjectId(subjectId);
    }

    public List<Enrollment> getEnrollmentsByTeacherId(String teacherId) {
        return enrollmentRepository.findByTeacherId(teacherId);
    }

    public List<Enrollment> getEnrollmentsBySemester(String semester) {
        return enrollmentRepository.findBySemester(semester);
    }

    public Enrollment createEnrollment(Enrollment enrollment) {
        // Validate required fields
        if (enrollment.getStudentId() == null || enrollment.getStudentId().trim().isEmpty()) {
            throw new RuntimeException("Student ID is required");
        }
        if (enrollment.getSubjectId() == null || enrollment.getSubjectId().trim().isEmpty()) {
            throw new RuntimeException("Subject ID is required");
        }
        if (enrollment.getTeacherId() == null || enrollment.getTeacherId().trim().isEmpty()) {
            throw new RuntimeException("Teacher ID is required");
        }

        // Set default values
        if (enrollment.getEnrollmentDate() == null) {
            enrollment.setEnrollmentDate(new Date());
        }
        enrollment.setCreatedAt(new Date());
        enrollment.setUpdatedAt(new Date());

        return enrollmentRepository.save(enrollment);
    }

    public Enrollment updateEnrollment(String id, Enrollment enrollmentDetails) {
        Enrollment enrollment = enrollmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Enrollment not found"));

        // Update fields
        if (enrollmentDetails.getStudentId() != null) {
            enrollment.setStudentId(enrollmentDetails.getStudentId());
        }
        if (enrollmentDetails.getSubjectId() != null) {
            enrollment.setSubjectId(enrollmentDetails.getSubjectId());
        }
        if (enrollmentDetails.getTeacherId() != null) {
            enrollment.setTeacherId(enrollmentDetails.getTeacherId());
        }
        if (enrollmentDetails.getSemester() != null) {
            enrollment.setSemester(enrollmentDetails.getSemester());
        }
        if (enrollmentDetails.getScore() != null) {
            enrollment.setScore(enrollmentDetails.getScore());
        }
        if (enrollmentDetails.getGrade() != null) {
            enrollment.setGrade(enrollmentDetails.getGrade());
        }

        enrollment.setUpdatedAt(new Date());
        return enrollmentRepository.save(enrollment);
    }

    public void deleteEnrollment(String id) {
        if (!enrollmentRepository.existsById(id)) {
            throw new RuntimeException("Enrollment not found");
        }
        enrollmentRepository.deleteById(id);
    }
}
