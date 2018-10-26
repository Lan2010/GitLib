package com.tianzhixing.common.auth.service.idcard;

import com.tianzhixing.common.auth.model.MobileValidationCode;

/**
 * Created by routine.k on 2018/6/14.
 */
public interface MobileValidationCodeService {

    /**
     * 添加
     *
     * @param mobileValidationCode
     * @return
     */
    MobileValidationCode insert(MobileValidationCode mobileValidationCode);
}
