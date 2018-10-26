<?php

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

namespace Backend\Controller;

use Service\User\Logic as userLogic;
use Service\Common\CollectiveLogic;


/**
 * Description of AuditController
 *
 * @author DREAM
 */
class AuditController extends BaseEndController {

    private $userColor = array(0 => "text-info", 1 => "text-warning", 5 => "text-danger", 15 => "text-success", 20 => "text-error", 21 => "text-info", 30 => "text-success", 40 => "text-danger");





    /**
     * 获取实名认证列表
     */
    public function getRealName() {
        $par = I("post.");
        $where = "";
        $whereArr = array();
        if (!is_null($par['txtorderNO']) && !empty($par["txtorderNO"])) {
            $where .= " AND order_no = '%s'";
            array_push($whereArr, $par["txtorderNO"]);
        }
        if (!is_null($par["txtuserName"]) && !empty($par["txtuserName"])) {
            $where .= " AND u.phone = '%s'";
            array_push($whereArr, $par["txtuserName"]);
        }
        if (!is_null($par["txtrealName"]) && !empty($par["txtrealName"])) {
            $where .= " AND r.real_name = '%s'";
            array_push($whereArr, aes($par["txtrealName"]));
        }
        if (!is_null($par["txtstart"]) && !empty($par["txtstart"])) {
            $where .= " AND r.add_datetime > '%d'";
            array_push($whereArr, strtotime($par["txtstart"]));
        }
        if (!is_null($par["txtend"]) && !empty($par["txtend"])) {
            $where .= " AND r.add_datetime < '%d'";
            array_push($whereArr, strtotime($par["txtend"] . ' 23:59:59'));
        }
        if (!is_null($par["auditStatus"]) && $par["auditStatus"] != "") {
            $where .= " AND r.audit_status = '%s'";
            array_push($whereArr, $par["auditStatus"]);
        }
        if (empty($where)) {//默认三天
            $where .= " AND r.add_datetime > '%d'";
            array_push($whereArr, strtotime(date('Y-m-d', strtotime('-3 day'))));
        }
        $limit = $this->getPage();
        $logic = new userLogic\RealNameLogic();
        $result = $logic->getRealname($where, $whereArr, $limit);
        $this->assign('realuser', $result["rows"]);
        $this->assign('pageHtml', $this->showPage($result["total"]));
        $this->display("realNameList");
    }

    /**
     * 实名认证导出
     */
    public function expRealName() {
        $par = I("get.");
        $where = "";
        $whereArr = array();
        if (!is_null($par['txtorderNO']) && !empty($par["txtorderNO"])) {
            $where .= " AND r.order_no = '%s'";
            array_push($whereArr, $par["txtorderNO"]);
        }
        if (!is_null($par["txtuserName"]) && !empty($par["txtuserName"])) {
            $where .= " AND u.phone = '%s'";
            array_push($whereArr, $par["txtuserName"]);
        }
        if (!is_null($par["txtrealName"]) && !empty($par["txtrealName"])) {
            $where .= " AND r.real_name = '%s'";
            array_push($whereArr, aes($par["txtrealName"]));
        }
        if (!is_null($par["txtstart"]) && !empty($par["txtstart"])) {
            $where .= " AND r.add_datetime > '%d'";
            array_push($whereArr, strtotime($par["txtstart"]));
        }
        if (!is_null($par["txtend"]) && !empty($par["txtend"])) {
            $where .= " AND r.add_datetime < '%d'";
            array_push($whereArr, strtotime($par["txtend"] . ' 23:59:59'));
        }
        if (!is_null($par["auditStatus"]) && $par["auditStatus"] != "") {
            $where .= " AND r.audit_status = '%s'";
            array_push($whereArr, $par["auditStatus"]);
        }
        if (empty($where)) {//默认三天
            $where .= " AND r.add_datetime > '%d'";
            array_push($whereArr, strtotime(date('Y-m-d', strtotime('-3 day'))));
        }
        $logic = new userLogic\RealNameLogic();
        $result = $logic->getRealname($where, $whereArr);
        $dataExcel = array();
        foreach ($result["rows"] as $value) {
            switch ($value["audit_status"]) {
                case 0:
                    $value["audit_status"] = "申请中";
                    break;
                case 1:
                    $value["audit_status"] = "审核通过";
                    break;
                case 2:
                    $value["audit_status"] = " 审核失败";
                    break;
                default:
                    $value["audit_status"] = "未知";
            }
            $rowData = array(
                $value['phone'],
                aes($value['real_name'], "DECODE"),
                $value['order_no'],
                aes($value['card_id'], "DECODE"),
                $value['user_age'],
                formatSex($value['user_sex']),
                $value['card_address'],
                $value['audit_status'],
                formatlongDate($value['add_datetime']),
                formatterminal($value['terminal'])
            );
            array_push($dataExcel, $rowData);
        }
        $title = array(
            "手机号码", "姓名", "单号", "证件号", "年龄", "性别", "证件地址", "状态", "申请时间", "终端");
        //进行导出
        $this->exportExcel($dataExcel, $title, iconv('utf-8', 'gbk', '实名认证'));
    }






