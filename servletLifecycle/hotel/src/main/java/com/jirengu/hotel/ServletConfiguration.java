package com.jirengu.hotel;

public class ServletConfiguration {

    private String urlPattern;

    private String servletClass;

    private Integer loadOnStartup;

    public ServletConfiguration(String urlPattern, String servletClass, Integer loadOnStartup) {
        this.urlPattern = urlPattern;
        this.servletClass = servletClass;
        this.loadOnStartup = loadOnStartup;
    }

    public String getUrlPattern() {
        return this.urlPattern;
    }

    public String getServletClass() {
        return this.servletClass;
    }

    public Integer getLoadOnStartup() {
        return this.loadOnStartup;
    }

    public void setUrlPattern(String urlPattern) {
        this.urlPattern = urlPattern;
    }

    public void setServletClass(String servletClass) {
        this.servletClass = servletClass;
    }

    public void setLoadOnStartup(Integer loadOnStartup) {
        this.loadOnStartup = loadOnStartup;
    }

}
