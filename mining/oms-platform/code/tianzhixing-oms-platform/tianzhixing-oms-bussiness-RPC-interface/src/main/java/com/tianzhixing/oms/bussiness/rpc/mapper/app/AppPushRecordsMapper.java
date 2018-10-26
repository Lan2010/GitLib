package com.tianzhixing.oms.bussiness.rpc.mapper.app;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by routine.k on 2018/7/7.
 */
public class AppPushRecordsMapper implements Serializable {

    /**
     * id
     */
    private Long id;

    /**
     * 版本号
     */
    private Integer version;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 操作人
     */
    private String operUser;

    /**
     * 推送类型(1=任务, 2=广告, 3=悬浮窗)
     */
    private Integer pushType;

    /**
     * 第三方ID
     */
    private Long thirdId;

    public AppPushRecordsMapper() {
    }

    public AppPushRecordsMapper(Long id, Integer version, Date createTime, Date updateTime, String operUser, Integer pushType, Long thirdId) {
        this.id = id;
        this.version = version;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.operUser = operUser;
        this.pushType = pushType;
        this.thirdId = thirdId;
    }

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

    public String getOperUser() {
        return operUser;
    }

    public void setOperUser(String operUser) {
        this.operUser = operUser;
    }

    public Integer getPushType() {
        return pushType;
    }

    public void setPushType(Integer pushType) {
        this.pushType = pushType;
    }

    public Long getThirdId() {
        return thirdId;
    }

    public void setThirdId(Long thirdId) {
        this.thirdId = thirdId;
    }
}
