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
 * 敏感词信息
 */
class SysSensitiveModel extends SlaveModel {

    /**
     * 查询敏感词
     * @param type $where
     * @param type $wherearr
     * @param type $limit
     * @return type
     */
    public function getSen($where, $wherearr, $limit) {
        $result["total"] = $this->SlaveDB()->table("d_sys_sensitive")->where($where, $wherearr)->count();
        $result["rows"] = $this->SlaveDB()->table("d_sys_sensitive")->where($where, $wherearr)->limit($limit)->select();
        return $result;
    }

    /**
     * 保存敏感词
     * @param type $data
     * @return boolean
     */
    public function saveSen($data, $where) {
        $model = M("sys_sensitive");
        try {
            if (is_array($where) && !empty($where)) {
                $result = $model->where($where)->save($data);
                return $result * 1 > 0 ? $where["sID"] : FALSE;
            } else {
                return $model->add($data);
            }
        } catch (Exception $ex) {
            logger(__METHOD__, $ex->getMessage());
            return false;
        }
    }

    /**
     * 查询编辑信息
     * @param type $where
     * @return type
     */
    public function getSinCen($where) {
        $model = M("sys_sensitive");
        $result = $model->where($where)->find();
        return $result;
    }

    /**
     * 删除敏感词信息
     * @param type $where
     */
    public function delSen($where) {
        $model = M("sys_sensitive");
        $result = $model->where($where)->delete();
        return $result * 1 > 0 ? $where["sID"] : FALSE;
    }

}
