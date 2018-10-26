<?php
// GENERATED CODE -- DO NOT EDIT!

namespace Mobile;

/**
 * 手机验证服务类
 */
class MobileValidationSerivceClient extends \Grpc\BaseStub {

    /**
     * @param string $hostname hostname
     * @param array $opts channel options
     * @param \Grpc\Channel $channel (optional) re-use channel object
     */
    public function __construct($hostname, $opts, $channel = null) {
        parent::__construct($hostname, $opts, $channel);
    }

    /**
     * 发送验证码
     * @param \Mobile\SendCodeInfo $argument input argument
     * @param array $metadata metadata
     * @param array $options call options
     */
    public function send(\Mobile\SendCodeInfo $argument,
      $metadata = [], $options = []) {
        return $this->_simpleRequest('/mobile.MobileValidationSerivce/send',
        $argument,
        ['\Mobile\SendCodeResult', 'decode'],
        $metadata, $options);
    }

    /**
     * 执行验证
     * @param \Mobile\ValidationCodeInfo $argument input argument
     * @param array $metadata metadata
     * @param array $options call options
     */
    public function validate(\Mobile\ValidationCodeInfo $argument,
      $metadata = [], $options = []) {
        return $this->_simpleRequest('/mobile.MobileValidationSerivce/validate',
        $argument,
        ['\Mobile\ValidationResult', 'decode'],
        $metadata, $options);
    }

    /**
     * 根据手机号码查询验证码（仅能够检索到24小时内且未被使用过的记录信息）
     * @param \Mobile\SearchCodeInfo $argument input argument
     * @param array $metadata metadata
     * @param array $options call options
     */
    public function search(\Mobile\SearchCodeInfo $argument,
      $metadata = [], $options = []) {
        return $this->_simpleRequest('/mobile.MobileValidationSerivce/search',
        $argument,
        ['\Mobile\SearchResultInfo', 'decode'],
        $metadata, $options);
    }

}
