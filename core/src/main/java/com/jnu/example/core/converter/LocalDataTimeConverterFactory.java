package com.jnu.example.core.converter;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.ConverterFactory;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

/**
 * Author: zy
 * Description: String->LocalDataTime类型转换器
 * Date: 2020/5/21
 */
public class LocalDataTimeConverterFactory implements ConverterFactory<String, LocalDateTime> {

    /**
     * @author: zy
     * @description: 获取String->LocalDateTime类型的转换器
     * @date: 2020/5/21 10:17
     * @param targetType: 目标类型
     * @return Converter<String,T>:
     */
    @Override
    public <T extends LocalDateTime> Converter<String, T> getConverter(Class<T> targetType) {
        //获取给定类型对应的转换器
        return new StrToLocalDataTime();
    }

    /*
     * 整型字符串转换为T枚举类型的转换器
     */
    private static final class StrToLocalDataTime<T extends LocalDateTime> implements Converter<String, T> {
        /*
         * 将给定的字符串转换成对应的枚举类型
         */
        @Override
        public T convert(String source) {
            DateTime dateTime;
            try {
                //解析字符串
                dateTime = DateUtil.parse(source);
            } catch (Exception e) {
                dateTime = DateUtil.parseDateTime(source.replaceAll("T", " "));
            }
            if(dateTime == null){
                return null;
            }
            Date date = dateTime.toJdkDate();
            // Instant 类代表的是某个时间（有点像 java.util.Date），它是精确到纳秒的（而不是象旧版本的Date精确到毫秒）
            Instant instant = date.toInstant();
            // 获取当前时区
            ZoneId zoneId = ZoneId.systemDefault();
            // 转为当前时区的日期
            return (T) instant.atZone(zoneId).toLocalDateTime();
        }
    }
}
