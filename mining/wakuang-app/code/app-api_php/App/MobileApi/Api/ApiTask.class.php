<?php

namespace MobileApi\Api;

use MobileApi\Common\MobileApiBase;
use Service\Charge\Logic\ChargeLogic;
use Service\Nats\Logic\NatsLogic;
use Service\Task\Logic\TaskLogic;
use Service\User\Logic\UserLogic;


/**
 * Description of index
 *
 * @author Administrator
 */
class ApiTask extends MobileApiBase
{

    public $return = array('code' => 0, 'msg' => 'Welcome to use Api!', 'info' =>"" );

    public function getRules()
    {
        return array(
            'getTask' => array(
                'pageindex' => array('name' => 'pageindex', 'type' => 'int', 'default' => 1, 'desc' => '页码'),
                'pagesize' => array('name' => 'pagesize', 'type' => 'int', 'default' => 15, 'desc' => '每页显示数量'),
                'lat' => array('name' => 'lat', 'require' => true, 'type' => 'string', 'default' => 0, 'desc' => '当前的纬度'),
                'lng' => array('name' => 'lng', 'require' => true, 'type' => 'string', 'default' => 0, 'desc' => '当前的经度'),
                'mi' => array('name' => 'mi', 'require' => true, 'type' => 'string', 'default' => 0, 'desc' => '距离单位米'),
                'cityCode'=>array('name' => 'cityCode', 'require' => true, 'type' => 'int', 'default' => 340, 'desc' => '城市代码'),
            ),
            'userAcceptTask' => array(
                'orderNO' => array("name" => 'orderNO', "type" => 'int', "require" => true, "desc" => "任务的唯一标识"),
                'tl_id' => array("name" => 'tl_id', "type" => 'int', "require" => true, "desc" => "地址的ID"),
            ),
            'userOpTask' => array(
                "taskID" => array("name" => 'taskID', "type" => 'int', "require" => true, "desc" => "任务的ID"),
                "op" => array("name" => 'op', "type" => 'string', "require" => true, "desc" => "操作标识 完成 ok 取消 cancel ")
            ),
            'getMyTask' => array(
                'pageindex' => array('name' => 'pageindex', 'type' => 'int', 'default' => 1, 'desc' => '页码'),
                'pagesize' => array('name' => 'pagesize', 'type' => 'int', 'default' => 15, 'desc' => '每页显示数量'),
                'type' => array("name" => "type", 'type' => 'int', 'default' => 1, 'desc' => '1 是完成的任务 2 是进行中的任务')
            ),
            'getRemindAd' => array(
                'lat' => array('name' => 'lat', 'require' => true, 'type' => 'string', 'default' => 0, 'desc' => '当前的纬度'),
                'lng' => array('name' => 'lng', 'require' => true, 'type' => 'string', 'default' => 0, 'desc' => '当前的经度'),
            ),
        );
    }

    /**
     * 获取到当前的任务列表
     * @desc 获取到已经推送到数据库中的任务
     * @return array info.
     * @return string  order_no  单号
     * @return string  task_name  任务名字
     * @return int task_id  任务ID
     * @return int order_no 唯一标识
     * @return double lat  纬度
     * @return double lng  经度
     * @return string name  麦当劳(南山餐厅) 详细名称
     * @return string task_name  任务名称
     * @return string city  城市
     * @return string tl_id  地址ID
     * @return double  task_radius  任务范围
     * @return string city_code  城市代码
     * @return string task_lcon  标志的图片
     * @return string begin_time 开始时间
     * @return string end_time 结束时间
     * @return string keyword 关键字
     * @return string distance 距离
     * @return int task_level 任务级别 0 普通 1紧急
     * @return string task_award 任务的期望值
     * @return string task_remark  任务简介
     * @return string status 状态 1 接受了
     * @return   string    msg                           提示信息
     * @return   code    int                           提示信息
     */
    public function getTask()
    {
        $this->userCheck();
        $logic = new TaskLogic();
        $limit = $this->getLimit($this->pageindex, $this->pagesize);
        $field = 'a.task_id,a.task_level,a.task_award,a.task_remark,a.task_radius,a.order_no, a.task_name , a.city , a.city_code , a.task_lcon , FROM_UNIXTIME(a.begin_time,"%Y-%m-%d %H:%i") as begin_time ,FROM_UNIXTIME(a.end_time,"%Y-%m-%d %H:%i") as end_time,a.keyword,ts.status';
        $all_info = $logic->getTasklist($this->lat, $this->lng, $field, $this->userID, $limit, $this->cityCode); //出来的任务已经根据 距离排了一次序了
        if (empty($all_info)) {
            $this->return['code'] = 1;
            $this->return['msg'] = "没有数据";
            return $this->return;
        }
        $nostatus_info = array();
        $yesstatus_info = array();
        foreach ($all_info as $key => $value) {
            if(empty($all_info[$key]['status'])) {         //将排序后的结果分成两组 按照是否接受任务进行第二次排序
                $all_info[$key]['status'] = 0;
            }
            if(strtotime($all_info[$key]['begin_time']) > time()) {
                $nostatus_info[] = $all_info[$key];
            }else {
                $yesstatus_info[] = $all_info[$key];
            }
        }
        if(!empty($yesstatus_info) && !empty($nostatus_info) ) {     //当作为组合的数组的为空的时候就直接返回数据库结果
            $all_info = array_merge($yesstatus_info,$nostatus_info);
        }
        $this->return['msg'] = "获取成功";
        $this->return['info'] = $all_info;
        return $this->return;
    }

