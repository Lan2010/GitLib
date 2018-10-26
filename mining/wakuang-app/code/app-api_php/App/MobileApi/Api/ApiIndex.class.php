<?php

namespace MobileApi\Api;

use MobileApi\Common\MobileApiBase;

/**
 * Description of index
 *
 * @author Administrator
 */
class ApiIndex extends MobileApiBase {

    public function getRules() {
        return array(
            'index' => array(
                array("name" => "param", "type" => "string", "require" => false, "desc" => "描述")
            ),
        );
    }

    /**
     * 实例
     * @desc   测试用例
     * @return   int       code                          操作码，0表示成功， 1表示失败
     * @return   array    info 
     * @return   int       info.timestamp                返回的参数
     * @return   string    msg                           提示信息
     */
    public function index() {
         Vendor("Grpc.php.Account_Reg");
         $ob = \Reg();
         print_r($ob);
         exit;
    }


    
    





}
