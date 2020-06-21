package com.jnu.example.core.config.security.handler.authorization;

import com.jnu.example.core.constant.enums.ResponseCode;
import com.jnu.example.core.pojo.CustomizedResponseEntity;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 *  @Author: zy
 *  @Date: 2020/4/15 23:50
 *  @Description: 处理AccessDeniedException类型异常
 *  AccessDeniedException: 主要是在用户在访问受保护资源时被拒绝而抛出的异常
 */
@Configuration
public class JwtAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException e) throws IOException, ServletException {
        CustomizedResponseEntity responseEntity = CustomizedResponseEntity.fail(ResponseCode.NO_PERMISSION);
        httpServletResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        httpServletResponse.setContentType("application/json;charset=utf-8");
        httpServletResponse.getWriter().write(responseEntity.toString());
    }
}
