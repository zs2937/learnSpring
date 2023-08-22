package com.jirengu.hotel;

import javax.servlet.*;
import java.io.IOException;

public class QueryRoomServlet implements Servlet {

    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        System.out.println("QueryRoomServlet init is called.");
    }

    @Override
    public ServletConfig getServletConfig() {
        return null;
    }

    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        System.out.println("QueryRoomServlet service is called. object is " + this);
    }

    @Override
    public String getServletInfo() {
        return null;
    }

    @Override
    public void destroy() {
        System.out.println("QueryRoomServlet destroy is called.");
    }
}
