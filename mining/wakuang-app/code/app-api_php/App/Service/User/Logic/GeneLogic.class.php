<?php

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

namespace Service\User\Logic;

use Service\User\Logic\BankCardLogic;
use Service\System\Logic\DictionaryLogic;
use Service\User\Model;

/**
 * RealName
 *
 * @author DREAM
 */
class GeneLogic {


    /**添加实名信息
     * @param $addData
     * @param $cardID
     * @return bool|mixed
     */
    public function findUserBank($userID) {
        $model = M("Gene");
        return $model->where(array("user_id"=>$userID))->find();

    }


    /**
     * 填写基因
     */
    public function addGene($data) {
        $model = M("Gene");
        return $model->add($data);
    }



    /**
     * 获取到全部的地址
     */
    public function getGeneList($where,$field,$limit="") {
        $model =  M("Gene")->alias("g")->join("d_user u on g.user_id = u.user_id")->where($where)->field($field);
        if(!empty($limit)) {
            $model = $model->limit($limit);
        }
        return $model->select();
    }






}
