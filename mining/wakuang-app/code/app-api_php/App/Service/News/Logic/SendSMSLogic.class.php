<?php

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

namespace Service\News\Logic;

use Common\Common\Redis;
use Service\User\Logic\UserLogic;
use Service\News\Logic\WXTemplateLogic;

/**
 * 短信消息体组合，站内信消息组合
 *
 * @author DREAM
 */
class SendSMSLogic {

    private $XAName = "希奥";
    private $XASend = null;
    private $logic = null;
    private $mobileLock = 'mobile_check_Lock_'; //同一手机号码验证短信次数不能超过5次 前缀
    private $mobileLockTime = 600; //验证失败次数过多，锁定10分钟

    public function __construct() {
        $this->XASend = new IOOSMSLogic();
        $this->logic = new SmsVerifyLogic();
    }

    /**
     * 希奥通讯验证码发送通道
     */
    public function sendSMS($mobile, $content) {
        if (APP_DEBUG) {
            return true;
        }
        $result = $this->XASend->sendCode($mobile, $content);
        if ($result !== true) {
            logger("发送短信失败结果是：" . $result);
            return false;
        }
        return true;
    }

    /**
     * 希奥通讯通知短信发送通道
     */
    public function sendNotice($mobile, $content) {
        if (APP_DEBUG) {
            return true;
        }
        $result = $this->XASend->sendNotice($mobile, $content);
        if ($result !== true) {
            logger("sendNotice发送短信失败结果是：" . $result);
            return false;
        }
        return true;
    }
    
     /**
     * 希奥通讯营销短信发送通道
     */
    public function sendMarket($mobile, $content) {
        if (APP_DEBUG) {
            return true;
        }
        $result = $this->XASend->sendMarket($mobile, $content);
        if ($result !== true) {
            logger("sendMarket发送短信失败结果是：" . $result);
            return false;
        }
        return true;
    }

    /**
     * 发送验证码
     * @param type $mobile
     * @param type $type
     * @param type $code
     * @param type $terminal
     * @param type $time
     * @return type
     */
    private function sendValid($mobile, $type, $code, $terminal, $time) {
        $smstArr = C('phone_Message');
        $content = $smstArr["$type"];
        $content = sprintf($content, $code);
        $this->sendSMS($mobile, $content);
        $data ['receiveName'] = $mobile;
        $data ['receivePhone'] = $mobile;
        $data ['overdueTime'] = $time; //time() + 60 * 5;
        $data ['verifyCode'] = $code;
        $data ['sendInterface'] = $this->XAName;
        $data ['sendContent'] = $content;
        $data["terminal"] = $terminal;
        $data['operateIP'] = get_client_ip(); // 参数传入
        $result = $this->logic->addVerify($data);
        return $result;
    }

    /**
     * 确认手机号
     * @param type $mobile
     * @param type $code
     * --
     */
    public function ConfirmVerify($mobile, $code) {
        $cache = Redis::GetInstance();
        $firm = $cache->get($mobile . "_Valid");
        $ret = $this->codeSock($mobile);
        if (!$ret) {
            return false; //同一验证码次数不能超过5次
        }
        if ($firm) {
            if ($firm == $code) {
                $this->codeUnLock($mobile);
                return true;
            }
        } else {
            $result = $this->logic->getConfirmVerify($mobile, $code);
            if ($result) {
                $this->codeUnLock($mobile);
            }
            return $result;
        }
    }

    /**
     * 计算手机短信验证次数
     * @param type $mobile
     * 
     */
    public function codeSock($mobile) {
        $lock = $this->mobileLock . $mobile;
        $cache = Redis::GetInstance();
        $times = $cache->get($lock);
        if ($times && $times >= 6) {
            return false;
        }
        //失败次数累加
        if ($times && $times < 6) {
            $cache->set($lock, $times + 1, $this->mobileLockTime);
        } else {
            $cache->set($lock, 1, $this->mobileLockTime);
        }
        return true;
    }

