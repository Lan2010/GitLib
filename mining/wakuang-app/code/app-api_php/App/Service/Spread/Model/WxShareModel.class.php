<?php

namespace Service\Spread\Model;

use Think\Model;
use Common\Model\SlaveModel;
use Think\Exception;

class WxShareModel extends SlaveModel {

    /**
     * 获取微信分享的信息  查询从库
     * @param type $where
     * @param type $whereArr
     * @param type $limit
     * @return type
     */
    public function getShare($where, $whereArr, $limit) {
        $result["total"] = $this->SlaveDB()->table("d_wx_share")->where($where, $whereArr)->count();
        $field = "share_id,share_name,share_code,add_user_id,add_datetime,share_title,add_user_name,share_desc,share_img";
        $result["rows"] = $this->SlaveDB()->table("d_wx_share")->field($field)->where($where, $whereArr)->order("add_datetime desc")->limit($limit)->select();
        return $result;
    }

    /**
     * 删除微信分享的信息
     * 添加微信分享的信息
     * @param type $data
     * @param type $where
     * @return type
     */
    public function saveShare($data, $where) {
        try {
            $model = M("wx_share");
            if (is_array($where) && count($where) > 0) {
                $result = $model->where($where)->data($data)->save();
                return $result * 1 > 0 ? $where["share_id"] : false;
            } else {
                return $model->add($data);
            }
            return false;
        } catch (Exception $ex) {
            logger(__METHOD__, $ex->getMessage());
            return false;
        }
    }

    /**
     * 获取编辑时候微信分享信息
     * @param type $where
     * @return type
     */
    public function getSinShare($where) {
        $result = $this->SlaveDB()->table("d_wx_share")->where($where)->find();
        return $result;
    }
}
