<?php

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

namespace MobileApi\Api;

use Common\Common\InvalidCode;
use GPBMetadata\AccountReg;
use MobileApi\Common\MobileApiBase;
use Service\Account\Logic\AccountLogic;
use Service\Charge\Logic\ChargeLogic;
use Service\Common\smsLogic;
use Service\Nats\Logic\NatsLogic;
use Service\User\Logic\GeneLogic;
use Service\User\Logic\StarModuleLogic;
use Service\User\Logic\UserLogic;
use Service\User\Logic\UserInviteLogic;
use Service\User\Logic\UserLogLogic;
use Service\User\Logic\UserLevelLogic;
use Service\News\Logic\SendSMSLogic;
use Service\Spread\Logic\AppInfoLogic;
use Common\Common\Redis;
use Service\User\Logic\ProblemLogic;
use Service\User\Logic\userMaillistLogic;
use Service\Wechat\Logic\WxCodebindLogic;
use Service\Wechat\Model\WxCodebindModel;
use Think\Model;


/**
 * Description of ApiUser
 *
 * @author Administrator
 */
class ApiUser extends MobileApiBase {

    public function getRules() {
        return array(
            'login' => array(
                'phone' => array('name' => 'phone', 'require' => true, 'type' => 'phone', 'desc' => '用户手机号码'),
                'password' => array('name' => 'password', 'require' => true, 'desc' => '用户登录密码')
            ),
            'loginByToken' => array(
                'token' => array('name' => 'token', 'require' => true, 'desc' => 'Token'),
            ),
            'loginOut' => array(),
            'register' => array(
                'phone' => array('name' => 'phone', 'require' => true, 'type' => 'phone', 'desc' => '用户手机号码'),
                'password' => array('name' => 'password', 'require' => true, 'desc' => '用户登录密码'),
                'phoneCode' => array('name' => 'phoneCode', 'require' => true, 'desc' => '手机短信验证码'),
                'inviteCode' => array('name' => 'inviteCode', 'desc' => '邀请码'),
                'agreement' => array('name' => 'agreement', 'type' => 'int', 'require' => true, 'desc' => '是否同意注册协议'),
            ),
            'info' => array(),
            'changePwd' => array(
                "usedPwd" => array('name' => 'usedPwd', 'type' => 'string', 'require' => true, 'desc' => '原始密码'),
                "newPwd" => array('name' => 'newPwd', 'type' => 'string', 'require' => true, 'desc' => '新密码'),
                "chkNewPwd" => array('name' => 'chkNewPwd', 'type' => 'string', 'require' => true, 'desc' => '确认的密码')
            ),
            'findPwd' => array(
                'phone' => array("name" => "phone", "type" => "string", "require" => true, "desc" => "手机号码"),
                'code' => array("name" => "code", "type" => "int", "require" => true, "desc" => "手机验证码"),
                "newPwd" => array('name' => 'newPwd', 'type' => 'string', 'require' => true, 'desc' => '新密码'),
                "chkNewPwd" => array('name' => 'chkNewPwd', 'type' => 'string', 'require' => true, 'desc' => '确认的密码')
            ),
            "getAppInfo" => array(
                'type' => array("name" => "type", "type" => "int", "desc" => "第一次打开传值为1 其他打开传值为2"),
                'phoneInfo' => array("name" => "phoneInfo", "type" => "string", "desc" => "手机信息 手机的系统 机型信息"),
                "latitude" => array('name' => 'latitude', 'type' => 'string', 'desc' => '纬度'),
                "longitude" => array('name' => 'longitude', 'type' => 'string', 'desc' => '经度'),
                'province' => array("name" => "province", "type" => "string", "desc" => "省份"),
                'city' => array("name" => "city", "type" => "string", "desc" => "城市"),
                "area" => array('name' => 'area', 'type' => 'string', 'desc' => '区'),
                "place" => array('name' => 'place', 'type' => 'string', 'desc' => '详细地址'),
                "jphId" => array('name' => 'jphId', 'type' => 'string', 'desc' => '极光推送ID'),
                'key' => array('name' => 'qdKey', 'type' => 'string', 'desc' => '渠道Key'),
            ),
            'addFeback' => array(
                'phone' => array("name" => "phone", "type" => "string", "desc" => "反馈预留电话"),
                'febackInfo' => array("name" => "febackInfo", "require" => true, "type" => "string", "desc" => "用户反馈的信息")
            ),
            'upload' => array(
                'type' => array('name' => 'type','type'=>'string' ,'require' => true, 'desc' => '上传的类型 人脸为 Face 声音为 Voice'),
                'file' => array('name' => 'file',  'type' => 'string', 'desc' => '上传标识 传递的文件标识为这个 不'),
            ),
            'smsLogin' => array(
                'phone' => array('name' => 'phone', 'require' => true, 'type' => 'phone', 'desc' => '用户手机号码'),
                'smsCode' => array('name' => 'smsCode', 'require' => true, 'type' => 'string', 'desc' => '手机短信验证码'),
            ),
            "isRegCheck" => array(),
            "getStarModuleList"=>array(),
            "getUserCenterStar"=>array(),
            "subWechat"=>array(
                'Code' => array('name' => 'Code', 'require' => true, 'type' => 'string', 'desc' => '微信关注码'),
            ),
            'Gene'=>array(
                "name"=> array('name' => 'name', 'require' => true, 'type' => 'string', 'desc' => '名字'),
                "brithDate"=> array('name' => 'brithDate', 'require' => true, 'type' => 'int', 'desc' => '生日'),
                "nativePlace"=>array('name' => 'nativePlace', 'require' => true, 'type' => 'string', 'desc' => '出生地址'),
                "exerciseTime"=>array('name' => 'exerciseTime', 'require' => true, 'type' => 'int', 'desc' => '运动时间以秒为单位'),
                "ill"=>array('name' =>"ill", 'require' => true, 'type' => 'string', 'default'=>"", 'desc' => '遗传病无填无'),
                "sex"=>array('name' =>"sex", 'require' => true, 'type' => 'int', 'default'=>0, 'desc' => ' 0 女  1男'),
                "height"=>array('name' =>"height", 'require' => true, 'type' => 'string', 'default'=>0, 'desc' => '身高 单位米 2位小数'),
                "weight"=>array('name' =>"weight", 'require' => true, 'type' => 'string', 'default'=>0, 'desc' => '体重 单位千克 2位小数'),
            ),

            'getWithDayList' =>array(
                "days"=>array("name"=>"days",'require'=>true,"type"=>"int",'desc'=>'第几天'),
                "endTime"=>array("name"=>"endTime",'require'=>true,"type"=>"string",'desc'=>'结束时间，意思就是 从结束时间开始 返回之前几天的数据'),
            ),
            'handldMailList'=>array(
                "phoneStr"=>array("name"=>"phoneStr",'require'=>true,"type"=>"string",'desc'=>'账户信息的json字符串'),
            ),
            'getMailListResInfo' =>array(
                'pageindex' => array('name' => 'pageindex', 'type' => 'int', 'default' => 1, 'desc' => '页码'),
                'pagesize' => array('name' => 'pagesize', 'type' => 'int', 'default' => 15, 'desc' => '每页显示数量'),
                'type' => array('name' => 'type', 'type' => 'int', 'default' => 3, 'desc' => '1 未注册过的 2注册的 3 全部'),
             ),
            'bindMailList' => array(
            ),
        );
    }

