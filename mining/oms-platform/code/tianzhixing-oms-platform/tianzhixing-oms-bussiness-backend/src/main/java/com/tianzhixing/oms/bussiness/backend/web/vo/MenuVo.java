package com.tianzhixing.oms.bussiness.backend.web.vo;

import java.util.List;

/**
 * Created by routine.k on 16/8/16.
 */
public class MenuVo {

    /**
     * id
     */
    private Integer id;

    /**
     * 名称
     */
    private String name;

    /**
     * 父类id
     */
    private Integer pId;

    /**
     * 链接地址
     */
    private String url;

    /**
     * 图片
     */
    private String pic;

    /**
     * 子类节点
     */
    private List<MenuVo> node;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getpId() {
        return pId;
    }

    public void setpId(Integer pId) {
        this.pId = pId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<MenuVo> getNode() {
        return node;
    }

    public void setNode(List<MenuVo> node) {
        this.node = node;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }
}
