<?php

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

namespace Service\User\Logic;

use Service\Common\smsLogic;
use Service\Nats\Logic\NatsLogic;
use Service\User\Model\UserModel;
use Common\Common\Redis;
use Common\Common\Authorized;
use Service\Account\Logic\AccountLogic;

use Service\News\Logic\SendSMSLogic;
use Service\User\Model\UserInviteModel;
use Service\Backend\Logic\ConsoleUserLogic;
use Service\Common\FinishEventLogic;

use Service\User\Logic\UserActionLogLogic;
use Service\Account\Logic\AccountBankLogic;

/**
 * Description of AccountLogic
 *
 * @author Bourne
 */
class UserLogic
{

    private $model = null;
    private $logic = null;

    public function __construct()
    {
        $this->model = new UserModel();
        $this->logic = new UserLogLogic();
    }

    /**
     * 获取用户类型
     */
    public function getUserType()
    {
        return $this->model->userType;
    }

    /**
     * 用户登录。
     * @param array $entity
     * @return string
     * --
     */
    public function Login($entity)
    {
        $cache = Redis::GetInstance(); //缓存   
        $lock = "Login_Lock_" . $entity["phone"];   //锁
        if ($entity["terminal"] * 1 > 5 || $entity["terminal"] * 1 < 1) {   //接口传递的终端编号有问题？
            return "参数错误！";
        }
        $times = $cache->get($lock);
        if ($times && $times >= 6) {      //判断是否为锁定
            return "登录错误次数过多还在锁定中！";
        }
        $entity["password"] = md5(md5($entity["password"]) . C('MKEY'));     //加密随机盐
        $result = $this->model->userLogin($entity["phone"], $entity["password"]);   //寻找用户
        $actLogic = new UserActionLogLogic();
        if (is_array($result) && $result["user_id"] * 1 > 1) {
            if ($times > 0) {
                $cache->del($lock);
            }
            $actLogic->addLog($result["user_id"], $result["phone"], $entity["operate_ip"], "用户登录", "登录成功", $entity["terminal"]);
            return $result["user_id"];
        } else {
            $actLogic->addLog($result["user_id"], $result["phone"], $entity["operate_ip"], "用户登录", "登录失败", $entity["terminal"]);
            if ($times && $times < 6) {
                $cache->set($lock, $times + 1, 7200);
                return "抱歉您还有" . (5 - $times) . "次登录机会,超出将锁定两个小时!";
            } else {
                $cache->set($lock, 1, 7200);
                return "抱歉请检查您的登录名和密码是否正确！";
            }
        }
        return "抱歉请检查您的登录名和密码是否正确！";
    }

    /**
     * 扫码登录。
     * @param type $userID
     * @param type $phone
     */
    public function qrLogin($userID, $phone, $terminal = 1, $equipment = "", $operateIP = "")
    {
        if ($userID * 1 < 1 || empty($phone) || !verify_phone($phone))
            return false;
        $event = new FinishEventLogic();
        $event->loginEvent($userID, $terminal);
        Authorized::setAuthorized($userID, $phone, $terminal, 30);
        $this->logic->AddLoginLog($userID, $phone, $terminal, $equipment, $operateIP);
        $actLogic = new UserActionLogLogic();
        $actLogic->addLog($userID, $phone, $operateIP, "用户登录", "登录成功", $terminal);
        return true;
    }

    /**
     *  短信登录
     * @param type $phone
     * @param type $smsCode
     * --
     */
    public function smsLogin($entity)
    {
        $sms = new smsLogic();
        $stutas = $sms->validate($entity['smsCode'],$entity['phone'],'login');
        if (!$stutas) {
            return "动态登录验证码错误！";
        }
        $result = $this->getByPhone($entity["phone"]);
        $actLogic = new UserActionLogLogic();
        if (is_array($result) && $result["user_id"] * 1 > 1) {
            $actLogic->addLog($result["user_id"], $result["phone"], $entity["operate_ip"], "用户登录", "登录成功", $entity["terminal"]);
            return $result["user_id"];
        } else {
            $actLogic->addLog($result["user_id"], $result["phone"], $entity["operate_ip"], "用户登录", "登录失败", $entity["terminal"]);
        }
        return "抱歉请检查您的登录名和密码是否正确！";
    }

