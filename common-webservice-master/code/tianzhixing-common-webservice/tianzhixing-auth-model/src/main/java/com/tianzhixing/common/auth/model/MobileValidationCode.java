package com.tianzhixing.common.auth.model;

import com.tianzhixing.common.auth.commons.em.RequestFromType;
import com.tianzhixing.common.auth.model.annotation.Column;
import com.tianzhixing.common.auth.model.annotation.PrimaryKey;
import com.tianzhixing.common.auth.model.annotation.Table;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by routine.k on 2018/6/14.
 */
@Table(name = "mobile_validation_code")
public class MobileValidationCode implements Serializable {

    /**
     * id
     */
    @PrimaryKey
    @Column(name = "id")
    private Long id;

    /**
     * 版本号
     */
    @Column(name = "version")
    private Integer version;

    /**
     * 用户手机号码
     */
    @Column(name = "mobile")
    private String mobile;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 更新时间
     */
    @Column(name = "update_time")
    private Date updateTime;

    /**
     * 请求来源
     */
    @Column(name = "request_from_type")
    private RequestFromType requestFromType;

    /**
     * 验证码
     */
    @Column(name = "validation_code")
    private String validationCode;

    /**
     * 授权码
     */
    @Column(name = "token")
    private String token;

    /**
     * 状态
     */
    @Column(name = "status")
    private Boolean status;

    /**
     * 发送结构
     */
    @Column(name = "sender_org")
    private String senderOrg;

    /**
     * 消息
     */
    @Column(name = "message")
    private String message;

    /**
     * 返回代码
     */
    @Column(name = "result_code")
    private String resultCode;

    /**
     * 费用
     */
    @Column(name = "fee")
    private String fee;

    /**
     * 短信ID
     */
    @Column(name = "sid")
    private String sid;

    /**
     * 短信类型
     */
    @Column(name = "sms_type")
    private String smsType;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public RequestFromType getRequestFromType() {
        return requestFromType;
    }

    public void setRequestFromType(RequestFromType requestFromType) {
        this.requestFromType = requestFromType;
    }

    public String getValidationCode() {
        return validationCode;
    }

    public void setValidationCode(String validationCode) {
        this.validationCode = validationCode;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getSenderOrg() {
        return senderOrg;
    }

    public void setSenderOrg(String senderOrg) {
        this.senderOrg = senderOrg;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public String getFee() {
        return fee;
    }

    public void setFee(String fee) {
        this.fee = fee;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getSmsType() {
        return smsType;
    }

    public void setSmsType(String smsType) {
        this.smsType = smsType;
    }
}