    /**
     * 用户登录。
     * @desc 用于用户登录
     * @return int code                 操作码，0表示成功， 1登录失败
     * @return array info              登录成功信息
     * @return int info.phone           登录用户手机号码
     * @return string info.device_id     设备ID
     * @return string info.token        Token
     * @return string info.login_time    登录时间
     * @return string info.expires_time  Token过期时间
     * @return string info.user_key      用户标识（用于对接在线客服）
     * @return string msg 提示信息
     * --
     */
    public function login() {
        $rs = array('code' => 0, 'msg' => '', 'info' => array());
        $entity["phone"] = $this->phone;
        $entity["password"] = $this->password;
        $entity["terminal"] = $this->terminal;   //获取到全部的代码
        $entity["operate_ip"] = getIP();
        $userLogic = new UserLogic();
        $result = $userLogic->Login($entity);   //登录
        if ($result * 1 > 0) {
            $data = $this->getLoginInfoData($result, $this->phone);
            $this->setUserInfo($data, array($this, 'loginSucceedCallback'));    //登录成功
            $rs['info'] = $this->getLoginBackInfo($data);
            $rs['msg'] = '登录成功';

            //nats 推送
            $logic = new NatsLogic();
            $publish["mobile"] = (empty($entity['phone']))? "": $data['phone'];//选填
            $publish["operationType"] = 1; //必填(0=登出，1=登录)
            $logic->loginLogout($this->terminal,$publish);

        } else {
            $rs['code'] = 1;
            $rs['msg'] = $result;
        }
        return $rs;
    }

    /**
     * 通过Token进行登录。
     * @desc 用于用户登录
     * @return int code                 操作码，0表示成功， 1登录失败
     * @return object info              登录成功信息
     * @return int info.phone           登录用户手机号码
     * @return string info.device_id     设备ID
     * @return string info.token        Token
     * @return string info.login_time    登录时间
     * @return string info.expires_time  Token过期时间
     * @return string info.user_key      用户标识（用于对接在线客服）
     * @return string msg 提示信息
     * --
     */
    public function loginByToken() {
        $rs = array('code' => 0, 'msg' => '', 'info' => array());
        if (empty($this->userToken) || $this->userToken != $this->token || $this->userID * 1 < 1) {
            $rs['code'] = 1;
            $rs['msg'] = 'Token非法或者已过期';
            return $rs;
        }
        if ($this->userDeviceID != $this->deviceID) {
            $rs['code'] = 1;
            $rs['msg'] = '非法请求';
            return $rs;
        }
        $data = $this->getLoginInfoData($this->userID, $this->userPhone, 'TokenLogin', $this->userToken);
        $this->setUserInfo($data, array($this, 'loginSucceedCallback'));
        $rs['info'] = $this->getLoginBackInfo($data);
        $log = new UserLogLogic();
        $info = $log->getPreUserLoginInfo($this->userID);   //获取到上次用户的登录
        $rs['info']['equipment'] = $info[0];
        $rs['msg'] = '登录成功';

        //nats 推送
        $logic = new NatsLogic();
        $publish["mobile"] = (empty($this->userPhone))? "": $this->userPhone;//选填
        $publish["operationType"] = 1; //必填(0=登出，1=登录)
        $logic->loginLogout($this->terminal,$publish);

        return $rs;
    }



