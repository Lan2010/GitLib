<?php

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

namespace Service\News\Logic;

/**
 * Description of SmsVerifyLogic
 *
 * @author DREAM
 */
use Service\News\Model\SmsVerifyModel;

class SmsVerifyLogic {

    private $model = null;

    public function __construct() {
        $this->model = new SmsVerifyModel();
    }

    /**
     * 添加验证码
     * @param type $entity
     * @return boolean
     */
    public function addVerify($entity) {
        if (is_array($entity)) {
            $result = $this->model->addVerify($entity);
            return $result;
        }
        return false;
    }

    /**
     * 获取验证码
     * @param type $phone
     * @return boolean
     */
    public function getVerify($phone) {
        $where["receivePhone"] = $phone;
        $where['overdueTime'] = array('gt', time());
        $field = "verifyCode,addDatetime";
        $result = $this->model->getVerify($field, $where);
        if (isset($result)) {
            return $result;
        }
        return false;
    }

    /**
     * 确认验证码
     * @param type $phone
     * @param type $code 
     * @return boolean
     * --
     */
    public function getConfirmVerify($phone, $code) {
        $where["receive_phone"] = $phone;
        $where['overdue_time'] = array('gt', time());
        $where["verify_code"] = $code;
        $field = "verify_code";
        $result = $this->model->getVerify($field, $where);
        if (isset($result)) {
            return false;
        }
        return true;
    }

    /**
     * 短信验证码查询 
     * @param type $where
     * @param type $whereArr
     * @param type $limit
     */
    public function getCode($where, $whereArr, $limit, $field) {
        $field = "verifyID,receiveName,receivePhone,verifyCode,sendContent,sendInterface,overdueTime,addDatetime";
        $result = $this->model->getCode("(1=1)" . $where, $whereArr, $limit, $field);
        return $result;
    }

}
