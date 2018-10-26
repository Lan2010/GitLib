package com.tianzhixing.oms.model;

import com.tianzhixing.oms.model.annotation.Column;
import com.tianzhixing.oms.model.annotation.PrimaryKey;
import com.tianzhixing.oms.model.annotation.Table;

import java.io.Serializable;
import java.util.Date;

/**
 * 商城用户订单支付信息表
 * Created by routine.k on 2018/6/16.
 */
@Table(name = "mall_user_order_pay_info")
public class MallUserOrderPayInfoModel implements Serializable {

    /**
     * id
     */
    @PrimaryKey
    @Column(name = "id")
    private String id;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 请求平台来源
     */
    @Column(name = "platform_from")
    private String platformFrom;

    /**
     * 客户端类型
     */
    @Column(name = "client_platform_type")
    private String clientPlatformType;

    /**
     * 手机号(可能为空)
     */
    @Column(name = "mobile")
    private String mobile;

    /**
     * 订单号
     */
    @Column(name = "order_num")
    private String orderNum;

    /**
     * 订单状态(0=待支付，1=已支付, 3=支付失败 )
     */
    @Column(name = "order_status")
    private Integer orderStatus;

    /**
     * 订单额度
     */
    @Column(name = "order_amount")
    private Double orderAmount;

    /**
     * 产品信息
     */
    @Column(name = "product_info")
    private String productInfo;

    /**
     * 操作时间
     */
    @Column(name = "operation_time")
    private Long operationTime;

    /**
     * 微信ID
     */
    @Column(name = "wx_id")
    private String wxID;

    /**
     * QQID
     */
    @Column(name = "qq_id")
    private String qqID;

    /**
     * 新浪微博ID
     */
    @Column(name = "sina_weibo_id")
    private String sinaWeiBoID;

    /**
     * ip
     */
    @Column(name = "ip")
    private String ip;

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
