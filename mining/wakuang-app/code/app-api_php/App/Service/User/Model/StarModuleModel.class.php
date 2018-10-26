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
class StarModuleModel extends SlaveModel {

    /**
     * 新增实名申请
     * @param type $entity
     */
    public function AddModule($entity) {
        if (is_array($entity)) {
            $entity['add_datetime'] = time();
            $model = M("StarModule");
            $result = $model->data($entity)->add();
            return $result;
        }
        return false;
    }

    /**
     * 获取到全部模块列表
     */
    public function getModuleList($is_system = 0) {
        $model = M('StarType');
        $where['mark'] = 1;
        if(!empty($is_system)) {
            $where['is_system'] = $is_system;
        }  else {
            $where['is_system'] = array('neq',0);
        }
        return $model->where($where)->field(array('type_name','type_code','type_value','mark','remark','type_id'))->select();
    }


    /**
     * 获取到全部的模块列表
     */
    public function getBndModuleList($where) {
        $model = M('StarType');
        if(!is_array($where)) {
            return;
        }
        return $model->where($where)->field(array('type_name','type_code','type_value','mark','remark','type_id'))->select();
    }


    /**获取用户的认证对象
     * @param $userID
     * @return mixed
     */
    public function getUserModuleList($userID) {
        return $this->where(array("user_id"=>$userID))->select();
    }

    /**
     * 修改绑定的状态
     */
    public function updateModule($where,$data)  {
        if(empty($where)|| empty($data)) {
            return false;
        }
        return $this->where($where)->save($data);
    }




}
