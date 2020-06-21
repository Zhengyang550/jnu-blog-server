package com.jnu.example.core.annotation;


        import com.jnu.example.core.selector.CustomizedImportSelector;
        import org.springframework.context.annotation.Import;

        import java.lang.annotation.Retention;
        import java.lang.annotation.RetentionPolicy;

/**
 *  @Author: zy
 *  @Date: 2020/6/20 17:22
 *  @Description:aop开关注解
 *  如果在App启动类上加入该注解,将会将我们的后置处理器的实现交给spring管理,spring才能去扫描得到这个类，才能去执行我们的自定义的后置处理器里面的方法，才能实现我们的aop的代理
 */
@Retention(RetentionPolicy.RUNTIME)
@Import(CustomizedImportSelector.class)
public @interface EnableAspectAutoProxy {

}
