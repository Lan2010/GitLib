<?php

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

namespace Service\Common;

use Service\User\Logic\UserLogic;
use Service\Business\Logic\TenderLogic;
use Service\Business\Logic\HdAwardLogic;
use Service\Operate\Logic\CpsLogic;
use Common\Common\CostCompute;
use Service\Operate\Logic\TicketLogic;
use Service\Operate\Logic\IntegralLogic;
use Common\Common\Redis;
use Service\Business\Logic\BorrowLogic;
use Service\Operate\Logic\ActivityLogic;
use Service\Account\Logic\AccountRechargeLogic;
use Service\Operate\Model\ActivityModel;
use Service\User\Logic\UserInviteLogic;

/**
 * Description of FinishEvent
 *
 * @author DREAM
 */
class FinishEventLogic {

    /**
     * 注册事件
     * @param type $userID
     * @param type $phone
     * @return boolean
     */
    public function regEvent($userID) {
        sendSiteNews($userID, "Register");
        $tickLogic = new TicketLogic();
        $ticketlist = C('TICKET_REG');
        if (empty($ticketlist)) {
            return false;
        }
        $ticketArr = split(',', $ticketlist);
        foreach ($ticketArr as $value) {
            $tickLogic->sendUserTikcket($value, $userID, 1, '用户注册', 1);
        }
        return true;
    }

    /**
     * 开通存管账户事件。
     * @param type $userID
     * @param type $accNO
     * @param type $realName
     */
    public function createHxEvent($userID) {
        sendSiteNews($userID, "OpenedEAccount");
        $integLogic = new IntegralLogic();
        $type = "RealAuth";
        $operateIP = get_client_ip();
        $integLogic->updateIntegral($userID, $type, $operateIP);
        $time = strtotime("2017-5-23");
        $regStart = $this->getUserReg($userID, $time); //必须要在活动期间注册
        if (empty($regStart)) {
            return false;
        }
        if (time() >= $time) {
            $ticketlist = C("TICKET_ADDACT");
            if (!empty($ticketlist)) {
                $tickLogic = new TicketLogic();
                $ticketArr = split(',', $ticketlist);
                foreach ($ticketArr as $value) {
                    $tickLogic->sendUserTikcket($value, $userID, 1, "开通存管账户", 1);
                }
            }
        }
        $logic = new ActivityLogic();
        $logic->sendBagEvent($userID, 3); //二月活动，邀请开户送福袋
        return true;
    }

    /**
     * 首次充值事件
     * @param type $userID
     */
    public function rechargeEvent($userID, $money) {
        //过滤以前注册现在首次投资的用户
        $time = strtotime("2017-5-23");
        $regStart = $this->getUserReg($userID, $time); //必须要在活动期间注册
        if (empty($regStart)) {
            sendSiteNews($userID, "Recharge", array($money));
            return false;
        }
        $tickLogic = new TicketLogic();
        if (time() >= $time) {
            $chregLogic = new AccountRechargeLogic();
            $regInfo = $chregLogic->rechargeTotal($userID);
            if (empty($regInfo) || $regInfo["count"] * 1 > 1) {
                return false;
            }
            $ticketlist = C('TICKET_RECHARGE');
            if (empty($ticketlist)) {
                return;
            }
            $ticketArr = split(',', $ticketlist);
            foreach ($ticketArr as $value) {
                $tickLogic->sendUserTikcket($value, $userID, 1, '用户首次充值', 1);
            }
            sendSiteNews($userID, "FirstRecharge", array($money));
        }
        return true;
    }

    /**
     * 投资事件
     * @param type $tender
     * @param type $borrow
     */
    public function tenderEvent($tender, $borrow) {
        $act = new ActivityLogic();
        $act->tbActReward($borrow, $tender);
//        $act->sendJanActAward($tender["userID"]); //一月活动投资奖励
//        $act->sendBagEvent($tender["userID"], 4); //二月活动，投资送福袋
//        $act->getWaterByTender($tender["userID"]); //三月活动，获取浇水次数
        $act->getThrowByTender($tender["userID"]);//五月活动，获取投色子次数
        $time = strtotime("2017-5-23");
        $regStart = $this->getUserReg($tender["userID"], $time); //必须要在活动期间注册
        if (empty($regStart)) {
            return false;
        }
        if (time() >= $time) {
            $tend = new TenderLogic();
            $count = $tend->getfirstTender($tender['userID']);
            if ($count) {
                $ticketlist = C('TICKET_ADDIVM');
                if (empty($ticketlist)) {
                    return;
                }
                $ticketArr = split(',', $ticketlist);
                $tickLogic = new TicketLogic();
                foreach ($ticketArr as $value) {
                    $tickLogic->sendUserTikcket($value, $tender["userID"], 1, '用户首次投资', 1);
                }
                sendSiteNews($tender["userID"], "FirstTender", array($tender['realTenderMoney']));
            }
        }


        return;
    }

