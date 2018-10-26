package com.tianzhixing.oms.rpc.mapper;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户消费星点表
 * Created by routine.k on 2018/6/12.
 */
public class UserStarPointConsumeInfoMapper implements Serializable {

    /**
     * id
     */
    private String id;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 请求平台来源
     */
    private String platformFrom;

    /**
     * 客户端类型
     */
    private String clientPlatformType;

    /**
     * 手机号
     */
    private String mobile;

    /**
     * 消费时间
     */
    private Long consumeTime;

    /**
     * 消费数量
     */
    private String consumeCount;

    /**
     * 消费原因
     */
    private String consumeCause;

    public UserStarPointConsumeInfoMapper() {
    }

    public UserStarPointConsumeInfoMapper(String id, Date createTime, String platformFrom, String clientPlatformType, String mobile, Long consumeTime, String consumeCount, String consumeCause) {
        this.id = id;
        this.createTime = createTime;
        this.platformFrom = platformFrom;
        this.clientPlatformType = clientPlatformType;
        this.mobile = mobile;
        this.consumeTime = consumeTime;
        this.consumeCount = consumeCount;
        this.consumeCause = consumeCause;
    }

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

    public Long getConsumeTime() {
        return consumeTime;
    }

    public void setConsumeTime(Long consumeTime) {
        this.consumeTime = consumeTime;
    }

    public String getConsumeCount() {
        return consumeCount;
    }

    public void setConsumeCount(String consumeCount) {
        this.consumeCount = consumeCount;
    }

    public String getConsumeCause() {
        return consumeCause;
    }

    public void setConsumeCause(String consumeCause) {
        this.consumeCause = consumeCause;
    }
}
