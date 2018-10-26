<?php

namespace Backend\Controller;

use Service\Charge\Logic\ChargeLogic;
use Service\Common\CollectiveLogic;
use Service\User\Logic\StarModuleLogic;
use Service\User\Logic\UserLogic;


class OperateController extends BaseEndController {

    public function starModuleList() {
        $star_logic  = new StarModuleLogic();
        $where['is_system'] = 1;
        $list = $star_logic->getEndModuleList($where);
        $this->assign("list",$list);
        $this->display("StarList");
    }

    /**
     * 修改模块的状态
     */
    public function updateStatus() {
        $type_id = I("type_id");
        $status = I("status");
        $star_logic  = new StarModuleLogic();
        $res = $star_logic->updateStatus(array('type_id'=>$type_id),$status);
        if($res) {
            $this->jsonReturn(1, "操作成功");
        } else {
            $this->jsonReturn(0, "操作失败");
        }
    }

    /**
     * 设备的列表
     */
    public function chargeList() {
        $par = I("post.");
        $where = " (1=1) ";
        if (!is_null($par["txtphone"]) && !empty($par["txtphone"])) {
            $where .= " AND u.phone='".trim($par['txtphone'])."'";
        }
        if (!is_null($par["state"]) && !empty($par["state"])) {
            $where .= " AND c.state = ".$par['state'];
        }
        if (!is_null($par["MAC"]) && !empty($par["MAC"])) {
            $where .= " AND c.device_mac = '".$par["MAC"]."'";
        }
        if (!is_null($par["deviceID"]) && !empty($par["deviceID"])) {
            $where .= " AND c.device_id = '".$par['device_id']."'";
        }
        if (!is_null($par["txtDateStart"]) && !empty($par["txtDateStart"])) {
            $where .= " AND c.bind_datetime >'".strtotime($par['txtDateStart'])."'";
        }
        if (!is_null($par["txtDateEnd"]) && !empty($par["txtDateEnd"])) {
            $where .= " AND c.bind_datetime <'".strtotime('+1 day', strtotime($par["txtDateEnd"]))."'";
        }
        $limit = $this->getPage();
        $logic = new ChargeLogic();
        $List = $logic->getChargeList($where,"c.*,IF(u.phone is NULL,0,u.phone) as phone",$limit);
        $result = $logic->getChargeList($where,"count(c.charge_id) as total");

        $this->assign("list",$List);
        $this->assign("page", $this->showPage($result[0]["total"]));
        $this->display("chargeList");
    }

    public function exCharge() {
        $par = I("post.");
        $where = " (1=1) ";
        $limit = $this->getPage();
        if (!is_null($par["txtphone"]) && !empty($par["txtphone"])) {
            $where .= " AND u.phone='".trim($par['txtphone'])."'";
        }
        if (!is_null($par["state"]) && !empty($par["state"])) {
            $where .= " AND c.state = ".$par['state'];
            $limit = "";
        }
        if (!is_null($par["MAC"]) && !empty($par["MAC"])) {
            $where .= " AND c.device_mac = '".$par["MAC"]."'";
        }
        if (!is_null($par["deviceID"]) && !empty($par["deviceID"])) {
            $where .= " AND c.device_id = '".$par['device_id']."'";
        }
        if (!is_null($par["txtDateStart"]) && !empty($par["txtDateStart"])) {
            $where .= " AND c.bind_datetime >'".strtotime($par['txtDateStart'])."'";
            $limit = "";
        }
        if (!is_null($par["txtDateEnd"]) && !empty($par["txtDateEnd"])) {
            $where .= " AND c.bind_datetime <'".strtotime('+1 day', strtotime($par["txtDateEnd"]))."'";
            $limit = "";
        }

        $logic = new ChargeLogic();
        $List = $logic->getChargeList($where,"c.*,IF(u.phone is NULL,0,u.phone) as phone",$limit);

        if (!empty($List)) {
            $expLogic = new CollectiveLogic();
            $title = array( "设备ID",  "设备类型",  "设备模式","mac","绑定状态","绑定时间","备注","用户电话");
            $fileName = "cache/chargelist_" . date("Ymdhis") . ".csv";
            $data = array();
            foreach ($List as $k => $v) {
                $data[$k]["device_id"] = iconv("UTF-8", "GBK", $v["device_id"]);
                $data[$k]["device_type"] = iconv("UTF-8", "GBK", ($v["device_type"]));
                $data[$k]["device_model"] = iconv("UTF-8", "GBK", ($v["device_model"]));
                $data[$k]["device_mac"] = iconv("UTF-8", "GBK", $v["device_mac"]);
                $v['state'] = ($v['state'] == 2)? "绑定":"未绑定";
                $data[$k]["state"] = iconv("UTF-8", "GBK", $v["state"]);
                $data[$k]["bind_datetime"] = iconv("UTF-8", "GBK", formatlongDate($v["add_datetime"]));
                $data[$k]["remark"] = iconv("UTF-8", "GBK", $v["remark"]);
                $data[$k]["phone"] = iconv("UTF-8", "GBK", $v["phone"]);
            }
            $expLogic->export($fileName, $title, $data);
            $this->jsonReturn(1, "", $fileName);
        } else {
            $this->jsonReturn(0, "没有可导出的数据！");
        }

    }


