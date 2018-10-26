/*
Navicat MySQL Data Transfer

Source Server         : 数据库
Source Server Version : 50631
Source Host           : 120.25.85.171:3306
Source Database       : bm

Target Server Type    : MYSQL
Target Server Version : 50631
File Encoding         : 65001

Date: 2018-06-09 17:49:23
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for d_console_button
-- ----------------------------
DROP TABLE IF EXISTS `d_console_button`;
CREATE TABLE `d_console_button` (
  `button_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '按钮ID',
  `button_code` varchar(50) DEFAULT NULL COMMENT '按钮编码',
  `button_value` varchar(50) DEFAULT NULL COMMENT '按钮值，用2的次方',
  `button_name` varchar(50) DEFAULT NULL COMMENT '按钮名字',
  PRIMARY KEY (`button_id`)
) ENGINE=MyISAM AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 COMMENT='按钮信息表';

-- ----------------------------
-- Records of d_console_button
-- ----------------------------
INSERT INTO `d_console_button` VALUES ('1', 'Select', '2', '查询');
INSERT INTO `d_console_button` VALUES ('2', 'Add', '4', '新增');
INSERT INTO `d_console_button` VALUES ('3', 'Edit', '8', '修改');
INSERT INTO `d_console_button` VALUES ('4', 'Delete', '16', '删除');
INSERT INTO `d_console_button` VALUES ('5', 'Audit', '32', '审核');
INSERT INTO `d_console_button` VALUES ('6', 'Export', '64', '导出');
INSERT INTO `d_console_button` VALUES ('7', 'Cancel', '128', '注销');
INSERT INTO `d_console_button` VALUES ('8', 'Enable', '256', '启用');
INSERT INTO `d_console_button` VALUES ('9', 'View', '512', '查看');