    /**
     * 解锁手机验证次数
     * @param type $mobile
     */
    public function codeUnLock($mobile) {
        $lock = $this->mobileLock . $mobile;
        $cache = Redis::GetInstance();
        $cache->del($lock);
    }

    /**
     * 发送短信验证码
     * @param type $userID
     * @param type $username
     * @param type $mobile
     * @param type $type
     */
    public function sendCode($mobile, $type, $terminal) {
        $time = time() + 60 * 5;
        $cache = Redis::GetInstance();
        $over = $cache->get($mobile . "_2_SMSCode_" . $type); //两分钟内不能重复发送同一个业务
        $count = $cache->get($mobile . "_10_SMSCode"); //十分钟内发送三条
        $varlast = 120 - (time() - $over) - 1;
        if ($varlast * 1 > 1) {
            return "请 " . $varlast . " 秒后再重试获取!";
        }
        if ($count > 2) {
            return "您发送的短信过多！";
        }
        $code = rand_code(); //生成手机验证
        $result = $this->sendValid($mobile, $type, $code, $terminal, $time);
        if ($result) {
            $this->codeUnLock($mobile);
            $cache->set($mobile . "_Valid", $code, 121); //两分钟内有效
            $cache->set($mobile . "_10_SMSCode", $count * 1 + 1, 600); //限制10分钟发送三条
            $cache->set($mobile . "_2_SMSCode_" . $type, time(), 120);
            return true;
        }
        return "发送失败！";
    }

    /**
     * @desc 发送短信注册成功
     * 2015年8月11日
     * @abstract HM
     * --
     */
    public function sendMsg($userID, $phone, $type) {
        if (strlen($phone) > 10) {
            $smstArr = C('phone_Message');
            $content = $smstArr[$type];
            $this->sendMarket($phone, $content);
            $sms["receiveUserID"] = $userID;
            $sms["receivePhone"] = $phone;
            $sms["sendContent"] = $content;
            $sms["smsType"] = $type;
            $sms["sendInterface"] = $this->XAName;
            $send = new SmsSendLogic();
            $send->addSend($sms);
        }
        return true;
    }

    /**
     * 发送生日等短信。
     * @param type $userID
     * @param type $phone
     * @param type $type
     * @param type $content
     */
    public function sendOtherMsg($userID, $phone, $type, $content) {
        if (strlen($phone) > 10 && !empty($content)) {
            $this->sendMarket($phone, $content);//营销短信
            $sms["receiveUserID"] = $userID;
            $sms["receivePhone"] = $phone;
            $sms["sendContent"] = $content;
            $sms["smsType"] = $type;
            $sms["sendInterface"] = $this->XAName;
            $send = new SmsSendLogic();
            $send->addSend($sms);
        }
        return true;
    }

    /**
     * 发送融资人还款成功信息
     * @param type $userID
     * @param type $title
     * @param type $money
     * @return boolean
     */
    public function RepayMsg($userID, $title, $limit, $money) {
        $logic = new UserLogic();
        $userInfo = $logic->getPhone($userID); //获取手机号码   
        //短信
        if (strlen($userInfo["phone"]) > 10) {
            $smstArr = C('phone_Message');
            $content = $smstArr['Repay'];
            $content = sprintf($content, $userInfo["userName"], $title, $limit, $money);
            $this->sendNotice($userInfo["phone"], $content); //通知短信
            $sms["receiveUserID"] = $userID;
            $sms["receivePhone"] = $userInfo["phone"];
            $sms["sendContent"] = $content;
            $sms["smsType"] = "还款成功";
            $sms["sendInterface"] = $this->XAName;
            $send = new SmsSendLogic();
            $send->addSend($sms); //短信
        }
        return true;
    }