    /**
     * 获取用户注册时间
     * @param type $userID
     */
    private function getUserReg($userID, $time) {
        $userLogic = new UserLogic();
        $userInfo = $userLogic->getUser($userID);
        if (!empty($userInfo)) {
            if ($userInfo["regDatetime"] < $time) {
                return false;
            }
        } else {
            return false;
        }
        return true;
    }

    /**
     * CPS事件。
     * @param type $tender
     * @param type $borrow
     * @return boolean
     */
    public function cpsEvent($tender, $borrow) {
        if (empty($tender) || empty($borrow))
            return false;
        $cpsconfig = C('CPS_CONFIG');
        if (empty($cpsconfig) || !is_array($cpsconfig))
            return false;
        $rate = $cpsconfig[$borrow['borrowType']];
        $cpslogic = new CpsLogic();
        $isForCps = $cpslogic->checkUserForCps($tender['userID']);
        if ($isForCps && $isForCps['userID'] * 1 > 0 && $isForCps['count'] * 1 > 0 && !empty($rate) && $rate * 1 > 0) {
            //佣金信息
            $entity['userID'] = $isForCps['userID'];
            $entity['userName'] = $isForCps['userName'];
            $nowtime = time();
            $entity['accYear'] = date("Y", $nowtime);
            $entity['accMonth'] = date("m", $nowtime) * 1;
            $entity['rewardRate'] = $rate;
            $entity['amount'] = CostCompute::totalInterest($tender['realTenderMoney'], $rate, $borrow["borrowLimit"], $borrow["repaymentStyle"], $borrow["borrowDays"]);
            if (empty($entity['amount']) || $entity['amount'] * 1 <= 0)
                return false;
            //标信息
            $entity['borrowNO'] = $borrow['borrowNO'];
            $entity['borrowType'] = $borrow['borrowType'];
            $entity['borrowApr'] = $borrow['borrowApr'];
            $entity['borrowDays'] = $borrow['borrowDays'];
            $entity['borrowLimit'] = $borrow['borrowLimit'];
            $entity['repaymentStyle'] = $borrow['repaymentStyle'];
            //投资信息
            $entity['tenderNO'] = $tender['tenderNO'];
            $entity['tenderUserID'] = $tender['userID'];
            $entity['tenderUserName'] = $tender['phone'];
            $entity['tenderMoney'] = $tender['tenderMoney'];
            $entity['realTenderMoney'] = $tender['realTenderMoney'];
            $entity['tenderDatetime'] = $tender['addDatetime'];

            $cpslogic->addFees($entity);
            return true;
        }
        return false;
    }

    /**
     * 双11活动送券 || 2018年腊八节登录送券
     */
    public function loginEvent($userID, $terminal = 2) {
        $logic = new ActivityLogic();
        $logic->sendBagEvent($userID, 1); //二月活动送福卡
        $festivalTime = strtotime("2018-3-2");
        $festivalEndTime = strtotime("2018-3-3");
        $nowTime = time();
        if ($nowTime * 1 >= $festivalTime && $nowTime < $festivalEndTime) {
            $userLogic = new UserLogic();
            $userInfo = $userLogic->getUser($userID);
            if (($userInfo["realStatus"] * 1 == 1) || ($userInfo["regDatetime"] * 1 >= strtotime("2016-9-26") && $userInfo["regDatetime"] * 1 <= strtotime("2018-3-2"))) {
                $tickLogic = new TicketLogic();
                $ticketArr = $this->getTicketList(C('TICKET_ACT_LOGIN'));
//            $redis = Redis::GetInstance();
                foreach ($ticketArr as $value) {
                    $tickLogic->sendUserTikcket($value['ticketNo'], $userID, 1, "元宵节活动", $terminal);
//                if ($sendInfo["status"] == 1) {
//                    $redis->set("ShowSendTicketTip_" . $userID, "送券活动");
//                }
                }
            }
        }
    }

