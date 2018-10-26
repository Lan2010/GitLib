package com.tianzhixing.oms.web.security.util;

import com.tianzhixing.oms.web.security.service.entity.SecurityUser;
import com.tianzhixing.oms.web.security.service.entity.UserInfo;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * 当前登陆用户
 * @author jinkai
 */
public class SecurityUtil {

    /**
     * 获取登陆用户
     */
    public static UserInfo currentLogin() {
        try {
            SecurityUser userDetails = (SecurityUser) SecurityContextHolder.getContext().getAuthentication()
                    .getPrincipal();
            return userDetails.getUser();
        } catch (Exception e) {
            return null;
        }
    }

    public static void setSecurityUser(UserInfo user) {
        try {
            SecurityUser userDetails = (SecurityUser) SecurityContextHolder.getContext().getAuthentication()
                    .getPrincipal();
            userDetails.setUser(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
