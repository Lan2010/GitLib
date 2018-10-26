<?php
# Generated by the protocol buffer compiler.  DO NOT EDIT!
# source: MobileValidationService.proto

namespace Mobile;

use Google\Protobuf\Internal\GPBType;
use Google\Protobuf\Internal\RepeatedField;
use Google\Protobuf\Internal\GPBUtil;

/**
 *检索验证码信息
 *
 * Generated from protobuf message <code>mobile.SearchCodeInfo</code>
 */
class SearchCodeInfo extends \Google\Protobuf\Internal\Message
{
    /**
     *手机号码-必填
     *
     * Generated from protobuf field <code>string mobile = 1;</code>
     */
    private $mobile = '';
    /**
     *平台身份识别-必填
     *
     * Generated from protobuf field <code>string token = 2;</code>
     */
    private $token = '';

    public function __construct() {
        \GPBMetadata\MobileValidationService::initOnce();
        parent::__construct();
    }

    /**
     *手机号码-必填
     *
     * Generated from protobuf field <code>string mobile = 1;</code>
     * @return string
     */
    public function getMobile()
    {
        return $this->mobile;
    }

    /**
     *手机号码-必填
     *
     * Generated from protobuf field <code>string mobile = 1;</code>
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
     *平台身份识别-必填
     *
     * Generated from protobuf field <code>string token = 2;</code>
     * @return string
     */
    public function getToken()
    {
        return $this->token;
    }

    /**
     *平台身份识别-必填
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

}