    /**
     * 第三方间接登录
     * @param type $data
     * @return string
     */
    public function thirdPartyLogin($userInfo)
    {
        $actLogic = new UserActionLogLogic();
        if (is_array($userInfo) && $userInfo["userID"] * 1 > 1) {
            //PC登陆 10分钟redis失效
            if (!isApp()) {
                $event = new FinishEventLogic();
                $event->loginEvent($userInfo["userID"], $userInfo["terminal"]);
                Authorized::setAuthorized($userInfo["userID"], $userInfo["phone"], $userInfo["terminal"], 30);
                $this->logic->AddLoginLog($userInfo["userID"], $userInfo["phone"], $userInfo["terminal"], $userInfo["equipment"], $userInfo["operateIP"]);
            }
            $actLogic->addLog($userInfo["userID"], $userInfo["phone"], $userInfo["operateIP"], "用户登录", "登录成功", $userInfo["terminal"]);
            return $userInfo["userID"];
        } else {
            $actLogic->addLog($userInfo["userID"], $userInfo["phone"], $userInfo["operateIP"], "用户登录", "登录失败", $userInfo["terminal"]);
        }
        return "抱歉登录失败";
    }

    /**
     * 显示登录送券提示。
     * @param type $userID
     */
    public function showLoginEventTip($userID)
    {
        $redis = Redis::GetInstance();
        $tip = $redis->get('ShowSendTicketTip_' . $userID);
        if ($tip != false) {
            $redis->del('ShowSendTicketTip_' . $userID);
        }
        return $tip;
    }

    /**
     * 注册
     * @param type $entity
     * @return string|boolean
     * --
     */
    public function Register($entity)
    {
        if (is_array($entity)) {
            $entity["phone"] = trim($entity["phone"]);
            if ($this->model->userCheck($entity["phone"], 3)) {
                return "数据库存在该手机号码";
            }
            $ret = valid_pass($entity["password"]); //验证密码复杂度
            if ($ret !== true) {
                return $ret;
            }
            $result = $this->model->addUser($entity);
            if ($result * 1 > 0) {
                if (!empty($entity['invite_code'])) {
                    $this->handlInvite($result, $entity['invite_code']); //处理邀请
                }
                if ($entity ["terminal"] == 1) {
                    Authorized::setAuthorized($result, $entity["phone"], $entity["terminal"], 30);
                } elseif ($entity ["terminal"] == 4) {
                    Authorized::setAuthorized($result, $entity["phone"], $entity["terminal"], 30);
                }
                return $result;
            }
        }
        return "非法数据";
    }


    /*
     * 根据UserId来插入用户信息
     * @param type $inviteUserInfo  邀请用户信息
     * @param type $userInfo   被邀请用户信息
     */

    public function addInviterHM($inviteUserInfo, $userInfo)
    {
        if (empty($inviteUserInfo) || empty($userInfo)) {
            return false;
        }
        $model = new UserInviteModel();
        $inviter['user_id'] = $userInfo["user_id"];
        $inviter['invite_user_id'] = $inviteUserInfo["invite_user_id"];
        return $model->addInvite($inviter);
    }


    /**
     * 处理邀请的逻辑
     */
    public function handlInvite($userID, $inviterCode)
    {
        $info = $this->getUserByInviter($inviterCode);
        if (empty($info)) {
            return false;
        }
        $invite_user['invite_user_id'] = $info['user_id'];
        $user_info['user_id'] = $userID;
        return $this->addInviterHM($invite_user, $user_info);

    }

