package com.axyl.student_management.controller;

import com.axyl.student_management.dto.ApiResponse;
import com.axyl.student_management.dto.LoginRequest;
import com.axyl.student_management.dto.LoginResponse;
import com.axyl.student_management.entity.User;
import com.axyl.student_management.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/api/v1/auth")
@CrossOrigin(origins = "*")
public class AuthController {
    
    @Autowired
    private UserService userService;
    
    @PostMapping("/login")
    public ResponseEntity<ApiResponse<LoginResponse>> login(@RequestBody LoginRequest loginRequest) {
        try {
            if (userService.authenticateUser(loginRequest.getUsername(), loginRequest.getPassword())) {
                User user = userService.getUserByUsername(loginRequest.getUsername()).orElse(null);
                if (user != null && user.isEnabled()) {
                    userService.updateLastLogin(loginRequest.getUsername());
                    
                    // Generate a simple token (in real application, use JWT)
                    String token = "Bearer_" + System.currentTimeMillis() + "_" + user.getId();
                    
                    LoginResponse response = new LoginResponse(
                        token,
                        user.getUsername(),
                        user.getFullName(),
                        user.getRole(),
                        "Login successful"
                    );
                    
                    return ResponseEntity.ok(ApiResponse.success("Login successful", response));
                } else {
                    return ResponseEntity.badRequest()
                        .body(ApiResponse.error("Account is disabled"));
                }
            } else {
                return ResponseEntity.badRequest()
                    .body(ApiResponse.error("Invalid username or password"));
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                .body(ApiResponse.error("Login failed: " + e.getMessage()));
        }
    }
    
    @PostMapping("/register")
    public ResponseEntity<ApiResponse<User>> register(@RequestBody User user) {
        try {
            // Set default role if not provided
            if (user.getRole() == null || user.getRole().isEmpty()) {
                user.setRole("STUDENT");
            }
            
            // Set default permissions
            Set<String> permissions = new HashSet<>();
            permissions.add("READ");
            user.setPermissions(permissions);
            
            User createdUser = userService.createUser(user);
            // Don't return password in response
            createdUser.setPassword(null);
            
            return ResponseEntity.ok(ApiResponse.success("User registered successfully", createdUser));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                .body(ApiResponse.error("Registration failed: " + e.getMessage()));
        }
    }
    
    @PostMapping("/logout")
    public ResponseEntity<ApiResponse<String>> logout() {
        // In a real application, you would invalidate the JWT token
        return ResponseEntity.ok(ApiResponse.success("Logout successful", "Logged out successfully"));
    }
    
    @GetMapping("/profile")
    public ResponseEntity<ApiResponse<User>> getProfile(@RequestParam String username) {
        try {
            User user = userService.getUserByUsername(username).orElse(null);
            if (user != null) {
                user.setPassword(null); // Don't return password
                return ResponseEntity.ok(ApiResponse.success(user));
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                .body(ApiResponse.error("Failed to get profile: " + e.getMessage()));
        }
    }
}