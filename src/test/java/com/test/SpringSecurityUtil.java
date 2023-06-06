package com.test;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class SpringSecurityUtil {
    public static void main(String[] args) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String password = bCryptPasswordEncoder.encode("123");

        System.out.println(password);
    }
}