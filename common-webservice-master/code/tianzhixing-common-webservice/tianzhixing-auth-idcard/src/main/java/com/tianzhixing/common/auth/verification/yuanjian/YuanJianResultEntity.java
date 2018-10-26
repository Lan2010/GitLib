package com.tianzhixing.common.auth.verification.yuanjian;

import java.io.Serializable;

/**
 * Created by routine.k on 2018/5/22.
 */
public class YuanJianResultEntity implements Serializable {

    private Boolean success;

    private String errorCode;

    private String errorMsg;

    private YuanJianResultDataEntity data;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public YuanJianResultDataEntity getData() {
        return data;
    }

    public void setData(YuanJianResultDataEntity data) {
        this.data = data;
    }
}
