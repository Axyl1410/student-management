package com.axyl.student_management.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "enrollments")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Enrollment {
    @Id
    private String id;
    private String studentId;
    private String subjectId;
    private String teacherId;
    private String semester;
    private Double score;
    private String grade;
    private Date enrollmentDate;
    private Date createdAt;
    private Date updatedAt;
}
