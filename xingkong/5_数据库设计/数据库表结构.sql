/*
Navicat MySQL Data Transfer

Source Server         : 天智星开发（11.11）
Source Server Version : 50717
Source Host           : 192.168.11.11:3306
Source Database       : xingkong

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2018-09-04 17:37:33
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `t_dest`
-- ----------------------------
DROP TABLE IF EXISTS `t_dest`;
CREATE TABLE `t_dest` (
  `dest_id` int(11) NOT NULL AUTO_INCREMENT,
  `dest_name` varchar(40) DEFAULT NULL,
  `dest_logo` varchar(200) DEFAULT NULL,
  `dest_desc` varchar(200) DEFAULT NULL,
  `dest_img_url` varchar(200) DEFAULT NULL,
  `thumb_image_url` varchar(200) DEFAULT NULL,
  `category` tinyint(4) DEFAULT NULL,
  `status` tinyint(4) DEFAULT NULL COMMENT '状态，0无效，1有效',
  `dest_music` varchar(200) DEFAULT NULL,
  `dest_btimg_url` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`dest_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_dest
-- ----------------------------
INSERT INTO `t_dest` VALUES ('1', '九寨沟', null, null, '/static/resource/postcard/spot-1.png', '/static/resource/postcard/spot-1-s.png', null, '1', '/static/resource/postcard/diefeihuawu.m4a', '/static/resource/postcard/spot-1-t.png');
INSERT INTO `t_dest` VALUES ('2', '长城', null, null, '/static/resource/postcard/spot-2.png', '/static/resource/postcard/spot-2-s.png', null, '1', '/static/resource/postcard/xianjing.mp3', '/static/resource/postcard/spot-2-t.png');
INSERT INTO `t_dest` VALUES ('3', '张家界', null, null, '/static/resource/postcard/spot-3.png', '/static/resource/postcard/spot-3-s.png', null, '1', '/static/resource/postcard/xianjing.mp3', '/static/resource/postcard/spot-3-t.png');
INSERT INTO `t_dest` VALUES ('4', '天坛', null, null, '/static/resource/postcard/spot-4.png', '/static/resource/postcard/spot-4-s.png', null, '1', '/static/resource/postcard/xianjing.mp3', '/static/resource/postcard/spot-4-t.png');
INSERT INTO `t_dest` VALUES ('5', '颐和园', null, null, '/static/resource/postcard/spot-5.png', '/static/resource/postcard/spot-5-s.png', null, '1', '/static/resource/postcard/xianjing.mp3', '/static/resource/postcard/spot-5-t.png');
INSERT INTO `t_dest` VALUES ('6', '西湖', null, null, '/static/resource/postcard/spot-6.png', '/static/resource/postcard/spot-6-s.png', null, '1', '/static/resource/postcard/diefeihuawu.m4a', '/static/resource/postcard/spot-6-t.png');
INSERT INTO `t_dest` VALUES ('7', '凤凰古城', null, null, '/static/resource/postcard/spot-7.png', '/static/resource/postcard/spot-7-s.png', null, '1', '/static/resource/postcard/diefeihuawu.m4a', '/static/resource/postcard/spot-7-t.png');
INSERT INTO `t_dest` VALUES ('8', '白羊座', null, null, '/static/resource/postcard2/spot-1.png', '/static/resource/postcard2/spot-1-s.png', null, '1', null, '/static/resource/postcard2/spot-1-t.png');

-- ----------------------------
-- Table structure for `t_greencard`
-- ----------------------------
DROP TABLE IF EXISTS `t_greencard`;
CREATE TABLE `t_greencard` (
  `gc_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `gc_number` varchar(40) DEFAULT NULL,
  `sign_date` date DEFAULT NULL,
  `gc_name` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `gc_head_img` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`gc_id`),
  KEY `FK_Reference_3` (`user_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=109 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_greencard
-- ----------------------------
INSERT INTO `t_greencard` VALUES ('93', '91', 'TZX000001000', '2018-07-17', 'Gang ?', 'https://wx.qlogo.cn/mmopen/vi_32/Q3auHgzwzM61oJUYATvJvbBBuTqxA9Lo0AbEduRPReREopvzat1s7addcuicMQhu8035ic73lF2YjCTk71IfRjtA/132');
INSERT INTO `t_greencard` VALUES ('94', '92', 'TZX000001001', '2018-07-17', '夕马', 'https://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTJ3XlqrRYiaZoficl4Vsr7xyzH14Ublxdib4kcwlIMImVCPqyjibdia64MLNw5I5RJNmfav8qdzVZDZ8jA/132');
INSERT INTO `t_greencard` VALUES ('95', '93', 'TZX000001002', '2018-07-17', '杨双汉', 'https://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTLYOwCV8l3cMhgJdHKtGrH4JnkUlQZoH1fSLEP5u7LDd6FhibeKOAeGouVnBx0QR7rquB0SQZialSgw/132');
INSERT INTO `t_greencard` VALUES ('96', '94', 'TZX000001003', '2018-07-17', '易国辉', 'https://wx.qlogo.cn/mmopen/vi_32/ZP93PCMoceo4wEHCKUK92LacF07p4D2mzIojd26pIN3j39iczlFkGqNWQNVneGzvZlm1ia1vg7wicslW9IySNk9MQ/132');
INSERT INTO `t_greencard` VALUES ('97', '95', 'TZX000001004', '2018-07-17', '糖糖糖开心糖', 'https://wx.qlogo.cn/mmopen/vi_32/cZpmNicibSqJRmbVdj6gspr9iaKW7cV6RicBEb5U5RqWicmjmia4qfmm5bx3vT1IYPphF7ic4DExuQrDO2pu50teVpV6w/132');
INSERT INTO `t_greencard` VALUES ('98', '96', 'TZX000001005', '2018-07-17', '慵懒的被子', 'https://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTLtCmI67j2d9icCR2tqjH8MwN0uFE5uRaGZ4R70wibJbgXtv3oX1E60OLbVHX22IUle841maj2s2oKA/132');
INSERT INTO `t_greencard` VALUES ('99', '97', 'TZX000001006', '2018-07-17', 'mwy', 'https://wx.qlogo.cn/mmopen/vi_32/oGSSbOMCwL34hYEdrcn7cN1AOqXTFBlDPOxHAGbdmdU6WwKuDbyA5hIsB7aJVXeaGT7YzkqicxnYiazwu0spD61w/132');
INSERT INTO `t_greencard` VALUES ('100', '98', 'TZX000001007', '2018-07-18', '精品女装', '');
INSERT INTO `t_greencard` VALUES ('101', '99', 'TZX000001008', '2018-07-18', '1加1', '');
INSERT INTO `t_greencard` VALUES ('102', '100', 'TZX000001009', '2018-07-20', '李昕骞 Xinqian', 'https://wx.qlogo.cn/mmopen/vi_32/ajNVdqHZLLD535IzhyZGyOBPEY6scoSUa6ZcicWmiaWEYnpVmhXgSPzCHqVekZJmKkqMOC6yDic6m32dnlsbMnx5g/132');
INSERT INTO `t_greencard` VALUES ('103', '101', 'TZX000001010', '2018-07-23', 'Gang ?', 'https://wx.qlogo.cn/mmopen/vi_32/Q3auHgzwzM61oJUYATvJvbBBuTqxA9Lo0AbEduRPReREopvzat1s7addcuicMQhu8035ic73lF2YjCTk71IfRjtA/132');
INSERT INTO `t_greencard` VALUES ('104', '102', 'TZX000001011', '2018-07-23', '李昕骞 Xinqian', 'https://wx.qlogo.cn/mmopen/vi_32/ajNVdqHZLLD535IzhyZGyOBPEY6scoSUa6ZcicWmiaWEYnpVmhXgSPzCHqVekZJmKkqMOC6yDic6m32dnlsbMnx5g/132');
INSERT INTO `t_greencard` VALUES ('105', '104', 'TZX000001012', '2018-07-23', '杨双汉', 'https://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTLYOwCV8l3cMhgJdHKtGrH4JnkUlQZoH1fSLEP5u7LDd6FhibeKOAeGouVnBx0QR7rquB0SQZialSgw/132');
INSERT INTO `t_greencard` VALUES ('106', '105', 'TZX000001013', '2018-07-23', '权奥乔', 'https://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTIym7CJnvZW4bmvYGmico8xz4tUpFaY38xYdZYlic2C3ojuhptR5drKSibNibkSeIK8ovdwC0MibqdQz1A/132');
INSERT INTO `t_greencard` VALUES ('107', '103', 'TZX000001014', '2018-07-23', '邱彪', 'https://wx.qlogo.cn/mmopen/vi_32/HrKAxicdXxhWTFDKU9rW0wz6JcrhddEwSCWybvGMEnRAU4TNlM9jkV2g7iaCXEkWp4Pu7FqI9R3zgmIYgkdMpy6A/132');
INSERT INTO `t_greencard` VALUES ('108', '107', 'TZX000001015', '2018-07-27', '正好', 'https://wx.qlogo.cn/mmopen/vi_32/iaQQECZYDzPMnRmFFQuXJZfcBw7sYWsE3F0CDicufBicZNibcrQbgM8OtbQm4wQicCZFQ7fg5MR0wiaiayt9a0ibEChmEA/132');

-- ----------------------------
-- Table structure for `t_login_log`
-- ----------------------------
DROP TABLE IF EXISTS `t_login_log`;
CREATE TABLE `t_login_log` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `openid` varchar(30) DEFAULT NULL,
  `login_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='记录用户登录的日志信息';

-- ----------------------------
-- Records of t_login_log
-- ----------------------------

-- ----------------------------
-- Table structure for `t_postcard`
-- ----------------------------
DROP TABLE IF EXISTS `t_postcard`;
CREATE TABLE `t_postcard` (
  `pc_id` int(11) NOT NULL AUTO_INCREMENT,
  `pc_head_img` varchar(200) DEFAULT NULL,
  `pc_title` varchar(200) DEFAULT NULL,
  `pc_text` varchar(200) CHARACTER SET utf8mb4 DEFAULT NULL,
  `pc_date` date DEFAULT NULL,
  `dest_id` int(11) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`pc_id`),
  KEY `FK_Reference_5` (`user_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=238 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_postcard
-- ----------------------------
INSERT INTO `t_postcard` VALUES ('200', '/user/avatar/qXNEvHoc_1531821850127.jpg', null, '', '2018-07-17', '3', '91');
INSERT INTO `t_postcard` VALUES ('202', '/user/avatar/SwPF9Pwe_1531822942658.jpg', null, '', '2018-07-17', '4', '94');
INSERT INTO `t_postcard` VALUES ('203', '/user/avatar/99cT2H1i_1531822944134.jpg', null, '', '2018-07-17', '3', '95');
INSERT INTO `t_postcard` VALUES ('204', '/user/avatar/qXNEvHoc_1531821850127.jpg', null, '', '2018-07-17', '5', '91');
INSERT INTO `t_postcard` VALUES ('205', '/user/avatar/qXNEvHoc_1531821850127.jpg', null, '', '2018-07-17', '5', '91');
INSERT INTO `t_postcard` VALUES ('206', '/user/avatar/Vhb6KXmk_1531825206842.jpg', null, '祝福语', '2018-07-17', '3', '96');
INSERT INTO `t_postcard` VALUES ('207', '/user/avatar/Vhb6KXmk_1531825206842.jpg', null, '时间', '2018-07-17', '3', '96');
INSERT INTO `t_postcard` VALUES ('209', '/user/avatar/HD0Ef283_1531825584661.jpg', null, '祈福c', '2018-07-17', '3', '97');
INSERT INTO `t_postcard` VALUES ('210', '/user/avatar/Vhb6KXmk_1531825206842.jpg', null, '', '2018-07-17', '3', '96');
INSERT INTO `t_postcard` VALUES ('211', '/user/avatar/HD0Ef283_1531825584661.jpg', 'nxnxnxnxxnxncnn', '张家无界', '2018-07-17', '5', '97');
INSERT INTO `t_postcard` VALUES ('212', '/user/avatar/yc2qh1GR_1531822648559.jpg', null, '', '2018-07-17', '9', '93');
INSERT INTO `t_postcard` VALUES ('213', null, '哦呜噜噜了t', 'w题咯途径', '2018-07-18', '4', '98');
INSERT INTO `t_postcard` VALUES ('214', '/user/avatar/qXNEvHoc_1531821850127.jpg', null, '', '2018-07-19', '6', '91');
INSERT INTO `t_postcard` VALUES ('215', '/user/avatar/yc2qh1GR_1531822648559.jpg', null, '', '2018-07-22', '3', '93');
INSERT INTO `t_postcard` VALUES ('216', '/user/avatar/950P34Vs_1531822397091.jpg', null, '', '2018-07-23', '4', '92');
INSERT INTO `t_postcard` VALUES ('217', '/user/avatar/950P34Vs_1531822397091.jpg', null, '', '2018-07-23', '5', '92');
INSERT INTO `t_postcard` VALUES ('218', '/user/avatar/GG8Oy3Fv_1532325847688.jpg', null, '', '2018-07-23', '6', '101');
INSERT INTO `t_postcard` VALUES ('219', '/user/avatar/Vhb6KXmk_1531825206842.jpg', null, '好的好的', '2018-07-23', '3', '96');
INSERT INTO `t_postcard` VALUES ('220', '/user/avatar/1igHk2LL_1532326475984.jpg', '祝你生日快乐', '祝天智星生日快乐?！', '2018-07-23', '3', '102');
INSERT INTO `t_postcard` VALUES ('221', '/user/avatar/7luripXy_1532328744218.jpg', null, '祝福远方的你', '2018-07-23', '8', '105');
INSERT INTO `t_postcard` VALUES ('222', '/user/avatar/7luripXy_1532328744218.jpg', null, '', '2018-07-25', '4', '105');
INSERT INTO `t_postcard` VALUES ('223', '/user/avatar/GG8Oy3Fv_1532325847688.jpg', null, '', '2018-07-25', '3', '101');
INSERT INTO `t_postcard` VALUES ('224', '/user/avatar/GG8Oy3Fv_1532325847688.jpg', null, '', '2018-07-25', '3', '101');
INSERT INTO `t_postcard` VALUES ('225', '/user/avatar/GG8Oy3Fv_1532325847688.jpg', null, '', '2018-07-25', '5', '101');
INSERT INTO `t_postcard` VALUES ('226', '/user/avatar/GG8Oy3Fv_1532325847688.jpg', null, '', '2018-07-25', '3', '101');
INSERT INTO `t_postcard` VALUES ('227', '/user/avatar/GG8Oy3Fv_1532325847688.jpg', null, '', '2018-07-25', '3', '101');
INSERT INTO `t_postcard` VALUES ('228', '/user/avatar/GG8Oy3Fv_1532325847688.jpg', null, '', '2018-07-25', '3', '101');
INSERT INTO `t_postcard` VALUES ('229', '/user/avatar/GG8Oy3Fv_1532325847688.jpg', null, '', '2018-07-25', '3', '101');
INSERT INTO `t_postcard` VALUES ('230', '/user/avatar/7luripXy_1532328744218.jpg', null, '', '2018-07-26', '3', '105');
INSERT INTO `t_postcard` VALUES ('231', '/user/avatar/7luripXy_1532328744218.jpg', null, '', '2018-07-26', '3', '105');
INSERT INTO `t_postcard` VALUES ('232', '/user/avatar/7luripXy_1532328744218.jpg', null, '', '2018-07-26', '5', '105');
INSERT INTO `t_postcard` VALUES ('233', '/user/avatar/7luripXy_1532328744218.jpg', null, '', '2018-07-26', '3', '105');
INSERT INTO `t_postcard` VALUES ('234', '/user/avatar/GG8Oy3Fv_1532325847688.jpg', null, '', '2018-07-27', '3', '101');
INSERT INTO `t_postcard` VALUES ('235', '/user/avatar/GG8Oy3Fv_1532325847688.jpg', null, '', '2018-07-27', '3', '101');
INSERT INTO `t_postcard` VALUES ('236', '/user/avatar/GG8Oy3Fv_1532325847688.jpg', null, '', '2018-07-27', '3', '101');
INSERT INTO `t_postcard` VALUES ('237', '/user/avatar/GG8Oy3Fv_1532325847688.jpg', null, '', '2018-07-27', '3', '101');

-- ----------------------------
-- Table structure for `t_prize`
-- ----------------------------
DROP TABLE IF EXISTS `t_prize`;
CREATE TABLE `t_prize` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `user_id` int(11) DEFAULT NULL COMMENT '用户ID',
  `prize_time` timestamp NULL DEFAULT NULL,
  `desc` varchar(100) CHARACTER SET utf8 DEFAULT NULL COMMENT '中奖内容',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of t_prize
-- ----------------------------
INSERT INTO `t_prize` VALUES ('1', '91', '2018-06-29 00:00:00', '去酒泉看卫星发射');
INSERT INTO `t_prize` VALUES ('2', '92', '2018-06-29 00:00:00', '去酒泉看卫星发射');
INSERT INTO `t_prize` VALUES ('3', '93', '2018-06-29 00:00:00', '去酒泉看卫星发射');
INSERT INTO `t_prize` VALUES ('4', '94', '2018-06-22 00:00:00', '去酒泉看卫星发射');
INSERT INTO `t_prize` VALUES ('5', '95', '2018-06-22 00:00:00', '去酒泉看卫星发射');
INSERT INTO `t_prize` VALUES ('6', '96', '2018-06-22 00:00:00', '现场操控卫星拍摄');
INSERT INTO `t_prize` VALUES ('7', '97', '2018-06-15 00:00:00', '去酒泉看卫星发射');
INSERT INTO `t_prize` VALUES ('8', '98', '2018-06-15 00:00:00', '现场操控卫星拍摄');
INSERT INTO `t_prize` VALUES ('9', '99', '2018-06-15 00:00:00', '去酒泉看卫星发射');
INSERT INTO `t_prize` VALUES ('10', '100', '2018-06-08 00:00:00', '现场操控卫星拍摄');
INSERT INTO `t_prize` VALUES ('11', '101', '2018-06-08 00:00:00', '现场操控卫星拍摄');
INSERT INTO `t_prize` VALUES ('12', '102', '2018-06-08 00:00:00', '去酒泉看卫星发射');
INSERT INTO `t_prize` VALUES ('13', '103', '2018-06-01 00:00:00', '现场操控卫星拍摄');
INSERT INTO `t_prize` VALUES ('14', '104', '2018-06-01 00:00:00', '去酒泉看卫星发射');
INSERT INTO `t_prize` VALUES ('15', '105', '2018-06-01 00:00:00', '去酒泉看卫星发射');
INSERT INTO `t_prize` VALUES ('16', '106', '2018-06-01 00:00:00', '现场操控卫星拍摄');

-- ----------------------------
-- Table structure for `t_sub`
-- ----------------------------
DROP TABLE IF EXISTS `t_sub`;
CREATE TABLE `t_sub` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `sub_success` tinyint(4) DEFAULT NULL,
  `sub_time` timestamp NULL DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_Reference_4` (`user_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=167 DEFAULT CHARSET=utf8 COMMENT='用户预约的记录（预约单）都保存在这里';

-- ----------------------------
-- Records of t_sub
-- ----------------------------
INSERT INTO `t_sub` VALUES ('149', '1', '2018-07-17 18:25:59', '91');
INSERT INTO `t_sub` VALUES ('150', '0', '2018-07-17 19:00:24', '96');
INSERT INTO `t_sub` VALUES ('151', '1', '2018-07-17 19:01:14', '92');
INSERT INTO `t_sub` VALUES ('152', '0', '2018-07-17 19:07:52', '97');
INSERT INTO `t_sub` VALUES ('153', '0', '2018-07-18 08:53:49', '96');
INSERT INTO `t_sub` VALUES ('154', '0', '2018-07-18 10:24:13', '98');
INSERT INTO `t_sub` VALUES ('155', '0', '2018-07-18 10:47:36', '99');
INSERT INTO `t_sub` VALUES ('156', '0', '2018-07-18 11:44:18', '91');
INSERT INTO `t_sub` VALUES ('157', '0', '2018-07-19 23:26:35', '91');
INSERT INTO `t_sub` VALUES ('158', '0', '2018-07-20 10:41:57', '100');
INSERT INTO `t_sub` VALUES ('159', '1', '2018-07-20 15:52:54', '91');
INSERT INTO `t_sub` VALUES ('160', '1', '2018-07-22 15:25:43', '91');
INSERT INTO `t_sub` VALUES ('161', '1', '2018-07-23 14:03:46', '92');
INSERT INTO `t_sub` VALUES ('162', '1', '2018-07-23 14:31:39', '102');
INSERT INTO `t_sub` VALUES ('163', '0', '2018-07-23 14:55:32', '105');
INSERT INTO `t_sub` VALUES ('164', '1', '2018-07-24 17:55:52', '101');
INSERT INTO `t_sub` VALUES ('165', '1', '2018-07-25 10:02:01', '101');
INSERT INTO `t_sub` VALUES ('166', '1', '2018-07-26 11:06:17', '105');

-- ----------------------------
-- Table structure for `t_user`
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `nick_name` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `cellphone` varchar(40) DEFAULT NULL,
  `openid` varchar(40) DEFAULT NULL,
  `avatar_url` varchar(200) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `is_auth` tinyint(4) DEFAULT '0',
  `is_get_gc` tinyint(4) DEFAULT '0',
  `avatar_local` varchar(200) DEFAULT NULL COMMENT '头像url为本地服务器',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=108 DEFAULT CHARSET=utf8 COMMENT='用户表';

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES ('92', '夕马', null, 'ooUA340vgCUGtMbnlRa7T1F20L_E', 'https://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTJ3XlqrRYiaZoficl4Vsr7xyzH14Ublxdib4kcwlIMImVCPqyjibdia64MLNw5I5RJNmfav8qdzVZDZ8jA/132', '1', '1', '/user/avatar/950P34Vs_1531822397091.jpg');
INSERT INTO `t_user` VALUES ('94', '易国辉', null, 'ooUA346bbZTmQJ1yU7ta1qiJyNfY', 'https://wx.qlogo.cn/mmopen/vi_32/ZP93PCMoceo4wEHCKUK92LacF07p4D2mzIojd26pIN3j39iczlFkGqNWQNVneGzvZlm1ia1vg7wicslW9IySNk9MQ/132', '1', '1', '/user/avatar/SwPF9Pwe_1531822942658.jpg');
INSERT INTO `t_user` VALUES ('95', '糖糖糖开心糖', null, 'ooUA347K2sROo0U-O86XLBzI-XJ4', 'https://wx.qlogo.cn/mmopen/vi_32/cZpmNicibSqJRmbVdj6gspr9iaKW7cV6RicBEb5U5RqWicmjmia4qfmm5bx3vT1IYPphF7ic4DExuQrDO2pu50teVpV6w/132', '1', '1', '/user/avatar/99cT2H1i_1531822944134.jpg');
INSERT INTO `t_user` VALUES ('96', '慵懒的被子', null, 'ooUA348kALxIylBGYXb_FgP2McDc', 'https://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTLtCmI67j2d9icCR2tqjH8MwN0uFE5uRaGZ4R70wibJbgXtv3oX1E60OLbVHX22IUle841maj2s2oKA/132', '1', '1', '/user/avatar/Vhb6KXmk_1531825206842.jpg');
INSERT INTO `t_user` VALUES ('97', 'mwy', null, 'ooUA344jiJ9FsLkp0svwx8ouNQrw', 'https://wx.qlogo.cn/mmopen/vi_32/oGSSbOMCwL34hYEdrcn7cN1AOqXTFBlDPOxHAGbdmdU6WwKuDbyA5hIsB7aJVXeaGT7YzkqicxnYiazwu0spD61w/132', '1', '1', '/user/avatar/HD0Ef283_1531825584661.jpg');
INSERT INTO `t_user` VALUES ('98', '精品女装', null, 'ooUA341F5JyGBrxzbtufa-G-UKHk', null, '1', '1', null);
INSERT INTO `t_user` VALUES ('99', '1加1', null, 'ooUA34yMUZ23MvmMJ2iyj8K67ud8', null, '1', '1', null);
INSERT INTO `t_user` VALUES ('101', 'Gang ?', null, 'ooUA347FURlks_Z7zkYSPDjglB6c', 'https://wx.qlogo.cn/mmopen/vi_32/Q3auHgzwzM61oJUYATvJvbBBuTqxA9Lo0AbEduRPReREopvzat1s7addcuicMQhu8035ic73lF2YjCTk71IfRjtA/132', '1', '1', '/user/avatar/GG8Oy3Fv_1532325847688.jpg');
INSERT INTO `t_user` VALUES ('102', '李昕骞 Xinqian', null, 'ooUA3434gG85KMcPiX-CxIDmg3Dk', 'https://wx.qlogo.cn/mmopen/vi_32/ajNVdqHZLLD535IzhyZGyOBPEY6scoSUa6ZcicWmiaWEYnpVmhXgSPzCHqVekZJmKkqMOC6yDic6m32dnlsbMnx5g/132', '1', '1', '/user/avatar/1igHk2LL_1532326475984.jpg');
INSERT INTO `t_user` VALUES ('103', '邱彪', null, 'ooUA34yxyJ7q5K5bOaFLVe3dW4gc', 'https://wx.qlogo.cn/mmopen/vi_32/HrKAxicdXxhWTFDKU9rW0wz6JcrhddEwSCWybvGMEnRAU4TNlM9jkV2g7iaCXEkWp4Pu7FqI9R3zgmIYgkdMpy6A/132', '1', '1', '/user/avatar/m4ZHMxpe_1532332534890.jpg');
INSERT INTO `t_user` VALUES ('104', '杨双汉', null, 'ooUA348byajFGgERVBxUMobfUytk', 'https://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTLYOwCV8l3cMhgJdHKtGrH4JnkUlQZoH1fSLEP5u7LDd6FhibeKOAeGouVnBx0QR7rquB0SQZialSgw/132', '1', '1', '/user/avatar/Y5eEo3Ec_1532328441066.jpg');
INSERT INTO `t_user` VALUES ('105', '权奥乔', null, 'ooUA348J75x8KJNZAfbNc5GIjh7w', 'https://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTIym7CJnvZW4bmvYGmico8xz4tUpFaY38xYdZYlic2C3ojuhptR5drKSibNibkSeIK8ovdwC0MibqdQz1A/132', '1', '1', '/user/avatar/7luripXy_1532328744218.jpg');
INSERT INTO `t_user` VALUES ('106', null, null, 'ooUA349xKYBZqj4AkQenF3jdtbxE', null, '0', '0', null);
INSERT INTO `t_user` VALUES ('107', '正好', null, 'ooUA341RA57yIRwPdQcSydLzE5Yo', 'https://wx.qlogo.cn/mmopen/vi_32/iaQQECZYDzPMnRmFFQuXJZfcBw7sYWsE3F0CDicufBicZNibcrQbgM8OtbQm4wQicCZFQ7fg5MR0wiaiayt9a0ibEChmEA/132', '1', '1', '/user/avatar/iOvhSuxZ_1532661117328.jpg');

-- ----------------------------
-- Table structure for `t_wish`
-- ----------------------------
DROP TABLE IF EXISTS `t_wish`;
CREATE TABLE `t_wish` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `audio_time_len` int(11) DEFAULT NULL,
  `audio_url` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_Reference_2` (`user_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=56 DEFAULT CHARSET=utf8 COMMENT='用户的许愿信息';

-- ----------------------------
-- Records of t_wish
-- ----------------------------
INSERT INTO `t_wish` VALUES ('50', '92', '2018-07-17 19:01:24', '6467', 'https://testtzx.100msh.com/wish/audio/zA4TcxDE_1531825284373.m4a');
INSERT INTO `t_wish` VALUES ('51', '91', '2018-07-17 19:01:56', '9138', 'https://testtzx.100msh.com/wish/audio/sIXHIcYG_1531825316890.m4a');
INSERT INTO `t_wish` VALUES ('52', '91', '2018-07-20 15:54:03', '60203', 'https://testtzx.100msh.com/wish/audio/yCbHYjUW_1532073243614.m4a');
INSERT INTO `t_wish` VALUES ('53', '92', '2018-07-23 14:03:52', '3460', 'https://testtzx.100msh.com/wish/audio/YVMYYHvu_1532325832118.m4a');
INSERT INTO `t_wish` VALUES ('54', '102', '2018-07-23 14:32:03', '6335', 'https://testtzx.100msh.com/wish/audio/ZiRS50pE_1532327523489.m4a');
INSERT INTO `t_wish` VALUES ('55', '105', '2018-07-26 11:16:58', '681', 'https://testtzx.100msh.com/wish/audio/MpwxxdmI_1532575018055.m4a');
