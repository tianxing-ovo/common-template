package com.ltx.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

/**
 * @author tianxing
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SexCount {
    private Object field;
    private Integer count;
}
