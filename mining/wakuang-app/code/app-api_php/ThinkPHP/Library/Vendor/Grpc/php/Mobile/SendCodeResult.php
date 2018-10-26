<?php
# Generated by the protocol buffer compiler.  DO NOT EDIT!
# source: MobileValidationService.proto

namespace Mobile;

use Google\Protobuf\Internal\GPBType;
use Google\Protobuf\Internal\RepeatedField;
use Google\Protobuf\Internal\GPBUtil;

/**
 *发送验证码反馈结果
 *
 * Generated from protobuf message <code>mobile.SendCodeResult</code>
 */
class SendCodeResult extends \Google\Protobuf\Internal\Message
{
    /**
     *200成功，300业务错误
     *
     * Generated from protobuf field <code>int32 code = 1;</code>
     */
    private $code = 0;
    /**
     *信息
     *
     * Generated from protobuf field <code>string message = 2;</code>
     */
    private $message = '';
    /**
     *手机号
     *
     * Generated from protobuf field <code>string mobile = 3;</code>
     */
    private $mobile = '';
    /**
     *授权码-验证时使用
     *
     * Generated from protobuf field <code>string codeToken = 4;</code>
     */
    private $codeToken = '';
    /**
     *发送平台
     *
     * Generated from protobuf field <code>string sendPlatform = 5;</code>
     */
    private $sendPlatform = '';

    public function __construct() {
        \GPBMetadata\MobileValidationService::initOnce();
        parent::__construct();
    }

    /**
     *200成功，300业务错误
     *
     * Generated from protobuf field <code>int32 code = 1;</code>
     * @return int
     */
    public function getCode()
    {
        return $this->code;
    }

    /**
     *200成功，300业务错误
     *
     * Generated from protobuf field <code>int32 code = 1;</code>
     * @param int $var
     * @return $this
     */
    public function setCode($var)
    {
        GPBUtil::checkInt32($var);
        $this->code = $var;

        return $this;
    }

    /**
     *信息
     *
     * Generated from protobuf field <code>string message = 2;</code>
     * @return string
     */
    public function getMessage()
    {
        return $this->message;
    }

    /**
     *信息
     *
     * Generated from protobuf field <code>string message = 2;</code>
     * @param string $var
     * @return $this
     */
    public function setMessage($var)
    {
        GPBUtil::checkString($var, True);
        $this->message = $var;

        return $this;
    }

    /**
     *手机号
     *
     * Generated from protobuf field <code>string mobile = 3;</code>
     * @return string
     */
    public function getMobile()
    {
        return $this->mobile;
    }

    /**
     *手机号
     *
     * Generated from protobuf field <code>string mobile = 3;</code>
     * @param string $var
     * @return $this
     */
    public function setMobile($var)
    {
        GPBUtil::checkString($var, True);
        $this->mobile = $var;

        return $this;
    }

    /**
     *授权码-验证时使用
     *
     * Generated from protobuf field <code>string codeToken = 4;</code>
     * @return string
     */
    public function getCodeToken()
    {
        return $this->codeToken;
    }

    /**
     *授权码-验证时使用
     *
     * Generated from protobuf field <code>string codeToken = 4;</code>
     * @param string $var
     * @return $this
     */
    public function setCodeToken($var)
    {
        GPBUtil::checkString($var, True);
        $this->codeToken = $var;
        return $this;
    }

    /**
     *发送平台
     *
     * Generated from protobuf field <code>string sendPlatform = 5;</code>
     * @return string
     */
    public function getSendPlatform()
    {
        return $this->sendPlatform;
    }

    /**
     *发送平台
     *
     * Generated from protobuf field <code>string sendPlatform = 5;</code>
     * @param string $var
     * @return $this
     */
    public function setSendPlatform($var)
    {
        GPBUtil::checkString($var, True);
        $this->sendPlatform = $var;

        return $this;
    }

}

