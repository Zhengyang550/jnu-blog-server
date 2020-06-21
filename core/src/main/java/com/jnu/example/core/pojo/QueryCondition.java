package com.jnu.example.core.pojo;

import com.jnu.example.core.constant.enums.QueryConditionEnum;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

/**
 * Author: zy
 * Description: 高级查询 json格式
 * Date: 2020/5/20
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class QueryCondition {
    @ApiModelProperty(value = "查询字段",example="createTime")
    private String field;

    @NotNull(message="查询条件不能为空")
    @ApiModelProperty(value = "查询条件",example = "GE")
    private QueryConditionEnum condition;

    @ApiModelProperty(value = "查询值",example = "2020-02-14")
    private String value;
}
