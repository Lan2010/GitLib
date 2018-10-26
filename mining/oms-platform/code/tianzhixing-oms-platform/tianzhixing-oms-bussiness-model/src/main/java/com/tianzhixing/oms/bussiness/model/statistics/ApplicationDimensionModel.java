package com.tianzhixing.oms.bussiness.model.statistics;

import com.tianzhixing.bussiness.commons.em.StatisticsDimension;
import com.tianzhixing.oms.bussiness.model.annotation.Column;
import com.tianzhixing.oms.bussiness.model.annotation.PrimaryKey;
import com.tianzhixing.oms.bussiness.model.annotation.Table;

import java.io.Serializable;
import java.util.Date;

/**
 * 系统维度管理
 * Created by routine.k on 2018/7/5.
 */
@Table(name = "application_dimension")
public class ApplicationDimensionModel implements Serializable {

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
     * 统计维度
     */
    @Column(name = "statistics_dimension")
    private StatisticsDimension statisticsDimension;

    /**
     * parentid
     */
    @Column(name = "parent_id")
    private Long parentId;

    /**
     * 名字
     */
    @Column(name = "name")
    private String name;

    /**
     * 值
     */
    @Column(name = "value")
    private String value;

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

    public StatisticsDimension getStatisticsDimension() {
        return statisticsDimension;
    }

    public void setStatisticsDimension(StatisticsDimension statisticsDimension) {
        this.statisticsDimension = statisticsDimension;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public Boolean getEnable() {
        return enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
