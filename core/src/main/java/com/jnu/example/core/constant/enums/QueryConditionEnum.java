package com.jnu.example.core.constant.enums;

/**
 * Author: zy
 * Description: 高阶查询查询条件
 * Date: 2020/5/20
 */
public enum QueryConditionEnum {
    // 相于
    EQ,
    // 不等于
    NE,
    // in
    IN,
    // not in
    NOT_IN,
    // 大于
    GT,
    //大于等于
    GE,
    //小于
    LE,
    // like
    LIKE,
    // not like
    NOT_LIKE,
    // like left
    LIKE_LEFT,
    // like right
    LIKE_RIGHT,
    // is null
    IS_NULL,
    // is not null
    IS_NOT_NULL,
    // orderByAsc
    ORDER_BY_ASC,
    // orderByDesc
    ORDER_BY_DESC,
    // Apply （手写sql）
    APPLY
}
