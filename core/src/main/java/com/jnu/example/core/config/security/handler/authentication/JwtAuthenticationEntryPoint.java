package com.jnu.example.core.config.security.handler.authentication;

import com.jnu.example.core.constant.enums.ResponseCode;
import com.jnu.example.core.pojo.CustomizedResponseEntity;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Author: zy
 * Description: 处理AuthenticationException 类型的异常
 * AuthenticationException：是在用户认证的时候出现错误时抛出的异常
 * Date: 2020/4/15
 */
@Configuration
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        CustomizedResponseEntity responseEntity = CustomizedResponseEntity.fail(ResponseCode.AUTHENTICATION_FAIL);
        httpServletResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        httpServletResponse.setContentType("application/json;charset=utf-8");
        httpServletResponse.getWriter().write(responseEntity.toString());
    }
}
