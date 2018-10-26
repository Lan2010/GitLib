/*
Navicat MySQL Data Transfer

Source Server         : 数据库
Source Server Version : 50631
Source Host           : 120.25.85.171:3306
Source Database       : 20161205

Target Server Type    : MYSQL
Target Server Version : 50631
File Encoding         : 65001

Date: 2018-06-07 13:42:38
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for d_user_login_log
-- ----------------------------
DROP TABLE IF EXISTS `d_user_login_log`;
CREATE TABLE `d_user_login_log` (
  `logID` int(11) NOT NULL AUTO_INCREMENT COMMENT '日志ID',
  `userID` int(11) NOT NULL COMMENT '用户ID',
  `phone` varchar(50) DEFAULT NULL COMMENT '手机号',
  `equipment` varchar(200) DEFAULT '' COMMENT '设备信息',
  `loginIP` varchar(20) DEFAULT NULL COMMENT '登录IP',
  `loginDatetime` int(11) DEFAULT NULL COMMENT '登录时间',
  `terminal` tinyint(4) DEFAULT '1' COMMENT '操作终端 1 PC 2 安卓 3 IOS 4 微信',
  PRIMARY KEY (`logID`),
  KEY `log_userID` (`userID`)
) ENGINE=InnoDB AUTO_INCREMENT=619824 DEFAULT CHARSET=utf8 COMMENT='用户登录日志信息';
