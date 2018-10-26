<?php

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

namespace Backend\Controller;

use Service\User\Logic;
use Service\Backend\Logic\ConsoleUserLogic;
use Service\System\Logic as sys;
use Service\Common\CollectiveLogic;

class UserController extends BaseEndController {

    /**
     * 获取用户信息
     */
    public function getUser() {
        $par = I("post.");
        $where = "";
        $whereArr = array();
        if (!is_null($par["txtphone"]) && !empty($par["txtphone"])) {
            $where .= "AND A.phone='%s'";
            array_push($whereArr, $par["txtphone"]);
        }

        if (!is_null($par["txtDateStart"]) && !empty($par["txtDateStart"])) {
            $where .= "AND A.reg_datetime>'%d'";
            array_push($whereArr, strtotime($par["txtDateStart"]));
        }
        if (!is_null($par["txtDateEnd"]) && !empty($par["txtDateEnd"])) {
            $where .= "AND A.reg_datetime<'%d'";
            array_push($whereArr, strtotime('+1 day', strtotime($par["txtDateEnd"])));
        }
        if (empty($where)) {
            $where .= "AND A.reg_datetime>'%d'";
            $startDate = date('Y-m-d', strtotime('-7 days'));
            array_push($whereArr, strtotime($startDate));
            $_POST["txtDateStart"] = $startDate;
            $_POST["txtDateEnd"] = date('Y-m-d', time());
        }
        $limit = $this->getPage();
        $logic = new Logic\UserLogic();
        $result = $logic->getUserList($where, $whereArr, $limit);
        $this->assign("result", $result["rows"]);
        $this->assign("page", $this->showPage($result["total"]));
        $this->display("userList");
    }



    /**
     * 导出excel
     */
    public function exportUserInfo() {
        $dir = "cache/";
        $fileName = $dir . "UserInfoList_" . date('Ymdhis') . ".csv";
        $par = I("post.par");
        $where = "";
        $whereArr = array();
        if (!is_null($par["txtphone"]) && !empty($par["txtphone"])) {
            $where .= " AND A.phone='%s' ";
            array_push($whereArr, $par["txtphone"]);
        }

        if (!is_null($par["txtDateStart"]) && !empty($par["txtDateStart"])) {
            $where .= " AND A.reg_datetime>%d ";
            array_push($whereArr, strtotime($par["txtDateStart"]));
        }
        if (!is_null($par["txtDateEnd"]) && !empty($par["txtDateEnd"])) {
            $where .= " AND A.reg_datetime<%d ";
            array_push($whereArr, strtotime('+1 day', strtotime($par["txtDateEnd"])));
        }
        if (empty($where)) {
            $where .= " AND A.reg_datetime>%d ";
            array_push($whereArr, strtotime(date('Y-m-d', strtotime('-7 days'))));
        }
        $limit = "0,50000";
        $logic = new Logic\UserLogic();
        $result = $logic->getUserList($where, $whereArr, $limit);

        if (empty($result['rows'])) {
            $this->jsonReturn(0, "导出失败,没有所要导出的数据");
        }
        $title = array(
            "电话",
            "客户状态",
            "注册时间",
            "终端",
        );
        $data = "";
        $k = 0;
        foreach ($result["rows"] as $val) {
            $data[$k][] = $val["phone"];
            $data[$k][] = $val["user_status"] == 1 ? "正常" : "不正常";
            $data[$k][] = formatlongDate($val["reg_datetime"]);
            $data[$k][] = formatterminal($val["reg_terminal"]);
            $k++;
        }
        $fp = fopen($fileName, "a+");
        //添加表头
        foreach ($title as $key => $item) {
            $title[$key] = iconv('utf-8', 'GBK', $item);
        }
        fputcsv($fp, $title);
        $arr = array();
        foreach ($data as $value) {
            foreach ($value as $keys => $info) {
                $arr[$keys] = iconv('utf-8', 'GBK', $info);
            }
            fputcsv($fp, $arr);
        }
        fclose($fp);
        $this->jsonReturn(1, "导出成功，请点击Dowonload下载", $fileName);
    }





