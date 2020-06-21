package com.jnu.example.blog;

import com.jnu.example.core.annotation.Aspect;
import com.jnu.example.core.annotation.Before;
import com.jnu.example.core.annotation.Pointcut;
import org.springframework.stereotype.Component;


/**
 *  @Author: zy
 *  @Date: 2020/6/21 10:12
 *  @Description: 这是一个切面类 不可以使用@Configuration注解(会被动态代理)
 */
@Component
@Aspect
public class AspectTest {

    /**
     *  @Author: zy
     *  @Date: 2020/6/21 17:57
     *  @Description: 定义一个切点 目前只支持指定包路径
     */
    @Pointcut("com.jnu.example.blog.service")
    public void servicePointCut(){

    }

    /**
     *  @Author: zy
     *  @Date: 2020/6/21 17:57
     *  @Description:前置通知
     */
    @Before("servicePointCut()")
    public void testBefore(){
        System.out.println("before -----------------------,测试成功");
    }
}
