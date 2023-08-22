package com.jirengu.hotel.session;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class CreateSessionServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException
    {
        HttpSession session = req.getSession(true);
        Object obj = session.getAttribute("count");
        if (obj == null) {
            // 如果"次数"属性不存在，设置属性值为1，表示第一次访问
            session.setAttribute("count", 1);
        } else {
            // 如果"次数"属性存在，那么将属性值+1
            int count = (int) obj;
            count = count + 1;
            session.setAttribute("count", count);
        }
    }

}
