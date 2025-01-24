package com.ltx.aop;

import com.ltx.entity.po.User;
import org.springframework.aop.framework.AopContext;
import org.springframework.stereotype.Service;

/**
 * @author tianxing
 */
@Service
public class UserService {

    public User hello(String name) {
        System.out.println("Hello Aop");
        // 获取代理对象
        System.out.println("代理对象: " + AopContext.currentProxy());
        return new User().setName(name);
    }
}
