<?php
/**
 * Created by PhpStorm.
 * User: RLY
 * Date: 2018/6/27
 * Time: 13:36
 */

namespace MobileApi\Api;

use Common\Common\Redis;
use MobileApi\Common\MobileApiBase;
use Service\Advertisement\Logic\AdvertisementLogic;
use Service\Nats\Logic\NatsLogic;
use Service\Task\Logic\TaskLogic;
use Service\User\Logic\UserLogic;

class ApiAccount extends MobileApiBase
{

    public function getRules()
    {
        return array(
            'info' => array(),
            'ranking' => array(),
            'records' => array(
                'pageindex' => array('name' => 'pageindex', 'type' => 'int', 'default' => 1, 'desc' => '页码'),
                'pagesize' => array('name' => 'pagesize', 'type' => 'int', 'default' => 15, 'desc' => '每页显示数量')
            ),
            'unCollection' => array(
                "cityCode" => array("name"=>'cityCode','type'=>'int','default'=>340,'desc'=>'城市的代号'),
            ),
            'clickAd' => array(
                'advertId' => array('name' => 'advertId', 'type' => 'int', 'default' => 1, 'desc' => '广告ID'),
                'starPoint' => array('name' => 'starPoint', 'type' => 'float', 'default' => 1, 'desc' => '星点数'),
                'adStarPointType' => array('name' => 'adStarPointType', 'type' => 'int', 'default' => 0, 'desc' => '收取的星星类型,浏览为0 1为点击'),

            ),
            'collection' => array(
                'recordToken' => array('name' => 'recordToken', 'type' => 'string', 'default' => '', 'desc' => '记录Token'),
                'advertId' => array('name' => 'advertId', 'type' => 'int', 'default' => '', 'desc' => "广告的ID"),

                'taskId'=>array('name' => 'taskId', 'type' => 'int', 'default' => '-1', 'desc' => "任务的ID，非任务星星不需要填写"),
            )
        );
    }


    /**
     * 获取用户账户信息。
     * @desc 用于获取用户账户信息
     * @return int code                 操作码，0表示成功， 1登录失败
     * @return array info               账户信息
     * @return string info.availableStarPoint  可用星星数
     * @return string info.frozenStarPoint     冻结信息数
     * @return string msg 提示信息
     */
    public function info()
    {
        $this->userCheck();
        $rs = array('code' => 1, 'msg' => '账户获取失败', 'info' => array());
        $logic = new UserLogic();
        $userinfo = $logic->getUser($this->userID);
        if ($userinfo && !empty($userinfo["user_token"])) {
            vendor("Grpc.php.AccountStarPoint");
            $client = new \AccountStarPoint();
            $accinfo = $client->starPoint($userinfo["user_token"]);
            if ($accinfo && $accinfo["code"] == 200) {
                $code = $accinfo["code"];
                $rs["msg"] = $accinfo["msg"];
                if ($code == 200) {
                    $rs["code"] = 0;
                    $rs["info"] = array(
                        "availableStarPoint" => $accinfo["availableStarPoint"],
                        "frozenStarPoint" => $accinfo["frozenStarPoint"]
                    );
                }
            } else {
                $rs["msg"] = "Grpc服务异常！";
            }
        }
        return $rs;
    }

