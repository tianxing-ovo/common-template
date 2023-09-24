package com.ltx.controller;

import com.ltx.dao.UserDao;
import com.ltx.entity.User;
import common.R;
import exceptions.CustomException;
import org.springframework.cache.annotation.CachePut;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

    @PostMapping("/users")
    @CachePut(value = "userCache",key = "#user.id")
    public R add(@RequestBody User user) {
        userDao.add(user);
        return R.ok("新增成功");
    }


    @GetMapping("/i18n")
    public void test() {
        throw new CustomException(404, "未找到");
    }
}
