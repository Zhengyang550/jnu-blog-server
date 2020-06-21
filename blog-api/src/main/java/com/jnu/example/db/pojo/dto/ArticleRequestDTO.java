package com.jnu.example.db.pojo.dto;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.List;

/**
 *  @Author: zy
 *  @Date: 2020/5/17 18:59
 *  @Description:新增或者修改文章通用字段
 */
@Data
public class ArticleRequestDTO {
    @NotBlank(message="文章标题不能为空")
    @ApiModelProperty(value = "文章标题")
    private String title;

    @NotBlank(message="文章内容不能为空")
    @ApiModelProperty(value = "文章内容")
    private String content;

    @ApiModelProperty(value = "文章标签")
    private List<String> tags;
}