    /**
     * 星星流水信息。
     * @desc 用于获取星星流水信息
     * @return int code 操作码，0表示成功， 1表示失败
     * @return int totalPage 总页码数
     * @return object info 标信息对象
     * @return string info.operStarPoint 星星数
     * @return string info.createTime 时间
     * @return string info. operationType 操作类型
     * @return string info. recordsType 星星获取来源
     * @return string info.remark 备注
     * @return string msg 提示信息
     */
    public function records()
    {
        $this->userCheck();
        $rs = array('code' => 1, 'msg' => '没有记录', "totalPage" => 0, 'info' => array());
        $logic = new UserLogic();
        $userinfo = $logic->getUser($this->userID);
        if ($userinfo && !empty($userinfo["user_token"])) {
            vendor("Grpc.php.AccountStarPoint");
            $client = new \AccountStarPoint();
            $result = $client->records($userinfo["user_token"], ( ($this->pageindex-1) * $this->pagesize), $this->pagesize);
            if ($result) {
                $code = $result["code"];
                $rs["msg"] = $result["msg"];
                if ($code == 200) {
                    $records = $result["records"];
                    $rs["totalPage"] = $result["total"];
                    foreach ($records as $record) {
                        if($record["operStarPoint"] != 0) {
                            $rs["info"][] = array(
                                "operStarPoint" => $record["operStarPoint"],
                                "createTime" => date('Y-m-d H:i',$record["createTime"]/1000),
                                "operationType" =>$this->switchOpType($record['operationType']),
                                "recordsType"=>$this->switchRecordsType($record['recordsType'],$record['remark']),
                                "remark" => $record["remark"]
                            );
                        }
                    }
                    $rs["code"] = (count($rs["info"]) == 0 ? 1 : 0);
                }
            } else {
                $rs["msg"] = "Grpc服务异常！";
            }
        }
        if(empty($rs['info'])) {
        	$rs['code'] = 1;
        	$rs['msg'] = "数据为空";
        }
        return $rs;
    }


    protected function switchRecordsType($str,$remark) {

        $detail = array(
            "REGAWARD"=>"注册",
            "AUTHIDCARD"=>"实名认证",
            "GENE"=>"生物数据",
            "INVITATION"=>"邀请好友",
            "ADDRESS"=>"送货地址",
            "ATTENTIONWEBCHAT"=>"关注微信公众号",
            "VOICEDISCERN"=>"声音识别",
            "FACEDEISCEERN"=>"人脸识别",
            "BINDBANK"=>"绑定银行卡",
            "HARDWAREBIND"=>"绑定矿机",
            "BINDADDRESSLIST"=>"通讯录邀请好友",
        );
        if($str == "AWARD") {
            return $detail[$remark];
        } else {
            switch ($str) {
                case "TASK":
                    return "领取星星";
                case "ADVERTISEMENT":
                    return "领取星星";
                case "BASIC":
                    return "基本";
                case "RANDOM":
                    return "领取星星";
                case "CONSUME":
                    return "消费";
                default:
                    return "未知";
            }
        }
    }



    protected function switchOpType($str) {
        switch ($str) {
            case "CONSUME":
                return "消费";
            case "INCREMENT":
                return "增量";
            case "FROZEN":
                return "冻结";
            default:
                return "未知";
        }
    }

