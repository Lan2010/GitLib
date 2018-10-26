<?php

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

namespace Service\Spread\Model;

use Common\Model\SlaveModel;
use Think\Exception;

/**
 * Description of WxReplyModel
 *
 * @author Administrator
 */
class WxReplyModel extends SlaveModel {

    /**
     * 获取微信回复消息集合。
     * @param type $where
     * @param type $whereArr
     * @param type $limit
     * @return type
     */
    public function getList($where, $whereArr, $limit) {
        $result["total"] = $this->where($where, $whereArr)->count();
        $result["rows"] = $this->where($where, $whereArr)->order('add_datetime desc')->limit($limit)->select();
        return $result;
    }

    /**
     * 根据回复类型获取微信回复消息集合。
     * @param type $where
     * @param type $whereArr
     * @param type $limit
     * @return type
     */
    public function getReplys($replyType) {
        if (empty($replyType)) {
            return false;
        }
        $where['replyType'] = $replyType;
        $where['status'] = 1;
        return $this->field('replyType,keyword,contentType,title,content,imgUrl,url,showTitle,isAuth')->where($where)->order('addDatetime desc')->select();
    }

    /**
     * 查询微信回复消息
     * @param type $where
     * @return type
     */
    public function getEntity($where) {
        $result = $this->where($where)->find();
        return $result;
    }

    /**
     * 保存回复消息 
     * @param type $where
     * @param type $data
     */
    public function saveReply($data, $where) {
        try {
            if (!empty($where) && is_array($where) && count($where) > 0) {
                return $this->where($where)->data($data)->save();
            } else {
                return $this->add($data);
            }
        } catch (Exception $ex) {
            logger(__METHOD__, $ex->getMessage());
            return FALSE;
        }
    }

}
