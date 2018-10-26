package com.tianzhixing.kernel.grpc.exception;

/**
 * 账户注册异常
 * Created by routine.k on 2018/6/21.
 */
public class AccountRegException extends RuntimeException {

    public AccountRegException(String message) {
        super(message);
    }

    public AccountRegException() {
    }
}
