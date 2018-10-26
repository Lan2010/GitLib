<?php

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

namespace Service\News\Model;

/**
 * 代办任务相关
 *
 * @author DREAM
 */
use Think\Model;
use Think\Exception;

class SysTaskModel extends Model {

    /**
     * 增加代办的任务
     * @param type $entity
     * @return boolean
     */
    public function addTask($entity) {
        try {
            if (is_array($entity)) {
                $entity["taskStatus"] = 0;
                $entity["addDatetime"] = time();
                $model = M("SysTask");
                $result = $model->add($entity);
                return $result;
            }
            return false;
        } catch (Exception $ex) {
            logger(__METHOD__, $ex->getMessage());
            return false;
        }
    }

    /**
     * 获取代办数据
     * @return boolean
     */
    public function getTaskMore($field, $where) {     
        $model = M("SysTask");
        $result = $model->field($field)->where($where)->order('addDatetime desc')->select();
        if (is_array($result)) {
            return $result;
        }
        return false;
    }

    /**
     * 修改代办数据
     * @param type $data
     * @param type $where
     * @return type
     */
    public function editTask($data, $where) {
        try {
            $model = M("HomePatch");
            if (is_array($where) && count($where) > 0) {
                $model = M("SysTask");
                $result = $model->where($where)->data($data)->save();
                return $result;
            }
            return false;
        } catch (Exception $ex) {
            logger(__METHOD__, $ex->getMessage());
            return false;
        }
    }

}
