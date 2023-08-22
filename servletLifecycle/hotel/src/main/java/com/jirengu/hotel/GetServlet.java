package com.jirengu.hotel;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class GetServlet extends HttpServlet {
//    @Override
//    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
//        System.out.println("GetServlet service is call.");
//        System.out.println(req.getParameter("username"));
//        System.out.println(req.getParameter("password"));
//        res.getWriter().write("GetServlet success.");
//    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException
    {
        System.out.println("GetServlet service is call.");
        System.out.println(req);
        System.out.println(req.getParameter("username"));
        System.out.println(req.getParameter("password"));
        resp.getWriter().write("GetServlet success.");
    }

}