    /**
     * 短信登录
     * @desc 通过短信登录的接口
     */
    public function smsLogin() {
        $rs = array('code' => 0, 'msg' => '', 'info' => array());
        $entity["phone"] = $this->phone;
        $entity["smsCode"] = $this->smsCode;
        $entity["terminal"] = $this->terminal;
        $userLogic = new UserLogic();
        $result = $userLogic->smsLogin($entity);
        if ($result * 1 > 0) {
            $data = $this->getLoginInfoData($result, $this->phone, "smsLogin");
            $this->setUserInfo($data, array($this, 'loginSucceedCallback'));
            $rs['info'] = $this->getLoginBackInfo($data);
            $rs['msg'] = '登录成功';
            //nats 推送 给运营平台
            $logic = new NatsLogic();
            $publish["mobile"] = (empty($entity['phone']))? "": $data['phone'];//选填
            $publish["operationType"] = 1; //必填(0=登出，1=登录)
            $logic->loginLogout($this->terminal,$publish);
        } else {
            $rs['code'] = 1;
            $rs['msg'] = $result;
        }
        perr($rs);
        return $rs;
    }

    /**
     * 用户注册。
     * @desc 用于用户注册
     * @return int code                 操作码，0表示成功， 1登录失败
     * @return array info               注册成功信息
     * @return int info.phone           注册用户手机号码
     * @return string info.device_id     设备ID
     * @return string info.token        Token
     * @return string info.login_time    登录时间
     * @return string info.expires_time  Token过期时间
     * @return string info.user_key      用户标识（用于对接在线客服）
     * @return int info.return_msg  范湖的效果语
     * @return string msg 提示信息
     * --
     */
    public function register() {
        $rs = array('code' => 0, 'msg' => '', 'info' => array());
        $sms = new smsLogic();
        $stutas = $sms->validate($this->phoneCode,$this->phone,'reg');
        if (!$stutas) {
            $data["code"] = 1;
            $data["msg"] = "手机短信验证码已过期";
            return $data;
        }
        $userLogic = new UserLogic();

        $info = $userLogic->getByPhone($this->phone);
        if(!empty($info)) {
            $rs['code'] = 1;
            $rs['msg'] = "已经注册过了";
            return $rs;
        }
        
        if ($this->agreement * 1 != 1) {
            $rs['code'] = 1;
            $rs['msg'] = '您必须同意条款！';
            return $rs;
        }
        $entity["phone"] = $this->phone;
        $entity["invite_code"] = $this->inviteCode;
        $entity["password"] = $this->password;
        $entity["reg_terminal"] = $this->terminal;
        $result = $userLogic->Register($entity);

        if ($result * 1 > 0) {
            $intavl_token = "";
            if(!empty($this->inviteCode)) {
                $info = $userLogic->getUserByInviter($this->inviteCode);
                $intavl_user_id = $info['user_id'];
                $user_info = $userLogic->getUserInfo($intavl_user_id);
                $intavl_token = $user_info['user_token'];
            }
            vendor("Grpc.php.AccountRegister");
            $reg_logic = new \AccountRegister();
            $reg_result = $reg_logic->reg($this->phone,$result,$intavl_token);  //GRPC
            if($reg_result && ($reg_result["code"]*1 == 200 || $reg_result["code"] *1 == 501 )) {
                if($reg_result["code"] *1 == 501) {  //为重新注册的时候 查询一遍重新再本地注册
                    $checkData = $reg_logic->check($this->phone);
                    $reg_result["token"] = $checkData['accountToken'];
                }
                $updata["user_token"] = $reg_result["token"];
                if(empty($updata["user_token"])) {
                    $userLogic->delUser($result);
                    $rs["code"] = 1;
                    $rs['msg'] = "注册失败";
                    return $rs;
                }
                $userLogic->editUser($result, $updata);
                $data = $this->getLoginInfoData($result, $this->phone, 'RegisterLogin');
                $this->setUserInfo($data, array($this, 'loginSucceedCallback'));
                $info_data = $this->getLoginBackInfo($data);
                $info_data['return_msg'] = "恭喜您，注册成功";  //几个提示语
                $rs['info'] = $info_data;
                $rs['msg'] = "";
                //nats 推送
                $nat_logic = new NatsLogic();
                $publish["mobile"] = (empty($entity['phone']))? "": $entity['phone'];//选填
                $nat_logic->basicInfo($this->terminal,$publish);

            } else {
                $userLogic->delUser($result);
                $rs["code"] = 1;
                $rs["msg"] = !empty($reg_result['msg']) ? $reg_result["msg"] : "注册失败s";
            }
        } else {
            $rs['code'] = 1;
            $rs['msg'] = $result;
        }
        return $rs;
    }

