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

public class AbcFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("AbcFilter 拦截成功");
        System.out.println("AbcFilter 放行之前的操作");
        // 放行操作,如果不写，就访问不到对应的资源
        filterChain.doFilter(servletRequest, servletResponse);
        System.out.println("AbcFilter 放行之后的操作");
    }
}
