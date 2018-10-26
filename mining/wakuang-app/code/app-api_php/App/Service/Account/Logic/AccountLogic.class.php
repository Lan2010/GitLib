<?php
/**
 * Created by PhpStorm.
 * User: RLY
 * Date: 2018/6/16
 * Time: 16:07
 */

namespace Service\Account\Logic;

use Service\Account\Model;

class AccountLogic
{

    private $model = null;

    public function __construct()
    {
        $this->model = new model\AccountModel();
    }


    /**
     * 新增用户账户 在注册成功写入
     * @param type $userID
     * @return boolean
     */
    public function addAccount($userID)
    {
        if ($userID) {
            $this->model->addAccount($userID);
        }
        $add_data = array(
            'user_id'=>$userID,
            'status'=>1,
            'bus_type'=>'Register',
              'order_no'=>'',
              'available'=>getStarCountByCode("Register"),
              'unavailable'=>0,
              'trade'=>5,
              'add_datetime'=>time(),
              'remark'=>'注册送星星',
        );
        return $this->addAccountDetail($add_data); //手动插入
    }


    /**
     * 记录账户变更明细便捷方法
     */
    public function addAccountDetail($data) {
        $model =  M('AccountDetail');
        return $model->data($data)->add();
    }


    /**
     * zhanghu
     * @param $userID
     * @param $available
     * @param $orderNO
     * @param $remark
     * @return bool
     */
    public function recAccount($userID,$type, $available, $orderNO, $remark) {
        if ($userID * 1 > 0) {
            $result = $this->model->updateAccount($userID, $type, $orderNO, $available, 0, $available, $remark);
            if (!empty($result) && $result[0]['result'] == 1) {
                return true;
            }
        }
        return false;
    }

    /**
     * 获取到当前用户的账户
     * @param $userID
     */
    public function getAccount($userID) {
        return $this->model->getAccount($userID);
    }

}