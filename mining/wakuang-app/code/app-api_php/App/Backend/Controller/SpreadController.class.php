<?php

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

namespace Backend\Controller;

use Service\Spread\Logic;
use Service\System\Logic as sys;
use Common\Common\Redis;

/**
 * 文案以及推广控制器
 *
 * @author Administrator
 */
class SpreadController extends BaseEndController {













    /**
     * 微信分享管理
     */
    public function getShare() {
        $txtshareName = I("post.txtshareName");
        $txtshareCode = I("post.txtshareCode");
        $limit = $this->getPage();
        $where = "";
        $whereArr = array();
        if (!is_null($txtshareName) && !empty($txtshareName)) {
            $where .= " AND share_name like  '%s'";
            array_push($whereArr, "%" . $txtshareName . "%");
        }
        if (!is_null($txtshareCode) && !empty($txtshareCode)) {
            $where .= " AND share_code like '%s'";
            array_push($whereArr, "%" . $txtshareCode . "%");
        }
        $logic = new Logic\WxShareLogic();
        $result = $logic->getShare($where, $whereArr, $limit);
        $this->assign("wxinfo", $result["rows"]);
        $this->assign("page", $this->showPage($result["total"]));
        $this->display("shareList");
    }

    /**
     * 上传微信图片
     */
    public function saveShareImg() {
        $picture = $this->uploadImg("share");
        echo json_encode($picture);
    }

    /**
     * 编辑微信分享
     */
    public function editShare() {
        $key = I("get.key");
        if ($key * 1 > 0) {
            $logic = new Logic\WxShareLogic();
            $result = $logic->getSinShare($key);
            $this->assign("data", $result);
        }
        $this->display(shareEdit);
    }

    /**
     * 保存微信分享
     */
    public function saveShare() {
        $par = I("post.par");
        if (count($par) * 1 > 0) {
            $logic = new Logic\WxShareLogic();
            $result = $logic->saveShare($par);
            if ($result * 1 > 0) {
                $this->jsonReturn(1, "操作成功", $result);
            }
        }
        $this->jsonReturn(0, "操作失败!" . $result);
    }

    /**
     * 删除微信分享
     */
    public function delShare() {
        $key = I("post.key");
        if ($key * 1 > 0) {
            $logic = new Logic\WxShareLogic();
            $result = $logic->delShare($key);
            if ($result * 1 > 0) {
                $this->jsonReturn(1, "操作成功!", $result);
            }
        }
        $this->jsonReturn(0, "操作失败!");
    }





    /**
     * 广告图
     */
    public function getBanner() {
        $txtName = I("post.txtName");
        $txtType = I("post.txtType");
        $where = "";
        $whereArr = array();
        $limit = $this->getPage();
        if (!is_null($txtName) && !empty($txtName)) {
            $where .= " AND banner_name like  '%s'";
            array_push($whereArr, "%" . $txtName . "%");
        }
        if (!is_null($txtType) && !empty($txtType)) {
            $where .= " AND dic_type = '%s'";
            array_push($whereArr, $txtType);
        }
        $logicsys = new sys\DictionaryLogic();
        $logic = new Logic\HomeBannerLogic();
        $htmlType = $logicsys->getDicHTML(29, $txtType);
        $result = $logic->getBanner($where, $whereArr, $limit);
        $this->assign("htmlType", $htmlType);
        $this->assign("result", $result["rows"]);
        $this->assign("page", $this->showPage($result["total"]));
        $this->display("bannerList");
    }

    /**
     * 上传广告图
     */
    public function saveBannerImg() {
        $picture = $this->uploadImg("banner");
        echo json_encode($picture);
    }

    /**
     * 删除广告图
     */
    public function delBanner() {
        if (IS_POST) {
            $key = I("post.key");
            if ($key * 1 > 0) {
                $logic = new Logic\HomeBannerLogic();
                $result = $logic->delBanner($key);
                if ($result * 1 > 0) {
                    $this->jsonReturn(1, "删除成功");
                }
            }
            $this->jsonReturn(0, "删除失败");
        }
    }

    /**
     * 编辑广告图
     */
    public function editBanner() {
        $key = I("get.key");
        if ($key * 1 > 0) {
            $logic = new Logic\HomeBannerLogic();
            $result = $logic->getSinBanner($key);
            $this->assign("data", $result);
        }
        $logicsys = new sys\DictionaryLogic();
        $htmlType = $logicsys->getDicHTML(29, $result["dicType"]);
        $this->assign("htmlType", $htmlType);
        $this->display("bannerEdit");
    }

