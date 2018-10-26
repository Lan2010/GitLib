package com.tianzhixing.kernel.commons.ex;

/**
 * Created by routine.k on 2018/6/30.
 */
public class AccountAlreadyExitsException extends RuntimeException {

    public AccountAlreadyExitsException() {
        super();
    }

    public AccountAlreadyExitsException(String message) {
        super(message);
    }


}
