<?php

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

namespace Service\Backend\Model;

use Think\Model;
use Common\Model\SlaveModel;

/**
 * 后台菜单管理
 *
 * @author Administrator
 */
class ConsoleMenuModel extends SlaveModel {

    /**
     * 获取所有菜单
     * @return type
     */
    public function getAllMenu() {
        $menu = M("ConsoleMenu");
        $where["IfDisplay"] = 1;
        $result = $menu->where($where)->order("menu_sort desc")->field("menu_id,menu_code,menu_name,menu_url,parent_id,if_right,if_display,menu_sort")->select();
        return $result;
    }

    /**
     * 获取所有菜单
     * @return type
     */
    public function getUserMenu($userID) {
        $menu = M();
        $sql = "SELECT DISTINCT A.menu_id,menu_code,menu_name,menu_url,parent_id,if_right,if_display,menu_sort from d_console_menu AS A
                INNER JOIN d_console_access AS B on A.menu_id=B.menu_id
                INNER JOIN d_console_role as C on B.role_id=C.role_id 
                INNER JOIN d_console_roleuser AS D on D.role_id=C.role_id and D.user_id='%d' where  A.if_display=1";
        $result = $menu->query($sql, $userID);
        return $result;
    }

    /**
     * 根据父节点返回
     * @param type $parentID
     * @return type
     */
    public function getSubMenu($parentID) {
        $menu = M("ConsoleMenu");
        $where["IfDisplay"] = 1;
        $where["parentID"] = $parentID;
        return $menu->where($where)->order("menuSort desc")->field("menuID,menuName,menuUrl,parentID,IfRight")->select();
    }

    /**
     * 根据父节点返回用户的菜单
     * @param type $parentID
     * @param type $userID
     * @return type
     * --
     */
    public function getUserSubMenu($parentID, $userID) {
        $menu = M();
        $sql = "SELECT DISTINCT A.menu_id,menu_code,menu_name,menu_url,parent_id,if_right,if_display,menu_sort from d_console_menu AS A
                INNER JOIN d_console_access AS B on A.menu_id=B.menu_id
                INNER JOIN d_console_role as C on B.role_id=C.role_id 
                INNER JOIN d_console_roleuser AS D on D.role_id=C.role_id and D.user_id='%d' where  A.if_display=1 and A.parent_id='%d' order by A.menu_sort DESC ";
        $result = $menu->query($sql, $userID, $parentID);
        return $result;
    }

    /**
     * 获取全部的后台菜单
     * @param type $where
     * @param type $whereArr
     * @return type
     */
    public function getMenu($where, $whereArr, $field) {
        $menu = $this->SlaveDB()->table("d_console_menu");
        $result = $menu->where($where, $whereArr)->field($field)->order("menu_sort desc")->select();
        return $result;
    }

    /**
     * 获取要修改的后台菜单信息
     * @param type $where
     */
    public function getSinMenu($where) {
        $model = $this->SlaveDB()->table("d_console_menu");
        $result = $model->where($where)->find();
        return $result;
    }

    /**
     * 保存后台菜单
     * @param type $where
     * @param type $data
     */
    public function saveMenu($where, $data) {
        try {
            $model = M("ConsoleMenu");
            if (is_array($where) && count($where) > 0) {
                $result = $model->where($where)->save($data);
                return $result * 1 > 0 ? $where["menu_id"] : false;
            } else {
                return $model->add($data);
            }
        } catch (Exception $ex) {
            logger(__METHOD__, $ex->getMessage());
            return false;
        }
    }

}
