<?php

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

namespace Service\User\Model;

use Common\Model\SlaveModel;
use Think\Model;

/**
 * 用户邀请
 *
 * @author DREAM
 */
class UserInviteModel extends SlaveModel {

    /**
     * 新增邀请记录
     * @param array $entity
     * @return type
     */
    public function addInvite($entity) {
        try {
            $entity["add_datetime"] = time();
            $model = M("UserInvite");
            $result = $model->data($entity)->add();
            return $result;
        } catch (Exception $ex) {
            logger(__METHOD__, $ex->getMessage());
            return false;
        }
    }

    /**
     * 获取总的用户人数
     * @param type $userID
     */
    public function getInviteCount($userID, $startDate = 0, $endDate = 0) {
        if (empty($userID)) {
            return 0;
        }
        $where["invite_user_id"] = $userID;
        if (!empty($startDate)) {
            $where['add_datetime'][0] = array('egt', $startDate);
        }
        if (!empty($endDate)) {
            $where['add_datetime'][1] = array('elt', $endDate);
        }
        $count = $this->where($where)->count();
        return $count;
    }
    
    /**
     * 获取被邀请用户且开通存管账户的数量
     */
    public function getInviteECount($userID, $startDate = 0, $endDate = 0) {
        if (empty($userID)) {
            return 0;
        }
        $where["a.inviteUserID"] = $userID;
        if (!empty($startDate)) {
            $where['a.addDatetime'][0] = array('egt', $startDate);
        }
        if (!empty($endDate)) {
            $where['a.addDatetime'][1] = array('elt', $endDate);
        }
        $where["b.userStatus"] = 1;
        $where["b.realStatus"] = 1;
        $count = $this->alias('a')->join('d_user b ON a.userID= b.userID')->where($where)->count();
        return $count;
    }
    

    /**
     * 查寻邀请人下被邀请人 总投资额
     * @param type $inviteUserID
     * @return real
     */
    public function getInviteMoney($inviteUserID, $startdate = 0, $enddate = 0) {
        if (empty($inviteUserID)) {
            return false;
        }
        $borrowApr = 7.5;
        $where .= " i.inviteUserID =  " . $inviteUserID;
        $where .= " and b.borrowApr> " . $borrowApr;
        if (!empty($startdate)) {
            $where .= " and t.addDatetime >= " . $startdate;
        }
        if (!empty($enddate)) {
            $where .= " and t.addDatetime <= " . $enddate;
        }


        $sql = "SELECT SUM(t.realTenderMoney) as totalMoney , SUM(t.tenderInterest) as tenderInterest from d_user_invite  i 
                INNER JOIN d_borrow_tender t on i.userID=t.userID 
                LEFT JOIN d_borrow b ON b.borrowNO = t.borrowNO
                WHERE " . $where . " and t.moneySource=1 and t.tenderStatus>-1 and t.tenderStatus<2 ";
        $rows = $this->query($sql);
 
        return $rows[0];
    }

    /**
     * 获取个人推荐用户的信息 
     * @param type $userID
     * @param type $limit
     */
    public function getRecommendList($userID, $limit) {
        $model = M();
        $sqlcount = "SELECT  count(1) as count,sum(rewardMoney) as rewardMoney  FROM d_user_invite as a where a.inviteUserID='%d'";
        $sql = "SELECT a.userName,a.userID, b.regDatetime  as regDatetime ,SUM(a.rewardMoney) as rewardMoney
                FROM d_user_invite as a INNER JOIN d_user as b ON a.userID=b.userID where a.inviteUserID='%d'  GROUP BY b.userID limit %s";
        $total = $model->query($sqlcount, $userID);
        $result["total"] = $total[0]["count"];
        $result["rows"] = $model->query($sql, $userID, $limit);
        $result["rewardMoney"] = $total[0]["rewardMoney"];
        return $result;
    }

    /**
     * 统计推荐
     * @param type $userID
     * @return type
     */
    public function getRecommendCount($userID) {
        $model = M();
        $sqlcount = "SELECT  count(1) as count,sum(rewardMoney) as rewardMoney  FROM d_user_invite as a  where a.inviteUserID='%d'";
        $result = $model->query($sqlcount, $userID);
        if ($result) {
            return $result[0];
        }
        return array("count" => 0, "rewardMoney" => 0);
    }

    /**
     * 用户要求记录
     * @param type $where
     * @param type $whereArr
     * @param type $limit
     * @return type
     */
    public function getInvite($where, $whereArr, $limit) {
        $sqlcount = "SELECT COUNT(1) AS count FROM d_user_invite AS A INNER JOIN d_user as B ON A.user_id = B.user_id INNER JOIN d_user as C ON C.user_id = A.invite_user_id
                 WHERE (1=1)" . $where . " ";
        $total = $this->SlaveDB()->query($sqlcount, $whereArr);
        $result["total"] = $total[0]["count"];
        $sql = "SELECT A.invite_id,B.phone,C.phone as invite_user_phone,A.reward_type,
               A.reward_stutas,A.reward_name,A.reward_stutas,add_datetime FROM d_user_invite AS A 
               INNER JOIN d_user as B ON A.user_id = B.user_id INNER JOIN d_user as C ON C.user_id = A.invite_user_id WHERE (1=1) " . $where . " ORDER BY A.add_datetime DESC LIMIT %s;";
        array_push($whereArr, $limit);
        $result["rows"] = $this->SlaveDB()->query($sql, $whereArr);
        return $result;
    }

