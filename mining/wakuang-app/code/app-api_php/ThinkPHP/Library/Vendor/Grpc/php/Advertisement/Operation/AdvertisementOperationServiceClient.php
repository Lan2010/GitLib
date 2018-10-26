<?php
// GENERATED CODE -- DO NOT EDIT!

namespace Advertisement\Operation;

/**
 * 广告操作服务
 */
class AdvertisementOperationServiceClient extends \Grpc\BaseStub {

    /**
     * @param string $hostname hostname
     * @param array $opts channel options
     * @param \Grpc\Channel $channel (optional) re-use channel object
     */
    public function __construct($hostname, $opts, $channel = null) {
        parent::__construct($hostname, $opts, $channel);
    }

    /**
     * 广告操作接口
     * @param \Advertisement\Operation\AdvertisementInfo $argument input argument
     * @param array $metadata metadata
     * @param array $options call options
     */
    public function clickOrAccess(\Advertisement\Operation\AdvertisementInfo $argument,
      $metadata = [], $options = []) {
        return $this->_simpleRequest('/advertisement.operation.AdvertisementOperationService/clickOrAccess',
        $argument,
        ['\Advertisement\Operation\ClickResult', 'decode'],
        $metadata, $options);
    }

}
