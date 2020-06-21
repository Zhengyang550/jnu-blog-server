package com.jnu.example.core.annotation;


        import java.lang.annotation.Retention;
        import java.lang.annotation.RetentionPolicy;

/**
 *  @Author: zy
 *  @Date: 2020/6/20 17:31
 *  @Description: 前置通知注解类
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface Before {
    String value() default "";
}
