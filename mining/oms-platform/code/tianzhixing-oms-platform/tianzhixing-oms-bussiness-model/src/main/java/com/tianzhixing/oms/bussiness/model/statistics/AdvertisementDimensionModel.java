package com.tianzhixing.oms.bussiness.model.statistics;

import com.tianzhixing.oms.bussiness.model.annotation.Column;
import com.tianzhixing.oms.bussiness.model.annotation.PrimaryKey;
import com.tianzhixing.oms.bussiness.model.annotation.Table;

import java.io.Serializable;
import java.util.Date;

/**
 * 广告统计维度
 * Created by routine.k on 2018/7/5.
 */
@Table(name = "advertisement_dimension")
public class AdvertisementDimensionModel implements Serializable {

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
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 名字
     */
    @Column(name = "name")
    private String name;

    /**
     * 广告ID
     */
    @Column(name = "advertisement_id")
    private String advertisementId;

    /**
     * 开始时间
     */
    @Column(name = "begin_time")
    private Date beginTime;

    /**
     * 结束时间
     */
    @Column(name = "end_time")
    private Date endTime;

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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAdvertisementId() {
        return advertisementId;
    }

    public void setAdvertisementId(String advertisementId) {
        this.advertisementId = advertisementId;
    }

    public Boolean getEnable() {
        return enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }

    public Date getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }
}