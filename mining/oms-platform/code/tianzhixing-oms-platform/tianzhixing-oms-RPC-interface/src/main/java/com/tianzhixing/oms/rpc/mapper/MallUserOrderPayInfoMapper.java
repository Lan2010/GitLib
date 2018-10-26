package com.tianzhixing.oms.rpc.mapper;

import java.io.Serializable;
import java.util.Date;

/**
 * 商城用户订单支付信息表
 * Created by routine.k on 2018/6/16.
 */
public class MallUserOrderPayInfoMapper implements Serializable {

    /**
     * id
     */
    private String id;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 请求平台来源
     */
    private String platformFrom;

    /**
     * 客户端类型
     */
    private String clientPlatformType;

    /**
     * 手机号(可能为空)
     */
    private String mobile;

    /**
     * 订单号
     */
    private String orderNum;

    /**
     * 订单状态(0=待支付，1=已支付, 3=支付失败 )
     */
    private Integer orderStatus;

    /**
     * 订单额度
     */
    private Double orderAmount;

    /**
     * 产品信息
     */
    private String productInfo;

    /**
     * 操作时间
     */
    private Long operationTime;

    /**
     * 微信ID
     */
    private String wxID;

    /**
     * QQID
     */
    private String qqID;

    /**
     * 新浪微博ID
     */
    private String sinaWeiBoID;

    /**
     * ip
     */
    private String ip;

    public MallUserOrderPayInfoMapper(String id, Date createTime, String platformFrom, String clientPlatformType, String mobile, String orderNum, Integer orderStatus, Double orderAmount, String productInfo, Long operationTime, String wxID, String qqID, String sinaWeiBoID, String ip) {
        this.id = id;
        this.createTime = createTime;
        this.platformFrom = platformFrom;
        this.clientPlatformType = clientPlatformType;
        this.mobile = mobile;
        this.orderNum = orderNum;
        this.orderStatus = orderStatus;
        this.orderAmount = orderAmount;
        this.productInfo = productInfo;
        this.operationTime = operationTime;
        this.wxID = wxID;
        this.qqID = qqID;
        this.sinaWeiBoID = sinaWeiBoID;
        this.ip = ip;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getPlatformFrom() {
        return platformFrom;
    }

    public void setPlatformFrom(String platformFrom) {
        this.platformFrom = platformFrom;
    }

    public String getClientPlatformType() {
        return clientPlatformType;
    }

    public void setClientPlatformType(String clientPlatformType) {
        this.clientPlatformType = clientPlatformType;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }

    public Integer getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Double getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(Double orderAmount) {
        this.orderAmount = orderAmount;
    }

    public String getProductInfo() {
        return productInfo;
    }

    public void setProductInfo(String productInfo) {
        this.productInfo = productInfo;
    }

    public Long getOperationTime() {
        return operationTime;
    }

    public void setOperationTime(Long operationTime) {
        this.operationTime = operationTime;
    }

    public String getWxID() {
        return wxID;
    }

    public void setWxID(String wxID) {
        this.wxID = wxID;
    }

    public String getQqID() {
        return qqID;
    }

    public void setQqID(String qqID) {
        this.qqID = qqID;
    }

    public String getSinaWeiBoID() {
        return sinaWeiBoID;
    }

    public void setSinaWeiBoID(String sinaWeiBoID) {
        this.sinaWeiBoID = sinaWeiBoID;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }
}
