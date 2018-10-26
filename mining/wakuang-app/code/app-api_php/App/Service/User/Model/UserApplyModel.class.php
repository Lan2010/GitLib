<?php

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

namespace Service\User\Model;

use Common\Model\SlaveModel;
use Think\Exception;

/**
 * Description of UserApplyModel
 *
 * @author DREAM
 */
class UserApplyModel extends SlaveModel {

    /**
     * 新增用户申请
     * @param type $entity
     */
    public function addApply($entity) {
        try {
            if (is_array($entity)) {
                $entity['add_datetime'] = time();
                $model = M("UserApply");
                $result = $model->data($entity)->add();
                return $result;
            }
            return false;
        } catch (Exception $ex) {
            logger(__METHOD__, $ex->getMessage());
            return false;
        }
    }

    /**
     * 获取单个用户
     * @param type $where
     * @param type $field
     * @return type
     */
    public function getSinApply($where, $field) {
        $model = M("UserApply");
        $result = $model->field($field)->where($where)->order('verify_datetime desc,add_datetime desc')->find();
        return $result;
    }

    /**
     * 保存用户申请
     * @param type $where
     * @param type $data
     */
    public function saveApply($where, $data) {
        try {
            if (is_array($where) && count($where) > 0) {
                $model = M("UserApply");
                $result = $model->where($where)->data($data)->save();
                return $result * 1 > 0 ? $where["apply_id"] : false;
            }
            return false;
        } catch (Exception $ex) {
            logger(__METHOD__, $ex->getMessage());
            return false;
        }
    }

    /**
     * 用户申请查询
     * @param type $where
     * @param type $whereArr
     * @param type $limit
     * @return type
     */
    public function getApplyList($where, $whereArr, $limit) {
        $result["total"] = $this->SlaveDB()->where($where, $whereArr)->count();
        $field = "*";
        $result["rows"] = $this->SlaveDB()->where($where, $whereArr)->field($field)->order('apply_id desc')->limit($limit)->select();
        return $result;
    }
}
