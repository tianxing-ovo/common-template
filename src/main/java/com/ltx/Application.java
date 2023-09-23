package com.ltx;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

/**
 * proxyTargetClass = true -> 使用cglib代理(继承)
 * proxyTargetClass = false -> 使用jdk代理(实现接口)
 * exposeProxy = true -> 公开代理 -> 使用AopContext.currentProxy()获取代理对象
 */
@EnableScheduling // 启用Spring的任务调度
@EnableAspectJAutoProxy(proxyTargetClass = true, exposeProxy = true) // 启用AspectJ自动代理
@SpringBootApplication(scanBasePackages = "redis")
@EnableRedisHttpSession // 使用Redis存储session
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
