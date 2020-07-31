package com.jnu.example.core.config;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.jnu.example.core.converter.EnumConverterFactory;
import com.jnu.example.core.converter.LocalDataTimeConverterFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.support.GenericConversionService;

import java.io.IOException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;


/**
 *  @Author: zy
 *  @Date: 2020/4/14 22:52
 *  @Description: web配置
 *  LocalDateTime在spring boot中的格式化配置:https://www.cnblogs.com/carrychan/p/9883172.html
 */
@Configuration
public class WebConfig {
    @Value("${spring.jackson.date-format:yyyy-MM-dd HH:mm:ss}")
    private String pattern;

    /**
     * @Description: 日期格式的序列化和反序列化  可以将body内容、以及Feign请求的第三方接口的返回结果反序列胡为日期格式
     * @Author: zy
     * @Date: 2020/5/14 13:50
     * @param
     * @Return ObjectMapper:
     * @Exception :
     */
    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        objectMapper.disable(DeserializationFeature.ADJUST_DATES_TO_CONTEXT_TIME_ZONE);
        //添加此配置  解决json未知属性解析到PO造成的错误
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);


        // LocalDateTime系列序列化模块
        JavaTimeModule javaTimeModule = new JavaTimeModule();

        //序列化
        javaTimeModule.addSerializer(LocalDateTime.class, new  LocalDateTimeSerializer(DateTimeFormatter.ofPattern(pattern)));

        //反序列化
        javaTimeModule.addDeserializer(
                LocalDateTime.class,
                new JsonDeserializer<LocalDateTime>() {
                    @Override
                    public LocalDateTime deserialize(JsonParser jp, DeserializationContext dctx) throws IOException {
                        //获取日期时间
                        String dateStr = jp.getText();
                        DateTime dateTime;
                        try {
                            //解析字符串
                            dateTime = DateUtil.parse(dateStr);
                        } catch (Exception e) {
                            dateTime = DateUtil.parseDateTime(dateStr.replaceAll("T", " "));
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
                        return instant.atZone(zoneId).toLocalDateTime();
                    }
                }
        );

        // 反序列化
        javaTimeModule.addDeserializer(
                LocalDate.class,
                new JsonDeserializer<LocalDate>() {
                    @Override
                    public LocalDate deserialize(JsonParser jp, DeserializationContext dctx) throws IOException {
                        //获取日期时间
                        String dateStr = jp.getText();
                        Date date;
                        try {
                            //解析字符串
                            date = DateUtil.parse(dateStr);
                        } catch (Exception e) {
                            date = DateUtil.parseDateTime(dateStr.replaceAll("T", " "));
                        }
                        if(date == null){
                            return null;
                        }
                        Instant instant = date.toInstant();
                        ZoneId zoneId = ZoneId.systemDefault();
                        return instant.atZone(zoneId).toLocalDate();
                    }
                }
        );

        // 反序列化
        javaTimeModule.addDeserializer(
                Date.class,
                new JsonDeserializer<Date>() {
                    @Override
                    public Date deserialize(JsonParser jp, DeserializationContext dctx) throws IOException {
                        //获取日期时间
                        String dateStr = jp.getText();
                        Date date;
                        try {
                            //解析字符串
                            date = DateUtil.parse(dateStr);
                        } catch (Exception e) {
                            date = DateUtil.parseDateTime(dateStr.replaceAll("T", " "));
                        }
                        return date;
                    }
                }
        );

        objectMapper.registerModule(javaTimeModule);
        return objectMapper;
    }

    /*
     * 获取beanFactory中的conversionService，注入自定义类型转换器工厂
     */
    @Bean
    public GenericConversionService getDefaultConversionService(@Autowired GenericConversionService conversionService) {
        conversionService.addConverterFactory(new EnumConverterFactory());
        conversionService.addConverterFactory(new LocalDataTimeConverterFactory());
        return conversionService;
    }
}
