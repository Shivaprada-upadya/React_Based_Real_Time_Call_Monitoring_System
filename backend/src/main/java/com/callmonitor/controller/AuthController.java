package com.callmonitor.controller;

import com.callmonitor.model.AdminUser;
import com.callmonitor.repository.AdminUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AdminUserRepository adminUserRepository;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> loginData) {
        String username = loginData.get("username");
        String password = loginData.get("password");

        AdminUser user = adminUserRepository.findByUsername(username)
                .orElse(null);

        if (user != null && user.getPasswordHash().equals(password)) {
            return ResponseEntity.ok(Map.of("message", "Login successful", "user", user));
        }

        return ResponseEntity.status(401).body(Map.of("message", "Invalid credentials"));
    }
}