    /**
     * 待收取星星。
     * @desc 用于获取收取星星信息
     * @return int code 操作码，0表示成功， 1表示失败
     * @return object info 标信息对象
     * @return string info.operStarPoint 星点数
     * @return string info.longitudeAndLatitude 经纬度
     * @return string info.recordsType 记录类型 (TASK，RANDOM)
     * @return string info.taskId 任务ID
     * @return string info.advertisementId 广告ID
     * @return string info.remark 备注
     * @return string info.createTime 创建时间
     * @return string info.advertPic 展示图
     * @return string info.advertIcon 广告logo
     * @return string info.ad_url 广告链接
     * @return string info.ad_id 广告的ID
     * @return string info.onceStarPoint 每次点击的浏览星星数
     * @return string info.advertName 广告的名字
     * @return string info.advertRemark 广告的备注
     * @return string  user_browse_starpoint  已经被获取到的浏览星星数量
     * @return string  onceClickStarPoint   点击一次获取到的星星数量
     * @return string info.advertisement_describe 广告的描述
     * @return string msg 提示信息
     */
    public function unCollection()
    {
        $this->userCheck();
        $rs = array('code' => 1, 'msg' => '没有待收星星！', 'info' => array());
        $logic = new UserLogic();
        $userinfo = $logic->getUser($this->userID);
        if ($userinfo && !empty($userinfo["user_token"])) {
            vendor("Grpc.php.AccountStarPoint");
            $client = new \AccountStarPoint();
            $result = $client->unCollectionRecords($userinfo["user_token"]);

            if(empty($result['records'])) {
            	$rs['msg'] = "获取到星星为空";
            	return $rs;
            }
            if ($result) {
                $code = $result["code"];
                $rs["msg"] = $result["msg"];
                if ($code == 200) {
                    $records = $result["records"];
                    $taskStars = array();
                    $randomStars = array();
                    foreach ($records as $key => $item) {
                        $item["createTime"] = formatshortDate($item["createTime"]/1000);
                        if ($item["recordsType"] == "TASK") {
                            $taskStars[] = $item;
                        } else {
                            $randomStars[] = $item;
                        }
                    }
                    $records = $this->mergeStars($taskStars, $randomStars);   //合并星星
                    $ad_logic = new AdvertisementLogic();
                    $ad_data = $ad_logic->getEffectiveAd($this->cityCode);  //获取到全部的广告信息
                    if(empty($ad_data)) {   //广告位空
                        $ad_data = $ad_logic->getDefault($this->cityCode);  //获取到默认的
                    }
                    foreach ($records as $k=>$v){    //合并
                        $randad = $ad_logic->getRandAd($ad_data);
                        if(!empty($randad)) {
                            $arr[] = array('url' => $randad['advertPic']);
                            $randad['advertPic'] = $arr;
                            //$randad['onceStarPoint'] = ($randad['user_browse_starpoint'] >= $randad['totalStarPoint'])? 0: $randad['onceStarPoint'];
                        } else {
                            $randad['advertIcon'] = UPLOAD ."/upload/logo.png";
                            $randad["advertPic"] = array(array("url"=>  UPLOAD ."/upload/logo.png"));
                            $randad["ad_url"]  = "http://www.baidu.com";
                            $randad['ad_id'] = 0;
                            $randad["onceStarPoint"] = 0;
                            $randad["advertName"] = "天智星默认";
                            $randad['advertRemark'] = "天智星";
                            $randad['advertisement_describe'] = "天智星";
                            $randad['onceClickStarPoint'] = 0;
                            $randad['user_browse_starpoint'] = 0;
                            $randad['totalStarPoint'] = 0;
                        }
                        $records[$k] = array_merge($records[$k],$randad);
                        $arr = array();
                    }
                    $rs["code"] = (count($records) == 0 ? 1 : 0);
                    $rs["info"] = $records;
                }
            } else {
                $rs["msg"] = "Grpc服务异常！";
            }
        }
        return $rs;
    }

    /**
     * 合并星星
     * @param $taskStars
     * @param $randomStars
     */
    private function mergeStars($taskStars, $randomStars)
    {
        if (empty($taskStars) && empty($randomStars)) {
            return array();
        }
        $taskCount = count($taskStars);   //任务星星的总数
        $randomCount = count($randomStars);  //随机星星的总数

        if ($randomCount > 5) {
            $randomStars = $this->mergeStarsByGroup($randomStars, 5);   //合并随机星星
        }
        if ($taskCount > 1) {
            $taskStars = $this->mergeStarsByTaskId($taskStars);   //合并任务星星
        }
        if(!empty($taskStars)) {  //不为空
        	$this->cacheUnCollection($this->userID,$taskStars);   //缓存星星
        }
        return array_merge($taskStars, $randomStars);
    }


    /**
     * 缓存用户待收集的星星
     */
    protected function cacheUnCollection($user_id,$data) {
        $key = $user_id."_cacheUncollection";
        $logic = Redis::GetInstance();
        $info = $logic->get($key);
        if(!empty($info)) {
            $logic->del($key);
        }
        $str = json_encode($data);
        $logic->set($key,$str,1000);
    }

