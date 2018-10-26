package com.tianzhixing.common.auth.dao.user;

import com.tianzhixing.common.auth.dao.generic.GenericBaseDao;
import com.tianzhixing.common.auth.model.AuthIDCardInfo;
import org.springframework.stereotype.Repository;

/**
 * AuthIDCardInfoDao
 * Created by routine.k on 16/6/24.
 */
@Repository("authIDCardInfoDao")
public class AuthIDCardInfoDao extends GenericBaseDao<AuthIDCardInfo, Long> {


    /**
     * 添加
     *
     * @param authIDCardInfo
     * @return
     */
    public AuthIDCardInfo add(final AuthIDCardInfo authIDCardInfo) {
        long id = super.insert(authIDCardInfo);
        authIDCardInfo.setId(id);
        return authIDCardInfo;
    }

    /**
     * 禁用当前记录
     * @param id
     * @param authToken
     * @param version
     */
    public void disable(final Long id, final String authToken, final Integer version){
        super.update(id, new String[]{"auth_token", "enable"}, new Object[]{authToken, false}, version);
    }

    /**
     * 根据AuthToken获取
     *
     * @param authToken
     * @return
     */
    public AuthIDCardInfo getByAuthToken(final String authToken) {
        return super.get(new String[]{"auth_token"}, new String[]{authToken});
    }

}
