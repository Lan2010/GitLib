/*
Navicat MySQL Data Transfer

Source Server         : 数据库
Source Server Version : 50631
Source Host           : 120.25.85.171:3306
Source Database       : 20161205

Target Server Type    : MYSQL
Target Server Version : 50631
File Encoding         : 65001

Date: 2018-06-09 15:01:16
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for d_home_banner
-- ----------------------------
DROP TABLE IF EXISTS `d_home_banner`;
CREATE TABLE `d_home_banner` (
  `banner_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '论滚图ID',
  `banner_name` varchar(50) DEFAULT NULL COMMENT '路滚图名字',
  `dic_type` int(11) DEFAULT NULL COMMENT '类型 字典值',
  `dic_name` varchar(50) DEFAULT NULL COMMENT '类型名称',
  `banner_url` varchar(100) DEFAULT NULL COMMENT '图片路径',
  `link_url` varchar(200) DEFAULT NULL COMMENT '链接URL',
  `banner_title` varchar(100) DEFAULT NULL COMMENT 'title',
  `in_link` tinyint(4) DEFAULT '1' COMMENT '是否内链 0 非内链 1 内链',
  `banner_sort` tinyint(4) DEFAULT '0' COMMENT '排序',
  `banner_status` tinyint(4) DEFAULT '1' COMMENT ' 轮滚图状态 0 删除 1 启用',
  `banner_starttime` int(11) unsigned DEFAULT '0' COMMENT '0 不限制开始时间  ',
  `banner_endTime` int(11) unsigned DEFAULT '0' COMMENT '0 不限制结束时间  ',
  `remark` varchar(2000) DEFAULT NULL,
  `add_user_id` int(11) NOT NULL COMMENT '创建人ID',
  `add_user_name` varchar(50) DEFAULT NULL COMMENT '创建人',
  `add_datetime` int(11) NOT NULL COMMENT '创建时间',
  `edit_user_id` int(11) DEFAULT NULL COMMENT '修改人ID',
  `edit_user_name` varchar(50) DEFAULT NULL COMMENT '修改人',
  `edit_datetime` int(11) DEFAULT NULL COMMENT '修改时间',
  `operate_ip` varchar(50) DEFAULT NULL COMMENT '操作IP',
  PRIMARY KEY (`bannerID`)
) ENGINE=InnoDB AUTO_INCREMENT=445 DEFAULT CHARSET=utf8 COMMENT='广告图片管理';