    /**
     * 根据邀请码获取信息
     * @param type $code
     * @return type
     */
    public function getUserCode($code) {
        $model = M("UserInviteCode");
        if (strlen($code) > 2) {
            $where['code'] = $code;
            $where['status'] = 1;
            $result = $model->field("userID,userName,phone,code")->where($where)->find();
            return $result;
        }
    }

    /**
     *  查询用户邀请码
     * @param type $where
     * @param type $data
     * @return type
     */
    public function friend($where, $data) {
        $model = M("UserInviteCode");
        $result = $model->where($where)->find();
        if ($result["status"] * 1 == 0) {
            $result = $model->field("`codeID`,`code`,`status`")->where("`status`=0")->order('codeID asc')->find();
            $code["code"] = $result["code"];
            $edit = $model->where($code)->data($data)->save();
            if ($edit * 1 > 0) {
                return $result['code'];
            }
        } elseif ($result["status"] * 1 == 1) {
            return $result['code'];
        }
        return false;
    }

    /**
     * 获取邀请者信息
     * @param type $userID
     * @return type
     * --
     */
    public function getInviteUser($userID) {
        $model = M("UserInviteCode");
        if ($userID * 1 > 0) {
            $where['user_id'] = $userID;
            $where['status'] = 1;
            $result = $model->field("user_id,user_name,phone,code")->where($where)->find();
            return $result;
        }
    }

    /**
     * 查询邀请者
     * @param type $userID
     */
    public function getInviteAuthor($startDateMic) {
        if(empty($startDateMic)){
            return false;
        }
        $sql = 'SELECT  A.inviteID,A.rewardStutas,A.userID,A.inviteUserID,A.inviteUserPhone, sum(T.realTenderMoney) as tenderMoney  FROM d_user_invite A '
                . 'LEFT JOIN d_borrow_tender T on T.userID = A.userID AND T.moneySource = 1 AND T.tenderStatus > -1 AND T.tenderStatus < 2  '
                . 'WHERE A.rewardStutas=0 AND A.addDatetime>' . $startDateMic . ' AND EXISTS(SELECT 1 FROM d_user B WHERE B.userID=A.userID AND B.realStatus=1) '
                . 'GROUP BY A.userID';
        $result = $this->query($sql);
        return $result;
    }

    /**
     * 邀请记录修改
     * @param type $data
     * @param type $where
     * @return type
     */
    public function editInvite($data, $where) {
        $result = $this->where($where)->data($data)->save();
        return $result;
    }

    /**
     * 获得邀请好友及投资总额列表
     * @param $where type       查询条件（邀请好友添加时间）
     * @param $where1 type      查询条件（投资时间）
     * @param $whereArr         查询条件数组
     * @param $limit            分页
     */
    public function getInviteAndTener($where, $where1, $whereArr, $limit) {
        $sql = "select count(DISTINCT i.userID) as count from d_user_invite  i 
                left JOIN d_borrow_tender t on i.userID=t.userID and t.moneySource=1 and t.tenderStatus>-1 and t.tenderStatus<2 $where1
                where i.inviteUserID = %d $where";
        $count = $this->query($sql, $whereArr);      
        $result['total'] = $count[0]['count'];
        array_push($whereArr, $limit);
        $sql = "select i.userID,u.realStatus as nameStatus,case when u.realStatus=0 then '未实名' else u.realName end  as userName,i.phone,sum(t.realTenderMoney) as totalMoney,i.addDatetime as regDatetime from d_user_invite  i 
                left JOIN d_borrow_tender t on i.userID=t.userID and t.moneySource=1 and t.tenderStatus>-1 and t.tenderStatus<2 $where1
                INNER JOIN d_user u on i.userID=u.userID where i.inviteUserID = %d $where GROUP by i.userID ORDER BY i.addDatetime DESC limit %s";
        $rows = $this->query($sql, $whereArr);
        $result['rows'] = $rows;
        return $result;
    }

    /**
     * 邀请人数排行榜
     * @param type $where           查询条件（邀请好友添加时间）
     * @param type $whereArr        查询条件数组
     * @return array 
     */
    public function invitePersonRanking($where = '', $whereArr = '') {
        $sql = "SELECT count(1) AS num,i.inviteUserID,i.inviteUserPhone FROM d_user_invite i where (1=1) $where GROUP BY i.inviteUserID ORDER BY num DESC limit 5";
        $rows = $this->query($sql, $whereArr);
        return $rows;
    }

    /**
     * 邀请投资金额排行榜
     * @param type $where           查询条件（邀请好友投资时间）
     * @param type $whereArr        查询条件数组
     * @return array 
     */
    public function inviteTenderRanking($where = '', $whereArr = '') {
        $sql = "SELECT sum(t.realTenderMoney) AS tenderMoney,i.inviteUserID,i.inviteUserPhone FROM d_user_invite i INNER JOIN d_borrow_tender t 
		on i.userID = t.userID where  t.moneySource = 1 and  t.tenderStatus>-1 and t.tenderStatus<2 $where GROUP BY i.inviteUserID ORDER BY tenderMoney DESC limit 5";
        $rows = $this->query($sql, $whereArr);
        return $rows;
    }

    /**
     * 获取邀请人的信息
     * @param type $where
     */
    public function getInvitInfo($where) {
        $result = $this->where($where)->find();
        return $result;
    }

}
