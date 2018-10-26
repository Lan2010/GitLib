<?php
namespace Service\Nats\Logic;


class NatsLogic {

    private $nc;



    public function __construct()
    {
        vendor("nats.vendor.autoload");
        $connectionOptions = new \Nats\ConnectionOptions();
        $connectionOptions = $connectionOptions->setHost(NATS_HOSTNAME)->setPort(NATS_PORT);
        if(!empty(NATS_USER)) {
            $connectionOptions = $connectionOptions->setUser('foo');
        }
        if(!empty(NATS_USER)) {
            $connectionOptions = $connectionOptions->setPass('bar');
        }
        $this->nc = new \Nats\Connection($connectionOptions);
        $this->nc->connect();
    }



    /**
     * 推送APP的开关机
     */
    public function appOperation($tm,$data=array()) {
        $key = "oms.subject.application.operation";   //建
        $publish = $this->getPublishData($tm);
        $publish['appOperationTime'] = time() *1000;
        $publish['mobile'] = (empty($data['mobile']))? "": $data['mobile'];
        $publish["wxID" ] = "";
        $publish["ip"] = getIP();
        $publish['operationType'] = (empty($data['operationType']))? 0: $data['operationType'];
        $this->publish($key,json_encode($publish));
        $this->close();
    }


    /**
     * 是否绑定
     */
    public function bindOrUnbind($tm,$data=array()) {
        $key = "oms.subject.device.bind-unbind";   //建
        $publish = $this->getPublishData($tm);
        $publish['mobile'] = (empty($data['mobile']))? "": $data['mobile'];
        $publish["deviceId"] = (empty($data['device_id']))? "": $data['device_id'];//必填
        $publish["deviceType"] = (empty($data['device_type']))? "": $data['device_type'];//必填
        $publish["deviceMac"] = (empty($data['device_mac']))? "": $data['device_mac'] ;//选填
        $publish["deviceIp"] = getIP();
        $publish["deviceModel"] = (empty($data['device_model']))? "": $data['device_model'];
        $publish["deviceOperType"] =(empty($data['deviceOperType']))? "": $data['deviceOperType']; //选填
        $publish["operationTime"] = time() * 1000;
        $publish["operationType" ] = (!empty($data['operationType'])) ? 1 : 0 ;//必填(0=解除绑定，1=绑定)
        $this->publish($key,json_encode($publish));
        $this->close();
    }


    /**
     * 点击广告
     * @param $tm
     * @return mixed
     */
    public function clickAdvertisement($tm,$data=array()) {
        $key = "oms.subject.user.access-click.advertisement";   //建
        $publish = $this->getPublishData($tm);
        $publish["mobile"] = (empty($data['mobile']))? "": $data['mobile'];//选填
        $publish["advertId"] = (empty($data['advertId']))? "": $data['advertId'];//必填
        $publish["advertLink"] =(empty($data['advertLink']))? "": $data['advertLink'];
        $publish["advertType"] = (empty($data['advertType']))? "": $data['advertType'];//选填
        $publish["advertName"] = (empty($data['advertName']))? "": $data['advertName'];
        $publish["advertInfo"] = (empty($data['advertInfo']))? "": $data['advertInfo'];
        $publish["operationTime"] = time() * 1000; //必填
        $publish["operationType"] = 1;//必填(0=访问，1=点击)
        $publish["ip"] = getIP();
        $this->publish($key,json_encode($publish));
        $this->close();
    }

    /**用户注册信息
     * @param $tm
     * @return mixed
     */
    public function basicInfo($tm,$data=array()) {
        $key = "oms.subject.user.basic.info";   //建
        $publish = $this->getPublishData($tm);
        $publish["mobile"] = (empty($data['mobile']))? "": $data['mobile'];//选填
        $publish["email"] = null;//选填
        $publish["realName"] = null;//选填
        $publish["idCard"] = null;//选填
        $publish["nickName"] = null;//选填
        $publish["userFromType"] = "RANDOM";//必填, 用户来源[非渠道数据填写：RANDOM， 其他渠道，如推广，则填写推广渠道设置的名称-英文]
        $publish["avatar"] = null;//选填
        $publish["userOperType"] = "reg";//选填
        $publish["wxID"] = null;//选填
        $publish["qqID"] = null;//选填
        $publish["sinaWeiBoID"] = null;//选填
        $publish["regTime"] = time() * 1000;//必填
        $publish["ip"] = getIP();

        $this->publish($key,json_encode($publish));
        $this->close();
    }

    /**用户认知
     * @param $tm
     * @param array $data
     */
    public function userAuth($tm,$data=array()) {
        $key = "oms.subject.user.auth";   //建
        $publish = $this->getPublishData($tm);
        $publish["authType"] = (empty($data['authType']))? "": $data['authType']; //必填, [取值范围：AUTHIDCARD-实名认证，GENE-基因数据，ADDRESS-添加地址，ATTENTIONWEBCHAT-关注公众号，VOICEDISCERN-声音识别，FACEDEISCEERN-人脸识别，BINDBANK-绑定银行卡，HARDWAREBIND-绑定矿机, BINDADDRESSLIST=绑定通讯录]
        $publish["authStatus" ] = 1; //必填(0=失败，1=成功)
        $publish["authTime"] = time() * 1000;//必填

        $this->publish($key,json_encode($publish));
        $this->close();
    }


    /**登录登出
     * @param $tm
     * @return mixed
     */
    public function loginLogout($tm,$data=array()) {
        $key = "oms.subject.user.auth";   //建
        $publish = $this->getPublishData($tm);
        $publish["mobile"] = (empty($data['mobile']))? "": $data['mobile'];//选填
        $publish["operationTime"] = time() * 1000; //必填
        $publish["operationType"] = (empty($data['operationType']))? 0: $data['operationType'];//必填(0=登出，1=登录)
        $publish["wxID"] = null;    //选填
        $publish["qqID"] =  null; //选填
        $publish["sinaWeiBoID"] = null; //选填
        $publish["ip"] = getIP(); //必填

        $this->publish($key,json_encode($publish));
        $this->close();
    }


    /**
     * 接受任务
     * @param $tm
     * @return mixed
     */
    public function acceptFinished($tm,$data=array()) {
        $key = "oms.subject.user.auth";   //建
        $publish = $this->getPublishData($tm);
        $publish["mobile"] = (empty($data['mobile']))? "": $data['mobile'];//选填
        $publish["operationTime"] = time() * 1000; //必填
        $publish["operationType"] =  1;//必填(0=取消，1=接受)
        $publish["taskId"] = (empty($data['taskId']))? "": $data['taskId'] ;//必填
        $publish["taskName"] = (empty($data['taskName']))? "": $data['taskName'] ;//选填
        $publish["taskInfo"] = (empty($data['taskInfo']))? "": $data['taskInfo'] ;

        $this->publish($key,json_encode($publish));
        $this->close();
    }













    private function getPublishData($tm) {
        $return["id"] = null; //必填
        $return["createTime"] = time() * 1000; //必填
        $return["platformFrom"] = "WKAPP"; //必填
        $return["clientPlatformType"] = ($tm == 3)? "IOS" : "ANDROID";
        return $return;
    }



    /**
     * 关闭
     */
    private function close() {
        $this->nc->close();
    }

    /**
     * 推送
     */
    private function publish($key,$value) {
        $this->nc->publish($key,$value);
    }

}
