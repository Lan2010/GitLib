package com.tianzhixing.oms.bussiness.rpc.mapper.statistics;

import com.tianzhixing.bussiness.commons.em.StatisticsDimension;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by routine.k on 2018/7/5.
 */
public class ApplicationDimensionMapper implements Serializable {

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
     * 统计维度
     */
    private StatisticsDimension statisticsDimension;

    /**
     * parentid
     */
    private Long parentId;

    /**
     * 是否可用
     */
    private Boolean enable;

    /**
     * 名字
     */
    private String name;

    /**
     * 值
     */
    private String value;

    public ApplicationDimensionMapper() {
    }

    public ApplicationDimensionMapper(Long id, Integer version, Date createTime, StatisticsDimension statisticsDimension, Long parentId, Boolean enable, String name, String value) {
        this.id = id;
        this.version = version;
        this.createTime = createTime;
        this.statisticsDimension = statisticsDimension;
        this.parentId = parentId;
        this.enable = enable;
        this.name = name;
        this.value = value;
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
