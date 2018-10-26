<?php

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

namespace Service\Backend\Model;

use Think\Model;

/**
 * Description of ConsoleAccess
 *
 * @author Administrator
 */
class ConsoleAccessModel extends Model {

    /**
     * 根据角色ID获取所有授权信息。
     * @param type $roleID
     * @return boolean
     */
    public function getAccessByRole($roleID) {
        if (empty($roleID))
            return false;
        $where['role_id'] = $roleID;
        return $this->where($where)->getField('menu_id', true);
    }

    /**
     * 根据角色ID删除所有授权信息。
     * @param type $roleID
     * @return boolean
     */
    public function delAccessByRole($roleID) {
        if (empty($roleID))
            return false;
        $where['role_id'] = $roleID;
        $result = $this->where($where)->delete();
        return $result;
    }

    /**
     * 添加授权信息。
     * @param type $data
     * @return boolean
     */
    public function addAccess($data) {
        if (empty($data) || !is_array($data))
            return false;
        $result = $this->addAll($data);
        return $result;
    }

}
