<?php

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

namespace Service\News\Logic;

/**
 * 深圳诚立业科技发展有限公司
 *
 * @author DREAM
 */
class CLYSMSLogic {

    private $m_username = "qhzjrhyTest";
    private $m_pwd = "he5y2j90";

    /**
     * 
     * @param type $isPromotion 是否促銷短信
     */
    public function __construct($isPromotion = false) {
        if ($isPromotion === true) {
            $this->m_username = "qhzjryxTest";
            $this->m_pwd = "mfvsg7kr";
        }
    }

    public function sendsms($moblie, $content) {
        $password = md5($this->m_username . "" . md5($this->m_pwd));
        $url = "http://sms-cly.cn/smsSend.do?";
        $param = http_build_query(
                array(
                    'username' => $this->m_username,
                    'password' => $password,
                    'mobile' => $moblie,
                    'content' => $content//iconv("GB2312", "UTF-8", $content)
                )
        );
        $retult = $this->httpPost($url, $param);
        if ($retult * 1 > 0 || strlen($retult) > 6) {
            return true;
        }
        logger_sys("短信发送", $this->errorCode($retult) . $moblie);
        return false;
    }

    private function httpPost($url, $param) {
        $ch = curl_init();
        curl_setopt($ch, CURLOPT_URL, $url);
        curl_setopt($ch, CURLOPT_HEADER, 0);
        curl_setopt($ch, CURLOPT_RETURNTRANSFER, 1);
        curl_setopt($ch, CURLOPT_POST, 1);
        curl_setopt($ch, CURLOPT_POSTFIELDS, $param);
        $result = curl_exec($ch);
        curl_close($ch);
        return $result;
    }

    private function errorCode($num) {
        $errorArr = array(
            '0' => '失败',
            '-1' => '用户名或者密码不正确',
            '-2' => '必填选项为空',
            '-3' => '短信内容0个字节',
            '-5' => '余额不够',
            '-10' => '用户被禁用',
            '-11' => '短信内容超过500字',
            '-12' => '无扩展权限（ext字段需填空）',
            '-13' => 'IP校验错误',
            '-14' => '内容解析异常',
            '-990' => '未知错误'
        );
        return $errorArr[$num];
    }

//    0	失败
//-1	用户名或者密码不正确
//-2	必填选项为空
//-3	短信内容0个字节
//-5	余额不够
//-10	用户被禁用
//-11	短信内容超过500字
//-12	无扩展权限（ext字段需填空）
//-13	IP校验错误
//-14	内容解析异常
//-990	未知错误
//       DELIVRD - 成功接收
//DB:140 - 系统黑名单
//DB:0144 - 全局黑名单
//DB:0107 – 号码不存在、空号、关机或业务不存在
//MK:0000 – 停机
//MK:0001 – 空号
//MK:0004 – 手机暂停使用
//MK:0005 – 手机关机或来自MSC的未知错误
//ID:0076 – 信息内容涉及安全信息
//MC:0001 – 关机或停机
//MBBLACK – 黑名单
//MB:1041 – SMSC返回错误响应消息时的状态报告
//MN:0001 – SMSC返回状态报告为REJECT
//MM:0000 – SMSC返回报告为UNKNOWN
//MI:0000 – SMSC返回报告为EXPIRED
//MA:0054 – SMSC不返回相应消息的状态报告
//CB:0001 – SCP返回错误响应消息时的状态报告
//IA:0053 – 下一级ISMG不返回响应消息时的状态报告
//IB:0008 – 下一级ISMG返回错误响应时的状态报告
//IC:0055 – 没有从下一级ISMG返回错误响应消息时的状态报告
//UNDELIV – 手机关机、停机或信号不好等原因使信息不可达
//REJECT – 被SMSC驳回
//EXPIRED -消息过期或空号
}