    /**
     * 按任务ID合并星星
     * @param $starsArr
     */
    private function mergeStarsByTaskId($starsArr)
    {
        $taskIdArr = array_unique(array_column($starsArr, "taskId"));
        $newTaskStars = array();
        foreach ($taskIdArr as $taskId) {
            $starArr = array();
            foreach ($starsArr as $item) {
                if ($item["taskId"] == $taskId) {
                    $starArr[] = $item;
                }
            }
            if (0 < count($starArr)) {
                $starArr[0]["recordToken"] = implode(",", array_column($starArr, "recordToken"));
                $starArr[0]["operStarPoint"] = array_sum(array_column($starArr, "operStarPoint"));
                $newTaskStars[] = $starArr[0];
            }
        }
        return $newTaskStars;
    }

    /**
     * 分组合并星星
     * @param $starsArr
     * @return array
     */
    private function mergeStarsByGroup($starsArr, $num = 1)
    {
        if ($num * 1 < 1) {
            return $starsArr;    //分组的标识
        }
        $num_tmp = $num;
        $count = count($starsArr);   //全部星星
        $newStars = array();   //新的星星数量
        $starArr = array();    //新的星星数组
        $j = intval($count / $num);    //求整数
        for ($i = 0; $i < $count; $i++) {
            $starArr[] = $starsArr[$i];
            if ( (0 == (($i + 1) % $j) && ($num_tmp > 1) ) || $count == ($i + 1)) {
                $starArr[0]["recordToken"] = implode(",", array_column($starArr, "recordToken"));
                $starArr[0]["operStarPoint"] = array_sum(array_column($starArr, "operStarPoint"));
                $newStars[] = $starArr[0];
                $starArr = array();
                $num_tmp --;
            }
        }
        return $newStars;
    }

    /**
     * 收取星星。
     * @desc 用于收取星星
     * @return int code                 操作码，0表示成功， 1登录失败
     * @return array info               账户信息
     * @return string info.availableStarPoint  可用星星数
     * @return string info.frozenStarPoint     冻结信息数
     * @return string msg 提示信息
     */
    public function collection()
    {
        $this->userCheck();
        $rs = array('code' => 1, 'msg' => '收取失败!', 'info' => array());
        $logic = new UserLogic();
        $userinfo = $logic->getUser($this->userID);
        if ($userinfo && !empty($userinfo["user_token"])) {
            vendor("Grpc.php.AccountStarPointOperation");
            $client = new \AccountStarPointOperation();
            $result = $client->collection($userinfo["user_token"], $this->recordToken,$this->advertId);
            if ($result) {
                if(!empty($this->advertId)) {
                    $ad_logic = new AdvertisementLogic();   //减去广告
                    $ad_info = $ad_logic->getAd(array("order_no" => $this->advertId), array("total_starpoint","user_browse_starpoint","once_starpoint","ad_url"));
                    if(empty($ad_info[0]['ad_url'])) {  //是否为纯粹的浏览星星
                        $ad_logic->setInc(array("order_no" => $this->advertId));
                    }
                    $ad_logic->addNum(array("order_no" => $this->advertId),$ad_info[0]['once_starpoint']);  //加上
                }
                $task_logic = new TaskLogic();
                $task_logic->addUserTaskPoint($this->taskId,$this->userID);   //给该任务记录上加上星星值
                $rs['code'] = ($result["code"] == 200 ? 0 : 1);
                $rs['msg'] = $result['msg'];
                $rs["info"] = array(
                    "starpoint"=> $result['starpoint'],
                    "availableStarPoint" => $result["availableStarPoint"],
                    "frozenStarPoint" => $result["frozenStarPoint"]
                );
            } else {
                $rs["msg"] = "Grpc服务异常！";
            }
        }
        return $rs;
    }

