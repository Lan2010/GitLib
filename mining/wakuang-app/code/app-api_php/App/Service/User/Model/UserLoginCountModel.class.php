<?php

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

namespace Service\User\Model;

use Think\Model;
use Common\Model\SlaveModel;

/**
 * 用户登录次数合计
 *
 * @author DREAM
 */
class UserLoginCountModel extends SlaveModel {

    /**
     * 用户登录日志
     * @param type $where
     * @param type $whereArr
     * @param type $limit
     * @return type
     */
    public function getUserLog($where, $whereArr, $limit) {
        $sqlcount = "SELECT COUNT(1) AS count FROM d_user_login_count AS A 
                      INNER JOIN d_user AS B ON  A.user_id=B.user_id
                     WHERE (1=1)" . $where . " ";
        $total = $this->SlaveDB()->query($sqlcount, $whereArr);
        $result["total"] = $total[0]["count"];
        $sql = "SELECT A.user_id,B.real_name,B.phone,A.login_times,A.mobile_times,A.wechat_times,A.pc_times,
                 A.last_login_ip,A.last_login_datetime FROM d_user_login_count AS A 
                 INNER JOIN d_user AS B ON  A.user_id=B.user_id
                WHERE (1=1)" . $where . " ORDER BY last_login_datetime DESC LIMIT %s;";
        array_push($whereArr, $limit);
        $result["rows"] = $this->SlaveDB()->query($sql, $whereArr);
        return $result;
    }

    /**
     * 获取最近的15条数据
     * @param type $where
     * @param type $whereArr
     * @param type $limit
     * @return type
     */
    public function getLogItem($where, $whereArr, $limit) {
        $sql = "SELECT A.user_id,A.phone,A.equipment,A.login_ip,A.login_datetime,A.terminal FROM d_user_login_log AS A 
                WHERE (1=1) " . $where . " ORDER BY login_datetime DESC LIMIT %s;";
        array_push($whereArr, $limit);
        $result["rows"] = $this->SlaveDB()->query($sql, $whereArr);
        return $result;
    }

    public function getLogCount($where) {
        $sql = "select count(DISTINCT FROM_UNIXTIME(login_datetime,'%Y-%m-%d')) as total from d_user_login_log where equipment<>' '" . $where;
        $result = $this->SlaveDB()->query($sql);
        return $result[0]["total"];
    }

    /**
     * 登录日志
     * @param type $userID
     * @param type $phone
     * @param type $terminal 终端
     * @param type $equipment 终端信息
     * @param type $operateIP IP
     * @return type
     */
    public function AddLoginLog($userID, $phone, $terminal, $equipment, $operateIP) {
        $model = new Model();
        $result = $model->query("CALL sp_Login_log('%d','%s','%d','%s','%s')", $userID, $phone, $terminal, $equipment, $operateIP);
        return $result;
    }
    
    
    /**
     *获取到上次用户登录的设备信息和记录
     * --
     */
    public function getPreUserLoginInfo($userID) {
        $sql = "SELECT * from (SELECT * from d_user_login_log WHERE user_id=".$userID." ORDER BY log_id DESC LIMIT 2) y WHERE (1=1) ORDER BY log_id LIMIT 1";
        return $this->SlaveDB()->query($sql);
    }

}
