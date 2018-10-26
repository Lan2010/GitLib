package com.tianzhixing.oms.web.security.util;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by routine.k on 2018/4/29.
 */
public class RequestUtil {

    public static boolean isJsonRequest(HttpServletRequest request) {
        String accept = request.getHeader("accept");
        return !(accept != null && !(accept.indexOf("application/json") > -1 || (request.getHeader("X-Requested-With") != null && request.getHeader("X-Requested-With").indexOf("XMLHttpRequest") > -1)));
    }
}
