package com.ltx.threadPool;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;


/**
 * 线程池配置
 */
@Configuration
public class ThreadPoolConfig {

    @Bean
    public ThreadPoolExecutor threadPoolExecutor(ThreadPoolConfigProperties threadPoolConfigProperties) {
        return new ThreadPoolExecutor(
                threadPoolConfigProperties.corePoolSize,
                threadPoolConfigProperties.maximumPoolSize,
                threadPoolConfigProperties.keepAliveTime,
                threadPoolConfigProperties.unit,
                new LinkedBlockingQueue<>(100000), //存储待执行任务的阻塞队列
                Executors.defaultThreadFactory(), //创建新线程的工厂
                new ThreadPoolExecutor.AbortPolicy()); //拒绝策略
    }
}
