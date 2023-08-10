package com.ltx.interceptor;

import com.ltx.entity.User;
import org.springframework.context.annotation.Configuration;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 请求拦截器
 */
@Component
public class MyInterceptor implements HandlerInterceptor {

    public static ThreadLocal<User> threadLocal = new ThreadLocal<>(); //保存用户信息

    /**
     * 执行时机:Controller处理之前
     * 可以有多个Interceptor，拦截器会按照设定的Order顺序调用，当有一个拦截器在preHandle中返回false，请求就会终止
     */
    @Override
    public boolean preHandle(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull Object handler) {

        return true;
    }

    /**
     * 执行时机:preHandle返回true(Controller方法执行之后，视图渲染之前)
     */
    @Override
    public void postHandle(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull Object handler, ModelAndView modelAndView) {

    }

    /**
     * 执行时机:preHandle返回ture，整个请求结束之后
     */
    @Override
    public void afterCompletion(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull Object handler, Exception ex) {

    }

    @Configuration
    static class MvcConfig implements WebMvcConfigurer {
        /**
         * 添加拦截器
         * addPathPatterns:拦截路径
         * excludePathPatterns:不拦截路径
         */
        @Override
        public void addInterceptors(InterceptorRegistry registry) {
            registry.addInterceptor(new MyInterceptor())
                    .addPathPatterns("/**").
                    excludePathPatterns("/login");
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
    }
}
