package com.jnu.example.core;


import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Author: zy
 * Description: 对枚举类序列化 使得枚举类型返回前端内容为desc
 * Date: 2020/5/21
 */
public class JsonEnumSerializer extends JsonSerializer<IBaseEnum> {

    @Override
    public void serialize(IBaseEnum iBaseEnum, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        Map<String, Object> map = new HashMap<>();
        map.put("value", iBaseEnum.getValue());
        map.put("desc", iBaseEnum.getDesc());
        map.put("string",iBaseEnum.toString());
        serializerProvider.defaultSerializeValue(map, jsonGenerator);
    }
}
