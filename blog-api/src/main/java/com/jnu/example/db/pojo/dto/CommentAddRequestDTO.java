package com.jnu.example.db.pojo.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

/**
 *  @Author: zy
 *  @Date: 2020/4/14 23:32
 *  @Description: 新增评论前端要传的json格式
 */
@Data
public class CommentAddRequestDTO {
    @NotNull(message = "文章id不能为空")
    @Positive(message = "文章id只能是正整数")
    @ApiModelProperty(value = "文章id")
    private Integer articleId;

    @NotNull(message = "用户id不能为空")
    @Positive(message = "用户id只能是正整数")
    @ApiModelProperty(value = "用户id")
    private Integer userId;

    @NotBlank(message="文章评论不能为空")
    @ApiModelProperty(value = "文章评论")
    private String content;
}
