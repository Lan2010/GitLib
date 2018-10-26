package com.tianzhixing.common.auth.service.idcard;


import com.tianzhixing.common.auth.model.AuthIDCardInfo;

/**
 * 用户服务类
 * Created by routine.k on 16/6/20.
 */
public interface AuthIDCardInfoService {

    /**
     * 添加
     *
     * @param authIDCardInfo
     * @return
     */
    AuthIDCardInfo add(AuthIDCardInfo authIDCardInfo);

    /**
     * 根据AuthToken获取
     *
     * @param authToken
     * @return
     */
    AuthIDCardInfo getByAuthToken(String authToken);

}
