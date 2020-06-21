package com.jnu.example.core.holder;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *  @Author: zy
 *  @Date: 2020/6/20 15:03
 *  @Description: 自定义数据结构 用于存放代理信息
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProxyBeanHolder {
    /*
     * 切面类名称
     */
    private String className;

    /*
     * 代理方法 如：testBefore
     */
    private String methodName;

    /*
     * 通知注解类名称 如：com.jnu.example.core.annotation.Before
     */
    private String annotationName;
}
