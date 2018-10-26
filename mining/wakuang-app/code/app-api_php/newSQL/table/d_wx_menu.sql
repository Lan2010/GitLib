/*
Navicat MySQL Data Transfer

Source Server         : 数据库
Source Server Version : 50631
Source Host           : 120.25.85.171:3306
Source Database       : 20161205

Target Server Type    : MYSQL
Target Server Version : 50631
File Encoding         : 65001

Date: 2018-06-16 10:17:54
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for d_wx_menu
-- ----------------------------
DROP TABLE IF EXISTS `d_wx_menu`;
CREATE TABLE `d_wx_menu` (
  `menuID` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `parentID` smallint(4) unsigned NOT NULL DEFAULT '0' COMMENT '如果是菜单，parentID有效',
  `menuName` varchar(10) NOT NULL DEFAULT '' COMMENT '菜单标题，一级不超过4个汉字，2级不超过7个汉字',
  `menuType` varchar(16) NOT NULL DEFAULT '' COMMENT '根据微信消息推送接口来：even[click，view]',
  `menuAction` varchar(100) NOT NULL DEFAULT '' COMMENT '菜单动作',
  `isAuthorize` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '0：不用授权，1：需要授权',
  `sort` smallint(4) unsigned NOT NULL DEFAULT '0' COMMENT '菜单排序',
  `status` tinyint(1) unsigned NOT NULL DEFAULT '1' COMMENT '状态：1 有效  0：删除',
  `addDatetime` int(11) unsigned NOT NULL COMMENT '添加时间',
  `addUserID` int(11) unsigned NOT NULL COMMENT '添加人id',
  `addUserName` varchar(50) NOT NULL DEFAULT '' COMMENT '创建人',
  `editUserID` int(11) unsigned NOT NULL COMMENT '修改人id',
  `editDatetime` int(11) unsigned NOT NULL COMMENT '修改时间',
  `editUserName` varchar(50) NOT NULL DEFAULT '' COMMENT '修改人',
  `operateIP` varchar(50) NOT NULL DEFAULT '' COMMENT '操作IP地址',
  PRIMARY KEY (`menuID`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8 COMMENT='微信自定义菜单表';

-- ----------------------------
-- Records of d_wx_menu
-- ----------------------------
INSERT INTO `d_wx_menu` VALUES ('1', '0', '一级菜单', 'none', '', '0', '0', '0', '1493192216', '50', 'liuhy', '14', '1493263566', '', '202.105.127.122');
INSERT INTO `d_wx_menu` VALUES ('3', '0', '菜单二', 'view', 'http://dev.qianbox.net/Wechat/Index/index', '0', '3', '1', '1493262593', '14', 'jiangt', '14', '1493274727', '', '127.0.0.1');
INSERT INTO `d_wx_menu` VALUES ('4', '1', '测试一级', 'none', '', '0', '1', '0', '1493264634', '50', 'liuhy', '0', '0', '', '202.105.127.122');
INSERT INTO `d_wx_menu` VALUES ('5', '0', '理财', 'view', 'http://dev.qianbox.net/Wechat/Index/index', '0', '1', '1', '1493264657', '50', 'liuhy', '50', '1493965100', '', '202.105.127.122');
INSERT INTO `d_wx_menu` VALUES ('6', '5', '首页', 'view', 'http://shdev.qianbox.net/Wechat/Index/index', '0', '1', '1', '1493273660', '50', 'liuhy', '15', '1512541721', '', '121.35.189.154');
INSERT INTO `d_wx_menu` VALUES ('7', '5', '我的客服', 'click', 'kefu', '0', '2', '1', '1493273841', '50', 'liuhy', '0', '0', '', '202.105.127.122');
INSERT INTO `d_wx_menu` VALUES ('8', '3', '跳转', 'view', 'http://shdev.qianbox.net/Wechat/Index/index', '1', '3', '1', '1493275750', '50', 'liuhy', '15', '1512541783', '', '121.35.189.154');
INSERT INTO `d_wx_menu` VALUES ('9', '0', '我的菜单我的菜单我的', 'none', 'wode', '0', '3', '0', '1493276409', '50', 'liuhy', '50', '1493277286', '', '202.105.127.122');
INSERT INTO `d_wx_menu` VALUES ('10', '0', '第四个菜单', 'click', 'qhz', '0', '4', '0', '1493276657', '50', 'liuhy', '0', '0', '', '202.105.127.122');
INSERT INTO `d_wx_menu` VALUES ('11', '5', '理财', 'view', 'http://shdev.qianbox.net/Wechat/Project/plist', '1', '3', '1', '1493276898', '50', 'liuhy', '15', '1512541748', '', '121.35.189.154');
INSERT INTO `d_wx_menu` VALUES ('12', '5', '新手活动', 'view', 'http://shdev.qianbox.net/wechat/User/register', '1', '4', '1', '1493276951', '50', 'liuhy', '15', '1512541761', '', '121.35.189.154');
INSERT INTO `d_wx_menu` VALUES ('13', '5', '帮助中心', 'view', 'http://dev.qianbox.net/Wechat/About/help/', '1', '5', '1', '1493276989', '50', 'liuhy', '0', '0', '', '202.105.127.122');
INSERT INTO `d_wx_menu` VALUES ('14', '5', '六个', 'click', 'liu', '0', '6', '0', '1493277241', '50', 'liuhy', '0', '0', '', '202.105.127.122');
INSERT INTO `d_wx_menu` VALUES ('15', '9', '测试删除', 'click', 'h', '0', '1', '0', '1493277762', '50', 'liuhy', '0', '0', '', '202.105.127.122');
INSERT INTO `d_wx_menu` VALUES ('16', '0', '我的菜单', 'click', 'wode', '0', '3', '1', '1493277924', '50', 'liuhy', '0', '0', '', '202.105.127.122');
INSERT INTO `d_wx_menu` VALUES ('17', '16', 'ces', 'none', '', '0', '0', '0', '1493278187', '50', 'liuhy', '0', '0', '', '202.105.127.122');
INSERT INTO `d_wx_menu` VALUES ('18', '3', '登陆', 'view', 'http://shdev.qianbox.net/wechat/user/login', '1', '0', '1', '1508305083', '16', 'liup', '15', '1512541773', '', '121.35.189.154');
INSERT INTO `d_wx_menu` VALUES ('19', '3', '111', 'none', '', '0', '0', '1', '1525682141', '55', 'lizy', '0', '0', '', '113.89.98.211');
