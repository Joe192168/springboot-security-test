package com.test.filter;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @ClassName: 客户端认证过滤器
 * @Description: TODO
 */
@Component
public class ClientFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //处理自定义逻辑验证
        System.out.println("ClientFilter");
        //从数据查询客户端是否存在数据
        //成功，才放行
        filterChain.doFilter(request,response);
    }
}
