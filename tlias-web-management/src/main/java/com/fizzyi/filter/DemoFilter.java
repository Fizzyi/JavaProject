package com.fizzyi.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * Demo class
 *
 * @author Zhaohangyi
 * @time 2023/8/11
 */


public class DemoFilter implements Filter {
    @Override // 拦截到请求之后调用，调用多次
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("doFilter 拦截成功");
        System.out.println("doFilter 放行之前的操作");

        // 放行操作,如果不写，就访问不到对应的资源
        filterChain.doFilter(servletRequest, servletResponse);
        System.out.println("doFilter 放行之后的操作");
    }

    @Override // 初始化方法，只调用一次
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("init 初始化方法");
        Filter.super.init(filterConfig);
    }

    @Override // 摧毁方法，只调用一次
    public void destroy() {
        System.out.println("destroy 初始化方法");

        Filter.super.destroy();
    }
}
