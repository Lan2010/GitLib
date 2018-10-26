/*
Navicat MySQL Data Transfer

Source Server         : 数据库
Source Server Version : 50631
Source Host           : 120.25.85.171:3306
Source Database       : bm

Target Server Type    : MYSQL
Target Server Version : 50631
File Encoding         : 65001

Date: 2018-06-09 17:49:39
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for d_console_role
-- ----------------------------
DROP TABLE IF EXISTS `d_console_role`;
CREATE TABLE `d_console_role` (
  `role_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '角色ID',
  `role_code` varchar(50) DEFAULT NULL COMMENT '角色编码，或者key值，用于扩展字典',
  `role_name` varchar(50) DEFAULT NULL COMMENT '角色名字',
  `role_remark` varchar(200) DEFAULT NULL COMMENT '角色备注',
  `status` tinyint(4) DEFAULT '1' COMMENT '1,可用，0 删除',
  `add_user_id` int(11) NOT NULL COMMENT '创建人ID',
  `add_user_name` varchar(50) DEFAULT NULL COMMENT '创建人',
  `add_date_time` int(11) NOT NULL COMMENT '创建时间',
  `edit_user_id` int(11) DEFAULT NULL COMMENT '修改人ID',
  `edit_user_name` varchar(50) DEFAULT NULL COMMENT '修改人',
  `edit_date_time` int(11) DEFAULT NULL COMMENT '修改时间',
  `operate_ip` varchar(50) DEFAULT NULL COMMENT '操作IP',
  PRIMARY KEY (`role_id`)
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of d_console_role
-- ----------------------------
INSERT INTO `d_console_role` VALUES ('1', null, '管理员', '管理员', '1', '2', '管理员', '1415197462', null, null, null, '127.0.0.1');
