package com.tianzhixing.common.auth.service.idcard.impl;

import com.tianzhixing.common.auth.dao.mobile.MobileValidationCodeDao;
import com.tianzhixing.common.auth.model.MobileValidationCode;
import com.tianzhixing.common.auth.service.idcard.MobileValidationCodeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * Created by routine.k on 2018/6/14.
 */
@Service("mobileValidationCodeService")
public class MobileValidationCodeServiceImpl implements MobileValidationCodeService {

    @Resource(name = "mobileValidationCodeDao")
    private MobileValidationCodeDao mobileValidationCodeDao;

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = RuntimeException.class)
    public MobileValidationCode insert(MobileValidationCode mobileValidationCode) {
        return mobileValidationCodeDao.add(mobileValidationCode);
    }
}