    /**
     * 用户登录查询
     */
    public function getLogin() {
        $par = I("post.");
        $where = "";
        $whereArr = array();
        if (!is_null($par["txtphone"]) && !empty($par["txtphone"])) {
            $where .= "AND B.phone='%s'";
            array_push($whereArr, $par["txtphone"]);
        }
        if (!is_null($par["txtDateStart"]) && !empty($par["txtDateStart"])) {
            $where .= "AND A.last_login_datetime>'%d'";
            array_push($whereArr, strtotime($par["txtDateStart"]));
        }
        if (!is_null($par["txtDateEnd"]) && !empty($par["txtDateEnd"])) {
            $where .= "AND A.last_login_datetime<'%d'";
            array_push($whereArr, strtotime('+1 day', strtotime($par["txtDateEnd"])));
        }

        if (!is_null($par["second"]) && !empty($par["second"])) {
            if ($par["second"] == 1) {
                $where .= "AND A.login_times>='%s'";
                array_push($whereArr, $par["second"]);
                $where .= "AND A.login_times<'%s'";
                array_push($whereArr, 3);
            }
            if ($par["second"] == 3) {
                $where .= "AND A.login_times>='%s'";
                array_push($whereArr, $par["second"]);
                $where .= "AND A.login_times<'%s'";
                array_push($whereArr, 5);
            }
            if ($par["second"] == 5) {
                $where .= "AND A.login_times>='%s'";
                array_push($whereArr, $par["second"]);
                $where .= "AND A.login_times<'%s'";
                array_push($whereArr, 8);
            }
            if ($par["second"] == 8) {
                $where .= "AND A.login_times>='%s'";
                array_push($whereArr, $par["second"]);
            }
        }

        if (empty($where)) {//默认查询一个星期的短信验证码
            $where .= "AND A.last_login_datetime>'%d'";
            array_push($whereArr, strtotime("-7 day"));
        }
        $limit = $this->getPage();
        $logic = new Logic\UserLogLogic();
        $result = $logic->getUserLog($where, $whereArr, $limit);
        $this->assign("result", $result["rows"]);
        $this->assign("page", $this->showPage($result["total"]));
        $this->display("loginLog");
    }

    /**
     * 数据导出
     */
    public function exportLogin() {
        $par = I("post.data");
        $where = "";
        $whereArr = array();
        if (!is_null($par["txtphone"]) && !empty($par["txtphone"])) {
            $where .= "AND A.phone='%s'";
            array_push($whereArr, $par["txtphone"]);
        }
        if (!is_null($par["txtDateStart"]) && !empty($par["txtDateStart"])) {
            $where .= "AND A.last_login_datetime>'%d'";
            array_push($whereArr, strtotime($par["txtDateStart"]));
        }
        if (!is_null($par["txtDateEnd"]) && !empty($par["txtDateEnd"])) {
            $where .= "AND A.last_login_datetime<'%d'";
            array_push($whereArr, strtotime('+1 day', strtotime($par["txtDateEnd"])));
        }

        if (!is_null($par["second"]) && !empty($par["second"])) {
            if ($par["second"] == 1) {
                $where .= "AND A.login_times>='%s'";
                array_push($whereArr, $par["second"]);
                $where .= "AND A.login_times<'%s'";
                array_push($whereArr, 3);
            }
            if ($par["second"] == 3) {
                $where .= "AND A.login_times>='%s'";
                array_push($whereArr, $par["second"]);
                $where .= "AND A.login_times<'%s'";
                array_push($whereArr, 5);
            }
            if ($par["second"] == 5) {
                $where .= "AND A.login_times>='%s'";
                array_push($whereArr, $par["second"]);
                $where .= "AND A.login_times<'%s'";
                array_push($whereArr, 8);
            }
            if ($par["second"] == 8) {
                $where .= "AND A.login_times>='%s'";
                array_push($whereArr, $par["second"]);
            }
        }

        if (empty($where)) {//默认查询一个星期的短信验证码
            $where .= "AND A.last_login_datetime>'%d'";
            array_push($whereArr, strtotime("-7 day"));
        }
        $limit = "0,50000";
        $logic = new Logic\UserLogLogic();
        $result = $logic->getUserLog($where, $whereArr, $limit);
        if (empty($result["rows"])) {
            $this->jsonReturn(0, "导出失败，没有数据！");
        }
        $title = array("手机号",  "登录次数", "手机次数", "微信次数", "PC次数", "最后IP", "最后登录时间");
        $fileName = "cache/LoginLog_" . date("Ymdhis") . ".csv";
        $fp = fopen($fileName, "a+");
        $arr = array();
        $data = array();
        $mes = array();
        foreach ($title as $v) {
            $arr[] = iconv("UTF-8", "GBK", $v);
        }
        fputcsv($fp, $arr);
        foreach ($result["rows"] as $k => $val) {
            $data[$k]["phone"] = iconv("UTF-8", "GBK", $val["phone"]);
            $data[$k]["loginTimes"] = iconv("UTF-8", "GBK", $val["login_times"]);
            $data[$k]["mobileTimes"] = iconv("UTF-8", "GBK", $val["mobile_times"]);
            $data[$k]["weChatTimes"] = iconv("UTF-8", "GBK", $val["wechat_times"]);
            $data[$k]["pcTimes"] = iconv("UTF-8", "GBK", $val["pc_times"]);
            $data[$k]["lastLoginIP"] = iconv("UTF-8", "GBK", $val["last_login_ip"]);
            $data[$k]["lastLoginDatetime"] = iconv("UTF-8", "GBK", formatlongDate($val["last_login_datetime"]));
        }
        foreach ($data as $l) {
            foreach ($l as $key => $m) {
                $mes[$key] = $m;
            }
            fputcsv($fp, $mes);
        }
        fclose($fp);
        $this->jsonReturn(1, "导出成功请点击Dowonload下载", $fileName);
    }









