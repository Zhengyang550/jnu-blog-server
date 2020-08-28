package com.jnu.example.db.pojo.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

/**
 *  @Author: zy
 *  @Date: 2020/8/28 18:57
 *  @Description:更新文章前端要传的json格式
 */
@Data
public class ArticleUpdateRequestDTO extends ArticleRequestDTO  {
    @NotNull(message = "文章id不能为空")
    @Positive(message = "文章id必须是正整数")
    @ApiModelProperty(value = "文章id")
    private Integer id;
}