    /**
     * 获取用户的基本信息。
     * @desc 用于获取用户的基本信息
     * @return int code                 操作码，0表示成功， 1 表示未找到相应数据
     * @return object info.user_info              用户的信息表
     * @return string info.user_info.phone        手机号码
     * @return string info.user_info.real_name     用户真实姓名
     * @return string info.user_info.user_sex      性别
     * @return string info.user_info.head_url      头像
     * @return int info.user_info.available 可用星星
     * @return int info.user_info.unavailable 不可用星星
     * @return object info.all_status              用户的信息表
     * @return int info.all_status.Auth   代表是否认证
     * @return int info.all_status.Gene 基因商城
     * @return int info.all_status.BindBank  是否绑定银行卡
     * @return int info.all_status.Address 是否绑定地址
     * @return int info.all_status.Wechat 是否绑定微信
     * @return int info.all_status.Voice 是否认证声音
     * @return int info.all_status.Voice Face 是否人脸
     * @return int info.all_status.Charge]  是否矿机
     * @return int info.all_status.Maillist  是否矿机
     * @return string msg               提示信息
     * --
     */
    public function info() {
        $this->userCheck();
        $rs = array('code' => 0, 'msg' => '', 'info' => array());
        $logic = new UserLogic();
        $userInfo = $logic->getUserInfo($this->userID);  //获取到用户的信息
        $status = $logic->getStarStatus($this->userID);

        if ($userInfo && !empty($userInfo["user_token"])) {
            vendor("Grpc.php.AccountStarPoint");
            $client = new \AccountStarPoint();
            $accinfo = $client->starPoint($userInfo["user_token"]);
            if ($accinfo && $accinfo["code"] == 200) {
                $userInfo["available"] = (string)$accinfo["availableStarPoint"];
                $userInfo["unavailable"] = (string)$accinfo["frozenStarPoint"];
            } else {
            	$userInfo["available"] = "0";
                $userInfo["unavailable"] = "0";
            }
        }
        $charge = new ChargeLogic();
        $info = $charge->getInfoByUser($this->userID);
        $mac = ($info["device_id"])? $info['device_id']:"";
        $status['charge_mac'] = $mac;
        if (!empty($userInfo)) {
            $rs['info']['all_status'] = $status;
            $rs['info']['user_info'] = $userInfo;
        } else {
            $rs['code'] = 1;
            $rs['msg'] = '未找到相应数据';
        }
        return $rs;
    }


    /**
     * 获取登录数据。
     * @param type $userID
     * @param type $type
     * --
     */
    private function getLoginInfoData($userID, $phone, $type = 'PassWordLogin', $token = '') {
        $data['phone'] = $phone;
        $data['device_id'] = $this->deviceID;
        if (!empty($token)) {
            $data['token'] = $token;
        } else {
            $data['token'] = $this->getToken();
        }
        $data['login_time'] = $_SERVER['REQUEST_TIME'];
        $data['expires_time'] = $_SERVER['REQUEST_TIME'] + self::MAX_EXPIRE_TIME_FOR_SESSION;
        $data['operate_ip'] = getIP();
        $data['login_type'] = $type;
        $data['terminal'] = $this->terminal;
        $data['user_id'] = $userID;
        $data['user_key'] = '';
        $logic = new UserLogic();
        $userkey = $logic->getUserKeyByUserID($userID);
        if ($userkey) {
            $data['user_key'] = $userkey['user_key'];
        }
        return $data;
    }

    /**
     * 获取用户登录成功返回信息。
     * @param type $data
     * --
     */
    private function getLoginBackInfo($data) {
        unset($data['user_id']);
        unset($data['type']);
        unset($data['login_type']);
        unset($data['operate_ip']);
        unset($data['terminal']);
        return $data;
    }

    /**
     * 登录成功回调（非接口 ）。
     * @param type $data
     * --
     */
    public static function loginSucceedCallback($data) {
        logger_applog($data["user_id"], $data["phone"], $data["terminal"], $data['login_type'] . '|' . $data['device_id'] . '|' . $data['token']);
    }

    /**
     * 修改密码。
     * @desc 用户修改密码
     * @return int code 操作码，0表示成功， 1表示失败 
     * @return string msg 提示信息
     * --
     */
    public function changePwd() {
        $this->userCheck();
        $data = array('code' => 0, 'msg' => '', 'info' => array());
        $usedPwd = $this->usedPwd;
        $newPwd = $this->newPwd;
        $chkNewPwd = $this->chkNewPwd;
        $userID = $this->userID;
        if ($newPwd != $chkNewPwd) {
            $data["msg"] = "确认密码不一致";
            $data["code"] = 1;
            return $data;
        }
        if ($usedPwd == $newPwd) {
            $data["msg"] = "输入的新旧密码相同";
            $data["code"] = 1;
            return $data;
        }
        $logic = new UserLogic();
        $userPhone = $logic->verifyLoginPassword($userID, $usedPwd);
        if (!empty($userPhone["phone"])) {
            $result = $logic->editLoginPassword($userID, $usedPwd, $newPwd);
            if ($result * 1 > 0) {
                $data["msg"] = "修改密码成功";
            } else {
                $data["code"] = 1;
                $data["msg"] = $result;
            }
        } else {
            $data["msg"] = "原密码错误";
            $data["code"] = 1;
        }
        return $data;
    }

