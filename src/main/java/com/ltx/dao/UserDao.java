package com.ltx.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ltx.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserDao extends BaseMapper<User> {

    List<User> select();

}