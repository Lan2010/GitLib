/*
Navicat MySQL Data Transfer

Source Server         : 数据库
Source Server Version : 50631
Source Host           : 120.25.85.171:3306
Source Database       : bm

Target Server Type    : MYSQL
Target Server Version : 50631
File Encoding         : 65001

Date: 2018-06-09 17:50:01
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for d_console_roleuser
-- ----------------------------
DROP TABLE IF EXISTS `d_console_roleuser`;
CREATE TABLE `d_console_roleuser` (
  `role_user_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '角色人员ID',
  `user_id` int(11) DEFAULT NULL COMMENT '用户ID',
  `role_id` int(11) DEFAULT NULL COMMENT '角色ID',
  PRIMARY KEY (`role_user_id`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of d_console_roleuser
-- ----------------------------
INSERT INTO `d_console_roleuser` VALUES ('1', '1', '1');
INSERT INTO `d_console_roleuser` VALUES ('2', '2', '1');