    /**
     * 用户领取任务接口
     * @desc   领取任务动作
     * @return   string    msg                           提示信息
     * @return array info.
     * @return int code  0代表成功 1代表失败
     */
    public function userAcceptTask()
    {
        $this->userCheck();
        $logic = new TaskLogic();
        $where['order_no'] = $this->orderNO;
        $task_info = $logic->getTask($where, array("order_no","task_id","task_name","task_remark"));
        $charge_logic = new ChargeLogic();
        $charge_info = $charge_logic->getInfoByUser($this->userID);
        if (empty($charge_info) && $charge_info['state'] != 2) {
            $this->return['code'] = 1;
            $this->return['msg'] = "未绑定矿机";

            return $this->return;
        }
        if (empty($task_info)) {
            $this->return['code'] = 1;
            $this->return['msg'] = "不存在任务";
            return $this->return;
        }

        $where = array();
        $where['ts.user_id'] = $this->userID;
        $where['ts.task_id'] = $task_info[0]['task_id'];

        $tmp = $logic->getUserTaskList($where, array("ts.task_id"));
        if (!empty($tmp['list'])) {
            $this->return['code'] = 1;
            $this->return['msg'] = "已经领取过该任务";
            return $this->return;
        }

        vendor("Grpc.php.TaskOperation");

        $userlogic = new UserLogic();
        $user_info = $userlogic->getUser($this->userID);


        $GRPClogic = new \TaskOperation();
        $result = $GRPClogic->accept($user_info['user_token'], $this->orderNO);

        if (empty($result)) {
            $this->return['code'] = 1;
            $this->return['msg'] = "GRPC出错";
            return $this->return;
        }

        if ($result) {
            $this->return['code'] = ($result["code"] == 200 ? 0 : 1);
            $this->return['msg'] = $result['msg'];
            if ($result['code'] != 200) {   //先屏蔽
                return $this->return;
            }
        }


        $data['user_id'] = $this->userID;
        $data['task_id'] = $task_info[0]['task_id'];
        $data['status'] = 1;
        $data['receive_date'] = time();
        $data['tl_id'] = $this->tl_id;

        $ret = $logic->setUserTask($data);
        if ($ret) {
            $this->return['msg'] = "接受成功";

            //nat 推送
            $logic = new NatsLogic();
            $publish["mobile"] = (empty($this->userPhone))? "": $this->userPhone;//选填
            $publish["operationType"] =  1;//必填(0=取消，1=接受)
            $publish["taskId"] = (empty($this->orderNO))? "": $this->orderNO;//必填
            $publish["taskName"] = (empty($task_info['task_name']))? "": $task_info['task_name'] ;//选填
            $publish["taskInfo"] = (empty($task_info['task_remark']))? "": $task_info['task_remark'] ;
            $logic->acceptFinished($this->terminal,$publish);

        } else {
            $this->return['code'] = 1;
            $this->return['msg'] = "接受失败";
        }
        return $this->return;
    }


