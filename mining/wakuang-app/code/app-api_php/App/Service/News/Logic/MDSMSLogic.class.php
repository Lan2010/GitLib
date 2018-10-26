<?php

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

namespace Service\News\Logic;

/**
 * 漫道短信
 */
class MDSMSLogic {

    public function sendsms($moblie, $content) {
        $sn = 'SDK-BBX-010-23690Test'; //提供的账号
        $pwd = strtoupper(md5('SDK-BBX-010-23690' . '9b3db5@a'));
        $data = array(
            'sn' => $sn, //提供的账号
            'pwd' => $pwd, //此处密码需要加密 加密方式为 md5(sn+password) 32位大写
            'mobile' => $moblie, //手机号 多个用英文的逗号隔开 post理论没有长度限制.推荐群发一次小于等于10000个手机号
            'content' => htmlspecialchars($content . "【钱盒子金融】"), //短信内容//htmlspecialchars() 函数把一些预定义的字符转换为 HTML 实体。
            'ext' => '',
            'stime' => '', //定时时间 格式为2011-6-29 11:09:21
            'rrid' => '', //默认空 如果空返回系统生成的标识串 如果传值保证值唯一 成功则返回传入的值
            'msgfmt' => ''
        );
        $url = "http://sdk.entinfo.cn:8061/mdsmssend.ashx";
        $retult = $this->api_notice_increment($url, $data);
        $retult = str_replace("<?xml version=\"1.0\" encoding=\"utf-8\"?>", "", $retult);
        $retult = str_replace("<string xmlns=\"http://tempuri.org/\">", "", $retult);
        $retult = str_replace("</string>", "", $retult);
        if ($retult * 1 > 0) {
            return true;
        }
        return $this->errorCode($retult);
    }

    private function api_notice_increment($url, $data) {
        $curl = curl_init(); // 启动一个CURL会话
        curl_setopt($curl, CURLOPT_URL, $url); // 要访问的地址
        curl_setopt($curl, CURLOPT_SSL_VERIFYPEER, 0); // 对认证证书来源的检查
        curl_setopt($curl, CURLOPT_SSL_VERIFYHOST, 1); // 从证书中检查SSL加密算法是否存在
        curl_setopt($curl, CURLOPT_USERAGENT, $_SERVER['HTTP_USER_AGENT']); // 模拟用户使用的浏览器
        curl_setopt($curl, CURLOPT_FOLLOWLOCATION, 1); // 使用自动跳转
        curl_setopt($curl, CURLOPT_AUTOREFERER, 1); // 自动设置Referer
        curl_setopt($curl, CURLOPT_POST, 1); // 发送一个常规的Post请求
        $data = http_build_query($data);
        curl_setopt($curl, CURLOPT_POSTFIELDS, $data); // Post提交的数据包
        curl_setopt($curl, CURLOPT_TIMEOUT, 30); // 设置超时限制防止死循环
        curl_setopt($curl, CURLOPT_HEADER, 0); // 显示返回的Header区域内容
        curl_setopt($curl, CURLOPT_RETURNTRANSFER, 1); // 获取的信息以文件流的形式返回
        $lst = curl_exec($curl);
        if (curl_errno($curl)) {
            return 'Errno' . curl_error($curl); //捕抓异常
        }
        curl_close($curl);
        return $lst;
    }

    private function errorCode($num) {
        $errorArr = array(
            '0' => '成功',
            '-2' => '帐号/密码不正确',
            '-4' => '余额不足支持本次发送',
            '-5' => '数据格式错误',
            '-6' => '参数有误',
            '-7' => '权限受限',
            '-8' => '流量控制错误',
            '-9' => '扩展码权限错误',
            '-10' => '内容长度长',
            '-11' => '内部数据库错误',
            '-12' => '序列号状态错误',
            '-14' => '服务器写文件失败',
            '-17' => '没有权限',
            '-19' => '禁止同时使用多个接口地址',
            '-20' => '相同手机号，相同内容重复提交',
            '-22' => 'Ip鉴权失败',
            '-23' => '缓存无此序列号信息',
            '-601' => '序列号为空，参数错误',
            '-602' => '序列号格式错误，参数错误',
            '-603' => '密码为空，参数错误',
            '-604' => '手机号码为空，参数错误',
            '-605' => '内容为空，参数错误',
            '-606' => 'ext长度大于9，参数错误',
            '-607' => '参数错误 扩展码非数字',
            '-608' => '参数错误 定时时间非日期格式',
            '-609' => 'rrid长度大于18,参数错误',
            '-610' => '参数错误 rrid非数字',
            '-611' => '参数错误 内容编码不符合规范',
            '-623' => '手机个数与内容个数不匹配',
            '-624' => '扩展个数与手机个数数',
            '-625' => '定时时间个数与手机个数数不匹配',
            '-626' => 'rrid个数与手机个数数不匹配',
        );
        return $errorArr[$num];
    }

}
