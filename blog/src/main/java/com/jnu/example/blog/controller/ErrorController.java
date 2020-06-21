package com.jnu.example.blog.controller;


import com.jnu.example.core.constant.enums.ResponseCode;
import com.jnu.example.core.pojo.CustomizedResponseEntity;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *  @Author: zy
 *  @Date: 2020/4/18 19:46
 *  @Description: 错误页面配置
 */
@Api(tags = "错误页面配置")
@RestController
@RequestMapping("/error")
public class ErrorController {
    @ApiOperation(value = "404配置")
    @RequestMapping("/404")
    public CustomizedResponseEntity<String> error404(){
        return CustomizedResponseEntity.fail(ResponseCode.PAGE_NOT_FOUND);
    }
}
