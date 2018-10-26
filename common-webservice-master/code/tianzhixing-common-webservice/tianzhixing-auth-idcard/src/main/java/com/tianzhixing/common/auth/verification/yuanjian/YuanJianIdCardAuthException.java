package com.tianzhixing.common.auth.verification.yuanjian;

/**
 * Created by routine.k on 2018/5/22.
 */
public class YuanJianIdCardAuthException extends RuntimeException {

    public YuanJianIdCardAuthException() {
    }

    public YuanJianIdCardAuthException(String message) {
        super(message);
    }

    public YuanJianIdCardAuthException(String message, Throwable cause) {
        super(message, cause);
    }

    public YuanJianIdCardAuthException(Throwable cause) {
        super(cause);
    }
}
