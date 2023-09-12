package com.ltx.mybatisPlus;


import com.baomidou.mybatisplus.annotation.IEnum;

/**
 * 性别枚举
 * IEnum是mybatis-plus中的接口,Java枚举 -> 数据库中的字段
 * SexEnum -> Integer
 */
public enum SexEnum implements IEnum<Integer> {

    MAN(1, "男"),
    WOMAN(2, "女"),
    UNKNOWN(3, "未知");
    private final int value;
    private final String desc;

    SexEnum(int value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    /**
     * 根据value获取desc
     */
    public static String getDescByValue(int value) {
        for (SexEnum sexEnum : SexEnum.values()) {
            if (sexEnum.value == value) {
                return sexEnum.toString();
            }
        }
        return UNKNOWN.toString();
    }

    /**
     * 根据desc获取SexEnum
     */
    public static SexEnum getEnumByDesc(String desc) {
        for (SexEnum sexEnum : SexEnum.values()) {
            if (sexEnum.toString().equals(desc)) {
                return sexEnum;
            }
        }
        return UNKNOWN;
    }


    @Override
    public Integer getValue() {
        return value;
    }

    @Override
    public String toString() {
        return desc;
    }
}
