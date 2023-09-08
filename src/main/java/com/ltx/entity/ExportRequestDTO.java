package com.ltx.entity;

import lombok.Data;

import java.util.List;

/**
 * 封装Get请求参数
 */
@Data
public class ExportRequestDTO {
    private String fileName;
    private List<String> fields;
}
