package com.ltx.entity.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ltx.mybatisPlus.SexEnum;
import com.ltx.valid.InsertGroup;
import com.ltx.valid.UpdateGroup;
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
    @Null(message = "新增不能指定id", groups = InsertGroup.class)
    @NotNull(message = "更新必须指定id", groups = UpdateGroup.class)
    private Integer id;

    @NotNull(groups = InsertGroup.class)
    private String name;

    @Max(20)
    private Integer age;

    private SexEnum sex;

    private List<String> password;

    @NotEmpty
    private String province;

    @NotBlank(message = "地址必须提交", groups = {InsertGroup.class, UpdateGroup.class})
    private String address;

    @Size(min = 1, max = 10)
    private String city;

    private String description;

    @JsonProperty("start_date")
    private Date startDate;

    @JsonProperty("end_date")
    private Date endDate;
}
