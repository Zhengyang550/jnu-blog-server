package com.jnu.example.core.processor;

import com.jnu.example.core.util.JnuAspectUtil;
import lombok.SneakyThrows;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.cglib.proxy.Enhancer;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;


/**
 *  @Author: zy
 *  @Date: 2020/6/20 16:52
 *  @Description: bean实例化之后调用
 *  我们可以利用BeanPostProcessor，在bean实例化之后，在放入容器之前，进行一个条件过滤，如果当前对象是我们的目标对象（即在我们定义好的Map中），
 *  则对对象进行代理，将目标对象替换成代理对象返回即可
 */
public class CustomizedBeanPostProcessor implements BeanPostProcessor {

    /**
     *  @Author: zy
     *  @Date: 2020/6/21 21:04
     *  @Description: bean实例化之后放到Spring IOC容器之前
     */
    @SneakyThrows
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName)  {
        //获取bean class
        Class clazz = bean.getClass();
        //获取bean类名
        String className = clazz.getName();
        //如果这个bean是已经被代理后的  获取被代理前的类名
        className = className.substring(0,className.indexOf("$$") > 0 ? className.indexOf("$$"):className.length());
        Object object = bean;
        //对目标对象进行代理  采用cglib代理 继承方式
        if(JnuAspectUtil.classzzProxyBeanHolder.containsKey(className)){
            // 创建加强器，用来创建动态代理类
            Enhancer enhancer = new Enhancer();
            // 为加强器指定要代理的业务类（即：为下面生成的代理类指定父类）
            enhancer.setSuperclass(clazz);
            // 设置回调：对于代理类上所有方法的调用，都会调用CallBack，而Callback则需要实现intercept()方法进行拦
            enhancer.setCallback(new CustomizedProxyInterceptor(JnuAspectUtil.classzzProxyBeanHolder.get(className)));
            // 创建动态代理类对象并返回
            object = enhancer.create();

            //获取Class对象所表示的类或接口的所有(包含private修饰的)字段,不包括继承的字段
            Field[] fields = clazz.getDeclaredFields();
            //遍历字段
            for(Field field:fields){
                //排除静态方法
                if (Modifier.isFinal(field.getModifiers())){
                    continue;
                }
                //设置私有字段可以访问
                field.setAccessible(true);
                //实现相同字段赋值，解决代理对象中的自动注入bean为空的问题
                field.set(object,field.get(bean));
            }
        }
        return object;
    }


    /**
     *  @Author: zy
     *  @Date: 2020/6/21 21:05
     *  @Description: bean实例化之后放到Spring IOC容器之后执行
     */
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) {
        return bean;
    }
}
