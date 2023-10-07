package com.ltx.scheduled;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 异步执行,防止阻塞
 */
@Component
@Slf4j
public class ScheduledTask {

    /**
     * 秒 分 时 日 月 周
     */
    @SneakyThrows
    @Scheduled(cron = "* * * * * ?") // 每秒执行一次
    @Async
    public void runTask() {
        log.info("定时任务执行中...");
        Thread.sleep(3000);
    }
}
