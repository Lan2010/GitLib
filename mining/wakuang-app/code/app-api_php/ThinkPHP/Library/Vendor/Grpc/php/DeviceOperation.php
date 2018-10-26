<?php
/**
 * Created by PhpStorm.
 * User: RLY
 * Date: 2018/6/27
 * Time: 10:49
 */

require dirname(__FILE__) . '/vendor/autoload.php';

@include_once dirname(__FILE__) . '/GPBMetadata/DeviceOperation.php';
@include_once dirname(__FILE__) . '/GPBMetadata/ResponseEntity.php';
@include_once dirname(__FILE__) . '/ResponseEntity.php';
@include_once dirname(__FILE__) . '/Device/Operation/DeviceOperationServiceClient.php';
@include_once dirname(__FILE__) . '/Device/Operation/Result.php';
@include_once dirname(__FILE__) . '/Device/Operation/DeviceInfo.php';


class DeviceOperation
{

    private $client = null;

    public function __construct()
    {
        $this->client = new \Device\Operation\DeviceOperationServiceClient(GRPC_HOSTNAME, [
            'credentials' => Grpc\ChannelCredentials::createInsecure(),
        ]);
    }

    /**
     * 绑定设备
     * @param $userToken
     * @param $deviceId
     * @param $deviceType
     * @param $deviceMAC
     * @param $deviceModel
     * @return array|bool
     */
    function bind($userToken,$deviceId,$deviceType,$deviceMAC,$deviceModel){

        if (empty($userToken) || empty($deviceId) || empty($deviceType) || empty($deviceMAC) || empty($deviceModel)) {
            return false;
        }

        $request=new Device\Operation\DeviceInfo();
        $request->setToken(GRPC_TOKEN);
        $request->setAccountToken($userToken);
        $request->setDeviceId($deviceId);
        $request->setDeviceType($deviceType);
        $request->setDeviceMAC($deviceMAC);
        $request->setDeviceModel($deviceModel);
        $request->setOperationTime(time()*1000);

        list($reply, $status) = $this->client->bind($request)->wait();
        if (empty($reply)) {
            return false;
        }
        $message = $reply->getResponseEntity();
        if (empty($message)) {
            return false;
        }

        return array(
            "code" => $message->getCode(),
            "msg" => $message->getMessage()
        );
    }

    /**
     * 解绑设备
     * @param $userToken
     * @param $deviceId
     * @param $deviceType
     * @param $deviceMAC
     * @param $deviceModel
     * @return array|bool
     */
    function unBind($userToken,$deviceId,$deviceType,$deviceMAC,$deviceModel){

        if (empty($userToken) || empty($deviceId) || empty($deviceType) || empty($deviceMAC) || empty($deviceModel)) {
            return false;
        }

        $request=new Device\Operation\DeviceInfo();
        $request->setToken(GRPC_TOKEN);
        $request->setAccountToken($userToken);
        $request->setDeviceId($deviceId);
        $request->setDeviceType($deviceType);
        $request->setDeviceMAC($deviceMAC);
        $request->setDeviceModel($deviceModel);

        list($reply, $status) = $this->client->unBind($request)->wait();
        if (empty($reply)) {
            return false;
        }
        $message = $reply->getResponseEntity();
        if (empty($message)) {
            return false;
        }

        return array(
            "code" => $message->getCode(),
            "msg" => $message->getMessage()
        );
    }
}