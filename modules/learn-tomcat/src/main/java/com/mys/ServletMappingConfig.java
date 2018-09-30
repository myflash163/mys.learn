package com.mys;

import java.util.ArrayList;
import java.util.List;

public class ServletMappingConfig {
    public static List<ServletMapping> servletMappingList = new ArrayList<>();

    static {
        servletMappingList.add(new ServletMapping("findGirl", "/girl", "com.mys.FindGirlServlet"));
        servletMappingList.add(new ServletMapping("helloWorld", "/world", "com.mys.HelloWorldServlet"));

    }
}
