package com.jirengu.hotel.session;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AddToCartServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException
    {
        System.out.println("AddToCartServlet is call.");
        HttpSession session = req.getSession(true);
        String product = req.getParameter("product");
        Object obj = session.getAttribute("cart");
        List<String> productList;
        if (obj == null) {
            // 如果"购物车"属性不存在
            productList = new ArrayList<>();
        } else {
            // 如果"购物车"属性存在，那本次要添加的商品加入购物车
            productList = (List<String>) obj;
        }
        productList.add(product);
        session.setAttribute("cart", productList);
    }

}
