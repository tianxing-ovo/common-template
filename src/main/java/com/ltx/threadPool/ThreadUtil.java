package com.ltx.threadPool;

import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.concurrent.Callable;
import java.util.concurrent.ThreadPoolExecutor;

@Component
public class ThreadUtil {

    @Resource
    ThreadPoolExecutor executor;

    /**
     * 无返回值
     */
    public void execute(Runnable runnable) {
        executor.execute(runnable);
    }

    /**
     * 有返回值
     */
    @SneakyThrows
    public <T> T submit(Callable<T> callable) {
        return executor.submit(callable).get();
    }
}
