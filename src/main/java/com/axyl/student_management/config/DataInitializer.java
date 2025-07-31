package com.axyl.student_management.config;

import com.axyl.student_management.entity.User;
import com.axyl.student_management.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private UserService userService;

    @Override
    public void run(String... args) throws Exception {
        // Tạo admin user mặc định nếu chưa có
        if (!userService.getUserByUsername("admin").isPresent()) {
            User adminUser = new User();
            adminUser.setUsername("admin");
            adminUser.setPassword("admin123");
            adminUser.setEmail("admin@studentmanagement.com");
            adminUser.setFullName("System Administrator");
            adminUser.setRole("ADMIN");
            
            Set<String> permissions = new HashSet<>();
            permissions.add("READ");
            permissions.add("WRITE");
            permissions.add("DELETE");
            permissions.add("ADMIN");
            adminUser.setPermissions(permissions);
            
            adminUser.setEnabled(true);
            adminUser.setAccountNonExpired(true);
            adminUser.setCredentialsNonExpired(true);
            adminUser.setAccountNonLocked(true);
            
            try {
                userService.createUser(adminUser);
                System.out.println("Admin user created successfully!");
                System.out.println("Username: admin");
                System.out.println("Password: admin123");
            } catch (Exception e) {
                System.out.println("Admin user already exists or creation failed: " + e.getMessage());
            }
        }
        
        // Tạo teacher user mặc định
        if (!userService.getUserByUsername("teacher").isPresent()) {
            User teacherUser = new User();
            teacherUser.setUsername("teacher");
            teacherUser.setPassword("teacher123");
            teacherUser.setEmail("teacher@studentmanagement.com");
            teacherUser.setFullName("Default Teacher");
            teacherUser.setRole("TEACHER");
            
            Set<String> permissions = new HashSet<>();
            permissions.add("READ");
            permissions.add("WRITE");
            teacherUser.setPermissions(permissions);
            
            teacherUser.setEnabled(true);
            teacherUser.setAccountNonExpired(true);
            teacherUser.setCredentialsNonExpired(true);
            teacherUser.setAccountNonLocked(true);
            
            try {
                userService.createUser(teacherUser);
                System.out.println("Teacher user created successfully!");
                System.out.println("Username: teacher");
                System.out.println("Password: teacher123");
            } catch (Exception e) {
                System.out.println("Teacher user already exists or creation failed: " + e.getMessage());
            }
        }
        
        // Tạo student user mặc định
        if (!userService.getUserByUsername("student").isPresent()) {
            User studentUser = new User();
            studentUser.setUsername("student");
            studentUser.setPassword("student123");
            studentUser.setEmail("student@studentmanagement.com");
            studentUser.setFullName("Default Student");
            studentUser.setRole("STUDENT");
            
            Set<String> permissions = new HashSet<>();
            permissions.add("READ");
            studentUser.setPermissions(permissions);
            
            studentUser.setEnabled(true);
            studentUser.setAccountNonExpired(true);
            studentUser.setCredentialsNonExpired(true);
            studentUser.setAccountNonLocked(true);
            
            try {
                userService.createUser(studentUser);
                System.out.println("Student user created successfully!");
                System.out.println("Username: student");
                System.out.println("Password: student123");
            } catch (Exception e) {
                System.out.println("Student user already exists or creation failed: " + e.getMessage());
            }
        }
    }
}