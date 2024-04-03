package com.ltx.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.ltx.annotation.SensitiveInfo;
import com.ltx.easyExcel.converter.ListConverter;
import com.ltx.easyExcel.converter.SexEnumConverter;
import com.ltx.mybatisPlus.SexEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@SensitiveInfo({"province", "address", "password", "city"})
public class User {
    @ExcelProperty(value = "id")
    private Integer id;

    @ExcelProperty(value = "姓名")
    private String name;

    @ExcelProperty(value = "年龄")
    private Integer age;

    @ExcelProperty(value = "性别", converter = SexEnumConverter.class)
    private SexEnum sex;

    @ExcelProperty(value = "密码", converter = ListConverter.class)
    private List<String> password;

    @ExcelProperty(value = "省份")
    private String province;

    @ExcelProperty(value = "地址")
    private String address;

    @ExcelProperty(value = "城市")
    private String city;

    @ExcelProperty(value = "描述")
    private String description;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @TableField("my_datetime")
    private Timestamp datetime;

    @TableField("my_date")
    private Date date;

    private String role;
}
