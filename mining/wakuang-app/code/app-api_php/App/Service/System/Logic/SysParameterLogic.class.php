<?php

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

namespace Service\System\Logic;

use Service\System\Model;
use Common\Common\Redis;

class SysParameterLogic {

    /**
     * 查询系统参数
     * @param type $where
     * @param type $whereArr
     * @param type $limit
     */
    public function getParameter($where, $whereArr, $limit) {
        $where.="AND par_status='%d'";
        array_push($whereArr, 1);
        $model = new Model\SysParameterModel();
        $result = $model->getParameter("(1=1)" . $where, $whereArr, $limit);
        return $result;
    }

    /**
     * 查询单个参数
     * @param type $parID
     */
    public function getXinParameter($parID) {
        $model = new Model\SysParameterModel();
        $where["par_id"] = $parID;
        $result = $model->getXinParameter($where);
        return $result;
    }

    /**
     * 获取所有系统参数
     * @return type
     */
    public function getConfig() {
        $cache = Redis::GetInstance();
        $result = $cache->get("Parameter");
        if (empty($result) || is_null($result)) {
            $model = new Model\SysParameterModel();
            $where["parStatus"] = 1;
            $result = $model->getParMore($where);
            $config = array();
            if ($result && is_array($result)) {
                foreach ($result as $value) {
                    $config[$value['parKey']] = $value['parValue'];
                }
            }
            $cache->set("Parameter", $config, 86400 * 7); //存储一周
        }
        return $result;
    }

    /**
     * 保存参数信息
     * @param type $data
     */
    public function saveParameter($data) {
        try {
            $where = array();
            $model = new Model\SysParameterModel();
            if ($data["par_id"] * 1 > 0 && count($data) > 0) {
                $where["par_id"] = $data["par_id"];
                $data["edit_user_id"] = C("userID");
                $data["edit_user_name"] = C("userName");
                $data["edit_datetime"] = time();
                unset($data["par_id"]);
            } else {
                $data["add_user_id"] = C("userID");
                $data["add_user_name"] = C("userName");
                $data["add_datetime"] = time();
            }
            $result = $model->saveParameter($where, $data);
            $cache = Redis::GetInstance();
            $cache->del("Parameter");
            return $result;
        } catch (Exception $ex) {
            logger(__METHOD__, $ex->getMessage());
            return false;
        }
    }

    /**
     * 删除参数
     * @param type $parID
     */
    public function delParameter($parID) {
        if ($parID * 1 < 0) {
            return false;
        }
        $where["par_id"] = $parID;
        $data["par_status"] = 0;
        $model = new Model\SysParameterModel();
        $result = $model->saveParameter($where, $data);
        return $result;
    }

}
