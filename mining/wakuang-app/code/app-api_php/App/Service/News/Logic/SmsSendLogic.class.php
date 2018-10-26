<?php

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

namespace Service\News\Logic;

use Service\News\Model\SmsSendModel;
use Service\News\Model\SiteNewsModel;

/**
 * Description of AccountLogic
 *
 * @author Bourne
 */
class SmsSendLogic {

    private $model = null;

    public function __construct() {
        $this->model = new SmsSendModel();
    }

    /**
     * 发送短信
     * @param type $entity
     * @return type
     */
    public function addSend($entity) {
        $result = $this->model->addSend($entity);
        return $result;
    }

    /**
     * 批量发送短信
     * @param type $entity
     * @return type
     */
    public function addALLSend($entity) {
        $result = $this->model->addSmsALL($entity);
        return $result;
    }

    /**
     * 发送站内信
     * @param type $entity
     * @return type
     */
    public function addNews($entity) {
        $news = new SiteNewsModel();
        $result = $news->addNews($entity);
        return $result;
    }

    /**
     * 批量发送站内信
     * @param type $entity
     * @return type
     */
    public function addAllNews($entity) {
        $news = new SiteNewsModel();
        $result = $news->addSiteALL($entity);
        return $result;
    }

  

    /**
     * 短信记录查询 
     * @param type $where
     * @param type $whereArr
     * @param type $limit
     */
    public function getSms($where, $whereArr, $limit, $field) {
        $field = "receivePhone,sendContent,sendDatetime,smsType,sendInterface";
        $result = $this->model->getSms("(1=1)" . $where, $whereArr, $limit, $field);
        return $result;
    }

}
