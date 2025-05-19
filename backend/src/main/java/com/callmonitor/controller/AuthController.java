package com.callmonitor.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.callmonitor.model.AdminUser;
import com.callmonitor.payload.LoginRequest;
import com.callmonitor.repository.AdminUserRepository;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AdminUserRepository adminUserRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
@PostMapping("/api/auth/login")
public ResponseEntity<?> login(@RequestBody LoginRequest request) {
    Optional<AdminUser> userOpt = adminUserRepository.findByUsername(request.getUsername());
    if (userOpt.isEmpty()) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
    }
    AdminUser user = userOpt.get();
    if (!passwordEncoder.matches(request.getPassword(), user.getPasswordHash())) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
    }
    // Login successful
    return ResponseEntity.ok("Login successful");
}

}