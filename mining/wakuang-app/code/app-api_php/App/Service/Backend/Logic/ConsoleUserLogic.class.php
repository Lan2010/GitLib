<?php

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

namespace Service\Backend\Logic;

use Service\Backend\Model;
use Common\Common\Redis;
use Common\Common\Authorized;
use Service\User\Logic\UserBackactionLogLogic;

/**
 * Description of ConsoleUserLogic
 *
 * @author DREAM
 */
class ConsoleUserLogic {


    /**
     * 获取后台用户
     * @param type $where
     * @param type $whereArr
     * @param type $limit
     * @return type
     */
    public function getUserList($where, $whereArr, $limit) {
        $model = new Model\ConsoleUserModel();
        $where .= " AND A.status > '%d'";
        array_push($whereArr, 0);
        $result = $model->getUserList("(1=1) " . $where, $whereArr, $limit);
        return $result;
    }

    /**
     * 查询要被修改的用户
     * @param type $userID
     */
    public function getUser($userID) {
        if ($userID * 1 < 1) {
            return false;
        }
        $where["user_id"] = $userID;
        $model = new Model\ConsoleUserModel();
        $field = "*";
        $result = $model->getSinUser($where, $field);
        return $result;
    }

    /**
     * 获取后台登录用户的手机号
     * @param type $userName
     * @return boolean
     * --
     */
    public function getUserByName($userName) {
        if (empty($userName)) {
            return false;
        }
        $where["user_name"] = $userName;
        $model = new Model\ConsoleUserModel();
        $field = "phone";
        $result = $model->getSinUser($where, $field);
        return $result["phone"];
    }

    /**
     * 获取所有客服
     * @param type $include 是否包含删除 true是不包含
     * @return type
     */
    public function getAllService($include = true) {
        $where["userType"] = array('GT', 6);
        if ($include) {
            $where["status"] = array('eq', 1);
        }
        $model = new Model\ConsoleUserModel();
        $field = "userID,cusName";
        $result = $model->getMoreUser($where, $field);
        return $result;
    }

    /**
     * 判断当前用户是否是客服
     * @param type $userID
     * @return type
     */
    public function existsService($userID) {
        $where["userID"] = $userID;
        $where["userType"] = array('EQ', 8);
        $model = new Model\ConsoleUserModel();
        $field = "userID,cusName";
        $result = $model->getSinUser($where, $field);
        if (isset($result) && count($result)) {
            return $result;
        }
        return false;
    }

    /**
     *  分配随机客服
     * @return type
     */
    public function getFenpei() {
        $result = $this->getAdvisers();
        $arr = array('userID' => 0, 'cusName' => '');
        if (!empty($result) && is_array($result) && count($result) > 0) {
            $result = $this->arraySequence($result, 'count');
            $arr['userID'] = $result[0]['userID'];
            $arr['cusName'] = $result[0]['cusName'];
            $result[0]['count'] = $result[0]['count'] * 1 + 1;
            $key = 'Financial_Adviser_List';
            $redis = Redis::GetInstance();
            $redis->set($key, $result, 86400 * 7);
        }
        return $arr;
    }

    /**
     * 获取理财顾问列表。
     */
    private function getAdvisers() {
        $key = 'Financial_Adviser_List';
        $redis = Redis::GetInstance();
        $result = $redis->get($key);
        if (empty($result) || !is_array($result) || count($result) == 0) {
            $model = new Model\ConsoleUserModel();
            $result = $model->getAdviserCount();
            $redis->set($key, $result, 86400 * 7);
        }
        return $result;
    }

    /**
     * 二维数组根据字段进行排序
     * @params array $array 需要排序的数组
     * @params string $field 排序的字段
     * @params string $sort 排序顺序标志 SORT_DESC 降序；SORT_ASC 升序
     */
    private function arraySequence($array, $field, $sort = 'SORT_ASC') {
        shuffle($array);
        $arrSort = array();
        foreach ($array as $uniqid => $row) {
            foreach ($row as $key => $value) {
                $arrSort[$key][$uniqid] = $value;
            }
        }
        array_multisort($arrSort[$field], constant($sort), $array);
        return $array;
    }

