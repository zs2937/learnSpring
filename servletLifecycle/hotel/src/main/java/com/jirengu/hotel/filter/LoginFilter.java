package com.jirengu.hotel.filter;

import com.jirengu.hotel.cookie.PasswordClass;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebFilter(filterName = "loginFilter", urlPatterns = "/cookie/*")
public class LoginFilter extends HttpFilter {

    @Override
    public void doFilter(HttpServletRequest req, HttpServletResponse resp, FilterChain chain) throws IOException, ServletException {
        // 如果请求的是登录的请求路径，那么直接放行请求
        if ("/cookie/login".equals(req.getServletPath())) {
            System.out.println("是登录的请求路径，直接放行请求");
            chain.doFilter(req, resp);
            return;
        }
        // 校验用户是否已登录
        String username = getCookieValue(req, "username");
        String password = getCookieValue(req, "password");
        if (username == null || password == null) {
            // 用户未登录，跳转到登录页面
            resp.sendRedirect("/hotel/login.html");
        } else {
            // 校验用户和密码是否正确
            if (PasswordClass.verify(username, password)) {
                // 验证通过，放行请求
                System.out.println("验证通过，放行请求");
                chain.doFilter(req, resp);
            } else {
                String path = req.getContextPath();
                Cookie cookie1 = new Cookie("username", "sdfdsf");
                cookie1.setMaxAge(0);
                cookie1.setPath(path);
                Cookie cookie2 = new Cookie("password", "sdfdsf");
                cookie2.setMaxAge(0);
                cookie1.setPath(path);
                resp.addCookie(cookie1);
                resp.addCookie(cookie2);
                resp.sendRedirect("/hotel/login.html");
            }
        }
    }

    private String getCookieValue(HttpServletRequest req, String cookieName) {
        Cookie[] cookies = req.getCookies();
        if (cookies == null) {
            return null;
        }
        for (Cookie cookie : cookies) {
            if (cookieName.equals(cookie.getName())) {
                return cookie.getValue();
            }
        }
        return null;
    }
}
