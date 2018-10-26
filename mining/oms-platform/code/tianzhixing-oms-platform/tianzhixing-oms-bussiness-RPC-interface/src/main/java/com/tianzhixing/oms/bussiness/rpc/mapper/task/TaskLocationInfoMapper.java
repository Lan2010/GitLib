package com.tianzhixing.oms.bussiness.rpc.mapper.task;

import com.tianzhixing.bussiness.commons.em.MapType;

import java.io.Serializable;

/**
 * Created by routine.k on 2018/6/24.
 */
public class TaskLocationInfoMapper implements Serializable {

    /**
     * id
     */
    private Long id;

    /**
     * 所属任务
     */
    private Long taskId;

    /**
     * 是否可用
     */
    private Boolean enable;

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
     * 坐标类型
     */
    private String coordType;

    /**
     * 地图类型
     */
    private MapType mapType;

    /**
     * 街道ID
     */
    private String streetId;

    public TaskLocationInfoMapper() {
    }

    public TaskLocationInfoMapper(Long id, Long taskId, Boolean enable, String name, String location, String address, String province, String city, String area, String coordType, MapType mapType, String streetId) {
        this.id = id;
        this.taskId = taskId;
        this.enable = enable;
        this.name = name;
        this.location = location;
        this.address = address;
        this.province = province;
        this.city = city;
        this.area = area;
        this.coordType = coordType;
        this.mapType = mapType;
        this.streetId = streetId;
    }

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

    public Boolean getEnable() {
        return enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
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

    public String getCoordType() {
        return coordType;
    }

    public void setCoordType(String coordType) {
        this.coordType = coordType;
    }

    public MapType getMapType() {
        return mapType;
    }

    public void setMapType(MapType mapType) {
        this.mapType = mapType;
    }

    public String getStreetId() {
        return streetId;
    }

    public void setStreetId(String streetId) {
        this.streetId = streetId;
    }
}
