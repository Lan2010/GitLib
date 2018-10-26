<?php

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

namespace MobileApi\Api;

use MobileApi\Common\MobileApiBase;
use Common\Common;
use Service\Common\CommonLogic;
use Service\Nats\Logic\NatsLogic;
use Service\User\Logic\UserLogic;
use Service\Spread\Logic\AppVersionsLogic;
use Service\Spread\Logic\HomeBannerLogic;
use Service\User\Logic\UserInviteLogic;
use Common\Common\Redis;

/**
 * Description of ApiCommon
 *
 * @author Administrator
 */
class ApiCommon extends MobileApiBase {

    public function getRules() {
        return array(
            'timestamp' => array(),
            'appjmp' => array(),
            'versionInfo' => array(
            ),
            'scanQrcode' => array(
                'qrtxt' => array('name' => 'qrtxt', 'desc' => '二维码内容', 'default' => ''),
            ),
            "commonUrl"=>array(),
            "suspensionFrame"=>array(),

            'AppStopStar'=>array(
                'mobile'=>array("name"=>"mobile","desc"=>"手机号码",'default'=>''),
                'type' => array('name' => 'type', 'desc' =>'1开机 0 关机' , 'default' => ''),
            ),
        );
    }


    /**
     * 获取通用的链接信息
     * @desc 获取到通用的信息链接
     * @return   int       code                          操作码，0表示成功， 1表示失败
     * @return   object    info                           对应的信息
     * @return   string    msg                           提示信息
     */
    public function commonUrl() {
        $data = array('code' => 0, 'msg' => '操作成功', 'info' => array());
        $rs = array(
            array("url"=>'http://192.168.11.17/wechat/web/gengc','desc'=>"基因数据","state"=>"GeneUrl"),
            array("url"=>'http://192.168.11.17/wechat/web/getServiceAgreement','desc'=>"用户服务协议","state"=>"RegisterUrl"),
            array("url"=>'http://www.baidu.com','desc'=>"关于我们","state"=>"AboutUrl"),
            array("url"=>'http://192.168.11.17/wechat/web/gengc','desc'=>"数字生命","state"=>"DigitalUrl"),
            array("url"=>'http://www.baidu.com','desc'=>"星链攻略","state"=>"StrategyUrl"),
        );
        $data['info'] = $rs;
        return $data;
    }

    /**
     * 获取服务器时间。
     * @desc 用于APP与服务器对时。
     * @return   int       code                          操作码，0表示成功， 1表示失败
     * @return   object    info 
     * @return   int       info.timestamp                服务器当前时间 
     * @return   string    msg                           提示信息
     */
    public function timestamp() {
        $data = array('code' => 0, 'msg' => '操作成功', 'info' => array());
        $data['info']['timestamp'] = time();
        return $data;
    }

    /**
     * 获取APP版本信息。
     * @desc 用于获取最新APP版本的相关信息，用于APP更新
     * @return   int       code                          操作码，0表示成功， 1表示失败
     * @return   object    info 
     * @return   string    info.version_name    版本名称（IOS版本号/安卓版本名称）
     * @return   string    info.update_desc     更新说明
     * @return   string    info.app_size        APP包大小（单位MB）
     * @return   string    info.is_forced       是否强制更新（0：不强制 1：强制）
     * @return   string    info.version_code    版本号
     * @return   string    info.update_url      更新地址（用于安卓，IOS不返回该字段）
     * @return   array     info.codes          MD5集合（用于安卓，IOS不返回该字段） 
     * @return   string    msg                 提示信息
     */
    public function versionInfo() {
        $data = array('code' => 1, 'msg' => '获取失败', 'info' => array());
        if ($this->terminal == 2 || $this->terminal == 3) {
            $logic = new AppVersionsLogic();
            $verInfo = $logic->getInfo($this->terminal);
            if (!empty($verInfo)) {
                $data['code'] = 0;
                $data['msg'] = '获取成功！';
                $data['info']['version_name'] = $verInfo['version_name'];
                $data['info']['version_code'] = $verInfo['version_code'];
                $data['info']['update_desc'] = $verInfo['update_desc'];
                $data['info']['is_forced'] = $verInfo['is_forced'];
                $data['info']['app_size'] = $verInfo['app_size'];
                if ($this->terminal == 2) {
                    $data['info']['update_url'] = $verInfo['update_url'];
                    $codes = $logic->getCheckCodes();
                    $data['info']['codes'] = array_column($codes, 'check_code');
                }
            }
        }
        return $data;
    }

