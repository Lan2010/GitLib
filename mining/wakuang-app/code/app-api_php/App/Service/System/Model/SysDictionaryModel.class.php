<?php

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

namespace Service\System\Model;

use Think\Model;
use Common\Model\SlaveModel;
use Think\Exception;

/**
 * 用户字典
 *
 * @author Administrator
 */
class SysDictionaryModel extends SlaveModel {

    /**
     * 用户字典查询（用于分类查找）
     * @return type
     */
    public function getDic() {
        $where["dic_status"] = 1;
        $field = "dic_id,dic_parent_id,dic_name,dic_node,dic_key";
        $result = $this->SlaveDB()->table("d_sys_dictionary")->field($field)->where($where)->order('dic_id')->select();
        if (is_array($result)) {
            return $result;
        }
    }

    /**
     * 获取字典条件ID
     * @param type $where
     * @return type
     */
    public function getDicBuyID($where) {
        $where["dic_status"] = 1;
        $field = "dic_id,dic_parent_id,dic_name,dic_node,dic_key";
        $result = $this->SlaveDB()->table("d_sys_dictionary")->field($field)->where($where)->order('dic_id')->select();
        if (is_array($result)) {
            return $result;
        }
    }

    /**
     * 词典管理查询
     * @param type $where
     * @param type $whereArr
     */
    public function getDicList($where, $whereArr) {
        $model = $this->SlaveDB()->table("d_sys_dictionary");
        $result = $model->where($where, $whereArr)->order("dic_parent_id ")->select();
        return $result;
    }

    /**
     * 查询要修改的字典字段
     * @param type $where
     */
    public function getSinDic($where) {
        $model = $this->SlaveDB()->table("d_sys_dictionary");
        $result = $model->where($where)->find();
        return $result;
    }

    /**
     * 保存词典 
     * @param type $where
     * @param type $data
     */
    public function saveDic($where, $data) {
        try {
            $model = M("SysDictionary");
            if (is_array($where) && count($where) > 0) {
                $result = $model->where($where)->data($data)->save();
                return $result * 1 > 0 ? $where["dic_id"] : false;
            } else {
                return $model->add($data);
            }
        } catch (Exception $ex) {
            logger(__METHOD__, $ex->getMessage());
            return false;
        }
    }

}
