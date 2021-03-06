<?php
# Generated by the protocol buffer compiler.  DO NOT EDIT!
# source: account_starpoint_operation.proto

namespace Account\Starpoint\Operation;

use Google\Protobuf\Internal\GPBType;
use Google\Protobuf\Internal\RepeatedField;
use Google\Protobuf\Internal\GPBUtil;

/**
 *采集结果
 *
 * Generated from protobuf message <code>account.starpoint.operation.CollectionResult</code>
 */
class CollectionResult extends \Google\Protobuf\Internal\Message
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
     *采集星点数
     *
     * Generated from protobuf field <code>string starpoint = 4;</code>
     */
    private $starpoint = '';
    /**
     * Generated from protobuf field <code>.ResponseEntity responseEntity = 3;</code>
     */
    private $responseEntity = null;

    public function __construct() {
        \GPBMetadata\AccountStarpointOperation::initOnce();
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
     *采集星点数
     *
     * Generated from protobuf field <code>string starpoint = 4;</code>
     * @return string
     */
    public function getStarpoint()
    {
        return $this->starpoint;
    }

    /**
     *采集星点数
     *
     * Generated from protobuf field <code>string starpoint = 4;</code>
     * @param string $var
     * @return $this
     */
    public function setStarpoint($var)
    {
        GPBUtil::checkString($var, True);
        $this->starpoint = $var;

        return $this;
    }

    /**
     * Generated from protobuf field <code>.ResponseEntity responseEntity = 3;</code>
     * @return \ResponseEntity
     */
    public function getResponseEntity()
    {
        return $this->responseEntity;
    }

    /**
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

