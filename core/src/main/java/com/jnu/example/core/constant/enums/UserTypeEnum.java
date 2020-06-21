package com.jnu.example.core.constant.enums;


/**
 *  @Author: zy
 *  @Date: 2020/5/10 15:46
 *  @Description:用户类型
 */
public enum UserTypeEnum implements IBaseEnum{
    //github用户
    GITHUB_USER(0,"github用户"),
    //站内用户
    SITE_USER(1,"站内用户");

    /*
     * 枚举值
     */
    private int value;

    /**
     * 描述
     */
    private String desc;

    /**
     * @Description: 构造函数
     * @Author: zy
     * @Date: 2020/5/10 15:50
     * @param value: 值
     * @param value: 描述
     * @Return :
     * @Exception :
     */
    private UserTypeEnum(int value, String desc) {
        this.value = value;
        this.desc = desc;
    }


    /**
     * @Description: 获取值
     * @Author: zy
     * @Date: 2020/5/10 15:51*
     * @Exception :
     */
    public int getValue(){
        return value;
    }

    /**
     * @author: zy
     * @description: 获取描述信息
     * @date: 2020/3/2 10:15
     * @param :
     * @return int:
     */
    @Override
    public String getDesc() {
        return this.desc;
    }
}
