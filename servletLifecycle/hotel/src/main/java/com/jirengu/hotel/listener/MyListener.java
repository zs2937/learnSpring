package com.jirengu.hotel.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class MyListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("MyListener start.");
        ServletContext servletContext = sce.getServletContext();
        servletContext.setAttribute("onlineCount", 0);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("MyListener end.");
    }
}
