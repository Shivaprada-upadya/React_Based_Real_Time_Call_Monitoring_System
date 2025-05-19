package com.callmonitor.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.callmonitor.model.AdminUser;
import com.callmonitor.repository.AdminUserRepository;

@Service
public class AdminUserService {
    @Autowired
    private AdminUserRepository adminUserRepository;

    @Autowired
    private PasswordEncoder passwordEncoder; // BCryptPasswordEncoder bean

    public AdminUser createAdminUser(String username, String rawPassword, String role) {
        String hashedPassword = passwordEncoder.encode(rawPassword);
        AdminUser user = new AdminUser();
        user.setUsername(username);
        user.setPasswordHash(hashedPassword);
        user.setRole(role);
        return adminUserRepository.save(user);
    }
}
