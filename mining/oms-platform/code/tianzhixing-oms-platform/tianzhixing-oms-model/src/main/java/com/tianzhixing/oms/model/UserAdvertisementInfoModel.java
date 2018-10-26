package com.tianzhixing.oms.model;

import com.tianzhixing.oms.model.annotation.Column;
import com.tianzhixing.oms.model.annotation.PrimaryKey;
import com.tianzhixing.oms.model.annotation.Table;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户访问/点击广告信息表
 * Created by routine.k on 2018/6/16.
 */
@Table(name = "user_advertisement_info")
public class UserAdvertisementInfoModel implements Serializable {

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
     * 广告ID
     */
    @Column(name = "advert_id")
    private String advertId;

    /**
     * 广告链接
     */
    @Column(name = "advert_link")
    private String advertLink;

    /**
     * 广告类型
     */
    @Column(name = "advert_type")
    private String advertType;

    /**
     * 广告名字
     */
    @Column(name = "advert_name")
    private String advertName;

    /**
     * 广告信息
     */
    @Column(name = "advert_info")
    private String advertInfo;

    /**
     * 操作时间
     */
    @Column(name = "operation_time")
    private Long operationTime;

    /**
     * 操作类型(0=访问，1=点击)
     */
    @Column(name = "operation_type")
    private Integer operationType;

    /**
     * ip
     */
    @Column(name = "ip")
    private String ip;

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

    public String getAdvertId() {
        return advertId;
    }

    public void setAdvertId(String advertId) {
        this.advertId = advertId;
    }

    public String getAdvertLink() {
        return advertLink;
    }

    public void setAdvertLink(String advertLink) {
        this.advertLink = advertLink;
    }

    public String getAdvertType() {
        return advertType;
    }

    public void setAdvertType(String advertType) {
        this.advertType = advertType;
    }

    public String getAdvertName() {
        return advertName;
    }

    public void setAdvertName(String advertName) {
        this.advertName = advertName;
    }

    public String getAdvertInfo() {
        return advertInfo;
    }

    public void setAdvertInfo(String advertInfo) {
        this.advertInfo = advertInfo;
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

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }
}
