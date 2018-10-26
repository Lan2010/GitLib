package com.tianzhixing.oms.redis.bussiness;

import java.io.Serializable;

/**
 * Created by routine.k on 2018/6/26.
 */
public class TaskLocationEntity implements Serializable {

    /**
     * id
     */
    private Long id;

    /**
     * 版本号
     */
    private Integer version;

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
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
}
