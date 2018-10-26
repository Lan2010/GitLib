<?php
// GENERATED CODE -- DO NOT EDIT!

namespace Account\Reg;

/**
 * 账户注册服务
 */
class AccountRegServiceClient extends \Grpc\BaseStub {

    /**
     * @param string $hostname hostname
     * @param array $opts channel options
     * @param \Grpc\Channel $channel (optional) re-use channel object
     */
    public function __construct($hostname, $opts, $channel = null) {
        parent::__construct($hostname, $opts, $channel);
    }

    /**
     * 注册
     * @param \Account\Reg\AccountInfo $argument input argument
     * @param array $metadata metadata
     * @param array $options call options
     */
    public function reg(\Account\Reg\AccountInfo $argument,
      $metadata = [], $options = []) {
        return $this->_simpleRequest('/account.reg.AccountRegService/reg',
        $argument,
        ['\Account\Reg\Result', 'decode'],
        $metadata, $options);
    }

    /**
     * 检查手机号
     * @param \Account\Reg\CheckMobileInfo $argument input argument
     * @param array $metadata metadata
     * @param array $options call options
     */
    public function check(\Account\Reg\CheckMobileInfo $argument,
      $metadata = [], $options = []) {
        return $this->_simpleRequest('/account.reg.AccountRegService/check',
        $argument,
        ['\Account\Reg\CheckMobileResult', 'decode'],
        $metadata, $options);
    }

}
