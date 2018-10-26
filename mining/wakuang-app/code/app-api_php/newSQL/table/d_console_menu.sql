/*
Navicat MySQL Data Transfer

Source Server         : 数据库
Source Server Version : 50631
Source Host           : 120.25.85.171:3306
Source Database       : bm

Target Server Type    : MYSQL
Target Server Version : 50631
File Encoding         : 65001

Date: 2018-06-09 17:49:33
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for d_console_menu
-- ----------------------------
DROP TABLE IF EXISTS `d_console_menu`;
CREATE TABLE `d_console_menu` (
  `menu_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '菜单ID',
  `menu_name` varchar(50) NOT NULL COMMENT '菜单名称',
  `menu_code` varchar(50) DEFAULT NULL COMMENT '模块代码 Action(模块)名称 Function(方法)名称',
  `menu_url` varchar(100) DEFAULT NULL COMMENT '菜单URl',
  `parent_id` int(11) DEFAULT '1' COMMENT '父菜单ID',
  `parent_name` varchar(50) DEFAULT NULL COMMENT '父级菜单',
  `menu_sort` smallint(6) DEFAULT '0' COMMENT '排序',
  `if_right` tinyint(4) DEFAULT '1' COMMENT '是否菜单',
  `if_display` tinyint(4) DEFAULT '1' COMMENT '是否显示',
  `level_type` tinyint(2) unsigned NOT NULL DEFAULT '1' COMMENT '节点类型：1:表示应用（模块）；2:表示控制器；3：表示方法',
  `remark` varchar(200) DEFAULT NULL COMMENT '备注',
  `add_user_id` int(11) NOT NULL COMMENT '创建人ID',
  `add_user_name` varchar(50) DEFAULT NULL COMMENT '创建人',
  `add_date_time` int(11) NOT NULL COMMENT '创建时间',
  `edit_user_id` int(11) DEFAULT NULL COMMENT '修改人ID',
  `edit_user_name` varchar(50) DEFAULT NULL COMMENT '修改人',
  `edit_date_time` int(11) DEFAULT NULL COMMENT '修改时间',
  `operate_ip` varchar(50) DEFAULT NULL COMMENT '操作IP',
  PRIMARY KEY (`menu_id`)
) ENGINE=MyISAM AUTO_INCREMENT=92 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of d_console_menu
-- ----------------------------
INSERT INTO `d_console_menu` VALUES ('1', '系统管理', null, null, '0', null, '60', '1', '1', '1', null, '0', null, '0', null, null, null, null);
INSERT INTO `d_console_menu` VALUES ('2', '后台用户管理', 'Backend/getUser', '/Backend/Backend/getUser', '1', null, '30', '1', '1', '3', ' ', '0', null, '0', '15', 'mbox', '1476149880', null);
INSERT INTO `d_console_menu` VALUES ('3', '后台角色管理', 'Backend/getRole', '/Backend/Backend/getRole', '1', null, '28', '1', '1', '3', ' ', '0', null, '0', '15', 'mbox', '1476149892', null);
INSERT INTO `d_console_menu` VALUES ('4', '后台菜单管理', 'Backend/getMenu', '/Backend/Backend/getMenu', '1', null, '27', '1', '1', '3', ' ', '0', null, '0', '15', 'mbox', '1476149905', null);
INSERT INTO `d_console_menu` VALUES ('5', 'Redis缓存', 'System/getSysRedis', '/Backend/System/getSysRedis', '1', null, '48', '1', '1', '3', ' ', '0', null, '0', '15', 'mbox', '1476149861', null);
INSERT INTO `d_console_menu` VALUES ('6', '敏感词', 'System/getSen', '/Backend/System/getSen', '1', null, '0', '1', '1', '3', null, '0', null, '0', null, null, null, null);
INSERT INTO `d_console_menu` VALUES ('8', '字典管理', 'System/getDic', '/Backend/System/getDic', '1', '', '47', '1', '1', '3', '', '0', '', '0', '15', 'mbox', '1476149937', '');
INSERT INTO `d_console_menu` VALUES ('7', '系统日志', 'System/getSysLog', '/Backend/System/getSysLog', '1', '', '100', '1', '1', '3', '', '0', '', '0', '15', 'mbox', '1476150611', '');
INSERT INTO `d_console_menu` VALUES ('9', '系统参数', 'System/getParameter', '/Backend/System/getParameter', '1', '', '49', '1', '1', '3', '系统参数 ', '2', '管理员', '1467351968', '15', 'mbox', '1476149851', '');