    /**
     * 获取到我的已完成任务或者我的进行中的任务
     * @desc 分别对应着在用户中心和地图上的接口
     * @return array info.
     * @return string  task_id  单号
     * @return string  order_no  任务的唯一标识
     * @return string  task_name  任务名字
     * @return string  task_lcon  任务图标
     * @return string  city  城市
     * @return string  city_code  城市的编码
     * @return string  begin_time  开始时间
     * @return string  end_time    结束时间
     * @return string  keyword  备注
     * @return string  lat 纬度
     * @return string   lng 经度
     * @return double   task_radius
     * @return string  place 地点
     * @return string  starPoint 当然任务用户获取到的积分 默认为0
     * @return int task_level 任务级别 0 普通 1紧急
     * @return string task_award 任务的期望值
     * @return string task_remark  任务简介
     * @return string  status  0 代表未领取 1代表进行中 2已完成 3代表失效
     * @return   string    msg                           提示信息
     */
    public function getMyTask()
    {
        $this->userCheck();
        $logic = new TaskLogic();
        $where['ts.user_id'] = $this->userID;
        $where['ts.status'] = 1;
        $this->return['totalPage'] = 0;
        if ($this->type == 1) {    //已经完成的
            $where['t.end_time'] = array('elt', time());
            $where['ts.starPoint'] = array('neq',0);
        } else if($this->type == 2) {  //进行中的
            $where['t.end_time'] = array('egt',time());
        }
        $limit = $this->getLimit($this->pageindex, $this->pagesize);
        $field = ['t.task_id','ts.starPoint', 't.order_no', 't.task_radius', 'tl.lat', 'tl.lng', 't.task_level','t.task_award','t.task_remark','t.task_name', 't.task_lcon', 't.city', 't.city_code', 'FROM_UNIXTIME(t.begin_time,"%Y-%m-%d %H:%i") as begin_time ', 'FROM_UNIXTIME(t.end_time,"%Y-%m-%d %H:%i") as end_time', 't.keyword', 'ts.status'];
        $all_info = $logic->getUserTaskList($where, $field, $limit);

        if (empty($all_info['list'])) {
            $this->return['code'] = 1;
            $this->return['msg'] = "没有数据";
            return $this->return;
        }

//        if($this->type == 1) {    //完成的任务 获取到点数
//            $userlogic = new UserLogic();
//            $userinfo = $userlogic->getUser($this->userID);
//            $taskId_arr = array_column($all_info['list'],'order_no');
//            vendor("Grpc.php.AccountStarPoint");
//            $pro = new \AccountStarPoint();
//            $info = $pro->starPointWithTask($userinfo['user_token'],$taskId_arr);
//            if(!empty($info)) {
//            	foreach ($info['info'] as $key=>$value) {
//                	$all_info['list'][$key] = array_merge($all_info['list'][$key],$value);
//            	}
//        	} else {
//        		foreach ($all_info['list'] as $key=>$value) {
//                	$all_info['list'][$key] = array_merge($all_info['list'][$key],array("taskId"=>0,"starPoint"=>0));
//            	}
//        	}
//        } else {
//            foreach ($all_info['list'] as $key=>$value) {
//                $all_info['list'][$key] = array_merge($all_info['list'][$key],array("taskId"=>0,"starPoint"=>0));
//            }
//        }
        $this->return['msg'] = "获取成功";
        $this->return['info'] = $all_info['list'];
        $this->return['totalPage'] = (empty($all_info['totalPage'])) ? 0 : $all_info['totalPage'];
        return $this->return;
    }


    /**
     * 获取需要提醒任务列表
     * @desc  检查服务器是否有最新的推送到数据库
     * @return array info.
     * @return string  order_no  单号
     * @return string  task_name  任务名字
     * @return int task_id  任务ID
     * @return int order_no 唯一标识
     * @return double lat  纬度
     * @return double lng  经度
     * @return string name  麦当劳(南山餐厅) 详细名称
     * @return string task_name  任务名称
     * @return string city  城市
     * @return string tl_id  地址ID
     * @return double  task_radius  任务范围
     * @return string city_code  城市代码
     * @return string task_lcon  标志的图片
     * @return string begin_time 开始时间
     * @return string end_time 结束时间
     * @return string keyword 关键字
     * @return int task_level 任务级别 0 普通 1紧急
     * @return string task_award 任务的期望值
     * @return string task_remark  任务简介
     * @return string distance 距离
     * @return   string    msg                           提示信息
     * @return   code    int                           提示信息
     */
    public function getRemindAd()
    {
        $this->userCheck();
        $user_task_logic = new TaskLogic();
        $field = 'a.task_id,a.task_level,a.task_award,a.task_remark,a.task_radius,a.order_no, a.task_name , a.city , a.city_code , a.task_lcon , FROM_UNIXTIME(a.begin_time,"%Y-%m-%d %H:%i") as begin_time ,FROM_UNIXTIME(a.end_time,"%Y-%m-%d %H:%i") as end_time,a.keyword';
        $Remind_task = $user_task_logic->getRemind($field,$this->lat,$this->lng);   //获取提醒的数据

        if(empty($Remind_task)) {
            $this->return['code'] = 1;
            $this->return['msg'] = "没有数据";
            return $this->return;
        }
        //获取到当天已经推送过的任务
        $userFrame = $user_task_logic->getFrameLogID($this->userID);
        if(!empty($userFrame)) {
                $userFra = $userFrame;
        }
        //获取到当前用户已经接受过的任务
        $userAcceptTask = $user_task_logic->getUserAcceptTask($this->userID);
        if(!empty($userAcceptTask)) {
            foreach ($userAcceptTask as $key=>$value) {
                $userAcTp[] = $value['task_id'];
            }
        }
        //从符合推送到任务中筛选出未接受和未推送的任务
        $info = [];
        foreach ($Remind_task as $key=>$value) {
            if(!in_array($value['task_id'],$userAcTp) && !in_array($value['task_id'],$userFra)) {
                $info[] = $value;
                $add_log_data[] = $value['task_id'];
            }
        }
        if(empty($info)) {
            $this->return['msg'] = "数据为空";
            $this->return['code'] = 1;
            return $this->return;
        }
        //记录当前用户已经弹窗的任务
        $user_task_logic->addFrameLog($this->userID,$add_log_data);
        $this->return['msg'] = "获取成功";
        $this->return['info'] = $info;
        return $this->return;
    }


}
