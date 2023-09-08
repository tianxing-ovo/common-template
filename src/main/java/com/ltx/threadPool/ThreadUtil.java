package com.ltx.threadPool;

import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ThreadPoolExecutor;

@Component
public class ThreadUtil {

    @Resource
    ThreadPoolExecutor executor;

    /**
     * 无返回值
     */
    public void execute() {
        executor.execute(() -> System.out.println(1));
    }

    /**
     * 有返回值
     */
    public Object submit() {
        try {
            return executor.submit(() -> 1).get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return null;
    }
}
