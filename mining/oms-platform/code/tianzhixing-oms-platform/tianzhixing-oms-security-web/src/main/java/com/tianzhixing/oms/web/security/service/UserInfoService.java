package com.tianzhixing.oms.web.security.service;

import com.tianzhixing.oms.web.security.service.entity.UserInfo;

/**
 * Created by routine.k on 2018/6/23.
 */
public interface UserInfoService {

    /**
     * 根据username获取
     *
     * @param username
     * @return
     */
    UserInfo loadByUserName(String username);

    /**
     * 更新用户信息
     *
     * @param userInfo
     */
    void updateUserInfo(UserInfo userInfo);
}
