package com.jirengu.hotel.session;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class QueryCartServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException
    {
        System.out.println("QueryCartServlet is call.");
        HttpSession session = req.getSession(true);
        Object obj = session.getAttribute("cart");
        if (obj == null) {
            // 如果cart属性不存在，说明还没有加入购物车
            resp.getWriter().write("nothing in cart.");
        } else {
            // 如果cart属性存在，展示购物车中的商品
            List<String> productList = (List<String>) obj;
            resp.getWriter().write("product in cart " + productList);
        }
    }
}
