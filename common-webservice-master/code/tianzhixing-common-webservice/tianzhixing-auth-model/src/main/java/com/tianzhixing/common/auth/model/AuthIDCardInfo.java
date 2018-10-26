package com.tianzhixing.common.auth.model;

import com.tianzhixing.common.auth.commons.em.AuthOrgType;
import com.tianzhixing.common.auth.commons.em.RequestFromType;
import com.tianzhixing.common.auth.model.annotation.Column;
import com.tianzhixing.common.auth.model.annotation.PrimaryKey;
import com.tianzhixing.common.auth.model.annotation.Table;

import java.io.Serializable;
import java.util.Date;

/**
 * 身份证验证信息
 * Created by routine.k on 16/6/20.
 */
@Table(name = "auth_idcard_info")
public class AuthIDCardInfo implements Serializable {

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
     * 用户姓名
     */
    @Column(name = "user_name")
    private String userName;

    /**
     * 用户身份证
     */
    @Column(name = "id_card")
    private String idCard;

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
     * AuthToken
     */
    @Column(name = "auth_token")
    private String authToken;

    /**
     * 请求状态
     */
    @Column(name = "request_status")
    private Boolean requestStatus;

    /**
     * 交易单号
     */
    @Column(name = "trans_id")
    private String transID;

    /**
     * 交易流水号
     */
    @Column(name = "trade_num")
    private String tradeNum;

    /**
     * 收费状态
     */
    @Column(name = "fee_status")
    private String feeStatus;

    /**
     * 返回码
     */
    @Column(name = "result_data_code")
    private String resultDataCode;

    /**
     * 返回状态码
     */
    @Column(name = "status_code")
    private String statusCode;

    /**
     * 照片（如果存在）
     */
    @Column(name = "photo")
    private String photo;

    /**
     * 验证机构
     */
    @Column(name = "auth_org_type")
    private AuthOrgType authOrgType;

    /**
     * 是否可用
     */
    @Column(name = "enable")
    private Boolean enable;

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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
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

    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }

    public Boolean getEnable() {
        return enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
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

    public String getFeeStatus() {
        return feeStatus;
    }

    public void setFeeStatus(String feeStatus) {
        this.feeStatus = feeStatus;
    }

    public String getResultDataCode() {
        return resultDataCode;
    }

    public void setResultDataCode(String resultDataCode) {
        this.resultDataCode = resultDataCode;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public AuthOrgType getAuthOrgType() {
        return authOrgType;
    }

    public void setAuthOrgType(AuthOrgType authOrgType) {
        this.authOrgType = authOrgType;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }
}
