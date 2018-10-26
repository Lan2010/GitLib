package com.tianzhixing.kernel.rpc.mapper.account;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by routine.k on 2018/4/27.
 */
public class AccountInfoMapper implements Serializable {

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
     * 更新时间
     */
    private Date updateTime;

    /**
     * 注册时间
     */
    private Date regTime;

    /**
     * 手机号
     */
    private String mobile;

    /**
     * 账户token
     */
    private String accountToken;

    /**
     * 机构
     */
    private String org;

    /**
     * 第三方token
     */
    private String thirdToken;

    public AccountInfoMapper() {
    }

    public AccountInfoMapper(Long id, Integer version, Date createTime, Date updateTime, Date regTime, String mobile, String accountToken, String org, String thirdToken) {
        this.id = id;
        this.version = version;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.regTime = regTime;
        this.mobile = mobile;
        this.accountToken = accountToken;
        this.org = org;
        this.thirdToken = thirdToken;
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

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Date getRegTime() {
        return regTime;
    }

    public void setRegTime(Date regTime) {
        this.regTime = regTime;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getAccountToken() {
        return accountToken;
    }

    public void setAccountToken(String accountToken) {
        this.accountToken = accountToken;
    }

    public String getOrg() {
        return org;
    }

    public void setOrg(String org) {
        this.org = org;
    }

    public String getThirdToken() {
        return thirdToken;
    }

    public void setThirdToken(String thirdToken) {
        this.thirdToken = thirdToken;
    }

}
