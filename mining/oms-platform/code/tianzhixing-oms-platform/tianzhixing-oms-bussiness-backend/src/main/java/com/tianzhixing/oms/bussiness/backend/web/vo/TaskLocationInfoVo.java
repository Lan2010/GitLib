package com.tianzhixing.oms.bussiness.backend.web.vo;

import java.io.Serializable;

/**
 * Created by routine.k on 2018/6/24.
 */
public class TaskLocationInfoVo implements Serializable {

    /**
     * id
     */
    private Long id;

    /**
     * 所属任务
     */
    private Long taskId;

    /**
     * 任务名称
     */
    private String taskName;

    /**
     * 是否可用
     */
    private Boolean enable;

    /**
     * 状态
     */
    private String status;

    /**
     * 名字
     */
    private String name;

    /**
     * 经纬度（格式:lat|lng）
     */
    private String location;

    /**
     * 地址
     */
    private String address;

    /**
     * 省份
     */
    private String province;

    /**
     * 城市
     */
    private String city;

    /**
     * 区域
     */
    private String area;

    /**
     * 纬度
     */
    private String lat;

    /**
     * 经度
     */
    private String lng;

    /**
     * 地图类型
     */
    private String mapType;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public Boolean getEnable() {
        return enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public TaskLocationInfoVo() {
    }

    public TaskLocationInfoVo(Long id, Long taskId, String taskName, Boolean enable, String status, String name, String location, String address, String province, String city, String area) {
        this.id = id;
        this.taskId = taskId;
        this.taskName = taskName;
        this.enable = enable;
        this.status = status;
        this.name = name;
        this.location = location;
        this.address = address;
        this.province = province;
        this.city = city;
        this.area = area;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public String getMapType() {
        return mapType;
    }

    public void setMapType(String mapType) {
        this.mapType = mapType;
    }
}
