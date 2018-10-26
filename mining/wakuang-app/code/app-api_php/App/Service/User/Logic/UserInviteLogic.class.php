<?php

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

namespace Service\User\Logic;

use Service\User\Model\UserInviteModel;
use Service\Operate\Logic\TicketLogic;
use Service\Common\FinishEventLogic;

class UserInviteLogic {

    private $model = null;

    public function __construct() {
        $this->model = new UserInviteModel();
    }

    /**
     * 邀请记录查询
     * @param type $where
     * @param type $whereArr
     * @param type $limit
     */
    public function getInvite($where, $whereArr, $limit) {
        $result = $this->model->getInvite($where, $whereArr, $limit);
        return $result;
    }

    /**
     * 用户邀请
     * @param type $userInfo
     * @return type
     */
    public function friend($userInfo) {
        $where["userID"] = $userInfo["userID"];
        $data["userName"] = $userInfo["userName"];
        $data["userID"] = $userInfo["userID"];
        $data["phone"] = $userInfo["phone"];
        $data["status"] = 1;
        $result = $this->model->friend($where, $data);
        return $result;
    }

    /**
     * 获取个人推荐的用户
     * @param type $userID
     * @param type $limit
     */
    public function getRecommendList($userID, $limit) {
        $result = $this->model->getRecommendList($userID, $limit);
        foreach ($result['rows'] as $key => $value) {
            $result['rows'][$key]['regDatetimeFormat'] = date("Y-m-d H:i", $value['regDatetime']);
            $result['rows'][$key]['userNameFormat'] = hidUserName($value['userName']);
            $result['rows'][$key]['rewardMoneyFormat'] = formatMoney($value['rewardMoney']);
        }
        return $result;
    }

    /**
     * 获取总的用户人数
     * @param type $inviteUserID
     */
    public function getInviteCount($inviteUserID, $startDate = 0, $endDate = 0) {
        $ret = $this->model->getInviteCount($inviteUserID, $startDate, $endDate);
        return $ret;
    }

    /**
     * 获取总的用户邀请并人数
     * @param type $inviteUserID
     */
    public function getInviteECount($inviteUserID, $startDate = 0, $endDate = 0) {
        $ret = $this->model->getInviteECount($inviteUserID, $startDate, $endDate);
        return $ret;
    }

    /**
     * 查寻邀请人下被邀请人 总投资额
     * @param type $inviteUserID
     * @return real
     */
    public function getInviteMoney($inviteUserID, $startdate = 0, $enddate = 0) {
        if (empty($inviteUserID)) {
            return Array("totalMoney" => "0.00", "tenderInterest" => "0.00");
        }
        $ret = $this->model->getInviteMoney($inviteUserID, $startdate, $enddate);
        $ret["totalMoney"] = roundMoney($ret["totalMoney"]);
        $ret["tenderInterest"] = $ret["tenderInterest"] * 1;
        return $ret;
    }

    /**
     * 个人推荐用户统计
     * @param type $userID
     * @return type
     */
    public function getRecommendCount($userID) {
        $result = $this->model->getRecommendCount($userID);
        return $result;
    }

    /**
     * 根据ID找邀请码
     * --
     */
    public function getInviteUser($userID) {
        $ret = $this->model->getInviteUser($userID);
        return $ret;
    }

    /**
     * 邀请奖励
     */
    public function inviteReward() {
        $inviteReward = new UserInviteRewardLogic();
        $actDate = strtotime($inviteReward->actdate);
        $user = $this->model->getInviteAuthor($actDate);
        foreach ($user as $key => $value) {

            //过滤没有投资
            if ($value["tenderMoney"] * 1 <= 0) {
                continue;
            }
            //不是未处理状态
            if ($value["rewardStutas"] * 1 != 0) {
                continue;
            }
            $ticketlist = C("TICKET_INVITE");
            if (empty($ticketlist)) {
                continue;
            }
            $tickctArr = split(',', $ticketlist);
            //添加站内信
            $finLogic = new FinishEventLogic();
            $finData["phone"] = $value["phone"];
            $logic = new TicketLogic();
            foreach ($tickctArr as $v) {
                $logic->sendUserTikcket($v, $value["inviteUserID"], 1, "邀请好友", 1);
                $finData["ticketNumber"] = $v;
                $finLogic->invitEvent($value["inviteUserID"], $finData);
            }
            $data["rewardStutas"] = 1;
            $data["rewardType"] = 108;
            $data["rewardName"] = '邀请送现金券';
            $where["userID"] = $value["userID"];
            $where["inviteUserID"] = $value["inviteUserID"];
            $ret = $this->model->editInvite($data, $where);
            if ($ret) {
                //插入到邀请奖励表
                $rewardLogic = new UserInviteRewardLogic();
                foreach ($tickctArr as $v) {
                    $rewardLogic->saveReward($value["inviteID"], $value["inviteUserID"], $value["inviteUserPhone"], 3, $v, 2);
                }
            }
        }
        return false;
    }

    /**
     * 获得邀请好友及投资总额列表
     * @param $where type       查询条件
     * @param $where1 type       查询条件
     * @param $whereArr         查询条件数组
     * @param $limit            分页
     */
    public function getInviteAndTener($where, $where1, $whereArr, $limit) {
        return $this->model->getInviteAndTener($where, $where1, $whereArr, $limit);
    }

    /**
     * 邀请人数排行榜
     * @param type $where         查询条件 （邀请好友添加时间）
     * @param type $whereArr      查询数组
     * @return array
     */
    public function invitePersonRanking($where = '', $whereArr = '') {
        return $this->model->invitePersonRanking($where, $whereArr);
    }

    /**
     * 邀请投资金额排行榜
     * @param type $where         查询条件 （邀请好友投资时间）
     * @param type $whereArr      查询数组
     * @return array
     */
    public function inviteTenderRanking($where = '', $whereArr = '') {
        return $this->model->inviteTenderRanking($where, $whereArr);
    }

    /**
     *  获取邀请人的信息
     * @param type $data
     */
    public function getInvitInfo($where) {
        $startTime = strtotime(date("2017-4-14"));
        $endTime = strtotime(date("2017-5-2"));
        $where["addDatetime"] = array(array("egt", $startTime), array("lt", $endTime));
        return $this->model->getInvitInfo($where);
    }

    /**
     * 获取邀请信息
     * @param type $userID
     */
    public function getinvitByID($userID) {
        if ($userID * 1 < 1) {
            return false;
        }
        $where["userID"] = $userID;
        $result = $this->model->getInvitInfo($where);
        return $result;
    }

    /**
     * 获取邀请信息
     */
    public function getInviteDetail($where) {
        if (!is_array($where) || empty($where)) {
            return false;
        }
        $result = $this->model->getInvitInfo($where);
        return $result;
    }

}
