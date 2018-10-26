<?php

namespace MobileApi\Api;

use MobileApi\Common\MobileApiBase;
use Service\Charge\Logic\ChargeLogic;
use Service\Nats\Logic\NatsLogic;
use Service\User\Logic\StarModuleLogic;
use Service\User\Logic\UserLogic;

/**
 * Description of index
 *
 * @author Administrator
 */
class ApiCharge extends MobileApiBase {

    public $return = array('code' => 0, 'msg' => 'Welcome to use Api!', 'info' => array());

    public function getRules() {
        return array(
            'bind' => array(
              "Mac" => array("name" => "Mac", "type" => "string", "require" => true, "desc" => "Mac地址"),
            ),
        );
    }

    /**
     * 绑定
     * @desc 绑定设备 
     * @return   int       code                          操作码，0表示成功， 1表示失败
     * @return   array    info
     * @return   int       info.msg                返回的参数
     * @return   string    msg                           提示信息
     */
    public function bind() {
        $this->userCheck();
        $devid = GetParam($this->Mac,'devid');
        if(empty($devid)) {
            $this->return['code'] = 1;
            $this->return['msg'] = "扫码信息不对";
            return $this->return;
        }
        $charge = new ChargeLogic();
        $charge_info = $charge->getBindInfo($devid);   //获取到绑定的信息
        if(empty($charge_info)) {
            $this->return['code'] = 1;
            $this->return['msg'] = "扫码信息不对";
            return $this->return;
        }
        if($charge_info['state'] == 2) {   //当为绑定状态的时候
            if($this->userID == $charge_info['bing_user_id']) {
                $this->return['msg'] = "您已经绑定该充电宝";
            } else {
                $this->return['msg'] = "该充电宝已经被别人绑定";
            }
            $this->return['code'] = 1;
            return $this->return;
        }
        vendor("Grpc.php.DeviceOperation");
        $userlogic = new UserLogic();
        $user_info = $userlogic->getUser($this->userID);
        $client = new \DeviceOperation();   //GRPC访问
        $result = $client->bind($user_info['user_token'],$charge_info['device_id'],$charge_info['device_type'],$charge_info['device_mac'],$charge_info['device_model']);

        if(empty($result)) {
        	$this->return["code"] = 1;
        	$this->return["msg"] = "grpc错误";
        	return $this->return;
        }

        if ($result) {
            $this->return['code'] = ($result["code"] == 200 ? 0 : 1);
            $this->return['msg'] = $result['msg'];
            if($result["code"] != 200) {
                return $this->return;
            }
        }

        $res = $charge->updateBindInfo($this->userID,$devid);
        if($res) {
            $userlogic = new StarModuleLogic();
            $userlogic->addModule($this->userID,'Charge');  //添加矿机绑定

            $award_logic = new UserLogic();    //grpc 增加奖励
            $award_logic->award($this->userID,"HARDWAREBIND");

            //nats推送
            $natsLogic = new NatsLogic();
            $publish = $charge_info;
            $publish['mobile'] = $this->userPhone;
            $publish['operationType'] = 1;
            $natsLogic->bindOrUnbind($this->terminal,$publish);  //nats 推送 运营平台

            $this->return['msg'] = "绑定成功";
        } else {
            $this->return['code'] = 1;
            $this->return['msg'] = "绑定成功";
        }
        return $this->return;
    }

    /**
     * 解绑操作
     * @desc 就是解绑操作
     * @return int code
     * @return   array    info
     * @return   int       info.msg                返回的参数
     * @return   string    msg                           提示信息
     */
    public function unbindCharge() {
        $this->userCheck();
        $charge = new ChargeLogic();
        $info = $charge->getInfoByUser($this->userID);
        if(empty($info)) {
            $this->return['code'] = 0;
            $this->return['msg'] = "您尚未绑定任何矿机";
            return $this->return;
        }
        $res = $charge->unbind($this->userID,$info['mac']);
        if($res) {
            $userlogic = new StarModuleLogic();
            $userlogic->updateModule($this->userID,'Charge',0);  //添加矿机绑定
            $this->return['msg'] = "解绑成功";
        } else {
            $this->return['code'] = 1;
            $this->return['msg'] = "解绑失败";
        }
        return $this->return;
    }


}
