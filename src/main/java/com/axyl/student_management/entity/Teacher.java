package com.axyl.student_management.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "teachers")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Teacher {
    @Id
    private String id;
    private String fullName;
    private Date dateOfBirth;
    private String gender;
    @Indexed(unique = true)
    private String email;
    private String phoneNumber;
    private String address;
    @Indexed(unique = true)
    private String teacherId;
    private String department;
    private String status;
    private Date createdAt;
    private Date updatedAt;
}
