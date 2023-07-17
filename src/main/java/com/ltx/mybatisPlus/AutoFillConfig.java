package com.ltx.mybatisPlus;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.context.annotation.Configuration;
import java.util.Date;


/**
 * 自动填充配置
 */
@Configuration
public class AutoFillConfig implements MetaObjectHandler {


   
    /**
     * 插入填充
     */
    @Override
    public void insertFill(MetaObject metaObject) {
        metaObject.setValue("createTime",new Date());
        metaObject.setValue("updateTime",new Date());
    }


    /**
     * 更新填充
     */
    @Override
    public void updateFill(MetaObject metaObject) {
        metaObject.setValue("updateTime",new Date());
    }
}
