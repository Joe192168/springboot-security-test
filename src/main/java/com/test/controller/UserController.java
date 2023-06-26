package com.test.controller;

import com.test.domain.pojo.MsgResult;
import com.test.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserMapper userMapper;

    //@PreAuthorize("hasPermission('/user/getUserByName/**')")
    @GetMapping("/getUserByName/{userName}")
    public MsgResult user(@PathVariable String userName){
        return MsgResult.success(userMapper.getUserByUsername(userName));
    }

    //@PreAuthorize("hasAnyRole('admin')")
    @GetMapping("/admin")
    public String admin(){
        return "admin";
    }
}
