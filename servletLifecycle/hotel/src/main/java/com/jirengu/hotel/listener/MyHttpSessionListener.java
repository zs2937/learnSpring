package com.jirengu.hotel.listener;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class MyHttpSessionListener implements HttpSessionListener {

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        ServletContext servletContext = se.getSession().getServletContext();
        int onlineCount = (int) servletContext.getAttribute("onlineCount");
        servletContext.setAttribute("onlineCount", onlineCount + 1);
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        ServletContext servletContext = se.getSession().getServletContext();
        int onlineCount = (int) servletContext.getAttribute("onlineCount");
        servletContext.setAttribute("onlineCount", onlineCount - 1);
    }
}
