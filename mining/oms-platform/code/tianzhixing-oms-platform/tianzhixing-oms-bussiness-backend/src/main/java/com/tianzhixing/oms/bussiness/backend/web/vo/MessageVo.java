package com.tianzhixing.oms.bussiness.backend.web.vo;

import java.io.Serializable;

/**
 * Created by routine.k on 16/8/15.
 */
public class MessageVo implements Serializable {

    /**
     * id
     */
    private Long id;

    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 信息content
     */
    private String content;

    /**
     * 是否已读
     */
    private String isRead;

    /**
     * 标题
     */
    private String title;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getIsRead() {
        return isRead;
    }

    public void setIsRead(String isRead) {
        this.isRead = isRead;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
