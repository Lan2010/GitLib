<?php
/**
 * Created by PhpStorm.
 * User: RLY
 * Date: 2018/6/27
 * Time: 10:31
 */

require dirname(__FILE__) . '/vendor/autoload.php';

@include_once dirname(__FILE__) . '/GPBMetadata/AccountStarpointOperation.php';
@include_once dirname(__FILE__) . '/GPBMetadata/ResponseEntity.php';
@include_once dirname(__FILE__) . '/ResponseEntity.php';
@include_once dirname(__FILE__) . '/Account/Starpoint/Operation/AccountStarPointOperationServiceClient.php';
@include_once dirname(__FILE__) . '/Account/Starpoint/Operation/CollectionResult.php';
@include_once dirname(__FILE__) . '/Account/Starpoint/Operation/WithdrawResult.php';
@include_once dirname(__FILE__) . '/Account/Starpoint/Operation/CollectionStarPointInfo.php';
@include_once dirname(__FILE__) . '/Account/Starpoint/Operation/StarPointRecordsInfo.php';
@include_once dirname(__FILE__) . '/Account/Starpoint/Operation/AwardInfo.php';
@include_once dirname(__FILE__) . '/Account/Starpoint/Operation/AwardResult.php';

@include_once dirname(__FILE__) . '/Account/Starpoint/Operation/BindAddressAwardInfo.php';





class AccountStarPointOperation
{
    private $client = null;

    public function __construct()
    {
        $this->client = new \Account\Starpoint\Operation\AccountStarPointOperationServiceClient(GRPC_HOSTNAME, [
            'credentials' => Grpc\ChannelCredentials::createInsecure(),
        ]);
    }

    /**
     * 收星星
     * @param $userToken
     * @param $recordToken
     * @return array|bool
     */
    function collection($userToken, $recordToken,$advertId="",$advertIdOperationType=0)
    {
        if (empty($userToken) || empty($recordToken)) {
            return false;
        }
        $records = array();
        $tokenArr = explode(",", $recordToken);
        foreach ($tokenArr as $item) {
            $record = new Account\Starpoint\Operation\StarPointRecordsInfo();
            $record->setRecordToken($item);
            $records[] = $record;
        }
        $request = new \Account\Starpoint\Operation\CollectionStarPointInfo();
        $request->setToken(GRPC_TOKEN);
        $request->setAccountToken($userToken);
        $request->setCollectionTime(time()*1000);
        $request->setRecords($records);
        $request->setAdvertIdOperationType($advertIdOperationType);
        if(!empty($advertId)) {
            $request->setAdvertId($advertId);
        }
        list($reply, $status) = $this->client->collection($request)->wait();
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
            "starpoint"=>$reply->getStarpoint(),
            "availableStarPoint" => $reply->getAvailableStarPoint(),
            "frozenStarPoint" => $reply->getFrozenStarPoint()
        );
    }



    /**
     * 奖励
     */
    function award($token,$adwardTime,$adwardType) {
        if(empty($token) || empty($adwardTime) || empty($adwardType)) {
            return false;
        }

        $request = new \Account\Starpoint\Operation\AwardInfo();
        $request->setAccountToken($token);
        $request->setAdwardTime($adwardTime);
        $request->setAdwardType($adwardType);
        $request->setToken(GRPC_TOKEN);

        list($reply, $status) = $this->client->award($request)->wait();
        if (empty($reply)) {
            return false;
        }
        $message = $reply->getResponseEntity();
        if (empty($message)) {
            return false;
        }

        return array(
            "availableStarPoint"=>$reply->getAvailableStarPoint(),
            "frozenStarPoint"=>$reply->getFrozenStarPoint(),
            "starPoint"=>$reply->getStarPoint(),
            "code"=>$message->getCode(),
            "msg"=>$message->getMessage(),
        );
    }


    /**
     * 绑定通讯录
     */
    function bindAddressList($token,$adwardTime,$count) {
        if(empty($token) || empty($count) || empty($adwardTime)) {
            return false;
        }
        $request = new \Account\Starpoint\Operation\BindAddressAwardInfo();
        $request->setAccountToken($token);
        $request->setToken(GRPC_TOKEN);
        $request->setAdwardTime($adwardTime);
        $request->setContactCount($count);

        list($reply, $status) = $this->client->bindAddressList($request)->wait();
        if (empty($reply)) {
            return false;
        }

        $message = $reply->getResponseEntity();
        if (empty($message)) {
            return false;
        }

        return array(
            "availableStarPoint"=>$reply->getAvailableStarPoint(),
            "frozenStarPoint"=>$reply->getFrozenStarPoint(),
            "starPoint"=>$reply->getStarPoint(),
            "code"=>$message->getCode(),
            "msg"=>$message->getMessage(),
        );

    }
}