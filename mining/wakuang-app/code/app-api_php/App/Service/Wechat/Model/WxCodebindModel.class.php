<?php
/**
 * Created by PhpStorm.
 * User: RLY
 * Date: 2018/6/16
 * Time: 16:32
 */

namespace Service\Wechat\Model;

use Common\Model\SlaveModel;
use Think\Exception;

class WxCodebindModel extends SlaveModel
{
      public function getOpenInfo($openID) {
          $where['open_id'] = $openID;
          return $this->where($where)->find();
      }

      public function getCodeInfo($code) {
          $where['code'] = $code;
          return $this->where($code)->find();
      }
}