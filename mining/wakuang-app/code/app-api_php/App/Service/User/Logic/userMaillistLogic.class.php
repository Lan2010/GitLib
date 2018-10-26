<?php

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

namespace Service\User\Logic;
use Think\Model;


/**
 * RealName 
 *
 * @author DREAM
 */
class userMaillistLogic {

    public function addAll($data) {
        if(!is_array($data[0]) || empty($data)) {
            return false;
        } else {
            return M("userMaillist")->addAll($data);
        }
    }

    /**获取到同学的电话
     * @param $userID
     * @return mixed
     */
    public function getPhoneList($userID) {
        return M("userMaillist")->field("phone")->where(array('user_id'=>$userID ))->select();
    }

    /**
     * 获取到通讯录的注册情况
     * @param $userID
     */
    public function getList($userID,$type=3,$limit="0,15") {
        $sql = "select um.name,um.phone,IF(u.user_token is NULL,0,1) as state from d_user_maillist as um LEFT JOIN  d_user as u ON um.phone = u.phone WHERE um.user_id = ".$userID ;
        if($type == 1) {   //注册过的
            $sql.=" and u.user_token is NULL";
        } else if($type == 2)  {  //未注册过的
            $sql .= " and u.user_token is not null";
        }
        $model = new Model();
        return $model->query($sql);
    }


    /**
     * 获取到通讯录列表
     */
    public function getMailList($where,$field,$limit="") {
        $model =  M("userMaillist")->alias("um")->join("d_user u on um.user_id = u.user_id")->where($where)->field($field);
        if(!empty($limit)) {
            $model = $model->limit($limit);
        }
        return $model->select();
    }

    /**
     * 获取到数量
     */
    public function getListCount($user_id) {
        return M("userMaillist")->field("count('user_id') as user_count")->where(array("user_id"=>$user_id))->select();
    }


}
