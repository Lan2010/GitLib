<?php

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

namespace Service\User\Model;

use Common\Model\SlaveModel;

class UserAddressModel extends SlaveModel {

    /**
     * 保存地址
     * @param type $data
     * @param type $where
     * @return boolean
     */
    public function SaveUserAddress($data, $where = array()) {

        if (is_array($where) && !empty($where)) {
            $result = $this->where($where)->data($data)->save();
        } else {
            $result = $this->data($data)->add();
        }
        return $result;
    }

    /**
     * 查询个人地址多少个
     * 
     */
    public function getAddressCount($userID) {
        if (empty($userID)) {
            return 5;
        }
        $where['userID'] = $userID;
        $count = $this->where($where)->count();
        return $count;
    }

    /**
     * 获取用户列表
     * @param type $userID
     */
    public function getUserAddressList($userID) {
        $where['user_id'] = $userID;
        $list = $this->where($where)->order("is_default DESC ,address_id ASC")->select();
        return $list;
    }

    public function delAddress($where) {

        if (empty($where)) {
            return false;
        }

        $ret = $this->where($where)->delete();

        return $ret;
    }

    /**
     * 获取用户所用的地址
     * @param type $where
     */
    public function getALLaddress($where) {
        if (empty($where)) {
            return false;
        }
        $result = $this->where($where)->find();
        return $result;
    }

    /**
     * 查询地址id
     * @param type $addressName     地址名称
     * @param type $parentID        父id
     * @param array
     */
    public function getAddressID($addressName, $parentID = 0) {
        $sql = "select regionID,parentID,name,level from d_sys_region where parentID=%d and name like '%s'";
        $rows = $this->query($sql, array($parentID, $addressName));
        return $rows[0];
    }

    /**
     * 根据ID 返回对应的地址信息
     */
    public function getAdNameByID($addressID) {
        $sql = "select name from d_sys_region where region_id=%d";
        $ret = $this->query($sql,array($addressID));
        return $ret[0]['name'];
    }

}
