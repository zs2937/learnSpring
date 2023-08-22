package com.jirengu.hotel;

import javax.servlet.*;
import java.io.IOException;

public class BookRoomServlet implements Servlet {

    private DatabaseConnection databaseConnection;


    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        System.out.println("BookRoomServlet init is called.");
        databaseConnection = new DatabaseConnection();
        databaseConnection.connect();
    }

    @Override
    public ServletConfig getServletConfig() {
        return null;
    }

    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        System.out.println("BookRoomServlet service is called.");
        databaseConnection.write();
    }

    @Override
    public String getServletInfo() {
        return null;
    }

    @Override
    public void destroy() {
        System.out.println("BookRoomServlet destroy is called.");
        databaseConnection.close();
    }
}
