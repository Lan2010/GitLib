<?php

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

namespace Service\Common;

use Common\Common\RabbitMQ;

/**
 * Description of CallBackLogic
 *
 * @author Administrator
 */
class CallBackLogic {

    /**
     * 当前队列名称
     * @var type 
     */
    private $currQueueName = "";

    /**
     * 当前队列名称
     * @var type 
     */
    private $currExchange = "";

    /**
     * 目标交换器
     * @var type 
     */
    private $targetExchange = "";

    /**
     * 延时时长
     * @var type 
     */
    private $sendTicket = null;

    /**
     * 初始化消息服务器
     * @param type $isDelay
     * @param type $ticket
     * @param type $queueName
     * @param type $exchange
     */
    private function initRabbitMQ($isDelay = false, $ticket = 0, $queueName = "Callback", $exchange = "CallBack.Exchange") {
        if (empty($queueName) || empty($exchange)) {
            return false;
        }
        $suffix = "";
        (($ticket * 1) > 0 && $isDelay === true) && $suffix = "_" . $ticket;
        $targetExchange = 'Delay' . $exchange . $suffix;
        $this->sendTicket = null;
        $this->currQueueName = $queueName;
        $this->currExchange = $exchange;
        $ra = RabbitMQ::getInstance();
        $ra->initService($queueName, $exchange);
        $ra->setQoS('0', '1', '0');
        if ($isDelay === true && ($ticket * 1 > 0)) {
            $this->sendTicket = $ticket * 1000;
            $this->currDelayQueue = $delayQueue = 'Delay' . $queueName . $suffix;
            $this->targetExchange = $targetExchange;
            $ra->createDelayExchange($targetExchange);
            $ra->createDelayQueue($delayQueue, $exchange, $this->sendTicket);  //将延时队列绑定到目标交换机
            $ra->queueBind($delayQueue, $targetExchange);  //将延时队列与延时交换机绑定
        } else {
            $this->targetExchange = "";
        }
        return $ra;
    }

    /**
     * 发送回调消息。
     * @param type $returnurl 回调url
     * @param type $data      需要发送的数据
     * @param type $isDelay   是否延时队列
     * @param type $ticket    延时时长（单位秒）
     * @return type
     */
    public function sendCallbackInfo($returnurl = "", $data = array(), $isDelay = false, $ticket = 0) {
        if (empty($returnurl) || trim($returnurl) === "" || !is_array($data) || count($data) == 0)
            return;
        $data["returnurl"] = $returnurl;
        try {
            $ra = $this->initRabbitMQ($isDelay, $ticket);
            return $ra && $ra->sendMessage($data, '', $this->targetExchange, $this->sendTicket);
        } catch (Exception $ex) {
            logger_sys("发送单条消息异常", $ex->getMessage());
            return false;
        }
    }

    /**
     * 发送通知。
     * @param type $data
     * @param type $isDelay
     * @param type $ticket
     * @return boolean
     */
    public function sendNotice($data = array(), $isDelay = false, $ticket = 0) {
        if (empty($data) || count($data) == 0) {
            return false;
        }
        try {
            $ra = $this->initRabbitMQ($isDelay, $ticket, 'Notice', 'Notice.Exchange');
            return $ra && $ra->sendMessage($data, '', $this->targetExchange, $this->sendTicket);
        } catch (Exception $ex) {
            logger_sys("发送单条消息异常", $ex->getMessage());
            return false;
        }
    }

    /**
     * 批量发送消息
     * @param type $data
     * @param type $isDelay
     * @param type $ticket
     * @param type $queueName
     * @param type $exchange
     * @return type
     */
    public function sendBatchMsg($data = array(), $isDelay = false, $ticket = 0, $queueName = "Callback", $exchange = "CallBack.Exchange") {
        if (empty($data) || count($data) == count($data, 1))
            return false;
        try {
            $ra = $this->initRabbitMQ($isDelay, $ticket, $queueName, $exchange);
            if ($ra) {
                foreach ($data as $value) {
                    $ra->sendMessage($value, '', $this->targetExchange, $this->sendTicket);
                }
                return true;
            } else {
                return false;
            }
        } catch (Exception $ex) {
            logger_sys("批量发送消息异常", $ex->getMessage());
            return false;
        }
    }

