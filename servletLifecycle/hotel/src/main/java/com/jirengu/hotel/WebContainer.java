package com.jirengu.hotel;

import javax.servlet.*;
import java.io.IOException;
import java.util.*;

public class WebContainer {

    Map<String, String> urlPatternToServletClass = new HashMap<>();

    Map<String, Servlet> urlPatternToServlet = new HashMap<>();

    /**
     * 启动web容器
     */
    public void start(List<ServletConfiguration> configurationList) throws ClassNotFoundException, InstantiationException, IllegalAccessException, ServletException {
        List<ServletConfiguration> servletNeedToLoadOnStart = new ArrayList<>();
        for (ServletConfiguration servletConfiguration : configurationList) {
            String urlPattern = servletConfiguration.getUrlPattern();
            String servletClass = servletConfiguration.getServletClass();
            urlPatternToServletClass.put(urlPattern, servletClass);
            Integer loadOnStartup = servletConfiguration.getLoadOnStartup();
            if (loadOnStartup != null && loadOnStartup >= 0) {
                // 是不是在这里创建 Servlet 对象？
                // 不是，为什么呢？为什么不能在此处创建 Servlet 对象
                // 因为 loadOnStartup 有优先级的概念，数值越小(>=0)，优先级越高，需要按照优先级的顺序，创建 Servlet 对象
                servletNeedToLoadOnStart.add(servletConfiguration);
            }
        }
        // 按照 loadOnStartup 的优先级，对配置信息进行排序（升序排序）
        servletNeedToLoadOnStart.sort(Comparator.comparing(v -> v.getLoadOnStartup()));
        // 创建 Servlet 对象
        for (ServletConfiguration servletConfiguration : servletNeedToLoadOnStart) {
            String servletClass = servletConfiguration.getServletClass();
            String urlPattern = servletConfiguration.getUrlPattern();
            createServletObj(urlPattern, servletClass);
        }
    }

    private Servlet createServletObj(String urlPattern, String servletClass) throws InstantiationException, IllegalAccessException, ClassNotFoundException, ServletException {
        Class clazz = Class.forName(servletClass);
        Object obj = clazz.newInstance();
        Servlet servlet = (Servlet) obj;
        // Servlet 对象创建出来后，还需要调用 init 方法完成初始化
        servlet.init(null);
        urlPatternToServlet.put(urlPattern, servlet);
        return servlet;
    }

    /**
     * 执行请求
     */
    public void doService(String urlPattern, ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        // 情况一：如果请求路径和 Servlet 对象之间的映射关系已经存在，直接获取 Servlet 对象，并调用其 service 方法
        if (urlPatternToServlet.containsKey(urlPattern)) {
            Servlet servlet = urlPatternToServlet.get(urlPattern);
            servlet.service(servletRequest, servletResponse);
            return;
        }
        // 情况二：如果请求路径和 Servlet 对象之间的映射关系还不存在，但是请求路径和 Servlet 类名之间的映射关系存在，那么创建 Servlet 对象，并调用其 service 方法
        if (urlPatternToServletClass.containsKey(urlPattern)) {
            String servletClass = urlPatternToServletClass.get(urlPattern);
            Servlet servlet = createServletObj(urlPattern, servletClass);
            servlet.service(servletRequest, servletResponse);
            return;
        }
        // 情况三：如果用户输入的请求路径不存在，那么无法处理用户请求
        System.out.println("请求路径不存在，无法处理请求。");
    }

    /**
     * 关闭 web 容器
     */
    public void close() {
        for (Servlet servlet : urlPatternToServlet.values()) {
            servlet.destroy();
        }
    }


}