    /**
     * 保存添加的广告信息
     */
    public function saveBanner() {
        $par = I("post.par");
        if(empty($par["banner_url"]) && empty($par['link_url']) ) {
            $this->jsonReturn(0,"图片和链接不能为空");
        }
        if (count($par) * 1 > 0) {
            $logic = new Logic\HomeBannerLogic();
            $result = $logic->saveBanner($par);
            if ($result * 1 > 0) {
                $this->jsonReturn(1, "操作成功", $result);
            }
        }
        $this->jsonReturn(0, "操作失败!" . $result);
    }


    /**
     * 获取用户APP的
     */
    public function getAPP() {
        $par = I("post.");
        $where = "";
        $whereArr = array();
        if (!is_null($par["type"]) && !empty($par["type"])) {
            $where .= " AND type =  '%s'";
            array_push($whereArr, $par["type"]);
        }
        if (!is_null($par["terminal"]) && !empty($par["terminal"])) {
            $where .= " AND terminal =  '%s'";
            array_push($whereArr, $par["terminal"]);
        }
        if (!is_null($par["txtDateStart"]) && !empty($par["txtDateStart"])) {
            $where .= " AND addDatetime >=  '%s'";
            array_push($whereArr, strtotime($par["txtDateStart"]));
        }
        if (!is_null($par["txtDateEnd"]) && !empty($par["txtDateEnd"])) {
            $where .= " AND addDatetime <  '%s'";
            array_push($whereArr, strtotime($par["txtDateEnd"] . "+1 day"));
        }
        $limit = $this->getPage();
        $logic = new Logic\AppInfoLogic();
        $result = $logic->getAppInfo($where, $whereArr, $limit);
        $this->assign("page", $this->showPage($result["total"]));
        $this->assign("result", $result["rows"]);
        $this->display("appList");
    }

    /**
     * APP版本
     */
    public function getAppVer() {
        $par = I("post.");
        $where = "";
        $whereArr = array();
        if (!is_null($par["versionName"]) && !empty($par["versionName"])) {
            $where .= "AND version_name='%s'";
            array_push($whereArr, $par["versionName"]);
        }
        if (!is_null($par["terminal"]) && !empty($par["terminal"])) {
            $where .= "AND terminal='%s'";
            array_push($whereArr, $par["terminal"]);
        }
        $where .= " AND  status>'%s'";
        array_push($whereArr, 0);
        $limit = $this->getPage();
        $logic = new Logic\AppVersionsLogic();
        $result = $logic->getAppList($where, $whereArr, $limit);
        $this->assign("result", $result["rows"]);
        $this->assign("pageHtml", $this->showPage($result["total"]));
        $this->display("appVerList");
    }

    /**
     * 增加APP版本
     */
    public function getAllAPP() {
        $key = I("get.key");
        if ($key * 1 > 0) {
            $logic = new Logic\AppVersionsLogic();
            $result = $logic->getAllApp($key);
            $this->assign("data", $result);
        }
        $this->display("appVerEdit");
    }

    /**
     * 更新APP版本
     */
    public function saveAppVer() {
        $par = I("post.par");
        if (count($par) * 1 > 0) {
            $logic = new Logic\AppVersionsLogic();
            $result = $logic->saveAppVer($par);
            if ($result * 1 > 0) {
                $this->jsonReturn(1, "操作成功", $result);
            }
        }
        $this->jsonReturn(0, "操作失败!" . $result);
    }

    /**
     * APP版本
     */
    public function delAppVer() {
        $key = I("post.key");
        if ($key * 1 > 0) {
            $logic = new Logic\AppVersionsLogic();
            $result = $logic->delAppVer($key);
            if ($result * 1 > 0) {
                $this->jsonReturn(1, "删除成功");
            }
        }
        $this->jsonReturn(0, "删除失败");
    }

    /**
     * 微信自定义菜单列表
     */
    public function getWxMenu() {
        $title = htmlspecialchars(I('post.txtmenuName'));
        $where = array();
        if (!empty($title)) {
            $where['menu_name'] = array('like', "%" . $title . "%");
        }
        $wxLogic = new Logic\WxMenuLogic();
        $menulist = $wxLogic->getWxMenuList($where);
        $this->menuTypes = $wxLogic->menuTypes;
        $this->result = $menulist;
        $this->display('wxMenu');
    }

    /**
     * 编辑微信自定义菜单
     */
    public function wxMenuEdit() {
        $key = I("get.key");
        $wxLogic = new Logic\WxMenuLogic();
        $result = $wxLogic->getWxMenu($key);
        $this->menu = $wxLogic->getParentMenus();
        $this->data = $result;
        $this->menuTypes = $wxLogic->menuTypes;
        $this->display('wxMenuEdit');
    }

