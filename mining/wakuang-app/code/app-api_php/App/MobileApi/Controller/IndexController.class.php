<?php

namespace MobileApi\Controller;

use Service\Advertisement\Logic\AdvertisementLogic;
use Service\Common\CommonLogic;
use Service\Task\Logic\TaskLogic;
use Service\User\Logic\StarModuleLogic;
use Think\Controller;
use Service\Api\Common;
use Service\Api\Exception;
use Service\Api\Config;
use Service\System\Logic\SysParameterLogic;
use Service\Api\Crypt\Api3Des;
use MobileApi\Common\ResponseHtml;

/**
 * Description of IndexController
 *
 * @author Administrator
 */
class IndexController extends Controller {

    private static $HTML_LIST = array();

    public function _initialize() {//配置
        DI()->config = new Config\ApiConfigFile(APP_PATH . '/MobileApi/Config');
        $this->getSystemPar();
    }

    public function index() {
//        if (!isApp()) {
//            echo '非法请求！';
//            exit;
//        }
        $rs = $this->response();
        $rs->output();
    }

    /**
     * 获取系统参数。
     */
    private function getSystemPar() {
        $par = new SysParameterLogic();
        $config = $par->getConfig();
        C($config);
    }

    /**
     * 响应操作
     *
     * 通过工厂方法创建合适的控制器，然后调用指定的方法，最后返回格式化的数据。
     *
     * @return mixed 根据配置的或者手动设置的返回格式，将结果返回
     *  其结果包含以下元素：
      ```
     *  array(
     *      'ret'   => 200,	            //服务器响应状态
     *      'data'  => array(),	        //正常并成功响应后，返回给客户端的数据	
     *      'msg'   => '',		        //错误提示信息
     *  );
      ```
     */
    private function response() {
        $rs = DI()->response;
        $service = DI()->request->get('service', 'Index.Index');
        if (in_array(strtolower($service), self::$HTML_LIST)) {
            $rs = DI()->response = new ResponseHtml();
        }
        try {
            // 接口响应
            $this->changeConfig($service);
            $api = Common\ApiFactory::generateService();
            list($apiClassName, $action) = explode('.', $service);
            $data = call_user_func(array($api, $action));
            $rs->setData($data);
        } catch (Exception\BaseException $ex) {
            // 框架或项目的异常
            $rs->setRet($ex->getCode());
            $rs->setError($ex->getMessage());
            $rs->setJmpUrl($ex->getJmpUrl());
        } catch (Exception $ex) {
            // 不可控的异常
            logger_api($service, strval($ex));
            throw $ex;
        }
        return $rs;
    }

    /**
     * 根据接口名改变配置。
     * @param type $service
     */
    private function changeConfig($service) {
        if (strtolower($service) == 'common.timestamp') {
            DI()->config->set('app.apiEnabledTime', false);
        }
    }


    /**
     * 被动调用接口 ---接受任务
     *
     */
    public function setTask() {

        $list = I("post.");
        $data = array("code"=>0,"msg"=>"","info"=>"");
        if(empty($list) && !is_array($list) && empty($list['list'])) {
            $data["code"] = 1;
            $data['msg'] = "参数错误";
            echo json_encode($data);
        }
        $tasklogic = new TaskLogic();
        $where['order_no'] = $list['id'];
        $ret = $tasklogic ->getTask($where,array("task_id"));   //获取
        if(!empty($ret)) {
            $op = "save";
        }
        if(empty($list['list'])) {
            $data["code"] = 1;
            $data['msg'] = "无地址信息";
            echo json_encode($data);
        }
        $tasKdata = array(
            'order_no'=>$list['id'],
            'task_name'=>$list['taskName'],
            'task_radius'=>$list['taskRadius'],
            'city'=>$list['city'],
            'city_code'=>$list['cityCode'],
            'rate'=>$list['rate'],
            'task_lcon'=>$list['taskIcon'],
            'begin_time'=>$list['beginTime']/1000,
            'end_time'=>$list['endTime']/1000,
            'keyword'=>$list['keyword'],
            'add_detatime'=>time(),
            'area'=>$list['area'],
            'task_level'=>empty($list['taskLevel']) ? 0:$list['taskLevel'],
            'task_award'=>empty($list['taskAward']) ? 0:$list['taskAward'],
            'task_remark'=>empty($list['taskRemark']) ? "": $list['taskRemark'],
        );
        if($op == "save") {   //更新
            $tasklogic->update($where,$tasKdata);
            $tasklogic->updatelist($where,$tasKdata['begin_time'],$tasKdata['end_time']);
            $data['msg'] = "设置成功";
            echo json_encode($data);
            exit;
        } else {    //插入
            $res = $tasklogic->setTask($tasKdata);   //插入主表
            if(!$res) {
                $data["code"] = 1;
                $data['msg'] = "设置失败";
                echo json_encode($data);
                exit;
            }
            $list_data = json_decode(htmlspecialchars_decode($list['list']),true);
            $res = $tasklogic->setTasklist($list_data,$tasKdata['begin_time'],$tasKdata['end_time']);
            if(!$res) {
                $data["code"] = 1;
                $data['msg'] = "设置失败";
            } else {
                $data['msg'] = "设置成功";
            }
            echo json_encode($data);
            exit;
        }
    }