    /**
     * 找回密码 
     * @desc 用户找回密码
     * @return int code 操作码，0表示成功， 1表示失败 
     * @return string msg 提示信息
     * --
     */
    public function findPwd() {
        $phone = $this->phone;
        $code = $this->code;
        $newPwd = $this->newPwd;
        $chkNewPwd = $this->chkNewPwd;
        $data = array('code' => 0, 'msg' => '', 'info' => array());
        if ($newPwd != $chkNewPwd) {
            $data["code"] = 1;
            $data["msg"] = "两次输入的密码不一致";
            return $data;
        }
        $logic = new smsLogic();
        $result = $logic->validate($code,$phone,'find');
        if (!$result) {
            $data["code"] = 1;
            $data["msg"] = "手机短信验证码已过期";
            return $data;
        }
        $userLogic = new UserLogic();
        $userinfo = $userLogic->getByPhone($phone);
        if (empty($userinfo) || $userinfo['user_id'] * 1 < 1) {
            $data["code"] = 1;
            $data["msg"] = "手机号码不存在";
            return $data;
        }
        $res = $userLogic->restLoginPassword($userinfo['user_id'], $newPwd, $phone);
        if ($res * 1 > 0) {
            $redis = Redis::GetInstance();
            $redis->del("Login_Lock_" . $phone);
            $data["code"] = 0;
            $data["msg"] = "重置登录密码成功";
        } else {
            $data["code"] = 1;
            $data["msg"] = $res;
        }
        return $data;
    }



    /**
     * 退出登录
     * @desc用户退出登录
     *  @return int    code         操作码，0表示成功， 1表示失败
     * @return  array  info         返回数据 
     * @return  string msg          提示信息
     * --
     */
    public function loginOut() {
        $this->userCheck();
        $data = array('code' => 0, 'msg' => '退出成功', 'info' => array());
        $redis = Redis::GetInstance();
        $token = $this->userToken;
        $userID = $this->userID;
        $this->setAppJmpCookie($token, time() - 1);
        $searchKey = $this->getLoginCaheKey($token, $userID);
        logger_api('loginOut', "SearchKey：$searchKey");
        $keys = $redis->getKeys($searchKey);
        logger_api('loginOut', "Keys：" . json_encode($keys));
        if (!empty($keys)) {
            $redis->dels($keys);
            $data["msg"] = "退出成功";
            //nats 推送
            $logic = new NatsLogic();
            $publish["mobile"] = (empty($this->userPhone)) ? "": $this->userPhone;//选填
            $publish["operationType"] = 0; //必填(0=登出，1=登录)
            $logic->loginLogout($this->terminal,$publish);

        } else {
            $data["code"] = 1;
            $data["msg"] = "退出失败";
        }
        return $data;
    }


    /**
     *  接收手机APP信息
     * @desc 接收手机APP信息
     * @return int    code         操作码，0表示成功， 1表示失败
     * @return  array  info         返回数据 
     * @return  string msg          提示信息
     * --
     */
    public function getAppInfo() {
        $data = array('code' => 0, 'msg' => '', 'info' => array());
        $saveData['equipment_id'] = $this->deviceID; //设备ID
        $saveData['versions'] = $this->version; //客户端APP的版本号
        $saveData['terminal'] = $this->terminal; //终端
        $saveData['jph_reg_id'] = $this->jphId; //极光推送ID
        $saveData['type'] = $this->type; //第一次打开传值为1 其他打开传值为2
        $saveData['phone_info'] = $this->phoneInfo; //手机信息 手机的系统 机型信息
        $saveData['latitude'] = $this->latitude; //纬度
        $saveData['longitude'] = $this->longitude; //经度
        $saveData['province'] = $this->province; //省份
        $saveData['city'] = $this->city; //城市
        $saveData['area'] = $this->area; //区
        $saveData['place'] = $this->place; //地址详情
        $key = $this->key;
        $arr = array("qhz" => "主包", "qq" => "应用宝", "baidu" => "百度手机助手", "360" => "360手机助手", "huawei" => "华为应用市场", "xiaomi" => "小米应用商店",
            "oppo" => "OPPO软件商店", "vivo" => "VIVO应用商店", "meizu" => "魅族应用商店", "lianxiang" => "联想乐商店", "ali" => "阿里应用", "jifeng" => "机锋网", "anzhi" => "安智网"
            , "developer-default" => "测试包");
        $iosArr = array("zhongChen" => "深圳中辰科技有限公司");
        if ($this->terminal == 2) {
            if (!empty($arr[$key])) {
                $appKey = $key;
                $appChannel = $arr[$key];
            } else {
                $appKey = "defChannel";
                $appChannel = "未知渠道 ";
            }
        } else {
            if (!empty($iosArr[$key])) {
                $appKey = $key;
                $appChannel = $iosArr[$key];
            } else {
                $appKey = "appShop";
                $appChannel = "苹果商店";
            }
        }
        $saveData["chan_Key"] = $appKey;
        $saveData["channel"] = $appChannel;
        $saveData['add_datetime'] = time();
        $AppInfoLogic = new AppInfoLogic();
        $result = $AppInfoLogic->saveAppInfo($saveData);
        if ($result) {
            $data["msg"] = "成功";
        } else {
            $data["code"] = 1;
            $data["msg"] = "失败";
        }
        return $data;
    }



    /**
     * 用户上传文件
     * @desc 用户上传录音和上传人脸识别的图片
     *
     */
    public function upload() {
        $this->userCheck();
        $res = array('code' => 1, 'msg' => '上传头像失败！', 'info' => array());
        $userID = $this->userID;
        $type = $this->type;
        $logic = new UserLogic();
        if($logic->checkUserUpload($this->userID,$this->type)) {
            $res['msg'] = "已经识别过了";
            return $res;
        }
        $appUploadData = $this->uploadImg($type, "", $delPath);
        $status = $appUploadData["status"];
        if (!empty($status)) {
            $data = array(
                "user_id"=>$userID,
                "path"=>$appUploadData['file']['path'],
                "name"=>$appUploadData['file']['name'],
                "add_detatime"=>time(),
                "type"=>$type,
            );
            $logic->addUserUpload($data);
            $userlogic = new StarModuleLogic();   //添加模块
            $userlogic->addModule($this->userID,$type);
            if($type == "Voice") {
                $type_string = "VOICEDISCERN";
            } else   {
                $type_string = "FACEDEISCEERN";
            }
            $award_logic = new UserLogic();    //grpc 增加奖励
            $ret = $award_logic->award($this->userID,$type_string);
            $res['code'] = 0;
            $res["msg"] = "识别成功";
        } else {
            $res['code'] = 1;
            $res["msg"] = "识别失败";
        }
        return $res;
    }




