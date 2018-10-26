<?php
/**
 * Created by PhpStorm.
 * User: RLY
 * Date: 2018/6/16
 * Time: 16:07
 */

namespace Service\Charge\Logic;

use Service\Charge\Model;

class ChargeLogic
{

    private $model = null;

    public function __construct()
    {
        $this->model = new model\ChargeModel();
    }


    /**获取绑定的信息
     *
     *
     *
     *
     * @param $mac
     * @return bool|mixed
     */
    public function getBindInfo($mac) {
        $where['device_id'] = $mac;
        return $this->model->getChargeInfo($where);
    }


    /**获取绑定的信息
     * @param $mac
     * @return bool|mixed
     */
    public function getInfoByUser($userID) {
        $where['bind_user_id'] = $userID;
        return $this->model->getChargeInfo($where);
    }


    /**更新绑定的信息
     * @param $userID
     * @param $mac
     */
    public function updateBindInfo($userID,$mac) {
        if(empty($mac)) {
            return;
        }
        $data['bind_user_id'] = $userID;
        $data['state'] = 2;
        $data['bind_datetime'] = time();
        $where['device_id'] = $mac;
        $where['state'] = 1;
        $this->chargeOpLog($userID,'bind',$mac);
        return $this->model->updateBindInfo($where,$data);
    }

    /**解绑操作
     * @param $userID
     */
    public function unbind($userID,$mac) {
        $data['bind_user_id'] = 0;
        $data['state'] = 1;
        $data['bind_datetime'] = 0;
        $where['bind_user_id'] = $userID;
        $where['state'] = 2;
        $this->chargeOpLog($userID,'unbind',$mac);
        return $this->model->updateBindInfo($where,$data);
    }

    /**
     * 日记
     *
     */
    public function chargeOpLog($userID,$type,$mac) {
        $model = M('ChargeLog');
        $data = array(
            'user_id'=>$userID,
            'op_type'=>$type,
            'device_id'=>$mac,
            'add_datetime'=>time(),
        );
        $model->add($data);
    }

    /**
     * 获取设备列表
     */
    public function getChargeList($where,$field,$limit="") {
        $sql = "select ".$field." from d_charge as c left join  d_user as u on u.user_id=c.bind_user_id where " .$where ;
        $model = new \Think\Model();
        $sql .= " order by charge_id desc";
        if(!empty($limit)) {
            $sql .= " limit ".$limit;
        }
        return $model->query($sql);
    }

    /**
     * 修改和新增
     */
    public function editOrAdd($data,$charge_id="") {
        if(empty($charge_id)) {
            unset($data['charge_id']);
            $data['add_datetime'] = time();
            return M('Charge')->add($data);
        } else {
            unset($data['charge_id']);
            return M('Charge')->where(array('charge_id'=>$charge_id))->save($data);
        }
    }


}