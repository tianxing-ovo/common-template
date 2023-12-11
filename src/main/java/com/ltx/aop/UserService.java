package com.ltx.aop;

import org.springframework.aop.framework.AopContext;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    public String main(String s) {
        System.out.println("Hello Aop");
        printProxy();
        return s;
    }

    /**
     * 获取代理对象
     */
    public void printProxy() {
        System.out.println("代理对象: " + AopContext.currentProxy());
    }
}
