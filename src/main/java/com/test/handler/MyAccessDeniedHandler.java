package com.test.handler;

import com.alibaba.fastjson.JSON;
import com.test.config.HttpStatus;
import com.test.domain.pojo.MsgResult;
import com.test.utils.ServletUtils;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 认证过程中出现异常处理
 */
@Component
public class MyAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException e) throws IOException, ServletException {
        response.setStatus(HttpStatus.FORBIDDEN); //403
        ServletUtils.renderString(response, JSON.toJSONString(new MsgResult(HttpStatus.FORBIDDEN,"权限不足无法访问")));
    }
}