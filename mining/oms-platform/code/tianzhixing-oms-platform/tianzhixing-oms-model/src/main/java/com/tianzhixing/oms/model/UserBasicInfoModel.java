package com.tianzhixing.oms.model;

import com.tianzhixing.oms.model.annotation.Column;
import com.tianzhixing.oms.model.annotation.PrimaryKey;
import com.tianzhixing.oms.model.annotation.Table;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户基础信息表
 * Created by routine.k on 2018/6/12.
 */
@Table(name = "user_basic_info")
public class UserBasicInfoModel implements Serializable {

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
     * 手机号
     */
    @Column(name = "mobile")
    private String mobile;

    /**
     * 邮箱
     */
    @Column(name = "email")
    private String email;

    /**
     * 真实姓名
     */
    @Column(name = "real_name")
    private String realName;

    /**
     * 身份证号码
     */
    @Column(name = "id_card")
    private String idCard;

    /**
     * 昵称
     */
    @Column(name = "nick_name")
    private String nickName;

    /**
     * 用户渠道
     */
    @Column(name = "user_from_type")
    private String userFromType;

    /**
     * 头像
     */
    @Column(name = "avatar")
    private String avatar;

    /**
     * 用户操作类型
     */
    @Column(name = "user_oper_type")
    private String userOperType;

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
     * 注册时间
     */
    @Column(name = "reg_time")
    private Long regTime;

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
