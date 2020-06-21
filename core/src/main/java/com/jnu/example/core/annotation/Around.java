package com.jnu.example.core.annotation;


import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 *  @Author: zy
 *  @Date: 2020/6/20 17:33
 *  @Description:环绕通知注解类
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface Around {
    String value() default "";
}
