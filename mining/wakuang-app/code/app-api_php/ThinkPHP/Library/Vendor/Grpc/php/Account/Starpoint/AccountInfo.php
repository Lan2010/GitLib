<?php
# Generated by the protocol buffer compiler.  DO NOT EDIT!
# source: account_starpoint.proto

namespace Account\Starpoint;

use Google\Protobuf\Internal\GPBType;
use Google\Protobuf\Internal\RepeatedField;
use Google\Protobuf\Internal\GPBUtil;

/**
 *账户信息
 *
 * Generated from protobuf message <code>account.starpoint.AccountInfo</code>
 */
class AccountInfo extends \Google\Protobuf\Internal\Message
{
    /**
     *需存储的第三方唯一token 必填
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

    public function __construct() {
        \GPBMetadata\AccountStarpoint::initOnce();
        parent::__construct();
    }

    /**
     *需存储的第三方唯一token 必填
     *
     * Generated from protobuf field <code>string accountToken = 1;</code>
     * @return string
     */
    public function getAccountToken()
    {
        return $this->accountToken;
    }

    /**
     *需存储的第三方唯一token 必填
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

}

