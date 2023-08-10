package com.ltx.filter;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 过滤器配置类,用于注册过滤器
 */
@Configuration
public class FilterConfig {

    /**
     * 注册过滤器
     */
    @Bean
    public FilterRegistrationBean<MyFilter> registerMyFilter() {
        FilterRegistrationBean<MyFilter> registration = new FilterRegistrationBean<>();
        registration.setFilter(myFilter());
        registration.addUrlPatterns("/*"); //匹配所有路径
        registration.setOrder(Integer.MIN_VALUE); //设置过滤器的执行顺序
        return registration;
    }

    @Bean
    public MyFilter myFilter() {
        return new MyFilter();
    }
}
