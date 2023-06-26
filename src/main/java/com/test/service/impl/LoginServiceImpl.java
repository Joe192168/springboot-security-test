package com.test.service.impl;

import com.test.constant.Constants;
import com.test.domain.dto.LoginUser;
import com.test.domain.entity.TUser;
import com.test.domain.pojo.MsgResult;
import com.test.service.LoginService;
import com.test.service.TokenService;
import com.test.utils.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @ClassName: LoginServiceImpl
 * @Author : xqh
 * @Date :2023/6/6
 * @Description: TODO
 */
@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private RedisCache redisCache;

    @Override
    public MsgResult login(TUser TUser) {
        //使用Authentication的实现类
        Authentication authentication = new UsernamePasswordAuthenticationToken(TUser.getUsername(), TUser.getPassword());

        //手动调用方法去认证,它会自动调用UserDetailsService查 然后对比啥的
        Authentication authenticate = authenticationManager.authenticate(authentication);
        if(Objects.isNull(authenticate)){ //说明输入错误
            throw new  RuntimeException("用户名或密码错误");
        }
        //拿到用户信息 然后生成jwt返回给前端，并且将用户的信息存入redis
        LoginUser loginUser = (LoginUser)authenticate.getPrincipal(); // 这个其实就是UserDetails 也就是LoginUser
        //生成token
        String token = tokenService.createToken(loginUser);
        Map<String, String> map = new HashMap<>();
        map.put("token",token);
        return MsgResult.success("获取jwt成功",map);
    }

    @Override
    public MsgResult logout() {
        //因为这个方法 是通过了jwt过滤器执行到这里的 所以SecurityContextHolder上下文是一样的
        LoginUser loginUser = (LoginUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        //拿到用户id删除redis中的数据
        String userId  = loginUser.getTUser().getId().toString();
        redisCache.deleteObject(Constants.LOGIN_USER_KEY+userId);
        return MsgResult.success("退出成功");
    }
}
