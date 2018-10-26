<?php

namespace MobileApi\Api;

use MobileApi\Common\MobileApiBase;
use Service\Account\Logic\AccountLogic;
use Service\System\Logic\DictionaryLogic;
use Service\User\Logic\BankLogic;
use Service\User\Logic\RealNameLogic;
use Service\User\Logic\StarModuleLogic;
use Service\User\Logic\UserAddressLogic;
use Service\System\Logic\SysRegionLogic;
use Service\User\Logic\UserLogic;

/**
 * Description of index
 *
 * @author Administrator
 */
class ApiReal extends MobileApiBase
{

    public $return = array('code' => 0, 'msg' => '', 'info' => array());

    public function getRules()
    {
        return array(
            'cardReal' => array(
                "name" => array("name" => "name", "type" => "string", "require" => true, "desc" => "名字"),
                "cardID" => array("name" => "cardID", "type" => "string", "require" => true, "desc" => "身份证号码"),
            ),
            'getCardInfo' => array(),
            'addBank' => array(
                'bankCardNo' => array("name" => 'bankCardNo', "type" => "string", "require" => true, "desc" => "银行卡号码"),
            ),
            'setUserAddress' => array(
                'province' => array("name" => 'province', 'type' => "int", "require" => true, "desc" => "省份ID"),
                'city' => array("name" => 'city', 'type' => "int", "require" => true, "desc" => "城市ID"),
                'district' => array("name" => 'district', 'type' => "int", "require" => true, "desc" => "街道ID"),
                'address' => array("name" => 'address', 'type' => "string", "require" => true, "desc" => "详细地址"),
                "zipCode" => array("name" => 'zipCode', 'type' => "string", "require" => false, "desc" => "邮政编码", "default" => "000"),
                "addressID" => array("name" => "addressID", 'type' => 'int', "require" => false, "desc" => "地址的ID 当为添加的时候不用传递 当为修改的时候需要传递"),
                'workUnit' => array("name" => "workUnit", "type" => "string", "require" => true, "desc" => "工作单位"),
                'workAddress' => array("name" => "workAddress", "type" => "string", "require" => true, "desc" => "工作地址"),
            ),
            "getCityData" => array(
                "parentID" => array("name" => "parentID", "type" => "int", "require" => true, "desc" => "父类ID", "default" => 1)
            ),

        );
    }

    /**
     * 用户身份证验证
     * @desc 用户的身份证信息认证
     * @return   int       code                          操作码，0表示成功， 1表示失败
     * @return   array    info
     * @return   int       info.timestamp                返回的参数
     * @return   string    msg                           提示信息
     */
    public function cardReal()
    {
        $this->userCheck();
        $name = $this->name;   //姓名
        $cardID = $this->cardID;  //身份证号码
        $logic = new RealNameLogic();
        $rs = $logic->getRealByCardID($cardID, $this->userID);
        if (!empty($rs)) {
            $this->return['code'] = 1;
            $this->return['msg'] = "存在同样的实名信息";
            return $this->return;
        }
        vendor("Grpc.php.IDCardAuth");
        $client = new \IDCardAuth();
        $result = $client->authIDCard($name, $cardID);
        if (!$result || $result["code"] != 201) {
            $this->return['code'] = 1;
            $this->return['msg'] = (empty($result["msg"]) ? "身份证校验失败！" : $result["msg"]);
            return $this->return;
        }
        $addData['real_name'] = $name;
        $addData['user_id'] = $this->userID;
        $addData['terminal'] = $this->terminal;
        $ret = $logic->addRealName($addData, $cardID);
        if ($ret * 1 > 0) {
            $userlogic = new StarModuleLogic();
            $userlogic->addModule($this->userID,'Auth');
            $award_logic = new UserLogic();    //grpc 增加奖励
            $award_logic->award($this->userID,"AUTHIDCARD");
            $this->return['msg']="验证成功";

        } else {
            $this->return['code'] = 1;
            $this->return['msg'] = "实名验证失败";
        }
        return $this->return;
    }


    /**
     * 获取到用户的实名信息
     * @desc 获取的用户的实名信息
     * @return  int code 0 代表成功 代表失败
     * @return  int  card_id  身份证
     * @return  string real_name  真实名字
     * @return  string birth_day 生日
     * @return  int age 年龄
     * @return int real_status 0 表示未实名 1表示实名
     * @return  string card_address 身份地址
     * @return  string msg 提示信息
     */
    public function getCardInfo()
    {
        $this->userCheck();
        $logic = new RealNameLogic();
        $userRealInfo = $logic->getRealByUserID($this->userID);
        if (empty($userRealInfo)) {
            $this->return['code'] = 1;
            $this->return['msg'] = "不存在实名的信息或者审核尚未通过";
        } else {
            $userRealInfo['card_id'] = hidCard4($userRealInfo['card_id']);
            $userRealInfo['real_name'] = hidRealName($userRealInfo['real_name']);
            $this->return['info'] = $userRealInfo;
        }
        return $this->return;
    }


