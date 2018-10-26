<?php
// GENERATED CODE -- DO NOT EDIT!

namespace Device\Operation;

/**
 * 设备操作服务
 */
class DeviceOperationServiceClient extends \Grpc\BaseStub {

    /**
     * @param string $hostname hostname
     * @param array $opts channel options
     * @param \Grpc\Channel $channel (optional) re-use channel object
     */
    public function __construct($hostname, $opts, $channel = null) {
        parent::__construct($hostname, $opts, $channel);
    }

    /**
     * 绑定
     * @param \Device\Operation\DeviceInfo $argument input argument
     * @param array $metadata metadata
     * @param array $options call options
     */
    public function bind(\Device\Operation\DeviceInfo $argument,
      $metadata = [], $options = []) {
        return $this->_simpleRequest('/device.operation.DeviceOperationService/bind',
        $argument,
        ['\Device\Operation\Result', 'decode'],
        $metadata, $options);
    }

    /**
     * 解除绑定
     * @param \Device\Operation\DeviceInfo $argument input argument
     * @param array $metadata metadata
     * @param array $options call options
     */
    public function unBind(\Device\Operation\DeviceInfo $argument,
      $metadata = [], $options = []) {
        return $this->_simpleRequest('/device.operation.DeviceOperationService/unBind',
        $argument,
        ['\Device\Operation\Result', 'decode'],
        $metadata, $options);
    }

}
