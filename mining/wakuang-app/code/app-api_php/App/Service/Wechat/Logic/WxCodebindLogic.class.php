<?php
/**
 * Created by PhpStorm.
 * User: RLY
 * Date: 2018/6/16
 * Time: 16:07
 */

namespace Service\Wechat\Logic;

use Service\Wechat\Model;

class WxCodebindLogic
{

    private $model = null;

    public function __construct()
    {
        $this->model = new model\WxCodebindModel();
    }

    /**生成或者插入
     * @param $openID
     * @return mixed
     */
    public function getCode($openID) {
        $res = $this->model->getOpenInfo($openID);
        if(empty($res)) {
            $add_Data  =  array(
                "code"=>mt_rand(1000,9999),
                'open_id'=>$openID,
                'user_id'=>0,
                'add_detatime'=>time(),
            );
            $this->model->add($add_Data);
            return $add_Data['code'];
        } else if(empty($res['user_id'])) {
            return $res['code'];
        } else {
            return -1; //已经绑定过了
        }
    }


    /**
     * 验证和绑定
     */
    public function setCode($userID,$code) {
        $add_data = array(
            'user_id'=>$userID,
            'bind_deatatime'=>time(),
        );
        return $this->model->where(array('code'=>$code))->save($add_data);
    }

    public function getInfoByCode($code) {
        return $this->model->where(array('code'=>$code))->find();
    }




}