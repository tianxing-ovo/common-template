package com.ltx.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 自定义异常
 */
@Getter
@AllArgsConstructor
public class CustomException extends RuntimeException {
    private final int code;

    public CustomException(int code, String message) {
        super(message);
        this.code = code;
    }
}