    /**
     * 获取排行榜。
     * @desc 用于获取到排行信息
     * @return int code                 操作码，0表示成功， 1 没有相关数据
     * @return array info               信息
     * @return string info.num          排行
     * @return string info.phone        可用星星数
     * @return string info.starPoint    冻结信息数
     * @return string msg 提示信息
     */
    public function ranking()
    {
        $rs = array('code' => 1, 'msg' => '没有数据！', 'info' => array());
        vendor("Grpc.php.AccountStarPoint");
        $client = new \AccountStarPoint();
        $result = $client->ranking();
        if ($result) {
            $code = $result["code"];
            $rs["msg"] = $result["msg"];
            if ($code == 200) {
                $rs["code"] = 0;
                $logic = new UserLogic();
                $records = $result["records"];
                $i = 1;
                foreach ($records as $record) {
                    $userinfo = $logic->getByToken($record["accountToken"]);
                    $rs["info"][] = array(
                        "num" => (string)$i,
                        "phone" => (empty($userinfo["phone"]))? hidPhone('13100004545'):hidPhone($userinfo["phone"]),
                        "starPoint" => $record["starPoint"]
                    );
                    $i++;
                }
            }
        } else {
            $rs["msg"] = "Grpc服务异常！";
        }
        return $rs;

    }

    /**
     * 点击广告。
     * @desc 用于点击广告
     * @return int code                 操作码，0表示成功， 1登录失败
     * @return array info               账户信息
     * @return string info.availableStarPoint  可用星星数
     * @return string info.frozenStarPoint     冻结信息数
     * @return string msg 提示信息
     */
    public function clickAd()
    {
        $this->userCheck();
        $rs = array('code' => 1, 'msg' => '操作失败！', 'info' => array());
        $mon_res = $this->Monitor($this->userID);   //是否过频繁访问
        if(!$mon_res) {
            $rs['msg'] = "访问过频繁";
            return $rs;
        }
        $logic = new UserLogic();
        $userinfo = $logic->getUser($this->userID);
        $ad_logic = new AdvertisementLogic();
        $field = array("total_count","order_no","ad_url","advertisement_type","advert_name",
                        "advert_remark","once_starpoint",'total_starpoint','user_browse_starpoint','once_click_starpoint'
                       );
        $ad_info = $ad_logic->getAd(array("order_no" => $this->advertId),$field);
        if ($ad_info[0]['total_count'] <= 0) {   //先统一检查
            $rs["code"] = 1;
            $rs["msg"] = "星星被收取了";
            return $rs;
        }
        if($this->adStarPointType == 1) {   //当时点击星星的时候
            if(empty($ad_info[0]['ad_url'])) {
                $rs["code"] = 1;
                $rs["msg"] = "星星的类型不对";
                return $rs;
            }
            $this->starPoint = $ad_info[0]['once_click_starpoint'];   //由前端传递 改成 后台自己读取
        } else {
            if(empty($ad_info[0]['ad_url'])) {      //当为纯粹的浏览星星时

            } else {     //当是点击星星且是浏览星值的时候 限制下
                $res = $this->getlock($this->userID,$this->advertId);
                if(!$res) {
                    $rs['code'] = 1;
                    $rs["msg"] = "浏览星数用尽";
                    return $rs;
                }
                //限制下
                if($ad_info[0]['user_browse_starpoint'] >= $ad_info[0]['total_starpoint']){
                    $rs['code'] = 1;
                    $rs["msg"] = "浏览星数用尽";
                    return $rs;
                }
            }
            $this->starPoint = $ad_info[0]['once_starpoint'];
        }


        if ($userinfo && !empty($userinfo["user_token"])) {
            vendor("Grpc.php.AdvertisementOperation");
            $client = new \AdvertisementOperation();
            $result = $client->click($userinfo["user_token"], $this->advertId, $this->starPoint,$this->adStarPointType);
            if ($result) {

                if($result["code"] == 200)  {   //成功的时候
                    if($this->adStarPointType == 1) {  //如果是点击的话
                        $ad_logic->setInc(array("order_no" => $this->advertId));  //减去
                    } else {
                        if(empty($ad_info[0]['ad_url'])) {   //纯粹的浏览星星
                            $ad_logic->setInc(array("order_no" => $this->advertId));  //减去
                        } else {
                            $this->setlock($this->userID,$this->advertId);  //加上锁
                        }
                        $ad_logic->addNum(array("order_no" => $this->advertId),$ad_info[0]['once_starpoint']);   //加上用户的浏览星星数
                    }
                }

                $rs['code'] = ($result["code"] == 200 ? 0 : 1);
                $rs['msg'] = $result['msg'];
                $rs["info"] = array(
                    "availableStarPoint" => $result["availableStarPoint"],
                    "frozenStarPoint" => $result["frozenStarPoint"]
                );

                //nat推送
                $publish["mobile"] = (empty($userinfo['mobile']))? "": $userinfo['mobile'];//选填
                $publish["advertId"] = $this->advertId;
                $publish["advertLink"] = $ad_info[0]['ad_url'];
                $publish["advertType"] =$ad_info[0]['advertisement_type'];
                $publish["advertName"] =$ad_info[0]['advert_name'];
                $publish["advertInfo"] =$ad_info[0]['advert_remark'];
                $logic = new NatsLogic();
                $logic->clickAdvertisement($this->terminal,$publish);    //nats 推送

            } else {
                $rs["msg"] = "Grpc服务异常！";
            }
        }
        return $rs;
    }

