package com.ltx.controller;

import com.ltx.dao.UserDao;
import com.ltx.entity.User;
import common.R;
import exceptions.CustomException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class UserController {

    @Resource
    UserDao userDao;

    @GetMapping("/users")
    public R query() {
        List<User> userList = userDao.select();
        return R.ok().put("userList", userList);
    }

    @GetMapping("/i18n")
    public void test() {
        throw new CustomException(404, "未找到");
    }
}
