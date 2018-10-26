package com.tianzhixing.oms.bussiness.rpc.mapper.advertisement;

import com.tianzhixing.bussiness.commons.em.AdvertisementAttribute;
import com.tianzhixing.bussiness.commons.em.AdvertisementStatus;
import com.tianzhixing.bussiness.commons.em.AdvertisementType;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by routine.k on 2018/6/22.
 */
public class AdvertisementMapper implements Serializable {

    /**
     * id
     */
    private Long id;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 创建人
     */
    private String createUser;

    /**
     * 更新人
     */
    private String updateUser;

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
    private Date beginTime;

    /**
     * 结束时间
     */
    private Date endTime;

    /**
     * 广告状态
     */
    private AdvertisementStatus advertStatus;

    /**
     * 广告类型
     */
    private AdvertisementType advertisementType;

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
    private AdvertisementAttribute advertisementAttribute;

    public AdvertisementMapper() {
    }

    public AdvertisementMapper(Long id, Date createTime, Date updateTime, String createUser, String updateUser, String advertName, String city, String area, String advertRemark, String advertIcon, String advertPic, Date beginTime, Date endTime, AdvertisementStatus advertStatus, AdvertisementType advertisementType, Long totalCount, Double totalAccessStarPoint, Double onceAccessStarPoint, Boolean isSend, String advertisementLink, String advertisementDescribe, AdvertisementAttribute advertisementAttribute, Double onceClickStarPoint, Double totalClickStarPoint) {
        this.advertisementAttribute = advertisementAttribute;
        this.id = id;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.createUser = createUser;
        this.updateUser = updateUser;
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
        this.advertisementLink = advertisementLink;
        this.advertisementDescribe = advertisementDescribe;
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

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
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

    public AdvertisementStatus getAdvertStatus() {
        return advertStatus;
    }

    public void setAdvertStatus(AdvertisementStatus advertStatus) {
        this.advertStatus = advertStatus;
    }

    public AdvertisementType getAdvertisementType() {
        return advertisementType;
    }

    public void setAdvertisementType(AdvertisementType advertisementType) {
        this.advertisementType = advertisementType;
    }

    public Long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Long totalCount) {
        this.totalCount = totalCount;
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

    public AdvertisementAttribute getAdvertisementAttribute() {
        return advertisementAttribute;
    }

    public void setAdvertisementAttribute(AdvertisementAttribute advertisementAttribute) {
        this.advertisementAttribute = advertisementAttribute;
    }
}
