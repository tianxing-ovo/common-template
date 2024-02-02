package com.ltx.scheduled;

/**
 * 执行任务的接口
 */
@FunctionalInterface
public interface Job {

    void run() throws InterruptedException;
}
