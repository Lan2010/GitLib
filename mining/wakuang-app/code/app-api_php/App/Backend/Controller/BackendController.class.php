<?php

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

namespace Backend\Controller;

use Service\Backend\Logic;
use Common\Common\Redis;

/**
 * Description 后台用户及权限管理
 *
 * @author Administrator
 */
class BackendController extends BaseEndController {

    /**
     * 后台用户
     */
    public function getUser() {
        //$this->checkAuthority();
        $par = I("post.");
        $where = "";
        $whereArr = array();
        if (!is_null($par["txtuserName"]) && !empty($par["txtuserName"])) {
            $where .= " AND A.user_name = '%s'";
            array_push($whereArr, $par["txtuserName"]);
        }
        if (!is_null($par["txtrealName"]) && !empty($par["txtrealName"])) {
            $where .= " AND A.real_name = '%s'";
            array_push($whereArr, aes($par["txtrealName"]));
        }
        $limit = $this->getPage();
        $logic = new Logic\ConsoleUserLogic();
        $result = $logic->getUserList($where, $whereArr, $limit);
        $this->assign('user', $result["rows"]);
        $this->assign('pageHtml', $this->showPage($result["total"]));
        $this->display("userList");
    }

    /**
     * 删除后台角色
     */
    public function delUser() {
        $key = I("post.key");
        if ($key * 1 > 0) {
            $logic = new Logic\ConsoleUserLogic();
            $result = $logic->delUser($key);
            if ($result * 1 > 0) {
                $this->clearAuthCash();
                $this->jsonReturn(1, "删除成功", $result);
            }
        }
        return $this->jsonReturn(0, "删除失败");
    }

    /**
     * 后台用户
     */
    public function editUser() {
        $key = I("get.key");
        if ($key * 1 > 0) {
            $logic = new Logic\ConsoleUserLogic();
            $data = $logic->getUser($key);
            if (!empty($data)) {
                $data['realName'] = aes($data['realName'], 'DECODE');
            }
            $this->assign("data", $data);
        }
        $this->display("userEdit");
    }

    /**
     * 保存后台角色
     */
    public function saveUser() {
        $par = I("post.par");
        if (count($par) > 0) {
            $logic = new Logic\ConsoleUserLogic();
            if (!empty($par['real_name'])) {
                $par['real_name'] = aes($par['real_name']);
            }
            $result = $logic->saveUser($par);
            if ($result * 1 > 0) {
                $this->jsonReturn(1, "保存成功!", $result);
            }
        }
        $this->jsonReturn(0, "保存失败!");
    }

    /**
     * 角色
     */
    public function getRole() {
        $par = I("post.");
        $where = "";
        $whereArr = array();
        if (!is_null($par["txtroleName"]) && !empty($par["txtroleName"])) {
            $where .= "AND role_name like '%s' ";
            array_push($whereArr, "%" . $par["txtroleName"] . "%");
        }
        $limit = $this->getPage();
        $logic = new Logic\ConsoleRoleLogic();
        $result = $logic->getRole($where, $whereArr, $limit);



        $this->assign("page", $this->showPage($result["total"]));
        $this->assign("result", $result["rows"]);
        $this->display("roleList");
    }

    /**
     * 删除角色
     */
    public function delRole() {
        $key = I("post.key");
        if ($key * 1 > 0) {
            $logic = new Logic\ConsoleRoleLogic();
            $result = $logic->delRole($key);
            $this->clearAuthCash();
            $this->jsonReturn(1, "删除成功!", $result);
        }
        $this->jsonReturn(0, "删除失败!");
    }

    /**
     * 后台用户
     */
    public function editRole() {
        $key = I("get.key");
        if ($key * 1 > 0) {
            $logic = new Logic\ConsoleRoleLogic();
            $result = $logic->getSinRole($key);
            $this->assign("data", $result);
        }
        $this->display("roleEdit");
    }

    /**
     * 保存角色管理
     */
    public function saveRole() {
        $par = I("post.par");
        if (count($par) > 0) {
            $logic = new Logic\ConsoleRoleLogic();
            $result = $logic->saveRole($par);
            if ($result * 1 > 0) {
                $this->jsonReturn(1, "保存成功!", $result);
            }
        }
        $this->jsonReturn(0, "保存失败!");
    }

    /**
     * 配置角色权限。
     */
    public function siteRole() {
        $key = I("get.key");
        if ($key * 1 > 0) {
            $logic = new Logic\ConsoleAccessLogic();
            $result = $logic->getAccessByRole($key);
            $this->assign("data", $result);
        }
        $this->assign('roleid', $key);
        $this->display("siteRole");
    }