    /**
     * 保存微信菜单
     */
    public function saveWxMenu() {
        if (!IS_AJAX) {
            $this->jsonReturn(0, "非法请求");
        }
        $post = I('post.par');
        $data['parent_id'] = intval($post['parent_id']);
        $data['menu_type'] = $post['menu_type'];
        $data['menu_name'] = trim(htmlspecialchars($post['menu_name']));
        $data['menu_action'] = trim(htmlspecialchars($post['menu_action']));
        $data['is_authorize'] = intval($post['is_authorize']);
        $data['sort'] = intval($post['sort']);
        $wxLogic = new Logic\WxMenuLogic();
        $result = $wxLogic->saveOrAddWxMenu($data, $post['menu_id']);
        if ($result) {
            $this->jsonReturn(1, "保存成功");
        } else {
            $this->jsonReturn(0, "保存失败");
        }
    }

    /**
     * 删除微信自定义菜单
     */
    public function delWxMenu() {
        if (!IS_AJAX) {
            $this->jsonReturn(1, '非法请求');
        }
        $wxMenuID = intval(I('post.key'));
        $wxLogic = new Logic\WxMenuLogic();
        $data = $wxLogic->getWxMenu($wxMenuID);
        if (!empty($data) && $data['parent_id'] * 1 == 0) {
            $count = $wxLogic->getMenuCount($wxMenuID);
            if ($count * 1 > 0) {
                $this->jsonReturn(0, "该菜单下面有子菜单，请先删除子菜单");
            }
        }
        $result = $wxLogic->delReply($wxMenuID);
        if ($result) {
            $this->jsonReturn(1, "删除成功");
        } else {
            $this->jsonReturn(0, "删除失败");
        }
    }

    /**
     * 重新生成微信服务号菜单
     */
    public function createWxMenu() {
        if (!IS_AJAX) {
            $this->jsonReturn(1, '非法请求');
        }
        $wxLogic = new Logic\WxMenuLogic();
        $result = $wxLogic->createWxMenu();
        if ($result === true) {
            $this->jsonReturn(1, '微信菜单创建成功');
        } else {
            $this->jsonReturn(0, "操作失败：" . $result);
        }
    }

    /**
     * 微信消息推送管理，数据列表
     */
    public function getWxReplys() {
        $par = I("post.");
        $where = "";
        $whereArr = array();
        if (!is_null($par["replyType"]) && !empty($par["replyType"])) {
            $where .= " AND reply_type = '%s'";
            array_push($whereArr, $par["replyType"]);
        }
        if (!is_null($par["keyword"]) && !empty($par["keyword"])) {
            $where .= " AND keyword like '%s'";
            array_push($whereArr, "%" . $par["keyword"] . "%");
        }
        $limit = $this->getPage();
        $wxLogic = new Logic\WxReplyLogic();
        $result = $wxLogic->getList($where, $whereArr, $limit);
        $this->replyTypes = $wxLogic->replyTypes;
        $this->contentTypes = $wxLogic->contentTypes;
        $this->result = $result['rows'];
        $this->pageHtml = $this->showPage($result["total"]);
        $this->display('wxReply');
    }

    /**
     * 微信消息推送，编辑界面
     */
    public function wxReplyEdit() {
        $key = I("get.key");
        $wxLogic = new Logic\WxReplyLogic();
        $result = $wxLogic->getEntity($key);
        $this->replyTypes = $wxLogic->replyTypes;
        $this->contentTypes = $wxLogic->contentTypes;
        $this->data = $result;
        $this->display('wxReplyEdit');
    }

    /**
     * 保存微信回复消息
     */
    public function saveWxReply() {
        if (!IS_AJAX) {
            $this->jsonReturn(0, "非法请求");
        }
        $par = I("post.par");
        if (count($par) * 1 > 0) {
            $logic = new Logic\WxReplyLogic();
            $result = $logic->saveReply($par);
            if ($result * 1 > 0) {
                $this->jsonReturn(1, "操作成功", $result);
            }
        }
        $this->jsonReturn(0, "操作失败!" . $result);
    }

    /**
     * 删除微信回复
     */
    public function delWxReply() {
        if (!IS_AJAX) {
            $this->jsonReturn(1, '非法请求');
        }
        $replyID = intval(I('post.key'));
        $wxLogic = new Logic\WxReplyLogic();
        $result = $wxLogic->delReply($replyID);
        if ($result * 1 > 0) {
            $this->jsonReturn(1, "删除成功", $result);
        } else {
            $this->jsonReturn(1, "删除失败", $result);
        }
    }

}
