package com.jirengu.hotel;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class PostServlet extends HttpServlet {

//    @Override
//    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
//        System.out.println("PostServlet service is call.");
//        System.out.println(req.getParameter("username"));
//        System.out.println(req.getParameter("password"));
//        res.getWriter().write("PostServlet success.");
//    }
    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException
    {
        System.out.println("PostServlet service is call.");
        String contentType = req.getContentType();
        System.out.println(contentType);
        System.out.println(req.getParameter("username"));
        System.out.println(req.getParameter("password"));
        // 第一步：从 请求的 body 中读取 json 数据
        BufferedReader streamReader = new BufferedReader(new InputStreamReader(
                req.getInputStream(), "UTF-8"
        ));
        StringBuilder sb = new StringBuilder();
        String inputStr;
        while ((inputStr = streamReader.readLine()) != null) {
            sb.append(inputStr);
        }
        System.out.println(sb);
        // 第二步：把 json 数据转成对象
        ObjectMapper objectMapper = new ObjectMapper();
        UserPassword userPassword = objectMapper.readValue(sb.toString(), UserPassword.class);
        // 之后我们用 SpringMVC 的时候，这两步是 SpringMVC 框架完成的，我们程序员接受前端请求直接就能拿到对象
        System.out.println(userPassword);
        // 将对象转成 json
        String json = objectMapper.writeValueAsString(userPassword);
        System.out.println(json);
        resp.getWriter().write(json);
    }

}
