package com.jnu.example.blog.service.impl;


import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.jnu.example.blog.constant.GitHubConstant;
import com.jnu.example.blog.entity.GithubUser;
import com.jnu.example.db.pojo.vo.LoginVO;
import com.jnu.example.blog.service.IJwtAuthService;
import com.jnu.example.core.config.security.JwtTokenUtil;
import com.jnu.example.core.constant.enums.ResponseCode;
import com.jnu.example.core.exception.BusinessException;
import com.jnu.example.core.util.JnuEncryptUtil;
import com.jnu.example.db.entity.BlogUser;
import com.jnu.example.db.service.IBlogUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * Author: zy
 * Description: 登录业务逻辑
 * Date: 2020/4/17
 */
@Service
@Slf4j
public class JwtAuthServiceImpl implements IJwtAuthService {
    //获取用户信息
    @Autowired
    private IBlogUserService blogUserService;

    @Override
    public LoginVO login(String username, String password) {
        //获取用户信息
        BlogUser user = blogUserService.getOne(Wrappers.<BlogUser>lambdaQuery().eq(BlogUser::getUsername,username)
                .isNull(BlogUser::getGithub));
        if(user == null){
            throw new BusinessException(ResponseCode.USER_ACCOUNT_NOT_EXIST);
        }

        //密码校验
        if(!user.getPassword().equals(JnuEncryptUtil.encryptToBase64(password))){
            throw new BusinessException(ResponseCode.USER_CREDENTIALS_ERROR);
        }

        //清空密码
        user.setPassword(null);

        // 创建token
        String token = JwtTokenUtil.generateToken(user.getId().toString(), user.getUsername());

        //创建返回实体
        LoginVO loginVO = new LoginVO();
        BeanUtils.copyProperties(user,loginVO);
        loginVO.setToken(token);
        return loginVO;
    }

    /**
     * @Description: 使用第三方github登录
     * @Author: zy
     * @Date: 2020/5/11 21:43
     * @param code: github授权后的授权码
     * @Return LoginVO:
     * @Exception :
     */
    @Override
    public LoginVO githubLogin(String code) {
        if(StrUtil.isBlank(code)){
            throw new BusinessException(ResponseCode.THREE_AUTHORIZATION_CODE_ERROR);
        }

        //拿到code请求token
        String tokenUrl = GitHubConstant.TOKEN_URL.replace("CODE",code);

        //发送请求
        String res = HttpUtil.get(tokenUrl);

        if(StrUtil.isBlank(res)){
            throw new BusinessException(ResponseCode.THREE_AUTHORIZATION_CODE_ERROR);
        }

        //得到的是类似这样的字符串，我们需要将它分割，只要access_token部分 access_token=9566ba3483a556c610be42d44338f3fd16a3b8d1&scope=&token_type=bearer
        String token =  res.split("&")[0].split("=")[1];

        //根据token获取用户信息
        String userInfoUrl = GitHubConstant.USER_INFO_URL.replace("TOKEN", token);

        //发送请求
        res = HttpUtil.get(userInfoUrl);

        if(StrUtil.isBlank(res)){
            throw new BusinessException(ResponseCode.THREE_AUTHORIZATION_CODE_ERROR);
        }

        //得到的是json字符串，因此需要转为GithubUser对象
        GithubUser githubUser = JSON.parseObject(res, GithubUser.class);

        if(githubUser.getLogin() == null){
            throw new BusinessException(ResponseCode.THREE_AUTHORIZATION_CODE_ERROR);
        }

        //判断数据库是否存在该用户
        BlogUser user = blogUserService.getOne(Wrappers.<BlogUser>lambdaQuery()
                .and(i ->i.eq(BlogUser::getEmail,githubUser.getEmail())
                        .or()
                        .eq(BlogUser::getGithub,githubUser.getLogin())));
        if(user == null) {
            //新增用户到数据表 github账号可以通过用户名或者邮箱登录
            user = new BlogUser();
            user.setUsername(StrUtil.isNotBlank(githubUser.getName())?githubUser.getName():githubUser.getLogin());
            user.setGithub(githubUser.getLogin());
            user.setEmail(githubUser.getEmail());
            user.setAvatar(githubUser.getAvatarUrl());

            //密码随机
            user.setPassword(JnuEncryptUtil.encryptToBase64("github"));

            //保存
            blogUserService.save(user);
        }

        //创建返回实体
        LoginVO loginVO = new LoginVO();
        BeanUtils.copyProperties(user,loginVO);
        loginVO.setToken(JwtTokenUtil.generateToken(user.getId().toString(), user.getUsername()));
        return loginVO;
    }
}
