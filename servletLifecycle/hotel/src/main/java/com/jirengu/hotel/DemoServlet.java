package com.jirengu.hotel;

import javax.servlet.*;
import java.io.IOException;

public class DemoServlet extends MyGenericServlet {

    @Override
    public void init() {
        System.out.println("初始化执行");
    }

    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        System.out.println("此处编写业务逻辑.");
    }

    @Override
    public void destroy() {
        System.out.println("destroy 方法执行.");
    }

}
