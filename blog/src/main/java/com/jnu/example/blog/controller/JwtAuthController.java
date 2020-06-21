package com.jnu.example.blog.controller;

import com.jnu.example.db.pojo.vo.LoginVO;
import com.jnu.example.blog.service.IJwtAuthService;
import com.jnu.example.core.pojo.CustomizedResponseEntity;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotBlank;

/**
 * Author: zy
 * Description: 登录控制器
 * Date: 2020/4/17
 */
@Api(tags = "登录控制器")
@RestController
@Validated
public class JwtAuthController {
    @Autowired
    private IJwtAuthService jwtAuthService;

    @ApiOperation(value = "获取token")
    @GetMapping("/login")
    public CustomizedResponseEntity<LoginVO> login(@ApiParam(value = "用户名",required = true) @RequestParam("username") @NotBlank(message="用户名不能为空") String username,
                                                   @ApiParam(value = "密码",required = true) @RequestParam("password") @NotBlank(message="密码不能为空") String password){
        return CustomizedResponseEntity.success(jwtAuthService.login(username,password));
    }

    @ApiOperation(value = "获取token")
    @GetMapping("/github")
    public CustomizedResponseEntity<LoginVO> login(@ApiParam(value = "授权码",required = true) @RequestParam("code") @NotBlank(message="授权码不能为空") String code){
        return CustomizedResponseEntity.success(jwtAuthService.githubLogin(code));
    }
}
