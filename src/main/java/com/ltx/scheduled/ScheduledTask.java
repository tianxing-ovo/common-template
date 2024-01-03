package com.ltx.scheduled;

import io.github.tianxingovo.redisson.RedissonUtil;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 异步执行,防止阻塞
 */
@Component
@Slf4j
public class ScheduledTask {

    private final String cron = "0 0 0 * * ?";

    @Resource
    private RedissonUtil redissonUtil;

    /**
     * 秒 分 时 日 月 周
     */
    @SneakyThrows
    @Scheduled(cron = cron)
    @Async
    public void runTask() {
        // 分布式锁保证定时任务只执行一次
        RLock lock = redissonUtil.getLock("lock");
        try {
            boolean b = redissonUtil.tryLock(lock, 0, 10);
            if (!b) {
                return;
            }
        } finally {
            lock.unlock();
        }
        log.info("定时任务执行中...");
        Thread.sleep(3000);
    }
}
