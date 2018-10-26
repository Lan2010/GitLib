<?php
# Generated by the protocol buffer compiler.  DO NOT EDIT!
# source: device_operation.proto

namespace Device\Operation;

use Google\Protobuf\Internal\GPBType;
use Google\Protobuf\Internal\RepeatedField;
use Google\Protobuf\Internal\GPBUtil;

/**
 *设备绑定信息
 *
 * Generated from protobuf message <code>device.operation.DeviceInfo</code>
 */
class DeviceInfo extends \Google\Protobuf\Internal\Message
{
    /**
     *accountToken-必填
     *
     * Generated from protobuf field <code>string accountToken = 1;</code>
     */
    private $accountToken = '';
    /**
     *安全校验码-必填
     *
     * Generated from protobuf field <code>string token = 2;</code>
     */
    private $token = '';
    /**
     *设备ID-必填
     *
     * Generated from protobuf field <code>string deviceId = 3;</code>
     */
    private $deviceId = '';
    /**
     *设备类型 - 必填
     *
     * Generated from protobuf field <code>string deviceType = 4;</code>
     */
    private $deviceType = '';
    /**
     *设备MAC地址 - 必填
     *
     * Generated from protobuf field <code>string deviceMAC = 5;</code>
     */
    private $deviceMAC = '';
    /**
     *设备型号 - 必填
     *
     * Generated from protobuf field <code>string deviceModel = 6;</code>
     */
    private $deviceModel = '';
    /**
     *绑定时间 - 必填
     *
     * Generated from protobuf field <code>int64 operationTime = 7;</code>
     */
    private $operationTime = 0;

    public function __construct() {
        \GPBMetadata\DeviceOperation::initOnce();
        parent::__construct();
    }

    /**
     *accountToken-必填
     *
     * Generated from protobuf field <code>string accountToken = 1;</code>
     * @return string
     */
    public function getAccountToken()
    {
        return $this->accountToken;
    }

    /**
     *accountToken-必填
     *
     * Generated from protobuf field <code>string accountToken = 1;</code>
     * @param string $var
     * @return $this
     */
    public function setAccountToken($var)
    {
        GPBUtil::checkString($var, True);
        $this->accountToken = $var;

        return $this;
    }

    /**
     *安全校验码-必填
     *
     * Generated from protobuf field <code>string token = 2;</code>
     * @return string
     */
    public function getToken()
    {
        return $this->token;
    }

    /**
     *安全校验码-必填
     *
     * Generated from protobuf field <code>string token = 2;</code>
     * @param string $var
     * @return $this
     */
    public function setToken($var)
    {
        GPBUtil::checkString($var, True);
        $this->token = $var;

        return $this;
    }

    /**
     *设备ID-必填
     *
     * Generated from protobuf field <code>string deviceId = 3;</code>
     * @return string
     */
    public function getDeviceId()
    {
        return $this->deviceId;
    }

    /**
     *设备ID-必填
     *
     * Generated from protobuf field <code>string deviceId = 3;</code>
     * @param string $var
     * @return $this
     */
    public function setDeviceId($var)
    {
        GPBUtil::checkString($var, True);
        $this->deviceId = $var;

        return $this;
    }

    /**
     *设备类型 - 必填
     *
     * Generated from protobuf field <code>string deviceType = 4;</code>
     * @return string
     */
    public function getDeviceType()
    {
        return $this->deviceType;
    }

    /**
     *设备类型 - 必填
     *
     * Generated from protobuf field <code>string deviceType = 4;</code>
     * @param string $var
     * @return $this
     */
    public function setDeviceType($var)
    {
        GPBUtil::checkString($var, True);
        $this->deviceType = $var;

        return $this;
    }

    /**
     *设备MAC地址 - 必填
     *
     * Generated from protobuf field <code>string deviceMAC = 5;</code>
     * @return string
     */
    public function getDeviceMAC()
    {
        return $this->deviceMAC;
    }

    /**
     *设备MAC地址 - 必填
     *
     * Generated from protobuf field <code>string deviceMAC = 5;</code>
     * @param string $var
     * @return $this
     */
    public function setDeviceMAC($var)
    {
        GPBUtil::checkString($var, True);
        $this->deviceMAC = $var;

        return $this;
    }

    /**
     *设备型号 - 必填
     *
     * Generated from protobuf field <code>string deviceModel = 6;</code>
     * @return string
     */
    public function getDeviceModel()
    {
        return $this->deviceModel;
    }

    /**
     *设备型号 - 必填
     *
     * Generated from protobuf field <code>string deviceModel = 6;</code>
     * @param string $var
     * @return $this
     */
    public function setDeviceModel($var)
    {
        GPBUtil::checkString($var, True);
        $this->deviceModel = $var;

        return $this;
    }

    /**
     *绑定时间 - 必填
     *
     * Generated from protobuf field <code>int64 operationTime = 7;</code>
     * @return int|string
     */
    public function getOperationTime()
    {
        return $this->operationTime;
    }

    /**
     *绑定时间 - 必填
     *
     * Generated from protobuf field <code>int64 operationTime = 7;</code>
     * @param int|string $var
     * @return $this
     */
    public function setOperationTime($var)
    {
        GPBUtil::checkInt64($var);
        $this->operationTime = $var;

        return $this;
    }

}
