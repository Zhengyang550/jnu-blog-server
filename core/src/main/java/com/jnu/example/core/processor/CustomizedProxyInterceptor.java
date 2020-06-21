package com.jnu.example.core.processor;

import com.jnu.example.core.holder.ProxyBeanHolder;
import com.jnu.example.core.util.JnuAspectUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;


/**
 *  @Author: zy
 *  @Date: 2020/6/20 16:58
 *  @Description：代理对象
 *  cglib是针对类来实现代理的，原理是对指定的业务类生成一个子类，并覆盖其中业务方法实现代理。因为采用的是继承，所以不能对final修饰的类进行代理。
 */
@Slf4j
public class CustomizedProxyInterceptor implements MethodInterceptor {

    /*
     * 用于接收切面信息
     */
    private List<ProxyBeanHolder> proxyBeanHolderList;

    /*
     * 构造函数 初始化切面信息
     */
    public CustomizedProxyInterceptor(List<ProxyBeanHolder> proxyBeanHolderList){
        this.proxyBeanHolderList = proxyBeanHolderList;
    }

    /**
     *  @Author: zy
     *  @Date: 2020/6/21 18:18
     *  @Description: 在目标对象中的方法执行前后、执行对应的通知函数
     */
    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        //处理前置以及环绕前置通知
        for(ProxyBeanHolder proxyBeanHolder:proxyBeanHolderList){
            //获取通知类型 @After、@Before、@Around
            String annotationName = proxyBeanHolder.getAnnotationName();
            if(annotationName.equals(JnuAspectUtil.BEFORE) || annotationName.equals(JnuAspectUtil.AROUND)){
                doProxy(proxyBeanHolder);
            }
        }

        //执行目标对象原始方法
        Object result = null;
        try{
            result = methodProxy.invokeSuper(o,objects);
        }catch (Exception e){
            log.error(e.getMessage(),e);
        }

        //处理后置以及环绕后置通知
        for(ProxyBeanHolder proxyBeanHolder:proxyBeanHolderList){
            //获取通知类型 @After、@Before、@Around
            String annotationName = proxyBeanHolder.getAnnotationName();
            if(annotationName.equals(JnuAspectUtil.AFTER) || annotationName.equals(JnuAspectUtil.AROUND)){
                doProxy(proxyBeanHolder);
            }
        }
        return result;
    }

    /*
    * 处理代理通知操作
     */
    private void doProxy(ProxyBeanHolder proxyBeanHolder) {
        //获取切面类名称
        String className = proxyBeanHolder.getClassName();
        //获取代理方法
        String methodName = proxyBeanHolder.getMethodName();
        Object clazz = null;
        try{
            //加载切面类
            clazz = Class.forName(className);
            //获取所有方法 包含继承方法
            Method[] methods = ((Class)clazz).getMethods();
            //执行代理方法
            for(Method proxyMethod:methods){
                if(proxyMethod.getName().equals(methodName)){
                    proxyMethod.invoke(((Class) clazz).newInstance(),null);
                }
            }

        } catch (ClassNotFoundException | IllegalAccessException | InvocationTargetException | InstantiationException e) {
            log.error(e.getMessage(),e);
        }
    }
}
