package com.test.service.impl;

import com.test.domain.dto.LoginUser;
import com.test.domain.entity.Authority;
import com.test.domain.entity.User;
import com.test.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userMapper.getUserByUsername(username);
        if (user == null) {
            throw new InternalAuthenticationServiceException("您的账户名有误！");
        }
        //根据用户名查询角色权限
        List<Authority> authorityList = userMapper.getRolesByUsername(username);
        user.setAuthorityList(authorityList);
        return new LoginUser(user);
    }
}