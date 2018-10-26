<?php

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

namespace Service\Spread\Logic;

use Service\Spread\Model\WxReplyModel;
use Common\Common\Redis;
use Think\Exception;

/**
 * Description of WxReplyLogic
 *
 * @author Administrator
 */
class WxReplyLogic {

    /**
     * 自动回复类型。
     * @var type 
     */
    public $replyTypes = array('default' => '未识别回复', 'autoReply' => '自动回复', 'subscribe' => '关注微信公共号回复', 'scan' => '邀请二维码扫码回复', 'click' => '点击回复');

    /**
     * 回复内容类型。
     * @var type 
     */
    public $contentTypes = array('text' => '文本', 'image' => '图片', 'news' => '图文');

    /**
     * 获取微信回复消息集合。
     * @param type $where
     * @param type $whereArr
     * @param type $limit
     * @return type
     */
    public function getList($where, $whereArr, $limit = '0,15') {
        $model = new WxReplyModel();
        $where .= " AND status = '%d'";
        array_push($whereArr, 1);
        $result = $model->getList("(1=1) " . $where, $whereArr, $limit);
        return $result;
    }

    /**
     * 查询微信回复消息
     * @param type $replyID
     * @return type
     */
    public function getEntity($replyID) {
        $where["reply_id"] = $replyID;
        $where["status"] = 1;
        $model = new WxReplyModel();
        return $model->getEntity($where);
    }

    /**
     * 根据回复类型获取回复消息内容。
     * @param type $replyType
     */
    public function getReplyByType($replyType) {
        $replyType = trim($replyType);
        if (empty($replyType) || strlen($replyType) < 1)
            return false;
        $redis = Redis::GetInstance();
        $arr = $redis->get("WeixinReply_" . $replyType);
        if (empty($arr) || is_null($arr)) {
            $model = new WxReplyModel();
            $arr = $model->getReplys($replyType);
            $redis->set("WeixinReply_" . $replyType, $arr, 86400 * 7); //存储一周
        }
        return $arr;
    }
   

    /**
     * 保存回复消息 
     * @param type $data
     */
    public function saveReply($data) {
        try {
            $where = array();
            if ($data["reply_id"] * 1 > 0 && count($data) > 0) {
                $where["reply_id"] = $data["reply_id"];
                $data ['edit_user_id'] = C('userID');
                $data ['edit_user_name'] = C('userName');
                $data ['edit_datetime'] = time();
            } else {
                $data ['add_user_id'] = C('userID');
                $data ['add_user_name'] = C('userName');
                $data ['add_datetime'] = time();
            }
            unset($data["reply_id"]);
            $data ['operate_ip'] = getIP();
            $model = new WxReplyModel();
            $result = $model->saveReply($data, $where);
            $redis = Redis::GetInstance(); //删除缓存
            $redis->del("WeixinReply_" . $data ['reply_type']);
            return $result;
        } catch (Exception $ex) {
            logger(__METHOD__, $ex->getMessage());
            return false;
        }
    }

    /**
     * 删除微信回复
     * @param type $replyID
     * @return boolean
     */
    public function delReply($replyID) {
        if ($replyID * 1 < 1) {
            return false;
        }
        $where["reply_id"] = $replyID;
        $data["status"] = 0;
        $data ['operate_ip'] = getIP();
        $model = new WxReplyModel();
        $result = $model->saveReply($data, $where);
        if ($result) {
            $rs = $model->getEntity($where);
            $type = $rs["reply_type"];
            $redis = Redis::GetInstance();
            $redis->del("WeixinReply_" . $type);
        }
        return $result;
    }

}
