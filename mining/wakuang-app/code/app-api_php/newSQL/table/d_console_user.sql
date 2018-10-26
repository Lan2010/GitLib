/*
Navicat MySQL Data Transfer

Source Server         : 数据库
Source Server Version : 50631
Source Host           : 120.25.85.171:3306
Source Database       : bm

Target Server Type    : MYSQL
Target Server Version : 50631
File Encoding         : 65001

Date: 2018-06-09 17:50:08
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for d_console_user
-- ----------------------------
DROP TABLE IF EXISTS `d_console_user`;
CREATE TABLE `d_console_user` (
  `user_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `user_name` varchar(50) NOT NULL COMMENT '用户名',
  `cus_name` varchar(50) DEFAULT NULL COMMENT '客服昵称',
  `password` varchar(50) NOT NULL COMMENT '密码',
  `real_name` varchar(200) NOT NULL DEFAULT '' COMMENT '姓名',
  `user_type` int(11) DEFAULT '1' COMMENT '用户类型 1 用户 2 管理员 7 客服主管 8 客服 ',
  `phone` varchar(50) DEFAULT NULL COMMENT '电话',
  `email` varchar(100) DEFAULT NULL COMMENT 'EMAIL',
  `status` tinyint(4) DEFAULT '1' COMMENT '1,可用，0 删除',
  `remark` varchar(100) DEFAULT NULL COMMENT '备注',
  `add_user_id` int(11) NOT NULL COMMENT '创建人ID',
  `add_user_name` varchar(50) DEFAULT NULL COMMENT '创建人',
  `add_date_time` int(11) NOT NULL COMMENT '创建时间',
  `edit_user_id` int(11) DEFAULT NULL COMMENT '修改人ID',
  `edit_user_name` varchar(50) DEFAULT NULL COMMENT '修改人',
  `edit_date_time` int(11) DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`user_id`)
) ENGINE=MyISAM AUTO_INCREMENT=66 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of d_console_user
-- ----------------------------
INSERT INTO `d_console_user` VALUES ('1', 'admin', '', '8dd54ee89a8bba1ca1863897dea45827', 'MB3eSV4c3y4BPgQQoq1LJw==', '1', '13751186805', null, '1', '', '2', '管理员', '1452157444', '1', 'admin', '1527664732');
INSERT INTO `d_console_user` VALUES ('2', 'liup', '', '8dd54ee89a8bba1ca1863897dea45827', 'MB3eSV4c3y4BPgQQoq1LJw==', '1', '13751172222', null, '1', '', '2', '管理员', '1452158134', '1', 'admin', '1527664756');
INSERT INTO `d_console_user` VALUES ('65', 'xupeiqin', '', '8dd54ee89a8bba1ca1863897dea45827', 'xYUpiUAEVn1+9hkg9yt8qg==', '1', '13169601345', null, '1', 'vcha', '2', 'liup', '1528272684', '2', 'liup', '1528273601');
