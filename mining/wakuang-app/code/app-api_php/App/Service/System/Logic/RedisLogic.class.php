<?php

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

namespace Service\System\Logic;

/**
 * Description of RedisLogic
 *
 * @author DREAM
 */
use Common\Common\Redis;

class RedisLogic {

    /**
     * 缓存查询
     */
    public function getRedisList($key) {
        $cache = Redis::GetInstance();
        $temp = $cache->getAll($key);
        $data = array();
        foreach ($temp as $key => $value) {
            $data[$key]["key"] = $value;
        }
        sort($temp);
        foreach ($data as $key => $value) {
            $cacheTime = $cache->getTTL($value["key"]);
            if ($cacheTime == -1) {
                $data[$key]["time"] = '永久有效';
            } elseif ($cacheTime > 0) {
                $data[$key]["time"] = $this->dataformat($cacheTime);
            } elseif ($cacheTime = -2) {
                $data[$key]["time"] = '过期';
            }
            $type = $cache->getTypeName($value["key"]);
            $data[$key]["type"] = $type['type'];
            $data[$key]["typeName"] = $type['name'];
        }
        return $data;
    }

    public function getView($key) {
        $cache = Redis::GetInstance();
        $type = $cache->getType($key);
        $result = $this->type($cache, $key, $type);
        return $result;
    }

    /**
     * none(key不存在) int(0)  string(字符串) int(1)   list(列表) int(3)  set(集合) int(2)   zset(有序集) int(4)    hash(哈希表) int(5)
     * @param type $cache
     * @param type $type
     */
    public function type($cache, $key, $type) {
        $arr = array("none(key不存在)", "string(字符串)", "list(列表)", "set(集合)", "zset(有序集)", "hash(哈希表)");
        switch ($type) {
            case 1:
                $head["type"] = $arr[$type];
                $head["key"] = $key;
                $value = $cache->get($key);
                $head["size"] = strlen($value) . " characters";
                $head["data"] = $this->getvalus($cache, $type, $key, $value);
                break;
            case 2:
                $values = $cache->hGetAll($key);
                $size = count($values);
                break;
            case 3:
                $head["type"] = $arr[$type];
                $head["key"] = $key;
                $head["size"] = $cache->listLen($key) . " items";
                $head["data"] = $this->getvalus($cache, $type, $key);
                break;
            case 4:
                $values = $cache->sMembers($key);
                $size = count($values);
                break;
            case 5:
                $head["type"] = $arr[$type];
                $head["key"] = $key;
                $head["size"] = $cache->listLen($key) . " items";
                $head["data"] = $this->getvalus($cache, $type, $key);
                break;
        }
        return $head;
    }

    private function getvalus($cache, $type, $key, $value = "") {
        $arr = array();
        switch ($type) {
            case 1:
                $arr = json_encode($value, JSON_UNESCAPED_UNICODE);
                break;
            case 2:
                $values = $cache->hGetAll($key);
                $size = count($values);
                break;
            case 3:
                $size = $cache->listLen($key);
                $list = $cache->lranges($key, 0, $size);
                $arr = $list;
                break;
            case 4:
                $values = $cache->sMembers($key);
                $size = count($values);
                break;
            case 5:
                $size = $cache->hLen($key);
                $list = $cache->hGetAll($key);
                foreach ($list as $key => $value) {
                   $arr[]= $value;
                }
                break;
        }
        return $arr;
    }

    /**
     * 删除Redis
     * @param type $key
     */
    public function delRedis($key) {
        $cache = Redis::GetInstance();
        $result = $cache->isExists($key) ? $cache->del($key) : false;
        return $result;
    }

    public function dataformat($num) {
        $day = floor($num / (3600 * 24));
        $hour = floor(($num - $day * 3600 * 24) / 3600);
        $minute = floor(($num - $day * 3600 * 24 - 3600 * $hour) / 60);
        $second = floor(($num - $day * 3600 * 24 - 3600 * $hour - 60 * $minute) % 60);
        return $day . "天" . $hour . '小时' . $minute . '分' . $second . "秒";
    }

}
