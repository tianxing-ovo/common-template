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
 * Web配置
 *
 * @author tianxing
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Resource
    private CustomInterceptor customInterceptor;

    /**
     * 添加拦截器
     * addPathPatterns: 拦截路径
     * excludePathPatterns: 不拦截路径
     * order小的先执行
     *
     * @param registry 拦截器注册对象
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(customInterceptor)
                .addPathPatterns("/**").
                excludePathPatterns("/login").order(0);
    }

    /**
     * 添加视图控制器
     * URL路径 -> HTML页面
     * 需要结合模板引擎thymeleaf使用
     *
     * @param registry 视图控制器注册对象
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        // index.html是默认页面 -> 可以不配置 -> 放到static和templates中即可直接访问
        registry.addViewController("/").setViewName("index");
        registry.addViewController("/login").setViewName("login");
    }


    /**
     * 注册自定义的LocaleResolver
     *
     * @return
     */
    @Bean
    public LocaleResolver localeResolver() {
        return new MyLocaleResolver();
    }
}
