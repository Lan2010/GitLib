<?php

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

namespace Service\News\Logic;

/**
 * 希奥短信
 *
 * @author DREAM
 */
class IOOSMSLogic {

    /**
     * 发送通知短信
     * @param type $moblie
     * @param type $content
     * @return boolean
     */
    public function sendNotice($moblie, $content) {
        $uid = '807892'; //提供的账号
        $auth = MD5("qhzi62Ls6##1");
        $content = urlencode($content);
        $url = "http://sms.10690221.com:9011/hy/?uid=$uid&auth=$auth&mobile=$moblie&msg=$content&expid=0&encode=UTF-8";
        $retult = $this->httpGet($url);
        $arr = explode(",", $retult); //0,1430599     
        if ($arr[0] * 1 == 0) {
            return true;
        }
        return $this->errorCode($retult);
    }

    /**
     * 发送验证码短信
     * @param type $moblie
     * @param type $content
     * @return boolean
     */
    public function sendCode($moblie, $content) {
        $uid = '80789'; //提供的账号
        $auth = MD5("qhzsqhzs66");
        $content = urlencode($content);
        $url = "http://sms.10690221.com:9011/hy/?uid=$uid&auth=$auth&mobile=$moblie&msg=$content&expid=0&encode=UTF-8";
        $retult = $this->httpGet($url);
        $arr = explode(",", $retult); //0,1430599     
        if ($arr[0] * 1 == 0) {
            return true;
        }
        return $this->errorCode($retult);
    }
 
    /**
     * 发送营销短信
     * @param type $moblie
     * @param type $content
     * @return boolean
     */
    public function sendMarket($moblie, $content) {
        $uid = '807891'; //提供的账号
        $auth = MD5("qhzsqhz66666");
        $content = urlencode($content);
        $url = "http://sms.10690221.com:9011/hy/?uid=$uid&auth=$auth&mobile=$moblie&msg=$content&expid=0&encode=UTF-8";
        $retult = $this->httpGet($url);
        $arr = explode(",", $retult); //0,1430599     
        if ($arr[0] * 1 == 0) {
            return true;
        }
        return $this->errorCode($retult);
    }


    private function httpGet($url) {
        $curl = curl_init();
        curl_setopt($curl, CURLOPT_RETURNTRANSFER, true);
        curl_setopt($curl, CURLOPT_TIMEOUT, 500);
        curl_setopt($curl, CURLOPT_SSL_VERIFYPEER, false);
        curl_setopt($curl, CURLOPT_SSL_VERIFYHOST, false);
        curl_setopt($curl, CURLOPT_URL, $url);
        $res = curl_exec($curl);
        curl_close($curl);
        return $res;
    }

    private function errorCode($num) {
        $errorArr = array(
            '0' => '操作成功',
            '-1' => '签权失败',
            '-2' => '未检索到被叫号码',
            '-3' => '被叫号码过多',
            '-4' => '内容未签名',
            '-5' => '内容过长',
            '-6' => '余额不足',
            '-7' => '暂停发送',
            '-8' => '保留',
            '-9' => '定时发送时间格式错误',
            '-10' => '下发内容为空',
            '-11' => '账户无效',
            '-12' => 'Ip地址非法',
            '-13' => '操作频率快',
            '-14' => '操作失败',
            '-15' => '拓展码无效',
            '-16' => '取消定时,seqid错误',
            '-17' => '未开通报告',
            '-18' => '暂留',
            '-19' => '未开通上行',
            '-20' => '暂留',
            '-21' => '包含屏蔽词'
        );
        return $errorArr[$num];
    }

}
