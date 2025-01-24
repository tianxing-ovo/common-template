package com.ltx;

import org.apache.catalina.util.ServerInfo;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.stereotype.Indexed;

import javax.annotation.PostConstruct;

/**
 * proxyTargetClass = true -> 使用cglib代理(继承)
 * proxyTargetClass = false -> 使用jdk代理(实现接口)
 * exposeProxy = true -> 公开代理 -> 使用AopContext.currentProxy()获取代理对象
 *
 * @author tianxing
 */
@EnableScheduling // 启用Spring定时任务
@EnableAspectJAutoProxy(proxyTargetClass = true, exposeProxy = true) // 启用AspectJ自动代理
@SpringBootApplication
@EnableRedisHttpSession // 使用Redis存储session
@EnableAsync  // 启用异步方法调用
@EnableRabbit
@MapperScan("com.ltx.mapper")
@Indexed
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @PostConstruct
    public void printTomcatVersion() {
        System.out.println("Tomcat Version: " + ServerInfo.getServerInfo());
    }
}