    /**
     * 根据邀请码来获取用户信息
     *
     */
    public function getUserByInviter($code)
    {
        $where['invitation_code'] = strtoupper($code);  //全大写
        $field = 'user_id';
        return $this->model->getUser($field, $where);
    }

    /**
     * 验证用户的重复
     * @param type $key
     * @param type $type
     */
    public function userCheck($key, $type)
    {
        $result = $this->model->userCheck($key, $type);
        return $result;
    }


    /**
     * 获取用户信息
     * --
     */
    public function getUser($userID)
    {
        $where["user_id"] = $userID;
        $field = "user_id,user_key,real_name,phone,reg_datetime,user_status,phone,invitation_code,user_token";
        $result = $this->model->getUser($field, $where);
        return $result;
    }


    /**
     *获取到用户的邀请的人数
     * -
     */
    public function getUserCount($userID)
    {
        $model = new UserInviteModel();
        return $model->getInviteCount($userID);
    }

    /**
     * 根据查询条件获取用户信息
     * @param type $field
     * @param type $where
     * @return type
     */
    public function getUserWhere($field, $where)
    {
        if (strlen($field) > 5 && count($where) > 0) {
            $result = $this->model->getUser($field, $where);
            return $result;
        }
        return false;
    }

    /**
     * 用手机号码获取用户
     * @param type $phone
     * @return type
     * --
     */
    public function getByPhone($phone)
    {
        $where["phone"] = $phone;
        $field = "user_id,user_key,phone,real_name,reg_datetime";
        $result = $this->model->getUser($field, $where);
        return $result;
    }


    /**
     * 用户Token获取用户信息
     * @param $token
     * @return bool
     */
    public function getByToken($token)
    {
        $where["user_token"] = $token;
        $field = "user_id,phone";
        $result = $this->model->getUser($field, $where);
        return $result;
    }


    /**
     * 修改用户
     * @param type $userID
     * @param type $data
     */
    public function editUser($userID, $data)
    {
        if ($userID > 0) {
            $where['user_id'] = $userID; //用户id
            unset($data["user_id"]);
            $result = $this->model->editUser($data, $where);
            return $result;
        } else {
            return false;
        }
    }

    /**
     * 用于验证用户输入的密码是否正确
     * @param type $userID
     * @param type $originalPwd
     * --
     */
    public function verifyLoginPassword($userID, $originalPwd)
    {
        $model = new UserModel();
        $where["user_id"] = $userID;
        $where["password"] = md5(md5($originalPwd) . C('MKEY'));
        $result = $model->verifyLoginPassword($where);
        return $result;
    }

    /**
     * 修改登录密码
     * @param type $userID
     * @param type $oldpassword
     * @param type $password
     * --
     */
    public function editLoginPassword($userID, $oldpassword, $password)
    {
        //验证密码复杂读
        $ret = valid_pass($password);
        if ($ret !== true) {
            return $ret;
        }
        $where['user_id'] = $userID; //用户id
        $where['password'] = md5(md5($oldpassword) . C('MKEY')); //交易密码
        $data['password'] = md5(md5($password) . C('MKEY'));
        $result = $this->model->editUser($data, $where);
        if (!$result) {
            return "修改失败";
        }
        return $result;
    }

    /**
     * 重置登录密码
     * @param type $userID
     * @param type $password
     * --
     */
    public function restLoginPassword($userID, $password, $phone = '')
    {
        //验证密码复杂读
        $ret = valid_pass($password);
        if ($ret !== true) {
            return $ret;
        }
        $where['user_id'] = $userID; //用户id
        $data['password'] = md5(md5($password) . C('MKEY'));
        $result = $this->model->editUser($data, $where);
        if ($result * 1 > 0 && !empty($phone)) {
            $cache = Redis::GetInstance();
            $lock = "Login_Lock_" . $phone;
            $times = $cache->get($lock);
            if ($times * 1 > 0) {
                $cache->del($lock);
            }
        }

        return $result;
    }

