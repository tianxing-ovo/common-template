package com.ltx.interceptor;

import com.ltx.entity.User;
import io.github.tianxingovo.common.ThreadLocalUtil;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 自定义拦截器
 */
@Component
public class CustomInterceptor implements HandlerInterceptor {

    private final ThreadLocalUtil<User> threadLocalUtil = new ThreadLocalUtil<>();


    /**
     * 执行时机:Controller处理之前
     * 可以有多个Interceptor,拦截器会按照设定的Order顺序调用,当有一个拦截器在preHandle中返回false,请求就会终止
     */
    @Override
    public boolean preHandle(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull Object handler) {
        // 保存用户信息
        threadLocalUtil.set(new User());
        return true;
    }

    /**
     * 执行时机:preHandle返回true(Controller方法执行之后,视图渲染之前)
     */
    @Override
    public void postHandle(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull Object handler, ModelAndView modelAndView) {

    }

    /**
     * 执行时机:preHandle返回ture,整个请求结束之后
     */
    @Override
    public void afterCompletion(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull Object handler, Exception ex) {
        // 删除用户信息
        threadLocalUtil.remove();
    }
}
