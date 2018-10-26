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
class BankLogic {


    /**添加实名信息
     * @param $addData
     * @param $cardID
     * @return bool|mixed
     */
    public function addUserBank($addData,$cardNo) {
        $info  =  BankCardLogic::info($cardNo);
        if(empty($info['bankName'])) {
            return false;
        }
        $addData['bank_name'] = $info['bankName'];
        $addData['bank_card_no'] = $cardNo;
        $addData['branch'] = $info['bankName'];
        $addData['status'] = 0;
        $addData['bank_img'] = $info['bank_img'];
        $dataData['bank_code'] =$info['bank'] ;
        $model= new Model\UserBankModel();
        return $model->addUserBank($addData);
    }


    public function getBankByCode($bankCardNo) {
        $model= new Model\UserBankModel();
        $where['bank_card_no'] = $bankCardNo ;
        $feild = array('bank_id');
        return $model->getUserBank($feild,$where);
    }

    public function getBankByUserID($userID) {
        $model= new Model\UserBankModel();
        $where['user_id'] = $userID;
        $where['status'] = 1;
        $feild = array('bank_id','user_id','real_name','bank_code','bank_name','bank_img','bank_card_no','branch','add_datetime');
        return $model->getUserBank($feild,$where);
    }


}
