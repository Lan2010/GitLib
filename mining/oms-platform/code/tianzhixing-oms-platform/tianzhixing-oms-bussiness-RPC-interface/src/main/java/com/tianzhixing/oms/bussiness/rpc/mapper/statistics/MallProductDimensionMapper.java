package com.tianzhixing.oms.bussiness.rpc.mapper.statistics;

import java.io.Serializable;
import java.util.Date;

/**
 * 产品统计维度
 * Created by routine.k on 2018/7/5.
 */
public class MallProductDimensionMapper implements Serializable {

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
     * 名字
     */
    private String name;

    /**
     * 产品信息
     */
    private String value;

    /**
     * 是否可用
     */
    private Boolean enable;

    public MallProductDimensionMapper() {
    }

    public MallProductDimensionMapper(Long id, Integer version, Date createTime, String name, String value, Boolean enable) {
        this.id = id;
        this.version = version;
        this.createTime = createTime;
        this.name = name;
        this.value = value;
        this.enable = enable;
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

    public Boolean getEnable() {
        return enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }
}
