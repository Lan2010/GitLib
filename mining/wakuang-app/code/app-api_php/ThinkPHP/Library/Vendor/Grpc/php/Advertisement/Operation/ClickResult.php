<?php
# Generated by the protocol buffer compiler.  DO NOT EDIT!
# source: advertisement_operation.proto

namespace Advertisement\Operation;

use Google\Protobuf\Internal\GPBType;
use Google\Protobuf\Internal\RepeatedField;
use Google\Protobuf\Internal\GPBUtil;

/**
 * Generated from protobuf message <code>advertisement.operation.ClickResult</code>
 */
class ClickResult extends \Google\Protobuf\Internal\Message
{
    /**
     *可用星点数
     *
     * Generated from protobuf field <code>string availableStarPoint = 1;</code>
     */
    private $availableStarPoint = '';
    /**
     *冻结星点数量
     *
     * Generated from protobuf field <code>string frozenStarPoint = 2;</code>
     */
    private $frozenStarPoint = '';
    /**
     *奖励的星点数
     *
     * Generated from protobuf field <code>string starPoint = 4;</code>
     */
    private $starPoint = '';
    /**
     *code : 200=ok, 300=param error, 502=operation error
     *
     * Generated from protobuf field <code>.ResponseEntity responseEntity = 3;</code>
     */
    private $responseEntity = null;

    public function __construct() {
        \GPBMetadata\AdvertisementOperation::initOnce();
        parent::__construct();
    }

    /**
     *可用星点数
     *
     * Generated from protobuf field <code>string availableStarPoint = 1;</code>
     * @return string
     */
    public function getAvailableStarPoint()
    {
        return $this->availableStarPoint;
    }

    /**
     *可用星点数
     *
     * Generated from protobuf field <code>string availableStarPoint = 1;</code>
     * @param string $var
     * @return $this
     */
    public function setAvailableStarPoint($var)
    {
        GPBUtil::checkString($var, True);
        $this->availableStarPoint = $var;

        return $this;
    }

    /**
     *冻结星点数量
     *
     * Generated from protobuf field <code>string frozenStarPoint = 2;</code>
     * @return string
     */
    public function getFrozenStarPoint()
    {
        return $this->frozenStarPoint;
    }

    /**
     *冻结星点数量
     *
     * Generated from protobuf field <code>string frozenStarPoint = 2;</code>
     * @param string $var
     * @return $this
     */
    public function setFrozenStarPoint($var)
    {
        GPBUtil::checkString($var, True);
        $this->frozenStarPoint = $var;

        return $this;
    }

    /**
     *奖励的星点数
     *
     * Generated from protobuf field <code>string starPoint = 4;</code>
     * @return string
     */
    public function getStarPoint()
    {
        return $this->starPoint;
    }

    /**
     *奖励的星点数
     *
     * Generated from protobuf field <code>string starPoint = 4;</code>
     * @param string $var
     * @return $this
     */
    public function setStarPoint($var)
    {
        GPBUtil::checkString($var, True);
        $this->starPoint = $var;

        return $this;
    }

    /**
     *code : 200=ok, 300=param error, 502=operation error
     *
     * Generated from protobuf field <code>.ResponseEntity responseEntity = 3;</code>
     * @return \ResponseEntity
     */
    public function getResponseEntity()
    {
        return $this->responseEntity;
    }

    /**
     *code : 200=ok, 300=param error, 502=operation error
     *
     * Generated from protobuf field <code>.ResponseEntity responseEntity = 3;</code>
     * @param \ResponseEntity $var
     * @return $this
     */
    public function setResponseEntity($var)
    {
        GPBUtil::checkMessage($var, \ResponseEntity::class);
        $this->responseEntity = $var;

        return $this;
    }

}
