package com.tianzhixing.oms.bussiness.backend.web.vo;

import com.fasterxml.jackson.annotation.JsonFormat;

import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by routine.k on 2018/6/22.
 */
public class PagesDimensionVo implements Serializable {

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
     * 名字
     */
    private String name;

    /**
     * 值
     */
    private String url;

    /**
     * 开始时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date beginTime;

    /**
     * 结束时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date endTime;
    
    /**
     * 是否可用
     */
    private Boolean enable;


    public PagesDimensionVo() {
    }


	public PagesDimensionVo(Long id, Integer version, Date createTime, String name, String url, Date beginTime,
			Date endTime, Boolean enable) {
		super();
		this.id = id;
		this.version = version;
		this.createTime = createTime;
		this.name = name;
		this.url = url;
		this.beginTime = beginTime;
		this.endTime = endTime;
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


	public String getUrl() {
		return url;
	}


	public void setUrl(String url) {
		this.url = url;
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


	public Boolean getEnable() {
		return enable;
	}


	public void setEnable(Boolean enable) {
		this.enable = enable;
	}

}
