<?php
/**
 * Created by PhpStorm.
 * User: RLY
 * Date: 2018/6/27
 * Time: 10:31
 */

require dirname(__FILE__) . '/vendor/autoload.php';

@include_once dirname(__FILE__) . '/GPBMetadata/TaskOperation.php';
@include_once dirname(__FILE__) . '/GPBMetadata/ResponseEntity.php';
@include_once dirname(__FILE__) . '/ResponseEntity.php';
@include_once dirname(__FILE__) . '/Task/Operation/TaskOperationServiceClient.php';
@include_once dirname(__FILE__) . '/Task/Operation/TaskInfo.php';
@include_once dirname(__FILE__) . '/Task/Operation/Result.php';


class TaskOperation
{
    private $client = null;

    public function __construct()
    {
        $this->client = new \Task\Operation\TaskOperationServiceClient(GRPC_HOSTNAME, [
            'credentials' => Grpc\ChannelCredentials::createInsecure(),
        ]);
    }

    /**
     * 收星星
     * @param $userToken
     * @param $recordToken
     * @return array|bool
     */
    function accept($userToken,$taskID)
    {
        if (empty($userToken) || empty($taskID)) {
            return false;
        }
        $request = new Task\Operation\TaskInfo();
        $request->setToken(GRPC_TOKEN);
        $request->setAccountToken($userToken);
        $request->setTaskId($taskID);
        $request->setOperationTime(time()*1000);

        list($reply, $status) = $this->client->accept($request)->wait();
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
        );
    }
}