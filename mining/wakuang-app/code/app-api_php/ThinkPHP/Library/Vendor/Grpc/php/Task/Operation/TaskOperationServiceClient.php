<?php
// GENERATED CODE -- DO NOT EDIT!

namespace Task\Operation;

/**
 * 任务操作服务
 */
class TaskOperationServiceClient extends \Grpc\BaseStub {

    /**
     * @param string $hostname hostname
     * @param array $opts channel options
     * @param \Grpc\Channel $channel (optional) re-use channel object
     */
    public function __construct($hostname, $opts, $channel = null) {
        parent::__construct($hostname, $opts, $channel);
    }

    /**
     * 接受任务
     * @param \Task\Operation\TaskInfo $argument input argument
     * @param array $metadata metadata
     * @param array $options call options
     */
    public function accept(\Task\Operation\TaskInfo $argument,
      $metadata = [], $options = []) {
        return $this->_simpleRequest('/task.operation.TaskOperationService/accept',
        $argument,
        ['\Task\Operation\Result', 'decode'],
        $metadata, $options);
    }

    /**
     * 取消任务
     * @param \Task\Operation\TaskInfo $argument input argument
     * @param array $metadata metadata
     * @param array $options call options
     */
    public function cancel(\Task\Operation\TaskInfo $argument,
      $metadata = [], $options = []) {
        return $this->_simpleRequest('/task.operation.TaskOperationService/cancel',
        $argument,
        ['\Task\Operation\Result', 'decode'],
        $metadata, $options);
    }

}
