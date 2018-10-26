<?php

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

namespace Service\Backend\Logic;

use Service\Backend\Model;

class ConsoleMenuLogic extends BaseLogic {

    public $levelType = array(1 => '其他', 2 => '控制器', 3 => '方法');

    /**
     * 获取全部菜单
     * @return type
     */
    public function getAllMenu() {
        $model = new Model\ConsoleMenuModel();
        $result = $model->getAllMenu();
        $arrMenu = $this->tree($result, 0);
        return $arrMenu;
    }

    /**
     * 获取用户菜单
     * @param type $userID
     * @return type
     */
    public function getUserMenu($userID) {
        $model = new Model\ConsoleMenuModel();
        $result = $model->getUserMenu($userID);
        $arrMenu = $this->tree($result, 0);
        return $arrMenu;
    }

    /**
     * 获取全部的后台菜单
     * @param type $where
     * @param type $whereArr
     * @param type $limit
     * @return type
     */
    public function getMenu($where, $whereArr) {
        $field = "menu_id,menu_code,menu_name,menu_url,parent_id,if_right,if_display,menu_sort,level_type";
        $model = new Model\ConsoleMenuModel();
        $result = $model->getMenu("(1=1)" . $where, $whereArr, $field);
        $arrMenu = $this->tree($result, 0);
        if (empty($where)) {
            return $arrMenu;
        } else {
            return $result;
        }
    }

    /**
     * 获取要修改的后台菜单信息
     * @param type $menuID
     */
    public function getSinMenu($menuID) {
        if ($menuID * 1 < 0) {
            return false;
        }
        $where["menu_id"] = $menuID;
        $model = new Model\ConsoleMenuModel();
        $result = $model->getSinMenu($where);
        return $result;
    }

    /**
     * 保存后台菜单信息 
     * @param type $menuID
     */
    public function saveMenu($data) {
        $where = array();
        try {
            if ($data["menu_id"] * 1 > 0) {
                $data["edit_user_id"] = C("userID");
                $data["edit_User_name"] = C("userName");
                $data["edit_date_time"] = time();
                $where["menu_id"] = $data["menu_id"];
                unset($data["menu_id"]);
            } else {
                $data["add_user_id"] = C("userID");
                $data["add_user_name"] = C("userName");
                $data["add_date_time"] = time();
            }
            $logic = new Model\ConsoleMenuModel();
            $result = $logic->saveMenu($where, $data);
            return $result;
        } catch (Exception $ex) {
            logger(__METHOD__, $ex->getMessage());
            return false;
        }
    }

    /**
     * 删除后台菜单 
     * @param type $menuID
     */
    public function delMenu($menuID) {
        if ($menuID * 1 < 0) {
            return FALSE;
        }
        $where["menu_id"] = $menuID;
        $data["if_display"] = 0;
        $model = new Model\ConsoleMenuModel();
        $result = $model->saveMenu($where, $data);
        return $result;
    }

    /**
     * 获取子菜单
     * @param type $parentID
     * @return type
     */
    public function getSubMenu($parentID) {
        $model = new Model\ConsoleMenuModel();
        $result = $model->getSubMenu($parentID);
        return $result;
    }

    /**
     * 获取子菜单
     * @param type $parentID
     * @return type
     */
    public function getUserSubMenu($parentID, $userID) {
        $model = new Model\ConsoleMenuModel();
        $result = $model->getUserSubMenu($parentID, $userID);
        return $result;
    }

    /**
     * 词典管理查询
     * @param type $where
     * @param type $whereArr
     */
    public function getDic($where, $whereArr, $dicParentID = 0) {
        if (!is_array($whereArr)) {
            $whereArr = array();
        }
        $where.="AND dicStatus ='%d'";
        array_push($whereArr, 1);
        $model = new Model\ConsoleMenuModel();
        $result = $model->getDic("(1=1)" . $where, $whereArr);
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
        $model = new Model\ConsoleMenuModel();
        $result = $model->getSinDic($where);
        return $result;
    }

    /**
     * 排序树
     * @staticvar array $tree
     * @param type $result
     * @param type $pid
     * @param type $level
     * @return type
     */
    public function tree($result, $pid = 0, $level = 0) {
        $treeArr = array();
        foreach ($result as $key => $v) {
            if ($v['parent_id'] == $pid) {
                $v['sort'] = $level;
                $arr = $this->tree($result, $v['menu_id'], $level + 1);
                if (!empty($arr)) {
                    $v["children"] = $arr;
                }
                array_push($treeArr, $v);
                unset($result[$key]);
            }
        }
        return $treeArr;
    }

}
