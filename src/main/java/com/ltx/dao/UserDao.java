package com.ltx.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ltx.entity.SexCount;
import com.ltx.entity.User;
import com.ltx.entity.request.UserRequestBody;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserDao extends BaseMapper<User> {

    List<User> select();

    List<User> query(UserRequestBody requestBody);

    void add(@Param("user") User user);

    List<SexCount> queryGroupBy(@Param("groupField") String groupField);
}