    /**
     * 保存图片
     * @param type $rootPath
     * @param type $subPath
     * @param type $del
     * @return type
     * --
     */
    private function uploadImg($rootPath, $subPath = "", $del = "") {
        $imgInfo = explode(UPLOAD, $del);
        $del = is_array($imgInfo) && !empty($imgInfo) ? $imgInfo[1] : ""; // 1用户上传新头像将删除之前的头像
        $file = $_FILES["file"];
        $client = new \Common\Plugins\UploadClient();
        $result = $client->upLoadSingle($rootPath, $file, $subPath, $del);
        if (is_array($result)) {
            $msg["status"] = true;
            $msg["file"] = $result;
        } else {
            $msg["status"] = false;
            $msg["msg"] = $result;
        }
        return $msg;
    }



    /**
     * 获取登录缓存key
     * @param type $token
     * @param type $userID
     * @return type
     * --
     */
    private function getLoginCaheKey($token, $userID = '') {
        return sprintf('APP_Login_%s_%s', $token, $userID);
    }



    /**
     * 用户反馈信息
     * @desc   用户投诉的信息
     * @return   int       code                          操作码，0表示成功， 1表示失败
     * @return   array    info
     * @return   string    msg                           提示信息
     * -
     */
    public function addFeback() {
        $this->userCheck();
        $data = array("code" => 0, "msg" => "提交成功", "info" => array());
        $where = array();
        $where["terminal"] = $this->terminal;
        $where["versions"] = $this->version;
        $where["feback_info"] = $this->febackInfo;
        $where["phone"] = $this->phone;
        $where["user_id"] = $this->userID;
        $where["equipment_id"] = strtolower($this->deviceID);
        $where["add_datetime"] = time();
        $logic = new AppInfoLogic();
        $countInfo = $logic->getDayFeback($where["equipment_id"]);
        if ($countInfo >= 3) {
            $data["code"] = 1;
            $data["msg"] = "每天只能反馈3次";
            return $data;
        }
        if (empty($where["phone"])) {
            $where["phone"] = $this->userPhone;
        } else if (!verify_phone($where["phone"])) {
            $data["code"] = 1;
            $data["msg"] = "请输入正确的手机号码！";
            return $data;
        }
        $where["feback_type"] = 0;
        $res = $logic->addFeback($where);
        if (empty($res)) {
            $data["code"] = 1;
            $data["msg"] = "反馈信息失败";
        }
        return $data;
    }


    /**
     * 获取到用户的邀请码
     * @desc 获取到用户的邀请码
     * @return int code 0对 1错
     * @return string msg 提示信息
     * @return info.code string 邀请吗
     *  @return info.count string 邀请人数
     */
    public function getInvitation() {
        $this->userCheck();
        $data = array("code" => 0, "msg" => "提交成功", "info" => array());
        $logic = new UserLogic();
        $userInfo = $logic->getUser($this->userID);
        $count = $logic->getUserCount($this->userID);
        if(empty($userInfo['invitation_code'])) {
            $code = InvalidCode::getInstance()->generate($this->userID);
            $data['info']['code'] = $code;
            $edit['invitation_code'] = $code;
            $logic->editUser($this->userID,$edit);
        } else {
            $data['info']['code'] = $userInfo['invitation_code'];
        }
        if(empty($count)) {
            $count = 0;
        }
        $data['info']['count'] = $count;
        return $data;
    }


    /**
     * 注册是否勾选按钮。
     * @desc 用于获取标详细信息
     * @return int code 操作码，0表示成功， 1表示失败
     * @return object info 注册信息对象
     * @return int info.isCheck    注册是否勾选（1 默认勾选  0 默认不勾选）
     * @return string msg 提示信息
     */
    public function isRegCheck() {
        $rs = array('code' => 0, 'msg' => '', 'info' => array());
        $userLogic = new UserLogic();
        $isCheck = $userLogic->isRegCheck();
        $info["isCheck"] = $isCheck;
        $rs['info'] = $info;
        return $rs;
    }

    /**
     * 实名奖励
     * @desc 获取到当前用户实力奖励列表
     * @return int type_id ID
     * @return string type_name  注册送积分
     * @return string type_code 模块的标识码
     * @return string type_value 可获取的星星数量
     * @return int code 0 成功 1失败
     * @return int status 1 代表该用户已经获取了 0代表没有
     * @return string msg 提示
     */
    public function getStarModuleList() {
        $this->userCheck();
        $rs = array('code' => 0, 'msg' => '', 'info' => array());
        $userLogic = new UserLogic();
        $ret = $userLogic->getStarModule($this->userID);
        foreach ($ret as $key=>$value) {
            $ret[$key]['type_value'] = intval($value['type_value']);
        }
        $rs['info'] = $ret;
        return $rs;
    }


