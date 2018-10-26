package com.tianzhixing.oms.web.security.handler;

import com.tianzhixing.oms.commons.CodeConfig;
import com.tianzhixing.oms.utils.JSONUtil;
import com.tianzhixing.oms.web.security.entity.ResponseEntity;
import com.tianzhixing.oms.web.security.service.UserInfoService;
import com.tianzhixing.oms.web.security.service.entity.UserInfo;
import com.tianzhixing.oms.web.security.util.RequestUtil;
import com.tianzhixing.oms.web.security.util.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 登陆成功handler
 *
 * @author jinkai.xie
 */
public class CustomerAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(CustomerAuthenticationSuccessHandler.class);

    @Autowired
    private UserInfoService userInfoService;

    /* (non-Javadoc)
     * @see org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler#onAuthenticationSuccess(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, org.springframework.security.core.Authentication)
     */
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response, Authentication authentication)
            throws IOException, ServletException {
        //获取当前登陆用户
        UserInfo userInfo = SecurityUtil.currentLogin();
        if (userInfo == null) {
            throw new RuntimeException("failed.found.current.login");
        }
        String ip = _getIP(request);
        userInfo.setLastLoginTime(new Date());
        userInfo.setIp(ip);
        userInfoService.updateUserInfo(userInfo);
        //记录登录日志
        logger.info("--- begin service logger ---   user:" + userInfo.getId() + ", login status: success, login time:" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + ", login ip:" + ip + "  --- end service logger--- ");
        if (!RequestUtil.isJsonRequest(request)) {
            super.onAuthenticationSuccess(request, response, authentication);
        } else {
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json");
            response.getWriter().println(JSONUtil.beanToJson(new ResponseEntity(CodeConfig.SUCCESS.getCode(), CodeConfig.SUCCESS.getValue()), false));
            response.getWriter().flush();
            clearAuthenticationAttributes(request);
        }
    }

    private String _getIP(HttpServletRequest request) {
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

}

