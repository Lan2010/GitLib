<?php

namespace Service\Spread\Logic;

use Think\Exception;
use Service\Spread\Model;

class WxShareLogic {

    public $pushType_arr = array('default' => '未识别', 'autoReply' => '自动回复', 'subscribe' => '关注微信公共号', 'click' => '点击回复', 'scan' => '个人微信二维码扫码',);
    public $newsType_arr = array('text' => '文本', 'image' => '图片', 'news' => '图文',);

    /**
     * 获取微信分享的信息
     * @param string $where
     * @param type $whereArr
     * @param type $limit
     * @return type
     */
    public function getShare($where, $whereArr, $limit) {
        $logic = new Model\WxShareModel();
        $where .= " AND share_status = '%d'";
        array_push($whereArr, 1);
        $result = $logic->getShare("(1=1)" . $where, $whereArr, $limit);
        return $result;
    }

    /**
     * 删除微信分享的信息
     * @param type $key
     * @return boolean
     */
    public function delShare($shareID) {
        if ($shareID * 1 < 1) {
            return FALSE;
        }
        $data["shareStatus"] = 0;
        $where["shareID"] = $shareID;
        $mod = new Model\WxShareModel();
        $result = $mod->saveShare($data, $where);
        return $result;
    }

    /**
     * 保存微信分享信息
     * @param type $data
     * @return boolean
     */
    public function saveShare($data) {
        try {
            $where = array();
            if ($data["share_id"] * 1 > 0 && count($data) > 0) {
                $where["share_id"] = $data["share_id"];
                $data ['edit_user_id'] = C('userID');
                $data ['edit_user_name'] = C('userName');
                $data ['edit_datetime'] = time();
                unset($data["share_id"]);
            } else {
                $data ['add_user_id'] = C('userID');
                $data ['add_user_name'] = C('userName');
                $data ['add_datetime'] = time();
            }
            $model = new Model\WxShareModel();
            $result = $model->saveShare($data, $where);
            return $result;
        } catch (Exception $ex) {
            logger(__METHOD__, $ex->getMessage());
            return false;
        }
    }

    /**
     * 获取编辑微信分享时的信息
     * @param type $shareID
     * @return boolean
     */
    public function getSinShare($shareID) {
        $model = new Model\WxShareModel();
        if ($shareID * 1 < 1) {
            return FALSE;
        }
        $where["shareID"] = $shareID;
        $where["shareStatus"] = 1;
        $result = $model->getSinShare($where);
        return $result;
    }

    /**
     * 获取微信分享通过类型
     * @param type $code
     */
    public function getWxshare($code) {
        $model = new Model\WxShareModel();
        $where["shareCode"] = $code;
        $where["shareStatus"] = 1;
        $result = $model->getSinShare($where);
        return $result;
    }

}