    /**
     * 銀行卡
     */
    public function getBank() {
        $par = I("post.");
        $where = "";
        $whereArr = array();
        if (!is_null($par["phone"]) && !empty($par["phone"])) {
            $where .= "AND u.phone='%s' ";
            array_push($whereArr, $par["phone"]);
        }
        if (!is_null($par["txtDateStart"]) && !empty($par["txtDateStart"])) {
            $where .= "AND r.add_datetime>'%d'";
            array_push($whereArr, strtotime($par["txtDateStart"]));
        }
        if (!is_null($par["txtDateEnd"]) && !empty($par["txtDateEnd"])) {
            $where .= "AND r.add_datetime<'%d'";
            array_push($whereArr, strtotime($par["txtDateEnd"] . ' 23:59:59'));
        }
        if (!is_null($par["txtStatus"]) && !empty($par["txtStatus"])) {
            if ($par["txtStatus"] == 5) {
                $par["txtStatus"] = 0;
            }
            $where .= "AND r.status='%d'";
            array_push($whereArr, $par["txtStatus"]);
        }
        $limit = $this->getPage();
        $logic = new userLogic\RealNameLogic();
        $result = $logic->getBank($where, $whereArr, $limit);
        $this->assign("result", $result["rows"]);
        $this->assign("page", $this->showPage($result["total"]));
        $this->display("banklist");
    }

    /**
     * 银行卡绑定导出
     */
    public function expBank() {
        $par = I("get.");
        $where = "";
        $whereArr = array();

        if (!is_null($par["phone"]) && !empty($par["phone"])) {
            $where .= "AND B.phone='%s' ";
            array_push($whereArr, $par["phone"]);
        }
        if (!is_null($par["txtDateStart"]) && !empty($par["txtDateStart"])) {
            $where .= "AND A.add_datetime>'%d'";
            array_push($whereArr, strtotime($par["txtDateStart"]));
        }
        if (!is_null($par["txtDateEnd"]) && !empty($par["txtDateEnd"])) {
            $where .= "AND A.add_datetime<'%d'";
            array_push($whereArr, strtotime($par["txtDateEnd"] . ' 23:59:59'));
        }
        $logic = new userLogic\RealNameLogic();
        $result = $logic->getBank($where, $whereArr);
        $dataExcel = array();
        $title = array("手机号码", "卡号", "银行", "终端", "绑卡时间");
        foreach ($result["rows"] as $k => $v) {
            $rowData = array(
                $v["phone"],
                $v["bank_card_no"],
                $v["branch"],
                formatterminal($v["terminal"]),
                formatlongDate($v["add_datetime"]),
            );
            array_push($dataExcel, $rowData);
        }
        //进行导出
        $this->exportExcel($dataExcel, $title, iconv('utf-8', 'gbk', '银行卡绑定'));
    }



    /**
     * 异步上传
     */
    public function saveGrant() {
        $hdtype = $_REQUEST["hdtype"];
        $explain = $_REQUEST["explain"];
        $fileName = $_FILES["file"]["name"];
        $filePsn = $_FILES["file"]["tmp_name"];
        if (empty($hdtype)) {
            $this->jsonReturn(0, "请选择奖励类型！");
        }
        if (empty($explain)) {
            $this->jsonReturn(0, "请输入发放说明！");
        }
        $logic = new CollectiveLogic();
        $fileFmt = $logic->checkFile($fileName);
        if (!empty($fileFmt)) {
            $result = $logic->importExcel($filePsn, $fileFmt, false);
            $data = array();
            $totalmoney = 0;
            foreach ($result as $key => $value) {
                if (empty($value["A"]) || !verify_phone($value["A"])) {
                    $this->jsonReturn(0, "第" . $key . "行手机号码格式不正确！");
                    exit;
                }
                if (empty($value["B"]) || !is_numeric($value["B"]) || $value["B"] * 1 <= 0) {
                    $this->jsonReturn(0, "第" . ($key + 1) . "行金额格式格式不正确！");
                    exit;
                }
                $money = pointMoney(trim($value["B"]));
                $totalmoney += $money;
                $data[] = array(
                    'phone' => trim($value["A"]),
                    'grantMoney' => $money,
                    'hdtype' => $hdtype,
                    'explain' => $explain,
                    'status' => 1,
                    'addDatetime' => time()
                );
            }
            if (count($data) == 0) {
                $this->jsonReturn(0, "没有任何需要导入的数据！");
            }
            $logic = new HdGrantLogic();
            $result = $logic->addGrantAll($data);
            if ($result * 1 > 0) {
                $this->jsonReturn(1, "导入成功,共计：" . count($data) . "条记录，" . $totalmoney . "元", $result);
            }
            $this->jsonReturn(0, "导入失败!" . $result);
        } else {
            $this->jsonReturn(0, "上传失败请检查文件格式是否正确！");
        }
    }

    /**
     * 发放奖励
     */
    public function handGrant() {
        $par = I("post.par");
        $logic = new HdGrantLogic();
        $result = $logic->handedGrant($par["grantID"], C("userID"), C("realName"));
        if ($result * 1 > 0) {
            $this->jsonReturn(1, "操作成功", $result);
        }
        $this->jsonReturn(0, "操作失败!" . $result);
    }



}
