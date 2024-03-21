package com.ltx.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.ltx.constant.DatasourceConstant;
import com.ltx.entity.Temp;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@DS(DatasourceConstant.MYSQL)
public interface TempMapper {

    List<Temp> select();

    Boolean insert(@Param("temp") Temp temp);

    @MapKey("serviceName")
    Map<String, Temp> query();
}
