<?php

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

namespace MobileApi\Api;

use MobileApi\Common\MobileApiBase;
use Service\Spread\Logic\HomeBannerLogic;

/**
 * Description of ApiUser
 *
 * @author Administrator
 */
class ApiBanner extends MobileApiBase {

    public function getRules() {
        return array(
            'getStartList' => array(
                "type" => array('name' => 'type', 'type' => 'int', 'require' => false, 'desc' => '广告类型', 'default' => 167)
            ),

        );
    }

    /**
     * APP广告启动页
     * @desc                            APP广告启动页
     * @return 数组 info                用户信息对象
     * @return int    code              操作码，0表示成功， 1表示失败
     * @return string info.banner_url    图片路径
     * @return string info.link_url      链接地址
     * @return string info.banner_title  标题
     * @return string info.version      图片版本号
     * @return string msg               提示信息
     * --
     */
    public function getStartList() {
        $type = $this->type;
        $logic = new HomeBannerLogic();
        $imgList = $logic->getImg($type);
        $data = array('code' => 0, 'msg' => '', 'info' => array());
        if (empty($imgList)) {
            $data["code"] = 1;
            $data["msg"] = "没有启动图";
            return $data;
        }
        $info = array();
        $info['banner_url'] = UPLOAD . $imgList[0]['banner_url'];
        $info['version'] = md5(UPLOAD . $imgList[0]['banner_url']);
        $info['link_url'] = $imgList[0]['link_url'];
        $info['banner_title'] = $imgList[0]['banner_title'];
        if(empty($info)) {
            $data['code'] = 1;
        }
        $data["info"] = $info;
        return $data;
    }




}
