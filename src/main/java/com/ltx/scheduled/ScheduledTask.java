package com.ltx.scheduled;


import com.ltx.util.RedissonUtil;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 定时任务
 *
 * @author tianxing
 */
@Component
@Slf4j
public class ScheduledTask {

    private final String cron = "0 0 * * * ?";

    @Resource
    private RedissonUtil redissonUtil;

    /**
     * 通用的定时任务代码
     *
     * @param runnable  具体的定时任务代码逻辑
     * @param name      分布式锁的名称
     * @param waitTime  尝试获取锁时的等待时间(单位为秒) -> 指定的等待时间内未能获取到锁 -> 返回false
     * @param leaseTime 获取锁成功后的持有时间(单位为秒)
     */
    public void runTask(Runnable runnable, String name, long waitTime, long leaseTime) {
        // 分布式锁保证定时任务只执行一次
        RLock lock = redissonUtil.getLock(name);
        boolean b = false;
        try {
            b = redissonUtil.tryLock(lock, waitTime, leaseTime);
            if (!b) {
                return;
            }
            // 执行具体的定时任务代码逻辑
            runnable.run();
        } finally {
            // 释放锁
            if (b && lock.isHeldByCurrentThread()) {
                redissonUtil.unlock(lock);
            }
        }
    }

    /**
     * 秒 分 时 日 月 周
     */
    @Scheduled(cron = cron)
    @Async
    public void runTask() {
        runTask(() -> {
            log.info("定时任务执行中...");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }, "lock", 1, 1);
    }
}
