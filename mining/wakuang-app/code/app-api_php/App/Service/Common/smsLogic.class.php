<?php


namespace Service\Common;

use Common\Common\Redis;

/**
 * Description of CallBackLogic
 *
 * @author Administrator
 *   用于简单控制并发
 *
 */
class smsLogic {

    public $return = array("code"=>0,'msg'=>"",'info'=>"");
    public function __construct() {
        vendor("Grpc.php.MobileValidation");
    }

    /**发送短信
     * @param $mobile
     * @return array|bool
     */
    public function send($mobile,$type) {

        $key = $mobile."_".$type;
        $redies = Redis::GetInstance();
        $redis_info = $redies->get($key);
        if(!empty($redis_info) && (time() - $redis_info['sendtime']) < 110 ) {
            return array("code"=>1,'msg'=>"你已经发送了一条短信了，请稍后再尝试");
        }
        $class = new \MobileValidation();
        $ret = $class->send($mobile);
        if(empty($ret)) {
            $this->return['code'] = 1;
            $this->return['msg'] = "grpc错误";
            return $this->return;
        }
        if($ret && $ret['code'] != 200) {
            $this->return['code'] = 1;
            $this->return['msg'] = ($ret['msg'])? $ret['msg'] :"";
        } else {
            $ret['sendtime'] = time();
            $redies->set($key,json_encode($ret),120);   //保存到redis中
            $this->return['msg'] = "发送成功";
        }
        return $this->return;
    }

    /**验证信息
     * @param $code
     * @param $phoneToken
     * @param $moblie
     */
    public function validate($code,$moblie,$type) {
        $key = $moblie."_".$type;
        $redis = Redis::GetInstance();
        $redis_info = $redis->get($key);
        if(empty($redis_info)) {
            return false;
        }
        $phoneToken = $redis_info['codeToken'];
        $class = new \MobileValidation();
        $res = $class->validate($code,$phoneToken,$moblie);
        if(empty($res)) {
            return false;
        }
        if($res && $res['code'] == 200 && $res['status'] == 0) {
            $redis->del($key);
            return true;
        } else {
            return false;
        }
    }


    /**检索信息
     * @param $moblie
     */
    public function search($moblie) {
        $class = new \MobileValidation();
        return $class->search($moblie);
    }

}
