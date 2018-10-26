<?php

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

namespace Backend\Controller;

use Service\News\Logic;
use Service\System\Logic as sys;
use Service\User\Logic as server;
use Service\Account\Logic\AccountLogic;
use Service\Backend\Logic\ConsoleUserLogic;
use Service\Spread\Logic\AppInfoLogic;
use Service\Common\CollectiveLogic;

/**
 * Description of ServiceController
 *
 * @author DREAM
 */
class ServiceController extends BaseEndController {



    /**
     * 短信记录查询
     */
    public function getSms() {
        $par = I("post.");
        $where = "";
        $whereArr = array();
        if (!is_null($par["phone"]) && !empty($par["phone"])) {
            $where .= "AND receivePhone='%s'";
            array_push($whereArr, $par["phone"]);
        }
        if (!is_null($par["dateStart"]) && !empty($par["dateStart"])) {
            $where .= "AND sendDatetime>'%d'";
            array_push($whereArr, strtotime($par["dateStart"]));
        }
        if (!is_null($par["dateEnd"]) && !empty($par["dateEnd"])) {
            $where .= "AND sendDatetime < '%d'";
            array_push($whereArr, strtotime("+1day", strtotime($par["dateEnd"])));
        }
        if (empty($where)) {//默认查询一个星期的短信记录
            $where .= "AND sendDatetime > '%d'";
            array_push($whereArr, strtotime(date('Y-m-d', strtotime("-7 day"))));
        }
        $limit = $this->getPage();
        $logic = new Logic\SmsSendLogic();
        $result = $logic->getSms($where, $whereArr, $limit);
        $this->assign("result", $result["rows"]);
        $this->assign("page", $this->showPage($result["total"]));
        $this->display("smsList");
    }

    /**
     * 验证码查询
     */
    public function getCode() {
        $par = I("post.");
        $where = "";
        $whereArr = array();
        if (!is_null($par["phone"]) && !empty($par["phone"])) {
            $where .= "AND receivePhone='%s'";
            array_push($whereArr, $par["phone"]);
        }
        if (!is_null($par["dateStart"]) && !empty($par["dateStart"])) {
            $where .= "AND addDatetime>'%d'";
            array_push($whereArr, strtotime($par["dateStart"]));
        }
        if (!is_null($par["dateEnd"]) && !empty($par["dateEnd"])) {
            $where .= "AND addDatetime<'%d'";
            array_push($whereArr, strtotime('+1 day', strtotime($par["dateEnd"])));
        }
        if (empty($where)) {//默认查询一个星期的短信验证码
            $where .= "AND addDatetime>'%d'";
            array_push($whereArr, strtotime(date('Y-m-d', strtotime("-7 day"))));
        }
        $limit = $this->getPage();
        $logic = new Logic\SmsVerifyLogic();
        $result = $logic->getCode($where, $whereArr, $limit);
        $this->assign("result", $result["rows"]);
        $this->assign("page", $this->showPage($result["total"]));
        $this->display("codeList");
    }

















    /**
     * 意见反馈
     */
    public function getProp() {
        $where = "";
        $whereArr = array();
        $par = I("post.");
        if (!is_null($par["phone"]) && !empty($par["phone"])) {
            $where .= "AND phone='%s'";
            array_push($whereArr, $par["phone"]);
        }
        $limit = $this->getPage();
        $logic = new AppInfoLogic();
        $result = $logic->getFeback($where, $whereArr, $limit);
        $this->assign("result", $result["rows"]);
        $this->assign("pageHtml", $this->showPage($result["total"]));
        $this->display("prop");
    }

    /**
     * 导出意见反馈
     */
    public function expProp() {
        $where = "";
        $whereArr = array();
        $par = I("post.data");
        if (!is_null($par["phone"]) && !empty($par["phone"])) {
            $where .= "AND phone='%s'";
            array_push($whereArr, $par["phone"]);
        }
        $limit = "0,50000";
        $logic = new AppInfoLogic();
        $result = $logic->getFeback($where, $whereArr, $limit);
        if (!empty($result["rows"])) {
            $expLogic = new CollectiveLogic();
            $title = array("电话", "用户反馈的信息", "终端", "APP版本", "反馈时间", "已处理", "处理人", "处理时间");
            $fileName = "cache/Prop_" . date("Ymdhis") . ".csv";
            $data = array();
            foreach ($result["rows"] as $k => $v) {
                $data[$k]["phone"] = iconv("UTF-8", "GBK", $v["phone"]);
                $data[$k]["febackInfo"] = iconv("UTF-8", "GBK", $v["feback_info"]);
                $data[$k]["terminal"] = iconv("UTF-8", "GBK", formatterminal($v["terminal"]));
                $data[$k]["versions"] = iconv("UTF-8", "GBK", ($v["versions"]));
                $data[$k]["addDatetime"] = iconv("UTF-8", "GBK", formatlongDate($v["add_datetime"]));
                switch ($v["status"]) {
                    case 0 :
                        $data[$k]["status"] = iconv("UTF-8", "GBK", "否");
                        break;
                    case 1 :
                        $data[$k]["status"] = iconv("UTF-8", "GBK", "是");
                        break;
                    default :
                        $data[$k]["status"] = iconv("UTF-8", "GBK", "未知");
                        break;
                }
                $data[$k]["editUserName"] = iconv("UTF-8", "GBK", ($v["edit_user_name"]));
                $data[$k]["editDatetime"] = iconv("UTF-8", "GBK", formatlongDate($v["edit_datetime"]));
            }
            $expLogic->export($fileName, $title, $data);
            $this->jsonReturn(1, "", $fileName);
        } else {
            $this->jsonReturn(1, "没有可导出的数据");
        }
    }

    /**
     * 跟进用户反馈的信息
     */
    public function editProp() {
        $key = I("get.key");
        if (!empty($key)) {
            $logic = new AppInfoLogic();
            $result = $logic->getProp($key);
            $this->assign("data", $result);
        }
        $this->display("editProp");
    }

    /**
     * 跟进用户反馈的信息
     */
    public function saveProp() {
        $par = I("post.par");
        if (empty($par["remark"])) {
            $this->jsonReturn(0, "您还没有填写处理信息");
        }
        $logic = new AppInfoLogic();
        $result = $logic->saveProp($par["remark"], $par["febackID"]);
        if (!empty($result)) {
            $this->jsonReturn(1, "处理成功");
        } else {
            $this->jsonReturn(0, "处理出现异常！");
        }
    }



}