    /**
     * 保存角色授权信息。
     */
    public function saveSiteRole() {
        $par = I("post.");
        $roleID = $par['roleID'];
        $access = $par['access'];
        $roleLogic = new Logic\ConsoleRoleLogic();
        $roleInfo = $roleLogic->getSinRole($roleID);
        if (empty($roleInfo) || !$roleInfo) {
            $this->jsonReturn(0, '不存在对应的角色');
        }
        $roleData = array();
        foreach ($access as $accessValue) {
            $tmpArr = explode("_", $accessValue);
            $roleData[] = array(
                "role_id" => $roleID,
                "menu_id" => $tmpArr[0],
                "level_type" => $tmpArr[1]
            );
        }
        if (count($roleData) == 0) {
            $this->jsonReturn(0, '没有任何授权信息');
        }
        $accessLogic = new Logic\ConsoleAccessLogic();
        $accessLogic->delAccessByRole($roleID);
        $result = $accessLogic->addAccess($roleData);
        if ($result) {
            $this->clearAuthCash();
            $this->jsonReturn(1, '更新角色权限信息成功');
        } else {
            $this->jsonReturn(0, '更新角色权限信息失败');
        }
    }

    /**
     * 配置用户角色。
     */
    public function siteRoleUser() {
        $key = I("get.key");
        if ($key * 1 > 0) {
            $logic = new Logic\ConsoleRoleuserLogic();
            $result = $logic->getRoleByUser($key);
            $this->assign("data", $result);
        }
        $this->assign('userID', $key);
        $this->display("roleUser");
    }

    /**
     * 保存用户角色信息。
     */
    public function saveSiteRoleUser() {
        $par = I("post.");
        $userID = $par['userID'];
        $access = $par['access'];
        $userLogic = new Logic\ConsoleUserLogic();
        $userInfo = $userLogic->getUser($userID);
        if (empty($userInfo) || !$userInfo) {
            $this->jsonReturn(0, '不存在对应的用户');
        }
        $roleData = array();
        foreach ($access as $accessValue) {
            $roleData[] = array(
                "user_id" => $userID,
                "role_id" => $accessValue
            );
        }
        if (count($roleData) == 0) {
            $this->jsonReturn(0, '没有分配任何角色信息');
        }
        $roleUserLogic = new Logic\ConsoleRoleuserLogic();
        $roleUserLogic->delRoleByUser($userID);
        $result = $roleUserLogic->addRoles($roleData);
        if ($result) {
            $this->clearAuthCash();
            $this->jsonReturn(1, '更新用户角色信息成功');
        } else {
            $this->jsonReturn(0, '更新用户角色信息失败');
        }
    }

    /**
     * 菜单管理
     */
    public function getMenu() {
        $par = I("post.");
        $where = "";
        $whereArr = array();
        if (!is_null($par["txtmenuName"]) && !empty($par["txtmenuName"])) {
            $where .= "AND menu_name= '%s'";
            array_push($whereArr, $par["txtmenuName"]);
        }
        $logic = new Logic\ConsoleMenuLogic();
        $result = $logic->getMenu($where, $whereArr);
        $this->assign("levelType", $logic->levelType);
        $this->assign("result", $result);
        $this->display("menuList");
    }



    /**
     * 修改菜单 
     */
    public function editMenu() {
        $key = I("get.key");
        $logic = new Logic\ConsoleMenuLogic();
        if ($key * 1 > 0) {
            $data = $logic->getSinMenu($key);
            $this->assign("data", $data);
        }
        $menuType = $logic->getMenu();
        $this->assign("menuType", $menuType);
        $this->display("menuEdit");
    }

    /**
     * 保存菜单
     */
    public function saveMenu() {
        $par = I("post.par");
        if (count($par) * 1 > 0) {
            $logic = new Logic\ConsoleMenuLogic();
            $result = $logic->saveMenu($par);
            if ($result * 1 > 0) {
                $this->clearAuthCash();
                $this->jsonReturn(1, "保存成功!", $result);
            }
        }
        $this->jsonReturn(0, "保存失败!");
    }

    /**
     * 删除后台菜单
     */
    public function delMenu() {
        $key = I("post.key");
        if ($key * 1 > 0) {
            $logic = new Logic\ConsoleMenuLogic();
            $result = $logic->delMenu($key);
            if ($result * 1 > 0) {
                $this->jsonReturn(1, "删除成功!", $result);
            }
        }
        $this->jsonReturn(0, "删除失败!");
    }

    /**
     * 清除权限缓存。
     */
    private function clearAuthCash() {
        $redis = Redis::GetInstance();
        $keys = $redis->getKeys(C('USER_AUTH_CACH_KEY'));
        if (is_array($keys) && count($keys) > 0) {
            $redis->dels($keys);
        }
    }



}
