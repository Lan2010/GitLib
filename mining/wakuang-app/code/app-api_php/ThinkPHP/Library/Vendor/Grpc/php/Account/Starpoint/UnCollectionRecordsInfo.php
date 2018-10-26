<?php
# Generated by the protocol buffer compiler.  DO NOT EDIT!
# source: account_starpoint.proto

namespace Account\Starpoint;

use Google\Protobuf\Internal\GPBType;
use Google\Protobuf\Internal\RepeatedField;
use Google\Protobuf\Internal\GPBUtil;

/**
 *未采集星点详情
 *
 * Generated from protobuf message <code>account.starpoint.UnCollectionRecordsInfo</code>
 */
class UnCollectionRecordsInfo extends \Google\Protobuf\Internal\Message
{
    /**
     *操作星点数
     *
     * Generated from protobuf field <code>string operStarPoint = 1;</code>
     */
    private $operStarPoint = '';
    /**
     *经纬度
     *
     * Generated from protobuf field <code>string longitudeAndLatitude = 2;</code>
     */
    private $longitudeAndLatitude = '';
    /**
     *记录类型(TASK，RANDOM)
     *
     * Generated from protobuf field <code>string recordsType = 3;</code>
     */
    private $recordsType = '';
    /**
     *任务ID
     *
     * Generated from protobuf field <code>string taskId = 4;</code>
     */
    private $taskId = '';
    /**
     *广告ID
     *
     * Generated from protobuf field <code>string advertisementId = 5;</code>
     */
    private $advertisementId = '';
    /**
     *备注
     *
     * Generated from protobuf field <code>string remark = 6;</code>
     */
    private $remark = '';
    /**
     *创建时间
     *
     * Generated from protobuf field <code>int64 createTime = 7;</code>
     */
    private $createTime = 0;
    /**
     *记录token
     *
     * Generated from protobuf field <code>string recordToken = 8;</code>
     */
    private $recordToken = '';
    /**
     *到期时间
     *
     * Generated from protobuf field <code>int64 overdueTime = 9;</code>
     */
    private $overdueTime = 0;

    public function __construct() {
        \GPBMetadata\AccountStarpoint::initOnce();
        parent::__construct();
    }

    /**
     *操作星点数
     *
     * Generated from protobuf field <code>string operStarPoint = 1;</code>
     * @return string
     */
    public function getOperStarPoint()
    {
        return $this->operStarPoint;
    }

    /**
     *操作星点数
     *
     * Generated from protobuf field <code>string operStarPoint = 1;</code>
     * @param string $var
     * @return $this
     */
    public function setOperStarPoint($var)
    {
        GPBUtil::checkString($var, True);
        $this->operStarPoint = $var;

        return $this;
    }

    /**
     *经纬度
     *
     * Generated from protobuf field <code>string longitudeAndLatitude = 2;</code>
     * @return string
     */
    public function getLongitudeAndLatitude()
    {
        return $this->longitudeAndLatitude;
    }

    /**
     *经纬度
     *
     * Generated from protobuf field <code>string longitudeAndLatitude = 2;</code>
     * @param string $var
     * @return $this
     */
    public function setLongitudeAndLatitude($var)
    {
        GPBUtil::checkString($var, True);
        $this->longitudeAndLatitude = $var;

        return $this;
    }

    /**
     *记录类型(TASK，RANDOM)
     *
     * Generated from protobuf field <code>string recordsType = 3;</code>
     * @return string
     */
    public function getRecordsType()
    {
        return $this->recordsType;
    }

    /**
     *记录类型(TASK，RANDOM)
     *
     * Generated from protobuf field <code>string recordsType = 3;</code>
     * @param string $var
     * @return $this
     */
    public function setRecordsType($var)
    {
        GPBUtil::checkString($var, True);
        $this->recordsType = $var;

        return $this;
    }

    /**
     *任务ID
     *
     * Generated from protobuf field <code>string taskId = 4;</code>
     * @return string
     */
    public function getTaskId()
    {
        return $this->taskId;
    }

    /**
     *任务ID
     *
     * Generated from protobuf field <code>string taskId = 4;</code>
     * @param string $var
     * @return $this
     */
    public function setTaskId($var)
    {
        GPBUtil::checkString($var, True);
        $this->taskId = $var;

        return $this;
    }

    /**
     *广告ID
     *
     * Generated from protobuf field <code>string advertisementId = 5;</code>
     * @return string
     */
    public function getAdvertisementId()
    {
        return $this->advertisementId;
    }

    /**
     *广告ID
     *
     * Generated from protobuf field <code>string advertisementId = 5;</code>
     * @param string $var
     * @return $this
     */
    public function setAdvertisementId($var)
    {
        GPBUtil::checkString($var, True);
        $this->advertisementId = $var;

        return $this;
    }

    /**
     *备注
     *
     * Generated from protobuf field <code>string remark = 6;</code>
     * @return string
     */
    public function getRemark()
    {
        return $this->remark;
    }

    /**
     *备注
     *
     * Generated from protobuf field <code>string remark = 6;</code>
     * @param string $var
     * @return $this
     */
    public function setRemark($var)
    {
        GPBUtil::checkString($var, True);
        $this->remark = $var;

        return $this;
    }

    /**
     *创建时间
     *
     * Generated from protobuf field <code>int64 createTime = 7;</code>
     * @return int|string
     */
    public function getCreateTime()
    {
        return $this->createTime;
    }

    /**
     *创建时间
     *
     * Generated from protobuf field <code>int64 createTime = 7;</code>
     * @param int|string $var
     * @return $this
     */
    public function setCreateTime($var)
    {
        GPBUtil::checkInt64($var);
        $this->createTime = $var;

        return $this;
    }

    /**
     *记录token
     *
     * Generated from protobuf field <code>string recordToken = 8;</code>
     * @return string
     */
    public function getRecordToken()
    {
        return $this->recordToken;
    }

    /**
     *记录token
     *
     * Generated from protobuf field <code>string recordToken = 8;</code>
     * @param string $var
     * @return $this
     */
    public function setRecordToken($var)
    {
        GPBUtil::checkString($var, True);
        $this->recordToken = $var;

        return $this;
    }

    /**
     *到期时间
     *
     * Generated from protobuf field <code>int64 overdueTime = 9;</code>
     * @return int|string
     */
    public function getOverdueTime()
    {
        return $this->overdueTime;
    }

    /**
     *到期时间
     *
     * Generated from protobuf field <code>int64 overdueTime = 9;</code>
     * @param int|string $var
     * @return $this
     */
    public function setOverdueTime($var)
    {
        GPBUtil::checkInt64($var);
        $this->overdueTime = $var;

        return $this;
    }

}

