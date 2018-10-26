/*
Navicat MySQL Data Transfer

Source Server         : 数据库
Source Server Version : 50631
Source Host           : 120.25.85.171:3306
Source Database       : 20161205

Target Server Type    : MYSQL
Target Server Version : 50631
File Encoding         : 65001

Date: 2018-06-07 15:33:49
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for d_user_action_log
-- ----------------------------
DROP TABLE IF EXISTS `d_user_action_log`;
CREATE TABLE `d_user_action_log` (
  `logID` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `userID` int(11) DEFAULT NULL COMMENT '用户ID',
  `phone` varchar(60) NOT NULL DEFAULT '' COMMENT '用户名/手机号码',
  `logIP` varchar(20) DEFAULT '' COMMENT 'IP',
  `action` varchar(100) DEFAULT NULL COMMENT '用户操作',
  `ret` varchar(50) DEFAULT NULL COMMENT '结果(如；登陆成功|登录失败)',
  `terminal` tinyint(4) DEFAULT '0' COMMENT '操作终端 1 PC 2 安卓 3 IOS 4 微信'',',
  `addDatetime` int(11) unsigned DEFAULT '0' COMMENT '添加时间',
  PRIMARY KEY (`logID`)
) ENGINE=InnoDB AUTO_INCREMENT=51810 DEFAULT CHARSET=utf8;
