package com.ltx.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.ltx.constant.DatasourceConstant;
import org.apache.ibatis.annotations.Select;

import java.util.LinkedHashMap;
import java.util.List;


public interface TestMapper {

    @Select("select * from test")
    @DS(DatasourceConstant.MYSQL)
    List<LinkedHashMap<String, Object>> selectFromMysql();

    @Select("select * from test")
    @DS(DatasourceConstant.POSTGRESQL)
    List<LinkedHashMap<String, Object>> selectFromPostgresql();
}
