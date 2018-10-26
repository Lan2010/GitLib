<?php

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

namespace Service\User\Model;

use Common\Model\SlaveModel;
use Think\Exception;

/**
 * Description of UserRealnameModel
 *
 * @author DREAM
 */
class UserRealnameModel extends SlaveModel {

    /**
     * 新增实名申请
     * @param type $entity
     */
    public function AddRealName($entity) {
        if (is_array($entity)) {
            $entity['add_datetime'] = time();
            $model = M("UserRealname");
            $result = $model->data($entity)->add();
            return $result;
        }
        return false;
    }

    /**
     * 获取用户实名身份信息
     * @return boolean
     */
    public function getUserReal($field, $where) {
        if (count($where) < 1) {
            return false;
        }
        $model = M("UserRealname");
        $result = $model->field($field)->where($where)->find();
        return $result;
    }

    /**
     * 获取实名认证记录
     * @return type
     */
    public function getRealname($where, $whereArr, $limit = 0) {
        $result["total"] = $this->SlaveDB()->table("d_user_realname r")->join("INNER JOIN  d_user u on u.user_id = r.user_id")->where($where, $whereArr)->count();
        $field = "r.user_id,r.order_no,r.real_name,r.card_id,r.card_address,r.audit_status,r.user_age,r.user_sex,r.add_datetime,r.terminal,u.phone,r.real_id";
        if (!empty($limit)) {
            $result["rows"] = $this->SlaveDB()->table("d_user_realname r")->join("INNER JOIN  d_user u on u.user_id = r.user_id")->where($where, $whereArr)->field($field)->order('real_id desc')->limit($limit)->select();
        } else {
            $result["rows"] = $this->SlaveDB()->table("d_user_realname r")->join("INNER JOIN d_user u on u.user_id = r.user_id")->where($where, $whereArr)->field($field)->order('real_id desc')->select();
        }
        return $result;
    }

    /**
     * 获取到银行卡的信息
     */
    public function getBank($where, $whereArr, $limit = 0) {
        $result["total"] = $this->SlaveDB()->table("d_user_bank r")->join("INNER JOIN  d_user u on u.user_id = r.user_id")->where($where, $whereArr)->count();
        $field = "r.bank_id,r.order_no,r.real_name,r.order_no,r.bank_code,r.add_datetime,r.bank_name,r.bank_card_no,r.branch,r.status,r.terminal,u.phone,r.bank_img";
        if (!empty($limit)) {
            $result["rows"] = $this->SlaveDB()->table("d_user_bank r")->join("INNER JOIN  d_user u on u.user_id = r.user_id")->where($where, $whereArr)->field($field)->order('bank_id desc')->limit($limit)->select();
        } else {
            $result["rows"] = $this->SlaveDB()->table("d_user_bank r")->join("INNER JOIN d_user u on u.user_id = r.user_id")->where($where, $whereArr)->field($field)->order('bank_id desc')->select();
        }
        return $result;
    }

    /**
     * 获取实名数据
     * @param type $userID
     * @param type $orderNO
     */
    public function getRealNameNO($orderNO) {
        $where["orderNO"] = $orderNO;
        $where["auditStatus"] = 0;
        $result = $this->field("userID,userAge,userSex,birthyear,birthday,cardAddress,auditStatus")->where($where)->find();
        return $result;
    }

    /**
     * 在线充值审核
     * @param type $data
     * @param type $where
     */
    public function saveRealName($data, $where) {
        $data["auditUserID"] = 2;
        $data["auditUserName"] = "管理员";
        $data["auditDatetime"] = time();
        $result = $this->where($where)->data($data)->save();
        return $result;
    }

    /**
     * 保存用户信息
     * @param type $userID
     * @param type $data
     * @return type
     */
    public function saveHXUserInfo($userID, $data) {
        $modelInfo = M("UserInfo");
        $where["userID"] = $userID;
        $data["editDatetime"] = time();
        $exist = $modelInfo->field("userID")->where($where)->find();
        if ($exist["userID"] * 1 > 1) {
            $result = $modelInfo->where($where)->data($data)->save();
            return $result;
        } else {
            $data["userID"] = $userID;
            $result = $modelInfo->data($data)->add();
            return $result;
        }
    }

    /**
     * 保存用户信息
     * @param type $where
     * @param type $data
     * @return type
     */
    public function saveUserInfo($where, $data) {
        $modelInfo = M("UserInfo");
        if ($where["userID"] * 1 < 1) {
            return false;
        }
        $result = $modelInfo->where($where)->data($data)->save();
        return $result;
    }

    /**
     * 获取用户信息
     * @param type $where
     * @param type $field
     */
    public function getUserInfo($where, $field) {
        $modelInfo = M("UserInfo");
        $result = $modelInfo->field($field)->where($where)->find();
        return $result;
    }

    /**
     * 销户
     * @param type $where
     * @param type $data
     */
    public function saveRealInfo($where, $data) {
        $result = $this->where($where)->data($data)->save();
        return $result;
    }

}
