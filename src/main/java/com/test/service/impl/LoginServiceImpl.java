package com.test.service.impl;

import com.test.domain.dto.LoginUser;
import com.test.domain.entity.User;
import com.test.domain.pojo.MsgResult;
import com.test.service.LoginService;
import com.test.utils.JwtUtil;
import com.test.utils.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
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
    private RedisCache redisCache;

    @Override
    public MsgResult login(User user) {
        //使用Authentication的实现类
        Authentication authentication = new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword());

        //手动调用方法去认证,它会自动调用UserDetailsService查 然后对比啥的
        Authentication authenticate = authenticationManager.authenticate(authentication);
        if(Objects.isNull(authenticate)){ //说明输入错误
            throw new  RuntimeException("用户名或密码错误");
        }
        //拿到用户信息 然后生成jwt返回给前端，并且将用户的信息存入redis
        LoginUser loginUser = (LoginUser)authenticate.getPrincipal(); // 这个其实就是UserDetails 也就是LoginUser
        String userId = loginUser.getUser().getId().toString();

        String jwt = JwtUtil.createJWT(userId+"");
        //将用户信息直接存入redis
        redisCache.setCacheObject("login:"+userId,loginUser);

        Map<String, String> map = new HashMap<>();
        map.put("token",jwt);
        return new MsgResult(200,"获取jwt成功",map);
    }
}