    /**
     * 获取邀请记录
     */
    public function getInvite() {
        $par = I("post.");
        $where = "";
        $whereArr = array();
        if (!is_null($par["txtphone"]) && !empty($par["txtphone"])) {
            $where .= "AND B.phone='%s'";
            array_push($whereArr, $par["txtphone"]);
        }
        if (!is_null($par["txtDateStart"]) && !empty($par["txtDateStart"])) {
            $where .= "AND A.add_datetime>'%d'";
            array_push($whereArr, strtotime($par["txtDateStart"]));
        }
        if (!is_null($par["txtDateEnd"]) && !empty($par["txtDateEnd"])) {
            $where .= "AND A.add_datetime<'%d'";
            array_push($whereArr, strtotime('+1 day', strtotime($par["txtDateEnd"])));
        }
        if (!is_null($par["rewardType"]) && !empty($par["rewardType"])) {
            $where .= "AND A.reward_type='%s'";
            array_push($whereArr, $par["rewardType"]);
        }
        if (!is_null($par["yqphone"]) && !empty($par["yqphone"])) {
            $where .= "AND C.phone='%s'";
            array_push($whereArr, $par["yqphone"]);
        }
        if (empty($where)) {//默认查询一个星期的短信验证码
            $where .= "AND A.add_datetime>'%d'";
            array_push($whereArr, strtotime("-7 day"));
        }
        $logicsys = new sys\DictionaryLogic();
        $htmlType = $logicsys->getDicHTML(106, $par["rewardType"]);
        $limit = $this->getPage();
        $logic = new Logic\UserInviteLogic();
        $result = $logic->getInvite($where, $whereArr, $limit);
        $this->assign("result", $result["rows"]);
        $this->assign("page", $this->showPage($result["total"]));
        $this->assign("html", $htmlType);
        if (C("userID") * 1 == 7 || C("userID") * 1 == 20) {
            $this->assign("showadd", 1);
        }
        $this->display("inviteList");
    }