    /**
     * 绑定银行卡
     * @desc 绑定银行卡
     * @return  int code 0成功 1失败
     * @return  string msg 提示
     * @return  array info 数据
     */
     public function  addBank() {
         $this->userCheck();
         $bankCardNo = $this->bankCardNo;
         $logic = new BankLogic();
         $rt  = $logic->getBankByCode($bankCardNo);
         if(!empty($rt)) {
             $this->return['code'] = 1;
             $this->return['msg'] = "存在银行卡信息";
             return $this->return;
         }
         $data['terminal'] = $this->terminal;
         $data['user_id'] = $this->userID;
         $res = $logic->addUserBank($data,$bankCardNo);
         if($res*1>0) {
             $userlogic = new StarModuleLogic();
             $userlogic->addModule($this->userID,'BindBank');

             $award_logic = new UserLogic();    //grpc 增加奖励
             $award_logic->award($this->userID,"BINDBANK");

             $this->return['msg'] = "绑定成功";
         } else {
             $this->return['code'] = 1;
             $this->return['msg'] = "绑定失败";
         }
         return $this->return;
     }



    /**
     * 获取到用户的银行卡信息
     * @desc 获取到用户保存的银行卡
     * @return int bank_id 对应的ID
     * @return int user_id 用户的ID
     * @return int real_name 用户的真实姓名
     * @return string bank_code 银行的标识
     * @return string 银行卡的名字 bank_name
     * @return string 银行卡的卡号 bank_card_no
     * @returns string 支行的号码 branch
     * @return int 添加时间 add_datetime
     * @return int code 0 正确 1失败
     * @return string msg 错误信息
     */
    public function getUserBankInfo()
    {
        $this->userCheck();
        $logic = new BankLogic();
        $info = $logic->getBankByUserID($this->userID);
        if (empty($info)) {
            $this->return['msg'] = "不存在信息或者尚未审核通过";
            $this->return['code'] = 1;
        } else {
            $info['bank_code'] = hidBankCard($info['bank_card_no']);
            $this->return['info'] = $info;
        }
        return $this->return;
    }


    /**
     * 获取到银行列表
     * @desc 获取到银行的列表
     * @return  int code 0成功 1失败
     * @return  string msg 提示
     * @return  array info 数据
     * @return  int info.dic_id 编码ID,
     * @return  int info.dic_parent_id  父编码ID,
     * @return string info.dic_name 节点名字
     * @return int info .dic_node 节点标识
     * @return string info dic_key 对应银行的标识 用于添加银行卡操作的参数
     *
     */
    public function getBankList()
    {
        $logic = new DictionaryLogic();
        $list = $userRealInfo = $logic->getBankList();
        $this->return['info'] = $list;
        return $this->return;
    }






     /**
      * 添加或者修改用户的地址
      * @desc 用户的地址设置
      * @return int code 0成功 1失败
      * @return string msg 提示
      * @return array info 信息
      */
     public function setUserAddress() {
         $this->userCheck();
         $addresslogic = new UserAddressLogic();
         $rt = $addresslogic->getdatAddress($this->userID,1);
         if(!empty($rt) && empty($this->addressID)) {
             $this->return['code']  = 1;
             $this->return['msg'] = "已经设置了地址了";
             return $this->return;
         }
         $data['province'] = $this->province;
         $data['city'] = $this->city;
         $data['district'] = $this->district;
         $data['address'] = $this->address;
         $data["zip_code"] = $this->zipCode;
         $data['work_unit'] = $this->workUnit;
         $data['work_address'] = $this->workAddress;
         $data['address_id'] = $this->addressID;
         $ret = $addresslogic->saveUserAddress($data,$this->userID);
         if(!$ret) {
             $this->return['code']=1;
             $this->return['msg'] = "操作失败";
         } else {
             if(!empty($data['address_id'])) {
                 $this->return["msg"] = "修改成功 ";
             } else {
                 $userlogic = new StarModuleLogic();
                 $userlogic->addModule($this->userID,'Address');  //添加模块

                 $award_logic = new UserLogic();    //grpc 增加奖励
                 $award_logic->award($this->userID,"ADDRESS");

                 $this->return["msg"] = "添加成功 ";
             }
         }
         return $this->return;
     }


     /**
       * 获取到用户的地址信息
       * @desc 返回用户的地址信息
       * @return int info.province 省份ID
       * @return int info.province_name 省份名字
       * @return int info.city 城市ID
       * @return int info.city_name 城市信息
       * @return int info.distreect 街道ID
       * @return int info.distreect_name 街道信息
       * @return int info.address_id 地址的id
       *@return strign info.address 地址的详情
      */
     public function getUserAddress() {
         $this->userCheck();
         $addresslogic = new UserAddressLogic();
         $ret = $addresslogic->getdatAddress($this->userID,1);
         if(empty($ret)) {
             $this->return['code'] = 1;
             $this->return['msg'] = "不存在地址";
         }
         $this->return['info'] = $ret;
         return $this->return;
     }



    /**
     * 地区信息
     * @desc 获取地区信息
     * @return  string   name        地区名称
     * @return  int      regionID    地区ID
     * @return  int      parentID    地区父类ID
     */
    public function getCityData()
    {
        $data = array('code' => 0, 'msg' => '', 'info' => array());
        $parentID = $this->parentID;
        $SysRegionLogic = new SysRegionLogic();
        $regionList = $SysRegionLogic->getDicRegList($parentID);
        if (!empty($regionList)) {
            $data["info"] = $regionList;
        } else {
            $data["code"] = 1;
            $data["msg"] = "没有此信息！";
        }
        return $data;
    }

















}