    /**
     * 账户中心显示项目
     * @desc 获取到账户中心列表的显示内容 如 实名奖励：40星星
     * @return int type_id ID
     * @return string type   类型
     * @return string desc 描述
     * @return string value 数值
     * @return int status  -1 代表没状态 0代表尚未认证 1代表认证
     * @return int code 0 成功 1失败
     * @return string msg 提示
     */
    public function getUserCenterStar() {
        $this->userCheck();
        $rs = array('code' => 0, 'msg' => '', 'info' => array());
        $userLogic = new UserLogic();
        $ret = $userLogic->getStarModule($this->userID);
        $logic = new StarModuleLogic();
        $module = $logic->getOneModule('Invitation');
        $list = array(
            array('type'=>'AuthAll','desc'=>'实名认证','value'=>'10','status'=>-1),
            array('type'=>'Gene','desc'=>'数字生命','value'=>'10','status'=>1),
            array('type'=>'Invitation','desc'=>'邀请好友','value'=>intval($module['type_value']),'status'=>-1),
        );
        foreach ($list as $key=>$value) {
            foreach ($ret as $k => $v) {
                if($value['type'] == $v['type_code']) {
                    $list[$key]['value']  = intval($v['type_value']);
                    $list[$key]['status'] = $v['status'];
                }
                if($value['type'] == 'AuthAll') {
                    $list[$key]['value'] =intval( $userLogic->getAllAuthStar($this->userID));
                }
                if($value['type'] == 'Gene') {  //特殊原因写死
                    $list[$key]['status'] = 1;
                }
            }
        }
        $rs['info'] = $list;
        return $rs;
    }

    /**
     * 验证关注微信
     * @desc 关注微信
     * @return int code 0 成功 1失败
     * @return string msg 提示
     * @return array info 信息
     */
    public function subWechat() {
        $this->userCheck();
        $rs = array('code' => 0, 'msg' => '', 'info' => array());
        $code = $this->Code;
        $logic = new WxCodebindLogic();
        $info = $logic->getInfoByCode($code);
        if(empty($info)) {
            $rs['code'] = 1;
            $rs['msg'] = "该码不存在";
            return $rs;
        } else if(!empty($info['user_id'])) {
            $rs['code'] = 1;
            $rs['msg'] = "该码已经绑定";
            return $rs;
        }
        $r = $logic->setCode($this->userID,$code);
        if($r) {
            $userlogic = new StarModuleLogic();
            $userlogic->addModule($this->userID,'Wechat');  //添加模块

            $award_logic = new UserLogic();    //grpc 增加奖励
            $award_logic->award($this->userID,"ATTENTIONWEBCHAT");

            $rs['msg'] = "验证成功";
        } else {
            $rs['code'] = 1;
            $rs['msg'] = "验证失败";
            return $rs;
        }
    }


    /**
     * 基因数据提交
     * @desc 用户基因数据表单填写
     * @return int code 0 成功 1失败
     * @return string msg 提示
     * @return array info 信息
     */
    public function Gene() {
        $this->userCheck();
        $rs = array('code' => 0, 'msg' => '', 'info' => array());
        $logic = new GeneLogic();
        $info = $logic->findUserBank($this->userID);
        if(!empty($info)) {
            $rs['code'] = 1;
            $rs['msg'] = "你已经填写过了";
            return $rs;
        }
        $addData = array(
            "name"=>$this->name,
            "birth_date"=>strtotime($this->brithDate),
            "native_place"=>$this->nativePlace,
            "exercise_time"=>$this->exerciseTime,
            "sex"=>$this->sex,
            "user_id"=>$this->userID,
            "add_datetime"=>time(),
            "ill"=>$this->ill,
            "height"=>$this->height,
            "weight"=>$this->weight,
        );
        $res = $logic->addGene($addData);
        if($res) {
            $rs['msg'] = "填写成功";
            $userlogic = new StarModuleLogic();
            $userlogic->addModule($this->userID,'Gene');

            $award_logic = new UserLogic();    //grpc 增加奖励
            $award_logic->award($this->userID,"GENE");

        } else {
            $rs['code'] = 1;
            $rs['msg'] = "填写失败";
        }
        return $rs;
    }


    /**
     * 星星图表
     * @desc 就是钱包的曲线图
     * @return int info.starpoint 星星数量
     * @return int info.day  时间
     * @return int code 0 成功 1失败
     * @return string msg 提示
     * @return array info 信息
     */
    public function getWithDayList() {
    	$this->userCheck();
        $rs = array('code' => 1, 'msg' => '', 'info' => array());
        vendor("Grpc.php.AccountStarPoint");
        $logic = new UserLogic();
        $info = $logic->getUserInfo($this->userID);
        $class = new \AccountStarPoint();
        $data = $class->recordsWithDay($info['user_token'],$this->days,$this->endTime);
        $list = $data['info'];
        if(empty($data)) {
            $rs['code'] = 1;
            $rs['msg'] = "grpc错误";
            return $rs;
        }
        if(empty($list)) {
            $rs['code'] = 0;
            $rs['info'] = array(array('starPoint'=>0,'day'=>date('m-d')));
            return $rs;
        }
        $starttime = strtotime(date('Y').'-'.$list[0]['day']);
        $endtime = strtotime(date('Y-m-d')) ;
        $key=0;
        while ($endtime - $starttime >= 0) {
            if(empty($list[$key]['day']) && $key!=0 ) {
                $list[$key]['starPoint'] = $list[$key-1]['starPoint'];
                $list[$key]['day'] = date('m-d',$starttime);
            }
            $starttime+=86400;  //每新增一天
            $key++;
            if( ($endtime - $starttime < 0) && ($starttime-$endtime < 86400) && empty($list[$key]['day']) ) {
                $list[$key]['starPoint'] = $list[$key-1]['starPoint'];
                $list[$key]['day'] = date('m-d',$starttime);
            }
        }
        if ($data && $data["code"] == 200) {
            $code = $data["code"];
            $rs["msg"] = $data["msg"];
            if ($code == 200) {
                $rs["code"] = 0;
                $rs["info"] = $list;
            }
        } else {
            $rs["msg"] = "Grpc服务异常！";
        }
        return $rs;
    }


