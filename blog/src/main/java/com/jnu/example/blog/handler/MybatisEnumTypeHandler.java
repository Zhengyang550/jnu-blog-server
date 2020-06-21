package com.jnu.example.blog.handler;

import com.jnu.example.core.constant.enums.IBaseEnum;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Author: zy
 * Description: 自定义Mybatis枚举类型转换器  用于将实体类中枚举类型转换为int保存到数据库
 * https://www.cnblogs.com/jackspan/p/10582948.html
 * https://blog.csdn.net/qq_26440803/article/details/83451221?utm_medium=distribute.pc_relevant.none-task-blog-BlogCommendFromMachineLearnPai2-2.nonecase&depth_1-utm_source=distribute.pc_relevant.none-task-blog-BlogCommendFromMachineLearnPai2-2.nonecase
 * Date: 2020/5/21
 */

@MappedTypes(value = IBaseEnum.class)
public class MybatisEnumTypeHandler<E extends IBaseEnum> extends BaseTypeHandler<E>{

    private Class<E> enumType;

    /*
     * 构造器
     */
    public MybatisEnumTypeHandler(Class<E> type) {
        if (type == null) {
            throw new IllegalArgumentException("Type argument cannot be null.");
        }
        this.enumType = type;
    }

    /*
     * 用于定义设置参数时，该如何把Java枚举类型的参数转换为对应的数据库类型
     */
    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, IBaseEnum parameter, JdbcType jdbcType) throws SQLException {
        ps.setInt(i,parameter.getValue());
    }

    /*
     * 用于定义通过字段名称获取字段数据时，如何把数据库类型转换为对应的Java枚举类型
     */
    @Override
    public E getNullableResult(ResultSet rs, String columnName) throws SQLException {
        if(rs.getObject(columnName) == null){
            return null;
        }
        int value = rs.getInt(columnName);
        return rs.wasNull()  ? null : valueOf(this.enumType, value);
    }

    /*
     * /用于定义通过字段索引获取字段数据时，如何把数据库类型转换为对应的Java枚举类型
     */
    @Override
    public E getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        if(rs.getObject(columnIndex) == null){
            return null;
        }
        int value = rs.getInt(columnIndex);
        return rs.wasNull()  ? null : valueOf(this.enumType, value);
    }

    /*
     * 用于定义调用存储过程后，如何把数据库类型转换为对应的Java类型
     */
    @Override
    public E getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        if(cs.getObject(columnIndex) == null){
            return null;
        }
        int value = cs.getInt(columnIndex);
        return cs.wasNull() ? null : valueOf(this.enumType,value);
    }

    /*
     * 将value装换为枚举类型
     */
    private E valueOf(Class<E> enumClass,int value){
        try {
            E[] enumConstants = enumClass.getEnumConstants();
            for (E e : enumConstants) {
                if (e.getValue() == value)
                    return e;
            }
            return null;
        } catch (Exception ex) {
            throw new IllegalArgumentException("Cannot convert " + value + " to " + enumClass.getSimpleName() + " by code value.", ex);
        }
    }
}
