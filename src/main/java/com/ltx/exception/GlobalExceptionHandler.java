package com.ltx.exception;


import io.github.tianxingovo.common.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.HashMap;
import java.util.Map;

/**
 * 集中处理所有异常,将异常信息返回给前端(json格式)
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 处理自定义异常
     *
     * @param customException 自定义异常
     */
    @ExceptionHandler(value = CustomException.class)
    public R handleAccessDeniedException(CustomException customException) {
        String message = customException.getMessage();
        log.error("错误信息:{},异常类型:{}", message, customException.getClass());
        return R.error(customException.getCode(), message);
    }

    /**
     * 处理实体类校验异常
     *
     * @param methodArgumentNotValidException 方法参数无效异常
     */
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public R handleEntityNotValidException(MethodArgumentNotValidException methodArgumentNotValidException) {
        // key=字段名称,value=错误信息
        Map<String, Object> errorMap = new HashMap<>();
        methodArgumentNotValidException.getBindingResult().getFieldErrors().forEach(fieldError -> errorMap.put(fieldError.getField(), fieldError.getDefaultMessage()));
        log.error("错误信息:{},异常类型:{}", methodArgumentNotValidException.getMessage(), methodArgumentNotValidException.getClass());
        return R.error(HttpStatus.BAD_REQUEST.value(), "方法参数无效", errorMap);
    }

    /**
     * 处理单个参数校验异常
     *
     * @param constraintViolationException 约束冲突异常
     */
    @ExceptionHandler(value = ConstraintViolationException.class)
    public R handleSingleParameterNotValidException(ConstraintViolationException constraintViolationException) {
        // key=字段名称,value=错误信息
        Map<String, Object> errorMap = new HashMap<>();
        for (ConstraintViolation<?> constraintViolation : constraintViolationException.getConstraintViolations()) {
            errorMap.put(constraintViolation.getPropertyPath().toString().split("\\.")[1], constraintViolation.getMessage());
        }
        log.error("错误信息:{},异常类型:{}", constraintViolationException.getMessage(), constraintViolationException.getClass());
        return R.error(HttpStatus.BAD_REQUEST.value(), "方法参数无效", errorMap);
    }


    /**
     * 处理未知异常
     */
    @ExceptionHandler(value = Exception.class)
    public R handleException(Exception e) {
        log.error("错误信息:{},异常类型:{}", e.getMessage(), e.getClass());
        return R.error(203, e.getMessage());
    }
}
