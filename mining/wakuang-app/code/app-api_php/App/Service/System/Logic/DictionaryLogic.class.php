<?php

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

namespace Service\System\Logic;

use Think\Exception;
use Service\System\Model;

/**
 * Description of DictionaryLogic
 *
 * @author DREAM
 */
class DictionaryLogic {

    /**
     * 生产html
     * @param type $parentID
     * @param type $select
     * @param type $ID
     * @return string
     */
    public function getDicHTML($parentID, $select = 0, $ID = "") {
        $result = $this->getDic($parentID, $ID);
        $html = "<option  value=''>--请选择--</option>";
        foreach ($result as $key => $value) {
            $sel = "";
            if ($select > 0 && $value["dic_id"] == $select) {
                $sel = "selected";
            }
            $bol = "";
            if ($value["dic_node"] == 3) {
                $bol = "&nbsp;&nbsp;&nbsp;&nbsp;";
            }
            if ($value["dic_node"] == 4) {
                $bol = "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
            }
            $html .= "<option " . $sel . "  value='" . $value["dic_id"] . "'>" . $bol . $value["dic_name"] . "</option>";
        }
        return $html;
    }

    /**
     * 获取到银行
     * @return  int dic_id 编码ID,
     * @return  int dic_parent_id  父编码ID,
     * @return string 节点名字 dic_name
     * @return int 节点标识 dic_node
     * @return string 节点字符 dic_key
     */
    public function getBankList() {
        $where['dic_parent_id'] = array('in',array(45,46));
        $model = new Model\SysDictionaryModel();
        $result = $model->getDicBuyID($where);
        return $result;
    }

    /**
     * 根据key来查找
     */
    public function getBankByKey($key) {
        $model =  new Model\SysDictionaryModel();
        $where['dic_key'] = $key;
        return $model->getDicBuyID($where);
    }


    /**
     * 获取字典值 按照条件In查询
     * @param type $whin
     * @return type
     */
    public function getBuyInID($whin) {
        $where['dicID'] = array('in', $whin);
        $model = new Model\SysDictionaryModel;
        $result = $model->getDicBuyID($where);
        return $result;
    }

    /**
     * 获取字典
     * @param type $parentID
     * @param string $dicID
     * @return type
     */
    public function getDic($parentID, $dicID = "") {
        $model = new Model\SysDictionaryModel;
        $result = $model->getDic();
        if (strlen($dicID) > 0) {
            $dicID = "," . $dicID;
        }
        $tree = $this->tree($result, $parentID, $dicID);
        return $tree;
    }

    /**
     * 词典管理查询
     * @param type $where
     * @param type $whereArr
     */
    public function getDicList($where, $whereArr, $dicParentID = 0) {
        if (!is_array($whereArr)) {
            $whereArr = array();
        }
        $where.="AND dic_status ='%d'";
        array_push($whereArr, 1);
        $model = new Model\SysDictionaryModel();
        $result = $model->getDicList("(1=1)" . $where, $whereArr);
        $arrDic = $this->dictree($result, $dicParentID);
        if (count($whereArr) > 1) {
            return $result;
        } else {
            return $arrDic;
        }
    }

    /**
     * 查询要修改的字典字段
     * @param type $dicID
     */
    public function getSinDic($dicID) {
        if ($dicID * 1 < 0) {
            return false;
        }
        $where["dicID"] = $dicID;
        $model = new Model\SysDictionaryModel();
        $result = $model->getSinDic($where);
        return $result;
    }

    /**
     * 保存字典 
     * @param type $data
     * @return boolean
     */
    public function saveDic($data) {
        $where = array();
        try {
            if ($data["dic_id"] * 1 > 0) {
                $where["dic_id"] = $data["dic_id"];
                $data["edit_user_id"] = C("userID");
                $data["edit_user_name"] = C("userName");
                $data["edit_datetime"] = time();
                unset($data["dic_id"]);
            } else {
                $data["add_user_id"] = C("userID");
                $data["add_user_name"] = C("userName");
                $data["add_datetime"] = time();
            }
            $model = new Model\SysDictionaryModel();
            $result = $model->saveDic($where, $data);
            return $result;
        } catch (Exception $ex) {
            logger(__METHOD__, $ex->getMessage());
            return false;
        }
    }

    /**
     * 删除字典
     * @param type $dicID
     */
    public function delDic($dicID) {
        if ($dicID * 1 < 0) {
            return false;
        }
        $where["dicID"] = $dicID;
        $data["dicStatus"] = 0;
           $model = new Model\SysDictionaryModel();
        $result = $model->saveDic($where, $data);
        return $result;
    }

    /**
     * 词典排序树
     * @staticvar array $tree
     * @param type $result
     * @param type $pid
     * @param type $level
     * @return type
     */
    public function dictree($result, $pid, $level = 0) {

        $treeArr = array();
        foreach ($result as $key => $v) {
            if ($v['dic_parent_id'] == $pid) {
                $v['sort'] = $level;
                $arr = $this->dictree($result, $v['dic_id'], $level + 1);
                if (!empty($arr)) {
                    $v["children"] = $arr;
                }
                array_push($treeArr, $v);
                unset($result[$key]);
            }
        }
        return $treeArr;
    }

    /**
     * 字典排序树
     * @staticvar array $tree
     * @param type $result
     * @param type $pid
     * @param type $dicID
     * @param type $level
     * @return type
     */
    public function tree($result, $pid = 0, $dicID = "", $level = 0) {
        $treeArr = array();
        foreach ($result as $key => $v) {
            if ($v['dic_parent_id'] == $pid) {
                $v['sort'] = $level;
                if (strlen($dicID) > 0 && strpos($dicID, $v['dic_id']) > 0) {
                    array_push($treeArr, $v);
                } elseif (strlen($dicID) == 0) {
                    array_push($treeArr, $v);
                }
                $arr = $this->tree($result, $v['dic_id'], $dicID, $level + 1);
                if (!empty($arr)) {
                    $treeArr = array_merge($treeArr, $arr);
                }
            } else {
                unset($result[$key]);
            }
        }
        return $treeArr;
    }

}
