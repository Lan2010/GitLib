<?php
/**
 * Created by PhpStorm.
 * User: RLY
 * Date: 2018/6/26
 * Time: 9:10
 */
/*
 *
 * Copyright 2015 gRPC authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

// php:generate protoc --proto_path=./../protos   --php_out=./   --grpc_out=./ --plugin=protoc-gen-grpc=./../../bins/opt/grpc_php_plugin ./../protos/helloworld.proto

require dirname(__FILE__) . '/vendor/autoload.php';

@include_once dirname(__FILE__) . '/Mobile/CodeInfo.php';
@include_once dirname(__FILE__) . '/Mobile/MobileValidationSerivceClient.php';
@include_once dirname(__FILE__) . '/Mobile/SearchCodeInfo.php';
@include_once dirname(__FILE__) . '/Mobile/SearchResultInfo.php';
@include_once dirname(__FILE__) . '/Mobile/SendCodeInfo.php';
@include_once dirname(__FILE__) . '/Mobile/SendCodeInfo_SendCodeType.php';
@include_once dirname(__FILE__) . '/Mobile/SendCodeResult.php';
@include_once dirname(__FILE__) . '/Mobile/ValidationCodeInfo.php';
@include_once dirname(__FILE__) . '/Mobile/ValidationResult.php';
@include_once dirname(__FILE__) . '/Mobile/ValidationResult_Status.php';
@include_once dirname(__FILE__) . '/GPBMetadata/MobileValidationService.php';
@include_once dirname(__FILE__) . '/GPBMetadata/ResponseEntity.php';
@include_once dirname(__FILE__) . '/ResponseEntity.php';

class MobileValidation
{
    private $client = null;

    public function __construct()
    {
        $this->client = new Mobile\MobileValidationSerivceClient(IDGRPC_HOSTNAME, [
            'credentials' => Grpc\ChannelCredentials::createInsecure(),
        ]);
    }

    /**
     * 发送
     * @param $phone
     * @param $userID
     * @return array|bool
     */
    function send($phone)
    {
        if (empty($phone) ) {
            return false;
        }
        $request = new Mobile\SendCodeInfo();
        $request->setMobile($phone);
        $request->setToken(IDGRPC_TOKEN);
        $request->setCodeLength(6);
        $request->setSendCodeType(0);

        list($reply, $status) = $this->client->send($request)->wait();
        if (empty($reply)) {
            return false;
        }
        return array("code" =>$reply->getCode(), "msg" => $reply->getMessage(), "mobile" => $reply->getMobile(), "codeToken"=>$reply->getCodeToken(), "sendPlatform"=>$reply->getSendPlatform() );
    }


    /**验证
     * @param $inputCode
     * @param $codeToken
     * @param $mobile
     * @param $token
     */
    function validate($inputCode,$codeToken,$mobile) {
        if(empty($inputCode) || empty($codeToken)||empty($mobile)) {
            return false;
        }
        $request = new Mobile\ValidationCodeInfo();
        $request->setMobile($mobile);
        $request->setCodeToken($codeToken);
        $request->setInputCode($inputCode);
        $request->setToken(IDGRPC_TOKEN);

        list($reply,$status) = $this->client->validate($request)->wait();
        if(empty($reply)) {
            return false;
        }
        return array('code'=>$reply->getCode(),'message'=>$reply->getMessage(),'status'=>$reply->getStatus());
    }

    /**检索信息
     * @param $mobile
     * @return array|bool
     */
    function search($mobile) {
        if(empty($mobile)) {
            return false;
        }
        $request = new \Mobile\SearchCodeInfo();
        $request->setMobile($mobile);
        $request->setToken(IDGRPC_TOKEN);

        list($reply,$status) = $this->client->search($request)->wait();
        if(empty($reply)) {
            return false;
        }
        $list = $reply->getMap();
        if(empty($list)) {
            return false;
        }
        foreach ($list as $key=>$value) {
            $mes['status'] = $value->getStatus();
            $mes['ValidationCode'] = $value->getValidationCode();
            $mes['SenderOrg'] = $value->getSenderOrg();
            $mes_list[] = $mes;
        }
        return array("code"=>$reply->getCode(),'message'=>$reply->getMessage(),'map'=>$mes_list,'mobile'=>$reply->getMobile());
    }

}