    /**
     * 发送融资成功信息
     * @param type $userID
     * @param type $title
     * @return boolean
     */
    public function LoanMsg($userID, $title) {
        $logic = new UserLogic();
        $userInfo = $logic->getPhone($userID); //获取手机号码    
//        //短信
        if (strlen($userInfo["phone"]) > 10) {
            $smstArr = C('phone_Message');
            $content = $smstArr['Loan'];
            $content = sprintf($content, $userInfo["userName"], $title);
            $this->sendNotice($userInfo["phone"], $content);
            $sms["receiveUserID"] = $userID;
            $sms["receivePhone"] = $userInfo["phone"];
            $sms["sendContent"] = $content;
            $sms["smsType"] = "融资成功";
            $sms["sendInterface"] = $this->XAName;
            $send = new SmsSendLogic();
            $send->addSend($sms); //短信
        }
        return true;
    }

    /**
     * 投资成功短信---批量
     * @param type $tender
     */
    public function tenderSMS($tender) {
        $sms = array();
        $wxTemplate = new WXTemplateLogic();
        foreach ($tender as $k => $value) {
            //微信投资通知
            if (strlen($value["openID"]) > 8) {
                $wxTemplate->tenderSend($value);
            }
            if (strlen($value["phone"]) < 10) {
                continue;
            }
            $sm = $this->getSms("Tender", $value["userID"], $value["phone"], $value["borrowTitle"], $value["TenderMoney"]);
            if ($sm) {
                $sms[$k] = $sm;
                $this->sendNotice($value["phone"], $sm["sendContent"]); //发送短信
            }
            sendSiteNews($value["userID"], "Tender", array($value["borrowTitle"], $value["TenderMoney"]));
        }
        $send = new SmsSendLogic();
        $send->addALLSend($sms);
        return true;
    }

    /**
     * 还款短信 对投资人--- 批量
     * @param type $repay
     */
    public function collectSMS($repay) {
        $sms = array();
        $smsType = "RepayCollect";
        $wxTemplate = new WXTemplateLogic();
        foreach ($repay as $k => $value) {
            //微信还款通知
            if (strlen($value["openID"]) > 8) {
                $wxTemplate->repSend($value);
            }
            if (strlen($value["phone"]) < 10) {
                continue;
            }
            $sm = $this->getSms($smsType, $value["userID"], $value["phone"], $value["borrowTitle"], $value["CollectMoney"] * 1 == 0 ? $value["collectionMoney"] : $value["CollectMoney"], $value["repaymentOrder"] . "/" . $value["borrowLimit"]);
            if ($sm) {
                $sms[$k] = $sm;
                $this->sendNotice($value["phone"], $sm["sendContent"]); //发送短信
            }
            $arr = array($value["borrowTitle"], $value["repaymentOrder"] . "/" . $value["borrowLimit"], $value["CollectMoney"] * 1 == 0 ? $value["collectionMoney"] : $value["CollectMoney"]);
            sendSiteNews($value['userID'], "RepayCollect", $arr);
        }
        $send = new SmsSendLogic();
        $send->addALLSend($sms);
        return true;
    }

    /**
     * 获取短信信息
     * @param type $type
     * @param type $userID
     * @param type $phone
     * @param type $title
     * @param type $money
     * @param type $limit
     * @return boolean
     */
    private function getSms($type, $userID, $phone, $title, $money, $limit = "") {
        $smstArr = C('phone_Message');
        $content = $smstArr["$type"];
        switch ($type) {
            case "Tender":
                $sms["sendContent"] = sprintf($content, $phone, $title, $money);
                $sms["smsType"] = "投资成功";
                break;
            case "RepayCollect":
                $sms["sendContent"] = sprintf($content, $phone, $title, $limit, $money);
                $sms["smsType"] = "还款成功";
                break;
            case "TicketPresenter":
                $sms["sendContent"] = sprintf($content, $phone, $money, $title);
                $sms["smsType"] = "赠送加息券";
                break;
            default :
                return false;
        }
        $sms["sendDatetime"] = time();
        $sms["receiveUserID"] = $userID;
        $sms["receivePhone"] = $phone;
        $sms["sendInterface"] = $this->XAName;
        return $sms;
    }

}
