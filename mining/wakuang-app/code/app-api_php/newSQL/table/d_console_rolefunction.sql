/*
Navicat MySQL Data Transfer

Source Server         : 数据库
Source Server Version : 50631
Source Host           : 120.25.85.171:3306
Source Database       : bm

Target Server Type    : MYSQL
Target Server Version : 50631
File Encoding         : 65001

Date: 2018-06-09 17:49:46
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for d_console_rolefunction
-- ----------------------------
DROP TABLE IF EXISTS `d_console_rolefunction`;
CREATE TABLE `d_console_rolefunction` (
  `rolefunction_id` int(11) DEFAULT NULL,
  `role_id` int(11) DEFAULT NULL,
  `function_id` int(11) DEFAULT NULL COMMENT '功能ID'
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='角色功能表';

-- ----------------------------
-- Records of d_console_rolefunction
-- ----------------------------
