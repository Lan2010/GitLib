/*
Navicat MySQL Data Transfer

Source Server         : 星链app(11.13)
Source Server Version : 50626
Source Host           : 192.168.11.13:3306
Source Database       : starchain

Target Server Type    : MYSQL
Target Server Version : 50626
File Encoding         : 65001

Date: 2018-09-04 17:41:16
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `d_advertisement`
-- ----------------------------
DROP TABLE IF EXISTS `d_advertisement`;
CREATE TABLE `d_advertisement` (
  `ad_id` int(11) NOT NULL AUTO_INCREMENT,
  `advert_name` varchar(225) DEFAULT '' COMMENT '广告名字',
  `area` varchar(255) DEFAULT '' COMMENT '广告宣传语',
  `city` varchar(255) DEFAULT '' COMMENT '广告图标',
  `city_code` varchar(255) DEFAULT '' COMMENT '广告图片',
  `advertisement_type` varchar(30) DEFAULT '0' COMMENT '广告类型 ALert  弹出 view消失',
  `total_count` varchar(255) DEFAULT '' COMMENT '点击总数(用于加减）',
  `advert_pic` varchar(2000) DEFAULT '' COMMENT '广告图片',
  `advert_icon` varchar(125) DEFAULT '',
  `total_starpoint` int(14) DEFAULT '0' COMMENT '总的浏览星星数',
  `advert_remark` varchar(255) DEFAULT '' COMMENT '广告的简介',
  `once_starpoint` varchar(14) DEFAULT '' COMMENT '看一次的浏览星星数量',
  `begin_time` varchar(255) DEFAULT '' COMMENT '备注',
  `order_no` varchar(125) DEFAULT '' COMMENT '单号',
  `end_time` varchar(125) DEFAULT '',
  `ad_url` varchar(255) DEFAULT '' COMMENT '广告链接',
  `advertisement_attribute` varchar(100) DEFAULT '' COMMENT '类型',
  `advertisement_describe` varchar(255) DEFAULT '' COMMENT '描述',
  `total_click_starpoint` decimal(14,4) NOT NULL DEFAULT '0.0000' COMMENT '总的点击星星数',
  `once_click_starpoint` decimal(14,4) NOT NULL DEFAULT '0.0000' COMMENT '点击一次的星星数量',
  `total_count_view` int(14) NOT NULL DEFAULT '0' COMMENT '总点击数用于展示和记录',
  `user_browse_starpoint` decimal(14,4) NOT NULL DEFAULT '0.0000' COMMENT '用户浏览的获取到的总星数',
  `user_click_starpoint` decimal(14,4) NOT NULL DEFAULT '0.0000' COMMENT '用户点击的获取到的星数',
  PRIMARY KEY (`ad_id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8 COMMENT='广告表';

-- ----------------------------
-- Records of d_advertisement
-- ----------------------------
INSERT INTO `d_advertisement` VALUES ('1', '用基因黑科技帮您搞定减肥', '', '深圳市', '340', 'VIEW', '999', 'http://192.168.11.23/oms/image/20180720/48b47ec8-8403-4e13-ba74-81ceda09582c.png', 'http://192.168.11.23/oms/image/20180720/b3cf53ae-b1fe-47b4-8f1a-02b1c6271edb.png', '888', '0元基因减肥,正常饮食,减脂增肌', '0.8880', '1532082120', '1', '1538322900', '', 'ADVERTISER', '用基因黑科技帮您搞定减肥,0元基因减肥，正常饮食，减脂增肌', '0.0000', '0.0000', '1000', '80.8080', '0.0000');
INSERT INTO `d_advertisement` VALUES ('2', '理财就上钱盒子', '', '深圳市', '340', 'VIEW', '1012', 'http://192.168.11.23/oms/image/20180720/4327a80b-b53c-49d4-86f3-73abfe4f27d3.png', 'http://192.168.11.23/oms/image/20180720/dd6d4d76-c50e-43ff-9634-5897b83c33f3.png', '666', '高收益、更安全', '0.6660', '1532082360', '2', '1533121320', 'http://a.app.qq.com/o/simple.jsp?pkgname=com.qianhezi.qhz', 'ADVERTISER', '钱盒子', '888.0000', '0.8880', '1000', '53.5220', '0.0000');
INSERT INTO `d_advertisement` VALUES ('3', '新鲜原创音乐短视频分享社区', '', '深圳市', '340', 'VIEW', '100', 'http://192.168.11.23/oms/image/20180720/2e25fcb6-604a-4407-b7d2-1b968b2b8d5a.png', 'http://192.168.11.23/oms/image/20180720/cf7a69a1-33e9-4d5f-855c-e5e14e6390dd.png', '168', '年轻人都爱玩的APP', '1.6800', '1532082720', '3', '1538322900', 'https://www.douyin.com/', 'ADVERTISER', '年轻人都爱玩的APP', '188.0000', '1.8800', '100', '168.0400', '0.0000');
INSERT INTO `d_advertisement` VALUES ('4', '百度一下，你就知道', '', '深圳市', '340', 'VIEW', '100', 'http://192.168.11.23/oms/image/20180720/8233abea-6969-4fb9-b07e-753e729fd691.png', 'http://192.168.11.23/oms/image/20180720/46df9bea-d09a-4f25-86b2-1297999a91a8.png', '68', '全球最大的中文搜索引擎', '0.6800', '1532082960', '4', '1538322900', 'https://www.baidu.com/', 'ADVERTISER', '百度', '198.0000', '1.9800', '100', '92.5400', '0.0000');
INSERT INTO `d_advertisement` VALUES ('5', '奥运，没你不行', '', '深圳市', '340', 'VIEW', '997', 'http://192.168.11.23/oms/image/20180720/8fa79e1d-d526-41c6-94e9-d3cf57f922e6.png', 'http://192.168.11.23/oms/image/20180720/56364eac-f747-45a4-905a-7c9aeb30f8bd.png', '998', '霸气归来，一举治好各种不服', '0.9980', '1532083080', '5', '1538322900', '', 'ADVERTISER', '霸气归来，一举治好各种不服', '0.0000', '0.0000', '1000', '95.8080', '0.0000');
INSERT INTO `d_advertisement` VALUES ('6', '红色鼓励，由你传递', '', '深圳市', '340', 'VIEW', '98', 'http://192.168.11.23/oms/image/20180720/f8214284-3080-4d21-861e-045dab224189.png', 'http://192.168.11.23/oms/image/20180720/a9f20d82-0e30-42d7-802e-9f78c1274725.png', '58', '李宇春在肯德基等你', '0.5800', '1532083260', '6', '1538322900', '', 'ADVERTISER', '李宇春在肯德基等你', '0.0000', '0.0000', '100', '40.6000', '0.0000');
INSERT INTO `d_advertisement` VALUES ('7', '一网打尽', '', '深圳市', '340', 'VIEW', '1000', 'http://192.168.11.23/oms/image/20180720/2dbaee20-38b0-4f6d-b839-787cafdd2e60.png', 'http://192.168.11.23/oms/image/20180720/610c6f10-5e93-4fe6-8e14-e7f1daba206e.png', '518', '优设专题2018，活力全开', '0.5180', '1532083380', '7', '1532684340', '', 'ADVERTISER', '优设专题2018，活力全开', '0.0000', '0.0000', '1000', '0.0000', '0.0000');
INSERT INTO `d_advertisement` VALUES ('8', '苏宁金融', '', '深圳市', '340', 'VIEW', '100', 'http://192.168.11.23/oms/image/20180720/05a63e0b-4183-4614-9292-e3e0048aa682.png', 'http://192.168.11.23/oms/image/20180720/9a767e33-e6e0-4b55-b7ea-0a4553a9d550.png', '418', '418提前发', '4.1800', '1532083440', '8', '1534002900', 'https://www.suning.com', 'ADVERTISER', '418提前发', '518.0000', '5.1800', '100', '167.0200', '0.0000');
INSERT INTO `d_advertisement` VALUES ('9', '京东金融送10元白条券', '', '深圳市', '340', 'VIEW', '100', 'http://192.168.11.23/oms/image/20180720/3a52108d-2dfb-42e1-99df-8eb343a3c807.png', 'http://192.168.11.23/oms/image/20180720/566ba6cc-c6e5-4d40-b590-15fba702676c.png', '618', '白条加油站，京东金融每月一聚', '6.1800', '1532083500', '9', '1534002900', 'https://www.jingdong.com', 'ADVERTISER', '白条加油站，京东金融每月一聚', '718.0000', '7.1800', '100', '0.0000', '0.0000');
INSERT INTO `d_advertisement` VALUES ('10', '麦当劳', '', '深圳市', '340', 'VIEW', '2', 'http://192.168.11.23/oms/image/20180727/6ed9ed42-5116-48b6-b91d-18cbcd7d839b.png', 'http://192.168.11.23/oms/image/20180727/79db2854-4635-4fd9-8921-c0f69761170b.png', '10', '横扫饥饿', '5.0000', '1532680740', '10', '1532706900', 'https://www.mcdonalds.com.cn/', 'DEFAULT', 'https://www.mcdonalds.com.cn/', '20.0000', '10.0000', '2', '15.0000', '0.0000');
INSERT INTO `d_advertisement` VALUES ('11', 'test1', '', '深圳市', '340', 'VIEW', '2', 'http://192.168.11.23/oms/image/20180727/67d29542-ce6c-41f3-bfc1-0c1e2b4cf991.png', 'http://192.168.11.23/oms/image/20180727/a15a901e-629a-4726-a6be-5f5c926ab6b0.png', '100', '广告语测试', '50.0000', '1532683380', '11', '1532706900', 'http://192.168.11.23:11080/index', 'DEFAULT', '测试', '88.0000', '44.0000', '2', '100.0000', '0.0000');
INSERT INTO `d_advertisement` VALUES ('12', 'test2', '', '深圳市', '340', 'VIEW', '2', 'http://192.168.11.23/oms/image/20180727/57dc9227-1c6f-4265-b581-c6edb21b415f.png', 'http://192.168.11.23/oms/image/20180727/d20d973a-f770-4485-a9ef-e6977876f550.png', '300', '欢迎作客星巴克', '150.0000', '1532683500', '12', '1532793300', 'https://map.baidu.com/', 'ADVERTISER', '的地方', '500.0000', '250.0000', '2', '450.0000', '0.0000');
INSERT INTO `d_advertisement` VALUES ('13', '浏览广告', '', '深圳市', '340', 'VIEW', '3', 'http://192.168.11.23/oms/image/20180727/be3fa54e-bec5-4508-aec4-3c0c3fdfccb6.png', 'http://192.168.11.23/oms/image/20180727/e8def4d7-2167-48bb-bf70-8c98795a155c.png', '12', '浏览', '4.0000', '1532686800', '13', '1532793300', '', 'ADVERTISER', '还好', '0.0000', '0.0000', '3', '12.0000', '0.0000');

-- ----------------------------
-- Table structure for `d_app_feedback`
-- ----------------------------
DROP TABLE IF EXISTS `d_app_feedback`;
CREATE TABLE `d_app_feedback` (
  `feback_id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '反馈ID',
  `user_id` int(11) unsigned NOT NULL COMMENT '用户ID',
  `phone` varchar(50) NOT NULL COMMENT '用户填写的电话',
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
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8 COMMENT='手机意见反馈';

-- ----------------------------
-- Records of d_app_feedback
-- ----------------------------
INSERT INTO `d_app_feedback` VALUES ('1', '37', '17520487755', '0', 'Fajsdfljads;Lukas;l', '3', '1.0.0', '1befc0e4-dedc-48da-9098-f1352c4012a2', '', '0', '1532428102', '0', '', '0');
INSERT INTO `d_app_feedback` VALUES ('2', '70', '15685858447', '0', '↘⬆', '2', '1.0.1', '59d7f803-d05f-300d-839b-e2b5b070ee84', '', '0', '1532504100', '0', '', '0');
INSERT INTO `d_app_feedback` VALUES ('3', '37', '17520487755', '0', 'heehhr', '3', '1.0.0', '481a0d18-3dcf-4c5c-b799-f32c33b72e0e', '', '0', '1532694230', '0', '', '0');
INSERT INTO `d_app_feedback` VALUES ('10', '84', '13111111111', '0', '你好啊', '3', '2.0.0', 'abcdefg', '', '0', '1535444364', '0', '', '0');
INSERT INTO `d_app_feedback` VALUES ('11', '84', '13111111111', '0', '你好啊', '3', '2.0.0', 'abcdefg', '', '0', '1535444364', '0', '', '0');
INSERT INTO `d_app_feedback` VALUES ('12', '84', '13111111111', '0', '你好啊', '3', '2.0.0', 'abcdefg', '', '0', '1535444467', '0', '', '0');
INSERT INTO `d_app_feedback` VALUES ('13', '111', '15768118003', '0', '你好啊', '3', '2.0.0', 'abcdefg', '', '0', '1535953952', '0', '', '0');
INSERT INTO `d_app_feedback` VALUES ('14', '111', '15768118003', '0', '你好啊', '3', '2.0.0', 'abcdefg', '', '0', '1535953977', '0', '', '0');
INSERT INTO `d_app_feedback` VALUES ('15', '111', '15768118003', '0', '你好啊', '3', '2.0.0', 'abcdefg', '', '0', '1535954016', '0', '', '0');
INSERT INTO `d_app_feedback` VALUES ('16', '111', '15768118003', '0', '你好', '3', '1.0.0', '80AFC481-891C-4B61-8D37-2EFB2432EFB0', '', '0', '1535959073', '0', '', '0');
INSERT INTO `d_app_feedback` VALUES ('17', '111', '15768118003', '0', '你好', '3', '1.0.0', '80AFC481-891C-4B61-8D37-2EFB2432EFB0', '', '0', '1535959078', '0', '', '0');
INSERT INTO `d_app_feedback` VALUES ('18', '111', '15768118003', '0', '你好', '3', '1.0.0', '80AFC481-891C-4B61-8D37-2EFB2432EFB0', '', '0', '1535959081', '0', '', '0');

-- ----------------------------
-- Table structure for `d_app_info`
-- ----------------------------
DROP TABLE IF EXISTS `d_app_info`;
CREATE TABLE `d_app_info` (
  `info_id` int(11) NOT NULL AUTO_INCREMENT,
  `equipment_id` varchar(80) NOT NULL COMMENT '设备ID',
  `type` tinyint(4) DEFAULT '0' COMMENT '类型 1 安装 2 活跃',
  `jph_reg_id` varchar(50) DEFAULT NULL COMMENT '极光推送ID',
  `versions` varchar(50) NOT NULL COMMENT '客户端APP的版本号',
  `phone_info` varchar(100) DEFAULT NULL COMMENT '手机信息 手机的系统 机型信息',
  `latitude` varchar(10) DEFAULT NULL COMMENT '纬度',
  `longitude` varchar(10) DEFAULT NULL COMMENT '经度',
  `terminal` tinyint(4) NOT NULL DEFAULT '0' COMMENT '终端  2 安卓 3 IOS',
  `province` varchar(50) DEFAULT NULL COMMENT '省份',
  `city` varchar(50) DEFAULT NULL COMMENT '城市',
  `area` varchar(100) DEFAULT NULL COMMENT '区',
  `place` varchar(150) DEFAULT NULL COMMENT '地址详细',
  `chan_key` varchar(50) NOT NULL DEFAULT '' COMMENT 'APP来源渠道KEY',
  `channel` varchar(50) NOT NULL DEFAULT '' COMMENT 'APP来源渠道',
  `add_datetime` int(11) NOT NULL COMMENT '发生时间',
  PRIMARY KEY (`info_id`),
  KEY `app_addDatetime` (`add_datetime`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=93712 DEFAULT CHARSET=utf8 COMMENT='手机信息数据表';

-- ----------------------------
-- Records of d_app_info
-- ----------------------------
INSERT INTO `d_app_info` VALUES ('93709', 'nexs-888', '1', '888', '2.0.0', '华为P20Pro', '666', '666', '3', '855', '6625', '855', '北极星', '', '苹果商店', '1528524620');
INSERT INTO `d_app_info` VALUES ('93710', 'nexs-888', '1', '888', '2.0.0', '华为P20Pro', '666', '666', '3', '855', '6625', '855', '北极星', '', '苹果商店', '1528524635');
INSERT INTO `d_app_info` VALUES ('93711', 'abcdefg', null, null, '2.0.0', null, null, null, '3', null, null, null, null, 'appShop', '苹果商店', '1535452465');

-- ----------------------------
-- Table structure for `d_app_versions`
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
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8 COMMENT='APP版本信息表';

-- ----------------------------
-- Records of d_app_versions
-- ----------------------------
INSERT INTO `d_app_versions` VALUES ('1', '2.0.0', '1', '3', '', '0.00', 'My Wonderful Days是一款可以写日记的APP，每天只能更新一条，界面清新范。\r\n\r\n我喜欢APP上的那句话：“写下你想写的，然后在心情条上，划出你的喜怒哀乐。”\r\n\r\n它让我感觉文字是有', 'http://www.mbox.com/Backend/', '0', '0', '1487814539', '0', 'aa', '0', '0', '');
INSERT INTO `d_app_versions` VALUES ('2', '2.0.1', '2', '2', 'CC16DFCEF32EE8E56B253B85E1A417F1', '15.00', '1. 验证码分为注册验证码和修改密码验证码\n2. 适配10余款不常见的分辨率\n3. 集成阿里最新版本的alihotfix热修复框架\n4. 根据产品需求进行注册成功页面更改\n5. 根据产品需求进行开通华', 'https://res.qianhezi.cn//download//app//qhz_v2.9.0_release.apk', '0', '0', '1487814589', '0', 'bb', '1529121082', '2', 'liup');
INSERT INTO `d_app_versions` VALUES ('3', '123123', '123', '3', '', '99.99', 'qweqwedddd', '', '0', '0', '1487822256', '15', 'mbox', '1487824733', '15', '');
INSERT INTO `d_app_versions` VALUES ('4', '去问去问', '请问', '3', '', '0.00', '请问阿萨德', '', '0', '0', '1487830433', '15', 'mbox', '0', '0', '');
INSERT INTO `d_app_versions` VALUES ('5', '去问去问', '请问', '3', '', '99.99', '请问', '', '0', '0', '1487830541', '15', 'mbox', '0', '0', '');
INSERT INTO `d_app_versions` VALUES ('6', 'qwe ', '', '2', '', '0.00', '', '', '0', '0', '0', '0', '', '0', '0', '');
INSERT INTO `d_app_versions` VALUES ('7', '去问去问', '请问', '3', '', '99.99', '请问', '', '0', '0', '1487830541', '15', 'mbox', '0', '0', '');
INSERT INTO `d_app_versions` VALUES ('8', '请问请问', '2.0.0', '3', '', '99.99', '123123', '', '0', '0', '1487831334', '15', 'mbox', '1490608158', '54', 'yaocy');
INSERT INTO `d_app_versions` VALUES ('9', '123', '123', '2', '123', '99.99', '123123', '123', '0', '0', '1487831883', '15', 'mbox', '0', '0', '');
INSERT INTO `d_app_versions` VALUES ('10', '123', '44', '2', '23123', '99.99', '123123', '123123', '0', '0', '1487831915', '15', 'mbox', '0', '0', '');
INSERT INTO `d_app_versions` VALUES ('11', 'V2.9.0', '15', '2', 'CC16DFCEF32EE8E56B253B85E1A417F1', '15.00', '1. 验证码分为注册验证码和修改密码验证码\n2. 适配10余款不常见的分辨率\n3. 集成阿里最新版本的alihotfix热修复框架\n4. 根据产品需求进行注册成功页面更改\n5. 根据产品需求进行开通华', 'https://res.qianhezi.cn//download//app//qhz_v2.9.0_release.apk', '0', '0', '1487832136', '15', 'mbox', '1522065729', '15', 'mbox');
INSERT INTO `d_app_versions` VALUES ('12', 'sfd', 'adf', '2', '', '0.00', 'adf', 'af', '0', '0', '0', '0', '', '0', '0', '');
INSERT INTO `d_app_versions` VALUES ('13', 'afd', 'afe', '2', 'afe', '0.00', 'afe', 'af', '0', '0', '1528710586', '2', 'liup', '0', '0', '');
INSERT INTO `d_app_versions` VALUES ('14', '你好', 'e', '2', 'a', '0.00', 'afe', 'a', '0', '0', '1528960155', '2', 'liup', '0', '0', '');
INSERT INTO `d_app_versions` VALUES ('15', '1.0.2', '2', '2', 'CC16DFCEF32EE8E56B253B85E1A417F1', '15.00', '1. 验证码分为注册验证码和修改密码验证码\n2. 适配10余款不常见的分辨率\n3. 集成阿里最新版本的alihotfix热修复框架\n4. 根据产品需求进行注册成功页面更改\n5. 根据产品需求进行开通华', 'https://res.qianhezi.cn//download//app//qhz_v2.9.0_release.apk', '0', '2', '1529121716', '2', 'liup', '1530264398', '2', 'liup');
INSERT INTO `d_app_versions` VALUES ('16', '强制更新', '1', '2', 'CC16DFCEF32EE8E56B253B85E1A417F1', '20.00', '安卓测试', 'https://res.qianhezi.cn//download//app//qhz_v2.9.0_release.apk', '0', '1', '1530264385', '2', 'liup', '1530759913', '2', 'liup');

-- ----------------------------
-- Table structure for `d_charge`
-- ----------------------------
DROP TABLE IF EXISTS `d_charge`;
CREATE TABLE `d_charge` (
  `charge_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '充电宝地址池ID',
  `device_id` varchar(50) DEFAULT '' COMMENT '设备ID',
  `device_type` varchar(50) DEFAULT '' COMMENT '设备类型',
  `device_model` varchar(50) DEFAULT '' COMMENT '设备型号',
  `device_mac` varchar(50) NOT NULL DEFAULT '' COMMENT '充电设备MAC',
  `state` tinyint(4) DEFAULT '1' COMMENT '充电宝状态 1默认未绑定 2 已经绑定  0 删除',
  `bind_user_id` int(11) DEFAULT '0',
  `bind_datetime` int(11) DEFAULT '0' COMMENT '绑定时间',
  `add_datetime` int(11) NOT NULL DEFAULT '0' COMMENT '添加时间',
  `remark` varchar(100) NOT NULL DEFAULT '' COMMENT '处理信息',
  PRIMARY KEY (`charge_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1148 DEFAULT CHARSET=utf8 COMMENT='充电宝地址池';

-- ----------------------------
-- Records of d_charge
-- ----------------------------
INSERT INTO `d_charge` VALUES ('1', 'df8', 'afd', 'af', 'AA:CC:bb:2d:34', '1', '0', '1530341213', '1529113384', '测试');
INSERT INTO `d_charge` VALUES ('2', 'adf10', 'adf', 'afd', 'AA:CC:bb:2d:E4', '1', '0', '1530341213', '1529113384', '测试');
INSERT INTO `d_charge` VALUES ('3', 'afd11', 'adf', 'fad', 'abc', '1', '0', '1530341213', '0', '');
INSERT INTO `d_charge` VALUES ('4', 'afd12', 'adf', 'adf', 'efg', '1', '0', '1530341213', '0', '');
INSERT INTO `d_charge` VALUES ('5', 'afd13', 'afd', 'af', 'addg', '1', '0', '1530341213', '0', '');
INSERT INTO `d_charge` VALUES ('6', 'df16', 'afd', 'adf', 'dd', '1', '0', '1530341213', '0', '');
INSERT INTO `d_charge` VALUES ('7', 'afd17', 'afd', 'df', 'ss', '2', '82', '1530329437', '0', '');
INSERT INTO `d_charge` VALUES ('8', 'afa18', 'af', 'afd', 'dd', '1', '0', '1530341213', '0', '');
INSERT INTO `d_charge` VALUES ('9', 'AA:CC:bb:2d:E4', 'd', 'd', 'a', '2', '90', '1530337064', '0', '');
INSERT INTO `d_charge` VALUES ('10', 'AA:CC:bb:2d:E5', 'e', 'd', 'b', '1', '0', '1530341213', '0', '');
INSERT INTO `d_charge` VALUES ('11', 'AA:CC:bb:2d:E6', 'ad', 'adf', 'c', '1', '0', '1530341213', '0', '');
INSERT INTO `d_charge` VALUES ('12', 'AA:CC:bb:2d:E7', 'ad', 'adf', 'd', '1', '0', '1530341213', '0', '');
INSERT INTO `d_charge` VALUES ('13', 'AA:CC:bb:2d:E8', 'ad', 'adf', 'e', '2', '100', '1530343016', '0', '');
INSERT INTO `d_charge` VALUES ('14', 'AA:CC:bb:2d:E9', 'ad', 'adf', 'f', '1', '0', '1530341213', '0', '');
INSERT INTO `d_charge` VALUES ('15', 'AA:CC:bb:2d:E0', 'ad', 'adf', 'g', '2', '158', '1530344999', '0', '');
INSERT INTO `d_charge` VALUES ('16', 'AA:CC:bb:2d:01', 'ad', 'adf', 'h', '2', '102', '1530343881', '0', '');
INSERT INTO `d_charge` VALUES ('17', 'af11', 'ad', 'adf', 'i', '2', '165', '1530350830', '0', '');
INSERT INTO `d_charge` VALUES ('18', 'af12', 'ad', 'adf', 'j', '1', '0', '1530341213', '0', '');
INSERT INTO `d_charge` VALUES ('19', 'af13', 'ad', 'adf', 'k', '1', '0', '1530341213', '0', '');
INSERT INTO `d_charge` VALUES ('20', 'af14', 'ad', 'adf', 'l', '1', '0', '1530341213', '0', '');
INSERT INTO `d_charge` VALUES ('21', 'af15', 'ad', 'adf', 'm', '2', '256', '1530959648', '0', '');
INSERT INTO `d_charge` VALUES ('22', 'af16', 'ad', 'adf', 'n', '1', '0', '1530341213', '0', '');
INSERT INTO `d_charge` VALUES ('23', 'af17', 'ad', 'adf', 'o', '2', '222', '1530669004', '0', '');
INSERT INTO `d_charge` VALUES ('24', 'af18', 'ad', 'adf', 'p', '2', '252', '1530953013', '0', '');
INSERT INTO `d_charge` VALUES ('25', 'af19', 'ad', 'adf', 'q', '2', '223', '1530669157', '0', '');
INSERT INTO `d_charge` VALUES ('26', 'af20', 'ad', 'adf', 'r', '1', '0', '1530341213', '0', '');
INSERT INTO `d_charge` VALUES ('27', 'af21', 'ad', 'adf', 's', '2', '258', '1531120161', '0', '');
INSERT INTO `d_charge` VALUES ('28', 'af22', 'ad', 'adf', 'y', '1', '0', '1530341213', '0', '');
INSERT INTO `d_charge` VALUES ('29', 'af23', 'ad', 'adf', 'z', '2', '294', '1531212966', '0', '');
INSERT INTO `d_charge` VALUES ('30', 'af24', 'ad', 'adf', 'u', '1', '0', '1530341213', '0', '');
INSERT INTO `d_charge` VALUES ('31', 'AA:CC:bb:2d:02', 'ad', 'adf', 'v', '1', '0', '1530350591', '0', '');
INSERT INTO `d_charge` VALUES ('32', 'AA:CC:bb:2d:03', 'ad', 'adf', 'w', '2', '167', '1530352325', '0', '');
INSERT INTO `d_charge` VALUES ('33', 'af27', 'ad', 'adf', 'x', '1', '0', '1530341213', '0', '');
INSERT INTO `d_charge` VALUES ('34', 'af28', 'ad', 'adf', 'y', '1', '0', '1530341213', '0', '');
INSERT INTO `d_charge` VALUES ('35', 'af29', 'ad', 'adf', 'z', '1', '0', '1530341213', '0', '');
INSERT INTO `d_charge` VALUES ('137', 'device99', 'dd', 'dde', 'mac99', '2', '155', '1530346640', '1530280993', '');
INSERT INTO `d_charge` VALUES ('138', 'device98', 'dd', 'dde', 'mac98', '2', '99', '1530342653', '1530280993', '');
INSERT INTO `d_charge` VALUES ('139', 'device97', 'dd', 'dde', 'mac97', '2', '329', '1531304897', '1530280993', '');
INSERT INTO `d_charge` VALUES ('140', 'device96', 'dd', 'dde', 'mac96', '2', '328', '1531361733', '1530280993', '');
INSERT INTO `d_charge` VALUES ('141', 'device95', 'dd', 'dde', 'mac95', '2', '304', '1531276282', '1530280993', '');
INSERT INTO `d_charge` VALUES ('142', 'device94', 'dd', 'dde', 'mac94', '2', '314', '1531297043', '1530280993', '');
INSERT INTO `d_charge` VALUES ('143', 'AA:CC:bb:2d:04', 'dd', 'dde', 'mac93', '2', '168', '1530353888', '1530280993', '');
INSERT INTO `d_charge` VALUES ('144', 'device92', 'dd', 'dde', 'mac92', '1', '0', '1530341213', '1530280993', '');
INSERT INTO `d_charge` VALUES ('145', 'tzx_8482f4252f28', 'dd', 'dde', 'mac91', '2', '169', '1530357101', '1530280993', '');
INSERT INTO `d_charge` VALUES ('146', 'device90', 'dd', 'dde', 'mac90', '2', '105', '1530344655', '1530280993', '');
INSERT INTO `d_charge` VALUES ('147', 'device89', 'dd', 'dde', 'mac89', '1', '0', '1530341213', '1530280993', '');
INSERT INTO `d_charge` VALUES ('148', 'device88', 'dd', 'dde', 'mac88', '2', '0', '1530347992', '1530280993', '');
INSERT INTO `d_charge` VALUES ('149', 'device87', 'dd', 'dde', 'mac87', '2', '161', '1530347667', '1530280993', '');
INSERT INTO `d_charge` VALUES ('150', 'device86', 'dd', 'dde', 'mac86', '2', '164', '1530348109', '1530280993', '');
INSERT INTO `d_charge` VALUES ('151', 'device85', 'dd', 'dde', 'mac85', '2', '101', '1530350369', '1530280993', '');
INSERT INTO `d_charge` VALUES ('152', 'device84', 'dd', 'dde', 'mac84', '2', '209', '1530589300', '1530280993', '');
INSERT INTO `d_charge` VALUES ('153', 'device83', 'dd', 'dde', 'mac83', '2', '327', '1531303224', '1530280993', '');
INSERT INTO `d_charge` VALUES ('154', 'device82', 'dd', 'dde', 'mac82', '2', '326', '1531302582', '1530280993', '');
INSERT INTO `d_charge` VALUES ('155', 'device81', 'dd', 'dde', 'mac81', '2', '325', '1531302137', '1530280993', '');
INSERT INTO `d_charge` VALUES ('156', 'device80', 'dd', 'dde', 'mac80', '2', '324', '1531301648', '1530280993', '');
INSERT INTO `d_charge` VALUES ('157', 'device79', 'dd', 'dde', 'mac79', '2', '318', '1531298568', '1530280993', '');
INSERT INTO `d_charge` VALUES ('158', 'device78', 'dd', 'dde', 'mac78', '2', '313', '1531296814', '1530280993', '');
INSERT INTO `d_charge` VALUES ('161', 'device75', 'dd', 'dde', 'mac75', '1', '0', '1530341213', '1530280993', '');
INSERT INTO `d_charge` VALUES ('162', 'device74', 'dd', 'dde', 'mac74', '1', '0', '1530341213', '1530280993', '');
INSERT INTO `d_charge` VALUES ('163', 'device73', 'dd', 'dde', 'mac73', '2', '386', '1531464224', '1530280993', '');
INSERT INTO `d_charge` VALUES ('164', 'device72', 'dd', 'dde', 'mac72', '1', '0', '1530341213', '1530280993', '');
INSERT INTO `d_charge` VALUES ('165', 'device71', 'dd', 'dde', 'mac71', '1', '0', '1530341213', '1530280993', '');
INSERT INTO `d_charge` VALUES ('166', 'device70', 'dd', 'dde', 'mac70', '1', '0', '1530341213', '1530280993', '');
INSERT INTO `d_charge` VALUES ('167', 'device69', 'dd', 'dde', 'mac69', '1', '0', '1530341213', '1530280993', '');
INSERT INTO `d_charge` VALUES ('168', 'device68', 'dd', 'dde', 'mac68', '2', '0', '1531550125', '1530280993', '');
INSERT INTO `d_charge` VALUES ('169', 'device67', 'dd', 'dde', 'mac67', '1', '0', '1530341213', '1530280993', '');
INSERT INTO `d_charge` VALUES ('170', 'device66', 'dd', 'dde', 'mac66', '2', '7', '1532077850', '1530280993', '');
INSERT INTO `d_charge` VALUES ('171', 'device65', 'dd', 'dde', 'mac65', '2', '9', '1532084973', '1530280993', '');
INSERT INTO `d_charge` VALUES ('172', 'device64', 'dd', 'dde', 'mac64', '2', '33', '1532313386', '1530280993', '');
INSERT INTO `d_charge` VALUES ('173', 'device63', 'dd', 'dde', 'mac63', '2', '35', '1532315050', '1530280993', '');
INSERT INTO `d_charge` VALUES ('174', 'device62', 'dd', 'dde', 'mac62', '1', '0', '1530341213', '1530280993', '');
INSERT INTO `d_charge` VALUES ('175', 'device61', 'dd', 'dde', 'mac61', '1', '0', '1530341213', '1530280993', '');
INSERT INTO `d_charge` VALUES ('176', 'device60', 'dd', 'dde', 'mac60', '2', '225', '1530675289', '1530280993', '');
INSERT INTO `d_charge` VALUES ('177', 'device59', 'dd', 'dde', 'mac59', '2', '224', '1530669865', '1530280993', '');
INSERT INTO `d_charge` VALUES ('178', 'device58', 'dd', 'dde', 'mac58', '2', '217', '1530607879', '1530280993', '');
INSERT INTO `d_charge` VALUES ('179', 'device57', 'dd', 'dde', 'mac57', '2', '215', '1530600901', '1530280993', '');
INSERT INTO `d_charge` VALUES ('180', 'device56', 'dd', 'dde', 'mac56', '2', '213', '1530597404', '1530280993', '');
INSERT INTO `d_charge` VALUES ('181', 'device55', 'dd', 'dde', 'mac55', '2', '162', '1530347596', '1530280993', '');
INSERT INTO `d_charge` VALUES ('182', 'device54', 'dd', 'dde', 'mac54', '2', '210', '1530586603', '1530280993', '');
INSERT INTO `d_charge` VALUES ('183', 'device53', 'dd', 'dde', 'mac53', '2', '104', '1530354043', '1530280993', '');
INSERT INTO `d_charge` VALUES ('184', 'device52', 'dd', 'dde', 'mac52', '2', '202', '1530521822', '1530280993', '');
INSERT INTO `d_charge` VALUES ('185', 'device51', 'dd', 'dde', 'mac51', '2', '197', '1530520604', '1530280993', '');
INSERT INTO `d_charge` VALUES ('186', 'device50', 'dd', 'dde', 'mac50', '2', '201', '1530520456', '1530280993', '');
INSERT INTO `d_charge` VALUES ('187', 'device49', 'dd', 'dde', 'mac49', '2', '199', '1530519126', '1530280993', '');
INSERT INTO `d_charge` VALUES ('188', 'device48', 'dd', 'dde', 'mac48', '2', '198', '1530517343', '1530280993', '');
INSERT INTO `d_charge` VALUES ('189', 'device47', 'dd', 'dde', 'mac47', '2', '422', '1531721608', '1530280993', '');
INSERT INTO `d_charge` VALUES ('190', 'device46', 'dd', 'dde', 'mac46', '1', '0', '1530341213', '1530280993', '');
INSERT INTO `d_charge` VALUES ('191', 'device45', 'dd', 'dde', 'mac45', '2', '220', '1530611840', '1530280993', '');
INSERT INTO `d_charge` VALUES ('192', 'device44', 'dd', 'dde', 'mac44', '2', '218', '1530611482', '1530280993', '');
INSERT INTO `d_charge` VALUES ('193', 'device43', 'dd', 'dde', 'mac43', '1', '0', '1530341213', '1530280993', '');
INSERT INTO `d_charge` VALUES ('194', 'device42', 'dd', 'dde', 'mac42', '2', '212', '1530589998', '1530280993', '');
INSERT INTO `d_charge` VALUES ('195', 'device41', 'dd', 'dde', 'mac41', '2', '377', '1531453228', '1530280993', '');
INSERT INTO `d_charge` VALUES ('196', 'device40', 'dd', 'dde', 'mac40', '2', '280', '1531203063', '1530280993', '');
INSERT INTO `d_charge` VALUES ('197', 'device39', 'dd', 'dde', 'mac39', '1', '0', '1530341213', '1530280993', '');
INSERT INTO `d_charge` VALUES ('198', 'device38', 'dd', 'dde', 'mac38', '1', '0', '1530341213', '1530280993', '');
INSERT INTO `d_charge` VALUES ('199', 'device37', 'dd', 'dde', 'mac37', '1', '0', '1530341213', '1530280993', '');
INSERT INTO `d_charge` VALUES ('200', 'device36', 'dd', 'dde', 'mac36', '2', '195', '1530500878', '1530280993', '');
INSERT INTO `d_charge` VALUES ('201', 'device35', 'dd', 'dde', 'mac35', '1', '0', '1530341213', '1530280993', '');
INSERT INTO `d_charge` VALUES ('202', 'device34', 'dd', 'dde', 'mac34', '1', '0', '1530341213', '1530280993', '');
INSERT INTO `d_charge` VALUES ('203', 'device33', 'dd', 'dde', 'mac33', '2', '221', '1530613277', '1530280993', '');
INSERT INTO `d_charge` VALUES ('204', 'device32', 'dd', 'dde', 'mac32', '1', '0', '1530341213', '1530280993', '');
INSERT INTO `d_charge` VALUES ('205', 'device31', 'dd', 'dde', 'mac31', '1', '0', '1530341213', '1530280993', '');
INSERT INTO `d_charge` VALUES ('206', 'device30', 'dd', 'dde', 'mac30', '1', '0', '1530341213', '1530280993', '');
INSERT INTO `d_charge` VALUES ('207', 'device29', 'dd', 'dde', 'mac29', '1', '0', '1530341213', '1530280993', '');
INSERT INTO `d_charge` VALUES ('208', 'device28', 'dd', 'dde', 'mac28', '1', '0', '1530341213', '1530280993', '');
INSERT INTO `d_charge` VALUES ('209', 'device27', 'dd', 'dde', 'mac27', '1', '0', '1530341213', '1530280993', '');
INSERT INTO `d_charge` VALUES ('210', 'device26', 'dd', 'dde', 'mac26', '1', '0', '1530341213', '1530280993', '');
INSERT INTO `d_charge` VALUES ('211', 'device25', 'dd', 'dde', 'mac25', '1', '0', '1530341213', '1530280993', '');
INSERT INTO `d_charge` VALUES ('212', 'device24', 'dd', 'dde', 'mac24', '1', '0', '1530341213', '1530280993', '');
INSERT INTO `d_charge` VALUES ('213', 'device23', 'dd', 'dde', 'mac23', '1', '0', '1530341213', '1530280993', '');
INSERT INTO `d_charge` VALUES ('214', 'device22', 'dd', 'dde', 'mac22', '1', '0', '1530341213', '1530280993', '');
INSERT INTO `d_charge` VALUES ('215', 'device21', 'dd', 'dde', 'mac21', '1', '0', '1530341213', '1530280993', '');
INSERT INTO `d_charge` VALUES ('216', 'device20', 'dd', 'dde', 'mac20', '1', '0', '1530341213', '1530280993', '');
INSERT INTO `d_charge` VALUES ('217', 'device19', 'dd', 'dde', 'mac19', '1', '0', '1530341213', '1530280993', '');
INSERT INTO `d_charge` VALUES ('218', 'device18', 'dd', 'dde', 'mac18', '1', '0', '1530341213', '1530280993', '');
INSERT INTO `d_charge` VALUES ('219', 'device17', 'dd', 'dde', 'mac17', '1', '0', '1530341213', '1530280993', '');
INSERT INTO `d_charge` VALUES ('220', 'device16', 'dd', 'dde', 'mac16', '1', '0', '1530341213', '1530280993', '');
INSERT INTO `d_charge` VALUES ('221', 'device15', 'dd', 'dde', 'mac15', '1', '0', '1530341213', '1530280993', '');
INSERT INTO `d_charge` VALUES ('222', 'device14', 'dd', 'dde', 'mac14', '1', '0', '1530341213', '1530280993', '');
INSERT INTO `d_charge` VALUES ('223', 'device13', 'dd', 'dde', 'mac13', '1', '0', '1530341213', '1530280993', '');
INSERT INTO `d_charge` VALUES ('224', 'device12', 'dd', 'dde', 'mac12', '1', '0', '1530341213', '1530280993', '');
INSERT INTO `d_charge` VALUES ('225', 'device11', 'dd', 'dde', 'mac11', '1', '0', '1530341213', '1530280993', '');
INSERT INTO `d_charge` VALUES ('226', 'device10', 'dd', 'dde', 'mac10', '1', '0', '1530341213', '1530280993', '');
INSERT INTO `d_charge` VALUES ('227', 'device9', 'dd', 'dde', 'mac9', '1', '0', '1530341213', '1530280993', '');
INSERT INTO `d_charge` VALUES ('228', 'device8', 'dd', 'dde', 'mac8', '1', '0', '1530341213', '1530280993', '');
INSERT INTO `d_charge` VALUES ('229', 'device7', 'dd', 'dde', 'mac7', '1', '0', '1530341213', '1530280993', '');
INSERT INTO `d_charge` VALUES ('230', 'device6', 'dd', 'dde', 'mac6', '2', '86', '1530340390', '1530280993', '');
INSERT INTO `d_charge` VALUES ('231', 'device5', 'dd', 'dde', 'mac5', '1', '0', '1530341213', '1530280993', '');
INSERT INTO `d_charge` VALUES ('232', 'device4', 'dd', 'dde', 'mac4', '1', '0', '1530341213', '1530280993', '');
INSERT INTO `d_charge` VALUES ('233', 'device3', 'dd', 'dde', 'mac3', '1', '0', '1530341213', '1530280993', '');
INSERT INTO `d_charge` VALUES ('234', 'device2', 'dd', 'dde', 'mac2', '2', '91', '1530340032', '1530280993', '');
INSERT INTO `d_charge` VALUES ('235', 'device1', 'dd', 'dde', 'mac1', '1', '0', '0', '1530280993', '');
INSERT INTO `d_charge` VALUES ('236', 'device_test400', 'dd', 'dde', 'mac_test400', '2', '254', '1530956788', '1530281039', '');
INSERT INTO `d_charge` VALUES ('237', 'device_test399', 'dd', 'dde', 'mac_test399', '1', '0', '1530341213', '1530281039', '');
INSERT INTO `d_charge` VALUES ('238', 'device_test398', 'dd', 'dde', 'mac_test398', '1', '0', '1530341213', '1530281039', '');
INSERT INTO `d_charge` VALUES ('239', 'device_test397', 'dd', 'dde', 'mac_test397', '1', '0', '1530341213', '1530281039', '');
INSERT INTO `d_charge` VALUES ('240', 'device_test396', 'dd', 'dde', 'mac_test396', '1', '0', '1530341213', '1530281039', '');
INSERT INTO `d_charge` VALUES ('241', 'device_test395', 'dd', 'dde', 'mac_test395', '2', '253', '1530956599', '1530281039', '');
INSERT INTO `d_charge` VALUES ('242', 'device_test394', 'dd', 'dde', 'mac_test394', '1', '0', '1530341213', '1530281039', '');
INSERT INTO `d_charge` VALUES ('243', 'device_test393', 'dd', 'dde', 'mac_test393', '1', '0', '1530341213', '1530281039', '');
INSERT INTO `d_charge` VALUES ('244', 'device_test392', 'dd', 'dde', 'mac_test392', '1', '0', '1530341213', '1530281039', '');
INSERT INTO `d_charge` VALUES ('245', 'device_test391', 'dd', 'dde', 'mac_test391', '1', '0', '1530341213', '1530281039', '');
INSERT INTO `d_charge` VALUES ('246', 'device_test390', 'dd', 'dde', 'mac_test390', '2', '255', '1530958972', '1530281039', '');
INSERT INTO `d_charge` VALUES ('247', 'device_test389', 'dd', 'dde', 'mac_test389', '1', '0', '1530341213', '1530281039', '');
INSERT INTO `d_charge` VALUES ('248', 'device_test388', 'dd', 'dde', 'mac_test388', '1', '0', '1530341213', '1530281039', '');
INSERT INTO `d_charge` VALUES ('249', 'device_test387', 'dd', 'dde', 'mac_test387', '1', '0', '1530341213', '1530281039', '');
INSERT INTO `d_charge` VALUES ('250', 'device_test386', 'dd', 'dde', 'mac_test386', '1', '0', '1530341213', '1530281039', '');
INSERT INTO `d_charge` VALUES ('251', 'device_test385', 'dd', 'dde', 'mac_test385', '1', '0', '1530341213', '1530281039', '');
INSERT INTO `d_charge` VALUES ('252', 'device_test384', 'dd', 'dde', 'mac_test384', '1', '0', '1530341213', '1530281039', '');
INSERT INTO `d_charge` VALUES ('253', 'device_test383', 'dd', 'dde', 'mac_test383', '1', '0', '1530341213', '1530281039', '');
INSERT INTO `d_charge` VALUES ('254', 'device_test382', 'dd', 'dde', 'mac_test382', '1', '0', '1530341213', '1530281039', '');
INSERT INTO `d_charge` VALUES ('255', 'device_test381', 'dd', 'dde', 'mac_test381', '1', '0', '1530341213', '1530281039', '');
INSERT INTO `d_charge` VALUES ('256', 'device_test380', 'dd', 'dde', 'mac_test380', '1', '0', '1530341213', '1530281039', '');
INSERT INTO `d_charge` VALUES ('257', 'device_test379', 'dd', 'dde', 'mac_test379', '1', '0', '1530341213', '1530281039', '');
INSERT INTO `d_charge` VALUES ('258', 'device_test378', 'dd', 'dde', 'mac_test378', '1', '0', '1530341213', '1530281039', '');
INSERT INTO `d_charge` VALUES ('259', 'device_test377', 'dd', 'dde', 'mac_test377', '1', '0', '1530341213', '1530281039', '');
INSERT INTO `d_charge` VALUES ('260', 'device_test376', 'dd', 'dde', 'mac_test376', '1', '0', '1530341213', '1530281039', '');
INSERT INTO `d_charge` VALUES ('261', 'device_test375', 'dd', 'dde', 'mac_test375', '1', '0', '1530341213', '1530281039', '');
INSERT INTO `d_charge` VALUES ('262', 'device_test374', 'dd', 'dde', 'mac_test374', '1', '0', '1530341213', '1530281039', '');
INSERT INTO `d_charge` VALUES ('263', 'device_test373', 'dd', 'dde', 'mac_test373', '1', '0', '1530341213', '1530281039', '');
INSERT INTO `d_charge` VALUES ('264', 'device_test372', 'dd', 'dde', 'mac_test372', '1', '0', '1530341213', '1530281039', '');
INSERT INTO `d_charge` VALUES ('265', 'device_test371', 'dd', 'dde', 'mac_test371', '2', '291', '1531208849', '1530281040', '');
INSERT INTO `d_charge` VALUES ('266', 'device_test370', 'dd', 'dde', 'mac_test370', '2', '295', '1531209065', '1530281040', '');
INSERT INTO `d_charge` VALUES ('267', 'device_test369', 'dd', 'dde', 'mac_test369', '2', '271', '1531194983', '1530281040', '');
INSERT INTO `d_charge` VALUES ('268', 'device_test368', 'dd', 'dde', 'mac_test368', '1', '0', '1530341213', '1530281040', '');
INSERT INTO `d_charge` VALUES ('269', 'device_test367', 'dd', 'dde', 'mac_test367', '1', '0', '1530341213', '1530281040', '');
INSERT INTO `d_charge` VALUES ('270', 'device_test366', 'dd', 'dde', 'mac_test366', '1', '0', '1530341213', '1530281040', '');
INSERT INTO `d_charge` VALUES ('271', 'device_test365', 'dd', 'dde', 'mac_test365', '1', '0', '1530341213', '1530281040', '');
INSERT INTO `d_charge` VALUES ('272', 'device_test364', 'dd', 'dde', 'mac_test364', '1', '0', '1530341213', '1530281040', '');
INSERT INTO `d_charge` VALUES ('273', 'device_test363', 'dd', 'dde', 'mac_test363', '1', '0', '1530341213', '1530281040', '');
INSERT INTO `d_charge` VALUES ('274', 'device_test362', 'dd', 'dde', 'mac_test362', '1', '0', '1530341213', '1530281040', '');
INSERT INTO `d_charge` VALUES ('275', 'device_test361', 'dd', 'dde', 'mac_test361', '2', '283', '1531205206', '1530281040', '');
INSERT INTO `d_charge` VALUES ('276', 'device_test360', 'dd', 'dde', 'mac_test360', '1', '0', '1530341213', '1530281040', '');
INSERT INTO `d_charge` VALUES ('277', 'device_test359', 'dd', 'dde', 'mac_test359', '1', '0', '1530341213', '1530281040', '');
INSERT INTO `d_charge` VALUES ('278', 'device_test358', 'dd', 'dde', 'mac_test358', '1', '0', '1530341213', '1530281040', '');
INSERT INTO `d_charge` VALUES ('279', 'device_test357', 'dd', 'dde', 'mac_test357', '2', '434', '1531550198', '1530281040', '');
INSERT INTO `d_charge` VALUES ('280', 'device_test356', 'dd', 'dde', 'mac_test356', '1', '0', '1530341213', '1530281040', '');
INSERT INTO `d_charge` VALUES ('281', 'device_test355', 'dd', 'dde', 'mac_test355', '1', '0', '1530341213', '1530281040', '');
INSERT INTO `d_charge` VALUES ('282', 'device_test354', 'dd', 'dde', 'mac_test354', '1', '0', '1530341213', '1530281040', '');
INSERT INTO `d_charge` VALUES ('283', 'device_test353', 'dd', 'dde', 'mac_test353', '1', '0', '1530341213', '1530281040', '');
INSERT INTO `d_charge` VALUES ('284', 'device_test352', 'dd', 'dde', 'mac_test352', '1', '0', '1530341213', '1530281040', '');
INSERT INTO `d_charge` VALUES ('285', 'device_test351', 'dd', 'dde', 'mac_test351', '1', '0', '1530341213', '1530281040', '');
INSERT INTO `d_charge` VALUES ('286', 'device_test350', 'dd', 'dde', 'mac_test350', '1', '0', '1530341213', '1530281040', '');
INSERT INTO `d_charge` VALUES ('287', 'device_test349', 'dd', 'dde', 'mac_test349', '1', '0', '1530341213', '1530281040', '');
INSERT INTO `d_charge` VALUES ('288', 'device_test348', 'dd', 'dde', 'mac_test348', '1', '0', '1530341213', '1530281040', '');
INSERT INTO `d_charge` VALUES ('289', 'device_test347', 'dd', 'dde', 'mac_test347', '1', '0', '1530341213', '1530281040', '');
INSERT INTO `d_charge` VALUES ('290', 'device_test346', 'dd', 'dde', 'mac_test346', '1', '0', '1530341213', '1530281040', '');
INSERT INTO `d_charge` VALUES ('291', 'device_test345', 'dd', 'dde', 'mac_test345', '2', '438', '1531552393', '1530281040', '');
INSERT INTO `d_charge` VALUES ('292', 'device_test344', 'dd', 'dde', 'mac_test344', '1', '0', '1530341213', '1530281040', '');
INSERT INTO `d_charge` VALUES ('293', 'device_test343', 'dd', 'dde', 'mac_test343', '1', '0', '1530341213', '1530281040', '');
INSERT INTO `d_charge` VALUES ('294', 'device_test342', 'dd', 'dde', 'mac_test342', '1', '0', '1530341213', '1530281040', '');
INSERT INTO `d_charge` VALUES ('295', 'device_test341', 'dd', 'dde', 'mac_test341', '2', '434', '1531550233', '1530281040', '');
INSERT INTO `d_charge` VALUES ('296', 'device_test340', 'dd', 'dde', 'mac_test340', '1', '0', '1530341213', '1530281040', '');
INSERT INTO `d_charge` VALUES ('297', 'device_test339', 'dd', 'dde', 'mac_test339', '1', '0', '1530341213', '1530281040', '');
INSERT INTO `d_charge` VALUES ('298', 'device_test338', 'dd', 'dde', 'mac_test338', '1', '0', '1530341213', '1530281040', '');
INSERT INTO `d_charge` VALUES ('299', 'device_test337', 'dd', 'dde', 'mac_test337', '1', '0', '1530341213', '1530281040', '');
INSERT INTO `d_charge` VALUES ('300', 'device_test336', 'dd', 'dde', 'mac_test336', '1', '0', '1530341213', '1530281040', '');
INSERT INTO `d_charge` VALUES ('301', 'device_test335', 'dd', 'dde', 'mac_test335', '1', '0', '1530341213', '1530281040', '');
INSERT INTO `d_charge` VALUES ('302', 'device_test334', 'dd', 'dde', 'mac_test334', '1', '0', '1530341213', '1530281040', '');
INSERT INTO `d_charge` VALUES ('303', 'device_test333', 'dd', 'dde', 'mac_test333', '2', '293', '1531213005', '1530281040', '');
INSERT INTO `d_charge` VALUES ('304', 'device_test332', 'dd', 'dde', 'mac_test332', '1', '0', '1530341213', '1530281040', '');
INSERT INTO `d_charge` VALUES ('305', 'device_test331', 'dd', 'dde', 'mac_test331', '1', '0', '1530341213', '1530281040', '');
INSERT INTO `d_charge` VALUES ('306', 'device_test330', 'dd', 'dde', 'mac_test330', '1', '0', '1530341213', '1530281040', '');
INSERT INTO `d_charge` VALUES ('307', 'device_test329', 'dd', 'dde', 'mac_test329', '1', '0', '1530341213', '1530281040', '');
INSERT INTO `d_charge` VALUES ('308', 'device_test328', 'dd', 'dde', 'mac_test328', '1', '0', '1530341213', '1530281040', '');
INSERT INTO `d_charge` VALUES ('309', 'device_test327', 'dd', 'dde', 'mac_test327', '1', '0', '1530341213', '1530281040', '');
INSERT INTO `d_charge` VALUES ('310', 'device_test326', 'dd', 'dde', 'mac_test326', '1', '0', '1530341213', '1530281040', '');
INSERT INTO `d_charge` VALUES ('311', 'device_test325', 'dd', 'dde', 'mac_test325', '1', '0', '1530341213', '1530281040', '');
INSERT INTO `d_charge` VALUES ('312', 'device_test324', 'dd', 'dde', 'mac_test324', '1', '0', '1530341213', '1530281040', '');
INSERT INTO `d_charge` VALUES ('313', 'device_test323', 'dd', 'dde', 'mac_test323', '1', '0', '1530341213', '1530281040', '');
INSERT INTO `d_charge` VALUES ('314', 'device_test322', 'dd', 'dde', 'mac_test322', '2', '297', '1531216393', '1530281040', '');
INSERT INTO `d_charge` VALUES ('315', 'device_test321', 'dd', 'dde', 'mac_test321', '2', '296', '1531213610', '1530281040', '');
INSERT INTO `d_charge` VALUES ('316', 'device_test320', 'dd', 'dde', 'mac_test320', '1', '0', '1530341213', '1530281040', '');
INSERT INTO `d_charge` VALUES ('317', 'device_test319', 'dd', 'dde', 'mac_test319', '1', '0', '1530341213', '1530281040', '');
INSERT INTO `d_charge` VALUES ('318', 'device_test318', 'dd', 'dde', 'mac_test318', '1', '0', '1530341213', '1530281040', '');
INSERT INTO `d_charge` VALUES ('319', 'device_test317', 'dd', 'dde', 'mac_test317', '1', '0', '1530341213', '1530281040', '');
INSERT INTO `d_charge` VALUES ('320', 'device_test316', 'dd', 'dde', 'mac_test316', '1', '0', '1530341213', '1530281040', '');
INSERT INTO `d_charge` VALUES ('321', 'device_test315', 'dd', 'dde', 'mac_test315', '1', '0', '1530341213', '1530281040', '');
INSERT INTO `d_charge` VALUES ('322', 'device_test314', 'dd', 'dde', 'mac_test314', '1', '0', '1530341213', '1530281040', '');
INSERT INTO `d_charge` VALUES ('323', 'device_test313', 'dd', 'dde', 'mac_test313', '1', '0', '1530341213', '1530281040', '');
INSERT INTO `d_charge` VALUES ('324', 'device_test312', 'dd', 'dde', 'mac_test312', '1', '0', '1530341213', '1530281040', '');
INSERT INTO `d_charge` VALUES ('325', 'device_test311', 'dd', 'dde', 'mac_test311', '1', '0', '1530341213', '1530281040', '');
INSERT INTO `d_charge` VALUES ('326', 'device_test310', 'dd', 'dde', 'mac_test310', '1', '0', '1530341213', '1530281040', '');
INSERT INTO `d_charge` VALUES ('327', 'device_test309', 'dd', 'dde', 'mac_test309', '1', '0', '1530341213', '1530281040', '');
INSERT INTO `d_charge` VALUES ('328', 'device_test308', 'dd', 'dde', 'mac_test308', '1', '0', '1530341213', '1530281040', '');
INSERT INTO `d_charge` VALUES ('329', 'device_test307', 'dd', 'dde', 'mac_test307', '1', '0', '1530341213', '1530281040', '');
INSERT INTO `d_charge` VALUES ('330', 'device_test306', 'dd', 'dde', 'mac_test306', '1', '0', '1530341213', '1530281040', '');
INSERT INTO `d_charge` VALUES ('331', 'device_test305', 'dd', 'dde', 'mac_test305', '1', '0', '1530341213', '1530281040', '');
INSERT INTO `d_charge` VALUES ('332', 'device_test304', 'dd', 'dde', 'mac_test304', '1', '0', '1530341213', '1530281040', '');
INSERT INTO `d_charge` VALUES ('333', 'device_test303', 'dd', 'dde', 'mac_test303', '1', '0', '1530341213', '1530281040', '');
INSERT INTO `d_charge` VALUES ('334', 'device_test302', 'dd', 'dde', 'mac_test302', '1', '0', '1530341213', '1530281040', '');
INSERT INTO `d_charge` VALUES ('335', 'device_test301', 'dd', 'dde', 'mac_test301', '1', '0', '1530341213', '1530281040', '');
INSERT INTO `d_charge` VALUES ('336', 'device_test300', 'dd', 'dde', 'mac_test300', '1', '0', '1530341213', '1530281040', '');
INSERT INTO `d_charge` VALUES ('337', 'device_test299', 'dd', 'dde', 'mac_test299', '1', '0', '1530341213', '1530281040', '');
INSERT INTO `d_charge` VALUES ('338', 'device_test298', 'dd', 'dde', 'mac_test298', '1', '0', '1530341213', '1530281040', '');
INSERT INTO `d_charge` VALUES ('339', 'device_test297', 'dd', 'dde', 'mac_test297', '1', '0', '1530341213', '1530281040', '');
INSERT INTO `d_charge` VALUES ('340', 'device_test296', 'dd', 'dde', 'mac_test296', '1', '0', '1530341213', '1530281040', '');
INSERT INTO `d_charge` VALUES ('341', 'device_test295', 'dd', 'dde', 'mac_test295', '1', '0', '1530341213', '1530281040', '');
INSERT INTO `d_charge` VALUES ('342', 'device_test294', 'dd', 'dde', 'mac_test294', '1', '0', '1530341213', '1530281040', '');
INSERT INTO `d_charge` VALUES ('343', 'device_test293', 'dd', 'dde', 'mac_test293', '1', '0', '1530341213', '1530281040', '');
INSERT INTO `d_charge` VALUES ('344', 'device_test292', 'dd', 'dde', 'mac_test292', '1', '0', '1530341213', '1530281040', '');
INSERT INTO `d_charge` VALUES ('345', 'device_test291', 'dd', 'dde', 'mac_test291', '1', '0', '1530341213', '1530281040', '');
INSERT INTO `d_charge` VALUES ('346', 'device_test290', 'dd', 'dde', 'mac_test290', '1', '0', '1530341213', '1530281040', '');
INSERT INTO `d_charge` VALUES ('347', 'device_test289', 'dd', 'dde', 'mac_test289', '1', '0', '1530341213', '1530281040', '');
INSERT INTO `d_charge` VALUES ('348', 'device_test288', 'dd', 'dde', 'mac_test288', '1', '0', '1530341213', '1530281040', '');
INSERT INTO `d_charge` VALUES ('349', 'device_test287', 'dd', 'dde', 'mac_test287', '1', '0', '1530341213', '1530281040', '');
INSERT INTO `d_charge` VALUES ('350', 'device_test286', 'dd', 'dde', 'mac_test286', '1', '0', '1530341213', '1530281040', '');
INSERT INTO `d_charge` VALUES ('351', 'device_test285', 'dd', 'dde', 'mac_test285', '1', '0', '1530341213', '1530281040', '');
INSERT INTO `d_charge` VALUES ('352', 'device_test284', 'dd', 'dde', 'mac_test284', '1', '0', '1530341213', '1530281040', '');
INSERT INTO `d_charge` VALUES ('353', 'device_test283', 'dd', 'dde', 'mac_test283', '1', '0', '1530341213', '1530281040', '');
INSERT INTO `d_charge` VALUES ('354', 'device_test282', 'dd', 'dde', 'mac_test282', '1', '0', '1530341213', '1530281040', '');
INSERT INTO `d_charge` VALUES ('355', 'device_test281', 'dd', 'dde', 'mac_test281', '1', '0', '1530341213', '1530281040', '');
INSERT INTO `d_charge` VALUES ('356', 'device_test280', 'dd', 'dde', 'mac_test280', '1', '0', '1530341213', '1530281040', '');
INSERT INTO `d_charge` VALUES ('357', 'device_test279', 'dd', 'dde', 'mac_test279', '1', '0', '1530341213', '1530281040', '');
INSERT INTO `d_charge` VALUES ('358', 'device_test278', 'dd', 'dde', 'mac_test278', '1', '0', '1530341213', '1530281040', '');
INSERT INTO `d_charge` VALUES ('359', 'device_test277', 'dd', 'dde', 'mac_test277', '1', '0', '1530341213', '1530281040', '');
INSERT INTO `d_charge` VALUES ('360', 'device_test276', 'dd', 'dde', 'mac_test276', '1', '0', '1530341213', '1530281040', '');
INSERT INTO `d_charge` VALUES ('361', 'device_test275', 'dd', 'dde', 'mac_test275', '1', '0', '1530341213', '1530281040', '');
INSERT INTO `d_charge` VALUES ('362', 'device_test274', 'dd', 'dde', 'mac_test274', '1', '0', '1530341213', '1530281040', '');
INSERT INTO `d_charge` VALUES ('363', 'device_test273', 'dd', 'dde', 'mac_test273', '1', '0', '1530341213', '1530281040', '');
INSERT INTO `d_charge` VALUES ('364', 'device_test272', 'dd', 'dde', 'mac_test272', '1', '0', '1530341213', '1530281040', '');
INSERT INTO `d_charge` VALUES ('365', 'device_test271', 'dd', 'dde', 'mac_test271', '1', '0', '1530341213', '1530281040', '');
INSERT INTO `d_charge` VALUES ('366', 'device_test270', 'dd', 'dde', 'mac_test270', '1', '0', '1530341213', '1530281040', '');
INSERT INTO `d_charge` VALUES ('367', 'device_test269', 'dd', 'dde', 'mac_test269', '1', '0', '1530341213', '1530281040', '');
INSERT INTO `d_charge` VALUES ('368', 'device_test268', 'dd', 'dde', 'mac_test268', '1', '0', '1530341213', '1530281040', '');
INSERT INTO `d_charge` VALUES ('369', 'device_test267', 'dd', 'dde', 'mac_test267', '1', '0', '1530341213', '1530281040', '');
INSERT INTO `d_charge` VALUES ('370', 'device_test266', 'dd', 'dde', 'mac_test266', '1', '0', '1530341213', '1530281040', '');
INSERT INTO `d_charge` VALUES ('371', 'device_test265', 'dd', 'dde', 'mac_test265', '1', '0', '1530341213', '1530281040', '');
INSERT INTO `d_charge` VALUES ('372', 'device_test264', 'dd', 'dde', 'mac_test264', '1', '0', '1530341213', '1530281040', '');
INSERT INTO `d_charge` VALUES ('373', 'device_test263', 'dd', 'dde', 'mac_test263', '1', '0', '1530341213', '1530281040', '');
INSERT INTO `d_charge` VALUES ('374', 'device_test262', 'dd', 'dde', 'mac_test262', '1', '0', '1530341213', '1530281040', '');
INSERT INTO `d_charge` VALUES ('375', 'device_test261', 'dd', 'dde', 'mac_test261', '1', '0', '1530341213', '1530281040', '');
INSERT INTO `d_charge` VALUES ('376', 'device_test260', 'dd', 'dde', 'mac_test260', '1', '0', '1530341213', '1530281040', '');
INSERT INTO `d_charge` VALUES ('377', 'device_test259', 'dd', 'dde', 'mac_test259', '1', '0', '1530341213', '1530281040', '');
INSERT INTO `d_charge` VALUES ('378', 'device_test258', 'dd', 'dde', 'mac_test258', '1', '0', '1530341213', '1530281040', '');
INSERT INTO `d_charge` VALUES ('379', 'device_test257', 'dd', 'dde', 'mac_test257', '1', '0', '1530341213', '1530281040', '');
INSERT INTO `d_charge` VALUES ('380', 'device_test256', 'dd', 'dde', 'mac_test256', '1', '0', '1530341213', '1530281040', '');
INSERT INTO `d_charge` VALUES ('381', 'device_test255', 'dd', 'dde', 'mac_test255', '1', '0', '1530341213', '1530281040', '');
INSERT INTO `d_charge` VALUES ('382', 'device_test254', 'dd', 'dde', 'mac_test254', '1', '0', '1530341213', '1530281040', '');
INSERT INTO `d_charge` VALUES ('383', 'device_test253', 'dd', 'dde', 'mac_test253', '1', '0', '1530341213', '1530281040', '');
INSERT INTO `d_charge` VALUES ('384', 'device_test252', 'dd', 'dde', 'mac_test252', '1', '0', '1530341213', '1530281040', '');
INSERT INTO `d_charge` VALUES ('385', 'device_test251', 'dd', 'dde', 'mac_test251', '1', '0', '1530341213', '1530281040', '');
INSERT INTO `d_charge` VALUES ('386', 'device_test250', 'dd', 'dde', 'mac_test250', '1', '0', '1530341213', '1530281040', '');
INSERT INTO `d_charge` VALUES ('387', 'device_test249', 'dd', 'dde', 'mac_test249', '1', '0', '1530341213', '1530281040', '');
INSERT INTO `d_charge` VALUES ('388', 'device_test248', 'dd', 'dde', 'mac_test248', '1', '0', '1530341213', '1530281040', '');
INSERT INTO `d_charge` VALUES ('389', 'device_test247', 'dd', 'dde', 'mac_test247', '1', '0', '1530341213', '1530281040', '');
INSERT INTO `d_charge` VALUES ('390', 'device_test246', 'dd', 'dde', 'mac_test246', '1', '0', '1530341213', '1530281040', '');
INSERT INTO `d_charge` VALUES ('391', 'device_test245', 'dd', 'dde', 'mac_test245', '1', '0', '1530341213', '1530281040', '');
INSERT INTO `d_charge` VALUES ('392', 'device_test244', 'dd', 'dde', 'mac_test244', '1', '0', '1530341213', '1530281040', '');
INSERT INTO `d_charge` VALUES ('393', 'device_test243', 'dd', 'dde', 'mac_test243', '1', '0', '1530341213', '1530281040', '');
INSERT INTO `d_charge` VALUES ('394', 'device_test242', 'dd', 'dde', 'mac_test242', '1', '0', '1530341213', '1530281040', '');
INSERT INTO `d_charge` VALUES ('395', 'device_test241', 'dd', 'dde', 'mac_test241', '1', '0', '1530341213', '1530281040', '');
INSERT INTO `d_charge` VALUES ('396', 'device_test240', 'dd', 'dde', 'mac_test240', '1', '0', '1530341213', '1530281040', '');
INSERT INTO `d_charge` VALUES ('397', 'device_test239', 'dd', 'dde', 'mac_test239', '1', '0', '1530341213', '1530281040', '');
INSERT INTO `d_charge` VALUES ('398', 'device_test238', 'dd', 'dde', 'mac_test238', '1', '0', '1530341213', '1530281040', '');
INSERT INTO `d_charge` VALUES ('399', 'device_test237', 'dd', 'dde', 'mac_test237', '1', '0', '1530341213', '1530281040', '');
INSERT INTO `d_charge` VALUES ('400', 'device_test236', 'dd', 'dde', 'mac_test236', '1', '0', '1530341213', '1530281040', '');
INSERT INTO `d_charge` VALUES ('401', 'device_test235', 'dd', 'dde', 'mac_test235', '1', '0', '1530341213', '1530281040', '');
INSERT INTO `d_charge` VALUES ('402', 'device_test234', 'dd', 'dde', 'mac_test234', '1', '0', '1530341213', '1530281040', '');
INSERT INTO `d_charge` VALUES ('403', 'device_test233', 'dd', 'dde', 'mac_test233', '1', '0', '1530341213', '1530281040', '');
INSERT INTO `d_charge` VALUES ('404', 'device_test232', 'dd', 'dde', 'mac_test232', '1', '0', '1530341213', '1530281040', '');
INSERT INTO `d_charge` VALUES ('405', 'device_test231', 'dd', 'dde', 'mac_test231', '1', '0', '1530341213', '1530281040', '');
INSERT INTO `d_charge` VALUES ('406', 'device_test230', 'dd', 'dde', 'mac_test230', '1', '0', '1530341213', '1530281040', '');
INSERT INTO `d_charge` VALUES ('407', 'device_test229', 'dd', 'dde', 'mac_test229', '1', '0', '1530341213', '1530281040', '');
INSERT INTO `d_charge` VALUES ('408', 'device_test228', 'dd', 'dde', 'mac_test228', '1', '0', '1530341213', '1530281040', '');
INSERT INTO `d_charge` VALUES ('409', 'device_test227', 'dd', 'dde', 'mac_test227', '1', '0', '1530341213', '1530281040', '');
INSERT INTO `d_charge` VALUES ('410', 'device_test226', 'dd', 'dde', 'mac_test226', '1', '0', '1530341213', '1530281040', '');
INSERT INTO `d_charge` VALUES ('411', 'device_test225', 'dd', 'dde', 'mac_test225', '1', '0', '1530341213', '1530281040', '');
INSERT INTO `d_charge` VALUES ('412', 'device_test224', 'dd', 'dde', 'mac_test224', '2', '39', '1532333204', '1530281040', '');
INSERT INTO `d_charge` VALUES ('413', 'device_test223', 'dd', 'dde', 'mac_test223', '1', '0', '1530341213', '1530281040', '');
INSERT INTO `d_charge` VALUES ('414', 'device_test222', 'dd', 'dde', 'mac_test222', '1', '0', '1530341213', '1530281040', '');
INSERT INTO `d_charge` VALUES ('415', 'device_test221', 'dd', 'dde', 'mac_test221', '1', '0', '1530341213', '1530281040', '');
INSERT INTO `d_charge` VALUES ('416', 'device_test220', 'dd', 'dde', 'mac_test220', '1', '0', '1530341213', '1530281040', '');
INSERT INTO `d_charge` VALUES ('417', 'device_test219', 'dd', 'dde', 'mac_test219', '1', '0', '1530341213', '1530281040', '');
INSERT INTO `d_charge` VALUES ('418', 'device_test218', 'dd', 'dde', 'mac_test218', '1', '0', '1530341213', '1530281040', '');
INSERT INTO `d_charge` VALUES ('419', 'device_test217', 'dd', 'dde', 'mac_test217', '1', '0', '1530341213', '1530281040', '');
INSERT INTO `d_charge` VALUES ('420', 'device_test216', 'dd', 'dde', 'mac_test216', '1', '0', '1530341213', '1530281040', '');
INSERT INTO `d_charge` VALUES ('421', 'device_test215', 'dd', 'dde', 'mac_test215', '1', '0', '1530341213', '1530281040', '');
INSERT INTO `d_charge` VALUES ('422', 'device_test214', 'dd', 'dde', 'mac_test214', '1', '0', '1530341213', '1530281040', '');
INSERT INTO `d_charge` VALUES ('423', 'device_test213', 'dd', 'dde', 'mac_test213', '1', '0', '1530341213', '1530281040', '');
INSERT INTO `d_charge` VALUES ('424', 'device_test212', 'dd', 'dde', 'mac_test212', '1', '0', '1530341213', '1530281040', '');
INSERT INTO `d_charge` VALUES ('425', 'device_test211', 'dd', 'dde', 'mac_test211', '1', '0', '1530341213', '1530281040', '');
INSERT INTO `d_charge` VALUES ('426', 'device_test210', 'dd', 'dde', 'mac_test210', '1', '0', '1530341213', '1530281040', '');
INSERT INTO `d_charge` VALUES ('427', 'device_test209', 'dd', 'dde', 'mac_test209', '1', '0', '1530341213', '1530281040', '');
INSERT INTO `d_charge` VALUES ('428', 'device_test208', 'dd', 'dde', 'mac_test208', '1', '0', '1530341213', '1530281040', '');
INSERT INTO `d_charge` VALUES ('429', 'device_test207', 'dd', 'dde', 'mac_test207', '1', '0', '1530341213', '1530281040', '');
INSERT INTO `d_charge` VALUES ('430', 'device_test206', 'dd', 'dde', 'mac_test206', '1', '0', '1530341213', '1530281040', '');
INSERT INTO `d_charge` VALUES ('431', 'device_test205', 'dd', 'dde', 'mac_test205', '1', '0', '1530341213', '1530281040', '');
INSERT INTO `d_charge` VALUES ('432', 'device_test204', 'dd', 'dde', 'mac_test204', '1', '0', '1530341213', '1530281040', '');
INSERT INTO `d_charge` VALUES ('433', 'device_test203', 'dd', 'dde', 'mac_test203', '1', '0', '1530341213', '1530281040', '');
INSERT INTO `d_charge` VALUES ('434', 'device_test202', 'dd', 'dde', 'mac_test202', '1', '0', '1530341213', '1530281040', '');
INSERT INTO `d_charge` VALUES ('435', 'device_test201', 'dd', 'dde', 'mac_test201', '1', '0', '1530341213', '1530281040', '');
INSERT INTO `d_charge` VALUES ('436', 'device_test200', 'dd', 'dde', 'mac_test200', '2', '37', '1532327164', '1530281040', '');
INSERT INTO `d_charge` VALUES ('437', 'device_test199', 'dd', 'dde', 'mac_test199', '1', '0', '1530341213', '1530281040', '');
INSERT INTO `d_charge` VALUES ('438', 'device_test198', 'dd', 'dde', 'mac_test198', '1', '0', '1530341213', '1530281040', '');
INSERT INTO `d_charge` VALUES ('439', 'device_test197', 'dd', 'dde', 'mac_test197', '1', '0', '1530341213', '1530281040', '');
INSERT INTO `d_charge` VALUES ('440', 'device_test196', 'dd', 'dde', 'mac_test196', '1', '0', '1530341213', '1530281040', '');
INSERT INTO `d_charge` VALUES ('441', 'device_test195', 'dd', 'dde', 'mac_test195', '1', '0', '1530341213', '1530281040', '');
INSERT INTO `d_charge` VALUES ('442', 'device_test194', 'dd', 'dde', 'mac_test194', '1', '0', '1530341213', '1530281040', '');
INSERT INTO `d_charge` VALUES ('443', 'device_test193', 'dd', 'dde', 'mac_test193', '1', '0', '1530341213', '1530281040', '');
INSERT INTO `d_charge` VALUES ('444', 'device_test192', 'dd', 'dde', 'mac_test192', '1', '0', '1530341213', '1530281040', '');
INSERT INTO `d_charge` VALUES ('445', 'device_test191', 'dd', 'dde', 'mac_test191', '1', '0', '1530341213', '1530281040', '');
INSERT INTO `d_charge` VALUES ('446', 'device_test190', 'dd', 'dde', 'mac_test190', '1', '0', '1530341213', '1530281040', '');
INSERT INTO `d_charge` VALUES ('447', 'device_test189', 'dd', 'dde', 'mac_test189', '1', '0', '1530341213', '1530281040', '');
INSERT INTO `d_charge` VALUES ('448', 'device_test188', 'dd', 'dde', 'mac_test188', '1', '0', '1530341213', '1530281040', '');
INSERT INTO `d_charge` VALUES ('449', 'device_test187', 'dd', 'dde', 'mac_test187', '1', '0', '1530341213', '1530281040', '');
INSERT INTO `d_charge` VALUES ('450', 'device_test186', 'dd', 'dde', 'mac_test186', '1', '0', '1530341213', '1530281040', '');
INSERT INTO `d_charge` VALUES ('451', 'device_test185', 'dd', 'dde', 'mac_test185', '1', '0', '1530341213', '1530281040', '');
INSERT INTO `d_charge` VALUES ('452', 'device_test184', 'dd', 'dde', 'mac_test184', '1', '0', '1530341213', '1530281040', '');
INSERT INTO `d_charge` VALUES ('453', 'device_test183', 'dd', 'dde', 'mac_test183', '1', '0', '1530341213', '1530281040', '');
INSERT INTO `d_charge` VALUES ('454', 'device_test182', 'dd', 'dde', 'mac_test182', '1', '0', '1530341213', '1530281040', '');
INSERT INTO `d_charge` VALUES ('455', 'device_test181', 'dd', 'dde', 'mac_test181', '1', '0', '1530341213', '1530281040', '');
INSERT INTO `d_charge` VALUES ('456', 'device_test180', 'dd', 'dde', 'mac_test180', '1', '0', '1530341213', '1530281040', '');
INSERT INTO `d_charge` VALUES ('457', 'device_test179', 'dd', 'dde', 'mac_test179', '1', '0', '1530341213', '1530281040', '');
INSERT INTO `d_charge` VALUES ('458', 'device_test178', 'dd', 'dde', 'mac_test178', '1', '0', '1530341213', '1530281040', '');
INSERT INTO `d_charge` VALUES ('459', 'device_test177', 'dd', 'dde', 'mac_test177', '1', '0', '1530341213', '1530281040', '');
INSERT INTO `d_charge` VALUES ('460', 'device_test176', 'dd', 'dde', 'mac_test176', '1', '0', '1530341213', '1530281040', '');
INSERT INTO `d_charge` VALUES ('461', 'device_test175', 'dd', 'dde', 'mac_test175', '1', '0', '1530341213', '1530281040', '');
INSERT INTO `d_charge` VALUES ('462', 'device_test174', 'dd', 'dde', 'mac_test174', '1', '0', '1530341213', '1530281040', '');
INSERT INTO `d_charge` VALUES ('463', 'device_test173', 'dd', 'dde', 'mac_test173', '1', '0', '1530341213', '1530281040', '');
INSERT INTO `d_charge` VALUES ('464', 'device_test172', 'dd', 'dde', 'mac_test172', '1', '0', '1530341213', '1530281040', '');
INSERT INTO `d_charge` VALUES ('465', 'device_test171', 'dd', 'dde', 'mac_test171', '1', '0', '1530341213', '1530281040', '');
INSERT INTO `d_charge` VALUES ('466', 'device_test170', 'dd', 'dde', 'mac_test170', '1', '0', '1530341213', '1530281040', '');
INSERT INTO `d_charge` VALUES ('467', 'device_test169', 'dd', 'dde', 'mac_test169', '1', '0', '1530341213', '1530281040', '');
INSERT INTO `d_charge` VALUES ('468', 'device_test168', 'dd', 'dde', 'mac_test168', '1', '0', '1530341213', '1530281040', '');
INSERT INTO `d_charge` VALUES ('469', 'device_test167', 'dd', 'dde', 'mac_test167', '1', '0', '1530341213', '1530281040', '');
INSERT INTO `d_charge` VALUES ('470', 'device_test166', 'dd', 'dde', 'mac_test166', '1', '0', '1530341213', '1530281040', '');
INSERT INTO `d_charge` VALUES ('471', 'device_test165', 'dd', 'dde', 'mac_test165', '1', '0', '1530341213', '1530281040', '');
INSERT INTO `d_charge` VALUES ('472', 'device_test164', 'dd', 'dde', 'mac_test164', '1', '0', '1530341213', '1530281040', '');
INSERT INTO `d_charge` VALUES ('473', 'device_test163', 'dd', 'dde', 'mac_test163', '1', '0', '1530341213', '1530281040', '');
INSERT INTO `d_charge` VALUES ('474', 'device_test162', 'dd', 'dde', 'mac_test162', '1', '0', '1530341213', '1530281040', '');
INSERT INTO `d_charge` VALUES ('475', 'device_test161', 'dd', 'dde', 'mac_test161', '1', '0', '1530341213', '1530281040', '');
INSERT INTO `d_charge` VALUES ('476', 'device_test160', 'dd', 'dde', 'mac_test160', '1', '0', '1530341213', '1530281040', '');
INSERT INTO `d_charge` VALUES ('477', 'device_test159', 'dd', 'dde', 'mac_test159', '1', '0', '1530341213', '1530281040', '');
INSERT INTO `d_charge` VALUES ('478', 'device_test158', 'dd', 'dde', 'mac_test158', '1', '0', '1530341213', '1530281040', '');
INSERT INTO `d_charge` VALUES ('479', 'device_test157', 'dd', 'dde', 'mac_test157', '1', '0', '1530341213', '1530281040', '');
INSERT INTO `d_charge` VALUES ('480', 'device_test156', 'dd', 'dde', 'mac_test156', '1', '0', '1530341213', '1530281040', '');
INSERT INTO `d_charge` VALUES ('481', 'device_test155', 'dd', 'dde', 'mac_test155', '1', '0', '1530341213', '1530281040', '');
INSERT INTO `d_charge` VALUES ('482', 'device_test154', 'dd', 'dde', 'mac_test154', '1', '0', '1530341213', '1530281040', '');
INSERT INTO `d_charge` VALUES ('483', 'device_test153', 'dd', 'dde', 'mac_test153', '1', '0', '1530341213', '1530281040', '');
INSERT INTO `d_charge` VALUES ('484', 'device_test152', 'dd', 'dde', 'mac_test152', '1', '0', '1530341213', '1530281040', '');
INSERT INTO `d_charge` VALUES ('485', 'device_test151', 'dd', 'dde', 'mac_test151', '1', '0', '1530341213', '1530281040', '');
INSERT INTO `d_charge` VALUES ('486', 'device_test150', 'dd', 'dde', 'mac_test150', '1', '0', '1530341213', '1530281040', '');
INSERT INTO `d_charge` VALUES ('487', 'device_test149', 'dd', 'dde', 'mac_test149', '1', '0', '1530341213', '1530281040', '');
INSERT INTO `d_charge` VALUES ('488', 'device_test148', 'dd', 'dde', 'mac_test148', '1', '0', '1530341213', '1530281040', '');
INSERT INTO `d_charge` VALUES ('489', 'device_test147', 'dd', 'dde', 'mac_test147', '1', '0', '1530341213', '1530281040', '');
INSERT INTO `d_charge` VALUES ('490', 'device_test146', 'dd', 'dde', 'mac_test146', '1', '0', '1530341213', '1530281040', '');
INSERT INTO `d_charge` VALUES ('491', 'device_test145', 'dd', 'dde', 'mac_test145', '1', '0', '1530341213', '1530281040', '');
INSERT INTO `d_charge` VALUES ('492', 'device_test144', 'dd', 'dde', 'mac_test144', '1', '0', '1530341213', '1530281040', '');
INSERT INTO `d_charge` VALUES ('493', 'device_test143', 'dd', 'dde', 'mac_test143', '1', '0', '1530341213', '1530281040', '');
INSERT INTO `d_charge` VALUES ('494', 'device_test142', 'dd', 'dde', 'mac_test142', '1', '0', '1530341213', '1530281040', '');
INSERT INTO `d_charge` VALUES ('495', 'device_test141', 'dd', 'dde', 'mac_test141', '1', '0', '1530341213', '1530281040', '');
INSERT INTO `d_charge` VALUES ('496', 'device_test140', 'dd', 'dde', 'mac_test140', '1', '0', '1530341213', '1530281040', '');
INSERT INTO `d_charge` VALUES ('497', 'device_test139', 'dd', 'dde', 'mac_test139', '1', '0', '1530341213', '1530281040', '');
INSERT INTO `d_charge` VALUES ('498', 'device_test138', 'dd', 'dde', 'mac_test138', '1', '0', '1530341213', '1530281040', '');
INSERT INTO `d_charge` VALUES ('499', 'device_test137', 'dd', 'dde', 'mac_test137', '1', '0', '1530341213', '1530281040', '');
INSERT INTO `d_charge` VALUES ('500', 'device_test136', 'dd', 'dde', 'mac_test136', '1', '0', '1530341213', '1530281041', '');
INSERT INTO `d_charge` VALUES ('501', 'device_test135', 'dd', 'dde', 'mac_test135', '1', '0', '1530341213', '1530281041', '');
INSERT INTO `d_charge` VALUES ('502', 'device_test134', 'dd', 'dde', 'mac_test134', '1', '0', '1530341213', '1530281041', '');
INSERT INTO `d_charge` VALUES ('503', 'device_test133', 'dd', 'dde', 'mac_test133', '1', '0', '1530341213', '1530281041', '');
INSERT INTO `d_charge` VALUES ('504', 'device_test132', 'dd', 'dde', 'mac_test132', '1', '0', '1530341213', '1530281041', '');
INSERT INTO `d_charge` VALUES ('505', 'device_test131', 'dd', 'dde', 'mac_test131', '1', '0', '1530341213', '1530281041', '');
INSERT INTO `d_charge` VALUES ('506', 'device_test130', 'dd', 'dde', 'mac_test130', '1', '0', '1530341213', '1530281041', '');
INSERT INTO `d_charge` VALUES ('507', 'device_test129', 'dd', 'dde', 'mac_test129', '1', '0', '1530341213', '1530281041', '');
INSERT INTO `d_charge` VALUES ('508', 'device_test128', 'dd', 'dde', 'mac_test128', '1', '0', '1530341213', '1530281041', '');
INSERT INTO `d_charge` VALUES ('509', 'device_test127', 'dd', 'dde', 'mac_test127', '1', '0', '1530341213', '1530281041', '');
INSERT INTO `d_charge` VALUES ('510', 'device_test126', 'dd', 'dde', 'mac_test126', '1', '0', '1530341213', '1530281041', '');
INSERT INTO `d_charge` VALUES ('511', 'device_test125', 'dd', 'dde', 'mac_test125', '1', '0', '1530341213', '1530281041', '');
INSERT INTO `d_charge` VALUES ('512', 'device_test124', 'dd', 'dde', 'mac_test124', '1', '0', '1530341213', '1530281041', '');
INSERT INTO `d_charge` VALUES ('513', 'device_test123', 'dd', 'dde', 'mac_test123', '1', '0', '1530341213', '1530281041', '');
INSERT INTO `d_charge` VALUES ('514', 'device_test122', 'dd', 'dde', 'mac_test122', '1', '0', '1530341213', '1530281041', '');
INSERT INTO `d_charge` VALUES ('515', 'device_test121', 'dd', 'dde', 'mac_test121', '1', '0', '1530341213', '1530281041', '');
INSERT INTO `d_charge` VALUES ('516', 'device_test120', 'dd', 'dde', 'mac_test120', '1', '0', '1530341213', '1530281041', '');
INSERT INTO `d_charge` VALUES ('517', 'device_test119', 'dd', 'dde', 'mac_test119', '1', '0', '1530341213', '1530281041', '');
INSERT INTO `d_charge` VALUES ('518', 'device_test118', 'dd', 'dde', 'mac_test118', '1', '0', '1530341213', '1530281041', '');
INSERT INTO `d_charge` VALUES ('519', 'device_test117', 'dd', 'dde', 'mac_test117', '1', '0', '1530341213', '1530281041', '');
INSERT INTO `d_charge` VALUES ('520', 'device_test116', 'dd', 'dde', 'mac_test116', '1', '0', '1530341213', '1530281041', '');
INSERT INTO `d_charge` VALUES ('521', 'device_test115', 'dd', 'dde', 'mac_test115', '1', '0', '1530341213', '1530281041', '');
INSERT INTO `d_charge` VALUES ('522', 'device_test114', 'dd', 'dde', 'mac_test114', '1', '0', '1530341213', '1530281041', '');
INSERT INTO `d_charge` VALUES ('523', 'device_test113', 'dd', 'dde', 'mac_test113', '1', '0', '1530341213', '1530281041', '');
INSERT INTO `d_charge` VALUES ('524', 'device_test112', 'dd', 'dde', 'mac_test112', '1', '0', '1530341213', '1530281041', '');
INSERT INTO `d_charge` VALUES ('525', 'device_test111', 'dd', 'dde', 'mac_test111', '1', '0', '1530341213', '1530281041', '');
INSERT INTO `d_charge` VALUES ('526', 'device_test110', 'dd', 'dde', 'mac_test110', '1', '0', '1530341213', '1530281041', '');
INSERT INTO `d_charge` VALUES ('527', 'device_test109', 'dd', 'dde', 'mac_test109', '1', '0', '1530341213', '1530281041', '');
INSERT INTO `d_charge` VALUES ('528', 'device_test108', 'dd', 'dde', 'mac_test108', '1', '0', '1530341213', '1530281041', '');
INSERT INTO `d_charge` VALUES ('529', 'device_test107', 'dd', 'dde', 'mac_test107', '1', '0', '1530341213', '1530281041', '');
INSERT INTO `d_charge` VALUES ('530', 'device_test106', 'dd', 'dde', 'mac_test106', '1', '0', '1530341213', '1530281041', '');
INSERT INTO `d_charge` VALUES ('531', 'device_test105', 'dd', 'dde', 'mac_test105', '1', '0', '1530341213', '1530281041', '');
INSERT INTO `d_charge` VALUES ('532', 'device_test104', 'dd', 'dde', 'mac_test104', '1', '0', '1530341213', '1530281041', '');
INSERT INTO `d_charge` VALUES ('533', 'device_test103', 'dd', 'dde', 'mac_test103', '1', '0', '1530341213', '1530281041', '');
INSERT INTO `d_charge` VALUES ('534', 'device_test102', 'dd', 'dde', 'mac_test102', '1', '0', '1530341213', '1530281041', '');
INSERT INTO `d_charge` VALUES ('535', 'device_test101', 'dd', 'dde', 'mac_test101', '1', '0', '1530341213', '1530281041', '');
INSERT INTO `d_charge` VALUES ('536', 'device_test100', 'dd', 'dde', 'mac_test100', '1', '0', '1530341213', '1530281041', '');
INSERT INTO `d_charge` VALUES ('537', 'device_test99', 'dd', 'dde', 'mac_test99', '1', '0', '1530341213', '1530281041', '');
INSERT INTO `d_charge` VALUES ('538', 'device_test98', 'dd', 'dde', 'mac_test98', '1', '0', '1530341213', '1530281041', '');
INSERT INTO `d_charge` VALUES ('539', 'device_test97', 'dd', 'dde', 'mac_test97', '1', '0', '1530341213', '1530281041', '');
INSERT INTO `d_charge` VALUES ('540', 'device_test96', 'dd', 'dde', 'mac_test96', '1', '0', '1530341213', '1530281041', '');
INSERT INTO `d_charge` VALUES ('541', 'device_test95', 'dd', 'dde', 'mac_test95', '1', '0', '1530341213', '1530281041', '');
INSERT INTO `d_charge` VALUES ('542', 'device_test94', 'dd', 'dde', 'mac_test94', '1', '0', '1530341213', '1530281041', '');
INSERT INTO `d_charge` VALUES ('543', 'device_test93', 'dd', 'dde', 'mac_test93', '1', '0', '1530341213', '1530281041', '');
INSERT INTO `d_charge` VALUES ('544', 'device_test92', 'dd', 'dde', 'mac_test92', '1', '0', '1530341213', '1530281041', '');
INSERT INTO `d_charge` VALUES ('545', 'device_test91', 'dd', 'dde', 'mac_test91', '1', '0', '1530341213', '1530281041', '');
INSERT INTO `d_charge` VALUES ('546', 'device_test90', 'dd', 'dde', 'mac_test90', '1', '0', '1530341213', '1530281041', '');
INSERT INTO `d_charge` VALUES ('547', 'device_test89', 'dd', 'dde', 'mac_test89', '1', '0', '1530341213', '1530281041', '');
INSERT INTO `d_charge` VALUES ('548', 'device_test88', 'dd', 'dde', 'mac_test88', '2', '257', '1531130729', '1530281041', '');
INSERT INTO `d_charge` VALUES ('549', 'device_test87', 'dd', 'dde', 'mac_test87', '1', '0', '1530341213', '1530281041', '');
INSERT INTO `d_charge` VALUES ('550', 'device_test86', 'dd', 'dde', 'mac_test86', '2', '259', '1531131729', '1530281041', '');
INSERT INTO `d_charge` VALUES ('551', 'device_test85', 'dd', 'dde', 'mac_test85', '1', '0', '1530341213', '1530281041', '');
INSERT INTO `d_charge` VALUES ('552', 'device_test84', 'dd', 'dde', 'mac_test84', '1', '0', '1530341213', '1530281041', '');
INSERT INTO `d_charge` VALUES ('553', 'device_test83', 'dd', 'dde', 'mac_test83', '1', '0', '1530341213', '1530281041', '');
INSERT INTO `d_charge` VALUES ('554', 'device_test82', 'dd', 'dde', 'mac_test82', '1', '0', '1530341213', '1530281041', '');
INSERT INTO `d_charge` VALUES ('555', 'device_test81', 'dd', 'dde', 'mac_test81', '1', '0', '1530341213', '1530281041', '');
INSERT INTO `d_charge` VALUES ('556', 'device_test80', 'dd', 'dde', 'mac_test80', '1', '0', '1530341213', '1530281041', '');
INSERT INTO `d_charge` VALUES ('557', 'device_test79', 'dd', 'dde', 'mac_test79', '1', '0', '1530341213', '1530281041', '');
INSERT INTO `d_charge` VALUES ('558', 'device_test78', 'dd', 'dde', 'mac_test78', '1', '0', '1530341213', '1530281041', '');
INSERT INTO `d_charge` VALUES ('559', 'device_test77', 'dd', 'dde', 'mac_test77', '1', '0', '1530341213', '1530281041', '');
INSERT INTO `d_charge` VALUES ('560', 'device_test76', 'dd', 'dde', 'mac_test76', '1', '0', '1530341213', '1530281041', '');
INSERT INTO `d_charge` VALUES ('561', 'device_test75', 'dd', 'dde', 'mac_test75', '1', '0', '1530341213', '1530281041', '');
INSERT INTO `d_charge` VALUES ('562', 'device_test74', 'dd', 'dde', 'mac_test74', '1', '0', '1530341213', '1530281041', '');
INSERT INTO `d_charge` VALUES ('563', 'device_test73', 'dd', 'dde', 'mac_test73', '1', '0', '1530341213', '1530281041', '');
INSERT INTO `d_charge` VALUES ('564', 'device_test72', 'dd', 'dde', 'mac_test72', '1', '0', '1530341213', '1530281041', '');
INSERT INTO `d_charge` VALUES ('565', 'device_test71', 'dd', 'dde', 'mac_test71', '1', '0', '1530341213', '1530281041', '');
INSERT INTO `d_charge` VALUES ('566', 'device_test70', 'dd', 'dde', 'mac_test70', '1', '0', '1530341213', '1530281041', '');
INSERT INTO `d_charge` VALUES ('567', 'device_test69', 'dd', 'dde', 'mac_test69', '1', '0', '1530341213', '1530281041', '');
INSERT INTO `d_charge` VALUES ('568', 'device_test68', 'dd', 'dde', 'mac_test68', '1', '0', '1530341213', '1530281041', '');
INSERT INTO `d_charge` VALUES ('569', 'device_test67', 'dd', 'dde', 'mac_test67', '1', '0', '1530341213', '1530281041', '');
INSERT INTO `d_charge` VALUES ('570', 'device_test66', 'dd', 'dde', 'mac_test66', '1', '0', '1530341213', '1530281041', '');
INSERT INTO `d_charge` VALUES ('571', 'device_test65', 'dd', 'dde', 'mac_test65', '1', '0', '1530341213', '1530281041', '');
INSERT INTO `d_charge` VALUES ('572', 'device_test64', 'dd', 'dde', 'mac_test64', '1', '0', '1530341213', '1530281041', '');
INSERT INTO `d_charge` VALUES ('573', 'device_test63', 'dd', 'dde', 'mac_test63', '1', '0', '1530341213', '1530281041', '');
INSERT INTO `d_charge` VALUES ('574', 'device_test62', 'dd', 'dde', 'mac_test62', '1', '0', '1530341213', '1530281041', '');
INSERT INTO `d_charge` VALUES ('575', 'device_test61', 'dd', 'dde', 'mac_test61', '1', '0', '1530341213', '1530281041', '');
INSERT INTO `d_charge` VALUES ('576', 'device_test60', 'dd', 'dde', 'mac_test60', '1', '0', '1530341213', '1530281041', '');
INSERT INTO `d_charge` VALUES ('577', 'device_test59', 'dd', 'dde', 'mac_test59', '1', '0', '1530341213', '1530281041', '');
INSERT INTO `d_charge` VALUES ('578', 'device_test58', 'dd', 'dde', 'mac_test58', '1', '0', '1530341213', '1530281041', '');
INSERT INTO `d_charge` VALUES ('579', 'device_test57', 'dd', 'dde', 'mac_test57', '1', '0', '1530341213', '1530281041', '');
INSERT INTO `d_charge` VALUES ('580', 'device_test56', 'dd', 'dde', 'mac_test56', '1', '0', '1530341213', '1530281041', '');
INSERT INTO `d_charge` VALUES ('581', 'device_test55', 'dd', 'dde', 'mac_test55', '1', '0', '1530341213', '1530281041', '');
INSERT INTO `d_charge` VALUES ('582', 'device_test54', 'dd', 'dde', 'mac_test54', '1', '0', '1530341213', '1530281041', '');
INSERT INTO `d_charge` VALUES ('583', 'device_test53', 'dd', 'dde', 'mac_test53', '1', '0', '1530341213', '1530281041', '');
INSERT INTO `d_charge` VALUES ('584', 'device_test52', 'dd', 'dde', 'mac_test52', '1', '0', '1530341213', '1530281041', '');
INSERT INTO `d_charge` VALUES ('585', 'device_test51', 'dd', 'dde', 'mac_test51', '1', '0', '1530341213', '1530281041', '');
INSERT INTO `d_charge` VALUES ('586', 'device_test50', 'dd', 'dde', 'mac_test50', '1', '0', '1530341213', '1530281041', '');
INSERT INTO `d_charge` VALUES ('587', 'device_test49', 'dd', 'dde', 'mac_test49', '1', '0', '1530341213', '1530281041', '');
INSERT INTO `d_charge` VALUES ('588', 'device_test48', 'dd', 'dde', 'mac_test48', '1', '0', '1530341213', '1530281041', '');
INSERT INTO `d_charge` VALUES ('589', 'device_test47', 'dd', 'dde', 'mac_test47', '1', '0', '1530341213', '1530281041', '');
INSERT INTO `d_charge` VALUES ('590', 'device_test46', 'dd', 'dde', 'mac_test46', '1', '0', '1530341213', '1530281041', '');
INSERT INTO `d_charge` VALUES ('591', 'device_test45', 'dd', 'dde', 'mac_test45', '1', '0', '1530341213', '1530281041', '');
INSERT INTO `d_charge` VALUES ('592', 'device_test44', 'dd', 'dde', 'mac_test44', '1', '0', '1530341213', '1530281041', '');
INSERT INTO `d_charge` VALUES ('593', 'device_test43', 'dd', 'dde', 'mac_test43', '1', '0', '1530341213', '1530281041', '');
INSERT INTO `d_charge` VALUES ('594', 'device_test42', 'dd', 'dde', 'mac_test42', '1', '0', '1530341213', '1530281041', '');
INSERT INTO `d_charge` VALUES ('595', 'device_test41', 'dd', 'dde', 'mac_test41', '1', '0', '1530341213', '1530281041', '');
INSERT INTO `d_charge` VALUES ('596', 'device_test40', 'dd', 'dde', 'mac_test40', '1', '0', '1530341213', '1530281041', '');
INSERT INTO `d_charge` VALUES ('597', 'device_test39', 'dd', 'dde', 'mac_test39', '1', '0', '1530341213', '1530281041', '');
INSERT INTO `d_charge` VALUES ('598', 'device_test38', 'dd', 'dde', 'mac_test38', '1', '0', '1530341213', '1530281041', '');
INSERT INTO `d_charge` VALUES ('599', 'device_test37', 'dd', 'dde', 'mac_test37', '1', '0', '1530341213', '1530281041', '');
INSERT INTO `d_charge` VALUES ('600', 'device_test36', 'dd', 'dde', 'mac_test36', '1', '0', '1530341213', '1530281041', '');
INSERT INTO `d_charge` VALUES ('601', 'device_test35', 'dd', 'dde', 'mac_test35', '1', '0', '1530341213', '1530281041', '');
INSERT INTO `d_charge` VALUES ('602', 'device_test34', 'dd', 'dde', 'mac_test34', '1', '0', '1530341213', '1530281041', '');
INSERT INTO `d_charge` VALUES ('603', 'device_test33', 'dd', 'dde', 'mac_test33', '1', '0', '1530341213', '1530281041', '');
INSERT INTO `d_charge` VALUES ('604', 'device_test32', 'dd', 'dde', 'mac_test32', '1', '0', '1530341213', '1530281041', '');
INSERT INTO `d_charge` VALUES ('605', 'device_test31', 'dd', 'dde', 'mac_test31', '1', '0', '1530341213', '1530281041', '');
INSERT INTO `d_charge` VALUES ('606', 'device_test30', 'dd', 'dde', 'mac_test30', '1', '0', '1530341213', '1530281041', '');
INSERT INTO `d_charge` VALUES ('607', 'device_test29', 'dd', 'dde', 'mac_test29', '1', '0', '1530341213', '1530281041', '');
INSERT INTO `d_charge` VALUES ('608', 'device_test28', 'dd', 'dde', 'mac_test28', '1', '0', '1530341213', '1530281041', '');
INSERT INTO `d_charge` VALUES ('609', 'device_test27', 'dd', 'dde', 'mac_test27', '1', '0', '1530341213', '1530281041', '');
INSERT INTO `d_charge` VALUES ('610', 'device_test26', 'dd', 'dde', 'mac_test26', '1', '0', '1530341213', '1530281041', '');
INSERT INTO `d_charge` VALUES ('611', 'device_test25', 'dd', 'dde', 'mac_test25', '1', '0', '1530341213', '1530281041', '');
INSERT INTO `d_charge` VALUES ('612', 'device_test24', 'dd', 'dde', 'mac_test24', '1', '0', '1530341213', '1530281041', '');
INSERT INTO `d_charge` VALUES ('613', 'device_test23', 'dd', 'dde', 'mac_test23', '1', '0', '1530341213', '1530281041', '');
INSERT INTO `d_charge` VALUES ('614', 'device_test22', 'dd', 'dde', 'mac_test22', '1', '0', '1530341213', '1530281041', '');
INSERT INTO `d_charge` VALUES ('615', 'device_test21', 'dd', 'dde', 'mac_test21', '1', '0', '1530341213', '1530281041', '');
INSERT INTO `d_charge` VALUES ('616', 'device_test20', 'dd', 'dde', 'mac_test20', '1', '0', '1530341213', '1530281041', '');
INSERT INTO `d_charge` VALUES ('617', 'device_test19', 'dd', 'dde', 'mac_test19', '1', '0', '1530341213', '1530281041', '');
INSERT INTO `d_charge` VALUES ('618', 'device_test18', 'dd', 'dde', 'mac_test18', '1', '0', '1530341213', '1530281041', '');
INSERT INTO `d_charge` VALUES ('619', 'device_test17', 'dd', 'dde', 'mac_test17', '1', '0', '1530341213', '1530281041', '');
INSERT INTO `d_charge` VALUES ('620', 'device_test16', 'dd', 'dde', 'mac_test16', '1', '0', '1530341213', '1530281041', '');
INSERT INTO `d_charge` VALUES ('621', 'device_test15', 'dd', 'dde', 'mac_test15', '1', '0', '1530341213', '1530281041', '');
INSERT INTO `d_charge` VALUES ('622', 'device_test14', 'dd', 'dde', 'mac_test14', '1', '0', '1530341213', '1530281041', '');
INSERT INTO `d_charge` VALUES ('623', 'device_test13', 'dd', 'dde', 'mac_test13', '1', '0', '1530341213', '1530281041', '');
INSERT INTO `d_charge` VALUES ('624', 'device_test12', 'dd', 'dde', 'mac_test12', '1', '0', '1530341213', '1530281041', '');
INSERT INTO `d_charge` VALUES ('625', 'device_test11', 'dd', 'dde', 'mac_test11', '1', '0', '1530341213', '1530281041', '');
INSERT INTO `d_charge` VALUES ('626', 'device_test10', 'dd', 'dde', 'mac_test10', '1', '0', '1530341213', '1530281041', '');
INSERT INTO `d_charge` VALUES ('627', 'device_test9', 'dd', 'dde', 'mac_test9', '1', '0', '1530341213', '1530281041', '');
INSERT INTO `d_charge` VALUES ('628', 'device_test8', 'dd', 'dde', 'mac_test8', '1', '0', '1530341213', '1530281041', '');
INSERT INTO `d_charge` VALUES ('629', 'device_test7', 'dd', 'dde', 'mac_test7', '1', '0', '1530341213', '1530281041', '');
INSERT INTO `d_charge` VALUES ('630', 'device_test6', 'dd', 'dde', 'mac_test6', '1', '0', '1530341213', '1530281041', '');
INSERT INTO `d_charge` VALUES ('631', 'device_test5', 'dd', 'dde', 'mac_test5', '1', '0', '1530341213', '1530281041', '');
INSERT INTO `d_charge` VALUES ('632', 'device_test4', 'dd', 'dde', 'mac_test4', '1', '0', '1530341213', '1530281041', '');
INSERT INTO `d_charge` VALUES ('633', 'device_test3', 'dd', 'dde', 'mac_test3', '1', '0', '1530341213', '1530281041', '');
INSERT INTO `d_charge` VALUES ('634', 'device_test2', 'dd', 'dde', 'mac_test2', '1', '0', '1530341213', '1530281041', '');
INSERT INTO `d_charge` VALUES ('635', 'device_test1', 'dd', 'dde', 'mac_test1', '1', '0', '1530341213', '1530281041', '');
INSERT INTO `d_charge` VALUES ('636', 'device_a500', 'dd', 'dde', 'mac_a500', '2', '1', '1532423832', '1530281066', '');
INSERT INTO `d_charge` VALUES ('637', 'device_a499', 'dd', 'dde', 'mac_a499', '1', '0', '1530341213', '1530281066', '');
INSERT INTO `d_charge` VALUES ('638', 'device_a498', 'dd', 'dde', 'mac_a498', '1', '0', '1530341213', '1530281066', '');
INSERT INTO `d_charge` VALUES ('639', 'device_a497', 'dd', 'dde', 'mac_a497', '1', '0', '1530341213', '1530281066', '');
INSERT INTO `d_charge` VALUES ('640', 'device_a496', 'dd', 'dde', 'mac_a496', '1', '0', '1530341213', '1530281066', '');
INSERT INTO `d_charge` VALUES ('641', 'device_a495', 'dd', 'dde', 'mac_a495', '1', '0', '1530341213', '1530281066', '');
INSERT INTO `d_charge` VALUES ('642', 'device_a494', 'dd', 'dde', 'mac_a494', '1', '0', '1530341213', '1530281066', '');
INSERT INTO `d_charge` VALUES ('643', 'device_a493', 'dd', 'dde', 'mac_a493', '1', '0', '1530341213', '1530281066', '');
INSERT INTO `d_charge` VALUES ('644', 'device_a492', 'dd', 'dde', 'mac_a492', '1', '0', '1530341213', '1530281066', '');
INSERT INTO `d_charge` VALUES ('645', 'device_a491', 'dd', 'dde', 'mac_a491', '1', '0', '1530341213', '1530281066', '');
INSERT INTO `d_charge` VALUES ('646', 'device_a490', 'dd', 'dde', 'mac_a490', '1', '0', '1530341213', '1530281066', '');
INSERT INTO `d_charge` VALUES ('647', 'device_a489', 'dd', 'dde', 'mac_a489', '1', '0', '1530341213', '1530281066', '');
INSERT INTO `d_charge` VALUES ('648', 'device_a488', 'dd', 'dde', 'mac_a488', '1', '0', '1530341213', '1530281066', '');
INSERT INTO `d_charge` VALUES ('649', 'device_a487', 'dd', 'dde', 'mac_a487', '1', '0', '1530341213', '1530281066', '');
INSERT INTO `d_charge` VALUES ('650', 'device_a486', 'dd', 'dde', 'mac_a486', '1', '0', '1530341213', '1530281066', '');
INSERT INTO `d_charge` VALUES ('651', 'device_a485', 'dd', 'dde', 'mac_a485', '1', '0', '1530341213', '1530281066', '');
INSERT INTO `d_charge` VALUES ('652', 'device_a484', 'dd', 'dde', 'mac_a484', '1', '0', '1530341213', '1530281066', '');
INSERT INTO `d_charge` VALUES ('653', 'device_a483', 'dd', 'dde', 'mac_a483', '1', '0', '1530341213', '1530281066', '');
INSERT INTO `d_charge` VALUES ('654', 'device_a482', 'dd', 'dde', 'mac_a482', '1', '0', '1530341213', '1530281066', '');
INSERT INTO `d_charge` VALUES ('655', 'device_a481', 'dd', 'dde', 'mac_a481', '1', '0', '1530341213', '1530281066', '');
INSERT INTO `d_charge` VALUES ('656', 'device_a480', 'dd', 'dde', 'mac_a480', '1', '0', '1530341213', '1530281066', '');
INSERT INTO `d_charge` VALUES ('657', 'device_a479', 'dd', 'dde', 'mac_a479', '1', '0', '1530341213', '1530281066', '');
INSERT INTO `d_charge` VALUES ('658', 'device_a478', 'dd', 'dde', 'mac_a478', '1', '0', '1530341213', '1530281066', '');
INSERT INTO `d_charge` VALUES ('659', 'device_a477', 'dd', 'dde', 'mac_a477', '1', '0', '1530341213', '1530281066', '');
INSERT INTO `d_charge` VALUES ('660', 'device_a476', 'dd', 'dde', 'mac_a476', '1', '0', '1530341213', '1530281066', '');
INSERT INTO `d_charge` VALUES ('661', 'device_a475', 'dd', 'dde', 'mac_a475', '1', '0', '1530341213', '1530281066', '');
INSERT INTO `d_charge` VALUES ('662', 'device_a474', 'dd', 'dde', 'mac_a474', '1', '0', '1530341213', '1530281066', '');
INSERT INTO `d_charge` VALUES ('663', 'device_a473', 'dd', 'dde', 'mac_a473', '1', '0', '1530341213', '1530281066', '');
INSERT INTO `d_charge` VALUES ('664', 'device_a472', 'dd', 'dde', 'mac_a472', '1', '0', '1530341213', '1530281066', '');
INSERT INTO `d_charge` VALUES ('665', 'device_a471', 'dd', 'dde', 'mac_a471', '1', '0', '1530341213', '1530281066', '');
INSERT INTO `d_charge` VALUES ('666', 'device_a470', 'dd', 'dde', 'mac_a470', '1', '0', '1530341213', '1530281066', '');
INSERT INTO `d_charge` VALUES ('667', 'device_a469', 'dd', 'dde', 'mac_a469', '1', '0', '1530341213', '1530281066', '');
INSERT INTO `d_charge` VALUES ('668', 'device_a468', 'dd', 'dde', 'mac_a468', '1', '0', '1530341213', '1530281066', '');
INSERT INTO `d_charge` VALUES ('669', 'device_a467', 'dd', 'dde', 'mac_a467', '1', '0', '1530341213', '1530281066', '');
INSERT INTO `d_charge` VALUES ('670', 'device_a466', 'dd', 'dde', 'mac_a466', '1', '0', '1530341213', '1530281066', '');
INSERT INTO `d_charge` VALUES ('671', 'device_a465', 'dd', 'dde', 'mac_a465', '1', '0', '1530341213', '1530281066', '');
INSERT INTO `d_charge` VALUES ('672', 'device_a464', 'dd', 'dde', 'mac_a464', '1', '0', '1530341213', '1530281066', '');
INSERT INTO `d_charge` VALUES ('673', 'device_a463', 'dd', 'dde', 'mac_a463', '1', '0', '1530341213', '1530281066', '');
INSERT INTO `d_charge` VALUES ('674', 'device_a462', 'dd', 'dde', 'mac_a462', '1', '0', '1530341213', '1530281066', '');
INSERT INTO `d_charge` VALUES ('675', 'device_a461', 'dd', 'dde', 'mac_a461', '1', '0', '1530341213', '1530281066', '');
INSERT INTO `d_charge` VALUES ('676', 'device_a460', 'dd', 'dde', 'mac_a460', '1', '0', '1530341213', '1530281066', '');
INSERT INTO `d_charge` VALUES ('677', 'device_a459', 'dd', 'dde', 'mac_a459', '1', '0', '1530341213', '1530281066', '');
INSERT INTO `d_charge` VALUES ('678', 'device_a458', 'dd', 'dde', 'mac_a458', '1', '0', '1530341213', '1530281066', '');
INSERT INTO `d_charge` VALUES ('679', 'device_a457', 'dd', 'dde', 'mac_a457', '1', '0', '1530341213', '1530281066', '');
INSERT INTO `d_charge` VALUES ('680', 'device_a456', 'dd', 'dde', 'mac_a456', '1', '0', '1530341213', '1530281066', '');
INSERT INTO `d_charge` VALUES ('681', 'device_a455', 'dd', 'dde', 'mac_a455', '1', '0', '1530341213', '1530281066', '');
INSERT INTO `d_charge` VALUES ('682', 'device_a454', 'dd', 'dde', 'mac_a454', '1', '0', '1530341213', '1530281066', '');
INSERT INTO `d_charge` VALUES ('683', 'device_a453', 'dd', 'dde', 'mac_a453', '1', '0', '1530341213', '1530281066', '');
INSERT INTO `d_charge` VALUES ('684', 'device_a452', 'dd', 'dde', 'mac_a452', '1', '0', '1530341213', '1530281066', '');
INSERT INTO `d_charge` VALUES ('685', 'device_a451', 'dd', 'dde', 'mac_a451', '1', '0', '1530341213', '1530281066', '');
INSERT INTO `d_charge` VALUES ('686', 'device_a450', 'dd', 'dde', 'mac_a450', '1', '0', '1530341213', '1530281066', '');
INSERT INTO `d_charge` VALUES ('687', 'device_a449', 'dd', 'dde', 'mac_a449', '1', '0', '1530341213', '1530281066', '');
INSERT INTO `d_charge` VALUES ('688', 'device_a448', 'dd', 'dde', 'mac_a448', '1', '0', '1530341213', '1530281066', '');
INSERT INTO `d_charge` VALUES ('689', 'device_a447', 'dd', 'dde', 'mac_a447', '1', '0', '1530341213', '1530281066', '');
INSERT INTO `d_charge` VALUES ('690', 'device_a446', 'dd', 'dde', 'mac_a446', '1', '0', '1530341213', '1530281066', '');
INSERT INTO `d_charge` VALUES ('691', 'device_a445', 'dd', 'dde', 'mac_a445', '1', '0', '1530341213', '1530281066', '');
INSERT INTO `d_charge` VALUES ('692', 'device_a444', 'dd', 'dde', 'mac_a444', '1', '0', '1530341213', '1530281066', '');
INSERT INTO `d_charge` VALUES ('693', 'device_a443', 'dd', 'dde', 'mac_a443', '1', '0', '1530341213', '1530281066', '');
INSERT INTO `d_charge` VALUES ('694', 'device_a442', 'dd', 'dde', 'mac_a442', '1', '0', '1530341213', '1530281066', '');
INSERT INTO `d_charge` VALUES ('695', 'device_a441', 'dd', 'dde', 'mac_a441', '1', '0', '1530341213', '1530281066', '');
INSERT INTO `d_charge` VALUES ('696', 'device_a440', 'dd', 'dde', 'mac_a440', '1', '0', '1530341213', '1530281066', '');
INSERT INTO `d_charge` VALUES ('697', 'device_a439', 'dd', 'dde', 'mac_a439', '1', '0', '1530341213', '1530281066', '');
INSERT INTO `d_charge` VALUES ('698', 'device_a438', 'dd', 'dde', 'mac_a438', '1', '0', '1530341213', '1530281066', '');
INSERT INTO `d_charge` VALUES ('699', 'device_a437', 'dd', 'dde', 'mac_a437', '1', '0', '1530341213', '1530281066', '');
INSERT INTO `d_charge` VALUES ('700', 'device_a436', 'dd', 'dde', 'mac_a436', '1', '0', '1530341213', '1530281066', '');
INSERT INTO `d_charge` VALUES ('701', 'device_a435', 'dd', 'dde', 'mac_a435', '1', '0', '1530341213', '1530281066', '');
INSERT INTO `d_charge` VALUES ('702', 'device_a434', 'dd', 'dde', 'mac_a434', '1', '0', '1530341213', '1530281066', '');
INSERT INTO `d_charge` VALUES ('703', 'device_a433', 'dd', 'dde', 'mac_a433', '1', '0', '1530341213', '1530281066', '');
INSERT INTO `d_charge` VALUES ('704', 'device_a432', 'dd', 'dde', 'mac_a432', '1', '0', '1530341213', '1530281066', '');
INSERT INTO `d_charge` VALUES ('705', 'device_a431', 'dd', 'dde', 'mac_a431', '1', '0', '1530341213', '1530281066', '');
INSERT INTO `d_charge` VALUES ('706', 'device_a430', 'dd', 'dde', 'mac_a430', '1', '0', '1530341213', '1530281066', '');
INSERT INTO `d_charge` VALUES ('707', 'device_a429', 'dd', 'dde', 'mac_a429', '1', '0', '1530341213', '1530281066', '');
INSERT INTO `d_charge` VALUES ('708', 'device_a428', 'dd', 'dde', 'mac_a428', '1', '0', '1530341213', '1530281066', '');
INSERT INTO `d_charge` VALUES ('709', 'device_a427', 'dd', 'dde', 'mac_a427', '1', '0', '1530341213', '1530281066', '');
INSERT INTO `d_charge` VALUES ('710', 'device_a426', 'dd', 'dde', 'mac_a426', '1', '0', '1530341213', '1530281066', '');
INSERT INTO `d_charge` VALUES ('711', 'device_a425', 'dd', 'dde', 'mac_a425', '1', '0', '1530341213', '1530281066', '');
INSERT INTO `d_charge` VALUES ('712', 'device_a424', 'dd', 'dde', 'mac_a424', '1', '0', '1530341213', '1530281066', '');
INSERT INTO `d_charge` VALUES ('713', 'device_a423', 'dd', 'dde', 'mac_a423', '1', '0', '1530341213', '1530281066', '');
INSERT INTO `d_charge` VALUES ('714', 'device_a422', 'dd', 'dde', 'mac_a422', '1', '0', '1530341213', '1530281066', '');
INSERT INTO `d_charge` VALUES ('715', 'device_a421', 'dd', 'dde', 'mac_a421', '1', '0', '1530341213', '1530281066', '');
INSERT INTO `d_charge` VALUES ('716', 'device_a420', 'dd', 'dde', 'mac_a420', '1', '0', '1530341213', '1530281066', '');
INSERT INTO `d_charge` VALUES ('717', 'device_a419', 'dd', 'dde', 'mac_a419', '1', '0', '1530341213', '1530281066', '');
INSERT INTO `d_charge` VALUES ('718', 'device_a418', 'dd', 'dde', 'mac_a418', '1', '0', '1530341213', '1530281066', '');
INSERT INTO `d_charge` VALUES ('719', 'device_a417', 'dd', 'dde', 'mac_a417', '1', '0', '1530341213', '1530281066', '');
INSERT INTO `d_charge` VALUES ('720', 'device_a416', 'dd', 'dde', 'mac_a416', '1', '0', '1530341213', '1530281066', '');
INSERT INTO `d_charge` VALUES ('721', 'device_a415', 'dd', 'dde', 'mac_a415', '1', '0', '1530341213', '1530281066', '');
INSERT INTO `d_charge` VALUES ('722', 'device_a414', 'dd', 'dde', 'mac_a414', '1', '0', '1530341213', '1530281066', '');
INSERT INTO `d_charge` VALUES ('723', 'device_a413', 'dd', 'dde', 'mac_a413', '1', '0', '1530341213', '1530281066', '');
INSERT INTO `d_charge` VALUES ('724', 'device_a412', 'dd', 'dde', 'mac_a412', '1', '0', '1530341213', '1530281066', '');
INSERT INTO `d_charge` VALUES ('725', 'device_a411', 'dd', 'dde', 'mac_a411', '1', '0', '1530341213', '1530281066', '');
INSERT INTO `d_charge` VALUES ('726', 'device_a410', 'dd', 'dde', 'mac_a410', '1', '0', '1530341213', '1530281066', '');
INSERT INTO `d_charge` VALUES ('727', 'device_a409', 'dd', 'dde', 'mac_a409', '1', '0', '1530341213', '1530281066', '');
INSERT INTO `d_charge` VALUES ('728', 'device_a408', 'dd', 'dde', 'mac_a408', '1', '0', '1530341213', '1530281066', '');
INSERT INTO `d_charge` VALUES ('729', 'device_a407', 'dd', 'dde', 'mac_a407', '1', '0', '1530341213', '1530281066', '');
INSERT INTO `d_charge` VALUES ('730', 'device_a406', 'dd', 'dde', 'mac_a406', '1', '0', '1530341213', '1530281066', '');
INSERT INTO `d_charge` VALUES ('731', 'device_a405', 'dd', 'dde', 'mac_a405', '1', '0', '1530341213', '1530281066', '');
INSERT INTO `d_charge` VALUES ('732', 'device_a404', 'dd', 'dde', 'mac_a404', '1', '0', '1530341213', '1530281066', '');
INSERT INTO `d_charge` VALUES ('733', 'device_a403', 'dd', 'dde', 'mac_a403', '1', '0', '1530341213', '1530281066', '');
INSERT INTO `d_charge` VALUES ('734', 'device_a402', 'dd', 'dde', 'mac_a402', '1', '0', '1530341213', '1530281066', '');
INSERT INTO `d_charge` VALUES ('735', 'device_a401', 'dd', 'dde', 'mac_a401', '1', '0', '1530341213', '1530281067', '');
INSERT INTO `d_charge` VALUES ('736', 'device_a400', 'dd', 'dde', 'mac_a400', '1', '0', '1530341213', '1530281067', '');
INSERT INTO `d_charge` VALUES ('737', 'device_a399', 'dd', 'dde', 'mac_a399', '1', '0', '1530341213', '1530281067', '');
INSERT INTO `d_charge` VALUES ('738', 'device_a398', 'dd', 'dde', 'mac_a398', '1', '0', '1530341213', '1530281067', '');
INSERT INTO `d_charge` VALUES ('739', 'device_a397', 'dd', 'dde', 'mac_a397', '1', '0', '1530341213', '1530281067', '');
INSERT INTO `d_charge` VALUES ('740', 'device_a396', 'dd', 'dde', 'mac_a396', '1', '0', '1530341213', '1530281067', '');
INSERT INTO `d_charge` VALUES ('741', 'device_a395', 'dd', 'dde', 'mac_a395', '1', '0', '1530341213', '1530281067', '');
INSERT INTO `d_charge` VALUES ('742', 'device_a394', 'dd', 'dde', 'mac_a394', '1', '0', '1530341213', '1530281067', '');
INSERT INTO `d_charge` VALUES ('743', 'device_a393', 'dd', 'dde', 'mac_a393', '1', '0', '1530341213', '1530281067', '');
INSERT INTO `d_charge` VALUES ('744', 'device_a392', 'dd', 'dde', 'mac_a392', '1', '0', '1530341213', '1530281067', '');
INSERT INTO `d_charge` VALUES ('745', 'device_a391', 'dd', 'dde', 'mac_a391', '1', '0', '1530341213', '1530281067', '');
INSERT INTO `d_charge` VALUES ('746', 'device_a390', 'dd', 'dde', 'mac_a390', '1', '0', '1530341213', '1530281067', '');
INSERT INTO `d_charge` VALUES ('747', 'device_a389', 'dd', 'dde', 'mac_a389', '1', '0', '1530341213', '1530281067', '');
INSERT INTO `d_charge` VALUES ('748', 'device_a388', 'dd', 'dde', 'mac_a388', '1', '0', '1530341213', '1530281067', '');
INSERT INTO `d_charge` VALUES ('749', 'device_a387', 'dd', 'dde', 'mac_a387', '1', '0', '1530341213', '1530281067', '');
INSERT INTO `d_charge` VALUES ('750', 'device_a386', 'dd', 'dde', 'mac_a386', '1', '0', '1530341213', '1530281067', '');
INSERT INTO `d_charge` VALUES ('751', 'device_a385', 'dd', 'dde', 'mac_a385', '1', '0', '1530341213', '1530281067', '');
INSERT INTO `d_charge` VALUES ('752', 'device_a384', 'dd', 'dde', 'mac_a384', '1', '0', '1530341213', '1530281067', '');
INSERT INTO `d_charge` VALUES ('753', 'device_a383', 'dd', 'dde', 'mac_a383', '1', '0', '1530341213', '1530281067', '');
INSERT INTO `d_charge` VALUES ('754', 'device_a382', 'dd', 'dde', 'mac_a382', '1', '0', '1530341213', '1530281067', '');
INSERT INTO `d_charge` VALUES ('755', 'device_a381', 'dd', 'dde', 'mac_a381', '1', '0', '1530341213', '1530281067', '');
INSERT INTO `d_charge` VALUES ('756', 'device_a380', 'dd', 'dde', 'mac_a380', '1', '0', '1530341213', '1530281067', '');
INSERT INTO `d_charge` VALUES ('757', 'device_a379', 'dd', 'dde', 'mac_a379', '2', '369', '1531391944', '1530281067', '');
INSERT INTO `d_charge` VALUES ('758', 'device_a378', 'dd', 'dde', 'mac_a378', '1', '0', '1530341213', '1530281067', '');
INSERT INTO `d_charge` VALUES ('759', 'device_a377', 'dd', 'dde', 'mac_a377', '1', '0', '1530341213', '1530281067', '');
INSERT INTO `d_charge` VALUES ('760', 'device_a376', 'dd', 'dde', 'mac_a376', '1', '0', '1530341213', '1530281067', '');
INSERT INTO `d_charge` VALUES ('761', 'device_a375', 'dd', 'dde', 'mac_a375', '1', '0', '1530341213', '1530281067', '');
INSERT INTO `d_charge` VALUES ('762', 'device_a374', 'dd', 'dde', 'mac_a374', '1', '0', '1530341213', '1530281067', '');
INSERT INTO `d_charge` VALUES ('763', 'device_a373', 'dd', 'dde', 'mac_a373', '1', '0', '1530341213', '1530281067', '');
INSERT INTO `d_charge` VALUES ('764', 'device_a372', 'dd', 'dde', 'mac_a372', '1', '0', '1530341213', '1530281067', '');
INSERT INTO `d_charge` VALUES ('765', 'device_a371', 'dd', 'dde', 'mac_a371', '1', '0', '1530341213', '1530281067', '');
INSERT INTO `d_charge` VALUES ('766', 'device_a370', 'dd', 'dde', 'mac_a370', '1', '0', '1530341213', '1530281067', '');
INSERT INTO `d_charge` VALUES ('767', 'device_a369', 'dd', 'dde', 'mac_a369', '2', '368', '1531391149', '1530281067', '');
INSERT INTO `d_charge` VALUES ('768', 'device_a368', 'dd', 'dde', 'mac_a368', '1', '0', '1530341213', '1530281067', '');
INSERT INTO `d_charge` VALUES ('769', 'device_a367', 'dd', 'dde', 'mac_a367', '1', '0', '1530341213', '1530281067', '');
INSERT INTO `d_charge` VALUES ('770', 'device_a366', 'dd', 'dde', 'mac_a366', '1', '0', '1530341213', '1530281067', '');
INSERT INTO `d_charge` VALUES ('771', 'device_a365', 'dd', 'dde', 'mac_a365', '1', '0', '1530341213', '1530281067', '');
INSERT INTO `d_charge` VALUES ('772', 'device_a364', 'dd', 'dde', 'mac_a364', '1', '0', '1530341213', '1530281067', '');
INSERT INTO `d_charge` VALUES ('773', 'device_a363', 'dd', 'dde', 'mac_a363', '1', '0', '1530341213', '1530281067', '');
INSERT INTO `d_charge` VALUES ('774', 'device_a362', 'dd', 'dde', 'mac_a362', '1', '0', '1530341213', '1530281067', '');
INSERT INTO `d_charge` VALUES ('775', 'device_a361', 'dd', 'dde', 'mac_a361', '1', '0', '1530341213', '1530281067', '');
INSERT INTO `d_charge` VALUES ('776', 'device_a360', 'dd', 'dde', 'mac_a360', '1', '0', '1530341213', '1530281067', '');
INSERT INTO `d_charge` VALUES ('777', 'device_a359', 'dd', 'dde', 'mac_a359', '1', '0', '1530341213', '1530281067', '');
INSERT INTO `d_charge` VALUES ('778', 'device_a358', 'dd', 'dde', 'mac_a358', '1', '0', '1530341213', '1530281067', '');
INSERT INTO `d_charge` VALUES ('779', 'device_a357', 'dd', 'dde', 'mac_a357', '1', '0', '1530341213', '1530281067', '');
INSERT INTO `d_charge` VALUES ('780', 'device_a356', 'dd', 'dde', 'mac_a356', '1', '0', '1530341213', '1530281067', '');
INSERT INTO `d_charge` VALUES ('781', 'device_a355', 'dd', 'dde', 'mac_a355', '1', '0', '1530341213', '1530281067', '');
INSERT INTO `d_charge` VALUES ('782', 'device_a354', 'dd', 'dde', 'mac_a354', '1', '0', '1530341213', '1530281067', '');
INSERT INTO `d_charge` VALUES ('783', 'device_a353', 'dd', 'dde', 'mac_a353', '1', '0', '1530341213', '1530281067', '');
INSERT INTO `d_charge` VALUES ('784', 'device_a352', 'dd', 'dde', 'mac_a352', '1', '0', '1530341213', '1530281067', '');
INSERT INTO `d_charge` VALUES ('785', 'device_a351', 'dd', 'dde', 'mac_a351', '1', '0', '1530341213', '1530281067', '');
INSERT INTO `d_charge` VALUES ('786', 'device_a350', 'dd', 'dde', 'mac_a350', '1', '0', '1530341213', '1530281067', '');
INSERT INTO `d_charge` VALUES ('787', 'device_a349', 'dd', 'dde', 'mac_a349', '2', '346', '1531377561', '1530281067', '');
INSERT INTO `d_charge` VALUES ('788', 'device_a348', 'dd', 'dde', 'mac_a348', '1', '0', '1530341213', '1530281067', '');
INSERT INTO `d_charge` VALUES ('789', 'device_a347', 'dd', 'dde', 'mac_a347', '2', '344', '1531377469', '1530281067', '');
INSERT INTO `d_charge` VALUES ('790', 'device_a346', 'dd', 'dde', 'mac_a346', '1', '0', '1530341213', '1530281067', '');
INSERT INTO `d_charge` VALUES ('791', 'device_a345', 'dd', 'dde', 'mac_a345', '1', '0', '1530341213', '1530281067', '');
INSERT INTO `d_charge` VALUES ('792', 'device_a344', 'dd', 'dde', 'mac_a344', '1', '0', '1530341213', '1530281067', '');
INSERT INTO `d_charge` VALUES ('793', 'device_a343', 'dd', 'dde', 'mac_a343', '1', '0', '1530341213', '1530281067', '');
INSERT INTO `d_charge` VALUES ('794', 'device_a342', 'dd', 'dde', 'mac_a342', '1', '0', '1530341213', '1530281067', '');
INSERT INTO `d_charge` VALUES ('795', 'device_a341', 'dd', 'dde', 'mac_a341', '2', '479', '1531815356', '1530281067', '');
INSERT INTO `d_charge` VALUES ('796', 'device_a340', 'dd', 'dde', 'mac_a340', '1', '0', '1530341213', '1530281067', '');
INSERT INTO `d_charge` VALUES ('797', 'device_a339', 'dd', 'dde', 'mac_a339', '1', '0', '1530341213', '1530281067', '');
INSERT INTO `d_charge` VALUES ('798', 'device_a338', 'dd', 'dde', 'mac_a338', '1', '0', '1530341213', '1530281067', '');
INSERT INTO `d_charge` VALUES ('799', 'device_a337', 'dd', 'dde', 'mac_a337', '1', '0', '1530341213', '1530281067', '');
INSERT INTO `d_charge` VALUES ('800', 'device_a336', 'dd', 'dde', 'mac_a336', '1', '0', '1530341213', '1530281067', '');
INSERT INTO `d_charge` VALUES ('801', 'device_a335', 'dd', 'dde', 'mac_a335', '1', '0', '1530341213', '1530281067', '');
INSERT INTO `d_charge` VALUES ('802', 'device_a334', 'dd', 'dde', 'mac_a334', '1', '0', '1530341213', '1530281067', '');
INSERT INTO `d_charge` VALUES ('803', 'device_a333', 'dd', 'dde', 'mac_a333', '1', '0', '1530341213', '1530281067', '');
INSERT INTO `d_charge` VALUES ('804', 'device_a332', 'dd', 'dde', 'mac_a332', '2', '332', '1531362043', '1530281067', '');
INSERT INTO `d_charge` VALUES ('805', 'device_a331', 'dd', 'dde', 'mac_a331', '1', '0', '1530341213', '1530281067', '');
INSERT INTO `d_charge` VALUES ('806', 'device_a330', 'dd', 'dde', 'mac_a330', '1', '0', '1530341213', '1530281067', '');
INSERT INTO `d_charge` VALUES ('807', 'device_a329', 'dd', 'dde', 'mac_a329', '1', '0', '1530341213', '1530281067', '');
INSERT INTO `d_charge` VALUES ('808', 'device_a328', 'dd', 'dde', 'mac_a328', '1', '0', '1530341213', '1530281067', '');
INSERT INTO `d_charge` VALUES ('809', 'device_a327', 'dd', 'dde', 'mac_a327', '1', '0', '1530341213', '1530281067', '');
INSERT INTO `d_charge` VALUES ('810', 'device_a326', 'dd', 'dde', 'mac_a326', '1', '0', '1530341213', '1530281067', '');
INSERT INTO `d_charge` VALUES ('811', 'device_a325', 'dd', 'dde', 'mac_a325', '1', '0', '1530341213', '1530281067', '');
INSERT INTO `d_charge` VALUES ('812', 'device_a324', 'dd', 'dde', 'mac_a324', '1', '0', '1530341213', '1530281067', '');
INSERT INTO `d_charge` VALUES ('813', 'device_a323', 'dd', 'dde', 'mac_a323', '1', '0', '1530341213', '1530281067', '');
INSERT INTO `d_charge` VALUES ('814', 'device_a322', 'dd', 'dde', 'mac_a322', '2', '333', '1531363020', '1530281067', '');
INSERT INTO `d_charge` VALUES ('815', 'device_a321', 'dd', 'dde', 'mac_a321', '1', '0', '1530341213', '1530281067', '');
INSERT INTO `d_charge` VALUES ('816', 'device_a320', 'dd', 'dde', 'mac_a320', '1', '0', '1530341213', '1530281067', '');
INSERT INTO `d_charge` VALUES ('817', 'device_a319', 'dd', 'dde', 'mac_a319', '1', '0', '1530341213', '1530281067', '');
INSERT INTO `d_charge` VALUES ('818', 'device_a318', 'dd', 'dde', 'mac_a318', '1', '0', '1530341213', '1530281067', '');
INSERT INTO `d_charge` VALUES ('819', 'device_a317', 'dd', 'dde', 'mac_a317', '1', '0', '1530341213', '1530281067', '');
INSERT INTO `d_charge` VALUES ('820', 'device_a316', 'dd', 'dde', 'mac_a316', '1', '0', '1530341213', '1530281067', '');
INSERT INTO `d_charge` VALUES ('821', 'device_a315', 'dd', 'dde', 'mac_a315', '1', '0', '1530341213', '1530281067', '');
INSERT INTO `d_charge` VALUES ('822', 'device_a314', 'dd', 'dde', 'mac_a314', '1', '0', '1530341213', '1530281067', '');
INSERT INTO `d_charge` VALUES ('823', 'device_a313', 'dd', 'dde', 'mac_a313', '1', '0', '1530341213', '1530281067', '');
INSERT INTO `d_charge` VALUES ('824', 'device_a312', 'dd', 'dde', 'mac_a312', '1', '0', '1530341213', '1530281067', '');
INSERT INTO `d_charge` VALUES ('825', 'device_a311', 'dd', 'dde', 'mac_a311', '1', '0', '1530341213', '1530281067', '');
INSERT INTO `d_charge` VALUES ('826', 'device_a310', 'dd', 'dde', 'mac_a310', '1', '0', '1530341213', '1530281067', '');
INSERT INTO `d_charge` VALUES ('827', 'device_a309', 'dd', 'dde', 'mac_a309', '2', '484', '1531818022', '1530281067', '');
INSERT INTO `d_charge` VALUES ('828', 'device_a308', 'dd', 'dde', 'mac_a308', '2', '485', '1531817079', '1530281067', '');
INSERT INTO `d_charge` VALUES ('829', 'device_a307', 'dd', 'dde', 'mac_a307', '2', '344', '1531377450', '1530281067', '');
INSERT INTO `d_charge` VALUES ('830', 'device_a306', 'dd', 'dde', 'mac_a306', '1', '0', '1530341213', '1530281067', '');
INSERT INTO `d_charge` VALUES ('831', 'device_a305', 'dd', 'dde', 'mac_a305', '1', '0', '1530341213', '1530281067', '');
INSERT INTO `d_charge` VALUES ('832', 'device_a304', 'dd', 'dde', 'mac_a304', '1', '0', '1530341213', '1530281067', '');
INSERT INTO `d_charge` VALUES ('833', 'device_a303', 'dd', 'dde', 'mac_a303', '1', '0', '1530341213', '1530281067', '');
INSERT INTO `d_charge` VALUES ('834', 'device_a302', 'dd', 'dde', 'mac_a302', '2', '341', '1531374190', '1530281067', '');
INSERT INTO `d_charge` VALUES ('835', 'device_a301', 'dd', 'dde', 'mac_a301', '2', '371', '1531464669', '1530281067', '');
INSERT INTO `d_charge` VALUES ('836', 'device_a300', 'dd', 'dde', 'mac_a300', '2', '331', '1531464453', '1530281067', '');
INSERT INTO `d_charge` VALUES ('837', 'device_a299', 'dd', 'dde', 'mac_a299', '2', '16', '1532084753', '1530281067', '');
INSERT INTO `d_charge` VALUES ('838', 'device_a298', 'dd', 'dde', 'mac_a298', '1', '0', '1530341213', '1530281067', '');
INSERT INTO `d_charge` VALUES ('839', 'device_a297', 'dd', 'dde', 'mac_a297', '1', '0', '1530341213', '1530281067', '');
INSERT INTO `d_charge` VALUES ('840', 'device_a296', 'dd', 'dde', 'mac_a296', '1', '0', '1530341213', '1530281067', '');
INSERT INTO `d_charge` VALUES ('841', 'device_a295', 'dd', 'dde', 'mac_a295', '1', '0', '1530341213', '1530281067', '');
INSERT INTO `d_charge` VALUES ('842', 'device_a294', 'dd', 'dde', 'mac_a294', '1', '0', '1530341213', '1530281067', '');
INSERT INTO `d_charge` VALUES ('843', 'device_a293', 'dd', 'dde', 'mac_a293', '1', '0', '1530341213', '1530281067', '');
INSERT INTO `d_charge` VALUES ('844', 'device_a292', 'dd', 'dde', 'mac_a292', '1', '0', '1530341213', '1530281067', '');
INSERT INTO `d_charge` VALUES ('845', 'device_a291', 'dd', 'dde', 'mac_a291', '1', '0', '1530341213', '1530281067', '');
INSERT INTO `d_charge` VALUES ('846', 'device_a290', 'dd', 'dde', 'mac_a290', '1', '0', '1530341213', '1530281067', '');
INSERT INTO `d_charge` VALUES ('847', 'device_a289', 'dd', 'dde', 'mac_a289', '1', '0', '1530341213', '1530281067', '');
INSERT INTO `d_charge` VALUES ('848', 'device_a288', 'dd', 'dde', 'mac_a288', '2', '359', '1531464313', '1530281067', '');
INSERT INTO `d_charge` VALUES ('849', 'device_a287', 'dd', 'dde', 'mac_a287', '1', '0', '1530341213', '1530281067', '');
INSERT INTO `d_charge` VALUES ('850', 'device_a286', 'dd', 'dde', 'mac_a286', '1', '0', '1530341213', '1530281067', '');
INSERT INTO `d_charge` VALUES ('851', 'device_a285', 'dd', 'dde', 'mac_a285', '1', '0', '1530341213', '1530281067', '');
INSERT INTO `d_charge` VALUES ('852', 'device_a284', 'dd', 'dde', 'mac_a284', '1', '0', '1530341213', '1530281067', '');
INSERT INTO `d_charge` VALUES ('853', 'z0c8eea63486b8', 'dd', 'dde', 'mac_a283', '2', '510', '1531981995', '1530281067', '');
INSERT INTO `d_charge` VALUES ('854', 'device_a282', 'dd', 'dde', 'mac_a282', '1', '0', '1530341213', '1530281067', '');
INSERT INTO `d_charge` VALUES ('855', 'device_a281', 'dd', 'dde', 'mac_a281', '1', '0', '1530341213', '1530281067', '');
INSERT INTO `d_charge` VALUES ('856', 'device_a280', 'dd', 'dde', 'mac_a280', '1', '0', '1530341213', '1530281067', '');
INSERT INTO `d_charge` VALUES ('857', 'device_a279', 'dd', 'dde', 'mac_a279', '1', '0', '1530341213', '1530281067', '');
INSERT INTO `d_charge` VALUES ('858', 'device_a278', 'dd', 'dde', 'mac_a278', '1', '0', '1530341213', '1530281067', '');
INSERT INTO `d_charge` VALUES ('859', 'device_a277', 'dd', 'dde', 'mac_a277', '1', '0', '1530341213', '1530281067', '');
INSERT INTO `d_charge` VALUES ('860', 'device_a276', 'dd', 'dde', 'mac_a276', '1', '0', '1530341213', '1530281067', '');
INSERT INTO `d_charge` VALUES ('861', 'device_a275', 'dd', 'dde', 'mac_a275', '1', '0', '1530341213', '1530281067', '');
INSERT INTO `d_charge` VALUES ('862', 'device_a274', 'dd', 'dde', 'mac_a274', '1', '0', '1530341213', '1530281067', '');
INSERT INTO `d_charge` VALUES ('863', 'device_a273', 'dd', 'dde', 'mac_a273', '1', '0', '1530341213', '1530281067', '');
INSERT INTO `d_charge` VALUES ('864', 'device_a272', 'dd', 'dde', 'mac_a272', '1', '0', '1530341213', '1530281067', '');
INSERT INTO `d_charge` VALUES ('865', 'device_a271', 'dd', 'dde', 'mac_a271', '1', '0', '1530341213', '1530281067', '');
INSERT INTO `d_charge` VALUES ('866', 'device_a270', 'dd', 'dde', 'mac_a270', '1', '0', '1530341213', '1530281067', '');
INSERT INTO `d_charge` VALUES ('867', 'device_a269', 'dd', 'dde', 'mac_a269', '1', '0', '1530341213', '1530281067', '');
INSERT INTO `d_charge` VALUES ('868', 'device_a268', 'dd', 'dde', 'mac_a268', '1', '0', '1530341213', '1530281067', '');
INSERT INTO `d_charge` VALUES ('869', 'device_a267', 'dd', 'dde', 'mac_a267', '1', '0', '1530341213', '1530281067', '');
INSERT INTO `d_charge` VALUES ('870', 'device_a266', 'dd', 'dde', 'mac_a266', '1', '0', '1530341213', '1530281067', '');
INSERT INTO `d_charge` VALUES ('871', 'device_a265', 'dd', 'dde', 'mac_a265', '1', '0', '1530341213', '1530281067', '');
INSERT INTO `d_charge` VALUES ('872', 'device_a264', 'dd', 'dde', 'mac_a264', '1', '0', '1530341213', '1530281067', '');
INSERT INTO `d_charge` VALUES ('873', 'device_a263', 'dd', 'dde', 'mac_a263', '1', '0', '1530341213', '1530281067', '');
INSERT INTO `d_charge` VALUES ('874', 'device_a262', 'dd', 'dde', 'mac_a262', '1', '0', '1530341213', '1530281067', '');
INSERT INTO `d_charge` VALUES ('875', 'device_a261', 'dd', 'dde', 'mac_a261', '2', '111', '1535966642', '1530281067', '');
INSERT INTO `d_charge` VALUES ('876', 'device_a260', 'dd', 'dde', 'mac_a260', '1', '0', '1535965398', '1530281067', '');
INSERT INTO `d_charge` VALUES ('877', 'device_a259', 'dd', 'dde', 'mac_a259', '1', '0', '1530341213', '1530281067', '');
INSERT INTO `d_charge` VALUES ('878', 'device_a258', 'dd', 'dde', 'mac_a258', '1', '0', '1530341213', '1530281067', '');
INSERT INTO `d_charge` VALUES ('879', 'device_a257', 'dd', 'dde', 'mac_a257', '1', '0', '1530341213', '1530281067', '');
INSERT INTO `d_charge` VALUES ('880', 'device_a256', 'dd', 'dde', 'mac_a256', '1', '0', '1530341213', '1530281067', '');
INSERT INTO `d_charge` VALUES ('881', 'device_a255', 'dd', 'dde', 'mac_a255', '1', '0', '1530341213', '1530281067', '');
INSERT INTO `d_charge` VALUES ('882', 'device_a254', 'dd', 'dde', 'mac_a254', '1', '0', '1530341213', '1530281067', '');
INSERT INTO `d_charge` VALUES ('883', 'device_a253', 'dd', 'dde', 'mac_a253', '1', '0', '1530341213', '1530281067', '');
INSERT INTO `d_charge` VALUES ('884', 'device_a252', 'dd', 'dde', 'mac_a252', '1', '0', '1530341213', '1530281067', '');
INSERT INTO `d_charge` VALUES ('885', 'device_a251', 'dd', 'dde', 'mac_a251', '2', '267', '1531192715', '1530281067', '');
INSERT INTO `d_charge` VALUES ('886', 'device_a250', 'dd', 'dde', 'mac_a250', '1', '0', '1530341213', '1530281067', '');
INSERT INTO `d_charge` VALUES ('887', 'device_a249', 'dd', 'dde', 'mac_a249', '1', '0', '1530341213', '1530281067', '');
INSERT INTO `d_charge` VALUES ('888', 'device_a248', 'dd', 'dde', 'mac_a248', '1', '0', '1530341213', '1530281067', '');
INSERT INTO `d_charge` VALUES ('889', 'device_a247', 'dd', 'dde', 'mac_a247', '1', '0', '1530341213', '1530281067', '');
INSERT INTO `d_charge` VALUES ('890', 'device_a246', 'dd', 'dde', 'mac_a246', '1', '0', '1530341213', '1530281067', '');
INSERT INTO `d_charge` VALUES ('891', 'device_a245', 'dd', 'dde', 'mac_a245', '1', '0', '1530341213', '1530281067', '');
INSERT INTO `d_charge` VALUES ('892', 'device_a244', 'dd', 'dde', 'mac_a244', '1', '0', '1530341213', '1530281067', '');
INSERT INTO `d_charge` VALUES ('893', 'device_a243', 'dd', 'dde', 'mac_a243', '1', '0', '1530341213', '1530281067', '');
INSERT INTO `d_charge` VALUES ('894', 'device_a242', 'dd', 'dde', 'mac_a242', '1', '0', '1530341213', '1530281067', '');
INSERT INTO `d_charge` VALUES ('895', 'device_a241', 'dd', 'dde', 'mac_a241', '1', '0', '1530341213', '1530281067', '');
INSERT INTO `d_charge` VALUES ('896', 'device_a240', 'dd', 'dde', 'mac_a240', '1', '0', '1530341213', '1530281067', '');
INSERT INTO `d_charge` VALUES ('897', 'device_a239', 'dd', 'dde', 'mac_a239', '1', '0', '1530341213', '1530281067', '');
INSERT INTO `d_charge` VALUES ('898', 'device_a238', 'dd', 'dde', 'mac_a238', '1', '0', '1530341213', '1530281067', '');
INSERT INTO `d_charge` VALUES ('899', 'device_a237', 'dd', 'dde', 'mac_a237', '1', '0', '1530341213', '1530281067', '');
INSERT INTO `d_charge` VALUES ('900', 'device_a236', 'dd', 'dde', 'mac_a236', '1', '0', '1530341213', '1530281067', '');
INSERT INTO `d_charge` VALUES ('901', 'device_a235', 'dd', 'dde', 'mac_a235', '1', '0', '1530341213', '1530281067', '');
INSERT INTO `d_charge` VALUES ('902', 'device_a234', 'dd', 'dde', 'mac_a234', '1', '0', '1530341213', '1530281067', '');
INSERT INTO `d_charge` VALUES ('903', 'device_a233', 'dd', 'dde', 'mac_a233', '1', '0', '1530341213', '1530281067', '');
INSERT INTO `d_charge` VALUES ('904', 'device_a232', 'dd', 'dde', 'mac_a232', '2', '269', '1531193984', '1530281067', '');
INSERT INTO `d_charge` VALUES ('905', 'device_a231', 'dd', 'dde', 'mac_a231', '1', '0', '1530341213', '1530281067', '');
INSERT INTO `d_charge` VALUES ('906', 'device_a230', 'dd', 'dde', 'mac_a230', '1', '0', '1530341213', '1530281067', '');
INSERT INTO `d_charge` VALUES ('907', 'device_a229', 'dd', 'dde', 'mac_a229', '2', '503', '1531966352', '1530281067', '');
INSERT INTO `d_charge` VALUES ('908', 'device_a228', 'dd', 'dde', 'mac_a228', '1', '0', '1530341213', '1530281067', '');
INSERT INTO `d_charge` VALUES ('909', 'device_a227', 'dd', 'dde', 'mac_a227', '1', '0', '1530341213', '1530281067', '');
INSERT INTO `d_charge` VALUES ('910', 'device_a226', 'dd', 'dde', 'mac_a226', '2', '387', '1531464518', '1530281067', '');
INSERT INTO `d_charge` VALUES ('911', 'device_a225', 'dd', 'dde', 'mac_a225', '1', '0', '1530341213', '1530281067', '');
INSERT INTO `d_charge` VALUES ('912', 'device_a224', 'dd', 'dde', 'mac_a224', '1', '0', '0', '1530281067', '');
INSERT INTO `d_charge` VALUES ('913', 'device_a223', 'dd', 'dde', 'mac_a223', '1', '0', '1530341213', '1530281067', '');
INSERT INTO `d_charge` VALUES ('914', 'device_a222', 'dd', 'dde', 'mac_a222', '2', '323', '1531301307', '1530281067', '');
INSERT INTO `d_charge` VALUES ('915', 'device_a221', 'dd', 'dde', 'mac_a221', '1', '0', '1530341213', '1530281067', '');
INSERT INTO `d_charge` VALUES ('916', 'device_a220', 'dd', 'dde', 'mac_a220', '1', '0', '1530341213', '1530281067', '');
INSERT INTO `d_charge` VALUES ('917', 'device_a219', 'dd', 'dde', 'mac_a219', '1', '0', '1530341213', '1530281067', '');
INSERT INTO `d_charge` VALUES ('918', 'device_a218', 'dd', 'dde', 'mac_a218', '1', '0', '1530341213', '1530281067', '');
INSERT INTO `d_charge` VALUES ('919', 'device_a217', 'dd', 'dde', 'mac_a217', '1', '0', '1530341213', '1530281067', '');
INSERT INTO `d_charge` VALUES ('920', 'device_a216', 'dd', 'dde', 'mac_a216', '1', '0', '1530341213', '1530281067', '');
INSERT INTO `d_charge` VALUES ('921', 'device_a215', 'dd', 'dde', 'mac_a215', '1', '0', '1530341213', '1530281067', '');
INSERT INTO `d_charge` VALUES ('922', 'device_a214', 'dd', 'dde', 'mac_a214', '2', '270', '1531194737', '1530281067', '');
INSERT INTO `d_charge` VALUES ('923', 'device_a213', 'dd', 'dde', 'mac_a213', '1', '0', '1530341213', '1530281067', '');
INSERT INTO `d_charge` VALUES ('924', 'device_a212', 'dd', 'dde', 'mac_a212', '1', '0', '1530341213', '1530281067', '');
INSERT INTO `d_charge` VALUES ('925', 'device_a211', 'dd', 'dde', 'mac_a211', '1', '0', '1530341213', '1530281067', '');
INSERT INTO `d_charge` VALUES ('926', 'device_a210', 'dd', 'dde', 'mac_a210', '1', '0', '1530341213', '1530281067', '');
INSERT INTO `d_charge` VALUES ('927', 'device_a209', 'dd', 'dde', 'mac_a209', '1', '0', '1530341213', '1530281067', '');
INSERT INTO `d_charge` VALUES ('928', 'device_a208', 'dd', 'dde', 'mac_a208', '2', '502', '1531907162', '1530281067', '');
INSERT INTO `d_charge` VALUES ('929', 'device_a207', 'dd', 'dde', 'mac_a207', '2', '265', '1531192802', '1530281067', '');
INSERT INTO `d_charge` VALUES ('930', 'device_a206', 'dd', 'dde', 'mac_a206', '1', '0', '1530341213', '1530281067', '');
INSERT INTO `d_charge` VALUES ('931', 'device_a205', 'dd', 'dde', 'mac_a205', '1', '0', '1530341213', '1530281067', '');
INSERT INTO `d_charge` VALUES ('932', 'device_a204', 'dd', 'dde', 'mac_a204', '1', '0', '1530341213', '1530281067', '');
INSERT INTO `d_charge` VALUES ('933', 'device_a203', 'dd', 'dde', 'mac_a203', '1', '0', '1530341213', '1530281067', '');
INSERT INTO `d_charge` VALUES ('934', 'device_a202', 'dd', 'dde', 'mac_a202', '2', '493', '1531881140', '1530281067', '');
INSERT INTO `d_charge` VALUES ('935', 'device_a201', 'dd', 'dde', 'mac_a201', '1', '0', '1530341213', '1530281067', '');
INSERT INTO `d_charge` VALUES ('936', 'device_a200', 'dd', 'dde', 'mac_a200', '2', '361', '1531387111', '1530281067', '');
INSERT INTO `d_charge` VALUES ('937', 'device_a199', 'dd', 'dde', 'mac_a199', '2', '5', '1532075043', '1530281067', '');
INSERT INTO `d_charge` VALUES ('938', 'device_a198', 'dd', 'dde', 'mac_a198', '2', '506', '1532067803', '1530281067', '');
INSERT INTO `d_charge` VALUES ('939', 'device_a197', 'dd', 'dde', 'mac_a197', '2', '508', '1531978825', '1530281067', '');
INSERT INTO `d_charge` VALUES ('940', 'device_a196', 'dd', 'dde', 'mac_a196', '2', '507', '1531978769', '1530281067', '');
INSERT INTO `d_charge` VALUES ('941', 'device_a195', 'dd', 'dde', 'mac_a195', '1', '0', '1530341213', '1530281067', '');
INSERT INTO `d_charge` VALUES ('942', 'device_a194', 'dd', 'dde', 'mac_a194', '1', '0', '1530341213', '1530281067', '');
INSERT INTO `d_charge` VALUES ('943', 'device_a193', 'dd', 'dde', 'mac_a193', '1', '0', '1530341213', '1530281067', '');
INSERT INTO `d_charge` VALUES ('944', 'device_a192', 'dd', 'dde', 'mac_a192', '1', '0', '1530341213', '1530281067', '');
INSERT INTO `d_charge` VALUES ('945', 'device_a191', 'dd', 'dde', 'mac_a191', '1', '0', '1530341213', '1530281067', '');
INSERT INTO `d_charge` VALUES ('946', 'device_a190', 'dd', 'dde', 'mac_a190', '1', '0', '1530341213', '1530281067', '');
INSERT INTO `d_charge` VALUES ('947', 'device_a189', 'dd', 'dde', 'mac_a189', '1', '0', '1530341213', '1530281067', '');
INSERT INTO `d_charge` VALUES ('948', 'device_a188', 'dd', 'dde', 'mac_a188', '2', '61', '1532512568', '1530281067', '');
INSERT INTO `d_charge` VALUES ('949', 'device_a187', 'dd', 'dde', 'mac_a187', '1', '0', '1530341213', '1530281067', '');
INSERT INTO `d_charge` VALUES ('950', 'device_a186', 'dd', 'dde', 'mac_a186', '1', '0', '1530341213', '1530281067', '');
INSERT INTO `d_charge` VALUES ('951', 'device_a185', 'dd', 'dde', 'mac_a185', '1', '0', '1530341213', '1530281067', '');
INSERT INTO `d_charge` VALUES ('952', 'device_a184', 'dd', 'dde', 'mac_a184', '1', '0', '1530341213', '1530281067', '');
INSERT INTO `d_charge` VALUES ('953', 'device_a183', 'dd', 'dde', 'mac_a183', '2', '112', '1535967737', '1530281067', '');
INSERT INTO `d_charge` VALUES ('954', 'device_a182', 'dd', 'dde', 'mac_a182', '2', '112', '1535967592', '1530281067', '');
INSERT INTO `d_charge` VALUES ('955', 'device_a181', 'dd', 'dde', 'mac_a181', '2', '84', '1535967291', '1530281067', '');
INSERT INTO `d_charge` VALUES ('956', 'device_a180', 'dd', 'dde', 'mac_a180', '1', '0', '1535967070', '1530281067', '');
INSERT INTO `d_charge` VALUES ('957', 'device_a179', 'dd', 'dde', 'mac_a179', '2', '22', '1532142735', '1530281067', '');
INSERT INTO `d_charge` VALUES ('958', 'device_a178', 'dd', 'dde', 'mac_a178', '2', '12', '1532079769', '1530281067', '');
INSERT INTO `d_charge` VALUES ('959', 'device_a177', 'dd', 'dde', 'mac_a177', '2', '11', '1532081031', '1530281067', '');
INSERT INTO `d_charge` VALUES ('960', 'device_a176', 'dd', 'dde', 'mac_a176', '1', '0', '1530341213', '1530281067', '');
INSERT INTO `d_charge` VALUES ('961', 'device_a175', 'dd', 'dde', 'mac_a175', '2', '264', '1531194857', '1530281067', '');
INSERT INTO `d_charge` VALUES ('962', 'device_a174', 'dd', 'dde', 'mac_a174', '1', '0', '1530341213', '1530281067', '');
INSERT INTO `d_charge` VALUES ('963', 'device_a173', 'dd', 'dde', 'mac_a173', '1', '0', '1530341213', '1530281067', '');
INSERT INTO `d_charge` VALUES ('964', 'device_a172', 'dd', 'dde', 'mac_a172', '1', '0', '1530341213', '1530281067', '');
INSERT INTO `d_charge` VALUES ('965', 'device_a171', 'dd', 'dde', 'mac_a171', '1', '0', '1530341213', '1530281067', '');
INSERT INTO `d_charge` VALUES ('966', 'device_a170', 'dd', 'dde', 'mac_a170', '1', '0', '1530341213', '1530281067', '');
INSERT INTO `d_charge` VALUES ('967', 'device_a169', 'dd', 'dde', 'mac_a169', '1', '0', '1530341213', '1530281067', '');
INSERT INTO `d_charge` VALUES ('968', 'device_a168', 'dd', 'dde', 'mac_a168', '1', '0', '1530341213', '1530281068', '');
INSERT INTO `d_charge` VALUES ('969', 'device_a167', 'dd', 'dde', 'mac_a167', '1', '0', '1530341213', '1530281068', '');
INSERT INTO `d_charge` VALUES ('970', 'device_a166', 'dd', 'dde', 'mac_a166', '2', '4', '1532080303', '1530281068', '');
INSERT INTO `d_charge` VALUES ('971', 'device_a165', 'dd', 'dde', 'mac_a165', '1', '0', '1530341213', '1530281068', '');
INSERT INTO `d_charge` VALUES ('972', 'device_a164', 'dd', 'dde', 'mac_a164', '1', '0', '1530341213', '1530281068', '');
INSERT INTO `d_charge` VALUES ('973', 'device_a163', 'dd', 'dde', 'mac_a163', '2', '23', '1532166537', '1530281068', '');
INSERT INTO `d_charge` VALUES ('974', 'device_a162', 'dd', 'dde', 'mac_a162', '2', '25', '1532166479', '1530281068', '');
INSERT INTO `d_charge` VALUES ('975', 'device_a161', 'dd', 'dde', 'mac_a161', '2', '14', '1532143134', '1530281068', '');
INSERT INTO `d_charge` VALUES ('976', 'device_a160', 'dd', 'dde', 'mac_a160', '1', '0', '1530341213', '1530281068', '');
INSERT INTO `d_charge` VALUES ('977', 'device_a159', 'dd', 'dde', 'mac_a159', '1', '0', '1530341213', '1530281068', '');
INSERT INTO `d_charge` VALUES ('978', 'device_a158', 'dd', 'dde', 'mac_a158', '1', '0', '1530341213', '1530281068', '');
INSERT INTO `d_charge` VALUES ('979', 'device_a157', 'dd', 'dde', 'mac_a157', '1', '0', '1530341213', '1530281068', '');
INSERT INTO `d_charge` VALUES ('980', 'device_a156', 'dd', 'dde', 'mac_a156', '1', '0', '1530341213', '1530281068', '');
INSERT INTO `d_charge` VALUES ('981', 'device_a155', 'dd', 'dde', 'mac_a155', '1', '0', '1530341213', '1530281068', '');
INSERT INTO `d_charge` VALUES ('982', 'device_a154', 'dd', 'dde', 'mac_a154', '1', '0', '1530341213', '1530281068', '');
INSERT INTO `d_charge` VALUES ('983', 'device_a153', 'dd', 'dde', 'mac_a153', '1', '0', '1530341213', '1530281068', '');
INSERT INTO `d_charge` VALUES ('984', 'device_a152', 'dd', 'dde', 'mac_a152', '1', '0', '1530341213', '1530281068', '');
INSERT INTO `d_charge` VALUES ('985', 'device_a151', 'dd', 'dde', 'mac_a151', '1', '0', '1530341213', '1530281068', '');
INSERT INTO `d_charge` VALUES ('986', 'device_a150', 'dd', 'dde', 'mac_a150', '1', '0', '1530341213', '1530281068', '');
INSERT INTO `d_charge` VALUES ('987', 'device_a149', 'dd', 'dde', 'mac_a149', '1', '0', '1530341213', '1530281068', '');
INSERT INTO `d_charge` VALUES ('988', 'device_a148', 'dd', 'dde', 'mac_a148', '2', '1', '1535700948', '1530281068', '');
INSERT INTO `d_charge` VALUES ('989', 'device_a147', 'dd', 'dde', 'mac_a147', '1', '0', '1530341213', '1530281068', '');
INSERT INTO `d_charge` VALUES ('990', 'device_a146', 'dd', 'dde', 'mac_a146', '1', '0', '1530341213', '1530281068', '');
INSERT INTO `d_charge` VALUES ('991', 'device_a145', 'dd', 'dde', 'mac_a145', '2', '13', '1532084129', '1530281068', '');
INSERT INTO `d_charge` VALUES ('992', 'device_a144', 'dd', 'dde', 'mac_a144', '1', '0', '1530341213', '1530281068', '');
INSERT INTO `d_charge` VALUES ('993', 'device_a143', 'dd', 'dde', 'mac_a143', '1', '0', '1530341213', '1530281068', '');
INSERT INTO `d_charge` VALUES ('994', 'device_a142', 'dd', 'dde', 'mac_a142', '1', '0', '1530341213', '1530281068', '');
INSERT INTO `d_charge` VALUES ('995', 'device_a141', 'dd', 'dde', 'mac_a141', '1', '0', '1530341213', '1530281068', '');
INSERT INTO `d_charge` VALUES ('996', 'device_a140', 'dd', 'dde', 'mac_a140', '1', '0', '1530341213', '1530281068', '');
INSERT INTO `d_charge` VALUES ('997', 'device_a139', 'dd', 'dde', 'mac_a139', '1', '0', '1530341213', '1530281068', '');
INSERT INTO `d_charge` VALUES ('998', 'device_a138', 'dd', 'dde', 'mac_a138', '1', '0', '1530341213', '1530281068', '');
INSERT INTO `d_charge` VALUES ('999', 'device_a137', 'dd', 'dde', 'mac_a137', '1', '0', '1530341213', '1530281068', '');
INSERT INTO `d_charge` VALUES ('1000', 'device_a136', 'dd', 'dde', 'mac_a136', '1', '0', '1530341213', '1530281068', '');
INSERT INTO `d_charge` VALUES ('1001', 'device_a135', 'dd', 'dde', 'mac_a135', '1', '0', '1530341213', '1530281068', '');
INSERT INTO `d_charge` VALUES ('1002', 'device_a134', 'dd', 'dde', 'mac_a134', '1', '0', '1530341213', '1530281068', '');
INSERT INTO `d_charge` VALUES ('1003', 'device_a133', 'dd', 'dde', 'mac_a133', '1', '0', '1530341213', '1530281068', '');
INSERT INTO `d_charge` VALUES ('1004', 'device_a132', 'dd', 'dde', 'mac_a132', '1', '0', '1530341213', '1530281068', '');
INSERT INTO `d_charge` VALUES ('1005', 'device_a131', 'dd', 'dde', 'mac_a131', '1', '0', '1530341213', '1530281068', '');
INSERT INTO `d_charge` VALUES ('1006', 'device_a130', 'dd', 'dde', 'mac_a130', '2', '99', '1535707373', '1530281068', '');
INSERT INTO `d_charge` VALUES ('1007', 'device_a129', 'dd', 'dde', 'mac_a129', '1', '0', '1530341213', '1530281068', '');
INSERT INTO `d_charge` VALUES ('1008', 'device_a128', 'dd', 'dde', 'mac_a128', '2', '102', '1535709599', '1530281068', '');
INSERT INTO `d_charge` VALUES ('1009', 'device_a127', 'dd', 'dde', 'mac_a127', '1', '0', '1530341213', '1530281068', '');
INSERT INTO `d_charge` VALUES ('1010', 'device_a126', 'dd', 'dde', 'mac_a126', '1', '0', '1530341213', '1530281068', '');
INSERT INTO `d_charge` VALUES ('1011', 'device_a125', 'dd', 'dde', 'mac_a125', '1', '0', '1530341213', '1530281068', '');
INSERT INTO `d_charge` VALUES ('1012', 'device_a124', 'dd', 'dde', 'mac_a124', '1', '0', '1530341213', '1530281068', '');
INSERT INTO `d_charge` VALUES ('1013', 'device_a123', 'dd', 'dde', 'mac_a123', '1', '0', '1530341213', '1530281068', '');
INSERT INTO `d_charge` VALUES ('1014', 'device_a122', 'dd', 'dde', 'mac_a122', '1', '0', '1530341213', '1530281068', '');
INSERT INTO `d_charge` VALUES ('1015', 'device_a121', 'dd', 'dde', 'mac_a121', '2', '2', '1532136782', '1530281068', '');
INSERT INTO `d_charge` VALUES ('1016', 'device_a120', 'dd', 'dde', 'mac_a120', '1', '0', '1530341213', '1530281068', '');
INSERT INTO `d_charge` VALUES ('1017', 'device_a119', 'dd', 'dde', 'mac_a119', '2', '454', '1531721667', '1530281068', '');
INSERT INTO `d_charge` VALUES ('1018', 'device_a118', 'dd', 'dde', 'mac_a118', '2', '339', '1531712582', '1530281068', '');
INSERT INTO `d_charge` VALUES ('1019', 'device_a117', 'dd', 'dde', 'mac_a117', '2', '457', '1531721890', '1530281068', '');
INSERT INTO `d_charge` VALUES ('1020', 'device_a116', 'dd', 'dde', 'mac_a116', '2', '455', '1531725360', '1530281068', '');
INSERT INTO `d_charge` VALUES ('1021', 'device_a115', 'dd', 'dde', 'mac_a115', '1', '0', '1530341213', '1530281068', '');
INSERT INTO `d_charge` VALUES ('1022', 'device_a114', 'dd', 'dde', 'mac_a114', '2', '465', '1531795990', '1530281068', '');
INSERT INTO `d_charge` VALUES ('1023', 'device_a113', 'dd', 'dde', 'mac_a113', '2', '470', '1531799652', '1530281068', '');
INSERT INTO `d_charge` VALUES ('1024', 'device_a112', 'dd', 'dde', 'mac_a112', '2', '478', '1531817866', '1530281068', '');
INSERT INTO `d_charge` VALUES ('1025', 'device_a111', 'dd', 'dde', 'mac_a111', '2', '482', '1531823384', '1530281068', '');
INSERT INTO `d_charge` VALUES ('1026', 'device_a110', 'dd', 'dde', 'mac_a110', '2', '490', '1531879919', '1530281068', '');
INSERT INTO `d_charge` VALUES ('1027', 'device_a109', 'dd', 'dde', 'mac_a109', '2', '491', '1531880189', '1530281068', '');
INSERT INTO `d_charge` VALUES ('1028', 'device_a108', 'dd', 'dde', 'mac_a108', '1', '0', '1530341213', '1530281068', '');
INSERT INTO `d_charge` VALUES ('1029', 'device_a107', 'dd', 'dde', 'mac_a107', '1', '0', '1530341213', '1530281068', '');
INSERT INTO `d_charge` VALUES ('1030', 'device_a106', 'dd', 'dde', 'mac_a106', '1', '0', '1530341213', '1530281068', '');
INSERT INTO `d_charge` VALUES ('1031', 'device_a105', 'dd', 'dde', 'mac_a105', '1', '0', '1530341213', '1530281068', '');
INSERT INTO `d_charge` VALUES ('1032', 'device_a104', 'dd', 'dde', 'mac_a104', '1', '0', '1530341213', '1530281068', '');
INSERT INTO `d_charge` VALUES ('1033', 'device_a103', 'dd', 'dde', 'mac_a103', '1', '0', '1530341213', '1530281068', '');
INSERT INTO `d_charge` VALUES ('1034', 'device_a102', 'dd', 'dde', 'mac_a102', '1', '0', '1530341213', '1530281068', '');
INSERT INTO `d_charge` VALUES ('1035', 'device_a101', 'dd', 'dde', 'mac_a101', '1', '0', '1530341213', '1530281068', '');
INSERT INTO `d_charge` VALUES ('1036', 'device_a100', 'dd', 'dde', 'mac_a100', '2', '504', '1531966376', '1530281068', '');
INSERT INTO `d_charge` VALUES ('1037', 'device_a99', 'dd', 'dde', 'mac_a99', '1', '0', '1530341213', '1530281068', '');
INSERT INTO `d_charge` VALUES ('1038', 'device_a98', 'dd', 'dde', 'mac_a98', '1', '0', '1530341213', '1530281068', '');
INSERT INTO `d_charge` VALUES ('1039', 'device_a97', 'dd', 'dde', 'mac_a97', '1', '0', '1530341213', '1530281068', '');
INSERT INTO `d_charge` VALUES ('1040', 'device_a96', 'dd', 'dde', 'mac_a96', '1', '0', '1530341213', '1530281068', '');
INSERT INTO `d_charge` VALUES ('1041', 'device_a95', 'dd', 'dde', 'mac_a95', '1', '0', '1530341213', '1530281068', '');
INSERT INTO `d_charge` VALUES ('1042', 'device_a94', 'dd', 'dde', 'mac_a94', '1', '0', '1530341213', '1530281068', '');
INSERT INTO `d_charge` VALUES ('1043', 'device_a93', 'dd', 'dde', 'mac_a93', '1', '0', '1530341213', '1530281068', '');
INSERT INTO `d_charge` VALUES ('1044', 'device_a92', 'dd', 'dde', 'mac_a92', '1', '0', '1530341213', '1530281068', '');
INSERT INTO `d_charge` VALUES ('1045', 'device_a91', 'dd', 'dde', 'mac_a91', '1', '0', '1530341213', '1530281068', '');
INSERT INTO `d_charge` VALUES ('1046', 'device_a90', 'dd', 'dde', 'mac_a90', '1', '0', '1530341213', '1530281068', '');
INSERT INTO `d_charge` VALUES ('1047', 'device_a89', 'dd', 'dde', 'mac_a89', '2', '370', '1531445390', '1530281068', '');
INSERT INTO `d_charge` VALUES ('1048', 'device_a88', 'dd', 'dde', 'mac_a88', '1', '0', '1530341213', '1530281068', '');
INSERT INTO `d_charge` VALUES ('1049', 'device_a87', 'dd', 'dde', 'mac_a87', '1', '0', '1530341213', '1530281068', '');
INSERT INTO `d_charge` VALUES ('1050', 'device_a86', 'dd', 'dde', 'mac_a86', '1', '0', '1530341213', '1530281068', '');
INSERT INTO `d_charge` VALUES ('1051', 'device_a85', 'dd', 'dde', 'mac_a85', '1', '0', '1530341213', '1530281068', '');
INSERT INTO `d_charge` VALUES ('1052', 'device_a84', 'dd', 'dde', 'mac_a84', '1', '0', '1530341213', '1530281068', '');
INSERT INTO `d_charge` VALUES ('1053', 'device_a83', 'dd', 'dde', 'mac_a83', '1', '0', '1530341213', '1530281068', '');
INSERT INTO `d_charge` VALUES ('1054', 'device_a82', 'dd', 'dde', 'mac_a82', '1', '0', '1530341213', '1530281068', '');
INSERT INTO `d_charge` VALUES ('1055', 'device_a81', 'dd', 'dde', 'mac_a81', '1', '0', '1530341213', '1530281068', '');
INSERT INTO `d_charge` VALUES ('1056', 'device_a80', 'dd', 'dde', 'mac_a80', '1', '0', '1530341213', '1530281068', '');
INSERT INTO `d_charge` VALUES ('1057', 'device_a79', 'dd', 'dde', 'mac_a79', '1', '0', '1530341213', '1530281068', '');
INSERT INTO `d_charge` VALUES ('1058', 'device_a78', 'dd', 'dde', 'mac_a78', '1', '0', '1530341213', '1530281068', '');
INSERT INTO `d_charge` VALUES ('1059', 'device_a77', 'dd', 'dde', 'mac_a77', '1', '0', '1530341213', '1530281068', '');
INSERT INTO `d_charge` VALUES ('1060', 'device_a76', 'dd', 'dde', 'mac_a76', '1', '0', '1530341213', '1530281068', '');
INSERT INTO `d_charge` VALUES ('1061', 'device_a75', 'dd', 'dde', 'mac_a75', '1', '0', '1530341213', '1530281068', '');
INSERT INTO `d_charge` VALUES ('1062', 'device_a74', 'dd', 'dde', 'mac_a74', '1', '0', '1530341213', '1530281068', '');
INSERT INTO `d_charge` VALUES ('1063', 'device_a73', 'dd', 'dde', 'mac_a73', '1', '0', '1530341213', '1530281068', '');
INSERT INTO `d_charge` VALUES ('1064', 'device_a72', 'dd', 'dde', 'mac_a72', '1', '0', '1530341213', '1530281068', '');
INSERT INTO `d_charge` VALUES ('1065', 'device_a71', 'dd', 'dde', 'mac_a71', '1', '0', '1530341213', '1530281068', '');
INSERT INTO `d_charge` VALUES ('1066', 'device_a70', 'dd', 'dde', 'mac_a70', '1', '0', '1530341213', '1530281068', '');
INSERT INTO `d_charge` VALUES ('1067', 'device_a69', 'dd', 'dde', 'mac_a69', '1', '0', '1530341213', '1530281068', '');
INSERT INTO `d_charge` VALUES ('1068', 'device_a68', 'dd', 'dde', 'mac_a68', '2', '41', '1533121000', '1530281068', '');
INSERT INTO `d_charge` VALUES ('1069', 'device_a67', 'dd', 'dde', 'mac_a67', '1', '0', '1530341213', '1530281068', '');
INSERT INTO `d_charge` VALUES ('1070', 'a1c8eea63486b8', 'dd', 'dde', 'mac_a66', '2', '496', '1531883270', '1530281068', '');
INSERT INTO `d_charge` VALUES ('1071', 'device_a65', 'dd', 'dde', 'mac_a65', '1', '0', '1530341213', '1530281068', '');
INSERT INTO `d_charge` VALUES ('1072', '	A0000000000111', 'dd', 'dde', 'mac_a64', '1', '0', '1530341213', '1530281068', '');
INSERT INTO `d_charge` VALUES ('1073', 'A0000000000009', 'dd', 'dde', 'mac_a63', '2', '447', '1531557400', '1530281068', '');
INSERT INTO `d_charge` VALUES ('1074', 'device_a62', 'dd', 'dde', 'mac_a62', '1', '0', '1530341213', '1530281068', '');
INSERT INTO `d_charge` VALUES ('1075', 'A0000000000019', 'dd', 'dde', 'mac_a61', '2', '448', '1531559107', '1530281068', '');
INSERT INTO `d_charge` VALUES ('1076', 'A0000000000016', 'dd', 'dde', 'mac_a60', '2', '446', '1531557291', '1530281068', '');
INSERT INTO `d_charge` VALUES ('1077', 'device_a59', 'dd', 'dde', 'mac_a59', '1', '0', '1530341213', '1530281068', '');
INSERT INTO `d_charge` VALUES ('1078', 'device_a58', 'dd', 'dde', 'mac_a58', '1', '0', '1530341213', '1530281068', '');
INSERT INTO `d_charge` VALUES ('1079', 'a0000011111011', 'dd', 'dde', 'mac_a57', '2', '449', '1531560452', '1530281068', '');
INSERT INTO `d_charge` VALUES ('1080', 'device_a56', 'dd', 'dde', 'mac_a56', '1', '0', '1530341213', '1530281068', '');
INSERT INTO `d_charge` VALUES ('1081', 'device_a55', 'dd', 'dde', 'mac_a55', '1', '0', '1530341213', '1530281068', '');
INSERT INTO `d_charge` VALUES ('1082', 'gf212132121555', 'dd', 'dde', 'mac_a54', '2', '440', '1531555122', '1530281068', '');
INSERT INTO `d_charge` VALUES ('1083', 'device_a53', 'dd', 'dde', 'mac_a53', '2', '59', '1532679465', '1530281068', '');
INSERT INTO `d_charge` VALUES ('1084', 'device_a52', 'dd', 'dde', 'mac_a52', '1', '0', '1530341213', '1530281068', '');
INSERT INTO `d_charge` VALUES ('1085', 'device_a51', 'dd', 'dde', 'mac_a51', '1', '0', '1530341213', '1530281068', '');
INSERT INTO `d_charge` VALUES ('1086', 'device_a50', 'dd', 'dde', 'mac_a50', '1', '0', '1530341213', '1530281068', '');
INSERT INTO `d_charge` VALUES ('1087', 'A0000000000024', 'dd', 'dde', 'mac_a49', '2', '282', '1531204214', '1530281068', '');
INSERT INTO `d_charge` VALUES ('1088', 'device_a48', 'dd', 'dde', 'mac_a48', '1', '0', '1530341213', '1530281068', '');
INSERT INTO `d_charge` VALUES ('1089', 'A0000044440000', 'dd', 'dde', 'mac_a47', '2', '442', '1531556078', '1530281068', '');
INSERT INTO `d_charge` VALUES ('1090', 'A0000000000014', 'dd', 'dde', 'mac_a46', '2', '443', '1531556335', '1530281068', '');
INSERT INTO `d_charge` VALUES ('1091', 'A0000000000022', 'dd', 'dde', 'mac_a45', '2', '444', '1531556421', '1530281068', '');
INSERT INTO `d_charge` VALUES ('1092', '	dfdfadf4440000', 'dd', 'dde', 'mac_a44', '1', '0', '1530341213', '1530281068', '');
INSERT INTO `d_charge` VALUES ('1093', 'device_a43', 'dd', 'dde', 'mac_a43', '1', '0', '1530341213', '1530281068', '');
INSERT INTO `d_charge` VALUES ('1094', 'device_a42', 'dd', 'dde', 'mac_a42', '2', '312', '1531291698', '1530281068', '');
INSERT INTO `d_charge` VALUES ('1095', 'device_a41', 'dd', 'dde', 'mac_a41', '1', '0', '1530341213', '1530281068', '');
INSERT INTO `d_charge` VALUES ('1096', '121212xcvc7878', 'dd', 'dde', 'mac_a40', '2', '441', '1531555463', '1530281068', '');
INSERT INTO `d_charge` VALUES ('1097', 'device_a39', 'dd', 'dde', 'mac_a39', '1', '0', '1530341213', '1530281068', '');
INSERT INTO `d_charge` VALUES ('1098', 'device_a38', 'dd', 'dde', 'mac_a38', '1', '0', '1530341213', '1530281068', '');
INSERT INTO `d_charge` VALUES ('1099', 'device_a37', 'dd', 'dde', 'mac_a37', '1', '0', '1530341213', '1530281068', '');
INSERT INTO `d_charge` VALUES ('1100', 'device_a36', 'dd', 'dde', 'mac_a36', '2', '322', '1531374232', '1530281068', '');
INSERT INTO `d_charge` VALUES ('1101', 'device_a35', 'dd', 'dde', 'mac_a35', '2', '342', '1531374808', '1530281068', '');
INSERT INTO `d_charge` VALUES ('1102', 'device_a34', 'dd', 'dde', 'mac_a34', '1', '0', '1530341213', '1530281068', '');
INSERT INTO `d_charge` VALUES ('1103', 'device_a33', 'dd', 'dde', 'mac_a33', '1', '0', '1530341213', '1530281068', '');
INSERT INTO `d_charge` VALUES ('1104', 'device_a32', 'dd', 'dde', 'mac_a32', '1', '0', '1530341213', '1530281068', '');
INSERT INTO `d_charge` VALUES ('1105', 'device_a31', 'dd', 'dde', 'mac_a31', '1', '0', '1530341213', '1530281068', '');
INSERT INTO `d_charge` VALUES ('1106', 'device_a30', 'dd', 'dde', 'mac_a30', '1', '0', '1530341213', '1530281068', '');
INSERT INTO `d_charge` VALUES ('1107', 'device_a29', 'dd', 'dde', 'mac_a29', '1', '0', '1530341213', '1530281068', '');
INSERT INTO `d_charge` VALUES ('1108', 'device_a28', 'dd', 'dde', 'mac_a28', '1', '0', '1530341213', '1530281068', '');
INSERT INTO `d_charge` VALUES ('1109', 'device_a27', 'dd', 'dde', 'mac_a27', '1', '0', '1530341213', '1530281068', '');
INSERT INTO `d_charge` VALUES ('1110', 'device_a26', 'dd', 'dde', 'mac_a26', '1', '0', '1530341213', '1530281068', '');
INSERT INTO `d_charge` VALUES ('1111', 'device_a25', 'dd', 'dde', 'mac_a25', '2', '487', '1531821955', '1530281068', '');
INSERT INTO `d_charge` VALUES ('1112', 'device_a24', 'dd', 'dde', 'mac_a24', '1', '0', '1530341213', '1530281068', '');
INSERT INTO `d_charge` VALUES ('1113', 'device_a23', 'dd', 'dde', 'mac_a23', '1', '0', '1530341213', '1530281068', '');
INSERT INTO `d_charge` VALUES ('1114', 'device_a22', 'dd', 'dde', 'mac_a22', '1', '0', '1530341213', '1530281068', '');
INSERT INTO `d_charge` VALUES ('1115', 'device_a21', 'dd', 'dde', 'mac_a21', '2', '315', '1531297789', '1530281068', '');
INSERT INTO `d_charge` VALUES ('1116', 'device_a20', 'dd', 'dde', 'mac_a20', '2', '316', '1531298704', '1530281068', '');
INSERT INTO `d_charge` VALUES ('1117', 'device_a19', 'dd', 'dde', 'mac_a19', '1', '0', '1530341213', '1530281068', '');
INSERT INTO `d_charge` VALUES ('1118', 'device_a18', 'dd', 'dde', 'mac_a18', '1', '0', '1530341213', '1530281068', '');
INSERT INTO `d_charge` VALUES ('1119', 'device_a17', 'dd', 'dde', 'mac_a17', '1', '0', '1530341213', '1530281068', '');
INSERT INTO `d_charge` VALUES ('1120', 'device_a16', 'dd', 'dde', 'mac_a16', '1', '0', '1530341213', '1530281068', '');
INSERT INTO `d_charge` VALUES ('1121', 'device_a15', 'dd', 'dde', 'mac_a15', '1', '0', '1530341213', '1530281068', '');
INSERT INTO `d_charge` VALUES ('1122', 'device_a14', 'dd', 'dde', 'mac_a14', '1', '0', '1530341213', '1530281068', '');
INSERT INTO `d_charge` VALUES ('1123', 'device_a13', 'dd', 'dde', 'mac_a13', '1', '0', '1530341213', '1530281068', '');
INSERT INTO `d_charge` VALUES ('1124', 'device_a12', 'dd', 'dde', 'mac_a12', '1', '0', '1530341213', '1530281068', '');
INSERT INTO `d_charge` VALUES ('1125', 'device_a11', 'dd', 'dde', 'mac_a11', '1', '0', '1530341213', '1530281068', '');
INSERT INTO `d_charge` VALUES ('1126', 'device_a10', 'dd', 'dde', 'mac_a10', '1', '0', '1530341213', '1530281068', '');
INSERT INTO `d_charge` VALUES ('1127', 'device_a9', 'dd', 'dde', 'mac_a9', '1', '0', '1530341213', '1530281068', '');
INSERT INTO `d_charge` VALUES ('1128', 'device_a8', 'dd', 'dde', 'mac_a8', '1', '0', '1530341213', '1530281068', '');
INSERT INTO `d_charge` VALUES ('1129', 'device_a7', 'dd', 'dde', 'mac_a7', '1', '0', '1530341213', '1530281068', '');
INSERT INTO `d_charge` VALUES ('1130', 'device_a6', 'dd', 'dde', 'mac_a6', '2', '458', '1531731641', '1530281068', '');
INSERT INTO `d_charge` VALUES ('1131', 'device_a5', 'dd', 'dde', 'mac_a5', '1', '0', '1530341213', '1530281068', '');
INSERT INTO `d_charge` VALUES ('1132', 'device_a4', 'dd', 'dde', 'mac_a4', '2', '382', '1531464010', '1530281068', '');
INSERT INTO `d_charge` VALUES ('1133', 'device_a3', 'dd', 'dde', 'mac_a3', '2', '347', '1531378953', '1530281068', '');
INSERT INTO `d_charge` VALUES ('1134', 'device_a2', 'dd', 'dde', 'mac_a2', '2', '345', '1531377481', '1530281068', '');
INSERT INTO `d_charge` VALUES ('1135', 'device_a1', 'dd', 'dde', 'mac_a1', '2', '290', '1531376189', '1530281068', '');
INSERT INTO `d_charge` VALUES ('1146', 'device_id', 'device_type', 'device_modle', 'device_mac', '1', '0', '0', '1532310939', 'remark');
INSERT INTO `d_charge` VALUES ('1147', 'device_id3', 'device_type3', 'device_modle2', 'device_mac3', '2', '3', '1532311564', '1532311312', '年后啊 ');

-- ----------------------------
-- Table structure for `d_charge_log`
-- ----------------------------
DROP TABLE IF EXISTS `d_charge_log`;
CREATE TABLE `d_charge_log` (
  `cl_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `op_type` varchar(40) DEFAULT '' COMMENT '操作类型 bind 绑定 unbind 解绑',
  `add_datetime` varchar(20) DEFAULT '' COMMENT '操作时间',
  `device_id` varchar(125) DEFAULT '',
  PRIMARY KEY (`cl_id`)
) ENGINE=InnoDB AUTO_INCREMENT=45 DEFAULT CHARSET=utf8 COMMENT='绑定设备记录';

-- ----------------------------
-- Records of d_charge_log
-- ----------------------------
INSERT INTO `d_charge_log` VALUES ('1', '5', 'bind', '1532075043', 'device_a199');
INSERT INTO `d_charge_log` VALUES ('2', '7', 'bind', '1532077850', 'device66');
INSERT INTO `d_charge_log` VALUES ('3', '12', 'bind', '1532079769', 'device_a178');
INSERT INTO `d_charge_log` VALUES ('4', '4', 'bind', '1532080303', 'device_a166');
INSERT INTO `d_charge_log` VALUES ('5', '11', 'bind', '1532081031', 'device_a177');
INSERT INTO `d_charge_log` VALUES ('6', '13', 'bind', '1532084129', 'device_a145');
INSERT INTO `d_charge_log` VALUES ('7', '16', 'bind', '1532084753', 'device_a299');
INSERT INTO `d_charge_log` VALUES ('8', '9', 'bind', '1532084973', 'device65');
INSERT INTO `d_charge_log` VALUES ('9', '1', 'bind', '1532136782', 'device_a500');
INSERT INTO `d_charge_log` VALUES ('10', '22', 'bind', '1532142735', 'device_a179');
INSERT INTO `d_charge_log` VALUES ('11', '14', 'bind', '1532143134', 'device_a161');
INSERT INTO `d_charge_log` VALUES ('12', '25', 'bind', '1532166479', 'device_a162');
INSERT INTO `d_charge_log` VALUES ('13', '23', 'bind', '1532166537', 'device_a163');
INSERT INTO `d_charge_log` VALUES ('14', '33', 'bind', '1532313386', 'device64');
INSERT INTO `d_charge_log` VALUES ('15', '35', 'bind', '1532315050', 'device63');
INSERT INTO `d_charge_log` VALUES ('16', '37', 'bind', '1532327164', 'device_test200');
INSERT INTO `d_charge_log` VALUES ('17', '39', 'bind', '1532333204', 'device_test224');
INSERT INTO `d_charge_log` VALUES ('18', '58', 'bind', '1532423832', 'device_a500');
INSERT INTO `d_charge_log` VALUES ('19', '61', 'bind', '1532512568', 'device_a188');
INSERT INTO `d_charge_log` VALUES ('20', '59', 'bind', '1532679465', 'device_a53');
INSERT INTO `d_charge_log` VALUES ('21', '41', 'bind', '1533121000', 'device_a68');
INSERT INTO `d_charge_log` VALUES ('22', '82', 'bind', '1534324873', 'device_a224');
INSERT INTO `d_charge_log` VALUES ('23', '82', 'unbind', '1534327484', 'afd17.device_a224.');
INSERT INTO `d_charge_log` VALUES ('24', '82', 'unbind', '1534328530', 'AA:CC:bb:2d:34');
INSERT INTO `d_charge_log` VALUES ('27', '82', 'unbind', '1534328884', 'device_a224');
INSERT INTO `d_charge_log` VALUES ('28', '82', 'unbind', '1534328900', 'device_a224');
INSERT INTO `d_charge_log` VALUES ('29', '84', 'bind', '1534753472', 'device_a224');
INSERT INTO `d_charge_log` VALUES ('30', '84', 'unbind', '1534753661', 'AA:CC:bb:2d:34');
INSERT INTO `d_charge_log` VALUES ('31', '84', 'bind', '1534753853', 'df8');
INSERT INTO `d_charge_log` VALUES ('32', '84', 'unbind', '1534754202', null);
INSERT INTO `d_charge_log` VALUES ('33', '84', 'unbind', '1534754818', 'mac76');
INSERT INTO `d_charge_log` VALUES ('34', '82', 'bind', '1535626529', null);
INSERT INTO `d_charge_log` VALUES ('35', '84', 'bind', '1535627149', null);
INSERT INTO `d_charge_log` VALUES ('36', '1', 'bind', '1535700948', null);
INSERT INTO `d_charge_log` VALUES ('37', '99', 'bind', '1535707373', null);
INSERT INTO `d_charge_log` VALUES ('38', '102', 'bind', '1535709599', null);
INSERT INTO `d_charge_log` VALUES ('39', '111', 'bind', '1535965398', 'device_a260');
INSERT INTO `d_charge_log` VALUES ('40', '111', 'bind', '1535966642', 'device_a261');
INSERT INTO `d_charge_log` VALUES ('41', '84', 'bind', '1535967070', 'device_a180');
INSERT INTO `d_charge_log` VALUES ('42', '84', 'bind', '1535967291', 'device_a181');
INSERT INTO `d_charge_log` VALUES ('43', '112', 'bind', '1535967592', 'device_a182');
INSERT INTO `d_charge_log` VALUES ('44', '112', 'bind', '1535967737', 'device_a183');

-- ----------------------------
-- Table structure for `d_config`
-- ----------------------------
DROP TABLE IF EXISTS `d_config`;
CREATE TABLE `d_config` (
  `config_id` int(11) NOT NULL AUTO_INCREMENT,
  `paramCord` varchar(125) DEFAULT '' COMMENT '字段名称',
  `value` int(15) DEFAULT '0' COMMENT '值',
  PRIMARY KEY (`config_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of d_config
-- ----------------------------
INSERT INTO `d_config` VALUES ('1', 'auditStatus', '0');

-- ----------------------------
-- Table structure for `d_console_access`
-- ----------------------------
DROP TABLE IF EXISTS `d_console_access`;
CREATE TABLE `d_console_access` (
  `access_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `role_id` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '权限ID',
  `menu_id` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '功能模块ID',
  `level` tinyint(1) unsigned NOT NULL DEFAULT '1' COMMENT '节点类型：1:表示应用（模块）；2:表示控制器；3：表示方法',
  PRIMARY KEY (`access_id`)
) ENGINE=MyISAM AUTO_INCREMENT=399 DEFAULT CHARSET=utf8 COMMENT='角色授权信息';

-- ----------------------------
-- Records of d_console_access
-- ----------------------------
INSERT INTO `d_console_access` VALUES ('397', '1', '109', '1');
INSERT INTO `d_console_access` VALUES ('396', '1', '117', '1');
INSERT INTO `d_console_access` VALUES ('395', '1', '98', '1');
INSERT INTO `d_console_access` VALUES ('394', '1', '97', '1');
INSERT INTO `d_console_access` VALUES ('393', '1', '118', '1');
INSERT INTO `d_console_access` VALUES ('392', '1', '100', '1');
INSERT INTO `d_console_access` VALUES ('391', '1', '99', '1');
INSERT INTO `d_console_access` VALUES ('390', '1', '6', '1');
INSERT INTO `d_console_access` VALUES ('389', '1', '4', '1');
INSERT INTO `d_console_access` VALUES ('27', '0', '0', '1');
INSERT INTO `d_console_access` VALUES ('28', '0', '0', '1');
INSERT INTO `d_console_access` VALUES ('29', '0', '0', '1');
INSERT INTO `d_console_access` VALUES ('30', '0', '0', '1');
INSERT INTO `d_console_access` VALUES ('31', '0', '0', '1');
INSERT INTO `d_console_access` VALUES ('32', '0', '0', '1');
INSERT INTO `d_console_access` VALUES ('33', '0', '0', '1');
INSERT INTO `d_console_access` VALUES ('34', '0', '0', '1');
INSERT INTO `d_console_access` VALUES ('35', '0', '0', '1');
INSERT INTO `d_console_access` VALUES ('36', '0', '0', '1');
INSERT INTO `d_console_access` VALUES ('37', '0', '0', '1');
INSERT INTO `d_console_access` VALUES ('388', '1', '3', '1');
INSERT INTO `d_console_access` VALUES ('387', '1', '2', '1');
INSERT INTO `d_console_access` VALUES ('386', '1', '8', '1');
INSERT INTO `d_console_access` VALUES ('385', '1', '5', '1');
INSERT INTO `d_console_access` VALUES ('384', '1', '9', '1');
INSERT INTO `d_console_access` VALUES ('383', '1', '7', '1');
INSERT INTO `d_console_access` VALUES ('382', '1', '1', '1');
INSERT INTO `d_console_access` VALUES ('381', '1', '111', '1');
INSERT INTO `d_console_access` VALUES ('380', '1', '112', '1');
INSERT INTO `d_console_access` VALUES ('379', '1', '113', '1');
INSERT INTO `d_console_access` VALUES ('378', '1', '104', '1');
INSERT INTO `d_console_access` VALUES ('377', '1', '114', '1');
INSERT INTO `d_console_access` VALUES ('376', '1', '102', '1');
INSERT INTO `d_console_access` VALUES ('375', '1', '103', '1');
INSERT INTO `d_console_access` VALUES ('374', '1', '101', '1');
INSERT INTO `d_console_access` VALUES ('373', '1', '95', '1');
INSERT INTO `d_console_access` VALUES ('372', '1', '96', '1');
INSERT INTO `d_console_access` VALUES ('371', '1', '94', '1');
INSERT INTO `d_console_access` VALUES ('370', '1', '106', '1');
INSERT INTO `d_console_access` VALUES ('369', '1', '108', '1');
INSERT INTO `d_console_access` VALUES ('368', '1', '107', '1');
INSERT INTO `d_console_access` VALUES ('367', '1', '105', '1');
INSERT INTO `d_console_access` VALUES ('366', '1', '116', '1');
INSERT INTO `d_console_access` VALUES ('365', '1', '115', '1');
INSERT INTO `d_console_access` VALUES ('398', '1', '110', '1');

-- ----------------------------
-- Table structure for `d_console_button`
-- ----------------------------
DROP TABLE IF EXISTS `d_console_button`;
CREATE TABLE `d_console_button` (
  `button_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '按钮ID',
  `button_code` varchar(50) DEFAULT NULL COMMENT '按钮编码',
  `button_value` varchar(50) DEFAULT NULL COMMENT '按钮值，用2的次方',
  `button_name` varchar(50) DEFAULT NULL COMMENT '按钮名字',
  PRIMARY KEY (`button_id`)
) ENGINE=MyISAM AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 COMMENT='按钮信息表';

-- ----------------------------
-- Records of d_console_button
-- ----------------------------
INSERT INTO `d_console_button` VALUES ('1', 'Select', '2', '查询');
INSERT INTO `d_console_button` VALUES ('2', 'Add', '4', '新增');
INSERT INTO `d_console_button` VALUES ('3', 'Edit', '8', '修改');
INSERT INTO `d_console_button` VALUES ('4', 'Delete', '16', '删除');
INSERT INTO `d_console_button` VALUES ('5', 'Audit', '32', '审核');
INSERT INTO `d_console_button` VALUES ('6', 'Export', '64', '导出');
INSERT INTO `d_console_button` VALUES ('7', 'Cancel', '128', '注销');
INSERT INTO `d_console_button` VALUES ('8', 'Enable', '256', '启用');
INSERT INTO `d_console_button` VALUES ('9', 'View', '512', '查看');

-- ----------------------------
-- Table structure for `d_console_menu`
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
) ENGINE=MyISAM AUTO_INCREMENT=119 DEFAULT CHARSET=utf8 COMMENT='后台菜单模块';

-- ----------------------------
-- Records of d_console_menu
-- ----------------------------
INSERT INTO `d_console_menu` VALUES ('1', '系统管理', null, null, '0', null, '60', '1', '1', '1', null, '0', null, '0', null, null, null, null);
INSERT INTO `d_console_menu` VALUES ('2', '后台用户管理', 'Backend/getUser', '/Backend/Backend/getUser', '1', null, '30', '1', '1', '3', ' ', '0', null, '0', '15', 'mbox', '1476149880', null);
INSERT INTO `d_console_menu` VALUES ('3', '后台角色管理', 'Backend/getRole', '/Backend/Backend/getRole', '1', null, '28', '1', '1', '3', ' ', '0', null, '0', '15', 'mbox', '1476149892', null);
INSERT INTO `d_console_menu` VALUES ('4', '后台菜单管理', 'Backend/getMenu', '/Backend/Backend/getMenu', '1', null, '27', '1', '1', '3', ' ', '0', null, '0', '15', 'mbox', '1476149905', null);
INSERT INTO `d_console_menu` VALUES ('5', 'Redis缓存', 'System/getSysRedis', '/Backend/System/getSysRedis', '1', null, '48', '1', '1', '3', ' ', '0', null, '0', '15', 'mbox', '1476149861', null);
INSERT INTO `d_console_menu` VALUES ('6', '敏感词', 'System/getSen', '/Backend/System/getSen', '1', null, '0', '1', '0', '3', null, '0', null, '0', null, null, null, null);
INSERT INTO `d_console_menu` VALUES ('8', '字典管理', 'System/getDic', '/Backend/System/getDic', '1', '', '47', '1', '1', '3', '', '0', '', '0', '15', 'mbox', '1476149937', '');
INSERT INTO `d_console_menu` VALUES ('7', '系统日志', 'System/getSysLog', '/Backend/System/getSysLog', '1', '', '100', '1', '1', '3', '', '0', '', '0', '15', 'mbox', '1476150611', '');
INSERT INTO `d_console_menu` VALUES ('9', '系统参数', 'System/getParameter', '/Backend/System/getParameter', '1', '', '49', '1', '1', '3', '系统参数 ', '2', '管理员', '1467351968', '15', 'mbox', '1476149851', '');
INSERT INTO `d_console_menu` VALUES ('94', '用户管理', 'User', '', '0', null, '88', '1', '1', '3', '用户管理模块', '2', 'liup', '1528702405', null, null, null, null);
INSERT INTO `d_console_menu` VALUES ('95', '用户信息列表', 'User/getUser', '/Backend/User/getUser', '94', null, '100', '1', '1', '3', ' 100  ', '2', 'liup', '1528702444', '2', null, '1528702655', null);
INSERT INTO `d_console_menu` VALUES ('96', '用户登录记录', 'User/getLogin', '/Backend/User/getLogin	', '94', null, '100', '1', '1', '3', ' 110', '2', 'liup', '1528706717', null, null, null, null);
INSERT INTO `d_console_menu` VALUES ('97', 'APP管理', 's', '', '0', null, '55', '1', '1', '3', ' 55', '2', 'liup', '1528709304', null, null, null, null);
INSERT INTO `d_console_menu` VALUES ('98', 'APP版本管理', 'Spread/getAppVer', '/Backend/Spread/getAppVer', '97', null, '69', '1', '1', '3', ' 89', '2', 'liup', '1528709365', null, null, null, null);
INSERT INTO `d_console_menu` VALUES ('99', '文案管理', 'd', '', '0', null, '55', '1', '1', '3', ' 55 ', '2', 'liup', '1528710751', '2', null, '1528768812', null);
INSERT INTO `d_console_menu` VALUES ('100', '首页广告图', 'Spread/getBanner', '/Backend/Spread/getBanner', '99', null, '110', '1', '1', '3', ' 110 ', '2', 'liup', '1528769224', '2', null, '1528963095', null);
INSERT INTO `d_console_menu` VALUES ('101', '用户邀请', 'User', '/Backend/User/getInvite', '94', null, '88', '1', '1', '3', ' 88', '2', 'liup', '1528943067', null, null, null, null);
INSERT INTO `d_console_menu` VALUES ('102', '意见反馈', 'User', '/Backend/Service/getProp', '94', null, '88', '1', '1', '3', ' 88', '2', 'liup', '1528945120', null, null, null, null);
INSERT INTO `d_console_menu` VALUES ('103', '实名审核', 'User', '/Backend/Audit/getRealName', '94', null, '88', '1', '1', '3', ' 88', '2', 'liup', '1528946355', null, null, null, null);
INSERT INTO `d_console_menu` VALUES ('104', '银行卡', 'User/Bank', '/Backend/Audit/getBank', '94', null, '0', '1', '1', '3', '    ', '2', 'liup', '1528956801', '2', null, '1528957644', null);
INSERT INTO `d_console_menu` VALUES ('105', '微信管理', 'weixin', 'weixin', '0', null, '166', '1', '0', '3', ' 166 ', '2', 'liup', '1529026825', '2', null, '1529026914', null);
INSERT INTO `d_console_menu` VALUES ('106', '微信分享', 'weixin', '/Backend/Spread/getShare', '105', null, '168', '1', '1', '3', ' 168 ', '2', 'liup', '1529026885', '2', null, '1529027395', null);
INSERT INTO `d_console_menu` VALUES ('107', '微信回复', 'weixin', '/Backend/Spread/getWxReplys', '105', null, '175', '1', '1', '3', ' 175 ', '2', 'liup', '1529027059', '2', null, '1529027769', null);
INSERT INTO `d_console_menu` VALUES ('108', '微信菜单管理', 'weixin', '/Backend/Spread/getWxMenu', '105', null, '174', '1', '1', '3', ' 174 ', '2', 'liup', '1529027095', '2', null, '1529027725', null);
INSERT INTO `d_console_menu` VALUES ('109', '运营管理', 'dd', '', '0', null, '0', '0', '0', '3', '  ', '2', 'liup', '1530510244', '65', null, '1531731767', null);
INSERT INTO `d_console_menu` VALUES ('110', '积分模块', 'start/index', '/Backend/Operate/starModuleList', '109', null, '158', '1', '1', '3', '   ', '2', 'liup', '1530510278', '2', null, '1530511076', null);
INSERT INTO `d_console_menu` VALUES ('111', '用户上传文件', 'User/getUserUpload', '/Backend/User/getUserUpload', '94', null, '0', '1', '1', '3', ' ', '2', 'liup', '1531453052', null, null, null, null);
INSERT INTO `d_console_menu` VALUES ('112', '用户的地址', 'User/getAddress', '/Backend/User/getAddress', '94', null, '0', '1', '1', '3', '  ', '2', 'liup', '1531453083', '65', null, '1531706024', null);
INSERT INTO `d_console_menu` VALUES ('113', '通讯录', 'User/getUserUpload', '/Backend/User/getMaileList', '94', null, '0', '1', '1', '3', '    ', '2', 'liup', '1531453123', '65', null, '1531706091', null);
INSERT INTO `d_console_menu` VALUES ('114', '基因数据', 'User/gene', '/Backend/User/gene', '94', null, '11', '1', '1', '3', ' dd  ', '65', 'xupeiqin', '1531969333', '65', null, '1531970086', null);
INSERT INTO `d_console_menu` VALUES ('115', '设备管理', 'charge', '', '0', null, '188', '1', '1', '3', '   ', '65', 'xupeiqin', '1532072628', '65', null, '1532072755', null);
INSERT INTO `d_console_menu` VALUES ('116', '设备列表', 'charge/list', '/Backend/Operate/chargeList', '115', null, '55', '1', '1', '3', '  ', '65', 'xupeiqin', '1532072698', '65', null, '1532076037', null);
INSERT INTO `d_console_menu` VALUES ('117', 'IOS审核状态', 'Index/auditStatus', '/Backend/Index/auditStatus', '97', null, '0', '1', '1', '3', '  ', '65', 'xupeiqin', '1533029058', '65', null, '1533029318', null);
INSERT INTO `d_console_menu` VALUES ('118', '审核优惠券', 'index/getCouponList', '/Backend/Index/getCouponList', '99', null, '0', '1', '1', '3', '  ', '65', 'xupeiqin', '1533030609', '65', null, '1533030649', null);

-- ----------------------------
-- Table structure for `d_console_role`
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
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='后台角色表';

-- ----------------------------
-- Records of d_console_role
-- ----------------------------
INSERT INTO `d_console_role` VALUES ('1', null, '管理员', '管理员', '1', '2', '管理员', '1415197462', null, null, null, '127.0.0.1');
INSERT INTO `d_console_role` VALUES ('2', null, null, null, '0', '2', 'liup', '1531452795', null, null, null, null);
INSERT INTO `d_console_role` VALUES ('3', null, '普通员工', '测试', '1', '2', 'liup', '1531452941', null, null, null, null);

-- ----------------------------
-- Table structure for `d_console_rolefunction`
-- ----------------------------
DROP TABLE IF EXISTS `d_console_rolefunction`;
CREATE TABLE `d_console_rolefunction` (
  `rolefunction_id` int(11) DEFAULT NULL,
  `role_id` int(11) DEFAULT NULL,
  `function_id` int(11) DEFAULT NULL COMMENT '功能ID'
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='角色功能表';

-- ----------------------------
-- Records of d_console_rolefunction
-- ----------------------------

-- ----------------------------
-- Table structure for `d_console_roleuser`
-- ----------------------------
DROP TABLE IF EXISTS `d_console_roleuser`;
CREATE TABLE `d_console_roleuser` (
  `role_user_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '角色人员ID',
  `user_id` int(11) DEFAULT NULL COMMENT '用户ID',
  `role_id` int(11) DEFAULT NULL COMMENT '角色ID',
  PRIMARY KEY (`role_user_id`)
) ENGINE=MyISAM AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COMMENT='角色与用户对应表';

-- ----------------------------
-- Records of d_console_roleuser
-- ----------------------------
INSERT INTO `d_console_roleuser` VALUES ('1', '1', '1');
INSERT INTO `d_console_roleuser` VALUES ('2', '2', '1');
INSERT INTO `d_console_roleuser` VALUES ('3', null, null);
INSERT INTO `d_console_roleuser` VALUES ('4', null, null);
INSERT INTO `d_console_roleuser` VALUES ('5', '65', '1');

-- ----------------------------
-- Table structure for `d_console_user`
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
) ENGINE=MyISAM AUTO_INCREMENT=69 DEFAULT CHARSET=utf8 COMMENT='后台用户';

-- ----------------------------
-- Records of d_console_user
-- ----------------------------
INSERT INTO `d_console_user` VALUES ('1', 'admin', '', '8dd54ee89a8bba1ca1863897dea45827', 'MB3eSV4c3y4BPgQQoq1LJw==', '1', '13751186805', null, '1', '', '2', '管理员', '1452157444', '1', 'admin', '1527664732');
INSERT INTO `d_console_user` VALUES ('2', 'liup', '', '8dd54ee89a8bba1ca1863897dea45827', 'MB3eSV4c3y4BPgQQoq1LJw==', '1', '13751172222', null, '1', '', '2', '管理员', '1452158134', '1', 'admin', '1527664756');
INSERT INTO `d_console_user` VALUES ('65', 'xupeiqin', '', '8dd54ee89a8bba1ca1863897dea45827', 'xYUpiUAEVn1+9hkg9yt8qg==', '1', '13169601345', null, '1', 'vcha', '2', 'liup', '1528272684', '2', 'liup', '1528273601');
INSERT INTO `d_console_user` VALUES ('66', 'af', '', '8dd54ee89a8bba1ca1863897dea45827', 'wscaveaGcLEkBqcdqXWEzfduAkWRM9ppo+tP9dcOveU=', '1', 'afh', null, '1', 'af', '2', 'liup', '1531448216', '2', 'liup', '1531448227');
INSERT INTO `d_console_user` VALUES ('67', 'liup', '', '8dd54ee89a8bba1ca1863897dea45827', 'lPn0VBTclnmp9XrWEvhc96qmQyvn7zaFY22FfCBPn6pcQwUl6Pxu2l4uxm7t9l+pvRG5P+jotPbpXwcUSPFRpg==', '2', '13169601345', null, '1', 'CS', '2', 'liup', '1531452398', '2', 'liup', '1531452454');
INSERT INTO `d_console_user` VALUES ('68', 'CES', '', '8dd54ee89a8bba1ca1863897dea45827', 'E8R7/mVzX+la05xmrI0t3g==', '1', 'AD', null, '1', 'D', '2', 'liup', '1531452430', null, null, null);

-- ----------------------------
-- Table structure for `d_coupon`
-- ----------------------------
DROP TABLE IF EXISTS `d_coupon`;
CREATE TABLE `d_coupon` (
  `cou_id` int(11) NOT NULL AUTO_INCREMENT,
  `coupon_name` varchar(125) DEFAULT '' COMMENT '优惠券的名字',
  `coupon_num` int(11) DEFAULT '100' COMMENT '优惠券的数量',
  `start_datetime` int(25) DEFAULT '0' COMMENT '开始时间',
  `end_datetime` int(25) DEFAULT '0' COMMENT '结束时间',
  `coupon_icon` varchar(255) DEFAULT '' COMMENT '图标的logo',
  `coupon_type` int(5) DEFAULT '1' COMMENT '类型 1 满减 暂时只有满减',
  `quota_max` decimal(15,2) DEFAULT '0.00' COMMENT '最大额度 满多少减多少',
  `quota_discount` decimal(15,2) DEFAULT '0.00' COMMENT '优惠多少',
  `add_datetime` int(20) DEFAULT '0' COMMENT '添加时间',
  `sort` int(20) DEFAULT '0' COMMENT '排序',
  `remark` text COMMENT '备注',
  PRIMARY KEY (`cou_id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of d_coupon
-- ----------------------------
INSERT INTO `d_coupon` VALUES ('8', '肯德基优惠券', '100', '1533052800', '1534521600', '/upload/banner/201808/201808015b61299ece932.png', '1', '18.00', '2.00', '1533094443', '33', '仅限北京,上海,广州,深圳使用');
INSERT INTO `d_coupon` VALUES ('10', '麦当劳优惠券', '100', '1533052800', '1535644800', '/upload/banner/201808/201808015b612a9fec624.png', '1', '25.00', '5.00', '1533094653', '0', '仅限北京,上海,广州,深圳使用');
INSERT INTO `d_coupon` VALUES ('20', '星巴克优惠券', '100', '1533052800', '1535644800', '/upload/banner/201808/201808015b612b2581190.png', '1', '20.00', '3.00', '1533103251', '0', '仅限北京,上海,广州,深圳使用');

-- ----------------------------
-- Table structure for `d_coupon_user`
-- ----------------------------
DROP TABLE IF EXISTS `d_coupon_user`;
CREATE TABLE `d_coupon_user` (
  `cou_user_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT '0' COMMENT '用户的ID',
  `coupon_id` int(11) DEFAULT '0' COMMENT '优惠券的ID',
  `add_datetime` int(15) DEFAULT '0' COMMENT '添加时间',
  PRIMARY KEY (`cou_user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=109 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of d_coupon_user
-- ----------------------------
INSERT INTO `d_coupon_user` VALUES ('1', '53', '4', '0');
INSERT INTO `d_coupon_user` VALUES ('2', '53', '4', '1533038226');
INSERT INTO `d_coupon_user` VALUES ('3', '53', '4', '1533038245');
INSERT INTO `d_coupon_user` VALUES ('4', '37', '20', '1533107902');
INSERT INTO `d_coupon_user` VALUES ('5', '37', '8', '1533108815');
INSERT INTO `d_coupon_user` VALUES ('6', '37', '15', '1533109073');
INSERT INTO `d_coupon_user` VALUES ('7', '37', '8', '1533109076');
INSERT INTO `d_coupon_user` VALUES ('8', '37', '15', '1533109791');
INSERT INTO `d_coupon_user` VALUES ('9', '37', '10', '1533109794');
INSERT INTO `d_coupon_user` VALUES ('10', '37', '10', '1533109942');
INSERT INTO `d_coupon_user` VALUES ('11', '37', '8', '1533109945');
INSERT INTO `d_coupon_user` VALUES ('12', '37', '15', '1533110393');
INSERT INTO `d_coupon_user` VALUES ('13', '37', '20', '1533110405');
INSERT INTO `d_coupon_user` VALUES ('14', '37', '10', '1533110578');
INSERT INTO `d_coupon_user` VALUES ('15', '37', '8', '1533111258');
INSERT INTO `d_coupon_user` VALUES ('16', '37', '20', '1533111260');
INSERT INTO `d_coupon_user` VALUES ('17', '37', '15', '1533111261');
INSERT INTO `d_coupon_user` VALUES ('18', '37', '10', '1533111263');
INSERT INTO `d_coupon_user` VALUES ('19', '37', '8', '1533111318');
INSERT INTO `d_coupon_user` VALUES ('20', '37', '8', '1533111828');
INSERT INTO `d_coupon_user` VALUES ('21', '37', '15', '1533111996');
INSERT INTO `d_coupon_user` VALUES ('22', '37', '20', '1533112004');
INSERT INTO `d_coupon_user` VALUES ('23', '41', '15', '1533113930');
INSERT INTO `d_coupon_user` VALUES ('24', '41', '10', '1533114049');
INSERT INTO `d_coupon_user` VALUES ('25', '41', '15', '1533114057');
INSERT INTO `d_coupon_user` VALUES ('26', '41', '20', '1533114124');
INSERT INTO `d_coupon_user` VALUES ('27', '37', '10', '1533114155');
INSERT INTO `d_coupon_user` VALUES ('28', '41', '20', '1533115122');
INSERT INTO `d_coupon_user` VALUES ('29', '37', '15', '1533115361');
INSERT INTO `d_coupon_user` VALUES ('30', '37', '10', '1533115574');
INSERT INTO `d_coupon_user` VALUES ('31', '41', '15', '1533115629');
INSERT INTO `d_coupon_user` VALUES ('32', '41', '20', '1533115643');
INSERT INTO `d_coupon_user` VALUES ('33', '41', '8', '1533115655');
INSERT INTO `d_coupon_user` VALUES ('34', '41', '15', '1533115685');
INSERT INTO `d_coupon_user` VALUES ('35', '41', '15', '1533115689');
INSERT INTO `d_coupon_user` VALUES ('36', '41', '20', '1533115700');
INSERT INTO `d_coupon_user` VALUES ('37', '37', '20', '1533115705');
INSERT INTO `d_coupon_user` VALUES ('38', '37', '10', '1533115812');
INSERT INTO `d_coupon_user` VALUES ('39', '37', '8', '1533115953');
INSERT INTO `d_coupon_user` VALUES ('40', '37', '20', '1533115958');
INSERT INTO `d_coupon_user` VALUES ('41', '37', '15', '1533115970');
INSERT INTO `d_coupon_user` VALUES ('42', '41', '10', '1533116029');
INSERT INTO `d_coupon_user` VALUES ('43', '41', '15', '1533116031');
INSERT INTO `d_coupon_user` VALUES ('44', '41', '10', '1533116079');
INSERT INTO `d_coupon_user` VALUES ('45', '78', '20', '1533116327');
INSERT INTO `d_coupon_user` VALUES ('46', '37', '8', '1533116404');
INSERT INTO `d_coupon_user` VALUES ('47', '37', '20', '1533116467');
INSERT INTO `d_coupon_user` VALUES ('48', '37', '20', '1533117116');
INSERT INTO `d_coupon_user` VALUES ('49', '37', '15', '1533117118');
INSERT INTO `d_coupon_user` VALUES ('50', '37', '10', '1533117120');
INSERT INTO `d_coupon_user` VALUES ('51', '37', '10', '1533117187');
INSERT INTO `d_coupon_user` VALUES ('52', '78', '15', '1533117543');
INSERT INTO `d_coupon_user` VALUES ('53', '78', '20', '1533117559');
INSERT INTO `d_coupon_user` VALUES ('54', '78', '10', '1533117563');
INSERT INTO `d_coupon_user` VALUES ('55', '78', '10', '1533117976');
INSERT INTO `d_coupon_user` VALUES ('56', '78', '22', '1533118729');
INSERT INTO `d_coupon_user` VALUES ('57', '78', '8', '1533118733');
INSERT INTO `d_coupon_user` VALUES ('58', '78', '10', '1533118741');
INSERT INTO `d_coupon_user` VALUES ('59', '78', '10', '1533118749');
INSERT INTO `d_coupon_user` VALUES ('60', '37', '8', '1533120550');
INSERT INTO `d_coupon_user` VALUES ('61', '33', '8', '1533120665');
INSERT INTO `d_coupon_user` VALUES ('62', '33', '20', '1533120668');
INSERT INTO `d_coupon_user` VALUES ('63', '33', '8', '1533120679');
INSERT INTO `d_coupon_user` VALUES ('64', '33', '20', '1533120680');
INSERT INTO `d_coupon_user` VALUES ('65', '33', '20', '1533120690');
INSERT INTO `d_coupon_user` VALUES ('66', '33', '10', '1533120696');
INSERT INTO `d_coupon_user` VALUES ('67', '33', '21', '1533120704');
INSERT INTO `d_coupon_user` VALUES ('68', '33', '20', '1533120719');
INSERT INTO `d_coupon_user` VALUES ('69', '33', '20', '1533120722');
INSERT INTO `d_coupon_user` VALUES ('70', '33', '8', '1533120724');
INSERT INTO `d_coupon_user` VALUES ('71', '37', '10', '1533120728');
INSERT INTO `d_coupon_user` VALUES ('72', '33', '20', '1533120741');
INSERT INTO `d_coupon_user` VALUES ('73', '78', '20', '1533121152');
INSERT INTO `d_coupon_user` VALUES ('74', '78', '10', '1533121159');
INSERT INTO `d_coupon_user` VALUES ('75', '78', '8', '1533121167');
INSERT INTO `d_coupon_user` VALUES ('76', '78', '10', '1533121174');
INSERT INTO `d_coupon_user` VALUES ('77', '78', '20', '1533121181');
INSERT INTO `d_coupon_user` VALUES ('78', '78', '10', '1533121187');
INSERT INTO `d_coupon_user` VALUES ('79', '78', '20', '1533121377');
INSERT INTO `d_coupon_user` VALUES ('80', '37', '8', '1533193978');
INSERT INTO `d_coupon_user` VALUES ('81', '37', '10', '1533193986');
INSERT INTO `d_coupon_user` VALUES ('82', '37', '8', '1533200999');
INSERT INTO `d_coupon_user` VALUES ('83', '37', '20', '1533201002');
INSERT INTO `d_coupon_user` VALUES ('84', '37', '20', '1533202143');
INSERT INTO `d_coupon_user` VALUES ('85', '37', '20', '1533202148');
INSERT INTO `d_coupon_user` VALUES ('86', '37', '10', '1533204670');
INSERT INTO `d_coupon_user` VALUES ('87', '37', '8', '1533264606');
INSERT INTO `d_coupon_user` VALUES ('88', '37', '20', '1533264635');
INSERT INTO `d_coupon_user` VALUES ('89', '37', '10', '1533264645');
INSERT INTO `d_coupon_user` VALUES ('90', '37', '8', '1533264676');
INSERT INTO `d_coupon_user` VALUES ('91', '37', '8', '1533717156');
INSERT INTO `d_coupon_user` VALUES ('92', '37', '10', '1533718180');
INSERT INTO `d_coupon_user` VALUES ('93', '37', '20', '1533718185');
INSERT INTO `d_coupon_user` VALUES ('94', '37', '20', '1533718574');
INSERT INTO `d_coupon_user` VALUES ('95', '37', '8', '1533718744');
INSERT INTO `d_coupon_user` VALUES ('96', '79', '10', '1533889940');
INSERT INTO `d_coupon_user` VALUES ('97', '79', '20', '1533889972');
INSERT INTO `d_coupon_user` VALUES ('98', '79', '10', '1533889977');
INSERT INTO `d_coupon_user` VALUES ('99', '86', '10', '1534491653');
INSERT INTO `d_coupon_user` VALUES ('100', '86', '10', '1534491660');
INSERT INTO `d_coupon_user` VALUES ('101', '86', '8', '1534491665');
INSERT INTO `d_coupon_user` VALUES ('102', '86', '8', '1534498634');
INSERT INTO `d_coupon_user` VALUES ('103', '86', '20', '1534743703');
INSERT INTO `d_coupon_user` VALUES ('104', '86', '8', '1534743704');
INSERT INTO `d_coupon_user` VALUES ('105', '86', '8', '1534743757');
INSERT INTO `d_coupon_user` VALUES ('106', '86', '20', '1534743760');
INSERT INTO `d_coupon_user` VALUES ('107', '86', '20', '1534743828');
INSERT INTO `d_coupon_user` VALUES ('108', '86', '20', '1534743841');

-- ----------------------------
-- Table structure for `d_gene`
-- ----------------------------
DROP TABLE IF EXISTS `d_gene`;
CREATE TABLE `d_gene` (
  `gene_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(125) DEFAULT '' COMMENT '名字',
  `birth_date` int(25) DEFAULT '0' COMMENT '生日',
  `native_place` varchar(255) DEFAULT '' COMMENT '出生地',
  `exercise_time` int(25) DEFAULT '0' COMMENT '运动时间 以秒为单位',
  `add_datetime` int(30) DEFAULT '0' COMMENT '添加时间',
  `user_id` int(11) DEFAULT '0' COMMENT '用户的ID',
  `sex` int(3) DEFAULT '0' COMMENT '性别 0 女 1男',
  `ill` varchar(255) DEFAULT '无' COMMENT '遗传病',
  `height` double(12,2) DEFAULT '0.00' COMMENT '身高 单位米',
  `weight` double(12,2) DEFAULT '0.00' COMMENT '体重 单位千克',
  PRIMARY KEY (`gene_id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8 COMMENT='基本的基因数据';

-- ----------------------------
-- Records of d_gene
-- ----------------------------
INSERT INTO `d_gene` VALUES ('1', '我们', '17313444', '北京,北京,东城区', '3600', '1532078244', '7', '1', null, null, null);
INSERT INTO `d_gene` VALUES ('2', '测试啊', '17372272', '北京,北京,东城区', '10801', '1532137072', '2', '1', null, null, null);
INSERT INTO `d_gene` VALUES ('3', 'Liu', '17556923', '北京,北京,东城区', '3600', '1532321723', '37', '1', null, null, null);
INSERT INTO `d_gene` VALUES ('4', 'lzy', '1532348280', '北京北京东城区', '3600', '1532341086', '4', '1', null, null, null);
INSERT INTO `d_gene` VALUES ('5', '你是谁呀', '1532434680', '北京,北京,东城区', '10801', '1532399453', '27', '1', null, null, null);
INSERT INTO `d_gene` VALUES ('11', '许培钦', '869810692', '广东普宁', '888888', '1532498692', '53', '1', '无', '111.33', '2555.25');
INSERT INTO `d_gene` VALUES ('12', '张三', '1532521080', '北京北京朝阳区', '7200', '1532501274', '69', '1', '无', '1.75', '50.00');
INSERT INTO `d_gene` VALUES ('13', '测试', '1532521080', '北京北京平谷区', '11000', '1532501684', '70', '1', '无', '1.75', '58.00');
INSERT INTO `d_gene` VALUES ('14', '这么', '1532521080', '北京,北京,东城区', '3600', '1532510786', '61', '1', '否', '1.75', '67.00');
INSERT INTO `d_gene` VALUES ('15', '李', '1532689200', '北京,北京,东城区', '3600', '1532680975', '23', '1', '无', '1.99', '99.00');
INSERT INTO `d_gene` VALUES ('16', '这是', '1533125880', '台湾,台湾,澎湖县', '3600', '1533116520', '78', '1', '没有', '1.90', '180.00');
INSERT INTO `d_gene` VALUES ('19', 'luo', '1535445040', '广东深圳', '3620', '1535447812', '84', '1', '无', '1.68', '65.00');
INSERT INTO `d_gene` VALUES ('20', 'luo', '1535445040', '广东深圳', '3620', '1535954294', '111', '1', '无', '1.68', '65.00');

-- ----------------------------
-- Table structure for `d_home_banner`
-- ----------------------------
DROP TABLE IF EXISTS `d_home_banner`;
CREATE TABLE `d_home_banner` (
  `banner_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '论滚图ID',
  `banner_name` varchar(50) DEFAULT '' COMMENT '路滚图名字',
  `dic_type` int(11) DEFAULT '0' COMMENT '类型 字典值',
  `dic_name` varchar(50) DEFAULT '' COMMENT '类型名称',
  `banner_url` varchar(100) DEFAULT '' COMMENT '图片路径',
  `link_url` text COMMENT '链接URL',
  `banner_title` varchar(100) DEFAULT '' COMMENT 'title',
  `in_link` tinyint(4) DEFAULT '1' COMMENT '是否内链 0 非内链 1 内链',
  `banner_sort` int(4) DEFAULT '0' COMMENT '排序',
  `banner_status` tinyint(4) DEFAULT '1' COMMENT ' 轮滚图状态 0 删除 1 启用',
  `banner_starttime` int(11) unsigned DEFAULT '0' COMMENT '0 不限制开始时间  ',
  `banner_endTime` int(11) unsigned DEFAULT '0' COMMENT '0 不限制结束时间  ',
  `remark` varchar(2000) DEFAULT '',
  `add_user_id` int(11) NOT NULL COMMENT '创建人ID',
  `add_user_name` varchar(50) DEFAULT '' COMMENT '创建人',
  `add_datetime` int(11) NOT NULL COMMENT '创建时间',
  `edit_user_id` int(11) DEFAULT '0' COMMENT '修改人ID',
  `edit_user_name` varchar(50) DEFAULT '' COMMENT '修改人',
  `edit_datetime` int(11) DEFAULT '0' COMMENT '修改时间',
  `operate_ip` varchar(50) DEFAULT '' COMMENT '操作IP',
  PRIMARY KEY (`banner_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='广告图片管理';

-- ----------------------------
-- Records of d_home_banner
-- ----------------------------
INSERT INTO `d_home_banner` VALUES ('1', ' 你好', '167', '', '/upload/banner/201807/201807265b59a733140dd.png', 'http://www.baidu.com', 'dd', '0', '55', '1', '1531929600', '0', '&amp;lt;p&amp;gt;ddd&amp;lt;/p&amp;gt;', '65', 'xupeiqin', '1532602182', '0', '', '0', '');
INSERT INTO `d_home_banner` VALUES ('2', 'sddf', '167', '', '/upload/banner/201807/201807265b59a7b2f40da.png', 'http://www.baidu.com', '', '0', '12', '1', '1532448000', '0', '&amp;lt;p&amp;gt;adf&amp;lt;/p&amp;gt;', '65', 'xupeiqin', '1532602296', '0', '', '0', '');

-- ----------------------------
-- Table structure for `d_sms_send_del`
-- ----------------------------
DROP TABLE IF EXISTS `d_sms_send_del`;
CREATE TABLE `d_sms_send_del` (
  `sms_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '短信ID',
  `receive_user_id` int(11) DEFAULT NULL COMMENT '接收人',
  `receive_phone` varchar(50) DEFAULT NULL COMMENT '接收号码',
  `send_content` varchar(500) DEFAULT NULL COMMENT '发送内容',
  `send_date_time` int(11) DEFAULT NULL,
  `sms_type` varchar(50) DEFAULT NULL COMMENT '短信类型：投标成功，融资成功，还款成功，收到回款',
  `send_interface` varchar(50) DEFAULT NULL COMMENT '发送短信的接口',
  `remark` varchar(50) DEFAULT '' COMMENT '备注',
  PRIMARY KEY (`sms_id`),
  KEY `index_name` (`receive_phone`) USING BTREE
) ENGINE=MyISAM AUTO_INCREMENT=83 DEFAULT CHARSET=utf8 COMMENT='短信发送历史记录';

-- ----------------------------
-- Records of d_sms_send_del
-- ----------------------------
INSERT INTO `d_sms_send_del` VALUES ('4', null, null, null, null, null, null, '');
INSERT INTO `d_sms_send_del` VALUES ('5', null, null, null, null, null, null, '');
INSERT INTO `d_sms_send_del` VALUES ('6', null, null, null, null, null, null, '');
INSERT INTO `d_sms_send_del` VALUES ('7', null, null, null, null, null, null, '');
INSERT INTO `d_sms_send_del` VALUES ('8', null, null, null, null, null, null, '');
INSERT INTO `d_sms_send_del` VALUES ('1', '397725', '13169601345', '恭喜您注册成功!68元红包已到账，还有600元红包等着您！下载APP( t.cn/RITGwWJ )轻松查看', '1528355255', 'Reg_Success', '希奥', '');
INSERT INTO `d_sms_send_del` VALUES ('2', '397726', '13169601345', '恭喜您注册成功!68元红包已到账，还有600元红包等着您！下载APP( t.cn/RITGwWJ )轻松查看', '1528355321', 'Reg_Success', '希奥', '');
INSERT INTO `d_sms_send_del` VALUES ('3', '397727', '13169601345', '恭喜您注册成功!68元红包已到账，还有600元红包等着您！下载APP( t.cn/RITGwWJ )轻松查看', '1528355808', 'Reg_Success', '希奥', '');
INSERT INTO `d_sms_send_del` VALUES ('9', null, null, null, null, null, null, '');
INSERT INTO `d_sms_send_del` VALUES ('10', null, null, null, null, null, null, '');
INSERT INTO `d_sms_send_del` VALUES ('11', null, null, null, null, null, null, '');
INSERT INTO `d_sms_send_del` VALUES ('12', null, null, null, null, null, null, '');
INSERT INTO `d_sms_send_del` VALUES ('13', null, null, null, null, null, null, '');
INSERT INTO `d_sms_send_del` VALUES ('14', null, null, null, null, null, null, '');
INSERT INTO `d_sms_send_del` VALUES ('15', null, null, null, null, null, null, '');
INSERT INTO `d_sms_send_del` VALUES ('16', null, null, null, null, null, null, '');
INSERT INTO `d_sms_send_del` VALUES ('17', null, null, null, null, null, null, '');
INSERT INTO `d_sms_send_del` VALUES ('18', null, null, null, null, null, null, '');
INSERT INTO `d_sms_send_del` VALUES ('19', null, null, null, null, null, null, '');
INSERT INTO `d_sms_send_del` VALUES ('20', null, null, null, null, null, null, '');
INSERT INTO `d_sms_send_del` VALUES ('21', null, null, null, null, null, null, '');
INSERT INTO `d_sms_send_del` VALUES ('22', null, null, null, null, null, null, '');
INSERT INTO `d_sms_send_del` VALUES ('23', null, null, null, null, null, null, '');
INSERT INTO `d_sms_send_del` VALUES ('24', null, null, null, null, null, null, '');
INSERT INTO `d_sms_send_del` VALUES ('25', null, null, null, null, null, null, '');
INSERT INTO `d_sms_send_del` VALUES ('26', null, null, null, null, null, null, '');
INSERT INTO `d_sms_send_del` VALUES ('27', null, null, null, null, null, null, '');
INSERT INTO `d_sms_send_del` VALUES ('28', null, null, null, null, null, null, '');
INSERT INTO `d_sms_send_del` VALUES ('29', null, null, null, null, null, null, '');
INSERT INTO `d_sms_send_del` VALUES ('30', null, null, null, null, null, null, '');
INSERT INTO `d_sms_send_del` VALUES ('31', null, null, null, null, null, null, '');
INSERT INTO `d_sms_send_del` VALUES ('32', null, null, null, null, null, null, '');
INSERT INTO `d_sms_send_del` VALUES ('33', null, null, null, null, null, null, '');
INSERT INTO `d_sms_send_del` VALUES ('34', null, null, null, null, null, null, '');
INSERT INTO `d_sms_send_del` VALUES ('35', null, null, null, null, null, null, '');
INSERT INTO `d_sms_send_del` VALUES ('36', null, null, null, null, null, null, '');
INSERT INTO `d_sms_send_del` VALUES ('37', null, null, null, null, null, null, '');
INSERT INTO `d_sms_send_del` VALUES ('38', null, null, null, null, null, null, '');
INSERT INTO `d_sms_send_del` VALUES ('39', null, null, null, null, null, null, '');
INSERT INTO `d_sms_send_del` VALUES ('40', null, null, null, null, null, null, '');
INSERT INTO `d_sms_send_del` VALUES ('41', null, null, null, null, null, null, '');
INSERT INTO `d_sms_send_del` VALUES ('42', null, null, null, null, null, null, '');
INSERT INTO `d_sms_send_del` VALUES ('43', null, null, null, null, null, null, '');
INSERT INTO `d_sms_send_del` VALUES ('44', null, null, null, null, null, null, '');
INSERT INTO `d_sms_send_del` VALUES ('45', null, null, null, null, null, null, '');
INSERT INTO `d_sms_send_del` VALUES ('46', null, null, null, null, null, null, '');
INSERT INTO `d_sms_send_del` VALUES ('47', null, null, null, null, null, null, '');
INSERT INTO `d_sms_send_del` VALUES ('48', null, null, null, null, null, null, '');
INSERT INTO `d_sms_send_del` VALUES ('49', null, null, null, null, null, null, '');
INSERT INTO `d_sms_send_del` VALUES ('50', null, null, null, null, null, null, '');
INSERT INTO `d_sms_send_del` VALUES ('51', null, null, null, null, null, null, '');
INSERT INTO `d_sms_send_del` VALUES ('52', null, null, null, null, null, null, '');
INSERT INTO `d_sms_send_del` VALUES ('53', null, null, null, null, null, null, '');
INSERT INTO `d_sms_send_del` VALUES ('54', null, null, null, null, null, null, '');
INSERT INTO `d_sms_send_del` VALUES ('55', null, null, null, null, null, null, '');
INSERT INTO `d_sms_send_del` VALUES ('56', null, null, null, null, null, null, '');
INSERT INTO `d_sms_send_del` VALUES ('57', null, null, null, null, null, null, '');
INSERT INTO `d_sms_send_del` VALUES ('58', null, null, null, null, null, null, '');
INSERT INTO `d_sms_send_del` VALUES ('59', null, null, null, null, null, null, '');
INSERT INTO `d_sms_send_del` VALUES ('60', null, null, null, null, null, null, '');
INSERT INTO `d_sms_send_del` VALUES ('61', null, null, null, null, null, null, '');
INSERT INTO `d_sms_send_del` VALUES ('62', null, null, null, null, null, null, '');
INSERT INTO `d_sms_send_del` VALUES ('63', null, null, null, null, null, null, '');
INSERT INTO `d_sms_send_del` VALUES ('64', null, null, null, null, null, null, '');
INSERT INTO `d_sms_send_del` VALUES ('65', null, null, null, null, null, null, '');
INSERT INTO `d_sms_send_del` VALUES ('66', null, null, null, null, null, null, '');
INSERT INTO `d_sms_send_del` VALUES ('67', null, null, null, null, null, null, '');
INSERT INTO `d_sms_send_del` VALUES ('68', null, null, null, null, null, null, '');
INSERT INTO `d_sms_send_del` VALUES ('69', null, null, null, null, null, null, '');
INSERT INTO `d_sms_send_del` VALUES ('70', null, null, null, null, null, null, '');
INSERT INTO `d_sms_send_del` VALUES ('71', null, null, null, null, null, null, '');
INSERT INTO `d_sms_send_del` VALUES ('72', null, null, null, null, null, null, '');
INSERT INTO `d_sms_send_del` VALUES ('73', null, null, null, null, null, null, '');
INSERT INTO `d_sms_send_del` VALUES ('74', null, null, null, null, null, null, '');
INSERT INTO `d_sms_send_del` VALUES ('75', null, null, null, null, null, null, '');
INSERT INTO `d_sms_send_del` VALUES ('76', null, null, null, null, null, null, '');
INSERT INTO `d_sms_send_del` VALUES ('77', null, null, null, null, null, null, '');
INSERT INTO `d_sms_send_del` VALUES ('78', null, null, null, null, null, null, '');
INSERT INTO `d_sms_send_del` VALUES ('79', null, null, null, null, null, null, '');
INSERT INTO `d_sms_send_del` VALUES ('80', null, null, null, null, null, null, '');
INSERT INTO `d_sms_send_del` VALUES ('81', null, null, null, null, null, null, '');
INSERT INTO `d_sms_send_del` VALUES ('82', null, null, null, null, null, null, '');

-- ----------------------------
-- Table structure for `d_sms_verify_del`
-- ----------------------------
DROP TABLE IF EXISTS `d_sms_verify_del`;
CREATE TABLE `d_sms_verify_del` (
  `verify_id` int(11) NOT NULL AUTO_INCREMENT,
  `receive_id` int(11) DEFAULT NULL COMMENT '接收人ID',
  `receive_name` varchar(50) DEFAULT NULL COMMENT '接收用户名',
  `stutas` tinyint(4) DEFAULT '0',
  `receive_phone` varchar(50) DEFAULT NULL COMMENT '接收号码',
  `verify_code` varchar(20) DEFAULT NULL COMMENT '验证码',
  `send_content` varchar(200) DEFAULT NULL COMMENT '发送内容',
  `send_interface` varchar(50) DEFAULT NULL,
  `overdue_time` int(11) DEFAULT NULL COMMENT '过期时间',
  `operate_ip` varchar(50) DEFAULT NULL COMMENT '操作IP',
  `terminal` tinyint(4) DEFAULT '1' COMMENT '操作终端 1 PC 2 安卓 3 IOS 4 微信',
  `add_datetime` int(11) DEFAULT NULL COMMENT '创建时间',
  `remark` varchar(50) DEFAULT '' COMMENT '备注',
  PRIMARY KEY (`verify_id`),
  KEY `index_name` (`receive_phone`) USING BTREE
) ENGINE=MyISAM AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COMMENT='短信验证码';

-- ----------------------------
-- Records of d_sms_verify_del
-- ----------------------------
INSERT INTO `d_sms_verify_del` VALUES ('1', null, '13751186805', '1', '13751186805', '888888', '您的登录验证码为：888888，不要告诉任何人，以免造成帐号被盗风险！', '希奥', '1527662861', '127.0.0.1', '1', '1527662561', '');
INSERT INTO `d_sms_verify_del` VALUES ('2', null, '13751186805', '0', '13751186805', '888888', '您的登录验证码为：888888，不要告诉任何人，以免造成帐号被盗风险！', '希奥', '1527672145', '127.0.0.1', '1', '1527671845', '');
INSERT INTO `d_sms_verify_del` VALUES ('3', null, '13751172222', '1', '13751172222', '888888', '您的登录验证码为：888888，不要告诉任何人，以免造成帐号被盗风险！', '希奥', '1528268842', '0.0.0.0', '1', '1528268542', '');
INSERT INTO `d_sms_verify_del` VALUES ('4', null, '13751172222', '1', '13751172222', '888888', '您的登录验证码为：888888，不要告诉任何人，以免造成帐号被盗风险！', '希奥', '1528272840', '127.0.0.1', '1', '1528272540', '');
INSERT INTO `d_sms_verify_del` VALUES ('5', null, '13169601345', '0', '13169601345', '888888', '您的登录验证码为：888888，不要告诉任何人，以免造成帐号被盗风险！', '希奥', '1528273828', '127.0.0.1', '1', '1528273528', '');
INSERT INTO `d_sms_verify_del` VALUES ('6', null, '13751172222', '1', '13751172222', '888888', '您的登录验证码为：888888，不要告诉任何人，以免造成帐号被盗风险！', '希奥', '1528273881', '127.0.0.1', '1', '1528273581', '');
INSERT INTO `d_sms_verify_del` VALUES ('7', null, '13751172222', '0', '13751172222', '888888', '您的登录验证码为：888888，不要告诉任何人，以免造成帐号被盗风险！', '希奥', '1528336732', '127.0.0.1', '1', '1528336432', '');

-- ----------------------------
-- Table structure for `d_star_module`
-- ----------------------------
DROP TABLE IF EXISTS `d_star_module`;
CREATE TABLE `d_star_module` (
  `sm_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT '0',
  `module_code` varchar(125) DEFAULT '0' COMMENT '0 未启动 1认证过',
  `status` int(11) DEFAULT '1' COMMENT '1 是有效 0 是无效',
  `add_detatime` int(25) DEFAULT '0',
  PRIMARY KEY (`sm_id`)
) ENGINE=InnoDB AUTO_INCREMENT=170 DEFAULT CHARSET=utf8 COMMENT='用户与星星奖励模块的关联表';

-- ----------------------------
-- Records of d_star_module
-- ----------------------------
INSERT INTO `d_star_module` VALUES ('1', '1', 'Face', '1', '0');
INSERT INTO `d_star_module` VALUES ('2', '2', 'Face', '1', '0');
INSERT INTO `d_star_module` VALUES ('3', '2', 'Voice', '1', '0');
INSERT INTO `d_star_module` VALUES ('4', '5', 'Face', '1', '0');
INSERT INTO `d_star_module` VALUES ('6', '7', 'Charge', '1', '0');
INSERT INTO `d_star_module` VALUES ('7', '7', 'Maillist', '1', '0');
INSERT INTO `d_star_module` VALUES ('8', '7', 'BindBank', '1', '0');
INSERT INTO `d_star_module` VALUES ('9', '7', 'Voice', '1', '0');
INSERT INTO `d_star_module` VALUES ('10', '7', 'Face', '1', '0');
INSERT INTO `d_star_module` VALUES ('11', '7', 'Gene', '1', '0');
INSERT INTO `d_star_module` VALUES ('12', '7', 'Address', '1', '0');
INSERT INTO `d_star_module` VALUES ('13', '12', 'Charge', '1', '0');
INSERT INTO `d_star_module` VALUES ('14', '4', 'Charge', '1', '0');
INSERT INTO `d_star_module` VALUES ('15', '11', 'Charge', '1', '0');
INSERT INTO `d_star_module` VALUES ('16', '13', 'Charge', '1', '0');
INSERT INTO `d_star_module` VALUES ('17', '13', 'Voice', '1', '0');
INSERT INTO `d_star_module` VALUES ('18', '16', 'Charge', '1', '0');
INSERT INTO `d_star_module` VALUES ('19', '9', 'Charge', '1', '0');
INSERT INTO `d_star_module` VALUES ('20', '2', 'Charge', '1', '0');
INSERT INTO `d_star_module` VALUES ('21', '2', 'Gene', '1', '0');
INSERT INTO `d_star_module` VALUES ('22', '22', 'Charge', '1', '0');
INSERT INTO `d_star_module` VALUES ('23', '14', 'Charge', '1', '0');
INSERT INTO `d_star_module` VALUES ('24', '21', 'Maillist', '1', '0');
INSERT INTO `d_star_module` VALUES ('25', '25', 'Charge', '1', '0');
INSERT INTO `d_star_module` VALUES ('26', '23', 'Charge', '1', '0');
INSERT INTO `d_star_module` VALUES ('27', '28', 'Maillist', '1', '0');
INSERT INTO `d_star_module` VALUES ('28', '8', 'Maillist', '1', '0');
INSERT INTO `d_star_module` VALUES ('29', '33', 'Charge', '1', '0');
INSERT INTO `d_star_module` VALUES ('30', '35', 'Charge', '1', '0');
INSERT INTO `d_star_module` VALUES ('31', '37', 'Gene', '1', '0');
INSERT INTO `d_star_module` VALUES ('32', '37', 'Maillist', '1', '0');
INSERT INTO `d_star_module` VALUES ('33', '37', 'Face', '1', '0');
INSERT INTO `d_star_module` VALUES ('34', '37', 'Charge', '1', '0');
INSERT INTO `d_star_module` VALUES ('35', '37', 'Voice', '1', '0');
INSERT INTO `d_star_module` VALUES ('36', '12', 'Face', '1', '0');
INSERT INTO `d_star_module` VALUES ('37', '39', 'Charge', '1', '0');
INSERT INTO `d_star_module` VALUES ('38', '33', 'Maillist', '1', '0');
INSERT INTO `d_star_module` VALUES ('39', '43', 'Face', '1', '0');
INSERT INTO `d_star_module` VALUES ('40', '43', 'Voice', '1', '0');
INSERT INTO `d_star_module` VALUES ('41', '41', 'Address', '1', '0');
INSERT INTO `d_star_module` VALUES ('42', '41', 'Face', '1', '0');
INSERT INTO `d_star_module` VALUES ('43', '42', 'Face', '1', '0');
INSERT INTO `d_star_module` VALUES ('44', '39', 'Face', '1', '0');
INSERT INTO `d_star_module` VALUES ('45', '42', 'Voice', '1', '0');
INSERT INTO `d_star_module` VALUES ('46', '33', 'Face', '1', '0');
INSERT INTO `d_star_module` VALUES ('47', '33', 'Voice', '1', '0');
INSERT INTO `d_star_module` VALUES ('48', '41', 'Maillist', '1', '0');
INSERT INTO `d_star_module` VALUES ('49', '43', 'Maillist', '1', '0');
INSERT INTO `d_star_module` VALUES ('50', '44', 'Maillist', '1', '0');
INSERT INTO `d_star_module` VALUES ('51', '42', 'Maillist', '1', '0');
INSERT INTO `d_star_module` VALUES ('52', '4', 'Face', '1', '0');
INSERT INTO `d_star_module` VALUES ('53', '40', 'Face', '1', '0');
INSERT INTO `d_star_module` VALUES ('54', '4', 'BindBank', '1', '0');
INSERT INTO `d_star_module` VALUES ('55', '4', 'Voice', '1', '0');
INSERT INTO `d_star_module` VALUES ('56', '4', 'Gene', '1', '0');
INSERT INTO `d_star_module` VALUES ('57', '4', 'Address', '1', '0');
INSERT INTO `d_star_module` VALUES ('58', '27', 'Face', '1', '0');
INSERT INTO `d_star_module` VALUES ('59', '3', 'Auth', '1', '0');
INSERT INTO `d_star_module` VALUES ('60', '27', 'Gene', '1', '0');
INSERT INTO `d_star_module` VALUES ('61', '58', 'Charge', '1', '0');
INSERT INTO `d_star_module` VALUES ('62', '53', 'Gene', '1', '0');
INSERT INTO `d_star_module` VALUES ('63', '53', 'Gene', '1', '0');
INSERT INTO `d_star_module` VALUES ('64', '53', 'Gene', '1', '0');
INSERT INTO `d_star_module` VALUES ('65', '53', 'Gene', '1', '0');
INSERT INTO `d_star_module` VALUES ('66', '53', 'Gene', '1', '0');
INSERT INTO `d_star_module` VALUES ('67', '53', 'Gene', '1', '0');
INSERT INTO `d_star_module` VALUES ('68', '69', 'Gene', '1', '0');
INSERT INTO `d_star_module` VALUES ('69', '70', 'Gene', '1', '0');
INSERT INTO `d_star_module` VALUES ('70', '70', 'Maillist', '1', '0');
INSERT INTO `d_star_module` VALUES ('71', '53', 'Voice', '1', '0');
INSERT INTO `d_star_module` VALUES ('72', '53', 'Voice', '1', '0');
INSERT INTO `d_star_module` VALUES ('73', '53', 'Voice', '1', '0');
INSERT INTO `d_star_module` VALUES ('74', '61', 'Gene', '1', '0');
INSERT INTO `d_star_module` VALUES ('75', '61', 'Charge', '1', '0');
INSERT INTO `d_star_module` VALUES ('76', '61', 'Face', '1', '0');
INSERT INTO `d_star_module` VALUES ('77', '59', 'Maillist', '1', '0');
INSERT INTO `d_star_module` VALUES ('78', '59', 'Charge', '1', '0');
INSERT INTO `d_star_module` VALUES ('79', '23', 'BindBank', '1', '0');
INSERT INTO `d_star_module` VALUES ('80', '23', 'Auth', '1', '0');
INSERT INTO `d_star_module` VALUES ('81', '23', 'Face', '1', '0');
INSERT INTO `d_star_module` VALUES ('82', '23', 'Gene', '1', '0');
INSERT INTO `d_star_module` VALUES ('83', '42', 'BindBank', '1', '0');
INSERT INTO `d_star_module` VALUES ('84', '77', 'Maillist', '1', '0');
INSERT INTO `d_star_module` VALUES ('85', '78', 'Maillist', '1', '0');
INSERT INTO `d_star_module` VALUES ('86', '78', 'Gene', '1', '0');
INSERT INTO `d_star_module` VALUES ('87', '41', 'Charge', '1', '0');
INSERT INTO `d_star_module` VALUES ('88', '82', 'Maillist', '1', '0');
INSERT INTO `d_star_module` VALUES ('90', '82', 'Charge', '0', '1534324872');
INSERT INTO `d_star_module` VALUES ('92', '87', 'Auth', '1', '0');
INSERT INTO `d_star_module` VALUES ('104', '87', 'Face', '1', '1534849240');
INSERT INTO `d_star_module` VALUES ('107', '86', 'Face', '1', '1534903102');
INSERT INTO `d_star_module` VALUES ('109', '87', 'Face', '1', '1534903916');
INSERT INTO `d_star_module` VALUES ('110', '87', 'Face', '1', '1534905121');
INSERT INTO `d_star_module` VALUES ('111', '87', 'Face', '1', '1534905675');
INSERT INTO `d_star_module` VALUES ('112', '87', 'Face', '1', '1534908757');
INSERT INTO `d_star_module` VALUES ('113', '87', 'Face', '1', '1534909269');
INSERT INTO `d_star_module` VALUES ('114', '87', 'Face', '1', '1534909740');
INSERT INTO `d_star_module` VALUES ('115', '87', 'Face', '1', '1534910332');
INSERT INTO `d_star_module` VALUES ('135', '84', 'Gene', '1', '0');
INSERT INTO `d_star_module` VALUES ('136', '84', 'Gene', '1', '1535447734');
INSERT INTO `d_star_module` VALUES ('137', '84', 'Gene', '1', '1535447814');
INSERT INTO `d_star_module` VALUES ('138', '84', 'Auth', '1', '1535522146');
INSERT INTO `d_star_module` VALUES ('139', '84', 'Auth', '1', '1535522344');
INSERT INTO `d_star_module` VALUES ('140', '84', 'Auth', '1', '1535522405');
INSERT INTO `d_star_module` VALUES ('141', '84', 'Auth', '1', '1535522608');
INSERT INTO `d_star_module` VALUES ('142', '84', 'Auth', '1', '1535522687');
INSERT INTO `d_star_module` VALUES ('145', '84', 'BindBank', '1', '1535525147');
INSERT INTO `d_star_module` VALUES ('152', '83', 'Face', '1', '1535624197');
INSERT INTO `d_star_module` VALUES ('153', '82', 'Face', '1', '1535626409');
INSERT INTO `d_star_module` VALUES ('154', '1', 'Charge', '1', '1535700948');
INSERT INTO `d_star_module` VALUES ('155', '99', 'Charge', '1', '1535707373');
INSERT INTO `d_star_module` VALUES ('156', '102', 'Charge', '1', '1535709599');
INSERT INTO `d_star_module` VALUES ('157', '104', 'BindBank', '1', '1535710338');
INSERT INTO `d_star_module` VALUES ('158', '109', 'BindBank', '1', '1535712553');
INSERT INTO `d_star_module` VALUES ('159', '110', 'BindBank', '1', '1535712646');
INSERT INTO `d_star_module` VALUES ('160', '111', 'Gene', '1', '1535954331');
INSERT INTO `d_star_module` VALUES ('161', '111', 'Auth', '1', '1535955309');
INSERT INTO `d_star_module` VALUES ('162', '111', 'BindBank', '1', '1535955577');
INSERT INTO `d_star_module` VALUES ('163', '111', 'Face', '1', '1535962482');
INSERT INTO `d_star_module` VALUES ('165', '111', 'Charge', '1', '1535966642');
INSERT INTO `d_star_module` VALUES ('167', '84', 'Charge', '1', '1535967291');
INSERT INTO `d_star_module` VALUES ('169', '112', 'Charge', '1', '1535967737');

-- ----------------------------
-- Table structure for `d_star_type`
-- ----------------------------
DROP TABLE IF EXISTS `d_star_type`;
CREATE TABLE `d_star_type` (
  `type_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '积分类型ID',
  `type_name` varchar(50) DEFAULT '' COMMENT '星星类型名称',
  `type_code` varchar(50) DEFAULT '' COMMENT '类型代码',
  `type_value` decimal(15,5) DEFAULT '0.00000' COMMENT '星星值',
  `is_system` tinyint(1) unsigned NOT NULL DEFAULT '1' COMMENT '用户积分参数表 0代表为底层 1 代表APP显示的类型 2 代表APP无需显示的类型',
  `mark` tinyint(1) DEFAULT '1' COMMENT '状态标识，0是停用，默认是1',
  `remark` varchar(200) DEFAULT '' COMMENT '备注',
  `add_user_id` int(11) NOT NULL DEFAULT '0' COMMENT '创建人ID',
  `add_user_name` varchar(50) DEFAULT NULL COMMENT '创建人',
  `add_datetime` int(11) NOT NULL COMMENT '创建时间',
  `edit_user_id` int(11) DEFAULT NULL COMMENT '修改人ID',
  `edit_user_name` varchar(50) DEFAULT NULL COMMENT '修改人',
  `edit_datetime` int(11) DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`type_id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8 COMMENT='积分类型';

-- ----------------------------
-- Records of d_star_type
-- ----------------------------
INSERT INTO `d_star_type` VALUES ('9', '注册', 'Register', '7.00000', '0', '1', '', '1', '系统', '0', '0', '系统', '0');
INSERT INTO `d_star_type` VALUES ('10', '实名认证', 'Auth', '10.00000', '1', '1', '', '1', '系统', '0', '0', '系统', '0');
INSERT INTO `d_star_type` VALUES ('11', '基因数据', 'Gene', '10.00000', '1', '1', '', '1', '系统', '0', '0', '系统', '0');
INSERT INTO `d_star_type` VALUES ('12', '邀请用户', 'Invitation', '10.00000', '0', '1', '', '1', '系统', '0', '0', '系统', '0');
INSERT INTO `d_star_type` VALUES ('13', '绑定银行卡', 'BindBank', '10.00000', '1', '1', '', '1', '系统', '0', '0', '系统', '0');
INSERT INTO `d_star_type` VALUES ('14', '地址', 'Address', '0.00000', '1', '1', '', '1', '系统', '0', '0', '系统', '0');
INSERT INTO `d_star_type` VALUES ('15', '关注公众号', 'Wechat', '10.00000', '1', '1', '', '1', '系统', '0', '0', '系统', '0');
INSERT INTO `d_star_type` VALUES ('16', '声音识别', 'Voice', '0.00000', '1', '1', '', '1', '系统', '0', '0', '系统', '0');
INSERT INTO `d_star_type` VALUES ('17', '人脸识别', 'Face', '10.00000', '1', '1', '', '1', '系统', '0', '0', '系统', '0');
INSERT INTO `d_star_type` VALUES ('19', '矿机绑定', 'Charge', '0.00000', '2', '1', '', '1', '系统', '0', '0', '系统', '0');
INSERT INTO `d_star_type` VALUES ('20', '绑定通讯录', 'Maillist', '0.00000', '2', '1', '', '1', '系统', '0', '0', '系统', '0');

-- ----------------------------
-- Table structure for `d_suspension_frame`
-- ----------------------------
DROP TABLE IF EXISTS `d_suspension_frame`;
CREATE TABLE `d_suspension_frame` (
  `frame_id` int(11) NOT NULL AUTO_INCREMENT,
  `frame_icon` varchar(255) DEFAULT '' COMMENT 'logo',
  `link` varchar(255) DEFAULT '',
  `begin_datetime` int(35) DEFAULT '0' COMMENT '开始时间',
  `end_datetime` int(35) DEFAULT '0' COMMENT '结束时间',
  `frame_word` varchar(255) DEFAULT '' COMMENT '弹出的文字',
  `add_datetime` int(30) DEFAULT '0' COMMENT '添加时间',
  PRIMARY KEY (`frame_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='APP 首页悬浮层';

-- ----------------------------
-- Records of d_suspension_frame
-- ----------------------------
INSERT INTO `d_suspension_frame` VALUES ('1', 'http://192.168.11.23/oms/image/20180720/b1bb4c7c-a385-46b9-a6b8-9590873e36cd.png', 'http://192.168.11.17:8080/wechat/web/web', '1532079540', '1532793300', '', '1532079770');

-- ----------------------------
-- Table structure for `d_sys_dictionary`
-- ----------------------------
DROP TABLE IF EXISTS `d_sys_dictionary`;
CREATE TABLE `d_sys_dictionary` (
  `dic_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '字典ID',
  `dic_parent_id` int(11) DEFAULT NULL COMMENT '父节点',
  `dic_name` varchar(50) NOT NULL COMMENT '字典名称',
  `dic_key` varchar(50) DEFAULT NULL COMMENT '关键字',
  `dic_node` int(11) DEFAULT NULL COMMENT '节点类型',
  `dic_status` tinyint(4) DEFAULT '1' COMMENT '状态 0 删除 1 正常',
  `dic_remark` varchar(200) DEFAULT NULL COMMENT '备注',
  `add_user_id` int(11) NOT NULL COMMENT '创建人ID',
  `add_user_name` varchar(50) DEFAULT NULL COMMENT '创建人',
  `add_datetime` int(11) NOT NULL COMMENT '创建时间',
  `edit_user_id` int(11) DEFAULT NULL COMMENT '修改人ID',
  `edit_user_name` varchar(50) DEFAULT NULL COMMENT '修改人',
  `edit_datetime` int(11) DEFAULT NULL COMMENT '修改时间',
  `operate_ip` varchar(50) DEFAULT NULL COMMENT '操作IP',
  PRIMARY KEY (`dic_id`)
) ENGINE=MyISAM AUTO_INCREMENT=195 DEFAULT CHARSET=utf8 COMMENT='字典表(树)';

-- ----------------------------
-- Records of d_sys_dictionary
-- ----------------------------
INSERT INTO `d_sys_dictionary` VALUES ('1', '8', '等额本息2', '', '0', '1', '不可以变更', '7', 'mbox', '1448245450', '2', 'liup', '1528685968', '127.0.0.1');
INSERT INTO `d_sys_dictionary` VALUES ('2', '8', '先息后本', '', '2', '1', '不可以变更', '7', 'mbox', '1448245469', null, null, null, '127.0.0.1');
INSERT INTO `d_sys_dictionary` VALUES ('3', '8', '到期还款', '', '2', '1', '不可以变更', '7', 'mbox', '1448245481', null, null, null, '127.0.0.1');
INSERT INTO `d_sys_dictionary` VALUES ('4', '8', '余额计息', '', '2', '1', '不可以变更', '7', 'mbox', '1448245605', null, null, null, '127.0.0.1');
INSERT INTO `d_sys_dictionary` VALUES ('5', '8', '等本等息', '', '2', '1', '不可以变更', '7', 'mbox', '1448245619', null, null, null, '127.0.0.1');
INSERT INTO `d_sys_dictionary` VALUES ('7', '8', '其他还款方式', '', '2', '0', '不可以变更', '7', 'mbox', '1448245792', null, null, null, '127.0.0.1');
INSERT INTO `d_sys_dictionary` VALUES ('8', '0', '还款方式', '', '1', '1', '不可以变更', '7', 'mbox', '1448245345', '16', 'liup', '1473153351', '127.0.0.1');
INSERT INTO `d_sys_dictionary` VALUES ('9', '0', '内容类型', '', '1', '1', '内容类型', '7', 'mbox', '1448245900', null, null, null, '127.0.0.1');
INSERT INTO `d_sys_dictionary` VALUES ('10', '9', '文章类型', '', '2', '1', '文章类型', '7', 'mbox', '1448245952', null, null, null, '127.0.0.1');
INSERT INTO `d_sys_dictionary` VALUES ('11', '9', '帮助中心', '', '2', '1', '帮助中心', '7', 'mbox', '1448245978', null, null, null, '127.0.0.1');
INSERT INTO `d_sys_dictionary` VALUES ('12', '10', '平台公告', '', '3', '1', '平台公告', '7', 'mbox', '1448246015', null, null, null, '127.0.0.1');
INSERT INTO `d_sys_dictionary` VALUES ('13', '10', '行业动态', '', '3', '1', '行业动态', '7', 'mbox', '1448246035', null, null, null, '127.0.0.1');
INSERT INTO `d_sys_dictionary` VALUES ('14', '10', '媒体报道', '', '3', '1', '媒体报道', '7', 'mbox', '1448246129', null, null, null, '127.0.0.1');
INSERT INTO `d_sys_dictionary` VALUES ('15', '10', '监管报告', '', '3', '1', '监管报告', '7', 'mbox', '1448246171', null, null, null, '127.0.0.1');
INSERT INTO `d_sys_dictionary` VALUES ('16', '10', '合作机构', '', '3', '1', '合作机构', '7', 'mbox', '1448246192', null, null, null, '127.0.0.1');
INSERT INTO `d_sys_dictionary` VALUES ('17', '10', '管理团队', '', '3', '1', '管理团队', '7', 'mbox', '1448246255', null, null, null, '127.0.0.1');
INSERT INTO `d_sys_dictionary` VALUES ('18', '10', '专家顾问', '', '3', '1', '专家顾问', '7', 'mbox', '1448246282', null, null, null, '127.0.0.1');
INSERT INTO `d_sys_dictionary` VALUES ('19', '10', '项目公告', '', '3', '1', '项目公告', '7', 'mbox', '1448246318', '7', 'mbox', '1450940508', '113.92.250.122');
INSERT INTO `d_sys_dictionary` VALUES ('20', '10', '还款公告', '', '3', '0', '还款公告', '7', 'mbox', '1448246353', '7', 'mbox', '1450940536', '113.92.250.122');
INSERT INTO `d_sys_dictionary` VALUES ('21', '10', 'SEO文章', '', '3', '1', 'SEO文章', '7', 'mbox', '1448246374', null, null, null, '127.0.0.1');
INSERT INTO `d_sys_dictionary` VALUES ('22', '11', '注册/登录', '', '3', '1', '', '7', 'mbox', '1448246412', '38', 'lizy', '1473142175', '127.0.0.1');
INSERT INTO `d_sys_dictionary` VALUES ('23', '11', '认证问题', '', '3', '1', '', '7', 'mbox', '1448246535', '38', 'lizy', '1473489261', '127.0.0.1');
INSERT INTO `d_sys_dictionary` VALUES ('24', '11', '充值问题', '', '3', '1', '', '7', 'mbox', '1448246567', '38', 'lizy', '1473489273', '127.0.0.1');
INSERT INTO `d_sys_dictionary` VALUES ('25', '11', '投资问题', '', '3', '1', '', '7', 'mbox', '1448246589', '38', 'lizy', '1473489293', '127.0.0.1');
INSERT INTO `d_sys_dictionary` VALUES ('26', '11', '回款问题', '', '3', '1', '', '7', 'mbox', '1448246629', '38', 'lizy', '1473489303', '127.0.0.1');
INSERT INTO `d_sys_dictionary` VALUES ('27', '11', '提现问题', '', '3', '1', '', '7', 'mbox', '1448246644', '38', 'lizy', '1473489313', '127.0.0.1');
INSERT INTO `d_sys_dictionary` VALUES ('28', '11', '消费理财', '', '3', '1', '', '7', 'mbox', '1448246660', '38', 'lizy', '1473142570', '127.0.0.1');
INSERT INTO `d_sys_dictionary` VALUES ('29', '0', '广告位类型', '', '1', '1', '广告位类型', '7', 'mbox', '1448247873', null, null, null, '127.0.0.1');
INSERT INTO `d_sys_dictionary` VALUES ('30', '29', 'PC广告位', '', '2', '1', 'PC广告位', '7', 'mbox', '1448247904', null, null, null, '127.0.0.1');
INSERT INTO `d_sys_dictionary` VALUES ('31', '29', '微信广告位', '', '2', '1', '微信广告位', '7', 'mbox', '1448247958', null, null, null, '127.0.0.1');
INSERT INTO `d_sys_dictionary` VALUES ('32', '30', '首页Banner图', '', '3', '1', '首页Banner图', '7', 'mbox', '1448248023', null, null, null, '127.0.0.1');
INSERT INTO `d_sys_dictionary` VALUES ('33', '30', '注册广告', '', '3', '1', '注册广告', '7', 'mbox', '1448248100', null, null, null, '127.0.0.1');
INSERT INTO `d_sys_dictionary` VALUES ('34', '30', '登录页广告', '', '3', '1', '登录页广告', '7', 'mbox', '1448248122', null, null, null, '127.0.0.1');
INSERT INTO `d_sys_dictionary` VALUES ('35', '30', '找回密码页广告', '', '3', '1', '找回密码页广告', '7', 'mbox', '1448248241', null, null, null, '127.0.0.1');
INSERT INTO `d_sys_dictionary` VALUES ('36', '30', '公司资质图片', '', '3', '1', '公司资质图片', '7', 'mbox', '1448248303', null, null, null, '127.0.0.1');
INSERT INTO `d_sys_dictionary` VALUES ('37', '0', '用户管理', '', '1', '1', '用户管理', '7', 'mbox', '1448248411', null, null, null, '127.0.0.1');
INSERT INTO `d_sys_dictionary` VALUES ('38', '37', '用户来源', '', '2', '1', '用户来源', '7', 'mbox', '1448248430', null, null, null, '127.0.0.1');
INSERT INTO `d_sys_dictionary` VALUES ('39', '37', '推广来源', '', '2', '1', '推广来源', '7', 'mbox', '1448248449', null, null, null, '127.0.0.1');
INSERT INTO `d_sys_dictionary` VALUES ('40', '37', '用户类型', '', '2', '1', '用户类型', '7', 'mbox', '1448248464', null, null, null, '127.0.0.1');
INSERT INTO `d_sys_dictionary` VALUES ('41', '40', '普通用户', '', '3', '1', '普通用户', '7', 'mbox', '1448248487', '15', 'mbox', '1452756136', '113.90.221.189');
INSERT INTO `d_sys_dictionary` VALUES ('42', '40', '投资用户', '', '3', '1', '投资用户', '7', 'mbox', '1448248530', '15', 'mbox', '1452756156', '113.90.221.189');
INSERT INTO `d_sys_dictionary` VALUES ('43', '40', 'VIP用户', '', '3', '1', 'VIP用户', '7', 'mbox', '1448248559', '15', 'mbox', '1452756202', '113.90.221.189');
INSERT INTO `d_sys_dictionary` VALUES ('44', '0', '银行卡管理', '', '1', '1', '银行卡管理', '7', 'mbox', '1448248775', null, null, null, '127.0.0.1');
INSERT INTO `d_sys_dictionary` VALUES ('45', '44', '网银银行卡', '', '2', '1', '网银银行卡', '7', 'mbox', '1448248790', '7', 'mbox', '1450237534', '113.90.83.30');
INSERT INTO `d_sys_dictionary` VALUES ('46', '44', '快捷银行卡', '', '2', '1', '快捷银行卡', '7', 'mbox', '1448248801', null, null, null, '127.0.0.1');
INSERT INTO `d_sys_dictionary` VALUES ('47', '0', '活动类型管理', '', '1', '1', '活动类型管理', '7', 'mbox', '1448248873', null, null, null, '127.0.0.1');
INSERT INTO `d_sys_dictionary` VALUES ('48', '47', '长期活动', '', '2', '1', '长期活动', '7', 'mbox', '1448249041', null, null, null, '127.0.0.1');
INSERT INTO `d_sys_dictionary` VALUES ('49', '47', '临时活动', '', '2', '1', '临时活动', '7', 'mbox', '1448249150', null, null, null, '127.0.0.1');
INSERT INTO `d_sys_dictionary` VALUES ('50', '48', '大富翁转盘', '', '3', '1', '大富翁转盘', '7', 'mbox', '1448250067', null, null, null, '127.0.0.1');
INSERT INTO `d_sys_dictionary` VALUES ('51', '48', '用户注册', '', '3', '1', '用户注册', '7', 'mbox', '1448250081', null, null, null, '127.0.0.1');
INSERT INTO `d_sys_dictionary` VALUES ('52', '48', '用户实名', '', '3', '1', '用户实名', '7', 'mbox', '1448250093', null, null, null, '127.0.0.1');
INSERT INTO `d_sys_dictionary` VALUES ('53', '48', '邀请好友注册', '', '3', '1', '邀请好友注册', '7', 'mbox', '1448250198', null, null, null, '127.0.0.1');
INSERT INTO `d_sys_dictionary` VALUES ('54', '48', '用户首次充值', '', '3', '1', '用户首次充值', '7', 'mbox', '1448250266', null, null, null, '127.0.0.1');
INSERT INTO `d_sys_dictionary` VALUES ('55', '48', '绑定银行卡', '', '3', '1', '绑定银行卡', '7', 'mbox', '1448250311', null, null, null, '127.0.0.1');
INSERT INTO `d_sys_dictionary` VALUES ('56', '49', '周年庆', '', '3', '1', '周年庆', '7', 'mbox', '1448250326', null, null, null, '127.0.0.1');
INSERT INTO `d_sys_dictionary` VALUES ('57', '49', '11.11活动', '', '3', '1', '11.11活动', '7', 'mbox', '1448250414', null, null, null, '127.0.0.1');
INSERT INTO `d_sys_dictionary` VALUES ('58', '45', '中国银行', 'BOC', '3', '1', '中国银行', '7', 'mbox', '1448250524', null, null, null, '127.0.0.1');
INSERT INTO `d_sys_dictionary` VALUES ('59', '46', '中国银行', 'BOC', '3', '1', '中国银行', '7', 'mbox', '1448250605', null, null, null, '127.0.0.1');
INSERT INTO `d_sys_dictionary` VALUES ('60', '46', '工商银行', 'ICBC', '3', '1', '工商银行', '7', 'mbox', '1448250647', null, null, null, '127.0.0.1');
INSERT INTO `d_sys_dictionary` VALUES ('61', '46', '农业银行', 'ABC', '3', '1', '农业银行', '7', 'mbox', '1448250693', null, null, null, '127.0.0.1');
INSERT INTO `d_sys_dictionary` VALUES ('62', '46', '建设银行', 'CCB', '3', '1', '建设银行', '7', 'mbox', '1448250728', null, null, null, '127.0.0.1');
INSERT INTO `d_sys_dictionary` VALUES ('63', '46', '光大银行', 'CEB', '3', '1', '光大银行', '7', 'mbox', '1448250855', null, null, null, '127.0.0.1');
INSERT INTO `d_sys_dictionary` VALUES ('64', '46', '兴业银行', 'CIB', '3', '1', '兴业银行', '7', 'mbox', '1448250892', null, null, null, '127.0.0.1');
INSERT INTO `d_sys_dictionary` VALUES ('65', '46', '民生银行', 'CMBC', '3', '1', '民生银行', '7', 'mbox', '1448250940', null, null, null, '127.0.0.1');
INSERT INTO `d_sys_dictionary` VALUES ('66', '46', '华夏银行', 'HXB', '3', '0', '华夏银行', '7', 'mbox', '1448250962', '15', 'mbox', '1455853161', '14.153.2.243');
INSERT INTO `d_sys_dictionary` VALUES ('67', '46', '邮储银行', 'PSBC', '3', '1', '邮储银行', '7', 'mbox', '1448250999', null, null, null, '127.0.0.1');
INSERT INTO `d_sys_dictionary` VALUES ('68', '46', '交通银行', 'COMM', '3', '1', '交通银行', '7', 'mbox', '1448251048', null, null, null, '127.0.0.1');
INSERT INTO `d_sys_dictionary` VALUES ('69', '46', '广发银行', 'GDB', '3', '1', '广发银行', '7', 'mbox', '1448251116', null, null, null, '127.0.0.1');
INSERT INTO `d_sys_dictionary` VALUES ('70', '46', '中信银行', 'CITIC', '3', '1', '中信银行', '7', 'mbox', '1448251146', null, null, null, '127.0.0.1');
INSERT INTO `d_sys_dictionary` VALUES ('71', '46', '招商银行', 'CMB', '3', '1', '招商银行', '7', 'mbox', '1448251168', null, null, null, '127.0.0.1');
INSERT INTO `d_sys_dictionary` VALUES ('72', '46', '平安银行', 'SPAB', '3', '1', '平安银行', '7', 'mbox', '1448251191', '15', 'mbox', '1457002154', '113.116.28.254');
INSERT INTO `d_sys_dictionary` VALUES ('73', '45', '工商银行', 'ICBC', '3', '1', '工商银行', '7', 'mbox', '1448251236', null, null, null, '127.0.0.1');
INSERT INTO `d_sys_dictionary` VALUES ('74', '45', '农业银行', 'ABC', '3', '1', '农业银行', '7', 'mbox', '1448255145', null, null, null, '127.0.0.1');
INSERT INTO `d_sys_dictionary` VALUES ('75', '45', '建设银行', 'CCB', '3', '1', '建设银行', '7', 'mbox', '1448255173', null, null, null, '127.0.0.1');
INSERT INTO `d_sys_dictionary` VALUES ('76', '45', '民生银行', 'CMBC', '3', '1', '民生银行', '7', 'mbox', '1448255203', null, null, null, '127.0.0.1');
INSERT INTO `d_sys_dictionary` VALUES ('77', '45', '浦发银行', 'SPDB', '3', '1', '浦发银行', '7', 'mbox', '1448255247', null, null, null, '127.0.0.1');
INSERT INTO `d_sys_dictionary` VALUES ('78', '45', '广发银行', 'GDB', '3', '1', '广发银行', '7', 'mbox', '1448255266', null, null, null, '127.0.0.1');
INSERT INTO `d_sys_dictionary` VALUES ('79', '45', '华夏银行', 'HXB', '3', '1', '华夏银行', '7', 'mbox', '1448255296', null, null, null, '127.0.0.1');
INSERT INTO `d_sys_dictionary` VALUES ('80', '45', '邮储银行', 'PSBC', '3', '1', '邮储银行', '7', 'mbox', '1448255315', null, null, null, '127.0.0.1');
INSERT INTO `d_sys_dictionary` VALUES ('81', '45', '光大银行', 'CEB', '3', '1', '光大银行', '7', 'mbox', '1448255335', null, null, null, '127.0.0.1');
INSERT INTO `d_sys_dictionary` VALUES ('82', '45', '东亚银行', 'BEA', '3', '1', '东亚银行', '7', 'mbox', '1448255359', null, null, null, '127.0.0.1');
INSERT INTO `d_sys_dictionary` VALUES ('83', '45', '兴业银行', 'CIB', '3', '1', '兴业银行', '7', 'mbox', '1448255388', null, null, null, '127.0.0.1');
INSERT INTO `d_sys_dictionary` VALUES ('84', '45', '交通银行', 'COMM', '3', '1', '交通银行', '7', 'mbox', '1448255415', null, null, null, '127.0.0.1');
INSERT INTO `d_sys_dictionary` VALUES ('85', '45', '中信银行', 'CITIC', '3', '1', '中信银行', '7', 'mbox', '1448255433', null, null, null, '127.0.0.1');
INSERT INTO `d_sys_dictionary` VALUES ('86', '31', '微信首页广告位', '', '3', '1', '微信首页广告位', '7', 'mbox', '1449119766', '7', 'mbox', '1449119824', '14.153.2.41');
INSERT INTO `d_sys_dictionary` VALUES ('87', '31', '微信关于我们', '', '3', '1', '微信关于我们', '7', 'mbox', '1449119845', null, null, null, '14.153.2.41');
INSERT INTO `d_sys_dictionary` VALUES ('88', '31', '帮助中心', '', '3', '1', '', '7', 'mbox', '1449120223', null, null, null, '14.153.2.41');
INSERT INTO `d_sys_dictionary` VALUES ('89', '0', '活动所属平台', '', '1', '1', '活动发布到的平台类型', '7', 'mbox', '1449194092', '7', 'mbox', '1449194174', '127.0.0.1');
INSERT INTO `d_sys_dictionary` VALUES ('90', '89', 'PC', '', '2', '1', 'PC客户端', '7', 'mbox', '1449194104', '7', 'mbox', '1450168876', '113.90.83.30');
INSERT INTO `d_sys_dictionary` VALUES ('91', '89', '微信', '', '2', '1', '微信客户端', '7', 'mbox', '1449194117', '7', 'mbox', '1449194211', '127.0.0.1');
INSERT INTO `d_sys_dictionary` VALUES ('92', '45', '招商银行', 'CMB', '3', '1', '', '7', 'mbox', '1449655322', null, null, null, '116.24.133.126');
INSERT INTO `d_sys_dictionary` VALUES ('93', '45', '平安银行', 'SPAB', '3', '0', '平安银行', '7', 'mbox', '1450237560', '15', 'mbox', '1457002140', '113.116.28.254');
INSERT INTO `d_sys_dictionary` VALUES ('94', '31', '微信注册广告', '', '3', '1', '微信注册广告', '7', 'mbox', '1450316754', null, null, null, '113.90.223.24');
INSERT INTO `d_sys_dictionary` VALUES ('95', '31', '邀请好友', '', '3', '1', '邀请好友', '7', 'mbox', '1450668600', null, null, null, '14.153.0.189');
INSERT INTO `d_sys_dictionary` VALUES ('96', '0', '奖励方式', '', '1', '1', '奖励方式', '7', 'mbox', '1451285640', '15', 'mbox', '1472266512', '183.38.70.149');
INSERT INTO `d_sys_dictionary` VALUES ('97', '96', '体验金', '', '2', '1', '体验金', '7', 'mbox', '1451285659', null, null, null, '183.38.70.149');
INSERT INTO `d_sys_dictionary` VALUES ('98', '96', '现金奖励', '', '2', '1', '现金奖励', '7', 'mbox', '1451285737', '15', 'mbox', '1472267374', '183.38.70.149');
INSERT INTO `d_sys_dictionary` VALUES ('99', '96', '现金券', '', '2', '1', '现金券', '7', 'mbox', '1451285751', '15', 'mbox', '1472267455', '183.38.70.149');
INSERT INTO `d_sys_dictionary` VALUES ('100', '96', '抵用券', '', '1', '1', '抵用券', '7', 'mbox', '1451285765', '15', 'mbox', '1472267523', '183.38.70.149');
INSERT INTO `d_sys_dictionary` VALUES ('101', '96', '加息券', '', '1', '1', '加息券', '7', 'mbox', '1451285816', '15', 'mbox', '1472267556', '183.38.70.149');
INSERT INTO `d_sys_dictionary` VALUES ('102', '96', 'VIP加息', '', '0', '1', 'VIP加息', '7', 'mbox', '1451285829', '15', 'mbox', '1480571192', '183.38.70.149');
INSERT INTO `d_sys_dictionary` VALUES ('103', '96', '积分', '', '1', '1', '积分', '7', 'mbox', '1451285839', '14', 'jiangt', '1472278627', '183.38.70.149');
INSERT INTO `d_sys_dictionary` VALUES ('104', '96', '资金平账', '', '1', '1', '资金平账', '7', 'mbox', '1451285874', null, null, null, '183.38.70.149');
INSERT INTO `d_sys_dictionary` VALUES ('105', '96', '融资人补账', '', '1', '1', '融资人补账', '7', 'mbox', '1451285889', '15', 'mbox', '1472268929', '183.38.70.149');
INSERT INTO `d_sys_dictionary` VALUES ('106', '0', '邀请奖励(有变更)', '', '1', '1', '邀请奖励', '7', 'mbox', '1451884829', '15', 'mbox', '1472267860', '113.116.31.87');
INSERT INTO `d_sys_dictionary` VALUES ('107', '106', '邀请送现金', '', '2', '1', '邀请送现金', '7', 'mbox', '1451884857', null, null, null, '113.116.31.87');
INSERT INTO `d_sys_dictionary` VALUES ('108', '106', '邀请送现金券', '', '3', '1', '邀请送现金券', '7', 'mbox', '1451884873', null, null, null, '113.116.31.87');
INSERT INTO `d_sys_dictionary` VALUES ('109', '30', '平台落地页Banner图', '', '3', '1', '平台落地页Banner图', '7', 'mbox', '1452135667', null, null, null, '113.90.86.213');
INSERT INTO `d_sys_dictionary` VALUES ('110', '30', '方正落地页Banner图', '', '3', '1', '方正落地页Banner图', '7', 'mbox', '1452135710', null, null, null, '113.90.86.213');
INSERT INTO `d_sys_dictionary` VALUES ('111', '30', '计算器banner', '', '3', '1', '计算器banner', '15', 'mbox', '1452224541', null, null, null, '119.123.111.26');
INSERT INTO `d_sys_dictionary` VALUES ('112', '40', '公司员工', '', '3', '1', '公司员工', '15', 'mbox', '1452756223', '15', 'mbox', '1452756309', '113.90.221.189');
INSERT INTO `d_sys_dictionary` VALUES ('113', '40', '融资用户', '', '3', '1', '融资用户', '15', 'mbox', '1452756238', null, null, null, '113.90.221.189');
INSERT INTO `d_sys_dictionary` VALUES ('114', '40', '马甲用户', '', '3', '1', '马甲用户', '15', 'mbox', '1452756329', null, null, null, '113.90.221.189');
INSERT INTO `d_sys_dictionary` VALUES ('115', '0', '客户服务类型', '', '1', '1', '客户服务类型', '15', 'mbox', '1456291516', null, null, null, '116.30.243.222');
INSERT INTO `d_sys_dictionary` VALUES ('116', '115', '沟通方式', '', '0', '1', '沟通方式', '15', 'mbox', '1456291631', '15', 'mbox', '1476150359', '116.30.243.222');
INSERT INTO `d_sys_dictionary` VALUES ('117', '116', '电话', '', '3', '1', '电话', '15', 'mbox', '1456292114', '15', 'mbox', '1458185873', '183.38.71.18');
INSERT INTO `d_sys_dictionary` VALUES ('118', '116', '短信', '', '3', '1', '短信', '15', 'mbox', '1456292139', null, null, null, '116.30.243.222');
INSERT INTO `d_sys_dictionary` VALUES ('119', '116', 'QQ', '', '3', '1', 'QQ', '15', 'mbox', '1456292157', null, null, null, '116.30.243.222');
INSERT INTO `d_sys_dictionary` VALUES ('120', '116', '微信', '', '3', '1', '微信', '15', 'mbox', '1456292171', null, null, null, '116.30.243.222');
INSERT INTO `d_sys_dictionary` VALUES ('121', '116', '多客服', '', '3', '1', '多客服', '15', 'mbox', '1456292187', null, null, null, '116.30.243.222');
INSERT INTO `d_sys_dictionary` VALUES ('122', '116', '营销QQ', '', '3', '1', '营销QQ', '15', 'mbox', '1456292205', '14', 'jiangt', '1456824314', '127.0.0.1');
INSERT INTO `d_sys_dictionary` VALUES ('123', '115', '沟通类型', '', '0', '1', '沟通类型', '15', 'mbox', '1456292270', '15', 'mbox', '1476150371', '116.30.243.222');
INSERT INTO `d_sys_dictionary` VALUES ('124', '123', '技术型', '', '0', '1', '技术型', '15', 'mbox', '1456294538', '15', 'mbox', '1476150400', '116.30.243.222');
INSERT INTO `d_sys_dictionary` VALUES ('125', '123', '亲和型', '', '0', '1', '亲和型', '15', 'mbox', '1456294581', '15', 'mbox', '1476150425', '116.30.243.222');
INSERT INTO `d_sys_dictionary` VALUES ('126', '123', '礼节型', '', '0', '1', '礼节型', '15', 'mbox', '1456294649', '15', 'mbox', '1476150459', '116.30.243.222');
INSERT INTO `d_sys_dictionary` VALUES ('127', '123', '推广型', '', '0', '1', '推广型', '15', 'mbox', '1456294685', '15', 'mbox', '1476150483', '116.30.243.222');
INSERT INTO `d_sys_dictionary` VALUES ('128', '123', '无效沟通', '', '0', '1', '无效沟通', '15', 'mbox', '1456295073', '15', 'mbox', '1476150504', '116.30.243.222');
INSERT INTO `d_sys_dictionary` VALUES ('129', '123', '投诉型', '', '0', '1', '投诉型', '15', 'mbox', '1456295113', '15', 'mbox', '1476150524', '116.30.243.222');
INSERT INTO `d_sys_dictionary` VALUES ('130', '123', '意见反馈', '', '0', '1', '意见反馈', '15', 'mbox', '1456295148', '15', 'mbox', '1476150544', '116.30.243.222');
INSERT INTO `d_sys_dictionary` VALUES ('131', '123', '其他情况', '', '0', '1', '其他情况', '15', 'mbox', '1456295182', '15', 'mbox', '1476150629', '116.30.243.222');
INSERT INTO `d_sys_dictionary` VALUES ('132', '46', '浦发银行', 'SPDB', '3', '1', '浦发银行', '15', 'mbox', '1460007287', '15', 'mbox', '1460007301', '113.116.31.58');
INSERT INTO `d_sys_dictionary` VALUES ('133', '45', '北京银行', 'BJB', '3', '1', '北京银行', '15', 'mbox', '1460007861', '14', 'jiangt', '1465711772', '183.54.46.141');
INSERT INTO `d_sys_dictionary` VALUES ('134', '155', '虚拟商品', '', '2', '1', '', '14', 'jiangt', '1460525077', '14', 'jiangt', '1460627341', '127.0.0.1');
INSERT INTO `d_sys_dictionary` VALUES ('135', '155', '实物商品', '', '2', '1', '', '14', 'jiangt', '1460525100', '14', 'jiangt', '1460525110', '127.0.0.1');
INSERT INTO `d_sys_dictionary` VALUES ('136', '134', '体验金', '', '3', '1', '', '14', 'jiangt', '1460525137', '14', 'jiangt', '1460627354', '127.0.0.1');
INSERT INTO `d_sys_dictionary` VALUES ('137', '134', '卡券', '', '3', '1', '', '14', 'jiangt', '1460525281', null, '', null, '127.0.0.1');
INSERT INTO `d_sys_dictionary` VALUES ('138', '30', '首页右一广告位', '', '3', '1', '首页右一广告位（287 * 154）', '14', 'jiangt', '1461051556', '14', 'jiangt', '1461821001', '113.90.222.18');
INSERT INTO `d_sys_dictionary` VALUES ('139', '30', '首页右二广告位', '', '3', '1', '首页右二广告位（287 * 173）', '14', 'jiangt', '1461051736', null, '', null, '127.0.0.1');
INSERT INTO `d_sys_dictionary` VALUES ('140', '30', '首页右三广告位', '', '3', '1', '首页右三广告位（287 * 316）', '14', 'jiangt', '1461051775', null, '', null, '127.0.0.1');
INSERT INTO `d_sys_dictionary` VALUES ('141', '96', '合伙人奖励', '', '2', '1', '合伙人奖励', '14', 'jiangt', '1462934082', '15', 'mbox', '1472267642', '127.0.0.1');
INSERT INTO `d_sys_dictionary` VALUES ('142', '96', '推广佣金', '', '2', '1', '推广佣金', '14', 'jiangt', '1462934128', '15', 'mbox', '1472267655', '127.0.0.1');
INSERT INTO `d_sys_dictionary` VALUES ('143', '10', '运营报告', '', '3', '1', '运营报告', '30', 'mbox', '1463131746', '15', 'mbox', '1463535966', '113.90.220.139');
INSERT INTO `d_sys_dictionary` VALUES ('145', '30', '运营报告中间图', '', '3', '1', '运营报告中间图', '15', 'mbox', '1463557639', '30', 'mbox', '1463563537', '127.0.0.1');
INSERT INTO `d_sys_dictionary` VALUES ('146', '30', '运营报告左边图', '', '3', '1', '运营报告左边图', '15', 'mbox', '1463563565', null, '', null, '127.0.0.1');
INSERT INTO `d_sys_dictionary` VALUES ('147', '30', '运营报告右边图', '', '3', '1', '运营报告右边图', '15', 'mbox', '1463563608', null, '', null, '127.0.0.1');
INSERT INTO `d_sys_dictionary` VALUES ('148', '49', '传统文化形象大使', '', '3', '1', '传统文化形象大使（百米）', '14', 'mbox', '1464675202', null, '', null, '127.0.0.1');
INSERT INTO `d_sys_dictionary` VALUES ('149', '31', '微信百米注册图', '', '3', '1', '微信百米注册页广告位', '30', 'mbox', '1464835590', '15', 'mbox', '1464845948', '113.90.84.103');
INSERT INTO `d_sys_dictionary` VALUES ('150', '30', '积分商城广告位', '', '3', '1', '积分商城广告位', '15', 'mbox', '1464916335', null, null, null, '113.90.84.103');
INSERT INTO `d_sys_dictionary` VALUES ('151', '30', '关于我们banner图', '', '3', '1', '关于我们banner图', '15', 'mbox', '1466058015', null, null, null, '116.30.241.113');
INSERT INTO `d_sys_dictionary` VALUES ('152', '30', '消费理财banner图', '', '3', '1', '消费理财banner图', '15', 'mbox', '1467190721', null, null, null, '116.24.133.21');
INSERT INTO `d_sys_dictionary` VALUES ('153', '31', '微信消费理财banner图', '', '3', '1', '微信消费理财banner图', '15', 'mbox', '1467190755', null, null, null, '116.24.133.21');
INSERT INTO `d_sys_dictionary` VALUES ('154', '96', '暂定', '', '2', '1', '暂定', '14', 'jiangt', '1471255960', '15', 'mbox', '1472267671', '113.97.181.244');
INSERT INTO `d_sys_dictionary` VALUES ('155', '0', '商品类型', null, '1', '1', '商品类型', '14', 'jiangt', '1460525056', null, '', null, '127.0.0.1');
INSERT INTO `d_sys_dictionary` VALUES ('156', '135', '实物', '5', '0', '1', '实物', '2', '管理员', '1467012409', '15', 'mbox', '1474941842', '127.0.0.1');
INSERT INTO `d_sys_dictionary` VALUES ('157', '11', '存管指引', '', '3', '1', '', '16', 'liup', '1472526942', '38', 'lizy', '1473489332', null);
INSERT INTO `d_sys_dictionary` VALUES ('158', '0', '奖品类型', '', '1', '1', '奖品类型（用于抽奖）', '7', 'mbox', '1448250198', null, '', null, '127.0.0.1');
INSERT INTO `d_sys_dictionary` VALUES ('159', '158', '虚拟奖品', '', null, '1', '虚拟奖品（券，积分）', '16', 'liup', '1470710745', null, '', null, '');
INSERT INTO `d_sys_dictionary` VALUES ('160', '158', '实物奖品', '', null, '1', '实物奖品', '16', 'liup', '1470710764', null, '', null, '');
INSERT INTO `d_sys_dictionary` VALUES ('161', '158', '谢谢参与', '', null, '1', '谢谢参与（没有任何奖品）', '16', 'liup', '1470710808', '7', 'liyy', '1472203261', '');
INSERT INTO `d_sys_dictionary` VALUES ('162', '159', '卡券', '', '3', '1', '卡券', '16', 'liup', '1470712535', null, '', null, '');
INSERT INTO `d_sys_dictionary` VALUES ('163', '159', '抽奖积分', '', '3', '1', '抽奖积分', '16', 'liup', '1470712559', null, '', null, '');
INSERT INTO `d_sys_dictionary` VALUES ('164', '96', 'CPS佣金', '', null, '1', 'CPS佣金', '14', 'jiangt', '1473233757', null, null, null, null);
INSERT INTO `d_sys_dictionary` VALUES ('165', '11', '111', '', null, '0', '', '38', 'lizy', '1473307115', null, null, null, null);
INSERT INTO `d_sys_dictionary` VALUES ('166', '30', '活动中心banner图', '3', '3', '1', '活动中心', '15', 'mbox', '1473649185', null, null, null, null);
INSERT INTO `d_sys_dictionary` VALUES ('167', '29', 'APP广告位', '', null, '1', 'APP广告位', '14', 'jiangt', '1474430508', null, null, null, null);
INSERT INTO `d_sys_dictionary` VALUES ('168', '167', 'APP启动页广告位', '', '3', '1', 'APP启动页广告位', '14', 'jiangt', '1474430537', '14', 'jiangt', '1474430560', null);
INSERT INTO `d_sys_dictionary` VALUES ('169', '167', 'APP首页广告', '', '3', '1', 'APP首页广告', '14', 'jiangt', '1474506378', null, null, null, null);
INSERT INTO `d_sys_dictionary` VALUES ('172', '30', '产品介绍页图', '3', '3', '1', '产品介绍页图', '15', 'mbox', '1474509346', null, null, null, null);
INSERT INTO `d_sys_dictionary` VALUES ('173', '30', '首页推荐Banner图', '3', '3', '1', '首页推荐Banner图', '15', 'mbox', '1476352284', null, '', null, '');
INSERT INTO `d_sys_dictionary` VALUES ('174', '30', '新手活动Banner', '', '3', '1', '新手活动Banner', '15', 'mbox', '1477218052', null, null, null, null);
INSERT INTO `d_sys_dictionary` VALUES ('175', '31', '微信注册活动说明', '', '3', '1', '', '15', 'mbox', '1479374216', null, null, null, null);
INSERT INTO `d_sys_dictionary` VALUES ('176', '89', 'APP', '', '2', '1', 'APP活动', '15', 'mbox', '1479374367', null, null, null, null);
INSERT INTO `d_sys_dictionary` VALUES ('177', '31', '微信登录页广告图', '', '3', '1', '', '15', 'mbox', '1479374930', null, null, null, null);
INSERT INTO `d_sys_dictionary` VALUES ('178', '30', 'VIP特权广告位', '', '3', '1', 'VIP特权广告位', '14', 'jiangt', '1481251753', null, null, null, null);
INSERT INTO `d_sys_dictionary` VALUES ('179', '167', 'APP活动中心广告', '', '3', '1', 'APP活动中心广告', '14', 'jiangt', '1488333263', null, null, null, null);
INSERT INTO `d_sys_dictionary` VALUES ('180', '167', 'APP积分商城广告', '', '3', '1', 'APP积分商城首页广告', '14', 'jiangt', '1489564205', null, null, null, null);
INSERT INTO `d_sys_dictionary` VALUES ('181', '167', 'APP首页弹窗广告', '', '3', '1', 'APP首页弹窗广告', '14', 'jiangt', '1489572675', null, null, null, null);
INSERT INTO `d_sys_dictionary` VALUES ('182', '167', 'APP登录送券弹出广告', '', '3', '1', 'APP登录送券弹出广告', '14', 'jiangt', '1489572780', null, null, null, null);
INSERT INTO `d_sys_dictionary` VALUES ('183', '96', '满标奖', '', '2', '1', '满标奖', '14', 'jiangt', '1496894384', null, null, null, null);
INSERT INTO `d_sys_dictionary` VALUES ('184', '96', '土豪奖', '', '2', '1', '土豪奖', '14', 'jiangt', '1496894409', null, null, null, null);
INSERT INTO `d_sys_dictionary` VALUES ('185', '9', '网贷知识', '', '2', '1', '网贷知识', '14', 'jiangt', '1502692597', null, null, null, null);
INSERT INTO `d_sys_dictionary` VALUES ('186', '185', '知识要点', '', '3', '1', '知识要点', '14', 'jiangt', '1502692620', null, null, null, null);
INSERT INTO `d_sys_dictionary` VALUES ('187', '185', '法律法规', '', '3', '1', '法律法规', '14', 'jiangt', '1502692634', null, null, null, null);
INSERT INTO `d_sys_dictionary` VALUES ('188', '167', 'APP首页顶部展示区图片', '', '3', '1', 'APP首页顶部展示区图片', '14', 'jiangt', '1513230138', null, null, null, null);
INSERT INTO `d_sys_dictionary` VALUES ('189', '30', 'PC首页弹窗', '', '3', '1', 'PC首页弹窗', '14', 'jiangt', '1515480803', null, null, null, null);
INSERT INTO `d_sys_dictionary` VALUES ('190', '30', 'PC首页悬浮', '', '3', '1', 'PC首页悬浮', '14', 'jiangt', '1515480828', null, null, null, null);
INSERT INTO `d_sys_dictionary` VALUES ('191', '31', '微信首页悬浮', '', '3', '1', '微信首页悬浮', '14', 'jiangt', '1515480864', null, null, null, null);
INSERT INTO `d_sys_dictionary` VALUES ('192', '167', 'APP底部皮肤', '', '3', '1', 'APP底部皮肤', '14', 'jiangt', '1517217776', null, null, null, null);
INSERT INTO `d_sys_dictionary` VALUES ('193', '10', '资讯管理', '', '3', '1', '资讯管理', '14', 'jiangt', '1521785480', null, null, null, null);
INSERT INTO `d_sys_dictionary` VALUES ('194', '2', 'adf', 'ad', '3', '1', 'afea', '2', 'liup', '1528685894', null, null, null, null);

-- ----------------------------
-- Table structure for `d_sys_parameter`
-- ----------------------------
DROP TABLE IF EXISTS `d_sys_parameter`;
CREATE TABLE `d_sys_parameter` (
  `par_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '参数ID',
  `par_name` varchar(100) NOT NULL COMMENT '参数描述',
  `par_key` varchar(50) NOT NULL COMMENT 'Key建唯一值',
  `par_type` tinyint(4) DEFAULT '1' COMMENT '参数类型 1单类型 ， 2多类型',
  `par_value` varchar(200) DEFAULT NULL COMMENT '参数值',
  `par_start` varchar(50) DEFAULT NULL COMMENT '参数起始值',
  `par_end` varchar(50) DEFAULT NULL COMMENT '参数结束值',
  `par_status` tinyint(4) DEFAULT '1' COMMENT '状态 1 整除 0 删除',
  `remark` varchar(200) DEFAULT NULL COMMENT '备注',
  `add_user_id` int(11) NOT NULL COMMENT '创建人ID',
  `add_user_name` varchar(50) DEFAULT NULL COMMENT '创建人',
  `add_datetime` int(11) NOT NULL COMMENT '创建时间',
  `edit_user_id` int(11) DEFAULT NULL COMMENT '修改人ID',
  `edit_user_name` varchar(50) DEFAULT NULL COMMENT '修改人',
  `edit_datetime` int(11) DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`par_id`)
) ENGINE=MyISAM AUTO_INCREMENT=42 DEFAULT CHARSET=utf8 COMMENT='系统参数表';

-- ----------------------------
-- Records of d_sys_parameter
-- ----------------------------
INSERT INTO `d_sys_parameter` VALUES ('37', 'sdf', '我的爱', '1', 'af', 'adf', 'adf', '0', 'afd', '85', 'adf', '7', '2', 'liup', '1528684287');
INSERT INTO `d_sys_parameter` VALUES ('38', 'adf', 'afd', '1', 'adf', 'adf', 'fda', '0', 'adf', '5', 'afe', '9', '7', 'afe', '48');
INSERT INTO `d_sys_parameter` VALUES ('39', 'wsaf', 'af', '1', 'afe', null, null, '0', 'af', '2', 'liup', '1528684259', null, null, null);
INSERT INTO `d_sys_parameter` VALUES ('40', '首页是否自动选中协议', 'REG_ISCHECK', '1', '5', null, null, '0', '首页是否自动选中协议', '2', 'liup', '1528965159', '2', 'liup', '1531452265');
INSERT INTO `d_sys_parameter` VALUES ('41', 'adf', 'e', '1', 'afd', null, null, '1', 'afd', '2', 'liup', '1531452256', null, null, null);

-- ----------------------------
-- Table structure for `d_sys_region`
-- ----------------------------
DROP TABLE IF EXISTS `d_sys_region`;
CREATE TABLE `d_sys_region` (
  `region_id` smallint(5) unsigned NOT NULL AUTO_INCREMENT,
  `parent_id` smallint(5) unsigned NOT NULL DEFAULT '0' COMMENT '父类',
  `name` varchar(120) NOT NULL DEFAULT '' COMMENT '名字',
  `level` tinyint(1) NOT NULL DEFAULT '2' COMMENT '级别',
  PRIMARY KEY (`region_id`),
  KEY `parentID` (`parent_id`) USING BTREE,
  KEY `level` (`level`) USING BTREE
) ENGINE=MyISAM AUTO_INCREMENT=3409 DEFAULT CHARSET=utf8 COMMENT='地区信息';

-- ----------------------------
-- Records of d_sys_region
-- ----------------------------
INSERT INTO `d_sys_region` VALUES ('1', '0', '中国', '0');
INSERT INTO `d_sys_region` VALUES ('2', '1', '北京', '1');
INSERT INTO `d_sys_region` VALUES ('3', '1', '安徽', '1');
INSERT INTO `d_sys_region` VALUES ('4', '1', '福建', '1');
INSERT INTO `d_sys_region` VALUES ('5', '1', '甘肃', '1');
INSERT INTO `d_sys_region` VALUES ('6', '1', '广东', '1');
INSERT INTO `d_sys_region` VALUES ('7', '1', '广西', '1');
INSERT INTO `d_sys_region` VALUES ('8', '1', '贵州', '1');
INSERT INTO `d_sys_region` VALUES ('9', '1', '海南', '1');
INSERT INTO `d_sys_region` VALUES ('10', '1', '河北', '1');
INSERT INTO `d_sys_region` VALUES ('11', '1', '河南', '1');
INSERT INTO `d_sys_region` VALUES ('12', '1', '黑龙江', '1');
INSERT INTO `d_sys_region` VALUES ('13', '1', '湖北', '1');
INSERT INTO `d_sys_region` VALUES ('14', '1', '湖南', '1');
INSERT INTO `d_sys_region` VALUES ('15', '1', '吉林', '1');
INSERT INTO `d_sys_region` VALUES ('16', '1', '江苏', '1');
INSERT INTO `d_sys_region` VALUES ('17', '1', '江西', '1');
INSERT INTO `d_sys_region` VALUES ('18', '1', '辽宁', '1');
INSERT INTO `d_sys_region` VALUES ('19', '1', '内蒙古', '1');
INSERT INTO `d_sys_region` VALUES ('20', '1', '宁夏', '1');
INSERT INTO `d_sys_region` VALUES ('21', '1', '青海', '1');
INSERT INTO `d_sys_region` VALUES ('22', '1', '山东', '1');
INSERT INTO `d_sys_region` VALUES ('23', '1', '山西', '1');
INSERT INTO `d_sys_region` VALUES ('24', '1', '陕西', '1');
INSERT INTO `d_sys_region` VALUES ('25', '1', '上海', '1');
INSERT INTO `d_sys_region` VALUES ('26', '1', '四川', '1');
INSERT INTO `d_sys_region` VALUES ('27', '1', '天津', '1');
INSERT INTO `d_sys_region` VALUES ('28', '1', '西藏', '1');
INSERT INTO `d_sys_region` VALUES ('29', '1', '新疆', '1');
INSERT INTO `d_sys_region` VALUES ('30', '1', '云南', '1');
INSERT INTO `d_sys_region` VALUES ('31', '1', '浙江', '1');
INSERT INTO `d_sys_region` VALUES ('32', '1', '重庆', '1');
INSERT INTO `d_sys_region` VALUES ('33', '1', '香港', '1');
INSERT INTO `d_sys_region` VALUES ('34', '1', '澳门', '1');
INSERT INTO `d_sys_region` VALUES ('35', '1', '台湾', '1');
INSERT INTO `d_sys_region` VALUES ('36', '3', '安庆', '2');
INSERT INTO `d_sys_region` VALUES ('37', '3', '蚌埠', '2');
INSERT INTO `d_sys_region` VALUES ('38', '3', '巢湖', '2');
INSERT INTO `d_sys_region` VALUES ('39', '3', '池州', '2');
INSERT INTO `d_sys_region` VALUES ('40', '3', '滁州', '2');
INSERT INTO `d_sys_region` VALUES ('41', '3', '阜阳', '2');
INSERT INTO `d_sys_region` VALUES ('42', '3', '淮北', '2');
INSERT INTO `d_sys_region` VALUES ('43', '3', '淮南', '2');
INSERT INTO `d_sys_region` VALUES ('44', '3', '黄山', '2');
INSERT INTO `d_sys_region` VALUES ('45', '3', '六安', '2');
INSERT INTO `d_sys_region` VALUES ('46', '3', '马鞍山', '2');
INSERT INTO `d_sys_region` VALUES ('47', '3', '宿州', '2');
INSERT INTO `d_sys_region` VALUES ('48', '3', '铜陵', '2');
INSERT INTO `d_sys_region` VALUES ('49', '3', '芜湖', '2');
INSERT INTO `d_sys_region` VALUES ('50', '3', '宣城', '2');
INSERT INTO `d_sys_region` VALUES ('51', '3', '亳州', '2');
INSERT INTO `d_sys_region` VALUES ('52', '2', '北京', '2');
INSERT INTO `d_sys_region` VALUES ('53', '4', '福州', '2');
INSERT INTO `d_sys_region` VALUES ('54', '4', '龙岩', '2');
INSERT INTO `d_sys_region` VALUES ('55', '4', '南平', '2');
INSERT INTO `d_sys_region` VALUES ('56', '4', '宁德', '2');
INSERT INTO `d_sys_region` VALUES ('57', '4', '莆田', '2');
INSERT INTO `d_sys_region` VALUES ('58', '4', '泉州', '2');
INSERT INTO `d_sys_region` VALUES ('59', '4', '三明', '2');
INSERT INTO `d_sys_region` VALUES ('60', '4', '厦门', '2');
INSERT INTO `d_sys_region` VALUES ('61', '4', '漳州', '2');
INSERT INTO `d_sys_region` VALUES ('62', '5', '兰州', '2');
INSERT INTO `d_sys_region` VALUES ('63', '5', '白银', '2');
INSERT INTO `d_sys_region` VALUES ('64', '5', '定西', '2');
INSERT INTO `d_sys_region` VALUES ('65', '5', '甘南', '2');
INSERT INTO `d_sys_region` VALUES ('66', '5', '嘉峪关', '2');
INSERT INTO `d_sys_region` VALUES ('67', '5', '金昌', '2');
INSERT INTO `d_sys_region` VALUES ('68', '5', '酒泉', '2');
INSERT INTO `d_sys_region` VALUES ('69', '5', '临夏', '2');
INSERT INTO `d_sys_region` VALUES ('70', '5', '陇南', '2');
INSERT INTO `d_sys_region` VALUES ('71', '5', '平凉', '2');
INSERT INTO `d_sys_region` VALUES ('72', '5', '庆阳', '2');
INSERT INTO `d_sys_region` VALUES ('73', '5', '天水', '2');
INSERT INTO `d_sys_region` VALUES ('74', '5', '武威', '2');
INSERT INTO `d_sys_region` VALUES ('75', '5', '张掖', '2');
INSERT INTO `d_sys_region` VALUES ('76', '6', '广州', '2');
INSERT INTO `d_sys_region` VALUES ('77', '6', '深圳', '2');
INSERT INTO `d_sys_region` VALUES ('78', '6', '潮州', '2');
INSERT INTO `d_sys_region` VALUES ('79', '6', '东莞', '2');
INSERT INTO `d_sys_region` VALUES ('80', '6', '佛山', '2');
INSERT INTO `d_sys_region` VALUES ('81', '6', '河源', '2');
INSERT INTO `d_sys_region` VALUES ('82', '6', '惠州', '2');
INSERT INTO `d_sys_region` VALUES ('83', '6', '江门', '2');
INSERT INTO `d_sys_region` VALUES ('84', '6', '揭阳', '2');
INSERT INTO `d_sys_region` VALUES ('85', '6', '茂名', '2');
INSERT INTO `d_sys_region` VALUES ('86', '6', '梅州', '2');
INSERT INTO `d_sys_region` VALUES ('87', '6', '清远', '2');
INSERT INTO `d_sys_region` VALUES ('88', '6', '汕头', '2');
INSERT INTO `d_sys_region` VALUES ('89', '6', '汕尾', '2');
INSERT INTO `d_sys_region` VALUES ('90', '6', '韶关', '2');
INSERT INTO `d_sys_region` VALUES ('91', '6', '阳江', '2');
INSERT INTO `d_sys_region` VALUES ('92', '6', '云浮', '2');
INSERT INTO `d_sys_region` VALUES ('93', '6', '湛江', '2');
INSERT INTO `d_sys_region` VALUES ('94', '6', '肇庆', '2');
INSERT INTO `d_sys_region` VALUES ('95', '6', '中山', '2');
INSERT INTO `d_sys_region` VALUES ('96', '6', '珠海', '2');
INSERT INTO `d_sys_region` VALUES ('97', '7', '南宁', '2');
INSERT INTO `d_sys_region` VALUES ('98', '7', '桂林', '2');
INSERT INTO `d_sys_region` VALUES ('99', '7', '百色', '2');
INSERT INTO `d_sys_region` VALUES ('100', '7', '北海', '2');
INSERT INTO `d_sys_region` VALUES ('101', '7', '崇左', '2');
INSERT INTO `d_sys_region` VALUES ('102', '7', '防城港', '2');
INSERT INTO `d_sys_region` VALUES ('103', '7', '贵港', '2');
INSERT INTO `d_sys_region` VALUES ('104', '7', '河池', '2');
INSERT INTO `d_sys_region` VALUES ('105', '7', '贺州', '2');
INSERT INTO `d_sys_region` VALUES ('106', '7', '来宾', '2');
INSERT INTO `d_sys_region` VALUES ('107', '7', '柳州', '2');
INSERT INTO `d_sys_region` VALUES ('108', '7', '钦州', '2');
INSERT INTO `d_sys_region` VALUES ('109', '7', '梧州', '2');
INSERT INTO `d_sys_region` VALUES ('110', '7', '玉林', '2');
INSERT INTO `d_sys_region` VALUES ('111', '8', '贵阳', '2');
INSERT INTO `d_sys_region` VALUES ('112', '8', '安顺', '2');
INSERT INTO `d_sys_region` VALUES ('113', '8', '毕节', '2');
INSERT INTO `d_sys_region` VALUES ('114', '8', '六盘水', '2');
INSERT INTO `d_sys_region` VALUES ('115', '8', '黔东南', '2');
INSERT INTO `d_sys_region` VALUES ('116', '8', '黔南', '2');
INSERT INTO `d_sys_region` VALUES ('117', '8', '黔西南', '2');
INSERT INTO `d_sys_region` VALUES ('118', '8', '铜仁', '2');
INSERT INTO `d_sys_region` VALUES ('119', '8', '遵义', '2');
INSERT INTO `d_sys_region` VALUES ('120', '9', '海口', '2');
INSERT INTO `d_sys_region` VALUES ('121', '9', '三亚', '2');
INSERT INTO `d_sys_region` VALUES ('122', '9', '白沙', '2');
INSERT INTO `d_sys_region` VALUES ('123', '9', '保亭', '2');
INSERT INTO `d_sys_region` VALUES ('124', '9', '昌江', '2');
INSERT INTO `d_sys_region` VALUES ('125', '9', '澄迈县', '2');
INSERT INTO `d_sys_region` VALUES ('126', '9', '定安县', '2');
INSERT INTO `d_sys_region` VALUES ('127', '9', '东方', '2');
INSERT INTO `d_sys_region` VALUES ('128', '9', '乐东', '2');
INSERT INTO `d_sys_region` VALUES ('129', '9', '临高县', '2');
INSERT INTO `d_sys_region` VALUES ('130', '9', '陵水', '2');
INSERT INTO `d_sys_region` VALUES ('131', '9', '琼海', '2');
INSERT INTO `d_sys_region` VALUES ('132', '9', '琼中', '2');
INSERT INTO `d_sys_region` VALUES ('133', '9', '屯昌县', '2');
INSERT INTO `d_sys_region` VALUES ('134', '9', '万宁', '2');
INSERT INTO `d_sys_region` VALUES ('135', '9', '文昌', '2');
INSERT INTO `d_sys_region` VALUES ('136', '9', '五指山', '2');
INSERT INTO `d_sys_region` VALUES ('137', '9', '儋州', '2');
INSERT INTO `d_sys_region` VALUES ('138', '10', '石家庄', '2');
INSERT INTO `d_sys_region` VALUES ('139', '10', '保定', '2');
INSERT INTO `d_sys_region` VALUES ('140', '10', '沧州', '2');
INSERT INTO `d_sys_region` VALUES ('141', '10', '承德', '2');
INSERT INTO `d_sys_region` VALUES ('142', '10', '邯郸', '2');
INSERT INTO `d_sys_region` VALUES ('143', '10', '衡水', '2');
INSERT INTO `d_sys_region` VALUES ('144', '10', '廊坊', '2');
INSERT INTO `d_sys_region` VALUES ('145', '10', '秦皇岛', '2');
INSERT INTO `d_sys_region` VALUES ('146', '10', '唐山', '2');
INSERT INTO `d_sys_region` VALUES ('147', '10', '邢台', '2');
INSERT INTO `d_sys_region` VALUES ('148', '10', '张家口', '2');
INSERT INTO `d_sys_region` VALUES ('149', '11', '郑州', '2');
INSERT INTO `d_sys_region` VALUES ('150', '11', '洛阳', '2');
INSERT INTO `d_sys_region` VALUES ('151', '11', '开封', '2');
INSERT INTO `d_sys_region` VALUES ('152', '11', '安阳', '2');
INSERT INTO `d_sys_region` VALUES ('153', '11', '鹤壁', '2');
INSERT INTO `d_sys_region` VALUES ('154', '11', '济源', '2');
INSERT INTO `d_sys_region` VALUES ('155', '11', '焦作', '2');
INSERT INTO `d_sys_region` VALUES ('156', '11', '南阳', '2');
INSERT INTO `d_sys_region` VALUES ('157', '11', '平顶山', '2');
INSERT INTO `d_sys_region` VALUES ('158', '11', '三门峡', '2');
INSERT INTO `d_sys_region` VALUES ('159', '11', '商丘', '2');
INSERT INTO `d_sys_region` VALUES ('160', '11', '新乡', '2');
INSERT INTO `d_sys_region` VALUES ('161', '11', '信阳', '2');
INSERT INTO `d_sys_region` VALUES ('162', '11', '许昌', '2');
INSERT INTO `d_sys_region` VALUES ('163', '11', '周口', '2');
INSERT INTO `d_sys_region` VALUES ('164', '11', '驻马店', '2');
INSERT INTO `d_sys_region` VALUES ('165', '11', '漯河', '2');
INSERT INTO `d_sys_region` VALUES ('166', '11', '濮阳', '2');
INSERT INTO `d_sys_region` VALUES ('167', '12', '哈尔滨', '2');
INSERT INTO `d_sys_region` VALUES ('168', '12', '大庆', '2');
INSERT INTO `d_sys_region` VALUES ('169', '12', '大兴安岭', '2');
INSERT INTO `d_sys_region` VALUES ('170', '12', '鹤岗', '2');
INSERT INTO `d_sys_region` VALUES ('171', '12', '黑河', '2');
INSERT INTO `d_sys_region` VALUES ('172', '12', '鸡西', '2');
INSERT INTO `d_sys_region` VALUES ('173', '12', '佳木斯', '2');
INSERT INTO `d_sys_region` VALUES ('174', '12', '牡丹江', '2');
INSERT INTO `d_sys_region` VALUES ('175', '12', '七台河', '2');
INSERT INTO `d_sys_region` VALUES ('176', '12', '齐齐哈尔', '2');
INSERT INTO `d_sys_region` VALUES ('177', '12', '双鸭山', '2');
INSERT INTO `d_sys_region` VALUES ('178', '12', '绥化', '2');
INSERT INTO `d_sys_region` VALUES ('179', '12', '伊春', '2');
INSERT INTO `d_sys_region` VALUES ('180', '13', '武汉', '2');
INSERT INTO `d_sys_region` VALUES ('181', '13', '仙桃', '2');
INSERT INTO `d_sys_region` VALUES ('182', '13', '鄂州', '2');
INSERT INTO `d_sys_region` VALUES ('183', '13', '黄冈', '2');
INSERT INTO `d_sys_region` VALUES ('184', '13', '黄石', '2');
INSERT INTO `d_sys_region` VALUES ('185', '13', '荆门', '2');
INSERT INTO `d_sys_region` VALUES ('186', '13', '荆州', '2');
INSERT INTO `d_sys_region` VALUES ('187', '13', '潜江', '2');
INSERT INTO `d_sys_region` VALUES ('188', '13', '神农架林区', '2');
INSERT INTO `d_sys_region` VALUES ('189', '13', '十堰', '2');
INSERT INTO `d_sys_region` VALUES ('190', '13', '随州', '2');
INSERT INTO `d_sys_region` VALUES ('191', '13', '天门', '2');
INSERT INTO `d_sys_region` VALUES ('192', '13', '咸宁', '2');
INSERT INTO `d_sys_region` VALUES ('193', '13', '襄樊', '2');
INSERT INTO `d_sys_region` VALUES ('194', '13', '孝感', '2');
INSERT INTO `d_sys_region` VALUES ('195', '13', '宜昌', '2');
INSERT INTO `d_sys_region` VALUES ('196', '13', '恩施', '2');
INSERT INTO `d_sys_region` VALUES ('197', '14', '长沙', '2');
INSERT INTO `d_sys_region` VALUES ('198', '14', '张家界', '2');
INSERT INTO `d_sys_region` VALUES ('199', '14', '常德', '2');
INSERT INTO `d_sys_region` VALUES ('200', '14', '郴州', '2');
INSERT INTO `d_sys_region` VALUES ('201', '14', '衡阳', '2');
INSERT INTO `d_sys_region` VALUES ('202', '14', '怀化', '2');
INSERT INTO `d_sys_region` VALUES ('203', '14', '娄底', '2');
INSERT INTO `d_sys_region` VALUES ('204', '14', '邵阳', '2');
INSERT INTO `d_sys_region` VALUES ('205', '14', '湘潭', '2');
INSERT INTO `d_sys_region` VALUES ('206', '14', '湘西', '2');
INSERT INTO `d_sys_region` VALUES ('207', '14', '益阳', '2');
INSERT INTO `d_sys_region` VALUES ('208', '14', '永州', '2');
INSERT INTO `d_sys_region` VALUES ('209', '14', '岳阳', '2');
INSERT INTO `d_sys_region` VALUES ('210', '14', '株洲', '2');
INSERT INTO `d_sys_region` VALUES ('211', '15', '长春', '2');
INSERT INTO `d_sys_region` VALUES ('212', '15', '吉林', '2');
INSERT INTO `d_sys_region` VALUES ('213', '15', '白城', '2');
INSERT INTO `d_sys_region` VALUES ('214', '15', '白山', '2');
INSERT INTO `d_sys_region` VALUES ('215', '15', '辽源', '2');
INSERT INTO `d_sys_region` VALUES ('216', '15', '四平', '2');
INSERT INTO `d_sys_region` VALUES ('217', '15', '松原', '2');
INSERT INTO `d_sys_region` VALUES ('218', '15', '通化', '2');
INSERT INTO `d_sys_region` VALUES ('219', '15', '延边', '2');
INSERT INTO `d_sys_region` VALUES ('220', '16', '南京', '2');
INSERT INTO `d_sys_region` VALUES ('221', '16', '苏州', '2');
INSERT INTO `d_sys_region` VALUES ('222', '16', '无锡', '2');
INSERT INTO `d_sys_region` VALUES ('223', '16', '常州', '2');
INSERT INTO `d_sys_region` VALUES ('224', '16', '淮安', '2');
INSERT INTO `d_sys_region` VALUES ('225', '16', '连云港', '2');
INSERT INTO `d_sys_region` VALUES ('226', '16', '南通', '2');
INSERT INTO `d_sys_region` VALUES ('227', '16', '宿迁', '2');
INSERT INTO `d_sys_region` VALUES ('228', '16', '泰州', '2');
INSERT INTO `d_sys_region` VALUES ('229', '16', '徐州', '2');
INSERT INTO `d_sys_region` VALUES ('230', '16', '盐城', '2');
INSERT INTO `d_sys_region` VALUES ('231', '16', '扬州', '2');
INSERT INTO `d_sys_region` VALUES ('232', '16', '镇江', '2');
INSERT INTO `d_sys_region` VALUES ('233', '17', '南昌', '2');
INSERT INTO `d_sys_region` VALUES ('234', '17', '抚州', '2');
INSERT INTO `d_sys_region` VALUES ('235', '17', '赣州', '2');
INSERT INTO `d_sys_region` VALUES ('236', '17', '吉安', '2');
INSERT INTO `d_sys_region` VALUES ('237', '17', '景德镇', '2');
INSERT INTO `d_sys_region` VALUES ('238', '17', '九江', '2');
INSERT INTO `d_sys_region` VALUES ('239', '17', '萍乡', '2');
INSERT INTO `d_sys_region` VALUES ('240', '17', '上饶', '2');
INSERT INTO `d_sys_region` VALUES ('241', '17', '新余', '2');
INSERT INTO `d_sys_region` VALUES ('242', '17', '宜春', '2');
INSERT INTO `d_sys_region` VALUES ('243', '17', '鹰潭', '2');
INSERT INTO `d_sys_region` VALUES ('244', '18', '沈阳', '2');
INSERT INTO `d_sys_region` VALUES ('245', '18', '大连', '2');
INSERT INTO `d_sys_region` VALUES ('246', '18', '鞍山', '2');
INSERT INTO `d_sys_region` VALUES ('247', '18', '本溪', '2');
INSERT INTO `d_sys_region` VALUES ('248', '18', '朝阳', '2');
INSERT INTO `d_sys_region` VALUES ('249', '18', '丹东', '2');
INSERT INTO `d_sys_region` VALUES ('250', '18', '抚顺', '2');
INSERT INTO `d_sys_region` VALUES ('251', '18', '阜新', '2');
INSERT INTO `d_sys_region` VALUES ('252', '18', '葫芦岛', '2');
INSERT INTO `d_sys_region` VALUES ('253', '18', '锦州', '2');
INSERT INTO `d_sys_region` VALUES ('254', '18', '辽阳', '2');
INSERT INTO `d_sys_region` VALUES ('255', '18', '盘锦', '2');
INSERT INTO `d_sys_region` VALUES ('256', '18', '铁岭', '2');
INSERT INTO `d_sys_region` VALUES ('257', '18', '营口', '2');
INSERT INTO `d_sys_region` VALUES ('258', '19', '呼和浩特', '2');
INSERT INTO `d_sys_region` VALUES ('259', '19', '阿拉善盟', '2');
INSERT INTO `d_sys_region` VALUES ('260', '19', '巴彦淖尔盟', '2');
INSERT INTO `d_sys_region` VALUES ('261', '19', '包头', '2');
INSERT INTO `d_sys_region` VALUES ('262', '19', '赤峰', '2');
INSERT INTO `d_sys_region` VALUES ('263', '19', '鄂尔多斯', '2');
INSERT INTO `d_sys_region` VALUES ('264', '19', '呼伦贝尔', '2');
INSERT INTO `d_sys_region` VALUES ('265', '19', '通辽', '2');
INSERT INTO `d_sys_region` VALUES ('266', '19', '乌海', '2');
INSERT INTO `d_sys_region` VALUES ('267', '19', '乌兰察布市', '2');
INSERT INTO `d_sys_region` VALUES ('268', '19', '锡林郭勒盟', '2');
INSERT INTO `d_sys_region` VALUES ('269', '19', '兴安盟', '2');
INSERT INTO `d_sys_region` VALUES ('270', '20', '银川', '2');
INSERT INTO `d_sys_region` VALUES ('271', '20', '固原', '2');
INSERT INTO `d_sys_region` VALUES ('272', '20', '石嘴山', '2');
INSERT INTO `d_sys_region` VALUES ('273', '20', '吴忠', '2');
INSERT INTO `d_sys_region` VALUES ('274', '20', '中卫', '2');
INSERT INTO `d_sys_region` VALUES ('275', '21', '西宁', '2');
INSERT INTO `d_sys_region` VALUES ('276', '21', '果洛', '2');
INSERT INTO `d_sys_region` VALUES ('277', '21', '海北', '2');
INSERT INTO `d_sys_region` VALUES ('278', '21', '海东', '2');
INSERT INTO `d_sys_region` VALUES ('279', '21', '海南', '2');
INSERT INTO `d_sys_region` VALUES ('280', '21', '海西', '2');
INSERT INTO `d_sys_region` VALUES ('281', '21', '黄南', '2');
INSERT INTO `d_sys_region` VALUES ('282', '21', '玉树', '2');
INSERT INTO `d_sys_region` VALUES ('283', '22', '济南', '2');
INSERT INTO `d_sys_region` VALUES ('284', '22', '青岛', '2');
INSERT INTO `d_sys_region` VALUES ('285', '22', '滨州', '2');
INSERT INTO `d_sys_region` VALUES ('286', '22', '德州', '2');
INSERT INTO `d_sys_region` VALUES ('287', '22', '东营', '2');
INSERT INTO `d_sys_region` VALUES ('288', '22', '菏泽', '2');
INSERT INTO `d_sys_region` VALUES ('289', '22', '济宁', '2');
INSERT INTO `d_sys_region` VALUES ('290', '22', '莱芜', '2');
INSERT INTO `d_sys_region` VALUES ('291', '22', '聊城', '2');
INSERT INTO `d_sys_region` VALUES ('292', '22', '临沂', '2');
INSERT INTO `d_sys_region` VALUES ('293', '22', '日照', '2');
INSERT INTO `d_sys_region` VALUES ('294', '22', '泰安', '2');
INSERT INTO `d_sys_region` VALUES ('295', '22', '威海', '2');
INSERT INTO `d_sys_region` VALUES ('296', '22', '潍坊', '2');
INSERT INTO `d_sys_region` VALUES ('297', '22', '烟台', '2');
INSERT INTO `d_sys_region` VALUES ('298', '22', '枣庄', '2');
INSERT INTO `d_sys_region` VALUES ('299', '22', '淄博', '2');
INSERT INTO `d_sys_region` VALUES ('300', '23', '太原', '2');
INSERT INTO `d_sys_region` VALUES ('301', '23', '长治', '2');
INSERT INTO `d_sys_region` VALUES ('302', '23', '大同', '2');
INSERT INTO `d_sys_region` VALUES ('303', '23', '晋城', '2');
INSERT INTO `d_sys_region` VALUES ('304', '23', '晋中', '2');
INSERT INTO `d_sys_region` VALUES ('305', '23', '临汾', '2');
INSERT INTO `d_sys_region` VALUES ('306', '23', '吕梁', '2');
INSERT INTO `d_sys_region` VALUES ('307', '23', '朔州', '2');
INSERT INTO `d_sys_region` VALUES ('308', '23', '忻州', '2');
INSERT INTO `d_sys_region` VALUES ('309', '23', '阳泉', '2');
INSERT INTO `d_sys_region` VALUES ('310', '23', '运城', '2');
INSERT INTO `d_sys_region` VALUES ('311', '24', '西安', '2');
INSERT INTO `d_sys_region` VALUES ('312', '24', '安康', '2');
INSERT INTO `d_sys_region` VALUES ('313', '24', '宝鸡', '2');
INSERT INTO `d_sys_region` VALUES ('314', '24', '汉中', '2');
INSERT INTO `d_sys_region` VALUES ('315', '24', '商洛', '2');
INSERT INTO `d_sys_region` VALUES ('316', '24', '铜川', '2');
INSERT INTO `d_sys_region` VALUES ('317', '24', '渭南', '2');
INSERT INTO `d_sys_region` VALUES ('318', '24', '咸阳', '2');
INSERT INTO `d_sys_region` VALUES ('319', '24', '延安', '2');
INSERT INTO `d_sys_region` VALUES ('320', '24', '榆林', '2');
INSERT INTO `d_sys_region` VALUES ('321', '25', '上海', '2');
INSERT INTO `d_sys_region` VALUES ('322', '26', '成都', '2');
INSERT INTO `d_sys_region` VALUES ('323', '26', '绵阳', '2');
INSERT INTO `d_sys_region` VALUES ('324', '26', '阿坝', '2');
INSERT INTO `d_sys_region` VALUES ('325', '26', '巴中', '2');
INSERT INTO `d_sys_region` VALUES ('326', '26', '达州', '2');
INSERT INTO `d_sys_region` VALUES ('327', '26', '德阳', '2');
INSERT INTO `d_sys_region` VALUES ('328', '26', '甘孜', '2');
INSERT INTO `d_sys_region` VALUES ('329', '26', '广安', '2');
INSERT INTO `d_sys_region` VALUES ('330', '26', '广元', '2');
INSERT INTO `d_sys_region` VALUES ('331', '26', '乐山', '2');
INSERT INTO `d_sys_region` VALUES ('332', '26', '凉山', '2');
INSERT INTO `d_sys_region` VALUES ('333', '26', '眉山', '2');
INSERT INTO `d_sys_region` VALUES ('334', '26', '南充', '2');
INSERT INTO `d_sys_region` VALUES ('335', '26', '内江', '2');
INSERT INTO `d_sys_region` VALUES ('336', '26', '攀枝花', '2');
INSERT INTO `d_sys_region` VALUES ('337', '26', '遂宁', '2');
INSERT INTO `d_sys_region` VALUES ('338', '26', '雅安', '2');
INSERT INTO `d_sys_region` VALUES ('339', '26', '宜宾', '2');
INSERT INTO `d_sys_region` VALUES ('340', '26', '资阳', '2');
INSERT INTO `d_sys_region` VALUES ('341', '26', '自贡', '2');
INSERT INTO `d_sys_region` VALUES ('342', '26', '泸州', '2');
INSERT INTO `d_sys_region` VALUES ('343', '27', '天津', '2');
INSERT INTO `d_sys_region` VALUES ('344', '28', '拉萨', '2');
INSERT INTO `d_sys_region` VALUES ('345', '28', '阿里', '2');
INSERT INTO `d_sys_region` VALUES ('346', '28', '昌都', '2');
INSERT INTO `d_sys_region` VALUES ('347', '28', '林芝', '2');
INSERT INTO `d_sys_region` VALUES ('348', '28', '那曲', '2');
INSERT INTO `d_sys_region` VALUES ('349', '28', '日喀则', '2');
INSERT INTO `d_sys_region` VALUES ('350', '28', '山南', '2');
INSERT INTO `d_sys_region` VALUES ('351', '29', '乌鲁木齐', '2');
INSERT INTO `d_sys_region` VALUES ('352', '29', '阿克苏', '2');
INSERT INTO `d_sys_region` VALUES ('353', '29', '阿拉尔', '2');
INSERT INTO `d_sys_region` VALUES ('354', '29', '巴音郭楞', '2');
INSERT INTO `d_sys_region` VALUES ('355', '29', '博尔塔拉', '2');
INSERT INTO `d_sys_region` VALUES ('356', '29', '昌吉', '2');
INSERT INTO `d_sys_region` VALUES ('357', '29', '哈密', '2');
INSERT INTO `d_sys_region` VALUES ('358', '29', '和田', '2');
INSERT INTO `d_sys_region` VALUES ('359', '29', '喀什', '2');
INSERT INTO `d_sys_region` VALUES ('360', '29', '克拉玛依', '2');
INSERT INTO `d_sys_region` VALUES ('361', '29', '克孜勒苏', '2');
INSERT INTO `d_sys_region` VALUES ('362', '29', '石河子', '2');
INSERT INTO `d_sys_region` VALUES ('363', '29', '图木舒克', '2');
INSERT INTO `d_sys_region` VALUES ('364', '29', '吐鲁番', '2');
INSERT INTO `d_sys_region` VALUES ('365', '29', '五家渠', '2');
INSERT INTO `d_sys_region` VALUES ('366', '29', '伊犁', '2');
INSERT INTO `d_sys_region` VALUES ('367', '30', '昆明', '2');
INSERT INTO `d_sys_region` VALUES ('368', '30', '怒江', '2');
INSERT INTO `d_sys_region` VALUES ('369', '30', '普洱', '2');
INSERT INTO `d_sys_region` VALUES ('370', '30', '丽江', '2');
INSERT INTO `d_sys_region` VALUES ('371', '30', '保山', '2');
INSERT INTO `d_sys_region` VALUES ('372', '30', '楚雄', '2');
INSERT INTO `d_sys_region` VALUES ('373', '30', '大理', '2');
INSERT INTO `d_sys_region` VALUES ('374', '30', '德宏', '2');
INSERT INTO `d_sys_region` VALUES ('375', '30', '迪庆', '2');
INSERT INTO `d_sys_region` VALUES ('376', '30', '红河', '2');
INSERT INTO `d_sys_region` VALUES ('377', '30', '临沧', '2');
INSERT INTO `d_sys_region` VALUES ('378', '30', '曲靖', '2');
INSERT INTO `d_sys_region` VALUES ('379', '30', '文山', '2');
INSERT INTO `d_sys_region` VALUES ('380', '30', '西双版纳', '2');
INSERT INTO `d_sys_region` VALUES ('381', '30', '玉溪', '2');
INSERT INTO `d_sys_region` VALUES ('382', '30', '昭通', '2');
INSERT INTO `d_sys_region` VALUES ('383', '31', '杭州', '2');
INSERT INTO `d_sys_region` VALUES ('384', '31', '湖州', '2');
INSERT INTO `d_sys_region` VALUES ('385', '31', '嘉兴', '2');
INSERT INTO `d_sys_region` VALUES ('386', '31', '金华', '2');
INSERT INTO `d_sys_region` VALUES ('387', '31', '丽水', '2');
INSERT INTO `d_sys_region` VALUES ('388', '31', '宁波', '2');
INSERT INTO `d_sys_region` VALUES ('389', '31', '绍兴', '2');
INSERT INTO `d_sys_region` VALUES ('390', '31', '台州', '2');
INSERT INTO `d_sys_region` VALUES ('391', '31', '温州', '2');
INSERT INTO `d_sys_region` VALUES ('392', '31', '舟山', '2');
INSERT INTO `d_sys_region` VALUES ('393', '31', '衢州', '2');
INSERT INTO `d_sys_region` VALUES ('394', '32', '重庆', '2');
INSERT INTO `d_sys_region` VALUES ('395', '33', '香港', '2');
INSERT INTO `d_sys_region` VALUES ('396', '34', '澳门', '2');
INSERT INTO `d_sys_region` VALUES ('397', '35', '台湾', '2');
INSERT INTO `d_sys_region` VALUES ('398', '36', '迎江区', '3');
INSERT INTO `d_sys_region` VALUES ('399', '36', '大观区', '3');
INSERT INTO `d_sys_region` VALUES ('400', '36', '宜秀区', '3');
INSERT INTO `d_sys_region` VALUES ('401', '36', '桐城市', '3');
INSERT INTO `d_sys_region` VALUES ('402', '36', '怀宁县', '3');
INSERT INTO `d_sys_region` VALUES ('403', '36', '枞阳县', '3');
INSERT INTO `d_sys_region` VALUES ('404', '36', '潜山县', '3');
INSERT INTO `d_sys_region` VALUES ('405', '36', '太湖县', '3');
INSERT INTO `d_sys_region` VALUES ('406', '36', '宿松县', '3');
INSERT INTO `d_sys_region` VALUES ('407', '36', '望江县', '3');
INSERT INTO `d_sys_region` VALUES ('408', '36', '岳西县', '3');
INSERT INTO `d_sys_region` VALUES ('409', '37', '中市区', '3');
INSERT INTO `d_sys_region` VALUES ('410', '37', '东市区', '3');
INSERT INTO `d_sys_region` VALUES ('411', '37', '西市区', '3');
INSERT INTO `d_sys_region` VALUES ('412', '37', '郊区', '3');
INSERT INTO `d_sys_region` VALUES ('413', '37', '怀远县', '3');
INSERT INTO `d_sys_region` VALUES ('414', '37', '五河县', '3');
INSERT INTO `d_sys_region` VALUES ('415', '37', '固镇县', '3');
INSERT INTO `d_sys_region` VALUES ('416', '38', '居巢区', '3');
INSERT INTO `d_sys_region` VALUES ('417', '38', '庐江县', '3');
INSERT INTO `d_sys_region` VALUES ('418', '38', '无为县', '3');
INSERT INTO `d_sys_region` VALUES ('419', '38', '含山县', '3');
INSERT INTO `d_sys_region` VALUES ('420', '38', '和县', '3');
INSERT INTO `d_sys_region` VALUES ('421', '39', '贵池区', '3');
INSERT INTO `d_sys_region` VALUES ('422', '39', '东至县', '3');
INSERT INTO `d_sys_region` VALUES ('423', '39', '石台县', '3');
INSERT INTO `d_sys_region` VALUES ('424', '39', '青阳县', '3');
INSERT INTO `d_sys_region` VALUES ('425', '40', '琅琊区', '3');
INSERT INTO `d_sys_region` VALUES ('426', '40', '南谯区', '3');
INSERT INTO `d_sys_region` VALUES ('427', '40', '天长市', '3');
INSERT INTO `d_sys_region` VALUES ('428', '40', '明光市', '3');
INSERT INTO `d_sys_region` VALUES ('429', '40', '来安县', '3');
INSERT INTO `d_sys_region` VALUES ('430', '40', '全椒县', '3');
INSERT INTO `d_sys_region` VALUES ('431', '40', '定远县', '3');
INSERT INTO `d_sys_region` VALUES ('432', '40', '凤阳县', '3');
INSERT INTO `d_sys_region` VALUES ('433', '41', '蚌山区', '3');
INSERT INTO `d_sys_region` VALUES ('434', '41', '龙子湖区', '3');
INSERT INTO `d_sys_region` VALUES ('435', '41', '禹会区', '3');
INSERT INTO `d_sys_region` VALUES ('436', '41', '淮上区', '3');
INSERT INTO `d_sys_region` VALUES ('437', '41', '颍州区', '3');
INSERT INTO `d_sys_region` VALUES ('438', '41', '颍东区', '3');
INSERT INTO `d_sys_region` VALUES ('439', '41', '颍泉区', '3');
INSERT INTO `d_sys_region` VALUES ('440', '41', '界首市', '3');
INSERT INTO `d_sys_region` VALUES ('441', '41', '临泉县', '3');
INSERT INTO `d_sys_region` VALUES ('442', '41', '太和县', '3');
INSERT INTO `d_sys_region` VALUES ('443', '41', '阜南县', '3');
INSERT INTO `d_sys_region` VALUES ('444', '41', '颖上县', '3');
INSERT INTO `d_sys_region` VALUES ('445', '42', '相山区', '3');
INSERT INTO `d_sys_region` VALUES ('446', '42', '杜集区', '3');
INSERT INTO `d_sys_region` VALUES ('447', '42', '烈山区', '3');
INSERT INTO `d_sys_region` VALUES ('448', '42', '濉溪县', '3');
INSERT INTO `d_sys_region` VALUES ('449', '43', '田家庵区', '3');
INSERT INTO `d_sys_region` VALUES ('450', '43', '大通区', '3');
INSERT INTO `d_sys_region` VALUES ('451', '43', '谢家集区', '3');
INSERT INTO `d_sys_region` VALUES ('452', '43', '八公山区', '3');
INSERT INTO `d_sys_region` VALUES ('453', '43', '潘集区', '3');
INSERT INTO `d_sys_region` VALUES ('454', '43', '凤台县', '3');
INSERT INTO `d_sys_region` VALUES ('455', '44', '屯溪区', '3');
INSERT INTO `d_sys_region` VALUES ('456', '44', '黄山区', '3');
INSERT INTO `d_sys_region` VALUES ('457', '44', '徽州区', '3');
INSERT INTO `d_sys_region` VALUES ('458', '44', '歙县', '3');
INSERT INTO `d_sys_region` VALUES ('459', '44', '休宁县', '3');
INSERT INTO `d_sys_region` VALUES ('460', '44', '黟县', '3');
INSERT INTO `d_sys_region` VALUES ('461', '44', '祁门县', '3');
INSERT INTO `d_sys_region` VALUES ('462', '45', '金安区', '3');
INSERT INTO `d_sys_region` VALUES ('463', '45', '裕安区', '3');
INSERT INTO `d_sys_region` VALUES ('464', '45', '寿县', '3');
INSERT INTO `d_sys_region` VALUES ('465', '45', '霍邱县', '3');
INSERT INTO `d_sys_region` VALUES ('466', '45', '舒城县', '3');
INSERT INTO `d_sys_region` VALUES ('467', '45', '金寨县', '3');
INSERT INTO `d_sys_region` VALUES ('468', '45', '霍山县', '3');
INSERT INTO `d_sys_region` VALUES ('469', '46', '雨山区', '3');
INSERT INTO `d_sys_region` VALUES ('470', '46', '花山区', '3');
INSERT INTO `d_sys_region` VALUES ('471', '46', '金家庄区', '3');
INSERT INTO `d_sys_region` VALUES ('472', '46', '当涂县', '3');
INSERT INTO `d_sys_region` VALUES ('473', '47', '埇桥区', '3');
INSERT INTO `d_sys_region` VALUES ('474', '47', '砀山县', '3');
INSERT INTO `d_sys_region` VALUES ('475', '47', '萧县', '3');
INSERT INTO `d_sys_region` VALUES ('476', '47', '灵璧县', '3');
INSERT INTO `d_sys_region` VALUES ('477', '47', '泗县', '3');
INSERT INTO `d_sys_region` VALUES ('478', '48', '铜官山区', '3');
INSERT INTO `d_sys_region` VALUES ('479', '48', '狮子山区', '3');
INSERT INTO `d_sys_region` VALUES ('480', '48', '郊区', '3');
INSERT INTO `d_sys_region` VALUES ('481', '48', '铜陵县', '3');
INSERT INTO `d_sys_region` VALUES ('482', '49', '镜湖区', '3');
INSERT INTO `d_sys_region` VALUES ('483', '49', '弋江区', '3');
INSERT INTO `d_sys_region` VALUES ('484', '49', '鸠江区', '3');
INSERT INTO `d_sys_region` VALUES ('485', '49', '三山区', '3');
INSERT INTO `d_sys_region` VALUES ('486', '49', '芜湖县', '3');
INSERT INTO `d_sys_region` VALUES ('487', '49', '繁昌县', '3');
INSERT INTO `d_sys_region` VALUES ('488', '49', '南陵县', '3');
INSERT INTO `d_sys_region` VALUES ('489', '50', '宣州区', '3');
INSERT INTO `d_sys_region` VALUES ('490', '50', '宁国市', '3');
INSERT INTO `d_sys_region` VALUES ('491', '50', '郎溪县', '3');
INSERT INTO `d_sys_region` VALUES ('492', '50', '广德县', '3');
INSERT INTO `d_sys_region` VALUES ('493', '50', '泾县', '3');
INSERT INTO `d_sys_region` VALUES ('494', '50', '绩溪县', '3');
INSERT INTO `d_sys_region` VALUES ('495', '50', '旌德县', '3');
INSERT INTO `d_sys_region` VALUES ('496', '51', '涡阳县', '3');
INSERT INTO `d_sys_region` VALUES ('497', '51', '蒙城县', '3');
INSERT INTO `d_sys_region` VALUES ('498', '51', '利辛县', '3');
INSERT INTO `d_sys_region` VALUES ('499', '51', '谯城区', '3');
INSERT INTO `d_sys_region` VALUES ('500', '52', '东城区', '3');
INSERT INTO `d_sys_region` VALUES ('501', '52', '西城区', '3');
INSERT INTO `d_sys_region` VALUES ('502', '52', '海淀区', '3');
INSERT INTO `d_sys_region` VALUES ('503', '52', '朝阳区', '3');
INSERT INTO `d_sys_region` VALUES ('504', '52', '崇文区', '3');
INSERT INTO `d_sys_region` VALUES ('505', '52', '宣武区', '3');
INSERT INTO `d_sys_region` VALUES ('506', '52', '丰台区', '3');
INSERT INTO `d_sys_region` VALUES ('507', '52', '石景山区', '3');
INSERT INTO `d_sys_region` VALUES ('508', '52', '房山区', '3');
INSERT INTO `d_sys_region` VALUES ('509', '52', '门头沟区', '3');
INSERT INTO `d_sys_region` VALUES ('510', '52', '通州区', '3');
INSERT INTO `d_sys_region` VALUES ('511', '52', '顺义区', '3');
INSERT INTO `d_sys_region` VALUES ('512', '52', '昌平区', '3');
INSERT INTO `d_sys_region` VALUES ('513', '52', '怀柔区', '3');
INSERT INTO `d_sys_region` VALUES ('514', '52', '平谷区', '3');
INSERT INTO `d_sys_region` VALUES ('515', '52', '大兴区', '3');
INSERT INTO `d_sys_region` VALUES ('516', '52', '密云县', '3');
INSERT INTO `d_sys_region` VALUES ('517', '52', '延庆县', '3');
INSERT INTO `d_sys_region` VALUES ('518', '53', '鼓楼区', '3');
INSERT INTO `d_sys_region` VALUES ('519', '53', '台江区', '3');
INSERT INTO `d_sys_region` VALUES ('520', '53', '仓山区', '3');
INSERT INTO `d_sys_region` VALUES ('521', '53', '马尾区', '3');
INSERT INTO `d_sys_region` VALUES ('522', '53', '晋安区', '3');
INSERT INTO `d_sys_region` VALUES ('523', '53', '福清市', '3');
INSERT INTO `d_sys_region` VALUES ('524', '53', '长乐市', '3');
INSERT INTO `d_sys_region` VALUES ('525', '53', '闽侯县', '3');
INSERT INTO `d_sys_region` VALUES ('526', '53', '连江县', '3');
INSERT INTO `d_sys_region` VALUES ('527', '53', '罗源县', '3');
INSERT INTO `d_sys_region` VALUES ('528', '53', '闽清县', '3');
INSERT INTO `d_sys_region` VALUES ('529', '53', '永泰县', '3');
INSERT INTO `d_sys_region` VALUES ('530', '53', '平潭县', '3');
INSERT INTO `d_sys_region` VALUES ('531', '54', '新罗区', '3');
INSERT INTO `d_sys_region` VALUES ('532', '54', '漳平市', '3');
INSERT INTO `d_sys_region` VALUES ('533', '54', '长汀县', '3');
INSERT INTO `d_sys_region` VALUES ('534', '54', '永定县', '3');
INSERT INTO `d_sys_region` VALUES ('535', '54', '上杭县', '3');
INSERT INTO `d_sys_region` VALUES ('536', '54', '武平县', '3');
INSERT INTO `d_sys_region` VALUES ('537', '54', '连城县', '3');
INSERT INTO `d_sys_region` VALUES ('538', '55', '延平区', '3');
INSERT INTO `d_sys_region` VALUES ('539', '55', '邵武市', '3');
INSERT INTO `d_sys_region` VALUES ('540', '55', '武夷山市', '3');
INSERT INTO `d_sys_region` VALUES ('541', '55', '建瓯市', '3');
INSERT INTO `d_sys_region` VALUES ('542', '55', '建阳市', '3');
INSERT INTO `d_sys_region` VALUES ('543', '55', '顺昌县', '3');
INSERT INTO `d_sys_region` VALUES ('544', '55', '浦城县', '3');
INSERT INTO `d_sys_region` VALUES ('545', '55', '光泽县', '3');
INSERT INTO `d_sys_region` VALUES ('546', '55', '松溪县', '3');
INSERT INTO `d_sys_region` VALUES ('547', '55', '政和县', '3');
INSERT INTO `d_sys_region` VALUES ('548', '56', '蕉城区', '3');
INSERT INTO `d_sys_region` VALUES ('549', '56', '福安市', '3');
INSERT INTO `d_sys_region` VALUES ('550', '56', '福鼎市', '3');
INSERT INTO `d_sys_region` VALUES ('551', '56', '霞浦县', '3');
INSERT INTO `d_sys_region` VALUES ('552', '56', '古田县', '3');
INSERT INTO `d_sys_region` VALUES ('553', '56', '屏南县', '3');
INSERT INTO `d_sys_region` VALUES ('554', '56', '寿宁县', '3');
INSERT INTO `d_sys_region` VALUES ('555', '56', '周宁县', '3');
INSERT INTO `d_sys_region` VALUES ('556', '56', '柘荣县', '3');
INSERT INTO `d_sys_region` VALUES ('557', '57', '城厢区', '3');
INSERT INTO `d_sys_region` VALUES ('558', '57', '涵江区', '3');
INSERT INTO `d_sys_region` VALUES ('559', '57', '荔城区', '3');
INSERT INTO `d_sys_region` VALUES ('560', '57', '秀屿区', '3');
INSERT INTO `d_sys_region` VALUES ('561', '57', '仙游县', '3');
INSERT INTO `d_sys_region` VALUES ('562', '58', '鲤城区', '3');
INSERT INTO `d_sys_region` VALUES ('563', '58', '丰泽区', '3');
INSERT INTO `d_sys_region` VALUES ('564', '58', '洛江区', '3');
INSERT INTO `d_sys_region` VALUES ('565', '58', '清濛开发区', '3');
INSERT INTO `d_sys_region` VALUES ('566', '58', '泉港区', '3');
INSERT INTO `d_sys_region` VALUES ('567', '58', '石狮市', '3');
INSERT INTO `d_sys_region` VALUES ('568', '58', '晋江市', '3');
INSERT INTO `d_sys_region` VALUES ('569', '58', '南安市', '3');
INSERT INTO `d_sys_region` VALUES ('570', '58', '惠安县', '3');
INSERT INTO `d_sys_region` VALUES ('571', '58', '安溪县', '3');
INSERT INTO `d_sys_region` VALUES ('572', '58', '永春县', '3');
INSERT INTO `d_sys_region` VALUES ('573', '58', '德化县', '3');
INSERT INTO `d_sys_region` VALUES ('574', '58', '金门县', '3');
INSERT INTO `d_sys_region` VALUES ('575', '59', '梅列区', '3');
INSERT INTO `d_sys_region` VALUES ('576', '59', '三元区', '3');
INSERT INTO `d_sys_region` VALUES ('577', '59', '永安市', '3');
INSERT INTO `d_sys_region` VALUES ('578', '59', '明溪县', '3');
INSERT INTO `d_sys_region` VALUES ('579', '59', '清流县', '3');
INSERT INTO `d_sys_region` VALUES ('580', '59', '宁化县', '3');
INSERT INTO `d_sys_region` VALUES ('581', '59', '大田县', '3');
INSERT INTO `d_sys_region` VALUES ('582', '59', '尤溪县', '3');
INSERT INTO `d_sys_region` VALUES ('583', '59', '沙县', '3');
INSERT INTO `d_sys_region` VALUES ('584', '59', '将乐县', '3');
INSERT INTO `d_sys_region` VALUES ('585', '59', '泰宁县', '3');
INSERT INTO `d_sys_region` VALUES ('586', '59', '建宁县', '3');
INSERT INTO `d_sys_region` VALUES ('587', '60', '思明区', '3');
INSERT INTO `d_sys_region` VALUES ('588', '60', '海沧区', '3');
INSERT INTO `d_sys_region` VALUES ('589', '60', '湖里区', '3');
INSERT INTO `d_sys_region` VALUES ('590', '60', '集美区', '3');
INSERT INTO `d_sys_region` VALUES ('591', '60', '同安区', '3');
INSERT INTO `d_sys_region` VALUES ('592', '60', '翔安区', '3');
INSERT INTO `d_sys_region` VALUES ('593', '61', '芗城区', '3');
INSERT INTO `d_sys_region` VALUES ('594', '61', '龙文区', '3');
INSERT INTO `d_sys_region` VALUES ('595', '61', '龙海市', '3');
INSERT INTO `d_sys_region` VALUES ('596', '61', '云霄县', '3');
INSERT INTO `d_sys_region` VALUES ('597', '61', '漳浦县', '3');
INSERT INTO `d_sys_region` VALUES ('598', '61', '诏安县', '3');
INSERT INTO `d_sys_region` VALUES ('599', '61', '长泰县', '3');
INSERT INTO `d_sys_region` VALUES ('600', '61', '东山县', '3');
INSERT INTO `d_sys_region` VALUES ('601', '61', '南靖县', '3');
INSERT INTO `d_sys_region` VALUES ('602', '61', '平和县', '3');
INSERT INTO `d_sys_region` VALUES ('603', '61', '华安县', '3');
INSERT INTO `d_sys_region` VALUES ('604', '62', '皋兰县', '3');
INSERT INTO `d_sys_region` VALUES ('605', '62', '城关区', '3');
INSERT INTO `d_sys_region` VALUES ('606', '62', '七里河区', '3');
INSERT INTO `d_sys_region` VALUES ('607', '62', '西固区', '3');
INSERT INTO `d_sys_region` VALUES ('608', '62', '安宁区', '3');
INSERT INTO `d_sys_region` VALUES ('609', '62', '红古区', '3');
INSERT INTO `d_sys_region` VALUES ('610', '62', '永登县', '3');
INSERT INTO `d_sys_region` VALUES ('611', '62', '榆中县', '3');
INSERT INTO `d_sys_region` VALUES ('612', '63', '白银区', '3');
INSERT INTO `d_sys_region` VALUES ('613', '63', '平川区', '3');
INSERT INTO `d_sys_region` VALUES ('614', '63', '会宁县', '3');
INSERT INTO `d_sys_region` VALUES ('615', '63', '景泰县', '3');
INSERT INTO `d_sys_region` VALUES ('616', '63', '靖远县', '3');
INSERT INTO `d_sys_region` VALUES ('617', '64', '临洮县', '3');
INSERT INTO `d_sys_region` VALUES ('618', '64', '陇西县', '3');
INSERT INTO `d_sys_region` VALUES ('619', '64', '通渭县', '3');
INSERT INTO `d_sys_region` VALUES ('620', '64', '渭源县', '3');
INSERT INTO `d_sys_region` VALUES ('621', '64', '漳县', '3');
INSERT INTO `d_sys_region` VALUES ('622', '64', '岷县', '3');
INSERT INTO `d_sys_region` VALUES ('623', '64', '安定区', '3');
INSERT INTO `d_sys_region` VALUES ('624', '64', '安定区', '3');
INSERT INTO `d_sys_region` VALUES ('625', '65', '合作市', '3');
INSERT INTO `d_sys_region` VALUES ('626', '65', '临潭县', '3');
INSERT INTO `d_sys_region` VALUES ('627', '65', '卓尼县', '3');
INSERT INTO `d_sys_region` VALUES ('628', '65', '舟曲县', '3');
INSERT INTO `d_sys_region` VALUES ('629', '65', '迭部县', '3');
INSERT INTO `d_sys_region` VALUES ('630', '65', '玛曲县', '3');
INSERT INTO `d_sys_region` VALUES ('631', '65', '碌曲县', '3');
INSERT INTO `d_sys_region` VALUES ('632', '65', '夏河县', '3');
INSERT INTO `d_sys_region` VALUES ('633', '66', '嘉峪关市', '3');
INSERT INTO `d_sys_region` VALUES ('634', '67', '金川区', '3');
INSERT INTO `d_sys_region` VALUES ('635', '67', '永昌县', '3');
INSERT INTO `d_sys_region` VALUES ('636', '68', '肃州区', '3');
INSERT INTO `d_sys_region` VALUES ('637', '68', '玉门市', '3');
INSERT INTO `d_sys_region` VALUES ('638', '68', '敦煌市', '3');
INSERT INTO `d_sys_region` VALUES ('639', '68', '金塔县', '3');
INSERT INTO `d_sys_region` VALUES ('640', '68', '瓜州县', '3');
INSERT INTO `d_sys_region` VALUES ('641', '68', '肃北', '3');
INSERT INTO `d_sys_region` VALUES ('642', '68', '阿克塞', '3');
INSERT INTO `d_sys_region` VALUES ('643', '69', '临夏市', '3');
INSERT INTO `d_sys_region` VALUES ('644', '69', '临夏县', '3');
INSERT INTO `d_sys_region` VALUES ('645', '69', '康乐县', '3');
INSERT INTO `d_sys_region` VALUES ('646', '69', '永靖县', '3');
INSERT INTO `d_sys_region` VALUES ('647', '69', '广河县', '3');
INSERT INTO `d_sys_region` VALUES ('648', '69', '和政县', '3');
INSERT INTO `d_sys_region` VALUES ('649', '69', '东乡族自治县', '3');
INSERT INTO `d_sys_region` VALUES ('650', '69', '积石山', '3');
INSERT INTO `d_sys_region` VALUES ('651', '70', '成县', '3');
INSERT INTO `d_sys_region` VALUES ('652', '70', '徽县', '3');
INSERT INTO `d_sys_region` VALUES ('653', '70', '康县', '3');
INSERT INTO `d_sys_region` VALUES ('654', '70', '礼县', '3');
INSERT INTO `d_sys_region` VALUES ('655', '70', '两当县', '3');
INSERT INTO `d_sys_region` VALUES ('656', '70', '文县', '3');
INSERT INTO `d_sys_region` VALUES ('657', '70', '西和县', '3');
INSERT INTO `d_sys_region` VALUES ('658', '70', '宕昌县', '3');
INSERT INTO `d_sys_region` VALUES ('659', '70', '武都区', '3');
INSERT INTO `d_sys_region` VALUES ('660', '71', '崇信县', '3');
INSERT INTO `d_sys_region` VALUES ('661', '71', '华亭县', '3');
INSERT INTO `d_sys_region` VALUES ('662', '71', '静宁县', '3');
INSERT INTO `d_sys_region` VALUES ('663', '71', '灵台县', '3');
INSERT INTO `d_sys_region` VALUES ('664', '71', '崆峒区', '3');
INSERT INTO `d_sys_region` VALUES ('665', '71', '庄浪县', '3');
INSERT INTO `d_sys_region` VALUES ('666', '71', '泾川县', '3');
INSERT INTO `d_sys_region` VALUES ('667', '72', '合水县', '3');
INSERT INTO `d_sys_region` VALUES ('668', '72', '华池县', '3');
INSERT INTO `d_sys_region` VALUES ('669', '72', '环县', '3');
INSERT INTO `d_sys_region` VALUES ('670', '72', '宁县', '3');
INSERT INTO `d_sys_region` VALUES ('671', '72', '庆城县', '3');
INSERT INTO `d_sys_region` VALUES ('672', '72', '西峰区', '3');
INSERT INTO `d_sys_region` VALUES ('673', '72', '镇原县', '3');
INSERT INTO `d_sys_region` VALUES ('674', '72', '正宁县', '3');
INSERT INTO `d_sys_region` VALUES ('675', '73', '甘谷县', '3');
INSERT INTO `d_sys_region` VALUES ('676', '73', '秦安县', '3');
INSERT INTO `d_sys_region` VALUES ('677', '73', '清水县', '3');
INSERT INTO `d_sys_region` VALUES ('678', '73', '秦州区', '3');
INSERT INTO `d_sys_region` VALUES ('679', '73', '麦积区', '3');
INSERT INTO `d_sys_region` VALUES ('680', '73', '武山县', '3');
INSERT INTO `d_sys_region` VALUES ('681', '73', '张家川', '3');
INSERT INTO `d_sys_region` VALUES ('682', '74', '古浪县', '3');
INSERT INTO `d_sys_region` VALUES ('683', '74', '民勤县', '3');
INSERT INTO `d_sys_region` VALUES ('684', '74', '天祝', '3');
INSERT INTO `d_sys_region` VALUES ('685', '74', '凉州区', '3');
INSERT INTO `d_sys_region` VALUES ('686', '75', '高台县', '3');
INSERT INTO `d_sys_region` VALUES ('687', '75', '临泽县', '3');
INSERT INTO `d_sys_region` VALUES ('688', '75', '民乐县', '3');
INSERT INTO `d_sys_region` VALUES ('689', '75', '山丹县', '3');
INSERT INTO `d_sys_region` VALUES ('690', '75', '肃南', '3');
INSERT INTO `d_sys_region` VALUES ('691', '75', '甘州区', '3');
INSERT INTO `d_sys_region` VALUES ('692', '76', '从化市', '3');
INSERT INTO `d_sys_region` VALUES ('693', '76', '天河区', '3');
INSERT INTO `d_sys_region` VALUES ('694', '76', '东山区', '3');
INSERT INTO `d_sys_region` VALUES ('695', '76', '白云区', '3');
INSERT INTO `d_sys_region` VALUES ('696', '76', '海珠区', '3');
INSERT INTO `d_sys_region` VALUES ('697', '76', '荔湾区', '3');
INSERT INTO `d_sys_region` VALUES ('698', '76', '越秀区', '3');
INSERT INTO `d_sys_region` VALUES ('699', '76', '黄埔区', '3');
INSERT INTO `d_sys_region` VALUES ('700', '76', '番禺区', '3');
INSERT INTO `d_sys_region` VALUES ('701', '76', '花都区', '3');
INSERT INTO `d_sys_region` VALUES ('702', '76', '增城区', '3');
INSERT INTO `d_sys_region` VALUES ('703', '76', '从化区', '3');
INSERT INTO `d_sys_region` VALUES ('704', '76', '市郊', '3');
INSERT INTO `d_sys_region` VALUES ('705', '77', '福田区', '3');
INSERT INTO `d_sys_region` VALUES ('706', '77', '罗湖区', '3');
INSERT INTO `d_sys_region` VALUES ('707', '77', '南山区', '3');
INSERT INTO `d_sys_region` VALUES ('708', '77', '宝安区', '3');
INSERT INTO `d_sys_region` VALUES ('709', '77', '龙岗区', '3');
INSERT INTO `d_sys_region` VALUES ('710', '77', '盐田区', '3');
INSERT INTO `d_sys_region` VALUES ('711', '78', '湘桥区', '3');
INSERT INTO `d_sys_region` VALUES ('712', '78', '潮安县', '3');
INSERT INTO `d_sys_region` VALUES ('713', '78', '饶平县', '3');
INSERT INTO `d_sys_region` VALUES ('714', '79', '南城区', '3');
INSERT INTO `d_sys_region` VALUES ('715', '79', '东城区', '3');
INSERT INTO `d_sys_region` VALUES ('716', '79', '万江区', '3');
INSERT INTO `d_sys_region` VALUES ('717', '79', '莞城区', '3');
INSERT INTO `d_sys_region` VALUES ('718', '79', '石龙镇', '3');
INSERT INTO `d_sys_region` VALUES ('719', '79', '虎门镇', '3');
INSERT INTO `d_sys_region` VALUES ('720', '79', '麻涌镇', '3');
INSERT INTO `d_sys_region` VALUES ('721', '79', '道滘镇', '3');
INSERT INTO `d_sys_region` VALUES ('722', '79', '石碣镇', '3');
INSERT INTO `d_sys_region` VALUES ('723', '79', '沙田镇', '3');
INSERT INTO `d_sys_region` VALUES ('724', '79', '望牛墩镇', '3');
INSERT INTO `d_sys_region` VALUES ('725', '79', '洪梅镇', '3');
INSERT INTO `d_sys_region` VALUES ('726', '79', '茶山镇', '3');
INSERT INTO `d_sys_region` VALUES ('727', '79', '寮步镇', '3');
INSERT INTO `d_sys_region` VALUES ('728', '79', '大岭山镇', '3');
INSERT INTO `d_sys_region` VALUES ('729', '79', '大朗镇', '3');
INSERT INTO `d_sys_region` VALUES ('730', '79', '黄江镇', '3');
INSERT INTO `d_sys_region` VALUES ('731', '79', '樟木头', '3');
INSERT INTO `d_sys_region` VALUES ('732', '79', '凤岗镇', '3');
INSERT INTO `d_sys_region` VALUES ('733', '79', '塘厦镇', '3');
INSERT INTO `d_sys_region` VALUES ('734', '79', '谢岗镇', '3');
INSERT INTO `d_sys_region` VALUES ('735', '79', '厚街镇', '3');
INSERT INTO `d_sys_region` VALUES ('736', '79', '清溪镇', '3');
INSERT INTO `d_sys_region` VALUES ('737', '79', '常平镇', '3');
INSERT INTO `d_sys_region` VALUES ('738', '79', '桥头镇', '3');
INSERT INTO `d_sys_region` VALUES ('739', '79', '横沥镇', '3');
INSERT INTO `d_sys_region` VALUES ('740', '79', '东坑镇', '3');
INSERT INTO `d_sys_region` VALUES ('741', '79', '企石镇', '3');
INSERT INTO `d_sys_region` VALUES ('742', '79', '石排镇', '3');
INSERT INTO `d_sys_region` VALUES ('743', '79', '长安镇', '3');
INSERT INTO `d_sys_region` VALUES ('744', '79', '中堂镇', '3');
INSERT INTO `d_sys_region` VALUES ('745', '79', '高埗镇', '3');
INSERT INTO `d_sys_region` VALUES ('746', '80', '禅城区', '3');
INSERT INTO `d_sys_region` VALUES ('747', '80', '南海区', '3');
INSERT INTO `d_sys_region` VALUES ('748', '80', '顺德区', '3');
INSERT INTO `d_sys_region` VALUES ('749', '80', '三水区', '3');
INSERT INTO `d_sys_region` VALUES ('750', '80', '高明区', '3');
INSERT INTO `d_sys_region` VALUES ('751', '81', '东源县', '3');
INSERT INTO `d_sys_region` VALUES ('752', '81', '和平县', '3');
INSERT INTO `d_sys_region` VALUES ('753', '81', '源城区', '3');
INSERT INTO `d_sys_region` VALUES ('754', '81', '连平县', '3');
INSERT INTO `d_sys_region` VALUES ('755', '81', '龙川县', '3');
INSERT INTO `d_sys_region` VALUES ('756', '81', '紫金县', '3');
INSERT INTO `d_sys_region` VALUES ('757', '82', '惠阳区', '3');
INSERT INTO `d_sys_region` VALUES ('758', '82', '惠城区', '3');
INSERT INTO `d_sys_region` VALUES ('759', '82', '大亚湾', '3');
INSERT INTO `d_sys_region` VALUES ('760', '82', '博罗县', '3');
INSERT INTO `d_sys_region` VALUES ('761', '82', '惠东县', '3');
INSERT INTO `d_sys_region` VALUES ('762', '82', '龙门县', '3');
INSERT INTO `d_sys_region` VALUES ('763', '83', '江海区', '3');
INSERT INTO `d_sys_region` VALUES ('764', '83', '蓬江区', '3');
INSERT INTO `d_sys_region` VALUES ('765', '83', '新会区', '3');
INSERT INTO `d_sys_region` VALUES ('766', '83', '台山市', '3');
INSERT INTO `d_sys_region` VALUES ('767', '83', '开平市', '3');
INSERT INTO `d_sys_region` VALUES ('768', '83', '鹤山市', '3');
INSERT INTO `d_sys_region` VALUES ('769', '83', '恩平市', '3');
INSERT INTO `d_sys_region` VALUES ('770', '84', '榕城区', '3');
INSERT INTO `d_sys_region` VALUES ('771', '84', '普宁市', '3');
INSERT INTO `d_sys_region` VALUES ('772', '84', '揭东县', '3');
INSERT INTO `d_sys_region` VALUES ('773', '84', '揭西县', '3');
INSERT INTO `d_sys_region` VALUES ('774', '84', '惠来县', '3');
INSERT INTO `d_sys_region` VALUES ('775', '85', '茂南区', '3');
INSERT INTO `d_sys_region` VALUES ('776', '85', '茂港区', '3');
INSERT INTO `d_sys_region` VALUES ('777', '85', '高州市', '3');
INSERT INTO `d_sys_region` VALUES ('778', '85', '化州市', '3');
INSERT INTO `d_sys_region` VALUES ('779', '85', '信宜市', '3');
INSERT INTO `d_sys_region` VALUES ('780', '85', '电白县', '3');
INSERT INTO `d_sys_region` VALUES ('781', '86', '梅县', '3');
INSERT INTO `d_sys_region` VALUES ('782', '86', '梅江区', '3');
INSERT INTO `d_sys_region` VALUES ('783', '86', '兴宁市', '3');
INSERT INTO `d_sys_region` VALUES ('784', '86', '大埔县', '3');
INSERT INTO `d_sys_region` VALUES ('785', '86', '丰顺县', '3');
INSERT INTO `d_sys_region` VALUES ('786', '86', '五华县', '3');
INSERT INTO `d_sys_region` VALUES ('787', '86', '平远县', '3');
INSERT INTO `d_sys_region` VALUES ('788', '86', '蕉岭县', '3');
INSERT INTO `d_sys_region` VALUES ('789', '87', '清城区', '3');
INSERT INTO `d_sys_region` VALUES ('790', '87', '英德市', '3');
INSERT INTO `d_sys_region` VALUES ('791', '87', '连州市', '3');
INSERT INTO `d_sys_region` VALUES ('792', '87', '佛冈县', '3');
INSERT INTO `d_sys_region` VALUES ('793', '87', '阳山县', '3');
INSERT INTO `d_sys_region` VALUES ('794', '87', '清新县', '3');
INSERT INTO `d_sys_region` VALUES ('795', '87', '连山', '3');
INSERT INTO `d_sys_region` VALUES ('796', '87', '连南', '3');
INSERT INTO `d_sys_region` VALUES ('797', '88', '南澳县', '3');
INSERT INTO `d_sys_region` VALUES ('798', '88', '潮阳区', '3');
INSERT INTO `d_sys_region` VALUES ('799', '88', '澄海区', '3');
INSERT INTO `d_sys_region` VALUES ('800', '88', '龙湖区', '3');
INSERT INTO `d_sys_region` VALUES ('801', '88', '金平区', '3');
INSERT INTO `d_sys_region` VALUES ('802', '88', '濠江区', '3');
INSERT INTO `d_sys_region` VALUES ('803', '88', '潮南区', '3');
INSERT INTO `d_sys_region` VALUES ('804', '89', '城区', '3');
INSERT INTO `d_sys_region` VALUES ('805', '89', '陆丰市', '3');
INSERT INTO `d_sys_region` VALUES ('806', '89', '海丰县', '3');
INSERT INTO `d_sys_region` VALUES ('807', '89', '陆河县', '3');
INSERT INTO `d_sys_region` VALUES ('808', '90', '曲江县', '3');
INSERT INTO `d_sys_region` VALUES ('809', '90', '浈江区', '3');
INSERT INTO `d_sys_region` VALUES ('810', '90', '武江区', '3');
INSERT INTO `d_sys_region` VALUES ('811', '90', '曲江区', '3');
INSERT INTO `d_sys_region` VALUES ('812', '90', '乐昌市', '3');
INSERT INTO `d_sys_region` VALUES ('813', '90', '南雄市', '3');
INSERT INTO `d_sys_region` VALUES ('814', '90', '始兴县', '3');
INSERT INTO `d_sys_region` VALUES ('815', '90', '仁化县', '3');
INSERT INTO `d_sys_region` VALUES ('816', '90', '翁源县', '3');
INSERT INTO `d_sys_region` VALUES ('817', '90', '新丰县', '3');
INSERT INTO `d_sys_region` VALUES ('818', '90', '乳源', '3');
INSERT INTO `d_sys_region` VALUES ('819', '91', '江城区', '3');
INSERT INTO `d_sys_region` VALUES ('820', '91', '阳春市', '3');
INSERT INTO `d_sys_region` VALUES ('821', '91', '阳西县', '3');
INSERT INTO `d_sys_region` VALUES ('822', '91', '阳东县', '3');
INSERT INTO `d_sys_region` VALUES ('823', '92', '云城区', '3');
INSERT INTO `d_sys_region` VALUES ('824', '92', '罗定市', '3');
INSERT INTO `d_sys_region` VALUES ('825', '92', '新兴县', '3');
INSERT INTO `d_sys_region` VALUES ('826', '92', '郁南县', '3');
INSERT INTO `d_sys_region` VALUES ('827', '92', '云安县', '3');
INSERT INTO `d_sys_region` VALUES ('828', '93', '赤坎区', '3');
INSERT INTO `d_sys_region` VALUES ('829', '93', '霞山区', '3');
INSERT INTO `d_sys_region` VALUES ('830', '93', '坡头区', '3');
INSERT INTO `d_sys_region` VALUES ('831', '93', '麻章区', '3');
INSERT INTO `d_sys_region` VALUES ('832', '93', '廉江市', '3');
INSERT INTO `d_sys_region` VALUES ('833', '93', '雷州市', '3');
INSERT INTO `d_sys_region` VALUES ('834', '93', '吴川市', '3');
INSERT INTO `d_sys_region` VALUES ('835', '93', '遂溪县', '3');
INSERT INTO `d_sys_region` VALUES ('836', '93', '徐闻县', '3');
INSERT INTO `d_sys_region` VALUES ('837', '94', '肇庆市', '3');
INSERT INTO `d_sys_region` VALUES ('838', '94', '高要市', '3');
INSERT INTO `d_sys_region` VALUES ('839', '94', '四会市', '3');
INSERT INTO `d_sys_region` VALUES ('840', '94', '广宁县', '3');
INSERT INTO `d_sys_region` VALUES ('841', '94', '怀集县', '3');
INSERT INTO `d_sys_region` VALUES ('842', '94', '封开县', '3');
INSERT INTO `d_sys_region` VALUES ('843', '94', '德庆县', '3');
INSERT INTO `d_sys_region` VALUES ('844', '95', '石岐街道', '3');
INSERT INTO `d_sys_region` VALUES ('845', '95', '东区街道', '3');
INSERT INTO `d_sys_region` VALUES ('846', '95', '西区街道', '3');
INSERT INTO `d_sys_region` VALUES ('847', '95', '环城街道', '3');
INSERT INTO `d_sys_region` VALUES ('848', '95', '中山港街道', '3');
INSERT INTO `d_sys_region` VALUES ('849', '95', '五桂山街道', '3');
INSERT INTO `d_sys_region` VALUES ('850', '96', '香洲区', '3');
INSERT INTO `d_sys_region` VALUES ('851', '96', '斗门区', '3');
INSERT INTO `d_sys_region` VALUES ('852', '96', '金湾区', '3');
INSERT INTO `d_sys_region` VALUES ('853', '97', '邕宁区', '3');
INSERT INTO `d_sys_region` VALUES ('854', '97', '青秀区', '3');
INSERT INTO `d_sys_region` VALUES ('855', '97', '兴宁区', '3');
INSERT INTO `d_sys_region` VALUES ('856', '97', '良庆区', '3');
INSERT INTO `d_sys_region` VALUES ('857', '97', '西乡塘区', '3');
INSERT INTO `d_sys_region` VALUES ('858', '97', '江南区', '3');
INSERT INTO `d_sys_region` VALUES ('859', '97', '武鸣县', '3');
INSERT INTO `d_sys_region` VALUES ('860', '97', '隆安县', '3');
INSERT INTO `d_sys_region` VALUES ('861', '97', '马山县', '3');
INSERT INTO `d_sys_region` VALUES ('862', '97', '上林县', '3');
INSERT INTO `d_sys_region` VALUES ('863', '97', '宾阳县', '3');
INSERT INTO `d_sys_region` VALUES ('864', '97', '横县', '3');
INSERT INTO `d_sys_region` VALUES ('865', '98', '秀峰区', '3');
INSERT INTO `d_sys_region` VALUES ('866', '98', '叠彩区', '3');
INSERT INTO `d_sys_region` VALUES ('867', '98', '象山区', '3');
INSERT INTO `d_sys_region` VALUES ('868', '98', '七星区', '3');
INSERT INTO `d_sys_region` VALUES ('869', '98', '雁山区', '3');
INSERT INTO `d_sys_region` VALUES ('870', '98', '阳朔县', '3');
INSERT INTO `d_sys_region` VALUES ('871', '98', '临桂县', '3');
INSERT INTO `d_sys_region` VALUES ('872', '98', '灵川县', '3');
INSERT INTO `d_sys_region` VALUES ('873', '98', '全州县', '3');
INSERT INTO `d_sys_region` VALUES ('874', '98', '平乐县', '3');
INSERT INTO `d_sys_region` VALUES ('875', '98', '兴安县', '3');
INSERT INTO `d_sys_region` VALUES ('876', '98', '灌阳县', '3');
INSERT INTO `d_sys_region` VALUES ('877', '98', '荔浦县', '3');
INSERT INTO `d_sys_region` VALUES ('878', '98', '资源县', '3');
INSERT INTO `d_sys_region` VALUES ('879', '98', '永福县', '3');
INSERT INTO `d_sys_region` VALUES ('880', '98', '龙胜', '3');
INSERT INTO `d_sys_region` VALUES ('881', '98', '恭城', '3');
INSERT INTO `d_sys_region` VALUES ('882', '99', '右江区', '3');
INSERT INTO `d_sys_region` VALUES ('883', '99', '凌云县', '3');
INSERT INTO `d_sys_region` VALUES ('884', '99', '平果县', '3');
INSERT INTO `d_sys_region` VALUES ('885', '99', '西林县', '3');
INSERT INTO `d_sys_region` VALUES ('886', '99', '乐业县', '3');
INSERT INTO `d_sys_region` VALUES ('887', '99', '德保县', '3');
INSERT INTO `d_sys_region` VALUES ('888', '99', '田林县', '3');
INSERT INTO `d_sys_region` VALUES ('889', '99', '田阳县', '3');
INSERT INTO `d_sys_region` VALUES ('890', '99', '靖西县', '3');
INSERT INTO `d_sys_region` VALUES ('891', '99', '田东县', '3');
INSERT INTO `d_sys_region` VALUES ('892', '99', '那坡县', '3');
INSERT INTO `d_sys_region` VALUES ('893', '99', '隆林', '3');
INSERT INTO `d_sys_region` VALUES ('894', '100', '海城区', '3');
INSERT INTO `d_sys_region` VALUES ('895', '100', '银海区', '3');
INSERT INTO `d_sys_region` VALUES ('896', '100', '铁山港区', '3');
INSERT INTO `d_sys_region` VALUES ('897', '100', '合浦县', '3');
INSERT INTO `d_sys_region` VALUES ('898', '101', '江州区', '3');
INSERT INTO `d_sys_region` VALUES ('899', '101', '凭祥市', '3');
INSERT INTO `d_sys_region` VALUES ('900', '101', '宁明县', '3');
INSERT INTO `d_sys_region` VALUES ('901', '101', '扶绥县', '3');
INSERT INTO `d_sys_region` VALUES ('902', '101', '龙州县', '3');
INSERT INTO `d_sys_region` VALUES ('903', '101', '大新县', '3');
INSERT INTO `d_sys_region` VALUES ('904', '101', '天等县', '3');
INSERT INTO `d_sys_region` VALUES ('905', '102', '港口区', '3');
INSERT INTO `d_sys_region` VALUES ('906', '102', '防城区', '3');
INSERT INTO `d_sys_region` VALUES ('907', '102', '东兴市', '3');
INSERT INTO `d_sys_region` VALUES ('908', '102', '上思县', '3');
INSERT INTO `d_sys_region` VALUES ('909', '103', '港北区', '3');
INSERT INTO `d_sys_region` VALUES ('910', '103', '港南区', '3');
INSERT INTO `d_sys_region` VALUES ('911', '103', '覃塘区', '3');
INSERT INTO `d_sys_region` VALUES ('912', '103', '桂平市', '3');
INSERT INTO `d_sys_region` VALUES ('913', '103', '平南县', '3');
INSERT INTO `d_sys_region` VALUES ('914', '104', '金城江区', '3');
INSERT INTO `d_sys_region` VALUES ('915', '104', '宜州市', '3');
INSERT INTO `d_sys_region` VALUES ('916', '104', '天峨县', '3');
INSERT INTO `d_sys_region` VALUES ('917', '104', '凤山县', '3');
INSERT INTO `d_sys_region` VALUES ('918', '104', '南丹县', '3');
INSERT INTO `d_sys_region` VALUES ('919', '104', '东兰县', '3');
INSERT INTO `d_sys_region` VALUES ('920', '104', '都安', '3');
INSERT INTO `d_sys_region` VALUES ('921', '104', '罗城', '3');
INSERT INTO `d_sys_region` VALUES ('922', '104', '巴马', '3');
INSERT INTO `d_sys_region` VALUES ('923', '104', '环江', '3');
INSERT INTO `d_sys_region` VALUES ('924', '104', '大化', '3');
INSERT INTO `d_sys_region` VALUES ('925', '105', '八步区', '3');
INSERT INTO `d_sys_region` VALUES ('926', '105', '钟山县', '3');
INSERT INTO `d_sys_region` VALUES ('927', '105', '昭平县', '3');
INSERT INTO `d_sys_region` VALUES ('928', '105', '富川', '3');
INSERT INTO `d_sys_region` VALUES ('929', '106', '兴宾区', '3');
INSERT INTO `d_sys_region` VALUES ('930', '106', '合山市', '3');
INSERT INTO `d_sys_region` VALUES ('931', '106', '象州县', '3');
INSERT INTO `d_sys_region` VALUES ('932', '106', '武宣县', '3');
INSERT INTO `d_sys_region` VALUES ('933', '106', '忻城县', '3');
INSERT INTO `d_sys_region` VALUES ('934', '106', '金秀', '3');
INSERT INTO `d_sys_region` VALUES ('935', '107', '城中区', '3');
INSERT INTO `d_sys_region` VALUES ('936', '107', '鱼峰区', '3');
INSERT INTO `d_sys_region` VALUES ('937', '107', '柳北区', '3');
INSERT INTO `d_sys_region` VALUES ('938', '107', '柳南区', '3');
INSERT INTO `d_sys_region` VALUES ('939', '107', '柳江县', '3');
INSERT INTO `d_sys_region` VALUES ('940', '107', '柳城县', '3');
INSERT INTO `d_sys_region` VALUES ('941', '107', '鹿寨县', '3');
INSERT INTO `d_sys_region` VALUES ('942', '107', '融安县', '3');
INSERT INTO `d_sys_region` VALUES ('943', '107', '融水', '3');
INSERT INTO `d_sys_region` VALUES ('944', '107', '三江', '3');
INSERT INTO `d_sys_region` VALUES ('945', '108', '钦南区', '3');
INSERT INTO `d_sys_region` VALUES ('946', '108', '钦北区', '3');
INSERT INTO `d_sys_region` VALUES ('947', '108', '灵山县', '3');
INSERT INTO `d_sys_region` VALUES ('948', '108', '浦北县', '3');
INSERT INTO `d_sys_region` VALUES ('949', '109', '万秀区', '3');
INSERT INTO `d_sys_region` VALUES ('950', '109', '蝶山区', '3');
INSERT INTO `d_sys_region` VALUES ('951', '109', '长洲区', '3');
INSERT INTO `d_sys_region` VALUES ('952', '109', '岑溪市', '3');
INSERT INTO `d_sys_region` VALUES ('953', '109', '苍梧县', '3');
INSERT INTO `d_sys_region` VALUES ('954', '109', '藤县', '3');
INSERT INTO `d_sys_region` VALUES ('955', '109', '蒙山县', '3');
INSERT INTO `d_sys_region` VALUES ('956', '110', '玉州区', '3');
INSERT INTO `d_sys_region` VALUES ('957', '110', '北流市', '3');
INSERT INTO `d_sys_region` VALUES ('958', '110', '容县', '3');
INSERT INTO `d_sys_region` VALUES ('959', '110', '陆川县', '3');
INSERT INTO `d_sys_region` VALUES ('960', '110', '博白县', '3');
INSERT INTO `d_sys_region` VALUES ('961', '110', '兴业县', '3');
INSERT INTO `d_sys_region` VALUES ('962', '111', '南明区', '3');
INSERT INTO `d_sys_region` VALUES ('963', '111', '云岩区', '3');
INSERT INTO `d_sys_region` VALUES ('964', '111', '花溪区', '3');
INSERT INTO `d_sys_region` VALUES ('965', '111', '乌当区', '3');
INSERT INTO `d_sys_region` VALUES ('966', '111', '白云区', '3');
INSERT INTO `d_sys_region` VALUES ('967', '111', '小河区', '3');
INSERT INTO `d_sys_region` VALUES ('968', '111', '金阳新区', '3');
INSERT INTO `d_sys_region` VALUES ('969', '111', '新天园区', '3');
INSERT INTO `d_sys_region` VALUES ('970', '111', '清镇市', '3');
INSERT INTO `d_sys_region` VALUES ('971', '111', '开阳县', '3');
INSERT INTO `d_sys_region` VALUES ('972', '111', '修文县', '3');
INSERT INTO `d_sys_region` VALUES ('973', '111', '息烽县', '3');
INSERT INTO `d_sys_region` VALUES ('974', '112', '西秀区', '3');
INSERT INTO `d_sys_region` VALUES ('975', '112', '关岭', '3');
INSERT INTO `d_sys_region` VALUES ('976', '112', '镇宁', '3');
INSERT INTO `d_sys_region` VALUES ('977', '112', '紫云', '3');
INSERT INTO `d_sys_region` VALUES ('978', '112', '平坝县', '3');
INSERT INTO `d_sys_region` VALUES ('979', '112', '普定县', '3');
INSERT INTO `d_sys_region` VALUES ('980', '113', '毕节市', '3');
INSERT INTO `d_sys_region` VALUES ('981', '113', '大方县', '3');
INSERT INTO `d_sys_region` VALUES ('982', '113', '黔西县', '3');
INSERT INTO `d_sys_region` VALUES ('983', '113', '金沙县', '3');
INSERT INTO `d_sys_region` VALUES ('984', '113', '织金县', '3');
INSERT INTO `d_sys_region` VALUES ('985', '113', '纳雍县', '3');
INSERT INTO `d_sys_region` VALUES ('986', '113', '赫章县', '3');
INSERT INTO `d_sys_region` VALUES ('987', '113', '威宁', '3');
INSERT INTO `d_sys_region` VALUES ('988', '114', '钟山区', '3');
INSERT INTO `d_sys_region` VALUES ('989', '114', '六枝特区', '3');
INSERT INTO `d_sys_region` VALUES ('990', '114', '水城县', '3');
INSERT INTO `d_sys_region` VALUES ('991', '114', '盘县', '3');
INSERT INTO `d_sys_region` VALUES ('992', '115', '凯里市', '3');
INSERT INTO `d_sys_region` VALUES ('993', '115', '黄平县', '3');
INSERT INTO `d_sys_region` VALUES ('994', '115', '施秉县', '3');
INSERT INTO `d_sys_region` VALUES ('995', '115', '三穗县', '3');
INSERT INTO `d_sys_region` VALUES ('996', '115', '镇远县', '3');
INSERT INTO `d_sys_region` VALUES ('997', '115', '岑巩县', '3');
INSERT INTO `d_sys_region` VALUES ('998', '115', '天柱县', '3');
INSERT INTO `d_sys_region` VALUES ('999', '115', '锦屏县', '3');
INSERT INTO `d_sys_region` VALUES ('1000', '115', '剑河县', '3');
INSERT INTO `d_sys_region` VALUES ('1001', '115', '台江县', '3');
INSERT INTO `d_sys_region` VALUES ('1002', '115', '黎平县', '3');
INSERT INTO `d_sys_region` VALUES ('1003', '115', '榕江县', '3');
INSERT INTO `d_sys_region` VALUES ('1004', '115', '从江县', '3');
INSERT INTO `d_sys_region` VALUES ('1005', '115', '雷山县', '3');
INSERT INTO `d_sys_region` VALUES ('1006', '115', '麻江县', '3');
INSERT INTO `d_sys_region` VALUES ('1007', '115', '丹寨县', '3');
INSERT INTO `d_sys_region` VALUES ('1008', '116', '都匀市', '3');
INSERT INTO `d_sys_region` VALUES ('1009', '116', '福泉市', '3');
INSERT INTO `d_sys_region` VALUES ('1010', '116', '荔波县', '3');
INSERT INTO `d_sys_region` VALUES ('1011', '116', '贵定县', '3');
INSERT INTO `d_sys_region` VALUES ('1012', '116', '瓮安县', '3');
INSERT INTO `d_sys_region` VALUES ('1013', '116', '独山县', '3');
INSERT INTO `d_sys_region` VALUES ('1014', '116', '平塘县', '3');
INSERT INTO `d_sys_region` VALUES ('1015', '116', '罗甸县', '3');
INSERT INTO `d_sys_region` VALUES ('1016', '116', '长顺县', '3');
INSERT INTO `d_sys_region` VALUES ('1017', '116', '龙里县', '3');
INSERT INTO `d_sys_region` VALUES ('1018', '116', '惠水县', '3');
INSERT INTO `d_sys_region` VALUES ('1019', '116', '三都', '3');
INSERT INTO `d_sys_region` VALUES ('1020', '117', '兴义市', '3');
INSERT INTO `d_sys_region` VALUES ('1021', '117', '兴仁县', '3');
INSERT INTO `d_sys_region` VALUES ('1022', '117', '普安县', '3');
INSERT INTO `d_sys_region` VALUES ('1023', '117', '晴隆县', '3');
INSERT INTO `d_sys_region` VALUES ('1024', '117', '贞丰县', '3');
INSERT INTO `d_sys_region` VALUES ('1025', '117', '望谟县', '3');
INSERT INTO `d_sys_region` VALUES ('1026', '117', '册亨县', '3');
INSERT INTO `d_sys_region` VALUES ('1027', '117', '安龙县', '3');
INSERT INTO `d_sys_region` VALUES ('1028', '118', '铜仁市', '3');
INSERT INTO `d_sys_region` VALUES ('1029', '118', '江口县', '3');
INSERT INTO `d_sys_region` VALUES ('1030', '118', '石阡县', '3');
INSERT INTO `d_sys_region` VALUES ('1031', '118', '思南县', '3');
INSERT INTO `d_sys_region` VALUES ('1032', '118', '德江县', '3');
INSERT INTO `d_sys_region` VALUES ('1033', '118', '玉屏', '3');
INSERT INTO `d_sys_region` VALUES ('1034', '118', '印江', '3');
INSERT INTO `d_sys_region` VALUES ('1035', '118', '沿河', '3');
INSERT INTO `d_sys_region` VALUES ('1036', '118', '松桃', '3');
INSERT INTO `d_sys_region` VALUES ('1037', '118', '万山特区', '3');
INSERT INTO `d_sys_region` VALUES ('1038', '119', '红花岗区', '3');
INSERT INTO `d_sys_region` VALUES ('1039', '119', '务川县', '3');
INSERT INTO `d_sys_region` VALUES ('1040', '119', '道真县', '3');
INSERT INTO `d_sys_region` VALUES ('1041', '119', '汇川区', '3');
INSERT INTO `d_sys_region` VALUES ('1042', '119', '赤水市', '3');
INSERT INTO `d_sys_region` VALUES ('1043', '119', '仁怀市', '3');
INSERT INTO `d_sys_region` VALUES ('1044', '119', '遵义县', '3');
INSERT INTO `d_sys_region` VALUES ('1045', '119', '桐梓县', '3');
INSERT INTO `d_sys_region` VALUES ('1046', '119', '绥阳县', '3');
INSERT INTO `d_sys_region` VALUES ('1047', '119', '正安县', '3');
INSERT INTO `d_sys_region` VALUES ('1048', '119', '凤冈县', '3');
INSERT INTO `d_sys_region` VALUES ('1049', '119', '湄潭县', '3');
INSERT INTO `d_sys_region` VALUES ('1050', '119', '余庆县', '3');
INSERT INTO `d_sys_region` VALUES ('1051', '119', '习水县', '3');
INSERT INTO `d_sys_region` VALUES ('1052', '119', '道真', '3');
INSERT INTO `d_sys_region` VALUES ('1053', '119', '务川', '3');
INSERT INTO `d_sys_region` VALUES ('1054', '120', '秀英区', '3');
INSERT INTO `d_sys_region` VALUES ('1055', '120', '龙华区', '3');
INSERT INTO `d_sys_region` VALUES ('1056', '120', '琼山区', '3');
INSERT INTO `d_sys_region` VALUES ('1057', '120', '美兰区', '3');
INSERT INTO `d_sys_region` VALUES ('1058', '137', '市区', '3');
INSERT INTO `d_sys_region` VALUES ('1059', '137', '洋浦开发区', '3');
INSERT INTO `d_sys_region` VALUES ('1060', '137', '那大镇', '3');
INSERT INTO `d_sys_region` VALUES ('1061', '137', '王五镇', '3');
INSERT INTO `d_sys_region` VALUES ('1062', '137', '雅星镇', '3');
INSERT INTO `d_sys_region` VALUES ('1063', '137', '大成镇', '3');
INSERT INTO `d_sys_region` VALUES ('1064', '137', '中和镇', '3');
INSERT INTO `d_sys_region` VALUES ('1065', '137', '峨蔓镇', '3');
INSERT INTO `d_sys_region` VALUES ('1066', '137', '南丰镇', '3');
INSERT INTO `d_sys_region` VALUES ('1067', '137', '白马井镇', '3');
INSERT INTO `d_sys_region` VALUES ('1068', '137', '兰洋镇', '3');
INSERT INTO `d_sys_region` VALUES ('1069', '137', '和庆镇', '3');
INSERT INTO `d_sys_region` VALUES ('1070', '137', '海头镇', '3');
INSERT INTO `d_sys_region` VALUES ('1071', '137', '排浦镇', '3');
INSERT INTO `d_sys_region` VALUES ('1072', '137', '东成镇', '3');
INSERT INTO `d_sys_region` VALUES ('1073', '137', '光村镇', '3');
INSERT INTO `d_sys_region` VALUES ('1074', '137', '木棠镇', '3');
INSERT INTO `d_sys_region` VALUES ('1075', '137', '新州镇', '3');
INSERT INTO `d_sys_region` VALUES ('1076', '137', '三都镇', '3');
INSERT INTO `d_sys_region` VALUES ('1077', '137', '其他', '3');
INSERT INTO `d_sys_region` VALUES ('1078', '138', '长安区', '3');
INSERT INTO `d_sys_region` VALUES ('1079', '138', '桥东区', '3');
INSERT INTO `d_sys_region` VALUES ('1080', '138', '桥西区', '3');
INSERT INTO `d_sys_region` VALUES ('1081', '138', '新华区', '3');
INSERT INTO `d_sys_region` VALUES ('1082', '138', '裕华区', '3');
INSERT INTO `d_sys_region` VALUES ('1083', '138', '井陉矿区', '3');
INSERT INTO `d_sys_region` VALUES ('1084', '138', '高新区', '3');
INSERT INTO `d_sys_region` VALUES ('1085', '138', '辛集市', '3');
INSERT INTO `d_sys_region` VALUES ('1086', '138', '藁城市', '3');
INSERT INTO `d_sys_region` VALUES ('1087', '138', '晋州市', '3');
INSERT INTO `d_sys_region` VALUES ('1088', '138', '新乐市', '3');
INSERT INTO `d_sys_region` VALUES ('1089', '138', '鹿泉市', '3');
INSERT INTO `d_sys_region` VALUES ('1090', '138', '井陉县', '3');
INSERT INTO `d_sys_region` VALUES ('1091', '138', '正定县', '3');
INSERT INTO `d_sys_region` VALUES ('1092', '138', '栾城县', '3');
INSERT INTO `d_sys_region` VALUES ('1093', '138', '行唐县', '3');
INSERT INTO `d_sys_region` VALUES ('1094', '138', '灵寿县', '3');
INSERT INTO `d_sys_region` VALUES ('1095', '138', '高邑县', '3');
INSERT INTO `d_sys_region` VALUES ('1096', '138', '深泽县', '3');
INSERT INTO `d_sys_region` VALUES ('1097', '138', '赞皇县', '3');
INSERT INTO `d_sys_region` VALUES ('1098', '138', '无极县', '3');
INSERT INTO `d_sys_region` VALUES ('1099', '138', '平山县', '3');
INSERT INTO `d_sys_region` VALUES ('1100', '138', '元氏县', '3');
INSERT INTO `d_sys_region` VALUES ('1101', '138', '赵县', '3');
INSERT INTO `d_sys_region` VALUES ('1102', '139', '新市区', '3');
INSERT INTO `d_sys_region` VALUES ('1103', '139', '南市区', '3');
INSERT INTO `d_sys_region` VALUES ('1104', '139', '北市区', '3');
INSERT INTO `d_sys_region` VALUES ('1105', '139', '涿州市', '3');
INSERT INTO `d_sys_region` VALUES ('1106', '139', '定州市', '3');
INSERT INTO `d_sys_region` VALUES ('1107', '139', '安国市', '3');
INSERT INTO `d_sys_region` VALUES ('1108', '139', '高碑店市', '3');
INSERT INTO `d_sys_region` VALUES ('1109', '139', '满城县', '3');
INSERT INTO `d_sys_region` VALUES ('1110', '139', '清苑县', '3');
INSERT INTO `d_sys_region` VALUES ('1111', '139', '涞水县', '3');
INSERT INTO `d_sys_region` VALUES ('1112', '139', '阜平县', '3');
INSERT INTO `d_sys_region` VALUES ('1113', '139', '徐水县', '3');
INSERT INTO `d_sys_region` VALUES ('1114', '139', '定兴县', '3');
INSERT INTO `d_sys_region` VALUES ('1115', '139', '唐县', '3');
INSERT INTO `d_sys_region` VALUES ('1116', '139', '高阳县', '3');
INSERT INTO `d_sys_region` VALUES ('1117', '139', '容城县', '3');
INSERT INTO `d_sys_region` VALUES ('1118', '139', '涞源县', '3');
INSERT INTO `d_sys_region` VALUES ('1119', '139', '望都县', '3');
INSERT INTO `d_sys_region` VALUES ('1120', '139', '安新县', '3');
INSERT INTO `d_sys_region` VALUES ('1121', '139', '易县', '3');
INSERT INTO `d_sys_region` VALUES ('1122', '139', '曲阳县', '3');
INSERT INTO `d_sys_region` VALUES ('1123', '139', '蠡县', '3');
INSERT INTO `d_sys_region` VALUES ('1124', '139', '顺平县', '3');
INSERT INTO `d_sys_region` VALUES ('1125', '139', '博野县', '3');
INSERT INTO `d_sys_region` VALUES ('1126', '139', '雄县', '3');
INSERT INTO `d_sys_region` VALUES ('1127', '140', '运河区', '3');
INSERT INTO `d_sys_region` VALUES ('1128', '140', '新华区', '3');
INSERT INTO `d_sys_region` VALUES ('1129', '140', '泊头市', '3');
INSERT INTO `d_sys_region` VALUES ('1130', '140', '任丘市', '3');
INSERT INTO `d_sys_region` VALUES ('1131', '140', '黄骅市', '3');
INSERT INTO `d_sys_region` VALUES ('1132', '140', '河间市', '3');
INSERT INTO `d_sys_region` VALUES ('1133', '140', '沧县', '3');
INSERT INTO `d_sys_region` VALUES ('1134', '140', '青县', '3');
INSERT INTO `d_sys_region` VALUES ('1135', '140', '东光县', '3');
INSERT INTO `d_sys_region` VALUES ('1136', '140', '海兴县', '3');
INSERT INTO `d_sys_region` VALUES ('1137', '140', '盐山县', '3');
INSERT INTO `d_sys_region` VALUES ('1138', '140', '肃宁县', '3');
INSERT INTO `d_sys_region` VALUES ('1139', '140', '南皮县', '3');
INSERT INTO `d_sys_region` VALUES ('1140', '140', '吴桥县', '3');
INSERT INTO `d_sys_region` VALUES ('1141', '140', '献县', '3');
INSERT INTO `d_sys_region` VALUES ('1142', '140', '孟村', '3');
INSERT INTO `d_sys_region` VALUES ('1143', '141', '双桥区', '3');
INSERT INTO `d_sys_region` VALUES ('1144', '141', '双滦区', '3');
INSERT INTO `d_sys_region` VALUES ('1145', '141', '鹰手营子矿区', '3');
INSERT INTO `d_sys_region` VALUES ('1146', '141', '承德县', '3');
INSERT INTO `d_sys_region` VALUES ('1147', '141', '兴隆县', '3');
INSERT INTO `d_sys_region` VALUES ('1148', '141', '平泉县', '3');
INSERT INTO `d_sys_region` VALUES ('1149', '141', '滦平县', '3');
INSERT INTO `d_sys_region` VALUES ('1150', '141', '隆化县', '3');
INSERT INTO `d_sys_region` VALUES ('1151', '141', '丰宁', '3');
INSERT INTO `d_sys_region` VALUES ('1152', '141', '宽城', '3');
INSERT INTO `d_sys_region` VALUES ('1153', '141', '围场', '3');
INSERT INTO `d_sys_region` VALUES ('1154', '142', '从台区', '3');
INSERT INTO `d_sys_region` VALUES ('1155', '142', '复兴区', '3');
INSERT INTO `d_sys_region` VALUES ('1156', '142', '邯山区', '3');
INSERT INTO `d_sys_region` VALUES ('1157', '142', '峰峰矿区', '3');
INSERT INTO `d_sys_region` VALUES ('1158', '142', '武安市', '3');
INSERT INTO `d_sys_region` VALUES ('1159', '142', '邯郸县', '3');
INSERT INTO `d_sys_region` VALUES ('1160', '142', '临漳县', '3');
INSERT INTO `d_sys_region` VALUES ('1161', '142', '成安县', '3');
INSERT INTO `d_sys_region` VALUES ('1162', '142', '大名县', '3');
INSERT INTO `d_sys_region` VALUES ('1163', '142', '涉县', '3');
INSERT INTO `d_sys_region` VALUES ('1164', '142', '磁县', '3');
INSERT INTO `d_sys_region` VALUES ('1165', '142', '肥乡县', '3');
INSERT INTO `d_sys_region` VALUES ('1166', '142', '永年县', '3');
INSERT INTO `d_sys_region` VALUES ('1167', '142', '邱县', '3');
INSERT INTO `d_sys_region` VALUES ('1168', '142', '鸡泽县', '3');
INSERT INTO `d_sys_region` VALUES ('1169', '142', '广平县', '3');
INSERT INTO `d_sys_region` VALUES ('1170', '142', '馆陶县', '3');
INSERT INTO `d_sys_region` VALUES ('1171', '142', '魏县', '3');
INSERT INTO `d_sys_region` VALUES ('1172', '142', '曲周县', '3');
INSERT INTO `d_sys_region` VALUES ('1173', '143', '桃城区', '3');
INSERT INTO `d_sys_region` VALUES ('1174', '143', '冀州市', '3');
INSERT INTO `d_sys_region` VALUES ('1175', '143', '深州市', '3');
INSERT INTO `d_sys_region` VALUES ('1176', '143', '枣强县', '3');
INSERT INTO `d_sys_region` VALUES ('1177', '143', '武邑县', '3');
INSERT INTO `d_sys_region` VALUES ('1178', '143', '武强县', '3');
INSERT INTO `d_sys_region` VALUES ('1179', '143', '饶阳县', '3');
INSERT INTO `d_sys_region` VALUES ('1180', '143', '安平县', '3');
INSERT INTO `d_sys_region` VALUES ('1181', '143', '故城县', '3');
INSERT INTO `d_sys_region` VALUES ('1182', '143', '景县', '3');
INSERT INTO `d_sys_region` VALUES ('1183', '143', '阜城县', '3');
INSERT INTO `d_sys_region` VALUES ('1184', '144', '安次区', '3');
INSERT INTO `d_sys_region` VALUES ('1185', '144', '广阳区', '3');
INSERT INTO `d_sys_region` VALUES ('1186', '144', '霸州市', '3');
INSERT INTO `d_sys_region` VALUES ('1187', '144', '三河市', '3');
INSERT INTO `d_sys_region` VALUES ('1188', '144', '固安县', '3');
INSERT INTO `d_sys_region` VALUES ('1189', '144', '永清县', '3');
INSERT INTO `d_sys_region` VALUES ('1190', '144', '香河县', '3');
INSERT INTO `d_sys_region` VALUES ('1191', '144', '大城县', '3');
INSERT INTO `d_sys_region` VALUES ('1192', '144', '文安县', '3');
INSERT INTO `d_sys_region` VALUES ('1193', '144', '大厂', '3');
INSERT INTO `d_sys_region` VALUES ('1194', '145', '海港区', '3');
INSERT INTO `d_sys_region` VALUES ('1195', '145', '山海关区', '3');
INSERT INTO `d_sys_region` VALUES ('1196', '145', '北戴河区', '3');
INSERT INTO `d_sys_region` VALUES ('1197', '145', '昌黎县', '3');
INSERT INTO `d_sys_region` VALUES ('1198', '145', '抚宁县', '3');
INSERT INTO `d_sys_region` VALUES ('1199', '145', '卢龙县', '3');
INSERT INTO `d_sys_region` VALUES ('1200', '145', '青龙', '3');
INSERT INTO `d_sys_region` VALUES ('1201', '146', '路北区', '3');
INSERT INTO `d_sys_region` VALUES ('1202', '146', '路南区', '3');
INSERT INTO `d_sys_region` VALUES ('1203', '146', '古冶区', '3');
INSERT INTO `d_sys_region` VALUES ('1204', '146', '开平区', '3');
INSERT INTO `d_sys_region` VALUES ('1205', '146', '丰南区', '3');
INSERT INTO `d_sys_region` VALUES ('1206', '146', '丰润区', '3');
INSERT INTO `d_sys_region` VALUES ('1207', '146', '遵化市', '3');
INSERT INTO `d_sys_region` VALUES ('1208', '146', '迁安市', '3');
INSERT INTO `d_sys_region` VALUES ('1209', '146', '滦县', '3');
INSERT INTO `d_sys_region` VALUES ('1210', '146', '滦南县', '3');
INSERT INTO `d_sys_region` VALUES ('1211', '146', '乐亭县', '3');
INSERT INTO `d_sys_region` VALUES ('1212', '146', '迁西县', '3');
INSERT INTO `d_sys_region` VALUES ('1213', '146', '玉田县', '3');
INSERT INTO `d_sys_region` VALUES ('1214', '146', '唐海县', '3');
INSERT INTO `d_sys_region` VALUES ('1215', '147', '桥东区', '3');
INSERT INTO `d_sys_region` VALUES ('1216', '147', '桥西区', '3');
INSERT INTO `d_sys_region` VALUES ('1217', '147', '南宫市', '3');
INSERT INTO `d_sys_region` VALUES ('1218', '147', '沙河市', '3');
INSERT INTO `d_sys_region` VALUES ('1219', '147', '邢台县', '3');
INSERT INTO `d_sys_region` VALUES ('1220', '147', '临城县', '3');
INSERT INTO `d_sys_region` VALUES ('1221', '147', '内丘县', '3');
INSERT INTO `d_sys_region` VALUES ('1222', '147', '柏乡县', '3');
INSERT INTO `d_sys_region` VALUES ('1223', '147', '隆尧县', '3');
INSERT INTO `d_sys_region` VALUES ('1224', '147', '任县', '3');
INSERT INTO `d_sys_region` VALUES ('1225', '147', '南和县', '3');
INSERT INTO `d_sys_region` VALUES ('1226', '147', '宁晋县', '3');
INSERT INTO `d_sys_region` VALUES ('1227', '147', '巨鹿县', '3');
INSERT INTO `d_sys_region` VALUES ('1228', '147', '新河县', '3');
INSERT INTO `d_sys_region` VALUES ('1229', '147', '广宗县', '3');
INSERT INTO `d_sys_region` VALUES ('1230', '147', '平乡县', '3');
INSERT INTO `d_sys_region` VALUES ('1231', '147', '威县', '3');
INSERT INTO `d_sys_region` VALUES ('1232', '147', '清河县', '3');
INSERT INTO `d_sys_region` VALUES ('1233', '147', '临西县', '3');
INSERT INTO `d_sys_region` VALUES ('1234', '148', '桥西区', '3');
INSERT INTO `d_sys_region` VALUES ('1235', '148', '桥东区', '3');
INSERT INTO `d_sys_region` VALUES ('1236', '148', '宣化区', '3');
INSERT INTO `d_sys_region` VALUES ('1237', '148', '下花园区', '3');
INSERT INTO `d_sys_region` VALUES ('1238', '148', '宣化县', '3');
INSERT INTO `d_sys_region` VALUES ('1239', '148', '张北县', '3');
INSERT INTO `d_sys_region` VALUES ('1240', '148', '康保县', '3');
INSERT INTO `d_sys_region` VALUES ('1241', '148', '沽源县', '3');
INSERT INTO `d_sys_region` VALUES ('1242', '148', '尚义县', '3');
INSERT INTO `d_sys_region` VALUES ('1243', '148', '蔚县', '3');
INSERT INTO `d_sys_region` VALUES ('1244', '148', '阳原县', '3');
INSERT INTO `d_sys_region` VALUES ('1245', '148', '怀安县', '3');
INSERT INTO `d_sys_region` VALUES ('1246', '148', '万全县', '3');
INSERT INTO `d_sys_region` VALUES ('1247', '148', '怀来县', '3');
INSERT INTO `d_sys_region` VALUES ('1248', '148', '涿鹿县', '3');
INSERT INTO `d_sys_region` VALUES ('1249', '148', '赤城县', '3');
INSERT INTO `d_sys_region` VALUES ('1250', '148', '崇礼县', '3');
INSERT INTO `d_sys_region` VALUES ('1251', '149', '金水区', '3');
INSERT INTO `d_sys_region` VALUES ('1252', '149', '邙山区', '3');
INSERT INTO `d_sys_region` VALUES ('1253', '149', '二七区', '3');
INSERT INTO `d_sys_region` VALUES ('1254', '149', '管城区', '3');
INSERT INTO `d_sys_region` VALUES ('1255', '149', '中原区', '3');
INSERT INTO `d_sys_region` VALUES ('1256', '149', '上街区', '3');
INSERT INTO `d_sys_region` VALUES ('1257', '149', '惠济区', '3');
INSERT INTO `d_sys_region` VALUES ('1258', '149', '郑东新区', '3');
INSERT INTO `d_sys_region` VALUES ('1259', '149', '经济技术开发区', '3');
INSERT INTO `d_sys_region` VALUES ('1260', '149', '高新开发区', '3');
INSERT INTO `d_sys_region` VALUES ('1261', '149', '出口加工区', '3');
INSERT INTO `d_sys_region` VALUES ('1262', '149', '巩义市', '3');
INSERT INTO `d_sys_region` VALUES ('1263', '149', '荥阳市', '3');
INSERT INTO `d_sys_region` VALUES ('1264', '149', '新密市', '3');
INSERT INTO `d_sys_region` VALUES ('1265', '149', '新郑市', '3');
INSERT INTO `d_sys_region` VALUES ('1266', '149', '登封市', '3');
INSERT INTO `d_sys_region` VALUES ('1267', '149', '中牟县', '3');
INSERT INTO `d_sys_region` VALUES ('1268', '150', '西工区', '3');
INSERT INTO `d_sys_region` VALUES ('1269', '150', '老城区', '3');
INSERT INTO `d_sys_region` VALUES ('1270', '150', '涧西区', '3');
INSERT INTO `d_sys_region` VALUES ('1271', '150', '瀍河回族区', '3');
INSERT INTO `d_sys_region` VALUES ('1272', '150', '洛龙区', '3');
INSERT INTO `d_sys_region` VALUES ('1273', '150', '吉利区', '3');
INSERT INTO `d_sys_region` VALUES ('1274', '150', '偃师市', '3');
INSERT INTO `d_sys_region` VALUES ('1275', '150', '孟津县', '3');
INSERT INTO `d_sys_region` VALUES ('1276', '150', '新安县', '3');
INSERT INTO `d_sys_region` VALUES ('1277', '150', '栾川县', '3');
INSERT INTO `d_sys_region` VALUES ('1278', '150', '嵩县', '3');
INSERT INTO `d_sys_region` VALUES ('1279', '150', '汝阳县', '3');
INSERT INTO `d_sys_region` VALUES ('1280', '150', '宜阳县', '3');
INSERT INTO `d_sys_region` VALUES ('1281', '150', '洛宁县', '3');
INSERT INTO `d_sys_region` VALUES ('1282', '150', '伊川县', '3');
INSERT INTO `d_sys_region` VALUES ('1283', '151', '鼓楼区', '3');
INSERT INTO `d_sys_region` VALUES ('1284', '151', '龙亭区', '3');
INSERT INTO `d_sys_region` VALUES ('1285', '151', '顺河回族区', '3');
INSERT INTO `d_sys_region` VALUES ('1286', '151', '金明区', '3');
INSERT INTO `d_sys_region` VALUES ('1287', '151', '禹王台区', '3');
INSERT INTO `d_sys_region` VALUES ('1288', '151', '杞县', '3');
INSERT INTO `d_sys_region` VALUES ('1289', '151', '通许县', '3');
INSERT INTO `d_sys_region` VALUES ('1290', '151', '尉氏县', '3');
INSERT INTO `d_sys_region` VALUES ('1291', '151', '开封县', '3');
INSERT INTO `d_sys_region` VALUES ('1292', '151', '兰考县', '3');
INSERT INTO `d_sys_region` VALUES ('1293', '152', '北关区', '3');
INSERT INTO `d_sys_region` VALUES ('1294', '152', '文峰区', '3');
INSERT INTO `d_sys_region` VALUES ('1295', '152', '殷都区', '3');
INSERT INTO `d_sys_region` VALUES ('1296', '152', '龙安区', '3');
INSERT INTO `d_sys_region` VALUES ('1297', '152', '林州市', '3');
INSERT INTO `d_sys_region` VALUES ('1298', '152', '安阳县', '3');
INSERT INTO `d_sys_region` VALUES ('1299', '152', '汤阴县', '3');
INSERT INTO `d_sys_region` VALUES ('1300', '152', '滑县', '3');
INSERT INTO `d_sys_region` VALUES ('1301', '152', '内黄县', '3');
INSERT INTO `d_sys_region` VALUES ('1302', '153', '淇滨区', '3');
INSERT INTO `d_sys_region` VALUES ('1303', '153', '山城区', '3');
INSERT INTO `d_sys_region` VALUES ('1304', '153', '鹤山区', '3');
INSERT INTO `d_sys_region` VALUES ('1305', '153', '浚县', '3');
INSERT INTO `d_sys_region` VALUES ('1306', '153', '淇县', '3');
INSERT INTO `d_sys_region` VALUES ('1307', '154', '济源市', '3');
INSERT INTO `d_sys_region` VALUES ('1308', '155', '解放区', '3');
INSERT INTO `d_sys_region` VALUES ('1309', '155', '中站区', '3');
INSERT INTO `d_sys_region` VALUES ('1310', '155', '马村区', '3');
INSERT INTO `d_sys_region` VALUES ('1311', '155', '山阳区', '3');
INSERT INTO `d_sys_region` VALUES ('1312', '155', '沁阳市', '3');
INSERT INTO `d_sys_region` VALUES ('1313', '155', '孟州市', '3');
INSERT INTO `d_sys_region` VALUES ('1314', '155', '修武县', '3');
INSERT INTO `d_sys_region` VALUES ('1315', '155', '博爱县', '3');
INSERT INTO `d_sys_region` VALUES ('1316', '155', '武陟县', '3');
INSERT INTO `d_sys_region` VALUES ('1317', '155', '温县', '3');
INSERT INTO `d_sys_region` VALUES ('1318', '156', '卧龙区', '3');
INSERT INTO `d_sys_region` VALUES ('1319', '156', '宛城区', '3');
INSERT INTO `d_sys_region` VALUES ('1320', '156', '邓州市', '3');
INSERT INTO `d_sys_region` VALUES ('1321', '156', '南召县', '3');
INSERT INTO `d_sys_region` VALUES ('1322', '156', '方城县', '3');
INSERT INTO `d_sys_region` VALUES ('1323', '156', '西峡县', '3');
INSERT INTO `d_sys_region` VALUES ('1324', '156', '镇平县', '3');
INSERT INTO `d_sys_region` VALUES ('1325', '156', '内乡县', '3');
INSERT INTO `d_sys_region` VALUES ('1326', '156', '淅川县', '3');
INSERT INTO `d_sys_region` VALUES ('1327', '156', '社旗县', '3');
INSERT INTO `d_sys_region` VALUES ('1328', '156', '唐河县', '3');
INSERT INTO `d_sys_region` VALUES ('1329', '156', '新野县', '3');
INSERT INTO `d_sys_region` VALUES ('1330', '156', '桐柏县', '3');
INSERT INTO `d_sys_region` VALUES ('1331', '157', '新华区', '3');
INSERT INTO `d_sys_region` VALUES ('1332', '157', '卫东区', '3');
INSERT INTO `d_sys_region` VALUES ('1333', '157', '湛河区', '3');
INSERT INTO `d_sys_region` VALUES ('1334', '157', '石龙区', '3');
INSERT INTO `d_sys_region` VALUES ('1335', '157', '舞钢市', '3');
INSERT INTO `d_sys_region` VALUES ('1336', '157', '汝州市', '3');
INSERT INTO `d_sys_region` VALUES ('1337', '157', '宝丰县', '3');
INSERT INTO `d_sys_region` VALUES ('1338', '157', '叶县', '3');
INSERT INTO `d_sys_region` VALUES ('1339', '157', '鲁山县', '3');
INSERT INTO `d_sys_region` VALUES ('1340', '157', '郏县', '3');
INSERT INTO `d_sys_region` VALUES ('1341', '158', '湖滨区', '3');
INSERT INTO `d_sys_region` VALUES ('1342', '158', '义马市', '3');
INSERT INTO `d_sys_region` VALUES ('1343', '158', '灵宝市', '3');
INSERT INTO `d_sys_region` VALUES ('1344', '158', '渑池县', '3');
INSERT INTO `d_sys_region` VALUES ('1345', '158', '陕县', '3');
INSERT INTO `d_sys_region` VALUES ('1346', '158', '卢氏县', '3');
INSERT INTO `d_sys_region` VALUES ('1347', '159', '梁园区', '3');
INSERT INTO `d_sys_region` VALUES ('1348', '159', '睢阳区', '3');
INSERT INTO `d_sys_region` VALUES ('1349', '159', '永城市', '3');
INSERT INTO `d_sys_region` VALUES ('1350', '159', '民权县', '3');
INSERT INTO `d_sys_region` VALUES ('1351', '159', '睢县', '3');
INSERT INTO `d_sys_region` VALUES ('1352', '159', '宁陵县', '3');
INSERT INTO `d_sys_region` VALUES ('1353', '159', '虞城县', '3');
INSERT INTO `d_sys_region` VALUES ('1354', '159', '柘城县', '3');
INSERT INTO `d_sys_region` VALUES ('1355', '159', '夏邑县', '3');
INSERT INTO `d_sys_region` VALUES ('1356', '160', '卫滨区', '3');
INSERT INTO `d_sys_region` VALUES ('1357', '160', '红旗区', '3');
INSERT INTO `d_sys_region` VALUES ('1358', '160', '凤泉区', '3');
INSERT INTO `d_sys_region` VALUES ('1359', '160', '牧野区', '3');
INSERT INTO `d_sys_region` VALUES ('1360', '160', '卫辉市', '3');
INSERT INTO `d_sys_region` VALUES ('1361', '160', '辉县市', '3');
INSERT INTO `d_sys_region` VALUES ('1362', '160', '新乡县', '3');
INSERT INTO `d_sys_region` VALUES ('1363', '160', '获嘉县', '3');
INSERT INTO `d_sys_region` VALUES ('1364', '160', '原阳县', '3');
INSERT INTO `d_sys_region` VALUES ('1365', '160', '延津县', '3');
INSERT INTO `d_sys_region` VALUES ('1366', '160', '封丘县', '3');
INSERT INTO `d_sys_region` VALUES ('1367', '160', '长垣县', '3');
INSERT INTO `d_sys_region` VALUES ('1368', '161', '浉河区', '3');
INSERT INTO `d_sys_region` VALUES ('1369', '161', '平桥区', '3');
INSERT INTO `d_sys_region` VALUES ('1370', '161', '罗山县', '3');
INSERT INTO `d_sys_region` VALUES ('1371', '161', '光山县', '3');
INSERT INTO `d_sys_region` VALUES ('1372', '161', '新县', '3');
INSERT INTO `d_sys_region` VALUES ('1373', '161', '商城县', '3');
INSERT INTO `d_sys_region` VALUES ('1374', '161', '固始县', '3');
INSERT INTO `d_sys_region` VALUES ('1375', '161', '潢川县', '3');
INSERT INTO `d_sys_region` VALUES ('1376', '161', '淮滨县', '3');
INSERT INTO `d_sys_region` VALUES ('1377', '161', '息县', '3');
INSERT INTO `d_sys_region` VALUES ('1378', '162', '魏都区', '3');
INSERT INTO `d_sys_region` VALUES ('1379', '162', '禹州市', '3');
INSERT INTO `d_sys_region` VALUES ('1380', '162', '长葛市', '3');
INSERT INTO `d_sys_region` VALUES ('1381', '162', '许昌县', '3');
INSERT INTO `d_sys_region` VALUES ('1382', '162', '鄢陵县', '3');
INSERT INTO `d_sys_region` VALUES ('1383', '162', '襄城县', '3');
INSERT INTO `d_sys_region` VALUES ('1384', '163', '川汇区', '3');
INSERT INTO `d_sys_region` VALUES ('1385', '163', '项城市', '3');
INSERT INTO `d_sys_region` VALUES ('1386', '163', '扶沟县', '3');
INSERT INTO `d_sys_region` VALUES ('1387', '163', '西华县', '3');
INSERT INTO `d_sys_region` VALUES ('1388', '163', '商水县', '3');
INSERT INTO `d_sys_region` VALUES ('1389', '163', '沈丘县', '3');
INSERT INTO `d_sys_region` VALUES ('1390', '163', '郸城县', '3');
INSERT INTO `d_sys_region` VALUES ('1391', '163', '淮阳县', '3');
INSERT INTO `d_sys_region` VALUES ('1392', '163', '太康县', '3');
INSERT INTO `d_sys_region` VALUES ('1393', '163', '鹿邑县', '3');
INSERT INTO `d_sys_region` VALUES ('1394', '164', '驿城区', '3');
INSERT INTO `d_sys_region` VALUES ('1395', '164', '西平县', '3');
INSERT INTO `d_sys_region` VALUES ('1396', '164', '上蔡县', '3');
INSERT INTO `d_sys_region` VALUES ('1397', '164', '平舆县', '3');
INSERT INTO `d_sys_region` VALUES ('1398', '164', '正阳县', '3');
INSERT INTO `d_sys_region` VALUES ('1399', '164', '确山县', '3');
INSERT INTO `d_sys_region` VALUES ('1400', '164', '泌阳县', '3');
INSERT INTO `d_sys_region` VALUES ('1401', '164', '汝南县', '3');
INSERT INTO `d_sys_region` VALUES ('1402', '164', '遂平县', '3');
INSERT INTO `d_sys_region` VALUES ('1403', '164', '新蔡县', '3');
INSERT INTO `d_sys_region` VALUES ('1404', '165', '郾城区', '3');
INSERT INTO `d_sys_region` VALUES ('1405', '165', '源汇区', '3');
INSERT INTO `d_sys_region` VALUES ('1406', '165', '召陵区', '3');
INSERT INTO `d_sys_region` VALUES ('1407', '165', '舞阳县', '3');
INSERT INTO `d_sys_region` VALUES ('1408', '165', '临颍县', '3');
INSERT INTO `d_sys_region` VALUES ('1409', '166', '华龙区', '3');
INSERT INTO `d_sys_region` VALUES ('1410', '166', '清丰县', '3');
INSERT INTO `d_sys_region` VALUES ('1411', '166', '南乐县', '3');
INSERT INTO `d_sys_region` VALUES ('1412', '166', '范县', '3');
INSERT INTO `d_sys_region` VALUES ('1413', '166', '台前县', '3');
INSERT INTO `d_sys_region` VALUES ('1414', '166', '濮阳县', '3');
INSERT INTO `d_sys_region` VALUES ('1415', '167', '道里区', '3');
INSERT INTO `d_sys_region` VALUES ('1416', '167', '南岗区', '3');
INSERT INTO `d_sys_region` VALUES ('1417', '167', '动力区', '3');
INSERT INTO `d_sys_region` VALUES ('1418', '167', '平房区', '3');
INSERT INTO `d_sys_region` VALUES ('1419', '167', '香坊区', '3');
INSERT INTO `d_sys_region` VALUES ('1420', '167', '太平区', '3');
INSERT INTO `d_sys_region` VALUES ('1421', '167', '道外区', '3');
INSERT INTO `d_sys_region` VALUES ('1422', '167', '阿城区', '3');
INSERT INTO `d_sys_region` VALUES ('1423', '167', '呼兰区', '3');
INSERT INTO `d_sys_region` VALUES ('1424', '167', '松北区', '3');
INSERT INTO `d_sys_region` VALUES ('1425', '167', '尚志市', '3');
INSERT INTO `d_sys_region` VALUES ('1426', '167', '双城市', '3');
INSERT INTO `d_sys_region` VALUES ('1427', '167', '五常市', '3');
INSERT INTO `d_sys_region` VALUES ('1428', '167', '方正县', '3');
INSERT INTO `d_sys_region` VALUES ('1429', '167', '宾县', '3');
INSERT INTO `d_sys_region` VALUES ('1430', '167', '依兰县', '3');
INSERT INTO `d_sys_region` VALUES ('1431', '167', '巴彦县', '3');
INSERT INTO `d_sys_region` VALUES ('1432', '167', '通河县', '3');
INSERT INTO `d_sys_region` VALUES ('1433', '167', '木兰县', '3');
INSERT INTO `d_sys_region` VALUES ('1434', '167', '延寿县', '3');
INSERT INTO `d_sys_region` VALUES ('1435', '168', '萨尔图区', '3');
INSERT INTO `d_sys_region` VALUES ('1436', '168', '红岗区', '3');
INSERT INTO `d_sys_region` VALUES ('1437', '168', '龙凤区', '3');
INSERT INTO `d_sys_region` VALUES ('1438', '168', '让胡路区', '3');
INSERT INTO `d_sys_region` VALUES ('1439', '168', '大同区', '3');
INSERT INTO `d_sys_region` VALUES ('1440', '168', '肇州县', '3');
INSERT INTO `d_sys_region` VALUES ('1441', '168', '肇源县', '3');
INSERT INTO `d_sys_region` VALUES ('1442', '168', '林甸县', '3');
INSERT INTO `d_sys_region` VALUES ('1443', '168', '杜尔伯特', '3');
INSERT INTO `d_sys_region` VALUES ('1444', '169', '呼玛县', '3');
INSERT INTO `d_sys_region` VALUES ('1445', '169', '漠河县', '3');
INSERT INTO `d_sys_region` VALUES ('1446', '169', '塔河县', '3');
INSERT INTO `d_sys_region` VALUES ('1447', '170', '兴山区', '3');
INSERT INTO `d_sys_region` VALUES ('1448', '170', '工农区', '3');
INSERT INTO `d_sys_region` VALUES ('1449', '170', '南山区', '3');
INSERT INTO `d_sys_region` VALUES ('1450', '170', '兴安区', '3');
INSERT INTO `d_sys_region` VALUES ('1451', '170', '向阳区', '3');
INSERT INTO `d_sys_region` VALUES ('1452', '170', '东山区', '3');
INSERT INTO `d_sys_region` VALUES ('1453', '170', '萝北县', '3');
INSERT INTO `d_sys_region` VALUES ('1454', '170', '绥滨县', '3');
INSERT INTO `d_sys_region` VALUES ('1455', '171', '爱辉区', '3');
INSERT INTO `d_sys_region` VALUES ('1456', '171', '五大连池市', '3');
INSERT INTO `d_sys_region` VALUES ('1457', '171', '北安市', '3');
INSERT INTO `d_sys_region` VALUES ('1458', '171', '嫩江县', '3');
INSERT INTO `d_sys_region` VALUES ('1459', '171', '逊克县', '3');
INSERT INTO `d_sys_region` VALUES ('1460', '171', '孙吴县', '3');
INSERT INTO `d_sys_region` VALUES ('1461', '172', '鸡冠区', '3');
INSERT INTO `d_sys_region` VALUES ('1462', '172', '恒山区', '3');
INSERT INTO `d_sys_region` VALUES ('1463', '172', '城子河区', '3');
INSERT INTO `d_sys_region` VALUES ('1464', '172', '滴道区', '3');
INSERT INTO `d_sys_region` VALUES ('1465', '172', '梨树区', '3');
INSERT INTO `d_sys_region` VALUES ('1466', '172', '虎林市', '3');
INSERT INTO `d_sys_region` VALUES ('1467', '172', '密山市', '3');
INSERT INTO `d_sys_region` VALUES ('1468', '172', '鸡东县', '3');
INSERT INTO `d_sys_region` VALUES ('1469', '173', '前进区', '3');
INSERT INTO `d_sys_region` VALUES ('1470', '173', '郊区', '3');
INSERT INTO `d_sys_region` VALUES ('1471', '173', '向阳区', '3');
INSERT INTO `d_sys_region` VALUES ('1472', '173', '东风区', '3');
INSERT INTO `d_sys_region` VALUES ('1473', '173', '同江市', '3');
INSERT INTO `d_sys_region` VALUES ('1474', '173', '富锦市', '3');
INSERT INTO `d_sys_region` VALUES ('1475', '173', '桦南县', '3');
INSERT INTO `d_sys_region` VALUES ('1476', '173', '桦川县', '3');
INSERT INTO `d_sys_region` VALUES ('1477', '173', '汤原县', '3');
INSERT INTO `d_sys_region` VALUES ('1478', '173', '抚远县', '3');
INSERT INTO `d_sys_region` VALUES ('1479', '174', '爱民区', '3');
INSERT INTO `d_sys_region` VALUES ('1480', '174', '东安区', '3');
INSERT INTO `d_sys_region` VALUES ('1481', '174', '阳明区', '3');
INSERT INTO `d_sys_region` VALUES ('1482', '174', '西安区', '3');
INSERT INTO `d_sys_region` VALUES ('1483', '174', '绥芬河市', '3');
INSERT INTO `d_sys_region` VALUES ('1484', '174', '海林市', '3');
INSERT INTO `d_sys_region` VALUES ('1485', '174', '宁安市', '3');
INSERT INTO `d_sys_region` VALUES ('1486', '174', '穆棱市', '3');
INSERT INTO `d_sys_region` VALUES ('1487', '174', '东宁县', '3');
INSERT INTO `d_sys_region` VALUES ('1488', '174', '林口县', '3');
INSERT INTO `d_sys_region` VALUES ('1489', '175', '桃山区', '3');
INSERT INTO `d_sys_region` VALUES ('1490', '175', '新兴区', '3');
INSERT INTO `d_sys_region` VALUES ('1491', '175', '茄子河区', '3');
INSERT INTO `d_sys_region` VALUES ('1492', '175', '勃利县', '3');
INSERT INTO `d_sys_region` VALUES ('1493', '176', '龙沙区', '3');
INSERT INTO `d_sys_region` VALUES ('1494', '176', '昂昂溪区', '3');
INSERT INTO `d_sys_region` VALUES ('1495', '176', '铁峰区', '3');
INSERT INTO `d_sys_region` VALUES ('1496', '176', '建华区', '3');
INSERT INTO `d_sys_region` VALUES ('1497', '176', '富拉尔基区', '3');
INSERT INTO `d_sys_region` VALUES ('1498', '176', '碾子山区', '3');
INSERT INTO `d_sys_region` VALUES ('1499', '176', '梅里斯达斡尔区', '3');
INSERT INTO `d_sys_region` VALUES ('1500', '176', '讷河市', '3');
INSERT INTO `d_sys_region` VALUES ('1501', '176', '龙江县', '3');
INSERT INTO `d_sys_region` VALUES ('1502', '176', '依安县', '3');
INSERT INTO `d_sys_region` VALUES ('1503', '176', '泰来县', '3');
INSERT INTO `d_sys_region` VALUES ('1504', '176', '甘南县', '3');
INSERT INTO `d_sys_region` VALUES ('1505', '176', '富裕县', '3');
INSERT INTO `d_sys_region` VALUES ('1506', '176', '克山县', '3');
INSERT INTO `d_sys_region` VALUES ('1507', '176', '克东县', '3');
INSERT INTO `d_sys_region` VALUES ('1508', '176', '拜泉县', '3');
INSERT INTO `d_sys_region` VALUES ('1509', '177', '尖山区', '3');
INSERT INTO `d_sys_region` VALUES ('1510', '177', '岭东区', '3');
INSERT INTO `d_sys_region` VALUES ('1511', '177', '四方台区', '3');
INSERT INTO `d_sys_region` VALUES ('1512', '177', '宝山区', '3');
INSERT INTO `d_sys_region` VALUES ('1513', '177', '集贤县', '3');
INSERT INTO `d_sys_region` VALUES ('1514', '177', '友谊县', '3');
INSERT INTO `d_sys_region` VALUES ('1515', '177', '宝清县', '3');
INSERT INTO `d_sys_region` VALUES ('1516', '177', '饶河县', '3');
INSERT INTO `d_sys_region` VALUES ('1517', '178', '北林区', '3');
INSERT INTO `d_sys_region` VALUES ('1518', '178', '安达市', '3');
INSERT INTO `d_sys_region` VALUES ('1519', '178', '肇东市', '3');
INSERT INTO `d_sys_region` VALUES ('1520', '178', '海伦市', '3');
INSERT INTO `d_sys_region` VALUES ('1521', '178', '望奎县', '3');
INSERT INTO `d_sys_region` VALUES ('1522', '178', '兰西县', '3');
INSERT INTO `d_sys_region` VALUES ('1523', '178', '青冈县', '3');
INSERT INTO `d_sys_region` VALUES ('1524', '178', '庆安县', '3');
INSERT INTO `d_sys_region` VALUES ('1525', '178', '明水县', '3');
INSERT INTO `d_sys_region` VALUES ('1526', '178', '绥棱县', '3');
INSERT INTO `d_sys_region` VALUES ('1527', '179', '伊春区', '3');
INSERT INTO `d_sys_region` VALUES ('1528', '179', '带岭区', '3');
INSERT INTO `d_sys_region` VALUES ('1529', '179', '南岔区', '3');
INSERT INTO `d_sys_region` VALUES ('1530', '179', '金山屯区', '3');
INSERT INTO `d_sys_region` VALUES ('1531', '179', '西林区', '3');
INSERT INTO `d_sys_region` VALUES ('1532', '179', '美溪区', '3');
INSERT INTO `d_sys_region` VALUES ('1533', '179', '乌马河区', '3');
INSERT INTO `d_sys_region` VALUES ('1534', '179', '翠峦区', '3');
INSERT INTO `d_sys_region` VALUES ('1535', '179', '友好区', '3');
INSERT INTO `d_sys_region` VALUES ('1536', '179', '上甘岭区', '3');
INSERT INTO `d_sys_region` VALUES ('1537', '179', '五营区', '3');
INSERT INTO `d_sys_region` VALUES ('1538', '179', '红星区', '3');
INSERT INTO `d_sys_region` VALUES ('1539', '179', '新青区', '3');
INSERT INTO `d_sys_region` VALUES ('1540', '179', '汤旺河区', '3');
INSERT INTO `d_sys_region` VALUES ('1541', '179', '乌伊岭区', '3');
INSERT INTO `d_sys_region` VALUES ('1542', '179', '铁力市', '3');
INSERT INTO `d_sys_region` VALUES ('1543', '179', '嘉荫县', '3');
INSERT INTO `d_sys_region` VALUES ('1544', '180', '江岸区', '3');
INSERT INTO `d_sys_region` VALUES ('1545', '180', '武昌区', '3');
INSERT INTO `d_sys_region` VALUES ('1546', '180', '江汉区', '3');
INSERT INTO `d_sys_region` VALUES ('1547', '180', '硚口区', '3');
INSERT INTO `d_sys_region` VALUES ('1548', '180', '汉阳区', '3');
INSERT INTO `d_sys_region` VALUES ('1549', '180', '青山区', '3');
INSERT INTO `d_sys_region` VALUES ('1550', '180', '洪山区', '3');
INSERT INTO `d_sys_region` VALUES ('1551', '180', '东西湖区', '3');
INSERT INTO `d_sys_region` VALUES ('1552', '180', '汉南区', '3');
INSERT INTO `d_sys_region` VALUES ('1553', '180', '蔡甸区', '3');
INSERT INTO `d_sys_region` VALUES ('1554', '180', '江夏区', '3');
INSERT INTO `d_sys_region` VALUES ('1555', '180', '黄陂区', '3');
INSERT INTO `d_sys_region` VALUES ('1556', '180', '新洲区', '3');
INSERT INTO `d_sys_region` VALUES ('1557', '180', '经济开发区', '3');
INSERT INTO `d_sys_region` VALUES ('1558', '181', '仙桃市', '3');
INSERT INTO `d_sys_region` VALUES ('1559', '182', '鄂城区', '3');
INSERT INTO `d_sys_region` VALUES ('1560', '182', '华容区', '3');
INSERT INTO `d_sys_region` VALUES ('1561', '182', '梁子湖区', '3');
INSERT INTO `d_sys_region` VALUES ('1562', '183', '黄州区', '3');
INSERT INTO `d_sys_region` VALUES ('1563', '183', '麻城市', '3');
INSERT INTO `d_sys_region` VALUES ('1564', '183', '武穴市', '3');
INSERT INTO `d_sys_region` VALUES ('1565', '183', '团风县', '3');
INSERT INTO `d_sys_region` VALUES ('1566', '183', '红安县', '3');
INSERT INTO `d_sys_region` VALUES ('1567', '183', '罗田县', '3');
INSERT INTO `d_sys_region` VALUES ('1568', '183', '英山县', '3');
INSERT INTO `d_sys_region` VALUES ('1569', '183', '浠水县', '3');
INSERT INTO `d_sys_region` VALUES ('1570', '183', '蕲春县', '3');
INSERT INTO `d_sys_region` VALUES ('1571', '183', '黄梅县', '3');
INSERT INTO `d_sys_region` VALUES ('1572', '184', '黄石港区', '3');
INSERT INTO `d_sys_region` VALUES ('1573', '184', '西塞山区', '3');
INSERT INTO `d_sys_region` VALUES ('1574', '184', '下陆区', '3');
INSERT INTO `d_sys_region` VALUES ('1575', '184', '铁山区', '3');
INSERT INTO `d_sys_region` VALUES ('1576', '184', '大冶市', '3');
INSERT INTO `d_sys_region` VALUES ('1577', '184', '阳新县', '3');
INSERT INTO `d_sys_region` VALUES ('1578', '185', '东宝区', '3');
INSERT INTO `d_sys_region` VALUES ('1579', '185', '掇刀区', '3');
INSERT INTO `d_sys_region` VALUES ('1580', '185', '钟祥市', '3');
INSERT INTO `d_sys_region` VALUES ('1581', '185', '京山县', '3');
INSERT INTO `d_sys_region` VALUES ('1582', '185', '沙洋县', '3');
INSERT INTO `d_sys_region` VALUES ('1583', '186', '沙市区', '3');
INSERT INTO `d_sys_region` VALUES ('1584', '186', '荆州区', '3');
INSERT INTO `d_sys_region` VALUES ('1585', '186', '石首市', '3');
INSERT INTO `d_sys_region` VALUES ('1586', '186', '洪湖市', '3');
INSERT INTO `d_sys_region` VALUES ('1587', '186', '松滋市', '3');
INSERT INTO `d_sys_region` VALUES ('1588', '186', '公安县', '3');
INSERT INTO `d_sys_region` VALUES ('1589', '186', '监利县', '3');
INSERT INTO `d_sys_region` VALUES ('1590', '186', '江陵县', '3');
INSERT INTO `d_sys_region` VALUES ('1591', '187', '潜江市', '3');
INSERT INTO `d_sys_region` VALUES ('1592', '188', '神农架林区', '3');
INSERT INTO `d_sys_region` VALUES ('1593', '189', '张湾区', '3');
INSERT INTO `d_sys_region` VALUES ('1594', '189', '茅箭区', '3');
INSERT INTO `d_sys_region` VALUES ('1595', '189', '丹江口市', '3');
INSERT INTO `d_sys_region` VALUES ('1596', '189', '郧县', '3');
INSERT INTO `d_sys_region` VALUES ('1597', '189', '郧西县', '3');
INSERT INTO `d_sys_region` VALUES ('1598', '189', '竹山县', '3');
INSERT INTO `d_sys_region` VALUES ('1599', '189', '竹溪县', '3');
INSERT INTO `d_sys_region` VALUES ('1600', '189', '房县', '3');
INSERT INTO `d_sys_region` VALUES ('1601', '190', '曾都区', '3');
INSERT INTO `d_sys_region` VALUES ('1602', '190', '广水市', '3');
INSERT INTO `d_sys_region` VALUES ('1603', '191', '天门市', '3');
INSERT INTO `d_sys_region` VALUES ('1604', '192', '咸安区', '3');
INSERT INTO `d_sys_region` VALUES ('1605', '192', '赤壁市', '3');
INSERT INTO `d_sys_region` VALUES ('1606', '192', '嘉鱼县', '3');
INSERT INTO `d_sys_region` VALUES ('1607', '192', '通城县', '3');
INSERT INTO `d_sys_region` VALUES ('1608', '192', '崇阳县', '3');
INSERT INTO `d_sys_region` VALUES ('1609', '192', '通山县', '3');
INSERT INTO `d_sys_region` VALUES ('1610', '193', '襄城区', '3');
INSERT INTO `d_sys_region` VALUES ('1611', '193', '樊城区', '3');
INSERT INTO `d_sys_region` VALUES ('1612', '193', '襄阳区', '3');
INSERT INTO `d_sys_region` VALUES ('1613', '193', '老河口市', '3');
INSERT INTO `d_sys_region` VALUES ('1614', '193', '枣阳市', '3');
INSERT INTO `d_sys_region` VALUES ('1615', '193', '宜城市', '3');
INSERT INTO `d_sys_region` VALUES ('1616', '193', '南漳县', '3');
INSERT INTO `d_sys_region` VALUES ('1617', '193', '谷城县', '3');
INSERT INTO `d_sys_region` VALUES ('1618', '193', '保康县', '3');
INSERT INTO `d_sys_region` VALUES ('1619', '194', '孝南区', '3');
INSERT INTO `d_sys_region` VALUES ('1620', '194', '应城市', '3');
INSERT INTO `d_sys_region` VALUES ('1621', '194', '安陆市', '3');
INSERT INTO `d_sys_region` VALUES ('1622', '194', '汉川市', '3');
INSERT INTO `d_sys_region` VALUES ('1623', '194', '孝昌县', '3');
INSERT INTO `d_sys_region` VALUES ('1624', '194', '大悟县', '3');
INSERT INTO `d_sys_region` VALUES ('1625', '194', '云梦县', '3');
INSERT INTO `d_sys_region` VALUES ('1626', '195', '长阳', '3');
INSERT INTO `d_sys_region` VALUES ('1627', '195', '五峰', '3');
INSERT INTO `d_sys_region` VALUES ('1628', '195', '西陵区', '3');
INSERT INTO `d_sys_region` VALUES ('1629', '195', '伍家岗区', '3');
INSERT INTO `d_sys_region` VALUES ('1630', '195', '点军区', '3');
INSERT INTO `d_sys_region` VALUES ('1631', '195', '猇亭区', '3');
INSERT INTO `d_sys_region` VALUES ('1632', '195', '夷陵区', '3');
INSERT INTO `d_sys_region` VALUES ('1633', '195', '宜都市', '3');
INSERT INTO `d_sys_region` VALUES ('1634', '195', '当阳市', '3');
INSERT INTO `d_sys_region` VALUES ('1635', '195', '枝江市', '3');
INSERT INTO `d_sys_region` VALUES ('1636', '195', '远安县', '3');
INSERT INTO `d_sys_region` VALUES ('1637', '195', '兴山县', '3');
INSERT INTO `d_sys_region` VALUES ('1638', '195', '秭归县', '3');
INSERT INTO `d_sys_region` VALUES ('1639', '196', '恩施市', '3');
INSERT INTO `d_sys_region` VALUES ('1640', '196', '利川市', '3');
INSERT INTO `d_sys_region` VALUES ('1641', '196', '建始县', '3');
INSERT INTO `d_sys_region` VALUES ('1642', '196', '巴东县', '3');
INSERT INTO `d_sys_region` VALUES ('1643', '196', '宣恩县', '3');
INSERT INTO `d_sys_region` VALUES ('1644', '196', '咸丰县', '3');
INSERT INTO `d_sys_region` VALUES ('1645', '196', '来凤县', '3');
INSERT INTO `d_sys_region` VALUES ('1646', '196', '鹤峰县', '3');
INSERT INTO `d_sys_region` VALUES ('1647', '197', '岳麓区', '3');
INSERT INTO `d_sys_region` VALUES ('1648', '197', '芙蓉区', '3');
INSERT INTO `d_sys_region` VALUES ('1649', '197', '天心区', '3');
INSERT INTO `d_sys_region` VALUES ('1650', '197', '开福区', '3');
INSERT INTO `d_sys_region` VALUES ('1651', '197', '雨花区', '3');
INSERT INTO `d_sys_region` VALUES ('1652', '197', '开发区', '3');
INSERT INTO `d_sys_region` VALUES ('1653', '197', '浏阳市', '3');
INSERT INTO `d_sys_region` VALUES ('1654', '197', '长沙县', '3');
INSERT INTO `d_sys_region` VALUES ('1655', '197', '望城县', '3');
INSERT INTO `d_sys_region` VALUES ('1656', '197', '宁乡县', '3');
INSERT INTO `d_sys_region` VALUES ('1657', '198', '永定区', '3');
INSERT INTO `d_sys_region` VALUES ('1658', '198', '武陵源区', '3');
INSERT INTO `d_sys_region` VALUES ('1659', '198', '慈利县', '3');
INSERT INTO `d_sys_region` VALUES ('1660', '198', '桑植县', '3');
INSERT INTO `d_sys_region` VALUES ('1661', '199', '武陵区', '3');
INSERT INTO `d_sys_region` VALUES ('1662', '199', '鼎城区', '3');
INSERT INTO `d_sys_region` VALUES ('1663', '199', '津市市', '3');
INSERT INTO `d_sys_region` VALUES ('1664', '199', '安乡县', '3');
INSERT INTO `d_sys_region` VALUES ('1665', '199', '汉寿县', '3');
INSERT INTO `d_sys_region` VALUES ('1666', '199', '澧县', '3');
INSERT INTO `d_sys_region` VALUES ('1667', '199', '临澧县', '3');
INSERT INTO `d_sys_region` VALUES ('1668', '199', '桃源县', '3');
INSERT INTO `d_sys_region` VALUES ('1669', '199', '石门县', '3');
INSERT INTO `d_sys_region` VALUES ('1670', '200', '北湖区', '3');
INSERT INTO `d_sys_region` VALUES ('1671', '200', '苏仙区', '3');
INSERT INTO `d_sys_region` VALUES ('1672', '200', '资兴市', '3');
INSERT INTO `d_sys_region` VALUES ('1673', '200', '桂阳县', '3');
INSERT INTO `d_sys_region` VALUES ('1674', '200', '宜章县', '3');
INSERT INTO `d_sys_region` VALUES ('1675', '200', '永兴县', '3');
INSERT INTO `d_sys_region` VALUES ('1676', '200', '嘉禾县', '3');
INSERT INTO `d_sys_region` VALUES ('1677', '200', '临武县', '3');
INSERT INTO `d_sys_region` VALUES ('1678', '200', '汝城县', '3');
INSERT INTO `d_sys_region` VALUES ('1679', '200', '桂东县', '3');
INSERT INTO `d_sys_region` VALUES ('1680', '200', '安仁县', '3');
INSERT INTO `d_sys_region` VALUES ('1681', '201', '雁峰区', '3');
INSERT INTO `d_sys_region` VALUES ('1682', '201', '珠晖区', '3');
INSERT INTO `d_sys_region` VALUES ('1683', '201', '石鼓区', '3');
INSERT INTO `d_sys_region` VALUES ('1684', '201', '蒸湘区', '3');
INSERT INTO `d_sys_region` VALUES ('1685', '201', '南岳区', '3');
INSERT INTO `d_sys_region` VALUES ('1686', '201', '耒阳市', '3');
INSERT INTO `d_sys_region` VALUES ('1687', '201', '常宁市', '3');
INSERT INTO `d_sys_region` VALUES ('1688', '201', '衡阳县', '3');
INSERT INTO `d_sys_region` VALUES ('1689', '201', '衡南县', '3');
INSERT INTO `d_sys_region` VALUES ('1690', '201', '衡山县', '3');
INSERT INTO `d_sys_region` VALUES ('1691', '201', '衡东县', '3');
INSERT INTO `d_sys_region` VALUES ('1692', '201', '祁东县', '3');
INSERT INTO `d_sys_region` VALUES ('1693', '202', '鹤城区', '3');
INSERT INTO `d_sys_region` VALUES ('1694', '202', '靖州', '3');
INSERT INTO `d_sys_region` VALUES ('1695', '202', '麻阳', '3');
INSERT INTO `d_sys_region` VALUES ('1696', '202', '通道', '3');
INSERT INTO `d_sys_region` VALUES ('1697', '202', '新晃', '3');
INSERT INTO `d_sys_region` VALUES ('1698', '202', '芷江', '3');
INSERT INTO `d_sys_region` VALUES ('1699', '202', '沅陵县', '3');
INSERT INTO `d_sys_region` VALUES ('1700', '202', '辰溪县', '3');
INSERT INTO `d_sys_region` VALUES ('1701', '202', '溆浦县', '3');
INSERT INTO `d_sys_region` VALUES ('1702', '202', '中方县', '3');
INSERT INTO `d_sys_region` VALUES ('1703', '202', '会同县', '3');
INSERT INTO `d_sys_region` VALUES ('1704', '202', '洪江市', '3');
INSERT INTO `d_sys_region` VALUES ('1705', '203', '娄星区', '3');
INSERT INTO `d_sys_region` VALUES ('1706', '203', '冷水江市', '3');
INSERT INTO `d_sys_region` VALUES ('1707', '203', '涟源市', '3');
INSERT INTO `d_sys_region` VALUES ('1708', '203', '双峰县', '3');
INSERT INTO `d_sys_region` VALUES ('1709', '203', '新化县', '3');
INSERT INTO `d_sys_region` VALUES ('1710', '204', '城步', '3');
INSERT INTO `d_sys_region` VALUES ('1711', '204', '双清区', '3');
INSERT INTO `d_sys_region` VALUES ('1712', '204', '大祥区', '3');
INSERT INTO `d_sys_region` VALUES ('1713', '204', '北塔区', '3');
INSERT INTO `d_sys_region` VALUES ('1714', '204', '武冈市', '3');
INSERT INTO `d_sys_region` VALUES ('1715', '204', '邵东县', '3');
INSERT INTO `d_sys_region` VALUES ('1716', '204', '新邵县', '3');
INSERT INTO `d_sys_region` VALUES ('1717', '204', '邵阳县', '3');
INSERT INTO `d_sys_region` VALUES ('1718', '204', '隆回县', '3');
INSERT INTO `d_sys_region` VALUES ('1719', '204', '洞口县', '3');
INSERT INTO `d_sys_region` VALUES ('1720', '204', '绥宁县', '3');
INSERT INTO `d_sys_region` VALUES ('1721', '204', '新宁县', '3');
INSERT INTO `d_sys_region` VALUES ('1722', '205', '岳塘区', '3');
INSERT INTO `d_sys_region` VALUES ('1723', '205', '雨湖区', '3');
INSERT INTO `d_sys_region` VALUES ('1724', '205', '湘乡市', '3');
INSERT INTO `d_sys_region` VALUES ('1725', '205', '韶山市', '3');
INSERT INTO `d_sys_region` VALUES ('1726', '205', '湘潭县', '3');
INSERT INTO `d_sys_region` VALUES ('1727', '206', '吉首市', '3');
INSERT INTO `d_sys_region` VALUES ('1728', '206', '泸溪县', '3');
INSERT INTO `d_sys_region` VALUES ('1729', '206', '凤凰县', '3');
INSERT INTO `d_sys_region` VALUES ('1730', '206', '花垣县', '3');
INSERT INTO `d_sys_region` VALUES ('1731', '206', '保靖县', '3');
INSERT INTO `d_sys_region` VALUES ('1732', '206', '古丈县', '3');
INSERT INTO `d_sys_region` VALUES ('1733', '206', '永顺县', '3');
INSERT INTO `d_sys_region` VALUES ('1734', '206', '龙山县', '3');
INSERT INTO `d_sys_region` VALUES ('1735', '207', '赫山区', '3');
INSERT INTO `d_sys_region` VALUES ('1736', '207', '资阳区', '3');
INSERT INTO `d_sys_region` VALUES ('1737', '207', '沅江市', '3');
INSERT INTO `d_sys_region` VALUES ('1738', '207', '南县', '3');
INSERT INTO `d_sys_region` VALUES ('1739', '207', '桃江县', '3');
INSERT INTO `d_sys_region` VALUES ('1740', '207', '安化县', '3');
INSERT INTO `d_sys_region` VALUES ('1741', '208', '江华', '3');
INSERT INTO `d_sys_region` VALUES ('1742', '208', '冷水滩区', '3');
INSERT INTO `d_sys_region` VALUES ('1743', '208', '零陵区', '3');
INSERT INTO `d_sys_region` VALUES ('1744', '208', '祁阳县', '3');
INSERT INTO `d_sys_region` VALUES ('1745', '208', '东安县', '3');
INSERT INTO `d_sys_region` VALUES ('1746', '208', '双牌县', '3');
INSERT INTO `d_sys_region` VALUES ('1747', '208', '道县', '3');
INSERT INTO `d_sys_region` VALUES ('1748', '208', '江永县', '3');
INSERT INTO `d_sys_region` VALUES ('1749', '208', '宁远县', '3');
INSERT INTO `d_sys_region` VALUES ('1750', '208', '蓝山县', '3');
INSERT INTO `d_sys_region` VALUES ('1751', '208', '新田县', '3');
INSERT INTO `d_sys_region` VALUES ('1752', '209', '岳阳楼区', '3');
INSERT INTO `d_sys_region` VALUES ('1753', '209', '君山区', '3');
INSERT INTO `d_sys_region` VALUES ('1754', '209', '云溪区', '3');
INSERT INTO `d_sys_region` VALUES ('1755', '209', '汨罗市', '3');
INSERT INTO `d_sys_region` VALUES ('1756', '209', '临湘市', '3');
INSERT INTO `d_sys_region` VALUES ('1757', '209', '岳阳县', '3');
INSERT INTO `d_sys_region` VALUES ('1758', '209', '华容县', '3');
INSERT INTO `d_sys_region` VALUES ('1759', '209', '湘阴县', '3');
INSERT INTO `d_sys_region` VALUES ('1760', '209', '平江县', '3');
INSERT INTO `d_sys_region` VALUES ('1761', '210', '天元区', '3');
INSERT INTO `d_sys_region` VALUES ('1762', '210', '荷塘区', '3');
INSERT INTO `d_sys_region` VALUES ('1763', '210', '芦淞区', '3');
INSERT INTO `d_sys_region` VALUES ('1764', '210', '石峰区', '3');
INSERT INTO `d_sys_region` VALUES ('1765', '210', '醴陵市', '3');
INSERT INTO `d_sys_region` VALUES ('1766', '210', '株洲县', '3');
INSERT INTO `d_sys_region` VALUES ('1767', '210', '攸县', '3');
INSERT INTO `d_sys_region` VALUES ('1768', '210', '茶陵县', '3');
INSERT INTO `d_sys_region` VALUES ('1769', '210', '炎陵县', '3');
INSERT INTO `d_sys_region` VALUES ('1770', '211', '朝阳区', '3');
INSERT INTO `d_sys_region` VALUES ('1771', '211', '宽城区', '3');
INSERT INTO `d_sys_region` VALUES ('1772', '211', '二道区', '3');
INSERT INTO `d_sys_region` VALUES ('1773', '211', '南关区', '3');
INSERT INTO `d_sys_region` VALUES ('1774', '211', '绿园区', '3');
INSERT INTO `d_sys_region` VALUES ('1775', '211', '双阳区', '3');
INSERT INTO `d_sys_region` VALUES ('1776', '211', '净月潭开发区', '3');
INSERT INTO `d_sys_region` VALUES ('1777', '211', '高新技术开发区', '3');
INSERT INTO `d_sys_region` VALUES ('1778', '211', '经济技术开发区', '3');
INSERT INTO `d_sys_region` VALUES ('1779', '211', '汽车产业开发区', '3');
INSERT INTO `d_sys_region` VALUES ('1780', '211', '德惠市', '3');
INSERT INTO `d_sys_region` VALUES ('1781', '211', '九台市', '3');
INSERT INTO `d_sys_region` VALUES ('1782', '211', '榆树市', '3');
INSERT INTO `d_sys_region` VALUES ('1783', '211', '农安县', '3');
INSERT INTO `d_sys_region` VALUES ('1784', '212', '船营区', '3');
INSERT INTO `d_sys_region` VALUES ('1785', '212', '昌邑区', '3');
INSERT INTO `d_sys_region` VALUES ('1786', '212', '龙潭区', '3');
INSERT INTO `d_sys_region` VALUES ('1787', '212', '丰满区', '3');
INSERT INTO `d_sys_region` VALUES ('1788', '212', '蛟河市', '3');
INSERT INTO `d_sys_region` VALUES ('1789', '212', '桦甸市', '3');
INSERT INTO `d_sys_region` VALUES ('1790', '212', '舒兰市', '3');
INSERT INTO `d_sys_region` VALUES ('1791', '212', '磐石市', '3');
INSERT INTO `d_sys_region` VALUES ('1792', '212', '永吉县', '3');
INSERT INTO `d_sys_region` VALUES ('1793', '213', '洮北区', '3');
INSERT INTO `d_sys_region` VALUES ('1794', '213', '洮南市', '3');
INSERT INTO `d_sys_region` VALUES ('1795', '213', '大安市', '3');
INSERT INTO `d_sys_region` VALUES ('1796', '213', '镇赉县', '3');
INSERT INTO `d_sys_region` VALUES ('1797', '213', '通榆县', '3');
INSERT INTO `d_sys_region` VALUES ('1798', '214', '江源区', '3');
INSERT INTO `d_sys_region` VALUES ('1799', '214', '八道江区', '3');
INSERT INTO `d_sys_region` VALUES ('1800', '214', '长白', '3');
INSERT INTO `d_sys_region` VALUES ('1801', '214', '临江市', '3');
INSERT INTO `d_sys_region` VALUES ('1802', '214', '抚松县', '3');
INSERT INTO `d_sys_region` VALUES ('1803', '214', '靖宇县', '3');
INSERT INTO `d_sys_region` VALUES ('1804', '215', '龙山区', '3');
INSERT INTO `d_sys_region` VALUES ('1805', '215', '西安区', '3');
INSERT INTO `d_sys_region` VALUES ('1806', '215', '东丰县', '3');
INSERT INTO `d_sys_region` VALUES ('1807', '215', '东辽县', '3');
INSERT INTO `d_sys_region` VALUES ('1808', '216', '铁西区', '3');
INSERT INTO `d_sys_region` VALUES ('1809', '216', '铁东区', '3');
INSERT INTO `d_sys_region` VALUES ('1810', '216', '伊通', '3');
INSERT INTO `d_sys_region` VALUES ('1811', '216', '公主岭市', '3');
INSERT INTO `d_sys_region` VALUES ('1812', '216', '双辽市', '3');
INSERT INTO `d_sys_region` VALUES ('1813', '216', '梨树县', '3');
INSERT INTO `d_sys_region` VALUES ('1814', '217', '前郭尔罗斯', '3');
INSERT INTO `d_sys_region` VALUES ('1815', '217', '宁江区', '3');
INSERT INTO `d_sys_region` VALUES ('1816', '217', '长岭县', '3');
INSERT INTO `d_sys_region` VALUES ('1817', '217', '乾安县', '3');
INSERT INTO `d_sys_region` VALUES ('1818', '217', '扶余县', '3');
INSERT INTO `d_sys_region` VALUES ('1819', '218', '东昌区', '3');
INSERT INTO `d_sys_region` VALUES ('1820', '218', '二道江区', '3');
INSERT INTO `d_sys_region` VALUES ('1821', '218', '梅河口市', '3');
INSERT INTO `d_sys_region` VALUES ('1822', '218', '集安市', '3');
INSERT INTO `d_sys_region` VALUES ('1823', '218', '通化县', '3');
INSERT INTO `d_sys_region` VALUES ('1824', '218', '辉南县', '3');
INSERT INTO `d_sys_region` VALUES ('1825', '218', '柳河县', '3');
INSERT INTO `d_sys_region` VALUES ('1826', '219', '延吉市', '3');
INSERT INTO `d_sys_region` VALUES ('1827', '219', '图们市', '3');
INSERT INTO `d_sys_region` VALUES ('1828', '219', '敦化市', '3');
INSERT INTO `d_sys_region` VALUES ('1829', '219', '珲春市', '3');
INSERT INTO `d_sys_region` VALUES ('1830', '219', '龙井市', '3');
INSERT INTO `d_sys_region` VALUES ('1831', '219', '和龙市', '3');
INSERT INTO `d_sys_region` VALUES ('1832', '219', '安图县', '3');
INSERT INTO `d_sys_region` VALUES ('1833', '219', '汪清县', '3');
INSERT INTO `d_sys_region` VALUES ('1834', '220', '玄武区', '3');
INSERT INTO `d_sys_region` VALUES ('1835', '220', '鼓楼区', '3');
INSERT INTO `d_sys_region` VALUES ('1836', '220', '白下区', '3');
INSERT INTO `d_sys_region` VALUES ('1837', '220', '建邺区', '3');
INSERT INTO `d_sys_region` VALUES ('1838', '220', '秦淮区', '3');
INSERT INTO `d_sys_region` VALUES ('1839', '220', '雨花台区', '3');
INSERT INTO `d_sys_region` VALUES ('1840', '220', '下关区', '3');
INSERT INTO `d_sys_region` VALUES ('1841', '220', '栖霞区', '3');
INSERT INTO `d_sys_region` VALUES ('1842', '220', '浦口区', '3');
INSERT INTO `d_sys_region` VALUES ('1843', '220', '江宁区', '3');
INSERT INTO `d_sys_region` VALUES ('1844', '220', '六合区', '3');
INSERT INTO `d_sys_region` VALUES ('1845', '220', '溧水县', '3');
INSERT INTO `d_sys_region` VALUES ('1846', '220', '高淳县', '3');
INSERT INTO `d_sys_region` VALUES ('1847', '221', '沧浪区', '3');
INSERT INTO `d_sys_region` VALUES ('1848', '221', '金阊区', '3');
INSERT INTO `d_sys_region` VALUES ('1849', '221', '平江区', '3');
INSERT INTO `d_sys_region` VALUES ('1850', '221', '虎丘区', '3');
INSERT INTO `d_sys_region` VALUES ('1851', '221', '吴中区', '3');
INSERT INTO `d_sys_region` VALUES ('1852', '221', '相城区', '3');
INSERT INTO `d_sys_region` VALUES ('1853', '221', '园区', '3');
INSERT INTO `d_sys_region` VALUES ('1854', '221', '新区', '3');
INSERT INTO `d_sys_region` VALUES ('1855', '221', '常熟市', '3');
INSERT INTO `d_sys_region` VALUES ('1856', '221', '张家港市', '3');
INSERT INTO `d_sys_region` VALUES ('1857', '221', '玉山镇', '3');
INSERT INTO `d_sys_region` VALUES ('1858', '221', '巴城镇', '3');
INSERT INTO `d_sys_region` VALUES ('1859', '221', '周市镇', '3');
INSERT INTO `d_sys_region` VALUES ('1860', '221', '陆家镇', '3');
INSERT INTO `d_sys_region` VALUES ('1861', '221', '花桥镇', '3');
INSERT INTO `d_sys_region` VALUES ('1862', '221', '淀山湖镇', '3');
INSERT INTO `d_sys_region` VALUES ('1863', '221', '张浦镇', '3');
INSERT INTO `d_sys_region` VALUES ('1864', '221', '周庄镇', '3');
INSERT INTO `d_sys_region` VALUES ('1865', '221', '千灯镇', '3');
INSERT INTO `d_sys_region` VALUES ('1866', '221', '锦溪镇', '3');
INSERT INTO `d_sys_region` VALUES ('1867', '221', '开发区', '3');
INSERT INTO `d_sys_region` VALUES ('1868', '221', '吴江市', '3');
INSERT INTO `d_sys_region` VALUES ('1869', '221', '太仓市', '3');
INSERT INTO `d_sys_region` VALUES ('1870', '222', '崇安区', '3');
INSERT INTO `d_sys_region` VALUES ('1871', '222', '北塘区', '3');
INSERT INTO `d_sys_region` VALUES ('1872', '222', '南长区', '3');
INSERT INTO `d_sys_region` VALUES ('1873', '222', '锡山区', '3');
INSERT INTO `d_sys_region` VALUES ('1874', '222', '惠山区', '3');
INSERT INTO `d_sys_region` VALUES ('1875', '222', '滨湖区', '3');
INSERT INTO `d_sys_region` VALUES ('1876', '222', '新区', '3');
INSERT INTO `d_sys_region` VALUES ('1877', '222', '江阴市', '3');
INSERT INTO `d_sys_region` VALUES ('1878', '222', '宜兴市', '3');
INSERT INTO `d_sys_region` VALUES ('1879', '223', '天宁区', '3');
INSERT INTO `d_sys_region` VALUES ('1880', '223', '钟楼区', '3');
INSERT INTO `d_sys_region` VALUES ('1881', '223', '戚墅堰区', '3');
INSERT INTO `d_sys_region` VALUES ('1882', '223', '郊区', '3');
INSERT INTO `d_sys_region` VALUES ('1883', '223', '新北区', '3');
INSERT INTO `d_sys_region` VALUES ('1884', '223', '武进区', '3');
INSERT INTO `d_sys_region` VALUES ('1885', '223', '溧阳市', '3');
INSERT INTO `d_sys_region` VALUES ('1886', '223', '金坛市', '3');
INSERT INTO `d_sys_region` VALUES ('1887', '224', '清河区', '3');
INSERT INTO `d_sys_region` VALUES ('1888', '224', '清浦区', '3');
INSERT INTO `d_sys_region` VALUES ('1889', '224', '楚州区', '3');
INSERT INTO `d_sys_region` VALUES ('1890', '224', '淮阴区', '3');
INSERT INTO `d_sys_region` VALUES ('1891', '224', '涟水县', '3');
INSERT INTO `d_sys_region` VALUES ('1892', '224', '洪泽县', '3');
INSERT INTO `d_sys_region` VALUES ('1893', '224', '盱眙县', '3');
INSERT INTO `d_sys_region` VALUES ('1894', '224', '金湖县', '3');
INSERT INTO `d_sys_region` VALUES ('1895', '225', '新浦区', '3');
INSERT INTO `d_sys_region` VALUES ('1896', '225', '连云区', '3');
INSERT INTO `d_sys_region` VALUES ('1897', '225', '海州区', '3');
INSERT INTO `d_sys_region` VALUES ('1898', '225', '赣榆县', '3');
INSERT INTO `d_sys_region` VALUES ('1899', '225', '东海县', '3');
INSERT INTO `d_sys_region` VALUES ('1900', '225', '灌云县', '3');
INSERT INTO `d_sys_region` VALUES ('1901', '225', '灌南县', '3');
INSERT INTO `d_sys_region` VALUES ('1902', '226', '崇川区', '3');
INSERT INTO `d_sys_region` VALUES ('1903', '226', '港闸区', '3');
INSERT INTO `d_sys_region` VALUES ('1904', '226', '经济开发区', '3');
INSERT INTO `d_sys_region` VALUES ('1905', '226', '启东市', '3');
INSERT INTO `d_sys_region` VALUES ('1906', '226', '如皋市', '3');
INSERT INTO `d_sys_region` VALUES ('1907', '226', '通州市', '3');
INSERT INTO `d_sys_region` VALUES ('1908', '226', '海门市', '3');
INSERT INTO `d_sys_region` VALUES ('1909', '226', '海安县', '3');
INSERT INTO `d_sys_region` VALUES ('1910', '226', '如东县', '3');
INSERT INTO `d_sys_region` VALUES ('1911', '227', '宿城区', '3');
INSERT INTO `d_sys_region` VALUES ('1912', '227', '宿豫区', '3');
INSERT INTO `d_sys_region` VALUES ('1913', '227', '宿豫县', '3');
INSERT INTO `d_sys_region` VALUES ('1914', '227', '沭阳县', '3');
INSERT INTO `d_sys_region` VALUES ('1915', '227', '泗阳县', '3');
INSERT INTO `d_sys_region` VALUES ('1916', '227', '泗洪县', '3');
INSERT INTO `d_sys_region` VALUES ('1917', '228', '海陵区', '3');
INSERT INTO `d_sys_region` VALUES ('1918', '228', '高港区', '3');
INSERT INTO `d_sys_region` VALUES ('1919', '228', '兴化市', '3');
INSERT INTO `d_sys_region` VALUES ('1920', '228', '靖江市', '3');
INSERT INTO `d_sys_region` VALUES ('1921', '228', '泰兴市', '3');
INSERT INTO `d_sys_region` VALUES ('1922', '228', '姜堰市', '3');
INSERT INTO `d_sys_region` VALUES ('1923', '229', '云龙区', '3');
INSERT INTO `d_sys_region` VALUES ('1924', '229', '鼓楼区', '3');
INSERT INTO `d_sys_region` VALUES ('1925', '229', '九里区', '3');
INSERT INTO `d_sys_region` VALUES ('1926', '229', '贾汪区', '3');
INSERT INTO `d_sys_region` VALUES ('1927', '229', '泉山区', '3');
INSERT INTO `d_sys_region` VALUES ('1928', '229', '新沂市', '3');
INSERT INTO `d_sys_region` VALUES ('1929', '229', '邳州市', '3');
INSERT INTO `d_sys_region` VALUES ('1930', '229', '丰县', '3');
INSERT INTO `d_sys_region` VALUES ('1931', '229', '沛县', '3');
INSERT INTO `d_sys_region` VALUES ('1932', '229', '铜山县', '3');
INSERT INTO `d_sys_region` VALUES ('1933', '229', '睢宁县', '3');
INSERT INTO `d_sys_region` VALUES ('1934', '230', '城区', '3');
INSERT INTO `d_sys_region` VALUES ('1935', '230', '亭湖区', '3');
INSERT INTO `d_sys_region` VALUES ('1936', '230', '盐都区', '3');
INSERT INTO `d_sys_region` VALUES ('1937', '230', '盐都县', '3');
INSERT INTO `d_sys_region` VALUES ('1938', '230', '东台市', '3');
INSERT INTO `d_sys_region` VALUES ('1939', '230', '大丰市', '3');
INSERT INTO `d_sys_region` VALUES ('1940', '230', '响水县', '3');
INSERT INTO `d_sys_region` VALUES ('1941', '230', '滨海县', '3');
INSERT INTO `d_sys_region` VALUES ('1942', '230', '阜宁县', '3');
INSERT INTO `d_sys_region` VALUES ('1943', '230', '射阳县', '3');
INSERT INTO `d_sys_region` VALUES ('1944', '230', '建湖县', '3');
INSERT INTO `d_sys_region` VALUES ('1945', '231', '广陵区', '3');
INSERT INTO `d_sys_region` VALUES ('1946', '231', '维扬区', '3');
INSERT INTO `d_sys_region` VALUES ('1947', '231', '邗江区', '3');
INSERT INTO `d_sys_region` VALUES ('1948', '231', '仪征市', '3');
INSERT INTO `d_sys_region` VALUES ('1949', '231', '高邮市', '3');
INSERT INTO `d_sys_region` VALUES ('1950', '231', '江都市', '3');
INSERT INTO `d_sys_region` VALUES ('1951', '231', '宝应县', '3');
INSERT INTO `d_sys_region` VALUES ('1952', '232', '京口区', '3');
INSERT INTO `d_sys_region` VALUES ('1953', '232', '润州区', '3');
INSERT INTO `d_sys_region` VALUES ('1954', '232', '丹徒区', '3');
INSERT INTO `d_sys_region` VALUES ('1955', '232', '丹阳市', '3');
INSERT INTO `d_sys_region` VALUES ('1956', '232', '扬中市', '3');
INSERT INTO `d_sys_region` VALUES ('1957', '232', '句容市', '3');
INSERT INTO `d_sys_region` VALUES ('1958', '233', '东湖区', '3');
INSERT INTO `d_sys_region` VALUES ('1959', '233', '西湖区', '3');
INSERT INTO `d_sys_region` VALUES ('1960', '233', '青云谱区', '3');
INSERT INTO `d_sys_region` VALUES ('1961', '233', '湾里区', '3');
INSERT INTO `d_sys_region` VALUES ('1962', '233', '青山湖区', '3');
INSERT INTO `d_sys_region` VALUES ('1963', '233', '红谷滩新区', '3');
INSERT INTO `d_sys_region` VALUES ('1964', '233', '昌北区', '3');
INSERT INTO `d_sys_region` VALUES ('1965', '233', '高新区', '3');
INSERT INTO `d_sys_region` VALUES ('1966', '233', '南昌县', '3');
INSERT INTO `d_sys_region` VALUES ('1967', '233', '新建县', '3');
INSERT INTO `d_sys_region` VALUES ('1968', '233', '安义县', '3');
INSERT INTO `d_sys_region` VALUES ('1969', '233', '进贤县', '3');
INSERT INTO `d_sys_region` VALUES ('1970', '234', '临川区', '3');
INSERT INTO `d_sys_region` VALUES ('1971', '234', '南城县', '3');
INSERT INTO `d_sys_region` VALUES ('1972', '234', '黎川县', '3');
INSERT INTO `d_sys_region` VALUES ('1973', '234', '南丰县', '3');
INSERT INTO `d_sys_region` VALUES ('1974', '234', '崇仁县', '3');
INSERT INTO `d_sys_region` VALUES ('1975', '234', '乐安县', '3');
INSERT INTO `d_sys_region` VALUES ('1976', '234', '宜黄县', '3');
INSERT INTO `d_sys_region` VALUES ('1977', '234', '金溪县', '3');
INSERT INTO `d_sys_region` VALUES ('1978', '234', '资溪县', '3');
INSERT INTO `d_sys_region` VALUES ('1979', '234', '东乡县', '3');
INSERT INTO `d_sys_region` VALUES ('1980', '234', '广昌县', '3');
INSERT INTO `d_sys_region` VALUES ('1981', '235', '章贡区', '3');
INSERT INTO `d_sys_region` VALUES ('1982', '235', '于都县', '3');
INSERT INTO `d_sys_region` VALUES ('1983', '235', '瑞金市', '3');
INSERT INTO `d_sys_region` VALUES ('1984', '235', '南康市', '3');
INSERT INTO `d_sys_region` VALUES ('1985', '235', '赣县', '3');
INSERT INTO `d_sys_region` VALUES ('1986', '235', '信丰县', '3');
INSERT INTO `d_sys_region` VALUES ('1987', '235', '大余县', '3');
INSERT INTO `d_sys_region` VALUES ('1988', '235', '上犹县', '3');
INSERT INTO `d_sys_region` VALUES ('1989', '235', '崇义县', '3');
INSERT INTO `d_sys_region` VALUES ('1990', '235', '安远县', '3');
INSERT INTO `d_sys_region` VALUES ('1991', '235', '龙南县', '3');
INSERT INTO `d_sys_region` VALUES ('1992', '235', '定南县', '3');
INSERT INTO `d_sys_region` VALUES ('1993', '235', '全南县', '3');
INSERT INTO `d_sys_region` VALUES ('1994', '235', '宁都县', '3');
INSERT INTO `d_sys_region` VALUES ('1995', '235', '兴国县', '3');
INSERT INTO `d_sys_region` VALUES ('1996', '235', '会昌县', '3');
INSERT INTO `d_sys_region` VALUES ('1997', '235', '寻乌县', '3');
INSERT INTO `d_sys_region` VALUES ('1998', '235', '石城县', '3');
INSERT INTO `d_sys_region` VALUES ('1999', '236', '安福县', '3');
INSERT INTO `d_sys_region` VALUES ('2000', '236', '吉州区', '3');
INSERT INTO `d_sys_region` VALUES ('2001', '236', '青原区', '3');
INSERT INTO `d_sys_region` VALUES ('2002', '236', '井冈山市', '3');
INSERT INTO `d_sys_region` VALUES ('2003', '236', '吉安县', '3');
INSERT INTO `d_sys_region` VALUES ('2004', '236', '吉水县', '3');
INSERT INTO `d_sys_region` VALUES ('2005', '236', '峡江县', '3');
INSERT INTO `d_sys_region` VALUES ('2006', '236', '新干县', '3');
INSERT INTO `d_sys_region` VALUES ('2007', '236', '永丰县', '3');
INSERT INTO `d_sys_region` VALUES ('2008', '236', '泰和县', '3');
INSERT INTO `d_sys_region` VALUES ('2009', '236', '遂川县', '3');
INSERT INTO `d_sys_region` VALUES ('2010', '236', '万安县', '3');
INSERT INTO `d_sys_region` VALUES ('2011', '236', '永新县', '3');
INSERT INTO `d_sys_region` VALUES ('2012', '237', '珠山区', '3');
INSERT INTO `d_sys_region` VALUES ('2013', '237', '昌江区', '3');
INSERT INTO `d_sys_region` VALUES ('2014', '237', '乐平市', '3');
INSERT INTO `d_sys_region` VALUES ('2015', '237', '浮梁县', '3');
INSERT INTO `d_sys_region` VALUES ('2016', '238', '浔阳区', '3');
INSERT INTO `d_sys_region` VALUES ('2017', '238', '庐山区', '3');
INSERT INTO `d_sys_region` VALUES ('2018', '238', '瑞昌市', '3');
INSERT INTO `d_sys_region` VALUES ('2019', '238', '九江县', '3');
INSERT INTO `d_sys_region` VALUES ('2020', '238', '武宁县', '3');
INSERT INTO `d_sys_region` VALUES ('2021', '238', '修水县', '3');
INSERT INTO `d_sys_region` VALUES ('2022', '238', '永修县', '3');
INSERT INTO `d_sys_region` VALUES ('2023', '238', '德安县', '3');
INSERT INTO `d_sys_region` VALUES ('2024', '238', '星子县', '3');
INSERT INTO `d_sys_region` VALUES ('2025', '238', '都昌县', '3');
INSERT INTO `d_sys_region` VALUES ('2026', '238', '湖口县', '3');
INSERT INTO `d_sys_region` VALUES ('2027', '238', '彭泽县', '3');
INSERT INTO `d_sys_region` VALUES ('2028', '239', '安源区', '3');
INSERT INTO `d_sys_region` VALUES ('2029', '239', '湘东区', '3');
INSERT INTO `d_sys_region` VALUES ('2030', '239', '莲花县', '3');
INSERT INTO `d_sys_region` VALUES ('2031', '239', '芦溪县', '3');
INSERT INTO `d_sys_region` VALUES ('2032', '239', '上栗县', '3');
INSERT INTO `d_sys_region` VALUES ('2033', '240', '信州区', '3');
INSERT INTO `d_sys_region` VALUES ('2034', '240', '德兴市', '3');
INSERT INTO `d_sys_region` VALUES ('2035', '240', '上饶县', '3');
INSERT INTO `d_sys_region` VALUES ('2036', '240', '广丰县', '3');
INSERT INTO `d_sys_region` VALUES ('2037', '240', '玉山县', '3');
INSERT INTO `d_sys_region` VALUES ('2038', '240', '铅山县', '3');
INSERT INTO `d_sys_region` VALUES ('2039', '240', '横峰县', '3');
INSERT INTO `d_sys_region` VALUES ('2040', '240', '弋阳县', '3');
INSERT INTO `d_sys_region` VALUES ('2041', '240', '余干县', '3');
INSERT INTO `d_sys_region` VALUES ('2042', '240', '波阳县', '3');
INSERT INTO `d_sys_region` VALUES ('2043', '240', '万年县', '3');
INSERT INTO `d_sys_region` VALUES ('2044', '240', '婺源县', '3');
INSERT INTO `d_sys_region` VALUES ('2045', '241', '渝水区', '3');
INSERT INTO `d_sys_region` VALUES ('2046', '241', '分宜县', '3');
INSERT INTO `d_sys_region` VALUES ('2047', '242', '袁州区', '3');
INSERT INTO `d_sys_region` VALUES ('2048', '242', '丰城市', '3');
INSERT INTO `d_sys_region` VALUES ('2049', '242', '樟树市', '3');
INSERT INTO `d_sys_region` VALUES ('2050', '242', '高安市', '3');
INSERT INTO `d_sys_region` VALUES ('2051', '242', '奉新县', '3');
INSERT INTO `d_sys_region` VALUES ('2052', '242', '万载县', '3');
INSERT INTO `d_sys_region` VALUES ('2053', '242', '上高县', '3');
INSERT INTO `d_sys_region` VALUES ('2054', '242', '宜丰县', '3');
INSERT INTO `d_sys_region` VALUES ('2055', '242', '靖安县', '3');
INSERT INTO `d_sys_region` VALUES ('2056', '242', '铜鼓县', '3');
INSERT INTO `d_sys_region` VALUES ('2057', '243', '月湖区', '3');
INSERT INTO `d_sys_region` VALUES ('2058', '243', '贵溪市', '3');
INSERT INTO `d_sys_region` VALUES ('2059', '243', '余江县', '3');
INSERT INTO `d_sys_region` VALUES ('2060', '244', '沈河区', '3');
INSERT INTO `d_sys_region` VALUES ('2061', '244', '皇姑区', '3');
INSERT INTO `d_sys_region` VALUES ('2062', '244', '和平区', '3');
INSERT INTO `d_sys_region` VALUES ('2063', '244', '大东区', '3');
INSERT INTO `d_sys_region` VALUES ('2064', '244', '铁西区', '3');
INSERT INTO `d_sys_region` VALUES ('2065', '244', '苏家屯区', '3');
INSERT INTO `d_sys_region` VALUES ('2066', '244', '东陵区', '3');
INSERT INTO `d_sys_region` VALUES ('2067', '244', '沈北新区', '3');
INSERT INTO `d_sys_region` VALUES ('2068', '244', '于洪区', '3');
INSERT INTO `d_sys_region` VALUES ('2069', '244', '浑南新区', '3');
INSERT INTO `d_sys_region` VALUES ('2070', '244', '新民市', '3');
INSERT INTO `d_sys_region` VALUES ('2071', '244', '辽中县', '3');
INSERT INTO `d_sys_region` VALUES ('2072', '244', '康平县', '3');
INSERT INTO `d_sys_region` VALUES ('2073', '244', '法库县', '3');
INSERT INTO `d_sys_region` VALUES ('2074', '245', '西岗区', '3');
INSERT INTO `d_sys_region` VALUES ('2075', '245', '中山区', '3');
INSERT INTO `d_sys_region` VALUES ('2076', '245', '沙河口区', '3');
INSERT INTO `d_sys_region` VALUES ('2077', '245', '甘井子区', '3');
INSERT INTO `d_sys_region` VALUES ('2078', '245', '旅顺口区', '3');
INSERT INTO `d_sys_region` VALUES ('2079', '245', '金州区', '3');
INSERT INTO `d_sys_region` VALUES ('2080', '245', '开发区', '3');
INSERT INTO `d_sys_region` VALUES ('2081', '245', '瓦房店市', '3');
INSERT INTO `d_sys_region` VALUES ('2082', '245', '普兰店市', '3');
INSERT INTO `d_sys_region` VALUES ('2083', '245', '庄河市', '3');
INSERT INTO `d_sys_region` VALUES ('2084', '245', '长海县', '3');
INSERT INTO `d_sys_region` VALUES ('2085', '246', '铁东区', '3');
INSERT INTO `d_sys_region` VALUES ('2086', '246', '铁西区', '3');
INSERT INTO `d_sys_region` VALUES ('2087', '246', '立山区', '3');
INSERT INTO `d_sys_region` VALUES ('2088', '246', '千山区', '3');
INSERT INTO `d_sys_region` VALUES ('2089', '246', '岫岩', '3');
INSERT INTO `d_sys_region` VALUES ('2090', '246', '海城市', '3');
INSERT INTO `d_sys_region` VALUES ('2091', '246', '台安县', '3');
INSERT INTO `d_sys_region` VALUES ('2092', '247', '本溪', '3');
INSERT INTO `d_sys_region` VALUES ('2093', '247', '平山区', '3');
INSERT INTO `d_sys_region` VALUES ('2094', '247', '明山区', '3');
INSERT INTO `d_sys_region` VALUES ('2095', '247', '溪湖区', '3');
INSERT INTO `d_sys_region` VALUES ('2096', '247', '南芬区', '3');
INSERT INTO `d_sys_region` VALUES ('2097', '247', '桓仁', '3');
INSERT INTO `d_sys_region` VALUES ('2098', '248', '双塔区', '3');
INSERT INTO `d_sys_region` VALUES ('2099', '248', '龙城区', '3');
INSERT INTO `d_sys_region` VALUES ('2100', '248', '喀喇沁左翼蒙古族自治县', '3');
INSERT INTO `d_sys_region` VALUES ('2101', '248', '北票市', '3');
INSERT INTO `d_sys_region` VALUES ('2102', '248', '凌源市', '3');
INSERT INTO `d_sys_region` VALUES ('2103', '248', '朝阳县', '3');
INSERT INTO `d_sys_region` VALUES ('2104', '248', '建平县', '3');
INSERT INTO `d_sys_region` VALUES ('2105', '249', '振兴区', '3');
INSERT INTO `d_sys_region` VALUES ('2106', '249', '元宝区', '3');
INSERT INTO `d_sys_region` VALUES ('2107', '249', '振安区', '3');
INSERT INTO `d_sys_region` VALUES ('2108', '249', '宽甸', '3');
INSERT INTO `d_sys_region` VALUES ('2109', '249', '东港市', '3');
INSERT INTO `d_sys_region` VALUES ('2110', '249', '凤城市', '3');
INSERT INTO `d_sys_region` VALUES ('2111', '250', '顺城区', '3');
INSERT INTO `d_sys_region` VALUES ('2112', '250', '新抚区', '3');
INSERT INTO `d_sys_region` VALUES ('2113', '250', '东洲区', '3');
INSERT INTO `d_sys_region` VALUES ('2114', '250', '望花区', '3');
INSERT INTO `d_sys_region` VALUES ('2115', '250', '清原', '3');
INSERT INTO `d_sys_region` VALUES ('2116', '250', '新宾', '3');
INSERT INTO `d_sys_region` VALUES ('2117', '250', '抚顺县', '3');
INSERT INTO `d_sys_region` VALUES ('2118', '251', '阜新', '3');
INSERT INTO `d_sys_region` VALUES ('2119', '251', '海州区', '3');
INSERT INTO `d_sys_region` VALUES ('2120', '251', '新邱区', '3');
INSERT INTO `d_sys_region` VALUES ('2121', '251', '太平区', '3');
INSERT INTO `d_sys_region` VALUES ('2122', '251', '清河门区', '3');
INSERT INTO `d_sys_region` VALUES ('2123', '251', '细河区', '3');
INSERT INTO `d_sys_region` VALUES ('2124', '251', '彰武县', '3');
INSERT INTO `d_sys_region` VALUES ('2125', '252', '龙港区', '3');
INSERT INTO `d_sys_region` VALUES ('2126', '252', '南票区', '3');
INSERT INTO `d_sys_region` VALUES ('2127', '252', '连山区', '3');
INSERT INTO `d_sys_region` VALUES ('2128', '252', '兴城市', '3');
INSERT INTO `d_sys_region` VALUES ('2129', '252', '绥中县', '3');
INSERT INTO `d_sys_region` VALUES ('2130', '252', '建昌县', '3');
INSERT INTO `d_sys_region` VALUES ('2131', '253', '太和区', '3');
INSERT INTO `d_sys_region` VALUES ('2132', '253', '古塔区', '3');
INSERT INTO `d_sys_region` VALUES ('2133', '253', '凌河区', '3');
INSERT INTO `d_sys_region` VALUES ('2134', '253', '凌海市', '3');
INSERT INTO `d_sys_region` VALUES ('2135', '253', '北镇市', '3');
INSERT INTO `d_sys_region` VALUES ('2136', '253', '黑山县', '3');
INSERT INTO `d_sys_region` VALUES ('2137', '253', '义县', '3');
INSERT INTO `d_sys_region` VALUES ('2138', '254', '白塔区', '3');
INSERT INTO `d_sys_region` VALUES ('2139', '254', '文圣区', '3');
INSERT INTO `d_sys_region` VALUES ('2140', '254', '宏伟区', '3');
INSERT INTO `d_sys_region` VALUES ('2141', '254', '太子河区', '3');
INSERT INTO `d_sys_region` VALUES ('2142', '254', '弓长岭区', '3');
INSERT INTO `d_sys_region` VALUES ('2143', '254', '灯塔市', '3');
INSERT INTO `d_sys_region` VALUES ('2144', '254', '辽阳县', '3');
INSERT INTO `d_sys_region` VALUES ('2145', '255', '双台子区', '3');
INSERT INTO `d_sys_region` VALUES ('2146', '255', '兴隆台区', '3');
INSERT INTO `d_sys_region` VALUES ('2147', '255', '大洼县', '3');
INSERT INTO `d_sys_region` VALUES ('2148', '255', '盘山县', '3');
INSERT INTO `d_sys_region` VALUES ('2149', '256', '银州区', '3');
INSERT INTO `d_sys_region` VALUES ('2150', '256', '清河区', '3');
INSERT INTO `d_sys_region` VALUES ('2151', '256', '调兵山市', '3');
INSERT INTO `d_sys_region` VALUES ('2152', '256', '开原市', '3');
INSERT INTO `d_sys_region` VALUES ('2153', '256', '铁岭县', '3');
INSERT INTO `d_sys_region` VALUES ('2154', '256', '西丰县', '3');
INSERT INTO `d_sys_region` VALUES ('2155', '256', '昌图县', '3');
INSERT INTO `d_sys_region` VALUES ('2156', '257', '站前区', '3');
INSERT INTO `d_sys_region` VALUES ('2157', '257', '西市区', '3');
INSERT INTO `d_sys_region` VALUES ('2158', '257', '鲅鱼圈区', '3');
INSERT INTO `d_sys_region` VALUES ('2159', '257', '老边区', '3');
INSERT INTO `d_sys_region` VALUES ('2160', '257', '盖州市', '3');
INSERT INTO `d_sys_region` VALUES ('2161', '257', '大石桥市', '3');
INSERT INTO `d_sys_region` VALUES ('2162', '258', '回民区', '3');
INSERT INTO `d_sys_region` VALUES ('2163', '258', '玉泉区', '3');
INSERT INTO `d_sys_region` VALUES ('2164', '258', '新城区', '3');
INSERT INTO `d_sys_region` VALUES ('2165', '258', '赛罕区', '3');
INSERT INTO `d_sys_region` VALUES ('2166', '258', '清水河县', '3');
INSERT INTO `d_sys_region` VALUES ('2167', '258', '土默特左旗', '3');
INSERT INTO `d_sys_region` VALUES ('2168', '258', '托克托县', '3');
INSERT INTO `d_sys_region` VALUES ('2169', '258', '和林格尔县', '3');
INSERT INTO `d_sys_region` VALUES ('2170', '258', '武川县', '3');
INSERT INTO `d_sys_region` VALUES ('2171', '259', '阿拉善左旗', '3');
INSERT INTO `d_sys_region` VALUES ('2172', '259', '阿拉善右旗', '3');
INSERT INTO `d_sys_region` VALUES ('2173', '259', '额济纳旗', '3');
INSERT INTO `d_sys_region` VALUES ('2174', '260', '临河区', '3');
INSERT INTO `d_sys_region` VALUES ('2175', '260', '五原县', '3');
INSERT INTO `d_sys_region` VALUES ('2176', '260', '磴口县', '3');
INSERT INTO `d_sys_region` VALUES ('2177', '260', '乌拉特前旗', '3');
INSERT INTO `d_sys_region` VALUES ('2178', '260', '乌拉特中旗', '3');
INSERT INTO `d_sys_region` VALUES ('2179', '260', '乌拉特后旗', '3');
INSERT INTO `d_sys_region` VALUES ('2180', '260', '杭锦后旗', '3');
INSERT INTO `d_sys_region` VALUES ('2181', '261', '昆都仑区', '3');
INSERT INTO `d_sys_region` VALUES ('2182', '261', '青山区', '3');
INSERT INTO `d_sys_region` VALUES ('2183', '261', '东河区', '3');
INSERT INTO `d_sys_region` VALUES ('2184', '261', '九原区', '3');
INSERT INTO `d_sys_region` VALUES ('2185', '261', '石拐区', '3');
INSERT INTO `d_sys_region` VALUES ('2186', '261', '白云矿区', '3');
INSERT INTO `d_sys_region` VALUES ('2187', '261', '土默特右旗', '3');
INSERT INTO `d_sys_region` VALUES ('2188', '261', '固阳县', '3');
INSERT INTO `d_sys_region` VALUES ('2189', '261', '达尔罕茂明安联合旗', '3');
INSERT INTO `d_sys_region` VALUES ('2190', '262', '红山区', '3');
INSERT INTO `d_sys_region` VALUES ('2191', '262', '元宝山区', '3');
INSERT INTO `d_sys_region` VALUES ('2192', '262', '松山区', '3');
INSERT INTO `d_sys_region` VALUES ('2193', '262', '阿鲁科尔沁旗', '3');
INSERT INTO `d_sys_region` VALUES ('2194', '262', '巴林左旗', '3');
INSERT INTO `d_sys_region` VALUES ('2195', '262', '巴林右旗', '3');
INSERT INTO `d_sys_region` VALUES ('2196', '262', '林西县', '3');
INSERT INTO `d_sys_region` VALUES ('2197', '262', '克什克腾旗', '3');
INSERT INTO `d_sys_region` VALUES ('2198', '262', '翁牛特旗', '3');
INSERT INTO `d_sys_region` VALUES ('2199', '262', '喀喇沁旗', '3');
INSERT INTO `d_sys_region` VALUES ('2200', '262', '宁城县', '3');
INSERT INTO `d_sys_region` VALUES ('2201', '262', '敖汉旗', '3');
INSERT INTO `d_sys_region` VALUES ('2202', '263', '东胜区', '3');
INSERT INTO `d_sys_region` VALUES ('2203', '263', '达拉特旗', '3');
INSERT INTO `d_sys_region` VALUES ('2204', '263', '准格尔旗', '3');
INSERT INTO `d_sys_region` VALUES ('2205', '263', '鄂托克前旗', '3');
INSERT INTO `d_sys_region` VALUES ('2206', '263', '鄂托克旗', '3');
INSERT INTO `d_sys_region` VALUES ('2207', '263', '杭锦旗', '3');
INSERT INTO `d_sys_region` VALUES ('2208', '263', '乌审旗', '3');
INSERT INTO `d_sys_region` VALUES ('2209', '263', '伊金霍洛旗', '3');
INSERT INTO `d_sys_region` VALUES ('2210', '264', '海拉尔区', '3');
INSERT INTO `d_sys_region` VALUES ('2211', '264', '莫力达瓦', '3');
INSERT INTO `d_sys_region` VALUES ('2212', '264', '满洲里市', '3');
INSERT INTO `d_sys_region` VALUES ('2213', '264', '牙克石市', '3');
INSERT INTO `d_sys_region` VALUES ('2214', '264', '扎兰屯市', '3');
INSERT INTO `d_sys_region` VALUES ('2215', '264', '额尔古纳市', '3');
INSERT INTO `d_sys_region` VALUES ('2216', '264', '根河市', '3');
INSERT INTO `d_sys_region` VALUES ('2217', '264', '阿荣旗', '3');
INSERT INTO `d_sys_region` VALUES ('2218', '264', '鄂伦春自治旗', '3');
INSERT INTO `d_sys_region` VALUES ('2219', '264', '鄂温克族自治旗', '3');
INSERT INTO `d_sys_region` VALUES ('2220', '264', '陈巴尔虎旗', '3');
INSERT INTO `d_sys_region` VALUES ('2221', '264', '新巴尔虎左旗', '3');
INSERT INTO `d_sys_region` VALUES ('2222', '264', '新巴尔虎右旗', '3');
INSERT INTO `d_sys_region` VALUES ('2223', '265', '科尔沁区', '3');
INSERT INTO `d_sys_region` VALUES ('2224', '265', '霍林郭勒市', '3');
INSERT INTO `d_sys_region` VALUES ('2225', '265', '科尔沁左翼中旗', '3');
INSERT INTO `d_sys_region` VALUES ('2226', '265', '科尔沁左翼后旗', '3');
INSERT INTO `d_sys_region` VALUES ('2227', '265', '开鲁县', '3');
INSERT INTO `d_sys_region` VALUES ('2228', '265', '库伦旗', '3');
INSERT INTO `d_sys_region` VALUES ('2229', '265', '奈曼旗', '3');
INSERT INTO `d_sys_region` VALUES ('2230', '265', '扎鲁特旗', '3');
INSERT INTO `d_sys_region` VALUES ('2231', '266', '海勃湾区', '3');
INSERT INTO `d_sys_region` VALUES ('2232', '266', '乌达区', '3');
INSERT INTO `d_sys_region` VALUES ('2233', '266', '海南区', '3');
INSERT INTO `d_sys_region` VALUES ('2234', '267', '化德县', '3');
INSERT INTO `d_sys_region` VALUES ('2235', '267', '集宁区', '3');
INSERT INTO `d_sys_region` VALUES ('2236', '267', '丰镇市', '3');
INSERT INTO `d_sys_region` VALUES ('2237', '267', '卓资县', '3');
INSERT INTO `d_sys_region` VALUES ('2238', '267', '商都县', '3');
INSERT INTO `d_sys_region` VALUES ('2239', '267', '兴和县', '3');
INSERT INTO `d_sys_region` VALUES ('2240', '267', '凉城县', '3');
INSERT INTO `d_sys_region` VALUES ('2241', '267', '察哈尔右翼前旗', '3');
INSERT INTO `d_sys_region` VALUES ('2242', '267', '察哈尔右翼中旗', '3');
INSERT INTO `d_sys_region` VALUES ('2243', '267', '察哈尔右翼后旗', '3');
INSERT INTO `d_sys_region` VALUES ('2244', '267', '四子王旗', '3');
INSERT INTO `d_sys_region` VALUES ('2245', '268', '二连浩特市', '3');
INSERT INTO `d_sys_region` VALUES ('2246', '268', '锡林浩特市', '3');
INSERT INTO `d_sys_region` VALUES ('2247', '268', '阿巴嘎旗', '3');
INSERT INTO `d_sys_region` VALUES ('2248', '268', '苏尼特左旗', '3');
INSERT INTO `d_sys_region` VALUES ('2249', '268', '苏尼特右旗', '3');
INSERT INTO `d_sys_region` VALUES ('2250', '268', '东乌珠穆沁旗', '3');
INSERT INTO `d_sys_region` VALUES ('2251', '268', '西乌珠穆沁旗', '3');
INSERT INTO `d_sys_region` VALUES ('2252', '268', '太仆寺旗', '3');
INSERT INTO `d_sys_region` VALUES ('2253', '268', '镶黄旗', '3');
INSERT INTO `d_sys_region` VALUES ('2254', '268', '正镶白旗', '3');
INSERT INTO `d_sys_region` VALUES ('2255', '268', '正蓝旗', '3');
INSERT INTO `d_sys_region` VALUES ('2256', '268', '多伦县', '3');
INSERT INTO `d_sys_region` VALUES ('2257', '269', '乌兰浩特市', '3');
INSERT INTO `d_sys_region` VALUES ('2258', '269', '阿尔山市', '3');
INSERT INTO `d_sys_region` VALUES ('2259', '269', '科尔沁右翼前旗', '3');
INSERT INTO `d_sys_region` VALUES ('2260', '269', '科尔沁右翼中旗', '3');
INSERT INTO `d_sys_region` VALUES ('2261', '269', '扎赉特旗', '3');
INSERT INTO `d_sys_region` VALUES ('2262', '269', '突泉县', '3');
INSERT INTO `d_sys_region` VALUES ('2263', '270', '西夏区', '3');
INSERT INTO `d_sys_region` VALUES ('2264', '270', '金凤区', '3');
INSERT INTO `d_sys_region` VALUES ('2265', '270', '兴庆区', '3');
INSERT INTO `d_sys_region` VALUES ('2266', '270', '灵武市', '3');
INSERT INTO `d_sys_region` VALUES ('2267', '270', '永宁县', '3');
INSERT INTO `d_sys_region` VALUES ('2268', '270', '贺兰县', '3');
INSERT INTO `d_sys_region` VALUES ('2269', '271', '原州区', '3');
INSERT INTO `d_sys_region` VALUES ('2270', '271', '海原县', '3');
INSERT INTO `d_sys_region` VALUES ('2271', '271', '西吉县', '3');
INSERT INTO `d_sys_region` VALUES ('2272', '271', '隆德县', '3');
INSERT INTO `d_sys_region` VALUES ('2273', '271', '泾源县', '3');
INSERT INTO `d_sys_region` VALUES ('2274', '271', '彭阳县', '3');
INSERT INTO `d_sys_region` VALUES ('2275', '272', '惠农县', '3');
INSERT INTO `d_sys_region` VALUES ('2276', '272', '大武口区', '3');
INSERT INTO `d_sys_region` VALUES ('2277', '272', '惠农区', '3');
INSERT INTO `d_sys_region` VALUES ('2278', '272', '陶乐县', '3');
INSERT INTO `d_sys_region` VALUES ('2279', '272', '平罗县', '3');
INSERT INTO `d_sys_region` VALUES ('2280', '273', '利通区', '3');
INSERT INTO `d_sys_region` VALUES ('2281', '273', '中卫县', '3');
INSERT INTO `d_sys_region` VALUES ('2282', '273', '青铜峡市', '3');
INSERT INTO `d_sys_region` VALUES ('2283', '273', '中宁县', '3');
INSERT INTO `d_sys_region` VALUES ('2284', '273', '盐池县', '3');
INSERT INTO `d_sys_region` VALUES ('2285', '273', '同心县', '3');
INSERT INTO `d_sys_region` VALUES ('2286', '274', '沙坡头区', '3');
INSERT INTO `d_sys_region` VALUES ('2287', '274', '海原县', '3');
INSERT INTO `d_sys_region` VALUES ('2288', '274', '中宁县', '3');
INSERT INTO `d_sys_region` VALUES ('2289', '275', '城中区', '3');
INSERT INTO `d_sys_region` VALUES ('2290', '275', '城东区', '3');
INSERT INTO `d_sys_region` VALUES ('2291', '275', '城西区', '3');
INSERT INTO `d_sys_region` VALUES ('2292', '275', '城北区', '3');
INSERT INTO `d_sys_region` VALUES ('2293', '275', '湟中县', '3');
INSERT INTO `d_sys_region` VALUES ('2294', '275', '湟源县', '3');
INSERT INTO `d_sys_region` VALUES ('2295', '275', '大通', '3');
INSERT INTO `d_sys_region` VALUES ('2296', '276', '玛沁县', '3');
INSERT INTO `d_sys_region` VALUES ('2297', '276', '班玛县', '3');
INSERT INTO `d_sys_region` VALUES ('2298', '276', '甘德县', '3');
INSERT INTO `d_sys_region` VALUES ('2299', '276', '达日县', '3');
INSERT INTO `d_sys_region` VALUES ('2300', '276', '久治县', '3');
INSERT INTO `d_sys_region` VALUES ('2301', '276', '玛多县', '3');
INSERT INTO `d_sys_region` VALUES ('2302', '277', '海晏县', '3');
INSERT INTO `d_sys_region` VALUES ('2303', '277', '祁连县', '3');
INSERT INTO `d_sys_region` VALUES ('2304', '277', '刚察县', '3');
INSERT INTO `d_sys_region` VALUES ('2305', '277', '门源', '3');
INSERT INTO `d_sys_region` VALUES ('2306', '278', '平安县', '3');
INSERT INTO `d_sys_region` VALUES ('2307', '278', '乐都县', '3');
INSERT INTO `d_sys_region` VALUES ('2308', '278', '民和', '3');
INSERT INTO `d_sys_region` VALUES ('2309', '278', '互助', '3');
INSERT INTO `d_sys_region` VALUES ('2310', '278', '化隆', '3');
INSERT INTO `d_sys_region` VALUES ('2311', '278', '循化', '3');
INSERT INTO `d_sys_region` VALUES ('2312', '279', '共和县', '3');
INSERT INTO `d_sys_region` VALUES ('2313', '279', '同德县', '3');
INSERT INTO `d_sys_region` VALUES ('2314', '279', '贵德县', '3');
INSERT INTO `d_sys_region` VALUES ('2315', '279', '兴海县', '3');
INSERT INTO `d_sys_region` VALUES ('2316', '279', '贵南县', '3');
INSERT INTO `d_sys_region` VALUES ('2317', '280', '德令哈市', '3');
INSERT INTO `d_sys_region` VALUES ('2318', '280', '格尔木市', '3');
INSERT INTO `d_sys_region` VALUES ('2319', '280', '乌兰县', '3');
INSERT INTO `d_sys_region` VALUES ('2320', '280', '都兰县', '3');
INSERT INTO `d_sys_region` VALUES ('2321', '280', '天峻县', '3');
INSERT INTO `d_sys_region` VALUES ('2322', '281', '同仁县', '3');
INSERT INTO `d_sys_region` VALUES ('2323', '281', '尖扎县', '3');
INSERT INTO `d_sys_region` VALUES ('2324', '281', '泽库县', '3');
INSERT INTO `d_sys_region` VALUES ('2325', '281', '河南蒙古族自治县', '3');
INSERT INTO `d_sys_region` VALUES ('2326', '282', '玉树县', '3');
INSERT INTO `d_sys_region` VALUES ('2327', '282', '杂多县', '3');
INSERT INTO `d_sys_region` VALUES ('2328', '282', '称多县', '3');
INSERT INTO `d_sys_region` VALUES ('2329', '282', '治多县', '3');
INSERT INTO `d_sys_region` VALUES ('2330', '282', '囊谦县', '3');
INSERT INTO `d_sys_region` VALUES ('2331', '282', '曲麻莱县', '3');
INSERT INTO `d_sys_region` VALUES ('2332', '283', '市中区', '3');
INSERT INTO `d_sys_region` VALUES ('2333', '283', '历下区', '3');
INSERT INTO `d_sys_region` VALUES ('2334', '283', '天桥区', '3');
INSERT INTO `d_sys_region` VALUES ('2335', '283', '槐荫区', '3');
INSERT INTO `d_sys_region` VALUES ('2336', '283', '历城区', '3');
INSERT INTO `d_sys_region` VALUES ('2337', '283', '长清区', '3');
INSERT INTO `d_sys_region` VALUES ('2338', '283', '章丘市', '3');
INSERT INTO `d_sys_region` VALUES ('2339', '283', '平阴县', '3');
INSERT INTO `d_sys_region` VALUES ('2340', '283', '济阳县', '3');
INSERT INTO `d_sys_region` VALUES ('2341', '283', '商河县', '3');
INSERT INTO `d_sys_region` VALUES ('2342', '284', '市南区', '3');
INSERT INTO `d_sys_region` VALUES ('2343', '284', '市北区', '3');
INSERT INTO `d_sys_region` VALUES ('2344', '284', '城阳区', '3');
INSERT INTO `d_sys_region` VALUES ('2345', '284', '四方区', '3');
INSERT INTO `d_sys_region` VALUES ('2346', '284', '李沧区', '3');
INSERT INTO `d_sys_region` VALUES ('2347', '284', '黄岛区', '3');
INSERT INTO `d_sys_region` VALUES ('2348', '284', '崂山区', '3');
INSERT INTO `d_sys_region` VALUES ('2349', '284', '胶州市', '3');
INSERT INTO `d_sys_region` VALUES ('2350', '284', '即墨市', '3');
INSERT INTO `d_sys_region` VALUES ('2351', '284', '平度市', '3');
INSERT INTO `d_sys_region` VALUES ('2352', '284', '胶南市', '3');
INSERT INTO `d_sys_region` VALUES ('2353', '284', '莱西市', '3');
INSERT INTO `d_sys_region` VALUES ('2354', '285', '滨城区', '3');
INSERT INTO `d_sys_region` VALUES ('2355', '285', '惠民县', '3');
INSERT INTO `d_sys_region` VALUES ('2356', '285', '阳信县', '3');
INSERT INTO `d_sys_region` VALUES ('2357', '285', '无棣县', '3');
INSERT INTO `d_sys_region` VALUES ('2358', '285', '沾化县', '3');
INSERT INTO `d_sys_region` VALUES ('2359', '285', '博兴县', '3');
INSERT INTO `d_sys_region` VALUES ('2360', '285', '邹平县', '3');
INSERT INTO `d_sys_region` VALUES ('2361', '286', '德城区', '3');
INSERT INTO `d_sys_region` VALUES ('2362', '286', '陵县', '3');
INSERT INTO `d_sys_region` VALUES ('2363', '286', '乐陵市', '3');
INSERT INTO `d_sys_region` VALUES ('2364', '286', '禹城市', '3');
INSERT INTO `d_sys_region` VALUES ('2365', '286', '宁津县', '3');
INSERT INTO `d_sys_region` VALUES ('2366', '286', '庆云县', '3');
INSERT INTO `d_sys_region` VALUES ('2367', '286', '临邑县', '3');
INSERT INTO `d_sys_region` VALUES ('2368', '286', '齐河县', '3');
INSERT INTO `d_sys_region` VALUES ('2369', '286', '平原县', '3');
INSERT INTO `d_sys_region` VALUES ('2370', '286', '夏津县', '3');
INSERT INTO `d_sys_region` VALUES ('2371', '286', '武城县', '3');
INSERT INTO `d_sys_region` VALUES ('2372', '287', '东营区', '3');
INSERT INTO `d_sys_region` VALUES ('2373', '287', '河口区', '3');
INSERT INTO `d_sys_region` VALUES ('2374', '287', '垦利县', '3');
INSERT INTO `d_sys_region` VALUES ('2375', '287', '利津县', '3');
INSERT INTO `d_sys_region` VALUES ('2376', '287', '广饶县', '3');
INSERT INTO `d_sys_region` VALUES ('2377', '288', '牡丹区', '3');
INSERT INTO `d_sys_region` VALUES ('2378', '288', '曹县', '3');
INSERT INTO `d_sys_region` VALUES ('2379', '288', '单县', '3');
INSERT INTO `d_sys_region` VALUES ('2380', '288', '成武县', '3');
INSERT INTO `d_sys_region` VALUES ('2381', '288', '巨野县', '3');
INSERT INTO `d_sys_region` VALUES ('2382', '288', '郓城县', '3');
INSERT INTO `d_sys_region` VALUES ('2383', '288', '鄄城县', '3');
INSERT INTO `d_sys_region` VALUES ('2384', '288', '定陶县', '3');
INSERT INTO `d_sys_region` VALUES ('2385', '288', '东明县', '3');
INSERT INTO `d_sys_region` VALUES ('2386', '289', '市中区', '3');
INSERT INTO `d_sys_region` VALUES ('2387', '289', '任城区', '3');
INSERT INTO `d_sys_region` VALUES ('2388', '289', '曲阜市', '3');
INSERT INTO `d_sys_region` VALUES ('2389', '289', '兖州市', '3');
INSERT INTO `d_sys_region` VALUES ('2390', '289', '邹城市', '3');
INSERT INTO `d_sys_region` VALUES ('2391', '289', '微山县', '3');
INSERT INTO `d_sys_region` VALUES ('2392', '289', '鱼台县', '3');
INSERT INTO `d_sys_region` VALUES ('2393', '289', '金乡县', '3');
INSERT INTO `d_sys_region` VALUES ('2394', '289', '嘉祥县', '3');
INSERT INTO `d_sys_region` VALUES ('2395', '289', '汶上县', '3');
INSERT INTO `d_sys_region` VALUES ('2396', '289', '泗水县', '3');
INSERT INTO `d_sys_region` VALUES ('2397', '289', '梁山县', '3');
INSERT INTO `d_sys_region` VALUES ('2398', '290', '莱城区', '3');
INSERT INTO `d_sys_region` VALUES ('2399', '290', '钢城区', '3');
INSERT INTO `d_sys_region` VALUES ('2400', '291', '东昌府区', '3');
INSERT INTO `d_sys_region` VALUES ('2401', '291', '临清市', '3');
INSERT INTO `d_sys_region` VALUES ('2402', '291', '阳谷县', '3');
INSERT INTO `d_sys_region` VALUES ('2403', '291', '莘县', '3');
INSERT INTO `d_sys_region` VALUES ('2404', '291', '茌平县', '3');
INSERT INTO `d_sys_region` VALUES ('2405', '291', '东阿县', '3');
INSERT INTO `d_sys_region` VALUES ('2406', '291', '冠县', '3');
INSERT INTO `d_sys_region` VALUES ('2407', '291', '高唐县', '3');
INSERT INTO `d_sys_region` VALUES ('2408', '292', '兰山区', '3');
INSERT INTO `d_sys_region` VALUES ('2409', '292', '罗庄区', '3');
INSERT INTO `d_sys_region` VALUES ('2410', '292', '河东区', '3');
INSERT INTO `d_sys_region` VALUES ('2411', '292', '沂南县', '3');
INSERT INTO `d_sys_region` VALUES ('2412', '292', '郯城县', '3');
INSERT INTO `d_sys_region` VALUES ('2413', '292', '沂水县', '3');
INSERT INTO `d_sys_region` VALUES ('2414', '292', '苍山县', '3');
INSERT INTO `d_sys_region` VALUES ('2415', '292', '费县', '3');
INSERT INTO `d_sys_region` VALUES ('2416', '292', '平邑县', '3');
INSERT INTO `d_sys_region` VALUES ('2417', '292', '莒南县', '3');
INSERT INTO `d_sys_region` VALUES ('2418', '292', '蒙阴县', '3');
INSERT INTO `d_sys_region` VALUES ('2419', '292', '临沭县', '3');
INSERT INTO `d_sys_region` VALUES ('2420', '293', '东港区', '3');
INSERT INTO `d_sys_region` VALUES ('2421', '293', '岚山区', '3');
INSERT INTO `d_sys_region` VALUES ('2422', '293', '五莲县', '3');
INSERT INTO `d_sys_region` VALUES ('2423', '293', '莒县', '3');
INSERT INTO `d_sys_region` VALUES ('2424', '294', '泰山区', '3');
INSERT INTO `d_sys_region` VALUES ('2425', '294', '岱岳区', '3');
INSERT INTO `d_sys_region` VALUES ('2426', '294', '新泰市', '3');
INSERT INTO `d_sys_region` VALUES ('2427', '294', '肥城市', '3');
INSERT INTO `d_sys_region` VALUES ('2428', '294', '宁阳县', '3');
INSERT INTO `d_sys_region` VALUES ('2429', '294', '东平县', '3');
INSERT INTO `d_sys_region` VALUES ('2430', '295', '荣成市', '3');
INSERT INTO `d_sys_region` VALUES ('2431', '295', '乳山市', '3');
INSERT INTO `d_sys_region` VALUES ('2432', '295', '环翠区', '3');
INSERT INTO `d_sys_region` VALUES ('2433', '295', '文登市', '3');
INSERT INTO `d_sys_region` VALUES ('2434', '296', '潍城区', '3');
INSERT INTO `d_sys_region` VALUES ('2435', '296', '寒亭区', '3');
INSERT INTO `d_sys_region` VALUES ('2436', '296', '坊子区', '3');
INSERT INTO `d_sys_region` VALUES ('2437', '296', '奎文区', '3');
INSERT INTO `d_sys_region` VALUES ('2438', '296', '青州市', '3');
INSERT INTO `d_sys_region` VALUES ('2439', '296', '诸城市', '3');
INSERT INTO `d_sys_region` VALUES ('2440', '296', '寿光市', '3');
INSERT INTO `d_sys_region` VALUES ('2441', '296', '安丘市', '3');
INSERT INTO `d_sys_region` VALUES ('2442', '296', '高密市', '3');
INSERT INTO `d_sys_region` VALUES ('2443', '296', '昌邑市', '3');
INSERT INTO `d_sys_region` VALUES ('2444', '296', '临朐县', '3');
INSERT INTO `d_sys_region` VALUES ('2445', '296', '昌乐县', '3');
INSERT INTO `d_sys_region` VALUES ('2446', '297', '芝罘区', '3');
INSERT INTO `d_sys_region` VALUES ('2447', '297', '福山区', '3');
INSERT INTO `d_sys_region` VALUES ('2448', '297', '牟平区', '3');
INSERT INTO `d_sys_region` VALUES ('2449', '297', '莱山区', '3');
INSERT INTO `d_sys_region` VALUES ('2450', '297', '开发区', '3');
INSERT INTO `d_sys_region` VALUES ('2451', '297', '龙口市', '3');
INSERT INTO `d_sys_region` VALUES ('2452', '297', '莱阳市', '3');
INSERT INTO `d_sys_region` VALUES ('2453', '297', '莱州市', '3');
INSERT INTO `d_sys_region` VALUES ('2454', '297', '蓬莱市', '3');
INSERT INTO `d_sys_region` VALUES ('2455', '297', '招远市', '3');
INSERT INTO `d_sys_region` VALUES ('2456', '297', '栖霞市', '3');
INSERT INTO `d_sys_region` VALUES ('2457', '297', '海阳市', '3');
INSERT INTO `d_sys_region` VALUES ('2458', '297', '长岛县', '3');
INSERT INTO `d_sys_region` VALUES ('2459', '298', '市中区', '3');
INSERT INTO `d_sys_region` VALUES ('2460', '298', '山亭区', '3');
INSERT INTO `d_sys_region` VALUES ('2461', '298', '峄城区', '3');
INSERT INTO `d_sys_region` VALUES ('2462', '298', '台儿庄区', '3');
INSERT INTO `d_sys_region` VALUES ('2463', '298', '薛城区', '3');
INSERT INTO `d_sys_region` VALUES ('2464', '298', '滕州市', '3');
INSERT INTO `d_sys_region` VALUES ('2465', '299', '张店区', '3');
INSERT INTO `d_sys_region` VALUES ('2466', '299', '临淄区', '3');
INSERT INTO `d_sys_region` VALUES ('2467', '299', '淄川区', '3');
INSERT INTO `d_sys_region` VALUES ('2468', '299', '博山区', '3');
INSERT INTO `d_sys_region` VALUES ('2469', '299', '周村区', '3');
INSERT INTO `d_sys_region` VALUES ('2470', '299', '桓台县', '3');
INSERT INTO `d_sys_region` VALUES ('2471', '299', '高青县', '3');
INSERT INTO `d_sys_region` VALUES ('2472', '299', '沂源县', '3');
INSERT INTO `d_sys_region` VALUES ('2473', '300', '杏花岭区', '3');
INSERT INTO `d_sys_region` VALUES ('2474', '300', '小店区', '3');
INSERT INTO `d_sys_region` VALUES ('2475', '300', '迎泽区', '3');
INSERT INTO `d_sys_region` VALUES ('2476', '300', '尖草坪区', '3');
INSERT INTO `d_sys_region` VALUES ('2477', '300', '万柏林区', '3');
INSERT INTO `d_sys_region` VALUES ('2478', '300', '晋源区', '3');
INSERT INTO `d_sys_region` VALUES ('2479', '300', '高新开发区', '3');
INSERT INTO `d_sys_region` VALUES ('2480', '300', '民营经济开发区', '3');
INSERT INTO `d_sys_region` VALUES ('2481', '300', '经济技术开发区', '3');
INSERT INTO `d_sys_region` VALUES ('2482', '300', '清徐县', '3');
INSERT INTO `d_sys_region` VALUES ('2483', '300', '阳曲县', '3');
INSERT INTO `d_sys_region` VALUES ('2484', '300', '娄烦县', '3');
INSERT INTO `d_sys_region` VALUES ('2485', '300', '古交市', '3');
INSERT INTO `d_sys_region` VALUES ('2486', '301', '城区', '3');
INSERT INTO `d_sys_region` VALUES ('2487', '301', '郊区', '3');
INSERT INTO `d_sys_region` VALUES ('2488', '301', '沁县', '3');
INSERT INTO `d_sys_region` VALUES ('2489', '301', '潞城市', '3');
INSERT INTO `d_sys_region` VALUES ('2490', '301', '长治县', '3');
INSERT INTO `d_sys_region` VALUES ('2491', '301', '襄垣县', '3');
INSERT INTO `d_sys_region` VALUES ('2492', '301', '屯留县', '3');
INSERT INTO `d_sys_region` VALUES ('2493', '301', '平顺县', '3');
INSERT INTO `d_sys_region` VALUES ('2494', '301', '黎城县', '3');
INSERT INTO `d_sys_region` VALUES ('2495', '301', '壶关县', '3');
INSERT INTO `d_sys_region` VALUES ('2496', '301', '长子县', '3');
INSERT INTO `d_sys_region` VALUES ('2497', '301', '武乡县', '3');
INSERT INTO `d_sys_region` VALUES ('2498', '301', '沁源县', '3');
INSERT INTO `d_sys_region` VALUES ('2499', '302', '城区', '3');
INSERT INTO `d_sys_region` VALUES ('2500', '302', '矿区', '3');
INSERT INTO `d_sys_region` VALUES ('2501', '302', '南郊区', '3');
INSERT INTO `d_sys_region` VALUES ('2502', '302', '新荣区', '3');
INSERT INTO `d_sys_region` VALUES ('2503', '302', '阳高县', '3');
INSERT INTO `d_sys_region` VALUES ('2504', '302', '天镇县', '3');
INSERT INTO `d_sys_region` VALUES ('2505', '302', '广灵县', '3');
INSERT INTO `d_sys_region` VALUES ('2506', '302', '灵丘县', '3');
INSERT INTO `d_sys_region` VALUES ('2507', '302', '浑源县', '3');
INSERT INTO `d_sys_region` VALUES ('2508', '302', '左云县', '3');
INSERT INTO `d_sys_region` VALUES ('2509', '302', '大同县', '3');
INSERT INTO `d_sys_region` VALUES ('2510', '303', '城区', '3');
INSERT INTO `d_sys_region` VALUES ('2511', '303', '高平市', '3');
INSERT INTO `d_sys_region` VALUES ('2512', '303', '沁水县', '3');
INSERT INTO `d_sys_region` VALUES ('2513', '303', '阳城县', '3');
INSERT INTO `d_sys_region` VALUES ('2514', '303', '陵川县', '3');
INSERT INTO `d_sys_region` VALUES ('2515', '303', '泽州县', '3');
INSERT INTO `d_sys_region` VALUES ('2516', '304', '榆次区', '3');
INSERT INTO `d_sys_region` VALUES ('2517', '304', '介休市', '3');
INSERT INTO `d_sys_region` VALUES ('2518', '304', '榆社县', '3');
INSERT INTO `d_sys_region` VALUES ('2519', '304', '左权县', '3');
INSERT INTO `d_sys_region` VALUES ('2520', '304', '和顺县', '3');
INSERT INTO `d_sys_region` VALUES ('2521', '304', '昔阳县', '3');
INSERT INTO `d_sys_region` VALUES ('2522', '304', '寿阳县', '3');
INSERT INTO `d_sys_region` VALUES ('2523', '304', '太谷县', '3');
INSERT INTO `d_sys_region` VALUES ('2524', '304', '祁县', '3');
INSERT INTO `d_sys_region` VALUES ('2525', '304', '平遥县', '3');
INSERT INTO `d_sys_region` VALUES ('2526', '304', '灵石县', '3');
INSERT INTO `d_sys_region` VALUES ('2527', '305', '尧都区', '3');
INSERT INTO `d_sys_region` VALUES ('2528', '305', '侯马市', '3');
INSERT INTO `d_sys_region` VALUES ('2529', '305', '霍州市', '3');
INSERT INTO `d_sys_region` VALUES ('2530', '305', '曲沃县', '3');
INSERT INTO `d_sys_region` VALUES ('2531', '305', '翼城县', '3');
INSERT INTO `d_sys_region` VALUES ('2532', '305', '襄汾县', '3');
INSERT INTO `d_sys_region` VALUES ('2533', '305', '洪洞县', '3');
INSERT INTO `d_sys_region` VALUES ('2534', '305', '吉县', '3');
INSERT INTO `d_sys_region` VALUES ('2535', '305', '安泽县', '3');
INSERT INTO `d_sys_region` VALUES ('2536', '305', '浮山县', '3');
INSERT INTO `d_sys_region` VALUES ('2537', '305', '古县', '3');
INSERT INTO `d_sys_region` VALUES ('2538', '305', '乡宁县', '3');
INSERT INTO `d_sys_region` VALUES ('2539', '305', '大宁县', '3');
INSERT INTO `d_sys_region` VALUES ('2540', '305', '隰县', '3');
INSERT INTO `d_sys_region` VALUES ('2541', '305', '永和县', '3');
INSERT INTO `d_sys_region` VALUES ('2542', '305', '蒲县', '3');
INSERT INTO `d_sys_region` VALUES ('2543', '305', '汾西县', '3');
INSERT INTO `d_sys_region` VALUES ('2544', '306', '离石市', '3');
INSERT INTO `d_sys_region` VALUES ('2545', '306', '离石区', '3');
INSERT INTO `d_sys_region` VALUES ('2546', '306', '孝义市', '3');
INSERT INTO `d_sys_region` VALUES ('2547', '306', '汾阳市', '3');
INSERT INTO `d_sys_region` VALUES ('2548', '306', '文水县', '3');
INSERT INTO `d_sys_region` VALUES ('2549', '306', '交城县', '3');
INSERT INTO `d_sys_region` VALUES ('2550', '306', '兴县', '3');
INSERT INTO `d_sys_region` VALUES ('2551', '306', '临县', '3');
INSERT INTO `d_sys_region` VALUES ('2552', '306', '柳林县', '3');
INSERT INTO `d_sys_region` VALUES ('2553', '306', '石楼县', '3');
INSERT INTO `d_sys_region` VALUES ('2554', '306', '岚县', '3');
INSERT INTO `d_sys_region` VALUES ('2555', '306', '方山县', '3');
INSERT INTO `d_sys_region` VALUES ('2556', '306', '中阳县', '3');
INSERT INTO `d_sys_region` VALUES ('2557', '306', '交口县', '3');
INSERT INTO `d_sys_region` VALUES ('2558', '307', '朔城区', '3');
INSERT INTO `d_sys_region` VALUES ('2559', '307', '平鲁区', '3');
INSERT INTO `d_sys_region` VALUES ('2560', '307', '山阴县', '3');
INSERT INTO `d_sys_region` VALUES ('2561', '307', '应县', '3');
INSERT INTO `d_sys_region` VALUES ('2562', '307', '右玉县', '3');
INSERT INTO `d_sys_region` VALUES ('2563', '307', '怀仁县', '3');
INSERT INTO `d_sys_region` VALUES ('2564', '308', '忻府区', '3');
INSERT INTO `d_sys_region` VALUES ('2565', '308', '原平市', '3');
INSERT INTO `d_sys_region` VALUES ('2566', '308', '定襄县', '3');
INSERT INTO `d_sys_region` VALUES ('2567', '308', '五台县', '3');
INSERT INTO `d_sys_region` VALUES ('2568', '308', '代县', '3');
INSERT INTO `d_sys_region` VALUES ('2569', '308', '繁峙县', '3');
INSERT INTO `d_sys_region` VALUES ('2570', '308', '宁武县', '3');
INSERT INTO `d_sys_region` VALUES ('2571', '308', '静乐县', '3');
INSERT INTO `d_sys_region` VALUES ('2572', '308', '神池县', '3');
INSERT INTO `d_sys_region` VALUES ('2573', '308', '五寨县', '3');
INSERT INTO `d_sys_region` VALUES ('2574', '308', '岢岚县', '3');
INSERT INTO `d_sys_region` VALUES ('2575', '308', '河曲县', '3');
INSERT INTO `d_sys_region` VALUES ('2576', '308', '保德县', '3');
INSERT INTO `d_sys_region` VALUES ('2577', '308', '偏关县', '3');
INSERT INTO `d_sys_region` VALUES ('2578', '309', '城区', '3');
INSERT INTO `d_sys_region` VALUES ('2579', '309', '矿区', '3');
INSERT INTO `d_sys_region` VALUES ('2580', '309', '郊区', '3');
INSERT INTO `d_sys_region` VALUES ('2581', '309', '平定县', '3');
INSERT INTO `d_sys_region` VALUES ('2582', '309', '盂县', '3');
INSERT INTO `d_sys_region` VALUES ('2583', '310', '盐湖区', '3');
INSERT INTO `d_sys_region` VALUES ('2584', '310', '永济市', '3');
INSERT INTO `d_sys_region` VALUES ('2585', '310', '河津市', '3');
INSERT INTO `d_sys_region` VALUES ('2586', '310', '临猗县', '3');
INSERT INTO `d_sys_region` VALUES ('2587', '310', '万荣县', '3');
INSERT INTO `d_sys_region` VALUES ('2588', '310', '闻喜县', '3');
INSERT INTO `d_sys_region` VALUES ('2589', '310', '稷山县', '3');
INSERT INTO `d_sys_region` VALUES ('2590', '310', '新绛县', '3');
INSERT INTO `d_sys_region` VALUES ('2591', '310', '绛县', '3');
INSERT INTO `d_sys_region` VALUES ('2592', '310', '垣曲县', '3');
INSERT INTO `d_sys_region` VALUES ('2593', '310', '夏县', '3');
INSERT INTO `d_sys_region` VALUES ('2594', '310', '平陆县', '3');
INSERT INTO `d_sys_region` VALUES ('2595', '310', '芮城县', '3');
INSERT INTO `d_sys_region` VALUES ('2596', '311', '莲湖区', '3');
INSERT INTO `d_sys_region` VALUES ('2597', '311', '新城区', '3');
INSERT INTO `d_sys_region` VALUES ('2598', '311', '碑林区', '3');
INSERT INTO `d_sys_region` VALUES ('2599', '311', '雁塔区', '3');
INSERT INTO `d_sys_region` VALUES ('2600', '311', '灞桥区', '3');
INSERT INTO `d_sys_region` VALUES ('2601', '311', '未央区', '3');
INSERT INTO `d_sys_region` VALUES ('2602', '311', '阎良区', '3');
INSERT INTO `d_sys_region` VALUES ('2603', '311', '临潼区', '3');
INSERT INTO `d_sys_region` VALUES ('2604', '311', '长安区', '3');
INSERT INTO `d_sys_region` VALUES ('2605', '311', '蓝田县', '3');
INSERT INTO `d_sys_region` VALUES ('2606', '311', '周至县', '3');
INSERT INTO `d_sys_region` VALUES ('2607', '311', '户县', '3');
INSERT INTO `d_sys_region` VALUES ('2608', '311', '高陵县', '3');
INSERT INTO `d_sys_region` VALUES ('2609', '312', '汉滨区', '3');
INSERT INTO `d_sys_region` VALUES ('2610', '312', '汉阴县', '3');
INSERT INTO `d_sys_region` VALUES ('2611', '312', '石泉县', '3');
INSERT INTO `d_sys_region` VALUES ('2612', '312', '宁陕县', '3');
INSERT INTO `d_sys_region` VALUES ('2613', '312', '紫阳县', '3');
INSERT INTO `d_sys_region` VALUES ('2614', '312', '岚皋县', '3');
INSERT INTO `d_sys_region` VALUES ('2615', '312', '平利县', '3');
INSERT INTO `d_sys_region` VALUES ('2616', '312', '镇坪县', '3');
INSERT INTO `d_sys_region` VALUES ('2617', '312', '旬阳县', '3');
INSERT INTO `d_sys_region` VALUES ('2618', '312', '白河县', '3');
INSERT INTO `d_sys_region` VALUES ('2619', '313', '陈仓区', '3');
INSERT INTO `d_sys_region` VALUES ('2620', '313', '渭滨区', '3');
INSERT INTO `d_sys_region` VALUES ('2621', '313', '金台区', '3');
INSERT INTO `d_sys_region` VALUES ('2622', '313', '凤翔县', '3');
INSERT INTO `d_sys_region` VALUES ('2623', '313', '岐山县', '3');
INSERT INTO `d_sys_region` VALUES ('2624', '313', '扶风县', '3');
INSERT INTO `d_sys_region` VALUES ('2625', '313', '眉县', '3');
INSERT INTO `d_sys_region` VALUES ('2626', '313', '陇县', '3');
INSERT INTO `d_sys_region` VALUES ('2627', '313', '千阳县', '3');
INSERT INTO `d_sys_region` VALUES ('2628', '313', '麟游县', '3');
INSERT INTO `d_sys_region` VALUES ('2629', '313', '凤县', '3');
INSERT INTO `d_sys_region` VALUES ('2630', '313', '太白县', '3');
INSERT INTO `d_sys_region` VALUES ('2631', '314', '汉台区', '3');
INSERT INTO `d_sys_region` VALUES ('2632', '314', '南郑县', '3');
INSERT INTO `d_sys_region` VALUES ('2633', '314', '城固县', '3');
INSERT INTO `d_sys_region` VALUES ('2634', '314', '洋县', '3');
INSERT INTO `d_sys_region` VALUES ('2635', '314', '西乡县', '3');
INSERT INTO `d_sys_region` VALUES ('2636', '314', '勉县', '3');
INSERT INTO `d_sys_region` VALUES ('2637', '314', '宁强县', '3');
INSERT INTO `d_sys_region` VALUES ('2638', '314', '略阳县', '3');
INSERT INTO `d_sys_region` VALUES ('2639', '314', '镇巴县', '3');
INSERT INTO `d_sys_region` VALUES ('2640', '314', '留坝县', '3');
INSERT INTO `d_sys_region` VALUES ('2641', '314', '佛坪县', '3');
INSERT INTO `d_sys_region` VALUES ('2642', '315', '商州区', '3');
INSERT INTO `d_sys_region` VALUES ('2643', '315', '洛南县', '3');
INSERT INTO `d_sys_region` VALUES ('2644', '315', '丹凤县', '3');
INSERT INTO `d_sys_region` VALUES ('2645', '315', '商南县', '3');
INSERT INTO `d_sys_region` VALUES ('2646', '315', '山阳县', '3');
INSERT INTO `d_sys_region` VALUES ('2647', '315', '镇安县', '3');
INSERT INTO `d_sys_region` VALUES ('2648', '315', '柞水县', '3');
INSERT INTO `d_sys_region` VALUES ('2649', '316', '耀州区', '3');
INSERT INTO `d_sys_region` VALUES ('2650', '316', '王益区', '3');
INSERT INTO `d_sys_region` VALUES ('2651', '316', '印台区', '3');
INSERT INTO `d_sys_region` VALUES ('2652', '316', '宜君县', '3');
INSERT INTO `d_sys_region` VALUES ('2653', '317', '临渭区', '3');
INSERT INTO `d_sys_region` VALUES ('2654', '317', '韩城市', '3');
INSERT INTO `d_sys_region` VALUES ('2655', '317', '华阴市', '3');
INSERT INTO `d_sys_region` VALUES ('2656', '317', '华县', '3');
INSERT INTO `d_sys_region` VALUES ('2657', '317', '潼关县', '3');
INSERT INTO `d_sys_region` VALUES ('2658', '317', '大荔县', '3');
INSERT INTO `d_sys_region` VALUES ('2659', '317', '合阳县', '3');
INSERT INTO `d_sys_region` VALUES ('2660', '317', '澄城县', '3');
INSERT INTO `d_sys_region` VALUES ('2661', '317', '蒲城县', '3');
INSERT INTO `d_sys_region` VALUES ('2662', '317', '白水县', '3');
INSERT INTO `d_sys_region` VALUES ('2663', '317', '富平县', '3');
INSERT INTO `d_sys_region` VALUES ('2664', '318', '秦都区', '3');
INSERT INTO `d_sys_region` VALUES ('2665', '318', '渭城区', '3');
INSERT INTO `d_sys_region` VALUES ('2666', '318', '杨陵区', '3');
INSERT INTO `d_sys_region` VALUES ('2667', '318', '兴平市', '3');
INSERT INTO `d_sys_region` VALUES ('2668', '318', '三原县', '3');
INSERT INTO `d_sys_region` VALUES ('2669', '318', '泾阳县', '3');
INSERT INTO `d_sys_region` VALUES ('2670', '318', '乾县', '3');
INSERT INTO `d_sys_region` VALUES ('2671', '318', '礼泉县', '3');
INSERT INTO `d_sys_region` VALUES ('2672', '318', '永寿县', '3');
INSERT INTO `d_sys_region` VALUES ('2673', '318', '彬县', '3');
INSERT INTO `d_sys_region` VALUES ('2674', '318', '长武县', '3');
INSERT INTO `d_sys_region` VALUES ('2675', '318', '旬邑县', '3');
INSERT INTO `d_sys_region` VALUES ('2676', '318', '淳化县', '3');
INSERT INTO `d_sys_region` VALUES ('2677', '318', '武功县', '3');
INSERT INTO `d_sys_region` VALUES ('2678', '319', '吴起县', '3');
INSERT INTO `d_sys_region` VALUES ('2679', '319', '宝塔区', '3');
INSERT INTO `d_sys_region` VALUES ('2680', '319', '延长县', '3');
INSERT INTO `d_sys_region` VALUES ('2681', '319', '延川县', '3');
INSERT INTO `d_sys_region` VALUES ('2682', '319', '子长县', '3');
INSERT INTO `d_sys_region` VALUES ('2683', '319', '安塞县', '3');
INSERT INTO `d_sys_region` VALUES ('2684', '319', '志丹县', '3');
INSERT INTO `d_sys_region` VALUES ('2685', '319', '甘泉县', '3');
INSERT INTO `d_sys_region` VALUES ('2686', '319', '富县', '3');
INSERT INTO `d_sys_region` VALUES ('2687', '319', '洛川县', '3');
INSERT INTO `d_sys_region` VALUES ('2688', '319', '宜川县', '3');
INSERT INTO `d_sys_region` VALUES ('2689', '319', '黄龙县', '3');
INSERT INTO `d_sys_region` VALUES ('2690', '319', '黄陵县', '3');
INSERT INTO `d_sys_region` VALUES ('2691', '320', '榆阳区', '3');
INSERT INTO `d_sys_region` VALUES ('2692', '320', '神木县', '3');
INSERT INTO `d_sys_region` VALUES ('2693', '320', '府谷县', '3');
INSERT INTO `d_sys_region` VALUES ('2694', '320', '横山县', '3');
INSERT INTO `d_sys_region` VALUES ('2695', '320', '靖边县', '3');
INSERT INTO `d_sys_region` VALUES ('2696', '320', '定边县', '3');
INSERT INTO `d_sys_region` VALUES ('2697', '320', '绥德县', '3');
INSERT INTO `d_sys_region` VALUES ('2698', '320', '米脂县', '3');
INSERT INTO `d_sys_region` VALUES ('2699', '320', '佳县', '3');
INSERT INTO `d_sys_region` VALUES ('2700', '320', '吴堡县', '3');
INSERT INTO `d_sys_region` VALUES ('2701', '320', '清涧县', '3');
INSERT INTO `d_sys_region` VALUES ('2702', '320', '子洲县', '3');
INSERT INTO `d_sys_region` VALUES ('2703', '321', '长宁区', '3');
INSERT INTO `d_sys_region` VALUES ('2704', '321', '闸北区', '3');
INSERT INTO `d_sys_region` VALUES ('2705', '321', '闵行区', '3');
INSERT INTO `d_sys_region` VALUES ('2706', '321', '徐汇区', '3');
INSERT INTO `d_sys_region` VALUES ('2707', '321', '浦东新区', '3');
INSERT INTO `d_sys_region` VALUES ('2708', '321', '杨浦区', '3');
INSERT INTO `d_sys_region` VALUES ('2709', '321', '普陀区', '3');
INSERT INTO `d_sys_region` VALUES ('2710', '321', '静安区', '3');
INSERT INTO `d_sys_region` VALUES ('2711', '321', '卢湾区', '3');
INSERT INTO `d_sys_region` VALUES ('2712', '321', '虹口区', '3');
INSERT INTO `d_sys_region` VALUES ('2713', '321', '黄浦区', '3');
INSERT INTO `d_sys_region` VALUES ('2714', '321', '南汇区', '3');
INSERT INTO `d_sys_region` VALUES ('2715', '321', '松江区', '3');
INSERT INTO `d_sys_region` VALUES ('2716', '321', '嘉定区', '3');
INSERT INTO `d_sys_region` VALUES ('2717', '321', '宝山区', '3');
INSERT INTO `d_sys_region` VALUES ('2718', '321', '青浦区', '3');
INSERT INTO `d_sys_region` VALUES ('2719', '321', '金山区', '3');
INSERT INTO `d_sys_region` VALUES ('2720', '321', '奉贤区', '3');
INSERT INTO `d_sys_region` VALUES ('2721', '321', '崇明县', '3');
INSERT INTO `d_sys_region` VALUES ('2722', '322', '青羊区', '3');
INSERT INTO `d_sys_region` VALUES ('2723', '322', '锦江区', '3');
INSERT INTO `d_sys_region` VALUES ('2724', '322', '金牛区', '3');
INSERT INTO `d_sys_region` VALUES ('2725', '322', '武侯区', '3');
INSERT INTO `d_sys_region` VALUES ('2726', '322', '成华区', '3');
INSERT INTO `d_sys_region` VALUES ('2727', '322', '龙泉驿区', '3');
INSERT INTO `d_sys_region` VALUES ('2728', '322', '青白江区', '3');
INSERT INTO `d_sys_region` VALUES ('2729', '322', '新都区', '3');
INSERT INTO `d_sys_region` VALUES ('2730', '322', '温江区', '3');
INSERT INTO `d_sys_region` VALUES ('2731', '322', '高新区', '3');
INSERT INTO `d_sys_region` VALUES ('2732', '322', '高新西区', '3');
INSERT INTO `d_sys_region` VALUES ('2733', '322', '都江堰市', '3');
INSERT INTO `d_sys_region` VALUES ('2734', '322', '彭州市', '3');
INSERT INTO `d_sys_region` VALUES ('2735', '322', '邛崃市', '3');
INSERT INTO `d_sys_region` VALUES ('2736', '322', '崇州市', '3');
INSERT INTO `d_sys_region` VALUES ('2737', '322', '金堂县', '3');
INSERT INTO `d_sys_region` VALUES ('2738', '322', '双流县', '3');
INSERT INTO `d_sys_region` VALUES ('2739', '322', '郫县', '3');
INSERT INTO `d_sys_region` VALUES ('2740', '322', '大邑县', '3');
INSERT INTO `d_sys_region` VALUES ('2741', '322', '蒲江县', '3');
INSERT INTO `d_sys_region` VALUES ('2742', '322', '新津县', '3');
INSERT INTO `d_sys_region` VALUES ('2743', '322', '都江堰市', '3');
INSERT INTO `d_sys_region` VALUES ('2744', '322', '彭州市', '3');
INSERT INTO `d_sys_region` VALUES ('2745', '322', '邛崃市', '3');
INSERT INTO `d_sys_region` VALUES ('2746', '322', '崇州市', '3');
INSERT INTO `d_sys_region` VALUES ('2747', '322', '金堂县', '3');
INSERT INTO `d_sys_region` VALUES ('2748', '322', '双流县', '3');
INSERT INTO `d_sys_region` VALUES ('2749', '322', '郫县', '3');
INSERT INTO `d_sys_region` VALUES ('2750', '322', '大邑县', '3');
INSERT INTO `d_sys_region` VALUES ('2751', '322', '蒲江县', '3');
INSERT INTO `d_sys_region` VALUES ('2752', '322', '新津县', '3');
INSERT INTO `d_sys_region` VALUES ('2753', '323', '涪城区', '3');
INSERT INTO `d_sys_region` VALUES ('2754', '323', '游仙区', '3');
INSERT INTO `d_sys_region` VALUES ('2755', '323', '江油市', '3');
INSERT INTO `d_sys_region` VALUES ('2756', '323', '盐亭县', '3');
INSERT INTO `d_sys_region` VALUES ('2757', '323', '三台县', '3');
INSERT INTO `d_sys_region` VALUES ('2758', '323', '平武县', '3');
INSERT INTO `d_sys_region` VALUES ('2759', '323', '安县', '3');
INSERT INTO `d_sys_region` VALUES ('2760', '323', '梓潼县', '3');
INSERT INTO `d_sys_region` VALUES ('2761', '323', '北川县', '3');
INSERT INTO `d_sys_region` VALUES ('2762', '324', '马尔康县', '3');
INSERT INTO `d_sys_region` VALUES ('2763', '324', '汶川县', '3');
INSERT INTO `d_sys_region` VALUES ('2764', '324', '理县', '3');
INSERT INTO `d_sys_region` VALUES ('2765', '324', '茂县', '3');
INSERT INTO `d_sys_region` VALUES ('2766', '324', '松潘县', '3');
INSERT INTO `d_sys_region` VALUES ('2767', '324', '九寨沟县', '3');
INSERT INTO `d_sys_region` VALUES ('2768', '324', '金川县', '3');
INSERT INTO `d_sys_region` VALUES ('2769', '324', '小金县', '3');
INSERT INTO `d_sys_region` VALUES ('2770', '324', '黑水县', '3');
INSERT INTO `d_sys_region` VALUES ('2771', '324', '壤塘县', '3');
INSERT INTO `d_sys_region` VALUES ('2772', '324', '阿坝县', '3');
INSERT INTO `d_sys_region` VALUES ('2773', '324', '若尔盖县', '3');
INSERT INTO `d_sys_region` VALUES ('2774', '324', '红原县', '3');
INSERT INTO `d_sys_region` VALUES ('2775', '325', '巴州区', '3');
INSERT INTO `d_sys_region` VALUES ('2776', '325', '通江县', '3');
INSERT INTO `d_sys_region` VALUES ('2777', '325', '南江县', '3');
INSERT INTO `d_sys_region` VALUES ('2778', '325', '平昌县', '3');
INSERT INTO `d_sys_region` VALUES ('2779', '326', '通川区', '3');
INSERT INTO `d_sys_region` VALUES ('2780', '326', '万源市', '3');
INSERT INTO `d_sys_region` VALUES ('2781', '326', '达县', '3');
INSERT INTO `d_sys_region` VALUES ('2782', '326', '宣汉县', '3');
INSERT INTO `d_sys_region` VALUES ('2783', '326', '开江县', '3');
INSERT INTO `d_sys_region` VALUES ('2784', '326', '大竹县', '3');
INSERT INTO `d_sys_region` VALUES ('2785', '326', '渠县', '3');
INSERT INTO `d_sys_region` VALUES ('2786', '327', '旌阳区', '3');
INSERT INTO `d_sys_region` VALUES ('2787', '327', '广汉市', '3');
INSERT INTO `d_sys_region` VALUES ('2788', '327', '什邡市', '3');
INSERT INTO `d_sys_region` VALUES ('2789', '327', '绵竹市', '3');
INSERT INTO `d_sys_region` VALUES ('2790', '327', '罗江县', '3');
INSERT INTO `d_sys_region` VALUES ('2791', '327', '中江县', '3');
INSERT INTO `d_sys_region` VALUES ('2792', '328', '康定县', '3');
INSERT INTO `d_sys_region` VALUES ('2793', '328', '丹巴县', '3');
INSERT INTO `d_sys_region` VALUES ('2794', '328', '泸定县', '3');
INSERT INTO `d_sys_region` VALUES ('2795', '328', '炉霍县', '3');
INSERT INTO `d_sys_region` VALUES ('2796', '328', '九龙县', '3');
INSERT INTO `d_sys_region` VALUES ('2797', '328', '甘孜县', '3');
INSERT INTO `d_sys_region` VALUES ('2798', '328', '雅江县', '3');
INSERT INTO `d_sys_region` VALUES ('2799', '328', '新龙县', '3');
INSERT INTO `d_sys_region` VALUES ('2800', '328', '道孚县', '3');
INSERT INTO `d_sys_region` VALUES ('2801', '328', '白玉县', '3');
INSERT INTO `d_sys_region` VALUES ('2802', '328', '理塘县', '3');
INSERT INTO `d_sys_region` VALUES ('2803', '328', '德格县', '3');
INSERT INTO `d_sys_region` VALUES ('2804', '328', '乡城县', '3');
INSERT INTO `d_sys_region` VALUES ('2805', '328', '石渠县', '3');
INSERT INTO `d_sys_region` VALUES ('2806', '328', '稻城县', '3');
INSERT INTO `d_sys_region` VALUES ('2807', '328', '色达县', '3');
INSERT INTO `d_sys_region` VALUES ('2808', '328', '巴塘县', '3');
INSERT INTO `d_sys_region` VALUES ('2809', '328', '得荣县', '3');
INSERT INTO `d_sys_region` VALUES ('2810', '329', '广安区', '3');
INSERT INTO `d_sys_region` VALUES ('2811', '329', '华蓥市', '3');
INSERT INTO `d_sys_region` VALUES ('2812', '329', '岳池县', '3');
INSERT INTO `d_sys_region` VALUES ('2813', '329', '武胜县', '3');
INSERT INTO `d_sys_region` VALUES ('2814', '329', '邻水县', '3');
INSERT INTO `d_sys_region` VALUES ('2815', '330', '利州区', '3');
INSERT INTO `d_sys_region` VALUES ('2816', '330', '元坝区', '3');
INSERT INTO `d_sys_region` VALUES ('2817', '330', '朝天区', '3');
INSERT INTO `d_sys_region` VALUES ('2818', '330', '旺苍县', '3');
INSERT INTO `d_sys_region` VALUES ('2819', '330', '青川县', '3');
INSERT INTO `d_sys_region` VALUES ('2820', '330', '剑阁县', '3');
INSERT INTO `d_sys_region` VALUES ('2821', '330', '苍溪县', '3');
INSERT INTO `d_sys_region` VALUES ('2822', '331', '峨眉山市', '3');
INSERT INTO `d_sys_region` VALUES ('2823', '331', '乐山市', '3');
INSERT INTO `d_sys_region` VALUES ('2824', '331', '犍为县', '3');
INSERT INTO `d_sys_region` VALUES ('2825', '331', '井研县', '3');
INSERT INTO `d_sys_region` VALUES ('2826', '331', '夹江县', '3');
INSERT INTO `d_sys_region` VALUES ('2827', '331', '沐川县', '3');
INSERT INTO `d_sys_region` VALUES ('2828', '331', '峨边', '3');
INSERT INTO `d_sys_region` VALUES ('2829', '331', '马边', '3');
INSERT INTO `d_sys_region` VALUES ('2830', '332', '西昌市', '3');
INSERT INTO `d_sys_region` VALUES ('2831', '332', '盐源县', '3');
INSERT INTO `d_sys_region` VALUES ('2832', '332', '德昌县', '3');
INSERT INTO `d_sys_region` VALUES ('2833', '332', '会理县', '3');
INSERT INTO `d_sys_region` VALUES ('2834', '332', '会东县', '3');
INSERT INTO `d_sys_region` VALUES ('2835', '332', '宁南县', '3');
INSERT INTO `d_sys_region` VALUES ('2836', '332', '普格县', '3');
INSERT INTO `d_sys_region` VALUES ('2837', '332', '布拖县', '3');
INSERT INTO `d_sys_region` VALUES ('2838', '332', '金阳县', '3');
INSERT INTO `d_sys_region` VALUES ('2839', '332', '昭觉县', '3');
INSERT INTO `d_sys_region` VALUES ('2840', '332', '喜德县', '3');
INSERT INTO `d_sys_region` VALUES ('2841', '332', '冕宁县', '3');
INSERT INTO `d_sys_region` VALUES ('2842', '332', '越西县', '3');
INSERT INTO `d_sys_region` VALUES ('2843', '332', '甘洛县', '3');
INSERT INTO `d_sys_region` VALUES ('2844', '332', '美姑县', '3');
INSERT INTO `d_sys_region` VALUES ('2845', '332', '雷波县', '3');
INSERT INTO `d_sys_region` VALUES ('2846', '332', '木里', '3');
INSERT INTO `d_sys_region` VALUES ('2847', '333', '东坡区', '3');
INSERT INTO `d_sys_region` VALUES ('2848', '333', '仁寿县', '3');
INSERT INTO `d_sys_region` VALUES ('2849', '333', '彭山县', '3');
INSERT INTO `d_sys_region` VALUES ('2850', '333', '洪雅县', '3');
INSERT INTO `d_sys_region` VALUES ('2851', '333', '丹棱县', '3');
INSERT INTO `d_sys_region` VALUES ('2852', '333', '青神县', '3');
INSERT INTO `d_sys_region` VALUES ('2853', '334', '阆中市', '3');
INSERT INTO `d_sys_region` VALUES ('2854', '334', '南部县', '3');
INSERT INTO `d_sys_region` VALUES ('2855', '334', '营山县', '3');
INSERT INTO `d_sys_region` VALUES ('2856', '334', '蓬安县', '3');
INSERT INTO `d_sys_region` VALUES ('2857', '334', '仪陇县', '3');
INSERT INTO `d_sys_region` VALUES ('2858', '334', '顺庆区', '3');
INSERT INTO `d_sys_region` VALUES ('2859', '334', '高坪区', '3');
INSERT INTO `d_sys_region` VALUES ('2860', '334', '嘉陵区', '3');
INSERT INTO `d_sys_region` VALUES ('2861', '334', '西充县', '3');
INSERT INTO `d_sys_region` VALUES ('2862', '335', '市中区', '3');
INSERT INTO `d_sys_region` VALUES ('2863', '335', '东兴区', '3');
INSERT INTO `d_sys_region` VALUES ('2864', '335', '威远县', '3');
INSERT INTO `d_sys_region` VALUES ('2865', '335', '资中县', '3');
INSERT INTO `d_sys_region` VALUES ('2866', '335', '隆昌县', '3');
INSERT INTO `d_sys_region` VALUES ('2867', '336', '东  区', '3');
INSERT INTO `d_sys_region` VALUES ('2868', '336', '西  区', '3');
INSERT INTO `d_sys_region` VALUES ('2869', '336', '仁和区', '3');
INSERT INTO `d_sys_region` VALUES ('2870', '336', '米易县', '3');
INSERT INTO `d_sys_region` VALUES ('2871', '336', '盐边县', '3');
INSERT INTO `d_sys_region` VALUES ('2872', '337', '船山区', '3');
INSERT INTO `d_sys_region` VALUES ('2873', '337', '安居区', '3');
INSERT INTO `d_sys_region` VALUES ('2874', '337', '蓬溪县', '3');
INSERT INTO `d_sys_region` VALUES ('2875', '337', '射洪县', '3');
INSERT INTO `d_sys_region` VALUES ('2876', '337', '大英县', '3');
INSERT INTO `d_sys_region` VALUES ('2877', '338', '雨城区', '3');
INSERT INTO `d_sys_region` VALUES ('2878', '338', '名山县', '3');
INSERT INTO `d_sys_region` VALUES ('2879', '338', '荥经县', '3');
INSERT INTO `d_sys_region` VALUES ('2880', '338', '汉源县', '3');
INSERT INTO `d_sys_region` VALUES ('2881', '338', '石棉县', '3');
INSERT INTO `d_sys_region` VALUES ('2882', '338', '天全县', '3');
INSERT INTO `d_sys_region` VALUES ('2883', '338', '芦山县', '3');
INSERT INTO `d_sys_region` VALUES ('2884', '338', '宝兴县', '3');
INSERT INTO `d_sys_region` VALUES ('2885', '339', '翠屏区', '3');
INSERT INTO `d_sys_region` VALUES ('2886', '339', '宜宾县', '3');
INSERT INTO `d_sys_region` VALUES ('2887', '339', '南溪县', '3');
INSERT INTO `d_sys_region` VALUES ('2888', '339', '江安县', '3');
INSERT INTO `d_sys_region` VALUES ('2889', '339', '长宁县', '3');
INSERT INTO `d_sys_region` VALUES ('2890', '339', '高县', '3');
INSERT INTO `d_sys_region` VALUES ('2891', '339', '珙县', '3');
INSERT INTO `d_sys_region` VALUES ('2892', '339', '筠连县', '3');
INSERT INTO `d_sys_region` VALUES ('2893', '339', '兴文县', '3');
INSERT INTO `d_sys_region` VALUES ('2894', '339', '屏山县', '3');
INSERT INTO `d_sys_region` VALUES ('2895', '340', '雁江区', '3');
INSERT INTO `d_sys_region` VALUES ('2896', '340', '简阳市', '3');
INSERT INTO `d_sys_region` VALUES ('2897', '340', '安岳县', '3');
INSERT INTO `d_sys_region` VALUES ('2898', '340', '乐至县', '3');
INSERT INTO `d_sys_region` VALUES ('2899', '341', '大安区', '3');
INSERT INTO `d_sys_region` VALUES ('2900', '341', '自流井区', '3');
INSERT INTO `d_sys_region` VALUES ('2901', '341', '贡井区', '3');
INSERT INTO `d_sys_region` VALUES ('2902', '341', '沿滩区', '3');
INSERT INTO `d_sys_region` VALUES ('2903', '341', '荣县', '3');
INSERT INTO `d_sys_region` VALUES ('2904', '341', '富顺县', '3');
INSERT INTO `d_sys_region` VALUES ('2905', '342', '江阳区', '3');
INSERT INTO `d_sys_region` VALUES ('2906', '342', '纳溪区', '3');
INSERT INTO `d_sys_region` VALUES ('2907', '342', '龙马潭区', '3');
INSERT INTO `d_sys_region` VALUES ('2908', '342', '泸县', '3');
INSERT INTO `d_sys_region` VALUES ('2909', '342', '合江县', '3');
INSERT INTO `d_sys_region` VALUES ('2910', '342', '叙永县', '3');
INSERT INTO `d_sys_region` VALUES ('2911', '342', '古蔺县', '3');
INSERT INTO `d_sys_region` VALUES ('2912', '343', '和平区', '3');
INSERT INTO `d_sys_region` VALUES ('2913', '343', '河西区', '3');
INSERT INTO `d_sys_region` VALUES ('2914', '343', '南开区', '3');
INSERT INTO `d_sys_region` VALUES ('2915', '343', '河北区', '3');
INSERT INTO `d_sys_region` VALUES ('2916', '343', '河东区', '3');
INSERT INTO `d_sys_region` VALUES ('2917', '343', '红桥区', '3');
INSERT INTO `d_sys_region` VALUES ('2918', '343', '东丽区', '3');
INSERT INTO `d_sys_region` VALUES ('2919', '343', '津南区', '3');
INSERT INTO `d_sys_region` VALUES ('2920', '343', '西青区', '3');
INSERT INTO `d_sys_region` VALUES ('2921', '343', '北辰区', '3');
INSERT INTO `d_sys_region` VALUES ('2922', '343', '塘沽区', '3');
INSERT INTO `d_sys_region` VALUES ('2923', '343', '汉沽区', '3');
INSERT INTO `d_sys_region` VALUES ('2924', '343', '大港区', '3');
INSERT INTO `d_sys_region` VALUES ('2925', '343', '武清区', '3');
INSERT INTO `d_sys_region` VALUES ('2926', '343', '宝坻区', '3');
INSERT INTO `d_sys_region` VALUES ('2927', '343', '经济开发区', '3');
INSERT INTO `d_sys_region` VALUES ('2928', '343', '宁河县', '3');
INSERT INTO `d_sys_region` VALUES ('2929', '343', '静海县', '3');
INSERT INTO `d_sys_region` VALUES ('2930', '343', '蓟县', '3');
INSERT INTO `d_sys_region` VALUES ('2931', '344', '城关区', '3');
INSERT INTO `d_sys_region` VALUES ('2932', '344', '林周县', '3');
INSERT INTO `d_sys_region` VALUES ('2933', '344', '当雄县', '3');
INSERT INTO `d_sys_region` VALUES ('2934', '344', '尼木县', '3');
INSERT INTO `d_sys_region` VALUES ('2935', '344', '曲水县', '3');
INSERT INTO `d_sys_region` VALUES ('2936', '344', '堆龙德庆县', '3');
INSERT INTO `d_sys_region` VALUES ('2937', '344', '达孜县', '3');
INSERT INTO `d_sys_region` VALUES ('2938', '344', '墨竹工卡县', '3');
INSERT INTO `d_sys_region` VALUES ('2939', '345', '噶尔县', '3');
INSERT INTO `d_sys_region` VALUES ('2940', '345', '普兰县', '3');
INSERT INTO `d_sys_region` VALUES ('2941', '345', '札达县', '3');
INSERT INTO `d_sys_region` VALUES ('2942', '345', '日土县', '3');
INSERT INTO `d_sys_region` VALUES ('2943', '345', '革吉县', '3');
INSERT INTO `d_sys_region` VALUES ('2944', '345', '改则县', '3');
INSERT INTO `d_sys_region` VALUES ('2945', '345', '措勤县', '3');
INSERT INTO `d_sys_region` VALUES ('2946', '346', '昌都县', '3');
INSERT INTO `d_sys_region` VALUES ('2947', '346', '江达县', '3');
INSERT INTO `d_sys_region` VALUES ('2948', '346', '贡觉县', '3');
INSERT INTO `d_sys_region` VALUES ('2949', '346', '类乌齐县', '3');
INSERT INTO `d_sys_region` VALUES ('2950', '346', '丁青县', '3');
INSERT INTO `d_sys_region` VALUES ('2951', '346', '察雅县', '3');
INSERT INTO `d_sys_region` VALUES ('2952', '346', '八宿县', '3');
INSERT INTO `d_sys_region` VALUES ('2953', '346', '左贡县', '3');
INSERT INTO `d_sys_region` VALUES ('2954', '346', '芒康县', '3');
INSERT INTO `d_sys_region` VALUES ('2955', '346', '洛隆县', '3');
INSERT INTO `d_sys_region` VALUES ('2956', '346', '边坝县', '3');
INSERT INTO `d_sys_region` VALUES ('2957', '347', '林芝县', '3');
INSERT INTO `d_sys_region` VALUES ('2958', '347', '工布江达县', '3');
INSERT INTO `d_sys_region` VALUES ('2959', '347', '米林县', '3');
INSERT INTO `d_sys_region` VALUES ('2960', '347', '墨脱县', '3');
INSERT INTO `d_sys_region` VALUES ('2961', '347', '波密县', '3');
INSERT INTO `d_sys_region` VALUES ('2962', '347', '察隅县', '3');
INSERT INTO `d_sys_region` VALUES ('2963', '347', '朗县', '3');
INSERT INTO `d_sys_region` VALUES ('2964', '348', '那曲县', '3');
INSERT INTO `d_sys_region` VALUES ('2965', '348', '嘉黎县', '3');
INSERT INTO `d_sys_region` VALUES ('2966', '348', '比如县', '3');
INSERT INTO `d_sys_region` VALUES ('2967', '348', '聂荣县', '3');
INSERT INTO `d_sys_region` VALUES ('2968', '348', '安多县', '3');
INSERT INTO `d_sys_region` VALUES ('2969', '348', '申扎县', '3');
INSERT INTO `d_sys_region` VALUES ('2970', '348', '索县', '3');
INSERT INTO `d_sys_region` VALUES ('2971', '348', '班戈县', '3');
INSERT INTO `d_sys_region` VALUES ('2972', '348', '巴青县', '3');
INSERT INTO `d_sys_region` VALUES ('2973', '348', '尼玛县', '3');
INSERT INTO `d_sys_region` VALUES ('2974', '349', '日喀则市', '3');
INSERT INTO `d_sys_region` VALUES ('2975', '349', '南木林县', '3');
INSERT INTO `d_sys_region` VALUES ('2976', '349', '江孜县', '3');
INSERT INTO `d_sys_region` VALUES ('2977', '349', '定日县', '3');
INSERT INTO `d_sys_region` VALUES ('2978', '349', '萨迦县', '3');
INSERT INTO `d_sys_region` VALUES ('2979', '349', '拉孜县', '3');
INSERT INTO `d_sys_region` VALUES ('2980', '349', '昂仁县', '3');
INSERT INTO `d_sys_region` VALUES ('2981', '349', '谢通门县', '3');
INSERT INTO `d_sys_region` VALUES ('2982', '349', '白朗县', '3');
INSERT INTO `d_sys_region` VALUES ('2983', '349', '仁布县', '3');
INSERT INTO `d_sys_region` VALUES ('2984', '349', '康马县', '3');
INSERT INTO `d_sys_region` VALUES ('2985', '349', '定结县', '3');
INSERT INTO `d_sys_region` VALUES ('2986', '349', '仲巴县', '3');
INSERT INTO `d_sys_region` VALUES ('2987', '349', '亚东县', '3');
INSERT INTO `d_sys_region` VALUES ('2988', '349', '吉隆县', '3');
INSERT INTO `d_sys_region` VALUES ('2989', '349', '聂拉木县', '3');
INSERT INTO `d_sys_region` VALUES ('2990', '349', '萨嘎县', '3');
INSERT INTO `d_sys_region` VALUES ('2991', '349', '岗巴县', '3');
INSERT INTO `d_sys_region` VALUES ('2992', '350', '乃东县', '3');
INSERT INTO `d_sys_region` VALUES ('2993', '350', '扎囊县', '3');
INSERT INTO `d_sys_region` VALUES ('2994', '350', '贡嘎县', '3');
INSERT INTO `d_sys_region` VALUES ('2995', '350', '桑日县', '3');
INSERT INTO `d_sys_region` VALUES ('2996', '350', '琼结县', '3');
INSERT INTO `d_sys_region` VALUES ('2997', '350', '曲松县', '3');
INSERT INTO `d_sys_region` VALUES ('2998', '350', '措美县', '3');
INSERT INTO `d_sys_region` VALUES ('2999', '350', '洛扎县', '3');
INSERT INTO `d_sys_region` VALUES ('3000', '350', '加查县', '3');
INSERT INTO `d_sys_region` VALUES ('3001', '350', '隆子县', '3');
INSERT INTO `d_sys_region` VALUES ('3002', '350', '错那县', '3');
INSERT INTO `d_sys_region` VALUES ('3003', '350', '浪卡子县', '3');
INSERT INTO `d_sys_region` VALUES ('3004', '351', '天山区', '3');
INSERT INTO `d_sys_region` VALUES ('3005', '351', '沙依巴克区', '3');
INSERT INTO `d_sys_region` VALUES ('3006', '351', '新市区', '3');
INSERT INTO `d_sys_region` VALUES ('3007', '351', '水磨沟区', '3');
INSERT INTO `d_sys_region` VALUES ('3008', '351', '头屯河区', '3');
INSERT INTO `d_sys_region` VALUES ('3009', '351', '达坂城区', '3');
INSERT INTO `d_sys_region` VALUES ('3010', '351', '米东区', '3');
INSERT INTO `d_sys_region` VALUES ('3011', '351', '乌鲁木齐县', '3');
INSERT INTO `d_sys_region` VALUES ('3012', '352', '阿克苏市', '3');
INSERT INTO `d_sys_region` VALUES ('3013', '352', '温宿县', '3');
INSERT INTO `d_sys_region` VALUES ('3014', '352', '库车县', '3');
INSERT INTO `d_sys_region` VALUES ('3015', '352', '沙雅县', '3');
INSERT INTO `d_sys_region` VALUES ('3016', '352', '新和县', '3');
INSERT INTO `d_sys_region` VALUES ('3017', '352', '拜城县', '3');
INSERT INTO `d_sys_region` VALUES ('3018', '352', '乌什县', '3');
INSERT INTO `d_sys_region` VALUES ('3019', '352', '阿瓦提县', '3');
INSERT INTO `d_sys_region` VALUES ('3020', '352', '柯坪县', '3');
INSERT INTO `d_sys_region` VALUES ('3021', '353', '阿拉尔市', '3');
INSERT INTO `d_sys_region` VALUES ('3022', '354', '库尔勒市', '3');
INSERT INTO `d_sys_region` VALUES ('3023', '354', '轮台县', '3');
INSERT INTO `d_sys_region` VALUES ('3024', '354', '尉犁县', '3');
INSERT INTO `d_sys_region` VALUES ('3025', '354', '若羌县', '3');
INSERT INTO `d_sys_region` VALUES ('3026', '354', '且末县', '3');
INSERT INTO `d_sys_region` VALUES ('3027', '354', '焉耆', '3');
INSERT INTO `d_sys_region` VALUES ('3028', '354', '和静县', '3');
INSERT INTO `d_sys_region` VALUES ('3029', '354', '和硕县', '3');
INSERT INTO `d_sys_region` VALUES ('3030', '354', '博湖县', '3');
INSERT INTO `d_sys_region` VALUES ('3031', '355', '博乐市', '3');
INSERT INTO `d_sys_region` VALUES ('3032', '355', '精河县', '3');
INSERT INTO `d_sys_region` VALUES ('3033', '355', '温泉县', '3');
INSERT INTO `d_sys_region` VALUES ('3034', '356', '呼图壁县', '3');
INSERT INTO `d_sys_region` VALUES ('3035', '356', '米泉市', '3');
INSERT INTO `d_sys_region` VALUES ('3036', '356', '昌吉市', '3');
INSERT INTO `d_sys_region` VALUES ('3037', '356', '阜康市', '3');
INSERT INTO `d_sys_region` VALUES ('3038', '356', '玛纳斯县', '3');
INSERT INTO `d_sys_region` VALUES ('3039', '356', '奇台县', '3');
INSERT INTO `d_sys_region` VALUES ('3040', '356', '吉木萨尔县', '3');
INSERT INTO `d_sys_region` VALUES ('3041', '356', '木垒', '3');
INSERT INTO `d_sys_region` VALUES ('3042', '357', '哈密市', '3');
INSERT INTO `d_sys_region` VALUES ('3043', '357', '伊吾县', '3');
INSERT INTO `d_sys_region` VALUES ('3044', '357', '巴里坤', '3');
INSERT INTO `d_sys_region` VALUES ('3045', '358', '和田市', '3');
INSERT INTO `d_sys_region` VALUES ('3046', '358', '和田县', '3');
INSERT INTO `d_sys_region` VALUES ('3047', '358', '墨玉县', '3');
INSERT INTO `d_sys_region` VALUES ('3048', '358', '皮山县', '3');
INSERT INTO `d_sys_region` VALUES ('3049', '358', '洛浦县', '3');
INSERT INTO `d_sys_region` VALUES ('3050', '358', '策勒县', '3');
INSERT INTO `d_sys_region` VALUES ('3051', '358', '于田县', '3');
INSERT INTO `d_sys_region` VALUES ('3052', '358', '民丰县', '3');
INSERT INTO `d_sys_region` VALUES ('3053', '359', '喀什市', '3');
INSERT INTO `d_sys_region` VALUES ('3054', '359', '疏附县', '3');
INSERT INTO `d_sys_region` VALUES ('3055', '359', '疏勒县', '3');
INSERT INTO `d_sys_region` VALUES ('3056', '359', '英吉沙县', '3');
INSERT INTO `d_sys_region` VALUES ('3057', '359', '泽普县', '3');
INSERT INTO `d_sys_region` VALUES ('3058', '359', '莎车县', '3');
INSERT INTO `d_sys_region` VALUES ('3059', '359', '叶城县', '3');
INSERT INTO `d_sys_region` VALUES ('3060', '359', '麦盖提县', '3');
INSERT INTO `d_sys_region` VALUES ('3061', '359', '岳普湖县', '3');
INSERT INTO `d_sys_region` VALUES ('3062', '359', '伽师县', '3');
INSERT INTO `d_sys_region` VALUES ('3063', '359', '巴楚县', '3');
INSERT INTO `d_sys_region` VALUES ('3064', '359', '塔什库尔干', '3');
INSERT INTO `d_sys_region` VALUES ('3065', '360', '克拉玛依市', '3');
INSERT INTO `d_sys_region` VALUES ('3066', '361', '阿图什市', '3');
INSERT INTO `d_sys_region` VALUES ('3067', '361', '阿克陶县', '3');
INSERT INTO `d_sys_region` VALUES ('3068', '361', '阿合奇县', '3');
INSERT INTO `d_sys_region` VALUES ('3069', '361', '乌恰县', '3');
INSERT INTO `d_sys_region` VALUES ('3070', '362', '石河子市', '3');
INSERT INTO `d_sys_region` VALUES ('3071', '363', '图木舒克市', '3');
INSERT INTO `d_sys_region` VALUES ('3072', '364', '吐鲁番市', '3');
INSERT INTO `d_sys_region` VALUES ('3073', '364', '鄯善县', '3');
INSERT INTO `d_sys_region` VALUES ('3074', '364', '托克逊县', '3');
INSERT INTO `d_sys_region` VALUES ('3075', '365', '五家渠市', '3');
INSERT INTO `d_sys_region` VALUES ('3076', '366', '阿勒泰市', '3');
INSERT INTO `d_sys_region` VALUES ('3077', '366', '布克赛尔', '3');
INSERT INTO `d_sys_region` VALUES ('3078', '366', '伊宁市', '3');
INSERT INTO `d_sys_region` VALUES ('3079', '366', '布尔津县', '3');
INSERT INTO `d_sys_region` VALUES ('3080', '366', '奎屯市', '3');
INSERT INTO `d_sys_region` VALUES ('3081', '366', '乌苏市', '3');
INSERT INTO `d_sys_region` VALUES ('3082', '366', '额敏县', '3');
INSERT INTO `d_sys_region` VALUES ('3083', '366', '富蕴县', '3');
INSERT INTO `d_sys_region` VALUES ('3084', '366', '伊宁县', '3');
INSERT INTO `d_sys_region` VALUES ('3085', '366', '福海县', '3');
INSERT INTO `d_sys_region` VALUES ('3086', '366', '霍城县', '3');
INSERT INTO `d_sys_region` VALUES ('3087', '366', '沙湾县', '3');
INSERT INTO `d_sys_region` VALUES ('3088', '366', '巩留县', '3');
INSERT INTO `d_sys_region` VALUES ('3089', '366', '哈巴河县', '3');
INSERT INTO `d_sys_region` VALUES ('3090', '366', '托里县', '3');
INSERT INTO `d_sys_region` VALUES ('3091', '366', '青河县', '3');
INSERT INTO `d_sys_region` VALUES ('3092', '366', '新源县', '3');
INSERT INTO `d_sys_region` VALUES ('3093', '366', '裕民县', '3');
INSERT INTO `d_sys_region` VALUES ('3094', '366', '和布克赛尔', '3');
INSERT INTO `d_sys_region` VALUES ('3095', '366', '吉木乃县', '3');
INSERT INTO `d_sys_region` VALUES ('3096', '366', '昭苏县', '3');
INSERT INTO `d_sys_region` VALUES ('3097', '366', '特克斯县', '3');
INSERT INTO `d_sys_region` VALUES ('3098', '366', '尼勒克县', '3');
INSERT INTO `d_sys_region` VALUES ('3099', '366', '察布查尔', '3');
INSERT INTO `d_sys_region` VALUES ('3100', '367', '盘龙区', '3');
INSERT INTO `d_sys_region` VALUES ('3101', '367', '五华区', '3');
INSERT INTO `d_sys_region` VALUES ('3102', '367', '官渡区', '3');
INSERT INTO `d_sys_region` VALUES ('3103', '367', '西山区', '3');
INSERT INTO `d_sys_region` VALUES ('3104', '367', '东川区', '3');
INSERT INTO `d_sys_region` VALUES ('3105', '367', '安宁市', '3');
INSERT INTO `d_sys_region` VALUES ('3106', '367', '呈贡县', '3');
INSERT INTO `d_sys_region` VALUES ('3107', '367', '晋宁县', '3');
INSERT INTO `d_sys_region` VALUES ('3108', '367', '富民县', '3');
INSERT INTO `d_sys_region` VALUES ('3109', '367', '宜良县', '3');
INSERT INTO `d_sys_region` VALUES ('3110', '367', '嵩明县', '3');
INSERT INTO `d_sys_region` VALUES ('3111', '367', '石林县', '3');
INSERT INTO `d_sys_region` VALUES ('3112', '367', '禄劝', '3');
INSERT INTO `d_sys_region` VALUES ('3113', '367', '寻甸', '3');
INSERT INTO `d_sys_region` VALUES ('3114', '368', '兰坪', '3');
INSERT INTO `d_sys_region` VALUES ('3115', '368', '泸水县', '3');
INSERT INTO `d_sys_region` VALUES ('3116', '368', '福贡县', '3');
INSERT INTO `d_sys_region` VALUES ('3117', '368', '贡山', '3');
INSERT INTO `d_sys_region` VALUES ('3118', '369', '宁洱', '3');
INSERT INTO `d_sys_region` VALUES ('3119', '369', '思茅区', '3');
INSERT INTO `d_sys_region` VALUES ('3120', '369', '墨江', '3');
INSERT INTO `d_sys_region` VALUES ('3121', '369', '景东', '3');
INSERT INTO `d_sys_region` VALUES ('3122', '369', '景谷', '3');
INSERT INTO `d_sys_region` VALUES ('3123', '369', '镇沅', '3');
INSERT INTO `d_sys_region` VALUES ('3124', '369', '江城', '3');
INSERT INTO `d_sys_region` VALUES ('3125', '369', '孟连', '3');
INSERT INTO `d_sys_region` VALUES ('3126', '369', '澜沧', '3');
INSERT INTO `d_sys_region` VALUES ('3127', '369', '西盟', '3');
INSERT INTO `d_sys_region` VALUES ('3128', '370', '古城区', '3');
INSERT INTO `d_sys_region` VALUES ('3129', '370', '宁蒗', '3');
INSERT INTO `d_sys_region` VALUES ('3130', '370', '玉龙', '3');
INSERT INTO `d_sys_region` VALUES ('3131', '370', '永胜县', '3');
INSERT INTO `d_sys_region` VALUES ('3132', '370', '华坪县', '3');
INSERT INTO `d_sys_region` VALUES ('3133', '371', '隆阳区', '3');
INSERT INTO `d_sys_region` VALUES ('3134', '371', '施甸县', '3');
INSERT INTO `d_sys_region` VALUES ('3135', '371', '腾冲县', '3');
INSERT INTO `d_sys_region` VALUES ('3136', '371', '龙陵县', '3');
INSERT INTO `d_sys_region` VALUES ('3137', '371', '昌宁县', '3');
INSERT INTO `d_sys_region` VALUES ('3138', '372', '楚雄市', '3');
INSERT INTO `d_sys_region` VALUES ('3139', '372', '双柏县', '3');
INSERT INTO `d_sys_region` VALUES ('3140', '372', '牟定县', '3');
INSERT INTO `d_sys_region` VALUES ('3141', '372', '南华县', '3');
INSERT INTO `d_sys_region` VALUES ('3142', '372', '姚安县', '3');
INSERT INTO `d_sys_region` VALUES ('3143', '372', '大姚县', '3');
INSERT INTO `d_sys_region` VALUES ('3144', '372', '永仁县', '3');
INSERT INTO `d_sys_region` VALUES ('3145', '372', '元谋县', '3');
INSERT INTO `d_sys_region` VALUES ('3146', '372', '武定县', '3');
INSERT INTO `d_sys_region` VALUES ('3147', '372', '禄丰县', '3');
INSERT INTO `d_sys_region` VALUES ('3148', '373', '大理市', '3');
INSERT INTO `d_sys_region` VALUES ('3149', '373', '祥云县', '3');
INSERT INTO `d_sys_region` VALUES ('3150', '373', '宾川县', '3');
INSERT INTO `d_sys_region` VALUES ('3151', '373', '弥渡县', '3');
INSERT INTO `d_sys_region` VALUES ('3152', '373', '永平县', '3');
INSERT INTO `d_sys_region` VALUES ('3153', '373', '云龙县', '3');
INSERT INTO `d_sys_region` VALUES ('3154', '373', '洱源县', '3');
INSERT INTO `d_sys_region` VALUES ('3155', '373', '剑川县', '3');
INSERT INTO `d_sys_region` VALUES ('3156', '373', '鹤庆县', '3');
INSERT INTO `d_sys_region` VALUES ('3157', '373', '漾濞', '3');
INSERT INTO `d_sys_region` VALUES ('3158', '373', '南涧', '3');
INSERT INTO `d_sys_region` VALUES ('3159', '373', '巍山', '3');
INSERT INTO `d_sys_region` VALUES ('3160', '374', '潞西市', '3');
INSERT INTO `d_sys_region` VALUES ('3161', '374', '瑞丽市', '3');
INSERT INTO `d_sys_region` VALUES ('3162', '374', '梁河县', '3');
INSERT INTO `d_sys_region` VALUES ('3163', '374', '盈江县', '3');
INSERT INTO `d_sys_region` VALUES ('3164', '374', '陇川县', '3');
INSERT INTO `d_sys_region` VALUES ('3165', '375', '香格里拉县', '3');
INSERT INTO `d_sys_region` VALUES ('3166', '375', '德钦县', '3');
INSERT INTO `d_sys_region` VALUES ('3167', '375', '维西', '3');
INSERT INTO `d_sys_region` VALUES ('3168', '376', '泸西县', '3');
INSERT INTO `d_sys_region` VALUES ('3169', '376', '蒙自县', '3');
INSERT INTO `d_sys_region` VALUES ('3170', '376', '个旧市', '3');
INSERT INTO `d_sys_region` VALUES ('3171', '376', '开远市', '3');
INSERT INTO `d_sys_region` VALUES ('3172', '376', '绿春县', '3');
INSERT INTO `d_sys_region` VALUES ('3173', '376', '建水县', '3');
INSERT INTO `d_sys_region` VALUES ('3174', '376', '石屏县', '3');
INSERT INTO `d_sys_region` VALUES ('3175', '376', '弥勒县', '3');
INSERT INTO `d_sys_region` VALUES ('3176', '376', '元阳县', '3');
INSERT INTO `d_sys_region` VALUES ('3177', '376', '红河县', '3');
INSERT INTO `d_sys_region` VALUES ('3178', '376', '金平', '3');
INSERT INTO `d_sys_region` VALUES ('3179', '376', '河口', '3');
INSERT INTO `d_sys_region` VALUES ('3180', '376', '屏边', '3');
INSERT INTO `d_sys_region` VALUES ('3181', '377', '临翔区', '3');
INSERT INTO `d_sys_region` VALUES ('3182', '377', '凤庆县', '3');
INSERT INTO `d_sys_region` VALUES ('3183', '377', '云县', '3');
INSERT INTO `d_sys_region` VALUES ('3184', '377', '永德县', '3');
INSERT INTO `d_sys_region` VALUES ('3185', '377', '镇康县', '3');
INSERT INTO `d_sys_region` VALUES ('3186', '377', '双江', '3');
INSERT INTO `d_sys_region` VALUES ('3187', '377', '耿马', '3');
INSERT INTO `d_sys_region` VALUES ('3188', '377', '沧源', '3');
INSERT INTO `d_sys_region` VALUES ('3189', '378', '麒麟区', '3');
INSERT INTO `d_sys_region` VALUES ('3190', '378', '宣威市', '3');
INSERT INTO `d_sys_region` VALUES ('3191', '378', '马龙县', '3');
INSERT INTO `d_sys_region` VALUES ('3192', '378', '陆良县', '3');
INSERT INTO `d_sys_region` VALUES ('3193', '378', '师宗县', '3');
INSERT INTO `d_sys_region` VALUES ('3194', '378', '罗平县', '3');
INSERT INTO `d_sys_region` VALUES ('3195', '378', '富源县', '3');
INSERT INTO `d_sys_region` VALUES ('3196', '378', '会泽县', '3');
INSERT INTO `d_sys_region` VALUES ('3197', '378', '沾益县', '3');
INSERT INTO `d_sys_region` VALUES ('3198', '379', '文山县', '3');
INSERT INTO `d_sys_region` VALUES ('3199', '379', '砚山县', '3');
INSERT INTO `d_sys_region` VALUES ('3200', '379', '西畴县', '3');
INSERT INTO `d_sys_region` VALUES ('3201', '379', '麻栗坡县', '3');
INSERT INTO `d_sys_region` VALUES ('3202', '379', '马关县', '3');
INSERT INTO `d_sys_region` VALUES ('3203', '379', '丘北县', '3');
INSERT INTO `d_sys_region` VALUES ('3204', '379', '广南县', '3');
INSERT INTO `d_sys_region` VALUES ('3205', '379', '富宁县', '3');
INSERT INTO `d_sys_region` VALUES ('3206', '380', '景洪市', '3');
INSERT INTO `d_sys_region` VALUES ('3207', '380', '勐海县', '3');
INSERT INTO `d_sys_region` VALUES ('3208', '380', '勐腊县', '3');
INSERT INTO `d_sys_region` VALUES ('3209', '381', '红塔区', '3');
INSERT INTO `d_sys_region` VALUES ('3210', '381', '江川县', '3');
INSERT INTO `d_sys_region` VALUES ('3211', '381', '澄江县', '3');
INSERT INTO `d_sys_region` VALUES ('3212', '381', '通海县', '3');
INSERT INTO `d_sys_region` VALUES ('3213', '381', '华宁县', '3');
INSERT INTO `d_sys_region` VALUES ('3214', '381', '易门县', '3');
INSERT INTO `d_sys_region` VALUES ('3215', '381', '峨山', '3');
INSERT INTO `d_sys_region` VALUES ('3216', '381', '新平', '3');
INSERT INTO `d_sys_region` VALUES ('3217', '381', '元江', '3');
INSERT INTO `d_sys_region` VALUES ('3218', '382', '昭阳区', '3');
INSERT INTO `d_sys_region` VALUES ('3219', '382', '鲁甸县', '3');
INSERT INTO `d_sys_region` VALUES ('3220', '382', '巧家县', '3');
INSERT INTO `d_sys_region` VALUES ('3221', '382', '盐津县', '3');
INSERT INTO `d_sys_region` VALUES ('3222', '382', '大关县', '3');
INSERT INTO `d_sys_region` VALUES ('3223', '382', '永善县', '3');
INSERT INTO `d_sys_region` VALUES ('3224', '382', '绥江县', '3');
INSERT INTO `d_sys_region` VALUES ('3225', '382', '镇雄县', '3');
INSERT INTO `d_sys_region` VALUES ('3226', '382', '彝良县', '3');
INSERT INTO `d_sys_region` VALUES ('3227', '382', '威信县', '3');
INSERT INTO `d_sys_region` VALUES ('3228', '382', '水富县', '3');
INSERT INTO `d_sys_region` VALUES ('3229', '383', '西湖区', '3');
INSERT INTO `d_sys_region` VALUES ('3230', '383', '上城区', '3');
INSERT INTO `d_sys_region` VALUES ('3231', '383', '下城区', '3');
INSERT INTO `d_sys_region` VALUES ('3232', '383', '拱墅区', '3');
INSERT INTO `d_sys_region` VALUES ('3233', '383', '滨江区', '3');
INSERT INTO `d_sys_region` VALUES ('3234', '383', '江干区', '3');
INSERT INTO `d_sys_region` VALUES ('3235', '383', '萧山区', '3');
INSERT INTO `d_sys_region` VALUES ('3236', '383', '余杭区', '3');
INSERT INTO `d_sys_region` VALUES ('3237', '383', '市郊', '3');
INSERT INTO `d_sys_region` VALUES ('3238', '383', '建德市', '3');
INSERT INTO `d_sys_region` VALUES ('3239', '383', '富阳市', '3');
INSERT INTO `d_sys_region` VALUES ('3240', '383', '临安市', '3');
INSERT INTO `d_sys_region` VALUES ('3241', '383', '桐庐县', '3');
INSERT INTO `d_sys_region` VALUES ('3242', '383', '淳安县', '3');
INSERT INTO `d_sys_region` VALUES ('3243', '384', '吴兴区', '3');
INSERT INTO `d_sys_region` VALUES ('3244', '384', '南浔区', '3');
INSERT INTO `d_sys_region` VALUES ('3245', '384', '德清县', '3');
INSERT INTO `d_sys_region` VALUES ('3246', '384', '长兴县', '3');
INSERT INTO `d_sys_region` VALUES ('3247', '384', '安吉县', '3');
INSERT INTO `d_sys_region` VALUES ('3248', '385', '南湖区', '3');
INSERT INTO `d_sys_region` VALUES ('3249', '385', '秀洲区', '3');
INSERT INTO `d_sys_region` VALUES ('3250', '385', '海宁市', '3');
INSERT INTO `d_sys_region` VALUES ('3251', '385', '嘉善县', '3');
INSERT INTO `d_sys_region` VALUES ('3252', '385', '平湖市', '3');
INSERT INTO `d_sys_region` VALUES ('3253', '385', '桐乡市', '3');
INSERT INTO `d_sys_region` VALUES ('3254', '385', '海盐县', '3');
INSERT INTO `d_sys_region` VALUES ('3255', '386', '婺城区', '3');
INSERT INTO `d_sys_region` VALUES ('3256', '386', '金东区', '3');
INSERT INTO `d_sys_region` VALUES ('3257', '386', '兰溪市', '3');
INSERT INTO `d_sys_region` VALUES ('3258', '386', '市区', '3');
INSERT INTO `d_sys_region` VALUES ('3259', '386', '佛堂镇', '3');
INSERT INTO `d_sys_region` VALUES ('3260', '386', '上溪镇', '3');
INSERT INTO `d_sys_region` VALUES ('3261', '386', '义亭镇', '3');
INSERT INTO `d_sys_region` VALUES ('3262', '386', '大陈镇', '3');
INSERT INTO `d_sys_region` VALUES ('3263', '386', '苏溪镇', '3');
INSERT INTO `d_sys_region` VALUES ('3264', '386', '赤岸镇', '3');
INSERT INTO `d_sys_region` VALUES ('3265', '386', '东阳市', '3');
INSERT INTO `d_sys_region` VALUES ('3266', '386', '永康市', '3');
INSERT INTO `d_sys_region` VALUES ('3267', '386', '武义县', '3');
INSERT INTO `d_sys_region` VALUES ('3268', '386', '浦江县', '3');
INSERT INTO `d_sys_region` VALUES ('3269', '386', '磐安县', '3');
INSERT INTO `d_sys_region` VALUES ('3270', '387', '莲都区', '3');
INSERT INTO `d_sys_region` VALUES ('3271', '387', '龙泉市', '3');
INSERT INTO `d_sys_region` VALUES ('3272', '387', '青田县', '3');
INSERT INTO `d_sys_region` VALUES ('3273', '387', '缙云县', '3');
INSERT INTO `d_sys_region` VALUES ('3274', '387', '遂昌县', '3');
INSERT INTO `d_sys_region` VALUES ('3275', '387', '松阳县', '3');
INSERT INTO `d_sys_region` VALUES ('3276', '387', '云和县', '3');
INSERT INTO `d_sys_region` VALUES ('3277', '387', '庆元县', '3');
INSERT INTO `d_sys_region` VALUES ('3278', '387', '景宁', '3');
INSERT INTO `d_sys_region` VALUES ('3279', '388', '海曙区', '3');
INSERT INTO `d_sys_region` VALUES ('3280', '388', '江东区', '3');
INSERT INTO `d_sys_region` VALUES ('3281', '388', '江北区', '3');
INSERT INTO `d_sys_region` VALUES ('3282', '388', '镇海区', '3');
INSERT INTO `d_sys_region` VALUES ('3283', '388', '北仑区', '3');
INSERT INTO `d_sys_region` VALUES ('3284', '388', '鄞州区', '3');
INSERT INTO `d_sys_region` VALUES ('3285', '388', '余姚市', '3');
INSERT INTO `d_sys_region` VALUES ('3286', '388', '慈溪市', '3');
INSERT INTO `d_sys_region` VALUES ('3287', '388', '奉化市', '3');
INSERT INTO `d_sys_region` VALUES ('3288', '388', '象山县', '3');
INSERT INTO `d_sys_region` VALUES ('3289', '388', '宁海县', '3');
INSERT INTO `d_sys_region` VALUES ('3290', '389', '越城区', '3');
INSERT INTO `d_sys_region` VALUES ('3291', '389', '上虞市', '3');
INSERT INTO `d_sys_region` VALUES ('3292', '389', '嵊州市', '3');
INSERT INTO `d_sys_region` VALUES ('3293', '389', '绍兴县', '3');
INSERT INTO `d_sys_region` VALUES ('3294', '389', '新昌县', '3');
INSERT INTO `d_sys_region` VALUES ('3295', '389', '诸暨市', '3');
INSERT INTO `d_sys_region` VALUES ('3296', '390', '椒江区', '3');
INSERT INTO `d_sys_region` VALUES ('3297', '390', '黄岩区', '3');
INSERT INTO `d_sys_region` VALUES ('3298', '390', '路桥区', '3');
INSERT INTO `d_sys_region` VALUES ('3299', '390', '温岭市', '3');
INSERT INTO `d_sys_region` VALUES ('3300', '390', '临海市', '3');
INSERT INTO `d_sys_region` VALUES ('3301', '390', '玉环县', '3');
INSERT INTO `d_sys_region` VALUES ('3302', '390', '三门县', '3');
INSERT INTO `d_sys_region` VALUES ('3303', '390', '天台县', '3');
INSERT INTO `d_sys_region` VALUES ('3304', '390', '仙居县', '3');
INSERT INTO `d_sys_region` VALUES ('3305', '391', '鹿城区', '3');
INSERT INTO `d_sys_region` VALUES ('3306', '391', '龙湾区', '3');
INSERT INTO `d_sys_region` VALUES ('3307', '391', '瓯海区', '3');
INSERT INTO `d_sys_region` VALUES ('3308', '391', '瑞安市', '3');
INSERT INTO `d_sys_region` VALUES ('3309', '391', '乐清市', '3');
INSERT INTO `d_sys_region` VALUES ('3310', '391', '洞头县', '3');
INSERT INTO `d_sys_region` VALUES ('3311', '391', '永嘉县', '3');
INSERT INTO `d_sys_region` VALUES ('3312', '391', '平阳县', '3');
INSERT INTO `d_sys_region` VALUES ('3313', '391', '苍南县', '3');
INSERT INTO `d_sys_region` VALUES ('3314', '391', '文成县', '3');
INSERT INTO `d_sys_region` VALUES ('3315', '391', '泰顺县', '3');
INSERT INTO `d_sys_region` VALUES ('3316', '392', '定海区', '3');
INSERT INTO `d_sys_region` VALUES ('3317', '392', '普陀区', '3');
INSERT INTO `d_sys_region` VALUES ('3318', '392', '岱山县', '3');
INSERT INTO `d_sys_region` VALUES ('3319', '392', '嵊泗县', '3');
INSERT INTO `d_sys_region` VALUES ('3320', '393', '衢州市', '3');
INSERT INTO `d_sys_region` VALUES ('3321', '393', '江山市', '3');
INSERT INTO `d_sys_region` VALUES ('3322', '393', '常山县', '3');
INSERT INTO `d_sys_region` VALUES ('3323', '393', '开化县', '3');
INSERT INTO `d_sys_region` VALUES ('3324', '393', '龙游县', '3');
INSERT INTO `d_sys_region` VALUES ('3325', '394', '合川区', '3');
INSERT INTO `d_sys_region` VALUES ('3326', '394', '江津区', '3');
INSERT INTO `d_sys_region` VALUES ('3327', '394', '南川区', '3');
INSERT INTO `d_sys_region` VALUES ('3328', '394', '永川区', '3');
INSERT INTO `d_sys_region` VALUES ('3329', '394', '南岸区', '3');
INSERT INTO `d_sys_region` VALUES ('3330', '394', '渝北区', '3');
INSERT INTO `d_sys_region` VALUES ('3331', '394', '万盛区', '3');
INSERT INTO `d_sys_region` VALUES ('3332', '394', '大渡口区', '3');
INSERT INTO `d_sys_region` VALUES ('3333', '394', '万州区', '3');
INSERT INTO `d_sys_region` VALUES ('3334', '394', '北碚区', '3');
INSERT INTO `d_sys_region` VALUES ('3335', '394', '沙坪坝区', '3');
INSERT INTO `d_sys_region` VALUES ('3336', '394', '巴南区', '3');
INSERT INTO `d_sys_region` VALUES ('3337', '394', '涪陵区', '3');
INSERT INTO `d_sys_region` VALUES ('3338', '394', '江北区', '3');
INSERT INTO `d_sys_region` VALUES ('3339', '394', '九龙坡区', '3');
INSERT INTO `d_sys_region` VALUES ('3340', '394', '渝中区', '3');
INSERT INTO `d_sys_region` VALUES ('3341', '394', '黔江开发区', '3');
INSERT INTO `d_sys_region` VALUES ('3342', '394', '长寿区', '3');
INSERT INTO `d_sys_region` VALUES ('3343', '394', '双桥区', '3');
INSERT INTO `d_sys_region` VALUES ('3344', '394', '綦江县', '3');
INSERT INTO `d_sys_region` VALUES ('3345', '394', '潼南县', '3');
INSERT INTO `d_sys_region` VALUES ('3346', '394', '铜梁县', '3');
INSERT INTO `d_sys_region` VALUES ('3347', '394', '大足县', '3');
INSERT INTO `d_sys_region` VALUES ('3348', '394', '荣昌县', '3');
INSERT INTO `d_sys_region` VALUES ('3349', '394', '璧山县', '3');
INSERT INTO `d_sys_region` VALUES ('3350', '394', '垫江县', '3');
INSERT INTO `d_sys_region` VALUES ('3351', '394', '武隆县', '3');
INSERT INTO `d_sys_region` VALUES ('3352', '394', '丰都县', '3');
INSERT INTO `d_sys_region` VALUES ('3353', '394', '城口县', '3');
INSERT INTO `d_sys_region` VALUES ('3354', '394', '梁平县', '3');
INSERT INTO `d_sys_region` VALUES ('3355', '394', '开县', '3');
INSERT INTO `d_sys_region` VALUES ('3356', '394', '巫溪县', '3');
INSERT INTO `d_sys_region` VALUES ('3357', '394', '巫山县', '3');
INSERT INTO `d_sys_region` VALUES ('3358', '394', '奉节县', '3');
INSERT INTO `d_sys_region` VALUES ('3359', '394', '云阳县', '3');
INSERT INTO `d_sys_region` VALUES ('3360', '394', '忠县', '3');
INSERT INTO `d_sys_region` VALUES ('3361', '394', '石柱', '3');
INSERT INTO `d_sys_region` VALUES ('3362', '394', '彭水', '3');
INSERT INTO `d_sys_region` VALUES ('3363', '394', '酉阳', '3');
INSERT INTO `d_sys_region` VALUES ('3364', '394', '秀山', '3');
INSERT INTO `d_sys_region` VALUES ('3365', '395', '沙田区', '3');
INSERT INTO `d_sys_region` VALUES ('3366', '395', '东区', '3');
INSERT INTO `d_sys_region` VALUES ('3367', '395', '观塘区', '3');
INSERT INTO `d_sys_region` VALUES ('3368', '395', '黄大仙区', '3');
INSERT INTO `d_sys_region` VALUES ('3369', '395', '九龙城区', '3');
INSERT INTO `d_sys_region` VALUES ('3370', '395', '屯门区', '3');
INSERT INTO `d_sys_region` VALUES ('3371', '395', '葵青区', '3');
INSERT INTO `d_sys_region` VALUES ('3372', '395', '元朗区', '3');
INSERT INTO `d_sys_region` VALUES ('3373', '395', '深水埗区', '3');
INSERT INTO `d_sys_region` VALUES ('3374', '395', '西贡区', '3');
INSERT INTO `d_sys_region` VALUES ('3375', '395', '大埔区', '3');
INSERT INTO `d_sys_region` VALUES ('3376', '395', '湾仔区', '3');
INSERT INTO `d_sys_region` VALUES ('3377', '395', '油尖旺区', '3');
INSERT INTO `d_sys_region` VALUES ('3378', '395', '北区', '3');
INSERT INTO `d_sys_region` VALUES ('3379', '395', '南区', '3');
INSERT INTO `d_sys_region` VALUES ('3380', '395', '荃湾区', '3');
INSERT INTO `d_sys_region` VALUES ('3381', '395', '中西区', '3');
INSERT INTO `d_sys_region` VALUES ('3382', '395', '离岛区', '3');
INSERT INTO `d_sys_region` VALUES ('3383', '396', '澳门', '3');
INSERT INTO `d_sys_region` VALUES ('3384', '397', '台北', '3');
INSERT INTO `d_sys_region` VALUES ('3385', '397', '高雄', '3');
INSERT INTO `d_sys_region` VALUES ('3386', '397', '基隆', '3');
INSERT INTO `d_sys_region` VALUES ('3387', '397', '台中', '3');
INSERT INTO `d_sys_region` VALUES ('3388', '397', '台南', '3');
INSERT INTO `d_sys_region` VALUES ('3389', '397', '新竹', '3');
INSERT INTO `d_sys_region` VALUES ('3390', '397', '嘉义', '3');
INSERT INTO `d_sys_region` VALUES ('3391', '397', '宜兰县', '3');
INSERT INTO `d_sys_region` VALUES ('3392', '397', '桃园县', '3');
INSERT INTO `d_sys_region` VALUES ('3393', '397', '苗栗县', '3');
INSERT INTO `d_sys_region` VALUES ('3394', '397', '彰化县', '3');
INSERT INTO `d_sys_region` VALUES ('3395', '397', '南投县', '3');
INSERT INTO `d_sys_region` VALUES ('3396', '397', '云林县', '3');
INSERT INTO `d_sys_region` VALUES ('3397', '397', '屏东县', '3');
INSERT INTO `d_sys_region` VALUES ('3398', '397', '台东县', '3');
INSERT INTO `d_sys_region` VALUES ('3399', '397', '花莲县', '3');
INSERT INTO `d_sys_region` VALUES ('3400', '397', '澎湖县', '3');
INSERT INTO `d_sys_region` VALUES ('3401', '3', '合肥', '2');
INSERT INTO `d_sys_region` VALUES ('3402', '3401', '庐阳区', '3');
INSERT INTO `d_sys_region` VALUES ('3403', '3401', '瑶海区', '3');
INSERT INTO `d_sys_region` VALUES ('3404', '3401', '蜀山区', '3');
INSERT INTO `d_sys_region` VALUES ('3405', '3401', '包河区', '3');
INSERT INTO `d_sys_region` VALUES ('3406', '3401', '长丰县', '3');
INSERT INTO `d_sys_region` VALUES ('3407', '3401', '肥东县', '3');
INSERT INTO `d_sys_region` VALUES ('3408', '3401', '肥西县', '3');

-- ----------------------------
-- Table structure for `d_sys_sensitive`
-- ----------------------------
DROP TABLE IF EXISTS `d_sys_sensitive`;
CREATE TABLE `d_sys_sensitive` (
  `s_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '敏感词ID',
  `content` varchar(50) DEFAULT NULL COMMENT '敏感词名称',
  `add_user_id` int(11) NOT NULL COMMENT '创建人ID',
  `add_user_name` varchar(50) DEFAULT NULL COMMENT '创建人',
  `add_datetime` int(11) NOT NULL COMMENT '创建时间',
  `edit_user_id` int(11) DEFAULT NULL COMMENT '修改人ID',
  `edit_user_name` varchar(50) DEFAULT NULL COMMENT '修改人',
  `edit_datetime` int(11) DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`s_id`)
) ENGINE=MyISAM AUTO_INCREMENT=158 DEFAULT CHARSET=utf8 COMMENT='公安部要求的IDCP认证敏感词';

-- ----------------------------
-- Records of d_sys_sensitive
-- ----------------------------

-- ----------------------------
-- Table structure for `d_system_log`
-- ----------------------------
DROP TABLE IF EXISTS `d_system_log`;
CREATE TABLE `d_system_log` (
  `log_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '日志ID',
  `log_key` varchar(50) DEFAULT NULL COMMENT '关键字',
  `url` varchar(500) DEFAULT NULL COMMENT 'URL',
  `log_tag` varchar(500) DEFAULT NULL COMMENT '标记',
  `log_remark` varchar(2000) DEFAULT NULL COMMENT '日志描述',
  `add_datetime` int(11) DEFAULT NULL COMMENT '添加时间',
  `operateIP` varchar(50) DEFAULT NULL COMMENT 'IP地址',
  PRIMARY KEY (`log_id`)
) ENGINE=MyISAM AUTO_INCREMENT=67614 DEFAULT CHARSET=utf8 COMMENT='系统日志表';

-- ----------------------------
-- Records of d_system_log
-- ----------------------------
INSERT INTO `d_system_log` VALUES ('67612', 'df', 'afd', 'afd', 'afd', '8555', 'adf');
INSERT INTO `d_system_log` VALUES ('67613', 'afd', 'adf', 'afd', 'afd', '8878', 'afd');

-- ----------------------------
-- Table structure for `d_task`
-- ----------------------------
DROP TABLE IF EXISTS `d_task`;
CREATE TABLE `d_task` (
  `task_id` int(11) NOT NULL AUTO_INCREMENT,
  `order_no` varchar(50) NOT NULL DEFAULT '' COMMENT '任务单号',
  `task_name` varchar(50) DEFAULT '' COMMENT '任务名称',
  `city` varchar(100) DEFAULT '' COMMENT '任务地点',
  `city_code` varchar(100) DEFAULT '' COMMENT '场所',
  `task_lcon` varchar(125) DEFAULT '' COMMENT '任务图标',
  `begin_time` int(50) DEFAULT '0' COMMENT '开始时间',
  `end_time` int(50) DEFAULT '0' COMMENT '结束时间',
  `keyword` varchar(255) DEFAULT '' COMMENT '备注',
  `add_detatime` varchar(25) DEFAULT '' COMMENT '时间',
  `area` varchar(125) DEFAULT '' COMMENT '地址',
  `rate` varchar(20) DEFAULT '',
  `task_radius` decimal(20,2) DEFAULT '0.00',
  `task_level` int(10) DEFAULT '0' COMMENT '任务紧急级别 0 普通 1紧急',
  `task_award` varchar(30) DEFAULT '' COMMENT '任务期望收益值',
  `task_remark` varchar(255) DEFAULT '' COMMENT '任务的简介',
  PRIMARY KEY (`task_id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8 COMMENT='任务数据表';

-- ----------------------------
-- Records of d_task
-- ----------------------------
INSERT INTO `d_task` VALUES ('1', '4', '福田车公庙星巴克', '深圳市', '340', 'http://192.168.11.23/oms/image/20180720/fd58f031-17b7-42a5-8047-b5944cc51955.png', '1532081880', '1632793300', '福田车公庙星巴克', '1532082013', '', '1.0000', '100.00', '0', '666.0', '福田车公庙星巴克');
INSERT INTO `d_task` VALUES ('2', '3', '南山海岸城肯德基', '深圳市', '340', 'http://192.168.11.23/oms/image/20180720/20b9de0e-31c4-4f83-a4db-be7f7fe8b10d.png', '1532081700', '1532793300', '南山海岸城肯德基', '1532142909', '', '1.0000', '120.00', '0', '8888.0', '南山海岸城肯德基');
INSERT INTO `d_task` VALUES ('3', '5', '科技园麦当劳', '深圳市', '340', 'http://192.168.11.23/oms/image/20180720/d398cd5e-7656-49ac-9395-4795691adb6a.png', '1532083860', '1532793300', '科技园麦当劳', '1532083993', '', '1.0000', '120.00', '0', '1588.0', '麦当劳');
INSERT INTO `d_task` VALUES ('4', '7', '生态园汉堡王', '深圳市', '340', 'http://192.168.11.23/oms/image/20180721/99ffb387-3dd5-49a5-b902-185f05e3e6f9.jpeg', '1532052900', '1533117600', '南山区深圳湾生态园一期二栋C座', '1533111306', '', '1.0000', '50.00', '0', '666.0', '深圳湾生态园汉堡王');
INSERT INTO `d_task` VALUES ('5', '13', '深圳大学', '深圳市', '340', 'http://192.168.11.23/oms/image/20180723/55cb8efb-a1d2-4c98-84a6-5d4feb2a6cc9.png', '1532312700', '1532361300', '南山区南海大道3688号', '1532312936', '', '1.0000', '180.00', '1', '1800.0', '深圳大学');
INSERT INTO `d_task` VALUES ('6', '14', '乐活广场', '深圳市', '340', 'http://192.168.11.23/oms/image/20180723/59bfd4cf-e32f-494f-bff0-458908409495.jpeg', '1533264300', '1535730900', '沙河西路1811号', '1534126467', '', '1.0000', '150.00', '1', '1800.0', '乐活深圳');
INSERT INTO `d_task` VALUES ('7', '15', '投控物业（生态科技园）', '深圳市', '340', 'http://img.my.csdn.net/uploads/201407/26/1406383291_6518.jpg', '1535159400', '1538322900', '深圳湾生态科技园2栋C座', '1536026233', '', '1.0000', '100.00', '1', '1680.0', '投控物业');
INSERT INTO `d_task` VALUES ('8', '16', '你的空间', '深圳市', '340', 'http://192.168.11.23/oms/image/20180727/16cdc3d3-8f50-42d2-b42b-f81c3b1dd420.png', '1532682000', '1538322900', '你的空间', '1536026278', '', '1.0000', '1000.00', '1', '100.0', '任务');
INSERT INTO `d_task` VALUES ('13', '18', '深圳市南山区人民医院', '深圳市', '340', 'http://192.168.11.23/oms/image/20180720/b1bb4c7c-a385-46b9-a6b8-9590873e36cd.png', '1533717840', '1634002900', '深圳市南山区人民医院', '1633717999', '', '1.0000', '100.00', '1', '1000.0', ' 深圳市南山区人民医院');
INSERT INTO `d_task` VALUES ('14', '', '', '', '', '', '0', '0', '', '', '', '', '0.00', '0', '', '');

-- ----------------------------
-- Table structure for `d_task_frame`
-- ----------------------------
DROP TABLE IF EXISTS `d_task_frame`;
CREATE TABLE `d_task_frame` (
  `frame_id` int(15) NOT NULL AUTO_INCREMENT,
  `user_id` int(30) DEFAULT '0' COMMENT '用户弹框',
  `task_id` int(15) DEFAULT '0' COMMENT '用户的任务ID',
  PRIMARY KEY (`frame_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='任务弹框后的记录表';

-- ----------------------------
-- Records of d_task_frame
-- ----------------------------

-- ----------------------------
-- Table structure for `d_task_list`
-- ----------------------------
DROP TABLE IF EXISTS `d_task_list`;
CREATE TABLE `d_task_list` (
  `tl_id` int(11) NOT NULL AUTO_INCREMENT,
  `id` int(11) DEFAULT '0' COMMENT '账户中心推送过来的任务地点ID',
  `order_no` varchar(50) DEFAULT '' COMMENT '任务的唯一标识',
  `name` varchar(125) DEFAULT NULL COMMENT '地点的名字',
  `lat` decimal(30,6) DEFAULT '0.000000' COMMENT '纬度',
  `lng` decimal(30,6) DEFAULT '0.000000' COMMENT '经度',
  `address` varchar(125) DEFAULT '' COMMENT '具体地点位置',
  `province` varchar(125) DEFAULT '' COMMENT '省份',
  `city` varchar(125) DEFAULT '' COMMENT '城市',
  `area` varchar(125) DEFAULT '' COMMENT '地区',
  `coordType` varchar(125) DEFAULT '' COMMENT '类别编码',
  `mapType` varchar(125) DEFAULT '' COMMENT '地图类别',
  `streetId` varchar(125) DEFAULT '' COMMENT 'j街道ID',
  `begin_time` int(20) DEFAULT '0' COMMENT '对应任务的开始时间',
  `end_time` int(20) DEFAULT '0' COMMENT '对应的任务结束时间',
  PRIMARY KEY (`tl_id`)
) ENGINE=InnoDB AUTO_INCREMENT=239 DEFAULT CHARSET=utf8 COMMENT='任务数据地理位置表';

-- ----------------------------
-- Records of d_task_list
-- ----------------------------
INSERT INTO `d_task_list` VALUES ('1', '5', '4', '星巴克(东海坊店)', '22.544194', '114.032015', '福田区商店1层商场G18A号香林路东海花园福禄居4号', '广东省', '深圳市', '福田区', 'bd09ll', 'BAIDU', 'bb1d38e479a63f540c0a706a', '1532081880', '1532793300');
INSERT INTO `d_task_list` VALUES ('2', '6', '4', '星巴克(五洲星苑店)', '22.547649', '114.051918', '广东省深圳市福田区新闻路12号五洲星苑B座南105及西106-107铺', '广东省', '深圳市', '福田区', 'bd09ll', 'BAIDU', '53349ee3752931772202626a', '1532081880', '1532793300');
INSERT INTO `d_task_list` VALUES ('3', '7', '4', '星巴克(丰盛町店)', '22.541571', '114.030066', '深圳市福田区深南大道6019号丰盛町商业步行街B1层', '广东省', '深圳市', '福田区', 'bd09ll', 'BAIDU', '522a4717ec18f21b1fe9990e', '1532081880', '1532793300');
INSERT INTO `d_task_list` VALUES ('4', '8', '4', '星巴克(东海缤纷天地店)', '22.543006', '114.026981', '深圳市福田区深南大道7888号东海缤纷天地F1', '广东省', '深圳市', '福田区', 'bd09ll', 'BAIDU', '905ebe8e8678246495f08cc6', '1532081880', '1532793300');
INSERT INTO `d_task_list` VALUES ('5', '9', '4', 'STARBUCKS COFFEE', '22.550936', '114.018469', '农林路深国投商业中心山姆会员店1层1F0001铺位', '广东省', '深圳市', '福田区', 'bd09ll', 'BAIDU', '', '1532081880', '1532793300');
INSERT INTO `d_task_list` VALUES ('6', '1', '3', '肯德基(四海餐厅)', '22.508633', '113.930463', '南海大道蛇口沃尔玛广场1楼', '广东省', '深圳市', '南山区', 'bd09ll', 'BAIDU', '5e53af1aac41c2464051c092', '1532081700', '1532793300');
INSERT INTO `d_task_list` VALUES ('7', '2', '3', '肯德基(海雅餐厅)', '22.524333', '113.935411', '深圳市南山区南海大道2746号海雅缤纷广场F1', '广东省', '深圳市', '南山区', 'bd09ll', 'BAIDU', 'e6ee2fd2e681ec512a11d192', '1532081700', '1532793300');
INSERT INTO `d_task_list` VALUES ('8', '3', '3', '肯德基(桃园餐厅)', '22.537693', '113.927253', '深圳市南山区常兴路131', '广东省', '深圳市', '南山区', 'bd09ll', 'BAIDU', 'be1ea5effb2580a615948e92', '1532081700', '1532793300');
INSERT INTO `d_task_list` VALUES ('9', '4', '3', '肯德基(深圳湾体育中心餐厅)', '22.522787', '113.955992', '海德三道深圳湾体育中心首层', '广东省', '深圳市', '南山区', 'bd09ll', 'BAIDU', '5ffb181681fed0466f47602d', '1532081700', '1532793300');
INSERT INTO `d_task_list` VALUES ('10', '10', '5', '麦当劳(南园路店)-洗手间', '22.545197', '114.106349', '深圳福田区深南中路1095号新城市广场内中信地铁商场内B1-2楼(近地铁科学馆站)', '广东省', '深圳市', '福田区', 'bd09ll', 'BAIDU', '94c3943126d1d9daab175a1c', '1532083860', '1532793300');
INSERT INTO `d_task_list` VALUES ('11', '12', '7', '深圳湾科技生态园-1区', '22.534487', '113.960499', '广东省深圳市南山区沙河西路1811号附近', '广东省', '深圳市', '南山区', 'bd09ll', 'BAIDU', 'faf99347c7386d74d4851fc5', '1532052900', '1533117600');
INSERT INTO `d_task_list` VALUES ('12', '13', '7', '深圳湾科技生态园一区-4栋', '22.533908', '113.959241', '广东省深圳市南山区科技园金地国际公寓东(高新南环路)', '广东省', '深圳市', '南山区', 'bd09ll', 'BAIDU', '93d3649284b2fa3ffb0cada2', '1532052900', '1533117600');
INSERT INTO `d_task_list` VALUES ('13', '14', '7', '深圳湾科技生态园1区1期', '22.534170', '113.960039', '沙河西路1811号附近', '广东省', '深圳市', '南山区', 'bd09ll', 'BAIDU', 'c43eb6e946711964f82213a9', '1532052900', '1533117600');
INSERT INTO `d_task_list` VALUES ('14', '15', '7', '深圳市力辉电机有限公司', '22.534160', '113.960435', '深圳市南山区沙河西路深圳湾科技生态园一区2栋B座4楼', '广东省', '深圳市', '南山区', 'bd09ll', 'BAIDU', '7ceb3a935a0ae88ebbbd63dd', '1532052900', '1533117600');
INSERT INTO `d_task_list` VALUES ('15', '16', '7', '深圳市宏业基基础工程有限公司', '22.534280', '113.960757', '广东省深圳市南山区深圳湾科技生态园一区2栋B座9层', '广东省', '深圳市', '南山区', 'bd09ll', 'BAIDU', '9def2212e03d148ab05c6a73', '1532052900', '1533117600');
INSERT INTO `d_task_list` VALUES ('16', '17', '7', '深圳市澳美制药技术开发有限公司', '22.533841', '113.960451', '深圳湾科技生态园一区一期2栋A座4楼', '广东省', '深圳市', '南山区', 'bd09ll', 'BAIDU', 'a8cc37e78d88607d88b988b5', '1532052900', '1533117600');
INSERT INTO `d_task_list` VALUES ('17', '18', '7', '深圳易普森科技股份有限公司', '22.533848', '113.960370', '广东省深圳市南山区深圳湾科技生态园1区2栋701', '广东省', '深圳市', '南山区', 'bd09ll', 'BAIDU', '', '1532052900', '1533117600');
INSERT INTO `d_task_list` VALUES ('18', '19', '7', '和美(深圳)信息技术股份有限公司', '22.534499', '113.959668', '南山区粤海街道深圳湾科技生态园1区2栋B座8楼', '广东省', '深圳市', '南山区', 'bd09ll', 'BAIDU', 'd1219bab8e47f422364aae38', '1532052900', '1533117600');
INSERT INTO `d_task_list` VALUES ('19', '20', '7', '深圳市浓农怡贸易有限公司', '22.533505', '113.960136', '南山区沙河西路深圳湾科技生态园1区A栋30号', '广东省', '深圳市', '南山区', 'bd09ll', 'BAIDU', '2dd9985cb68f14c296ca074e', '1532052900', '1533117600');
INSERT INTO `d_task_list` VALUES ('20', '21', '7', '瑞草堂大药房', '22.533187', '113.960402', '深圳市南山区沙河西路1819号深圳湾科技生态园1区1栋', '广东省', '深圳市', '南山区', 'bd09ll', 'BAIDU', '1c8cf850dc1bc2eab75f1e89', '1532052900', '1533117600');
INSERT INTO `d_task_list` VALUES ('21', '22', '7', '深圳市长亮科技股份有限公司', '22.534076', '113.960724', '粤海街道深圳湾科技生态园一期一区2栋A座5楼', '广东省', '深圳市', '南山区', 'bd09ll', 'BAIDU', '', '1532052900', '1533117600');
INSERT INTO `d_task_list` VALUES ('22', '23', '7', '深圳湾科技生态园体验中心(Apple授权经销商)', '22.535710', '113.959258', '深圳市南山区高新南九道6号深圳湾科技生态园6栋1层27-32号', '广东省', '深圳市', '南山区', 'bd09ll', 'BAIDU', '', '1532052900', '1533117600');
INSERT INTO `d_task_list` VALUES ('23', '137', '13', '南海大道3688号', '22.532932', '113.937880', '南山区', '', '', '', 'bd09ll', 'BAIDU', '', '1532312700', '1532361300');
INSERT INTO `d_task_list` VALUES ('24', '138', '13', '南海大道3688号', '22.532932', '113.937880', '南山区', '', '', '', 'bd09ll', 'BAIDU', '', '1532312700', '1532361300');
INSERT INTO `d_task_list` VALUES ('25', '139', '13', '南海大道3688号', '22.532932', '113.937880', '南山区', '', '', '', 'bd09ll', 'BAIDU', '', '1532312700', '1532361300');
INSERT INTO `d_task_list` VALUES ('26', '140', '14', '沙河西路1811号', '22.535853', '113.960348', '南山区', '', '', '', 'bd09ll', 'BAIDU', '', '1533264300', '1535730900');
INSERT INTO `d_task_list` VALUES ('27', '141', '14', '沙河西路23号', '22.549779', '113.964597', '南山区', '', '', '', 'bd09ll', 'BAIDU', '', '1533264300', '1535730900');
INSERT INTO `d_task_list` VALUES ('29', '143', '15', '投控物业', '22.534616', '113.960228', '粤海街道深圳湾生态科技园2栋C座2楼', '广东省', '深圳市', '南山区', 'bd09ll', 'BAIDU', '06511be87b16285bb54f5489', '1535159400', '1538322900');
INSERT INTO `d_task_list` VALUES ('30', '144', '16', '你的空间', '22.537124', '113.961028', '沙河西路1819号深圳湾科技生态园7栋B座21-38号', '广东省', '深圳市', '南山区', 'bd09ll', 'BAIDU', '765a7e38418f8386ca2915af', '1532682000', '1538322900');
INSERT INTO `d_task_list` VALUES ('31', '146', '17', '深圳市第六人民医院', '22.536424', '113.930242', '深圳市南山区桃园路89号', '广东省', '深圳市', '南山区', 'bd09ll', 'BAIDU', '17c1d5de9ac72368ad31e544', '1533717840', '1534002900');
INSERT INTO `d_task_list` VALUES ('32', '147', '18', '深圳市南山区人民医院(马家龙社区健康服务中心)', '22.529005', '113.954260', '广东省深圳市南山区艺园路133号田厦IC产业园1层', '广东省', '深圳市', '南山区', 'bd09ll', 'BAIDU', 'ec76644a38486a704354d7ec', '1533717840', '1534002900');
INSERT INTO `d_task_list` VALUES ('33', '148', '18', '深圳市南山区西丽人民医院口腔科', '22.587455', '113.967347', '深圳市南山区留仙大道1298号东明花园', '广东省', '深圳市', '南山区', 'bd09ll', 'BAIDU', '4433308cbfc8df005a3ac86e', '1533717840', '1534002900');
INSERT INTO `d_task_list` VALUES ('34', '149', '18', '蛇口人民医院', '22.503854', '113.927523', '79路;113路;122路;328路;m133路;m241路;m409路;m448路;m484路;m527线;高快巴士123路;前海行8路', '', '', '', 'bd09ll', 'BAIDU', '', '1533717840', '1534002900');
INSERT INTO `d_task_list` VALUES ('35', '150', '18', '深圳市南山区人民医院荔湾社区健康服务中心', '22.517079', '113.912702', '前海路太子山庄管理处楼一层', '广东省', '深圳市', '南山区', 'bd09ll', 'BAIDU', '9ac69629cc0d2588acee7a7c', '1533717840', '1534002900');
INSERT INTO `d_task_list` VALUES ('36', '151', '18', '南山区人民医院粤桂社区健康服务中心', '22.531285', '113.938432', '深圳市南山区桂庙新村4-15-1', '广东省', '深圳市', '南山区', 'bd09ll', 'BAIDU', '8fde79cce18542a695ca0705', '1533717840', '1534002900');
INSERT INTO `d_task_list` VALUES ('37', '152', '18', '深圳市南山区蛇口人民医院海湾社区健康服务中心', '22.497130', '113.937029', '南山区蛇口招商路5号', '广东省', '深圳市', '南山区', 'bd09ll', 'BAIDU', '3f9337d613f1ebaaf7231001', '1533717840', '1534002900');
INSERT INTO `d_task_list` VALUES ('38', '153', '18', '深圳市南山区人民医院铜鼓社区健康服务中心', '22.553259', '113.963458', '广东省深圳市南山区沙河西路2021-2号', '广东省', '深圳市', '南山区', 'bd09ll', 'BAIDU', 'c33f4c1d3fd9405526d2bb1e', '1533717840', '1534002900');
INSERT INTO `d_task_list` VALUES ('39', '154', '18', '南山区人民医院特别保健中心', '22.536505', '113.930358', '桃园路89号南山医院住院部1楼', '广东省', '深圳市', '南山区', 'bd09ll', 'BAIDU', 'a170a4a4166c97954054d757', '1533717840', '1534002900');
INSERT INTO `d_task_list` VALUES ('40', '155', '18', '南山区蛇口人民医院深圳湾社区健康服务中心', '22.506947', '113.947576', '中心路2268号曦湾华府', '广东省', '深圳市', '南山区', 'bd09ll', 'BAIDU', '1554d6424e3a63c9b817e7c1', '1533717840', '1534002900');
INSERT INTO `d_task_list` VALUES ('41', '156', '18', '深圳市南山区蛇口人民医院花果山东社区健康服务中心中医区', '22.498422', '113.931048', '广东省深圳市南山区公园路25-4', '广东省', '深圳市', '南山区', 'bd09ll', 'BAIDU', '12ba746d20b4ce5ed5b74e2b', '1533717840', '1534002900');
INSERT INTO `d_task_list` VALUES ('42', '157', '18', '深圳市南山区人民医院前海湾社区健康服务中心', '22.516631', '113.891942', '广东省深圳市南山区六号路前海湾花园一期二栋', '广东省', '深圳市', '南山区', 'bd09ll', 'BAIDU', 'c599ad8af9c17adc28d7ab25', '1533717840', '1534002900');
INSERT INTO `d_task_list` VALUES ('43', '158', '18', '深圳市南山区蛇口人民医院兰园社区健康服务中心', '22.505719', '113.933568', '广东省深圳市南山区蛇口四海路16号', '广东省', '深圳市', '南山区', 'bd09ll', 'BAIDU', '567bb386c0b96d8eb45f1ec0', '1533717840', '1534002900');
INSERT INTO `d_task_list` VALUES ('44', '159', '18', '深圳市南山区西丽人民医院牛成社区健康服务中心', '22.619541', '113.936033', '牛成村路64', '广东省', '深圳市', '南山区', 'bd09ll', 'BAIDU', '', '1533717840', '1534002900');
INSERT INTO `d_task_list` VALUES ('45', '160', '18', '西丽人民医院西丽社康中心', '22.583273', '113.961179', '广东省深圳市南山区沙河西路3230号', '广东省', '深圳市', '南山区', 'bd09ll', 'BAIDU', '5ffb1816ba80a9466f476007', '1533717840', '1534002900');
INSERT INTO `d_task_list` VALUES ('46', '161', '18', '深圳市南山区蛇口人民医院赤湾社区健康服务中心', '22.486649', '113.892313', '少帝路8号', '广东省', '深圳市', '南山区', 'bd09ll', 'BAIDU', 'bb1d38e4dd69db4d0c0a7046', '1533717840', '1534002900');
INSERT INTO `d_task_list` VALUES ('47', '162', '18', '深圳市南山区蛇口人民医院商业文化区社区健康服务中心', '22.519601', '113.941869', '创业路3-22号', '广东省', '深圳市', '南山区', 'bd09ll', 'BAIDU', 'e42e4d44bcaf6cfa93114c9e', '1533717840', '1534002900');
INSERT INTO `d_task_list` VALUES ('48', '163', '18', '深圳市南山区蛇口人民医院大铲社区健康服务中心', '22.494960', '113.933705', '南山区石云路29号', '广东省', '深圳市', '南山区', 'bd09ll', 'BAIDU', '7e32d6b4b261d2f14f6d46e8', '1533717840', '1534002900');
INSERT INTO `d_task_list` VALUES ('49', '164', '18', '关口村人民医院住宅楼', '22.542206', '113.924599', '深圳市南山区南头街100号', '广东省', '深圳市', '南山区', 'bd09ll', 'BAIDU', '', '1533717840', '1534002900');
INSERT INTO `d_task_list` VALUES ('50', '165', '18', '蛇口人民医院-西区', '22.502576', '113.928659', '南山区工业七路36号', '广东省', '深圳市', '南山区', 'bd09ll', 'BAIDU', '2a7a25ec774be17a6c3e1b01', '1533717840', '1534002900');
INSERT INTO `d_task_list` VALUES ('51', '166', '18', '蛇口人民医院-1号楼', '22.502310', '113.928312', '广东省深圳市南山区荔园路21', '广东省', '深圳市', '南山区', 'bd09ll', 'BAIDU', 'b51b263e598a6e5972210ba8', '1533717840', '1534002900');
INSERT INTO `d_task_list` VALUES ('52', '167', '18', '南山区蛇口人民医院南光社区健康服务中心地上停车场-出入口', '22.524664', '113.933305', '南光路45号', '广东省', '深圳市', '南山区', 'bd09ll', 'BAIDU', 'bc7b54fb2c39603700ec01bf', '1533717840', '1534002900');
INSERT INTO `d_task_list` VALUES ('53', '168', '18', '南山区蛇口人民医院南光社区健康服务中心地上停车场-出入口', '22.525266', '113.933466', '南光路45号', '广东省', '深圳市', '南山区', 'bd09ll', 'BAIDU', '58eb83abaf8328a33be4a692', '1533717840', '1534002900');
INSERT INTO `d_task_list` VALUES ('54', '169', '18', '南山区蛇口人民医院南光社区健康服务中心-地上停车场', '22.525111', '113.933234', '南光路45号', '广东省', '深圳市', '南山区', 'bd09ll', 'BAIDU', 'f8010c97d943c8c3990050e9', '1533717840', '1534002900');
INSERT INTO `d_task_list` VALUES ('55', '170', '18', '深圳市西丽人民医院-急诊', '22.585749', '113.955658', '广东省深圳市南山区留仙大道2051', '广东省', '深圳市', '南山区', 'bd09ll', 'BAIDU', '28a27b95272da585b320a829', '1533717840', '1534002900');
INSERT INTO `d_task_list` VALUES ('56', '171', '18', '蛇口人民医院-2号楼', '22.502473', '113.928867', '南山区工业七路36号', '广东省', '深圳市', '南山区', 'bd09ll', 'BAIDU', '1e9277ce4ceac4b8940319b3', '1533717840', '1534002900');
INSERT INTO `d_task_list` VALUES ('57', '172', '18', '西丽人民医院宿舍区', '22.591742', '113.953122', '深圳市南山区官龙路8号', '广东省', '深圳市', '南山区', 'bd09ll', 'BAIDU', 'c65585d990f16568343a7163', '1533717840', '1534002900');
INSERT INTO `d_task_list` VALUES ('58', '173', '18', '蛇口人民医院西区-停车场', '22.502173', '113.929015', '广东省深圳市南山区荔园路21', '广东省', '深圳市', '南山区', 'bd09ll', 'BAIDU', 'c7eca12b4e1dc337e115f947', '1533717840', '1534002900');
INSERT INTO `d_task_list` VALUES ('59', '174', '18', '深圳市西丽人民医院-A栋', '22.585737', '113.955694', '广东省深圳市南山区留仙大道2051', '广东省', '深圳市', '南山区', 'bd09ll', 'BAIDU', 'f1965fa569e67eff9e82ea1d', '1533717840', '1534002900');
INSERT INTO `d_task_list` VALUES ('60', '175', '18', '西丽人民医院-血透室', '22.585809', '113.956825', '深圳市南山区留仙大道2051', '广东省', '深圳市', '南山区', 'bd09ll', 'BAIDU', '2f430ca16acb77039e9d686a', '1533717840', '1534002900');
INSERT INTO `d_task_list` VALUES ('61', '176', '18', '蛇口人民医院西区东一停车场-出入口', '22.502334', '113.928933', '广东省深圳市南山区深圳市蛇口人民医院南门', '广东省', '深圳市', '南山区', 'bd09ll', 'BAIDU', '89a3ff1aead310e79ee32188', '1533717840', '1534002900');
INSERT INTO `d_task_list` VALUES ('62', '177', '18', '深圳市南山区蛇口人民医院五湾社区健康服务中心', '22.483482', '113.914381', '深圳市南山区蛇口港湾大道龙港小区10栋', '广东省', '深圳市', '南山区', 'bd09ll', 'BAIDU', '91c13f931dd12d8420f01794', '1533717840', '1534002900');
INSERT INTO `d_task_list` VALUES ('63', '178', '18', '深圳市南山区蛇口人民医院桃花园社区健康服务中心-中医康复区', '22.508698', '113.926254', '兴工路59号', '广东省', '深圳市', '南山区', 'bd09ll', 'BAIDU', 'ed1d338408c5523e95ec84a3', '1533717840', '1534002900');
INSERT INTO `d_task_list` VALUES ('64', '179', '18', '蛇口人民医院西区-停车场', '22.502231', '113.927741', '深圳市南山区荔园路21号', '广东省', '深圳市', '南山区', 'bd09ll', 'BAIDU', 'bc532336d27bc108f50e7708', '1533717840', '1534002900');
INSERT INTO `d_task_list` VALUES ('65', '180', '18', '深圳市南山区蛇口人民医院龙坤社区健康服务中心中医区', '22.518406', '113.927797', '广东省深圳市南山区南光路与粤海路交叉口东北100米', '广东省', '深圳市', '南山区', 'bd09ll', 'BAIDU', 'd7d86477cda94c3817948e1c', '1533717840', '1534002900');
INSERT INTO `d_task_list` VALUES ('66', '181', '18', '蛇口人民医院西区-住院部', '22.502344', '113.928363', '广东省深圳市南山区工业七路36号深圳市蛇口人民医院内', '广东省', '深圳市', '南山区', 'bd09ll', 'BAIDU', '4002237b8904b1b83686f42d', '1533717840', '1534002900');
INSERT INTO `d_task_list` VALUES ('67', '182', '18', '蛇口人民医院西区-门诊部', '22.502344', '113.928363', '广东省深圳市南山区工业七路36号深圳市蛇口人民医院内', '广东省', '深圳市', '南山区', 'bd09ll', 'BAIDU', '49c759de7168b45494ca07bf', '1533717840', '1534002900');
INSERT INTO `d_task_list` VALUES ('68', '183', '18', '蛇口人民医院西区-发热门诊', '22.502341', '113.928696', '深圳市南山区荔园路21号', '广东省', '深圳市', '南山区', 'bd09ll', 'BAIDU', '76fd2ca30345c185bea240c7', '1533717840', '1534002900');
INSERT INTO `d_task_list` VALUES ('69', '184', '18', '蛇口人民医院西区-急诊科', '22.502231', '113.928482', '广东省深圳市南山区工业七路36号深圳市蛇口人民医院内', '广东省', '深圳市', '南山区', 'bd09ll', 'BAIDU', '74c933fb0006f0239e82ea1a', '1533717840', '1534002900');
INSERT INTO `d_task_list` VALUES ('70', '185', '18', '蛇口人民医院荔林社区健康服务中心', '22.511427', '113.926846', '广东省深圳市南山区兴工路与东滨路交叉口汉京山B栋1层', '广东省', '深圳市', '南山区', 'bd09ll', 'BAIDU', '5338102ace774cbbd7851f97', '1533717840', '1534002900');
INSERT INTO `d_task_list` VALUES ('71', '186', '18', '蛇口人民医院沿山社区健康服务中心', '22.496160', '113.919449', '深圳市南山区沿山路11-1', '广东省', '深圳市', '南山区', 'bd09ll', 'BAIDU', '3a0a78bd4040e6e363180901', '1533717840', '1534002900');
INSERT INTO `d_task_list` VALUES ('72', '187', '18', '蛇口人民医院龙坤社区健康服务中心', '22.518327', '113.927568', '广东省深圳市南山区南光路5-14号', '广东省', '深圳市', '南山区', 'bd09ll', 'BAIDU', '0adce3e2d82a327880a00c01', '1533717840', '1534002900');
INSERT INTO `d_task_list` VALUES ('73', '188', '18', '蛇口人民医院西区西停车场-出入口', '22.502243', '113.927792', '深圳市南山区工业七路36号', '广东省', '深圳市', '南山区', 'bd09ll', 'BAIDU', 'c6d2efc5845063161a11d5a4', '1533717840', '1534002900');
INSERT INTO `d_task_list` VALUES ('74', '189', '18', '蛇口人民医院西区自行车租赁点', '22.502507', '113.927470', '广东省深圳市南山区南海大道1068号附近', '广东省', '深圳市', '南山区', 'bd09ll', 'BAIDU', 'e068433c63c82f367ec8761a', '1533717840', '1534002900');
INSERT INTO `d_task_list` VALUES ('75', '190', '18', '蛇口人民医院水湾社区健康服务中心', '22.493916', '113.925652', '深圳市南山区太子路华府假日大厦1楼', '广东省', '深圳市', '南山区', 'bd09ll', 'BAIDU', 'c8568bd4385417aa5fbe864a', '1533717840', '1534002900');
INSERT INTO `d_task_list` VALUES ('76', '191', '18', '蛇口人民医院后海社区健康服务中心', '22.513354', '113.937582', '深圳市南山区华明路1-29', '广东省', '深圳市', '南山区', 'bd09ll', 'BAIDU', '881a28e7b748f05ef942b203', '1533717840', '1534002900');
INSERT INTO `d_task_list` VALUES ('77', '192', '18', '蛇口人民医院南水社康健康服务中心', '22.494383', '113.929881', ' 广东省深圳市南山区蛇口南水步行街南水花园3栋1层', '广东省', '深圳市', '南山区', 'bd09ll', 'BAIDU', 'a907d2ed563512e02202625f', '1533717840', '1534002900');
INSERT INTO `d_task_list` VALUES ('78', '193', '18', '西丽人民医院白芒社区健康服务中心', '22.628837', '113.946183', '深圳市南山区百旺研发公寓1层', '广东省', '深圳市', '南山区', 'bd09ll', 'BAIDU', 'd42110930090c7ff601c67d7', '1533717840', '1534002900');
INSERT INTO `d_task_list` VALUES ('79', '194', '18', '深圳市蛇口人民医院-停车场', '22.502011', '113.928461', '广东省深圳市南山区工业七路36号深圳市蛇口人民医院', '广东省', '深圳市', '南山区', 'bd09ll', 'BAIDU', '30a588861e4834be4107c134', '1533717840', '1534002900');
INSERT INTO `d_task_list` VALUES ('80', '195', '18', '蛇口人民医院桃花园社区健康服务中心', '22.508735', '113.926229', '广东省深圳市南山区兴工路43号', '广东省', '深圳市', '南山区', 'bd09ll', 'BAIDU', '96b672aa57a6436cce04ef07', '1533717840', '1534002900');
INSERT INTO `d_task_list` VALUES ('81', '196', '18', '蛇口人民医院东角头社区健康服务中心', '22.500350', '113.941990', '深圳市南山区工业七路3-14', '广东省', '深圳市', '南山区', 'bd09ll', 'BAIDU', 'd6df91c124240073f7b91601', '1533717840', '1534002900');
INSERT INTO `d_task_list` VALUES ('82', '197', '18', '蛇口人民医院海昌社区健康服务中心', '22.492750', '113.928544', '深圳市南山区蛇口海昌街82号', '广东省', '深圳市', '南山区', 'bd09ll', 'BAIDU', '91c13f932d613de321f01701', '1533717840', '1534002900');
INSERT INTO `d_task_list` VALUES ('83', '198', '18', '蛇口人民医院风华社区健康服务中心', '22.525050', '113.918882', '广东省深圳市南山区前海路1083-1', '广东省', '深圳市', '南山区', 'bd09ll', 'BAIDU', '4eb3b53326f10a6c4302453c', '1533717840', '1534002900');
INSERT INTO `d_task_list` VALUES ('84', '199', '18', '蛇口人民医院蔚蓝海岸社区健康服务中心', '22.515339', '113.940905', '广东省深圳市南山区登良路7-25号', '广东省', '深圳市', '南山区', 'bd09ll', 'BAIDU', 'd1469ee7c1cdb87b84330407', '1533717840', '1534002900');
INSERT INTO `d_task_list` VALUES ('85', '200', '18', '南山人民医院星海名城社区健康服务中心', '22.541380', '113.918628', '深圳市南山区一号路58', '广东省', '深圳市', '南山区', 'bd09ll', 'BAIDU', 'cb4c66e5a16c07a133169b30', '1533717840', '1534002900');
INSERT INTO `d_task_list` VALUES ('86', '201', '18', '深圳市南山人民医院南山社区健康服务中心', '22.519332', '113.916959', '广东省深圳市南山区南山村正巷83号', '广东省', '深圳市', '南山区', 'bd09ll', 'BAIDU', '24815cdb9b44f0edacee7a5f', '1533717840', '1534002900');
INSERT INTO `d_task_list` VALUES ('87', '202', '18', '蛇口人民医院西区东一停车场-出入口', '22.502602', '113.927731', '广东省深圳市南山区工业七路36号深圳市蛇口人民医院内', '广东省', '深圳市', '南山区', 'bd09ll', 'BAIDU', '39a34b7ad3e7fbfd1e1d11e6', '1533717840', '1534002900');
INSERT INTO `d_task_list` VALUES ('88', '203', '18', '蛇口人民医院文竹园社区健康服务中心', '22.503490', '113.938324', '南山区荔园路102号', '广东省', '深圳市', '南山区', 'bd09ll', 'BAIDU', '7d5f3a0a690a67522cb90801', '1533717840', '1534002900');
INSERT INTO `d_task_list` VALUES ('89', '204', '18', '西丽人民医院财务综合楼', '22.580360', '113.958215', '文苑街1号', '广东省', '深圳市', '南山区', 'bd09ll', 'BAIDU', 'e014ca0607d95f6cefe4a57b', '1533717840', '1534002900');
INSERT INTO `d_task_list` VALUES ('90', '205', '18', '蛇口人民医院西区-西门', '22.502232', '113.927741', '工业七路36号', '广东省', '深圳市', '南山区', 'bd09ll', 'BAIDU', '8b019cf3a3f6fa49130daaad', '1533717840', '1534002900');
INSERT INTO `d_task_list` VALUES ('91', '206', '18', '深圳市蛇口人民医院-停车场', '22.502623', '113.928189', '工业七路与南海大道交叉口东150米', '广东省', '深圳市', '南山区', 'bd09ll', 'BAIDU', '8ce6387ae83a63442b11d1af', '1533717840', '1534002900');
INSERT INTO `d_task_list` VALUES ('92', '207', '18', '深圳市蛇口人民医院-西南门', '22.502125', '113.927892', '工业七路36号', '广东省', '深圳市', '南山区', 'bd09ll', 'BAIDU', '37870d22454382c2841541e8', '1533717840', '1534002900');
INSERT INTO `d_task_list` VALUES ('93', '208', '18', '深圳市蛇口人民医院-废水处理站', '22.503136', '113.928278', '工业七路与南海大道交叉口东150米', '广东省', '深圳市', '南山区', 'bd09ll', 'BAIDU', 'e2a5a0c3ec8b4f6bc056acd1', '1533717840', '1534002900');
INSERT INTO `d_task_list` VALUES ('94', '209', '18', '南山人民医院莲城社区健康服务中心', '22.552436', '113.931446', '深圳市玉泉路118号', '广东省', '深圳市', '南山区', 'bd09ll', 'BAIDU', '49d95938884c535ac61e5100', '1533717840', '1534002900');
INSERT INTO `d_task_list` VALUES ('95', '210', '18', '深圳市蛇口人民医院1号楼-西南门', '22.502169', '113.928188', '工业七路36号附近', '广东省', '深圳市', '南山区', 'bd09ll', 'BAIDU', '0a8e88dc91b430999703191a', '1533717840', '1534002900');
INSERT INTO `d_task_list` VALUES ('96', '211', '18', '深圳市蛇口人民医院1号楼-东北门', '22.502444', '113.928533', '工业七路与荔园路交叉口北150米', '广东省', '深圳市', '南山区', 'bd09ll', 'BAIDU', 'ee6388f125c7da1d276e9854', '1533717840', '1534002900');
INSERT INTO `d_task_list` VALUES ('97', '212', '18', '深圳蛇口人民医院西区-地下停车场', '22.502743', '113.928698', '荔园路21号，工业七路36号', '广东省', '深圳市', '南山区', 'bd09ll', 'BAIDU', '1ffa3b6489a951f37adbd22f', '1533717840', '1534002900');
INSERT INTO `d_task_list` VALUES ('98', '213', '18', '深圳市蛇口人民医院西区-内部停车场', '22.502057', '113.927263', '工业七路36号深圳市蛇口人民医院西区内部', '广东省', '深圳市', '南山区', 'bd09ll', 'BAIDU', 'fcd65bea3b14eec9931fc94e', '1533717840', '1534002900');
INSERT INTO `d_task_list` VALUES ('99', '214', '18', '深圳市蛇口人民医院西区-地上停车场', '22.502306', '113.928430', '荔园路21号，工业七路36号', '广东省', '深圳市', '南山区', 'bd09ll', 'BAIDU', 'cc69b4fbb5077bbc2fe67bbe', '1533717840', '1534002900');
INSERT INTO `d_task_list` VALUES ('100', '215', '18', '深圳市第六人民医院(南山医院)-体检科', '22.537324', '113.930540', '广东省深圳市南山区第六人民医院(南山医院)住院大楼1楼', '广东省', '深圳市', '南山区', 'bd09ll', 'BAIDU', 'd33b9ca62f0ecaf5bb17e7bf', '1533717840', '1534002900');
INSERT INTO `d_task_list` VALUES ('101', '216', '18', '深圳市第六人民医院(南山医院)-示教室', '22.536629', '113.930353', '深圳市南山区第六人民医院(南山医院)住院大楼21楼', '广东省', '深圳市', '南山区', 'bd09ll', 'BAIDU', '83b4d2180e1d326b1029356f', '1533717840', '1534002900');
INSERT INTO `d_task_list` VALUES ('102', '217', '18', '深圳市第六人民医院(南山医院)-手术室', '22.536903', '113.930773', '深圳市南山区第六人民医院(南山医院)住院大楼3层', '广东省', '深圳市', '南山区', 'bd09ll', 'BAIDU', '9d234be33e0abce6d618b705', '1533717840', '1534002900');
INSERT INTO `d_task_list` VALUES ('103', '218', '18', '深圳市第六人民医院(南山医院)-接待室', '22.536629', '113.930353', '深圳市南山区第六人民医院(南山医院)住院大楼21楼', '广东省', '深圳市', '南山区', 'bd09ll', 'BAIDU', '5b3a1d2977c2bd5c96f08c5f', '1533717840', '1534002900');
INSERT INTO `d_task_list` VALUES ('104', '219', '18', '深圳市第六人民医院(南山医院)-妇产科', '22.537642', '113.929958', '深圳市南山区第六人民医院(南山医院)门诊大楼3层', '广东省', '深圳市', '南山区', 'bd09ll', 'BAIDU', 'cc48164ef2043af577026938', '1533717840', '1534002900');
INSERT INTO `d_task_list` VALUES ('105', '220', '18', '深圳市第六人民医院(南山医院)-中医科', '22.537345', '113.930816', '深圳市南山区第六人民医院(南山医院)门诊大楼6层', '广东省', '深圳市', '南山区', 'bd09ll', 'BAIDU', 'e24233669dfe382879dbd272', '1533717840', '1534002900');
INSERT INTO `d_task_list` VALUES ('106', '221', '18', '深圳市第六人民医院(南山医院)-会议室', '22.536629', '113.930353', '深圳市南山区第六人民医院(南山医院)住院大楼21楼', '广东省', '深圳市', '南山区', 'bd09ll', 'BAIDU', '4cfe41f007aabe9ddf1e52fe', '1533717840', '1534002900');
INSERT INTO `d_task_list` VALUES ('107', '222', '18', '深圳市第六人民医院(南山医院)-急诊科', '22.537345', '113.930816', '深圳市南山区第六人民医院(南山医院)门诊大楼1层', '广东省', '深圳市', '南山区', 'bd09ll', 'BAIDU', 'c46c2cdb1e726b8aad080fb8', '1533717840', '1534002900');
INSERT INTO `d_task_list` VALUES ('108', '223', '18', '深圳市第六人民医院(南山医院)-门诊药房', '22.537345', '113.930816', '深圳市第六人民医院门诊大楼1层', '广东省', '深圳市', '南山区', 'bd09ll', 'BAIDU', '96fe9b3e0123a2fb077ce830', '1533717840', '1534002900');
INSERT INTO `d_task_list` VALUES ('109', '224', '18', '蛇口人民医院龙城社区健康服务中心', '22.518128', '113.938169', '龙城路11号', '广东省', '深圳市', '南山区', 'bd09ll', 'BAIDU', '251374c9e791293fa11de97a', '1533717840', '1534002900');
INSERT INTO `d_task_list` VALUES ('110', '225', '18', '蛇口人民医院西区东二停车场-出入口', '22.502150', '113.928979', '荔园路21号', '广东省', '深圳市', '南山区', 'bd09ll', 'BAIDU', '', '1533717840', '1534002900');
INSERT INTO `d_task_list` VALUES ('111', '226', '18', '蛇口人民医院雷岭社区健康服务中心', '22.497395', '113.934016', '花果路40号', '广东省', '深圳市', '南山区', 'bd09ll', 'BAIDU', '56e88cba90087e7ca4c75603', '1533717840', '1534002900');
INSERT INTO `d_task_list` VALUES ('112', '227', '18', '深圳市蛇口人民医院西区内部地上停车场-出入口', '22.502096', '113.927559', '南海大道1056号', '广东省', '深圳市', '南山区', 'bd09ll', 'BAIDU', 'd785065942ec28dfa11de9c6', '1533717840', '1534002900');
INSERT INTO `d_task_list` VALUES ('113', '228', '18', '深圳市蛇口人民医院西区地上停车场-出入口', '22.501939', '113.929189', '荔园路21号，工业七路36号', '广东省', '深圳市', '南山区', 'bd09ll', 'BAIDU', '599a38500b809d296140a9e8', '1533717840', '1534002900');
INSERT INTO `d_task_list` VALUES ('114', '229', '18', '深圳市蛇口人民医院西区地上停车场-出入口', '22.502086', '113.927920', '荔园路21号，工业七路36号', '广东省', '深圳市', '南山区', 'bd09ll', 'BAIDU', '6e9f4c15f9e802f3c356ac5e', '1533717840', '1534002900');
INSERT INTO `d_task_list` VALUES ('115', '230', '18', '深圳市蛇口人民医院西区内部-地上停车场', '22.502041', '113.927231', '南海大道1056号', '广东省', '深圳市', '南山区', 'bd09ll', 'BAIDU', '', '1533717840', '1534002900');
INSERT INTO `d_task_list` VALUES ('116', '231', '18', '深圳市第六人民医院(南山医院)-招工调干体验处', '22.536612', '113.930811', '深圳市南山区第六人民医院(南山医院)医学影像楼1楼', '广东省', '深圳市', '南山区', 'bd09ll', 'BAIDU', '897ea64e7213a663b72655bd', '1533717840', '1534002900');
INSERT INTO `d_task_list` VALUES ('117', '232', '18', '深圳市第六人民医院(南山医院)-双源CT室', '22.536612', '113.930811', '深圳市南山区第六人民医院(南山医院)医学影像楼1楼', '广东省', '深圳市', '南山区', 'bd09ll', 'BAIDU', '919c7d8819c3b0f24bcc4a6b', '1533717840', '1534002900');
INSERT INTO `d_task_list` VALUES ('118', '233', '18', '深圳市第六人民医院(南山医院)-X光照片室', '22.536612', '113.930811', '深圳市南山区第六人民医院(南山医院)医学影像楼2楼', '广东省', '深圳市', '南山区', 'bd09ll', 'BAIDU', '7d864d82bafe5a823f9a5e21', '1533717840', '1534002900');
INSERT INTO `d_task_list` VALUES ('119', '234', '18', '深圳市第六人民医院(南山医院)-核医学科', '22.536612', '113.930811', '深圳市南山区第六人民医院(南山医院)医学影像楼3楼', '广东省', '深圳市', '南山区', 'bd09ll', 'BAIDU', '0028f570af95466c6c476031', '1533717840', '1534002900');
INSERT INTO `d_task_list` VALUES ('120', '235', '18', '深圳市第六人民医院(南山医院)-烧伤、创面修复科', '22.537345', '113.930816', '深圳市南山区第六人民医院(南山医院)门诊大楼2层', '广东省', '深圳市', '南山区', 'bd09ll', 'BAIDU', 'eb1f05b1bcd23d1b2806ce58', '1533717840', '1534002900');
INSERT INTO `d_task_list` VALUES ('121', '236', '18', '深圳市第六人民医院(南山医院)-商务服务', '22.536637', '113.930362', '深圳市南山区第六人民医院(南山医院)住院大楼1楼', '广东省', '深圳市', '南山区', 'bd09ll', 'BAIDU', '98a29660a35389d840ab9a82', '1533717840', '1534002900');
INSERT INTO `d_task_list` VALUES ('122', '237', '18', '深圳市第六人民医院(南山医院)-超声诊断科', '22.537345', '113.930816', '深圳市南山区第六人民医院(南山医院)门诊大楼5层', '广东省', '深圳市', '南山区', 'bd09ll', 'BAIDU', 'ddcf2164ae572a94f82d6156', '1533717840', '1534002900');
INSERT INTO `d_task_list` VALUES ('123', '238', '18', '深圳市第六人民医院(南山医院)-磁共振室', '22.536612', '113.930811', '深圳市南山区第六人民医院(南山医院)医学影像楼1楼', '广东省', '深圳市', '南山区', 'bd09ll', 'BAIDU', '95e10e39a7448db36849f30f', '1533717840', '1534002900');
INSERT INTO `d_task_list` VALUES ('124', '239', '18', '深圳市第六人民医院(南山医院)-纠纷调解办', '22.536612', '113.930811', '深圳市南山区第六人民医院(南山医院)医学影像楼3楼', '广东省', '深圳市', '南山区', 'bd09ll', 'BAIDU', 'e158a738263c969cb8bd63d6', '1533717840', '1534002900');
INSERT INTO `d_task_list` VALUES ('125', '240', '18', '深圳市第六人民医院(南山医院)-消毒供应中心', '22.536637', '113.930362', '深圳市南山区第六人民医院(南山医院)住院大楼2楼', '广东省', '深圳市', '南山区', 'bd09ll', 'BAIDU', 'e29654fc202e8b5d1c38947d', '1533717840', '1534002900');
INSERT INTO `d_task_list` VALUES ('126', '241', '18', '深圳市第六人民医院(南山医院)-中心实验室', '22.536637', '113.930362', '深圳市南山区第六人民医院(南山医院)住院大楼2楼', '广东省', '深圳市', '南山区', 'bd09ll', 'BAIDU', '8539f5016aa887c38bfc0627', '1533717840', '1534002900');
INSERT INTO `d_task_list` VALUES ('127', '242', '18', '深圳市第六人民医院(南山医院)出入院处-结算处', '22.536637', '113.930362', '深圳市南山区第六人民医院(南山医院)住院大楼1楼', '广东省', '深圳市', '南山区', 'bd09ll', 'BAIDU', '5e1a20d778f15781d0ff23dd', '1533717840', '1534002900');
INSERT INTO `d_task_list` VALUES ('128', '243', '18', '深圳市第六人民医院(南山医院)-整形美容科', '22.537345', '113.930816', '深圳市南山区第六人民医院(南山医院)门诊大楼2层', '广东省', '深圳市', '南山区', 'bd09ll', 'BAIDU', '5a96df3e86f258c0f942b2f8', '1533717840', '1534002900');
INSERT INTO `d_task_list` VALUES ('129', '244', '18', '深圳市第六人民医院(南山医院)-VIP病区', '22.536629', '113.930353', '深圳市南山区第六人民医院(南山医院)住院大楼20楼', '广东省', '深圳市', '南山区', 'bd09ll', 'BAIDU', '5d6643d533956c400bf5ebf5', '1533717840', '1534002900');
INSERT INTO `d_task_list` VALUES ('130', '245', '18', '深圳市第六人民医院(南山医院)入院-处置室', '22.536637', '113.930362', '深圳市南山区第六人民医院(南山医院)住院大楼1楼', '广东省', '深圳市', '南山区', 'bd09ll', 'BAIDU', '8733a76967fdb4c73010af8c', '1533717840', '1534002900');
INSERT INTO `d_task_list` VALUES ('131', '246', '18', '深圳市第六人民医院(南山医院)X光照片室-6号', '22.536845', '113.930665', '深圳市南山区第六人民医院(南山医院)医学影像楼1楼', '广东省', '深圳市', '南山区', 'bd09ll', 'BAIDU', 'e327710d28e070b2fadc8573', '1533717840', '1534002900');
INSERT INTO `d_task_list` VALUES ('132', '247', '18', '深圳市第六人民医院(南山医院)-特保中心', '22.536637', '113.930362', '深圳市南山区第六人民医院(南山医院)住院大楼1楼', '广东省', '深圳市', '南山区', 'bd09ll', 'BAIDU', '8550709a3ca68295b05c6a31', '1533717840', '1534002900');
INSERT INTO `d_task_list` VALUES ('133', '248', '18', '深圳蛇口人民医院西区地下停车场-出入口', '22.502154', '113.928985', '荔园路21号，工业七路36号', '广东省', '深圳市', '南山区', 'bd09ll', 'BAIDU', '1d397026befcbe1de507384c', '1533717840', '1534002900');
INSERT INTO `d_task_list` VALUES ('134', '249', '18', '蛇口人民医院站自行车租赁点', '22.503227', '113.927533', '广东省深圳市南山区南海大道1068号附近', '广东省', '深圳市', '南山区', 'bd09ll', 'BAIDU', '89dfbd1362c1f2abeee4a520', '1533717840', '1534002900');
INSERT INTO `d_task_list` VALUES ('135', '250', '18', '南山人民医院田厦社区健康服务中心', '22.534913', '113.925262', '广东省深圳市南山区南新路2030-19', '广东省', '深圳市', '南山区', 'bd09ll', 'BAIDU', '22e11407be59d4add5b74e00', '1533717840', '1534002900');
INSERT INTO `d_task_list` VALUES ('136', '251', '18', '深圳市第六人民医院-停车场', '22.536844', '113.930682', '广东省深圳市南山区桃园路89号东门', '广东省', '深圳市', '南山区', 'bd09ll', 'BAIDU', '14076dee01050f8a03294f8d', '1533717840', '1534002900');
INSERT INTO `d_task_list` VALUES ('137', '252', '18', '西丽人民医院丽湖社区健康服务中心', '22.598105', '113.968089', '深圳市南山区西丽湖路4144', '广东省', '深圳市', '南山区', 'bd09ll', 'BAIDU', '058edd8a9c1c316c6140a93f', '1533717840', '1534002900');
INSERT INTO `d_task_list` VALUES ('138', '253', '18', '蛇口人民医院南光社区健康服务中心', '22.524915', '113.933285', '深圳市南山区南光路45', '广东省', '深圳市', '南山区', 'bd09ll', 'BAIDU', '08fc93df454874ec9b68b411', '1533717840', '1534002900');
INSERT INTO `d_task_list` VALUES ('139', '254', '18', '深圳市第六人民医院-医学影像楼', '22.536612', '113.930811', '深圳市南山区桃园路89号深圳市第六人民医院(南山医院)', '广东省', '深圳市', '南山区', 'bd09ll', 'BAIDU', 'df8a6eb8fa3bfb73f72310df', '1533717840', '1534002900');
INSERT INTO `d_task_list` VALUES ('140', '255', '18', '深圳市第六人民医院图书馆(北门)', '22.536846', '113.929404', '广东省深圳市南山区桃园路89号深圳市第六人民医院(南山医院)', '广东省', '深圳市', '南山区', 'bd09ll', 'BAIDU', 'cd7dbe6b02c31d1d76e5b67b', '1533717840', '1534002900');
INSERT INTO `d_task_list` VALUES ('141', '256', '18', '深圳市第六人民医院-地下停车场', '22.536468', '113.930388', '广东省深圳市南山区桃园路89号深圳市第六人民医院(南山医院)', '广东省', '深圳市', '南山区', 'bd09ll', 'BAIDU', '875f22a820e7f745e9f853fa', '1533717840', '1534002900');
INSERT INTO `d_task_list` VALUES ('142', '257', '18', '蛇口人民医院南水社区健康服务中心预防接种门诊', '22.495297', '113.931268', '广东省深圳市南山区蛇口南水步行街南水花园3栋1楼', '广东省', '深圳市', '南山区', 'bd09ll', 'BAIDU', 'a783ce2888d7aea3a1a9348b', '1533717840', '1534002900');
INSERT INTO `d_task_list` VALUES ('143', '258', '18', '西丽人民医院住院部大学城分部地下停车场-出入口', '22.587757', '113.967157', '深圳市南山区学苑大道(西段)', '广东省', '深圳市', '南山区', 'bd09ll', 'BAIDU', '2c231ac922e497aa774ff1cd', '1533717840', '1534002900');
INSERT INTO `d_task_list` VALUES ('144', '259', '18', '南山人民医院阳光棕榈社区健康服务中心', '22.533846', '113.919902', '广东省深圳市南山区学府路203', '广东省', '深圳市', '南山区', 'bd09ll', 'BAIDU', '750ec165d4b2a8324c3a5900', '1533717840', '1534002900');
INSERT INTO `d_task_list` VALUES ('145', '260', '18', '西丽人民医院住院部大学城分部-地下停车场', '22.587826', '113.967274', '深圳市南山区学苑大道(西段)', '广东省', '深圳市', '南山区', 'bd09ll', 'BAIDU', '927ac088b08036d2ae045760', '1533717840', '1534002900');
INSERT INTO `d_task_list` VALUES ('146', '261', '18', '西丽人民医院住院部大学城分部停车场-出入口', '22.587806', '113.967168', '深圳市南山区学苑大道(西段)', '广东省', '深圳市', '南山区', 'bd09ll', 'BAIDU', 'd4753432994552d796ca07ac', '1533717840', '1534002900');
INSERT INTO `d_task_list` VALUES ('147', '262', '18', '蛇口人民医院文竹园社区健康服务中心-中医理疗室', '22.503489', '113.938443', '广东省深圳市南山区荔园路104', '广东省', '深圳市', '南山区', 'bd09ll', 'BAIDU', '2b893a6619b7833b0482c218', '1533717840', '1534002900');
INSERT INTO `d_task_list` VALUES ('148', '263', '18', '深圳市第六人民医院(南山医院)-门诊部', '22.537306', '113.930596', '深圳市南山区桃园路89号华中科技大学协和深圳医院', '广东省', '深圳市', '南山区', 'bd09ll', 'BAIDU', '0c1130d6bc8b15deae080f67', '1533717840', '1534002900');
INSERT INTO `d_task_list` VALUES ('149', '264', '18', '深圳市第六人民医院(南山医院)-门诊大楼', '22.537164', '113.930629', '广东省深圳市南山区桃园路89号深圳市第六人民医院(南山医院)', '广东省', '深圳市', '南山区', 'bd09ll', 'BAIDU', 'f44c5658e1e68a36e81e97d8', '1533717840', '1534002900');
INSERT INTO `d_task_list` VALUES ('150', '265', '18', '深圳市第六人民医院(南山医院)-疼痛医学韩济生院士工作站', '22.537226', '113.930606', '广东省深圳市南山区桃园路89号南山医院内', '广东省', '深圳市', '南山区', 'bd09ll', 'BAIDU', '8e964b7ebe073682081cc790', '1533717840', '1534002900');
INSERT INTO `d_task_list` VALUES ('151', '266', '18', '深圳市第六人民医院(南山医院)-行政综合楼', '22.536520', '113.929653', '深圳市南山区桃园路89号华中科技大学协和深圳医院', '广东省', '深圳市', '南山区', 'bd09ll', 'BAIDU', '045664503424e7dcaeb22a71', '1533717840', '1534002900');
INSERT INTO `d_task_list` VALUES ('152', '267', '18', '深圳市第六人民医院(南山医院)-实习生进修生宿舍', '22.535669', '113.929358', '深圳市南山区桃园路89号深圳市第六人民医院(南山医院)', '广东省', '深圳市', '南山区', 'bd09ll', 'BAIDU', 'ebc740438248958a8a36ee28', '1533717840', '1534002900');
INSERT INTO `d_task_list` VALUES ('153', '268', '18', '深圳市第六人民医院(南山医院)行政办公楼北座-正门', '22.536812', '113.929419', '深圳市南山区桃园路89号深圳市第六人民医院(南山医院)', '广东省', '深圳市', '南山区', 'bd09ll', 'BAIDU', '631951cbca4640222fb17eb6', '1533717840', '1534002900');
INSERT INTO `d_task_list` VALUES ('154', '269', '18', '深圳市第六人民医院(南山医院)-地下车库', '22.536817', '113.930635', '广东省深圳市南山区桃园路89号深圳市第六人民医院(南山医院)', '广东省', '深圳市', '南山区', 'bd09ll', 'BAIDU', '4f565082f2fa5dd1db131841', '1533717840', '1534002900');
INSERT INTO `d_task_list` VALUES ('155', '270', '18', '深圳市第六人民医院(南山医院)南区停车场-南出入口', '22.535040', '113.930941', '深圳市南山区桃园路89号深圳市第六人民医院(南山医院)', '广东省', '深圳市', '南山区', 'bd09ll', 'BAIDU', '5d90165d05dff4d92ce67b7d', '1533717840', '1534002900');
INSERT INTO `d_task_list` VALUES ('156', '271', '18', '深圳市第六人民医院(南山医院)行政综合楼-南座', '22.536531', '113.929422', '深圳市南山区桃园路89号深圳市第六人民医院(南山医院)', '广东省', '深圳市', '南山区', 'bd09ll', 'BAIDU', '3fa239cf294007d7bf4842ac', '1533717840', '1534002900');
INSERT INTO `d_task_list` VALUES ('157', '272', '18', '深圳市第六人民医院(南山医院)-3号停车场', '22.535961', '113.929509', '深圳市南山区桃园路89号深圳市第六人民医院(南山医院)', '广东省', '深圳市', '南山区', 'bd09ll', 'BAIDU', 'dabea39df35f0812dbeced54', '1533717840', '1534002900');
INSERT INTO `d_task_list` VALUES ('158', '273', '18', '深圳市第六人民医院(南山医院)-1号停车场', '22.537603', '113.930575', '深圳市南山区桃园路89号深圳市第六人民医院(南山医院)', '广东省', '深圳市', '南山区', 'bd09ll', 'BAIDU', '2a8eb6a4acd6074728b97cf3', '1533717840', '1534002900');
INSERT INTO `d_task_list` VALUES ('159', '274', '18', '深圳市第六人民医院(南山医院)地下车库-出口', '22.537161', '113.930195', '深圳市南山区桃园路89号深圳市第六人民医院(南山医院)', '广东省', '深圳市', '南山区', 'bd09ll', 'BAIDU', 'e500681c3c256be93913fde0', '1533717840', '1534002900');
INSERT INTO `d_task_list` VALUES ('160', '275', '18', '深圳市第六人民医院(南山医院)地下停车场入口', '22.536912', '113.930640', '深圳市南山区桃园路89号深圳市第六人民医院(南山医院)', '广东省', '深圳市', '南山区', 'bd09ll', 'BAIDU', '1e49646295276c157369d329', '1533717840', '1534002900');
INSERT INTO `d_task_list` VALUES ('161', '276', '18', '深圳市第六人民医院(南山医院)行政综合楼-北座', '22.536738', '113.929545', '深圳市南山区桃园路89号深圳市第六人民医院(南山医院)', '广东省', '深圳市', '南山区', 'bd09ll', 'BAIDU', '5c2878c04ca8b9f5f842b29c', '1533717840', '1534002900');
INSERT INTO `d_task_list` VALUES ('162', '277', '18', '深圳市第六人民医院(南山医院)-2号停车场', '22.537372', '113.929761', '深圳市南山区桃园路89号深圳市第六人民医院(南山医院)', '广东省', '深圳市', '南山区', 'bd09ll', 'BAIDU', '02b86a67338c6f9cc4f02538', '1533717840', '1534002900');
INSERT INTO `d_task_list` VALUES ('163', '278', '18', '蛇口人民医院花果山东社区健康服务中心', '22.498531', '113.930832', '南山区公园路25-2', '广东省', '深圳市', '南山区', 'bd09ll', 'BAIDU', 'ff4933d549e21aeedbd89162', '1533717840', '1534002900');
INSERT INTO `d_task_list` VALUES ('164', '279', '18', '深圳市第六人民医院-住院部', '22.536408', '113.930164', '桃园路89号深圳市第六人民医院南山医院内', '广东省', '深圳市', '南山区', 'bd09ll', 'BAIDU', 'ea487038d7d9fa7e5b109332', '1533717840', '1534002900');
INSERT INTO `d_task_list` VALUES ('165', '280', '18', '深圳市第六人民医院-口腔科', '22.537344', '113.930816', '深圳市第六人民医院门诊大楼6层', '广东省', '深圳市', '南山区', 'bd09ll', 'BAIDU', 'f7aef50f80ccfd3be812e4a0', '1533717840', '1534002900');
INSERT INTO `d_task_list` VALUES ('166', '281', '18', '深圳市第六人民医院-手术室', '22.536641', '113.930365', '深圳市第六人民医院住院大楼3层', '广东省', '深圳市', '南山区', 'bd09ll', 'BAIDU', '13aaaa5e721d12ffaf31e5c8', '1533717840', '1534002900');
INSERT INTO `d_task_list` VALUES ('167', '282', '18', '深圳市第六人民医院-检验科', '22.536641', '113.930365', '深圳市第六人民医院住院大楼1层', '广东省', '深圳市', '南山区', 'bd09ll', 'BAIDU', 'bd6868ddf25c8d811188246f', '1533717840', '1534002900');
INSERT INTO `d_task_list` VALUES ('168', '283', '18', '深圳市第六人民医院-注射中心', '22.537344', '113.930816', '深圳市第六人民医院门诊大楼1层', '广东省', '深圳市', '南山区', 'bd09ll', 'BAIDU', '429d99b8a504d98554bfffce', '1533717840', '1534002900');
INSERT INTO `d_task_list` VALUES ('169', '284', '18', '深圳市第六人民医院-心电图室', '22.537344', '113.930816', '深圳市第六人民医院门诊大楼5层', '广东省', '深圳市', '南山区', 'bd09ll', 'BAIDU', '3fa39ae8a08ee4e6ae31e53a', '1533717840', '1534002900');
INSERT INTO `d_task_list` VALUES ('170', '285', '18', '深圳市第六人民医院-烧伤科', '22.537344', '113.930816', '深圳市第六人民医院门诊大楼2层', '广东省', '深圳市', '南山区', 'bd09ll', 'BAIDU', '604b00046566d07cadcb66bb', '1533717840', '1534002900');
INSERT INTO `d_task_list` VALUES ('171', '286', '18', '西丽人民医院民警执勤点', '22.587771', '113.967507', '唯科路与学苑大道(西段)交叉口东南50米', '广东省', '深圳市', '南山区', 'bd09ll', 'BAIDU', '709552170caac5f04302453b', '1533717840', '1534002900');
INSERT INTO `d_task_list` VALUES ('172', '287', '18', '西丽人民医院体检中心停车场', '22.580871', '113.956191', '沙河路16号', '广东省', '深圳市', '南山区', 'bd09ll', 'BAIDU', 'abefb1829e12d8d7cac3db14', '1533717840', '1534002900');
INSERT INTO `d_task_list` VALUES ('173', '288', '18', '深圳市第六人民医院-激光美容科', '22.537344', '113.930816', '深圳市第六人民医院门诊大楼4层', '广东省', '深圳市', '南山区', 'bd09ll', 'BAIDU', 'c09683e0b486a3d6077ce8f0', '1533717840', '1534002900');
INSERT INTO `d_task_list` VALUES ('174', '289', '18', '深圳市蛇口人民医院2号楼-西南门', '22.502525', '113.928681', '工业七路36号深圳市蛇口人民医院附近', '广东省', '深圳市', '南山区', 'bd09ll', 'BAIDU', '3195342ea546658c46e274bc', '1533717840', '1534002900');
INSERT INTO `d_task_list` VALUES ('175', '290', '18', '深圳市第六人民医院-重症监护病房', '22.536641', '113.930365', '深圳市第六人民医院住院大楼3层', '广东省', '深圳市', '南山区', 'bd09ll', 'BAIDU', '322b937681aecd22c42ba1d8', '1533717840', '1534002900');
INSERT INTO `d_task_list` VALUES ('176', '291', '18', '深圳市第六人民医院-康复医学科', '22.537344', '113.930816', '深圳市第六人民医院门诊大楼2层', '广东省', '深圳市', '南山区', 'bd09ll', 'BAIDU', '7d9867497370e1474c6d467e', '1533717840', '1534002900');
INSERT INTO `d_task_list` VALUES ('177', '292', '18', '深圳市第六人民医院-地上停车场', '22.536511', '113.930145', '桃园路89号', '广东省', '深圳市', '南山区', 'bd09ll', 'BAIDU', '3516edb3cd042d1a593ac845', '1533717840', '1534002900');
INSERT INTO `d_task_list` VALUES ('178', '293', '18', '西丽人民医院大学城分部-西门', '22.587914', '113.967210', '唯科路与学苑大道(西段)交叉口东南50米', '广东省', '深圳市', '南山区', 'bd09ll', 'BAIDU', 'a68824636b5a54c5c72ba11b', '1533717840', '1534002900');
INSERT INTO `d_task_list` VALUES ('179', '294', '18', '南山人民医院麻岭社区健康服务中心', '22.548582', '113.940568', '南山麒麟路4040号汇园雅居C座首层', '广东省', '深圳市', '南山区', 'bd09ll', 'BAIDU', '9ee7cc781b95d67116c90554', '1533717840', '1534002900');
INSERT INTO `d_task_list` VALUES ('180', '295', '18', '深圳市第六人民医院地上停车场-出口', '22.534969', '113.930941', '桃园路89号', '广东省', '深圳市', '南山区', 'bd09ll', 'BAIDU', '219f5c9576e0d60d6b49f3bb', '1533717840', '1534002900');
INSERT INTO `d_task_list` VALUES ('181', '296', '18', '深圳市第六人民医院地下停车场-出入口', '22.536711', '113.931113', '桃园路89号', '广东省', '深圳市', '南山区', 'bd09ll', 'BAIDU', '5e56cc1cb4a64459a80dd819', '1533717840', '1534002900');
INSERT INTO `d_task_list` VALUES ('182', '297', '18', '深圳市第六人民医院南山医院门诊部A座', '22.537393', '113.930327', '南山大道与桃园路交叉口西南150米', '广东省', '深圳市', '南山区', 'bd09ll', 'BAIDU', '7c16eff836785d87260bd073', '1533717840', '1534002900');
INSERT INTO `d_task_list` VALUES ('183', '298', '18', '深圳市第六人民医院地上停车场-出入口', '22.536731', '113.931118', '桃园路89号', '广东省', '深圳市', '南山区', 'bd09ll', 'BAIDU', 'f5b774b018e6540df6c59c13', '1533717840', '1534002900');
INSERT INTO `d_task_list` VALUES ('184', '299', '18', '深圳市第六人民医院地下停车场-出入口', '22.537942', '113.930150', '桃园路89号', '广东省', '深圳市', '南山区', 'bd09ll', 'BAIDU', '60abad362811aa3beb56fa3f', '1533717840', '1534002900');
INSERT INTO `d_task_list` VALUES ('185', '300', '18', '深圳市第六人民医院地上停车场-出入口', '22.537988', '113.930145', '桃园路89号', '广东省', '深圳市', '南山区', 'bd09ll', 'BAIDU', 'd6d5bcbabfbb99c46f4760cf', '1533717840', '1534002900');
INSERT INTO `d_task_list` VALUES ('186', '301', '18', '深圳市第六人民医院地下停车场-出口', '22.534949', '113.930946', '桃园路89号', '广东省', '深圳市', '南山区', 'bd09ll', 'BAIDU', 'dc45b99d909a160b2efaa73e', '1533717840', '1534002900');
INSERT INTO `d_task_list` VALUES ('187', '302', '18', '深圳市第六人民医院实习生进修生公寓-北门', '22.535678', '113.929324', '南山大道附近', '广东省', '深圳市', '南山区', 'bd09ll', 'BAIDU', 'a987673fbd27f3b2373a71d7', '1533717840', '1534002900');
INSERT INTO `d_task_list` VALUES ('188', '303', '18', '深圳市第六人民医院(南山医院)行政办公楼-北座', '22.536737', '113.929543', '桃园路95号附近', '广东省', '深圳市', '南山区', 'bd09ll', 'BAIDU', '1b35924c263189b011882417', '1533717840', '1534002900');
INSERT INTO `d_task_list` VALUES ('189', '304', '18', '深圳市第六人民医院南山医院雾化治疗室-西北门', '22.537271', '113.930187', '南山大道199号附近', '广东省', '深圳市', '南山区', 'bd09ll', 'BAIDU', 'dfa8f781f69fcfbdda2f28cd', '1533717840', '1534002900');
INSERT INTO `d_task_list` VALUES ('190', '305', '18', '深圳市第六人民医院南山医院门诊部A座-北门', '22.537401', '113.930459', '南山大道与桃园路交叉口西南150米', '广东省', '深圳市', '南山区', 'bd09ll', 'BAIDU', 'a3c39359a70a3cdc2102205c', '1533717840', '1534002900');
INSERT INTO `d_task_list` VALUES ('191', '306', '18', '深圳市第六人民医院(南山医院)3号停车场-出入口', '22.535957', '113.929620', '桃园路89号', '广东省', '深圳市', '南山区', 'bd09ll', 'BAIDU', '05c1c61705a52f70c42ba1ab', '1533717840', '1534002900');
INSERT INTO `d_task_list` VALUES ('192', '307', '18', '深圳市第六人民医院(南山医院)2号停车场-出入口', '22.536992', '113.929838', '南山大道195号附近', '广东省', '深圳市', '南山区', 'bd09ll', 'BAIDU', 'db089b9aec743ff0e60738f4', '1533717840', '1534002900');
INSERT INTO `d_task_list` VALUES ('193', '308', '18', '深圳市第六人民医院南山医院病理科-西北门', '22.536126', '113.930676', '桃园地铁站D口西南200米', '广东省', '深圳市', '南山区', 'bd09ll', 'BAIDU', '393bd6f4b7eecbd80682c295', '1533717840', '1534002900');
INSERT INTO `d_task_list` VALUES ('194', '309', '18', '深圳市第六人民医院(南山医院)-高压氧舱楼', '22.536270', '113.930839', '第六人民医院(南山医院)住院部-第六人民医院(南山医院)', '广东省', '深圳市', '南山区', 'bd09ll', 'BAIDU', '554d8b92e6580325abf0cdfc', '1533717840', '1534002900');
INSERT INTO `d_task_list` VALUES ('195', '310', '18', '深圳市第六人民医院南山医院住院部-东北门', '22.536775', '113.929989', '桃园路89号深圳市第六人民医院南山医院内', '广东省', '深圳市', '南山区', 'bd09ll', 'BAIDU', '0be79f943bbd7b97a5c756c4', '1533717840', '1534002900');
INSERT INTO `d_task_list` VALUES ('196', '311', '18', '蛇口人民医院海湾社区健康服务中心-西北门', '22.497239', '113.937049', '招商路5号', '广东省', '深圳市', '南山区', 'bd09ll', 'BAIDU', '819585cb8e2402b52d4733b9', '1533717840', '1534002900');
INSERT INTO `d_task_list` VALUES ('197', '312', '18', '西丽人民医院松坪山社区健康服务中心(西门)', '22.565474', '113.962650', '松坪街20号', '广东省', '深圳市', '南山区', 'bd09ll', 'BAIDU', '', '1533717840', '1534002900');
INSERT INTO `d_task_list` VALUES ('198', '313', '18', '深圳市第六人民医院南山医院高压氧治疗科-西北门', '22.536154', '113.930676', '广东省深圳市南山区第六人民医院(南山医院)高压氧舱楼1楼', '广东省', '深圳市', '南山区', 'bd09ll', 'BAIDU', '2c0cfbbeabc020aab6147fa5', '1533717840', '1534002900');
INSERT INTO `d_task_list` VALUES ('199', '314', '18', '西丽人民医院丽城社区健康服务中心', '22.588954', '113.965442', '广东省深圳市南山区沿河路10-2号', '广东省', '深圳市', '南山区', 'bd09ll', 'BAIDU', '1d8a132356f0376833169b78', '1533717840', '1534002900');
INSERT INTO `d_task_list` VALUES ('200', '315', '18', '侨城社区健康中心', '22.541326', '113.996831', '华侨城光华街8栋1-2层南山区人民医院', '广东省', '深圳市', '南山区', 'bd09ll', 'BAIDU', 'ec94e3e7bbbba93f1df74d56', '1533717840', '1534002900');
INSERT INTO `d_task_list` VALUES ('201', '316', '18', '门诊部B座', '22.537656', '113.929794', '广东省深圳市南山区第六人民医院南山医院北门', '广东省', '深圳市', '南山区', 'bd09ll', 'BAIDU', '2bdc5bf95c85aaea60685803', '1533717840', '1534002900');
INSERT INTO `d_task_list` VALUES ('202', '317', '18', '院前急救办公室', '22.502526', '113.929286', '深圳市南山区蛇口人民医院2号楼1楼', '广东省', '深圳市', '南山区', 'bd09ll', 'BAIDU', '8a533569b8c1e84696ca0732', '1533717840', '1534002900');
INSERT INTO `d_task_list` VALUES ('203', '318', '18', '港湾社区活动中心', '22.487343', '113.892788', '广东省深圳市南山区蛇口人民医院赤湾社区健康服务中心', '广东省', '深圳市', '南山区', 'bd09ll', 'BAIDU', 'e4eb2ea7e998fee6aecb66a9', '1533717840', '1534002900');
INSERT INTO `d_task_list` VALUES ('204', '319', '18', '西丽人民医院第1门诊部停车场-出入口', '22.580864', '113.956329', '深圳市南山区沙河路16号', '广东省', '深圳市', '南山区', 'bd09ll', 'BAIDU', '4fde3c428945f8f7095cb3a8', '1533717840', '1534002900');
INSERT INTO `d_task_list` VALUES ('205', '320', '18', '蛇口人民医院南水社康健康服务中心-西南门', '22.494099', '113.930050', '深圳市南山区南水路234', '广东省', '深圳市', '南山区', 'bd09ll', 'BAIDU', 'c1bba6586fe4bd5af6c59c7d', '1533717840', '1534002900');
INSERT INTO `d_task_list` VALUES ('206', '321', '18', '深圳市南山区陈康口腔诊所', '22.506710', '113.947834', '广东省中心路曦湾华府裙楼A座13铺(中心路中国银行往里,蛇口人民医院深圳湾社区健康服务中心楼下)', '广东省', '深圳市', '南山区', 'bd09ll', 'BAIDU', 'ad3e603419d5afd5f942b214', '1533717840', '1534002900');
INSERT INTO `d_task_list` VALUES ('207', '322', '18', '桃苑C栋', '22.534227', '113.928998', '广东省深圳市南山区桃苑C栋(深圳市第六人民医院西南)', '广东省', '深圳市', '南山区', 'bd09ll', 'BAIDU', '7941eebd749dee57400245aa', '1533717840', '1534002900');
INSERT INTO `d_task_list` VALUES ('208', '323', '18', '桃苑公寓-北座', '22.533966', '113.930616', '广东省深圳市南山区桃苑公寓(深圳市第六人民医院南)', '广东省', '深圳市', '南山区', 'bd09ll', 'BAIDU', '4305258893a290371894c696', '1533717840', '1534002900');
INSERT INTO `d_task_list` VALUES ('209', '324', '18', '愉康花园-27幢', '22.533790', '113.931861', '广东省深圳市南山区愉康花园(深圳市第六人民医院东南)', '广东省', '深圳市', '南山区', 'bd09ll', 'BAIDU', '977be3b82983a1843904b17a', '1533717840', '1534002900');
INSERT INTO `d_task_list` VALUES ('210', '325', '18', '南山医院家属楼C栋-正门', '22.535485', '113.929177', '深圳市南山区桃园路89号深圳市第六人民医院(南山医院)', '广东省', '深圳市', '南山区', 'bd09ll', 'BAIDU', 'a5789624b442f070eb1e97ce', '1533717840', '1534002900');
INSERT INTO `d_task_list` VALUES ('211', '326', '18', '南山医院家属楼A栋-正门', '22.535403', '113.929770', '深圳市南山区桃园路89号深圳市第六人民医院(南山医院)', '广东省', '深圳市', '南山区', 'bd09ll', 'BAIDU', '38cf75a880e5696d0bccdd07', '1533717840', '1534002900');
INSERT INTO `d_task_list` VALUES ('212', '327', '18', '山顶观光平台', '22.583703', '113.956530', '广东省深圳市南山区石鼓山公园(深圳市西丽人民医院南)', '广东省', '深圳市', '南山区', 'bd09ll', 'BAIDU', 'b51a89988463c5250482c2ca', '1533717840', '1534002900');
INSERT INTO `d_task_list` VALUES ('213', '328', '18', '西丽人民医院宿舍区-西南门', '22.591643', '113.953367', '官龙路8', '广东省', '深圳市', '南山区', 'bd09ll', 'BAIDU', '14955535be2f52b952e5c5c8', '1533717840', '1534002900');
INSERT INTO `d_task_list` VALUES ('214', '329', '18', '西丽人民医院停车场-出入口', '22.586007', '113.957010', '留仙大道2051号西丽人民医院内', '广东省', '深圳市', '南山区', 'bd09ll', 'BAIDU', '3798c1adefe526f9d22dd6eb', '1533717840', '1534002900');
INSERT INTO `d_task_list` VALUES ('215', '330', '18', '常兴广场-东座', '22.539065', '113.928665', '广东省深圳市南山区常兴广场东座(深圳市第六人民医院西北)', '广东省', '深圳市', '南山区', 'bd09ll', 'BAIDU', '4745ab728fbbfcf3ebe58248', '1533717840', '1534002900');
INSERT INTO `d_task_list` VALUES ('216', '331', '18', '蛇口体育中心-网球场', '22.501055', '113.929537', '南山区公园路45号(近蛇口人民医院西区)', '广东省', '深圳市', '南山区', 'bd09ll', 'BAIDU', '750ec165fcb7d0324c3a5905', '1533717840', '1534002900');
INSERT INTO `d_task_list` VALUES ('217', '332', '18', '赢龙数码', '22.534284', '113.931517', '广东省深圳市南山区时代骄子大厦(深圳市第六人民医院东南)', '广东省', '深圳市', '南山区', 'bd09ll', 'BAIDU', 'b7c24b9fc8f969c7f5c59ce5', '1533717840', '1534002900');
INSERT INTO `d_task_list` VALUES ('218', '333', '18', '西丽人民医院第1门诊部-西南门', '22.580823', '113.956286', '沙河路16号西丽人民医院第一门诊部内', '广东省', '深圳市', '南山区', 'bd09ll', 'BAIDU', '00ff2c2cc83d6b03a004645d', '1533717840', '1534002900');
INSERT INTO `d_task_list` VALUES ('219', '334', '18', '西丽人民医院财务综合楼-东南门', '22.580286', '113.958265', '文苑街1号', '广东省', '深圳市', '南山区', 'bd09ll', 'BAIDU', 'dc30ed1cb965ae122902e1f5', '1533717840', '1534002900');
INSERT INTO `d_task_list` VALUES ('220', '335', '18', '荔香亭', '22.584277', '113.956925', '广东省深圳市南山区西丽石鼓山公园(深圳市西丽人民医院东南)', '广东省', '深圳市', '南山区', 'bd09ll', 'BAIDU', '47947f9dbf8e54e82afc4848', '1533717840', '1534002900');
INSERT INTO `d_task_list` VALUES ('221', '336', '18', '留仙苑-7栋', '22.585402', '113.957242', '广东省深圳市南山区沙河西路留仙苑(深圳市西丽人民医院旁)', '广东省', '深圳市', '南山区', 'bd09ll', 'BAIDU', '0c1a4a1a975117862902e169', '1533717840', '1534002900');
INSERT INTO `d_task_list` VALUES ('222', '337', '18', '观景长廊', '22.583903', '113.957114', '广东省深圳市南山区西丽石鼓山公园(深圳市西丽人民医院东南)', '广东省', '深圳市', '南山区', 'bd09ll', 'BAIDU', '9c9f4e6cd1e5fe048b2fb517', '1533717840', '1534002900');
INSERT INTO `d_task_list` VALUES ('223', '338', '18', '公用电话', '22.639869', '113.942220', '广东省深圳市南山区陆地方舟电动车有限公司(西丽人民医院阳光社区健康服务中心东)附近', '广东省', '深圳市', '南山区', 'bd09ll', 'BAIDU', '', '1533717840', '1534002900');
INSERT INTO `d_task_list` VALUES ('224', '339', '18', '南方和顺充电站(南山西丽人民医院)', '22.585925', '113.955152', '深圳市南山区西丽人民医院西北角', '广东省', '深圳市', '南山区', 'bd09ll', 'BAIDU', '', '1533717840', '1534002900');
INSERT INTO `d_task_list` VALUES ('225', '340', '18', '威尔登酒店-洗手间', '22.496282', '113.941013', '深圳市南山区后海大道31号(海岸城、深圳湾口岸蛇口人民医院旁)', '广东省', '深圳市', '南山区', 'bd09ll', 'BAIDU', '4b5167f5b34fab97095cb3c4', '1533717840', '1534002900');
INSERT INTO `d_task_list` VALUES ('226', '341', '18', '南山医院-停车场', '22.536916', '113.930646', '桃园路89号深圳市第六人民医院南山医院内', '广东省', '深圳市', '南山区', 'bd09ll', 'BAIDU', '67694c9b6f6bb5cbae31e5a4', '1533717840', '1534002900');
INSERT INTO `d_task_list` VALUES ('227', '342', '18', '华中科技大学协和深圳医院-急诊科', '22.537344', '113.930816', '深圳市第六人民医院门诊大楼1层', '广东省', '深圳市', '南山区', 'bd09ll', 'BAIDU', 'fc767524b5ce7d874e776e1e', '1533717840', '1534002900');
INSERT INTO `d_task_list` VALUES ('228', '343', '18', '平安银行-24小时自助银行', '22.536641', '113.930365', '第六人民医院门诊大楼', '广东省', '深圳市', '南山区', 'bd09ll', 'BAIDU', 'bf995c54196a3f3b1e58006d', '1533717840', '1534002900');
INSERT INTO `d_task_list` VALUES ('229', '344', '18', '上海银行24小时自助银行(深圳分行)', '22.537289', '113.930827', '桃园路89号第六人民医院南山医院门诊大楼', '广东省', '深圳市', '南山区', 'bd09ll', 'BAIDU', 'dc7eb5ec01510c740bccddac', '1533717840', '1534002900');
INSERT INTO `d_task_list` VALUES ('230', '345', '18', '花果山东社区残疾人康复站', '22.498584', '113.930726', '公园路25-2号蛇口人民医院', '广东省', '深圳市', '南山区', 'bd09ll', 'BAIDU', '3f333bb4496732d53b221c21', '1533717840', '1534002900');
INSERT INTO `d_task_list` VALUES ('231', '346', '18', '南方和顺充电站(西丽人民医院)', '22.585885', '113.956838', '深圳市南山区西丽街道留仙大道103号西丽人民医院', '广东省', '深圳市', '南山区', 'bd09ll', 'BAIDU', '', '1533717840', '1534002900');
INSERT INTO `d_task_list` VALUES ('232', '347', '18', '停车场(西丽人民医院北)', '22.586640', '113.955683', '留仙大道2152附近', '广东省', '深圳市', '南山区', 'bd09ll', 'BAIDU', '56fc14c6e82c99b472455bcc', '1533717840', '1534002900');
INSERT INTO `d_task_list` VALUES ('233', '348', '18', '中国农业银行ATM(西丽人民医院西)', '22.589563', '113.955515', '同沙路27-13号', '广东省', '深圳市', '南山区', 'bd09ll', 'BAIDU', '39c2d2f5194a76de2d4733cd', '1533717840', '1534002900');
INSERT INTO `d_task_list` VALUES ('234', '349', '18', '7天连锁酒店(深圳西丽地铁站店)', '22.587426', '113.954976', '广东省深圳市南山区石鼓路3006号', '广东省', '深圳市', '南山区', 'bd09ll', 'BAIDU', 'e6d59ef58b8fe4f38e55eca4', '1533717840', '1534002900');
INSERT INTO `d_task_list` VALUES ('235', '350', '18', '品正麻辣香锅(西丽店)', '22.586727', '113.956686', '深圳市南山区西丽街道留仙大道嘉兴苑2栋102铺', '广东省', '深圳市', '南山区', 'bd09ll', 'BAIDU', 'ee515661ee0f13f7aa059d4f', '1533717840', '1534002900');
INSERT INTO `d_task_list` VALUES ('236', '351', '18', '小黑牛自助畅吃(乐悦方广场店)', '22.586780', '113.952315', '深圳市南山区西丽街道留仙大道2188号悦方广场F1', '广东省', '深圳市', '南山区', 'bd09ll', 'BAIDU', '8263c02e7d7b5954c829154e', '1533717840', '1534002900');
INSERT INTO `d_task_list` VALUES ('237', '352', '18', '深圳市陆地方舟电动车有限公司(西丽人民医院阳光社区健康服务中心东)', '22.639282', '113.941924', '金筷子附近', '广东省', '深圳市', '南山区', 'bd09ll', 'BAIDU', '', '1533717840', '1534002900');
INSERT INTO `d_task_list` VALUES ('238', '353', '18', '自行车租赁点(南山人民医院荔湾社区健康服务中心东北)', '22.519117', '113.915305', '东滨路222号附近', '广东省', '深圳市', '南山区', 'bd09ll', 'BAIDU', '5f1864f85843f24b2ab97c5e', '1533717840', '1534002900');

-- ----------------------------
-- Table structure for `d_task_user`
-- ----------------------------
DROP TABLE IF EXISTS `d_task_user`;
CREATE TABLE `d_task_user` (
  `tu_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `user_id` int(10) unsigned DEFAULT '0' COMMENT '用户ID',
  `task_id` int(10) unsigned DEFAULT '0' COMMENT '任务ID',
  `receive_date` int(11) unsigned DEFAULT '0' COMMENT '接收时间',
  `status` int(12) DEFAULT '0' COMMENT '1接受中 2完成 3无效 (该状态去除 无用化)',
  `tl_id` int(12) DEFAULT '0',
  `starPoint` decimal(15,4) DEFAULT '0.0000' COMMENT '每次任务星星获取的点数 不包括散落星星',
  PRIMARY KEY (`tu_id`)
) ENGINE=InnoDB AUTO_INCREMENT=63 DEFAULT CHARSET=utf8 COMMENT='任务和用户表';

-- ----------------------------
-- Records of d_task_user
-- ----------------------------
INSERT INTO `d_task_user` VALUES ('1', '13', '2', '1532084147', '1', '9', '0.0000');
INSERT INTO `d_task_user` VALUES ('2', '84', '4', '1532084988', '1', '9', '3.0000');
INSERT INTO `d_task_user` VALUES ('3', '84', '2', '1532136797', '1', '9', '2.0000');
INSERT INTO `d_task_user` VALUES ('4', '84', '1', '1532136828', '1', '5', '1.0230');
INSERT INTO `d_task_user` VALUES ('5', '2', '3', '1532136832', '1', '10', '0.0000');
INSERT INTO `d_task_user` VALUES ('6', '12', '2', '1532137069', '1', '9', '0.0000');
INSERT INTO `d_task_user` VALUES ('7', '12', '4', '1532139884', '1', '11', '0.0000');
INSERT INTO `d_task_user` VALUES ('8', '2', '4', '1532140895', '1', '11', '0.0000');
INSERT INTO `d_task_user` VALUES ('9', '22', '4', '1532142745', '1', '11', '0.0000');
INSERT INTO `d_task_user` VALUES ('10', '22', '2', '1532142866', '1', '9', '0.0000');
INSERT INTO `d_task_user` VALUES ('11', '14', '2', '1532143935', '1', '9', '0.0000');
INSERT INTO `d_task_user` VALUES ('12', '25', '4', '1532166484', '1', '11', '0.0000');
INSERT INTO `d_task_user` VALUES ('13', '86', '4', '1532166540', '1', '11', '2.0000');
INSERT INTO `d_task_user` VALUES ('14', '7', '4', '1532311993', '1', '11', '0.0000');
INSERT INTO `d_task_user` VALUES ('15', '2', '5', '1532312959', '1', '25', '0.0000');
INSERT INTO `d_task_user` VALUES ('16', '33', '4', '1532313404', '1', '11', '0.0000');
INSERT INTO `d_task_user` VALUES ('17', '33', '6', '1532314196', '1', '28', '0.0000');
INSERT INTO `d_task_user` VALUES ('18', '7', '6', '1532314735', '1', '28', '0.0000');
INSERT INTO `d_task_user` VALUES ('19', '35', '6', '1532315059', '1', '26', '0.0000');
INSERT INTO `d_task_user` VALUES ('20', '2', '6', '1532315080', '1', '26', '0.0000');
INSERT INTO `d_task_user` VALUES ('21', '4', '5', '1532316846', '1', '25', '0.0000');
INSERT INTO `d_task_user` VALUES ('22', '35', '7', '1532317024', '1', '29', '0.0000');
INSERT INTO `d_task_user` VALUES ('23', '2', '7', '1532317169', '1', '29', '0.0000');
INSERT INTO `d_task_user` VALUES ('24', '12', '5', '1532318463', '1', '25', '0.0000');
INSERT INTO `d_task_user` VALUES ('25', '12', '7', '1532318467', '1', '29', '0.0000');
INSERT INTO `d_task_user` VALUES ('26', '33', '7', '1532325737', '1', '29', '0.0000');
INSERT INTO `d_task_user` VALUES ('27', '37', '7', '1532327173', '1', '29', '46.0000');
INSERT INTO `d_task_user` VALUES ('28', '37', '2', '1532327180', '1', '9', '0.0000');
INSERT INTO `d_task_user` VALUES ('29', '39', '7', '1532333209', '1', '29', '0.0000');
INSERT INTO `d_task_user` VALUES ('30', '39', '5', '1532333211', '1', '25', '0.0000');
INSERT INTO `d_task_user` VALUES ('31', '58', '7', '1532423841', '1', '29', '0.0000');
INSERT INTO `d_task_user` VALUES ('32', '58', '2', '1532423845', '1', '9', '0.0000');
INSERT INTO `d_task_user` VALUES ('33', '58', '1', '1532423849', '1', '5', '0.0000');
INSERT INTO `d_task_user` VALUES ('34', '58', '3', '1532423853', '1', '10', '0.0000');
INSERT INTO `d_task_user` VALUES ('35', '61', '2', '1532512572', '1', '9', '26.0000');
INSERT INTO `d_task_user` VALUES ('36', '61', '1', '1532512576', '1', '5', '0.0000');
INSERT INTO `d_task_user` VALUES ('37', '61', '3', '1532512579', '1', '10', '0.0000');
INSERT INTO `d_task_user` VALUES ('38', '7', '2', '1532600333', '1', '9', '16.0000');
INSERT INTO `d_task_user` VALUES ('39', '37', '1', '1532662291', '1', '5', '0.0000');
INSERT INTO `d_task_user` VALUES ('40', '37', '3', '1532662292', '1', '10', '0.0000');
INSERT INTO `d_task_user` VALUES ('41', '86', '2', '1532679471', '1', '9', '6.0000');
INSERT INTO `d_task_user` VALUES ('42', '12', '1', '1532680204', '1', '5', '0.0000');
INSERT INTO `d_task_user` VALUES ('43', '4', '3', '1532681529', '1', '10', '0.0000');
INSERT INTO `d_task_user` VALUES ('44', '4', '1', '1532681648', '1', '5', '0.0000');
INSERT INTO `d_task_user` VALUES ('45', '25', '8', '1532682475', '1', '30', '0.0000');
INSERT INTO `d_task_user` VALUES ('46', '2', '8', '1532683111', '1', '30', '0.0000');
INSERT INTO `d_task_user` VALUES ('47', '23', '2', '1532684961', '1', '9', '0.0000');
INSERT INTO `d_task_user` VALUES ('48', '7', '8', '1532686142', '1', '30', '0.0000');
INSERT INTO `d_task_user` VALUES ('49', '37', '8', '1532690126', '1', '30', '0.0000');
INSERT INTO `d_task_user` VALUES ('50', '59', '8', '1532749583', '1', '30', '0.0000');
INSERT INTO `d_task_user` VALUES ('51', '59', '1', '1532749838', '1', '5', '0.0000');
INSERT INTO `d_task_user` VALUES ('52', '59', '3', '1532767012', '1', '10', '0.0000');
INSERT INTO `d_task_user` VALUES ('53', '37', '6', '1533113928', '1', '27', '0.0000');
INSERT INTO `d_task_user` VALUES ('54', '82', '2', '1534314012', '1', '1864', '0.0000');
INSERT INTO `d_task_user` VALUES ('55', '84', '3', '1534755456', '1', '1864', '0.0000');
INSERT INTO `d_task_user` VALUES ('56', '102', '13', '1535709611', '1', '38', '0.0000');
INSERT INTO `d_task_user` VALUES ('57', '99', '13', '1535943678', '1', '38', '0.0000');
INSERT INTO `d_task_user` VALUES ('58', '84', '13', '1535968174', '1', '38', '0.0000');
INSERT INTO `d_task_user` VALUES ('59', '112', '7', '1536029006', '1', '29', '0.0000');
INSERT INTO `d_task_user` VALUES ('60', '112', '8', '1536029011', '1', '30', '0.0000');
INSERT INTO `d_task_user` VALUES ('61', '111', '7', '1536031084', '1', '29', '0.0000');
INSERT INTO `d_task_user` VALUES ('62', '111', '8', '1536031172', '1', '30', '0.0000');

-- ----------------------------
-- Table structure for `d_upload_user`
-- ----------------------------
DROP TABLE IF EXISTS `d_upload_user`;
CREATE TABLE `d_upload_user` (
  `up_id` int(15) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` int(15) DEFAULT '0' COMMENT '上传的内容',
  `path` varchar(125) DEFAULT '' COMMENT '上传的路径',
  `add_detatime` int(30) DEFAULT '0' COMMENT '添加的时间',
  `type` varchar(25) DEFAULT '' COMMENT '类型',
  `name` varchar(125) DEFAULT '' COMMENT '名字',
  PRIMARY KEY (`up_id`)
) ENGINE=InnoDB AUTO_INCREMENT=80 DEFAULT CHARSET=utf8 COMMENT='声音和图片的上传表';

-- ----------------------------
-- Records of d_upload_user
-- ----------------------------
INSERT INTO `d_upload_user` VALUES ('1', '1', '/upload/Face/201807/201807205b518f0939412.jpg', '1532071689', 'Face', '1532071691704');
INSERT INTO `d_upload_user` VALUES ('2', '2', '/upload/Face/201807/201807205b5193ade8f0c.png', '1532072877', 'Face', '180720154757');
INSERT INTO `d_upload_user` VALUES ('3', '2', '/upload/Voice/201807/201807205b5193e012e10.png', '1532072928', 'Voice', '180720154847');
INSERT INTO `d_upload_user` VALUES ('4', '5', '/upload/Face/201807/201807205b519bacc2362.jpg', '1532074924', 'Face', '1532074924737');
INSERT INTO `d_upload_user` VALUES ('5', '7', '/upload/Voice/201807/201807205b51a82177f7c.png', '1532078113', 'Voice', '180720171513');
INSERT INTO `d_upload_user` VALUES ('6', '7', '/upload/Face/201807/201807205b51a8582dec6.png', '1532078168', 'Face', '180720171607');
INSERT INTO `d_upload_user` VALUES ('7', '13', '/upload/Voice/201807/201807205b51bffb10cc3.mp3', '1532084219', 'Voice', '1532084209779');
INSERT INTO `d_upload_user` VALUES ('8', '37', '/upload/Face/201807/201807235b55739fa9d92.png', '1532326815', 'Face', '180723142011');
INSERT INTO `d_upload_user` VALUES ('9', '37', '/upload/Voice/201807/201807235b557b846fbc3.png', '1532328836', 'Voice', '180723145356');
INSERT INTO `d_upload_user` VALUES ('10', '12', '/upload/Face/201807/201807235b5585977a6cd.png', '1532331415', 'Face', '180723153649');
INSERT INTO `d_upload_user` VALUES ('11', '43', '/upload/Face/201807/201807235b55969b92886.jpg', '1532335771', 'Face', '1532335769886');
INSERT INTO `d_upload_user` VALUES ('12', '43', '/upload/Voice/201807/201807235b55979d14058.mp3', '1532336029', 'Voice', '1532336006075');
INSERT INTO `d_upload_user` VALUES ('13', '41', '/upload/Face/201807/201807235b5597c53a5dd.jpg', '1532336069', 'Face', '1532336068130');
INSERT INTO `d_upload_user` VALUES ('14', '42', '/upload/Face/201807/201807235b55995828875.jpg', '1532336472', 'Face', '1532336470357');
INSERT INTO `d_upload_user` VALUES ('15', '39', '/upload/Face/201807/201807235b559a056cb11.jpg', '1532336645', 'Face', '1532336644601');
INSERT INTO `d_upload_user` VALUES ('16', '42', '/upload/Voice/201807/201807235b559ad7f3328.mp3', '1532336855', 'Voice', '1532336852302');
INSERT INTO `d_upload_user` VALUES ('17', '33', '/upload/Face/201807/201807235b559db01eacc.png', '1532337584', 'Face', '180723171942');
INSERT INTO `d_upload_user` VALUES ('18', '33', '/upload/Voice/201807/201807235b559dbd7983a.png', '1532337597', 'Voice', '180723171957');
INSERT INTO `d_upload_user` VALUES ('19', '4', '/upload/Face/201807/201807235b55a900c1ca4.jpg', '1532340480', 'Face', '1532340480452');
INSERT INTO `d_upload_user` VALUES ('20', '40', '/upload/Face/201807/201807235b55a9377f7df.png', '1532340535', 'Face', '180723180853');
INSERT INTO `d_upload_user` VALUES ('21', '4', '/upload/Voice/201807/201807235b55ab495ae55.mp3', '1532341065', 'Voice', '1532341061415');
INSERT INTO `d_upload_user` VALUES ('22', '27', '/upload/Face/201807/201807235b55aba33cc66.png', '1532341155', 'Face', '180723181914');
INSERT INTO `d_upload_user` VALUES ('25', '53', '/upload/Voice/201807/201807255b58349e8b271.jpg', '1532507294', 'Voice', '8852651530081198');
INSERT INTO `d_upload_user` VALUES ('26', '61', '/upload/Face/201807/201807265b5941dcf0348.png', '1532576220', 'Face', '180726113700');
INSERT INTO `d_upload_user` VALUES ('27', '23', '/upload/Face/201807/201807275b5adae89935b.png', '1532680936', 'Face', '180727164213');
INSERT INTO `d_upload_user` VALUES ('43', '86', '/upload/Face/201808//MOg70GaU_1534903105011.txt', '1534903102', 'Face', 'MOg70GaU_1534903105011.txt');
INSERT INTO `d_upload_user` VALUES ('77', '83', '/upload/Face/201808/WXEVVTpv_1535624197174.png', '1535624197', 'Face', 'WXEVVTpv_1535624197174.png');
INSERT INTO `d_upload_user` VALUES ('78', '82', '/upload/Face/201808/wfyykSpl_1535626409963.png', '1535626409', 'Face', 'wfyykSpl_1535626409963.png');
INSERT INTO `d_upload_user` VALUES ('79', '111', '/upload/Face/201809/uIguFhgO_1535962482358.png', '1535962482', 'Face', 'uIguFhgO_1535962482358.png');

-- ----------------------------
-- Table structure for `d_user`
-- ----------------------------
DROP TABLE IF EXISTS `d_user`;
CREATE TABLE `d_user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '客户ID',
  `user_key` varchar(50) DEFAULT NULL COMMENT '用户编码，或者KEY值',
  `phone` varchar(50) DEFAULT '' COMMENT '电话(账号)',
  `user_nickname` varchar(50) DEFAULT '' COMMENT '客户昵称',
  `password` varchar(50) NOT NULL COMMENT '密码',
  `pay_password` varchar(50) DEFAULT NULL COMMENT '客户交易密码',
  `user_status` tinyint(1) DEFAULT '1' COMMENT '客户状态 1 正常 0 注销',
  `real_name` varchar(200) NOT NULL DEFAULT '' COMMENT '客户姓名',
  `user_sex` tinyint(4) DEFAULT '0' COMMENT '性别 0 未知 1 男 2 女',
  `head_url` varchar(255) DEFAULT '' COMMENT '头像路径',
  `phone_status` tinyint(1) DEFAULT '0' COMMENT '电话认证状态 0 未认证，1已认证',
  `reg_datetime` int(11) NOT NULL COMMENT '注册时间',
  `reg_terminal` tinyint(4) DEFAULT '1' COMMENT '注册终端 1 PC 2 安卓 3 IOS 4 微信',
  `reg_ip` varchar(20) DEFAULT '' COMMENT '注册IP',
  `open_id` varchar(50) DEFAULT '' COMMENT '微信编码ID',
  `edit_datetime` int(11) DEFAULT NULL COMMENT '修改时间',
  `invitation_code` varchar(30) DEFAULT '' COMMENT '邀请吗',
  `user_token` varchar(100) DEFAULT '' COMMENT '用户Token',
  PRIMARY KEY (`user_id`),
  KEY `user_phone` (`phone`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=113 DEFAULT CHARSET=utf8 COMMENT='用户表';

-- ----------------------------
-- Records of d_user
-- ----------------------------
INSERT INTO `d_user` VALUES ('1', '0f4aca4f48bcfb469906d026a37deab3', '18866666666', '', '9fe00b7142d4f0308132f959b9372de9', '9cbf8a4dcb8e30682b927f352d6559a0', '1', '', '0', '', '1', '1532071647', '2', '192.168.11.148', '', '1532074163', 'ABCDFE', 'WAAPP-1532071647930-4059226701438165');
INSERT INTO `d_user` VALUES ('82', '4be8b13902b56932da46c694478c0cd5', '17520487755', '', 'a13d0edbf39da0f87bcbcf5829fed7cc', 'f6c65667c1b7f780ea31287b6cd7c03f', '1', '', '0', '', '1', '1533961230', '3', '192.168.11.126', '', '1535708143', 'AFCBDE', 'WAAPP-1532321532360-4713469053593348');
INSERT INTO `d_user` VALUES ('83', '0f65ad153971eb1882b77613683eae18', '18501370070', '', '9fe00b7142d4f0308132f959b9372de9', 'f6c65667c1b7f780ea31287b6cd7c03f', '1', '', '0', '', '1', '1533961715', '3', '192.168.11.131', '', '1533961715', 'FECBDA', 'WAAPP-1533800704354-8820172084260396');
INSERT INTO `d_user` VALUES ('84', 'e01cb9b6b28c6fa777b725a83fdddea9', '13111111111', '', 'efb0e3c43486a020c97a3ce889128c12', 'f34c7f88e29653b80504b421c04a2439', '1', '', '0', '', '1', '1534126989', '3', '192.168.11.105', '', '2137678', 'EDCBAF', 'WAAPP-1532075274362-4706642388541853');
INSERT INTO `d_user` VALUES ('99', '0e93b081c381ef9384d12235c9b1ad6b', '15013851179', '', '474d6ccd1a06517443baec016eb5ca25', 'dc483e80a7a0bd9ef71d8cf973673924', '1', '', '0', '', '1', '1535706899', '3', '192.168.11.141', '', '-1891392', 'DBFEAC', 'WAAPP-1533290536964-2388794200137839');
INSERT INTO `d_user` VALUES ('102', '9d037d01bb7ee6cefa43604963a5c6ad', '13111111113', '', 'efb0e3c43486a020c97a3ce889128c12', 'de34f856d9e3591013cea70fc52af18c', '1', '', '0', '', '1', '1535709527', '3', '192.168.11.141', '', '-1888764', 'DBEACF', 'WAAPP-1535709527314-4504604528437791');
INSERT INTO `d_user` VALUES ('111', '17ed6bc2625ac41bfe9957cfd91e3aaf', '15768118003', '', 'efb0e3c43486a020c97a3ce889128c12', 'f34c7f88e29653b80504b421c04a2439', '1', '', '0', '', '1', '1535942926', '3', '10.10.110.100', '', '1535959063', 'DEFABC', 'WAAPP-1532485304679-2653710949802137');
INSERT INTO `d_user` VALUES ('112', 'edc279dc381c540bb5a9a3037cffa46c', '15013851180', '', 'efb0e3c43486a020c97a3ce889128c12', 'de34f856d9e3591013cea70fc52af18c', '1', '', '0', '', '1', '1535967570', '3', '192.168.11.141', '', '1535967570', 'DFCABE', 'WAAPP-1535967570523-1336937729725077');

-- ----------------------------
-- Table structure for `d_user_action_log`
-- ----------------------------
DROP TABLE IF EXISTS `d_user_action_log`;
CREATE TABLE `d_user_action_log` (
  `log_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL COMMENT '用户ID',
  `phone` varchar(60) NOT NULL DEFAULT '' COMMENT '用户名/手机号码',
  `log_ip` varchar(20) DEFAULT '' COMMENT 'IP',
  `action` varchar(100) DEFAULT NULL COMMENT '用户操作',
  `ret` varchar(50) DEFAULT NULL COMMENT '结果(如；登陆成功|登录失败)',
  `terminal` tinyint(4) DEFAULT '0' COMMENT '操作终端 1 PC 2 安卓 3 IOS 4 微信'',',
  `add_datetime` int(11) unsigned DEFAULT '0' COMMENT '添加时间',
  PRIMARY KEY (`log_id`)
) ENGINE=InnoDB AUTO_INCREMENT=579 DEFAULT CHARSET=utf8 COMMENT='用户登录表';

-- ----------------------------
-- Records of d_user_action_log
-- ----------------------------
INSERT INTO `d_user_action_log` VALUES ('425', null, '13111111111', '192.168.11.103', '用户登录', '登录失败', '3', '1535357319');
INSERT INTO `d_user_action_log` VALUES ('426', null, '13111111111', '192.168.11.103', '用户登录', '登录失败', '3', '1535357372');
INSERT INTO `d_user_action_log` VALUES ('427', null, '13111111111', '192.168.11.103', '用户登录', '登录失败', '3', '1535357372');
INSERT INTO `d_user_action_log` VALUES ('428', null, '13111111111', '192.168.11.103', '用户登录', '登录失败', '3', '1535357372');
INSERT INTO `d_user_action_log` VALUES ('429', null, '13111111111', '192.168.11.103', '用户登录', '登录失败', '3', '1535357372');
INSERT INTO `d_user_action_log` VALUES ('430', null, '13111111111', '192.168.11.103', '用户登录', '登录失败', '3', '1535357373');
INSERT INTO `d_user_action_log` VALUES ('431', '84', '13111111111', '192.168.11.103', '用户登录', '登录成功', '3', '1535357411');
INSERT INTO `d_user_action_log` VALUES ('432', '84', '13111111111', '192.168.11.103', '用户登录', '登录成功', '3', '1535357683');
INSERT INTO `d_user_action_log` VALUES ('433', null, '13111111111', '192.168.11.103', '用户登录', '登录失败', '3', '1535358327');
INSERT INTO `d_user_action_log` VALUES ('434', null, '13111111111', '192.168.11.103', '用户登录', '登录失败', '3', '1535358331');
INSERT INTO `d_user_action_log` VALUES ('435', null, '13111111111', '192.168.11.103', '用户登录', '登录失败', '3', '1535358331');
INSERT INTO `d_user_action_log` VALUES ('436', null, '13111111111', '192.168.11.103', '用户登录', '登录失败', '3', '1535358331');
INSERT INTO `d_user_action_log` VALUES ('437', null, '13111111111', '192.168.11.103', '用户登录', '登录失败', '3', '1535358332');
INSERT INTO `d_user_action_log` VALUES ('438', null, '13111111111', '192.168.11.103', '用户登录', '登录失败', '3', '1535358332');
INSERT INTO `d_user_action_log` VALUES ('439', null, '13111111111', '192.168.11.103', '用户登录', '登录失败', '3', '1535358332');
INSERT INTO `d_user_action_log` VALUES ('440', null, '13111111111', '192.168.11.103', '用户登录', '登录失败', '3', '1535358332');
INSERT INTO `d_user_action_log` VALUES ('441', null, '13111111111', '192.168.11.103', '用户登录', '登录失败', '3', '1535358332');
INSERT INTO `d_user_action_log` VALUES ('442', null, '13111111111', '192.168.11.103', '用户登录', '登录失败', '3', '1535358338');
INSERT INTO `d_user_action_log` VALUES ('443', '84', '13111111111', '192.168.11.103', '用户登录', '登录成功', '3', '1535358404');
INSERT INTO `d_user_action_log` VALUES ('444', '84', '13111111111', '192.168.11.103', '用户登录', '登录成功', '3', '1535359066');
INSERT INTO `d_user_action_log` VALUES ('445', '84', '13111111111', '192.168.11.103', '用户登录', '登录成功', '3', '1535424265');
INSERT INTO `d_user_action_log` VALUES ('446', '84', '13111111111', '192.168.11.103', '用户登录', '登录成功', '3', '1535425218');
INSERT INTO `d_user_action_log` VALUES ('447', '84', '13111111111', '192.168.11.141', '用户登录', '登录成功', '3', '1535435177');
INSERT INTO `d_user_action_log` VALUES ('448', '84', '13111111111', '192.168.11.103', '用户登录', '登录成功', '3', '1535435927');
INSERT INTO `d_user_action_log` VALUES ('449', null, '13111111111', '192.168.11.103', '用户登录', '登录失败', '3', '1535440953');
INSERT INTO `d_user_action_log` VALUES ('450', null, '13111111111', '192.168.11.103', '用户登录', '登录失败', '3', '1535440960');
INSERT INTO `d_user_action_log` VALUES ('451', '84', '13111111111', '192.168.11.103', '用户登录', '登录成功', '3', '1535440975');
INSERT INTO `d_user_action_log` VALUES ('452', null, '13111111111', '192.168.11.141', '用户登录', '登录失败', '3', '1535450540');
INSERT INTO `d_user_action_log` VALUES ('453', null, '13111111111', '192.168.11.141', '用户登录', '登录失败', '3', '1535450848');
INSERT INTO `d_user_action_log` VALUES ('454', null, '13111111111', '192.168.11.141', '用户登录', '登录失败', '3', '1535450880');
INSERT INTO `d_user_action_log` VALUES ('455', null, '13111111111', '192.168.11.141', '用户登录', '登录失败', '3', '1535450934');
INSERT INTO `d_user_action_log` VALUES ('456', null, '13111111111', '192.168.11.141', '用户登录', '登录失败', '3', '1535450937');
INSERT INTO `d_user_action_log` VALUES ('457', null, '13111111111', '192.168.11.141', '用户登录', '登录失败', '3', '1535450959');
INSERT INTO `d_user_action_log` VALUES ('458', null, '13111111111', '192.168.11.141', '用户登录', '登录失败', '3', '1535450984');
INSERT INTO `d_user_action_log` VALUES ('459', '84', '13111111111', '192.168.11.141', '用户登录', '登录成功', '3', '1535451019');
INSERT INTO `d_user_action_log` VALUES ('460', '84', '13111111111', '192.168.11.103', '用户登录', '登录成功', '3', '1535452344');
INSERT INTO `d_user_action_log` VALUES ('461', '84', '13111111111', '10.10.110.101', '用户登录', '登录成功', '3', '1535509820');
INSERT INTO `d_user_action_log` VALUES ('462', '84', '13111111111', '192.168.11.141', '用户登录', '登录成功', '3', '1535618413');
INSERT INTO `d_user_action_log` VALUES ('463', '84', '13111111111', '192.168.11.119', '用户登录', '登录成功', '3', '1535618783');
INSERT INTO `d_user_action_log` VALUES ('464', '84', '13111111111', '192.168.11.119', '用户登录', '登录成功', '3', '1535618888');
INSERT INTO `d_user_action_log` VALUES ('465', '84', '13111111111', '192.168.11.119', '用户登录', '登录成功', '3', '1535619267');
INSERT INTO `d_user_action_log` VALUES ('466', '84', '13111111111', '192.168.11.119', '用户登录', '登录成功', '3', '1535619320');
INSERT INTO `d_user_action_log` VALUES ('467', '84', '13111111111', '192.168.11.141', '用户登录', '登录成功', '3', '1535619455');
INSERT INTO `d_user_action_log` VALUES ('468', '84', '13111111111', '192.168.11.119', '用户登录', '登录成功', '3', '1535619707');
INSERT INTO `d_user_action_log` VALUES ('469', '84', '13111111111', '192.168.11.141', '用户登录', '登录成功', '3', '1535619841');
INSERT INTO `d_user_action_log` VALUES ('470', '84', '13111111111', '192.168.11.119', '用户登录', '登录成功', '3', '1535620837');
INSERT INTO `d_user_action_log` VALUES ('471', '84', '13111111111', '192.168.11.119', '用户登录', '登录成功', '3', '1535621133');
INSERT INTO `d_user_action_log` VALUES ('472', '84', '13111111111', '10.10.110.102', '用户登录', '登录成功', '3', '1535621206');
INSERT INTO `d_user_action_log` VALUES ('473', '84', '13111111111', '192.168.11.119', '用户登录', '登录成功', '3', '1535621387');
INSERT INTO `d_user_action_log` VALUES ('474', '84', '13111111111', '192.168.11.119', '用户登录', '登录成功', '3', '1535621902');
INSERT INTO `d_user_action_log` VALUES ('475', '84', '13111111111', '10.10.110.102', '用户登录', '登录成功', '3', '1535622260');
INSERT INTO `d_user_action_log` VALUES ('476', '84', '13111111111', '192.168.11.141', '用户登录', '登录成功', '3', '1535622478');
INSERT INTO `d_user_action_log` VALUES ('477', '84', '13111111111', '192.168.11.119', '用户登录', '登录成功', '3', '1535622945');
INSERT INTO `d_user_action_log` VALUES ('478', '84', '13111111111', '192.168.11.119', '用户登录', '登录成功', '3', '1535623102');
INSERT INTO `d_user_action_log` VALUES ('479', '84', '13111111111', '192.168.11.141', '用户登录', '登录成功', '3', '1535623639');
INSERT INTO `d_user_action_log` VALUES ('480', '83', '18501370070', '192.168.11.141', '用户登录', '登录成功', '3', '1535624175');
INSERT INTO `d_user_action_log` VALUES ('481', '83', '18501370070', '192.168.11.119', '用户登录', '登录成功', '3', '1535624348');
INSERT INTO `d_user_action_log` VALUES ('482', '83', '18501370070', '192.168.11.119', '用户登录', '登录成功', '3', '1535624488');
INSERT INTO `d_user_action_log` VALUES ('483', '83', '18501370070', '192.168.11.119', '用户登录', '登录成功', '3', '1535624791');
INSERT INTO `d_user_action_log` VALUES ('484', '83', '18501370070', '192.168.11.119', '用户登录', '登录成功', '3', '1535624904');
INSERT INTO `d_user_action_log` VALUES ('485', '83', '18501370070', '192.168.11.141', '用户登录', '登录成功', '3', '1535625982');
INSERT INTO `d_user_action_log` VALUES ('486', '84', '13111111111', '192.168.11.141', '用户登录', '登录成功', '3', '1535626075');
INSERT INTO `d_user_action_log` VALUES ('487', null, '17520487755', '192.168.11.141', '用户登录', '登录失败', '3', '1535626242');
INSERT INTO `d_user_action_log` VALUES ('488', null, '17520487755', '192.168.11.141', '用户登录', '登录失败', '3', '1535626258');
INSERT INTO `d_user_action_log` VALUES ('489', null, '17520487755', '192.168.11.141', '用户登录', '登录失败', '3', '1535626275');
INSERT INTO `d_user_action_log` VALUES ('490', null, '17520487755', '192.168.11.141', '用户登录', '登录失败', '3', '1535626279');
INSERT INTO `d_user_action_log` VALUES ('491', null, '15326253563', '192.168.11.141', '用户登录', '登录失败', '3', '1535626317');
INSERT INTO `d_user_action_log` VALUES ('492', null, '15326253563', '192.168.11.141', '用户登录', '登录失败', '3', '1535626324');
INSERT INTO `d_user_action_log` VALUES ('493', '1', '15326253563', '192.168.11.141', '用户登录', '登录成功', '3', '1535626364');
INSERT INTO `d_user_action_log` VALUES ('494', '82', '17520487755', '192.168.11.141', '用户登录', '登录成功', '3', '1535626401');
INSERT INTO `d_user_action_log` VALUES ('495', '84', '13111111111', '192.168.11.141', '用户登录', '登录成功', '3', '1535626738');
INSERT INTO `d_user_action_log` VALUES ('496', '1', '15326253563', '192.168.11.141', '用户登录', '登录成功', '3', '1535627937');
INSERT INTO `d_user_action_log` VALUES ('497', '82', '17520487755', '192.168.11.141', '用户登录', '登录成功', '3', '1535627997');
INSERT INTO `d_user_action_log` VALUES ('498', '83', '18501370070', '192.168.11.119', '用户登录', '登录成功', '3', '1535628366');
INSERT INTO `d_user_action_log` VALUES ('499', '84', '13111111111', '192.168.11.141', '用户登录', '登录成功', '3', '1535684937');
INSERT INTO `d_user_action_log` VALUES ('500', '84', '13111111111', '192.168.11.119', '用户登录', '登录成功', '3', '1535685184');
INSERT INTO `d_user_action_log` VALUES ('501', '84', '13111111111', '192.168.11.119', '用户登录', '登录成功', '3', '1535685289');
INSERT INTO `d_user_action_log` VALUES ('502', '84', '13111111111', '192.168.11.141', '用户登录', '登录成功', '3', '1535687677');
INSERT INTO `d_user_action_log` VALUES ('503', '84', '13111111111', '192.168.11.119', '用户登录', '登录成功', '3', '1535693972');
INSERT INTO `d_user_action_log` VALUES ('504', '84', '13111111111', '192.168.11.141', '用户登录', '登录成功', '3', '1535694250');
INSERT INTO `d_user_action_log` VALUES ('505', '83', '18501370070', '192.168.11.141', '用户登录', '登录成功', '3', '1535700273');
INSERT INTO `d_user_action_log` VALUES ('506', null, '18866666666', '192.168.11.141', '用户登录', '登录失败', '3', '1535700712');
INSERT INTO `d_user_action_log` VALUES ('507', null, '18866666666', '192.168.11.141', '用户登录', '登录失败', '3', '1535700747');
INSERT INTO `d_user_action_log` VALUES ('508', '1', '18866666666', '192.168.11.141', '用户登录', '登录成功', '3', '1535700851');
INSERT INTO `d_user_action_log` VALUES ('509', '1', '18866666666', '192.168.11.141', '用户登录', '登录成功', '3', '1535701200');
INSERT INTO `d_user_action_log` VALUES ('510', '84', '13111111111', '192.168.11.119', '用户登录', '登录成功', '3', '1535701417');
INSERT INTO `d_user_action_log` VALUES ('511', '1', '18866666666', '192.168.11.119', '用户登录', '登录失败', '3', '1535701583');
INSERT INTO `d_user_action_log` VALUES ('512', '1', '18866666666', '192.168.11.119', '用户登录', '登录失败', '3', '1535701753');
INSERT INTO `d_user_action_log` VALUES ('513', '1', '18866666666', '192.168.11.141', '用户登录', '登录成功', '3', '1535701790');
INSERT INTO `d_user_action_log` VALUES ('514', '1', '18866666666', '192.168.11.119', '用户登录', '登录失败', '3', '1535701797');
INSERT INTO `d_user_action_log` VALUES ('515', '82', '17520487755', '192.168.11.119', '用户登录', '登录成功', '3', '1535701851');
INSERT INTO `d_user_action_log` VALUES ('516', '1', '18866666666', '192.168.11.119', '用户登录', '登录失败', '3', '1535701897');
INSERT INTO `d_user_action_log` VALUES ('517', '84', '13111111111', '192.168.11.103', '用户登录', '登录成功', '3', '1535702126');
INSERT INTO `d_user_action_log` VALUES ('518', '82', '17520487755', '192.168.11.141', '用户登录', '登录成功', '3', '1535702563');
INSERT INTO `d_user_action_log` VALUES ('519', '83', '18501370070', '192.168.11.141', '用户登录', '登录成功', '3', '1535702606');
INSERT INTO `d_user_action_log` VALUES ('520', '1', '18866666666', '192.168.11.141', '用户登录', '登录成功', '3', '1535702685');
INSERT INTO `d_user_action_log` VALUES ('521', '1', '18866666666', '192.168.11.141', '用户登录', '登录成功', '3', '1535704655');
INSERT INTO `d_user_action_log` VALUES ('522', '84', '13111111111', '192.168.11.103', '用户登录', '登录成功', '3', '1535707988');
INSERT INTO `d_user_action_log` VALUES ('523', '100', '15768118003', '192.168.11.103', '用户登录', '登录成功', '3', '1535708060');
INSERT INTO `d_user_action_log` VALUES ('524', '99', '15013851179', '192.168.11.141', '用户登录', '登录成功', '3', '1535708109');
INSERT INTO `d_user_action_log` VALUES ('525', '82', '17520487755', '192.168.11.126', '用户登录', '登录成功', '3', '1535708149');
INSERT INTO `d_user_action_log` VALUES ('526', '84', '13111111111', '192.168.11.141', '用户登录', '登录成功', '3', '1535709749');
INSERT INTO `d_user_action_log` VALUES ('527', '102', '13111111113', '192.168.11.141', '用户登录', '登录成功', '3', '1535709985');
INSERT INTO `d_user_action_log` VALUES ('528', '84', '13111111111', '192.168.11.141', '用户登录', '登录成功', '3', '1535710127');
INSERT INTO `d_user_action_log` VALUES ('529', '103', '15768118003', '192.168.11.103', '用户登录', '登录成功', '3', '1535710187');
INSERT INTO `d_user_action_log` VALUES ('530', '104', '15768118003', '192.168.11.103', '用户登录', '登录成功', '3', '1535710302');
INSERT INTO `d_user_action_log` VALUES ('531', '109', '15768118003', '192.168.11.103', '用户登录', '登录成功', '3', '1535712320');
INSERT INTO `d_user_action_log` VALUES ('532', null, '13111111113', '192.168.11.141', '用户登录', '登录失败', '3', '1535941156');
INSERT INTO `d_user_action_log` VALUES ('533', null, '13111111113', '192.168.11.141', '用户登录', '登录失败', '3', '1535941167');
INSERT INTO `d_user_action_log` VALUES ('534', '102', '13111111113', '192.168.11.141', '用户登录', '登录成功', '3', '1535941179');
INSERT INTO `d_user_action_log` VALUES ('535', '84', '13111111111', '192.168.11.119', '用户登录', '登录成功', '3', '1535942306');
INSERT INTO `d_user_action_log` VALUES ('536', '84', '13111111111', '192.168.11.119', '用户登录', '登录成功', '3', '1535942477');
INSERT INTO `d_user_action_log` VALUES ('537', '84', '13111111111', '192.168.11.141', '用户登录', '登录成功', '3', '1535943002');
INSERT INTO `d_user_action_log` VALUES ('538', '84', '13111111111', '192.168.11.119', '用户登录', '登录成功', '3', '1535943118');
INSERT INTO `d_user_action_log` VALUES ('539', '111', '15768118003', '10.10.110.100', '用户登录', '登录成功', '3', '1535943590');
INSERT INTO `d_user_action_log` VALUES ('540', null, '15013851179', '192.168.11.141', '用户登录', '登录失败', '3', '1535943644');
INSERT INTO `d_user_action_log` VALUES ('541', '99', '15013851179', '192.168.11.141', '用户登录', '登录成功', '3', '1535943670');
INSERT INTO `d_user_action_log` VALUES ('542', '111', '15768118003', '10.10.110.100', '用户登录', '登录成功', '3', '1535943673');
INSERT INTO `d_user_action_log` VALUES ('543', null, '15768118003', '10.10.110.100', '用户登录', '登录失败', '3', '1535953470');
INSERT INTO `d_user_action_log` VALUES ('544', '111', '15768118003', '10.10.110.100', '用户登录', '登录成功', '3', '1535953479');
INSERT INTO `d_user_action_log` VALUES ('545', null, '15768118003', '10.10.110.100', '用户登录', '登录失败', '3', '1535953770');
INSERT INTO `d_user_action_log` VALUES ('546', '111', '15768118003', '10.10.110.100', '用户登录', '登录成功', '3', '1535953793');
INSERT INTO `d_user_action_log` VALUES ('547', '111', '15768118003', '10.10.110.100', '用户登录', '登录成功', '3', '1535954991');
INSERT INTO `d_user_action_log` VALUES ('548', '84', '13111111111', '192.168.11.119', '用户登录', '登录成功', '3', '1535956881');
INSERT INTO `d_user_action_log` VALUES ('549', '84', '13111111111', '192.168.11.141', '用户登录', '登录成功', '3', '1535957031');
INSERT INTO `d_user_action_log` VALUES ('550', '111', '15768118003', '192.168.11.153', '用户登录', '登录成功', '3', '1535959030');
INSERT INTO `d_user_action_log` VALUES ('551', null, '15768118003', '192.168.11.153', '用户登录', '登录失败', '3', '1535959114');
INSERT INTO `d_user_action_log` VALUES ('552', '111', '15768118003', '192.168.11.153', '用户登录', '登录成功', '3', '1535959124');
INSERT INTO `d_user_action_log` VALUES ('553', '84', '13111111111', '192.168.11.141', '用户登录', '登录成功', '3', '1535960993');
INSERT INTO `d_user_action_log` VALUES ('554', '84', '13111111111', '192.168.11.141', '用户登录', '登录成功', '3', '1535961016');
INSERT INTO `d_user_action_log` VALUES ('555', '84', '13111111111', '192.168.11.141', '用户登录', '登录成功', '3', '1535961187');
INSERT INTO `d_user_action_log` VALUES ('556', null, '15768118003', '192.168.11.153', '用户登录', '登录失败', '3', '1535962397');
INSERT INTO `d_user_action_log` VALUES ('557', '111', '15768118003', '192.168.11.153', '用户登录', '登录成功', '3', '1535962410');
INSERT INTO `d_user_action_log` VALUES ('558', null, '15768118003', '192.168.11.153', '用户登录', '登录失败', '3', '1535965460');
INSERT INTO `d_user_action_log` VALUES ('559', '111', '15768118003', '192.168.11.153', '用户登录', '登录成功', '3', '1535965481');
INSERT INTO `d_user_action_log` VALUES ('560', '84', '13111111111', '192.168.11.141', '用户登录', '登录成功', '3', '1535967162');
INSERT INTO `d_user_action_log` VALUES ('561', '112', '15013851180', '192.168.11.141', '用户登录', '登录成功', '3', '1536029885');
INSERT INTO `d_user_action_log` VALUES ('562', '84', '13111111111', '192.168.11.119', '用户登录', '登录成功', '3', '1536032038');
INSERT INTO `d_user_action_log` VALUES ('563', '84', '13111111111', '192.168.11.119', '用户登录', '登录成功', '3', '1536032196');
INSERT INTO `d_user_action_log` VALUES ('564', '84', '13111111111', '192.168.11.119', '用户登录', '登录成功', '3', '1536032273');
INSERT INTO `d_user_action_log` VALUES ('565', null, '15013851179', '192.168.11.119', '用户登录', '登录失败', '3', '1536033106');
INSERT INTO `d_user_action_log` VALUES ('566', '112', '15013851180', '192.168.11.119', '用户登录', '登录成功', '3', '1536033133');
INSERT INTO `d_user_action_log` VALUES ('567', '112', '15013851180', '192.168.11.141', '用户登录', '登录成功', '3', '1536033225');
INSERT INTO `d_user_action_log` VALUES ('568', '112', '15013851180', '192.168.11.119', '用户登录', '登录成功', '3', '1536034439');
INSERT INTO `d_user_action_log` VALUES ('569', '112', '15013851180', '192.168.11.141', '用户登录', '登录成功', '3', '1536043196');
INSERT INTO `d_user_action_log` VALUES ('570', '112', '15013851180', '192.168.11.119', '用户登录', '登录成功', '3', '1536043691');
INSERT INTO `d_user_action_log` VALUES ('571', '112', '15013851180', '192.168.11.119', '用户登录', '登录成功', '3', '1536043776');
INSERT INTO `d_user_action_log` VALUES ('572', '112', '15013851180', '192.168.11.119', '用户登录', '登录成功', '3', '1536043886');
INSERT INTO `d_user_action_log` VALUES ('573', '112', '15013851180', '192.168.11.119', '用户登录', '登录成功', '3', '1536044253');
INSERT INTO `d_user_action_log` VALUES ('574', '112', '15013851180', '192.168.11.119', '用户登录', '登录成功', '3', '1536044476');
INSERT INTO `d_user_action_log` VALUES ('575', '112', '15013851180', '192.168.11.119', '用户登录', '登录成功', '3', '1536044691');
INSERT INTO `d_user_action_log` VALUES ('576', '112', '15013851180', '192.168.11.119', '用户登录', '登录成功', '3', '1536045813');
INSERT INTO `d_user_action_log` VALUES ('577', '112', '15013851180', '192.168.11.119', '用户登录', '登录成功', '3', '1536045897');
INSERT INTO `d_user_action_log` VALUES ('578', '112', '15013851180', '192.168.11.141', '用户登录', '登录成功', '3', '1536046141');

-- ----------------------------
-- Table structure for `d_user_address`
-- ----------------------------
DROP TABLE IF EXISTS `d_user_address`;
CREATE TABLE `d_user_address` (
  `address_id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '用户ID',
  `province` smallint(5) unsigned NOT NULL DEFAULT '0' COMMENT '省份',
  `province_name` varchar(50) NOT NULL DEFAULT '' COMMENT '省份名称',
  `city` smallint(5) unsigned NOT NULL DEFAULT '0' COMMENT '城市',
  `city_name` varchar(50) NOT NULL DEFAULT '' COMMENT '城市名称',
  `district` smallint(5) unsigned NOT NULL DEFAULT '0' COMMENT '区',
  `district_name` varchar(50) NOT NULL DEFAULT '' COMMENT '区名称',
  `address` varchar(255) NOT NULL DEFAULT '' COMMENT '详细地址',
  `zip_code` varchar(60) NOT NULL DEFAULT '' COMMENT '邮政编码',
  `is_default` tinyint(3) NOT NULL DEFAULT '0' COMMENT '默认地址【1：默认地址，0:非】',
  `work_unit` varchar(255) DEFAULT '' COMMENT '工作字段',
  `work_address` varchar(255) DEFAULT '' COMMENT '工作地址',
  `add_datetime` int(30) DEFAULT '0' COMMENT '添加时间',
  PRIMARY KEY (`address_id`),
  KEY `fk_mall_address_userID` (`user_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='用户地址信息';

-- ----------------------------
-- Records of d_user_address
-- ----------------------------
INSERT INTO `d_user_address` VALUES ('1', '7', '2', '北京', '52', '北京', '500', '东城区', '在一起', '', '1', '在一起', '这里', '1532078267');
INSERT INTO `d_user_address` VALUES ('2', '41', '2', '北京', '52', '北京', '500', '东城区', 'NXN', '', '1', 'JXXNX', 'JXXNC', '1532336049');
INSERT INTO `d_user_address` VALUES ('3', '4', '2', '北京', '52', '北京', '500', '东城区', '啊啊啊', '', '1', '啊啊啊', '啊啊啊', '1532341102');

-- ----------------------------
-- Table structure for `d_user_backaction_log`
-- ----------------------------
DROP TABLE IF EXISTS `d_user_backaction_log`;
CREATE TABLE `d_user_backaction_log` (
  `log_id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '日志ID',
  `user_id` int(11) unsigned DEFAULT NULL COMMENT '用户ID',
  `user_name` varchar(30) NOT NULL DEFAULT '' COMMENT '用户名',
  `ip` varchar(20) DEFAULT '' COMMENT 'IP',
  `action` varchar(50) DEFAULT '' COMMENT '操作行为',
  `ret` varchar(50) DEFAULT '' COMMENT '操作结果',
  `add_datetime` int(11) unsigned DEFAULT NULL COMMENT '添加时间',
  PRIMARY KEY (`log_id`)
) ENGINE=MyISAM AUTO_INCREMENT=206 DEFAULT CHARSET=utf8 COMMENT='用户后台登录表';

-- ----------------------------
-- Records of d_user_backaction_log
-- ----------------------------
INSERT INTO `d_user_backaction_log` VALUES ('10', '1', 'MB3eSV4c3y4BPgQQoq1LJw==', '127.0.0.1', '后台登录', '登录成功', '1528533694');
INSERT INTO `d_user_backaction_log` VALUES ('11', '1', 'MB3eSV4c3y4BPgQQoq1LJw==', '127.0.0.1', '后台登录', '登录成功', '1528534751');
INSERT INTO `d_user_backaction_log` VALUES ('12', '1', 'MB3eSV4c3y4BPgQQoq1LJw==', '127.0.0.1', '后台登录', '登录成功', '1528534778');
INSERT INTO `d_user_backaction_log` VALUES ('13', '1', 'MB3eSV4c3y4BPgQQoq1LJw==', '127.0.0.1', '后台登录', '登录成功', '1528534796');
INSERT INTO `d_user_backaction_log` VALUES ('14', '1', 'MB3eSV4c3y4BPgQQoq1LJw==', '127.0.0.1', '后台登录', '登录成功', '1528535143');
INSERT INTO `d_user_backaction_log` VALUES ('15', '1', 'MB3eSV4c3y4BPgQQoq1LJw==', '127.0.0.1', '后台退出', '退出成功', '1528537330');
INSERT INTO `d_user_backaction_log` VALUES ('16', '65', 'xYUpiUAEVn1+9hkg9yt8qg==', '127.0.0.1', '后台登录', '登录成功', '1528537346');
INSERT INTO `d_user_backaction_log` VALUES ('17', '65', 'xYUpiUAEVn1+9hkg9yt8qg==', '127.0.0.1', '后台退出', '退出成功', '1528537510');
INSERT INTO `d_user_backaction_log` VALUES ('18', '2', 'MB3eSV4c3y4BPgQQoq1LJw==', '127.0.0.1', '后台登录', '登录成功', '1528537526');
INSERT INTO `d_user_backaction_log` VALUES ('19', '2', 'MB3eSV4c3y4BPgQQoq1LJw==', '127.0.0.1', '后台退出', '退出成功', '1528538148');
INSERT INTO `d_user_backaction_log` VALUES ('20', '2', 'MB3eSV4c3y4BPgQQoq1LJw==', '127.0.0.1', '后台登录', '登录成功', '1528538177');
INSERT INTO `d_user_backaction_log` VALUES ('21', '2', 'MB3eSV4c3y4BPgQQoq1LJw==', '127.0.0.1', '后台登录', '登录成功', '1528680722');
INSERT INTO `d_user_backaction_log` VALUES ('22', '2', 'MB3eSV4c3y4BPgQQoq1LJw==', '127.0.0.1', '后台登录', '登录成功', '1528684340');
INSERT INTO `d_user_backaction_log` VALUES ('23', '2', 'MB3eSV4c3y4BPgQQoq1LJw==', '127.0.0.1', '后台登录', '登录成功', '1528695709');
INSERT INTO `d_user_backaction_log` VALUES ('24', '2', 'MB3eSV4c3y4BPgQQoq1LJw==', '127.0.0.1', '后台登录', '登录成功', '1528699663');
INSERT INTO `d_user_backaction_log` VALUES ('25', '2', 'MB3eSV4c3y4BPgQQoq1LJw==', '127.0.0.1', '后台登录', '登录成功', '1528703788');
INSERT INTO `d_user_backaction_log` VALUES ('26', '2', 'MB3eSV4c3y4BPgQQoq1LJw==', '127.0.0.1', '后台登录', '登录成功', '1528706417');
INSERT INTO `d_user_backaction_log` VALUES ('27', '2', 'MB3eSV4c3y4BPgQQoq1LJw==', '127.0.0.1', '后台登录', '登录成功', '1528768650');
INSERT INTO `d_user_backaction_log` VALUES ('28', '2', 'MB3eSV4c3y4BPgQQoq1LJw==', '127.0.0.1', '后台登录', '登录成功', '1528773873');
INSERT INTO `d_user_backaction_log` VALUES ('29', '2', 'MB3eSV4c3y4BPgQQoq1LJw==', '127.0.0.1', '后台登录', '登录成功', '1528878232');
INSERT INTO `d_user_backaction_log` VALUES ('30', '2', 'MB3eSV4c3y4BPgQQoq1LJw==', '127.0.0.1', '后台登录', '登录成功', '1528883469');
INSERT INTO `d_user_backaction_log` VALUES ('31', '2', 'MB3eSV4c3y4BPgQQoq1LJw==', '127.0.0.1', '后台登录', '登录成功', '1528942554');
INSERT INTO `d_user_backaction_log` VALUES ('32', '2', 'MB3eSV4c3y4BPgQQoq1LJw==', '127.0.0.1', '后台登录', '登录成功', '1528947182');
INSERT INTO `d_user_backaction_log` VALUES ('33', '2', 'MB3eSV4c3y4BPgQQoq1LJw==', '127.0.0.1', '后台登录', '登录成功', '1528955270');
INSERT INTO `d_user_backaction_log` VALUES ('34', '2', 'MB3eSV4c3y4BPgQQoq1LJw==', '127.0.0.1', '后台登录', '登录成功', '1528958924');
INSERT INTO `d_user_backaction_log` VALUES ('35', '2', 'MB3eSV4c3y4BPgQQoq1LJw==', '127.0.0.1', '后台登录', '登录成功', '1528961538');
INSERT INTO `d_user_backaction_log` VALUES ('36', '2', 'MB3eSV4c3y4BPgQQoq1LJw==', '127.0.0.1', '后台登录', '登录成功', '1529026612');
INSERT INTO `d_user_backaction_log` VALUES ('37', '2', 'MB3eSV4c3y4BPgQQoq1LJw==', '127.0.0.1', '后台登录', '登录成功', '1529055957');
INSERT INTO `d_user_backaction_log` VALUES ('38', '2', 'MB3eSV4c3y4BPgQQoq1LJw==', '127.0.0.1', '后台登录', '登录成功', '1529111858');
INSERT INTO `d_user_backaction_log` VALUES ('39', '2', 'MB3eSV4c3y4BPgQQoq1LJw==', '192.168.11.118', '后台登录', '登录成功', '1529120435');
INSERT INTO `d_user_backaction_log` VALUES ('40', '2', 'MB3eSV4c3y4BPgQQoq1LJw==', '192.168.11.117', '后台登录', '登录成功', '1529120462');
INSERT INTO `d_user_backaction_log` VALUES ('41', '2', 'MB3eSV4c3y4BPgQQoq1LJw==', '192.168.11.118', '后台登录', '登录成功', '1529120939');
INSERT INTO `d_user_backaction_log` VALUES ('42', '2', 'MB3eSV4c3y4BPgQQoq1LJw==', '127.0.0.1', '后台登录', '登录成功', '1529398201');
INSERT INTO `d_user_backaction_log` VALUES ('43', '2', 'MB3eSV4c3y4BPgQQoq1LJw==', '192.168.11.115', '后台登录', '登录成功', '1530064707');
INSERT INTO `d_user_backaction_log` VALUES ('44', '2', 'MB3eSV4c3y4BPgQQoq1LJw==', '192.168.11.164', '后台登录', '登录成功', '1530064920');
INSERT INTO `d_user_backaction_log` VALUES ('45', '2', 'MB3eSV4c3y4BPgQQoq1LJw==', '192.168.11.121', '后台登录', '登录成功', '1530065186');
INSERT INTO `d_user_backaction_log` VALUES ('46', '2', 'MB3eSV4c3y4BPgQQoq1LJw==', '192.168.11.119', '后台登录', '登录成功', '1530065809');
INSERT INTO `d_user_backaction_log` VALUES ('47', '2', 'MB3eSV4c3y4BPgQQoq1LJw==', '192.168.11.164', '后台登录', '登录成功', '1530065829');
INSERT INTO `d_user_backaction_log` VALUES ('48', '2', 'MB3eSV4c3y4BPgQQoq1LJw==', '192.168.11.121', '后台登录', '登录成功', '1530066259');
INSERT INTO `d_user_backaction_log` VALUES ('49', '2', 'MB3eSV4c3y4BPgQQoq1LJw==', '192.168.11.121', '后台登录', '登录成功', '1530066501');
INSERT INTO `d_user_backaction_log` VALUES ('50', '2', 'MB3eSV4c3y4BPgQQoq1LJw==', '192.168.11.121', '后台登录', '登录成功', '1530071769');
INSERT INTO `d_user_backaction_log` VALUES ('51', '2', 'MB3eSV4c3y4BPgQQoq1LJw==', '192.168.11.121', '后台登录', '登录成功', '1530078833');
INSERT INTO `d_user_backaction_log` VALUES ('52', '2', 'MB3eSV4c3y4BPgQQoq1LJw==', '192.168.11.121', '后台登录', '登录成功', '1530088741');
INSERT INTO `d_user_backaction_log` VALUES ('53', '2', 'MB3eSV4c3y4BPgQQoq1LJw==', '192.168.11.121', '后台登录', '登录成功', '1530090154');
INSERT INTO `d_user_backaction_log` VALUES ('54', '2', 'MB3eSV4c3y4BPgQQoq1LJw==', '192.168.11.119', '后台登录', '登录成功', '1530091159');
INSERT INTO `d_user_backaction_log` VALUES ('55', '2', 'MB3eSV4c3y4BPgQQoq1LJw==', '192.168.11.121', '后台登录', '登录成功', '1530091290');
INSERT INTO `d_user_backaction_log` VALUES ('56', '2', 'MB3eSV4c3y4BPgQQoq1LJw==', '192.168.11.121', '后台登录', '登录成功', '1530095971');
INSERT INTO `d_user_backaction_log` VALUES ('57', '2', 'MB3eSV4c3y4BPgQQoq1LJw==', '192.168.11.121', '后台登录', '登录成功', '1530155081');
INSERT INTO `d_user_backaction_log` VALUES ('58', '2', 'MB3eSV4c3y4BPgQQoq1LJw==', '192.168.11.121', '后台登录', '登录成功', '1530164577');
INSERT INTO `d_user_backaction_log` VALUES ('59', '2', 'MB3eSV4c3y4BPgQQoq1LJw==', '192.168.11.121', '后台登录', '登录成功', '1530172051');
INSERT INTO `d_user_backaction_log` VALUES ('60', '2', 'MB3eSV4c3y4BPgQQoq1LJw==', '127.0.0.1', '后台登录', '登录成功', '1530262741');
INSERT INTO `d_user_backaction_log` VALUES ('61', '2', 'MB3eSV4c3y4BPgQQoq1LJw==', '192.168.11.121', '后台登录', '登录成功', '1530264431');
INSERT INTO `d_user_backaction_log` VALUES ('62', '2', 'MB3eSV4c3y4BPgQQoq1LJw==', '127.0.0.1', '后台登录', '登录成功', '1530264438');
INSERT INTO `d_user_backaction_log` VALUES ('63', '2', 'MB3eSV4c3y4BPgQQoq1LJw==', '192.168.11.121', '后台登录', '登录成功', '1530264522');
INSERT INTO `d_user_backaction_log` VALUES ('64', '2', 'MB3eSV4c3y4BPgQQoq1LJw==', '127.0.0.1', '后台登录', '登录成功', '1530264610');
INSERT INTO `d_user_backaction_log` VALUES ('65', '2', 'MB3eSV4c3y4BPgQQoq1LJw==', '192.168.11.121', '后台登录', '登录成功', '1530265889');
INSERT INTO `d_user_backaction_log` VALUES ('66', '2', 'MB3eSV4c3y4BPgQQoq1LJw==', '192.168.11.121', '后台登录', '登录成功', '1530271752');
INSERT INTO `d_user_backaction_log` VALUES ('67', '2', 'MB3eSV4c3y4BPgQQoq1LJw==', '192.168.11.117', '后台登录', '登录成功', '1530322847');
INSERT INTO `d_user_backaction_log` VALUES ('68', '2', 'MB3eSV4c3y4BPgQQoq1LJw==', '192.168.11.117', '后台登录', '登录成功', '1530498008');
INSERT INTO `d_user_backaction_log` VALUES ('69', '2', 'MB3eSV4c3y4BPgQQoq1LJw==', '127.0.0.1', '后台登录', '登录成功', '1530510178');
INSERT INTO `d_user_backaction_log` VALUES ('70', '2', 'MB3eSV4c3y4BPgQQoq1LJw==', '127.0.0.1', '后台登录', '登录成功', '1530514029');
INSERT INTO `d_user_backaction_log` VALUES ('71', '2', 'MB3eSV4c3y4BPgQQoq1LJw==', '192.168.11.118', '后台登录', '登录成功', '1530517906');
INSERT INTO `d_user_backaction_log` VALUES ('72', '2', 'MB3eSV4c3y4BPgQQoq1LJw==', '192.168.11.117', '后台登录', '登录成功', '1530528499');
INSERT INTO `d_user_backaction_log` VALUES ('73', '2', 'MB3eSV4c3y4BPgQQoq1LJw==', '192.168.11.121', '后台登录', '登录成功', '1530602162');
INSERT INTO `d_user_backaction_log` VALUES ('74', '2', 'MB3eSV4c3y4BPgQQoq1LJw==', '192.168.11.121', '后台登录', '登录成功', '1530684745');
INSERT INTO `d_user_backaction_log` VALUES ('75', '2', 'MB3eSV4c3y4BPgQQoq1LJw==', '192.168.11.164', '后台登录', '登录成功', '1530756250');
INSERT INTO `d_user_backaction_log` VALUES ('76', '2', 'MB3eSV4c3y4BPgQQoq1LJw==', '192.168.11.164', '后台登录', '登录成功', '1530759890');
INSERT INTO `d_user_backaction_log` VALUES ('77', '2', 'MB3eSV4c3y4BPgQQoq1LJw==', '192.168.11.117', '后台登录', '登录成功', '1530759897');
INSERT INTO `d_user_backaction_log` VALUES ('78', '2', 'MB3eSV4c3y4BPgQQoq1LJw==', '192.168.11.164', '后台登录', '登录成功', '1530761624');
INSERT INTO `d_user_backaction_log` VALUES ('79', '2', 'MB3eSV4c3y4BPgQQoq1LJw==', '192.168.11.129', '后台登录', '登录成功', '1530844045');
INSERT INTO `d_user_backaction_log` VALUES ('80', '2', 'MB3eSV4c3y4BPgQQoq1LJw==', '192.168.11.129', '后台登录', '登录成功', '1530844097');
INSERT INTO `d_user_backaction_log` VALUES ('81', '2', 'MB3eSV4c3y4BPgQQoq1LJw==', '192.168.11.129', '后台登录', '登录成功', '1530855445');
INSERT INTO `d_user_backaction_log` VALUES ('82', '2', 'MB3eSV4c3y4BPgQQoq1LJw==', '192.168.11.129', '后台登录', '登录成功', '1530861835');
INSERT INTO `d_user_backaction_log` VALUES ('83', '2', 'MB3eSV4c3y4BPgQQoq1LJw==', '192.168.11.121', '后台登录', '登录成功', '1530864090');
INSERT INTO `d_user_backaction_log` VALUES ('84', '2', 'MB3eSV4c3y4BPgQQoq1LJw==', '192.168.11.129', '后台登录', '登录成功', '1530927925');
INSERT INTO `d_user_backaction_log` VALUES ('85', '2', 'MB3eSV4c3y4BPgQQoq1LJw==', '192.168.11.129', '后台登录', '登录成功', '1530931735');
INSERT INTO `d_user_backaction_log` VALUES ('86', '2', 'MB3eSV4c3y4BPgQQoq1LJw==', '192.168.11.129', '后台登录', '登录成功', '1530947359');
INSERT INTO `d_user_backaction_log` VALUES ('87', '2', 'MB3eSV4c3y4BPgQQoq1LJw==', '192.168.11.129', '后台登录', '登录成功', '1531101573');
INSERT INTO `d_user_backaction_log` VALUES ('88', '2', 'MB3eSV4c3y4BPgQQoq1LJw==', '192.168.11.121', '后台登录', '登录成功', '1531118683');
INSERT INTO `d_user_backaction_log` VALUES ('89', '2', 'MB3eSV4c3y4BPgQQoq1LJw==', '192.168.11.121', '后台登录', '登录成功', '1531130469');
INSERT INTO `d_user_backaction_log` VALUES ('90', '2', 'MB3eSV4c3y4BPgQQoq1LJw==', '192.168.11.129', '后台登录', '登录成功', '1531373570');
INSERT INTO `d_user_backaction_log` VALUES ('91', '2', 'MB3eSV4c3y4BPgQQoq1LJw==', '192.168.11.129', '后台登录', '登录成功', '1531381255');
INSERT INTO `d_user_backaction_log` VALUES ('92', '2', 'MB3eSV4c3y4BPgQQoq1LJw==', '192.168.11.121', '后台登录', '登录成功', '1531387695');
INSERT INTO `d_user_backaction_log` VALUES ('93', '2', 'MB3eSV4c3y4BPgQQoq1LJw==', '192.168.11.118', '后台登录', '登录成功', '1531387729');
INSERT INTO `d_user_backaction_log` VALUES ('94', '2', 'MB3eSV4c3y4BPgQQoq1LJw==', '192.168.11.121', '后台登录', '登录成功', '1531388182');
INSERT INTO `d_user_backaction_log` VALUES ('95', '2', 'MB3eSV4c3y4BPgQQoq1LJw==', '192.168.11.121', '后台登录', '登录成功', '1531388454');
INSERT INTO `d_user_backaction_log` VALUES ('96', '2', 'MB3eSV4c3y4BPgQQoq1LJw==', '192.168.11.121', '后台登录', '登录成功', '1531388465');
INSERT INTO `d_user_backaction_log` VALUES ('97', '2', 'MB3eSV4c3y4BPgQQoq1LJw==', '192.168.11.121', '后台登录', '登录成功', '1531388630');
INSERT INTO `d_user_backaction_log` VALUES ('98', '2', 'MB3eSV4c3y4BPgQQoq1LJw==', '192.168.11.121', '后台登录', '登录成功', '1531388801');
INSERT INTO `d_user_backaction_log` VALUES ('99', '2', 'MB3eSV4c3y4BPgQQoq1LJw==', '127.0.0.1', '后台登录', '登录成功', '1531446397');
INSERT INTO `d_user_backaction_log` VALUES ('100', '2', 'MB3eSV4c3y4BPgQQoq1LJw==', '192.168.11.129', '后台登录', '登录成功', '1531446784');
INSERT INTO `d_user_backaction_log` VALUES ('101', '2', 'MB3eSV4c3y4BPgQQoq1LJw==', '127.0.0.1', '后台登录', '登录成功', '1531446911');
INSERT INTO `d_user_backaction_log` VALUES ('102', '2', 'MB3eSV4c3y4BPgQQoq1LJw==', '127.0.0.1', '后台登录', '登录成功', '1531452098');
INSERT INTO `d_user_backaction_log` VALUES ('103', '2', 'MB3eSV4c3y4BPgQQoq1LJw==', '192.168.11.129', '后台登录', '登录成功', '1531477862');
INSERT INTO `d_user_backaction_log` VALUES ('104', '65', 'xYUpiUAEVn1+9hkg9yt8qg==', '127.0.0.1', '后台登录', '登录成功', '1531705108');
INSERT INTO `d_user_backaction_log` VALUES ('105', '2', 'MB3eSV4c3y4BPgQQoq1LJw==', '192.168.11.129', '后台登录', '登录成功', '1531709715');
INSERT INTO `d_user_backaction_log` VALUES ('106', '65', 'xYUpiUAEVn1+9hkg9yt8qg==', '127.0.0.1', '后台登录', '登录成功', '1531712489');
INSERT INTO `d_user_backaction_log` VALUES ('107', '65', 'xYUpiUAEVn1+9hkg9yt8qg==', '127.0.0.1', '后台登录', '登录成功', '1531719874');
INSERT INTO `d_user_backaction_log` VALUES ('108', '65', 'xYUpiUAEVn1+9hkg9yt8qg==', '127.0.0.1', '后台登录', '登录成功', '1531725499');
INSERT INTO `d_user_backaction_log` VALUES ('109', '65', 'xYUpiUAEVn1+9hkg9yt8qg==', '127.0.0.1', '后台登录', '登录成功', '1531729221');
INSERT INTO `d_user_backaction_log` VALUES ('110', '2', 'MB3eSV4c3y4BPgQQoq1LJw==', '192.168.11.121', '后台登录', '登录成功', '1531822124');
INSERT INTO `d_user_backaction_log` VALUES ('111', '65', 'xYUpiUAEVn1+9hkg9yt8qg==', '127.0.0.1', '后台登录', '登录成功', '1531967462');
INSERT INTO `d_user_backaction_log` VALUES ('112', '2', 'MB3eSV4c3y4BPgQQoq1LJw==', '192.168.11.121', '后台登录', '登录成功', '1531967501');
INSERT INTO `d_user_backaction_log` VALUES ('113', '2', 'MB3eSV4c3y4BPgQQoq1LJw==', '192.168.11.118', '后台登录', '登录成功', '1531967575');
INSERT INTO `d_user_backaction_log` VALUES ('114', '2', 'MB3eSV4c3y4BPgQQoq1LJw==', '192.168.11.121', '后台登录', '登录成功', '1531967584');
INSERT INTO `d_user_backaction_log` VALUES ('115', '65', 'xYUpiUAEVn1+9hkg9yt8qg==', '192.168.11.118', '后台登录', '登录成功', '1531967607');
INSERT INTO `d_user_backaction_log` VALUES ('116', '2', 'MB3eSV4c3y4BPgQQoq1LJw==', '192.168.11.129', '后台登录', '登录成功', '1531967735');
INSERT INTO `d_user_backaction_log` VALUES ('117', '65', 'xYUpiUAEVn1+9hkg9yt8qg==', '127.0.0.1', '后台登录', '登录成功', '1531969583');
INSERT INTO `d_user_backaction_log` VALUES ('118', '2', 'MB3eSV4c3y4BPgQQoq1LJw==', '192.168.11.118', '后台登录', '登录成功', '1531969710');
INSERT INTO `d_user_backaction_log` VALUES ('119', '65', 'xYUpiUAEVn1+9hkg9yt8qg==', '127.0.0.1', '后台登录', '登录成功', '1531979129');
INSERT INTO `d_user_backaction_log` VALUES ('120', '65', 'xYUpiUAEVn1+9hkg9yt8qg==', '192.168.11.118', '后台登录', '登录成功', '1531980018');
INSERT INTO `d_user_backaction_log` VALUES ('121', '2', 'MB3eSV4c3y4BPgQQoq1LJw==', '192.168.11.118', '后台登录', '登录成功', '1531980286');
INSERT INTO `d_user_backaction_log` VALUES ('122', '2', 'MB3eSV4c3y4BPgQQoq1LJw==', '192.168.11.121', '后台登录', '登录成功', '1531986410');
INSERT INTO `d_user_backaction_log` VALUES ('123', '65', 'xYUpiUAEVn1+9hkg9yt8qg==', '127.0.0.1', '后台登录', '登录成功', '1532051284');
INSERT INTO `d_user_backaction_log` VALUES ('124', '65', 'xYUpiUAEVn1+9hkg9yt8qg==', '127.0.0.1', '后台退出', '退出成功', '1532051364');
INSERT INTO `d_user_backaction_log` VALUES ('125', '2', 'MB3eSV4c3y4BPgQQoq1LJw==', '127.0.0.1', '后台登录', '登录成功', '1532051424');
INSERT INTO `d_user_backaction_log` VALUES ('126', '2', 'MB3eSV4c3y4BPgQQoq1LJw==', '127.0.0.1', '后台登录', '登录成功', '1532051451');
INSERT INTO `d_user_backaction_log` VALUES ('127', '2', 'MB3eSV4c3y4BPgQQoq1LJw==', '127.0.0.1', '后台退出', '退出成功', '1532051459');
INSERT INTO `d_user_backaction_log` VALUES ('128', '65', 'xYUpiUAEVn1+9hkg9yt8qg==', '127.0.0.1', '后台登录', '登录成功', '1532051469');
INSERT INTO `d_user_backaction_log` VALUES ('129', '65', 'xYUpiUAEVn1+9hkg9yt8qg==', '127.0.0.1', '后台退出', '退出成功', '1532051479');
INSERT INTO `d_user_backaction_log` VALUES ('130', '2', 'MB3eSV4c3y4BPgQQoq1LJw==', '127.0.0.1', '后台登录', '登录成功', '1532051506');
INSERT INTO `d_user_backaction_log` VALUES ('131', '2', 'MB3eSV4c3y4BPgQQoq1LJw==', '127.0.0.1', '后台退出', '退出成功', '1532051532');
INSERT INTO `d_user_backaction_log` VALUES ('132', '2', 'MB3eSV4c3y4BPgQQoq1LJw==', '192.168.11.129', '后台登录', '登录成功', '1532053153');
INSERT INTO `d_user_backaction_log` VALUES ('133', '2', 'MB3eSV4c3y4BPgQQoq1LJw==', '192.168.11.121', '后台登录', '登录成功', '1532071932');
INSERT INTO `d_user_backaction_log` VALUES ('134', '65', 'xYUpiUAEVn1+9hkg9yt8qg==', '127.0.0.1', '后台登录', '登录成功', '1532072543');
INSERT INTO `d_user_backaction_log` VALUES ('135', '65', 'xYUpiUAEVn1+9hkg9yt8qg==', '127.0.0.1', '后台登录', '登录成功', '1532072786');
INSERT INTO `d_user_backaction_log` VALUES ('136', '65', 'xYUpiUAEVn1+9hkg9yt8qg==', '127.0.0.1', '后台登录', '登录成功', '1532077384');
INSERT INTO `d_user_backaction_log` VALUES ('137', '2', 'MB3eSV4c3y4BPgQQoq1LJw==', '192.168.11.119', '后台登录', '登录成功', '1532077849');
INSERT INTO `d_user_backaction_log` VALUES ('138', '65', 'xYUpiUAEVn1+9hkg9yt8qg==', '192.168.11.118', '后台登录', '登录成功', '1532080999');
INSERT INTO `d_user_backaction_log` VALUES ('139', '2', 'MB3eSV4c3y4BPgQQoq1LJw==', '192.168.11.129', '后台登录', '登录成功', '1532084916');
INSERT INTO `d_user_backaction_log` VALUES ('140', '65', 'xYUpiUAEVn1+9hkg9yt8qg==', '127.0.0.1', '后台登录', '登录成功', '1532137736');
INSERT INTO `d_user_backaction_log` VALUES ('141', '65', 'xYUpiUAEVn1+9hkg9yt8qg==', '127.0.0.1', '后台登录', '登录成功', '1532141361');
INSERT INTO `d_user_backaction_log` VALUES ('142', '65', 'xYUpiUAEVn1+9hkg9yt8qg==', '127.0.0.1', '后台登录', '登录成功', '1532141448');
INSERT INTO `d_user_backaction_log` VALUES ('143', '65', 'xYUpiUAEVn1+9hkg9yt8qg==', '127.0.0.1', '后台登录', '登录成功', '1532153730');
INSERT INTO `d_user_backaction_log` VALUES ('144', '2', 'MB3eSV4c3y4BPgQQoq1LJw==', '192.168.11.119', '后台登录', '登录成功', '1532158450');
INSERT INTO `d_user_backaction_log` VALUES ('145', '65', 'xYUpiUAEVn1+9hkg9yt8qg==', '127.0.0.1', '后台登录', '登录成功', '1532161045');
INSERT INTO `d_user_backaction_log` VALUES ('146', '65', 'xYUpiUAEVn1+9hkg9yt8qg==', '127.0.0.1', '后台登录', '登录成功', '1532167570');
INSERT INTO `d_user_backaction_log` VALUES ('147', '65', 'xYUpiUAEVn1+9hkg9yt8qg==', '127.0.0.1', '后台登录', '登录成功', '1532308118');
INSERT INTO `d_user_backaction_log` VALUES ('148', '2', 'MB3eSV4c3y4BPgQQoq1LJw==', '192.168.11.115', '后台登录', '登录成功', '1532309144');
INSERT INTO `d_user_backaction_log` VALUES ('149', '65', 'xYUpiUAEVn1+9hkg9yt8qg==', '192.168.11.118', '后台登录', '登录成功', '1532309216');
INSERT INTO `d_user_backaction_log` VALUES ('150', '65', 'xYUpiUAEVn1+9hkg9yt8qg==', '127.0.0.1', '后台登录', '登录成功', '1532309637');
INSERT INTO `d_user_backaction_log` VALUES ('151', '65', 'xYUpiUAEVn1+9hkg9yt8qg==', '192.168.11.118', '后台登录', '登录成功', '1532310691');
INSERT INTO `d_user_backaction_log` VALUES ('152', '2', 'MB3eSV4c3y4BPgQQoq1LJw==', '127.0.0.1', '后台登录', '登录成功', '1532486576');
INSERT INTO `d_user_backaction_log` VALUES ('153', '2', 'MB3eSV4c3y4BPgQQoq1LJw==', '127.0.0.1', '后台退出', '退出成功', '1532486583');
INSERT INTO `d_user_backaction_log` VALUES ('154', '2', 'MB3eSV4c3y4BPgQQoq1LJw==', '127.0.0.1', '后台登录', '登录成功', '1532486595');
INSERT INTO `d_user_backaction_log` VALUES ('155', '65', 'xYUpiUAEVn1+9hkg9yt8qg==', '127.0.0.1', '后台登录', '登录成功', '1532503871');
INSERT INTO `d_user_backaction_log` VALUES ('156', '65', 'xYUpiUAEVn1+9hkg9yt8qg==', '192.168.11.118', '后台登录', '登录成功', '1532504045');
INSERT INTO `d_user_backaction_log` VALUES ('157', '65', 'xYUpiUAEVn1+9hkg9yt8qg==', '127.0.0.1', '后台登录', '登录成功', '1532507384');
INSERT INTO `d_user_backaction_log` VALUES ('158', '2', 'MB3eSV4c3y4BPgQQoq1LJw==', '192.168.11.121', '后台登录', '登录成功', '1532511560');
INSERT INTO `d_user_backaction_log` VALUES ('159', '65', 'xYUpiUAEVn1+9hkg9yt8qg==', '127.0.0.1', '后台登录', '登录成功', '1532577191');
INSERT INTO `d_user_backaction_log` VALUES ('160', '65', 'xYUpiUAEVn1+9hkg9yt8qg==', '127.0.0.1', '后台登录', '登录成功', '1532589233');
INSERT INTO `d_user_backaction_log` VALUES ('161', '65', 'xYUpiUAEVn1+9hkg9yt8qg==', '127.0.0.1', '后台退出', '退出成功', '1532589330');
INSERT INTO `d_user_backaction_log` VALUES ('162', '65', 'xYUpiUAEVn1+9hkg9yt8qg==', '127.0.0.1', '后台登录', '登录成功', '1532589342');
INSERT INTO `d_user_backaction_log` VALUES ('163', '65', 'xYUpiUAEVn1+9hkg9yt8qg==', '127.0.0.1', '后台退出', '退出成功', '1532589358');
INSERT INTO `d_user_backaction_log` VALUES ('164', '65', 'xYUpiUAEVn1+9hkg9yt8qg==', '127.0.0.1', '后台登录', '登录成功', '1532589571');
INSERT INTO `d_user_backaction_log` VALUES ('165', '65', 'xYUpiUAEVn1+9hkg9yt8qg==', '127.0.0.1', '后台退出', '退出成功', '1532589802');
INSERT INTO `d_user_backaction_log` VALUES ('166', '65', 'xYUpiUAEVn1+9hkg9yt8qg==', '127.0.0.1', '后台登录', '登录成功', '1532589848');
INSERT INTO `d_user_backaction_log` VALUES ('167', '65', 'xYUpiUAEVn1+9hkg9yt8qg==', '127.0.0.1', '后台退出', '退出成功', '1532589854');
INSERT INTO `d_user_backaction_log` VALUES ('168', '65', 'xYUpiUAEVn1+9hkg9yt8qg==', '127.0.0.1', '后台登录', '登录成功', '1532595652');
INSERT INTO `d_user_backaction_log` VALUES ('169', '65', 'xYUpiUAEVn1+9hkg9yt8qg==', '127.0.0.1', '后台登录', '登录成功', '1532601530');
INSERT INTO `d_user_backaction_log` VALUES ('170', '65', 'xYUpiUAEVn1+9hkg9yt8qg==', '192.168.11.118', '后台登录', '登录成功', '1532602222');
INSERT INTO `d_user_backaction_log` VALUES ('171', '65', 'xYUpiUAEVn1+9hkg9yt8qg==', '192.168.11.118', '后台退出', '退出成功', '1532602315');
INSERT INTO `d_user_backaction_log` VALUES ('172', '65', 'xYUpiUAEVn1+9hkg9yt8qg==', '127.0.0.1', '后台登录', '登录成功', '1532673637');
INSERT INTO `d_user_backaction_log` VALUES ('173', '65', 'xYUpiUAEVn1+9hkg9yt8qg==', '127.0.0.1', '后台登录', '登录成功', '1532675258');
INSERT INTO `d_user_backaction_log` VALUES ('174', '65', 'xYUpiUAEVn1+9hkg9yt8qg==', '127.0.0.1', '后台登录', '登录成功', '1532680302');
INSERT INTO `d_user_backaction_log` VALUES ('175', '2', 'MB3eSV4c3y4BPgQQoq1LJw==', '192.168.11.121', '后台登录', '登录成功', '1532689524');
INSERT INTO `d_user_backaction_log` VALUES ('176', '65', 'xYUpiUAEVn1+9hkg9yt8qg==', '192.168.11.103', '后台登录', '登录成功', '1532947934');
INSERT INTO `d_user_backaction_log` VALUES ('177', '65', 'xYUpiUAEVn1+9hkg9yt8qg==', '192.168.11.103', '后台登录', '登录成功', '1532947965');
INSERT INTO `d_user_backaction_log` VALUES ('178', '2', 'MB3eSV4c3y4BPgQQoq1LJw==', '192.168.11.119', '后台登录', '登录成功', '1533003482');
INSERT INTO `d_user_backaction_log` VALUES ('179', '2', 'MB3eSV4c3y4BPgQQoq1LJw==', '192.168.11.121', '后台登录', '登录成功', '1533004344');
INSERT INTO `d_user_backaction_log` VALUES ('180', '65', 'xYUpiUAEVn1+9hkg9yt8qg==', '127.0.0.1', '后台登录', '登录成功', '1533028955');
INSERT INTO `d_user_backaction_log` VALUES ('181', '65', 'xYUpiUAEVn1+9hkg9yt8qg==', '127.0.0.1', '后台登录', '登录成功', '1533031804');
INSERT INTO `d_user_backaction_log` VALUES ('182', '65', 'xYUpiUAEVn1+9hkg9yt8qg==', '127.0.0.1', '后台登录', '登录成功', '1533031824');
INSERT INTO `d_user_backaction_log` VALUES ('183', '65', 'xYUpiUAEVn1+9hkg9yt8qg==', '127.0.0.1', '后台登录', '登录成功', '1533034172');
INSERT INTO `d_user_backaction_log` VALUES ('184', '65', 'xYUpiUAEVn1+9hkg9yt8qg==', '127.0.0.1', '后台登录', '登录成功', '1533034200');
INSERT INTO `d_user_backaction_log` VALUES ('185', '65', 'xYUpiUAEVn1+9hkg9yt8qg==', '192.168.11.118', '后台登录', '登录成功', '1533039446');
INSERT INTO `d_user_backaction_log` VALUES ('186', '2', 'MB3eSV4c3y4BPgQQoq1LJw==', '192.168.11.121', '后台登录', '登录成功', '1533088145');
INSERT INTO `d_user_backaction_log` VALUES ('187', '65', 'xYUpiUAEVn1+9hkg9yt8qg==', '192.168.11.118', '后台登录', '登录成功', '1533090693');
INSERT INTO `d_user_backaction_log` VALUES ('188', '2', 'MB3eSV4c3y4BPgQQoq1LJw==', '192.168.11.123', '后台登录', '登录成功', '1533091262');
INSERT INTO `d_user_backaction_log` VALUES ('189', '2', 'MB3eSV4c3y4BPgQQoq1LJw==', '192.168.11.121', '后台登录', '登录成功', '1533093778');
INSERT INTO `d_user_backaction_log` VALUES ('190', '2', 'MB3eSV4c3y4BPgQQoq1LJw==', '192.168.11.121', '后台登录', '登录成功', '1533094517');
INSERT INTO `d_user_backaction_log` VALUES ('191', '65', 'xYUpiUAEVn1+9hkg9yt8qg==', '192.168.11.118', '后台登录', '登录成功', '1533094575');
INSERT INTO `d_user_backaction_log` VALUES ('192', '2', 'MB3eSV4c3y4BPgQQoq1LJw==', '192.168.11.121', '后台登录', '登录成功', '1533094671');
INSERT INTO `d_user_backaction_log` VALUES ('193', '2', 'MB3eSV4c3y4BPgQQoq1LJw==', '192.168.11.121', '后台登录', '登录成功', '1533103278');
INSERT INTO `d_user_backaction_log` VALUES ('194', '2', 'MB3eSV4c3y4BPgQQoq1LJw==', '192.168.11.121', '后台登录', '登录成功', '1533111524');
INSERT INTO `d_user_backaction_log` VALUES ('195', '65', 'xYUpiUAEVn1+9hkg9yt8qg==', '192.168.11.118', '后台登录', '登录成功', '1533114533');
INSERT INTO `d_user_backaction_log` VALUES ('196', '2', 'MB3eSV4c3y4BPgQQoq1LJw==', '192.168.11.121', '后台登录', '登录成功', '1533117586');
INSERT INTO `d_user_backaction_log` VALUES ('197', '65', 'xYUpiUAEVn1+9hkg9yt8qg==', '192.168.11.118', '后台登录', '登录成功', '1533119129');
INSERT INTO `d_user_backaction_log` VALUES ('198', '2', 'MB3eSV4c3y4BPgQQoq1LJw==', '192.168.11.121', '后台登录', '登录成功', '1533121219');
INSERT INTO `d_user_backaction_log` VALUES ('199', '2', 'MB3eSV4c3y4BPgQQoq1LJw==', '192.168.11.121', '后台登录', '登录成功', '1533889895');
INSERT INTO `d_user_backaction_log` VALUES ('200', '2', 'MB3eSV4c3y4BPgQQoq1LJw==', '192.168.11.121', '后台登录', '登录成功', '1534126419');
INSERT INTO `d_user_backaction_log` VALUES ('201', '2', 'MB3eSV4c3y4BPgQQoq1LJw==', '192.168.11.121', '后台登录', '登录成功', '1534130344');
INSERT INTO `d_user_backaction_log` VALUES ('202', '2', 'MB3eSV4c3y4BPgQQoq1LJw==', '192.168.11.121', '后台登录', '登录成功', '1534138807');
INSERT INTO `d_user_backaction_log` VALUES ('203', '2', 'MB3eSV4c3y4BPgQQoq1LJw==', '192.168.11.121', '后台登录', '登录成功', '1534146933');
INSERT INTO `d_user_backaction_log` VALUES ('204', '2', 'MB3eSV4c3y4BPgQQoq1LJw==', '192.168.11.121', '后台登录', '登录成功', '1534743903');
INSERT INTO `d_user_backaction_log` VALUES ('205', '2', 'MB3eSV4c3y4BPgQQoq1LJw==', '192.168.11.121', '后台登录', '登录成功', '1536031685');

-- ----------------------------
-- Table structure for `d_user_bank`
-- ----------------------------
DROP TABLE IF EXISTS `d_user_bank`;
CREATE TABLE `d_user_bank` (
  `bank_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '账户银行ID',
  `user_id` int(11) NOT NULL COMMENT '用户ID',
  `real_name` varchar(200) DEFAULT NULL COMMENT '姓名',
  `order_no` varchar(50) DEFAULT NULL COMMENT '绑定单号',
  `bank_code` varchar(50) DEFAULT NULL COMMENT '银行编码',
  `bank_name` varchar(50) DEFAULT NULL COMMENT '银行名称',
  `bank_card_no` varchar(50) DEFAULT '' COMMENT '银行卡号',
  `branch` varchar(100) DEFAULT NULL COMMENT '支行名称',
  `status` tinyint(4) DEFAULT '0' COMMENT '状态标识 0 初始状态 1 审核中 2 审核通过  3 审核不通过  4 已变更',
  `terminal` tinyint(4) DEFAULT '1' COMMENT '操作终端 1 PC 2 安卓 3 IOS 4 微信',
  `remark` varchar(200) DEFAULT NULL COMMENT '备注',
  `return_content` varchar(2000) DEFAULT NULL COMMENT '第三方返回内容',
  `edit_datetime` int(11) DEFAULT NULL COMMENT '审核时间',
  `add_datetime` int(11) NOT NULL COMMENT '创建时间',
  `bank_img` varchar(225) DEFAULT NULL,
  PRIMARY KEY (`bank_id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8 COMMENT='账户银行';

-- ----------------------------
-- Records of d_user_bank
-- ----------------------------
INSERT INTO `d_user_bank` VALUES ('1', '7', null, null, null, '招商银行', '6225887863925188', '招商银行', '0', '3', null, null, null, '1532078099', null);
INSERT INTO `d_user_bank` VALUES ('2', '4', null, null, null, '中国民生银行', '6226220612530807', '中国民生银行', '0', '2', null, null, null, '1532340664', null);
INSERT INTO `d_user_bank` VALUES ('3', '23', null, null, null, '中国民生银行', '6226220612530808', '中国民生银行', '0', '3', null, null, null, '1532679595', null);

-- ----------------------------
-- Table structure for `d_user_collect_log_del`
-- ----------------------------
DROP TABLE IF EXISTS `d_user_collect_log_del`;
CREATE TABLE `d_user_collect_log_del` (
  `collect_id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` int(10) unsigned DEFAULT '0' COMMENT '用户ID',
  `activity_id` int(10) unsigned DEFAULT '0' COMMENT '任务ID',
  `device_mac` varchar(50) DEFAULT '' COMMENT '绑定的充电宝MAC地址',
  `start_date` int(11) unsigned DEFAULT '0' COMMENT '开始时间',
  `end_date` int(11) unsigned DEFAULT '0' COMMENT '结束时间',
  `mac_num` int(10) unsigned DEFAULT '0' COMMENT '采集到的MAC数量',
  `status` tinyint(4) unsigned DEFAULT '1' COMMENT '状态',
  PRIMARY KEY (`collect_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户MAC采集日志';

-- ----------------------------
-- Records of d_user_collect_log_del
-- ----------------------------

-- ----------------------------
-- Table structure for `d_user_invite`
-- ----------------------------
DROP TABLE IF EXISTS `d_user_invite`;
CREATE TABLE `d_user_invite` (
  `invite_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `user_id` int(11) NOT NULL DEFAULT '0' COMMENT '用户(被邀请人)',
  `invite_user_id` int(11) NOT NULL DEFAULT '0' COMMENT '邀请人',
  `reward_type` int(11) NOT NULL DEFAULT '0' COMMENT '奖励类型',
  `reward_name` varchar(50) DEFAULT NULL COMMENT '奖励类型名称',
  `reward_stutas` tinyint(4) DEFAULT '0' COMMENT '0 未处理 1 已处理',
  `reward_money` decimal(15,6) DEFAULT '0.000000' COMMENT '邀请奖励',
  `add_datetime` int(11) NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`invite_id`),
  KEY `ind_invite_userID` (`user_id`) USING BTREE,
  KEY `ind_invite_inviteUserID` (`invite_user_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8 COMMENT='用户邀请';

-- ----------------------------
-- Records of d_user_invite
-- ----------------------------
INSERT INTO `d_user_invite` VALUES ('1', '9', '7', '0', null, '0', '0.000000', '1532077725');
INSERT INTO `d_user_invite` VALUES ('2', '23', '4', '0', null, '0', '0.000000', '1532154910');
INSERT INTO `d_user_invite` VALUES ('3', '25', '4', '0', null, '0', '0.000000', '1532164985');
INSERT INTO `d_user_invite` VALUES ('4', '41', '33', '0', null, '0', '0.000000', '1532335084');
INSERT INTO `d_user_invite` VALUES ('5', '43', '41', '0', null, '0', '0.000000', '1532335643');
INSERT INTO `d_user_invite` VALUES ('6', '98', '83', '0', null, '0', '0.000000', '1535705393');
INSERT INTO `d_user_invite` VALUES ('7', '101', '83', '0', null, '0', '0.000000', '1535709343');
INSERT INTO `d_user_invite` VALUES ('8', '103', '83', '0', null, '0', '0.000000', '1535710177');
INSERT INTO `d_user_invite` VALUES ('9', '104', '83', '0', null, '0', '0.000000', '1535710212');
INSERT INTO `d_user_invite` VALUES ('10', '105', '83', '0', null, '0', '0.000000', '1535710854');
INSERT INTO `d_user_invite` VALUES ('13', '108', '83', '0', null, '0', '0.000000', '1535711997');
INSERT INTO `d_user_invite` VALUES ('14', '109', '83', '0', null, '0', '0.000000', '1535712212');
INSERT INTO `d_user_invite` VALUES ('15', '110', '111', '0', null, '0', '0.000000', '1535712626');
INSERT INTO `d_user_invite` VALUES ('16', '111', '111', '0', null, '0', '0.000000', '1535942926');

-- ----------------------------
-- Table structure for `d_user_login_count`
-- ----------------------------
DROP TABLE IF EXISTS `d_user_login_count`;
CREATE TABLE `d_user_login_count` (
  `count_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '次数ID',
  `user_id` int(11) NOT NULL COMMENT '用户ID',
  `phone` varchar(50) DEFAULT NULL COMMENT '手机号',
  `login_times` int(11) DEFAULT '0' COMMENT '登录次数',
  `mobile_times` int(11) DEFAULT '0' COMMENT '移动端登录次数',
  `wechat_times` int(11) DEFAULT '0' COMMENT '微信端登录次数',
  `pc_times` int(11) DEFAULT '0' COMMENT 'pc端登录次数',
  `last_login_ip` varchar(20) DEFAULT '' COMMENT '最后登录IP',
  `last_login_datetime` int(11) DEFAULT '0' COMMENT '最后登录时间',
  PRIMARY KEY (`count_id`)
) ENGINE=InnoDB AUTO_INCREMENT=93 DEFAULT CHARSET=utf8 COMMENT='用户登录次数';

-- ----------------------------
-- Records of d_user_login_count
-- ----------------------------
INSERT INTO `d_user_login_count` VALUES ('1', '1', '15326253563', '8', '8', '0', '0', '192.168.11.141', '1535704655');
INSERT INTO `d_user_login_count` VALUES ('2', '2', '15666666666', '91', '91', '0', '0', '192.168.11.145', '1532686629');
INSERT INTO `d_user_login_count` VALUES ('3', '3', '13169601345', '9', '9', '0', '0', '127.0.0.1', '1532343082');
INSERT INTO `d_user_login_count` VALUES ('4', '4', '18938880002', '9', '9', '0', '0', '192.168.11.111', '1532684132');
INSERT INTO `d_user_login_count` VALUES ('5', '5', '13988887777', '1', '1', '0', '0', '192.168.11.105', '1532074864');
INSERT INTO `d_user_login_count` VALUES ('6', '6', '13966666666', '1', '1', '0', '0', '192.168.11.175', '1532075062');
INSERT INTO `d_user_login_count` VALUES ('7', '7', '13111111111', '21', '21', '0', '0', '192.168.11.107', '1533889821');
INSERT INTO `d_user_login_count` VALUES ('8', '8', '13122222222', '2', '2', '0', '0', '192.168.11.105', '1532311254');
INSERT INTO `d_user_login_count` VALUES ('9', '9', '13133333333', '3', '3', '0', '0', '192.168.11.105', '1532084693');
INSERT INTO `d_user_login_count` VALUES ('10', '10', '13656986365', '1', '1', '0', '0', '192.168.11.175', '1532077969');
INSERT INTO `d_user_login_count` VALUES ('11', '11', '13233334444', '1', '1', '0', '0', '192.168.11.105', '1532078353');
INSERT INTO `d_user_login_count` VALUES ('12', '12', '18316856636', '10', '10', '0', '0', '192.168.11.159', '1532680192');
INSERT INTO `d_user_login_count` VALUES ('13', '13', '13822223333', '1', '1', '0', '0', '192.168.11.105', '1532083946');
INSERT INTO `d_user_login_count` VALUES ('14', '14', '18938880003', '1', '1', '0', '0', '192.168.11.117', '1532084054');
INSERT INTO `d_user_login_count` VALUES ('15', '15', '13644444444', '1', '1', '0', '0', '192.168.11.145', '1532084300');
INSERT INTO `d_user_login_count` VALUES ('16', '16', '13244447777', '1', '1', '0', '0', '192.168.11.105', '1532084611');
INSERT INTO `d_user_login_count` VALUES ('17', '18', '15966663333', '1', '1', '0', '0', '192.168.11.105', '1532084975');
INSERT INTO `d_user_login_count` VALUES ('18', '19', '13653535353', '2', '2', '0', '0', '192.168.11.105', '1532085147');
INSERT INTO `d_user_login_count` VALUES ('19', '20', '13266696666', '2', '2', '0', '0', '192.168.11.105', '1532085285');
INSERT INTO `d_user_login_count` VALUES ('20', '21', '13652882888', '3', '3', '0', '0', '192.168.11.148', '1532143030');
INSERT INTO `d_user_login_count` VALUES ('21', '22', '18688886666', '4', '4', '0', '0', '192.168.11.159', '1532155733');
INSERT INTO `d_user_login_count` VALUES ('22', '23', '18938880001', '7', '7', '0', '0', '192.168.11.111', '1532688113');
INSERT INTO `d_user_login_count` VALUES ('23', '24', '18938880004', '4', '4', '0', '0', '192.168.11.139', '1532687989');
INSERT INTO `d_user_login_count` VALUES ('24', '25', '18938880005', '9', '9', '0', '0', '192.168.11.111', '1532684108');
INSERT INTO `d_user_login_count` VALUES ('25', '27', '18938880006', '8', '8', '0', '0', '192.168.11.168', '1532411909');
INSERT INTO `d_user_login_count` VALUES ('26', '28', '13693335613', '1', '1', '0', '0', '192.168.11.140', '1532168247');
INSERT INTO `d_user_login_count` VALUES ('27', '29', '18667987795', '3', '3', '0', '0', '192.168.11.122', '1532315504');
INSERT INTO `d_user_login_count` VALUES ('28', '30', '13688587474', '2', '2', '0', '0', '192.168.11.148', '1532309225');
INSERT INTO `d_user_login_count` VALUES ('29', '31', '13563253653', '7', '7', '0', '0', '192.168.11.168', '1532320364');
INSERT INTO `d_user_login_count` VALUES ('30', '32', '13100000000', '2', '2', '0', '0', '192.168.11.105', '1532328390');
INSERT INTO `d_user_login_count` VALUES ('31', '33', '13144444444', '6', '6', '0', '0', '192.168.11.105', '1533120333');
INSERT INTO `d_user_login_count` VALUES ('32', '35', '13188888888', '4', '4', '0', '0', '192.168.11.105', '1532316128');
INSERT INTO `d_user_login_count` VALUES ('33', '36', '13625252525', '1', '1', '0', '0', '192.168.11.175', '1532317750');
INSERT INTO `d_user_login_count` VALUES ('34', '37', '17520487755', '29', '29', '0', '0', '192.168.11.126', '1533892937');
INSERT INTO `d_user_login_count` VALUES ('35', '38', '13828851205', '4', '4', '0', '0', '192.168.11.109', '1532422643');
INSERT INTO `d_user_login_count` VALUES ('36', '39', '13532563636', '1', '1', '0', '0', '192.168.11.148', '1532332944');
INSERT INTO `d_user_login_count` VALUES ('37', '40', '18938880007', '2', '2', '0', '0', '192.168.11.139', '1532337897');
INSERT INTO `d_user_login_count` VALUES ('38', '41', '13211111111', '8', '8', '0', '0', '192.168.11.105', '1533120768');
INSERT INTO `d_user_login_count` VALUES ('39', '42', '13333333333', '29', '29', '0', '0', '192.168.11.148', '1532691681');
INSERT INTO `d_user_login_count` VALUES ('40', '43', '13311111111', '4', '4', '0', '0', '192.168.11.105', '1533120289');
INSERT INTO `d_user_login_count` VALUES ('41', '44', '13656565656', '2', '2', '0', '0', '192.168.11.107', '1532338680');
INSERT INTO `d_user_login_count` VALUES ('42', '48', '13169601345', '1', '1', '0', '0', '192.168.11.118', '1532415219');
INSERT INTO `d_user_login_count` VALUES ('43', '49', '13169601345', '1', '1', '0', '0', '192.168.11.118', '1532415262');
INSERT INTO `d_user_login_count` VALUES ('44', '50', '13169601345', '1', '1', '0', '0', '192.168.11.118', '1532415411');
INSERT INTO `d_user_login_count` VALUES ('45', '51', '13169601345', '1', '1', '0', '0', '192.168.11.118', '1532415521');
INSERT INTO `d_user_login_count` VALUES ('46', '52', '13169601345', '1', '1', '0', '0', '192.168.11.118', '1532415690');
INSERT INTO `d_user_login_count` VALUES ('47', '53', '13169601345', '21', '21', '0', '0', '127.0.0.1', '1533111722');
INSERT INTO `d_user_login_count` VALUES ('48', '54', '15388999999', '1', '1', '0', '0', '192.168.11.133', '1532419416');
INSERT INTO `d_user_login_count` VALUES ('49', '55', '15828569966', '1', '1', '0', '0', '192.168.11.133', '1532419604');
INSERT INTO `d_user_login_count` VALUES ('50', '56', '15585586666', '1', '1', '0', '0', '192.168.11.133', '1532420502');
INSERT INTO `d_user_login_count` VALUES ('51', '57', '15855586666', '1', '1', '0', '0', '192.168.11.117', '1532421732');
INSERT INTO `d_user_login_count` VALUES ('52', '58', '18866666666', '1', '1', '0', '0', '192.168.11.170', '1532423688');
INSERT INTO `d_user_login_count` VALUES ('53', '59', '15768118003', '41', '41', '0', '0', '192.168.11.119', '1533887009');
INSERT INTO `d_user_login_count` VALUES ('54', '60', '13563253555', '1', '1', '0', '0', '192.168.11.148', '1532487927');
INSERT INTO `d_user_login_count` VALUES ('55', '61', '15888888888', '27', '27', '0', '0', '192.168.11.168', '1532683385');
INSERT INTO `d_user_login_count` VALUES ('56', '62', '18565610262', '1', '1', '0', '0', '192.168.11.128', '1532497004');
INSERT INTO `d_user_login_count` VALUES ('57', '63', '13156557485', '1', '1', '0', '0', '192.168.11.118', '1532500691');
INSERT INTO `d_user_login_count` VALUES ('58', '64', '13156557432', '1', '1', '0', '0', '192.168.11.118', '1532500749');
INSERT INTO `d_user_login_count` VALUES ('59', '65', '13156234561', '1', '1', '0', '0', '192.168.11.118', '1532500769');
INSERT INTO `d_user_login_count` VALUES ('60', '66', '13656565654', '1', '1', '0', '0', '192.168.11.148', '1532500847');
INSERT INTO `d_user_login_count` VALUES ('61', '67', '13656565657', '1', '1', '0', '0', '192.168.11.148', '1532500851');
INSERT INTO `d_user_login_count` VALUES ('62', '69', '13656565658', '1', '1', '0', '0', '192.168.11.148', '1532501240');
INSERT INTO `d_user_login_count` VALUES ('63', '70', '15685858447', '1', '1', '0', '0', '192.168.11.148', '1532501645');
INSERT INTO `d_user_login_count` VALUES ('64', '71', '15637183131', '1', '1', '0', '0', '192.168.11.101', '1532503240');
INSERT INTO `d_user_login_count` VALUES ('65', '73', '15255596669', '1', '1', '0', '0', '192.168.11.168', '1532589936');
INSERT INTO `d_user_login_count` VALUES ('66', '74', '15258089999', '2', '2', '0', '0', '192.168.11.148', '1532676161');
INSERT INTO `d_user_login_count` VALUES ('67', '75', '17520487766', '2', '2', '0', '0', '192.168.11.126', '1532915517');
INSERT INTO `d_user_login_count` VALUES ('68', '76', '15285669666', '2', '2', '0', '0', '192.168.11.170', '1532682194');
INSERT INTO `d_user_login_count` VALUES ('69', '77', '13988888888', '1', '1', '0', '0', '192.168.11.105', '1532693897');
INSERT INTO `d_user_login_count` VALUES ('70', '78', '13555555555', '6', '6', '0', '0', '192.168.11.105', '1533121139');
INSERT INTO `d_user_login_count` VALUES ('71', '79', '15013851179', '4', '4', '0', '0', '192.168.11.141', '1533889921');
INSERT INTO `d_user_login_count` VALUES ('72', '80', '18501370077', '1', '1', '0', '0', '192.168.11.126', '1533290903');
INSERT INTO `d_user_login_count` VALUES ('73', '81', '18501370070', '2', '2', '0', '0', '192.168.11.131', '1533887554');
INSERT INTO `d_user_login_count` VALUES ('74', '82', '17520487755', '12', '12', '0', '0', '192.168.11.126', '1535708149');
INSERT INTO `d_user_login_count` VALUES ('75', '83', '18501370070', '12', '12', '0', '0', '192.168.11.141', '1535702606');
INSERT INTO `d_user_login_count` VALUES ('76', '84', '13111111111', '80', '80', '0', '0', '192.168.11.119', '1536032271');
INSERT INTO `d_user_login_count` VALUES ('77', '85', '15768118003', '7', '7', '0', '0', '192.168.11.119', '1534491132');
INSERT INTO `d_user_login_count` VALUES ('78', '86', '15013851179', '5', '5', '0', '0', '192.168.11.141', '1534926816');
INSERT INTO `d_user_login_count` VALUES ('79', '87', '15768118003', '8', '8', '0', '0', '192.168.11.119', '1534996083');
INSERT INTO `d_user_login_count` VALUES ('80', '96', null, '1', '0', '0', '0', null, '1535705041');
INSERT INTO `d_user_login_count` VALUES ('81', '98', null, '1', '0', '0', '0', null, '1535705396');
INSERT INTO `d_user_login_count` VALUES ('82', '99', null, '3', '2', '0', '0', '192.168.11.141', '1535943670');
INSERT INTO `d_user_login_count` VALUES ('83', '100', null, '2', '1', '0', '0', '192.168.11.103', '1535708061');
INSERT INTO `d_user_login_count` VALUES ('84', '101', null, '1', '0', '0', '0', null, '1535709345');
INSERT INTO `d_user_login_count` VALUES ('85', '102', null, '3', '2', '0', '0', '192.168.11.141', '1535941179');
INSERT INTO `d_user_login_count` VALUES ('86', '103', '15768118003', '1', '1', '0', '0', '192.168.11.103', '1535710189');
INSERT INTO `d_user_login_count` VALUES ('87', '104', '15768118003', '1', '1', '0', '0', '192.168.11.103', '1535710304');
INSERT INTO `d_user_login_count` VALUES ('88', '108', '15768118003', '1', '1', '0', '0', '192.168.11.103', '1535712012');
INSERT INTO `d_user_login_count` VALUES ('89', '109', '15768118003', '2', '2', '0', '0', '192.168.11.103', '1535712322');
INSERT INTO `d_user_login_count` VALUES ('90', '110', '15768118003', '1', '1', '0', '0', '192.168.11.103', '1535712629');
INSERT INTO `d_user_login_count` VALUES ('91', '111', '15768118003', '10', '10', '0', '0', '192.168.11.153', '1535965481');
INSERT INTO `d_user_login_count` VALUES ('92', '112', '15013851180', '15', '15', '0', '0', '192.168.11.141', '1536046141');

-- ----------------------------
-- Table structure for `d_user_login_log`
-- ----------------------------
DROP TABLE IF EXISTS `d_user_login_log`;
CREATE TABLE `d_user_login_log` (
  `log_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '日志ID',
  `user_id` int(11) NOT NULL COMMENT '用户ID',
  `phone` varchar(50) DEFAULT NULL COMMENT '手机号',
  `equipment` varchar(200) DEFAULT '' COMMENT '设备信息',
  `login_ip` varchar(20) DEFAULT NULL COMMENT '登录IP',
  `login_datetime` int(11) DEFAULT NULL COMMENT '登录时间',
  `terminal` tinyint(4) DEFAULT '1' COMMENT '操作终端 1 PC 2 安卓 3 IOS 4 微信',
  PRIMARY KEY (`log_id`),
  KEY `log_userID` (`user_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=603 DEFAULT CHARSET=utf8 COMMENT='用户登录日志信息';

-- ----------------------------
-- Records of d_user_login_log
-- ----------------------------
INSERT INTO `d_user_login_log` VALUES ('484', '84', '13111111111', 'PassWordLogin|abcdefg|A85311A4AFB13ACD3CEE2FD9D8F327A9473292D1AAA6A78EE8322C87AD2AA0CA', '192.168.11.103', '1535357411', '3');
INSERT INTO `d_user_login_log` VALUES ('485', '84', '13111111111', 'smsLogin|abcdefg|54814BB7088C4C5FA17D1896223A56FC82241F6CB67C875BB849A05BEEE868D5', '192.168.11.103', '1535357683', '3');
INSERT INTO `d_user_login_log` VALUES ('486', '84', '13111111111', 'smsLogin|abcdefg|AEBF284CED0810AA4234F255886EE9DD03EF5AAE3A4951711B8941A169C57141', '192.168.11.103', '1535358404', '3');
INSERT INTO `d_user_login_log` VALUES ('487', '84', '13111111111', 'PassWordLogin|abcdefg|DB246DC86C937944F4014FF21BCA41A1F70E5B7FC3C40FAE7D275050D0BD95C5', '192.168.11.103', '1535359066', '3');
INSERT INTO `d_user_login_log` VALUES ('488', '84', '13111111111', 'PassWordLogin|abcdefg|5D230881B882B327F7B6D780322863BFE350BAF13322A8E177CC767DD14AE561', '192.168.11.103', '1535424266', '3');
INSERT INTO `d_user_login_log` VALUES ('489', '84', '13111111111', 'PassWordLogin|abcdefg|685A14D3F4DB017AD09738EBC132BD16910D841D1FA840F264D4695CCAFA8629', '192.168.11.103', '1535425219', '3');
INSERT INTO `d_user_login_log` VALUES ('490', '84', '13111111111', 'PassWordLogin|8EA06BC6-27D5-4F88-B1B0-645F51DA8508|E5B4E5330EF168EF1AA37E88DD5BF132509C63D961ABB8CE09E2623303CBEC2A', '192.168.11.141', '1535435177', '3');
INSERT INTO `d_user_login_log` VALUES ('491', '84', '13111111111', 'PassWordLogin|abcdefg|3ED71D65FF0E114E54810F925D76ED92F1F4F67FF61FCEB258B98EAEF2A76C49', '192.168.11.103', '1535435928', '3');
INSERT INTO `d_user_login_log` VALUES ('492', '84', '13111111111', 'PassWordLogin|abcdefg|E53E734BA8A500910DD43A552B0EDB89304395B1DC8E120256BEAE0C81560003', '192.168.11.103', '1535440976', '3');
INSERT INTO `d_user_login_log` VALUES ('493', '84', '13111111111', 'PassWordLogin|8EA06BC6-27D5-4F88-B1B0-645F51DA8508|E5B4E5330EF168EF1AA37E88DD5BF132509C63D961ABB8CE09E2623303CBEC2A', '192.168.11.141', '1535451019', '3');
INSERT INTO `d_user_login_log` VALUES ('494', '84', '13111111111', 'PassWordLogin|abcdefg|A72CD131B415D41F6AE79340DE66B92964F376F5B94634A50A87FBB106CBE5FE', '192.168.11.103', '1535452345', '3');
INSERT INTO `d_user_login_log` VALUES ('495', '84', '13111111111', 'PassWordLogin|abcdefg|9790ACF9496252E700F8E3F3C02E969BCF87DBB40D46D6E8E90A7DAC32AE1A3F', '10.10.110.101', '1535509822', '3');
INSERT INTO `d_user_login_log` VALUES ('496', '84', '13111111111', 'PassWordLogin|8EA06BC6-27D5-4F88-B1B0-645F51DA8508|E5B4E5330EF168EF1AA37E88DD5BF132509C63D961ABB8CE09E2623303CBEC2A', '192.168.11.141', '1535618413', '3');
INSERT INTO `d_user_login_log` VALUES ('497', '84', '13111111111', 'PassWordLogin|nexs-888|D0591BCCAA681B96F6A989E5F52C0AA4A394A00D510084193C782FF0278CD39B', '192.168.11.119', '1535618781', '3');
INSERT INTO `d_user_login_log` VALUES ('498', '84', '13111111111', 'PassWordLogin|nexs-888|A35ADDEFD8A6D9422CF2334D89AC4D7983853EC432CC08194D75FC206C42881D', '192.168.11.119', '1535618887', '3');
INSERT INTO `d_user_login_log` VALUES ('499', '84', '13111111111', 'PassWordLogin|nexs-888|819570447D0550BA346B69FA864A4E57455C7832E7B763C8DA090E88B910C203', '192.168.11.119', '1535619265', '3');
INSERT INTO `d_user_login_log` VALUES ('500', '84', '13111111111', 'PassWordLogin|nexs-888|9B850DC9003E869B47BD6652B8767F724D32801B45EEE14A3B7C1D975210A69B', '192.168.11.119', '1535619319', '3');
INSERT INTO `d_user_login_log` VALUES ('501', '84', '13111111111', 'PassWordLogin|8EA06BC6-27D5-4F88-B1B0-645F51DA8508|E5B4E5330EF168EF1AA37E88DD5BF132509C63D961ABB8CE09E2623303CBEC2A', '192.168.11.141', '1535619455', '3');
INSERT INTO `d_user_login_log` VALUES ('502', '84', '13111111111', 'PassWordLogin|nexs-888|9A99D3EC75FA7AB1CE0E88FBB6AE44F27D34AC4B4D0B3310F7F32D251DEEBB30', '192.168.11.119', '1535619705', '3');
INSERT INTO `d_user_login_log` VALUES ('503', '84', '13111111111', 'PassWordLogin|8EA06BC6-27D5-4F88-B1B0-645F51DA8508|E5B4E5330EF168EF1AA37E88DD5BF132509C63D961ABB8CE09E2623303CBEC2A', '192.168.11.141', '1535619841', '3');
INSERT INTO `d_user_login_log` VALUES ('504', '84', '13111111111', 'PassWordLogin|nexs-888|7345D25DCE629D079F4D91EAEA215ED1452B6A536766FBAFB5641D31C1198426', '192.168.11.119', '1535620836', '3');
INSERT INTO `d_user_login_log` VALUES ('505', '84', '13111111111', 'PassWordLogin|nexs-888|459078225E33E0D85B459D36DCD4136BC32B932D6C5754690B8CA042F9DA0252', '192.168.11.119', '1535621131', '3');
INSERT INTO `d_user_login_log` VALUES ('506', '84', '13111111111', 'PassWordLogin|abcdefg|DD06D0C2A4BEA6D439BC1BBE6C4DD9A5F6896C0C744591518B45A8EAA5621BDD', '10.10.110.102', '1535621209', '3');
INSERT INTO `d_user_login_log` VALUES ('507', '84', '13111111111', 'PassWordLogin|nexs-888|4B3ABD0DF4570160211FEDD56A02D29D7CE99AB8E795C3F51C7395AFBC31387A', '192.168.11.119', '1535621386', '3');
INSERT INTO `d_user_login_log` VALUES ('508', '84', '13111111111', 'PassWordLogin|nexs-888|3501E2AAD3D2451FD02C54415EA598564AAF426FC0BD04062F1E685ECF4AE462', '192.168.11.119', '1535621901', '3');
INSERT INTO `d_user_login_log` VALUES ('509', '84', '13111111111', 'PassWordLogin|abcdefg|5677A1610142D00BD9D2DA4FF2803726BB6F7E6707469F5B0616D8875DB01435', '10.10.110.102', '1535622262', '3');
INSERT INTO `d_user_login_log` VALUES ('510', '84', '13111111111', 'PassWordLogin|8EA06BC6-27D5-4F88-B1B0-645F51DA8508|E5B4E5330EF168EF1AA37E88DD5BF132509C63D961ABB8CE09E2623303CBEC2A', '192.168.11.141', '1535622478', '3');
INSERT INTO `d_user_login_log` VALUES ('511', '84', '13111111111', 'PassWordLogin|nexs-888|02D066607F20716B4F9081EDCB3B1277146A27A7B46698F65CC12368AE8C5AEB', '192.168.11.119', '1535622943', '3');
INSERT INTO `d_user_login_log` VALUES ('512', '84', '13111111111', 'PassWordLogin|nexs-888|64552E47649C2473046EC7A598C32FA48A98B2564BD8053DE2DB475ADE41FFF5', '192.168.11.119', '1535623101', '3');
INSERT INTO `d_user_login_log` VALUES ('513', '84', '13111111111', 'PassWordLogin|8EA06BC6-27D5-4F88-B1B0-645F51DA8508|E5B4E5330EF168EF1AA37E88DD5BF132509C63D961ABB8CE09E2623303CBEC2A', '192.168.11.141', '1535623639', '3');
INSERT INTO `d_user_login_log` VALUES ('514', '83', '18501370070', 'PassWordLogin|8EA06BC6-27D5-4F88-B1B0-645F51DA8508|687610197106320DFD7C284185E4D688C10A247B46713E0DBA010CD10D1C33BC', '192.168.11.141', '1535624175', '3');
INSERT INTO `d_user_login_log` VALUES ('515', '83', '18501370070', 'PassWordLogin|nexs-888|61E125352A525B42F25C0A64A7D7AAC68E646B2CA93DBAC5FCF67AD9A172F4B2', '192.168.11.119', '1535624346', '3');
INSERT INTO `d_user_login_log` VALUES ('516', '83', '18501370070', 'PassWordLogin|nexs-888|B56B0FFEB89159D8D2CAFF7B6F65535CF634F8419FB3E64A7A96C7913A070017', '192.168.11.119', '1535624486', '3');
INSERT INTO `d_user_login_log` VALUES ('517', '83', '18501370070', 'PassWordLogin|nexs-888|31BED87672B53CCBCEE773C04C009E293079181D4C7B7B6F69218D124BA7C3AA', '192.168.11.119', '1535624789', '3');
INSERT INTO `d_user_login_log` VALUES ('518', '83', '18501370070', 'PassWordLogin|nexs-888|D480E8B1650A7441F5B3F24C2852817F10F3B962E8AB42C91E18D06F883EC29E', '192.168.11.119', '1535624903', '3');
INSERT INTO `d_user_login_log` VALUES ('519', '83', '18501370070', 'PassWordLogin|8EA06BC6-27D5-4F88-B1B0-645F51DA8508|687610197106320DFD7C284185E4D688C10A247B46713E0DBA010CD10D1C33BC', '192.168.11.141', '1535625982', '3');
INSERT INTO `d_user_login_log` VALUES ('520', '84', '13111111111', 'PassWordLogin|8EA06BC6-27D5-4F88-B1B0-645F51DA8508|B2F031CFB0329EE61873F787557DAD15D56406E59F8FED0C78B9770CC3C5C3EB', '192.168.11.141', '1535626075', '3');
INSERT INTO `d_user_login_log` VALUES ('521', '1', '15326253563', 'PassWordLogin|8EA06BC6-27D5-4F88-B1B0-645F51DA8508|64E97DF20F78FE5367A3821E24F528C1E6733AF7114DE25B5FDFE7538387FC87', '192.168.11.141', '1535626364', '3');
INSERT INTO `d_user_login_log` VALUES ('522', '82', '17520487755', 'PassWordLogin|8EA06BC6-27D5-4F88-B1B0-645F51DA8508|8A5B9D846F1E085F3E2741DD3FB4A1E9DA4A7CFD1EB5DA641694112E63DDE515', '192.168.11.141', '1535626401', '3');
INSERT INTO `d_user_login_log` VALUES ('523', '84', '13111111111', 'PassWordLogin|8EA06BC6-27D5-4F88-B1B0-645F51DA8508|51BA6004E760F804653543FFA57C81E224E35254BD289481F45593543F437188', '192.168.11.141', '1535626738', '3');
INSERT INTO `d_user_login_log` VALUES ('524', '1', '15326253563', 'PassWordLogin|8EA06BC6-27D5-4F88-B1B0-645F51DA8508|759B7E457929A4253E9CDD69589FE8A6C495602FBE763CD6DEF62D8F0BF787EF', '192.168.11.141', '1535627937', '3');
INSERT INTO `d_user_login_log` VALUES ('525', '82', '17520487755', 'PassWordLogin|8EA06BC6-27D5-4F88-B1B0-645F51DA8508|164017018EB95CBB59F7242EEC3BC586EC3CBB07B2E830498E04FEAC96F98C92', '192.168.11.141', '1535627998', '3');
INSERT INTO `d_user_login_log` VALUES ('526', '83', '18501370070', 'PassWordLogin|nexs-888|A184A12448F812E191AFD85A306B6F3323A530F47CE0C0DF23EB97169A30B891', '192.168.11.119', '1535628364', '3');
INSERT INTO `d_user_login_log` VALUES ('527', '84', '13111111111', 'PassWordLogin|8EA06BC6-27D5-4F88-B1B0-645F51DA8508|0CDF9E1ED8C7A4027C50BC4CAB352A31679C6078183D1626EC5C083A74DF07CC', '192.168.11.141', '1535684937', '3');
INSERT INTO `d_user_login_log` VALUES ('528', '84', '13111111111', 'PassWordLogin|nexs-888|8911A5E19CB7B3AAE9D8098C3EFD9AD1990057F373BDE49CADA5B91D8A0C8AED', '192.168.11.119', '1535685182', '3');
INSERT INTO `d_user_login_log` VALUES ('529', '84', '13111111111', 'PassWordLogin|nexs-888|C251212B54F70A3A25661FD1F36FECE335A15BBC501079E29684268D9AB24B1F', '192.168.11.119', '1535685287', '3');
INSERT INTO `d_user_login_log` VALUES ('530', '84', '13111111111', 'PassWordLogin|8EA06BC6-27D5-4F88-B1B0-645F51DA8508|0CDF9E1ED8C7A4027C50BC4CAB352A31679C6078183D1626EC5C083A74DF07CC', '192.168.11.141', '1535687677', '3');
INSERT INTO `d_user_login_log` VALUES ('531', '84', '13111111111', 'PassWordLogin|nexs-888|108CD291548DA45526D3D806D60F5DFABC511E77846387C142FF25F9A19D3350', '192.168.11.119', '1535693971', '3');
INSERT INTO `d_user_login_log` VALUES ('532', '84', '13111111111', 'PassWordLogin|8EA06BC6-27D5-4F88-B1B0-645F51DA8508|0CDF9E1ED8C7A4027C50BC4CAB352A31679C6078183D1626EC5C083A74DF07CC', '192.168.11.141', '1535694250', '3');
INSERT INTO `d_user_login_log` VALUES ('533', '83', '18501370070', 'PassWordLogin|8EA06BC6-27D5-4F88-B1B0-645F51DA8508|FAB16D669EABD5265E08BBBAC4155CBF0F4F889A90A36D2128BEBB1F5645C506', '192.168.11.141', '1535700273', '3');
INSERT INTO `d_user_login_log` VALUES ('534', '1', '18866666666', 'PassWordLogin|8EA06BC6-27D5-4F88-B1B0-645F51DA8508|7A9ABB31BA1CBDC2FFE29A5A2EDEFFE424D84E3D55348341B9A426688A48ECA6', '192.168.11.141', '1535700851', '3');
INSERT INTO `d_user_login_log` VALUES ('535', '1', '18866666666', 'PassWordLogin|8EA06BC6-27D5-4F88-B1B0-645F51DA8508|EA01922CF301BCE02B72FEBC77B61276F19E02DE7AB3BCB62D507D7F376615FB', '192.168.11.141', '1535701200', '3');
INSERT INTO `d_user_login_log` VALUES ('536', '84', '13111111111', 'PassWordLogin|nexs-888|C511F7C41E44A8651F23BFAE16E7912F7C948C61FAED1AB1A66B5D1A159DECBB', '192.168.11.119', '1535701417', '3');
INSERT INTO `d_user_login_log` VALUES ('537', '1', '18866666666', 'PassWordLogin|8EA06BC6-27D5-4F88-B1B0-645F51DA8508|75B79CA80153D5BC0413C174370DBA2ED3190D979B5E8B0D63135395F8CD6B27', '192.168.11.141', '1535701790', '3');
INSERT INTO `d_user_login_log` VALUES ('538', '82', '17520487755', 'PassWordLogin|nexs-888|C332E61ABDD59A59F2E5FDA8F66513A2A058746ABBE8E01343AD76F2E98D7D51', '192.168.11.119', '1535701851', '3');
INSERT INTO `d_user_login_log` VALUES ('539', '84', '13111111111', 'PassWordLogin|abcdefg|4755C12FBC5D46FD11C3CAE522843340257486DAB26823A86B890ADF97E7CA83', '192.168.11.103', '1535702128', '3');
INSERT INTO `d_user_login_log` VALUES ('540', '82', '17520487755', 'PassWordLogin|8EA06BC6-27D5-4F88-B1B0-645F51DA8508|EAD40340AD20BCE26DEA40BBDC09AEC9BF13C1983456F270C2E7C5563B83A5D3', '192.168.11.141', '1535702563', '3');
INSERT INTO `d_user_login_log` VALUES ('541', '83', '18501370070', 'PassWordLogin|8EA06BC6-27D5-4F88-B1B0-645F51DA8508|F93194EDFFF909E3D49C6892B29370A364ED18476CB0C9807A2C28EF3F52E6E4', '192.168.11.141', '1535702606', '3');
INSERT INTO `d_user_login_log` VALUES ('542', '1', '18866666666', 'PassWordLogin|8EA06BC6-27D5-4F88-B1B0-645F51DA8508|7FA6D71CFA85A100E12D874BBE96DC3F1BDEBE93737B22A6C3FF00AB3AC4AB76', '192.168.11.141', '1535702685', '3');
INSERT INTO `d_user_login_log` VALUES ('543', '1', '18866666666', 'PassWordLogin|8EA06BC6-27D5-4F88-B1B0-645F51DA8508|448DC61817CCCC43CB5F431FAF8916C9012F87D38BD55E29BD219D2BA582D1CE', '192.168.11.141', '1535704655', '3');
INSERT INTO `d_user_login_log` VALUES ('544', '96', null, 'RegisterLogin|abcdefg|75DC374156E5B30B3DC9D9EEE810742E330C3C3B014E3FEE621B10E52608C91E', null, '1535705041', null);
INSERT INTO `d_user_login_log` VALUES ('545', '98', null, 'RegisterLogin|abcdefg|4F40F289923C0920FB94AD7DB63F73564B9846731D6A3CC1DD0E66B60415DAC0', null, '1535705396', null);
INSERT INTO `d_user_login_log` VALUES ('546', '99', null, 'RegisterLogin|8EA06BC6-27D5-4F88-B1B0-645F51DA8508|0CA670ECA1E2BF6E3D37C80D02E9B6C3AF3FF06AC71989B9DDF4A1D564F05B19', null, '1535706899', null);
INSERT INTO `d_user_login_log` VALUES ('547', '100', null, 'RegisterLogin|80AFC481-891C-4B61-8D37-2EFB2432EFB0|CAC5BBBB457230FF4656A776FABF98BDF85A714C403027D70EB57F521AC44605', null, '1535706938', null);
INSERT INTO `d_user_login_log` VALUES ('548', '84', '13111111111', 'PassWordLogin|abcdefg|3175558D6F0C8DE14706D287A286FB7A2A704505E8E5867FF4B5CFF85E0F90A3', '192.168.11.103', '1535707990', '3');
INSERT INTO `d_user_login_log` VALUES ('549', '100', '15768118003', 'PassWordLogin|abcdefg|D8F16C128F1CB2261744661B24237419E10B6DD00B6EE54B2994BAAA604782E3', '192.168.11.103', '1535708061', '3');
INSERT INTO `d_user_login_log` VALUES ('550', '99', '15013851179', 'PassWordLogin|8EA06BC6-27D5-4F88-B1B0-645F51DA8508|A0DEAE6FD28EB491B9D931958BCFA77F3DE3EC2AA7A3275794652DA0DC43ED53', '192.168.11.141', '1535708109', '3');
INSERT INTO `d_user_login_log` VALUES ('551', '82', '17520487755', 'PassWordLogin|1BEFC0E4-DEDC-48DA-9098-F1352C4012A2|E3ED5FC4580D9D00696FE5CE318F3687DA775243D3FAAD9D10F44702FC257B76', '192.168.11.126', '1535708149', '3');
INSERT INTO `d_user_login_log` VALUES ('552', '101', null, 'RegisterLogin|abcdefg|057ABB16EE2036F978058C1167D88223C6F43E1F7CE30D20717C045DB04DBA29', null, '1535709345', null);
INSERT INTO `d_user_login_log` VALUES ('553', '102', null, 'RegisterLogin|8EA06BC6-27D5-4F88-B1B0-645F51DA8508|B22C65C67E0004255E18AA7A5E524535A8FB79A6E22433F960E7592C45799935', null, '1535709527', null);
INSERT INTO `d_user_login_log` VALUES ('554', '84', '13111111111', 'PassWordLogin|8EA06BC6-27D5-4F88-B1B0-645F51DA8508|82BF30AF3F06B24042DCAACC9FC7273D0177B2117E73558C70ABA3537EAF6D5A', '192.168.11.141', '1535709749', '3');
INSERT INTO `d_user_login_log` VALUES ('555', '102', '13111111113', 'PassWordLogin|8EA06BC6-27D5-4F88-B1B0-645F51DA8508|E5D1573C913477493208E5AC0A6274C0FED9A3F596DA56CF4202CDFB484AB0B3', '192.168.11.141', '1535709985', '3');
INSERT INTO `d_user_login_log` VALUES ('556', '84', '13111111111', 'PassWordLogin|8EA06BC6-27D5-4F88-B1B0-645F51DA8508|DAAC5FC1F5B66BCE08BE92D7F40B55667B7C36954C5E5DF285B8DFE6856ACEF3', '192.168.11.141', '1535710127', '3');
INSERT INTO `d_user_login_log` VALUES ('557', '103', '15768118003', 'PassWordLogin|abcdefg|6EB97C5DA2D44BF6AFBDD9BAB13F25588ADA66CF9939CCA10493298BE9AF956A', '192.168.11.103', '1535710189', '3');
INSERT INTO `d_user_login_log` VALUES ('558', '104', '15768118003', 'PassWordLogin|abcdefg|E1FA28303EB02C3C9259C47A454456A526810B76AFD8C5FCBAE932598E0AD5B8', '192.168.11.103', '1535710304', '3');
INSERT INTO `d_user_login_log` VALUES ('559', '108', '15768118003', 'RegisterLogin|abcdefg|CD7B57A840412A6678F916D7BADB7A8FA7084AD5845CC34B1D2B726AF04E3492', '192.168.11.103', '1535712012', '3');
INSERT INTO `d_user_login_log` VALUES ('560', '109', '15768118003', 'RegisterLogin|abcdefg|3C17AE55AEAF8D222D0F0AF5DD7BE8B1D6ABFEFED19D4636EEF981297AE94969', '192.168.11.103', '1535712220', '3');
INSERT INTO `d_user_login_log` VALUES ('561', '109', '15768118003', 'PassWordLogin|abcdefg|773FB63692CEE756DFF8A91E90FB704C8D001AFFB2E308DEDCF974EA73ABCE36', '192.168.11.103', '1535712322', '3');
INSERT INTO `d_user_login_log` VALUES ('562', '110', '15768118003', 'RegisterLogin|abcdefg|EC6A7E3181264FBADAD4B5EF89349CAF06BB733D562799D866D3DC6162DE1072', '192.168.11.103', '1535712629', '3');
INSERT INTO `d_user_login_log` VALUES ('563', '102', '13111111113', 'PassWordLogin|8EA06BC6-27D5-4F88-B1B0-645F51DA8508|6F3A960CFF5929C3B386AF4FECD0A9551199694E9547A5DE7415169AA5C9CC5E', '192.168.11.141', '1535941179', '3');
INSERT INTO `d_user_login_log` VALUES ('564', '84', '13111111111', 'PassWordLogin|nexs-888|3C8119AC335B909609A7F65AF425602986E9B2EBF9B36DEAC294D7177099FEC4', '192.168.11.119', '1535942304', '3');
INSERT INTO `d_user_login_log` VALUES ('565', '84', '13111111111', 'PassWordLogin|nexs-888|0872831F792053444952B1BF76CB46DDBD3C3237FDA893A9CF9E3C8138100477', '192.168.11.119', '1535942475', '3');
INSERT INTO `d_user_login_log` VALUES ('566', '111', '15768118003', 'RegisterLogin|abcdefg|A6BEDC907AE67E8D5F38F9EADEDD9707612948D39090F6EF8D6E26AE330E80E6', '10.10.110.100', '1535942927', '3');
INSERT INTO `d_user_login_log` VALUES ('567', '84', '13111111111', 'PassWordLogin|8EA06BC6-27D5-4F88-B1B0-645F51DA8508|D862E9CEC3B2771CADF6899E64A910883B142BF97D5E26F278073B03B676FC46', '192.168.11.141', '1535943002', '3');
INSERT INTO `d_user_login_log` VALUES ('568', '84', '13111111111', 'PassWordLogin|nexs-888|E9EBC23BC40F7A071A76637AF63F6C82717B8A3C69AFF95F1D79B7814118186B', '192.168.11.119', '1535943117', '3');
INSERT INTO `d_user_login_log` VALUES ('569', '111', '15768118003', 'PassWordLogin|abcdefg|7A99871FBD45E62E6D838612BDFCDDB1882CE03A453E60703FA50B8E49F9F93B', '10.10.110.100', '1535943591', '3');
INSERT INTO `d_user_login_log` VALUES ('570', '99', '15013851179', 'PassWordLogin|8EA06BC6-27D5-4F88-B1B0-645F51DA8508|D862E9CEC3B2771CADF6899E64A910883B142BF97D5E26F278073B03B676FC46', '192.168.11.141', '1535943670', '3');
INSERT INTO `d_user_login_log` VALUES ('571', '111', '15768118003', 'PassWordLogin|abcdefg|AC4D6597EFEDFB860053E9BB360C87C4837DE1703E589310AF1D8C24814DED9C', '10.10.110.100', '1535943673', '3');
INSERT INTO `d_user_login_log` VALUES ('572', '111', '15768118003', 'PassWordLogin|abcdefg|EB899D2B1BC30058403BC03B68FCF2016CF08965EF9E72F712DEE44CB918C44A', '10.10.110.100', '1535953479', '3');
INSERT INTO `d_user_login_log` VALUES ('573', '111', '15768118003', 'PassWordLogin|abcdefg|9EC986F44AE22DFE1C152622ED3CF9709B039C689F3039F8FB692AAF2103B5D8', '10.10.110.100', '1535953794', '3');
INSERT INTO `d_user_login_log` VALUES ('574', '111', '15768118003', 'PassWordLogin|abcdefg|5133D5806FA6B7F43A8BDE24BBE49AD3F22A0CD37474F23301EA688F483F505B', '10.10.110.100', '1535954992', '3');
INSERT INTO `d_user_login_log` VALUES ('575', '84', '13111111111', 'PassWordLogin|nexs-888|E79813A4ECEC779BCB6C71AA4F52072CB0F6E9908232C1AFF2A437CD5EB9A5DC', '192.168.11.119', '1535956879', '3');
INSERT INTO `d_user_login_log` VALUES ('576', '84', '13111111111', 'PassWordLogin|8EA06BC6-27D5-4F88-B1B0-645F51DA8508|BF18FE5D45BB42B541E4176F815E0BDB720C10C96F3E98A7BA2713480E751450', '192.168.11.141', '1535957031', '3');
INSERT INTO `d_user_login_log` VALUES ('577', '111', '15768118003', 'PassWordLogin|80AFC481-891C-4B61-8D37-2EFB2432EFB0|CAC5BBBB457230FF4656A776FABF98BDF85A714C403027D70EB57F521AC44605', '192.168.11.153', '1535959030', '3');
INSERT INTO `d_user_login_log` VALUES ('578', '111', '15768118003', 'PassWordLogin|80AFC481-891C-4B61-8D37-2EFB2432EFB0|17D06828E74EDE6772BE42D4CF32E4C6D40BF5F0A3E5AE2D0ADE297F2B756385', '192.168.11.153', '1535959124', '3');
INSERT INTO `d_user_login_log` VALUES ('579', '84', '13111111111', 'PassWordLogin|8EA06BC6-27D5-4F88-B1B0-645F51DA8508|CC9463204AAB9FA605013B28681F7A687A3FBB0EAAE80639A50EEB9D8F2EA637', '192.168.11.141', '1535960993', '3');
INSERT INTO `d_user_login_log` VALUES ('580', '84', '13111111111', 'PassWordLogin|8EA06BC6-27D5-4F88-B1B0-645F51DA8508|106AADE68EE95DE319B1D87963DF870D7AF6590D2CE34291D71F6D64ACAC24AD', '192.168.11.141', '1535961016', '3');
INSERT INTO `d_user_login_log` VALUES ('581', '84', '13111111111', 'PassWordLogin|8EA06BC6-27D5-4F88-B1B0-645F51DA8508|72F8B0FB82FB1FAF35CCE7A55D78DEF8AABB132071A5368B8836F4F331996B44', '192.168.11.141', '1535961187', '3');
INSERT INTO `d_user_login_log` VALUES ('582', '111', '15768118003', 'PassWordLogin|80AFC481-891C-4B61-8D37-2EFB2432EFB0|5A67E5AA3D814FDCFD287D9065309CDAC80349C11FF7074D119CF2E92164A846', '192.168.11.153', '1535962410', '3');
INSERT INTO `d_user_login_log` VALUES ('583', '111', '15768118003', 'PassWordLogin|80AFC481-891C-4B61-8D37-2EFB2432EFB0|B35C31D3E4E1038E1BE859B3563EC4CF3CA4DAD8A7B033370B3D641B827DDA5B', '192.168.11.153', '1535965481', '3');
INSERT INTO `d_user_login_log` VALUES ('584', '84', '13111111111', 'PassWordLogin|8EA06BC6-27D5-4F88-B1B0-645F51DA8508|755505B66F1A5374287E6379BE66FCE65CE8A6E98EC56E711D16AF3DFAB776F6', '192.168.11.141', '1535967162', '3');
INSERT INTO `d_user_login_log` VALUES ('585', '112', '15013851180', 'RegisterLogin|8EA06BC6-27D5-4F88-B1B0-645F51DA8508|BC2DA465857452A40055A847F12DF99D7C9E78846CE7BB6B7CFA2B5F92916D1D', '192.168.11.141', '1535967570', '3');
INSERT INTO `d_user_login_log` VALUES ('586', '112', '15013851180', 'PassWordLogin|8EA06BC6-27D5-4F88-B1B0-645F51DA8508|61653DF9240821D38BF1033DE73CB0E56B535979C18B99C354FAAB30AC63411C', '192.168.11.141', '1536029885', '3');
INSERT INTO `d_user_login_log` VALUES ('587', '84', '13111111111', 'PassWordLogin|nexs-888|6B479096E986628ABE82E8A1CAA36EBC081301C07F9270FD641FE2816DEF4487', '192.168.11.119', '1536032036', '3');
INSERT INTO `d_user_login_log` VALUES ('588', '84', '13111111111', 'PassWordLogin|nexs-888|F289660A103EC43AC8E8F310FCC7902B29B3FA6C7E3C34BB5905D535C8B8C619', '192.168.11.119', '1536032195', '3');
INSERT INTO `d_user_login_log` VALUES ('589', '84', '13111111111', 'PassWordLogin|nexs-888|88C8B321E70F7602E967795C5D0A50F9ADAFC439064978A3EC48E083457C94D5', '192.168.11.119', '1536032271', '3');
INSERT INTO `d_user_login_log` VALUES ('590', '112', '15013851180', 'PassWordLogin|nexs-888|F93520635468A00F188140992A94174D83351B817D5CE83EFBAD76F3BC4DF806', '192.168.11.119', '1536033132', '3');
INSERT INTO `d_user_login_log` VALUES ('591', '112', '15013851180', 'PassWordLogin|8EA06BC6-27D5-4F88-B1B0-645F51DA8508|61653DF9240821D38BF1033DE73CB0E56B535979C18B99C354FAAB30AC63411C', '192.168.11.141', '1536033225', '3');
INSERT INTO `d_user_login_log` VALUES ('592', '112', '15013851180', 'PassWordLogin|nexs-888|91916D88EA59DA6868C682EBE6E633A9885E2F933B07CA2B79D6B5DA5F2E8076', '192.168.11.119', '1536034438', '3');
INSERT INTO `d_user_login_log` VALUES ('593', '112', '15013851180', 'PassWordLogin|8EA06BC6-27D5-4F88-B1B0-645F51DA8508|61653DF9240821D38BF1033DE73CB0E56B535979C18B99C354FAAB30AC63411C', '192.168.11.141', '1536043196', '3');
INSERT INTO `d_user_login_log` VALUES ('594', '112', '15013851180', 'PassWordLogin|nexs-888|5C0C780861C61A03B88F559B5AFCB591668A45069D486078E68BF41E90210F47', '192.168.11.119', '1536043690', '3');
INSERT INTO `d_user_login_log` VALUES ('595', '112', '15013851180', 'PassWordLogin|nexs-888|A78A4B7F0F6ACAD2D9765E68AE276CA6C796ED05B30CAA0AE95E56898BE902FF', '192.168.11.119', '1536043775', '3');
INSERT INTO `d_user_login_log` VALUES ('596', '112', '15013851180', 'PassWordLogin|nexs-888|1E718244EA34F97CEDB01DF955DC181952A589F0D99411AF9CC7945F0B8714E5', '192.168.11.119', '1536043885', '3');
INSERT INTO `d_user_login_log` VALUES ('597', '112', '15013851180', 'PassWordLogin|nexs-888|619224D1E9D81D16A609FAB5593A3C41BD022CF1108F635F83B5B523008B6E0F', '192.168.11.119', '1536044252', '3');
INSERT INTO `d_user_login_log` VALUES ('598', '112', '15013851180', 'PassWordLogin|nexs-888|E9BA5940448D715E1E828155448630F4D5AC5568DA7EBEDED9FE95CC7A089ECD', '192.168.11.119', '1536044475', '3');
INSERT INTO `d_user_login_log` VALUES ('599', '112', '15013851180', 'PassWordLogin|nexs-888|0733A2991850DDF71547311EFE9B0639154386FDFD3DA725E122ADA757A0399D', '192.168.11.119', '1536044690', '3');
INSERT INTO `d_user_login_log` VALUES ('600', '112', '15013851180', 'PassWordLogin|nexs-888|3503AE2D7E52B0DE5329A7D6621F0C34F0C109245FAE766D96439A6C5BFC0FA4', '192.168.11.119', '1536045812', '3');
INSERT INTO `d_user_login_log` VALUES ('601', '112', '15013851180', 'PassWordLogin|nexs-888|7A22CAE88937A88903E64C9BBEDE6FD7F872DAD8F6BD3D7D8B2CD41E0C096D8C', '192.168.11.119', '1536045895', '3');
INSERT INTO `d_user_login_log` VALUES ('602', '112', '15013851180', 'PassWordLogin|8EA06BC6-27D5-4F88-B1B0-645F51DA8508|61653DF9240821D38BF1033DE73CB0E56B535979C18B99C354FAAB30AC63411C', '192.168.11.141', '1536046141', '3');

-- ----------------------------
-- Table structure for `d_user_maillist`
-- ----------------------------
DROP TABLE IF EXISTS `d_user_maillist`;
CREATE TABLE `d_user_maillist` (
  `maillist_id` int(15) NOT NULL AUTO_INCREMENT,
  `user_id` int(30) DEFAULT '0' COMMENT '用户的ID',
  `phone` varchar(255) DEFAULT '' COMMENT '用户的电话',
  `name` varchar(125) DEFAULT '' COMMENT '姓名',
  `add_datetime` int(16) DEFAULT '0',
  PRIMARY KEY (`maillist_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6941 DEFAULT CHARSET=utf8 COMMENT='通讯录名单';

-- ----------------------------
-- Records of d_user_maillist
-- ----------------------------

-- ----------------------------
-- Table structure for `d_user_realname`
-- ----------------------------
DROP TABLE IF EXISTS `d_user_realname`;
CREATE TABLE `d_user_realname` (
  `real_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL COMMENT '用户ID',
  `order_no` varchar(50) DEFAULT '0' COMMENT '单据编号',
  `real_name` varchar(200) DEFAULT '' COMMENT '姓名',
  `card_type` tinyint(4) DEFAULT '1' COMMENT '证件类型 1 二代身份证',
  `card_id` varchar(200) DEFAULT NULL COMMENT '身份证号码',
  `audit_status` tinyint(4) DEFAULT NULL COMMENT '审核状态 0 申请中 1 审核通过 2 审核不通过',
  `user_age` tinyint(4) DEFAULT '0' COMMENT '年龄',
  `user_sex` tinyint(4) DEFAULT '1' COMMENT '性别 0 未知 1 男 2 女',
  `birth_year` int(11) DEFAULT '0' COMMENT '生日年份',
  `birth_day` varchar(10) DEFAULT NULL COMMENT '生日',
  `card_address` varchar(200) DEFAULT NULL COMMENT '证件地址',
  `audit_user_id` int(11) DEFAULT '0' COMMENT '审核人ID',
  `audit_user_name` varchar(50) DEFAULT NULL,
  `audit_datetime` int(11) DEFAULT NULL COMMENT '审核时间',
  `audit_remark` varchar(1000) DEFAULT NULL COMMENT '审核备注',
  `add_datetime` int(11) NOT NULL COMMENT '创建时间',
  `terminal` tinyint(4) DEFAULT '1' COMMENT '操作终端 1 PC 2 安卓 3 IOS 4 微信',
  `operate_ip` varchar(50) DEFAULT NULL COMMENT '操作IP',
  PRIMARY KEY (`real_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 COMMENT='实名认证信息表';

-- ----------------------------
-- Records of d_user_realname
-- ----------------------------
INSERT INTO `d_user_realname` VALUES ('1', '3', '0', '许培钦', '1', '445281199701195151', '0', '21', '1', '1997', '0119', '广东省揭阳市普宁市', '0', null, null, null, '1532343306', '3', '192.168.11.118');
INSERT INTO `d_user_realname` VALUES ('2', '23', '0', '李泽源', '1', '445122198706245912', '0', '31', '1', '1987', '0624', '广东省潮州市饶平县', '0', null, null, null, '1532680918', '3', '192.168.11.139');

-- ----------------------------
-- Table structure for `d_wx_codebind`
-- ----------------------------
DROP TABLE IF EXISTS `d_wx_codebind`;
CREATE TABLE `d_wx_codebind` (
  `wcb_id` int(11) NOT NULL AUTO_INCREMENT,
  `code` varchar(20) DEFAULT '' COMMENT '关注微信的验证码',
  `open_id` varchar(225) DEFAULT '' COMMENT '获取到这个码的openid',
  `user_id` int(15) DEFAULT '0' COMMENT '验证关注的用户ID',
  `add_detatime` varchar(125) DEFAULT '' COMMENT '添加时间',
  `bind_detatime` varchar(125) DEFAULT '' COMMENT '绑定时间',
  PRIMARY KEY (`wcb_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='微信关注码存储';

-- ----------------------------
-- Records of d_wx_codebind
-- ----------------------------

-- ----------------------------
-- Table structure for `d_wx_menu`
-- ----------------------------
DROP TABLE IF EXISTS `d_wx_menu`;
CREATE TABLE `d_wx_menu` (
  `menu_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `parent_id` smallint(4) unsigned NOT NULL DEFAULT '0' COMMENT '如果是菜单，parentID有效',
  `menu_name` varchar(10) NOT NULL DEFAULT '' COMMENT '菜单标题，一级不超过4个汉字，2级不超过7个汉字',
  `menu_type` varchar(16) NOT NULL DEFAULT '' COMMENT '根据微信消息推送接口来：even[click，view]',
  `menu_action` varchar(100) NOT NULL DEFAULT '' COMMENT '菜单动作',
  `is_authorize` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '0：不用授权，1：需要授权',
  `sort` smallint(4) unsigned NOT NULL DEFAULT '0' COMMENT '菜单排序',
  `status` tinyint(1) unsigned NOT NULL DEFAULT '1' COMMENT '状态：1 有效  0：删除',
  `add_datetime` int(11) unsigned NOT NULL COMMENT '添加时间',
  `add_user_id` int(11) unsigned NOT NULL COMMENT '添加人id',
  `add_user_name` varchar(50) NOT NULL DEFAULT '' COMMENT '创建人',
  `edit_user_id` int(11) unsigned NOT NULL COMMENT '修改人id',
  `edit_datetime` int(11) unsigned NOT NULL COMMENT '修改时间',
  `edit_user_name` varchar(50) NOT NULL DEFAULT '' COMMENT '修改人',
  `operate_ip` varchar(50) NOT NULL DEFAULT '' COMMENT '操作IP地址',
  PRIMARY KEY (`menu_id`)
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
INSERT INTO `d_wx_menu` VALUES ('11', '5', '理财', 'view', 'http://shdev.qianbox.net/Wechat/Project/plist', '1', '3', '0', '1493276898', '50', 'liuhy', '15', '1512541748', '', '127.0.0.1');
INSERT INTO `d_wx_menu` VALUES ('12', '5', '新手活动', 'view', 'http://shdev.qianbox.net/wechat/User/register', '1', '4', '1', '1493276951', '50', 'liuhy', '15', '1512541761', '', '121.35.189.154');
INSERT INTO `d_wx_menu` VALUES ('13', '5', '帮助中心', 'view', 'http://dev.qianbox.net/Wechat/About/help/', '1', '5', '0', '1493276989', '50', 'liuhy', '0', '0', '', '127.0.0.1');
INSERT INTO `d_wx_menu` VALUES ('14', '5', '六个', 'click', 'liu', '0', '6', '0', '1493277241', '50', 'liuhy', '0', '0', '', '202.105.127.122');
INSERT INTO `d_wx_menu` VALUES ('15', '9', '测试删除', 'click', 'h', '0', '1', '0', '1493277762', '50', 'liuhy', '0', '0', '', '202.105.127.122');
INSERT INTO `d_wx_menu` VALUES ('16', '0', '我的菜单', 'click', 'wode2', '0', '3', '1', '1493277924', '50', 'liuhy', '2', '1529118177', 'liup', '127.0.0.1');
INSERT INTO `d_wx_menu` VALUES ('17', '16', 'ces', 'none', '', '0', '0', '0', '1493278187', '50', 'liuhy', '0', '0', '', '202.105.127.122');
INSERT INTO `d_wx_menu` VALUES ('18', '3', '登陆', 'view', 'http://shdev.qianbox.net/wechat/user/login', '1', '0', '1', '1508305083', '16', 'liup', '15', '1512541773', '', '121.35.189.154');
INSERT INTO `d_wx_menu` VALUES ('19', '3', '111', 'none', '', '0', '0', '1', '1525682141', '55', 'lizy', '0', '0', '', '113.89.98.211');

-- ----------------------------
-- Table structure for `d_wx_reply`
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='微信回复';

-- ----------------------------
-- Records of d_wx_reply
-- ----------------------------

-- ----------------------------
-- Table structure for `d_wx_share`
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='微信分享';

-- ----------------------------
-- Records of d_wx_share
-- ----------------------------

-- ----------------------------
-- Procedure structure for `sp_Login_log`
-- ----------------------------
DROP PROCEDURE IF EXISTS `sp_Login_log`;
DELIMITER ;;
CREATE DEFINER=`root`@`192.168.11.%` PROCEDURE `sp_Login_log`(
            m_user_id 			INT(11),         -- 用户ID
            m_phone 		  varchar(50),
            m_terminal 		varchar(50),     -- 终端
            m_equipment  	varchar(255),   -- 设备描述
            m_operate_ip 	varchar(50)
)
    COMMENT '登录日志'
BEGIN
        DECLARE t_error INTEGER DEFAULT 0;  
        DECLARE result INTEGER DEFAULT 0; 
        DECLARE v_pc INTEGER DEFAULT 0; 
        DECLARE v_wx INTEGER DEFAULT 0; -- 微信
        DECLARE v_m INTEGER DEFAULT 0; -- 手机 	
        DECLARE CONTINUE HANDLER FOR SQLEXCEPTION SET t_error=1;  
        START TRANSACTION;
        IF (m_terminal=1) THEN
            set v_pc = 1;
        ELSEIF (m_terminal=4) THEN
            set v_wx = 1;
        ELSEIF (m_terminal>1 and m_terminal<4) THEN
            set v_m = 1;
        END IF;
            insert into d_user_login_log(user_id,phone,equipment,login_ip,login_datetime,terminal) VALUES(m_user_id,m_phone,m_equipment,m_operate_ip,UNIX_TIMESTAMP(),m_terminal);
            IF (EXISTS(select 1 from d_user_login_count where user_id = m_user_id)) THEN
             UPDATE d_user_login_count SET  
                login_times=(login_times+1),
                mobile_times=(mobile_times + v_m),
                wechat_times=(wechat_times + v_wx),
                pc_times= (pc_times + v_pc),
                last_login_ip= m_operate_ip,
                last_login_datetime= UNIX_TIMESTAMP()
                WHERE user_id=m_user_id;
                ELSE
                insert into d_user_login_count(user_id,phone,login_times,mobile_times,wechat_times,pc_times,last_login_ip,last_login_datetime) VALUES(m_user_id,m_phone,1,v_m,v_wx,v_pc,m_operate_ip,UNIX_TIMESTAMP());
               END IF;
        SET result= ROW_COUNT();
             IF t_error = 1 or result<1 THEN
                     ROLLBACK;		
          SET result= ROW_COUNT();
             ELSE
                     COMMIT; 
             END IF;
        SELECT result;
END
;;
DELIMITER ;

-- ----------------------------
-- Procedure structure for `sp_update_account`
-- ----------------------------
DROP PROCEDURE IF EXISTS `sp_update_account`;
DELIMITER ;;
CREATE DEFINER=`root`@`192.168.11.%` PROCEDURE `sp_update_account`(m_userID 			INT(11),
            m_bustype 		varchar(50),
            m_orderNo 		varchar(50),
            m_available  	decimal(15,6),
            m_unavailable decimal(15,6),
            m_trade       decimal(15,6),
            m_remark 			varchar(200))
    COMMENT '账户变更操作'
BEGIN
    DECLARE t_error INTEGER DEFAULT 0;
    DECLARE result INTEGER DEFAULT 0;
    DECLARE CONTINUE HANDLER FOR SQLEXCEPTION SET t_error=1;
		START TRANSACTION;
      insert into d_account_detail(user_id,`status`,bus_type,order_no,available,unavailable,trade,add_datetime,remark)
                  select A.user_id,1,m_bustype,m_orderNo,(A.available+m_available),(A.unavailable+m_unavailable),
                  m_trade,UNIX_TIMESTAMP(),m_remark
                  from  d_account A where A.user_id =m_userID;
			IF ROW_COUNT()>0 THEN
			 UPDATE d_account SET
					available=(available+m_available),
					unavailable=(unavailable+m_unavailable),
					edit_datetime=UNIX_TIMESTAMP()
					WHERE user_id=m_userID;
			END IF;
   SET result= ROW_COUNT();
	IF t_error = 1 or result<1 THEN
			ROLLBACK;
     SET result= ROW_COUNT();
	ELSE
		COMMIT;
	END IF;
  SELECT result;
END
;;
DELIMITER ;
