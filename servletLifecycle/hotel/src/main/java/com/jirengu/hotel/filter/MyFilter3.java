package com.jirengu.hotel.filter;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MyFilter3 extends HttpFilter {

    public MyFilter3() {
        System.out.println("MyFilter3 create.");
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("MyFilter3 init");
    }

    @Override
    public void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        System.out.println("MyFilter3 before.");
        chain.doFilter(req, res);
        System.out.println("MyFilter3 after.");
    }

    @Override
    public void destroy() {
        System.out.println("MyFilter3 destroy");
    }
}
