package com.test.controller;

import com.test.domain.entity.User;
import com.test.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @Autowired
    private UserMapper userMapper;

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/hello/{userName}")
    public User hello(@PathVariable String userName){
        return userMapper.getUserByUsername(userName);
    }
}
