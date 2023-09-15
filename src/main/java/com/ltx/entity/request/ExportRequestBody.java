package com.ltx.entity.request;

import lombok.Data;

import java.util.List;

/**
 * 导出csv文件请求体
 */
@Data
public class ExportRequestBody {
    private String fileName;
    // 需要导出的字段
    private List<String> fields;
}
