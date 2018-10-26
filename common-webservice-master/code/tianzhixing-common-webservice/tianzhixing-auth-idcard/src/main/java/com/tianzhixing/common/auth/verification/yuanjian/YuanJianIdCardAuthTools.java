package com.tianzhixing.common.auth.verification.yuanjian;

import com.tianzhixing.common.auth.utils.JSONUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.Date;

/**
 * 远鉴科技身份证验证工具类
 * Created by routine.k on 2018/5/22.
 */
public class YuanJianIdCardAuthTools {

    private static Log logger = LogFactory.getLog(YuanJianIdCardAuthTools.class);

    /**
     * 身份证验证
     *
     * @param idCard
     * @param name
     * @return
     */
    public final static YuanJianAuthResult auth(final String idCard, final String name) {
        boolean envEnable = YuanJianConfig.isYuanjianEnvEnable();
        if (!envEnable) {
            Date date = new Date();
            String transID = YuanJianParamTools.transID(date);
            return new YuanJianAuthResult(true, transID, transID, YuanJianFeeStatus.N, YuanJianResultDataCode.ZERO, null, idCard, name, "env.success", "ENV10000");
        }
        YuanJianRequestEntity yuanJianRequestEntity = _requestEntity(idCard, name);
        return _execute(idCard, name, yuanJianRequestEntity);
    }

    private static YuanJianRequestEntity _requestEntity(String idCard, String name) {
        YuanJianRequestEntity yuanJianRequestEntity = new YuanJianRequestEntity();
        YuanJianRequestDataContentEntity yuanJianRequestDataContentEntity = new YuanJianRequestDataContentEntity();
        yuanJianRequestDataContentEntity.setId_card(idCard);
        yuanJianRequestDataContentEntity.setId_holder(name);
        yuanJianRequestDataContentEntity.setIndustry_type(YuanjianIndustryType.A1.name());
        yuanJianRequestDataContentEntity.setIs_photo(YuanJianConfig.getIsPhoto());
        Date date = new Date();
        yuanJianRequestDataContentEntity.setTrans_id(YuanJianParamTools.transID(date));
        yuanJianRequestDataContentEntity.setTrade_date(YuanJianParamTools.tradeDate(date));
        yuanJianRequestEntity.setData_content(yuanJianRequestDataContentEntity);
        return yuanJianRequestEntity;
    }

    private static YuanJianAuthResult _execute(String idCard, String name, YuanJianRequestEntity yuanJianRequestEntity) {
        YuanJianResultEntity yuanJianResultEntity = new YuanJianClient().executeAuth(yuanJianRequestEntity);
        YuanJianAuthResult yuanJianAuthResult = new YuanJianAuthResult();
        yuanJianAuthResult.setIdCard(idCard);
        yuanJianAuthResult.setName(name);
        yuanJianAuthResult.setRequestStatus(yuanJianResultEntity.getSuccess());
        if (yuanJianResultEntity.getSuccess()) {
            yuanJianAuthResult.setYuanJianFeeStatus(YuanJianFeeStatus.valueOf(yuanJianResultEntity.getData().getFee()));
            yuanJianAuthResult.setPhoto(yuanJianResultEntity.getData().getPhoto());
            yuanJianAuthResult.setTradeNum(yuanJianResultEntity.getData().getTrade_no());
            yuanJianAuthResult.setTransID(yuanJianResultEntity.getData().getTrans_id());
            YuanJianResultDataCode yuanJianResultDataCode = null;
            for (YuanJianResultDataCode rdc : YuanJianResultDataCode.values()) {
                if (rdc.getKey().equals(yuanJianResultEntity.getData().getCode())) {
                    yuanJianResultDataCode = rdc;
                }
            }
            if (yuanJianResultDataCode == null) {
                logger.error("request yuanjian remote server success, but result code unknown[" + yuanJianResultEntity.getData().getCode() + "]");
                throw new YuanJianIdCardAuthException("request yuanjian remote server success, but result code unknown[" + yuanJianResultEntity.getData().getCode() + "]");
            }
            yuanJianAuthResult.setYuanJianResultDataCode(yuanJianResultDataCode);
            yuanJianAuthResult.setCode("YJ_REQSUC");
        } else {
            logger.error("request finished, request status not success, error code[" + yuanJianResultEntity.getErrorCode() + ":: " + yuanJianResultEntity.getErrorMsg() + "]");
            yuanJianAuthResult.setMessage(yuanJianResultEntity.getErrorMsg());
            yuanJianAuthResult.setCode(yuanJianResultEntity.getErrorCode());
        }
        return yuanJianAuthResult;
    }
}
