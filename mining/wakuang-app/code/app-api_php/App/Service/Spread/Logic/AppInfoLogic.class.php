<?php

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

namespace Service\Spread\Logic;

use Think\Exception;
use Service\Spread\Model\AppInfoModel;
use Service\Spread\Model\AppFeedbackModel;

class AppInfoLogic {

    private $model = null;

    public function __construct() {
        $this->model = new AppInfoModel();
    }

    /**
     * 手机端下载信息
     * @return type
     */
    public function getAppInfo($where, $whereArr, $limit) {
        $result = $this->model->getAppInfo("(1=1)" . $where, $whereArr, $limit);
        return $result;
    }

    /**
     * APP收集用户信息信息
     * @param type $data
     * @return type
     * --
     */
    public function saveAppInfo($data) {
        $ret = $this->model->saveAppInfo($data);
        return $ret;
    }

    /**
     * 添加反馈信息  
     * @param type $where
     */
    public function addFeback($where) {
        $model = new AppFeedbackModel();
        $res = $model->addFeback($where);
        return $res;
    }

    /**
     * 获取反馈信息的次数根据设备ID
     */
    public function getDayFeback($data) {
        $startTime = strtotime(date("Y-m-d"));
        $endTime = strtotime("+1day", $startTime);
        $model = new AppFeedbackModel();
        $where = "";
        $whereArr = array();
        $where.=" AND add_datetime > '%s'";
        $where.=" AND add_datetime < '%s'";
        $where.=" AND equipment_id = '%s'";
        array_push($whereArr, $startTime);
        array_push($whereArr, $endTime);
        array_push($whereArr, $data);
        $result = $model->getDayFeback("(1=1)" . $where, $whereArr);
        return $result;
    }

    /**
     * 获取反馈信息
     */
    public function getFeback($where, $whereArr, $limit) {
        $model = new AppFeedbackModel();
        $res = $model->getFeback("(1=1)" . $where, $whereArr, $limit);
        return $res;
    }

    /**
     * 获取反馈信息
     */
    public function getProp($key) {
        $where["feback_id"] = $key;
        $model = new AppFeedbackModel();
        $result = $model->getProp($where);
        return $result;
    }

    /**
     * 跟进用户反馈的信息
     */
    public function saveProp($remark, $febackID) {
        if (empty($remark) || empty($febackID)) {
            return false;
        }
        $model = new AppFeedbackModel();
        $data = array();
        $where = array();
        $where["feback_id"] = $febackID;
        $data["remark"] = $remark;
        $data["status"] = 1;
        $data["edit_datetime"] = time();
        $data["edit_user_name"] = C("userName");
        $data["edit_user_id"] = C("userID");
        $ret = $model->saveProp($where, $data);
        return $ret;
    }

}
