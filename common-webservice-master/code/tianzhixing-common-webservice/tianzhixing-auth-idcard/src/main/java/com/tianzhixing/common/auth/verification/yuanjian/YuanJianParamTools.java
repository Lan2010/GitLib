package com.tianzhixing.common.auth.verification.yuanjian;

import com.tianzhixing.common.auth.utils.GenerateUtil;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by routine.k on 2018/5/22.
 */
public class YuanJianParamTools {

    public final static String transID(Date date) {
        String tradeDate = tradeDate(date);
        return tradeDate + GenerateUtil.generateNumber(YuanJianConfig.getTransIDLength() - tradeDate.length());
    }

    public final static String tradeDate(Date date) {
        String dateFormat = YuanJianConfig.getTradeDateFormat();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat);
        return simpleDateFormat.format(date);
    }
}
