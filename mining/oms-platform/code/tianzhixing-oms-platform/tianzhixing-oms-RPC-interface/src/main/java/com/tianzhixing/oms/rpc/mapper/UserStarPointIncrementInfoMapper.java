package com.tianzhixing.oms.rpc.mapper;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户消费星点表
 * Created by routine.k on 2018/6/12.
 */
public class UserStarPointIncrementInfoMapper implements Serializable {

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
     * 增长时间
     */
    private Long incrementTime;

    /**
     * 增长数量
     */
    private String incrementCount;

    /**
     * 增长类型(0=掉落，1=任务，2=广告)
     */
    private Integer incrementType;

    /**
     * 第三方id（广告id or 任务id， 掉落的为空）
     */
    private String thirdId;

    /**
     * 第三方名称(广告名字，任务名字，掉落的为空)
     */
    private String thirdName;


    public UserStarPointIncrementInfoMapper() {
    }

    public UserStarPointIncrementInfoMapper(String id, Date createTime, String platformFrom, String clientPlatformType, String mobile, Long incrementTime, String incrementCount, Integer incrementType, String thirdId, String thirdName) {
        this.id = id;
        this.createTime = createTime;
        this.platformFrom = platformFrom;
        this.clientPlatformType = clientPlatformType;
        this.mobile = mobile;
        this.incrementTime = incrementTime;
        this.incrementCount = incrementCount;
        this.incrementType = incrementType;
        this.thirdId = thirdId;
        this.thirdName = thirdName;
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

    public Long getIncrementTime() {
        return incrementTime;
    }

    public void setIncrementTime(Long incrementTime) {
        this.incrementTime = incrementTime;
    }

    public String getIncrementCount() {
        return incrementCount;
    }

    public void setIncrementCount(String incrementCount) {
        this.incrementCount = incrementCount;
    }

    public Integer getIncrementType() {
        return incrementType;
    }

    public void setIncrementType(Integer incrementType) {
        this.incrementType = incrementType;
    }

    public String getThirdId() {
        return thirdId;
    }

    public void setThirdId(String thirdId) {
        this.thirdId = thirdId;
    }

    public String getThirdName() {
        return thirdName;
    }

    public void setThirdName(String thirdName) {
        this.thirdName = thirdName;
    }
}
