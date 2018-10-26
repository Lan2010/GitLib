<?php

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

namespace Service\News\Logic;

use Service\News\Logic\WeiXinLogic;

/**
 * Description of Account
 *
 * @author abc
 */
class WXTemplateLogic {

    private $accessToken;
    public $wxObj;

    public function __construct() {

        $this->wxObj = new WeiXinLogic();
        $this->accessToken = $this->wxObj->accessToken;
    }

    /**
     * 发送模板消息
     * @param type $data
     * @return type
     */
    public function send_template_message($data = null) {

        if (empty($data) || empty($this->accessToken)) {
            return false;
        }
        $dataJson = urldecode(json_encode($data));
        $url = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=" . $this->accessToken;
        $res = $this->wxObj->https_post($url, $dataJson);
        return json_decode($res, TRUE);
    }

    /**
     * 还款通知 
     * @param type $openID 
     * @param type $url 链接地址
     * @param type $first 通知描述
     * @param type $repMoney  还款金额
     * @param type $repDate  还款日期
     * @param type $remark  还款描述
     * @return string
     */
    public function RepTemplate($openID, $url, $first, $repMoney, $repDate, $remark) {
        $template = array(
            "touser" => $openID,
            "template_id" => "R8Z-LjOvhRKHkQZxK8Clbh2sOrPHUW-zDBwSLG6lLyM",
            "url" => $url,
            "data" => array(
                "first" => array("value" => $first, "color" => "#173177"),
                "keyword1" => array("value" => $repDate, "color" => "#173177"),
                "keyword2" => array("value" => $repMoney, "color" => "#173177"),
                "remark" => array("value" => $remark, "color" => "#173177"),
            ),
        );
        return $template;
    }

    public function repSend($value) {
        $url = WECHAT . "Project/plist"; // $value["borrowNO"]
        $first = "亲爱的【" . $value["phone"] . "】，您今天有回款哦！";
        $repDate = date("Y-m-d H:i", $value["realRepayDatetime"] * 1 > 0 ? $value["realRepayDatetime"] : time());
        $repMoney = formatMoney($value["CollectMoney"] * 1 == 0 ? $value["collectionMoney"] : $value["CollectMoney"]) . " 元";
        $remark = "马上投资，再赚一笔>>>";
        $data = $this->RepTemplate($value["openID"], $url, $first, $repMoney, $repDate, $remark);
        $this->send_template_message($data);
    }

    /**
     * 投资成功 
     * @param type $openID
     * @param type $url
     * @param type $first
     * @param type $title 项目名字
     * @param type $capital 本金
     * @param type $remark
     * @return type
     */
    public function TenderTemplate($openID, $url, $first, $title, $capital, $remark) {
        $template = array(
            "touser" => $openID,
            "template_id" => "9uvqXM8C95XcQjNiJqX3lvxbdDSx4FmY_dlKcimxSvM",
            "url" => $url,
            "data" => array(
                "first" => array("value" => $first, "color" => "#173177"),
                "keyword1" => array("value" => $title, "color" => "#173177"),
                "keyword2" => array("value" => $capital, "color" => "#173177"),
                "remark" => array("value" => $remark, "color" => "#173177"),
            ),
        );
        return $template;
    }

    /**
     * 发送投资成功信息。
     * @param type $value
     */
    public function tenderSend($value) {
        $url = WECHAT . "Account/moneyall";
        $first = "亲爱的【" . $value["phone"] . "】，您已经成功投资，开始计息";
        $capital = formatMoney($value["TenderMoney"]) . "元";
        $title = $value["borrowTitle"];
        $remark = "查看账户>>>";
        // $value["openID"] ='oCY27jviTj9Fkyv2zQ0oOtSHSdlc';
        $data = $this->TenderTemplate($value["openID"], $url, $first, $title, $capital, $remark);
        $this->send_template_message($data);
    }

