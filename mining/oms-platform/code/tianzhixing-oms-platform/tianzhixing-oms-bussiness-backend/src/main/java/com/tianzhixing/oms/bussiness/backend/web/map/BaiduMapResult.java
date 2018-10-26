package com.tianzhixing.oms.bussiness.backend.web.map;

/**
 * Created by routine.k on 2018/6/24.
 */
public class BaiduMapResult {

    private String name;
    private BaiduMapLocation location;
    private String address;
    private String province;
    private String city;
    private String area;
    private String street_id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BaiduMapLocation getLocation() {
        return location;
    }

    public void setLocation(BaiduMapLocation location) {
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

    public String getStreet_id() {
        return street_id;
    }

    public void setStreet_id(String street_id) {
        this.street_id = street_id;
    }
}
