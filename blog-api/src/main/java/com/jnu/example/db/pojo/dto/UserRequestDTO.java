package com.jnu.example.db.pojo.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.*;

/**
 *  @Author: zy
 *  @Date: 2020/4/14 22:45
 *  @Description: 新增或者修改用户通用字段
 */
@Data
public class UserRequestDTO {
    @ApiModelProperty(value = "邮箱通知")
    private Boolean notice;

    @ApiModelProperty(value = "禁言")
    private Boolean disabledDiscuss;
}
