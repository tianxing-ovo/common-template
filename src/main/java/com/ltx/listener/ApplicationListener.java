package com.ltx.listener;

import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * 应用程序监听器
 */
@Component
public class ApplicationListener {


    /**
     * ApplicationContext被初始化或刷新时触发
     */
    @EventListener(ContextRefreshedEvent.class)
    public void handleContextRefreshEvent() {
        System.out.println("Application context refreshed!");
    }
}
