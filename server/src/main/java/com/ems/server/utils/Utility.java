package com.ems.server.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class Utility {
    private static final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public static String encodePassword(String password) {
        return passwordEncoder.encode(password);
    }
}
