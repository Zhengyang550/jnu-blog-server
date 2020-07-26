package com.jnu.example.core.util;


import com.jnu.example.core.holder.ProxyBeanHolder;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 *  @Author: zy
 *  @Date: 2020/6/20 15:27
 *  @Description: aop切面注解工具类
 *  AOP 领域中的特性术语：
 *  通知（Advice）: AOP 框架中的增强处理。通知描述了切面何时执行以及如何执行增强处理；对应注解@After、@Before、@Around ...
 *  连接点（join point）: 连接点表示应用执行过程中能够插入切面的一个点，这个点可以是方法的调用、异常的抛出。在 Spring AOP 中，连接点总是方法的调用，可以说目标对应中的方法就是一个连接点
 *  切点（PointCut）: 就是连接点的集合；对应注解@PonitCut
 *  切面（Aspect）: 切面是通知和切点的结合；对应注解@Aspect修饰的一个类
 */
public class JnuAspectUtil {
    /*
     * 指定切面注解类
     */
    public static final String ASPECT = "com.jnu.example.core.annotation.Aspect";

    /*
     * 指定切点注解类
     */
    public static final String POINTCUT = "com.jnu.example.core.annotation.Pointcut";

    /*
     * 指定前置通知注解类
     */
    public static final String BEFORE = "com.jnu.example.core.annotation.Before";

    /*
     * 指定后置通知注解类
     */
    public static final String AFTER = "com.jnu.example.core.annotation.After";

    /*
     * 指定环绕通知注解类
     */
    public static final String AROUND = "com.jnu.example.core.annotation.Around";

    /*
     * 存放AOP代理的全部目标类  目标类 ->(切面类,代理方法,通知注解)  如:com.jnu.example.blog.service.IArticleService -> [(com.jnu.example.blog.AspectTest, testBefore, com.jnu.example.core.annotation.Before)]
     */
    public static volatile Map<String, List<ProxyBeanHolder>> classzzProxyBeanHolder = new ConcurrentHashMap<>();
}
