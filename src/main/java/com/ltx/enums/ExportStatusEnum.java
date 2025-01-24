package com.ltx.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 导出状态枚举
 *
 * @author tianxing
 */
@AllArgsConstructor
@Getter
public enum ExportStatusEnum {

    EXPORTING(0, "导出中"), EXPORT_FINISHED(1, "导出结束");

    private final int value;
    private final String desc;

    /**
     * 根据值获取描述
     *
     * @param value 值
     * @return 描述
     */
    public static String getDescByValue(int value) {
        for (ExportStatusEnum exportStatusEnum : values()) {
            if (exportStatusEnum.value == value) {
                return exportStatusEnum.desc;
            }
        }
        throw new IllegalArgumentException("Invalid value: " + value);
    }
}
