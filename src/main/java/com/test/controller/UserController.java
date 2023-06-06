package com.test.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    //@PreAuthorize("hasAnyRole('ADMIN','USER')")
    @GetMapping("/user")
    public String user(){
        return "user";
    }

    //@PreAuthorize("hasAnyRole('ADMIN')")
    @GetMapping("/admin")
    public String admin(){
        return "admin";
    }
}
