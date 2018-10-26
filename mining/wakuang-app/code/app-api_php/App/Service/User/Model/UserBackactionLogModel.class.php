<?php

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

namespace Service\User\Model;

use Common\Model\SlaveModel;

class UserBackactionLogModel extends SlaveModel {

    /**
     * 增加ICP后台日志 
     * @param type $data
     * @return type
     */
    public function addlog($data) {
        return $this->add($data);
    }

    /**
     * 获取前台ICP日志 
     * @param type $where
     * @param type $whereArr
     * @param type $limit
     */
    public function getBackActLog($where, $whereArr, $limit) {
        $sqlcount = "SELECT COUNT(1) AS count FROM d_console_user A INNER JOIN d_user_backaction_log B ON B.user_id=A.user_id WHERE (1=1) " . $where;
        $total = $this->SlaveDB()->query($sqlcount, $whereArr);
        $result["total"] = $total[0]["count"];
        $sql = "SELECT A.user_name,A.real_name,B.ip,B.action,B.ret,B.add_datetime   
                FROM d_console_user A INNER JOIN d_user_backaction_log B ON B.userID=A.userID 
                WHERE (1=1) " . $where . " ORDER BY B.add_datetime DESC LIMIT %s;";
        array_push($whereArr, $limit);
        $result["rows"] = $this->SlaveDB()->query($sql, $whereArr);
        return $result;
    }

}
