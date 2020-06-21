package com.jnu.example.blog.service;

import com.jnu.example.db.pojo.vo.LoginVO;


/**
 * Author: zy
 * Description: 登录接口
 * Date: 2020/4/17
 */
public interface IJwtAuthService {
    LoginVO login(String username, String password);
    LoginVO githubLogin(String code);
}
