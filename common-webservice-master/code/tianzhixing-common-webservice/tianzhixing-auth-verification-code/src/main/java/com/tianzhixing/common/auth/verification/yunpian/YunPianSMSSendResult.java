package com.tianzhixing.common.auth.verification.yunpian;

/**
 * Created by routine.k on 2018/6/14.
 */
public class YunPianSMSSendResult {

    /**
     * 发送结果
     * 0=成功 其他=失败
     */
    private int code;

    /**
     * 反馈信息
     */
    private String msg;

    /**
     * 短信计费条数
     */
    private int count;

    /**
     * 费用
     */
    private double fee;

    /**
     * 计费单位
     */
    private String unit;

    /**
     * 手机号
     */
    private String mobile;

    /**
     * 短信id，64位整型
     */
    private long sid;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public double getFee() {
        return fee;
    }

    public void setFee(double fee) {
        this.fee = fee;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public long getSid() {
        return sid;
    }

    public void setSid(long sid) {
        this.sid = sid;
    }
}
