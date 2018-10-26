<?php
// GENERATED CODE -- DO NOT EDIT!

namespace Account\Starpoint;

/**
 * 账户星点查询服务
 */
class AccountStarPointServiceClient extends \Grpc\BaseStub {

    /**
     * @param string $hostname hostname
     * @param array $opts channel options
     * @param \Grpc\Channel $channel (optional) re-use channel object
     */
    public function __construct($hostname, $opts, $channel = null) {
        parent::__construct($hostname, $opts, $channel);
    }

    /**
     * 查询
     * @param \Account\Starpoint\AccountInfo $argument input argument
     * @param array $metadata metadata
     * @param array $options call options
     */
    public function starPoint(\Account\Starpoint\AccountInfo $argument,
      $metadata = [], $options = []) {
        return $this->_simpleRequest('/account.starpoint.AccountStarPointService/starPoint',
        $argument,
        ['\Account\Starpoint\Result', 'decode'],
        $metadata, $options);
    }

    /**
     * 查询排行
     * @param \Account\Starpoint\RankingInfo $argument input argument
     * @param array $metadata metadata
     * @param array $options call options
     */
    public function ranking(\Account\Starpoint\RankingInfo $argument,
      $metadata = [], $options = []) {
        return $this->_simpleRequest('/account.starpoint.AccountStarPointService/ranking',
        $argument,
        ['\Account\Starpoint\RankingResult', 'decode'],
        $metadata, $options);
    }

    /**
     * 查询流水
     * @param \Account\Starpoint\RecordsCondition $argument input argument
     * @param array $metadata metadata
     * @param array $options call options
     */
    public function records(\Account\Starpoint\RecordsCondition $argument,
      $metadata = [], $options = []) {
        return $this->_simpleRequest('/account.starpoint.AccountStarPointService/records',
        $argument,
        ['\Account\Starpoint\RecordsResult', 'decode'],
        $metadata, $options);
    }

    /**
     * 查询未采集的星点记录
     * @param \Account\Starpoint\UnCollectionRecordsCondition $argument input argument
     * @param array $metadata metadata
     * @param array $options call options
     */
    public function unCollectionRecords(\Account\Starpoint\UnCollectionRecordsCondition $argument,
      $metadata = [], $options = []) {
        return $this->_simpleRequest('/account.starpoint.AccountStarPointService/unCollectionRecords',
        $argument,
        ['\Account\Starpoint\UnCollectionRecordsResult', 'decode'],
        $metadata, $options);
    }

    /**
     * 查询当前日期内资金流水统计，以天为单位
     * @param \Account\Starpoint\RecordsWithDayCondition $argument input argument
     * @param array $metadata metadata
     * @param array $options call options
     */
    public function recordsWithDay(\Account\Starpoint\RecordsWithDayCondition $argument,
      $metadata = [], $options = []) {
        return $this->_simpleRequest('/account.starpoint.AccountStarPointService/recordsWithDay',
        $argument,
        ['\Account\Starpoint\RecordsWithDayResult', 'decode'],
        $metadata, $options);
    }

    /**
     * 根据任务查询用户已采集星点数
     * @param \Account\Starpoint\StarPointWithTaskInfo $argument input argument
     * @param array $metadata metadata
     * @param array $options call options
     */
    public function starPointWithTask(\Account\Starpoint\StarPointWithTaskInfo $argument,
      $metadata = [], $options = []) {
        return $this->_simpleRequest('/account.starpoint.AccountStarPointService/starPointWithTask',
        $argument,
        ['\Account\Starpoint\StarPointWithTaskResult', 'decode'],
        $metadata, $options);
    }

}
