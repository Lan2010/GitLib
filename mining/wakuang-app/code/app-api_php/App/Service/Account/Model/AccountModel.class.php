<?php
/**
 * Created by PhpStorm.
 * User: RLY
 * Date: 2018/6/16
 * Time: 16:32
 */

namespace Service\Account\Model;

use Common\Model\SlaveModel;
use Think\Exception;

class AccountModel extends SlaveModel
{
    /**
     * 新增用户账户 在用户注册的时候写入
     * @param type $userID
     * @return boolean
     */
    public function addAccount($userID)
    {
        if (isset($userID)) {
            $data['user_id'] = $userID;
            $data['edit_datetime'] = time();
            $data['available'] = getStarCountByCode("Register");  //获取到注册的需要多少星星
            $exist = $this->field("user_id")->where($data)->find();
            if ($exist["user_id"] * 1 > 0) {
                return $userID;
            }
            $result = $this->add($data);
            return $result;
        }
        return false;
    }

    /**
     * 账户变更记录
     */


    /**
     * 获取用户账户
     * @param $userID
     * @return bool
     */
    public function getAccount($userID)
    {
        if ($userID) {
            $where["user_id"] = $userID;
            $result = $this->where($where)->find();
            return $result;
        }
        return false;
    }


    /**
     * 账户变更操作
     * @param type $userID
     * @param type $available
     * @param type $unavailable
     * @param type $type
     * @param type $orderNo
     * @param type $trade
     * @param type $remark
     * @return type
     */
    public function updateAccount($userID, $type, $orderNo, $available, $unavailable, $trade, $remark)
    {

        $result = $this->query("CALL sp_update_account('%d','%s','%s','%f','%f','%f','%s')",
            $userID, $type, $orderNo, $available, $unavailable, $trade, $remark);
        return $result;
    }
}