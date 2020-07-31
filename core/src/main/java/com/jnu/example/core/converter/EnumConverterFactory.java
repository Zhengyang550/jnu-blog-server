package com.jnu.example.core.converter;

import com.jnu.example.core.constant.enums.IBaseEnum;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.ConverterFactory;

/**
 * Author: zy
 * Description: String->IbaseEnum类型转换器
 * Date: 2020/5/21
 */
public class EnumConverterFactory implements ConverterFactory<String, IBaseEnum> {

    /**
     * @author: zy
     * @description: 获取String->targetType类型的转换器 用于支持自己IBaseEnum类型(Spring支持常规的枚举类型)
     * @date: 2020/5/21 10:17
     * @param targetType: 目标类型
     * @return Converter<String,T>:
     */
    @Override
    public <T extends IBaseEnum> Converter<String, T> getConverter(Class<T> targetType) {
        //获取给定类型对应的转换器
        return new StrToEnum(targetType);
    }

    /*
     * 整型字符串转换为T枚举类型的转换器
     */
    private static final class StrToEnum<T extends Enum<T> & IBaseEnum> implements Converter<String, T> {
        /*
         * 保存枚举类型
         */
        private final Class<T> enumType;

        /*
         * 构造函数
         */
        private StrToEnum(Class<T> enumType) {
            this.enumType = enumType;
        }


        /*
         * 将给定的字符串转换成对应的枚举类型
         */
        @Override
        public T convert(String source) {
            try{
                return (T)IBaseEnum.valueOf(this.enumType, Integer.parseInt(source));
            }catch(NumberFormatException e) {
                return (T)IBaseEnum.valueOf(this.enumType, source);
            }
        }
    }
}
