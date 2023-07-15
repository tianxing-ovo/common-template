package com.ltx.aop;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class AopTest {


    @Autowired
    UserService userService;

    @Test
    void test1() {
        System.out.println(userService.main("Hello Aop 2"));
    }

    @Test
    void test2() {
        userService.printProxy(); //打印代理对象
    }

}
