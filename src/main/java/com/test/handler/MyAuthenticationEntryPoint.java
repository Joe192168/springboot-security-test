package com.test.handler;

import com.alibaba.fastjson.JSON;
import com.test.config.HttpStatus;
import com.test.domain.pojo.MsgResult;
import com.test.utils.ServletUtils;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 授权过程中出现异常处理
 */
@Component
public class MyAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {
        response.setStatus(HttpStatus.UNAUTHORIZED);//401 表示没有授权
        ServletUtils.renderString(response, JSON.toJSONString(new MsgResult(HttpStatus.UNAUTHORIZED,"认证失败请重新登录")));
    }
}