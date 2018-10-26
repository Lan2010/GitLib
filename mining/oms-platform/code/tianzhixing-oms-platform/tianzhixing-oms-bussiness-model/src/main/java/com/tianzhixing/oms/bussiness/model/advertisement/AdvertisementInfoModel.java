package com.tianzhixing.oms.bussiness.model.advertisement;

import com.tianzhixing.bussiness.commons.em.AdvertisementAttribute;
import com.tianzhixing.bussiness.commons.em.AdvertisementStatus;
import com.tianzhixing.bussiness.commons.em.AdvertisementType;
import com.tianzhixing.oms.bussiness.model.annotation.Column;
import com.tianzhixing.oms.bussiness.model.annotation.PrimaryKey;
import com.tianzhixing.oms.bussiness.model.annotation.Table;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by routine.k on 2018/6/23.
 */
@Table(name = "advertisement_info")
public class AdvertisementInfoModel implements Serializable {

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
     * 更新时间
     */
    @Column(name = "update_time")
    private Date updateTime;

    /**
     * 创建人
     */
    @Column(name = "create_user")
    private String createUser;

    /**
     * 更新人
     */
    @Column(name = "update_user")
    private String updateUser;

    /**
     * 广告名称
     */
    @Column(name = "advert_name")
    private String advertName;

    /**
     * 市
     */
    @Column(name = "city")
    private String city;

    /**
     * 区
     */
    @Column(name = "area")
    private String area;

    /**
     * 广告语
     */
    @Column(name = "advert_remark")
    private String advertRemark;

    /**
     * 广告图标
     */
    @Column(name = "advert_icon")
    private String advertIcon;

    /**
     * 广告宣传图
     */
    @Column(name = "advert_pic")
    private String advertPic;

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
     * 广告状态
     */
    @Column(name = "advert_status")
    private AdvertisementStatus advertStatus;

    /**
     * 广告属性
     */
    @Column(name = "advert_attribute")
    private AdvertisementAttribute advertisementAttribute;

    /**
     * 广告类型
     */
    @Column(name = "advert_type")
    private AdvertisementType advertisementType;

    /**
     * 总点击/浏览量
     */
    @Column(name = "total_count")
    private Long totalCount;

    /**
     * 总访问奖励星点数
     */
    @Column(name = "total_access_starpoint")
    private Double totalAccessStarPoint;

    /**
     * 总点击奖励星点数
     */
    @Column(name = "total_click_starpoint")
    private Double totalClickStarPoint;

    /**
     * 单次访问奖励星点
     */
    @Column(name = "once_access_starpoint")
    private Double onceAccessStarPoint;

    /**
     * 单次点击奖励星点
     */
    @Column(name = "once_click_starpoint")
    private Double onceClickStarPoint;

    /**
     * 是否已经推送
     */
    @Column(name = "is_send")
    private Boolean isSend;

    /**
     * 广告链接
     */
    @Column(name = "advertisement_link")
    private String advertisementLink;

    /**
     * 广告描述
     */
    @Column(name = "advertisement_describe")
    private String advertisementDescribe;


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
}
