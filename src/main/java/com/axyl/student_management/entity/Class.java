package com.axyl.student_management.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "classes")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Class {
    @Id
    private String id;
    private String className;
    private String description;
    private Integer year;
    private String major;
    private String teacherId;
    private String classMonitorId;
    private String status;
    private Date createdAt;
    private Date updatedAt;
}
