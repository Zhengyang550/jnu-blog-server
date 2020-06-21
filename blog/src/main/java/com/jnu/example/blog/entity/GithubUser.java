package com.jnu.example.blog.entity;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 *  @Author: zy
 *  @Date: 2020/5/11 22:27
 *  @Description:github用户
 */
@Data
public class GithubUser {
    /**
     * github登录账号
     */
    private String login;

    /**
     * id
     */
    private Long id;

    /**
     * node_id
     */
    @JsonProperty(value = "node_id")
    private String nodeId;

    /**
     * 头像url
     */
    @JsonProperty(value = "avatar_url")
    private String avatarUrl;

    /**
     * 首页url
     */
    @JsonProperty(value = "html_url")
    private String htmlUrl;

    /**
     * 用户名
     */
    private String name;

    /**
     * 公司
     */
    private String company;


    /**
     * 邮箱
     */
    private String email;

    /**
     * 地址
     */
    private String location;

    /**
     * 创建日期
     */
    @JsonProperty(value = "created_at")
    private LocalDateTime createdAt;

    /**
     * 更新日期
     */
    @JsonProperty(value = "updated_at")
    private LocalDateTime updatedAt;
}
