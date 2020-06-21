package com.jnu.example.db.pojo.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Author: zy
 * Description: 标签统计信息
 * Date: 2020/4/22
 */
@Data
public class TagVO {
    @ApiModelProperty("标签名称")
    private String name;

    @ApiModelProperty("文章计数")
    private Integer count;
}
