<?php

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

namespace Backend\Controller;

use Service\System\Logic;

class SystemController extends BaseEndController {

    /**
     * 系统日志列表
     */
    public function getSysLog() {
        $par = I("post.");
        $where = "";
        $whereArr = array();
        if (!is_null($par["logKey"]) && !empty($par["logKey"])) {
            $where .= "AND log_key ='%s'";
            array_push($whereArr, $par["logKey"]);
        }
        if (!is_null($par["txtDateStart"]) && !empty($par["txtDateStart"])) {
            $where .= "AND add_datetime >'%s'";
            array_push($whereArr, strtotime($par["txtDateStart"]));
        }
        if (!is_null($par["txtDateEnd"]) && !empty($par["txtDateEnd"])) {
            $where .= "AND add_datetime <'%s'";
            array_push($whereArr, strtotime("+1day", strtotime($par["txtDateEnd"])));
        }
        $limit = $this->getPage();
        $logic = new Logic\SystemLogLogic();
        $result = $logic->getSysLog($where, $whereArr, $limit);
        $this->assign("result", $result["rows"]);
        $this->assign("pageHtml", $this->showPage($result["total"]));
        $this->display("syslog");
    }

    /**
     * 查看系统日志里的日志描述
     */
    public function getLogView() {
        $key = I("get.key");
        $logic = new Logic\SystemLogLogic();
        $data = $logic->getLogView($key);
        $this->assign("data", $data["log_remark"]);
        $this->display("syslogView");
    }

    /**
     * redis列表
     */
    public function getSysRedis() {
        $par = I("post.");
        if (!is_null($par["txtkey"]) && !empty($par["txtkey"])) {
            $key = $par["txtkey"];
        } else {
            $key = C("SHORT_CODE") . 'APP_Login_';
        }
        $this->key = $key;
        $logic = new Logic\RedisLogic();
        $result = $logic->getRedisList($key);
        $this->assign('redlist', $result);
        $this->display("redisList");
    }

    /**
     * 查看
     */
    public function getView() {
        $key = I("get.key");
        $logic = new Logic\RedisLogic();
        $result = $logic->getView($key);
        $this->assign('data', $result);
        $this->display("redisView");
    }

    /**
     * 删除Redis
     */
    public function delRedis() {
        $key = I("post.key");
        $logic = new Logic\RedisLogic();
        $result = $logic->delRedis($key);
        if ($result * 1 > 0) {
            $this->jsonReturn(1, "删除成功!");
        }
        $this->jsonReturn(0, "操作失败!");
    }
 
 


    /**
     * 词典管理
     */
    public function getDic() {
        $par = I("post.");
        $where = "";
        $whereArr = array();
        if (!is_null($par["txtdicName"]) && !empty($par["txtdicName"])) {
            $where .= "AND dic_name = '%s'";
            array_push($whereArr, $par["txtdicName"]);
        }
        $logic = new Logic\DictionaryLogic();
        $result = $logic->getDicList($where, $whereArr);

        $dicId = 1;
        $this->assign("dicId", $dicId);
        $this->assign("result", $result);
        $this->display("dicList");
    }

    /**
     * 修改词典
     */
    public function editDic() {
        $key = I("get.key");
        $logic = new Logic\DictionaryLogic();
        if ($key * 1 > 0) {
            $data = $logic->getSinDic($key);
            $data["edit"] = "edit";
            $this->assign("data", $data);
        }
        $dicType = $logic->getDicList();
        $this->assign("dicType", $dicType);
        $this->display("dicEdit");
    }

    /**
     * 保存词典
     */
    public function saveDic() {
        $par = I("post.par");
        if (count($par) > 0) {
            $logic = new Logic\DictionaryLogic();
            $result = $logic->saveDic($par);
            if ($result * 1 > 0) {
                $this->jsonReturn(1, "保存成功!", $result);
            }
        }
        $this->jsonReturn(0, "保存失败!");
    }

    /**
     * 删除字典 
     */
    public function delDic() {
        $key = I("post.key");
        if ($key * 1 > 0) {
            $logic = new Logic\DictionaryLogic();
            $result = $logic->delDic($key);
            if ($result * 1 > 0) {
                $this->jsonReturn(1, "删除成功!", $result);
            }
        }
        $this->jsonReturn(0, "删除失败!");
    }

    /**
     * 获取系统参数
     */
    public function getParameter() {
        $par = I("post.");
        $where = "";
        $whereArr = array();
        if (!is_null($par["parType"]) && !empty($par["parType"])) {
            $where .= "AND par_type='%s'";
            array_push($whereArr, $par["parType"]);
        }
        if (!is_null($par["parName"]) && !empty($par["parName"])) {
            $where .= "AND par_name='%s'";
            array_push($whereArr, $par["parName"]);
        }
        $limit = $this->getPage();
        $logic = new Logic\SysParameterLogic();
        $result = $logic->getParameter($where, $whereArr, $limit);
        $this->assign("result", $result["rows"]);
        $this->assign("pageHtml", $this->showPage($result["total"]));
        $this->display("parameterList");
    }

    /**
     * 修改系统参数
     */
    public function getXinParameter() {
        $key = I("get.key");
        if ($key * 1 > 0) {
            $logic = new Logic\SysParameterLogic();
            $data = $logic->getXinParameter($key);
            $this->assign("data", $data);
        }
        $this->display("parameterEdit");
    }

    /**
     * 保存系统参数
     */
    public function saveParameter() {
        $par = I("post.par");
        if (count($par) > 0) {
            $logic = new Logic\SysParameterLogic();
            $result = $logic->saveParameter($par);
            if ($result * 1 > 0) {
                $this->jsonReturn(1, "保存成功!", $result);
            }
        }
        $this->jsonReturn(0, "保存失败!");
    }

    /**
     * 删除系统参数
     */
    public function delParameter() {
        $key = I("post.key");
        if ($key * 1 > 0) {
            $logic = new Logic\SysParameterLogic();
            $result = $logic->delParameter($key);
            if ($result * 1 > 0) {
                $this->jsonReturn(1, "删除成功!", $result);
            }
        }
        $this->jsonReturn(0, "删除失败!");
    }

 

}