    public function importF() {
        if(empty($_FILES['file']['size'])) {
            $this->redirect('/Backend',"",3,"上传文件为空，3秒后跳转回上一个页面...");
            exit;
        }
        $data = $this->uploadImg("ImportExcel");
        $file_type = substr(strrchr($data['file']['path'], '.'), 1);
        if(!in_array(strtolower($file_type),array('csv','xlsx','xls'))) {
            $this->redirect('/Backend',"",3,"上传文件格式错误，3秒后跳转回上一个页面...");
            exit;
        }
        vendor('PHPExcel.PHPExcel');
        if($file_type == "csv") {
            $objReader = \PHPExcel_IOFactory::createReader("CSV");  //创建一个实例
        } else if($file_type == "xls") {
            $objReader = \PHPExcel_IOFactory::createReader("Excel5");  //创建一个实例
        } else {
            $objReader = \PHPExcel_IOFactory::createReader("Excel2007");  //创建一个实例
        }
        $objPHPExcel = $objReader->load($_SERVER['DOCUMENT_ROOT'].$data['file']['path'],$encode='utf-8');
        $sheet = $objPHPExcel->getSheet(0);   //第一页表格
        $rowCnt = $sheet->getHighestRow();   //获取总行数
        $data = array();
        $time = time();
        for($_row=2; $_row<=$rowCnt; $_row++){  //读取内容
            $a = $objPHPExcel->getActiveSheet()->getCell("A" . $_row)->getValue();
            $b = $objPHPExcel->getActiveSheet()->getCell("B" . $_row)->getValue();
            //获取C列的值
            $c = $objPHPExcel->getActiveSheet()->getCell("C" . $_row)->getValue();
            //获取D列的值
            $d = $objPHPExcel->getActiveSheet()->getCell("D" . $_row)->getValue();
            $e = $objPHPExcel->getActiveSheet()->getCell("E" . $_row)->getValue();
            $data [] = array('device_id' => $a, 'device_type' => $b, 'device_model' => $c,'device_mac'=>$d,'remark'=>($e)?$e:"无",'add_datetime'=>$time);
        }
        $res = M('Charge')->addAll($data);
        if($res) {
            $this->redirect('/Backend',"",3,"插入成功..");
            exit;
        } else {
            $this->redirect('/Backend',"",3,"插入失败..");
            exit;
        }
    }


    public function edit() {
        $key = I('key');
        $where = " charge_id = ".$key;
        if(!empty($key)) {
            $logic = new ChargeLogic();
            $info =$logic->getChargeList($where,"c.*,IF(u.phone is NULL,0,u.phone) as phone");
            $this->assign("data",$info[0]);
        }
        $this->display("editCharge");
    }

    public function saveCharge() {
        $par = I("post.par");
        $logic = new ChargeLogic();
        if(!empty($par['charge_id'])) {
            $where = " charge_id = ".$par['charge_id'];
            $info =$logic->getChargeList($where,"c.*,IF(u.phone is NULL,0,u.phone) as phone");
            $info = $info[0];
            //是否修改电话号码
            if($info['phone '] != $par['phone'] && !empty($par['phone'])) {
                $where = " u.phone = ".$par['phone'];
                $par_phone_info  = $logic->getChargeList($where,"u.phone,c.charge_id");
                if(!empty($par_phone_info)) {
                    $this->jsonReturn(0, "想修改的电话号码已经存在绑定设备", false);
                    exit;
                }
                $userlogic = new UserLogic();
                $userinfo = $userlogic->getByPhone($par['phone']);
                if(empty($userinfo)) {
                    $this->jsonReturn(0, "不存在该用户", false);
                    exit;
                }
                $par['bind_user_id'] = $userinfo['user_id'];
                $par['bind_datetime'] = time();
            }
        }
        $result = $logic->editOrAdd($par,$par['charge_id']);
        if ($result * 1 > 0) {
            $this->jsonReturn(1, "操作成功", $result);
        } else {
            $this->jsonReturn(0, "操作失败", $result);
        }
    }
}