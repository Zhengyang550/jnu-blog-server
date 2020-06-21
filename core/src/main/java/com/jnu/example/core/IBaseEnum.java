package com.jnu.example.core;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * Author: zy
 * Description: 用户支持将http请求参数中传入的数字转换为对应的枚举值
 * Date: 2020/5/21
 */
@JsonSerialize(using = JsonEnumSerializer.class)
@JsonDeserialize(using = JsonEnumDeserializer.class)
public interface IBaseEnum{
    /*
     * 与数据库进行映射的值
     */
    int getValue();

    /*
     * 描述信息
     */
    String getDesc();

    /*
     * 将状态码装换为枚举类型
     */
    static <E extends IBaseEnum> IBaseEnum valueOf(Class<E> enumClass, int value){
        try {
            E[] enumConstants = enumClass.getEnumConstants();
            for (E e : enumConstants) {
                if (e.getValue() == value)
                    return e;
            }
            return null;
        } catch (Exception ex) {
            throw new IllegalArgumentException("Cannot convert " + value + " to " + enumClass.getSimpleName() + " by code value.", ex);
        }
    }

    /*
     * 将枚举字符串装换为枚举类型
     */
    static <E extends Enum<E>> IBaseEnum valueOf(Class<E> enumClass, String value) {
        return (IBaseEnum) Enum.valueOf(enumClass, value);
    }
}
