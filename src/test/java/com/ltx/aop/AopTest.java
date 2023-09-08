package com.ltx.aop;

import com.ltx.dao.UserDao;
import com.ltx.entity.User;
import com.ltx.mybatisPlus.SexEnum;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

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
        List<User> list = new ArrayList<>();
        for (int i = 0; i < 100000; i++) {
            User user = new User(i, "姓名" + i, i, SexEnum.MAN, "密码" + i, "省份" + i, "地址" + i, "城市" + i, "描述,他是一个非常友好的人，他很善良，他很活泼开朗啊啊啊啊啊啊啊啊啊啊啊啊啊啊阿啊啊啊啊啊阿啊啊啊啊啊阿啊啊啊啊啊阿啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊" + i);
            list.add(user);
        }
        list.forEach(user -> userDao.insert(user));
    }
}
