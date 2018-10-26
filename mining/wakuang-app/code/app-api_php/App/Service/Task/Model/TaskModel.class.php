<?php
/**
 * Created by PhpStorm.
 * User: RLY
 * Date: 2018/6/16
 * Time: 16:32
 */

namespace Service\Task\Model;

use Common\Model\SlaveModel;
use Think\Exception;

class TaskModel extends SlaveModel
{
      public function getTask($where,$felid) {
          if(!is_array($where)) {
              return false;
          }
           return  $this->where($where)->field($felid)->select();
      }

      public function getCodeInfo($code) {
          $where['code'] = $code;
          return $this->where($code)->find();
      }

      public function  getUserTaskList($where,$field=array('ts.task_id'),$limit="0,15") {
          if(empty($where)) {
              return false;
          }
          $model = M("taskUser");
          $return['list'] = $model->alias("ts")->where($where)->join("d_task t on ts.task_id = t.task_id")->join("d_task_list tl ON tl.tl_id = ts.tl_id")->field($field)->limit($limit)->select();
          $tmp = $model->alias("ts")->where($where)->join("d_task t on ts.task_id = t.task_id")->join("d_task_list tl ON tl.tl_id = ts.tl_id")->field("count(ts.task_id) as num")->select();
          $return['totalPage'] = $tmp[0]['num'];
          return $return;
      }

    /**
     * 更新或者新增加
     * @param $data
     * @param $where
     * @return bool|mixed
     */
      public function setOrUpdeta($data,$where) {
          $model = M("taskUser");
          if(empty($where)) {
              return $model->add($data);
          } else {
              return $model->alias("tl")->where($where)->save($data);
          }
      }
}