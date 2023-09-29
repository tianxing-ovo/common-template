package com.ltx.controller;

import com.ltx.dao.UserDao;
import com.ltx.entity.User;
import exceptions.CustomException;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
public class UserController {

    @Resource
    UserDao userDao;

    /**
     * 查询
     */
    @GetMapping("/users/{id}")
    @Cacheable(value = "userCache", key = "#id", unless = "#result == null")
    public User query(@PathVariable Integer id) {
        return userDao.selectById(id);
    }

    /**
     * 新增
     */
    @PostMapping("/users")
    @CachePut(value = "userCache", key = "#user.id")
    public User add(@RequestBody User user) {
        userDao.add(user);
        return user;
    }

    /**
     * 删除
     */
    @PostMapping("/users/{id}")
    @CacheEvict(value = "userCache", key = "#id")
    public void delete(@PathVariable Integer id) {
        userDao.deleteById(id);
    }

    /**
     * 更新
     */
    @PutMapping("/users/{id}")
    @CacheEvict(value = "userCache", key = "#id")
    public void update(@PathVariable Integer id) {
        User user = new User().setId(id).setAge(20);
        userDao.updateById(user);
    }


    @GetMapping("/i18n")
    public void test() {
        throw new CustomException(404, "未找到");
    }
}