    /**
     *  充值成功 
     * @param type $openID
     * @param type $url
     * @param type $first
     * @param type $money  充值金额
     * @param type $recTime  充值时间
     * @param type $remark
     * @return type
     */
    public function RechargeTemplate($openID, $url, $first, $money, $userMoney, $recTime, $remark) {
        $template = array(
            "touser" => $openID,
            "template_id" => "ibb5jyIWcq95Gymh3L8fNbCaqA1_Z87rkQp9NrlY7NA",
            "url" => $url,
            "data" => array(
                "first" => array("value" => $first, "color" => "#173177"),
                "keyword1" => array("value" => $recTime, "color" => "#173177"),
                "keyword2" => array("value" => $money, "color" => "#173177"),
                "keyword3" => array("value" => $userMoney, "color" => "#173177"),
                "remark" => array("value" => $remark, "color" => "#173177"),
            ),
        );
        return $template;
    }

    /**
     * 充值
     * @param type $user 
     * @param type $money 充值金额
     * @param type $useMoney   可用金额
     */
    public function rechargeSend($user, $money, $useMoney) {
        $url = WECHAT . "Project/plist";
        $first = "亲爱的【" . $user["phone"] . "】，您的充值已成功.";
        $wxMsg = "立即出借>>>";
        $regTime = date("Y-m-d");
        $moneyFormat = formatMoney($money) . " 元";
        $userMoneyFormat = formatMoney($useMoney) . " 元";
        //  $user["openID"] ='oCY27jviTj9Fkyv2zQ0oOtSHSdlc';
        $data = $this->RechargeTemplate($user["openID"], $url, $first, $moneyFormat, $userMoneyFormat, $regTime, $wxMsg);
        $this->send_template_message($data);
    }

    /**
     * 提现成功 
     * @param type $openID
     * @param type $url
     * @param type $first
     * @param type $money
     * @param type $cashTime
     * @param type $remark
     * @return string
     */
    private function CashTemplate($openID, $url, $first, $money, $cashTime, $remark) {
        $template = array(
            "touser" => $openID,
            "template_id" => "tcIT0IbwQi78qm86NVSn9CVkLJF6mBFzcrygNt58_0U",
            "url" => $url,
            "data" => array(
                "first" => array("value" => $first, "color" => "#173177"),
                "money" => array("value" => $money, "color" => "#173177"),
                "timet" => array("value" => $cashTime, "color" => "#173177"),
                "remark" => array("value" => $remark, "color" => "#173177"),
            ),
        );
        return $template;
    }

    /**
     * 提现  
     * @param type $user
     * @param type $money
     */
    public function cashSend($user, $money) {
        $url = WECHAT . "Account/moneyall";
        $first = "亲爱的【" . $user["phone"] . "】，您已经提现成功.";
        $wxMsg = "查看账户>>>";
        $cashTime = date("Y-m-d");
        $moneyFormat = formatMoney($money) . " 元";
        $data = $this->CashTemplate($user["openID"], $url, $first, $moneyFormat, $cashTime, $wxMsg);
        $this->send_template_message($data);
    }

    /**
     * 发送红包
     * @param type $user
     * @param type $money
     */
    public function redSend($user, $money) {
        $first = "亲爱的【" . $user["userName"] . "】，感谢参与“发红包”活动，现赠与您红包奖励.";
        $wxMsg = "红包投资可抵现，马上使用>>>>";
        $moneyFormat = formatMoney($money) . " 元.";
        $data = $this->SendRedTemplate($user["openID"], $first, $moneyFormat, $wxMsg);
        $res = $this->send_template_message($data);
        logger($user["userName"] . "发送红包" . $res["errmsg"]);
        return;
    }

