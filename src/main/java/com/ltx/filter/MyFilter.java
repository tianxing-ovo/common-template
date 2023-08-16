package com.ltx.filter;

import javax.servlet.FilterConfig;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 自定义过滤器
 */
public class MyFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) {
        //初始化过滤器
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        //通过
        filterChain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        //销毁过滤器
    }
}
