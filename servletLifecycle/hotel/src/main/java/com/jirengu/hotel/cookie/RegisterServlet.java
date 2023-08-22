package com.jirengu.hotel.cookie;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RegisterServlet extends HttpServlet {

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException
    {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        PasswordClass.registerUser(username, password);
        resp.getWriter().write("register success.");
    }


}
