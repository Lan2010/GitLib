package com.tianzhixing.oms.rpc.mapper;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户基础信息表
 * Created by routine.k on 2018/6/12.
 */
public class UserBasicInfoMapper implements Serializable {

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
     * 手机号
     */
    private String mobile;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 真实姓名
     */
    private String realName;

    /**
     * 身份证号码
     */
    private String idCard;

    /**
     * 昵称
     */
    private String nickName;

    /**
     * 用户渠道
     */
    private String userFromType;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 用户操作类型
     */
    private String userOperType;

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
     * 注册时间
     */
    private Long regTime;

    /**
     * ip
     */
    private String ip;

    public UserBasicInfoMapper() {
    }

    public UserBasicInfoMapper(String id, Date createTime, String platformFrom, String clientPlatformType, String mobile, String email, String realName, String idCard, String nickName, String userFromType, String avatar, String userOperType, String wxID, String qqID, String sinaWeiBoID, Long regTime, String ip) {
        this.id = id;
        this.createTime = createTime;
        this.platformFrom = platformFrom;
        this.clientPlatformType = clientPlatformType;
        this.mobile = mobile;
        this.email = email;
        this.realName = realName;
        this.idCard = idCard;
        this.nickName = nickName;
        this.userFromType = userFromType;
        this.avatar = avatar;
        this.userOperType = userOperType;
        this.wxID = wxID;
        this.qqID = qqID;
        this.sinaWeiBoID = sinaWeiBoID;
        this.regTime = regTime;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getUserFromType() {
        return userFromType;
    }

    public void setUserFromType(String userFromType) {
        this.userFromType = userFromType;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getUserOperType() {
        return userOperType;
    }

    public void setUserOperType(String userOperType) {
        this.userOperType = userOperType;
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

    public Long getRegTime() {
        return regTime;
    }

    public void setRegTime(Long regTime) {
        this.regTime = regTime;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }
}
