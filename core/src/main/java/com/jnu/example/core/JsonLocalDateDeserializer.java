package com.jnu.example.core;

import cn.hutool.core.date.DateUtil;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

/**
 * Author: zy
 * Description: LocalDate 反序列化器
 * Date: 2020/6/2
 */
public class JsonLocalDateDeserializer extends JsonDeserializer<LocalDate> {
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
        // Instant 类代表的是某个时间（有点像 java.util.Date），它是精确到纳秒的（而不是象旧版本的Date精确到毫秒）
        Instant instant = date.toInstant();
        // 获取当前时区
        ZoneId zoneId = ZoneId.systemDefault();
        // 转为当前时区的日期
        return instant.atZone(zoneId).toLocalDate();
    }
}
