package com.tianzhixing.oms.model;

import com.tianzhixing.oms.model.annotation.Column;
import com.tianzhixing.oms.model.annotation.PrimaryKey;
import com.tianzhixing.oms.model.annotation.Table;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户消费星点表
 * Created by routine.k on 2018/6/12.
 */
@Table(name = "user_star_point_increment_info")
public class UserStarPointIncrementInfoModel implements Serializable {

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
     * 增长时间
     */
    @Column(name = "increment_time")
    private Long incrementTime;

    /**
     * 增长数量
     */
    @Column(name = "increment_count")
    private String incrementCount;

    /**
     * 增长类型(0=掉落，1=任务，2=广告)
     */
    @Column(name = "increment_type")
    private Integer incrementType;

    /**
     * 第三方id（广告id or 任务id， 掉落的为空）
     */
    @Column(name = "third_id")
    private String thirdId;

    /**
     * 第三方名称(广告名字，任务名字，掉落的为空)
     */
    @Column(name = "third_name")
    private String thirdName;

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
