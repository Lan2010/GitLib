/*
Navicat MySQL Data Transfer

Source Server         : 数据库
Source Server Version : 50631
Source Host           : 120.25.85.171:3306
Source Database       : 20161205

Target Server Type    : MYSQL
Target Server Version : 50631
File Encoding         : 65001

Date: 2018-06-11 17:17:30
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for d_app_versions
-- ----------------------------
DROP TABLE IF EXISTS `d_app_versions`;
CREATE TABLE `d_app_versions` (
  `ver_id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `version_name` varchar(50) NOT NULL DEFAULT '' COMMENT '版本名称',
  `version_code` varchar(50) NOT NULL DEFAULT '' COMMENT '版本号',
  `terminal` tinyint(4) unsigned NOT NULL DEFAULT '2' COMMENT '终端类型（ 2 安卓 3 IOS）',
  `check_code` varchar(50) NOT NULL DEFAULT '' COMMENT '校验码（MD5值  IOS不需要此项）',
  `app_size` float(4,2) unsigned NOT NULL DEFAULT '0.00' COMMENT 'APP大小',
  `update_desc` varchar(100) NOT NULL DEFAULT '' COMMENT '更新说明',
  `update_url` varchar(100) NOT NULL DEFAULT '' COMMENT '更新地址（用于安卓）',
  `is_forced` tinyint(4) unsigned NOT NULL DEFAULT '0' COMMENT '是否强制更新（0：不强制更新  1：强制更新）',
  `status` tinyint(4) unsigned NOT NULL DEFAULT '1' COMMENT '状态（0：删除  1：有效  2：过往版本）',
  `add_datetime` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '添加时间',
  `add_user_id` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '添加用户ID',
  `add_user_name` varchar(50) NOT NULL DEFAULT '' COMMENT '添加用户名',
  `edit_datetime` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '编辑时间',
  `edit_user_id` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '编辑用户ID',
  `edit_user_name` varchar(50) NOT NULL DEFAULT '' COMMENT '编辑用户名',
  PRIMARY KEY (`ver_id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of d_app_versions
-- ----------------------------
INSERT INTO `d_app_versions` VALUES ('1', '2.0.0', '1', '3', '', '0.00', 'My Wonderful Days是一款可以写日记的APP，每天只能更新一条，界面清新范。\r\n\r\n我喜欢APP上的那句话：“写下你想写的，然后在心情条上，划出你的喜怒哀乐。”\r\n\r\n它让我感觉文字是有', 'http://www.mbox.com/Backend/', '0', '2', '1487814539', '0', 'aa', '0', '0', '');
INSERT INTO `d_app_versions` VALUES ('2', '2.0.1', '10', '2', 'asd', '0.00', 'My Wonderful Days是一款可以写日记的APP，每天只能更新一条，界面清新范。\r\n\r\n我喜欢APP上的那句话：“写下你想写的，然后在心情条上，划出你的喜怒哀乐。”\r\n\r\n它让我感觉文字是有', 'http://www.mbox.com/Backend/', '0', '2', '1487814589', '0', 'bb', '0', '0', '');
INSERT INTO `d_app_versions` VALUES ('3', '123123', '123', '3', '', '99.99', 'qweqwedddd', '', '0', '2', '1487822256', '15', 'mbox', '1487824733', '15', '');
INSERT INTO `d_app_versions` VALUES ('4', '去问去问', '请问', '3', '', '0.00', '请问阿萨德', '', '0', '2', '1487830433', '15', 'mbox', '0', '0', '');
INSERT INTO `d_app_versions` VALUES ('5', '去问去问', '请问', '3', '', '99.99', '请问', '', '0', '2', '1487830541', '15', 'mbox', '0', '0', '');
INSERT INTO `d_app_versions` VALUES ('6', 'qwe ', '', '2', '', '0.00', '', '', '0', '0', '0', '0', '', '0', '0', '');
INSERT INTO `d_app_versions` VALUES ('7', '去问去问', '请问', '3', '', '99.99', '请问', '', '0', '0', '1487830541', '15', 'mbox', '0', '0', '');
INSERT INTO `d_app_versions` VALUES ('8', '请问请问', '2.0.0', '3', '', '99.99', '123123', '', '0', '1', '1487831334', '15', 'mbox', '1490608158', '54', 'yaocy');
INSERT INTO `d_app_versions` VALUES ('9', '123', '123', '2', '123', '99.99', '123123', '123', '0', '2', '1487831883', '15', 'mbox', '0', '0', '');
INSERT INTO `d_app_versions` VALUES ('10', '123', '44', '2', '23123', '99.99', '123123', '123123', '0', '2', '1487831915', '15', 'mbox', '0', '0', '');
INSERT INTO `d_app_versions` VALUES ('11', 'V2.9.0', '15', '2', 'CC16DFCEF32EE8E56B253B85E1A417F1', '15.00', '1. 验证码分为注册验证码和修改密码验证码\n2. 适配10余款不常见的分辨率\n3. 集成阿里最新版本的alihotfix热修复框架\n4. 根据产品需求进行注册成功页面更改\n5. 根据产品需求进行开通华', 'https://res.qianhezi.cn//download//app//qhz_v2.9.0_release.apk', '0', '1', '1487832136', '15', 'mbox', '1522065729', '15', 'mbox');
