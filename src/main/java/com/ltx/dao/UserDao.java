package com.ltx.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ltx.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserDao extends BaseMapper<User> {

}