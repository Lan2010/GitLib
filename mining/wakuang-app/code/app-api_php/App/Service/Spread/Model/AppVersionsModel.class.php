<?php

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

namespace Service\Spread\Model;

use Common\Model\SlaveModel;

/**
 * Description of AppVersionsModel
 *
 * @author Administrator
 */
class AppVersionsModel extends SlaveModel {

    /**
     * 获取指定终端最新的一条版本记录。
     * @param type $terminal
     */
    public function getInfo($terminal = 2) {
        if (empty($terminal) || $terminal * 1 <= 0)
            return false;
        $field = "version_name,version_code,check_code,update_desc,update_url,is_forced,app_size";

        return   $this->field($field)->where("status=1 AND terminal = $terminal")->order('add_datetime DESC')->find();

    }

    /**
     * 获取安卓端版本摘要集合。
     */
    public function getCheckCodes() {
        return $this->field('check_code')->where("status>0 AND terminal = 2 and check_code<>''")->order('add_datetime DESC')->select();
    }

    /**
     * 获取APP版本信息 
     * @param type $where
     * @param type $whereArr
     * @param type $limit
     * @return type
     */
    public function getAppList($where, $whereArr, $limit) {
        $result["total"] = $this->where($where, $whereArr)->count();
        $result["rows"] = $this->where($where, $whereArr)->limit($limit)->order("add_datetime DESC")->select();
        return $result;
    }

    /**
     * 更新APP版本信息
     * @param type $data
     * @param type $where
     * @return boolean
     */
    public function saveAppVer($data, $where) {
        try {
            if (is_array($where) && count($where) > 0) {
                $res = $this->where($where)->data($data)->save();
                return $res * 1 > 0 ? $where["ver_id"] : false;
            } else {
                return $this->add($data);
            }
            return false;
        } catch (Exception $ex) {
            logger(__METHOD__, $ex->getMessage());
            return false;
        }
    }

    /**
     * 删除APP版本
     * @param type $where
     */
    public function getAllApp($where) {
        $result = $this->where($where)->find();
        return $result;
    }

    /**
     * 增加APP版本的时候覆盖状态
     */
    public function getAppInfo($where) {
        return $this->where($where)->select();
    }

}
