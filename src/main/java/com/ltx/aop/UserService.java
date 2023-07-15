package com.ltx.aop;

import org.springframework.aop.framework.AopContext;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    public String main(String s){
        System.out.println("Hello Aop 1");
        return s;
    }

    public void printProxy(){
        System.out.println(AopContext.currentProxy());
    }
}
