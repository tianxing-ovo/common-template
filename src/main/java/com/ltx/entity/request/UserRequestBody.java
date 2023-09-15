package com.ltx.entity.request;

import com.ltx.mybatisPlus.SexEnum;
import lombok.Data;

import java.util.List;

/**
 * 查询用户请求体
 */
@Data
public class UserRequestBody {

    private Integer id;

    private String name;

    private Integer age;

    private SexEnum sex;

    private List<String> password;

    private String province;

    private String address;

    private String city;

    private String description;
}
