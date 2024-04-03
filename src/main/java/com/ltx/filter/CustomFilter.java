package com.ltx.filter;

import com.ltx.entity.User;
import com.ltx.util.ThreadLocalUtil;
import lombok.SneakyThrows;

import javax.servlet.FilterConfig;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 自定义过滤器
 */
public class CustomFilter implements Filter {

    /**
     * Web应用程序启动时调用
     */
    @Override
    public void init(FilterConfig filterConfig) {
        System.out.println("init");
    }

    /**
     * 每次有请求到达时都会调用
     * 执行顺序: before filter1 -> before filter2 -> controller -> after filter2 -> after filter1
     */
    @Override
    @SneakyThrows
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        // controller处理请求前,前置逻辑顺序执行
        ThreadLocalUtil.set(new User());
        // 将请求和响应传递给下一个过滤器或目标Servlet
        filterChain.doFilter(request, response);
        // controller处理完请求并生成响应后,后置逻辑逆序执行
        ThreadLocalUtil.remove();
    }


    /**
     * Web应用程序停止运行时调用
     */
    @Override
    public void destroy() {
        System.out.println("destroy");
    }
}
