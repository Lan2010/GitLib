<?php

return array(
    'PAGE_SIZE' => '10', // 数据分页页码  
    'USER_AUTH_ON' => true, //是否开启权限验证(必配)
    'USER_AUTH_TYPE' => 2, //验证方式（1、登录验证；2、实时验证）
    'USER_AUTH_KEY' => 'userID', //用户认证识别号(必配)
    'ADMIN_AUTH_KEY' => 'superadmin', //超级管理员识别号(必配)
    'USER_AUTH_GATEWAY' => '/Public/login', //用户认证失败，跳转URL
    "RBAC_ROLE_TABLE" => 'd_console_role', //角色表名称(必配)
    "RBAC_USER_TABLE" => 'd_console_roleuser', //用户角色中间表名称(必配)
    "RBAC_ACCESS_TABLE" => 'd_console_access', //权限表名称(必配)
    "RBAC_NODE_TABLE" => 'd_console_menu', //节点表名称(必配)
    "USER_AUTH_CACH_KEY" => '_BACKEND_USER_AUTH_CACH_KEY_', //用户权限KEY
    "TICKET_MAXPROP" => '1',
    "TICKET_MAXTOTAL" => '1000',
);
