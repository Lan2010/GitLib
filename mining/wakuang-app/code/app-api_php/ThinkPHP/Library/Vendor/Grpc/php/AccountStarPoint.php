<?php

require dirname(__FILE__) . '/vendor/autoload.php';

@include_once dirname(__FILE__) . '/Account/Starpoint/AccountInfo.php';
@include_once dirname(__FILE__) . '/Account/Starpoint/RankingInfo.php';
@include_once dirname(__FILE__) . '/Account/Starpoint/PageMapper.php';
@include_once dirname(__FILE__) . '/Account/Starpoint/RecordsInfo.php';
@include_once dirname(__FILE__) . '/Account/Starpoint/UnCollectionRecordsInfo.php';
@include_once dirname(__FILE__) . '/Account/Starpoint/RankingRecordsInfo.php';
@include_once dirname(__FILE__) . '/Account/Starpoint/UnCollectionRecordsCondition.php';
@include_once dirname(__FILE__) . '/Account/Starpoint/RecordsCondition.php';
@include_once dirname(__FILE__) . '/Account/Starpoint/AccountStarPointServiceClient.php';
@include_once dirname(__FILE__) . '/Account/Starpoint/Result.php';
@include_once dirname(__FILE__) . '/Account/Starpoint/RankingResult.php';
@include_once dirname(__FILE__) . '/Account/Starpoint/UnCollectionRecordsResult.php';
@include_once dirname(__FILE__) . '/Account/Starpoint/RecordsResult.php';
@include_once dirname(__FILE__) . '/Account/Starpoint/RecordsWithDayInfo.php';
@include_once dirname(__FILE__) . '/Account/Starpoint/RecordsWithDayCondition.php';
@include_once dirname(__FILE__) . '/Account/Starpoint/RecordsWithDayResult.php';
@include_once dirname(__FILE__) . '/Account/Starpoint/StarPointWithTaskInfo.php';
@include_once dirname(__FILE__) . '/Account/Starpoint/StarPointWithTaskResult.php';
@include_once dirname(__FILE__) . '/Account/Starpoint/StarPointWithTaskResultDetail.php';
@include_once dirname(__FILE__) . '/GPBMetadata/AccountStarpoint.php';
@include_once dirname(__FILE__) . '/GPBMetadata/ResponseEntity.php';
@include_once dirname(__FILE__) . '/ResponseEntity.php';

class AccountStarPoint
{
    private $client = null;

    public function __construct()
    {
        $this->client = new \Account\Starpoint\AccountStarPointServiceClient(GRPC_HOSTNAME, [
            'credentials' => Grpc\ChannelCredentials::createInsecure(),
        ]);
    }

    /**
     * 星星排行榜
     * @param $accToken
     * @return array|bool
     */
    function ranking($pageindex = 0, $pagesize = 20)
    {
        $page = new \Account\Starpoint\PageMapper();
        $page->setFrom(intval($pageindex));
        $page->setSize(intval($pagesize));
        $request = new \Account\Starpoint\RankingInfo();
        $request->setPageMapper($page);
        $request->setToken(GRPC_TOKEN);

        list($reply, $status) = $this->client->ranking($request)->wait();
        if (empty($reply)) {
            return false;
        }
        $message = $reply->getResponseEntity();
        if (empty($message)) {
            return false;
        }

        $records = array();
        $code = $message->getCode() * 1;
        if ($code == 200) {
            $recordList = $reply->getRecords();
            foreach ($recordList as $record) {
                $records[] = array(
                    "thirdToken" => $record->getThirdToken(),
                    "accountToken" => $record->getAccountToken(),
                    "starPoint" => $record->getStarPoint()
                );
            }


        }

        return array(
            "code" => $code,
            "msg" => $message->getMessage(),
            "total" => $reply->getTotal(),
            "records" => $records
        );
    }

