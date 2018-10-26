/*
Navicat MySQL Data Transfer

Source Server         : 数据库
Source Server Version : 50631
Source Host           : 120.25.85.171:3306
Source Database       : 20161205

Target Server Type    : MYSQL
Target Server Version : 50631
File Encoding         : 65001

Date: 2018-06-09 16:03:16
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for d_user_invite
-- ----------------------------
DROP TABLE IF EXISTS `d_user_invite`;
CREATE TABLE `d_user_invite` (
  `invite_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `user_id` int(11) NOT NULL DEFAULT '0' COMMENT '用户(被邀请人)',
  `user_name` varchar(50) DEFAULT NULL COMMENT '被邀请人用户名',
  `phone` varchar(50) DEFAULT NULL COMMENT '被邀请人手机号码',
  `invite_user_id` int(11) NOT NULL DEFAULT '0' COMMENT '邀请人',
  `invite_user_name` varchar(50) DEFAULT NULL COMMENT '邀请人用户名',
  `invite_user_phone` varchar(50) DEFAULT NULL COMMENT '邀请人电话',
  `reward_type` int(11) NOT NULL DEFAULT '0' COMMENT '奖励类型',
  `reward_name` varchar(50) DEFAULT NULL COMMENT '奖励类型名称',
  `reward_stutas` tinyint(4) DEFAULT '0' COMMENT '0 未处理 1 已处理',
  `reward_money` decimal(15,6) DEFAULT '0.000000' COMMENT '邀请奖励',
  `add_datetime` int(11) NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`invite_id`),
  KEY `ind_invite_userID` (`user_id`) USING BTREE,
  KEY `ind_invite_inviteUserID` (`invite_user_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=73879 DEFAULT CHARSET=utf8 COMMENT='用户邀请';
