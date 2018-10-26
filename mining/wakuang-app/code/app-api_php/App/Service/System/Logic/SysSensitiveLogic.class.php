<?php

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

namespace Service\System\Logic;

use Think\Exception;
use Service\System\Model;

class SysSensitiveLogic {

    /**
     * 获取敏感词的信息
     * @param type $where
     * @param type $wherearr
     * @param type $limit
     * @return type
     */
    public function getSen($where, $wherearr, $limit) {
        $model = new Model\SysSensitiveModel();
        $result = $model->getSen("(1=1)" . $where, $wherearr, $limit);
        return $result;
    }

    /**
     * 保存敏感词
     * @param type $data
     * @return boolean
     */
    public function saveSen($data) {
        try {
            $where = array();
            if ($data["sID"] * 1 > 0 && count($data) > 0) {
                $where["sID"] = $data["sID"];
                $data ['editUserID'] = C('userID');
                $data ['editUserName'] = C('userName');
                $data ['editDatetime'] = time();
                unset($data["sID"]);
            } else {
                $data ['addUserID'] = C('userID');
                $data ['addUserName'] = C('userName');
                $data ['addDatetime'] = time();
            }
            $model = new Model\SysSensitiveModel();
            $result = $model->saveSen($data, $where);
            return $result;
        } catch (Exception $ex) {
            logger(__METHOD__, $ex->getMessage());
            return false;
        }
    }

    /**
     * 查询编辑信息
     * @param type $key
     * @return boolean
     */
    public function getSinCen($key) {
        if ($key * 1 < 1) {
            return FALSE;
        }
        $where["sID"] = $key;
        $model = new Model\SysSensitiveModel();
        $result = $model->getSinCen($where);
        return $result;
    }

    /**
     * 删除敏感词
     * @param type $key
     */
    public function delSen($sID) {
        if ($sID * 1 < 1) {
            return FALSE;
        }
        $where["sID"] = $sID;
        $model = new Model\SysSensitiveModel();
        $result = $model->delSen($where);
        return $result;
    }

}
