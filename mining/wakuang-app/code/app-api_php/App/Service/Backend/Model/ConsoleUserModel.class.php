<?php

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

namespace Service\Backend\Model;

use Think\Model;
use Common\Model\SlaveModel;

/**
 * Description of ConsoleUserModel
 *
 * @author DREAM
 */
class ConsoleUserModel extends SlaveModel {

    /**
     * 后台用户
     * @param type $where
     * @param type $whereArr
     * @param type $limit
     * @return type
     */
    public function getUserList($where, $whereArr, $limit) {
        $countsql = "SELECT COUNT(1) AS count FROM `d_console_user` AS A  
               LEFT JOIN d_console_roleuser AS B ON A.user_id=B.user_id
               LEFT JOIN d_console_role AS C ON B.role_id=C.role_id
              WHERE " . $where . " GROUP BY A.user_id";
        $total = $this->query($countsql, $whereArr);
        $result["total"] = count($total);
        $sql = "SELECT A.*,B.role_user_id,C.role_name ,concat(GROUP_CONCAT(C.role_name))AS user_role FROM `d_console_user` AS A  
              LEFT JOIN d_console_roleuser AS B ON A.user_id=B.user_id
              LEFT JOIN d_console_role AS C ON B.role_id=C.role_id
              WHERE " . $where . "  GROUP BY A.user_id ORDER BY A.user_id DESC limit %s;";
        array_push($whereArr, $limit);
        $result["rows"] = $this->query($sql, $whereArr);
        return $result;
    }

    /**
     * 获取单个用户
     * @param type $where
     * @param type $field
     * @return type
     */
    public function getSinUser($where, $field) {
        $model = M("ConsoleUser");
        $result = $model->field($field)->where($where)->find();
        return $result;
    }

    /**
     * 获取多个用户
     * @param type $where
     * @param type $field
     * @return type
     */
    public function getMoreUser($where, $field) {
        $model = M("ConsoleUser");
        $result = $model->field($field)->where($where)->select();
        return $result;
    }

    /**
     * 保存后台用户信息
     * @param type $where
     * @param type $data
     */
    public function saveUser($where, $data) {
        try {
            $model = M("ConsoleUser");
            if (is_array($where) && count($where) > 0) {
                $result = $model->where($where)->data($data)->save();
                return $result * 1 > 0 ? $where["user_id"] : false;
            } else {
                return $model->add($data);
            }
        } catch (Exception $ex) {
            logger(__METHOD__, $ex->getMessage());
            return false;
        }
    }

    /**
     * 获取理财顾问统计
     * @return type
     */
    public function getAdviserCount() {
        $sql = "SELECT A.userID,A.cusName,COUNT(C.attendantID) AS count FROM d_console_user A " .
                " LEFT JOIN (SELECT B.attendantID FROM d_user B WHERE B.regDatetime>=1507564800) C ON C.attendantID=A.userID " .
                " WHERE A.`status`=1 AND A.userType=8  GROUP BY A.userID";
        return $this->SlaveDB()->query($sql);
    }

    /**
     * 根据手机号码及姓名获取指定后台用户信息
     * @param type $phone
     * @param type $realName
     * @return type
     */
    public function getUserInfoForKF($phone, $realName) {
        $sql = "SELECT DISTINCT A.userID,A.userName,A.realName FROM d_console_user A " .
                " WHERE A.phone='%s' AND A.realName='%s' AND A.`status`=1 AND EXISTS(SELECT 1 FROM d_console_roleuser B WHERE B.userID=A.userID AND B.roleID IN (1,4,7,11,18)) limit 1";
        return $this->query($sql, $phone, $realName);
    }

}
