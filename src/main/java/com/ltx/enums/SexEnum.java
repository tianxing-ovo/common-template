package com.ltx.enums;


import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 性别枚举
 *
 * @author tianxing
 */
@AllArgsConstructor
@Getter
public enum SexEnum {
    MAN(1, "男"), WOMAN(2, "女"), UNKNOWN(3, "未知");

    @EnumValue
    private final int value;
    @JsonValue
    private final String desc;

    /**
     * 根据值获取描述
     *
     * @param value 值
     * @return 描述
     */
    public static String getDescByValue(int value) {
        for (SexEnum sexEnum : values()) {
            if (sexEnum.value == value) {
                return sexEnum.desc;
            }
        }
        throw new IllegalArgumentException("Invalid value: " + value);
    }

    /**
     * 根据描述获取性别枚举
     *
     * @param desc 描述
     * @return 性别枚举
     */
    public static SexEnum getEnumByDesc(String desc) {
        for (SexEnum sexEnum : values()) {
            if (sexEnum.desc.equals(desc)) {
                return sexEnum;
            }
        }
        throw new IllegalArgumentException("Invalid desc: " + desc);
    }

    @Override
    public String toString() {
        return desc;
    }
}
