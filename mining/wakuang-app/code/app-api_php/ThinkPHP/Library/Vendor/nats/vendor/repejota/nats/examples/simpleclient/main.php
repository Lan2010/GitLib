<?php
require_once __DIR__.'/../../../../../vendor/autoload.php';

use Nats\Connection as NatsClient;

$nc = new NatsClient();
$nc->connect();
$str = '{
    "devNum": "ABCEDFE",//设备编号
    "bindTime": 1530691812832,//绑定的时间（时间戳）
    "account": "13111111111", //绑定的账户名称
}';
$nc->publish("comm.device.bind-account",$str);