    /**
     * APP跳转到H5设置Cookie信息。
     * @desc   APP跳转到H5设置Cookie信息用于同步用户信息。 **
     * @return   int       code                          操作码，0表示成功， 1表示失败
     * @return   string    msg                           提示信息
     */
//    public function appjmp() {
//        $info = array('code' => 0, 'msg' => '操作成功', 'info' => array());
//        $this->setAppJmpCookie($this->userToken);
//        return $info;
//    }





    /**
     * 识别二维码。
     * @desc 用于识别二维码
     * @return  int code                       操作码，0表示成功， 1表示失败
     * @return  object info                    识别出的信息
     * @return  int info.typeCode              内容类型 （10：文本（只显示内容也可不显示） 20：邀请码（打开注册界面） 30：平台URL(打开WebView)）
     * @return  stirng info.typeName           内容类型说明
     * @return  string info.content            内容
     * @return string msg                      提示信息
     */
//    public function scanQrcode() {
//        $data = array('code' => 0, 'msg' => '识别到内容', 'info' => array());
//        $qrtxt = trim($this->qrtxt);
//        if (empty($qrtxt) || strlen($qrtxt) == 0) {
//            $data['code'] = 1;
//            $data['msg'] = "无法识别该二维码";
//            return $data;
//        }
//        if (filter_var($qrtxt, FILTER_VALIDATE_URL) === false) {
//            $data['info'] = array('typeCode' => 10, 'typeName' => '文本', 'content' => $qrtxt);
//        } else if (strpos(strtolower($qrtxt), "weixin.qq.com/q/") !== false) {
//            $redis = Redis::getInstance();
//            $userID = $redis->get("FriendWXQrcode_" . strtoupper(md5($qrtxt)));
//            if ($userID) {
//                $code = $this->getFriendCode($userID);
//                if (!empty($code)) {
//                    $data['info'] = array('typeCode' => 20, 'typeName' => '邀请码', 'content' => $code);
//                } else {
//                    $data['code'] = 1;
//                    $data['msg'] = "邀请二维码已过期";
//                }
//            } else {
//                $data['code'] = 1;
//                $data['msg'] = "邀请二维码已过期";
//            }
//        } else if (strpos(strtolower($qrtxt), ".qianhezi.cn/wechat") !== false || strpos(strtolower($qrtxt), ".qianbox.net/wechat") !== false) {
//            $data['info'] = array('typeCode' => 30, 'typeName' => '平台URL', 'content' => $qrtxt);
//        } else {
//            $data['info'] = array('typeCode' => 10, 'typeName' => '文本', 'content' => $qrtxt);
//        }
//        return $data;
//    }
    /**
     *悬浮窗数据获取
     * @desc 用于识别二维码
     * @return  int code                       操作码，0表示成功， 1表示失败
     * @return  object info                    识别出的信息
     * @return  int info.frame_icon             图片
     * @return  stirng info.add_datetime       添加时间
     * @return  string info.begin_datetime     弹出时间
     * @return  string info.end_datetime       结束时间
     * @return  string info.frame_word         弹出文案
     * @return int info.link                  链接
     * @return string msg                      提示信息
     */
    public function suspensionFrame() {
        $data = array('code' => 0, 'msg' => '识别到内容', 'info' => array());
        $com_logic = new CommonLogic();
        $info = $com_logic->getFrame();
        if(empty($info)) {
            $data['code'] = 1;
            $data["msg"] = "暂无数据";
        }
        $data["info"] = $info;
        return $data;
    }


    /**
     *APP启动关闭接口
     * @desc 用于识别二维码
     * @return  int code                       操作码，0表示成功， 1表示失败
     * @return string msg                      提示信息
     */
    public function AppStopStar() {
        $data = array('code' => 0, 'msg' => '识别到内容', 'info' => array());
        $logic = new NatsLogic();
        $publish['operationType'] = $this->type;
        $publish['mobile'] = $this->mobile;
        $logic->appOperation($this->terminal,$publish);
        return $data;
    }


}
