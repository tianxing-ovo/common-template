package com.ltx.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * @author tianxing
 */
public interface ExportTaskMapper {

    @Select("select id, export_status, file_name from export_task where user_id = #{id}")
    List<LinkedHashMap<String, Object>> queryExportTask(@Param("id") Integer id);

    @Insert("insert into export_task(user_id, file_name) values (#{userId},#{fileName})")
    void insertExportTask(@Param("userId") Integer userId, @Param("fileName") String fileName);

    @Update("update export_task set export_status=1 where user_id=#{userId} and file_name=#{fileName}")
    void updateExportStatus(@Param("userId") Integer userId, @Param("fileName") String fileName);
}
