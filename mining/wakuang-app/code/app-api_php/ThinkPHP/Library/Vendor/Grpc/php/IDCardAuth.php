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
@include_once dirname(__FILE__) . '/Idcard/AuthResult.php';
@include_once dirname(__FILE__) . '/Idcard/IDCardAuthSerivceClient.php';
@include_once dirname(__FILE__) . '/Idcard/IDCardInfo.php';
@include_once dirname(__FILE__) . '/GPBMetadata/IDCardAuthService.php';
@include_once dirname(__FILE__) . '/GPBMetadata/ResponseEntity.php';
@include_once dirname(__FILE__) . '/ResponseEntity.php';

class IDCardAuth
{
    private $client = null;

    public function __construct()
    {
        $this->client = new Idcard\IDCardAuthSerivceClient(IDGRPC_HOSTNAME, [
            'credentials' => Grpc\ChannelCredentials::createInsecure(),
        ]);
    }

    /**
     * 发送
     * @param $phone
     * @param $userID
     * @return array|bool
     */
    function authIDCard($name,$codeID)
    {
        if (empty($name) || empty($codeID) ) {
            return false;
        }
        $request = new Idcard\IDCardInfo();
        $request->setName($name);
        $request->setToken(IDGRPC_TOKEN);
        $request->setIdcard($codeID);
        list($reply, $status) = $this->client->authIDCard($request)->wait();
        if (empty($reply)) {
            return false;
        }
        return array("code"=>$reply->getCode(),"message"=>$reply->getMessage(),"photo"=>$reply->getPhoto(),"authPlatform"=>$reply->getAuthPlatform());
    }




}
