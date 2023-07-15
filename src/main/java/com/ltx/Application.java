package com.ltx;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy(proxyTargetClass = true, exposeProxy = true) //启用AspectJ自动代理
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
