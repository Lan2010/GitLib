package com.tianzhixing.common.auth.verification.yunpian;

import com.yunpian.sdk.YunpianClient;
import com.yunpian.sdk.model.Result;
import com.yunpian.sdk.model.SmsSingleSend;
import org.apache.commons.lang3.StringUtils;

import java.util.Map;

/**
 * Created by routine.k on 2018/6/14.
 */
public class YunPianSMSClient {

    /**
     * 发送短信验证码
     *
     * @param validationCode
     * @param mobile
     * @return
     */
    public YunPianSMSSendResult sendCodeWithValidateCode(String validationCode, String mobile) {
        String content = YunPianConfig.getSecurityMod().replace("#code#", validationCode);
        return _getYunPianSMSSendResult(mobile, content);
    }

    /**
     * 根据模板key发送内容短信
     *
     * @param modKey
     * @param replace
     * @param mobile
     * @return
     */
    public YunPianSMSSendResult sendCodeWithMod(String modKey, Map<String, String> replace, String mobile) {
        String content = YunPianConfig.getMod(modKey);
        if (replace != null) {
            for (String key : replace.keySet()) {
                String value = replace.get(key);
                content = content.replace("#" + key + "#", StringUtils.isEmpty(value) ? "" : value);
            }
        }
        return _getYunPianSMSSendResult(mobile, content);
    }

    private YunPianSMSSendResult _getYunPianSMSSendResult(String mobile, String content) {
        if (!YunPianConfig.isENABLE()) {
            YunPianSMSSendResult yunPianSMSSendResult = new YunPianSMSSendResult();
            yunPianSMSSendResult.setMobile(mobile);
            yunPianSMSSendResult.setCode(0);
            return yunPianSMSSendResult;
        }
        YunpianClient client = new YunpianClient(YunPianConfig.getApiKey()).init();
        //发送短信API
        Map<String, String> param = client.newParam(2);
        param.put(YunpianClient.MOBILE, mobile);
        param.put(YunpianClient.TEXT, content);
        Result<SmsSingleSend> result = client.sms().single_send(param);
        Integer code = result.getCode();
        YunPianSMSSendResult yunPianSMSSendResult = new YunPianSMSSendResult();
        yunPianSMSSendResult.setCode(code == null ? 0 : code.intValue());
        SmsSingleSend smsSingleSend = result.getData();
        Integer count = smsSingleSend == null ? 0 : smsSingleSend.getCount();
        yunPianSMSSendResult.setCount(count == null ? 0 : count.intValue());
        Double fee = smsSingleSend == null ? 0d : smsSingleSend.getFee();
        yunPianSMSSendResult.setFee(fee == null ? 0d : fee.doubleValue());
        yunPianSMSSendResult.setMobile(mobile);
        yunPianSMSSendResult.setMsg(result.getMsg());
        Long sid = smsSingleSend == null ? 0l : smsSingleSend.getSid();
        yunPianSMSSendResult.setSid(sid == null ? 0l : sid.longValue());
        String unit = smsSingleSend == null ? null : smsSingleSend.getUnit();
        yunPianSMSSendResult.setUnit(unit);
        return yunPianSMSSendResult;
    }

}
