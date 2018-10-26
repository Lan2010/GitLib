package com.tianzhixing.common.auth.verification.yuanjian;

import java.io.Serializable;

/**
 * Created by routine.k on 2018/5/22.
 */
public class YuanJianRequestDataContentEntity implements Serializable {

    private String member_id = YuanJianConfig.getMemberID();

    private String terminal_id = YuanJianConfig.getTerminalID();

    private String trans_id;

    private String trade_date;

    private String id_card;

    private String id_holder;

    private String industry_type;

    private String is_photo;

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

    public String getTrans_id() {
        return trans_id;
    }

    public void setTrans_id(String trans_id) {
        this.trans_id = trans_id;
    }

    public String getTrade_date() {
        return trade_date;
    }

    public void setTrade_date(String trade_date) {
        this.trade_date = trade_date;
    }

    public String getId_card() {
        return id_card;
    }

    public void setId_card(String id_card) {
        this.id_card = id_card;
    }

    public String getId_holder() {
        return id_holder;
    }

    public void setId_holder(String id_holder) {
        this.id_holder = id_holder;
    }

    public String getIndustry_type() {
        return industry_type;
    }

    public void setIndustry_type(String industry_type) {
        this.industry_type = industry_type;
    }

    public String getIs_photo() {
        return is_photo;
    }

    public void setIs_photo(String is_photo) {
        this.is_photo = is_photo;
    }
}
