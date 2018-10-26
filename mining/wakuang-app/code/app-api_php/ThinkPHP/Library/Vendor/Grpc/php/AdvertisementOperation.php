<?php
/**
 * Created by PhpStorm.
 * User: RLY
 * Date: 2018/6/27
 * Time: 10:49
 */

require dirname(__FILE__) . '/vendor/autoload.php';

@include_once dirname(__FILE__) . '/GPBMetadata/AdvertisementOperation.php';
@include_once dirname(__FILE__) . '/GPBMetadata/ResponseEntity.php';
@include_once dirname(__FILE__) . '/ResponseEntity.php';
@include_once dirname(__FILE__) . '/Advertisement/Operation/AdvertisementOperationServiceClient.php';
@include_once dirname(__FILE__) . '/Advertisement/Operation/ClickResult.php';
@include_once dirname(__FILE__) . '/Advertisement/Operation/AdvertisementInfo.php';


class AdvertisementOperation
{

    private $client = null;

    public function __construct()
    {
        $this->client = new \Advertisement\Operation\AdvertisementOperationServiceClient(GRPC_HOSTNAME, [
            'credentials' => Grpc\ChannelCredentials::createInsecure(),
        ]);
    }

    /**
     * 广告点击
     * @param $userToken
     * @param $advertId
     * @param $starPoint
     * @return bool
     */
    function click($userToken,$advertId,$starPoint,$type=0){
        if (empty($userToken) || empty($advertId) || $starPoint*1<=0){
            return false;
        }

        $request=new Advertisement\Operation\AdvertisementInfo();
        $request->setToken(GRPC_TOKEN);
        $request->setAccountToken($userToken);
        $request->setAdvertId($advertId);
        $request->setStarPoint($starPoint);
        $request->setClickTime(time()*1000);
        $request->setOperationType($type);

        list($reply, $status) = $this->client->clickOrAccess($request)->wait();
        if (empty($reply)) {
            return false;
        }
        $message = $reply->getResponseEntity();
        if (empty($message)) {
            return false;
        }

        return array(
            "code" => $message->getCode(),
            "msg" => $message->getMessage(),
            "starPoint"=>$reply->getStarPoint(),
            "availableStarPoint"=>$reply->getAvailableStarPoint(),
            "frozenStarPoint"=>$reply->getFrozenStarPoint(),
        );
    }
}