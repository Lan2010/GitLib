<?php

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

namespace Service\News\Logic;

/**
 * 代办任务业务处理
 *
 * @author DREAM
 */
use Service\News\Model\SysTaskModel;

class SysTaskLogic {

    private $model = null;

    public function __construct() {
        $this->model = new SysTaskModel();
    }

    /**
     * 保存代办任务
     * @param type $Name 名称
     * @param type $Type 类型 还款是 2 投标是1
     * @param type $borrowNO
     * @param type $repaymentNO
     * @return type
     */
    public function addTask($Name, $Type, $borrowNO, $repaymentNO = null) {
        $task["taskName"] = $Name;
        $task["taskType"] = $Type;
        $task["borrowNO"] = $borrowNO;
        $task["repaymentNO"] = $repaymentNO;
        $result = $this->model->addTask($task);
        return $result;
    }

    /**
     * 获取未处理的代办任务
     * @return type
     */
    public function getTask() {
        $where["taskStatus"] = 0;
        $field = "taskID,taskType,borrowNO,repaymentNO";
        $result = $this->model->getTaskMore($field, $where);
        return $result;
    }

    /**
     * 处理代办
     * @return boolean
     */
    public function handleTask($id) {
        $where["taskID"] = $id;
        $data["taskStatus"] = 1;
        $data["handleDatetime"] = time();
        $result = $this->model->editTask($data, $where);
        return $result;
    }

}
