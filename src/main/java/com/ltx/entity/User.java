package com.ltx.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import com.ltx.easyExcel.SexEnumConverter;
import com.ltx.mybatisPlus.SexEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @ExcelProperty(value = "id")
    private Integer id;
    @ExcelProperty(value = "姓名")
    private String name;
    @ExcelProperty(value = "年龄")
    private Integer age;
    @ExcelProperty(value = "性别", converter = SexEnumConverter.class)
    private SexEnum sex;
    @ExcelProperty(value = "密码")
    private String password;
    @ExcelProperty(value = "省份")
    private String province;
    @ExcelProperty(value = "地址")
    private String address;
    @ExcelProperty(value = "城市")
    private String city;
    @ExcelProperty(value = "描述")
    private String description;

}
