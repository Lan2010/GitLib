package com.tianzhixing.oms.web.security.exception;

import com.tianzhixing.oms.commons.CodeConfig;
import org.springframework.security.core.AuthenticationException;

/**
 * Created by routine.k on 16/8/11.
 */
public class LoginFailedException extends AuthenticationException {

    private CodeConfig codeConfig;

    public LoginFailedException(String msg, Throwable t) {
        super(msg, t);
    }

    public LoginFailedException(String msg) {
        super(msg);
    }

    public LoginFailedException(String msg, Throwable t, CodeConfig codeConfig) {
        super(msg, t);
        this.codeConfig = codeConfig;
    }

    public LoginFailedException(String msg, CodeConfig codeConfig) {
        super(msg);
        this.codeConfig = codeConfig;
    }

    public CodeConfig getCodeConfig() {
        return codeConfig;
    }

    public void setCodeConfig(CodeConfig codeConfig) {
        this.codeConfig = codeConfig;
    }
}
