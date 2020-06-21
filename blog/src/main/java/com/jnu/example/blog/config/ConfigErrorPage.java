package com.jnu.example.blog.config;


import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.server.ErrorPageRegistrar;
import org.springframework.boot.web.server.ErrorPageRegistry;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;

/**
 *  @Author: zy
 *  @Date: 2020/4/18 19:43
 *  @Description: 修改错误页面
 */
@Configuration
public class ConfigErrorPage implements ErrorPageRegistrar {

    @Override
    public void registerErrorPages(ErrorPageRegistry registry) {
        ErrorPage errorPage404 = new ErrorPage(HttpStatus.NOT_FOUND,"/error/404");
        registry.addErrorPages(errorPage404);
    }
}
