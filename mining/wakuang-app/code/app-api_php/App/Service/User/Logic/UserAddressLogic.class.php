<?php

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

namespace Service\User\Logic;

USE Service\User\Model\UserAddressModel;

/**
 * Description of AccountLogic
 *
 * @author Bourne
 */
class UserAddressLogic {

    private $model = null;

    public function __construct() {
        $this->model = new UserAddressModel();
    }

    /**
     * 保存用户地址
     * @param type $data
     * @param type $userID
     */
    public function saveUserAddress($data, $userID) {
        $ret = $this->checkUserAddress($data, $userID);
        if (!$ret['ret']) {
            return $ret;
        }
        $where = null;
        if ($data["txtID"]) {
            $where['user_id'] = $userID;
            $where['address_id'] = $data["txtID"];
        } else {
            unset($data["txtID"]);
            $savedata["user_id"] = $userID;
        }

        if(!empty($data['address_id'])) {
            $where['address_id'] = $data['address_id'];
            unset($data['address_id']);
        }
        $savedata["province"] = htmlspecialchars(stripslashes($data["province"]));
        $savedata["province_name"] = $this->model->getAdNameByID($data['province']);
        $savedata["city"] = htmlspecialchars(stripslashes($data["city"]));
        $savedata["city_name"] = $this->model->getAdNameByID($data['city']);
        $savedata["district"] = htmlspecialchars(stripslashes($data["district"]));
        $savedata["district_name"] = $this->model->getAdNameByID($data['district']);
        $savedata["address"] = htmlspecialchars(stripslashes($data["address"]));
        $savedata["zip_code"] = htmlspecialchars(stripslashes($data["zipcode"]));
        $savedata["is_default"] = 1;
        $savedata['work_unit'] = $data['work_unit'];
        $savedata['work_address'] = $data['work_address'];
        $savedata['add_datetime'] = time();
        $save_res = $this->model->SaveUserAddress($savedata, $where);
        if(is_array($save_res)) {
            return $save_res;
        }
        return $ret;
    }

    /**
     * 校验数据是否正确
     * @param type $data
     * 
     */
    public function checkUserAddress($data, $userID) {

        $ret = array("ret" => 1, "msg" => "");
        if ($data["province"] * 1 < 1) {
            $ret = array("ret" => 0, "msg" => "请选择省份!");
            return $ret;
        }
        if ($data["city"] * 1 < 1) {
            $ret = array("ret" => 0, "msg" => "请选择城市!");
            return $ret;
        }
        if ($data["district"] * 1 < 1) {
            $ret = array("ret" => 0, "msg" => "请选择地区!");
            return $ret;
        }
        if (empty($data["address"])) {
            $ret = array("ret" => 0, "msg" => "联系地址不能为空");
            return $ret;
        }
        return $ret;
    }

    /**
     *  获取用户列表
     * @param type $userID
     * @return type
     */
    public function getUserAddressList($userID) {
        $list = $this->model->getUserAddressList($userID);
        return $list;
    }

    /**
     * 删除
     * @param type $txtID
     * @param type $userID
     * @return type
     */
    public function delAddress($txtID, $userID) {
        $where['userID'] = $userID;
        $where['addressID'] = $txtID;
        $ret = $this->model->delAddress($where);
        return $ret;
    }

    /**
     * 
     * 设为默认地址
     * 
     */
    public function saveDefaultAddress($txtID) {
        if (empty($txtID)) {
            return 0;
        }
        $userID = C("userID");
        $where['userID'] = $userID;
        $data['isdefault'] = 0;
        $this->model->SaveUserAddress($data, $where);

        $where['addressID'] = $txtID;
        $saveData['isdefault'] = 1;
        $ret = $this->model->SaveUserAddress($saveData, $where);
        return $ret;
    }

    /**
     * 获取用户所用的地址
     * @param type $addressID
     */
    public function getALLaddress($addressID, $userID) {
        if ($userID * 1 <= 0)
            return false;
        $model = new MallUserAddressModel();
        $where["addressID"] = $addressID;
        $where["userID"] = $userID;
        $result = $model->getALLaddress($where);
        return $result;
    }

    /**
     * 获取默认地址
     */
    public function getdatAddress($userID, $isdefault) {
        if ($userID * 1 <= 0)
            return false;
        $where["user_id"] = $userID;
        $where["is_default"] = $isdefault;
        $model = new UserAddressModel();
        $result = $model->getALLaddress($where);
        return $result;
    }

    /**
     * 手机增加地址的时候如果设置为默认则覆盖上一次的默认
     * @param type $txtID
     */
    public function updateDafault($txtID, $userID) {
        if (empty($txtID) || $userID * 1 <= 0) {
            return 0;
        }
        $where['userID'] = $userID;
        $where['addressID'] = $txtID;
        $data['isdefault'] = 0;
        $re = $this->model->SaveUserAddress($data, $where);
        return $re;
    }

    /**
     * 查询地址id
     * @param type $addressName
     * @param type $parentID
     * @return array
     */
    public function getAddressID($addressName, $parentID = 0) {
        return $this->model->getAddressID($addressName, $parentID);
    }

    /**
     * 得到上次插入地址id
     */
    public function getPk() {
        return $this->model->getLastInsID();
    }

    /**
     * 获取到全部的地址
     */
    public function getAddressList($where,$field,$limit="") {
        $model =  $this->model->alias("ua")->join("d_user u on ua.user_id = u.user_id")->where($where)->field($field);
        if(!empty($limit)) {
            $model = $model->limit($limit);
        }
        return $model->select();
    }

}
