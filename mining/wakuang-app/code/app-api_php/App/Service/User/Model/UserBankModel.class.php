<?php

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

namespace Service\User\Model;

use Common\Model\SlaveModel;
use Think\Exception;

/**
 * Description of UserRealnameModel
 *
 * @author DREAM
 */
class UserBankModel extends SlaveModel {

    /**
     * 新增实名申请
     * @param type $entity
     */
    public function AddUserBank($entity) {
        if (is_array($entity)) {
            $entity['add_datetime'] = time();
            $model = M("UserBank");
            $result = $model->data($entity)->add();
            return $result;
        }
        return false;
    }

    /**
     * 获取用户实名身份信息
     * @return boolean
     */
    public function getUserBank($field, $where) {
        if (count($where) < 1) {
            return false;
        }
        $model = M("UserBank");
        $result = $model->field($field)->where($where)->find();
        return $result;
    }



}
