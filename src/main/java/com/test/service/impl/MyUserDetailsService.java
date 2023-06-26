package com.test.service.impl;

import com.test.domain.dto.LoginUser;
import com.test.domain.entity.TUser;
import com.test.mapper.UserMapper;
import com.test.service.SysPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private SysPermissionService permissionService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        TUser user = userMapper.getUserByUsername(username);
        if (user == null) {
            throw new InternalAuthenticationServiceException("您的账户名有误！");
        }
        //根据用户名查询角色权限
        //List<TRole> authorityList = userMapper.getRolesByUsername(username);
        //List<String> collect = authorityList.stream().map(authority -> authority.getRoleName()).collect(Collectors.toList());
        Set<String> menuPermission = permissionService.getMenuPermission(user);
        return new LoginUser(user,menuPermission);
    }
}