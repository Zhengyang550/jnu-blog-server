package com.jnu.example.db.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.time.LocalDateTime;
import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * InnoDB free: 3072 kB; InnoDB free: 3072 kB
 * </p>
 *
 * @author zy
 * @since 2020-04-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_blog_user")
@ApiModel(value="BlogUser对象", description="InnoDB free: 3072 kB; InnoDB free: 3072 kB")
public class BlogUser implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    protected Integer id;

    @ApiModelProperty(value = "用户名")
    protected String username;

    @ApiModelProperty(value = "通过 bcrypt 加密后的密码")
    @JsonIgnore
    protected String password;

    @ApiModelProperty(value = "邮箱")
    protected String email;

    @ApiModelProperty(value = "消息通知")
    protected Boolean notice;

    @ApiModelProperty(value = "github")
    protected String github;

    @ApiModelProperty(value = "禁言")
    @TableField("disabledDiscuss")
    protected Boolean disabledDiscuss;

    @ApiModelProperty(value = "头像")
    protected String avatar;

    @TableField(value = "createdAt",fill = FieldFill.INSERT)
    protected LocalDateTime createdAt;

    @TableField(value = "updatedAt",fill = FieldFill.UPDATE)
    protected LocalDateTime updatedAt;
}
