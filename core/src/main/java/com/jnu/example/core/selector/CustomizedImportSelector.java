package com.jnu.example.core.selector;

import com.jnu.example.core.processor.CustomizedBeanPostProcessor;
import com.jnu.example.core.processor.CustomizedBeanFactoryPostProcessor;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;


/**
 *  @Author: zy
 *  @Date: 2020/6/20 17:16
 *  @Description: 自定义aop实现,提交给spring容器
 *  @import(ImportSelector.class)
 *  ImportSelector 接口有一个实现方法，返回一个字符串类型的数组，里面可以放类名，在@import(ImportSelector.class)的时候，spring会把我们返回方法里面的类全部注册到
 *  BeanDefinitionMap中，继而将对象注册到Spring容器中
 */
public class CustomizedImportSelector implements ImportSelector {
    @Override
    public String[] selectImports(AnnotationMetadata annotationMetadata) {
        return new String[]{CustomizedBeanFactoryPostProcessor.class.getName(),CustomizedBeanPostProcessor.class.getName()};
    }
}
