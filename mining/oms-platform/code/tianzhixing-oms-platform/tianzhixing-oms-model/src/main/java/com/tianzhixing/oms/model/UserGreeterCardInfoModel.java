package com.tianzhixing.oms.model;

import com.tianzhixing.oms.model.annotation.Column;
import com.tianzhixing.oms.model.annotation.PrimaryKey;
import com.tianzhixing.oms.model.annotation.Table;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户创建/分享贺卡信息表
 * Created by routine.k on 2018/6/12.
 */
@Table(name = "user_greeter_card_info")
public class UserGreeterCardInfoModel implements Serializable {

    /**
     * id
     */
    @PrimaryKey
    @Column(name = "id")
    private String id;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 请求平台来源
     */
    @Column(name = "platform_from")
    private String platformFrom;

    /**
     * 客户端类型
     */
    @Column(name = "client_platform_type")
    private String clientPlatformType;

    /**
     * 手机号
     */
    @Column(name = "mobile")
    private String mobile;

    /**
     * 贺卡ID
     */
    @Column(name = "greeter_card_id")
    private String greeterCardId;

    /**
     * 贺卡信息
     */
    @Column(name = "greeter_card_info")
    private String greeterCardInfo;

    /**
     * 贺卡链接
     */
    @Column(name = "greeter_card_link")
    private String greeterCardLink;

    /**
     * 昵称
     */
    @Column(name = "nick_name")
    private String nickName;

    /**
     * 微信ID
     */
    @Column(name = "wx_id")
    private String wxID;

    /**
     * 操作时间
     */
    @Column(name = "operation_time")
    private Long operationTime;

    /**
     * 操作类型(0=分享，1=创建)
     */
    @Column(name = "operation_type")
    private Integer operationType;

    /**
     * 来源平台
     */
    @Column(name = "share_from_platform")
    private String shareFromPlatform;

    /**
     * 分享至的平台
     */
    @Column(name = "share_to_platform")
    private String shareToPlatform;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getPlatformFrom() {
        return platformFrom;
    }

    public void setPlatformFrom(String platformFrom) {
        this.platformFrom = platformFrom;
    }

    public String getClientPlatformType() {
        return clientPlatformType;
    }

    public void setClientPlatformType(String clientPlatformType) {
        this.clientPlatformType = clientPlatformType;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getGreeterCardId() {
        return greeterCardId;
    }

    public void setGreeterCardId(String greeterCardId) {
        this.greeterCardId = greeterCardId;
    }

    public String getGreeterCardInfo() {
        return greeterCardInfo;
    }

    public void setGreeterCardInfo(String greeterCardInfo) {
        this.greeterCardInfo = greeterCardInfo;
    }

    public String getGreeterCardLink() {
        return greeterCardLink;
    }

    public void setGreeterCardLink(String greeterCardLink) {
        this.greeterCardLink = greeterCardLink;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getWxID() {
        return wxID;
    }

    public void setWxID(String wxID) {
        this.wxID = wxID;
    }

    public Long getOperationTime() {
        return operationTime;
    }

    public void setOperationTime(Long operationTime) {
        this.operationTime = operationTime;
    }

    public Integer getOperationType() {
        return operationType;
    }

    public void setOperationType(Integer operationType) {
        this.operationType = operationType;
    }

    public String getShareFromPlatform() {
        return shareFromPlatform;
    }

    public void setShareFromPlatform(String shareFromPlatform) {
        this.shareFromPlatform = shareFromPlatform;
    }

    public String getShareToPlatform() {
        return shareToPlatform;
    }

    public void setShareToPlatform(String shareToPlatform) {
        this.shareToPlatform = shareToPlatform;
    }
}
