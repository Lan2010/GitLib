package com.tianzhixing.oms.bussiness.backend.web.vo;


import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by routine.k on 2018/6/22.
 */
public class MallProductDimensionVo implements Serializable {

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


    public MallProductDimensionVo() {
    }


	public MallProductDimensionVo(Long id, Integer version, Date createTime, Boolean enable, String name, String value) {
		super();
		this.id = id;
		this.version = version;
		this.createTime = createTime;
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
