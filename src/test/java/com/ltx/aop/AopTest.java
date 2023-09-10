package com.ltx.aop;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ltx.dao.UserDao;
import com.ltx.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class AopTest {


    @Autowired
    UserService userService;

    @Autowired
    UserDao userDao;

    @Test
    void test1() {
        System.out.println(userDao.selectList(null));
    }

    @Test
    void test2() {
        Page<User> page = new Page<>(1, 10);
        Page<User> page1 = userDao.selectPage(page, null);
        System.out.println(page1.getTotal());
        System.out.println(page1.getRecords());
    }
}
