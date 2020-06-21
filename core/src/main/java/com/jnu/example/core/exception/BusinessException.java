package com.jnu.example.core.exception;

import com.jnu.example.core.constant.enums.ResponseCode;
import lombok.Data;

/**
 *  @Author: zy
 *  @Date: 2020/4/15 0:41
 *  @Description: 业务出错统一处理异常
 */
@Data
public class BusinessException extends RuntimeException {

    /**
     * 状态码
     */
    private final ResponseCode responseCode;

    /**
     * @author: zy
     * @description: 构造函数
     * @date: 2020/3/1 20:32
     * @return :
     */
    public BusinessException(ResponseCode responseCode) {
        super(responseCode == null ? ResponseCode.FAIL.getMessage():responseCode.getMessage());
        this.responseCode = responseCode == null ? ResponseCode.FAIL:responseCode;
    }
}
