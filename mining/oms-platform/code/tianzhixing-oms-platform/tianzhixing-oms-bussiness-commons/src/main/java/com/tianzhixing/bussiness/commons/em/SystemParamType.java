package com.tianzhixing.bussiness.commons.em;

import java.util.HashSet;
import java.util.Set;

/**
 * 系统参数类型
 * Created by routine.k on 2018/6/25.
 */
public enum SystemParamType {
    TASKDISTANCE(0, "任务半径"),
    DEFAULTRATE(1, "默认汇率"),
    REGAWARD(3, "注册奖励"),
    AUTHIDCARD(2, "实名认证奖励"),
    GENE(4, "基因奖励"),
    INVITATION(5, "邀请用户奖励"),
    ADDRESS(6, "添加地址奖励"),
    ATTENTIONWEBCHAT(7, "关注公众号奖励"),
    VOICEDISCERN(8, "声音识别奖励"),
    FACEDEISCEERN(9, "人脸识别奖励"),
    BINDBANK(10, "绑定银行卡奖励"),
    HARDWAREBIND(11, "矿机绑定奖励"),
    STARPOINTLOST(12, "采集有效期"),
    BINDADDRESSLIST(13, "绑定通讯录奖励（每人）"),
    MAXBINDADDRESSLIST(14, "绑定通讯录奖励上限");

    /**
     * 奖励清单
     */
    public static Set<SystemParamType> AWARDSET = new HashSet<>();

    static {
        AWARDSET.add(AUTHIDCARD);
        AWARDSET.add(GENE);
        AWARDSET.add(ADDRESS);
        AWARDSET.add(ATTENTIONWEBCHAT);
        AWARDSET.add(FACEDEISCEERN);
        AWARDSET.add(BINDBANK);
        AWARDSET.add(HARDWAREBIND);
        AWARDSET.add(VOICEDISCERN);
    }

    /**
     * 标识码
     */
    private int code;

    /**
     * 资源值
     */
    private String value;

    SystemParamType(int code, String value) {
        this.code = code;
        this.value = value;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

}
