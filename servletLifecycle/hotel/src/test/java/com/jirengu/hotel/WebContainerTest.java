package com.jirengu.hotel;

import org.junit.Test;

import javax.servlet.ServletException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class WebContainerTest {

    private List<ServletConfiguration> buildConfiguration() {
        // 不配置 loadOnStartup
        ServletConfiguration bookRoomServletConfiguration = new ServletConfiguration(
                "/bookRoom",
                "com.jirengu.hotel.BookRoomServlet",
                null
        );
        // 配置 loadOnStartup，但是小于0
        ServletConfiguration queryRoomServletConfiguration = new ServletConfiguration(
                "/queryRoom",
                "com.jirengu.hotel.QueryRoomServlet",
                -1
        );
        // 配置 loadOnStartup = 1
        ServletConfiguration checkInServletConfiguration = new ServletConfiguration(
                "/checkIn",
                "com.jirengu.hotel.CheckInServlet",
                1
        );
        // 配置 loadOnStartup = 0
        ServletConfiguration checkOutServletConfiguration = new ServletConfiguration(
                "/checkOut",
                "com.jirengu.hotel.CheckOutServlet",
                0
        );
        // 不配置 loadOnStartup
        ServletConfiguration endServletConfiguration = new ServletConfiguration(
                "/end",
                "com.jirengu.hotel.EndServlet",
                null
        );
        List<ServletConfiguration> servletConfigurationList = new ArrayList<>();
        servletConfigurationList.add(bookRoomServletConfiguration);
        servletConfigurationList.add(queryRoomServletConfiguration);
        servletConfigurationList.add(checkInServletConfiguration);
        servletConfigurationList.add(checkOutServletConfiguration);
        servletConfigurationList.add(endServletConfiguration);
        return servletConfigurationList;
    }

    @Test
    public void testWebContainer() throws ServletException, ClassNotFoundException, InstantiationException, IllegalAccessException, IOException {
        // 1.获取配置信息
        List<ServletConfiguration> configurationList = buildConfiguration();
        // 2.创建 web 容器
        WebContainer webContainer = new WebContainer();
        // 3.启动 web 容器
        webContainer.start(configurationList);
        // 4.用户发起请求
        // 4.1.查询空房
        webContainer.doService("/queryRoom", null, null);
        // 4.2.查询预定房间
        webContainer.doService("/bookRoom", null, null);
        // 4.3.登记入住
        webContainer.doService("/checkIn", null, null);
        // 4.4.退房
        webContainer.doService("/checkOut", null, null);
        // 5.关闭容器
        webContainer.close();
        // 预期结果
        // CheckOutServlet init called.
        // CheckInServlet init is called.
        // QueryRoomServlet init is called.
        // QueryRoomServlet service is called. object is
        // BookRoomServlet init is called.
        // 连接数据库.
        // BookRoomServlet service is called.
        // 写数据库.
        // CheckInServlet service is called.
        // CheckOutServlet service called.
        // 调用所有创建出来的 Servlet 对象的 destroy 方法并打印内容，"EndServlet destroy is called."不会打印
    }

}