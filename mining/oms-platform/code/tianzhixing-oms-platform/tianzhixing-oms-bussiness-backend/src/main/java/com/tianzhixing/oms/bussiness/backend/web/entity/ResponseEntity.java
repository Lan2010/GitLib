package com.tianzhixing.oms.bussiness.backend.web.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * ResponseEntity
 * Created by routine on 14-10-17.
 */
public class ResponseEntity<T> implements Serializable {

    /**
     * 返回状态码
     */
    private int code;

    /**
     * 提示信息
     */
    private String msg;

    /**
     * 数据集
     */
    private Data<T> data;

    public ResponseEntity(int _code, String _message) {
        this.code = _code;
        this.msg = _message;
    }

    public ResponseEntity(int _code, String _message, long _total, List _list, Object _object) {
        this.code = _code;
        this.msg = _message;
        data = new Data<T>(_total, _list, _object);
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

    public Data<T> getData() {
        return data;
    }

    public void setData(Data<T> data) {
        this.data = data;
    }

    /**
     * 数量模型
     *
     * @param <T>
     */
    class Data<T> implements Serializable {

        public Data(long total, List<T> list, Object object) {
            this.total = total;
            this.list = list;
            this.object = object;
        }

        /**
         * 总数
         */
        private long total = 0;

        /**
         * 数据集合
         */
        private List<T> list = new ArrayList<T>();

        /**
         * 数据模型
         */
        private Object object;

        public long getTotal() {
            return total;
        }

        public void setTotal(long total) {
            this.total = total;
        }

        public List<T> getList() {
            return list;
        }

        public void setList(List<T> list) {
            this.list = list;
        }

        public Object getObject() {
            return object;
        }

        public void setObject(Object object) {
            this.object = object;
        }
    }
}


