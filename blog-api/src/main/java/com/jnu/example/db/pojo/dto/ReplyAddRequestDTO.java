package com.jnu.example.db.pojo.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

/**
 *  @Author: zy
 *  @Date: 2020/4/14 23:32
 *  @Description: 新增评论回复前端要传的json格式
 */
@Data
public class ReplyAddRequestDTO extends CommentAddRequestDTO{
    @NotNull(message = "评论id不能为空")
    @Positive(message = "评论id只能是正整数")
    @ApiModelProperty(value = "评论id")
    private Integer commentId;
}