    /**
     * 被动接受广告 --接受广告
     */
    public function setAdvertisement() {

        //$str = '{"advertName":"\u7ef4\u8fbe","area":"","advertisementAttribute":"ADVERTISER","city":"\u6df1\u5733\u5e02","cityCode":"340","link":"http:\/\/www.hao123.com","advertisementType":"VIEW","totalCount":"232","advertPic":"http:\/\/192.168.11.32\/oms\/image\/20180707\/61253946-fc8c-4745-9002-1ca383bf2729.jpeg","advertIcon":"http:\/\/192.168.11.32\/oms\/image\/20180707\/737a250d-fcf9-4047-881c-795b777b06b7.jpeg","totalStarPoint":"332.0000","advertRemark":"\u7ef4\u8fbe\u8d85\u97e7","onceStarPoint":"1.4310","id":"20","beginTime":"1530947400000","endTime":"1530958200000","advertisementDescribe":"\u7ef4\u8fbe"}';

        //$list  = json_decode($str,true);


        $list = I("post.");

        $data = array("code"=>0,"msg"=>"","info"=>"");
        if(empty($list)) {
            $data['code'] = 1;
            $data['msg'] = "参数传递错误";
            echo json_encode($data);
            exit;
        }
        $logic = new AdvertisementLogic();
        $where = array('order_no'=>$list['id']);
        $res = $logic->getAd($where,array('ad_id'));
        $setData['advert_pic'] = $list['advertPic'];
        $setData['advert_icon'] = $list['advertIcon'];
        $setData['begin_time'] = $list['beginTime'] / 1000;
        $setData['end_time'] = $list['endTime'] / 1000;
        $setData['ad_url'] = $list['link'];
        $setData['advertisement_attribute'] = $list['advertisementAttribute'];
        $setData['advert_remark'] = $list['advertRemark'];
        $setData['advertisement_describe']  = ($list['advertisementDescribe'])? $list['advertisementDescribe'] :"";
        $setData['area'] = $list['area'];
        $setData['city'] = $list['city'];
        $setData['advert_name'] = $list['advertName'];
        $setData['city_code'] = $list['cityCode'];
        $setData['total_count'] = $list['totalCount'];
        $setData['total_count_view'] = $list['totalCount'];
        $setData['total_starpoint'] = $list['totalStarPoint'];
        $setData['once_starpoint'] = $list['onceStarPoint'];
        $setData['total_click_starpoint'] = $list['totalClickStarPoint'];
        $setData['once_click_starpoint'] = $list['onceClickStarPoint'];
        $setData['advertisement_type'] = $list['advertisementType'];
        $setData['order_no'] = $list['id'];

        if(!empty($res)) {
            $logic->update($where,$setData);
            $data['msg'] = "更新成功";
            echo json_encode($data);
            exit;
        } else {
            $res = $logic->setAdvertisement($setData);
        }
        if(!$res) {
            $data["code"] = 1;
            $data['msg'] = "设置失败";
        } else {
            $data['msg'] = "设置成功";
        }
        echo json_encode($data);
        exit;
    }

    /**
     * 接受推送星星奖励
     */
    public function setStartType() {
    	$data = array("code"=>0,"msg"=>"","info"=>"");
        $post_data = I("post.");
        if(empty($post_data)) {
            $data['code'] = 1;
            $data['msg'] = "参数传递错误";
            echo json_encode($data);
            exit;
        }

        foreach ($post_data as $key => $value) {
            $word = $this->transformation($key);
        	$add_data = array(
            	"type_code"=>($word)? $word : $key,
            	"type_value"=>$value,
        	);
        }

        $logic = new StarModuleLogic();
        $res = $logic->setStarType($add_data);
        if(!$res) {
            $data["code"] = 1;
            $data['msg'] = "设置失败";
        } else {
            $data['msg'] = "设置成功";
        }
        echo json_encode($data);
        exit;
    }

    /**
     * 转换
     */
    public function transformation($str) {
        $arr = array(
            "REGAWARD"=>"Register",
            "AUTHIDCARD"=>"Auth",
            "GENE"=>"Gene",
            "INVITATION"=>"Invitation",
            "ADDRESS"=>"Address",
            "ATTENTIONWEBCHAT"=>"Wechat",
            "VOICEDISCERN"=>"Voice",
            "FACEDEISCEERN"=>"Face",
            "BINDBANK"=>"BindBank",
            "HARDWAREBIND"=>"Charge",
        );
        return $arr[$str];
    }


    /**
     * 设置悬浮弹出框
     */
    public function setFrame() {


          $data = I("post.");
          if(empty($data)) {
              $result["code"] = 1;
              $result['msg'] = "数据为空";
              echo json_encode($result);
              exit;
          }
          $data['frame_icon']= $data['appPic'];
          $data['begin_datetime']= strtotime($data['beginTime']);
          $data['end_datetime']= strtotime( $data['endTime']);
          $data['link']= $data['appPicLink'];
          $data['add_datetime']= time();
          $comlogic = new CommonLogic();
          $res = $comlogic->setFrame($data);
          if(!$res) {
              $result["code"] = 1;
              $result['msg'] = "设置失败";
          } else {
              $result["code"] = 0;
              $result['msg'] = "设置成功";
          }
          echo json_encode($result);
          exit;
    }




}
