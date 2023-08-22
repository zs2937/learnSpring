package com.jirengu.hotel.cookie;

import javax.servlet.ServletContext;
import javax.servlet.http.*;
import java.io.IOException;

public class QueryOrderServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException
    {
        HttpSession session = req.getSession(true);
        session.setMaxInactiveInterval(10);
        ServletContext servletContext = getServletContext();
        System.out.println(servletContext);
        // 校验用户是否已登录
        String username = getCookieValue(req, "username");
        // 执行业务逻辑
        System.out.println(username + " is querying order.");
        resp.getWriter().write(username + " is querying order.");
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
