package com.axyl.student_management.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "subjects")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Subject {
    @Id
    private String id;
    private String subjectName;
    private Integer credits;
    private String department;
    private Date createdAt;
    private Date updatedAt;

}
