package com.ltx.threadPool;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * 线程池属性
 */
@Component
@ConfigurationProperties(prefix = "my.thread")
@Data
public class ThreadPoolConfigProperties {
    int corePoolSize; //核心线程数
    int maximumPoolSize; //最大线程数
    long keepAliveTime; //非核心线程的存活时间
    TimeUnit unit; //存活时间的时间单位
}
