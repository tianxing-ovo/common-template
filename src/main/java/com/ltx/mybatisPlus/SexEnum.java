package com.ltx.mybatisPlus;


import com.baomidou.mybatisplus.annotation.IEnum;

/**
 * 性别枚举
 * IEnum是mybatis-plus中的接口，用于将Java枚举与数据库中的字段进行映射
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

    @Override
    public Integer getValue() {
        return value;
    }

    @Override
    public String toString() {
        return desc;
    }
}
