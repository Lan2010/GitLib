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
@Table(name = "user_star_point_consume_info")
public class UserStarPointConsumeInfoModel implements Serializable {

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
     * 消费时间
     */
    @Column(name = "consume_time")
    private Long consumeTime;

    /**
     * 消费数量
     */
    @Column(name = "consume_count")
    private String consumeCount;

    /**
     * 消费原因
     */
    @Column(name = "consume_cause")
    private String consumeCause;

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
