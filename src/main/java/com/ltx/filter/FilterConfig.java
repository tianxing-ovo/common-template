package com.ltx.filter;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

/**
 * 过滤器配置类,用于注册过滤器
 */
@Configuration
public class FilterConfig {

    @Resource
    private CustomFilter customFilter;

    /**
     * 注册自定义过滤器
     */
    @Bean
    public FilterRegistrationBean<CustomFilter> registerCustomFilter() {
        FilterRegistrationBean<CustomFilter> registration = new FilterRegistrationBean<>();
        registration.setFilter(customFilter);
        // 匹配所有路径
        registration.addUrlPatterns("/*");
        // 设置过滤器的执行顺序
        registration.setOrder(Integer.MIN_VALUE);
        return registration;
    }
}
