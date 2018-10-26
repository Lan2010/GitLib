package com.tianzhixing.oms.web.security.entity;

import java.io.Serializable;

/**
 * ResponseEntity
 * Created by routine on 14-10-17.
 */
public class ResponseEntity implements Serializable {

    /**
     * 返回状态码
     */
    private int code;

    /**
     * 提示信息
     */
    private String msg;

    public ResponseEntity(int _code, String _message) {
        this.code = _code;
        this.msg = _message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

}


