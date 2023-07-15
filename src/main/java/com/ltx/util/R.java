package com.ltx.util;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Map;

/**
 * 通用响应对象
 */
@Data
@AllArgsConstructor
public class R {
    private Integer code;
    private String msg;
    private Map<String, Object> map;

    public R(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static R error(Integer code, String msg, Map<String, Object> map) {
        return new R(code, msg, map);
    }

    public static R error(Integer code, String msg) {
        return new R(code, msg);
    }
}
