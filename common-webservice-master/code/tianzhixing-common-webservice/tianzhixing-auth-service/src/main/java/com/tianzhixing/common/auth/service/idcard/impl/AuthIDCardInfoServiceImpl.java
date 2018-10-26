package com.tianzhixing.common.auth.service.idcard.impl;

import com.tianzhixing.common.auth.dao.user.AuthIDCardInfoDao;
import com.tianzhixing.common.auth.model.AuthIDCardInfo;
import com.tianzhixing.common.auth.service.idcard.AuthIDCardInfoService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * Created by routine.k on 16/6/20.
 */
@Service("authIDCardInfoService")
public class AuthIDCardInfoServiceImpl implements AuthIDCardInfoService {

    @Resource(name = "authIDCardInfoDao")
    private AuthIDCardInfoDao authIDCardInfoDao;

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = RuntimeException.class)
    public AuthIDCardInfo add(AuthIDCardInfo authIDCardInfo) {
        return authIDCardInfoDao.add(authIDCardInfo);
    }

    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public AuthIDCardInfo getByAuthToken(String authToken) {
        return authIDCardInfoDao.getByAuthToken(authToken);
    }
}
