<?php

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

namespace Service\Backend\Logic;

use Service\Backend\Model;

/**
 * Description of ConsoleRoleuserLogic
 *
 * @author Administrator
 */
class ConsoleRoleuserLogic extends BaseLogic {

    /**
     * 根据用户ID获取所有角色信息。
     * @param type $userID
     * @return boolean
     */
    public function getRoleByUser($userID) {
        $rolemodel = new Model\ConsoleRoleModel();
        $roles = $rolemodel->getAllRole();
        $usermodel = new Model\ConsoleRoleuserModel();
        $roleuser = $usermodel->getRoleByUser($userID);
        $userRoleGroup = $this->roleUserMerge($roles, $roleuser);
        return $userRoleGroup;
    }

    /**
     * 根据用户ID删除所有分配角色信息。
     * @param type $userID
     * @return boolean
     */
    public function delRoleByUser($userID) {
        $model = new Model\ConsoleRoleuserModel();
        return $model->delRoleByUser($userID);
    }

    /**
     * 添加角色信息。
     * @param type $data
     * @return boolean
     */
    public function addRoles($data) {
        $model = new Model\ConsoleRoleuserModel();
        return $model->addRoles($data);
    }

    /**
     * 合并用户已分配角色信息及角色信息。
     * @param type $role
     * @param type $userroles
     * @return type
     */
    private function roleUserMerge($role, $userroles = null) {
        $arr = array();
        foreach ($role as $v) {
            $v['access'] = false;
            if (is_array($userroles) && in_array($v['role_id'], $userroles)) {
                $v['access'] = true;
            }
            $arr[] = $v;
        }
        return $arr;
    }

}
