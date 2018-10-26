package com.tianzhixing.oms.commons;

/**
 * 返回CODE配置文件
 * Created by routine.k on 16/8/12.
 */
public enum CodeConfig {

    /**
     * 1-100 授权错误码范围
     */
    AUTHFAILED(100, "授权失败,请重新获取授权"),

    /**
     * 处理成功代码
     * 200
     */
    SUCCESS(200, "请求成功"),

    /**
     * 201-300 业务系统码范围
     */
    NEEDLOGIN(201, "请登录后重试"),
    LOGIN_CODE_ERROR(202, "验证码错误"),
    LOGIN_FAILED(203, "登录失败，用户名密码不对"),
    LOGIN_ERROR_NULL_FIELD(204, "登录失败，当前用户未找到"),
    /**
     * 500 请求失败代码
     */
    REQUESTERROR(500, "请求失败，请重试");

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
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
