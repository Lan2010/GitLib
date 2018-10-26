<?php
// GENERATED CODE -- DO NOT EDIT!

namespace Account\Starpoint\Operation;

/**
 * 账户星点操作服务
 */
class AccountStarPointOperationServiceClient extends \Grpc\BaseStub {

    /**
     * @param string $hostname hostname
     * @param array $opts channel options
     * @param \Grpc\Channel $channel (optional) re-use channel object
     */
    public function __construct($hostname, $opts, $channel = null) {
        parent::__construct($hostname, $opts, $channel);
    }

    /**
     * 采集
     * @param \Account\Starpoint\Operation\CollectionStarPointInfo $argument input argument
     * @param array $metadata metadata
     * @param array $options call options
     */
    public function collection(\Account\Starpoint\Operation\CollectionStarPointInfo $argument,
      $metadata = [], $options = []) {
        return $this->_simpleRequest('/account.starpoint.operation.AccountStarPointOperationService/collection',
        $argument,
        ['\Account\Starpoint\Operation\CollectionResult', 'decode'],
        $metadata, $options);
    }

    /**
     * 提现 - 暂时未开启
     * @param \Account\Starpoint\Operation\WithdrawInfo $argument input argument
     * @param array $metadata metadata
     * @param array $options call options
     */
    public function withdraw(\Account\Starpoint\Operation\WithdrawInfo $argument,
      $metadata = [], $options = []) {
        return $this->_simpleRequest('/account.starpoint.operation.AccountStarPointOperationService/withdraw',
        $argument,
        ['\Account\Starpoint\Operation\WithdrawResult', 'decode'],
        $metadata, $options);
    }

    /**
     * 奖励
     * @param \Account\Starpoint\Operation\AwardInfo $argument input argument
     * @param array $metadata metadata
     * @param array $options call options
     */
    public function award(\Account\Starpoint\Operation\AwardInfo $argument,
      $metadata = [], $options = []) {
        return $this->_simpleRequest('/account.starpoint.operation.AccountStarPointOperationService/award',
        $argument,
        ['\Account\Starpoint\Operation\AwardResult', 'decode'],
        $metadata, $options);
    }

    /**
     * 绑定通讯录奖励
     * @param \Account\Starpoint\Operation\BindAddressAwardInfo $argument input argument
     * @param array $metadata metadata
     * @param array $options call options
     */
    public function bindAddressList(\Account\Starpoint\Operation\BindAddressAwardInfo $argument,
      $metadata = [], $options = []) {
        return $this->_simpleRequest('/account.starpoint.operation.AccountStarPointOperationService/bindAddressList',
        $argument,
        ['\Account\Starpoint\Operation\AwardResult', 'decode'],
        $metadata, $options);
    }

}
