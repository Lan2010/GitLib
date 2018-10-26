package com.tianzhixing.common.auth.verification.yuanjian;

import java.io.Serializable;

/**
 * Created by routine.k on 2018/5/22.
 */
public class YuanJianAuthResult implements Serializable {

    /**
     * 请求状态
     */
    private Boolean requestStatus;

    /**
     * 交易单号
     */
    private String transID;

    /**
     * 交易流水号
     */
    private String tradeNum;

    /**
     * 收费状态
     */
    private YuanJianFeeStatus yuanJianFeeStatus;

    /**
     * 返回码
     */
    private YuanJianResultDataCode yuanJianResultDataCode;

    /**
     * 照片（如果存在）
     */
    private String photo;

    /**
     * 身份证号码
     */
    private String idCard;

    /**
     * 姓名
     */
    private String name;

    /**
     * 信息
     */
    private String message;

    /**
     * code
     */
    private String code;

    public YuanJianAuthResult() {
    }

    public YuanJianAuthResult(Boolean requestStatus, String transID, String tradeNum, YuanJianFeeStatus yuanJianFeeStatus, YuanJianResultDataCode yuanJianResultDataCode, String photo, String idCard, String name, String message, String code) {
        this.requestStatus = requestStatus;
        this.transID = transID;
        this.tradeNum = tradeNum;
        this.yuanJianFeeStatus = yuanJianFeeStatus;
        this.yuanJianResultDataCode = yuanJianResultDataCode;
        this.photo = photo;
        this.idCard = idCard;
        this.name = name;
        this.message = message;
        this.code = code;
    }

    public Boolean getRequestStatus() {
        return requestStatus;
    }

    public void setRequestStatus(Boolean requestStatus) {
        this.requestStatus = requestStatus;
    }

    public String getTransID() {
        return transID;
    }

    public void setTransID(String transID) {
        this.transID = transID;
    }

    public String getTradeNum() {
        return tradeNum;
    }

    public void setTradeNum(String tradeNum) {
        this.tradeNum = tradeNum;
    }

    public YuanJianFeeStatus getYuanJianFeeStatus() {
        return yuanJianFeeStatus;
    }

    public void setYuanJianFeeStatus(YuanJianFeeStatus yuanJianFeeStatus) {
        this.yuanJianFeeStatus = yuanJianFeeStatus;
    }

    public YuanJianResultDataCode getYuanJianResultDataCode() {
        return yuanJianResultDataCode;
    }

    public void setYuanJianResultDataCode(YuanJianResultDataCode yuanJianResultDataCode) {
        this.yuanJianResultDataCode = yuanJianResultDataCode;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
