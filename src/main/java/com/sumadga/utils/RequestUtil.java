package com.sumadga.utils;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

public enum RequestUtil {

    INSTANCE;
    
    private static Logger logger = Logger.getLogger(RequestUtil.class);
    
    public Map<String, String> dumpRequestScope(HttpServletRequest request) {

        Map<String, String> map = new HashMap<String, String>();

        Enumeration<String> e = request.getParameterNames();
        String name = null;
        while (e.hasMoreElements()) {
            name = e.nextElement();

            String[] args = request.getParameterValues(name);
            StringBuilder sb = new StringBuilder();
            for (String arg : args) {
                sb.append(arg).append(",");
            }
            sb.setLength(sb.length() - 1);
            
            if(name != null)
            map.put(name, sb.toString());
            
            logger.debug(name + "=" + sb.toString());
        }

        return map;

    }
}