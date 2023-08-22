package com.jirengu.hotel;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "queryOnlineCountServlet", urlPatterns = "/queryOnlineCount")
public class QueryOnlineCountServlet extends HttpServlet {


    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException
    {
        ServletContext servletContext = req.getServletContext();
        int onlineCount = (int)servletContext.getAttribute("onlineCount");
        resp.getWriter().write("online count = " + onlineCount);
    }
}
