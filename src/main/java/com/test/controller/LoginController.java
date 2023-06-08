package com.test.controller;

import com.test.domain.entity.User;
import com.test.domain.pojo.MsgResult;
import com.test.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @PostMapping("/login")
    public MsgResult login(@RequestBody User user){
        MsgResult msgResult = loginService.login(user);
        return msgResult;
    }

    @PostMapping("/logout")
    public MsgResult logout(){
        return loginService.logout();
    }

    /*@GetMapping("/tologin")
    public String login(){
        return "login";
    }

    @GetMapping("/fail")
    public String fail(){
        return "fail";
    }

    @GetMapping("/toindex")
    public String toindex(){
        return "index";
    }*/

}
