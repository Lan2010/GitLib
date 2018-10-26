<?php

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

namespace Service\User\Logic;
use Service\User\Model;

/**
 * Description of BankCardLogic
 *
 * @author Administrator
 */
class StarModuleLogic {

    /**
     * 添加用户的认证标识
     * @param $userID
     * @param $module
     * @return bool|mixed
     */
    public function addModule($userID,$module) {
        $data['user_id'] = $userID;
        $data['module_code']= $module;
        $data['status'] = 1;
        $model = new Model\StarModuleModel();
        return $model->AddModule($data);
    }

    /**
     * 获取到全部需要认证的标识列表
     */
    public function getModuleList($is_system = 0) {
        $model = new Model\StarModuleModel();
        return $model->getModuleList($is_system);
    }


    public function getUserModuleList($userID) {
        $model = new Model\StarModuleModel();
        return $model->getUserModuleList($userID);
    }


    public function getEndModuleList($where) {
        $model = new Model\StarModuleModel();
        return $model->getBndModuleList($where);
    }


    public function updateStatus($where,$status) {
        $data['mark'] = $status;
        $model = M("starType");
        return $model->where($where)->save($data);
    }


    /**
     * 修改结构
     */
    public function updateModule($userID,$code,$status) {
        $data['status'] = $status;
        $where['user_id'] = $userID;
        $where['module_code'] = $code;
        $model = new Model\StarModuleModel();
        return $model->updateModule($where,$data);
    }

    /**
     * 修改结构
     */
    public function setStarType($data) {
        $model = M("starType");
        $res = $model->where(array("type_code"=>$data['type_code']))->find();
        if(empty($res)) {
            return $model->add($data);
        } else {
            $model->where(array("type_code"=>$data['type_code']))->save($data);
            return true;
        }
    }

    /**
     * 判断是否已经认证了某项功能
     */
    public function getOneModuleInfo($type_code,$userID) {
        return M("starModule")->where(array('module_code'=>$type_code,'user_id'=>$userID))->find();
    }

    public function getOneModule($type_code) {
        return M("starType")->where(array('type_code'=>$type_code))->find();
    }

}