    /**
     * 导出邀请记录
     */
    public function expInvite() {
        $par = I("post.data");
        $where = "";
        $whereArr = array();
        if (!is_null($par["txtphone"]) && !empty($par["txtphone"])) {
            $where .= "AND B.phone='%s'";
            array_push($whereArr, $par["txtphone"]);
        }
        if (!is_null($par["txtDateStart"]) && !empty($par["txtDateStart"])) {
            $where .= "AND A.add_datetime>'%d'";
            array_push($whereArr, strtotime($par["txtDateStart"]));
        }
        if (!is_null($par["txtDateEnd"]) && !empty($par["txtDateEnd"])) {
            $where .= "AND A.add_datetime<'%d'";
            array_push($whereArr, strtotime('+1 day', strtotime($par["txtDateEnd"])));
        }
        if (!is_null($par["rewardType"]) && !empty($par["rewardType"])) {
            $where .= "AND A.reward_type='%s'";
            array_push($whereArr, $par["rewardType"]);
        }
        if (!is_null($par["yqphone"]) && !empty($par["yqphone"])) {
            $where .= "AND C.phone='%s'";
            array_push($whereArr, $par["yqphone"]);
        }
        if (empty($where)) {//默认查询一个星期的短信验证码
            $where .= "AND A.add_datetime>'%d'";
            array_push($whereArr, strtotime("-7 day"));
        }
        $logic = new Logic\UserInviteLogic();
        $limit = "0,50000";
        $result = $logic->getInvite($where, $whereArr, $limit);
        if (!empty($result)) {
            $expLogic = new CollectiveLogic();
            $title = array( "被邀请人电话",  "邀请人电话",  "邀请时间");
            $fileName = "cache/Invite_" . date("Ymdhis") . ".csv";
            $data = array();
            foreach ($result["rows"] as $k => $v) {
                $data[$k]["phone"] = iconv("UTF-8", "GBK", $v["phone"]);
                $data[$k]["inviteUserPhone"] = iconv("UTF-8", "GBK", ($v["invite_user_phone"]));
                $data[$k]["addDatetime"] = iconv("UTF-8", "GBK", formatlongDate($v["add_datetime"]));
            }
            $expLogic->export($fileName, $title, $data);
            $this->jsonReturn(1, "", $fileName);
        } else {
            $this->jsonReturn(0, "没有可导出的数据！");
        }
    }





    /**
     * 用户上传文件
     */
    public function getUserUpload() {
        $par = I("post.");
        $where = " (1=1) ";
        if (!is_null($par["txtphone"]) && !empty($par["txtphone"])) {
            $where .= " AND u.phone='".trim($par['txtphone'])."'";
        }
        if (!is_null($par["txtDateStart"]) && !empty($par["txtDateStart"])) {
            $where .= " AND us.add_datetime>'".strtotime($par['txtDateStart'])."'";
        }
        if (!is_null($par["type"]) && !empty($par["type"])) {
            $where .= " AND us.type = '".$par['type']."'";
        }
        if (!is_null($par["txtDateEnd"]) && !empty($par["txtDateEnd"])) {
            $where .= " AND us.add_datetime<'".strtotime('+1 day', strtotime($par["txtDateEnd"]))."'";
        }
        $limit = $this->getPage();
        $logic = new Logic\UserLogic();
        $List = $logic->getUserUpload($where,"path,phone,add_detatime,type,name",$limit);
        $result = $logic->getUserUpload($where,"count(*) as total");
        $this->assign("list",$List);
        $this->assign("page", $this->showPage($result[0]["total"]));
        $this->display("uploadList");
    }

    /**
     * 基因
     */
    public function gene() {
        $par = I("post.");
        $where = " (1=1) ";
        if (!is_null($par["txtphone"]) && !empty($par["txtphone"])) {
            $where .= "AND u.phone='".trim($par['txtphone'])."'";
        }
        if (!is_null($par["is_illness"]) && !empty($par["is_illness"])) {
            $d = ($par['is_illness'] == 1)?  1:0;
            $where .= "AND g.is_illness =".$d." ";
        }
        if (!is_null($par["sex"]) && !empty($par["sex"])) {
            $d = ($par['sex'] == 1)?  1:0;
            $where .= "AND g.sex =" .$d ."  ";
        }
        if (!is_null($par["txtDateStart"]) && !empty($par["txtDateStart"])) {
            $where .= "AND g.birth_date >'".strtotime($par['txtDateStart'])."' ";
        }
        $limit = $this->getPage();
        $logic = new Logic\GeneLogic();
        $List = $logic->getGeneList($where,"g.*,u.phone",$limit);
        $result = $logic->getGeneList($where,"count(u.phone) as total");
        $this->assign("list",$List);
        $this->assign("page", $this->showPage($result[0]["total"]));
        $this->display("geneList");
    }


