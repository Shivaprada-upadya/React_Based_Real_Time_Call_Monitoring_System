package com.callmonitor.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordHasher {
    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String rawPassword = "admin123";  // your password here
        String encodedPassword = encoder.encode(rawPassword);
        System.out.println("Hashed password: " + encodedPassword);
    }
}
