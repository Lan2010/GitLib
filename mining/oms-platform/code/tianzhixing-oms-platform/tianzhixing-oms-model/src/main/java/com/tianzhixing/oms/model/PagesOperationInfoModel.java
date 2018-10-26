package com.tianzhixing.oms.model;

import com.tianzhixing.oms.commons.em.LoginStatus;
import com.tianzhixing.oms.model.annotation.Column;
import com.tianzhixing.oms.model.annotation.PrimaryKey;
import com.tianzhixing.oms.model.annotation.Table;

import java.io.Serializable;
import java.util.Date;

/**
 * 页面操作信息表
 * Created by routine.k on 2018/6/12.
 */
@Table(name = "pages_operation_info")
public class PagesOperationInfoModel implements Serializable {

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
     * 微信ID
     */
    @Column(name = "wx_id")
    private String wxID;

    /**
     * 访问时间
     */
    @Column(name = "access_time")
    private Long accessTime;

    /**
     * 页面地址
     */
    @Column(name = "pages_url")
    private String pagesURL;

    /**
     * 页面信息
     */
    @Column(name = "pages_info")
    private String pagesInfo;

    /**
     * 页面名字
     */
    @Column(name = "pages_name")
    private String pagesName;

    /**
     * 登录状态
     */
    @Column(name = "login_status")
    private LoginStatus loginStatus;

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
