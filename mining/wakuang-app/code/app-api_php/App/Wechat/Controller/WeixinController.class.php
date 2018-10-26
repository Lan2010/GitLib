<?php

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

namespace Wechat\Controller;

use Service\News\Logic\WeiXinLogic;
use Service\Spread\Logic\WxShareLogic;
use Service\User\Logic\UserInviteLogic;
use Service\Spread\Logic\HomeBannerLogic;
use Service\Spread\Logic\WxReplyLogic;
use Service\Wechat\Logic\WxCodebindLogic;

/**
 * Description of WeixinController
 *
 * @author Administrator
 */
class WeixinController extends BaseMobileController {

    private $fromUsername;
    private $toUsername;
    private $_time;
    private $MsgType;
    private $EventType;
    private $EventKey;

    /**
     * 微信入口文件
     */
    public function index() {
        if (!isset($_GET['echostr'])) {
            $this->responseMsg();
        } else {
            $this->checkSignature();
            echo $_GET['echostr'];
        }
        exit;
    }

    /**
     * 获取微信分享配置信息。
     */
    public function getShareConfig() {
        $url = $_REQUEST['url'];
        if (empty($url) || strlen($url) < 5) {
            $url = WECHAT;
        }
        $wxLogic = new WeiXinLogic();
        $signPackage = $wxLogic->getShareConfig($url);
        $jsondata = json_encode($signPackage);
        $callback = isset($_GET['callback']) ? trim($_GET['callback']) : ''; //jsonp回调参数，必需
        if (!empty($callback)) {
            header('Access-Control-Allow-Origin:*');   // 指定允许其他域名访问 
            header('Access-Control-Allow-Headers:x-requested-with,content-type'); // 响应头设置
            echo $callback . '(' . $jsondata . ')';  //返回格式，必需
        } else {
            echo $jsondata;  //返回格式，必需
        }
    }

    /**
     * 获取分享内容。
     */
    public function getShareInfo() {
        $code = $_REQUEST['actcode'];
        $shareUrl = $_REQUEST['shareurl'];
        $wxLogic = new WxShareLogic();
        $wxResult = $wxLogic->getWxshare($code);
        $data = array('code' => 1, 'result' => array());
        if ($wxResult) {
            $result = array(
                'title' => $wxResult['shareTitle'],
                'msg' => $wxResult['shareDesc'],
                'imgurl' => UPLOAD . $wxResult['shareImg'],
                'url' => (empty($shareUrl) ? WECHAT : $shareUrl),
            );
            $data['code'] = 0;
            $data['result'] = $result;
        }
        $jsondata = json_encode($data);
        $callback = isset($_GET['callback']) ? trim($_GET['callback']) : ''; //jsonp回调参数，必需

        if (!empty($callback)) {
            echo $callback . '(' . $jsondata . ')';  //返回格式，必需
        } else {
            echo $jsondata;  //返回格式，必需
        }
    }

