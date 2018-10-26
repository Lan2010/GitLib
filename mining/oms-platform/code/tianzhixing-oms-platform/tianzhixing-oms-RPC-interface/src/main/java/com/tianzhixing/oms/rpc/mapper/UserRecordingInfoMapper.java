package com.tianzhixing.oms.rpc.mapper;

import com.tianzhixing.oms.commons.em.ClientPlatformType;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户录音信息表
 * Created by routine.k on 2018/6/16.
 */
public class UserRecordingInfoMapper implements Serializable {

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
     * 声音ID
     */
    private String voiceId;

    /**
     * 声音链接
     */
    private String voiceLink;

    /**
     * 昵称
     */
    private String nickName;

    /**
     * 微信ID
     */
    private String wxID;

    /**
     * 录音时间
     */
    private Long recordingTime;

    public UserRecordingInfoMapper() {
    }

    public UserRecordingInfoMapper(String id, Date createTime, String platformFrom, String clientPlatformType, String mobile, String voiceId, String voiceLink, String nickName, String wxID, Long recordingTime) {
        this.id = id;
        this.createTime = createTime;
        this.platformFrom = platformFrom;
        this.clientPlatformType = clientPlatformType;
        this.mobile = mobile;
        this.voiceId = voiceId;
        this.voiceLink = voiceLink;
        this.nickName = nickName;
        this.wxID = wxID;
        this.recordingTime = recordingTime;
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

    public String getVoiceId() {
        return voiceId;
    }

    public void setVoiceId(String voiceId) {
        this.voiceId = voiceId;
    }

    public String getVoiceLink() {
        return voiceLink;
    }

    public void setVoiceLink(String voiceLink) {
        this.voiceLink = voiceLink;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getWxID() {
        return wxID;
    }

    public void setWxID(String wxID) {
        this.wxID = wxID;
    }

    public Long getRecordingTime() {
        return recordingTime;
    }

    public void setRecordingTime(Long recordingTime) {
        this.recordingTime = recordingTime;
    }
}
