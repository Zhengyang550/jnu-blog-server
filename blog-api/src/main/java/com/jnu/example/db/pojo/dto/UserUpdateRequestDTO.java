package com.jnu.example.db.pojo.dto;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;


/**
 *  @Author: zy
 *  @Date: 2020/5/10 17:34
 *  @Description:用户更新前端要传的json格式
 */
@Data
public class UserUpdateRequestDTO extends UserRequestDTO{
    @NotNull(message = "用户id不能为空")
    @Positive(message = "用户id必须是正整数")
    @ApiModelProperty(value = "用户id")
    private Integer id;

    @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*[1-9])(?=.*[\\W]).{8,50}$",
            message = "密码长度为8-50位字符,用大写字母、小写字母、数字、符号四种中至少包含三种且必须包含数字和符号，不能与用户名重复")
    @ApiModelProperty(value = "用户登陆密码")
    private String password;
}
