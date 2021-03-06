<?php
# Generated by the protocol buffer compiler.  DO NOT EDIT!
# source: account_starpoint.proto

namespace Account\Starpoint;

use Google\Protobuf\Internal\GPBType;
use Google\Protobuf\Internal\RepeatedField;
use Google\Protobuf\Internal\GPBUtil;

/**
 *根据任务查询用户采集的星点数结果详情
 *
 * Generated from protobuf message <code>account.starpoint.StarPointWithTaskResultDetail</code>
 */
class StarPointWithTaskResultDetail extends \Google\Protobuf\Internal\Message
{
    /**
     *星点数
     *
     * Generated from protobuf field <code>string starPoint = 1;</code>
     */
    private $starPoint = '';
    /**
     *任务ID
     *
     * Generated from protobuf field <code>string taskId = 2;</code>
     */
    private $taskId = '';

    public function __construct() {
        \GPBMetadata\AccountStarpoint::initOnce();
        parent::__construct();
    }

    /**
     *星点数
     *
     * Generated from protobuf field <code>string starPoint = 1;</code>
     * @return string
     */
    public function getStarPoint()
    {
        return $this->starPoint;
    }

    /**
     *星点数
     *
     * Generated from protobuf field <code>string starPoint = 1;</code>
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
     *任务ID
     *
     * Generated from protobuf field <code>string taskId = 2;</code>
     * @return string
     */
    public function getTaskId()
    {
        return $this->taskId;
    }

    /**
     *任务ID
     *
     * Generated from protobuf field <code>string taskId = 2;</code>
     * @param string $var
     * @return $this
     */
    public function setTaskId($var)
    {
        GPBUtil::checkString($var, True);
        $this->taskId = $var;

        return $this;
    }

}

