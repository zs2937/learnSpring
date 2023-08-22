package com.jirengu.hotel;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Map;

public class UserRegisterServlet extends HttpServlet {

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException
    {
        Map<String, String[]> parameterMap = req.getParameterMap();
        Enumeration<String> parameterNames = req.getParameterNames();
        String[] parameterUserName = req.getParameterValues("username");
        String[] parameterAge = req.getParameterValues("age");
        String[] parameterHobby = req.getParameterValues("hobby");
        String username = req.getParameter("username");
        String age = req.getParameter("age");
        String hobby = req.getParameter("hobby");
        System.out.println("doPost is called");
    }

}
