<?php


namespace Service\Common;

use Common\Common\Redis;

/**
 * Description of CallBackLogic
 * @author Administrator
 *   用于简单控制并发
 *
 */
class CommonLogic {

    public $return = array("code"=>0,'msg'=>"",'info'=>"");

    public function getFrame() {
        return M('suspensionFrame')->where("begin_datetime < ".time(). " and end_datetime >".time())->order("frame_id desc")->find();
    }


    public function setFrame($data) {
        return M('suspensionFrame')->add($data);
    }




}
