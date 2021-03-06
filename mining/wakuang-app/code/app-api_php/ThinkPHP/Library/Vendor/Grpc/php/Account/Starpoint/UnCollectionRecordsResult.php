<?php
# Generated by the protocol buffer compiler.  DO NOT EDIT!
# source: account_starpoint.proto

namespace Account\Starpoint;

use Google\Protobuf\Internal\GPBType;
use Google\Protobuf\Internal\RepeatedField;
use Google\Protobuf\Internal\GPBUtil;

/**
 *未采集星点记录结果
 *
 * Generated from protobuf message <code>account.starpoint.UnCollectionRecordsResult</code>
 */
class UnCollectionRecordsResult extends \Google\Protobuf\Internal\Message
{
    /**
     *记录
     *
     * Generated from protobuf field <code>repeated .account.starpoint.UnCollectionRecordsInfo records = 1;</code>
     */
    private $records;
    /**
     * Generated from protobuf field <code>.ResponseEntity responseEntity = 2;</code>
     */
    private $responseEntity = null;

    public function __construct() {
        \GPBMetadata\AccountStarpoint::initOnce();
        parent::__construct();
    }

    /**
     *记录
     *
     * Generated from protobuf field <code>repeated .account.starpoint.UnCollectionRecordsInfo records = 1;</code>
     * @return \Google\Protobuf\Internal\RepeatedField
     */
    public function getRecords()
    {
        return $this->records;
    }

    /**
     *记录
     *
     * Generated from protobuf field <code>repeated .account.starpoint.UnCollectionRecordsInfo records = 1;</code>
     * @param \Account\Starpoint\UnCollectionRecordsInfo[]|\Google\Protobuf\Internal\RepeatedField $var
     * @return $this
     */
    public function setRecords($var)
    {
        $arr = GPBUtil::checkRepeatedField($var, \Google\Protobuf\Internal\GPBType::MESSAGE, \Account\Starpoint\UnCollectionRecordsInfo::class);
        $this->records = $arr;

        return $this;
    }

    /**
     * Generated from protobuf field <code>.ResponseEntity responseEntity = 2;</code>
     * @return \ResponseEntity
     */
    public function getResponseEntity()
    {
        return $this->responseEntity;
    }

    /**
     * Generated from protobuf field <code>.ResponseEntity responseEntity = 2;</code>
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