    /**
     * 验证
     * @return boolean
     */
    private function checkSignature() {
        $parm = I("GET.");
        $signature = $parm["signature"];
        $timestamp = $parm["timestamp"];
        $nonce = $parm["nonce"];
        $token = TOKEN;
        $tmpArr = array($token, $timestamp, $nonce);
        sort($tmpArr, SORT_STRING);
        $tmpStr = implode($tmpArr);
        $tmpStr = sha1($tmpStr);
        if ($tmpStr == $signature) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 用户接受消息和发送消息，都通过此函数
     */
    private function responseMsg() {
        $postStr = $GLOBALS ["HTTP_RAW_POST_DATA"];
        if (!empty($postStr)) {
            libxml_disable_entity_loader(true);
            $postObj = simplexml_load_string($postStr, 'SimpleXMLElement', LIBXML_NOCDATA);
            $this->fromUsername = $postObj->FromUserName; // 发送方帐号（一个OpenID）
            $this->toUsername = $postObj->ToUserName; // 开发者微信号
            $this->MsgType = utf8_decode($postObj->MsgType); // 消息类型
            $RX_TYPE = trim(utf8_decode($postObj->MsgType));
            $this->_time = time();
            switch ($RX_TYPE) {
                case "event":
                    //$result = $this->receiveEvent($postObj);
                    break;
                case "text":
                    if(trim($postObj->Content) == "领取星星") {
                        $logic = new WxCodebindLogic();
                        $sendData  = $logic->getCode($this->fromUsername);   //只是加个验证码功能  提前出来  后面如果不要 直接删除 换为原先注释的内容即可
                        $this->replyTextMsg($sendData);
                    }
                    //$result = $this->receiveText($postObj);
                    break;
                case "image":
                    //  $result = $this->receiveImage($postObj);
                    break;
                case "location":
                    //  $result = $this->receiveLocation($postObj);
                    break;
                case "voice":
                    // $result = $this->receiveVoice($postObj);
                    break;
                case "video":
                    //  $result = $this->receiveVideo($postObj);
                    break;
                case "link":
                    //   $result = $this->receiveLink($postObj);
                    break;
                default:
                    $result = "unknow msg type: " . $RX_TYPE;
                    logger($result);
                    break;
            }
        }
    }



    //接收文本消息
    private function receiveText($postObj) {
        $this->replyInfo('autoReply', $postObj->Content);
    }

    /**
     * 接收事件消息
     * @param type $postObj
     */
    private function receiveEvent($postObj) {
        $object = utf8_decode($postObj->Event);
        switch ($object) {
            case "subscribe": //关注    
                if (!empty($postObj->EventKey)) {
                    $this->inviteFriends($postObj->EventKey);
                } else {
                    $this->replyInfo('subscribe', 'subscribe');
                }
                break;
            case "unsubscribe": //取消关注
                $userLogic = new \Service\User\Logic\UserLogic();
                $openID = $this->fromUsername . "";
                $ret = $userLogic->bindExist($openID, NULL);
                if ($ret * 1 > 0) {
                    $userLogic->cancelWeixin($openID);
                }
                break;
            case "SCAN"://已经关注扫码推广
                $this->inviteFriends($postObj->EventKey);
                break;
            case "CLICK"://事件
                $this->replyInfo('click', $postObj->EventKey);
                break;
            case "LOCATION":
                // $content = "上传位置：纬度 " . $object->Latitude . ";经度 " . $object->Longitude;
                break;
            case "VIEW":
                //  $content = "跳转链接 " . $object->EventKey;
                break;
            default:
                $this->replyDefaultInfo();
                break;
        }
    }

    /**
     * 回复图文信息接口级相关参数
     * @param 
     * @return string
     */
    private function replyImagesMsg($newsContent = array()) {
        $newsTplHead = "<xml>
                                <ToUserName><![CDATA[%s]]></ToUserName>
                                <FromUserName><![CDATA[%s]]></FromUserName>
                                <CreateTime>%s</CreateTime>
                                <MsgType><![CDATA[news]]></MsgType>
                                <ArticleCount>1</ArticleCount>
                                <Articles>";
        $newsTplBody = "<item>
                                <Title><![CDATA[%s]]></Title> 
                                <Description><![CDATA[%s]]></Description>
                                <PicUrl><![CDATA[%s]]></PicUrl>
                                <Url><![CDATA[%s]]></Url>
                                </item>";
        $newsTplFoot = "</Articles>
                                <FuncFlag>0</FuncFlag>
                                </xml>";
        $header = sprintf($newsTplHead, $this->fromUsername, $this->toUsername, $this->_time);
        $title = $newsContent['title'];
        $desc = $newsContent['description'];
        $picUrl = $newsContent['picUrl'];
        $url = $newsContent['url'];
        $body = sprintf($newsTplBody, $title, $desc, $picUrl, $url);
        $FuncFlag = 0;
        $footer = sprintf($newsTplFoot, $FuncFlag);
        $resultStr = $header . $body . $footer;
        echo $resultStr;
    }

    /**
     * 客服回复
     * @param type $sendmsg
     */
    private function replyCustomerMsg() {
        $textTpl = "<xml>
            <ToUserName><![CDATA[%s]]></ToUserName>
            <FromUserName><![CDATA[%s]]></FromUserName>
            <CreateTime>%s</CreateTime>
            <MsgType><![CDATA[transfer_customer_service]]></MsgType>
            </xml>";
        $resultStr = sprintf($textTpl, $this->fromUsername, "szqianhezi", $this->_time);
        echo $resultStr;
    }

    /**
     * 回复文本信息接口级相关参数
     * @param type $sendmsg
     */
    private function replyTextMsg($sendmsg) {
        $textTpl = "<xml>
                            <ToUserName><![CDATA[%s]]></ToUserName>
                            <FromUserName><![CDATA[%s]]></FromUserName>
                            <CreateTime>%s</CreateTime>
                            <MsgType><![CDATA[%s]]></MsgType>
                            <Content><![CDATA[%s]]></Content>
                            <FuncFlag>0</FuncFlag>
                            </xml>";
        $resultStr = sprintf($textTpl, $this->fromUsername, $this->toUsername, $this->_time, 'text', $sendmsg);
        echo $resultStr;
    }

    /**
     * 邀请好友回复
     */
    private function inviteFriends($eventKey) {
        $eventKeyArr = explode('_', $eventKey);
        $userID = $eventKeyArr[count($eventKeyArr) - 1];
        $userInvite = new UserInviteLogic();
        $result = $userInvite->getInviteUser($userID);
        $this->replyInfo('scan', 'scan', hidPhone($result["phone"]), $result['code']);
    }

    /**
     * 回复内容。
     * @param type $replyType
     * @param type $keyword
     */
    private function replyInfo($replyType, $keyword = "", $phone = "", $inviteCode = "") {
        $result = $this->replyInfoByKey($replyType, $keyword, $phone, $inviteCode);

        if ($result === false && $replyType != "default") {
            $this->replyDefaultInfo();
        }
    }

    /**
     * 回复默认消息。
     */
    private function replyDefaultInfo() {
        $this->replyInfoByKey('default', 'default');
    }

    /**
     * 根据关键字回复消息。
     * @param type $replyType
     * @param type $keyword
     * @return boolean
     */
    private function replyInfoByKey($replyType, $keyword = "", $phone = "", $inviteCode = "") {
        $keyword = trim($keyword);
        $wxlogic = new WxReplyLogic();
        $infos = $wxlogic->getReplyByType($replyType);
        $info = array();
        foreach ($infos as $key => $value) {
            if ($value['keyword'] == $keyword) {
                $info = $value;
                break;
            }
        }
        if (!empty($info) || is_array($info) && count($info) > 0) {
            $info['content'] = html_entity_decode($info['content']);
            $info['url'] = trim($info['url']);
            if (!empty($phone) && strlen($phone) > 0) {
                $info['title'] = str_replace('{phone}', $phone, $info['title']);
                $info['content'] = str_replace('{phone}', $phone, $info['content']);
                $info['description'] = str_replace('{phone}', $phone, $info['description']);
                $info['url'] = str_replace('{phone}', $phone, $info['url']);
            }
            if (!empty($inviteCode) && strlen($inviteCode) > 0) {
                $info['title'] = str_replace('{invitecode}', $inviteCode, $info['title']);
                $info['content'] = str_replace('{invitecode}', $inviteCode, $info['content']);
                $info['description'] = str_replace('{invitecode}', $inviteCode, $info['description']);
                $info['url'] = str_replace('{invitecode}', $inviteCode, $info['url']);
            }
            $contentType = $info['contentType'];
            if ($contentType == "text") {
                $this->replyTextMsg($info['content']);
            } else {
                $newsContent = array(
                    'title' => ($info['showTitle'] * 1 == 1 ? $info['title'] : ''),
                    'description' => $info['content'],
                    'picUrl' => UPLOAD . $info['imgUrl'],
                    'url' => $this->getOutUrlAuth($info['url'], $value['isAuth']),
                );
                $this->replyImagesMsg($newsContent);
            }
            return true;
        }
        return false;
    }

    /**
     * 获取微信Auth2.0网页授权链接
     * @param unknown $redirect  必填    跳转页面
     * @return string             
     */
    private function getOutUrlAuth($redirect, $isAuth = 0) {
        $redirect = trim($redirect);
        if ($isAuth * 1 != 1 || empty($redirect) || strlen($redirect) == 0) {
            return $redirect;
        }
        $appid = APPID;
        $redirect_url = urlencode($redirect);
        $url = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=$appid&redirect_uri=$redirect_url&response_type=code&scope=snsapi_userinfo&state=STATE#wechat_redirect";
        return $url;
    }





}