    /**
     * 注册是否选中
     */
    public function isRegCheck()
    {
        $isCheck = C("REG_ISCHECK");
        $check = $isCheck * 1 == 1 ? 1 : 0; //1 默认选中  0默认不选中
        return $check;
    }

    /**
     * 忘记密码
     * @param type $userName
     * @param type $phone
     * @param type $passWord
     * @param type $terminal
     */
    public function forgotPassword($phone, $password, $terminal)
    {
        //验证密码复杂读
        $ret = valid_pass($password);
        if ($ret !== true) {
            return $ret;
        }
        $data['password'] = md5(md5($password) . C('MKEY'));
        $data['operateTerminal'] = $terminal;
        $where['phone'] = $phone;
        $result = $this->model->forgotPassword($data, $where);
        return $result;
    }

    /**
     * 微信的绑定
     * @param type $userID
     * @param type $openID
     * @return type
     */
    public function bindWeixin($userID, $openID)
    {
        $where['userID'] = $userID;
        $data['openID'] = $openID;
        $result = $this->model->editUser($data, $where);
        return $result;
    }

    /**
     * 微信取消绑定
     * @param type $openID
     * @return type
     */
    public function cancelWeixin($openID)
    {
        if (strlen($openID) < 5)
            return false;
        $where['openID'] = $openID;
        $data['openID'] = '';
        $result = $this->model->editUser($data, $where);
        return $result;
    }


    /**
     * 获取用户信息
     * @param type $userID
     * --
     */
    public function getSinUserInfo($userID)
    {
        $where['user_id'] = $userID; //用户id
        $field = 'user_id,head_url,real_status,real_name,open_id,bank_status,user_sex,phone';
        $result = $this->model->getUser($field, $where);
        return $result;
    }

    /**
     * 获取用户信息
     * @param type $userID
     * @return type
     * --
     */
    public function getUserInfo($userID)
    {
        $info = $this->model->getUserInfo($userID);
        return $info;
    }


    /**
     * 获取总注册人数。
     */
    public function getTotalUser()
    {
        return $this->model->getTotalUser();
    }


    public function getUserList($where, $whereArr, $limit)
    {
        $result = $this->model->getUserList($where, $whereArr, $limit);
        return $result;
    }

    /**
     * 删除
     *
     */
    public function delUser($userID) {
        if($userID >0 ) {
            return $this->model->delete($userID);
        } else {
            return false;
        }
    }


    /**查询用户是否上传过
     * @param $userID
     * @param $type
     * @return mixed
     */
    public function checkUserUpload($userID, $type)
    {
        $model = M("uploadUser");
        return $model->where(array('user_id' => $userID, 'type' => $type))->find();
    }

    /**添加
     * @param $data
     * @return mixed
     */
    public function addUserUpload($data)
    {
        $model = M("uploadUser");
        return $model->add($data);
    }

    /**
     * 检测邮箱是否已绑定。
     * @param type $email
     * @return boolean
     */
    public function checkEmail($email)
    {
        if (empty($email) || strlen($email) < 3) {
            return true;
        }
        $where['email'] = aes($email);
        $result = $this->model->getUser('emailStatus', $where);
        if ($result && $result['emailStatus'] * 1 == 1) {
            return true;
        }
        return false;
    }


    /**
     * 查询是否绑定
     * @param type $openID
     * @return string 如果绑定的话，$result 是用户的userID
     */
    public function bindExist($openID, $userID)
    {
        if (strlen($openID) > 2) {
            $result = $this->model->bindExist($openID, $userID);
            return $result["userID"];
        }
        return FALSE;
    }

    /**
     * 修改紧急联系人
     * @param type $data
     * @param type $userID
     */
    public function editDear($data, $userID)
    {
        if (empty($userID) || $userID * 1 <= 0)
            return false;
        $where["userID"] = $userID;
        $res = $model = $this->model->editDear($data, $where);
        return $res;
    }

