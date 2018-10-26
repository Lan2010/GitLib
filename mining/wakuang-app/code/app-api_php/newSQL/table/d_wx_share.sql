/*
Navicat MySQL Data Transfer

Source Server         : 数据库
Source Server Version : 50631
Source Host           : 120.25.85.171:3306
Source Database       : 20161205

Target Server Type    : MYSQL
Target Server Version : 50631
File Encoding         : 65001

Date: 2018-06-16 10:42:21
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for d_wx_share
-- ----------------------------
DROP TABLE IF EXISTS `d_wx_share`;
CREATE TABLE `d_wx_share` (
  `share_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '分享ID',
  `share_name` varchar(50) DEFAULT NULL COMMENT '分享名称',
  `share_code` varchar(50) DEFAULT NULL COMMENT '标示，关键值',
  `share_title` varchar(150) DEFAULT NULL COMMENT '分享标题',
  `share_desc` varchar(200) DEFAULT NULL COMMENT '分享描述',
  `share_img` varchar(150) DEFAULT NULL COMMENT '分享图片',
  `share_status` tinyint(4) DEFAULT '1' COMMENT '0 删除 1 正常',
  `remark` varchar(200) DEFAULT NULL COMMENT '备注',
  `add_user_id` int(11) NOT NULL COMMENT '创建人ID',
  `add_user_name` varchar(50) DEFAULT NULL COMMENT '创建人',
  `add_datetime` int(11) NOT NULL COMMENT '创建时间',
  `edit_user_id` int(11) DEFAULT NULL COMMENT '修改人ID',
  `edit_user_name` varchar(50) DEFAULT NULL COMMENT '修改人',
  `edit_datetime` int(11) DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`share_id`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of d_wx_share
-- ----------------------------
INSERT INTO `d_wx_share` VALUES ('1', '注册标题', 'register', '万能的朋友圈，请告诉我如何才能把这个88元红包发出去？！', '钱盒子理财，注册即收获88元现金券红包哦。', '/Upload/banner/56727f33edde8.png', '0', null, '7', 'mbox', '1450336584', '4388', 'wendafang1999', '1450833298');
INSERT INTO `d_wx_share` VALUES ('2', '邀请好友', 'friend', '测试圈层经济时代。当机会来临，果断跟随进入一个优质圈子才是最大财富。', '测试有钱就存钱盒子。钱盒子投友邀请您，一同加入钱盒子致富大部队。', '/upload/share/201704/2017040558e4beb5097b1.jpg', '1', null, '7', 'mbox', '1450336642', '50', 'liuhy', '1491386072');
INSERT INTO `d_wx_share` VALUES ('3', '每日签到', 'sign', '每日签到拿积分，轻轻松松换大钱。', '钱盒子积分商城，加息券，现金券一秒签到就到手啦，加油。', '/upload/share/201610/201610275811daee2ee19.jpg', '1', null, '7', 'mbox', '1450336673', '15', 'mbox', '1477565198');
INSERT INTO `d_wx_share` VALUES ('4', '邀请好友回复', 'invitefriends', '邀请好友', '邀请好友回复', '/Upload/banner/56776236cea5d.jpg', '0', null, '7', 'mbox', '1450664503', '7', 'mbox', '1450753205');
INSERT INTO `d_wx_share` VALUES ('5', '八大理由', 'reason', '确定一个平台是否靠谱 列出8个理由就够了。', '选择钱盒子八大理由。', '/Upload/banner/567ca8c42def7.png', '1', null, '7', 'mbox', '1450753258', '4388', 'wendafang1999', '1451010245');
INSERT INTO `d_wx_share` VALUES ('6', '加息不算事，钱盒子双“蛋”还发现金！', 'christmas', '加息不算事，钱盒子双“蛋”还发现金！', '参与“点亮2016”圣诞小游戏，最高获得30元现金券。', '/Upload/banner/56790ab432038.png', '1', null, '7', 'mbox', '1450769074', '4388', 'wendafang1999', '1451009091');
INSERT INTO `d_wx_share` VALUES ('7', '消费理财', 'iphone', '免费送iphone，天天都有的福利哪里找？！', '钱盒子智能理财，投资送iphone。最新苹果手机，你想要的我们都有。', '/Upload/banner/56810f6d1c890.jpg', '1', null, '4388', 'wendafang1999', '1451293019', '15', 'mbox', '1477547482');
INSERT INTO `d_wx_share` VALUES ('8', '新人有礼：18888元体验金+350元红包', 'gift', '测试新人有礼：注册18888元体验金+首投350元红包', '测试新人专属福利：注册18888元体验金+首投350元红包', '/upload/share/201703/2017032758d88c299d4f1.png', '1', null, '4388', 'wendafang1999', '1452077093', '50', 'liuhy', '1491387201');
INSERT INTO `d_wx_share` VALUES ('9', '微信注册页面', 'register', '测试快来注册吧', '测试钱盒子金融注册', '/upload/share/201704/2017040558e4bf6058dbb.jpg', '1', null, '4388', 'wendafang1999', '1452159966', '50', 'liuhy', '1491386210');
INSERT INTO `d_wx_share` VALUES ('10', '让红包飞，快来领188元大红包！', 'newyearred', '让红包飞，快来领188元大红包！', '我只想任性的给你发个红包，反正钱盒子买单！！', '/Upload/banner/56a72d4081512.png', '1', null, '14', 'jiangt', '1453787049', '12', 'wenfang', '1453796697');
INSERT INTO `d_wx_share` VALUES ('11', '盛邀亲朋好友来发力——冲击新年大奖池', 'myear', '盛邀亲朋好友来发力——冲击新年大奖池', '钱盒子跨年贺岁，约你来拿新年大奖啦！', '/Upload/banner/56a9f2d87e590.png', '1', null, '15', 'mbox', '1453973186', '12', 'wenfang', '1453978436');
INSERT INTO `d_wx_share` VALUES ('12', '有人@你，你有一个劳动节红包还未领取', 'labor', '有人@你，你有一个劳动节红包还未领取', '赶快来抢！最高翻出100元假期红包！', '/Upload/banner/5721d0a575ade.jpg', '1', null, '14', 'jiangt', '1461827297', '12', 'wenfang', '1461833897');
INSERT INTO `d_wx_share` VALUES ('13', '有人@你，领取钱盒子消暑大礼包', 'juneevent', '有人@你，领取钱盒子消暑大礼包', '消暑计划，钱盒子350元高温补贴等你来拿。', '/Upload/banner/2016/06/5757c6773a15d.jpg', '1', null, '14', 'jiangt', '1465369412', '12', 'wenfang', '1465370233');
INSERT INTO `d_wx_share` VALUES ('14', '钱盒子金融2周年庆', 'integral', '钱盒子金融2周年庆  iphone 7 plus+千元现金大派送......', '钱盒子金融2周年庆，登陆账户即领千元红包雨，iphone 7 plus投资任意拿......', '/upload/share/201610/201610275811dc1fce0c9.jpg', '1', null, '14', 'jiangt', '1477547081', '14', 'jiangt', '1477877033');
INSERT INTO `d_wx_share` VALUES ('15', '钱盒子金融圣诞无礼不欢', 'chrGame', '钱盒子金融圣诞无礼不欢', '钱盒子金融圣诞无礼不欢：赚钱太累、省钱太苦，钱盒子金融给你机会直接拆红包！排名越靠前还有奖品喔！', '/upload/share/201612/20161213584fbed3aaae8.jpg', '1', null, '1', 'weilei', '1481621227', null, null, null);
INSERT INTO `d_wx_share` VALUES ('16', '钱盒子金额大家一起来砸蛋', 'newYear', '钱盒子金额大家一起来砸蛋', '和我一起砸蛋吧', '/upload/share/201701/2017011158760b17c0e47.jpg', '1', null, '15', 'mbox', '1484131113', '15', 'mbox', '1484131143');
INSERT INTO `d_wx_share` VALUES ('17', '钱盒子注册', 'register', '钱盒子注册大家一起来', '钱盒子注册大家一起来', '/upload/share/201703/2017032458d4da46b91bf.png', '0', null, '54', 'yaocy', '1490344528', null, null, null);
INSERT INTO `d_wx_share` VALUES ('18', 'aprilActivityqwrrrrrrrrrrrrrr', 'aprilActivity', 'aprilActivityqwrrrrrrrrrrrrrrrrrrrrrrrrrr', 'aprilActivityqweqwwwwwwwwwwwwwwwwwwwwwwwwww', '/upload/share/201704/2017041358ef45a35a294.png', '1', null, '54', 'yaocy', '1492075943', '54', 'yaocy', '1492076186');
INSERT INTO `d_wx_share` VALUES ('19', 'mayAct', 'mayAct', 'mayAct', 'mayAct', '/upload/share/201705/20170508590fd0bc338b3.png', '1', null, '54', 'yaocy', '1494208703', '63', 'lxx', '1527902798');
INSERT INTO `d_wx_share` VALUES ('20', '九月活动', 'sepAct', '九月活动', '九月活动九月活动九月活动九月活动九月活动九月活动九月活动九月活动九月活动九月活动九月活动九月活动九月活动九月活动九月活动', '/upload/share/201708/2017082959a53b67346e9.png', '1', null, '54', 'yaocy', '1504000876', null, null, null);
INSERT INTO `d_wx_share` VALUES ('21', '18年二月活动', 'febAct', '二月活动', '啦啦啦，分享没毛病，啦啦啦', '/upload/share/201801/201801295a6ec7cb1b089.jpg', '1', null, '64', 'zfy8001', '1517204471', '63', 'lxx', '1527902844');
INSERT INTO `d_wx_share` VALUES ('22', '六月活动', 'dragonAct', '六月活动', '六月活动', '/upload/share/201806/201806045b14889a54120.png', '1', null, '63', 'lxx', '1527911478', '63', 'lxx', '1528072347');
