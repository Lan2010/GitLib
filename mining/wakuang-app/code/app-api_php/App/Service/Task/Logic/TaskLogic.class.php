<?php
/**
 * Created by PhpStorm.
 * User: RLY
 * Date: 2018/6/16
 * Time: 16:07
 */

namespace Service\Task\Logic;

use Common\Common\Redis;
use Service\Task\Model;

class TaskLogic
{

    private $model = null;

    public function __construct()
    {
        $this->model = new model\TaskModel();
    }

    /**生成或者插入主表
     * @param $openID
     * @return mixed
     */
    public function setTask($data) {
        return $this->model->add($data);
    }

    public function update($where,$data) {
        return $this->model->where($where)->save($data);
    }

    /**获取到
     * @param $where
     * @param $felid
     * @return bool|mixed
     */
    public function getTask($where ,$field) {
        return $this->model->getTask($where,$field);
    }


    /**
     * 插入到字表中
     */
    public function setTasklist($data,$begin,$end) {
        M('taskList')->startTrans();
        foreach ($data as $key => $value) {
            $value['order_no'] = $value['taskId'];
            unset($value['taskId']);
            $value['lat'] = latlng($value['location'],'lat');
            $value['lng'] = latlng($value['location'],'lng');
            $value['begin_time'] = $begin;
            $value['end_time'] = $end;
            unset($value['location']);
            $res = M('taskList')->add($value);
            if(!$res) {
                $this->model->rollback();
                return false;
            }
        }
        M("taskList")->commit();
        return true;
    }


    /**
     * 获取到最近距离的任务地点表
     */
    public function getTaskList($lat,$lng,$field,$userID,$limit,$cityCode) {

        $sql = 'SELECT '.$field.' FROM d_task AS a LEFT JOIN d_task_user ts ON ts.task_id = a.task_id AND ts.user_id = '.$userID.' WHERE end_time>'.time().' AND city_code='.$cityCode.' ORDER BY begin_time ASC LIMIT '.$limit;
        $taskList = $this->model->query($sql);
        if(empty($taskList)) {
            return false;
        }
        foreach ($taskList as $key=>$value) {
            $arr = $this->getLocationInfomation($lat,$lng,$value['order_no']);
            $taskList[$key] = array_merge($taskList[$key],$arr);
        }
        $taskList = $this->arr_sort($taskList);
        return $taskList;
    }



    /**
     * 根据任务的ORDER——NO 获取到最近的信息
     */
    public function getLocationInfomation($lat,$lng,$order_no){
           $sql = 'select tl_id, order_no, name, lat, lng, address,
                 ROUND(6378.138 * 2 * ASIN(SQRT(POW( SIN( ( '.$lat.' * PI() / 180 - lat * PI() / 180 ) / 2 ), 2 ) + COS( '.$lat.' * PI() / 180) * COS(lat * PI() / 180) * POW( SIN( ( '.$lng.' * PI() / 180 - lng * PI() / 180 ) / 2 ), 2 ) ) ) * 1000 )  
                AS distance FROM d_task_list where order_no='.$order_no ;
           $adderss_list =  $this->model->query($sql);
           foreach ($adderss_list as $key=>$value) {
               $distance[$key] = $value['distance'];
           }
           asort($distance);  //排序
           foreach ($distance as $key=>$value) {
               return $adderss_list[$key];
           }
           return false;
    }

    /**
     * 根据distance 数组的排序
     */
    public function arr_sort($arr) {
        foreach ($arr as $key=>$value) {
            $sort_value_arr[$key] = $value['distance'];   //映射表
        }
        asort($sort_value_arr);
        foreach ($sort_value_arr as $key => $value) {
            $new_arr[] = $arr[$key];
        }
        return $new_arr;
    }

    /**
     * 获取到用户的领取列表
     */
    public function getUserTaskList($where,$field,$limit="0,15") {
        return $this->model->getUserTaskList($where,$field,$limit);
    }

    /**
     * 添加用户领取任务表
     */
    public function setUserTask($data,$where=array()) {
        if(empty($data)) {
            return false;
        }
        return $this->model->setOrUpdeta($data,$where);
    }



    public function updatelist($where,$begin,$end) {
        $data['begin_time'] = $begin;
        $data['end_time'] = $end;
        return M("taskList")->where($where)->save($data);
    }


    public function getUserAcceptTask($userID) {
        return M('taskUser')->where(array('user_id'=>$userID))->field("task_id")->select();
    }

    /**
     * 获取到提醒的任务
     */
    public function getRemind($field,$lat,$lng) {
        $sql = 'SELECT '.$field.' FROM d_task AS a WHERE a.task_level=1 and a.end_time > '.time().' and a.add_detatime >='.strtotime(date('Y-m-d',time()));
        $taskList = $this->model->query($sql);
        if(empty($taskList)) return false;

        $new = array();
        foreach ($taskList as $key=>$value) {
            $arr = $this->getLocationInfomation($lat,$lng,$value['order_no']);
            if($arr['distance'] <= 5000 && !empty($arr) ) {  //过滤掉超过5公里的
                $new[] = array_merge($taskList[$key],$arr);
            }
        }
        return $new;
    }


    /**
     * 添加弹窗的记录
     */
    public function addFrameLog($userID,$data) {
        $key = "Frame_log_id_".$userID;
        $cache = Redis::GetInstance();
        $Fra = $cache->get($key);
        if(!empty($Fra)) {
            $data = array_merge($Fra,$data);
        }
        return $cache->set($key,json_encode($data),86400);
    }

    /**
     * 获取到弹窗的ID
     */
    public function getFrameLogID($userID) {
        $key = "Frame_log_id_".$userID;
        $cache = Redis::GetInstance();
        return $cache->get($key);
    }


    /**
     * 添加任务的星数
     */
    public function addUserTaskPoint($order,$userID)
    {
        if($order <= 0) {
            return false;
        }
        $key = $userID."_cacheUncollection";
        $cache = Redis::GetInstance();     //从缓存里面拿
        $info = $cache->get($key);
        if(empty($info)) {
            return false;
        }
        foreach ($info as $key=>$value) {
            if($value['taskId'] == $order) {
                $starPoint = $value['operStarPoint'];
            }
        }
        
        $task_id = M("task")->where(array('order_no'=>$order))->getField('task_id');
        $task_user_starPoint = M('taskUser')->where(array('task_id'=>$task_id,'user_id'=>$userID))->getField('starPoint');
        $task_user_starPoint = ($task_user_starPoint)? $task_user_starPoint:0;
        return M("taskUser")->where(array('task_id'=>$task_id,'user_id'=>$userID))->save(array('starPoint'=>$task_user_starPoint+$starPoint));
    }







}