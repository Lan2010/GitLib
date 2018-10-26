<?php

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

namespace MobileApi\Api;

use MobileApi\Common\MobileApiBase;
use Service\Common\smsLogic;
use Service\News\Logic\SendSMSLogic;
use Service\User\Logic\UserLogic;
use Service\News\Logic\SendEmailLogic;
use Common\Common\Redis;

class ApiSms extends MobileApiBase {

    public function getRules() {
        return array(
            'regCode' => array(
                'phone' => array('name' => 'phone', 'type' => 'string', 'require' => true, 'desc' => '手机号码')
            ),
            'findCode' => array(
                'phone' => array('name' => 'phone', 'type' => 'string', 'require' => true, 'desc' => '手机号码')
            ),
            "loginPhoneCode" => array(
                'phone' => array('name' => 'phone', 'require' => true,  'desc' => '用户手机号码'),
            ),
        );
    }

    /**
     * 注册发送验证码
     * @desc 注册发送验证码
     * @return int code 操作码，0表示成功， 1表示失败 
     * @return string msg 提示信息
     */
    public function regCode() {

        $rs = array('code' => 0, 'msg' => '', 'info' => array());
        $userLogic = new UserLogic();
        $info = $userLogic->getByPhone($this->phone);
        if(!empty($info)) {
            $rs['code'] = 1;
            $rs['msg'] = "已经注册过了";
            return $rs;
        }

        $data["code"] = 0;
        $data["msg"] = "验证码发送成功";
        $data["info"] = array();
        return $data;
        $sms = new smsLogic();
        return $sms->send($this->phone,'reg');
    }

    /**
     * 找回密码发送验证码
     * @desc 忘记密码发送验证码
     * @return int code 操作码，0表示成功， 1表示失败 
     * @return string msg 提示信息
     */
    public function findCode() {
        $data["code"] = 0;
        $data["msg"] = "验证码发送成功";
        $data["info"] = array();
        return $data;
        $userLogic = new UserLogic();
        $info = $userLogic->getByPhone($this->phone);
        if(empty($info)) {
            $rs['code'] = 1;
            $rs['msg'] = "该账户未注册过";
            return $rs;
        }
        $sms = new smsLogic();
        return $sms->send($this->phone,'find');
    }





    /**
     * 验证码登录发送验证码
     * @desc 登录发送验证码 
     * @return int code 操作码，0表示成功， 1表示失败 
     * @return string msg 提示信息
     */
    public function loginPhoneCode() {
        $data["code"] = 0;
        $data["msg"] = "验证码发送成功";
        $data["info"] = array();
        return $data;

        $info = $userLogic->getByPhone($this->phone);
        if(empty($info)) {
            $rs['code'] = 1;
            $rs['msg'] = "该账户未注册过";
            return $rs;
        }
        $sms = new smsLogic();
        return $sms->send($this->phone,"login");
    }

}
