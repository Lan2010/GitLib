<?php

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

namespace Service\News\Model;

use Common\Model\SlaveModel;
use Think\Exception;

class SmsSendModel extends SlaveModel {

    /**
     * 发送短信记录
     * @param type $entity
     * @return boolean
     */
    public function addSend($entity) {
        if (is_array($entity)) {
            $entity["sendDatetime"] = time();
            $model = M("SmsSend");
            $result = $model->add($entity);
            return $result;
        } else {
            return false;
        }
    }

    /**
     * 批量插入短信记录
     * @param type $entity
     * @return type
     */
    public function addSmsALL($entity) {
        $model = M("SmsSend");
        $result = $model->addAll($entity);
        return $result;
    }

    
    /**
     * 短信记录查询 
     * @param type $where
     * @param type $whereArr
     * @param type $limit
     */
    public function getSms($where, $whereArr, $limit, $field) {
        $model = $this->SlaveDB();
        $result["total"] = $model->table("d_sms_send")->where($where, $whereArr)->count();
        $result["rows"] = $model->table("d_sms_send")->where($where, $whereArr)->field($field)->limit($limit)->order("sendDatetime DESC")->select();
        return $result;
    }

}
