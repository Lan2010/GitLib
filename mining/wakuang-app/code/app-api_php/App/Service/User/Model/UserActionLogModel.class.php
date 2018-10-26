<?php

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * Description of UserActionLogModel
 *
 * @author Administrator
 */

namespace Service\User\Model;

use Common\Model\SlaveModel;

class UserActionLogModel extends SlaveModel {

    /**
     * ICP要求记录的日志
     * @param type $data
     */
    public function addlog($data) {
        return $this->add($data);
    }

    /**
     * 获取前台ICP日志 
     * @param type $where
     * @param type $whereArr
     * @param type $limit
     */
    public function getActLog($where, $whereArr, $limit) {
        $result["total"] = $this->SlaveDB()->table("d_user_action_log")->where($where, $whereArr)->count();
        $result["rows"] = $this->SlaveDB()->table("d_user_action_log")->where($where, $whereArr)->order("add_datetime DESC")->limit($limit)->select();
        return $result;
    }

}