    public function exgene() {
        $par = I("post.");
        $where = " (1=1) ";
        if (!is_null($par["txtphone"]) && !empty($par["txtphone"])) {
            $where .= "AND u.phone='".trim($par['txtphone'])."'";
        }
        if (!is_null($par["is_illness"]) && !empty($par["is_illness"])) {
            $d = ($par['is_illness'] == 1)?  1:0;
            $where .= "AND g.is_illness =".$d." ";
        }
        if (!is_null($par["sex"]) && !empty($par["sex"])) {
            $d = ($par['sex'] == 1)?  1:0;
            $where .= "AND g.sex =" .$d ."  ";
        }
        if (!is_null($par["txtDateStart"]) && !empty($par["txtDateStart"])) {
            $where .= "AND g.birth_date >'".strtotime($par['txtDateStart'])."' ";
        }
        $limit = $this->getPage();
        $logic = new Logic\GeneLogic();
        $List = $logic->getGeneList($where,"g.*,u.phone",$limit);

        if (!empty($List)) {
            $expLogic = new CollectiveLogic();
            $title = array( "用户电话",  "姓名",  "遗传病史","性别","籍贯","生日","运动时长","认证时间");
            $fileName = "cache/genelist_" . date("Ymdhis") . ".csv";
            $data = array();
            foreach ($List as $k => $v) {
                $data[$k]["phone"] = iconv("UTF-8", "GBK", $v["phone"]);
                $data[$k]["name"] = iconv("UTF-8", "GBK", ($v["name"]));
                $str = ($v['is_illness'] == 1)? "具备":"无";
                $data[$k]["is_illness"] = iconv("UTF-8", "GBK", $str);
                $str = ($v['sex'] == 1)? "男":"女";
                $data[$k]["sex"] = iconv("UTF-8", "GBK", $str);
                $data[$k]["native_place"] = iconv("UTF-8", "GBK", $v['native_place']);
                $data[$k]["birth_date"] = iconv("UTF-8", "GBK", formatlongDate($v["birth_date"]));
                $data[$k]["exercise_time"] = iconv("UTF-8", "GBK", $v['exercise_time'].'/秒');
                $data[$k]["add_datetime"] = iconv("UTF-8", "GBK",formatlongDate($v["add_datetime"]));
            }
            $expLogic->export($fileName, $title, $data);
            $this->jsonReturn(1, "", $fileName);
        } else {
            $this->jsonReturn(0, "没有可导出的数据！");
        }
    }



    /**
     * 用户地址
     */
    public function getAddress() {
        $par = I("post.");
        $where = " (1=1) ";
        if (!is_null($par["txtphone"]) && !empty($par["txtphone"])) {
            $where .= "AND u.phone='".trim($par['txtphone'])."'";
        }
        if (!is_null($par["province"]) && !empty($par["province"])) {
            $where .= "AND ua.province_name like '%".trim($par['province'])."%'";
        }
        if (!is_null($par["city"]) && !empty($par["city"])) {
            $where .= "AND ua.city_name like '%".trim($par['city'])."%'";
        }
        if (!is_null($par["district"]) && !empty($par["district"])) {
            $where .= "AND ua.district_name like '%".trim($par['district'])."%'";
        }
        if (!is_null($par["txtDateStart"]) && !empty($par["txtDateStart"])) {
            $where .= "AND us.add_datetime>'".strtotime($par['txtDateStart'])."'";
        }
        if (!is_null($par["txtDateEnd"]) && !empty($par["txtDateEnd"])) {
            $where .= "AND ua.add_datetime<'".strtotime('+1 day', strtotime($par["txtDateEnd"]))."'";
        }
        $limit = $this->getPage();
        $logic = new Logic\UserAddressLogic();
        $List = $logic->getAddressList($where,"ua.*,u.phone",$limit);
        $result = $logic->getAddressList($where,"count(u.phone) as total");
        $this->assign("list",$List);
        $this->assign("page", $this->showPage($result[0]["total"]));
        $this->display("addressList");
    }


    /**
     * 用户通讯录
     */
    public function getMaileList() {

        $par = I("post.");
        $where = " (1=1) ";
        if (!is_null($par["txtphone"]) && !empty($par["txtphone"])) {
            $where .= "AND u.phone='".trim($par['txtphone'])."'";
        }
        $limit = $this->getPage();
        $logic = new Logic\userMaillistLogic();
        $List = $logic->getMailList($where,"um.phone as mail_phone,u.phone,name,add_datetime",$limit);
        $result = $logic->getMailList($where,"count(*) as total");
        $this->assign("list",$List);
        $this->assign("page", $this->showPage($result[0]["total"]));
        $this->display("mailList");
    }


