package com.jnu.example.core.processor;


import cn.hutool.core.util.StrUtil;
import com.jnu.example.core.holder.ProxyBeanHolder;
import com.jnu.example.core.util.JnuAspectUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.core.type.AnnotationMetadata;

import java.io.File;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URISyntaxException;
import java.util.*;

/**
 *  @Author: zy
 *  @Date: 2020/6/20 15:34
 *  @Description: BeanFactory初始化后调用
 *  定义注册类  用于注册目标对象和切面对象之间的关系
 *  1. 切面对象指的就是被@Aspect注解修饰的配置类对象
 *  2. 目标对象就是被@Pointcut("execution(public * com.goldwind.bigdataplat.admin.controller.*.*(..))")拦截的bean
 *  完成所有BeanDefinition的扫描，找出我们所有的切面类，然后循环里面的方法，找到切点、以及所有的通知方法，然后根据注解判断通知类型（也就是前置，后置还是环绕），
 *  最后解析切点的内容，扫描出所有的目标类，放入我们定义好的容器中。
 */
@Slf4j
public class CustomizedBeanFactoryPostProcessor implements BeanFactoryPostProcessor{
    /*
     * 保存所有的切点修饰的方法名、以及切点的value()函数值
     */
    private Map<String,String> pointCutMap = new HashMap<>();

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory)  {
        //获取所有的bean name
        String[] beanDefinitionNames = configurableListableBeanFactory.getBeanDefinitionNames();
        for(String beanDefinitionName:beanDefinitionNames){
            BeanDefinition beanDefinition = configurableListableBeanFactory.getBeanDefinition(beanDefinitionName);
            //判断bean是否被注解修饰
            if(beanDefinition instanceof AnnotatedBeanDefinition){
                //获取bean上的所有注解
                AnnotationMetadata metadata = ((AnnotatedBeanDefinition)beanDefinition).getMetadata();
                //获取注解类型  如:{org.springframework.boot.autoconfigure.SpringBootApplication,com.jnu.example.db.annotation.CoreMapperScan,com.jnu.example.core.annotation.EnableAspectAutoProxy}
                Set<String> annotations = metadata.getAnnotationTypes();
                //遍历所有注解、找到aop切面注解类
                for(String annotation:annotations){
                    //如果被@Aspect注解修饰 表明这是一个切面类 查找切面类中指定的切点(@Pointcut)
                    if(annotation.equals(JnuAspectUtil.ASPECT)){
                        doScan((GenericBeanDefinition)beanDefinition);
                    }
                }
            }
        }
    }


    /**
     * @Author: zy
     * @Description: 扫描切面类所有方法上的注解
     * 1、如果有@Pointcut注解：保存对应的切点
     * 2、找到所有的通知注解@After、@Before、@Around，获取对应的切点
     * 3、保存
     * @Date: 2020/6/20 15:59
     * @param beanDefinition
     * @Return void:
     * @Exception :
     */
    private void doScan(GenericBeanDefinition beanDefinition){
        try{
            //获取切面类名 如:com.jnu.example.blog.TestAop
            String className = beanDefinition.getBeanClassName();
            //加载类
            Class<?> beanDefinitionClazz = Class.forName(className);
            //获取所有方法
            Method[] methods = beanDefinitionClazz.getMethods();

            //遍历切面类所有方法 第一遍历找到所有切点
            for(Method method:methods){
                //获取注解类名  如:@com.jnu.example.core.annotation.Pointcut
                Annotation[] annotations = method.getAnnotations();
                for(Annotation annotation:annotations){
                    //获取注解类名  如:@com.jnu.example.core.annotation.Pointcut
                    String annotationName = annotation.annotationType().getName();
                    if(JnuAspectUtil.POINTCUT.equals(annotationName)){
                        String value = getAnnotationValue(annotation);
                        if(StrUtil.isNotBlank(value)) {
                            //获取切点指定包
                            pointCutMap.put(method.getName()+"()", value);
                        }
                    }
                }
            }
            //遍历切面类所有方法 第二次遍历找到所有通知
            for(Method method:methods) {
                //获取注解类名  如:@com.jnu.example.core.annotation.After
                Annotation[] annotations = method.getAnnotations();
                for (Annotation annotation : annotations) {
                    //获取注解类名  如:@com.jnu.example.core.annotation.After
                    String annotationName = annotation.annotationType().getName();
                    //如果是Before、或者After、Around
                    if (JnuAspectUtil.BEFORE.equals(annotationName) || JnuAspectUtil.AFTER.equals(annotationName)
                            || JnuAspectUtil.AROUND.equals(annotationName)) {
                        try {
                            doScan(className, method, annotation);
                        } catch (Exception e) {
                            log.error(e.getMessage(), e);
                        }
                    }
                }
            }
        }catch(ClassNotFoundException e){
            log.error(e.getMessage(),e);
        }
    }


    /**
     * @Author: zy
     * @Description: 扫描切点指定的所有目标类  保存到map中：目标类 ->(切面类,代理方法,通知注解)
     * @Date: 2020/6/20 16:09
     * @param className：切面类
     * @param method: 通知方法
     * @param annotation：通知注解 @after或者@before、@Around
     * @Return void:
     * @Exception :
     */
    private void doScan(String className,Method method,Annotation annotation) throws URISyntaxException {
        //保存代理信息
        ProxyBeanHolder proxyBeanHolder = new ProxyBeanHolder(className,method.getName(),annotation.annotationType().getName());

        //获取通知value，即切点方法
        String pointCutMethod = getAnnotationValue(annotation);

        //获取包路径
        String packagePath = pointCutMap.get(pointCutMethod);

        //遍历包、找到包下的所有目标类
        if(StrUtil.isNotBlank(packagePath)){
            traverseDir(packagePath,proxyBeanHolder);
        }
    }

    /*
     * 遍历file对象
     */
    private void  traverseDir(String packagePath,ProxyBeanHolder proxyBeanHolder) throws URISyntaxException {
        //获取目标包路径:classpath + 包名
        String targetPackagePath = this.getClass().getResource("/").toURI().getPath() + packagePath.replace(".","/");
        File file = new File(targetPackagePath);
        File[] fileList = file.listFiles();
        if(fileList == null){
            return;
        }
        List<ProxyBeanHolder> proxyBeanHolderList = null;
        //遍历包路径
        for(File fp:fileList){
            //判断是不是文件
            if(fp.isFile()){
                String targetClass = packagePath + "." + fp.getName().replace(".class","");
                try{
                    proxyBeanHolderList = JnuAspectUtil.classzzProxyBeanHolder.get(targetClass);
                }catch (Exception e){
                    log.error(e.getMessage(),e);
                }
                if(proxyBeanHolderList == null){
                    proxyBeanHolderList = new Vector<>();
                }
                proxyBeanHolderList.add(proxyBeanHolder);
                JnuAspectUtil.classzzProxyBeanHolder.put(targetClass,proxyBeanHolderList);
            }else{
                traverseDir(packagePath + "." + fp.getName(),proxyBeanHolder);
            }
        }
    }

    /**
     * @Author: zy
     * @Description: 获取注解value()方法的值
     * @Date: 2020/6/21 17:39
     * @param annotation：注解
     * @Return String:
     * @Exception :
     */
    private String getAnnotationValue(Annotation annotation){
        //获取注解上的所有方法  不包括继承的方法
        Method[] annotationMethods = annotation.annotationType().getDeclaredMethods();

        //遍历注解每一个方法
        for(Method annotationMethod:annotationMethods){
            //如果方法名称是value
            if(annotationMethod.getName().equals("value")){
                try{
                    //执行value()方法 第一个参数代表调用的对象，第二个参数传递的调用方法的参数
                    return (String)annotationMethod.invoke(annotation,null);
                }catch(IllegalAccessException | InvocationTargetException e){
                    log.error(e.getMessage(),e);
                }
            }
        }
        return "";
    }

}
