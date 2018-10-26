<?php

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

namespace Service\User\Model;

use Common\Model\SlaveModel;
use Think\Exception;

class UserModel extends SlaveModel {

    public $userType = array(0 => "普通用户", 1 => '潜在客户', 5 => '大客户', 15 => '公司员工', 20 => '企业用户', 21 => '担保用户', 30 => '融资用户', 40 => '马甲用户');

    /**
     * 用户检测邮箱 或者 电话 用户名
     * Enter description here ...
     * @param unknown_type $key
     * @param unknown_type $type 1 用户名 2 邮箱 3 电话
     */
    public function userCheck($key, $type) {
        if ($type == 2) {
            $where['email'] = $key;
        } else {
            $where['phone'] = $key;
        }
        $where['user_status'] = 1;
        $result = $this->where($where)->count();
        if ($result > 0) {
            return true;
        }
        return false;
    }


    /**
     * 用户登录
     * @param type $phone
     * @param type $password
     * --
     */
    public function userLogin($phone, $password) {
        $where['phone'] = $phone;
        $where['password'] = $password;
        $where['user_status'] = 1;
        $result = $this->field('user_id,phone,user_sex,reg_datetime')->where($where)->find();
        if (is_array($result)) {
            return $result;
        }
        return false;
    }

    /**
     * 获取用户
     * @param type $field
     * @param type $where
     * @return boolean
     */
    public function getUser($field, $where) {
        $where['user_status'] = 1;
        $result = $this->field($field)->where($where)->find();
        if (is_array($result)) {
            return $result;
        }
        return false;
    }

    /**
     * 获取用户
     * @param type $field
     * @param type $where
     * @return boolean
     */
    public function getUserfo($field, $where) {
        $result = $this->field($field)->where($where)->find();
        if (is_array($result)) {
            return $result;
        }
        return false;
    }

    /**
     * 获取用户
     * @param type $field
     * @param type $where
     * @return boolean
     */
    public function getUserMore($field, $where) {
        $where['user_status'] = 1;
        $model = M("User");
        $result = $model->field($field)->where($where)->select();
        if (is_array($result)) {
            return $result;
        }
        return false;
    }



    /**
     * 修改用户数据
     * @param array $data
     * @param type $where
     * @return type
     * --
     */
    public function editUser($data, $where) {
        $where['user_status'] = 1;
        $data['edit_datetime'] = time();
        $result = $this->where($where)->data($data)->save();
        return $result;
    }

    /**
     * 用于验证用户输入的密码是否正确
     * @param type $where
     * --
     */
    public function verifyLoginPassword($where) {
        $model = M("User");
        $result = $model->field("phone")->where($where)->find();
        return $result;
    }

    /**
     * 新增注册用户
     * @param type $entity
     * --
     */
    public function addUser($entity) {
        try {
            if (is_array($entity)) {
                $entity['user_key'] = md5(uniqid(rand(), TRUE));
                $entity['pay_password'] = md5($entity["password"]);
                $entity['password'] = md5(md5($entity ["password"]) . C('MKEY'));
                $entity['phone_status'] = 1;
                $entity['reg_datetime'] = time();
                $entity['reg_ip'] = getIP();
                $model = M("User");
                $result = $model->data($entity)->add();
                return $result;
            }
            return false;
        } catch (Exception $ex) {
            logger(__METHOD__, $ex->getMessage());
            return false;
        }
    }

    /**
     * 用户信息查询
     * @param type $where
     * @param type $whereArr
     * @param type $limit
     */
    public function getUserList($where, $whereArr, $limit) {
        $sqlcount = "SELECT COUNT(1) AS count FROM d_user AS A 
                 WHERE (1=1) " . $where . " ";
        $total = $this->SlaveDB()->query($sqlcount, $whereArr);
        $result["total"] = $total[0]["count"];
        $sql = "SELECT A.user_id,A.user_status,A.real_name,A.reg_datetime,A.reg_terminal,A.phone FROM d_user AS A 
                WHERE (1=1) " . $where . " ORDER BY A.reg_datetime DESC LIMIT %s;";
        array_push($whereArr, $limit);
        $result["rows"] = $this->SlaveDB()->query($sql, $whereArr);
        return $result;
    }





    /**
     * 获取用户状态
     * @param type $userID
     */
    public function getUserInfo($userID) {
        $condition['u.user_id'] = $userID; //用户id
        $condition['u.user_status'] = 1;
        $field = "u.`user_id`,u.`user_nickname`,u.`head_url`,u.`real_name`,"
                . "u.`phone`,u.`open_id`,u.user_token";
        $result = $this->alias("u")->field($field)->where($condition)->find();
        return $result;
    }



    /**
     * 获取总注册人数。
     * @return type
     */
    public function getTotalUser() {
        return $this->SlaveDB()->count('user_id');
    }



    /**
     * 查找是否绑定
     */
    public function bindExist($openID, $userID) {
        $where['open_id'] = $openID;
        if (!empty($userID)) {
            $where['user_id'] = $userID;
        }
        $result = $this->field('user_id,phone,open_id,head_url')->where($where)->find();
        return $result;
    }

    /**
     * 重置登录密码
     * @param type $userName
     * @param type $phone
     * @param type $passWord
     * @param type $terminal
     */
    public function forgotPassword($data, $where) {

        $data["edit_datetime"] = time();
        $result = $this->where($where)->data($data)->save();
        return $result;
    }






    /**
     * 获取用户生日
     * @param type $where
     */
    public function getBirthday($where) {
        $model = M("UserInfo");
        $res = $model->where($where)->find();
        return $res;
    }



    
    

}