    public function receivedRed($senduser, $receiveduser, $receivedOpenID, $money) {
        $first = "亲爱的【" . $receiveduser . "】，恭喜您成功抢到红包.";
        $keyword1 = "【" . $senduser . "】在钱盒子金融发的红包";
        $wxMsg = "红包投资可抵现，马上使用>>>>";
        $moneyFormat = formatMoney($money) . " 元.";
        $data = $this->ReceiveRedTemplate($receivedOpenID, $first, $keyword1, $moneyFormat, $wxMsg);
        $this->send_template_message($data);
    }

    /**
     * 发红包通知。
     * @param type $openID
     * @param type $first
     * @param type $money
     * @param type $remark
     * @return string
     */
    private function SendRedTemplate($openID, $first, $money, $remark) {
        $template = array(
            "touser" => $openID,
            "template_id" => "Q6fNY46I5K9GBJ-NacHt8kISLbSJK_3ol2k5sbYkG2w",
            "url" => WECHAT . "Project/plist",
            "data" => array(
                "first" => array("value" => $first, "color" => "#173177"),
                "present_income" => array("value" => $money, "color" => "#173177"),
                "remark" => array("value" => $remark, "color" => "#173177"),
            ),
        );
        return $template;
    }

    /**
     * 领取红包通知。
     * @param type $openID
     * @param type $first
     * @param type $money
     * @param type $remark
     * @return string
     */
    private function ReceiveRedTemplate($openID, $first, $keyword1, $money, $remark) {
        $template = array(
            "touser" => $openID,
            "template_id" => "br398amtTSNxbDHgNFRGL8UuGd9Zh0Cq52zaoADZaRk",
            "url" => WECHAT . "Project/plist",
            "data" => array(
                "first" => array("value" => $first, "color" => "#173177"),
                "keyword1" => array("value" => $keyword1, "color" => "#173177"),
                "keyword2" => array("value" => $money, "color" => "#173177"),
                "remark" => array("value" => $remark, "color" => "#173177"),
            ),
        );
        return $template;
    }

    /**
     * 审核模板通知
     * @param type $openID
     * @param type $first
     * @param type $username
     * @param type $phone
     * @param type $reviewData
     * @param type $remark
     * @return type
     */
    private function reviewTemplate($openID, $url, $first, $username, $phone, $reviewData, $remark) {
        $template = array(
            "touser" => $openID,
            "template_id" => "5PF0qqvZ4xczOWW4ArJD28b78e_AK_O6XjDivPVDtAc",
            "url" => $url,
            "data" => array(
                "first" => array("value" => $first, "color" => "#173177"),
                "keyword1" => array("value" => $username, "color" => "#173177"),
                "keyword2" => array("value" => $phone, "color" => "#173177"),
                "keyword3" => array("value" => $reviewData, "color" => "#173177"),
                "remark" => array("value" => $remark, "color" => "#173177"),
            ),
        );
        return $template;
    }

    /**
     * 审核中发送
     * @param type $openID
     * @param type $username
     * @param type $phone
     * @param type $reviewData
     */
    public function reviewSQSend($openID, $username, $phone, $reviewData) {
        $url = "";
        $first = "尊敬的百米代理商，您已申请加入钱盒子金融百米商户联盟，您的申请将在24小时内予以审核。感谢您的支持！（关注钱盒子金融）";
        $remark = "感谢您的参与,钱盒子金融有你更精彩";

        $data = $this->reviewTemplate($openID, $url, $first, $username, $phone, $reviewData, $remark);
        $ret = $this->send_template_message($data);
    }

    /**
     * 审核通过发送
     * @param type $openID
     * @param type $username 
     * @param type $phone
     * @param type $reviewData
     */
    public function reviewTGSend($openID, $username, $phone, $reviewData) {
        $url = "";
        $first = "尊敬的百米代理商，您申请的钱盒子金融百米商户联盟，已经审核通过，快快行动起来吧！（关注钱盒子金融）";
        $remark = "感谢您的参与,钱盒子金融有你更精彩";

        $data = $this->reviewTemplate($openID, $url, $first, $username, $phone, $reviewData, $remark);
        $this->send_template_message($data);
    }

}
