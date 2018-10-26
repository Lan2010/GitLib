package com.tianzhixing.common.auth.commons.code;

/**
 * 返回CODE配置文件
 * Created by routine.k on 16/8/12.
 */
public enum CodeConfig {

    /**
     * 1-100 授权错误码范围
     */
    AUTHFAILED(100, "授权失败"),

    /**
     * 处理成功代码
     * 200
     */
    SUCCESS(200, "OK"),

    /**
     * 201-300 业务系统码范围
     */
    IDCARDAUTHSUC(201, "身份证验证成功"),
    IDCARDAUTHFAILED(202, "身份证验证失败"),
    IDCARDNULL(203, "名字为空"),
    MOBILENULL(204, "手机号为空"),
    ONLYSECURITY(205, "只允许短信验证码"),
    CODELENGTH_ERROR(206, "验证码长度不正确"),
    MOBILESENDFAILED(207, "验证码发送失败"),
    INPUTCODENULL(208, "请输入短信验证码"),
    CODETOKENNULL(209, "短信验证凭证不可为空"),
    CODETOKENERROR(210, "短信验证凭证错误"),
    CODEVALIDATEFAILED(211, "短信验证码输入错误"),
    CODETIMEOUT(212, "验证码发送超时"),
    CODEINTERVALERROR(213, "短信验证码发送太过频繁"),
    SMSMODERROR(214, "短信模板错误"),

    /**
     * 500 请求失败代码
     */
    REQUESTERROR(500, "request.error");

    /**
     * 标识码
     */
    private int code;

    /**
     * 资源值
     */
    private String value;

    CodeConfig(int code, String value) {
        this.code = code;
        this.value = value;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getValue() {
        return CodeProperties.get(this.value);
    }

    public void setValue(String value) {
        this.value = value;
    }

}
