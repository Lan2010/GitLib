<?php

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

namespace Service\Spread\Model;

use Think\Model;
use Common\Model\SlaveModel;
use Think\Exception;

/**
 * 手机端下载信息
 *
 * @author DREAM
 */
class AppInfoModel extends SlaveModel {
    
    public $chanKey = array(
        "qhz"=>"钱盒子主包",
        "qq"=>"应用宝",
        "baidu"=>"百度手机助手",
        "360"=>"360手机助手",
        "huawei"=>"华为应用市场",
        "xiaomi"=>"小米应用商店",
        "oppo"=>"OPPO软件商店",
        "vivo"=>"VIVO应用商店",
        "meizu"=>"魅族应用商店",
        "lianxiang"=>"联想乐商店",
        "ali"=>"阿里应用",
        "jifeng"=>"机锋网",
        "anzhi"=>"安智网",
        "appShop"=>"IOS渠道",
    );

    /**
     * 手机端下载信息
     * @return type
     */
    public function getAppInfo($where, $whereArr, $limit) {
        $result["total"] = $this->SlaveDB()->table("d_app_info")->where($where, $whereArr)->count();
        $field = "equipmentID,type,versions,phoneInfo,terminal,province,city,area,addDatetime";
        $result["rows"] = $this->SlaveDB()->table("d_app_info")->where($where, $whereArr)->field($field)->order('addDatetime desc')->limit($limit)->select();
        return $result;
    }

    /**
     * 录入手机信息
     * @param type $data
     * @return type
     * 
     */
    public function saveAppInfo($data) {

        $result = $this->data($data)->add();
        return $result;
    }

}
