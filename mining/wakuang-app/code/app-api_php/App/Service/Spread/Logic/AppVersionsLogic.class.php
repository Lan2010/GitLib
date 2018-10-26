<?php

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

namespace Service\Spread\Logic;

use Service\Spread\Model\AppVersionsModel;

/**
 * Description of AppVersions
 *
 * @author Administrator
 */
class AppVersionsLogic {

    private $model = null;

    public function __construct() {
        $this->model = new AppVersionsModel();
    }

    /**
     * 获取指定终端最新的一条版本记录。
     * @param type $terminal
     */
    public function getInfo($terminal = 2) {
        return $this->model->getInfo($terminal);
    }

    /**
     * 获取安卓端版本摘要集合。
     */
    public function getCheckCodes() {
        return $this->model->getCheckCodes();
    }

    /**
     * 获取APP版本信息 
     * @param type $where
     * @param type $whereArr
     * @param type $limit
     * @return type
     */
    public function getAppList($where, $whereArr, $limit) {
        $result = $this->model->getAppList("(1=1)" . $where, $whereArr, $limit);
        return $result;
    }

    /**
     * 更新APP版本
     * @param type $data
     */
    public function saveAppVer($data) {
        try {
            $where = array();
            if ($data["ver_id"] * 1 > 0 && count($data) > 0) {
                $where["ver_id"] = $data["ver_id"];
                $data ['edit_user_id'] = C('userID');
                $data ['edit_user_name'] = C('userName');
                $data ['edit_datetime'] = time();
                unset($data["banner_id"]);
            } else {
                $this->saveApp($data["terminal"]);
                $data ['add_user_id'] = C('userID');
                $data ['add_user_name'] = C('userName');
                $data ['add_datetime'] = time();
            }
            $result = $this->model->saveAppVer($data, $where);
            return $result;
        } catch (Exception $ex) {
            logger(__METHOD__, $ex->getMessage());
            return false;
        }
    }

    /**
     * 获取APP版本详情
     * @param type $key
     */
    public function getAllApp($key) {
        if ($key * 1 < 1) {
            return false;
        }
        $where["ver_id"] = $key;
        $where["status"] = array('GT', 0);
        $result = $this->model->getAllApp($where);
        return $result;
    }

    /**
     * 删除APP版本
     * @param type $verID
     */
    public function delAppVer($verID) {
        $where["ver_id"] = $verID;
        $data["status"] = 0;
        return $this->model->saveAppVer($data, $where);
    }

    /**
     * 增加APP版本的时候覆盖状态
     */
    private function saveApp($terminal) {
        $where["terminal"] = $terminal;
        $where["status"] = array("GT", 0);
        $res = $this->model->getAppInfo($where);
        if (!empty($res)) {
            $data["status"] = 2;
            $this->model->saveAppVer($data, $where);
        }
        return true;
    }

}
