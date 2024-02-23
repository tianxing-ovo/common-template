package com.ltx.juc;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * 线程池属性
 */
@Component
@ConfigurationProperties(prefix = "thread-pool")
@Data
public class ThreadPoolConfigProperties {
    private int corePoolSize; // 核心线程数
    private int maximumPoolSize; // 最大线程数
    private long keepAliveTime; // 非核心线程的存活时间
    private TimeUnit unit; // 存活时间的时间单位
}
