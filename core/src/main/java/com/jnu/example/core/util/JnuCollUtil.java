package com.jnu.example.core.util;

import cn.hutool.core.collection.CollUtil;

import java.util.*;

/**
 * Author: zy
 * Description: 集合工具类
 * Date: 2020/5/28
 */
public final class JnuCollUtil extends CollUtil {
    /**
     * @author: zy
     * @description: 用户将单个元素转换为list 如果元素为null，返回一个长度为0的list
     * @date: 2020/5/28 13:52
     * @param value:
     * @return List<T>:
     */
    public static <T> List<T> toList(T value){
        if(value == null){
            return new ArrayList<>();
        } else{
            //之所以不直接使用这个，是因为如果value为null，会被转换为[null]
          return CollUtil.toList(value);
        }
    }

    /**
     * @author: zy
     * @description: 用户将集合转换为list 如果集合为null，返回一个长度为0的list
     * @date: 2020/5/28 13:52
     * @param value:
     * @return List<T>:
     */
    public static <T> List<T> toList(Collection<T> value){
        if(CollUtil.isEmpty(value)){
            return new ArrayList<>();
        } else{
            //之所以不直接使用这个，是因为如果value为null，会被转换为[null]
            return new ArrayList<>(value);
        }
    }

    /**
     * @author: zy
     * @description: 用户将单个元素转换为SET 如果元素为null，返回一个长度为0的SET
     * @date: 2020/5/28 13:52
     * @param value:
     * @return Set<T>:
     */
    public static <T> Set<T> toSet(T value){
        if(value == null){
            return new HashSet<>();
        } else{
            //之所以不直接使用这个，是因为如果value为null，会被转换为[null]
            return CollUtil.newHashSet(value);
        }
    }

    /**
     * @author: zy
     * @description: 用户将集合转换为set 如果集合为null，返回一个长度为0的SET
     * @date: 2020/5/28 13:52
     * @param value:
     * @return Set<T>:
     */
    public static <T> Set<T> toSet(Collection<T> value){
        if(CollUtil.isEmpty(value)){
            return new HashSet<>();
        } else{
            return CollUtil.newHashSet(value);
        }
    }
}
