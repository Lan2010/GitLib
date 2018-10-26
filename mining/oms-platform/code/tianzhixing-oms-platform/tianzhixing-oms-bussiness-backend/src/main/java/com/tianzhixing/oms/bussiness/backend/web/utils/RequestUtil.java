package com.tianzhixing.oms.bussiness.backend.web.utils;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @author jinkai.xie
 */
public class RequestUtil {

    private RequestUtil() {
    }

    public static String getRemoteIPAddress(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

    /**
     * 配置请求参数map
     *
     * @param keys
     * @param values
     * @return
     */
    public static Map<String, String> configRequestMap(String[] keys, Object[] values) {
        Map<String, String> map = new HashMap<String, String>();
        for (int i = 0; i < keys.length; i++) {
            map.put(keys[i], values[i].toString());
        }
        return map;
    }

    public static class KeysMap{
        /**
         * 分页开始
         */
        public final static String PAGE_FROM = "from";
        /**
         * 分页条数
         */
        public final static String PAGE_PAGESIZE = "pageSize";
    }
}
