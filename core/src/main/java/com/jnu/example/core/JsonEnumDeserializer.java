package com.jnu.example.core;

import cn.hutool.core.util.StrUtil;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonStreamContext;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.JsonNodeType;
import com.jnu.example.core.constant.enums.IBaseEnum;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;

/**
 * Author: zy
 * Description: 反序列化 https://blog.csdn.net/pistolove/article/details/50868105?a=12388
 * https://www.cnblogs.com/binz/p/9178988.html
 * Date: 2020/6/2
 */
@Slf4j
public class JsonEnumDeserializer extends JsonDeserializer<IBaseEnum> {
    @SuppressWarnings({ "unchecked", "rawtypes" })
    @SneakyThrows
    @Override
    public IBaseEnum deserialize(JsonParser jp, DeserializationContext ctxt)  {
        //获取当前反序列化的json key
        String currentFieldName = jp.currentName();
        //获取当前反序列化的json key对应的值
        JsonNode currentFieldValue = jp.getCodec().readTree(jp);
        //获取当前json反序列后的目标对象
        Object targetObject = jp.getCurrentValue();
        //获取反序列化的json key对应的类型
        Class currentFieldType = null;

        //如果反序列化为一个集合
        if(targetObject instanceof Collection) {
            JsonStreamContext parsingContext = jp.getParsingContext();
            JsonStreamContext parent = parsingContext.getParent();
            Object value = parent.getCurrentValue();
            String name = parent.getCurrentName();
            try {
                Field listField = value.getClass().getDeclaredField(name);
                ParameterizedType listGenericType = (ParameterizedType) listField.getGenericType();
                Type listActualTypeArguments = listGenericType.getActualTypeArguments()[0];
                currentFieldType = (Class) listActualTypeArguments;
            } catch (Exception e) {
                log.error("enum conveter fail:",e);
            }
        }else {
            //从目标对象中获取当前反序列化的字段的类型
            currentFieldType = BeanUtils.findPropertyType(currentFieldName, targetObject.getClass());
        }

        //如果目标对象不存在该字段
        if(currentFieldType == null) {
            log.error("enum conveter fail:cannot find field");
            return null;
        }

        //判断出当前token指向的是一个对象 如果时，表示使用FeignClient调用接口，将一个Map转为枚举
        String value = currentFieldValue.getNodeType() == JsonNodeType.OBJECT ? currentFieldValue.get("value").asText()
                :currentFieldValue.asText();

        //空
        if(StrUtil.isBlank(value)){
            return null;
        }

        //数字类型  将数字转换为枚举类型
        if(currentFieldValue.getNodeType() ==JsonNodeType.OBJECT || currentFieldValue.getNodeType() == JsonNodeType.NUMBER ){
            return IBaseEnum.valueOf(currentFieldType,Integer.parseInt(value));
        }

        //将字符串转为枚举类型
        return IBaseEnum.valueOf(currentFieldType,value);
    }
}
