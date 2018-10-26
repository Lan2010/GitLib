<?php

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

namespace Service\News\Logic;

use Service\News\Logic\WXTemplateLogic;
use Service\User\Model\UserModel;

/**
 * Description of Account 专用于微信发送模板业务
 *
 * @author abc
 */
class WXSendSiteLogic {

    public $wxtemplate = '';

    public function __construct() {
        $this->wxtemplate = new WXTemplateLogic();
    }

    /**
     * 充值发送模板消息
     * @param type $userID
     * @param type $money
     */
    public function wxSendRechargeSite($userID, $money) {
        $userModel = new UserModel();
        $userInfo = $userModel->getUserAccountInfo($userID);
        if (strlen($userInfo["openID"]) > 8) {//wx微信充值发送消息
            $availableMoney = $userInfo['availableMoney'] * 1 ;
            $this->wxtemplate->rechargeSend($userInfo, $money, $availableMoney);
        }
    }
    
    /**
     *  提现发送模板消息
     * @param type $userID
     * @param type $money
     */
    public function wxSendCashSite($userID, $money) {
        $userModel = new UserModel();
        $where['userID'] = $userID;
        $userInfo = $userModel->getUser("openID,phone", $where);
        if (strlen($userInfo["openID"]) > 8) {//wx微信充值发送消息
            $this->wxtemplate->cashSend($userInfo, $money);
        }
    }
    

}
