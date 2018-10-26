/*
Navicat MySQL Data Transfer

Source Server         : 数据库
Source Server Version : 50631
Source Host           : 120.25.85.171:3306
Source Database       : 20161205

Target Server Type    : MYSQL
Target Server Version : 50631
File Encoding         : 65001

Date: 2018-06-12 14:05:47
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for d_app_feedback
-- ----------------------------
DROP TABLE IF EXISTS `d_app_feedback`;
CREATE TABLE `d_app_feedback` (
  `feback_id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '反馈ID',
  `user_id` int(11) unsigned NOT NULL COMMENT '用户ID',
  `set_phone` varchar(50) NOT NULL COMMENT '用户填写的电话',
  `feback_type` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '0 : 默认  ',
  `feback_info` varchar(255) NOT NULL COMMENT '用户反馈的信息',
  `terminal` int(10) unsigned NOT NULL DEFAULT '1' COMMENT '操作终端 1 PC 2 安卓 3 IOS 4 微信',
  `versions` varchar(50) NOT NULL DEFAULT '' COMMENT '版本号',
  `equipment_id` varchar(80) NOT NULL DEFAULT '' COMMENT '设备ID',
  `remark` varchar(100) NOT NULL DEFAULT '' COMMENT '处理信息',
  `status` int(5) unsigned NOT NULL DEFAULT '0' COMMENT '0:未处理   1:已处理',
  `add_datetime` int(11) NOT NULL DEFAULT '0' COMMENT '添加时间',
  `edit_datetime` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '处理时间',
  `edit_user_name` varchar(50) NOT NULL DEFAULT '' COMMENT '处理人',
  `edit_user_id` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '处理人ID',
  PRIMARY KEY (`feback_id`)
) ENGINE=InnoDB AUTO_INCREMENT=96 DEFAULT CHARSET=utf8 COMMENT='手机意见反馈';
