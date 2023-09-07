package com.ltx.exception;


import common.R;
import exceptions.CustomException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

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
     */
    @ExceptionHandler(value = CustomException.class)
    public R handleAccessDeniedException(CustomException e) {
        log.error("错误信息:{},异常类型:{}", e.getMessage(), e.getClass());
        return R.error(e.getCode(), e.getMessage());
    }

    /**
     * 处理数据校验异常
     * value初始化为Exception.class，通过测试得出真正的异常类型
     */
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public R handleValidException(MethodArgumentNotValidException e) {
        Map<String, Object> map = new HashMap<>();
        BindingResult result = e.getBindingResult();
        result.getFieldErrors().forEach(item -> {
            String message = item.getDefaultMessage(); //获取错误信息
            String field = item.getField(); //获取错误字段
            map.put(field, message);
        });
        log.error("错误信息:{},异常类型:{}", e.getMessage(), e.getClass());
        return R.error(202, "方法参数无效", map);
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
