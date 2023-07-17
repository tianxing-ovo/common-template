package com.ltx.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter("/*")  //匹配所有的URL
public class MyFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) {
        //初始化过滤器
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        //在请求到达目标资源之前执行的操作
        //进行请求的处理、验证、转换等操作

        //将请求传递给下一个过滤器或目标资源
        filterChain.doFilter(request, response);

        //在响应返回给客户端之前执行的操作
        //可以在这里进行响应的处理、转换等操作
    }

    @Override
    public void destroy() {
        //销毁过滤器
    }
}
