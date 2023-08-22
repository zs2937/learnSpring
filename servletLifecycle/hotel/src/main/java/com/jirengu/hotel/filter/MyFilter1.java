package com.jirengu.hotel.filter;

import javax.servlet.*;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MyFilter1 extends HttpFilter {

    public MyFilter1() {
        System.out.println("MyFilter1 create.");
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("MyFilter1 init");
    }

    @Override
    public void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        System.out.println("MyFilter1 before.");
        chain.doFilter(req, res);
        System.out.println("MyFilter1 after.");
    }

    @Override
    public void destroy() {
        System.out.println("MyFilter1 destroy");
    }


}
