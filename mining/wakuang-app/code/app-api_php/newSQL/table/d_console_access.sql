/*
Navicat MySQL Data Transfer

Source Server         : 数据库
Source Server Version : 50631
Source Host           : 120.25.85.171:3306
Source Database       : bm

Target Server Type    : MYSQL
Target Server Version : 50631
File Encoding         : 65001

Date: 2018-06-09 17:49:14
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for d_console_access
-- ----------------------------
DROP TABLE IF EXISTS `d_console_access`;
CREATE TABLE `d_console_access` (
  `access_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `role_id` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '权限ID',
  `menu_id` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '功能模块ID',
  `level` tinyint(1) unsigned NOT NULL DEFAULT '1' COMMENT '节点类型：1:表示应用（模块）；2:表示控制器；3：表示方法',
  PRIMARY KEY (`access_id`)
) ENGINE=MyISAM AUTO_INCREMENT=27 DEFAULT CHARSET=utf8 COMMENT='角色授权信息';

-- ----------------------------
-- Records of d_console_access
-- ----------------------------
INSERT INTO `d_console_access` VALUES ('24', '1', '3', '1');
INSERT INTO `d_console_access` VALUES ('23', '1', '2', '1');
INSERT INTO `d_console_access` VALUES ('22', '1', '8', '1');
INSERT INTO `d_console_access` VALUES ('21', '1', '5', '1');
INSERT INTO `d_console_access` VALUES ('20', '1', '9', '1');
INSERT INTO `d_console_access` VALUES ('19', '1', '7', '1');
INSERT INTO `d_console_access` VALUES ('18', '1', '1', '1');
INSERT INTO `d_console_access` VALUES ('25', '1', '4', '1');
INSERT INTO `d_console_access` VALUES ('26', '1', '6', '1');
