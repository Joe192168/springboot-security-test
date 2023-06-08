package com.test.filter;

import com.test.domain.dto.LoginUser;
import com.test.utils.JwtUtil;
import com.test.utils.RedisCache;
import io.jsonwebtoken.Claims;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

/**
 * @ClassName: 认证过滤器
 * @Author : xqh
 * @Date :2023/6/6
 * @Description: TODO
 */
@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    @Autowired
    private RedisCache redisCache;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //从head获取token信息
        String token = request.getHeader("token");
        //判断token是否为空
        if (StringUtils.isBlank(token)){
            //说明没有携带token，那么直接放行之后的过滤器肯定会报错，那么就说明用户没有登录
            filterChain.doFilter(request,response);
            return;
            //throw new RuntimeException("没有携带token");
        }
        //解析token
        String userId;
        try {
            Claims claims = JwtUtil.parseJWT(token);
            userId = claims.getSubject();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("token无效");
        }
        //从redis拿到用户信息，给SecurityContextHolder设置上下文
        LoginUser loginUser = (LoginUser) redisCache.getCacheObject("login:" + userId);
        if (Objects.isNull(loginUser)){
            throw new RuntimeException("用户未登录");
        }
        //存入SecurityContextHolder上下文当中  注意 这里必须得使用三个参数的authentication
        Authentication authentication = new UsernamePasswordAuthenticationToken(loginUser,null,loginUser.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        //放行
        filterChain.doFilter(request,response);
    }
}
