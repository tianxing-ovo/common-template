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
     *
     * @param contextRefreshedEvent 上下文刷新事件
     */
    @EventListener(ContextRefreshedEvent.class)
    public void handleContextRefreshEvent(ContextRefreshedEvent contextRefreshedEvent) {
        System.out.println(contextRefreshedEvent);
        System.out.println("Application context refreshed!");
    }
}
