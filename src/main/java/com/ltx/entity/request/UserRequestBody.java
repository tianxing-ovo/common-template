package com.ltx.entity.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ltx.mybatisPlus.SexEnum;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.*;
import java.sql.Date;
import java.util.List;

/**
 * 查询用户请求体
 */
@Data
@Accessors(chain = true)
public class UserRequestBody {

    @Min(1)
    private Integer id;

    @NotNull
    private String name;

    @Max(20)
    private Integer age;

    private SexEnum sex;

    private List<String> password;

    @NotEmpty
    private String province;

    @NotBlank
    private String address;

    @Size(min = 1, max = 10)
    private String city;

    private String description;

    @JsonProperty("start_date")
    private Date startDate;

    @JsonProperty("end_date")
    private Date endDate;
}