    /**
     * 后台的登录方法
     * @param array $entity
     * @return boolean
     * --
     */
    public function userLogin($entity) {
        $cache = Redis::GetInstance();
        $lock = "Login_Lock_End_" . $entity["userName"];
        $times = $cache->get($lock);
        if ($times && $times >= 6) {
            return "登录错误次数过多还在锁定中！";
        }
        $where["password"] = md5(md5($entity ["password"]));
        $where["user_name"] = $entity["userName"];
        $where["status"] = array('EQ', 1);
        $model = new Model\ConsoleUserModel();
        $field = "user_id,cus_name,user_name,real_name";
        $result = $model->getSinUser($where, $field);
        $logicsys = new UserBackactionLogLogic();
        if (is_array($result)) {
            $logicsys->addLog($result["user_id"], $result["real_name"], get_client_ip(), "后台登录", "登录成功");
            Authorized::setEndAuth($result["user_id"], $result["user_name"], $result["real_name"], C("SHORT_CODE") . "_Login_End");
            if ($times > 0) {
                $cache->del($lock);
            }
            return true;
        } else {
            if ($times && $times < 6) {
                $cache->set($lock, $times + 1, 7200);
                return "抱歉您还有" . (5 - $times) . "次登录机会.<br/>超出您的账户将锁定两个小时!";
            } else {
                $cache->set($lock, 1, 7200);
                return "抱歉请检查您的用户名和密码是否正确！";
            }
        }
        $logicsys->addLog($result["user_id"], $result["user_name"], get_client_ip(), "后台登录", "登录失败");
        return "抱歉请检查您的用户名和密码是否正确";
    }

    /**
     * 保存用户数据
     * @param type $data
     */
    public function saveUser($data) {
        try {
            $key = 'Financial_Adviser_List';
            $redis = Redis::GetInstance();
            $where = array();
            if ($data["user_id"] * 1 > 0 && count($data) > 0) {
                $where["user_id"] = $data["user_id"];
                $data["edit_user_id"] = C("userID");
                $data["edit_user_name"] = C("userName");
                $data["edit_date_time"] = time();
                unset($data["user_id"]);
            } else {
                $data["add_user_id"] = C("userID");
                $data["add_user_name"] = C("userName");
                $data["add_date_time"] = time();
                $data["password"] = md5(md5("159357"));
            }
            $model = new Model\ConsoleUserModel();
            $result = $model->saveUser($where, $data);
            $redis->del($key);
            return $result;
        } catch (Exception $ex) {
            logger(__METHOD__, $ex->getMessage());
            return false;
        }
    }

    /**
     * 修改用户的密码
     * @param type $data
     * @return boolean
     */
    public function savePass($data) {
        try {
            $where = array();
            if ($data["userID"] * 1 > 0 && count($data) > 0) {
                $data["password"] = md5(md5($data["newPass"]));
                $where["userID"] = $data["userID"];
                $where["password"] = md5(md5($data["oldPass"]));
                $model = new Model\ConsoleUserModel();
                unset($data["userID"]);
                $result = $model->saveUser($where, $data);
                return $result;
            }
            return false;
        } catch (Exception $ex) {
            logger(__METHOD__, $ex->getMessage());
            return false;
        }
    }

    /**
     * 删除后台用户
     * @param type $userID
     */
    public function delUser($userID) {
        if ($userID * 1 < 1) {
            return false;
        }
        $where["user_id"] = $userID;
        $data["status"] = 0;
        $model = new Model\ConsoleUserModel();
        $result = $model->saveUser($where, $data);
        return $result;
    }

    /**
     * 根据手机号码及姓名获取指定后台用户信息
     * @param type $phone
     * @param type $realName
     * @return type
     */
    public function getUserInfoForKF($phone, $realName) {
        if (trim($phone) === "" || !verify_phone($phone) || trim($realName) === "")
            return false;
        $model = new Model\ConsoleUserModel();
        $result = $model->getUserInfoForKF($phone, $realName);
        if ($result && is_array($result)) {
            return $result[0];
        }
        return false;
    }

    /**
     * 扫码登录。
     * @param type $userID
     * @param type $phone
     */
    public function qrLogin($userID, $userName, $realName) {
        $cache = Redis::GetInstance();
        $lock = "Login_Lock_End_QR_" . $userName;
        $times = $cache->get($lock);
        if ($times && $times >= 6) {
            return "登录错误次数过多还在锁定中！";
        }
        $where["userID"] = $userID;
        $where["userName"] = $userName;
        $where["realName"] = $realName;
        $where["status"] = array('EQ', 1);
        $model = new Model\ConsoleUserModel();
        $field = "userID,cusName,userName,realName";
        $result = $model->getSinUser($where, $field);
        $logicsys = new UserBackactionLogLogic();
        if (is_array($result)) {
            $logicsys->addLog($result["userID"], $result["realName"], get_client_ip(), "后台二维码登录", "登录成功");
            Authorized::setEndAuth($result["userID"], $result["userName"], $result["realName"], C("SHORT_CODE") . "_Login_End_QR", 3600 * 8);
            if ($times > 0) {
                $cache->del($lock);
            }
            return true;
        } else {
            if ($times && $times < 6) {
                $cache->set($lock, $times + 1, 7200);
                return "抱歉您还有" . (5 - $times) . "次登录机会，超出您的账户将锁定两个小时!";
            } else {
                $cache->set($lock, 1, 7200);
            }
        }
        $logicsys->addLog($result["userID"], $result["userName"], get_client_ip(), "后台二维码登录", "登录成功失败");
        return "请检查你是否为授权用户";
    }

}