    /**
     * 获取紧急联系人
     * @param type $userID
     */
    public function getDear($userID)
    {
        if (empty($userID) || $userID * 1 <= 0)
            return false;
        $where["userID"] = $userID;
        $res = $model = $this->model->getDear($where);
        return $res;
    }

    /**
     * 查询是否绑定了头像和姓名
     * @param type $nikeName
     * @param type $wximg
     * @param type $userID
     */
    public function bindExistInfo($nikeName, $wximg, $userID)
    {
        if (empty($userID) || $userID * 1 <= 0)
            return false;
        $data = array("status" => 0);
        $where["userID"] = $userID;
        $field = "userNickname,headUrl";
        $result = $this->model->getUser($field, $where);
        if ($result["userNickname"] != $nikeName || $result["headUrl"] != $wximg) {
            $data["status"] = 1;
            $data["headUrl"] = $result["headUrl"];
        }
        return $data;
    }

    /**
     * 微信的绑定
     * @param type $userID
     * @param type $openID
     * @return type
     */
    public function editbindExist($userID, $data)
    {
        if (empty($userID) || $userID * 1 <= 0)
            return false;
        $where['userID'] = $userID;
        $result = $this->model->editUser($data, $where);
        return $result;
    }

    /**
     * 获取用户信息且获取到VIP等级
     * @param type $userID
     */
    public function getUserLevel($userID)
    {
        $where["userID"] = $userID;
        $field = 'u.userID,u.headUrl,u.userType,u.realStatus,u.realName,u.openID,u.bankStatus,u.userSex,u.phone,u.viplevel,u.accNO,b.disCount';
        $result = $this->model->getUserLevel($where, $field);

        if ($result["userType"] * 1 == 20 || $result["userType"] * 1 == 21) {  //企业用户 和 融资用户
            $result["openAccUrl"] = "/user/compRealname.html";
        } else {
            $result["openAccUrl"] = "/user/realName.html";
        };
        return $result;
    }

    /**
     * 获取用户生日
     * @param type $userID
     */
    public function getBirthday($userID)
    {
        $where["userID"] = $userID;
        $where["birthday"] = date("md");
        $model = new UserModel();
        $res = $model->getBirthday($where);
        return $res;
    }

    /**
     * 根据用户ID获取用户KEY
     * @param type $userID
     * @return type
     * --
     */
    public function getUserKeyByUserID($userID)
    {
        $where["user_id"] = $userID;
        return $this->model->getUser('user_key', $where);
    }

    /**
     * 获取智齿需要的信息
     * @param type $key
     */
    public function getZCuser($key)
    {
        $ret = $this->model->getZCuser($key);
        return $ret;
    }

    /**
     * 用户销户
     * @param type $accNO
     */
    public function dstyAccount($accNO)
    {
        if (empty($accNO)) {
            return false;
        }
        $field = 'userID,realStatus,realName,bankStatus,phone,accNO';
        $where["accNO"] = $accNO;
        $where["realStatus"] = 1;
        $logic = new RealNameLogic();
        $bankLogic = new AccountBankLogic();
        $userInfo = $this->model->getUser($field, $where);
        if (!empty($userInfo)) {
            if ($userInfo["realStatus"] * 1 == 1) {
                $data["realStatus"] = 0;
                $data["bankStatus"] = 0;
                $this->model->editUser($data, $where);
                $userID = $userInfo["userID"];
                $logic->saveRealInfo($userID);
                $bankLogic->editBankCard($userID);
                logger_sys("用户销户成功", "用户姓名:" . $userInfo["realName"] . "存管账户:" . $accNO . "销户成功");
                return true;
            }
        }
        logger_sys("用户销户失败", "没有查询到用户信息,或者用户实名状态为 0, 用户的存管账户为:" . $accNO);
        return false;
    }


