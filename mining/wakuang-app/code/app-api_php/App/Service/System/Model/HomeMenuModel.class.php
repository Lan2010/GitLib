<?php

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

namespace Service\System\Model;

use Think\Model;
use Common\Model\SlaveModel;
use Think\Exception;

/**
 * Description of HomeMenuModel
 *
 * @author DREAM
 */
class HomeMenuModel extends SlaveModel {

    /**
     * 获取前台菜单
     * @return type
     */
    public function getMenu($where) {
        $where["menuStatus"] = 1;
        $field = "menuName,functionUrl,menuDisplay,parentID,keyword,menuTitle,description";
        $result = $this->SlaveDB()->table("d_home_menu")->field($field)->where($where)->order('menuSort')->select();
        if (is_array($result)) {
            return $result;
        }
    }

    /**
     * 获取全部前台菜单
     * @return type
     */
    public function getHomeMenu($where, $whereArr, $field) {
        $model = $this->SlaveDB()->table("d_home_menu");
        $result = $model->field($field)->where($where, $whereArr)->order('menuSort')->select();
        return $result;
    }

    /**
     * 查询要被修改的菜单信息 
     * @param type $where
     */
    public function getXinMenu($where) {
        $model = $this->SlaveDB()->table("d_home_menu");
        $result = $model->where($where)->find();
        return $result;
    }

    /**
     * 保存前台菜单 
     * @param type $where
     * @param type $data
     */
    public function saveMenu($where, $data) {
        $model = M("HomeMenu");
        try {
            if (is_array($where) && count($where) > 0) {
                $result = $model->where($where)->data($data)->save();
                return $result * 1 > 0 ? $where["menuID"] : false;
            } else {
                return $model->add($data);
            }
        } catch (Exception $ex) {
            logger(__METHOD__, $ex->getMessage());
            return FALSE;
        }
    }

    /**
     * 获取全部的TDK信息
     * @param type $where
     * @param type $whereArr
     */
    public function getTDK($where, $whereArr,$field) {
        $model = $this->SlaveDB()->table("d_home_menu");
        $result= $model->field($field)->where($where, $whereArr)->select();
        return $result;
    }

    /**
     * 查询要被修改的TDK信息
     * @param type $where
     */
    public function getSinTdk($where) {
        $model = $this->SlaveDB()->table("d_home_menu");
        $result = $model->where($where)->find();
        return $result;
    }

}
