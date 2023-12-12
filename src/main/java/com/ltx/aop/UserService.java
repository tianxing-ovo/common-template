package com.ltx.aop;

import com.ltx.entity.User;
import org.springframework.aop.framework.AopContext;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    public User main(String name) {
        System.out.println("Hello Aop");
        printProxy();
        return new User().setName(name);
    }

    /**
     * 获取代理对象
     */
    public void printProxy() {
        System.out.println("代理对象: " + AopContext.currentProxy());
    }
}
