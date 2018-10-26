<?php
// GENERATED CODE -- DO NOT EDIT!

namespace Idcard;

/**
 * 身份证验证服务类
 */
class IDCardAuthSerivceClient extends \Grpc\BaseStub {

    /**
     * @param string $hostname hostname
     * @param array $opts channel options
     * @param \Grpc\Channel $channel (optional) re-use channel object
     */
    public function __construct($hostname, $opts, $channel = null) {
        parent::__construct($hostname, $opts, $channel);
    }

    /**
     * 验证身份证（请首先验证身份证基本规则信息）
     * @param \Idcard\IDCardInfo $argument input argument
     * @param array $metadata metadata
     * @param array $options call options
     */
    public function authIDCard(\Idcard\IDCardInfo $argument,
      $metadata = [], $options = []) {
        return $this->_simpleRequest('/idcard.IDCardAuthSerivce/authIDCard',
        $argument,
        ['\Idcard\AuthResult', 'decode'],
        $metadata, $options);
    }

}
