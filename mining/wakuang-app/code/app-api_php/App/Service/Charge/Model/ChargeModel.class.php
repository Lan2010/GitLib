<?php
/**
 * Created by PhpStorm.
 * User: RLY
 * Date: 2018/6/16
 * Time: 16:32
 */

namespace Service\Charge\Model;

use Common\Model\SlaveModel;
use Think\Exception;

class ChargeModel extends SlaveModel
{
    public function getChargeInfo($where) {
        if(!is_array($where)) {
            return false;
        }
        $where['state'] = array('NEQ',0);
        return $this->where($where)->find();
    }

    public function updateBindInfo($where,$data) {
        if(empty($where) || empty($data)) {
            return false;
        }
        return $this->where($where)->save($data);
    }
}