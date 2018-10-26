package com.tianzhixing.oms.bussiness.backend.web.vo;


import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by routine.k on 2018/6/22.
 */
public class ApplicationDimensionVo implements Serializable {

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
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date createTime;

    /**
     * 统计维度
     */
    private String statisticsDimension;

    /**
     * parentName
     */
    private String parentName;

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


    public ApplicationDimensionVo() {
    }


	public ApplicationDimensionVo(Long id, Integer version, Date createTime, String statisticsDimension,
			String parentName, Boolean enable, String name, String value) {
		super();
		this.id = id;
		this.version = version;
		this.createTime = createTime;
		this.statisticsDimension = statisticsDimension;
		this.parentName = parentName;
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

	
	public String getStatisticsDimension() {
		return statisticsDimension;
	}


	public void setStatisticsDimension(String statisticsDimension) {
		this.statisticsDimension = statisticsDimension;
	}


	public String getParentName() {
		return parentName;
	}


	public void setParentName(String parentName) {
		this.parentName = parentName;
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
