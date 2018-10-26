<?php


namespace Service\Coupon\Logic;



class CouponLogic
{

    public function getCouponList() {
        return M('Coupon')->order("sort desc")->limit(5)->select();
    }

    /**
     * 储存
     */
    public function saveUserCoupon($userid,$cou_id) {
        $add_data = array(
            "user_id"=>$userid,
            "coupon_id"=>$cou_id,
            "add_datetime"=>time(),
        );
        return M("CouponUser")->add($add_data);
    }


    /**
     * 获取到当前用户的优惠券
     */

    public function getMyCoupon($where,$limit = "0,15") {
        return  M('CouponUser')->alias('cu')->join("d_coupon c on cu.coupon_id = c.cou_id")->limit($limit)->where($where)->order("cu.add_datetime desc")->select();
    }


    /**
     * 获取到后台的优惠券列表
     */
    public function getBndCouponList($where,$field,$limit="") {
        $obj =  M('Coupon')->where($where)->field($field);

        if(!empty($limit)) {
            $obj->limit($limit);
        }
        return $obj->select();
    }

    /**
     * 保存
     */
    public function saveCoupon($data) {
        $where = array();
        if (empty(trim($data["start_datetime"])) || empty(trim($data["end_datetime"]))) {
            $data["start_datetime"] = 0;
            $data["end_datetime"] = 0;
        } else {
            $data["start_datetime"] = strtotime($data['start_datetime']);
            $data["end_datetime"] = strtotime($data['end_datetime']);
        }
        if(empty($data['cou_id'])) {
            $where = array();
            $data['add_datetime'] = time();
        } else {
            $where['cou_id'] = $data['cou_id'];
            unset($data['cou_id']);
        }

        $data["remark"] = !empty($data['remark'])? strip_tags(htmlspecialchars_decode($data['remark'])) : "";
       

        if(empty($where)) {
            return M('Coupon')->add($data);
        } else {
            return M("Coupon")->where($where)->save($data);
        }
    }




}