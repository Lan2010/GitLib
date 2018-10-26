<?php
/**
 * Created by PhpStorm.
 * User: RLY
 * Date: 2018/6/16
 * Time: 16:07
 */

namespace Service\Advertisement\Logic;

use Service\Advertisement\Model;

class AdvertisementLogic
{

    private $model = null;

    public function __construct()
    {
        $this->model = new model\AdvertisementModel();
    }

    /**生成或者插入
     * @param $openID
     * @return mixed
     */
    public function setAdvertisement($data) {
        return $this->model->add($data);
    }


    public function getAd($where ,$felid) {
        return $this->model->getAd($where,$felid);
    }


    public function getTotalPage($where) {
        $dat = $this->model->where($where)->field(array("count(ad_id) as num"))->select();
        return $dat[0]["num"];
    }

    public function update($where,$data) {
        return $this->model->where($where)->save($data);
    }

    public function setInc($where) {
        return $this->model->where($where)->setDec("total_count",1);
    }

    public function addNum($where,$num) {
        return $this->model->where($where)->setInc('user_browse_starpoint',$num);
    }

    /**
     * 获取到有效的信息
     *
     */
    public function getEffectiveAd($code) {
        $where['total_count'] = array("gt", 0);
        $where['begin_time'] = array('elt', time());
        $where['end_time'] = array('egt', time());
        $where['city_code'] = $code;
        $where['advertisement_attribute'] = "ADVERTISER";
        $field = [
            'advert_pic as advertPic','ad_url','advert_name as advertName', 'advert_icon as advertIcon','order_no as ad_id','once_starpoint as onceStarPoint',
            'advert_remark as advertRemark','advertisement_describe',
            'once_click_starpoint as onceClickStarPoint','user_browse_starpoint','total_starpoint as totalStarPoint'
        ];
        $logic = new AdvertisementLogic();
        return $logic->getAd($where,$field);
    }

    /**获取到随机
     * @param $data
     */
    public function getRandAd($data) {
        if(empty($data)) {
            return "";
        }
        $key_arr = array_keys($data);
        if(count($key_arr) <= 1) {
            return $data[0];
        }
        $rand_num = mt_rand(0,count($key_arr)-1);
        return $data[$rand_num];
    }


    /**
     * 获取到默认的图片
     */
    public function getDefault($code) {
        $where['total_count'] = array("gt", 0);
        $where['begin_time'] = array('elt', time());
        $where['end_time'] = array('egt', time());
        $where['city_code'] = $code;
        $where['advertisement_attribute'] = "DEFAULT";
        $field = [
            'advert_pic as advertPic','ad_url','advert_name as advertName', 'advert_icon as advertIcon','order_no as ad_id','once_starpoint as onceStarPoint',
            'advert_remark as advertRemark','advertisement_describe',
            'once_click_starpoint as onceClickStarPoint','user_browse_starpoint','total_starpoint as totalStarPoint'
        ];
        $logic = new AdvertisementLogic();
        return $logic->getAd($where,$field);
    }










}