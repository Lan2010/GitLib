package com.tianzhixing.kernel.grpc.util;

import com.tianzhixing.kernel.cache.APPClientCache;
import org.apache.commons.lang3.StringUtils;

import java.util.Date;

/**
 * Created by routine.k on 2018/6/12.
 */
public class ValidatorUtil {

    /**
     * 验证Token
     *
     * @param token
     * @param token
     * @return
     */
    public static ValidatorResult validatorToken(String token) {
        if (StringUtils.isEmpty(APPClientCache.get(token))) {
            return new ValidatorResult(false, 300, "request.client.token.null", null);
        }
        return new ValidatorResult(true, 200, "validate.suc", APPClientCache.get(token));
    }

    /**
     * 验证是否为时间格式
     *
     * @param time
     * @return
     */
    public static boolean vaildateTime(Long time) {
        if (time == null) {
            return false;
        }
        try {
            Date date = new Date(time);
        } catch (Exception ex) {
            return false;
        }
        return true;
    }

    /**
     * 验证Token&Account
     *
     * @param token
     * @param accountToken
     * @return
     */
    public static ValidatorResult validatorTokenAndAccount(String token, String accountToken) {
        ValidatorResult validatorResult = validatorToken(token);
        if (!validatorResult.getStatus()) {
            return validatorResult;
        }
        if (StringUtils.isEmpty(accountToken)) {
            return new ValidatorResult(false, 300, "request.account.token.null", null);
        }
        return validatorResult;
    }

    public static class ValidatorResult {
        private boolean status;
        private int code;
        private String message;
        private String org;

        public ValidatorResult(boolean status, int code, String message, String org) {
            this.status = status;
            this.code = code;
            this.message = message;
            this.org = org;
        }

        public boolean getStatus() {
            return status;
        }

        public void setStatus(boolean status) {
            this.status = status;
        }

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public String getOrg() {
            return org;
        }

        public void setOrg(String org) {
            this.org = org;
        }
    }
}
