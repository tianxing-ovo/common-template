package com.ltx.scheduled;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class Task {

    /**
     * 秒 分 时 日 月 周
     */
    @Scheduled(cron = "0/5 * * * * ?") //任务间隔5秒执行一次
    public void runTask() {
        //执行定时任务的逻辑
        System.out.println("定时任务执行中...");
    }
}