    /**
     * 同步通讯录
     * @desc 同步通讯录
     * @return int code 0 成功 1失败
     * @return string msg 提示
     * @return array info 信息
     */
    public function handldMailList() {
        //$this->userCheck();
        $rs = array('code' => 0, 'msg' => '', 'info' => array());
        
//        $infoList = array(
//            array("name"=>"张山","phone"=>1316960135),
//            array("name"=>"你好","phone"=>1316960134),
//            array("name"=>"不好","phone"=>1316960834),
//        );
        $infoList = json_decode($this->phoneStr,true);
        $phone_data["add_datetime"] = time();
        $phone_data['user_id'] = $this->userID;
        $logic = new userMaillistLogic();
        $phone_num_list = $logic->getPhoneList($this->userID);  //获取到全部注册的Phone
        $list = array_column($phone_num_list,"phone");   //二维数组化为一维
        foreach ($infoList as $key=>$value) {
            if(!in_array($value['phone'],$list) || empty($list)) {
                $phone_data['phone'] = ($value['phone'])? $value['phone']:"";
                $phone_data['name'] = ($value['name'])? $value['name']:"";
                $insert_data[] = $phone_data;
            }
        }
        if(!empty($insert_data)) {
            $res = $logic->addAll($insert_data);
            if($res) {
                $rs['msg'] = "操作成功";
            } else  {
                $rs['code'] = 1;
                $rs['msg'] = "操作失败";
            }
        } else {
            $rs['code'] = 1;
            $rs['msg'] = "暂无更新";
        }
        return $rs;
    }


    /**
     * 获取到通讯录列表
     * @desc 获取到用户同步后的通讯录
     * @return int code 0 成功 1失败
     * @return string msg 提示
     * @return string info.name 名字
     * @return string info.phone 电话
     * @return string info.steta 1注册过 0为注册
     * @return aray info 信息
     */
    public function getMailListResInfo() {
        $rs = array('code' => 0, 'msg' => '', 'info' => array());
        $this->userCheck();
        $logic = new userMaillistLogic();
        $list = $logic->getList($this->userID,$this->type);
        if(empty($list)) {
            $rs['code'] = 1;
            $rs['msg'] = "数据为空";
        } else {
            $rs['info'] = $list;
        }
        return $rs;

    }


    /**
     * 绑定通讯录的接口并获取奖励的接口
     * @desc 用户同步完通讯录后获取到的奖励
     * @return string msg 提示
     * @return string code 提示码
     * @return string info.availableStarPoint  可用星星数
     * @return string info.frozenStarPoint     冻结信息数
     * @return aray info 信息
     */
    public function bindMailList() {
        $rs = array('code' => 0, 'msg' => '', 'info' => array());
        $this->userCheck();
        $user_module = new StarModuleLogic();
        $user_info  = $user_module->getOneModuleInfo("Maillist",$this->userID);
        if(!empty($user_info) && $user_info['status'] == 1) {
            $rs['code'] = 1;
            $rs['msg'] = "已经认证了";
            return $rs;
        }
        $logic = new UserLogic();
        $userinfo  = $logic->getUser($this->userID);
        $userMail = new userMaillistLogic();
        $count = $userMail->getListCount($this->userID);
        $count = $count[0]['user_count'];
        if($count == 0) {
            $rs['code'] = 1;
            $rs['msg'] = "认证信息为零";
            return $rs;
        }
        vendor("Grpc.php.AccountStarPointOperation");
        $client = new \AccountStarPointOperation();
        $data = $client->bindAddressList($userinfo['user_token'],time()*1000,$count); //grpc请求
        if(empty($data)) {
            $rs['code'] = 1;
            $rs['msg'] = "grpc错误";
            return $rs;
        }
        if ($data && $data["code"] == 200) {
            $code = $data["code"];
            $rs["msg"] = $data["msg"];
            if ($code == 200) {
                $rs["code"] = 0;
                $rs["info"] = array(
                    "starpoint"=> $data['starpoint'],
                    "availableStarPoint" => $data["availableStarPoint"],
                    "frozenStarPoint" => $data["frozenStarPoint"]
                );
            }

            $userlogic = new StarModuleLogic();
            $userlogic->addModule($this->userID,'Maillist');  //添加模块

            //nats 推送
            $nat_logic = new NatsLogic();
            $publish["authType"] = "BINDADDRESSLIST";
            $nat_logic->userAuth($this->terminal,$publish);

        } else {
            $rs["msg"] = "Grpc服务异常！";
        }
        return $rs;
    }









}
