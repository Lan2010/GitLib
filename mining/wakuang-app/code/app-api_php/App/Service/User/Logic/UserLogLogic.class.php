<?php

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

namespace Service\User\Logic;

use Service\User\Model\UserLoginCountModel;

class UserLogLogic {

    private $model = null;

    public function __construct() {
        $this->model = new UserLoginCountModel();
    }

    /**
     * 用户登录日志合计
     * @param type $where
     * @param type $whereArr
     * @param type $limit
     * @return type
     */
    public function getUserLog($where, $whereArr, $limit) {
        $result = $this->model->getUserLog($where, $whereArr, $limit);
        return $result;
    }

    /**
     * 获取登录明细
     * @return type
     */
    public function getLogItem($where, $whereArr, $limit) {
        $result = $this->model->getLogItem($where, $whereArr, $limit);
        return $result;
    }

    /**
     * 获取用户登陆天数
     */
    public function getLogCount($userID, $startDate = 0, $endDate = 0) {
        if (empty($userID)) {
            return false;
        }
        $where = " AND userID=" . $userID;
        if (!empty($startDate)) {
            $where .=" AND loginDatetime >=" . $startDate;
        }
        if (!empty($endDate)) {
            $where.=" AND loginDatetime <" . $endDate;
        }
        $result = $this->model->getLogCount($where);
        return $result;
    }

    /**
     * 登录日志记载
     * @param type $userID
     * @param type $phone
     * @param type $terminal 终端
     * @param type $equipment 终端信息
     * @param type $operateIP IP
     * @return type
     */
    public function AddLoginLog($userID, $phone, $terminal, $equipment, $operateIP) {
        $result = $this->model->AddLoginLog($userID, $phone, $terminal, $equipment, $operateIP);
        return $result;
    }

    /**
     * 获取到用户的上次登录信息
     * --
     */
    public function getPreUserLoginInfo($userID) {
        if (empty($userID)) {
            return FALSE;
        }
        return $this->model->getPreUserLoginInfo($userID);
    }

}
