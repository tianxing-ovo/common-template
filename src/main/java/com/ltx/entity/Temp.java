package com.ltx.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Temp {
    private Integer id;
    private String serviceName;
    private String riskType;
    private List<String> person;
}