    /**
     * 获取账户信息
     * @param $userToken
     * @return bool
     */
    function starPoint($userToken)
    {
        if (empty($userToken)) {
            return false;
        }
        $request = new \Account\Starpoint\AccountInfo();
        $request->setToken(GRPC_TOKEN);
        $request->setAccountToken($userToken);
        list($reply, $status) = $this->client->starPoint($request)->wait();
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
            "availableStarPoint" => $reply->getAvailableStarPoint(),
            "frozenStarPoint" => $reply->getFrozenStarPoint()
        );
    }


    function records($userToken, $pageindex = 1, $pagesize = 20, $recordsType = "ALL")
    {
        if (empty($userToken)) {
            return false;
        }
        $page = new \Account\Starpoint\PageMapper();
        $page->setFrom(intval($pageindex));
        $page->setSize(intval($pagesize));

        $request = new \Account\Starpoint\RecordsCondition();
        $request->setToken(GRPC_TOKEN);
        $request->setAccountToken($userToken);
        $request->setPageMapper($page);
        $request->setRecordsType($recordsType);

        list($reply, $status) = $this->client->records($request)->wait();
        if (empty($reply)) {
            return false;
        }
        $message = $reply->getResponseEntity();
        if (empty($message)) {
            return false;
        }
        $records = array();
        $code = $message->getCode() * 1;
        if ($code == 200) {
            $recordList = $reply->getRecords();
            foreach ($recordList as $record) {
                $records[] = array(
                    "operStarPoint" => $record->getOperStarPoint(),
                    "operationType" => $record->getOperationType(),
                    "recordsType" => $record->getRecordsType(),
                    "taskId" => $record->getTaskId(),
                    "advertisementId" => $record->getAdvertisementId(),
                    "remark" => $record->getRemark(),
                    "createTime" => $record->getCreateTime()
                );
            }


        }
        return array(
            "code" => $code,
            "msg" => $message->getMessage(),
            "total" => $reply->getTotal(),
            "records" => $records
        );
    }

    /**
     * 查询未采集的星点记录
     * @param $userToken
     * @param int $beginTime
     * @param int $endTime
     * @return bool
     */
    function unCollectionRecords($userToken, $beginTime = 0, $endTime = 0)
    {
        if (empty($userToken)) {
            return false;
        }
        $request = new \Account\Starpoint\UnCollectionRecordsCondition();
        $request->setAccountToken($userToken);
        $request->setToken(GRPC_TOKEN);
        $request->setBeginTime($beginTime);
        $request->setEndTime($endTime);
        list($reply, $status) = $this->client->unCollectionRecords($request)->wait();
        if (empty($reply)) {
            return false;
        }
        $message = $reply->getResponseEntity();
        if (empty($message)) {
            return false;
        }

        $records = array();
        $code = $message->getCode() * 1;
        if ($code == 200) {
            $recordList = $reply->getRecords();
            foreach ($recordList as $record) {
                $records[] = array(
                    "operStarPoint" => $record->getOperStarPoint(),
                    "longitudeAndLatitude" => $record->getLongitudeAndLatitude(),
                    "recordsType" => $record->getRecordsType(),
                    "taskId" => $record->getTaskId(),
                    "advertisementId" => $record->getAdvertisementId(),
                    "remark" => $record->getRemark(),
                    "createTime" => $record->getCreateTime(),
                    "recordToken" => $record->getRecordToken()
                );
            }


        }

        return array(
            "code" => $code,
            "msg" => $message->getMessage(),
            "records" => $records
        );
    }


    /**
     * 获取到当前日期的星星流水
     */
    public function recordsWithDay($userToken,$days,$endTime) {
        if(empty($userToken)||empty($days) || empty($endTime))  {
            return false;
        }
        $request = new \Account\Starpoint\RecordsWithDayCondition();

        $request->setToken(GRPC_TOKEN);
        $request->setAccountToken($userToken);
        $request->setDays($days);
        $request->setEndTime($endTime);
        list($reply, $status) = $this->client->recordsWithDay($request)->wait();
        if (empty($reply)) {
            return false;
        }
        $message = $reply->getResponseEntity();
        if (empty($message)) {
            return false;
        }
        $info = $reply->getRecords();
        $rec = array();
        foreach ($info as $key => $value) {
        	$rec[] = array(
        		"starPoint"=>$value->getStarPoint(),
        		"day"=>date('m-d',strtotime($value->getDate())),
        	); 
        }
        return array(
        	"code"=>$message->getCode(),
        	"msg"=>$message->getMessage(),
        	"info"=>$rec,
        );
    }


    /**
     * 根据任务获取到用户的星点数
     */
    public function starPointWithTask($userToken,$taskIdArr) {
        if(empty($userToken) || empty($taskIdArr)) {
            return false;
        }
        $request = new \Account\Starpoint\StarPointWithTaskInfo();
        $request->setToken(GRPC_TOKEN);
        $request->setAccountToken($userToken);
        $request->setTaskIds($taskIdArr);

        list($reply, $status) = $this->client->starPointWithTask($request)->wait();

        if (empty($reply)) {
            return false;
        }
        $message = $reply->getResponseEntity();
        if (empty($message)) {
            return false;
        }

        $info = $reply->getRecords();

        $rec = array();
        foreach ($info as $key=>$value) {
            $rec[] = array(
                "starPoint"=>$value->getStarPoint(),
                "taskId"=>$value->getTaskId(),
            );
        }

        return array(
            "code"=>$message->getCode(),
            "msg"=>$message->getMessage(),
            "info"=>$rec,
        );
    }

}