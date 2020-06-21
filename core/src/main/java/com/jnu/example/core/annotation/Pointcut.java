package com.jnu.example.core.annotation;


import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 *  @Author: zy
 *  @Date: 2020/6/21 17:54
 *  @Description:切点
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface Pointcut {
    String value() default "";
}
