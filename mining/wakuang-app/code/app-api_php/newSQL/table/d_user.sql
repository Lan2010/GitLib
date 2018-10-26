/*
Navicat MySQL Data Transfer

Source Server         : 数据库
Source Server Version : 50631
Source Host           : 120.25.85.171:3306
Source Database       : 20161205

Target Server Type    : MYSQL
Target Server Version : 50631
File Encoding         : 65001

Date: 2018-06-07 11:16:45
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for d_user
-- ----------------------------
DROP TABLE IF EXISTS `d_user`;
CREATE TABLE `d_user` (
  `userID` int(11) NOT NULL AUTO_INCREMENT COMMENT '客户ID',
  `userKey` varchar(50) DEFAULT NULL COMMENT '用户编码，或者KEY值',
  `userName` varchar(50) NOT NULL COMMENT '用户名',
  `userNickname` varchar(50) DEFAULT NULL COMMENT '客户昵称',
  `password` varchar(50) NOT NULL COMMENT '密码',
  `payPassword` varchar(50) DEFAULT NULL COMMENT '客户交易密码',
  `userStatus` tinyint(1) DEFAULT '1' COMMENT '客户状态 1 正常 0 注销',
  `realName` varchar(200) NOT NULL DEFAULT '' COMMENT '客户姓名',
  `realStatus` tinyint(1) DEFAULT '0' COMMENT '实名认证状态 0 未认证，1已认证',
  `bankStatus` tinyint(1) DEFAULT '0' COMMENT '绑卡标示  0 未绑卡,1绑卡',
  `userSex` tinyint(4) DEFAULT '0' COMMENT '性别 0 未知 1 男 2 女',
  `headUrl` varchar(255) DEFAULT '' COMMENT '头像路径',
  `phone` varchar(50) DEFAULT NULL COMMENT '电话',
  `phoneStatus` tinyint(1) DEFAULT '0' COMMENT '电话认证状态 0 未认证，1已认证',  
  `regDatetime` int(11) NOT NULL COMMENT '注册时间',
  `regTerminal` tinyint(4) DEFAULT '1' COMMENT '注册终端 1 PC 2 安卓 3 IOS 4 微信',
  `regIP` varchar(20) DEFAULT NULL COMMENT '注册IP',
  `openID` varchar(50) DEFAULT NULL COMMENT '微信编码ID',
  `editDatetime` int(11) DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`userID`),
  KEY `user_phone` (`phone`)
) ENGINE=InnoDB AUTO_INCREMENT=397720 DEFAULT CHARSET=utf8 COMMENT='用户表';