    /**
     * 增加抽奖次数
     * @param type $userID
     * @param type $second 抽奖次数
     * @param type $type  抽奖类型
     * @param type $actID 活动ID
     */
    public function addThreeYear($userID, $second, $type, $actID, $drawName) {
        $actLogic = new ActivityLogic();
        $startTime = strtotime($actLogic->threeYearStart);
        $endTime = strtotime($actLogic->threeYearEnd);
        $dateInfo = $actLogic->activityOver($startTime, $endTime);
        if ($dateInfo["status"] * 1 == 0) {
            return false;
        }
        $model = new ActivityModel();
        $where = array();
        $result = false;
        if ($type * 1 == 4) {
            $tunWhere["userID"] = $userID;
            $tunWhere["drawType"] = $type;
            $tunityInfo = $model->getTunity($tunWhere);
            if (empty($tunityInfo)) {//为空就增加数据
                $data["actType"] = $actID;
                $data["userID"] = $userID;
                $data["drawName"] = $drawName;
                $data["drawType"] = $type;
                $data["totalTunity"] = $second;
                $data["addDatetime"] = strtotime(date("Ymd"));
            } else {
                $tunWhere["addDatetime"] = strtotime(date("Ymd")); //获取今天是否已经添加过抽奖机会了
                $tunityDayInfo = $model->getTunity($tunWhere);
                if (empty($tunityDayInfo)) {
                    $data["totalTunity"] = array('exp', 'totalTunity+1');
                    $data["usedTunity"] = $tunityInfo[0]["usedTunity"];
                    $data["lightTunity"] = $tunityInfo[0]["lightTunity"];
                    $data["addDatetime"] = strtotime(date("Ymd"));
                }
            }
        } elseif ($type * 1 == 2) {
            $data["actType"] = $actID;
            $data["userID"] = $userID;
            $data["drawName"] = $drawName;
            $data["drawType"] = $type;
            $data["totalTunity"] = $second;
            $data["addDatetime"] = strtotime(date("Ymd"));
        }

        if (!empty($data)) {
            $twhere["actType"] = $actID;
            $twhere["drawType"] = $type;
            $twhere["userID"] = $userID;
            $info = $model->getTunTal($twhere);
            if (!empty($info)) {
                $where["id"] = $info["id"];
            }
            $result = $model->saveTunity($data, $where);
        }
        return $result;
    }

    /**
     * 获取券信息集合。
     * @param type $strTickets
     * @return boolean|int
     */
    private function getTicketList($strTickets) {
        if (empty($strTickets))
            return false;
        $tickctArr = split(',', trim($strTickets));
        $tickctlist = array();
        foreach ($tickctArr as $key => $value) {
            $infoArr = split('_', $value);
            $count = count($infoArr);
            $arr = array();
            if ($count > 0) {
                $arr['ticketNo'] = $infoArr[0];
                if ($count > 1) {
                    $arr['money'] = $infoArr[1];
                } else {
                    $arr['money'] = 0;
                }
                $tickctlist[] = $arr;
            }
        }
        return $tickctlist;
    }

    /**
     * 关注订阅号发送5元现金券
     * @param type $userID
     */
    public function followEvent($userID) {
        $tickLogic = new TicketLogic();
        $ticketlist = C('TICKET_FLW');
        if (empty($ticketlist)) {
            return;
        }
        $ticketArr = split(',', $ticketlist);
        foreach ($ticketArr as $value) {
            $tickLogic->sendUserTikcket($value, $userID, 1, '关注订阅号', 1);
        }
        return true;
    }

    /**
     * 提现回调
     * @param type $userID
     * @param type $money
     */
    public function cashEvent($userID, $money) {
        if (empty($userID) || empty($money)) {
            return false;
        }
        sendSiteNews($userID, "CashSuccess", array($money));
        return true;
    }

    /**
     * 邀请回调
     * @param type $userID
     * @param type $data
     */
    public function invitEvent($userID, $data) {
        if (empty($data["ticketNumber"]) || empty($userID)) {
            return false;
        }
        $ticketModel = new \Service\Operate\Model\TicketModel();
        $ticketInfo = $ticketModel->getTicketEntity($data["ticketNumber"]);
        $data["phone"] = hidPhone($data["phone"]);
        sendSiteNews($userID, "InviteFriend", array($data["phone"], $ticketInfo["ticketCredits"]));
        return true;
    }

    /**
     * 评测送券回调
     * @param type $userID
     */
    public function evalEvent($userID) {
        $startTime = strtotime("2017-11-25"); //正式环境  11月27日
        $endTime = strtotime("2017-12-1");
        $nowTime = time();
        if ($nowTime < $startTime || $endTime < $nowTime) {
            return false;
        }
        $proLogic = new \Service\User\Logic\ProblemLogic();
        $userProCount = $proLogic->getProbAnwTal($userID);
        if ($userProCount * 1 > 1) {
            return true;
        }
        $tickLogic = new TicketLogic();
        $ticketNumber = C('TICKET_EVALUAT');
        if (empty($ticketNumber)) {
            return false;
        }
        $tickLogic->sendUserTikcket($ticketNumber, $userID, 1, '评测送券', 1);
        return true;
    }

}
