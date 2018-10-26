package com.tianzhixing.oms.bussiness.backend.web.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.tianzhixing.bussiness.commons.em.AdvertisementAttribute;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by routine.k on 2018/6/22.
 */
public class AdvertisementVo implements Serializable {

    /**
     * id
     */
    private Long id;

    /**
     * 创建时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date createTime;

    /**
     * 广告名称
     */
    private String advertName;

    /**
     * 市
     */
    private String city;

    /**
     * 区
     */
    private String area;

    /**
     * 广告语
     */
    private String advertRemark;

    /**
     * 广告图标
     */
    private String advertIcon;

    /**
     * 广告宣传图
     */
    private String advertPic;

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
     * 广告状态
     */
    private String advertStatus;

    /**
     * 广告类型
     */
    private String advertisementType;

    /**
     * 总点击/浏览量
     */
    private Long totalCount;

    /**
     * 总访问奖励星点数
     */
    private Double totalAccessStarPoint;

    /**
     * 总点击奖励星点数
     */
    private Double totalClickStarPoint;

    /**
     * 单次访问奖励星点
     */
    private Double onceAccessStarPoint;

    /**
     * 单次点击奖励星点
     */
    private Double onceClickStarPoint;

    /**
     * 是否已经推送
     */
    private Boolean isSend;

    /**
     * 广告链接
     */
    private String advertisementLink;

    /**
     * 广告描述
     */
    private String advertisementDescribe;

    /**
     * 广告属性
     */
    private String advertisementAttribute;

    public AdvertisementVo() {
    }

    public AdvertisementVo(Long id, Date createTime, String advertName, String city, String area, String advertRemark, String advertIcon, String advertPic, Date beginTime, Date endTime, String advertStatus, String advertisementType, Long totalCount, Double totalAccessStarPoint, Double onceAccessStarPoint, Boolean isSend, String advertisementLink, String advertisementDescribe, String advertisementAttribute, Double onceClickStarPoint, Double totalClickStarPoint) {
        this.advertisementLink = advertisementLink;
        this.id = id;
        this.createTime = createTime;
        this.advertName = advertName;
        this.city = city;
        this.area = area;
        this.advertRemark = advertRemark;
        this.advertIcon = advertIcon;
        this.advertPic = advertPic;
        this.beginTime = beginTime;
        this.endTime = endTime;
        this.advertStatus = advertStatus;
        this.advertisementType = advertisementType;
        this.totalCount = totalCount;
        this.totalAccessStarPoint = totalAccessStarPoint;
        this.onceAccessStarPoint = onceAccessStarPoint;
        this.isSend = isSend;
        this.advertisementDescribe = advertisementDescribe;
        this.advertisementAttribute = advertisementAttribute;
        this.onceClickStarPoint = onceClickStarPoint;
        this.totalClickStarPoint = totalClickStarPoint;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getAdvertName() {
        return advertName;
    }

    public void setAdvertName(String advertName) {
        this.advertName = advertName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getAdvertRemark() {
        return advertRemark;
    }

    public void setAdvertRemark(String advertRemark) {
        this.advertRemark = advertRemark;
    }

    public String getAdvertIcon() {
        return advertIcon;
    }

    public void setAdvertIcon(String advertIcon) {
        this.advertIcon = advertIcon;
    }

    public String getAdvertPic() {
        return advertPic;
    }

    public void setAdvertPic(String advertPic) {
        this.advertPic = advertPic;
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

    public String getAdvertStatus() {
        return advertStatus;
    }

    public void setAdvertStatus(String advertStatus) {
        this.advertStatus = advertStatus;
    }

    public String getAdvertisementType() {
        return advertisementType;
    }

    public void setAdvertisementType(String advertisementType) {
        this.advertisementType = advertisementType;
    }

    public Long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Long totalCount) {
        this.totalCount = totalCount;
    }

    public Boolean getIsSend() {
        return isSend;
    }

    public void setIsSend(Boolean isSend) {
        this.isSend = isSend;
    }

    public String getAdvertisementLink() {
        return advertisementLink;
    }

    public void setAdvertisementLink(String advertisementLink) {
        this.advertisementLink = advertisementLink;
    }

    public String getAdvertisementDescribe() {
        return advertisementDescribe;
    }

    public void setAdvertisementDescribe(String advertisementDescribe) {
        this.advertisementDescribe = advertisementDescribe;
    }

    public String getAdvertisementAttribute() {
        return advertisementAttribute;
    }

    public void setAdvertisementAttribute(String advertisementAttribute) {
        this.advertisementAttribute = advertisementAttribute;
    }

    public Double getOnceAccessStarPoint() {
        return onceAccessStarPoint;
    }

    public void setOnceAccessStarPoint(Double onceAccessStarPoint) {
        this.onceAccessStarPoint = onceAccessStarPoint;
    }

    public Double getOnceClickStarPoint() {
        return onceClickStarPoint;
    }

    public void setOnceClickStarPoint(Double onceClickStarPoint) {
        this.onceClickStarPoint = onceClickStarPoint;
    }

    public Double getTotalAccessStarPoint() {
        return totalAccessStarPoint;
    }

    public void setTotalAccessStarPoint(Double totalAccessStarPoint) {
        this.totalAccessStarPoint = totalAccessStarPoint;
    }

    public Double getTotalClickStarPoint() {
        return totalClickStarPoint;
    }

    public void setTotalClickStarPoint(Double totalClickStarPoint) {
        this.totalClickStarPoint = totalClickStarPoint;
    }
}