    public function extMaillist()
    {

        $par = I("post.data");
        $where = " (1=1) ";
        $limit = $this->getPage();
        if (!is_null($par["txtphone"]) && !empty($par["txtphone"])) {
            $where .= "AND u.phone='".trim($par['txtphone'])."'";
            $limit="";
        }
        $logic = new Logic\userMaillistLogic();
        $List = $logic->getMailList($where,"um.phone as mail_phone,u.phone,name,add_datetime",$limit);

        if (!empty($List)) {
            $expLogic = new CollectiveLogic();
            $title = array( "用户电话",  "通讯录电话",  "通讯录姓名","添加时间");
            $fileName = "cache/Maillist_" . date("Ymdhis") . ".csv";
            $data = array();
            foreach ($List as $k => $v) {
                $data[$k]["phone"] = iconv("UTF-8", "GBK", $v["phone"]);
                $data[$k]["mail_phone"] = iconv("UTF-8", "GBK", ($v["mail_phone"]));
                $data[$k]["name"] = iconv("UTF-8", "GBK", ($v["name"]));
                $data[$k]["add_datetime"] = iconv("UTF-8", "GBK", formatlongDate($v["add_datetime"]));
            }
            $expLogic->export($fileName, $title, $data);
            $this->jsonReturn(1, "", $fileName);
        } else {
            $this->jsonReturn(0, "没有可导出的数据！");
        }
    }

    public function expaddress() {
        $par = I("post.data");
        $where = " (1=1) ";
        if (!is_null($par["txtphone"]) && !empty($par["txtphone"])) {
            $where .= "AND u.phone='".trim($par['txtphone'])."'";
        }
        if (!is_null($par["province"]) && !empty($par["province"])) {
            $where .= "AND ua.province_name like '%".trim($par['province'])."%'";
        }
        if (!is_null($par["city"]) && !empty($par["city"])) {
            $where .= "AND ua.city_name like '%".trim($par['city'])."%'";
        }
        if (!is_null($par["district"]) && !empty($par["district"])) {
            $where .= "AND ua.district_name like '%".trim($par['district'])."%'";
        }
        if (!is_null($par["txtDateStart"]) && !empty($par["txtDateStart"])) {
            $where .= "AND us.add_datetime>'".strtotime($par['txtDateStart'])."'";
        }
        if (!is_null($par["txtDateEnd"]) && !empty($par["txtDateEnd"])) {
            $where .= "AND ua.add_datetime<'".strtotime('+1 day', strtotime($par["txtDateEnd"]))."'";
        }
        $limit = $this->getPage();
        $logic = new Logic\UserAddressLogic();

        $List = $logic->getAddressList($where,"ua.*,u.phone",$limit);

        if (!empty($List)) {
            $expLogic = new CollectiveLogic();
            $title = array( "用户电话",  "省份",  "城市","街区","地址","工作单位","工作地址");
            $fileName = "cache/address_" . date("Ymdhis") . ".csv";
            $data = array();
            foreach ($List as $k => $v) {
                $data[$k]["phone"] = iconv("UTF-8", "GBK", $v["phone"]);
                $data[$k]["province_name"] = iconv("UTF-8", "GBK", ($v["province_name"]));
                $data[$k]["city_name"] = iconv("UTF-8", "GBK", ($v["city_name"]));
                $data[$k]["district_name"] = iconv("UTF-8", "GBK", ($v["district_name"]));
                $data[$k]["address"] = iconv("UTF-8", "GBK", ($v["address"]));
                $data[$k]["work_unit"] = iconv("UTF-8", "GBK", ($v["work_unit"]));
                $data[$k]["work_address"] = iconv("UTF-8", "GBK", ($v["work_address"]));
                $data[$k]["add_datetime"] = iconv("UTF-8", "GBK", formatlongDate($v["add_datetime"]));
            }
            $expLogic->export($fileName, $title, $data);
            $this->jsonReturn(1, "", $fileName);
        } else {
            $this->jsonReturn(0, "没有可导出的数据！");
        }



    }



}