    /**
     * 法大大 发送消息专用
     * @param type $returnurl 回调url
     * @param type $data      需要发送的数据
     * @return type
     */
    public function fddSendCallBack($returnurl = "", $data = array(),$isDelay = false, $ticket = 0) {
        if (empty($returnurl) || trim($returnurl) === "" || !is_array($data) || count($data) == 0)
            return;
        $data["returnurl"] = $returnurl;
        try {
            $ra = $this->initRabbitMQ($isDelay,$ticket, "File", "File.Exchange");
            return $ra && $ra->sendMessage($data, '', $this->targetExchange, $this->sendTicket);
        } catch (Exception $ex) {
            logger_sys("发送单条消息异常", $ex->getMessage());
            return false;
        }
    }

    /**
     * 发送站内消息回调
     * @param type $data
     * @return type
     */
    public function sendSiteNewsCallback($newsType = "", $data = array()) {
        if (empty($newsType) || trim($newsType) === "")
            return;
        $data["newsType"] = $newsType;
        $returnurl = SITE . "Backend/GoCallback/sendSiteNews";
        $this->sendCallbackInfo($returnurl, $data);
    }

    /**
     * 发送标结清通知回调。
     * @param type $borrowNO
     */
    public function sendSettleNoticeCallback($borrowNO) {
        if (empty($borrowNO) || trim($borrowNO) === "")
            return;
        $data['borrowNO'] = $borrowNO;
        $returnurl = SITE . "Backend/GoCallback/borrowSettleNotice";
        $this->sendCallbackInfo($returnurl, $data, true, 21600);
    }

    /**
     * 华兴银行主动通知我们用户销户
     * @param type $accno  存管账户号码
     */
    public function hxDstyAccount($accno) {
        if (empty($accno) || trim($accno) === "")
            return;
        $data['accno'] = $accno;
        $returnurl = SITE . "Backend/GoCallback/hxDstyAccount";
        $this->sendCallbackInfo($returnurl, $data);
    }

    /**
     * 自动还款
     * @param type $borrowNO
     * @param type $repaymentNO
     * @return type
     */
    public function autoRepay($borrowNO, $repaymentNO, $repaymentMoney, $repaymentOrder) {
        if (empty($borrowNO) || trim($borrowNO) === "" || empty($repaymentNO) || trim($repaymentNO) === "")
            return;
        $data['borrowNO'] = $borrowNO;
        $data['repaymentNO'] = $repaymentNO;
        $data['repaymentMoney'] = $repaymentMoney;
        $data['repaymentOrder'] = $repaymentOrder;
        $returnurl = SITE . "Backend/GoCallback/autoRepay";
        $sendTime = strtotime(date("Y-m-d", time()) . ' 15:30:00');
        $ticket = $sendTime - time();
        $this->sendCallbackInfo($returnurl, $data, true, $ticket);
    }

    /**
     * 15点将自动还款标放入队列
     */
    public function addRepaysDayToRq() {
        $returnurl = SITE . "Backend/GoCallback/awaitAutoRepaysDay";
        $sendTime = strtotime(date("Y-m-d", time()) . ' 13:50:00');
        $ticket = $sendTime - time();
        $data["returnurl"] = $returnurl;
        $this->sendCallbackInfo($returnurl, $data, true, $ticket);
    }

    /**
     * 自动复审
     * @param type $borrowNO
     * @return type
     */
    public function autoReview($borrowNO) {
        if (empty($borrowNO) || trim($borrowNO) === "")
            return;
        $returnurl = SITE . "Backend/GoCallback/autoReview";
        $data["borrowNO"] = $borrowNO;
        $this->sendCallbackInfo($returnurl, $data);
    }

    /**
     * 提交收益明细
     * @param type $orderNO
     * @param type $borrowNO
     * @param type $repaymentOrder
     * @return type
     */
    public function payToCollect($orderNO, $borrowNO, $repaymentOrder) {
        if (empty($orderNO) || trim($orderNO) == "" || empty($borrowNO) || trim($borrowNO) === "")
            return;
        $returnurl = SITE . "Backend/GoCallback/payToCollect";
        $data["orderNO"] = $orderNO;
        $data["borrowNO"] = $borrowNO;
        $data["repaymentOrder"] = $repaymentOrder;
        $this->sendCallbackInfo($returnurl, $data);
    }

}
