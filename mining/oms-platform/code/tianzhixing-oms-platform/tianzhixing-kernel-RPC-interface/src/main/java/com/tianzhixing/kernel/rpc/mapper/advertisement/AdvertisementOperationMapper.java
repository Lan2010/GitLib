package com.tianzhixing.kernel.rpc.mapper.advertisement;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by routine.k on 2018/6/22.
 */
public class AdvertisementOperationMapper implements Serializable {

    /**
     * 广告ID
     */
    private String advertisementId;

    /**
     * 点击一次的星点数
     */
    private Double starPoint;

    /**
     * 点击时间
     */
    private Date clickTime;

    public AdvertisementOperationMapper() {
    }

    public AdvertisementOperationMapper(String advertisementId, Double starPoint, Date clickTime) {
        this.advertisementId = advertisementId;
        this.starPoint = starPoint;
        this.clickTime = clickTime;
    }

    public Double getStarPoint() {
        return starPoint;
    }

    public void setStarPoint(Double starPoint) {
        this.starPoint = starPoint;
    }

    public Date getClickTime() {
        return clickTime;
    }

    public void setClickTime(Date clickTime) {
        this.clickTime = clickTime;
    }

    public String getAdvertisementId() {
        return advertisementId;
    }

    public void setAdvertisementId(String advertsimentId) {
        this.advertisementId = advertsimentId;
    }
}