    //获取到锁
    private function getlock($userid,$adID) {
        $key = "key".$userid."_".$adID."_view";
        $cache = Redis::GetInstance();
        $d = $cache ->get($key);
        if($d['num'] >= 400) {
            return false;
        }
        return true;
    }


    //添加锁
    private function setlock($userid,$adID) {
        $key = "key".$userid."_".$adID."_view";
        $cache = Redis::GetInstance();
        $d = $cache->get($key);
        if(empty($d['num'])) {
            $cache->set($key,json_encode(array("num"=>1)),84600);
        } else {
            $cache->set($key,json_encode(array("num"=>$d['num']+1)),84600);
        }
    }


//    /**
//     * 监控
//     *
//     * 用户10秒之内只能访问8次 8次之后为过频
//     */
//    private function Monitor($userID) {
//        $key = "key_monitor_time_".$userID;
//        $cache = Redis::GetInstance();
//        $info = $cache->get($key);
//        if(empty($info)) {
//            $cache->set($key,json_encode(array("opTime"=>time(),'num'=>1)),10);
//            return true;
//        } else {
//            $date = time();  //当前的时间
//            if($date - $info['opTime'] <= 10 ) {   //10秒之内 5次
//                if($info['num'] >= 5) {   //过频 不允许访问
//                    return false;
//                } else {
//                    $time = 10 - ($date-$info['opTime']);  //剩余的秒数
//                    if(intval($time)>0) {  //剩余秒数不为0的时候
//                        $cache->set($key,json_encode(array("opTime"=>$info['opTime'],'num'=>1+$info['num'])),$time);
//                    }
//                }
//            } else {
//                $cache->del($key);
//            }
//        }
//        return true;
//    }

    /**
     * 监控
     *
     * 用户10秒之内只能访问8次 8次之后为过频
     */
    private function Monitor($userID) {
        $key = "key_monitor_time_".$userID;
        $lock_key = $userID.'_clickAd_lock';
        $cache = Redis::GetInstance();
        $info = $cache->get($key);
        if(empty($info)) {
            $cache->set($key,json_encode(array("opTime"=>time(),'num'=>1)),10);
            return true;
        } else {
            $date = time();  //当前的时间
            if($date - $info['opTime'] <= 10 ) {   //10秒之内 5次
                if($info['num'] >= 5) {   //过频 则为非法用户 直接锁定一个小时
                    $cache->set($lock_key,json_encode(array('lock'=>'22')),3600);
                    return false;
                } else {
                    $time = 10 - ($date-$info['opTime']);  //剩余的秒数
                    if(intval($time)>0) {  //剩余秒数不为0的时候
                        $cache->set($key,json_encode(array("opTime"=>$info['opTime'],'num'=>1+$info['num'])),$time);
                    }
                }
            } else {
                $cache->del($key);
            }
        }
        $data = $cache->get($lock_key); //检测是否上锁
        if(!empty($data)) {
            return false;
        }
        return true;
    }



}