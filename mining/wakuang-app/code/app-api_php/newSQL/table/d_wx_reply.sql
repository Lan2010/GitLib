/*
Navicat MySQL Data Transfer

Source Server         : 数据库
Source Server Version : 50631
Source Host           : 120.25.85.171:3306
Source Database       : 20161205

Target Server Type    : MYSQL
Target Server Version : 50631
File Encoding         : 65001

Date: 2018-06-16 09:23:34
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for d_wx_reply
-- ----------------------------
DROP TABLE IF EXISTS `d_wx_reply`;
CREATE TABLE `d_wx_reply` (
  `reply_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `reply_type` varchar(50) NOT NULL DEFAULT '' COMMENT '根据微信消息推送接口来：even[click，view]',
  `keyword` varchar(200) NOT NULL DEFAULT '' COMMENT '关键词',
  `content_type` varchar(50) NOT NULL DEFAULT '' COMMENT '内容类型：news，text，image',
  `title` varchar(100) NOT NULL DEFAULT '' COMMENT '回复标题',
  `show_title` tinyint(1) unsigned NOT NULL DEFAULT '1' COMMENT '是否显示回复标题',
  `content` varchar(500) NOT NULL DEFAULT '' COMMENT '文本字符串',
  `img_url` varchar(500) NOT NULL DEFAULT '' COMMENT '图片路径',
  `url` varchar(200) NOT NULL DEFAULT '' COMMENT 'URL地址',
  `is_auth` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT 'URL链接是否需要授权',
  `status` tinyint(1) unsigned NOT NULL DEFAULT '1' COMMENT '状态：1有效 0删除',
  `add_user_id` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '添加人用户ID',
  `add_user_name` varchar(50) NOT NULL DEFAULT '' COMMENT '添加人用户名',
  `add_datetime` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '添加时间',
  `edit_user_id` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '编辑人用户ID',
  `edit_user_name` varchar(50) NOT NULL DEFAULT '' COMMENT '编辑人用户名',
  `edit_datetime` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '修改时间',
  `operate_ip` varchar(50) NOT NULL DEFAULT '' COMMENT '操作IP',
  PRIMARY KEY (`reply_id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8 COMMENT='微信回复';

-- ----------------------------
-- Records of d_wx_reply
-- ----------------------------
INSERT INTO `d_wx_reply` VALUES ('1', 'subscribe', 'subscribe', 'text', 'guanzhu', '1', '关注回复', '', '', '0', '0', '50', 'liuhy', '1493190730', '0', '', '0', '202.105.127.122');
INSERT INTO `d_wx_reply` VALUES ('2', 'autoReply', '测试', 'text', '自动回复', '1', '测试自动回复文本', '', '', '0', '0', '50', 'liuhy', '1493191004', '0', '', '0', '202.105.127.122');
INSERT INTO `d_wx_reply` VALUES ('3', 'default', 'default', 'text', 'AAA', '1', 'AAAA', '', '', '0', '0', '14', 'jiangt', '1493200907', '0', '', '0', '202.105.127.122');
INSERT INTO `d_wx_reply` VALUES ('4', 'subscribe', 'subscribe', 'image', '关注图片', '1', '', '/upload/share/201704/20170426590074abbb45e.png', '', '0', '1', '50', 'liuhy', '1493201524', '14', 'jiangt', '1493276178', '202.105.127.122');
INSERT INTO `d_wx_reply` VALUES ('5', 'autoReply', '钱盒子', 'image', '自动回复图图片', '1', '', '/upload/share/201704/2017042759019f4bdfa46.jpg', 'http://dev.qianbox.net/Wechat/Index/index', '1', '1', '50', 'liuhy', '1493202244', '50', 'liuhy', '1493278553', '202.105.127.122');
INSERT INTO `d_wx_reply` VALUES ('6', 'scan', 'scan', 'text', '邀请二维码回复', '1', '测试', '', '', '0', '0', '50', 'liuhy', '1493271701', '50', 'liuhy', '1493271920', '202.105.127.122');
INSERT INTO `d_wx_reply` VALUES ('7', 'scan', 'scan', 'text', '邀请二维码回复', '1', '测试邀请二维码回复文本测试邀请二维码回复文本测试邀请二维码回复文本', '', '', '0', '0', '50', 'liuhy', '1493271718', '0', '', '0', '202.105.127.122');
INSERT INTO `d_wx_reply` VALUES ('8', 'scan', 'scan', 'image', '测试邀请二维码回复', '1', '', '/upload/share/201704/2017042759018611dcdf1.png', '', '0', '0', '50', 'liuhy', '1493271981', '50', 'liuhy', '1493272084', '202.105.127.122');
INSERT INTO `d_wx_reply` VALUES ('9', 'click', 'aaa', 'text', '测试点击回复', '1', '测试点击回复内容文本', '', '', '0', '1', '50', 'liuhy', '1493272881', '0', '', '0', '202.105.127.122');
INSERT INTO `d_wx_reply` VALUES ('10', 'click', 'kefu', 'text', '客服', '1', '钱盒子客服在线', '', '', '0', '1', '50', 'liuhy', '1493273896', '0', '', '0', '202.105.127.122');
INSERT INTO `d_wx_reply` VALUES ('11', 'click', 'wode', 'news', '测试点击回复图文', '1', '测试点击回复文本', '/upload/share/201704/2017042759019795959dd.jpg', 'http://dev.qianbox.net/Wechat/Index/index', '0', '1', '50', 'liuhy', '1493276472', '50', 'liuhy', '1493276615', '202.105.127.122');
INSERT INTO `d_wx_reply` VALUES ('12', 'default', 'default', 'text', '未识别回复', '1', '测试未识别回复', '', '', '0', '0', '50', 'liuhy', '1493278341', '50', 'liuhy', '1493278397', '202.105.127.122');
INSERT INTO `d_wx_reply` VALUES ('13', 'default', 'default', 'news', '未识别回复', '1', '测试未识别图文', '/upload/share/201704/2017042759019ed9153d4.jpg', 'http://dev.qianbox.net/Wechat/Index/index', '1', '1', '50', 'liuhy', '1493278346', '50', 'liuhy', '1493278491', '202.105.127.122');
INSERT INTO `d_wx_reply` VALUES ('14', 'autoReply', '钱盒子', 'news', '钱盒子', '1', '测试图文', '/upload/share/201704/2017042759019fc793747.png', 'http://dev.qianbox.net/Wechat/Index/index', '1', '1', '50', 'liuhy', '1493278595', '50', 'liuhy', '1493278722', '202.105.127.122');
INSERT INTO `d_wx_reply` VALUES ('15', 'subscribe', 'subscribe', 'news', '关注回复2', '1', '测试图文', '/upload/share/201704/201704275901a0c620375.jpg', 'http://dev.qianbox.net/Wechat/About/help/', '0', '1', '50', 'liuhy', '1493278790', '50', 'liuhy', '1493278971', '202.105.127.122');
INSERT INTO `d_wx_reply` VALUES ('16', 'autoReply', '测试', 'text', '测试', '1', '测试', '', '', '0', '1', '50', 'liuhy', '1493278875', '0', '', '0', '202.105.127.122');
INSERT INTO `d_wx_reply` VALUES ('17', 'scan', 'scan', 'news', '来自{phone}的邀请', '1', 'tuwen', '/upload/share/201704/201704275901a17a50ebf.png', 'http://dev.qianbox.net/wechat/User/register/inviteCode/{invitecode}', '0', '1', '50', 'liuhy', '1493279037', '50', 'liuhy', '1507800886', '202.105.127.122');
INSERT INTO `d_wx_reply` VALUES ('18', 'autoReply', '客服', 'text', '在线客服', '0', '&lt;a href=&quot;https://www.sobot.com/chat/h5/index.html?sysNum=7a88575979a943b6a0b296993d3a58a1&amp;source=1&quot;&gt;在线客服测试&lt;/a&gt;', '', '', '0', '1', '14', 'jiangt', '1500530365', '14', 'jiangt', '1500530376', '202.105.127.122');
