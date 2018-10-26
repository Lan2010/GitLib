package com.tianzhixing.common.auth.dao.mobile;

import com.tianzhixing.common.auth.dao.generic.GenericBaseDao;
import com.tianzhixing.common.auth.model.MobileValidationCode;
import org.springframework.stereotype.Repository;

/**
 * Created by routine.k on 2018/6/14.
 */
@Repository("mobileValidationCodeDao")
public class MobileValidationCodeDao extends GenericBaseDao<MobileValidationCode, Long> {

    /**
     * 添加
     *
     * @param mobileValidationCode
     * @return
     */
    public MobileValidationCode add(MobileValidationCode mobileValidationCode) {
        long id = super.insert(mobileValidationCode);
        mobileValidationCode.setId(id);
        return mobileValidationCode;
    }
}
