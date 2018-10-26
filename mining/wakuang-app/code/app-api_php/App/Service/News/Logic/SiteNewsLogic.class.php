<?php

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

namespace Service\News\Logic;

use Service\News\Model\SiteNewsModel;

/**
 * Description of SiteNewsLogic
 *
 * @author Administrator
 */
class SiteNewsLogic {

    private $model = null;

    public function __construct() {
        $this->model = new SiteNewsModel();
    }

    /**
     * 获取站内消息列表。
     * @param type $where
     * @param type $whereArr
     * @param type $limit
     * @return type
     */
    public function getNewsList($where, $whereArr, $limit = "0,15") {
        return $this->model->getNewsList("(1=1)" . $where, $whereArr, $limit);
    }

    /**
     * 获取站内消息列表。
     * @param type $where
     * @param type $whereArr
     * @param type $limit
     * @return type
     */
    public function getNewsListForUserID($userID, $limit = "0,15") {
        $whereArr = array();
        $where = " userID=%d AND status>0 ";
        array_push($whereArr, $userID);
        return $this->model->getNewsList($where, $whereArr, $limit);
    }

    /**
     * 发送站内消息。
     * @param type $userID
     * @param type $type
     * @param type $data
     * @param type $remark
     */
    public function sendNews($userID, $type = "", $data = array(), $remark = "") {
        $smstArr = C('site_Message');
        if ($userID * 1 > 0 && !empty($type) && !empty($smstArr) && !empty($smstArr[$type])) {
            $smsTpl = $smstArr[$type];
            $title = $smsTpl["title"];
            $content = $smsTpl["body"];
            if (count($data) > 0) {
                $content = vsprintf($smsTpl["body"], $data);
            }
            if (trim($title) === "" || trim($content) === "" || $content == false)
                return false;
            $data["userID"] = $userID;
            $data["title"] = $title;
            $data["content"] = $content;
            $data["remark"] = $remark;
            return $this->model->addNews($data);
        }
        return false;
    }

    /**
     * 批量发送站内消息
     * @param type $type
     * @param type $data  array(array("userID"=>1,"data"=>array()))
     * @param type $remark
     * @return boolean
     */
    public function sendAllNews($type = "", $data = array(), $remark = "") {
        $smstArr = C('site_Message');
        if (!empty($type) && !empty($smstArr) && !empty($smstArr[$type])) {
            $smsTpl = $smstArr[$type];
            $title = $smsTpl["title"];
            if (trim($title) === "") {
                return false;
            }
            $datalist = array();
            foreach ($data as $key => $value) {
                if ($value["userID"] * 1 < 1)
                    continue;
                $content = $smsTpl["body"];
                if (is_array($value["data"]) && count($value["data"]) > 0) {
                    $content = vsprintf($smsTpl["body"], $value["data"]);
                }
                if (trim($content) === "" || $content == false)
                    return false;
                $datalist[] = array(
                    "userID" => $value["userID"],
                    "title" => $title,
                    "content" => $content,
                    "remark" => $remark,
                    "status" => 1,
                    "addDatetime" => time()
                );
            }
            if (count($datalist) > 0) {
                return $this->model->addSiteALL($datalist);
            }
        }
        return false;
    }

    /**
     * 发送站内信
     * @param type $entity
     * @return boolean
     */
    public function addNews($entity) {
        return $this->model->addNews($entity);
    }

    /**
     * 批量插入站内信
     * @param type $entity
     * @return type
     */
    public function addSiteALL($entity) {
        return $this->model->addSiteALL($entity);
    }

    /**
     * 查看站内信
     * @param type $newsID
     * @param type $userID
     * @return boolean
     */
    public function readSiteNews($newsID, $userID) {
        return $this->model->readSiteNews($newsID, $userID);
    }

    /**
     * 全部标记为已读。
     * @param type $userID
     * @return boolean
     */
    public function allHaveRead($userID) {
        return $this->model->allHaveRead($userID);
    }

    /**
     * 获取未读消息数量
     * @param type $userID
     * @return boolean
     */
    public function getUnReadCount($userID) {
        return $this->model->getUnReadCount($userID);
    }

    /**
     * 获取站内信最新时间
     */
    public function getNewsTime($userID) {
        if (isEmpty($userID)) {
            return false;
        }
        $where = array();
        $where["userID"] = $userID;
        $result = $this->model->getSiteNewsTime($where);
        return $result;
    }

}
