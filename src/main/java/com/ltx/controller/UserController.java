package com.ltx.controller;

import com.ltx.dao.UserDao;
import com.ltx.entity.request.UserRequestBody;
import common.R;
import exceptions.CustomException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class UserController {

    @Resource
    UserDao userDao;

    @PostMapping("/users")
    public R query(@RequestBody UserRequestBody requestBody) {
        return R.ok().put("userList", userDao.query(requestBody));
    }

    @GetMapping("/i18n")
    public void test() {
        throw new CustomException(404,"未找到");
    }
}
