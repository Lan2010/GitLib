<?php

// +----------------------------------------------------------------------
// | ThinkPHP [ WE CAN DO IT JUST THINK ]
// +----------------------------------------------------------------------
// | Copyright (c) 2006-2014 http://thinkphp.cn All rights reserved.
// +----------------------------------------------------------------------
// | Licensed ( http://www.apache.org/licenses/LICENSE-2.0 )
// +----------------------------------------------------------------------
// | Author: liu21st <liu21st@gmail.com>
// +----------------------------------------------------------------------
header("Content-type: text/html; charset=utf-8");
defined('DARREN') or define('DARREN', true);
defined('THINKPHP') or define('THINKPHP', '../ThinkPHP/');  //thinkPHP框架目录
defined('APP_PATH') or define('APP_PATH', '../App/');
defined('RUNTIME_PATH') or define('RUNTIME_PATH', './Runtime/');


defined('UPLOAD') or define('UPLOAD', 'http://192.168.11.17'); //图片服务器
defined('SITE') or define('SITE', 'http://www.bm.com/');  //站点地址
defined('API') or define('API', 'http://dev.qianbox.net/');  //业务回调地址
defined('COOKIE_DOMAIN') or define('COOKIE_DOMAIN', '.mbox2.com');
defined('MOBILE') or define('MOBILE', 'http://www.bm.com');  //APP根目录 ,用于HX返回地址和接口根目录


defined('PUBLIC_DIR') or define('PUBLIC_DIR', '/Public/');   //公共文件路径，包括JS，CSS，图片路径
defined('PLUG') or define('PLUG', PUBLIC_DIR . 'Plug/');   //公共插件
defined('FPATH') or define('FPATH', PUBLIC_DIR . 'frontend/');   //前台系统
defined('BPATH') or define('BPATH', PUBLIC_DIR . 'backend/');   //后台系统路径
defined('WXPATH') or define('WXPATH', PUBLIC_DIR . 'wechat/');   //微信端路径
defined('MPATH') or define("MPATH", PUBLIC_DIR . 'mobile/'); //APP资源路径

defined('GRPC_HOSTNAME') or define('GRPC_HOSTNAME','192.168.11.23:5006');
defined('GRPC_TOKEN') or define('GRPC_TOKEN','111111');

defined('IDGRPC_HOSTNAME') or define('IDGRPC_HOSTNAME','192.168.11.23:5005');  //身份证 短信
defined('IDGRPC_TOKEN') or define('IDGRPC_TOKEN','111111');


defined('NATS_HOSTNAME') or define('NATS_HOSTNAME','192.168.11.18');  //IP地址
defined('NATS_PORT') or define('NATS_PORT','4222');   //端口
defined('NATS_USER') or define('NATS_USER','');  //用户
defined('NATS_PASS') or define('NATS_PASS','');   //密码


//
//微信公共配置
defined('TOKEN') or define("TOKEN", "tqianhezi");
defined('APPID') or define("APPID", "wx502b98267a93353d");
defined('APPSECRET') or define("APPSECRET", "0af62bc4e4f29a8bdcdcb4c19b27ce76");
define("JpushKey", "004c2b1b5366b49af651a3e4");
define("JpushSecret", "61ad3d33adfa4bf88e525460");


define('JS', FPATH . 'js/');  //JS路径
define('CSS', FPATH . 'style/');   //CSS路径
define('IMAGES', FPATH . 'images/');  //图片路径
defined('WX') or define("WX", PUBLIC_DIR . 'wechat/'); //微信资源路径

defined('VNO') or define('VNO', '1.2');  //前台版本号
defined('BNO') or define('BNO', '1.1');  //后台版本号
//defined('WNO') or define('WNO', '1.3');  //微信版本号
//defined('MNO') or define('MNO', '1.4');  //APP资源版本号
defined('WNO') or define('WNO', rand(0, 10000));  //微信版本号
defined('MNO') or define('MNO', rand(0, 10000));  //APP资源版本号
defined('APP_DEBUG') or define('APP_DEBUG', true);      //开启调试模式
require(THINKPHP . '/ThinkPHP.php');   //加载核心文件
