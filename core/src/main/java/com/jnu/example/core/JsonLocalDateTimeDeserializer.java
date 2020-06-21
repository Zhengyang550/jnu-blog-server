package com.jnu.example.core;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

/**
 * Author: zy
 * Description: LocalDateTime 反序列化器  https://blog.csdn.net/hpc_2015/article/details/97897660
 * Date: 2020/6/2
 * https://blog.csdn.net/Mr_Mocha/article/details/103332018
 */
public class JsonLocalDateTimeDeserializer extends JsonDeserializer<LocalDateTime> {
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
        Instant instant = date.toInstant();
        ZoneId zoneId = ZoneId.systemDefault();
        return instant.atZone(zoneId).toLocalDateTime();
    }

    @Override
    public Class<?> handledType() {
        // 关键
        return LocalDateTime.class;
    }
}
