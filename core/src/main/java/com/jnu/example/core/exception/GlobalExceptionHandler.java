package com.jnu.example.core.exception;


import com.jnu.example.core.constant.enums.ResponseCode;
import com.jnu.example.core.pojo.CustomizedResponseEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.converter.HttpMessageConversionException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.annotation.MethodArgumentConversionNotSupportedException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.Set;


/**
 *  @Author: zy
 *  @Date: 2020/4/15 0:40
 *  @Description:异常统一处理
 */
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    /**
     * @param req
     * @param e:
     * @return ResponseEntity:
     * @author: zy
     * @description: 系统异常
     * @date: 2020/2/17 13:02
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public CustomizedResponseEntity errorHandler(HttpServletRequest req, Exception e) {
        log.error(e.getMessage(), e);
        String result;

        //违反约束, javax扩展定义  https://www.cnblogs.com/jpfss/p/11451135.html
        if (e.getClass() == ConstraintViolationException.class) {
            Set<ConstraintViolation<?>> violations = ((ConstraintViolationException) e).getConstraintViolations();
            StringBuilder sb = new StringBuilder();
            for (ConstraintViolation<?> violation : violations) {
                sb.append(violation.getMessage()).append("\r\n");
            }
            return CustomizedResponseEntity.fail(ResponseCode.PARAM_NOT_VALID.setMessage(sb.toString()));
        }

        //绑定失败, 如表单对象参数违反约束
        if (e.getClass() == BindException.class) {
            result = buildMessage(((BindException) e).getBindingResult());
            return CustomizedResponseEntity.fail(ResponseCode.PARAM_NOT_VALID.setMessage(result));
        }

        //参数无效, 如JSON请求参数违反约束
        if (e.getClass() == MethodArgumentNotValidException.class) {
            result = buildMessage(((MethodArgumentNotValidException) e).getBindingResult());
            return CustomizedResponseEntity.fail(ResponseCode.PARAM_NOT_VALID.setMessage(result));
        }

        //参数类型不匹配
        if (e.getClass() == MethodArgumentTypeMismatchException.class) {
            MethodArgumentTypeMismatchException exception = (MethodArgumentTypeMismatchException) e;
            result = "请求参数" + exception.getName() + "数据类型为" + exception.getRequiredType();
            return CustomizedResponseEntity.fail(ResponseCode.PARAM_TYPE_ERROR.setMessage(result));
        }

        if (e.getClass() == MethodArgumentConversionNotSupportedException.class) {
            MethodArgumentConversionNotSupportedException exception = (MethodArgumentConversionNotSupportedException) e;
            result = "请求参数" + exception.getName() + "数据类型为" + exception.getRequiredType();
            return CustomizedResponseEntity.fail(ResponseCode.PARAM_TYPE_ERROR.setMessage(result));
        }

        //上传文件需要指定Content-Type: multipart/form-data
        if (e instanceof MultipartException) {
            result = "上传文件需要指定Content-Type:为multipart/form-data";
            if (e.getClass() == MaxUploadSizeExceededException.class) {
                result = "上传文件大小超过服务器设置的最大限制";
            }
            return CustomizedResponseEntity.fail(ResponseCode.FILE_UPLOAD_FAIL.setMessage(result));
        }

        if (e.getClass() == MissingServletRequestPartException.class) {
            result = "文件上传必须指定文件参数" + ((MissingServletRequestPartException) e).getRequestPartName();
            return CustomizedResponseEntity.fail(ResponseCode.FILE_UPLOAD_FAIL.setMessage(result));
        }
        //content-type类型错误 请求不支持
        if (e.getClass() == HttpMediaTypeNotSupportedException.class) {
            result = "不支持content-type请求格式" + ((HttpMediaTypeNotSupportedException) e).getContentType();
            return CustomizedResponseEntity.fail(ResponseCode.HTTP_CONTENT_TYPE_ERROR.setMessage(result));
        }

        //无效的content-type类型
        if (e.getClass() == HttpMediaTypeNotAcceptableException.class) {
            result = "无效的content-type请求格式";
            return CustomizedResponseEntity.fail(ResponseCode.HTTP_CONTENT_TYPE_ERROR.setMessage(result));
        }

        if (e.getClass() == HttpRequestMethodNotSupportedException.class) {
            result = "不支持请求方法" + ((HttpRequestMethodNotSupportedException) e).getMethod();
            return CustomizedResponseEntity.fail(ResponseCode.HTTP_REQUEST_METHOD_NOT_SUPPORTED.setMessage(result));
        }

        //请求参数缺失
        if (e.getClass() == MissingServletRequestParameterException.class) {
            result = "必须传入请求参数" + ((MissingServletRequestParameterException) e).getParameterName();
            return CustomizedResponseEntity.fail(ResponseCode.RRQUEST_PARAMETER_MISSING.setMessage(result));
        }

        //路径参数缺失
        if (e.getClass() == MissingPathVariableException.class) {
            result = "必须传入路径参数" + ((MissingPathVariableException) e).getVariableName();
            return CustomizedResponseEntity.fail(ResponseCode.PATH_VARIABLE_MISSING.setMessage(result));
        }

        //JSON解析异常  如将json字符串反序列化为日期，格式错误等
        if (e instanceof HttpMessageConversionException) {
            result = "JSON解析异常,请检查JSON对象格式";
            return CustomizedResponseEntity.fail(ResponseCode.JSON_PARSE_ERROR.setMessage(result));
        }

        //业务错误
        if (e.getClass() == BusinessException.class) {
            BusinessException exception = (BusinessException) e;
            return CustomizedResponseEntity.fail(exception.getResponseCode());
        }

        return CustomizedResponseEntity.fail(ResponseCode.FAIL);
    }


    /**
     * @param result:
     * @return String:
     * @author: zy
     * @description: 构建参数校验错误消息
     * @date: 2020/2/17 18:03
     */
    private String buildMessage(BindingResult result) {
        StringBuilder sb = new StringBuilder();
        List<ObjectError> errors = result.getAllErrors();
        for (ObjectError error : errors) {
            if (error instanceof FieldError) {
                FieldError fieldError = (FieldError) error;
                String fieldErrMsg = fieldError.getDefaultMessage();
                sb.append(fieldErrMsg).append(";");
            }
        }
        return sb.toString();
    }
}
