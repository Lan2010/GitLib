<?php

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

namespace Service\Backend\Logic;

use Service\Backend\Model;

class ConsoleRoleLogic {

    /**
     * 查询后台角色管理信息
     * @param type $where
     * @param type $whereArr
     */
    public function getRole($where, $whereArr, $limit) {
        $model = new Model\ConsoleRoleModel();
        $where.="AND status > %d";
        array_push($whereArr, 0);
        $result = $model->getRole("(1=1)" . $where, $whereArr, $limit);
        return $result;
    }

    /**
     * 删除角色
     * @param type $roleID
     */
    public function delRole($roleID) {
        if ($roleID * 1 < 1) {
            return FALSE;
        }
        $where["role_id"] = $roleID;
        $data["status"] = 0;
        $model = new Model\ConsoleRoleModel();
        $result = $model->saveRole($where, $data);
        return $result;
    }

    /**
     * 保存角色
     * @param type $data
     */
    public function saveRole($data) {
        try {
            $where = array();
            if ($data["role_id"] * 1 > 0 && count($data)> 0) {
                $where["role_id"] = $data["role_id"];
                $data["edit_user_id"] = C("userID");
                $data["edit_user_name"] = C("userName");
                $data["edit_date_time"] = time();
                unset($data["roleID"]);
            } else {
                $data["add_user_id"] = C("userID");
                $data["add_user_name"] = C("userName");
                $data["add_date_time"] = time();
            }
            $model = new Model\ConsoleRoleModel();
            $result = $model->saveRole($where, $data);
            return $result;
        } catch (Exception $ex) {
            logger(__METHOD__, $ex->getMessage());
            return false;
        }
    }

    public function getSinRole($roleID) {
        if ($roleID * 1 < 1) {
            return false;
        }
        $where["status"] = 1;
        $where["role_id"] = $roleID;
        $model = new Model\ConsoleRoleModel();
        $result = $model->getSinRole($where);
        return $result;
    }

}
