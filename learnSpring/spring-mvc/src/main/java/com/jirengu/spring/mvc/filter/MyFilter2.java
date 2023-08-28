package com.jirengu.spring.mvc.filter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName = "myFilter2", urlPatterns = "/account/queryAccountV2")
public class MyFilter2 extends HttpFilter {

    @Override
    public void doFilter(HttpServletRequest req, HttpServletResponse resp, FilterChain chain) throws IOException, ServletException {
        System.out.println("myFilter2 before");
        chain.doFilter(req, resp);
        System.out.println("myFilter2 after");
    }


}
