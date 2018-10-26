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

@include_once dirname(__FILE__) . '/Account/Reg/AccountInfo.php';
@include_once dirname(__FILE__) . '/Account/Reg/AccountRegServiceClient.php';
@include_once dirname(__FILE__) . '/Account/Reg/Result.php';
@include_once dirname(__FILE__) . '/GPBMetadata/AccountReg.php';
@include_once dirname(__FILE__) . '/GPBMetadata/ResponseEntity.php';
@include_once dirname(__FILE__) . '/Account/Reg/CheckMobileInfo.php';
@include_once dirname(__FILE__) . '/Account/Reg/CheckMobileResult.php';
@include_once dirname(__FILE__) . '/ResponseEntity.php';


class AccountRegister
{
    private $client = null;

    public function __construct()
    {
        $this->client = new Account\Reg\AccountRegServiceClient(GRPC_HOSTNAME, [
            'credentials' => Grpc\ChannelCredentials::createInsecure(),
        ]);
    }

    /**
     * 账户注册
     * @param $phone
     * @param $userID
     * @return array|bool
     */
    function reg($phone, $userID,$referrerToken="")
    {
        if (empty($phone) || $userID * 1 < 1) {
            return false;
        }

        $request = new Account\Reg\AccountInfo();
        $request->setMobile($phone);
        $request->setRegTime(time()*1000);
        $request->setThirdToken($userID);
        $request->setToken(GRPC_TOKEN);
        if(!empty($referrerToken)) {
            $request->setReferrerToken($referrerToken);
        }
        list($reply, $status) = $this->client->reg($request)->wait();
        if (empty($reply)) {
            return false;
        }
        $message = $reply->getResponseEntity();
        if (empty($message)) {
            return false;
        }
        return array("code" => $message->getCode(), "msg" => $message->getMessage(), "token" => $reply->getAccountToken());
    }


    /**
     * 检查
     */
    public function check($mobile) {
        if(empty($mobile)) {
            return false;
        }
        $request = new \Account\Reg\CheckMobileInfo();
        $request->setMobile($mobile);
        $request->setOrg('WAAPP');
        $request->setToken(GRPC_TOKEN);
        list($reply, $status) = $this->client->check($request)->wait();
        if(empty($reply)) {
            return false;
        }
        $message = $reply->getResponseEntity();
        if (empty($message)) {
            return false;
        }
        return array("code"=>$message->getCode(),"msg"=>$message->getMessage(),"accountToken"=>$reply->getAccountToken(),"thirdToken"=>$reply->getThirdToken(),"exists"=>$reply->getExists());
    }




}
