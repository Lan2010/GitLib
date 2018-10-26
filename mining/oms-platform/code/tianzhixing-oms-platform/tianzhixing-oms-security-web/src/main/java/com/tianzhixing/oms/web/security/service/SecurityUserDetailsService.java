package com.tianzhixing.oms.web.security.service;

import com.tianzhixing.oms.commons.CodeConfig;
import com.tianzhixing.oms.web.security.exception.LoginFailedException;
import com.tianzhixing.oms.web.security.service.entity.SecurityUser;
import com.tianzhixing.oms.web.security.service.entity.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * @author jinkai.xie
 */
public class SecurityUserDetailsService implements UserDetailsService {

    @Autowired
    private UserInfoService userInfoService;

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
        if (null == username || "".equals(username)) {
            //throw new LoginFailedException(null, CodeConfig.LOGIN_ERROR_NULL_FIELD);
            return new SecurityUser(new UserInfo());
        }
        UserInfo userInfo = userInfoService.loadByUserName(username);
        if (null == userInfo) {
            //throw new LoginFailedException("user.not.found");
            return new SecurityUser(new UserInfo());
        }
        return new SecurityUser(userInfo);
    }

}

