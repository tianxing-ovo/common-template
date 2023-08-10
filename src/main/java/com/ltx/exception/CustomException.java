package com.ltx.exception;

/**
 * 自定义异常
 */
public class CustomException extends RuntimeException {
    public CustomException(String message) {
        super(message);
    }
}
