package com.tianzhixing.oms.bussiness.rpc.mapper.statistics;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 商城用户订单支付统计
 * Created by routine.k on 2018/7/4.
 */
public class MallUserOrderPayStatisticsMapper implements Serializable {

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
     * 统计时间-小时
     */
    private Integer statisticsHour;

    /**
     * 统计时间-天
     */
    private Integer statisticsDay;

    /**
     * 统计时间-月
     */
    private Integer statisticsMonth;

    /**
     * 统计时间-年
     */
    private Integer statisticsYear;

    /**
     * 客户端类型
     */
    private String clientPlatformType;

    /**
     * 请求平台来源
     */
    private String platformFrom;

    /**
     * 产品类型
     */
    private String productType;

    /**
     * 产品名称
     */
    private String productName;

    /**
     * 已支付数量
     */
    private Integer alreadyPayCount;

    /**
     * 已支付额度
     */
    private Double alreadyPayAmount;

    /**
     * 待支付数量
     */
    private Integer awaitPayCount;

    /**
     * 待支付额度
     */
    private Double awaitPayAmount;

    /**
     * 支付失败数量
     */
    private Integer failedPayCount;

    /**
     * 支付失败额度
     */
    private Double failedPayAmount;

    /**
     * 平台名称
     */
    private String platformName;

    
    public MallUserOrderPayStatisticsMapper(Long id, Integer version, Date createTime, Integer statisticsHour, Integer statisticsDay, Integer statisticsMonth, Integer statisticsYear, String clientPlatformType, String platformFrom, String productType, String productName, Integer alreadyPayCount, Double alreadyPayAmount, Integer awaitPayCount, Double awaitPayAmount, Integer failedPayCount, Double failedPayAmount, String platformName) {
		this.id = id;
		this.version = version;
		this.createTime = createTime;
		this.statisticsHour = statisticsHour;
		this.statisticsDay = statisticsDay;
		this.statisticsMonth = statisticsMonth;
		this.statisticsYear = statisticsYear;
		this.clientPlatformType = clientPlatformType;
		this.platformFrom = platformFrom;
		this.productType = productType;
		this.productName = productName;
		this.alreadyPayCount = alreadyPayCount;
		this.alreadyPayAmount = alreadyPayAmount;
		this.awaitPayCount = awaitPayCount;
		this.awaitPayAmount = awaitPayAmount;
		this.failedPayCount = failedPayCount;
		this.failedPayAmount = failedPayAmount;
		this.platformName = platformName;
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

    public Integer getStatisticsHour() {
        return statisticsHour;
    }

    public void setStatisticsHour(Integer statisticsHour) {
        this.statisticsHour = statisticsHour;
    }

    public Integer getStatisticsDay() {
        return statisticsDay;
    }

    public void setStatisticsDay(Integer statisticsDay) {
        this.statisticsDay = statisticsDay;
    }

    public Integer getStatisticsMonth() {
        return statisticsMonth;
    }

    public void setStatisticsMonth(Integer statisticsMonth) {
        this.statisticsMonth = statisticsMonth;
    }

    public Integer getStatisticsYear() {
        return statisticsYear;
    }

    public void setStatisticsYear(Integer statisticsYear) {
        this.statisticsYear = statisticsYear;
    }

    public String getClientPlatformType() {
        return clientPlatformType;
    }

    public void setClientPlatformType(String clientPlatformType) {
        this.clientPlatformType = clientPlatformType;
    }

    public String getPlatformFrom() {
        return platformFrom;
    }

    public void setPlatformFrom(String platformFrom) {
        this.platformFrom = platformFrom;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Integer getAlreadyPayCount() {
        return alreadyPayCount;
    }

    public void setAlreadyPayCount(Integer alreadyPayCount) {
        this.alreadyPayCount = alreadyPayCount;
    }

    public Double getAlreadyPayAmount() {
        return alreadyPayAmount;
    }

    public void setAlreadyPayAmount(Double alreadyPayAmount) {
        this.alreadyPayAmount = alreadyPayAmount;
    }

    public Integer getAwaitPayCount() {
        return awaitPayCount;
    }

    public void setAwaitPayCount(Integer awaitPayCount) {
        this.awaitPayCount = awaitPayCount;
    }

    public Double getAwaitPayAmount() {
        return awaitPayAmount;
    }

    public void setAwaitPayAmount(Double awaitPayAmount) {
        this.awaitPayAmount = awaitPayAmount;
    }

    public String getPlatformName() {
        return platformName;
    }

    public void setPlatformName(String platformName) {
        this.platformName = platformName;
    }

    public Integer getFailedPayCount() {
        return failedPayCount;
    }

    public void setFailedPayCount(Integer failedPayCount) {
        this.failedPayCount = failedPayCount;
    }

    public Double getFailedPayAmount() {
        return failedPayAmount;
    }

    public void setFailedPayAmount(Double failedPayAmount) {
        this.failedPayAmount = failedPayAmount;
    }
}
