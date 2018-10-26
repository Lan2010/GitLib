<?php

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

namespace Service\Backend\Model;

use Think\Model;

class ConsoleRoleModel extends Model {

    /**
     * 查询后台角色信息
     * @param type $where
     * @param type $whereArr
     * @param type $limit
     */
    public function getRole($where, $whereArr, $limit) {

        $model = M("ConsoleRole");
        $result["total"] = $model->where($where, $whereArr)->count();
        $field = "role_name,add_user_name,add_date_time,role_remark,role_id";
        $result["rows"] = $model->field($field)->where($where, $whereArr)->limit($limit)->select();
        return $result;
    }

    /**
     * 获取所有有效角色。
     */
    public function getAllRole() {
        return $this->field('role_id,role_name')->where(array('status' => 1))->select();
    }

    /**
     * 删除角色
     * @param type $where
     */
    public function saveRole($where, $data) {
        $Model = M("ConsoleRole");
        try {
            if (is_array($where) && count($where) > 0) {
                $result = $Model->where($where)->data($data)->save();
                return $result * 1 > 0 ? $where["role_id"] : false;
            } else {
                return $Model->add($data);
            }
        } catch (Exception $ex) {
            logger(__METHOD__, $ex->getMessage());
            return false;
        }
    }

    /**
     * 查询修改时的信息
     * @param type $where
     */
    public function getSinRole($where) {
        $model = M("ConsoleRole");
        $field = "role_name,role_remark,role_id";
        $result = $model->where($where)->find();
        return $result;
    }

}
