package com.tianzhixing.common.auth.verification.yuanjian;

import com.tianzhixing.common.auth.utils.JSONUtil;

import java.io.Serializable;

/**
 * Created by routine.k on 2018/5/22.
 */
public class YuanJianRequestEntity implements Serializable {

    private String member_id = YuanJianConfig.getMemberID();

    private String terminal_id = YuanJianConfig.getTerminalID();

    private String data_type = YuanJianConfig.getDataType();

    private String data_content;

    public String getMember_id() {
        return member_id;
    }

    public void setMember_id(String member_id) {
        this.member_id = member_id;
    }

    public String getTerminal_id() {
        return terminal_id;
    }

    public void setTerminal_id(String terminal_id) {
        this.terminal_id = terminal_id;
    }

    public String getData_type() {
        return data_type;
    }

    public void setData_type(String data_type) {
        this.data_type = data_type;
    }

    public String getData_content() {
        return data_content;
    }

    public void setData_content(String data_content) {
        this.data_content = data_content;
    }

    public void setData_content(YuanJianRequestDataContentEntity data_content) {
        this.data_content = JSONUtil.beanToJson(data_content, true);
    }
}
