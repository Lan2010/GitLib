<?php

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

namespace Service\News\Model;

/**
 * Description of SiteNewsModel
 *
 * @author DREAM
 */
use Common\Model\SlaveModel;
use Think\Exception;

class SiteNewsModel extends SlaveModel {

    /**
     * 获取站内消息列表。
     * @param type $where
     * @param type $whereArr
     * @param type $limit
     * @return type
     */
    public function getNewsList($where, $whereArr, $limit = "0,15") {
        if (empty($where) || empty($whereArr) || !is_array($whereArr)) {
            $this->SlaveDB()->field("newsID,title,content,status,addDatetime")->order(" addDatetime DESC")->limit($limit)->select();
        } else {
            return $this->SlaveDB()->field("newsID,title,content,status,addDatetime")->where($where, $whereArr)->order(" addDatetime DESC")->limit($limit)->select();
        }
    }

    /**
     * 发送站内信
     * @param type $entity
     * @return boolean
     */
    public function addNews($entity) {
        try {
            if (is_array($entity)) {
                $entity["status"] = 1;
                $entity["addDatetime"] = time();
                return $this->add($entity);
            } else {
                return false;
            }
        } catch (Exception $ex) {
            logger_sys("站内消息发送失败", $ex->getMessage());
            return false;
        }
    }

    /**
     * 批量插入站内信
     * @param type $entity
     * @return type
     */
    public function addSiteALL($entity) {
        try {
            if (is_array($entity)) {
                return $this->addAll($entity);
            } else {
                return false;
            }
        } catch (Exception $ex) {
            logger_sys("站内消息批量发送失败", $ex->getMessage());
            return false;
        }
    }

    /**
     * 查看站内信
     * @param type $newsID
     * @param type $userID
     * @return boolean
     */
    public function readSiteNews($newsID, $userID) {
        if (isset($newsID)) {
            $data["status"] = 2;
            $data["viewDatetime"] = time();
            $where["newsID"] = $newsID;
            $where["userID"] = $userID;
            return $this->where($where)->data($data)->save();
        } else {
            return false;
        }
    }

    /**
     * 全部标记为已读。
     * @param type $userID
     * @return boolean
     */
    public function allHaveRead($userID) {
        if (isset($userID)) {
            $data["status"] = 2;
            $data["viewDatetime"] = time();
            $where["status"] = 1;
            $where["userID"] = $userID;
            return $this->where($where)->data($data)->save();
        } else {
            return false;
        }
    }

    /**
     * 获取未读消息数量
     * @param type $userID
     * @return boolean
     */
    public function getUnReadCount($userID) {
        if (isset($userID)) {
            $where["status"] = 1;
            $where["userID"] = $userID;
            return $this->where($where)->count();
        } else {
            return false;
        }
    }

    /**
     * 获取个人站内信最新时间
     * @param type $where
     */
    public function getSiteNewsTime($where) {
        $result = $this->field("newsID,status,addDatetime")->order("addDatetime DESC")->where($where)->find();
        return $result;
    }

}
