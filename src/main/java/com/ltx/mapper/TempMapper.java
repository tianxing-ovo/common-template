package com.ltx.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.ltx.constant.DatasourceConstant;
import com.ltx.entity.Temp;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@DS(DatasourceConstant.MYSQL)
public interface TempMapper {

    List<Temp> select();

    Boolean insert(@Param("temp") Temp temp);
}
