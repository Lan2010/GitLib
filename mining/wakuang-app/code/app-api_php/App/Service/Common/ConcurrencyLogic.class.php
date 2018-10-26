<?php

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

namespace Service\Common;

use Common\Common\Redis;

/**
 * Description of CallBackLogic
 *
 * @author Administrator
 *   用于简单控制并发
 * 
 */
class ConcurrencyLogic {

    /**
     * 利用redis setnx特点进行并发处理 ， 当同时3个请求到达时， 只有一个setnx设置成功值1， 其余返回0 ， 并不适合处理复制的业务并发 
     * @param type $key  
     * @param type $value  默认空 
     * @param type $ttl  默认2秒 
     * @return boolean true  表示设置成功，  false表示失败，  其余交给程序判断
     */
    public static function signleReq($key, $value = '', $ttl = 2) {
        if (empty($key)) {
            return false;
        }
        $redis = Redis::GetInstance();
        $ret = $redis->setnx($key, $value);
        if ($ret) {
            $redis->expire($key, $ttl);
            return true;
        }
        return false;
    }

}
