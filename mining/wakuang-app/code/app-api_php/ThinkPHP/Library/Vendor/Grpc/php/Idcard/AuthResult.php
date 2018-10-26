<?php
# Generated by the protocol buffer compiler.  DO NOT EDIT!
# source: IDCardAuthService.proto

namespace Idcard;

use Google\Protobuf\Internal\GPBType;
use Google\Protobuf\Internal\RepeatedField;
use Google\Protobuf\Internal\GPBUtil;

/**
 *身份证验证结果
 *
 * Generated from protobuf message <code>idcard.AuthResult</code>
 */
class AuthResult extends \Google\Protobuf\Internal\Message
{
    /**
     *200成功，300业务错误
     *
     * Generated from protobuf field <code>int32 code = 1;</code>
     */
    private $code = 0;
    /**
     *消息
     *
     * Generated from protobuf field <code>string message = 2;</code>
     */
    private $message = '';
    /**
     *照片-如果存在
     *
     * Generated from protobuf field <code>string photo = 3;</code>
     */
    private $photo = '';
    /**
     *验证平台
     *
     * Generated from protobuf field <code>string authPlatform = 4;</code>
     */
    private $authPlatform = '';

    public function __construct() {
        \GPBMetadata\IDCardAuthService::initOnce();
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
     *消息
     *
     * Generated from protobuf field <code>string message = 2;</code>
     * @return string
     */
    public function getMessage()
    {
        return $this->message;
    }

    /**
     *消息
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
     *照片-如果存在
     *
     * Generated from protobuf field <code>string photo = 3;</code>
     * @return string
     */
    public function getPhoto()
    {
        return $this->photo;
    }

    /**
     *照片-如果存在
     *
     * Generated from protobuf field <code>string photo = 3;</code>
     * @param string $var
     * @return $this
     */
    public function setPhoto($var)
    {
        GPBUtil::checkString($var, True);
        $this->photo = $var;

        return $this;
    }

    /**
     *验证平台
     *
     * Generated from protobuf field <code>string authPlatform = 4;</code>
     * @return string
     */
    public function getAuthPlatform()
    {
        return $this->authPlatform;
    }

    /**
     *验证平台
     *
     * Generated from protobuf field <code>string authPlatform = 4;</code>
     * @param string $var
     * @return $this
     */
    public function setAuthPlatform($var)
    {
        GPBUtil::checkString($var, True);
        $this->authPlatform = $var;

        return $this;
    }

}
