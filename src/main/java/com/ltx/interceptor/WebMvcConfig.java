package com.ltx.interceptor;

import com.ltx.internationalization.MyLocaleResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

/**
 * web配置
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Resource
    CustomInterceptor customInterceptor;

    /**
     * 添加拦截器
     * addPathPatterns: 拦截路径
     * excludePathPatterns: 不拦截路径
     * order小的先执行
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(customInterceptor)
                .addPathPatterns("/**").
                excludePathPatterns("/login").order(0);
    }

    /**
     * 配置请求与页面映射关系
     * 路径->页面
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/login.html").setViewName("login");
        registry.addViewController("/reg.html").setViewName("reg");
    }

    /**
     * 注册自定义的LocaleResolver
     */
    @Bean
    public LocaleResolver localeResolver() {
        return new MyLocaleResolver();
    }
}
