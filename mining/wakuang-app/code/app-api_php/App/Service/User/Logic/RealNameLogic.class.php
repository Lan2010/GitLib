<?php

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

namespace Service\User\Logic;

use Service\Account\Logic\AccountLogic;
use Service\User\Model;

/**
 * RealName 
 *
 * @author DREAM
 */
class RealNameLogic {

    private $model = array();

    public function __construct() {
        $this->model = new Model\UserRealnameModel();
    }

    /**添加实名信息
     * @param $addData
     * @param $cardID
     * @return bool|mixed
     */
    public function addRealName($addData,$cardID) {
        $addData['card_address'] = getCardArea($cardID);
        $age = getCardAge($cardID);
        $addData['user_age'] = ($age > 0) ? $age:0;
        $addData['birth_day'] = getCardBirthday($cardID);
        $addData['birth_year'] = getCardBirthYear($cardID);
        $addData['card_id'] = $cardID;
        $addData['card_type'] = 1;
        $addData['audit_status'] = 0;
        $addData['user_sex'] = getSex($cardID);
        $addData['operate_ip'] = getIP();
        $model = new Model\UserRealnameModel();
        return $model->AddRealName($addData);
    }


    public function getRealByCardID($cardID,$userID) {
        $model= new Model\UserRealnameModel();
        $where['card_id'] = $cardID;
        $where['user_id'] = $userID;
        $feild = array('card_id');
        return $model->getUserReal($feild,$where);
    }

    public function getRealByUserID($userID) {
        $model= new Model\UserRealnameModel();
        $where['user_id'] = $userID;
        $where['audit_status'] = 1;
        $feild = array('card_id','user_id','real_name','real_status','card_type','user_age','user_sex','birth_year','birth_day','card_address');
        return $model->getUserReal($feild,$where);
    }


    /**
     * 获取实名认证记录
     * @param type $where
     * @param type $whereArr
     * @param type $limit
     * @return type
     */
    public function getRealname($where, $whereArr, $limit = 0) {
        $result = $this->model->getRealname("(1=1) " . $where, $whereArr, $limit);
        return $result;
    }

    /**
     * @param $where
     * @param $whereArr
     * @param int $limit
     * @return Model\type
     */
    public function getBank($where, $whereArr, $limit = 0) {
        $result = $this->model->getBank("(1=1) " . $where, $whereArr, $limit);
        return $result;
    }






}