    /**
     * 通过存管账户获取用户信息
     * @param type $accNO
     */
    public function getUserInfoByAccNO($accNO)
    {
        if (strlen($accNO) * 1 < 15) {
            return false;
        }
        $where['accNO'] = $accNO;
        $where['realStatus'] = 1;
        $field = 'userID,phone,realStatus,realName,accNO,viplevel,bankStatus,userType';
        $result = $this->model->getUser($field, $where);
        return $result;
    }

    /**
     * 是否是融资用户
     * @param type $userType
     */
    public function isLoanUser($userType)
    {
        switch ($userType) {
            case 20:
            case 30:
                return true;
            default:
                return false;
        }
    }

    /**
     * 修改手机号码
     */
    public function changeUser($old, $new)
    {
        return $this->model->changeUser($old, $new);
    }

    /**
     * 添加用户
     */
    public function addChangephoneLog($data)
    {
        $addChangephoneLog = new \Service\User\Model\ChangephoneLogModel();
        return $addChangephoneLog->addChangephoneLog($data);
    }


    /**
     * 获取到可以拿到星星的模块列表
     *
     */
    public function getStarModule($userID)
    {
        $module = new StarModuleLogic();
        $list = $module->getModuleList(1);
        $userList = $module->getUserModuleList($userID);
        $return = array();
        foreach ($list as $key => $value) {
            $value['status'] = 0;
            foreach ($userList as $k => $v) {
                if ($value['type_code'] == $v['module_code'] && $v['status'] == 1) {
                    $value['status'] = 1;
                }
            }
            $return[] = $value;
        }
        return $return;
    }

    /**
     * 整理成状态
     */
    public function getStarStatus($userID)
    {
        $module = new StarModuleLogic();
        $list = $module->getModuleList();
        $userList = $module->getUserModuleList($userID);
        $return = array();
        foreach ($list as $key => $value) {
            $return[$value['type_code']] = 0;
            foreach ($userList as $k => $v) {
                if ($value['type_code'] == $v['module_code'] && $v['status'] == 1) {
                    $return[$value['type_code']] = 1;
                }
            }
        }
        return $return;
    }

    /**
     * 添加积分
     */
    public function award($userID,$type,$terminal="") {
        $info = $this->getUserInfo($userID);
        $token = $info['user_token'];
        vendor("Grpc.php.AccountStarPointOperation");
        $class = new \AccountStarPointOperation();

        //nats 推送
        $nat_logic = new NatsLogic();
        $publish["authType"] = (empty($type))? "": $type;
        //必填, [取值范围：AUTHIDCARD-实名认证，
        //GENE-基因数据，
        //ADDRESS-添加地址，
        //ATTENTIONWEBCHAT-关注公众号，
        //VOICEDISCERN-声音识别，
        //FACEDEISCEERN-人脸识别，
        //BINDBANK-绑定银行卡，
        //HARDWAREBIND-绑定矿机,
        //BINDADDRESSLIST=绑定通讯录
        $nat_logic->userAuth($terminal,$publish);
        return $class->award($token,time()*1000,$type);

    }

    /**获取实名认证尚未得到积分的积分数
     * @param $userID
     * @return int
     */
    public function getAllAuthStar($userID) {
        $sql = "select SUM(type_value) as AD from d_star_type where is_system=1";
        $ret = $this->model->query($sql);
        $all = $ret[0]['AD'];
        $sql = "select sum(type_value) as user from d_star_module as sm INNER JOIN d_star_type as st ON sm.module_code = st.type_code where sm.user_id = ".$userID." and st.is_system=1";
        $ret = $this->model->query($sql);
        $user = $ret[0]['user'];
        $return = $all - $user;
        if($return < 0) {
            $return = 0;
        }
        return $return;
    }


    /**
     * 获取到用户的上传信息
     *
     */
    public function getUserUpload($where,$field,$limit="") {
        $model =  M("uploadUser")->alias('us')->join("d_user u  on u.user_id = us.user_id ")->where($where)->field($field);
        if(!empty($limit)) {
            $model = $model->limit($limit);
        }
        return $model->select();
    }




}
