package com.jnu.example.blog.constant;


/**
 *  @Author: zy
 *  @Date: 2020/5/11 21:47
 *  @Description: github常量
 */
public class GitHubConstant {
    /*
     * 这里填写在GitHub上注册应用时候获得 CLIENT ID
     */
    public static final String  CLIENT_ID="Iv1.055b9abbe18d4600";

    /**
     * 这里填写在GitHub上注册应用时候获得 CLIENT_SECRET
     */
    public static final String CLIENT_SECRET="f1b9ac6c8fba2da9088f70c38a381acd3281f40a";

    /**
     * 回调路径
     */
    public static final String CALLBACK = "http://50937.51vip.biz/";

    /*
     * 获取code的url
     */
    public static final String CODE_URL = "https://github.com/login/oauth/authorize?client_id="
            + CLIENT_ID + "&state=STATE&redirect_uri=" + CALLBACK ;

    /***
     * 获取token的url
     */
    public static final String TOKEN_URL = "https://github.com/login/oauth/access_token?client_id="
            + CLIENT_ID + "&client_secret=" + CLIENT_SECRET + "&code=CODE";
    /*
     * 获取用户信息的url
     */
    public static final String USER_INFO_URL = "https://api.github.com/user?access_token=TOKEN";
}
