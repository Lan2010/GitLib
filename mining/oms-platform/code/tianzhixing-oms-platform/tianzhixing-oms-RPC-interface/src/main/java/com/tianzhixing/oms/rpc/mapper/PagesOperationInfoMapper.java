package com.tianzhixing.oms.rpc.mapper;

import com.tianzhixing.oms.commons.em.ClientPlatformType;
import com.tianzhixing.oms.commons.em.LoginStatus;

import java.io.Serializable;
import java.util.Date;

/**
 * 页面操作信息表
 * Created by routine.k on 2018/6/12.
 */
public class PagesOperationInfoMapper implements Serializable {

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
     * 微信ID
     */
    private String wxID;

    /**
     * 访问时间
     */
    private Long accessTime;

    /**
     * 页面地址
     */
    private String pagesURL;

    /**
     * 页面信息
     */
    private String pagesInfo;

    /**
     * 页面名字
     */
    private String pagesName;

    /**
     * 登录状态
     */
    private LoginStatus loginStatus;

    /**
     * ip
     */
    private String ip;

    public PagesOperationInfoMapper() {
    }

    public PagesOperationInfoMapper(String id, Date createTime, String platformFrom, String clientPlatformType, String mobile, String wxID, Long accessTime, String pagesURL, String pagesInfo, String pagesName, LoginStatus loginStatus, String ip) {
        this.id = id;
        this.createTime = createTime;
        this.platformFrom = platformFrom;
        this.clientPlatformType = clientPlatformType;
        this.mobile = mobile;
        this.wxID = wxID;
        this.accessTime = accessTime;
        this.pagesURL = pagesURL;
        this.pagesInfo = pagesInfo;
        this.pagesName = pagesName;
        this.loginStatus = loginStatus;
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

    public String getWxID() {
        return wxID;
    }

    public void setWxID(String wxID) {
        this.wxID = wxID;
    }

    public Long getAccessTime() {
        return accessTime;
    }

    public void setAccessTime(Long accessTime) {
        this.accessTime = accessTime;
    }

    public String getPagesURL() {
        return pagesURL;
    }

    public void setPagesURL(String pagesURL) {
        this.pagesURL = pagesURL;
    }

    public String getPagesInfo() {
        return pagesInfo;
    }

    public void setPagesInfo(String pagesInfo) {
        this.pagesInfo = pagesInfo;
    }

    public String getPagesName() {
        return pagesName;
    }

    public void setPagesName(String pagesName) {
        this.pagesName = pagesName;
    }

    public LoginStatus getLoginStatus() {
        return loginStatus;
    }

    public void setLoginStatus(LoginStatus loginStatus) {
        this.loginStatus = loginStatus;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }
}
