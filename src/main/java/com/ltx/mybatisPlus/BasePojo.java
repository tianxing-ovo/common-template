package com.ltx.mybatisPlus;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

/**
 * Plain Old Java Object:简单的Java对象
 */
@Data
public abstract class BasePojo {
    
    @TableField(fill = FieldFill.INSERT)
    private String createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private String updateTime;
}
