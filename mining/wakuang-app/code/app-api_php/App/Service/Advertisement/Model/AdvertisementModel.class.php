<?php
/**
 * Created by PhpStorm.
 * User: RLY
 * Date: 2018/6/16
 * Time: 16:32
 */

namespace Service\Advertisement\Model;

use Common\Model\SlaveModel;
use Think\Exception;

class AdvertisementModel extends SlaveModel
{


    public function getAd($where,$felid) {
        if(!is_array($where)) {
            return false;
        }
        return  $this->where($where)->limit(5)->field($felid)->select();


    }
}