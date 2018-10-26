package com.tianzhixing.oms.bussiness.backend.web.map;

import java.util.List;

/**
 * Created by routine.k on 2018/6/24.
 */
public class BaiduMapEntity {

    private int status;

    private String message;

    private int total;

    private List<BaiduMapResult> results;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<BaiduMapResult> getResults() {
        return results;
    }

    public void setResults(List<BaiduMapResult> results) {
        this.results = results;
    }
}
