<?php

namespace Backend\Controller;

/**
 * Description of IndexController
 *
 * @author Administrator
 */
use Service\Backend\Logic;
use Service\Coupon\Logic\CouponLogic;
use Think\Cache;
use Service\User\Logic\UserBackactionLogLogic;

class IndexController extends BaseEndController {

    public function index() {
        $userID = C("userID");
        if ($userID * 1 > 0) {
            $logic = new Logic\ConsoleMenuLogic();
            //$result = $logic->getAllMenu();
            $result = $logic->getUserMenu($userID);
            $this->assign('menu', $result);
        }
        $this->display("main");
    }

    /**
     * 修改用户的密码
     */
    public function savePass() {
        $par = I("post.par");
        if (count($par) > 0) {
            $par["userID"] = C("userID");
            if (strcmp($par["newPass"], $par["oldPass"]) == 0) {
                $this->jsonReturn(0, "新密码和旧密码一样!");
            }
            $logic = new Logic\ConsoleUserLogic();
            $result = $logic->savePass($par);
            $logicsys = new UserBackactionLogLogic();
            if ($result * 1 > 0) {
                $logicsys->addLog(C("userID"), C("realName"), get_client_ip(), "后台修改密码", "修改成功");
                $this->jsonReturn(1, "密码修改成功，请使用新密码重新登录!", $result);
            } else {
                $logicsys->addLog(C("userID"), C("realName"), get_client_ip(), "后台修改密码", "修改失败");
            }
        }
        $this->jsonReturn(0, "原密码错误!");
    }

    public function loginOut() {
        $logicsys = new UserBackactionLogLogic();
        $logicsys->addLog(C("userID"), aes(C("realName")), get_client_ip(), "后台退出", "退出成功");
        cookie(C("SHORT_CODE") . "_Login_End_", null);
        $userID = C("userID");
        $memcache = Cache::getInstance();
        if ($userID * 1 > 0) {
            $memcache->DEL(C("SHORT_CODE") . "_Login_End_" . $userID);
            $memcache->del(C("USER_AUTH_CACH_KEY") . $userID);
        }
        redirect("/Backend/Login/login");
        exit();
    }

    /**
     * 控制IOS的状态
     */
    public function auditStatus() {
        $value = M("Config")->where(array("paramCord"=>"auditStatus"))->getField('value');
        $this->assign("value",$value);
        $this->display();
    }

    /**
     * 修改状态
     */
    public function changeStatus() {
        $value = I("value");
        $value = ($value)? array("value"=>1):array("value"=>0);
        $res = M("Config")->where(array("paramCord"=>"auditStatus"))->save($value);
        if ($res) {
            $this->jsonReturn(1, "操作成功", $res);
        } else {
            $this->jsonReturn(0, "操作失败", $res);
        }
    }


    /**
     * 获取到优惠券的列表
     */
    public function getCouponList() {

        $par = I("post.");
        $where = " (true) ";
        if (!is_null($par["coupon_name"]) && !empty($par["coupon_name"])) {
            $where .= "  AND coupon_name  like '%".$par["coupon_name"]."%'";
        }

        $limit = $this->getPage();
        $logic = new CouponLogic();
        $list = $logic->getBndCouponList($where, "*", $limit);
        $result = $logic->getBndCouponList($where,"count(*) as total");
        $this->assign("result", $list);
        $this->assign("pageHtml", $this->showPage($result[0]["total"]));
        $this->display("couponList");

    }

    /**
     * 编辑
     */
    public function editCoupon() {
        $key = I("get.key");
        if ($key * 1 > 0) {
            $logic = new CouponLogic();
            $result = $logic->getBndCouponList("cou_id=$key","*");
            $this->assign("data", $result[0]);
        }
        $this->display("couponEdit");
    }


    public function saveCoupon() {
        $par = I("post.par");
        if(empty($par["coupon_icon"]) ) {
            $this->jsonReturn(0,"图片和链接不能为空");
        }
        if(is_int($par['quota_max']) && is_int($par['quota_discount'])) {
            $this->jsonReturn(0,"满减两项需要填为整数");
        }
        if (count($par) * 1 > 0) {
            $logic = new CouponLogic();
            $result = $logic->saveCoupon($par);
            if ($result * 1 > 0) {
                $this->jsonReturn(1, "操作成功", $result);
            }
        }
        $this->jsonReturn(0, "操作失败!" . $result);
    }

    /**
     * APP版本
     */
    public function delCoupon() {
        $key = I("post.key");
        if ($key * 1 > 0) {
            $result = M("Coupon")->where(array("cou_id"=>$key))->delete();
            if ($result * 1 > 0) {
                $this->jsonReturn(1, "删除成功");
            }
        }
        $this->jsonReturn(0, "删除失败");
    }




}
