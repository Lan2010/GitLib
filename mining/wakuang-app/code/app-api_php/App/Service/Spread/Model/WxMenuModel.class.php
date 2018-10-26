<?php

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

namespace Service\Spread\Model;

use Common\Model\SlaveModel;

/**
 * Description of WxMenuModel
 *
 * @author Administrator
 */
class WxMenuModel extends SlaveModel {

    /**
     * 获得微信自定义菜单
     * @param type $where       查询条件
     * return array    
     */
    public function getWxMenuList($where) {
        $where['status'] = 1;
        return $this->order('parent_id asc, sort asc')->where($where)->select();
    }

    /**
     * 获取指定菜单。
     * @param type $menuID
     */
    public function getWxMenu($menuID = 0) {
        if ($menuID * 1 < 1)
            return false;
        $where['menu_id'] = $menuID;
        return $this->where($where)->find();
    }

    /**
     * 保存微信菜单
     * @param type $data    需要保存的数据
     * @param type $menuID   
     */
    public function saveOrAddWxMenu($data, $menuID = 0) {
        if (!empty($menuID) && $menuID * 1 > 0) {
            $where['menu_id'] = $menuID;
            return $this->data($data)->where($where)->save();
        } else {
            return $this->data($data)->add();
        }
    }

    /**
     * 获取指定菜单下有多少子菜单
     * @param type $where       
     * @return int
     */
    public function getMenuCount($menuID) {
        if (empty($menuID) && $menuID * 1 <= 0) {
            return false;
        }
        $where['status'] = 1;
        $where['parent_id'] = $menuID;
        return $this->where($where)->count();
    }

}
