<?php
namespace MobileApi\Api;

use MobileApi\Common\MobileApiBase;
use Service\Coupon\Logic\CouponLogic;
use Think\Upload;


class ApiCoupon extends MobileApiBase
{


    private  $return  =  array('code' => 1, 'msg' => '', 'info' => array());  //返回的结果
    public function getRules()
    {
        return array(
            'getAuditStatus' => array(),
            'getCouponList' => array(
                "lat" => array('name' => 'lat', 'type' => 'string', "require" => true, 'desc' => '纬度'),
                "lng" => array('name' => 'lng', 'type' => 'string', "require" => true, 'desc' => '广告的经度'),
                "m" => array('name' => 'm', 'type' => 'int', "require" => true, 'desc' => '距离米'),
            ),
            'acceptCoupon' => array(
                'cou_id' => array('name' => 'cou_id', 'type' => 'int', 'default' => 0, 'desc' => '优惠券的Id'),
            ),
            'getMyCoupon' => array(
                'pageindex' => array('name' => 'pageindex', 'type' => 'int', 'default' => 1, 'desc' => '页码'),
                'pagesize' => array('name' => 'pagesize', 'type' => 'int', 'default' => 15, 'desc' => '每页显示数量'),
             ),
        );
    }


    /**
     * 获取审核状态
     * @desc 获取到用于审核的状态
     * @return int code                 操作码，0表示成功， 1登录失败
     * @return array info               账户信息
     * @return string info.AuditStatus  是否在审核状态 0 否  1是
     * @return string msg 提示信息
     */
     public function getAuditStatus() {
         $this->return['code'] = 0;
         $this->return['info'] = array("audit"=>M("Config")->where(array("paramCord"=>"auditStatus"))->getField('value'));
         return $this->return;
     }


     /**
      * 获取到优惠券
      * @desc 获取到优惠券星星
      * @return int code                 操作码，0表示成功， 1登录失败
      * @return array info               账户信息
      * @return int info.cou_id      优惠券的ID
      * @return string info.coupon_name  优惠券的名字
      * @return int info.coupon_num  '优惠券的数量',
      * @return int info.start_datetime  开始时间 为0就代表无时间限制,
      * @return int info.end_datetime  '结束时间 为0就代表无时间限制',
      * @return string info.coupon_icon '图标的logo',
      * @return int info.coupon_type  '类型 1 满减 暂时只有满减',
      * @return string info.remark  备注
      * @return int info.quota_max  '最大额度 满多少减多少',
      * @return int info.is_null  代表是否可领取 0 不可以领 1可以
      * @return int lat  纬度
      * @return int lng   经度
      * @return int info.quota_discount` decimal(15,2) DEFAULT '0.00' COMMENT '优惠多少',
      * @return string msg 提示信息
      */
     public function getCouponList() {


         $logic = new CouponLogic();
         $list = $logic->getCouponList();
         if(empty($list)) {
             $this->return['code'] = 1;
             $this->return['msg'] = "优惠券为空";
             return $this->return;
         }

         $this->lat = sprintf("%.6f", $this->lat);
         $this->lng = sprintf("%.6f", $this->lng);
         $range_lan_long = getAround($this->lat, $this->lng, $this->m);
         $lat_rand_num = $range_lan_long['maxLat'] - $range_lan_long['minLat'];
         $lng_rand_num = $range_lan_long['maxLong'] - $range_lan_long['minLong'];

         foreach ($list as $key=>$value) {
             $list[$key]['is_null'] = mt_rand(0,1);  //是否为领取为空
             $list[$key]['Lat'] = sprintf("%.6f", rand(0, 10)/10 * $lat_rand_num + $range_lan_long["minLat"]);
             $list[$key]['Lng'] = sprintf("%.6f", rand(0, 10)/10 * $lng_rand_num + $range_lan_long["minLong"]);
             $list[$key]['coupon_icon'] = UPLOAD.$list[$key]['coupon_icon'];
         }
         $this->return['code'] = 0;
         $this->return['info'] = $list;
         return $this->return;
     }

    /**
     * 领取优惠券
     * @desc 用户领取优惠券
     * @return int code                 操作码，0表示成功， 1登录失败
     * @return array info               账户信息
     * @return string msg 提示信息
     */
     public function acceptCoupon() {
         $this->userCheck();
         if(empty($this->cou_id)) {
             $this->return['code'] = 1;
             $this->return['msg'] = "参数传递错误";
             return $this->return;
         }
         $logic = new CouponLogic();
         $res = $logic->saveUserCoupon($this->userID,$this->cou_id);
         if($res) {
             $this->return['code'] = 0;
             $this->return['msg'] = "领取成功";
         } else {
             $this->return['code'] = 1;
             $this->return['msg'] = "领取失败";
         }
         return $this->return;
     }

    /**
     * 我的优惠券
     * @desc 获取到我的优惠券星星
     * @return int code                 操作码，0表示成功， 1登录失败
     * @return array info               账户信息
     * @return int info.cou_id      优惠券的ID
     * @return string info.coupon_name  优惠券的名字
     * @return int info.coupon_num  '优惠券的数量',
     * @return int info.start_datetime  开始时间 为0 无限制,
     * @return int info.end_datetime  '结束时间 为 0 无限制',
     * @return string info.coupon_icon '图标的logo',
     * @return int info.coupon_type  '类型 1 满减 暂时只有满减',
     * @return int info.quota_max  '最大额度 满多少减多少',
     * @return string info.remark  备注
     * @return int info.quota_discount` decimal(15,2) DEFAULT '0.00' COMMENT '优惠多少',
     * @return string msg 提示信息
     */
     public function getMyCoupon() {
         $this->userCheck();
         $limit = $this->getLimit($this->pageindex, $this->pagesize);
         $logic = new CouponLogic();
         $where = array('cu.user_id'=>$this->userID);
         $list = $logic->getMyCoupon($where,$limit);
         foreach ($list as $key=>$value) {
             $list[$key]['coupon_icon'] = UPLOAD.$list[$key]['coupon_icon'];
         }
         if(empty($list)) {
             $this->return["code"] = 1;
             $this->return['msg'] = "没有数据";
         } else {
             $this->return["code"] = 0;
             $this->return['info'] = $list;
         }
         return $this->return;
     }






}