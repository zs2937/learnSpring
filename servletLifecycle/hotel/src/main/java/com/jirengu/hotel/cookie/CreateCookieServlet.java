package com.jirengu.hotel.cookie;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CreateCookieServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException
    {
        Cookie cookie1 = new Cookie("username", "mary");
        cookie1.setMaxAge(1000);
        cookie1.setHttpOnly(true);
        Cookie cookie2 = new Cookie("age", "22");
        cookie2.setMaxAge(1000);
        String path = req.getContextPath();
        cookie2.setPath(path);
        resp.addCookie(cookie1);
        resp.addCookie(cookie2);
    }

}
