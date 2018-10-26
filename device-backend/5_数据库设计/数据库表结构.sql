/*
Navicat MySQL Data Transfer

Source Server         : 天智星开发（11.11）
Source Server Version : 50717
Source Host           : 192.168.11.11:3306
Source Database       : device-backend

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2018-09-04 17:35:40
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `t_admin_user`
-- ----------------------------
DROP TABLE IF EXISTS `t_admin_user`;
CREATE TABLE `t_admin_user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(20) DEFAULT NULL,
  `nick_name` varchar(20) DEFAULT NULL,
  `password` varchar(50) DEFAULT NULL,
  `cellphone` varchar(20) DEFAULT NULL,
  `status` tinyint(4) DEFAULT NULL,
  `create_time` timestamp NULL DEFAULT NULL COMMENT '账户创建时间，时间戳',
  `role_id` int(11) DEFAULT NULL COMMENT '0--管理员，1--普通用户',
  `modify_time` timestamp NULL DEFAULT NULL COMMENT '账户修改时间，时间戳形式',
  `remark` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8 COMMENT='后台用户信息';

-- ----------------------------
-- Records of t_admin_user
-- ----------------------------
INSERT INTO `t_admin_user` VALUES ('1', 'admin', '管理员', 'faa3a40036df51de4ab785d1e27dd673', null, '1', null, '0', null, null);
INSERT INTO `t_admin_user` VALUES ('2', 'user', null, '129f2361c9b33ff3c72b7a09cfbe60df', null, '1', null, '1', '2018-07-19 17:46:37', null);
INSERT INTO `t_admin_user` VALUES ('3', 'lan', 'lan', 'faa3a40036df51de4ab785d1e27dd673', null, '1', null, '0', null, null);
INSERT INTO `t_admin_user` VALUES ('4', 'lan2010', '昵称', 'faa3a40036df51de4ab785d1e27dd673', '15013888888', '1', '2018-07-19 16:06:52', '1', '2018-07-20 10:35:13', null);
INSERT INTO `t_admin_user` VALUES ('5', 'lan2011', '昵称lan', 'faa3a40036df51de4ab785d1e27dd673', '15018888888', '1', '2018-07-19 17:47:49', '1', null, null);
INSERT INTO `t_admin_user` VALUES ('6', 'lan20111', '', '54fb8b94643ec4a74bcc8f64eb3c8883', '', '1', '2018-07-19 17:48:07', '0', null, null);
INSERT INTO `t_admin_user` VALUES ('7', 'lan12', '', 'faa3a40036df51de4ab785d1e27dd673', '', '1', '2018-07-19 18:14:10', '1', '2018-07-20 10:25:50', null);
INSERT INTO `t_admin_user` VALUES ('8', 'lan1231', '', 'faa3a40036df51de4ab785d1e27dd673', '', '1', '2018-07-19 18:14:48', '1', null, null);
INSERT INTO `t_admin_user` VALUES ('9', 'lan21312', 'dwawde', '3961a4713c908c21bc0bd38a94614937', '15041654657', '1', '2018-07-19 18:36:49', '0', '2018-07-21 10:40:16', null);
INSERT INTO `t_admin_user` VALUES ('10', 'gang', '', 'faa3a40036df51de4ab785d1e27dd673', '', '1', '2018-07-19 18:38:36', '0', null, null);
INSERT INTO `t_admin_user` VALUES ('11', 'Arvit', '', 'faa3a40036df51de4ab785d1e27dd673', '', '1', '2018-08-03 10:26:56', '0', null, null);

-- ----------------------------
-- Table structure for `t_dev_online`
-- ----------------------------
DROP TABLE IF EXISTS `t_dev_online`;
CREATE TABLE `t_dev_online` (
  `dev_number` varchar(20) NOT NULL COMMENT '设备编号',
  `online_status` tinyint(4) DEFAULT NULL COMMENT '在线状态，0不在线，1在线',
  `record_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '生成记录的时间',
  PRIMARY KEY (`dev_number`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='记录设备当前在线情况';

-- ----------------------------
-- Records of t_dev_online
-- ----------------------------
INSERT INTO `t_dev_online` VALUES ('21313213132123', '0', '2018-07-23 11:27:20');
INSERT INTO `t_dev_online` VALUES ('55555666667777', '0', '2018-07-11 16:53:35');
INSERT INTO `t_dev_online` VALUES ('A0000044440000', '0', '2018-07-25 16:53:31');
INSERT INTO `t_dev_online` VALUES ('a1c8eea63486b8', '0', '2018-08-07 22:32:48');
INSERT INTO `t_dev_online` VALUES ('gf212132121555', '0', '2018-07-18 17:14:12');
INSERT INTO `t_dev_online` VALUES ('test1234567890', '0', '2018-07-23 11:24:53');

-- ----------------------------
-- Table structure for `t_device`
-- ----------------------------
DROP TABLE IF EXISTS `t_device`;
CREATE TABLE `t_device` (
  `dev_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增编号',
  `dev_number` varchar(20) NOT NULL COMMENT '设备编号',
  `dev_model` varchar(20) DEFAULT NULL COMMENT '设备型号',
  `belong_unit_id` int(11) DEFAULT '0' COMMENT '所属单位（0未知，1天智星，其它后续再定义）',
  `product_time` timestamp NULL DEFAULT NULL COMMENT '设备生产日期',
  `mac` varchar(20) DEFAULT NULL COMMENT 'mac地址（格式E3:00:00:98:1E:09,冒号分隔，字母大写）',
  `add_time` timestamp NULL DEFAULT NULL COMMENT '设备入库时间',
  `bind_account` varchar(20) DEFAULT '' COMMENT '绑定的账户（星链app的账号）',
  `bind_time` timestamp NULL DEFAULT NULL COMMENT '绑定时间（绑定星链app的时间）',
  `is_onlined` tinyint(4) DEFAULT '0' COMMENT '是否上过线，0未上过线，1上过线',
  `tag` int(11) NOT NULL DEFAULT '1' COMMENT '生效状态，0无效，1生效',
  `dev_passwd` varchar(20) DEFAULT NULL COMMENT '设备密码',
  PRIMARY KEY (`dev_id`)
) ENGINE=InnoDB AUTO_INCREMENT=99 DEFAULT CHARSET=utf8 COMMENT='充电宝设备的信息';

-- ----------------------------
-- Records of t_device
-- ----------------------------
INSERT INTO `t_device` VALUES ('51', 'a1c8eea63406b8', '1.0', '1', '2018-07-12 00:00:00', null, '2018-07-12 18:16:30', null, null, '0', '1', null);
INSERT INTO `t_device` VALUES ('52', 'a0000000000001', '1.0', '1', null, 'A0:00:00:00:00:00:01', '2018-07-12 18:25:10', null, null, '0', '1', null);
INSERT INTO `t_device` VALUES ('53', 'a0000000000002', '001', '1', null, 'A0:00:00:00:00:00:02', '2018-07-12 18:25:10', '', null, '0', '1', null);
INSERT INTO `t_device` VALUES ('54', 'a0000000000003', '001', '1', null, 'A0:00:00:00:00:00:03', '2018-07-12 18:25:10', null, null, '1', '1', null);
INSERT INTO `t_device` VALUES ('55', 'a0000000000004', '0', '1', null, 'A0:00:00:00:00:00:04', '2018-07-12 18:25:10', null, null, '0', '1', null);
INSERT INTO `t_device` VALUES ('56', 'a0000000000005', '0', '1', null, 'A0:00:00:00:00:00:05', '2018-07-12 18:25:10', null, null, '0', '1', null);
INSERT INTO `t_device` VALUES ('57', 'A0000000000006', '0', '1', null, 'A0:00:00:00:00:00:06', '2018-07-12 18:25:10', null, null, '1', '1', null);
INSERT INTO `t_device` VALUES ('58', 'A0000000000007', '0', '1', null, 'A0:00:00:00:00:00:07', '2018-07-12 18:25:10', null, null, '0', '1', null);
INSERT INTO `t_device` VALUES ('59', 'A0000000000008', '0', '1', null, 'A0:00:00:00:00:00:08', '2018-07-12 18:25:10', null, null, '1', '1', null);
INSERT INTO `t_device` VALUES ('60', 'A0000000000009', '0', '1', null, 'A0:00:00:00:00:00:09', '2018-07-12 18:25:10', null, null, '0', '1', null);
INSERT INTO `t_device` VALUES ('61', 'A0000000000010', '0', '1', null, 'A0:00:00:00:00:00:10', '2018-07-12 18:25:10', null, null, '0', '1', null);
INSERT INTO `t_device` VALUES ('62', 'A0000000000011', '0', '1', null, 'A0:00:00:00:00:00:11', '2018-07-12 18:25:10', null, null, '0', '1', null);
INSERT INTO `t_device` VALUES ('63', 'A0000000000012', '0', '1', null, 'A0:00:00:00:00:00:12', '2018-07-12 18:25:10', null, null, '0', '1', null);
INSERT INTO `t_device` VALUES ('64', 'A0000000000013', '0', '1', null, 'A0:00:00:00:00:00:13', '2018-07-12 18:25:10', null, null, '0', '1', null);
INSERT INTO `t_device` VALUES ('65', 'A0000000000014', '0', '1', null, 'A0:00:00:00:00:00:14', '2018-07-12 18:25:10', null, null, '0', '1', null);
INSERT INTO `t_device` VALUES ('66', 'A0000000000015', '0', '1', null, 'A0:00:00:00:00:00:15', '2018-07-12 18:25:10', null, null, '0', '1', null);
INSERT INTO `t_device` VALUES ('67', 'A0000000000016', '0', '1', null, 'A0:00:00:00:00:00:16', '2018-07-12 18:25:10', null, null, '0', '1', null);
INSERT INTO `t_device` VALUES ('68', 'A0000000000017', '0', '1', null, 'A0:00:00:00:00:00:17', '2018-07-12 18:25:10', null, null, '1', '1', null);
INSERT INTO `t_device` VALUES ('69', 'A0000000000018', '0', '1', null, 'A0:00:00:00:00:00:18', '2018-07-12 18:25:10', null, null, '0', '1', null);
INSERT INTO `t_device` VALUES ('70', 'A0000000000019', '0', '1', null, 'A0:00:00:00:00:00:19', '2018-07-12 18:25:10', null, null, '0', '1', null);
INSERT INTO `t_device` VALUES ('71', 'A0000000000020', '0', '1', null, 'A0:00:00:00:00:00:20', '2018-07-12 18:25:10', null, null, '1', '1', null);
INSERT INTO `t_device` VALUES ('72', 'A0000000000021', '0', '1', null, 'A0:00:00:00:00:00:21', '2018-07-12 18:25:10', null, null, '0', '1', null);
INSERT INTO `t_device` VALUES ('73', 'A0000000000022', '0', '1', null, 'A0:00:00:00:00:00:22', '2018-07-12 18:25:10', null, null, '0', '1', null);
INSERT INTO `t_device` VALUES ('74', 'A0000000000023', '0', '1', null, 'A0:00:00:00:00:00:23', '2018-07-12 18:25:10', null, null, '1', '1', null);
INSERT INTO `t_device` VALUES ('75', '1', '设备相关API', '1', null, '1', '2018-07-12 18:25:48', null, null, '0', '1', null);
INSERT INTO `t_device` VALUES ('76', '2', '界面功能', '1', null, '2', '2018-07-12 18:25:48', null, null, '1', '1', null);
INSERT INTO `t_device` VALUES ('77', '3', '系统管理', '1', null, '3', '2018-07-12 18:25:48', null, null, '0', '0', null);
INSERT INTO `t_device` VALUES ('78', '4', '账号管理', '1', null, '4', '2018-07-12 18:25:48', null, null, '0', '1', null);
INSERT INTO `t_device` VALUES ('79', '5', '代理商管理', '1', null, '5', '2018-07-12 18:25:48', null, null, '0', '1', null);
INSERT INTO `t_device` VALUES ('80', '6', 'DashBoard', '1', null, '6', '2018-07-12 18:25:48', null, null, '0', '1', null);
INSERT INTO `t_device` VALUES ('81', '7', '设备管理', '1', null, '7', '2018-07-12 18:25:48', null, null, '0', '1', null);
INSERT INTO `t_device` VALUES ('82', '8', '资源管理', '1', null, '8', '2018-07-12 18:25:48', null, null, '0', '1', null);
INSERT INTO `t_device` VALUES ('83', '总计', '', '1', null, '总计', '2018-07-12 18:25:48', null, null, '0', '0', null);
INSERT INTO `t_device` VALUES ('84', '21313213132123', '212', '1', '2018-07-13 00:00:00', null, '2018-07-13 14:08:11', null, null, '1', '1', null);
INSERT INTO `t_device` VALUES ('85', 'A0000000000024', '0', '1', null, 'A0:00:00:00:00:00:24', '2018-07-13 14:09:59', null, null, '0', '1', null);
INSERT INTO `t_device` VALUES ('86', 'A0000000000100', '0', '1', null, 'A0:00:00:00:00:01:00', '2018-07-13 14:10:46', null, null, '0', '1', null);
INSERT INTO `t_device` VALUES ('87', '121212xcvc7878', '对方身份的', '1', '2018-07-10 00:00:00', null, '2018-07-13 14:27:45', null, null, '0', '1', null);
INSERT INTO `t_device` VALUES ('88', '55555666667777', ' 更丰富', '1', '2018-07-13 00:00:00', null, '2018-07-13 14:30:08', null, null, '0', '1', null);
INSERT INTO `t_device` VALUES ('89', '23235264255755', '131', '1', null, '23:23:52:64:25:57:55', '2018-07-13 14:34:15', null, null, '0', '1', null);
INSERT INTO `t_device` VALUES ('90', 'a1c8eea63486b8', '001', '0', null, 'a1c8eea63486b8', '2018-07-13 14:44:27', '18100000000', '2018-07-18 11:07:50', '1', '1', '123456');
INSERT INTO `t_device` VALUES ('91', 'A0000000000111', '0', '1', null, 'A0:00:00:00:00:01:11', '2018-07-13 15:03:09', null, null, '0', '1', null);
INSERT INTO `t_device` VALUES ('92', 'A0000044440000', '0', '1', null, 'A0:00:00:44:44:00:00', '2018-07-13 15:04:25', null, null, '0', '1', null);
INSERT INTO `t_device` VALUES ('93', 'gf212132121555', 'ghh', '1', '2018-07-14 00:00:00', null, '2018-07-14 15:13:06', '13855555555', '2018-07-04 16:10:56', '0', '1', null);
INSERT INTO `t_device` VALUES ('94', 'gf212132121556', 'wad', '1', '2018-07-17 00:00:00', null, '2018-07-17 11:04:11', null, null, '0', '1', null);
INSERT INTO `t_device` VALUES ('95', '阿萨德发撒的发', null, '0', null, null, null, '', null, '0', '1', null);
INSERT INTO `t_device` VALUES ('96', '111111111111as', 'yes', '1', '2018-07-09 00:00:00', null, '2018-07-21 15:01:12', null, null, '0', '1', null);
INSERT INTO `t_device` VALUES ('97', '3344555555553d', 'eeesssss', '1', '2018-07-02 00:00:00', null, '2018-07-21 15:02:56', null, null, '0', '1', null);
INSERT INTO `t_device` VALUES ('98', 'test1234567890', null, '0', null, 'test1234567890', '2018-07-23 11:24:53', '', null, '1', '1', null);

-- ----------------------------
-- Table structure for `t_device_log`
-- ----------------------------
DROP TABLE IF EXISTS `t_device_log`;
CREATE TABLE `t_device_log` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `order_num` int(11) DEFAULT NULL COMMENT '排序',
  `operation` varchar(200) DEFAULT NULL COMMENT '操作内容',
  `user` varchar(10) DEFAULT NULL COMMENT '执行操作的用户账号',
  `oper_time` timestamp NULL DEFAULT NULL COMMENT '操作时间',
  `mac` varchar(20) DEFAULT NULL COMMENT 'mac地址',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=116 DEFAULT CHARSET=utf8 COMMENT='记录用户对设备的所有操作日志';

-- ----------------------------
-- Records of t_device_log
-- ----------------------------
INSERT INTO `t_device_log` VALUES ('68', null, '用户sischain添加了一台设备编号为dfdfadf4440000的设备', 'sischain', '2018-07-12 18:16:30', null);
INSERT INTO `t_device_log` VALUES ('69', null, '用户sischain添加了一台设备编号为A0000000000001的设备', 'sischain', '2018-07-12 18:25:10', 'A0:00:00:00:00:00:01');
INSERT INTO `t_device_log` VALUES ('70', null, '用户sischain添加了一台设备编号为A0000000000002的设备', 'sischain', '2018-07-12 18:25:10', 'A0:00:00:00:00:00:02');
INSERT INTO `t_device_log` VALUES ('71', null, '用户sischain添加了一台设备编号为A0000000000003的设备', 'sischain', '2018-07-12 18:25:10', 'A0:00:00:00:00:00:03');
INSERT INTO `t_device_log` VALUES ('72', null, '用户sischain添加了一台设备编号为A0000000000004的设备', 'sischain', '2018-07-12 18:25:10', 'A0:00:00:00:00:00:04');
INSERT INTO `t_device_log` VALUES ('73', null, '用户sischain添加了一台设备编号为A0000000000005的设备', 'sischain', '2018-07-12 18:25:10', 'A0:00:00:00:00:00:05');
INSERT INTO `t_device_log` VALUES ('74', null, '用户sischain添加了一台设备编号为A0000000000006的设备', 'sischain', '2018-07-12 18:25:10', 'A0:00:00:00:00:00:06');
INSERT INTO `t_device_log` VALUES ('75', null, '用户sischain添加了一台设备编号为A0000000000007的设备', 'sischain', '2018-07-12 18:25:10', 'A0:00:00:00:00:00:07');
INSERT INTO `t_device_log` VALUES ('76', null, '用户sischain添加了一台设备编号为A0000000000008的设备', 'sischain', '2018-07-12 18:25:10', 'A0:00:00:00:00:00:08');
INSERT INTO `t_device_log` VALUES ('77', null, '用户sischain添加了一台设备编号为A0000000000009的设备', 'sischain', '2018-07-12 18:25:10', 'A0:00:00:00:00:00:09');
INSERT INTO `t_device_log` VALUES ('78', null, '用户sischain添加了一台设备编号为A0000000000010的设备', 'sischain', '2018-07-12 18:25:10', 'A0:00:00:00:00:00:10');
INSERT INTO `t_device_log` VALUES ('79', null, '用户sischain添加了一台设备编号为A0000000000011的设备', 'sischain', '2018-07-12 18:25:10', 'A0:00:00:00:00:00:11');
INSERT INTO `t_device_log` VALUES ('80', null, '用户sischain添加了一台设备编号为A0000000000012的设备', 'sischain', '2018-07-12 18:25:10', 'A0:00:00:00:00:00:12');
INSERT INTO `t_device_log` VALUES ('81', null, '用户sischain添加了一台设备编号为A0000000000013的设备', 'sischain', '2018-07-12 18:25:10', 'A0:00:00:00:00:00:13');
INSERT INTO `t_device_log` VALUES ('82', null, '用户sischain添加了一台设备编号为A0000000000014的设备', 'sischain', '2018-07-12 18:25:10', 'A0:00:00:00:00:00:14');
INSERT INTO `t_device_log` VALUES ('83', null, '用户sischain添加了一台设备编号为A0000000000015的设备', 'sischain', '2018-07-12 18:25:10', 'A0:00:00:00:00:00:15');
INSERT INTO `t_device_log` VALUES ('84', null, '用户sischain添加了一台设备编号为A0000000000016的设备', 'sischain', '2018-07-12 18:25:10', 'A0:00:00:00:00:00:16');
INSERT INTO `t_device_log` VALUES ('85', null, '用户sischain添加了一台设备编号为A0000000000017的设备', 'sischain', '2018-07-12 18:25:10', 'A0:00:00:00:00:00:17');
INSERT INTO `t_device_log` VALUES ('86', null, '用户sischain添加了一台设备编号为A0000000000018的设备', 'sischain', '2018-07-12 18:25:10', 'A0:00:00:00:00:00:18');
INSERT INTO `t_device_log` VALUES ('87', null, '用户sischain添加了一台设备编号为A0000000000019的设备', 'sischain', '2018-07-12 18:25:10', 'A0:00:00:00:00:00:19');
INSERT INTO `t_device_log` VALUES ('88', null, '用户sischain添加了一台设备编号为A0000000000020的设备', 'sischain', '2018-07-12 18:25:10', 'A0:00:00:00:00:00:20');
INSERT INTO `t_device_log` VALUES ('89', null, '用户sischain添加了一台设备编号为A0000000000021的设备', 'sischain', '2018-07-12 18:25:10', 'A0:00:00:00:00:00:21');
INSERT INTO `t_device_log` VALUES ('90', null, '用户sischain添加了一台设备编号为A0000000000022的设备', 'sischain', '2018-07-12 18:25:10', 'A0:00:00:00:00:00:22');
INSERT INTO `t_device_log` VALUES ('91', null, '用户sischain添加了一台设备编号为A0000000000023的设备', 'sischain', '2018-07-12 18:25:10', 'A0:00:00:00:00:00:23');
INSERT INTO `t_device_log` VALUES ('92', null, '用户sischain添加了一台设备编号为1的设备', 'sischain', '2018-07-12 18:25:48', '1');
INSERT INTO `t_device_log` VALUES ('93', null, '用户sischain添加了一台设备编号为2的设备', 'sischain', '2018-07-12 18:25:48', '2');
INSERT INTO `t_device_log` VALUES ('94', null, '用户sischain添加了一台设备编号为3的设备', 'sischain', '2018-07-12 18:25:48', '3');
INSERT INTO `t_device_log` VALUES ('95', null, '用户sischain添加了一台设备编号为4的设备', 'sischain', '2018-07-12 18:25:48', '4');
INSERT INTO `t_device_log` VALUES ('96', null, '用户sischain添加了一台设备编号为5的设备', 'sischain', '2018-07-12 18:25:48', '5');
INSERT INTO `t_device_log` VALUES ('97', null, '用户sischain添加了一台设备编号为6的设备', 'sischain', '2018-07-12 18:25:48', '6');
INSERT INTO `t_device_log` VALUES ('98', null, '用户sischain添加了一台设备编号为7的设备', 'sischain', '2018-07-12 18:25:48', '7');
INSERT INTO `t_device_log` VALUES ('99', null, '用户sischain添加了一台设备编号为8的设备', 'sischain', '2018-07-12 18:25:48', '8');
INSERT INTO `t_device_log` VALUES ('100', null, '用户sischain添加了一台设备编号为总计的设备', 'sischain', '2018-07-12 18:25:48', '总计');
INSERT INTO `t_device_log` VALUES ('101', null, '用户sischain删除了一台设备编号为3的设备', 'sischain', '2018-07-13 10:00:06', null);
INSERT INTO `t_device_log` VALUES ('102', null, '用户sischain添加了一台设备编号为21313213132123的设备', 'sischain', '2018-07-13 14:08:11', null);
INSERT INTO `t_device_log` VALUES ('103', null, '用户sischain添加了一台设备编号为A0000000000024的设备', 'sischain', '2018-07-13 14:09:59', 'A0:00:00:00:00:00:24');
INSERT INTO `t_device_log` VALUES ('104', null, '用户sischain添加了一台设备编号为A0000000000100的设备', 'sischain', '2018-07-13 14:10:46', 'A0:00:00:00:00:01:00');
INSERT INTO `t_device_log` VALUES ('105', null, '用户sischain添加了一台设备编号为121212xcvc7878的设备', 'sischain', '2018-07-13 14:27:45', null);
INSERT INTO `t_device_log` VALUES ('106', null, '用户sischain添加了一台设备编号为55555666667777的设备', 'sischain', '2018-07-13 14:30:08', null);
INSERT INTO `t_device_log` VALUES ('107', null, '用户sischain添加了一台设备编号为23235264255755的设备', 'sischain', '2018-07-13 14:34:15', '23:23:52:64:25:57:55');
INSERT INTO `t_device_log` VALUES ('108', null, '用户sischain添加了一台设备编号为A0000000000111的设备', 'sischain', '2018-07-13 15:03:09', 'A0:00:00:00:00:01:11');
INSERT INTO `t_device_log` VALUES ('109', null, '用户sischain添加了一台设备编号为A0000044440000的设备', 'sischain', '2018-07-13 15:04:25', 'A0:00:00:44:44:00:00');
INSERT INTO `t_device_log` VALUES ('110', null, '用户sischain添加了一台设备编号为gf212132121555的设备', 'sischain', '2018-07-14 15:13:06', null);
INSERT INTO `t_device_log` VALUES ('111', null, '用户sischain删除了一台设备编号为A0000044440000的设备', 'sischain', '2018-07-14 18:12:06', null);
INSERT INTO `t_device_log` VALUES ('112', null, '用户sischain删除了一台设备编号为总计的设备', 'sischain', '2018-07-14 18:12:18', null);
INSERT INTO `t_device_log` VALUES ('113', null, '用户lan添加了一台设备编号为gf212132121556的设备', 'lan', '2018-07-17 11:04:11', null);
INSERT INTO `t_device_log` VALUES ('114', null, '用户admin添加了一台设备编号为111111111111as的设备', 'admin', '2018-07-21 15:01:46', null);
INSERT INTO `t_device_log` VALUES ('115', null, '用户admin添加了一台设备编号为3344555555553d的设备', 'admin', '2018-07-21 15:02:57', null);

-- ----------------------------
-- Table structure for `t_firmware`
-- ----------------------------
DROP TABLE IF EXISTS `t_firmware`;
CREATE TABLE `t_firmware` (
  `firmware_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增编号',
  `firmware_name_id` varchar(60) DEFAULT NULL COMMENT '固件名称ID',
  `model` varchar(20) DEFAULT NULL COMMENT '设备型号',
  `firmware_VERSION` varchar(20) DEFAULT NULL COMMENT '固件版本',
  `firmware_DESC` varchar(50) DEFAULT NULL COMMENT '固件描述',
  `firmware_path` varchar(200) DEFAULT NULL COMMENT '固件文件保存路径',
  `user_id` int(11) DEFAULT NULL COMMENT '用户ID',
  `addTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '上传时间',
  `status` tinyint(4) DEFAULT NULL COMMENT '固件状态 1:正常 0:失效',
  `firmware_md5` varchar(100) DEFAULT NULL COMMENT 'MD5值',
  PRIMARY KEY (`firmware_id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8 COMMENT='固件的信息';

-- ----------------------------
-- Records of t_firmware
-- ----------------------------
INSERT INTO `t_firmware` VALUES ('17', '3', '001', 'v1.0.9', '', 'http://127.0.0.1/file/firmware/oA2qXnuf_1533272332941.sh', '1', '2018-08-03 12:58:53', '1', '64cbb70a797211e02529800c51316364');
INSERT INTO `t_firmware` VALUES ('18', '3', '001', 'v0.0.1', '固件描述；两万多', 'http://192.168.11.11/file/firmware/W1D9BCBC_1533285524823.bin', '1', '2018-08-03 16:38:45', '1', 'ef6336a39617af2c5f747ae519ad63b3');

-- ----------------------------
-- Table structure for `t_firmwaretask`
-- ----------------------------
DROP TABLE IF EXISTS `t_firmwaretask`;
CREATE TABLE `t_firmwaretask` (
  `firmwaretask_id` int(20) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `task_name` char(40) DEFAULT NULL COMMENT '任务名称',
  `task_desc` char(200) DEFAULT NULL COMMENT '任务描述',
  `add_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '任务新增时间',
  `user_id` int(11) DEFAULT NULL COMMENT '用户ID',
  `dev_total` int(10) DEFAULT NULL COMMENT '设备总数',
  `completed_num` int(10) DEFAULT NULL COMMENT '设备已完成数',
  `firmware_name_id` varchar(60) DEFAULT NULL COMMENT '插件名称ID',
  `firmware_VERSION` varchar(60) DEFAULT NULL COMMENT '插件版本',
  `status` int(10) DEFAULT NULL COMMENT '任务状态，0--已发起，1--已完成',
  PRIMARY KEY (`firmwaretask_id`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8 COMMENT='固件任务';

-- ----------------------------
-- Records of t_firmwaretask
-- ----------------------------
INSERT INTO `t_firmwaretask` VALUES ('21', '固件任务01', '固件任务描述jkljw', '2018-08-03 16:13:56', '1', '3', '0', '3', 'v1.0.9', '0');
INSERT INTO `t_firmwaretask` VALUES ('22', '固件任务02', '描述wda', '2018-08-03 16:39:59', '1', '1', '1', '3', 'v0.0.1', '1');
INSERT INTO `t_firmwaretask` VALUES ('23', '固件任务04', '', '2018-08-04 14:24:04', '1', '3', '1', '3', 'v0.0.1', '0');

-- ----------------------------
-- Table structure for `t_firmwaretask_task`
-- ----------------------------
DROP TABLE IF EXISTS `t_firmwaretask_task`;
CREATE TABLE `t_firmwaretask_task` (
  `firmwaretask_id` int(20) NOT NULL COMMENT '固件任务ID',
  `task_id` varchar(40) NOT NULL COMMENT '设备任务ID'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_firmwaretask_task
-- ----------------------------
INSERT INTO `t_firmwaretask_task` VALUES ('21', '20180803041255a00000000000024504');
INSERT INTO `t_firmwaretask_task` VALUES ('21', '20180803041255a00000000000036995');
INSERT INTO `t_firmwaretask_task` VALUES ('21', '20180803041255a1c8eea63486b84039');
INSERT INTO `t_firmwaretask_task` VALUES ('22', '20180803043918a1c8eea63486b88290');
INSERT INTO `t_firmwaretask_task` VALUES ('23', '20180804103918a00000000000025504');
INSERT INTO `t_firmwaretask_task` VALUES ('23', '20180804103918a00000000000037163');
INSERT INTO `t_firmwaretask_task` VALUES ('23', '20180804103918a1c8eea63486b89908');

-- ----------------------------
-- Table structure for `t_ipk`
-- ----------------------------
DROP TABLE IF EXISTS `t_ipk`;
CREATE TABLE `t_ipk` (
  `ipk_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增编号',
  `ipk_name_id` varchar(60) DEFAULT NULL COMMENT '插件名称ID',
  `model` varchar(20) DEFAULT NULL COMMENT '设备型号',
  `ipk_VERSION` varchar(20) DEFAULT NULL COMMENT '插件版本',
  `ipk_DESC` varchar(50) DEFAULT NULL COMMENT '插件描述',
  `ipk_path` varchar(200) DEFAULT NULL COMMENT '插件文件保存路径',
  `user_id` int(11) DEFAULT NULL COMMENT '用户ID',
  `addTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '上传时间',
  `status` tinyint(4) DEFAULT NULL COMMENT '插件状态 1:正常 0:失效',
  `ipk_md5` varchar(100) DEFAULT NULL COMMENT 'MD5值',
  PRIMARY KEY (`ipk_id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8 COMMENT='插件的信息';

-- ----------------------------
-- Records of t_ipk
-- ----------------------------
INSERT INTO `t_ipk` VALUES ('9', '1', '001', 'v1.0.5', null, 'http://192.168.11.102:8080/device-backend/file/ipk/ARWzWT5j_1532164984761.ipk', '12', '2018-07-21 17:23:05', '1', '7a9ff977791f95493b9fd7da4f0639b4');
INSERT INTO `t_ipk` VALUES ('10', '1', '001', 'v1.0.6', null, 'http://192.168.11.102:8080/device-backend/file/ipk/CpFZm7s8_1532749071760.ipk', '3', '2018-07-28 11:37:52', '1', '7a9ff977791f95493b9fd7da4f0639b4');
INSERT INTO `t_ipk` VALUES ('11', '1', '001', 'v1.0.7', '', 'http://192.168.11.11/file/ipk/JOx2NiT5_1533267310985.ipk', '1', '2018-08-03 11:35:11', '1', '7a9ff977791f95493b9fd7da4f0639b4');
INSERT INTO `t_ipk` VALUES ('12', '1', '001', 'v1.0.8', '', 'http://127.0.0.1/file/ipk/uWfttRif_1533272036744.ipk', '1', '2018-08-03 12:53:57', '1', '7a9ff977791f95493b9fd7da4f0639b4');
INSERT INTO `t_ipk` VALUES ('13', '1', '001', 'v1.1.0', '', 'http://192.168.11.11/file/ipk/nRqUBsuW_1533275034858.ipk', '1', '2018-08-03 13:43:55', '1', '7a9ff977791f95493b9fd7da4f0639b4');

-- ----------------------------
-- Table structure for `t_ipk_name`
-- ----------------------------
DROP TABLE IF EXISTS `t_ipk_name`;
CREATE TABLE `t_ipk_name` (
  `ipk_name_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增编号',
  `ipk_name` varchar(20) DEFAULT NULL COMMENT '插件名称',
  `ipk_nickname` varchar(20) DEFAULT NULL COMMENT '插件中文名称',
  `type` tinyint(4) DEFAULT NULL COMMENT '类型：0--插件 1--固件',
  PRIMARY KEY (`ipk_name_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_ipk_name
-- ----------------------------
INSERT INTO `t_ipk_name` VALUES ('1', 'wifidog', '无线模块插件', '0');
INSERT INTO `t_ipk_name` VALUES ('2', 'bluetooth', '蓝牙模块插件', '0');
INSERT INTO `t_ipk_name` VALUES ('3', 'CS15D', '矿机固件', '1');

-- ----------------------------
-- Table structure for `t_ipktask`
-- ----------------------------
DROP TABLE IF EXISTS `t_ipktask`;
CREATE TABLE `t_ipktask` (
  `ipktask_id` int(20) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `task_name` char(40) DEFAULT NULL COMMENT '任务名称',
  `task_desc` char(200) DEFAULT NULL COMMENT '任务描述',
  `start_type` tinyint(4) DEFAULT NULL COMMENT '启动类型0--立即启动 1--定时启动',
  `start_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '启动时间',
  `operate_type` tinyint(4) DEFAULT NULL COMMENT '操作类型，0--安装插件 1--卸载插件',
  `append` varchar(70) DEFAULT NULL COMMENT '升级方式',
  `add_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '任务新增时间',
  `user_id` int(11) DEFAULT NULL COMMENT '用户ID',
  `dev_total` int(10) DEFAULT NULL COMMENT '设备总数',
  `completed_num` int(10) DEFAULT NULL COMMENT '设备已完成数',
  `rate` int(10) DEFAULT NULL COMMENT '任务进度 ？%（0--100）',
  `ipk_name_id` varchar(60) DEFAULT NULL COMMENT '插件名称ID',
  `ipk_VERSION` varchar(60) DEFAULT NULL COMMENT '插件版本',
  `status` int(10) DEFAULT NULL COMMENT '任务状态，0--已发起，1--已完成',
  PRIMARY KEY (`ipktask_id`)
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8 COMMENT='插件任务（安装/卸载）';

-- ----------------------------
-- Records of t_ipktask
-- ----------------------------
INSERT INTO `t_ipktask` VALUES ('28', '升级插件01', '升级插件描述hlawd', null, '2018-08-03 16:12:26', '0', '--force-depends --force-reinstall', '2018-08-03 16:11:26', '1', '3', '0', null, '1', 'v1.1.0', '0');
INSERT INTO `t_ipktask` VALUES ('29', '插件任务02', '', null, '2018-08-03 16:32:29', '0', '--force-depends --force-reinstall', '2018-08-03 16:32:09', '1', '3', '1', null, '1', 'v1.1.0', '0');
INSERT INTO `t_ipktask` VALUES ('30', '卸载插件', '', null, '2018-08-03 16:33:18', '1', null, '2018-08-03 16:33:18', '1', '1', '1', null, '1', 'v1.1.0', '1');
INSERT INTO `t_ipktask` VALUES ('31', '插件任务07', '', null, '2018-08-04 14:23:47', '0', '--force-depends --force-reinstall --force-overwrite\n', '2018-08-04 10:38:03', '1', '1', '1', null, '1', 'v1.1.0', '1');
INSERT INTO `t_ipktask` VALUES ('32', '卸载插件任务02', '', null, '2018-08-06 13:51:21', '1', null, '2018-08-06 13:50:47', '1', '3', '1', null, '1', 'v1.1.0', '0');
INSERT INTO `t_ipktask` VALUES ('33', '安装插件001', '', null, '2018-08-07 13:52:33', '0', '--force-depends --force-reinstall', '2018-08-07 13:52:22', '1', '1', '1', null, '1', 'v1.1.0', '1');
INSERT INTO `t_ipktask` VALUES ('34', '卸载插件001', '', null, '2018-08-07 13:53:14', '1', null, '2018-08-07 13:53:02', '1', '1', '1', null, '1', null, '1');
INSERT INTO `t_ipktask` VALUES ('35', '升级插件002', '', null, '2018-08-07 13:54:21', '0', '--force-depends --force-reinstall', '2018-08-07 13:54:12', '1', '3', '1', null, '1', 'v1.1.0', '0');

-- ----------------------------
-- Table structure for `t_ipktask_task`
-- ----------------------------
DROP TABLE IF EXISTS `t_ipktask_task`;
CREATE TABLE `t_ipktask_task` (
  `ipktask_id` int(20) NOT NULL COMMENT '插件任务ID',
  `task_id` varchar(40) NOT NULL COMMENT '设备任务ID'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_ipktask_task
-- ----------------------------
INSERT INTO `t_ipktask_task` VALUES ('28', '20180803041125a00000000000025158');
INSERT INTO `t_ipktask_task` VALUES ('28', '20180803041125a00000000000034349');
INSERT INTO `t_ipktask_task` VALUES ('28', '20180803041125a1c8eea63486b89223');
INSERT INTO `t_ipktask_task` VALUES ('29', '20180803043208a00000000000022917');
INSERT INTO `t_ipktask_task` VALUES ('29', '20180803043208a00000000000036214');
INSERT INTO `t_ipktask_task` VALUES ('29', '20180803043208a1c8eea63486b88240');
INSERT INTO `t_ipktask_task` VALUES ('30', '20180803043318a1c8eea63486b85208');
INSERT INTO `t_ipktask_task` VALUES ('31', '20180804103803a1c8eea63486b84994');
INSERT INTO `t_ipktask_task` VALUES ('32', '20180806015047a00000000000027614');
INSERT INTO `t_ipktask_task` VALUES ('32', '20180806015047a00000000000036596');
INSERT INTO `t_ipktask_task` VALUES ('32', '20180806015047a1c8eea63486b88725');
INSERT INTO `t_ipktask_task` VALUES ('33', '20180807015223a1c8eea63486b85453');
INSERT INTO `t_ipktask_task` VALUES ('34', '20180807015302a1c8eea63486b82797');
INSERT INTO `t_ipktask_task` VALUES ('35', '20180807015411a00000000000024890');
INSERT INTO `t_ipktask_task` VALUES ('35', '20180807015411a00000000000038087');
INSERT INTO `t_ipktask_task` VALUES ('35', '20180807015411a1c8eea63486b87940');

-- ----------------------------
-- Table structure for `t_login_log`
-- ----------------------------
DROP TABLE IF EXISTS `t_login_log`;
CREATE TABLE `t_login_log` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `login_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '登录时间',
  `nick_name` varchar(20) DEFAULT NULL COMMENT '用户昵称',
  `user_name` varchar(20) DEFAULT NULL COMMENT '用户账号',
  `ip` varchar(20) DEFAULT NULL COMMENT '登录ip地址',
  `login_out_time` timestamp NULL DEFAULT '0000-00-00 00:00:00' COMMENT '注销时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=543 DEFAULT CHARSET=utf8 COMMENT='记录用户登录的日志信息';

-- ----------------------------
-- Records of t_login_log
-- ----------------------------
INSERT INTO `t_login_log` VALUES ('146', '2018-07-12 18:10:37', null, 'sischain', null, '2018-07-12 18:18:51');
INSERT INTO `t_login_log` VALUES ('147', '2018-07-12 18:19:12', null, 'sischain', null, null);
INSERT INTO `t_login_log` VALUES ('148', '2018-07-12 18:45:47', null, 'sischain', null, null);
INSERT INTO `t_login_log` VALUES ('149', '2018-07-13 09:59:43', null, 'sischain', null, null);
INSERT INTO `t_login_log` VALUES ('150', '2018-07-13 11:13:43', null, 'sischain', null, null);
INSERT INTO `t_login_log` VALUES ('151', '2018-07-13 11:39:19', null, 'sischain', null, null);
INSERT INTO `t_login_log` VALUES ('152', '2018-07-13 13:52:08', null, 'sischain', null, null);
INSERT INTO `t_login_log` VALUES ('153', '2018-07-13 13:52:48', null, 'sischain', null, null);
INSERT INTO `t_login_log` VALUES ('154', '2018-07-13 14:06:45', null, 'sischain', null, null);
INSERT INTO `t_login_log` VALUES ('155', '2018-07-13 14:07:01', null, 'sischain', null, null);
INSERT INTO `t_login_log` VALUES ('156', '2018-07-13 14:26:27', null, 'sischain', null, null);
INSERT INTO `t_login_log` VALUES ('157', '2018-07-13 14:40:19', null, 'sischain', null, null);
INSERT INTO `t_login_log` VALUES ('158', '2018-07-13 14:51:43', null, 'sischain', null, null);
INSERT INTO `t_login_log` VALUES ('159', '2018-07-13 14:59:24', null, 'sischain', null, null);
INSERT INTO `t_login_log` VALUES ('160', '2018-07-13 15:02:58', null, 'sischain', null, null);
INSERT INTO `t_login_log` VALUES ('161', '2018-07-13 16:49:51', null, 'sischain', null, null);
INSERT INTO `t_login_log` VALUES ('162', '2018-07-13 16:57:49', null, 'sischain', null, '2018-07-13 16:57:51');
INSERT INTO `t_login_log` VALUES ('163', '2018-07-13 16:58:07', null, 'sischain', null, null);
INSERT INTO `t_login_log` VALUES ('164', '2018-07-13 17:28:32', null, 'sischain', null, '2018-07-13 17:29:36');
INSERT INTO `t_login_log` VALUES ('165', '2018-07-13 17:29:49', null, 'sischain', null, null);
INSERT INTO `t_login_log` VALUES ('166', '2018-07-13 17:45:55', null, 'sischain', null, '2018-07-13 17:59:16');
INSERT INTO `t_login_log` VALUES ('167', '2018-07-13 18:22:54', null, 'sischain', null, null);
INSERT INTO `t_login_log` VALUES ('168', '2018-07-13 18:33:56', null, 'sischain', null, null);
INSERT INTO `t_login_log` VALUES ('169', '2018-07-14 10:10:13', null, 'sischain', null, null);
INSERT INTO `t_login_log` VALUES ('170', '2018-07-14 10:55:23', null, 'sischain', null, null);
INSERT INTO `t_login_log` VALUES ('171', '2018-07-14 10:59:14', null, 'sischain', null, null);
INSERT INTO `t_login_log` VALUES ('172', '2018-07-14 14:17:36', null, 'sischain', null, null);
INSERT INTO `t_login_log` VALUES ('173', '2018-07-14 14:35:21', null, 'sischain', null, null);
INSERT INTO `t_login_log` VALUES ('174', '2018-07-14 15:17:37', null, 'sischain', null, null);
INSERT INTO `t_login_log` VALUES ('175', '2018-07-14 16:07:54', null, 'sischain', null, null);
INSERT INTO `t_login_log` VALUES ('176', '2018-07-14 16:08:27', null, 'sischain', null, null);
INSERT INTO `t_login_log` VALUES ('177', '2018-07-14 16:09:11', null, 'sischain', null, null);
INSERT INTO `t_login_log` VALUES ('178', '2018-07-14 16:09:33', null, 'sischain', null, null);
INSERT INTO `t_login_log` VALUES ('179', '2018-07-14 17:44:29', null, 'sischain', null, null);
INSERT INTO `t_login_log` VALUES ('180', '2018-07-14 17:46:22', null, 'sischain', null, null);
INSERT INTO `t_login_log` VALUES ('181', '2018-07-14 17:58:14', null, 'sischain', null, null);
INSERT INTO `t_login_log` VALUES ('182', '2018-07-14 17:58:58', null, 'sischain', null, null);
INSERT INTO `t_login_log` VALUES ('183', '2018-07-14 18:10:12', null, 'sischain', null, null);
INSERT INTO `t_login_log` VALUES ('184', '2018-07-16 10:41:41', null, 'sischain', null, null);
INSERT INTO `t_login_log` VALUES ('185', '2018-07-16 10:43:25', null, 'sischain', null, null);
INSERT INTO `t_login_log` VALUES ('186', '2018-07-16 14:04:06', null, 'sischain', null, null);
INSERT INTO `t_login_log` VALUES ('187', '2018-07-16 14:15:07', null, 'sischain', null, null);
INSERT INTO `t_login_log` VALUES ('188', '2018-07-16 14:36:21', null, 'sischain', null, null);
INSERT INTO `t_login_log` VALUES ('189', '2018-07-16 14:39:50', null, 'sischain', null, null);
INSERT INTO `t_login_log` VALUES ('190', '2018-07-16 15:35:06', null, 'sischain', null, null);
INSERT INTO `t_login_log` VALUES ('191', '2018-07-16 16:22:20', null, 'sischain', null, null);
INSERT INTO `t_login_log` VALUES ('192', '2018-07-16 16:41:47', null, 'sischain', null, null);
INSERT INTO `t_login_log` VALUES ('193', '2018-07-17 10:49:23', null, 'lan', null, null);
INSERT INTO `t_login_log` VALUES ('194', '2018-07-17 11:17:55', null, 'lan', null, '2018-07-17 11:19:35');
INSERT INTO `t_login_log` VALUES ('195', '2018-07-17 11:19:36', null, 'lan', null, '2018-07-17 11:20:14');
INSERT INTO `t_login_log` VALUES ('196', '2018-07-17 11:20:16', null, 'lan', null, null);
INSERT INTO `t_login_log` VALUES ('197', '2018-07-17 11:24:00', null, 'admin', null, null);
INSERT INTO `t_login_log` VALUES ('198', '2018-07-17 11:24:51', null, 'lan', null, null);
INSERT INTO `t_login_log` VALUES ('199', '2018-07-17 11:27:15', null, 'lan', null, null);
INSERT INTO `t_login_log` VALUES ('200', '2018-07-17 11:30:03', null, 'lan', null, null);
INSERT INTO `t_login_log` VALUES ('201', '2018-07-17 11:33:24', null, 'lan', null, null);
INSERT INTO `t_login_log` VALUES ('202', '2018-07-17 11:41:34', null, 'lan', null, null);
INSERT INTO `t_login_log` VALUES ('203', '2018-07-17 11:43:49', null, 'lan', null, '2018-07-17 11:44:13');
INSERT INTO `t_login_log` VALUES ('204', '2018-07-17 11:44:17', null, 'lan', null, null);
INSERT INTO `t_login_log` VALUES ('205', '2018-07-17 11:44:20', null, 'lan', null, null);
INSERT INTO `t_login_log` VALUES ('206', '2018-07-17 11:51:15', null, 'lan', null, null);
INSERT INTO `t_login_log` VALUES ('207', '2018-07-17 14:19:45', null, 'admin', null, null);
INSERT INTO `t_login_log` VALUES ('208', '2018-07-17 17:56:36', null, 'lan', null, null);
INSERT INTO `t_login_log` VALUES ('209', '2018-07-17 18:42:45', null, 'lan', null, null);
INSERT INTO `t_login_log` VALUES ('210', '2018-07-18 11:05:58', null, 'admin', null, null);
INSERT INTO `t_login_log` VALUES ('211', '2018-07-18 15:11:46', null, 'admin', null, null);
INSERT INTO `t_login_log` VALUES ('212', '2018-07-18 16:44:41', null, 'admin', null, null);
INSERT INTO `t_login_log` VALUES ('213', '2018-07-18 16:46:46', null, 'admin', null, null);
INSERT INTO `t_login_log` VALUES ('214', '2018-07-18 17:02:13', null, 'admin', null, null);
INSERT INTO `t_login_log` VALUES ('215', '2018-07-18 17:04:09', null, 'lan', null, null);
INSERT INTO `t_login_log` VALUES ('216', '2018-07-18 17:07:47', null, 'admin', null, null);
INSERT INTO `t_login_log` VALUES ('217', '2018-07-18 17:09:34', null, 'lan', null, '2018-07-18 17:11:04');
INSERT INTO `t_login_log` VALUES ('218', '2018-07-18 17:11:05', null, 'lan', null, null);
INSERT INTO `t_login_log` VALUES ('219', '2018-07-18 17:12:28', null, 'admin', null, null);
INSERT INTO `t_login_log` VALUES ('220', '2018-07-18 17:13:32', null, 'lan', null, null);
INSERT INTO `t_login_log` VALUES ('221', '2018-07-18 17:21:19', null, 'lan', null, null);
INSERT INTO `t_login_log` VALUES ('222', '2018-07-18 17:22:21', null, 'admin', null, null);
INSERT INTO `t_login_log` VALUES ('223', '2018-07-18 17:26:27', null, 'admin', null, null);
INSERT INTO `t_login_log` VALUES ('224', '2018-07-18 17:33:58', null, 'admin', null, null);
INSERT INTO `t_login_log` VALUES ('225', '2018-07-18 17:46:03', null, 'admin', null, null);
INSERT INTO `t_login_log` VALUES ('226', '2018-07-18 17:49:33', null, 'admin', null, null);
INSERT INTO `t_login_log` VALUES ('227', '2018-07-18 18:18:52', null, 'lan', null, null);
INSERT INTO `t_login_log` VALUES ('228', '2018-07-18 18:26:05', null, 'lan', null, null);
INSERT INTO `t_login_log` VALUES ('229', '2018-07-18 18:51:47', null, 'lan', null, null);
INSERT INTO `t_login_log` VALUES ('230', '2018-07-18 18:56:09', null, 'admin', null, null);
INSERT INTO `t_login_log` VALUES ('231', '2018-07-18 18:57:52', null, 'admin', null, null);
INSERT INTO `t_login_log` VALUES ('232', '2018-07-19 10:04:36', null, 'admin', null, null);
INSERT INTO `t_login_log` VALUES ('233', '2018-07-19 10:40:13', null, 'admin', null, null);
INSERT INTO `t_login_log` VALUES ('234', '2018-07-19 10:50:39', null, 'admin', null, null);
INSERT INTO `t_login_log` VALUES ('235', '2018-07-19 11:17:58', null, 'admin', null, null);
INSERT INTO `t_login_log` VALUES ('236', '2018-07-19 11:30:46', null, 'admin', null, null);
INSERT INTO `t_login_log` VALUES ('237', '2018-07-19 13:33:21', null, 'admin', null, null);
INSERT INTO `t_login_log` VALUES ('238', '2018-07-19 13:49:24', null, 'lan', null, null);
INSERT INTO `t_login_log` VALUES ('239', '2018-07-19 14:00:19', null, 'admin', null, null);
INSERT INTO `t_login_log` VALUES ('240', '2018-07-19 14:00:04', null, 'lan', null, null);
INSERT INTO `t_login_log` VALUES ('241', '2018-07-19 14:02:02', null, 'admin', null, null);
INSERT INTO `t_login_log` VALUES ('242', '2018-07-19 14:01:20', null, 'lan', null, null);
INSERT INTO `t_login_log` VALUES ('243', '2018-07-19 14:04:39', null, 'admin', null, null);
INSERT INTO `t_login_log` VALUES ('244', '2018-07-19 14:06:41', null, 'admin', null, null);
INSERT INTO `t_login_log` VALUES ('245', '2018-07-19 14:09:07', null, 'admin', null, null);
INSERT INTO `t_login_log` VALUES ('246', '2018-07-19 14:15:35', null, 'admin', null, null);
INSERT INTO `t_login_log` VALUES ('247', '2018-07-19 14:26:43', null, 'lan', null, null);
INSERT INTO `t_login_log` VALUES ('248', '2018-07-19 14:51:24', null, 'lan', null, null);
INSERT INTO `t_login_log` VALUES ('249', '2018-07-19 15:16:46', null, 'lan', null, null);
INSERT INTO `t_login_log` VALUES ('250', '2018-07-19 15:26:22', null, 'lan', null, null);
INSERT INTO `t_login_log` VALUES ('251', '2018-07-19 15:28:53', null, 'lan', null, null);
INSERT INTO `t_login_log` VALUES ('252', '2018-07-19 15:38:17', null, 'lan', null, null);
INSERT INTO `t_login_log` VALUES ('253', '2018-07-19 15:43:12', null, 'lan', null, null);
INSERT INTO `t_login_log` VALUES ('254', '2018-07-19 15:45:52', null, 'lan', null, null);
INSERT INTO `t_login_log` VALUES ('255', '2018-07-19 15:49:04', null, 'lan', null, null);
INSERT INTO `t_login_log` VALUES ('256', '2018-07-19 15:53:08', null, 'lan', null, null);
INSERT INTO `t_login_log` VALUES ('257', '2018-07-19 15:55:18', null, 'lan', null, null);
INSERT INTO `t_login_log` VALUES ('258', '2018-07-19 16:06:22', null, 'lan', null, null);
INSERT INTO `t_login_log` VALUES ('259', '2018-07-19 16:08:09', null, 'lan', null, null);
INSERT INTO `t_login_log` VALUES ('260', '2018-07-19 16:15:45', null, 'lan', null, null);
INSERT INTO `t_login_log` VALUES ('261', '2018-07-19 17:23:10', null, 'lan', null, null);
INSERT INTO `t_login_log` VALUES ('262', '2018-07-19 17:27:23', null, 'lan', null, null);
INSERT INTO `t_login_log` VALUES ('263', '2018-07-19 17:31:12', null, 'lan', null, null);
INSERT INTO `t_login_log` VALUES ('264', '2018-07-19 17:34:35', null, 'lan', null, null);
INSERT INTO `t_login_log` VALUES ('265', '2018-07-19 17:36:52', null, 'lan', null, null);
INSERT INTO `t_login_log` VALUES ('266', '2018-07-19 17:44:15', null, 'lan', null, null);
INSERT INTO `t_login_log` VALUES ('267', '2018-07-19 18:08:48', null, 'lan', null, null);
INSERT INTO `t_login_log` VALUES ('268', '2018-07-19 18:13:49', null, 'lan', null, null);
INSERT INTO `t_login_log` VALUES ('269', '2018-07-19 18:34:01', null, 'lan', null, null);
INSERT INTO `t_login_log` VALUES ('270', '2018-07-19 18:36:00', null, 'lan', null, null);
INSERT INTO `t_login_log` VALUES ('271', '2018-07-19 18:39:49', null, 'gang', null, null);
INSERT INTO `t_login_log` VALUES ('272', '2018-07-19 18:40:40', null, 'gang', null, null);
INSERT INTO `t_login_log` VALUES ('273', '2018-07-19 18:45:17', null, 'lan', null, null);
INSERT INTO `t_login_log` VALUES ('274', '2018-07-19 18:48:28', 'lan', 'lan', null, null);
INSERT INTO `t_login_log` VALUES ('275', '2018-07-19 18:51:04', 'lan', 'lan', null, null);
INSERT INTO `t_login_log` VALUES ('276', '2018-07-19 18:59:10', 'lan', 'lan', null, null);
INSERT INTO `t_login_log` VALUES ('277', '2018-07-20 10:25:34', 'lan', 'lan', null, null);
INSERT INTO `t_login_log` VALUES ('278', '2018-07-20 10:29:31', null, 'admin', null, null);
INSERT INTO `t_login_log` VALUES ('279', '2018-07-20 10:28:56', '管理员', 'admin', null, null);
INSERT INTO `t_login_log` VALUES ('280', '2018-07-20 10:34:35', 'lan', 'lan', null, '2018-07-20 10:34:52');
INSERT INTO `t_login_log` VALUES ('281', '2018-07-20 10:35:01', 'lan', 'lan', null, '2018-07-20 10:35:16');
INSERT INTO `t_login_log` VALUES ('282', '2018-07-20 10:35:22', '昵称', 'lan2010', null, null);
INSERT INTO `t_login_log` VALUES ('283', '2018-07-20 10:38:11', '昵称', 'lan2010', null, '2018-07-20 10:38:22');
INSERT INTO `t_login_log` VALUES ('284', '2018-07-20 10:38:26', 'lan', 'lan', null, null);
INSERT INTO `t_login_log` VALUES ('285', '2018-07-20 11:00:39', 'lan', 'lan', null, null);
INSERT INTO `t_login_log` VALUES ('286', '2018-07-20 11:03:06', 'lan', 'lan', null, null);
INSERT INTO `t_login_log` VALUES ('287', '2018-07-20 11:16:51', 'lan', 'lan', null, null);
INSERT INTO `t_login_log` VALUES ('288', '2018-07-20 11:18:14', 'lan', 'lan', null, null);
INSERT INTO `t_login_log` VALUES ('289', '2018-07-20 11:19:38', 'lan', 'lan', null, null);
INSERT INTO `t_login_log` VALUES ('290', '2018-07-20 11:44:55', null, 'admin', null, null);
INSERT INTO `t_login_log` VALUES ('291', '2018-07-20 12:10:38', null, 'admin', null, null);
INSERT INTO `t_login_log` VALUES ('292', '2018-07-20 12:22:33', null, 'admin', null, null);
INSERT INTO `t_login_log` VALUES ('293', '2018-07-20 12:26:44', null, 'admin', null, null);
INSERT INTO `t_login_log` VALUES ('294', '2018-07-20 13:32:14', null, 'admin', null, null);
INSERT INTO `t_login_log` VALUES ('295', '2018-07-20 13:35:08', null, 'admin', null, null);
INSERT INTO `t_login_log` VALUES ('296', '2018-07-20 13:40:26', 'lan', 'lan', null, null);
INSERT INTO `t_login_log` VALUES ('297', '2018-07-20 13:41:23', null, 'admin', null, null);
INSERT INTO `t_login_log` VALUES ('298', '2018-07-20 14:23:13', null, 'admin', null, null);
INSERT INTO `t_login_log` VALUES ('299', '2018-07-20 14:27:44', null, 'admin', null, null);
INSERT INTO `t_login_log` VALUES ('300', '2018-07-20 14:37:07', null, 'admin', null, null);
INSERT INTO `t_login_log` VALUES ('301', '2018-07-20 14:58:25', null, 'admin', null, null);
INSERT INTO `t_login_log` VALUES ('302', '2018-07-20 15:43:43', null, 'admin', null, null);
INSERT INTO `t_login_log` VALUES ('303', '2018-07-20 15:54:44', null, 'admin', null, null);
INSERT INTO `t_login_log` VALUES ('304', '2018-07-20 16:19:13', null, 'admin', null, null);
INSERT INTO `t_login_log` VALUES ('305', '2018-07-20 16:21:07', null, 'admin', null, null);
INSERT INTO `t_login_log` VALUES ('306', '2018-07-20 16:22:32', null, 'admin', null, null);
INSERT INTO `t_login_log` VALUES ('307', '2018-07-20 16:30:10', null, 'admin', null, null);
INSERT INTO `t_login_log` VALUES ('308', '2018-07-20 16:33:56', null, 'admin', null, null);
INSERT INTO `t_login_log` VALUES ('309', '2018-07-20 16:35:04', null, 'admin', null, null);
INSERT INTO `t_login_log` VALUES ('310', '2018-07-21 09:58:37', null, 'admin', null, null);
INSERT INTO `t_login_log` VALUES ('311', '2018-07-21 09:59:35', null, 'admin', null, null);
INSERT INTO `t_login_log` VALUES ('312', '2018-07-21 10:39:53', '管理员', 'admin', null, null);
INSERT INTO `t_login_log` VALUES ('313', '2018-07-21 10:57:53', '管理员', 'admin', null, null);
INSERT INTO `t_login_log` VALUES ('314', '2018-07-21 11:01:19', '管理员', 'admin', null, null);
INSERT INTO `t_login_log` VALUES ('319', '2018-07-21 14:15:23', '管理员', 'admin', null, '2018-07-21 14:16:26');
INSERT INTO `t_login_log` VALUES ('321', '2018-07-21 14:20:28', '管理员', 'admin', null, null);
INSERT INTO `t_login_log` VALUES ('322', '2018-07-21 14:22:36', '管理员', 'admin', null, '2018-07-21 14:23:13');
INSERT INTO `t_login_log` VALUES ('323', '2018-07-21 14:25:40', '管理员', 'admin', null, null);
INSERT INTO `t_login_log` VALUES ('324', '2018-07-21 14:27:12', '管理员', 'admin', null, null);
INSERT INTO `t_login_log` VALUES ('325', '2018-07-21 14:28:20', '管理员', 'admin', null, null);
INSERT INTO `t_login_log` VALUES ('326', '2018-07-21 14:59:58', '管理员', 'admin', null, null);
INSERT INTO `t_login_log` VALUES ('327', '2018-07-21 15:15:04', '管理员', 'admin', null, null);
INSERT INTO `t_login_log` VALUES ('328', '2018-07-21 15:39:01', '管理员', 'admin', null, null);
INSERT INTO `t_login_log` VALUES ('329', '2018-07-21 15:48:21', 'lan', 'lan', null, null);
INSERT INTO `t_login_log` VALUES ('330', '2018-07-21 15:59:27', 'lan', 'lan', null, null);
INSERT INTO `t_login_log` VALUES ('331', '2018-07-21 16:00:12', 'lan', 'lan', null, null);
INSERT INTO `t_login_log` VALUES ('332', '2018-07-21 16:07:03', '管理员', 'admin', null, null);
INSERT INTO `t_login_log` VALUES ('333', '2018-07-21 16:06:17', '管理员', 'admin', null, null);
INSERT INTO `t_login_log` VALUES ('334', '2018-07-21 16:25:40', 'lan', 'lan', null, null);
INSERT INTO `t_login_log` VALUES ('335', '2018-07-21 16:37:01', '管理员', 'admin', null, null);
INSERT INTO `t_login_log` VALUES ('336', '2018-07-21 16:41:59', 'lan', 'lan', null, null);
INSERT INTO `t_login_log` VALUES ('337', '2018-07-21 16:45:25', 'lan', 'lan', null, null);
INSERT INTO `t_login_log` VALUES ('338', '2018-07-21 16:54:32', '管理员', 'admin', null, null);
INSERT INTO `t_login_log` VALUES ('339', '2018-07-21 16:53:37', '管理员', 'admin', null, null);
INSERT INTO `t_login_log` VALUES ('340', '2018-07-21 16:55:35', '管理员', 'admin', null, null);
INSERT INTO `t_login_log` VALUES ('341', '2018-07-21 16:56:51', 'lan', 'lan', null, null);
INSERT INTO `t_login_log` VALUES ('342', '2018-07-21 16:57:54', '管理员', 'admin', null, null);
INSERT INTO `t_login_log` VALUES ('343', '2018-07-21 16:58:20', 'lan', 'lan', null, null);
INSERT INTO `t_login_log` VALUES ('344', '2018-07-21 17:01:08', 'lan', 'lan', null, null);
INSERT INTO `t_login_log` VALUES ('345', '2018-07-21 17:02:10', 'lan', 'lan', null, null);
INSERT INTO `t_login_log` VALUES ('346', '2018-07-21 17:06:05', 'lan', 'lan', null, null);
INSERT INTO `t_login_log` VALUES ('347', '2018-07-21 17:09:39', 'lan', 'lan', null, null);
INSERT INTO `t_login_log` VALUES ('348', '2018-07-21 17:13:47', 'lan', 'lan', null, null);
INSERT INTO `t_login_log` VALUES ('349', '2018-07-21 17:16:46', 'lan', 'lan', null, null);
INSERT INTO `t_login_log` VALUES ('350', '2018-07-21 17:20:39', 'lan', 'lan', null, null);
INSERT INTO `t_login_log` VALUES ('351', '2018-07-21 17:21:22', 'lan', 'lan', null, null);
INSERT INTO `t_login_log` VALUES ('352', '2018-07-21 17:24:55', 'lan', 'lan', null, null);
INSERT INTO `t_login_log` VALUES ('353', '2018-07-21 17:28:38', '管理员', 'admin', null, null);
INSERT INTO `t_login_log` VALUES ('354', '2018-07-21 17:31:42', '管理员', 'admin', null, null);
INSERT INTO `t_login_log` VALUES ('355', '2018-07-23 10:28:44', 'lan', 'lan', null, null);
INSERT INTO `t_login_log` VALUES ('356', '2018-07-23 10:36:07', 'lan', 'lan', null, null);
INSERT INTO `t_login_log` VALUES ('357', '2018-07-23 10:39:23', 'lan', 'lan', null, null);
INSERT INTO `t_login_log` VALUES ('358', '2018-07-23 10:54:20', 'lan', 'lan', null, null);
INSERT INTO `t_login_log` VALUES ('359', '2018-07-23 10:56:33', 'lan', 'lan', null, null);
INSERT INTO `t_login_log` VALUES ('360', '2018-07-23 11:00:24', 'lan', 'lan', null, null);
INSERT INTO `t_login_log` VALUES ('361', '2018-07-23 11:05:08', 'lan', 'lan', null, null);
INSERT INTO `t_login_log` VALUES ('362', '2018-07-23 14:30:54', '管理员', 'admin', null, null);
INSERT INTO `t_login_log` VALUES ('363', '2018-07-23 14:36:01', null, 'admin', null, null);
INSERT INTO `t_login_log` VALUES ('364', '2018-07-23 14:43:46', '管理员', 'admin', null, null);
INSERT INTO `t_login_log` VALUES ('365', '2018-07-23 14:54:38', '管理员', 'admin', null, null);
INSERT INTO `t_login_log` VALUES ('366', '2018-07-23 14:57:16', '管理员', 'admin', null, null);
INSERT INTO `t_login_log` VALUES ('367', '2018-07-23 14:59:06', '管理员', 'admin', null, null);
INSERT INTO `t_login_log` VALUES ('368', '2018-07-23 15:45:35', '管理员', 'admin', null, null);
INSERT INTO `t_login_log` VALUES ('369', '2018-07-23 15:47:05', '管理员', 'admin', null, null);
INSERT INTO `t_login_log` VALUES ('370', '2018-07-23 15:49:05', '管理员', 'admin', null, null);
INSERT INTO `t_login_log` VALUES ('371', '2018-07-23 15:55:54', '管理员', 'admin', null, null);
INSERT INTO `t_login_log` VALUES ('372', '2018-07-23 16:17:02', '管理员', 'admin', null, null);
INSERT INTO `t_login_log` VALUES ('373', '2018-07-23 16:33:23', '管理员', 'admin', null, null);
INSERT INTO `t_login_log` VALUES ('374', '2018-07-23 16:34:02', '管理员', 'admin', null, null);
INSERT INTO `t_login_log` VALUES ('375', '2018-07-23 18:20:39', '管理员', 'admin', null, null);
INSERT INTO `t_login_log` VALUES ('376', '2018-07-23 18:22:01', '管理员', 'admin', null, null);
INSERT INTO `t_login_log` VALUES ('377', '2018-07-23 18:37:35', '管理员', 'admin', null, null);
INSERT INTO `t_login_log` VALUES ('378', '2018-07-24 10:17:15', '管理员', 'admin', null, null);
INSERT INTO `t_login_log` VALUES ('379', '2018-07-24 14:06:47', 'lan', 'lan', null, null);
INSERT INTO `t_login_log` VALUES ('380', '2018-07-24 18:33:16', 'lan', 'lan', null, null);
INSERT INTO `t_login_log` VALUES ('381', '2018-07-25 14:57:45', 'lan', 'lan', null, null);
INSERT INTO `t_login_log` VALUES ('382', '2018-07-26 15:27:11', 'lan', 'lan', null, null);
INSERT INTO `t_login_log` VALUES ('383', '2018-07-26 17:42:15', 'lan', 'lan', null, null);
INSERT INTO `t_login_log` VALUES ('384', '2018-07-26 17:55:27', 'lan', 'lan', null, null);
INSERT INTO `t_login_log` VALUES ('385', '2018-07-27 18:13:34', 'lan', 'lan', null, null);
INSERT INTO `t_login_log` VALUES ('386', '2018-07-27 18:18:09', 'lan', 'lan', null, null);
INSERT INTO `t_login_log` VALUES ('387', '2018-07-27 18:21:02', 'lan', 'lan', null, null);
INSERT INTO `t_login_log` VALUES ('388', '2018-07-27 18:52:05', '管理员', 'admin', null, null);
INSERT INTO `t_login_log` VALUES ('389', '2018-07-27 18:58:10', 'lan', 'lan', null, null);
INSERT INTO `t_login_log` VALUES ('390', '2018-07-27 19:00:51', 'lan', 'lan', null, null);
INSERT INTO `t_login_log` VALUES ('391', '2018-07-27 19:08:09', 'lan', 'lan', null, null);
INSERT INTO `t_login_log` VALUES ('392', '2018-07-28 10:20:57', 'lan', 'lan', null, null);
INSERT INTO `t_login_log` VALUES ('393', '2018-07-28 10:23:39', 'lan', 'lan', null, null);
INSERT INTO `t_login_log` VALUES ('394', '2018-07-28 10:25:14', '管理员', 'admin', null, null);
INSERT INTO `t_login_log` VALUES ('395', '2018-07-28 10:38:22', 'lan', 'lan', null, null);
INSERT INTO `t_login_log` VALUES ('396', '2018-07-28 11:08:44', 'lan', 'lan', null, null);
INSERT INTO `t_login_log` VALUES ('397', '2018-07-28 11:26:32', 'lan', 'lan', null, null);
INSERT INTO `t_login_log` VALUES ('398', '2018-07-28 12:50:21', '管理员', 'admin', null, null);
INSERT INTO `t_login_log` VALUES ('399', '2018-07-28 13:32:48', '管理员', 'admin', null, null);
INSERT INTO `t_login_log` VALUES ('400', '2018-07-28 13:38:37', 'lan', 'lan', null, null);
INSERT INTO `t_login_log` VALUES ('401', '2018-07-28 13:41:11', 'lan', 'lan', null, null);
INSERT INTO `t_login_log` VALUES ('402', '2018-07-28 13:44:15', 'lan', 'lan', null, null);
INSERT INTO `t_login_log` VALUES ('403', '2018-07-28 15:05:54', 'lan', 'lan', null, null);
INSERT INTO `t_login_log` VALUES ('404', '2018-07-28 15:21:06', 'lan', 'lan', null, null);
INSERT INTO `t_login_log` VALUES ('405', '2018-07-28 15:46:59', 'lan', 'lan', null, null);
INSERT INTO `t_login_log` VALUES ('406', '2018-07-28 15:55:10', '昵称', 'lan2010', null, null);
INSERT INTO `t_login_log` VALUES ('407', '2018-07-28 16:05:43', '昵称', 'lan2010', null, null);
INSERT INTO `t_login_log` VALUES ('408', '2018-07-28 16:08:25', '昵称', 'lan2010', null, null);
INSERT INTO `t_login_log` VALUES ('409', '2018-07-28 16:32:01', 'lan', 'lan', null, null);
INSERT INTO `t_login_log` VALUES ('410', '2018-07-30 10:44:47', 'lan', 'lan', null, null);
INSERT INTO `t_login_log` VALUES ('411', '2018-07-30 13:52:21', 'lan', 'lan', null, null);
INSERT INTO `t_login_log` VALUES ('412', '2018-07-30 14:24:00', 'lan', 'lan', null, null);
INSERT INTO `t_login_log` VALUES ('413', '2018-07-30 18:29:52', '昵称', 'lan2010', null, null);
INSERT INTO `t_login_log` VALUES ('414', '2018-07-30 18:41:33', '昵称', 'lan2010', null, null);
INSERT INTO `t_login_log` VALUES ('415', '2018-07-30 18:43:59', '昵称', 'lan2010', null, null);
INSERT INTO `t_login_log` VALUES ('416', '2018-07-31 10:11:55', '昵称', 'lan2010', null, null);
INSERT INTO `t_login_log` VALUES ('417', '2018-07-31 10:41:29', '管理员', 'admin', null, '2018-07-31 10:46:59');
INSERT INTO `t_login_log` VALUES ('418', '2018-07-31 10:47:04', 'lan', 'lan', null, null);
INSERT INTO `t_login_log` VALUES ('419', '2018-07-31 11:19:11', 'lan', 'lan', null, null);
INSERT INTO `t_login_log` VALUES ('420', '2018-07-31 11:45:09', 'lan', 'lan', null, null);
INSERT INTO `t_login_log` VALUES ('421', '2018-07-31 12:15:13', 'lan', 'lan', null, null);
INSERT INTO `t_login_log` VALUES ('422', '2018-07-31 13:35:49', 'lan', 'lan', null, null);
INSERT INTO `t_login_log` VALUES ('423', '2018-07-31 14:15:08', 'lan', 'lan', null, null);
INSERT INTO `t_login_log` VALUES ('424', '2018-07-31 18:38:54', 'lan', 'lan', null, null);
INSERT INTO `t_login_log` VALUES ('425', '2018-08-01 10:42:25', '管理员', 'admin', null, null);
INSERT INTO `t_login_log` VALUES ('426', '2018-08-01 10:42:29', 'lan', 'lan', null, null);
INSERT INTO `t_login_log` VALUES ('427', '2018-08-01 11:18:02', 'lan', 'lan', null, null);
INSERT INTO `t_login_log` VALUES ('428', '2018-08-01 11:20:29', '管理员', 'admin', null, null);
INSERT INTO `t_login_log` VALUES ('429', '2018-08-01 11:51:22', 'lan', 'lan', null, null);
INSERT INTO `t_login_log` VALUES ('430', '2018-08-01 11:59:26', '管理员', 'admin', null, null);
INSERT INTO `t_login_log` VALUES ('431', '2018-08-01 12:01:43', 'lan', 'lan', null, null);
INSERT INTO `t_login_log` VALUES ('432', '2018-08-01 17:32:54', '管理员', 'admin', null, null);
INSERT INTO `t_login_log` VALUES ('433', '2018-08-01 17:46:33', 'lan', 'lan', null, null);
INSERT INTO `t_login_log` VALUES ('434', '2018-08-01 17:47:58', 'lan', 'lan', null, null);
INSERT INTO `t_login_log` VALUES ('435', '2018-08-01 17:50:36', 'lan', 'lan', null, null);
INSERT INTO `t_login_log` VALUES ('436', '2018-08-01 18:04:15', 'lan', 'lan', null, null);
INSERT INTO `t_login_log` VALUES ('437', '2018-08-01 18:05:17', 'lan', 'lan', null, null);
INSERT INTO `t_login_log` VALUES ('438', '2018-08-01 18:12:21', 'lan', 'lan', null, null);
INSERT INTO `t_login_log` VALUES ('439', '2018-08-01 18:14:53', '管理员', 'admin', null, null);
INSERT INTO `t_login_log` VALUES ('440', '2018-08-01 18:15:13', '管理员', 'admin', null, null);
INSERT INTO `t_login_log` VALUES ('441', '2018-08-01 18:20:50', 'lan', 'lan', null, null);
INSERT INTO `t_login_log` VALUES ('442', '2018-08-01 18:25:15', '管理员', 'admin', null, null);
INSERT INTO `t_login_log` VALUES ('443', '2018-08-01 18:25:52', '管理员', 'admin', null, null);
INSERT INTO `t_login_log` VALUES ('444', '2018-08-01 18:25:52', 'lan', 'lan', null, null);
INSERT INTO `t_login_log` VALUES ('445', '2018-08-01 18:28:21', 'lan', 'lan', null, null);
INSERT INTO `t_login_log` VALUES ('446', '2018-08-01 18:29:11', 'lan', 'lan', null, null);
INSERT INTO `t_login_log` VALUES ('447', '2018-08-01 18:31:52', '管理员', 'admin', null, null);
INSERT INTO `t_login_log` VALUES ('448', '2018-08-01 18:38:08', 'lan', 'lan', null, null);
INSERT INTO `t_login_log` VALUES ('449', '2018-08-01 18:38:33', '管理员', 'admin', null, null);
INSERT INTO `t_login_log` VALUES ('450', '2018-08-01 18:44:13', 'lan', 'lan', null, null);
INSERT INTO `t_login_log` VALUES ('451', '2018-08-01 18:46:21', 'lan', 'lan', null, null);
INSERT INTO `t_login_log` VALUES ('452', '2018-08-01 18:48:05', 'lan', 'lan', null, null);
INSERT INTO `t_login_log` VALUES ('453', '2018-08-01 18:57:32', 'lan', 'lan', null, null);
INSERT INTO `t_login_log` VALUES ('454', '2018-08-01 18:59:06', '管理员', 'admin', null, null);
INSERT INTO `t_login_log` VALUES ('455', '2018-08-01 19:00:07', 'lan', 'lan', null, null);
INSERT INTO `t_login_log` VALUES ('456', '2018-08-01 19:02:22', '管理员', 'admin', null, null);
INSERT INTO `t_login_log` VALUES ('457', '2018-08-01 19:09:01', 'lan', 'lan', null, null);
INSERT INTO `t_login_log` VALUES ('458', '2018-08-01 19:19:06', 'lan', 'lan', null, null);
INSERT INTO `t_login_log` VALUES ('459', '2018-08-01 19:23:47', 'lan', 'lan', null, null);
INSERT INTO `t_login_log` VALUES ('460', '2018-08-01 19:29:28', 'lan', 'lan', null, null);
INSERT INTO `t_login_log` VALUES ('461', '2018-08-01 19:35:36', 'lan', 'lan', null, null);
INSERT INTO `t_login_log` VALUES ('462', '2018-08-01 19:38:05', 'lan', 'lan', null, null);
INSERT INTO `t_login_log` VALUES ('463', '2018-08-01 19:41:22', 'lan', 'lan', null, null);
INSERT INTO `t_login_log` VALUES ('464', '2018-08-01 19:45:02', 'lan', 'lan', null, null);
INSERT INTO `t_login_log` VALUES ('465', '2018-08-01 19:50:48', 'lan', 'lan', null, null);
INSERT INTO `t_login_log` VALUES ('466', '2018-08-02 10:39:04', 'lan', 'lan', null, null);
INSERT INTO `t_login_log` VALUES ('467', '2018-08-02 10:42:08', 'lan', 'lan', null, null);
INSERT INTO `t_login_log` VALUES ('468', '2018-08-02 10:48:38', 'lan', 'lan', null, null);
INSERT INTO `t_login_log` VALUES ('469', '2018-08-02 10:57:21', 'lan', 'lan', null, null);
INSERT INTO `t_login_log` VALUES ('470', '2018-08-02 11:07:27', 'lan', 'lan', null, null);
INSERT INTO `t_login_log` VALUES ('471', '2018-08-02 11:12:27', 'lan', 'lan', null, null);
INSERT INTO `t_login_log` VALUES ('472', '2018-08-02 11:19:08', 'lan', 'lan', null, null);
INSERT INTO `t_login_log` VALUES ('473', '2018-08-02 11:20:49', 'lan', 'lan', null, null);
INSERT INTO `t_login_log` VALUES ('474', '2018-08-02 11:23:25', 'lan', 'lan', null, null);
INSERT INTO `t_login_log` VALUES ('475', '2018-08-02 11:38:35', 'lan', 'lan', null, null);
INSERT INTO `t_login_log` VALUES ('476', '2018-08-02 11:40:55', 'lan', 'lan', null, null);
INSERT INTO `t_login_log` VALUES ('477', '2018-08-02 11:52:23', 'lan', 'lan', null, null);
INSERT INTO `t_login_log` VALUES ('478', '2018-08-02 15:02:37', '管理员', 'admin', null, null);
INSERT INTO `t_login_log` VALUES ('479', '2018-08-02 15:13:12', '管理员', 'admin', null, null);
INSERT INTO `t_login_log` VALUES ('480', '2018-08-02 17:10:55', 'lan', 'lan', null, null);
INSERT INTO `t_login_log` VALUES ('481', '2018-08-02 17:13:36', 'lan', 'lan', null, null);
INSERT INTO `t_login_log` VALUES ('482', '2018-08-02 17:16:02', '管理员', 'admin', null, null);
INSERT INTO `t_login_log` VALUES ('483', '2018-08-02 17:32:10', '管理员', 'admin', null, null);
INSERT INTO `t_login_log` VALUES ('484', '2018-08-02 17:48:49', 'lan', 'lan', null, null);
INSERT INTO `t_login_log` VALUES ('485', '2018-08-02 17:52:50', 'lan', 'lan', null, null);
INSERT INTO `t_login_log` VALUES ('486', '2018-08-02 17:53:56', 'lan', 'lan', null, null);
INSERT INTO `t_login_log` VALUES ('487', '2018-08-02 17:56:09', 'lan', 'lan', null, null);
INSERT INTO `t_login_log` VALUES ('488', '2018-08-02 18:01:01', 'lan', 'lan', null, null);
INSERT INTO `t_login_log` VALUES ('489', '2018-08-02 18:06:28', 'lan', 'lan', null, null);
INSERT INTO `t_login_log` VALUES ('490', '2018-08-02 18:55:25', 'lan', 'lan', null, null);
INSERT INTO `t_login_log` VALUES ('491', '2018-08-02 18:57:39', 'lan', 'lan', null, null);
INSERT INTO `t_login_log` VALUES ('492', '2018-08-02 19:01:46', 'lan', 'lan', null, null);
INSERT INTO `t_login_log` VALUES ('493', '2018-08-02 19:05:42', 'lan', 'lan', null, null);
INSERT INTO `t_login_log` VALUES ('494', '2018-08-02 19:35:50', 'lan', 'lan', null, null);
INSERT INTO `t_login_log` VALUES ('495', '2018-08-03 10:15:04', '管理员', 'admin', null, null);
INSERT INTO `t_login_log` VALUES ('496', '2018-08-03 10:15:56', '管理员', 'admin', null, '2018-08-03 10:26:59');
INSERT INTO `t_login_log` VALUES ('497', '2018-08-03 10:27:19', '', 'Arvit', null, null);
INSERT INTO `t_login_log` VALUES ('498', '2018-08-03 10:37:54', '', 'Arvit', null, '2018-08-03 10:39:35');
INSERT INTO `t_login_log` VALUES ('499', '2018-08-03 10:39:38', '管理员', 'admin', null, null);
INSERT INTO `t_login_log` VALUES ('500', '2018-08-03 11:08:40', 'lan', 'lan', null, null);
INSERT INTO `t_login_log` VALUES ('501', '2018-08-03 11:12:44', '管理员', 'admin', null, null);
INSERT INTO `t_login_log` VALUES ('502', '2018-08-03 11:14:45', '管理员', 'admin', null, null);
INSERT INTO `t_login_log` VALUES ('503', '2018-08-03 11:17:16', '管理员', 'admin', null, null);
INSERT INTO `t_login_log` VALUES ('504', '2018-08-03 11:32:59', '管理员', 'admin', null, null);
INSERT INTO `t_login_log` VALUES ('505', '2018-08-03 12:49:35', '管理员', 'admin', null, null);
INSERT INTO `t_login_log` VALUES ('506', '2018-08-03 12:52:46', '管理员', 'admin', null, null);
INSERT INTO `t_login_log` VALUES ('507', '2018-08-03 13:41:04', '管理员', 'admin', null, null);
INSERT INTO `t_login_log` VALUES ('508', '2018-08-03 14:03:30', '管理员', 'admin', null, null);
INSERT INTO `t_login_log` VALUES ('509', '2018-08-03 14:08:13', '', 'Arvit', null, null);
INSERT INTO `t_login_log` VALUES ('510', '2018-08-03 14:17:54', '管理员', 'admin', null, '2018-08-03 14:18:19');
INSERT INTO `t_login_log` VALUES ('511', '2018-08-03 14:18:29', '管理员', 'admin', null, null);
INSERT INTO `t_login_log` VALUES ('512', '2018-08-03 14:37:13', '管理员', 'admin', null, null);
INSERT INTO `t_login_log` VALUES ('513', '2018-08-03 14:44:07', '管理员', 'admin', null, null);
INSERT INTO `t_login_log` VALUES ('514', '2018-08-03 15:52:53', '管理员', 'admin', null, '2018-08-03 15:55:43');
INSERT INTO `t_login_log` VALUES ('515', '2018-08-03 15:55:48', '管理员', 'admin', null, null);
INSERT INTO `t_login_log` VALUES ('516', '2018-08-03 15:55:52', '管理员', 'admin', null, null);
INSERT INTO `t_login_log` VALUES ('517', '2018-08-03 16:05:18', '管理员', 'admin', null, null);
INSERT INTO `t_login_log` VALUES ('518', '2018-08-03 16:08:20', '管理员', 'admin', null, null);
INSERT INTO `t_login_log` VALUES ('519', '2018-08-03 16:10:14', '管理员', 'admin', null, null);
INSERT INTO `t_login_log` VALUES ('520', '2018-08-03 16:18:06', '管理员', 'admin', null, null);
INSERT INTO `t_login_log` VALUES ('521', '2018-08-03 16:22:50', '', 'Arvit', null, null);
INSERT INTO `t_login_log` VALUES ('522', '2018-08-03 18:25:46', '', 'Arvit', null, null);
INSERT INTO `t_login_log` VALUES ('523', '2018-08-04 10:33:20', '管理员', 'admin', null, null);
INSERT INTO `t_login_log` VALUES ('524', '2018-08-04 14:20:35', '管理员', 'admin', null, null);
INSERT INTO `t_login_log` VALUES ('525', '2018-08-04 14:26:35', '管理员', 'admin', null, null);
INSERT INTO `t_login_log` VALUES ('526', '2018-08-04 14:28:35', '管理员', 'admin', null, null);
INSERT INTO `t_login_log` VALUES ('527', '2018-08-04 14:40:11', '管理员', 'admin', null, null);
INSERT INTO `t_login_log` VALUES ('528', '2018-08-04 14:58:43', '管理员', 'admin', null, null);
INSERT INTO `t_login_log` VALUES ('529', '2018-08-06 09:50:11', '', 'Arvit', null, null);
INSERT INTO `t_login_log` VALUES ('530', '2018-08-06 09:56:19', '管理员', 'admin', null, null);
INSERT INTO `t_login_log` VALUES ('531', '2018-08-06 10:42:27', '', 'Arvit', null, null);
INSERT INTO `t_login_log` VALUES ('532', '2018-08-06 13:50:05', '管理员', 'admin', null, null);
INSERT INTO `t_login_log` VALUES ('533', '2018-08-06 16:43:24', '管理员', 'admin', null, null);
INSERT INTO `t_login_log` VALUES ('534', '2018-08-07 11:34:27', '', 'Arvit', null, null);
INSERT INTO `t_login_log` VALUES ('535', '2018-08-07 11:58:55', '管理员', 'admin', null, null);
INSERT INTO `t_login_log` VALUES ('536', '2018-08-07 12:08:18', '管理员', 'admin', null, null);
INSERT INTO `t_login_log` VALUES ('537', '2018-08-07 13:32:31', '管理员', 'admin', null, null);
INSERT INTO `t_login_log` VALUES ('538', '2018-08-07 13:34:37', '管理员', 'admin', null, null);
INSERT INTO `t_login_log` VALUES ('539', '2018-08-07 13:37:12', '', 'Arvit', null, null);
INSERT INTO `t_login_log` VALUES ('540', '2018-08-07 13:50:02', '管理员', 'admin', null, null);
INSERT INTO `t_login_log` VALUES ('541', '2018-09-04 15:44:52', '管理员', 'admin', null, null);
INSERT INTO `t_login_log` VALUES ('542', '2018-09-04 17:15:44', '管理员', 'admin', null, null);

-- ----------------------------
-- Table structure for `t_menu`
-- ----------------------------
DROP TABLE IF EXISTS `t_menu`;
CREATE TABLE `t_menu` (
  `menu_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `icon` varchar(16) DEFAULT '' COMMENT '图表样式类型',
  `menu_path` varchar(64) DEFAULT '' COMMENT '菜单路径',
  `menu_name` varchar(16) DEFAULT '' COMMENT '菜单名称',
  `parent_id` int(11) DEFAULT '0' COMMENT '父级菜单ID',
  `level` tinyint(1) DEFAULT '0' COMMENT '菜单级别',
  PRIMARY KEY (`menu_id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_menu
-- ----------------------------
INSERT INTO `t_menu` VALUES ('1', 'weixin', '', '设备管理', '0', '1');
INSERT INTO `t_menu` VALUES ('2', 'weixin', 'device/list', '设备列表', '1', '2');
INSERT INTO `t_menu` VALUES ('3', 'weixin', '', '系统管理', '0', '1');
INSERT INTO `t_menu` VALUES ('4', 'weixin', 'user/list', '用户管理', '3', '2');
INSERT INTO `t_menu` VALUES ('9', 'weixin', 'login/list', '登录日志', '3', '2');
INSERT INTO `t_menu` VALUES ('10', '', 'operation/list', '操作日志', '3', '2');

-- ----------------------------
-- Table structure for `t_model`
-- ----------------------------
DROP TABLE IF EXISTS `t_model`;
CREATE TABLE `t_model` (
  `code` varchar(3) NOT NULL COMMENT '型号编码',
  `name` varchar(30) DEFAULT NULL COMMENT '型号名称',
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_model
-- ----------------------------
INSERT INTO `t_model` VALUES ('001', 'TZX-1');

-- ----------------------------
-- Table structure for `t_params_appoint`
-- ----------------------------
DROP TABLE IF EXISTS `t_params_appoint`;
CREATE TABLE `t_params_appoint` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `view_name` varchar(20) DEFAULT NULL COMMENT '参数中文释义，页面展示用名称',
  `dev_param` varchar(30) DEFAULT NULL COMMENT '对应设备参数名',
  `describe` varchar(50) DEFAULT NULL COMMENT '参数描述',
  `rw` varchar(2) DEFAULT 'r' COMMENT 'r：可读，rw：可读写',
  `input_type` varchar(20) DEFAULT NULL COMMENT '输入类型',
  `options` varchar(100) DEFAULT NULL COMMENT '输入类型的分类集',
  `group` varchar(20) DEFAULT NULL COMMENT '所属模块的分组',
  `required` tinyint(1) DEFAULT '0' COMMENT '是否必填',
  `rule` varchar(200) DEFAULT NULL COMMENT '验证规则，例如mac表示是一个mac地址类型的字符串，phone表示是一个手机类型',
  `reg_exp` varchar(200) DEFAULT NULL COMMENT '正则表达式',
  `reg_exp_tip` varchar(50) DEFAULT NULL COMMENT '正则验证提示，输入提示作用',
  `dev_alias` varchar(20) DEFAULT NULL COMMENT '设备参数名的别名',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COMMENT='设备参数约定规则';

-- ----------------------------
-- Records of t_params_appoint
-- ----------------------------
INSERT INTO `t_params_appoint` VALUES ('1', '探针开关', 'newprobe.probe.enable', '', 'rw', 'radio', '[{\"name\":\"开启\",\"value\":\"1\"},{\"name\":\"关闭\",\"value\":\"0\"}]', 'probe', '1', null, null, null, '');
INSERT INTO `t_params_appoint` VALUES ('2', '探针服务器', 'newprobe.probe.server', null, 'rw', 'text', null, 'probe', '1', null, '^(([0-9a-zA-Z-]{1,63}\\.)+[a-zA-Z]{2,4})$|^((?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?))$', '请输入域名 或 IP', null);
INSERT INTO `t_params_appoint` VALUES ('3', '探针端口', 'newprobe.probe.port', '1025 ~ 65535 之间', 'rw', 'text', null, 'probe', '1', null, '^(102[5-9]{1}|10[3-9]\\d{1}|1[1-9]\\d{2}|[2-9]\\d{3}|[1-5]\\d{4}|6[0-4]\\d{3}|65[0-4]\\d{2}|655[0-2]\\d|6553[0-5])$', '1025 ~ 65535 之间', null);
INSERT INTO `t_params_appoint` VALUES ('4', '探针抓包数', 'newprobe.probe.capcnt', '单次扫描单个信道无线包总个数上限，建议（500 ~ 3000之间）', 'rw', 'text', null, 'probe', '1', null, '^([0-9]|[1-9]\\d{1}|[1-9]\\d{2}|[1-9]\\d{3}|[1-2]\\d{4}|31\\d{3}|32[0-6]\\d{2}|327[1-5]\\d{1}|3276[0-7]{1})$', '0 ~ 32767 之间', null);
INSERT INTO `t_params_appoint` VALUES ('5', '设备无线名称', 'wireless.@wifi-iface[0].ssid', '1~40位（一个中文占3位）', 'rw', 'text', null, 'wifi', '1', '{\"byte\":[1,40]}', null, null, null);

-- ----------------------------
-- Table structure for `t_role`
-- ----------------------------
DROP TABLE IF EXISTS `t_role`;
CREATE TABLE `t_role` (
  `role_id` int(11) NOT NULL COMMENT '菜单表ID',
  `role_name` varchar(16) NOT NULL COMMENT '角色表ID',
  `remark` varchar(16) NOT NULL COMMENT '角色备注说明',
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色信息';

-- ----------------------------
-- Records of t_role
-- ----------------------------
INSERT INTO `t_role` VALUES ('0', '管理员', '拥有系统最高权限');
INSERT INTO `t_role` VALUES ('1', '普通用户', '普通用户');

-- ----------------------------
-- Table structure for `t_role_menu`
-- ----------------------------
DROP TABLE IF EXISTS `t_role_menu`;
CREATE TABLE `t_role_menu` (
  `menu_id` int(11) NOT NULL COMMENT '菜单表ID',
  `role_id` int(11) NOT NULL COMMENT '角色表ID',
  PRIMARY KEY (`menu_id`,`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_role_menu
-- ----------------------------
INSERT INTO `t_role_menu` VALUES ('1', '0');
INSERT INTO `t_role_menu` VALUES ('1', '1');
INSERT INTO `t_role_menu` VALUES ('2', '0');
INSERT INTO `t_role_menu` VALUES ('2', '1');
INSERT INTO `t_role_menu` VALUES ('3', '0');
INSERT INTO `t_role_menu` VALUES ('3', '1');
INSERT INTO `t_role_menu` VALUES ('4', '0');
INSERT INTO `t_role_menu` VALUES ('9', '0');
INSERT INTO `t_role_menu` VALUES ('9', '1');
INSERT INTO `t_role_menu` VALUES ('10', '0');
INSERT INTO `t_role_menu` VALUES ('10', '1');

-- ----------------------------
-- Table structure for `t_shell`
-- ----------------------------
DROP TABLE IF EXISTS `t_shell`;
CREATE TABLE `t_shell` (
  `shell_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增编号',
  `shell_name` varchar(20) DEFAULT NULL COMMENT '脚本名称',
  `shell_desc` varchar(50) DEFAULT NULL COMMENT '脚本描述',
  `shell_path` varchar(200) DEFAULT NULL COMMENT '脚本文件保存路径',
  `user_id` int(11) DEFAULT NULL COMMENT '用户ID',
  `addTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '上传时间',
  `status` tinyint(4) DEFAULT NULL COMMENT '插件状态 1:正常 0:失效',
  `shell_md5` varchar(50) DEFAULT NULL COMMENT 'MD5值',
  PRIMARY KEY (`shell_id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8 COMMENT='脚本信息表';

-- ----------------------------
-- Records of t_shell
-- ----------------------------
INSERT INTO `t_shell` VALUES ('11', 'jiaoben01', '脚本描述kdjwa', 'E:/data001/file/shell/QKNW4Mh0_1533203790296.sh', '3', '2018-08-02 17:56:30', '1', '64cbb70a797211e02529800c51316364');
INSERT INTO `t_shell` VALUES ('12', 'jiaoben03', '', 'http://192.168.11.11/file/shell/lkDCluEp_1533268625540.sh', '1', '2018-08-03 11:57:06', '1', '64cbb70a797211e02529800c51316364');
INSERT INTO `t_shell` VALUES ('13', 'jiaoben06', '', 'http://127.0.0.1/file/shell/HFUFPBFe_1533272156621.jar', '1', '2018-08-03 12:55:57', '1', 'cdff193f920d0588f0be7588ae3921f6');
INSERT INTO `t_shell` VALUES ('14', 'jiaoben07', '', 'http://192.168.11.11/file/shell/onqkvSbl_1533275650773.sh', '1', '2018-08-03 13:54:11', '1', '64cbb70a797211e02529800c51316364');

-- ----------------------------
-- Table structure for `t_shelltask`
-- ----------------------------
DROP TABLE IF EXISTS `t_shelltask`;
CREATE TABLE `t_shelltask` (
  `task_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `task_name` char(40) DEFAULT NULL COMMENT '任务名称',
  `task_desc` char(200) DEFAULT NULL COMMENT '任务描述',
  `shell_id` int(11) DEFAULT NULL COMMENT '脚本ID',
  `add_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '任务新增时间',
  `user_id` int(11) DEFAULT NULL COMMENT '用户ID',
  `dev_total` int(11) DEFAULT NULL COMMENT '设备总数',
  `completed_num` int(11) DEFAULT NULL COMMENT '设备已完成数',
  `status` int(11) DEFAULT NULL COMMENT '状态',
  PRIMARY KEY (`task_id`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8 COMMENT='脚本/固件任务表';

-- ----------------------------
-- Records of t_shelltask
-- ----------------------------
INSERT INTO `t_shelltask` VALUES ('24', '脚本任务01', '', '14', '2018-08-03 16:13:13', '1', '1', '0', '0');
INSERT INTO `t_shelltask` VALUES ('25', '脚本任务02', '', '14', '2018-08-03 16:34:01', '1', '1', '1', '1');
INSERT INTO `t_shelltask` VALUES ('26', '脚本任务03', '', '14', '2018-08-04 10:38:39', '1', '46', '1', '0');

-- ----------------------------
-- Table structure for `t_shelltask_task`
-- ----------------------------
DROP TABLE IF EXISTS `t_shelltask_task`;
CREATE TABLE `t_shelltask_task` (
  `shelltask_id` int(20) NOT NULL COMMENT '脚本任务ID',
  `task_id` varchar(40) NOT NULL COMMENT '设备任务ID'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_shelltask_task
-- ----------------------------
INSERT INTO `t_shelltask_task` VALUES ('24', '20180803041212a1c8eea63486b85935');
INSERT INTO `t_shelltask_task` VALUES ('25', '20180803043349a1c8eea63486b82545');
INSERT INTO `t_shelltask_task` VALUES ('26', '20180804103837a1c8eea63406b83690');
INSERT INTO `t_shelltask_task` VALUES ('26', '20180804103837a00000000000015544');
INSERT INTO `t_shelltask_task` VALUES ('26', '20180804103837a00000000000029538');
INSERT INTO `t_shelltask_task` VALUES ('26', '20180804103837a00000000000036295');
INSERT INTO `t_shelltask_task` VALUES ('26', '20180804103837a00000000000047314');
INSERT INTO `t_shelltask_task` VALUES ('26', '20180804103837a00000000000054041');
INSERT INTO `t_shelltask_task` VALUES ('26', '20180804103837A00000000000068013');
INSERT INTO `t_shelltask_task` VALUES ('26', '20180804103837A00000000000071291');
INSERT INTO `t_shelltask_task` VALUES ('26', '20180804103837A00000000000089002');
INSERT INTO `t_shelltask_task` VALUES ('26', '20180804103837A00000000000096926');
INSERT INTO `t_shelltask_task` VALUES ('26', '20180804103837A00000000000105652');
INSERT INTO `t_shelltask_task` VALUES ('26', '20180804103837A00000000000114977');
INSERT INTO `t_shelltask_task` VALUES ('26', '20180804103837A00000000000123887');
INSERT INTO `t_shelltask_task` VALUES ('26', '20180804103837A00000000000133629');
INSERT INTO `t_shelltask_task` VALUES ('26', '20180804103837A00000000000141996');
INSERT INTO `t_shelltask_task` VALUES ('26', '20180804103837A00000000000158958');
INSERT INTO `t_shelltask_task` VALUES ('26', '20180804103837A00000000000165405');
INSERT INTO `t_shelltask_task` VALUES ('26', '20180804103837A00000000000174399');
INSERT INTO `t_shelltask_task` VALUES ('26', '20180804103837A00000000000184183');
INSERT INTO `t_shelltask_task` VALUES ('26', '20180804103837A00000000000194770');
INSERT INTO `t_shelltask_task` VALUES ('26', '20180804103837A00000000000206979');
INSERT INTO `t_shelltask_task` VALUES ('26', '20180804103837A00000000000212652');
INSERT INTO `t_shelltask_task` VALUES ('26', '20180804103837A00000000000226387');
INSERT INTO `t_shelltask_task` VALUES ('26', '20180804103837A00000000000238124');
INSERT INTO `t_shelltask_task` VALUES ('26', '2018080410383718666');
INSERT INTO `t_shelltask_task` VALUES ('26', '2018080410383727545');
INSERT INTO `t_shelltask_task` VALUES ('26', '2018080410383743598');
INSERT INTO `t_shelltask_task` VALUES ('26', '2018080410383756284');
INSERT INTO `t_shelltask_task` VALUES ('26', '2018080410383767340');
INSERT INTO `t_shelltask_task` VALUES ('26', '2018080410383779574');
INSERT INTO `t_shelltask_task` VALUES ('26', '2018080410383784395');
INSERT INTO `t_shelltask_task` VALUES ('26', '20180804103837213132131321238075');
INSERT INTO `t_shelltask_task` VALUES ('26', '20180804103837A00000000000246835');
INSERT INTO `t_shelltask_task` VALUES ('26', '20180804103837A00000000001001152');
INSERT INTO `t_shelltask_task` VALUES ('26', '20180804103837121212xcvc78786157');
INSERT INTO `t_shelltask_task` VALUES ('26', '20180804103837555556666677779278');
INSERT INTO `t_shelltask_task` VALUES ('26', '20180804103837232352642557559400');
INSERT INTO `t_shelltask_task` VALUES ('26', '20180804103837a1c8eea63486b82686');
INSERT INTO `t_shelltask_task` VALUES ('26', '20180804103837A00000000001116583');
INSERT INTO `t_shelltask_task` VALUES ('26', '20180804103837A00000444400009283');
INSERT INTO `t_shelltask_task` VALUES ('26', '20180804103837gf2121321215557023');
INSERT INTO `t_shelltask_task` VALUES ('26', '20180804103837gf2121321215569588');
INSERT INTO `t_shelltask_task` VALUES ('26', '20180804103837???????5476');
INSERT INTO `t_shelltask_task` VALUES ('26', '20180804103837111111111111as3895');
INSERT INTO `t_shelltask_task` VALUES ('26', '201808041038373344555555553d2410');
INSERT INTO `t_shelltask_task` VALUES ('26', '20180804103837test12345678903082');

-- ----------------------------
-- Table structure for `t_task`
-- ----------------------------
DROP TABLE IF EXISTS `t_task`;
CREATE TABLE `t_task` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `task_id` varchar(40) DEFAULT NULL COMMENT '任务ID，时间戳+设备编号+随机数',
  `dev_num` varchar(30) DEFAULT NULL COMMENT '设备编号',
  `operate` varchar(50) DEFAULT NULL COMMENT '任务操作类型',
  `start_time` timestamp NULL DEFAULT NULL COMMENT '任务发起时间',
  `replay_time` timestamp NULL DEFAULT NULL COMMENT '任务回复时间',
  `status` tinyint(1) DEFAULT NULL COMMENT '任务完成状态',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=749 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_task
-- ----------------------------
INSERT INTO `t_task` VALUES ('663', '20180803040522a1c8eea63486b87232', 'a1c8eea63486b8', 'getConfig', '2018-08-03 16:05:23', '2018-08-03 16:05:23', '0');
INSERT INTO `t_task` VALUES ('664', '20180803040831a1c8eea63486b88982', 'a1c8eea63486b8', 'getConfig', '2018-08-03 16:08:32', '2018-08-03 16:08:32', '0');
INSERT INTO `t_task` VALUES ('665', '20180803041020a1c8eea63486b87470', 'a1c8eea63486b8', 'getConfig', '2018-08-03 16:10:20', '2018-08-03 16:10:20', '0');
INSERT INTO `t_task` VALUES ('666', '20180803041028a1c8eea63486b89193', 'a1c8eea63486b8', 'setPasswd', '2018-08-03 16:10:29', '2018-08-03 16:10:29', '0');
INSERT INTO `t_task` VALUES ('667', '20180803041125a00000000000025158', 'a0000000000002', 'installIpk', '2018-08-03 16:11:26', null, '-1');
INSERT INTO `t_task` VALUES ('668', '20180803041125a00000000000034349', 'a0000000000003', 'installIpk', '2018-08-03 16:11:26', null, '-1');
INSERT INTO `t_task` VALUES ('669', '20180803041125a1c8eea63486b89223', 'a1c8eea63486b8', 'installIpk', '2018-08-03 16:11:26', '2018-08-03 16:11:26', '10');
INSERT INTO `t_task` VALUES ('670', '20180803041212a1c8eea63486b85935', 'a1c8eea63486b8', 'shell', '2018-08-03 16:12:12', '2018-08-03 16:12:12', '10');
INSERT INTO `t_task` VALUES ('671', '20180803041255a00000000000024504', 'a0000000000002', 'firmware', '2018-08-03 16:12:56', null, '-1');
INSERT INTO `t_task` VALUES ('672', '20180803041255a00000000000036995', 'a0000000000003', 'firmware', '2018-08-03 16:12:56', null, '-1');
INSERT INTO `t_task` VALUES ('673', '20180803041255a1c8eea63486b84039', 'a1c8eea63486b8', 'firmware', '2018-08-03 16:12:56', '2018-08-03 16:12:56', '10');
INSERT INTO `t_task` VALUES ('674', '20180803041813a1c8eea63486b89840', 'a1c8eea63486b8', 'getConfig', '2018-08-03 16:18:13', '2018-08-03 16:18:13', '0');
INSERT INTO `t_task` VALUES ('675', '20180803041821a1c8eea63486b82535', 'a1c8eea63486b8', 'setPasswd', '2018-08-03 16:18:22', '2018-08-03 16:18:22', '0');
INSERT INTO `t_task` VALUES ('676', '20180803041827a1c8eea63486b83588', 'a1c8eea63486b8', 'getConfig', '2018-08-03 16:18:27', '2018-08-03 16:18:27', '0');
INSERT INTO `t_task` VALUES ('677', '20180803043208a00000000000022917', 'a0000000000002', 'installIpk', '2018-08-03 16:32:09', null, '-1');
INSERT INTO `t_task` VALUES ('678', '20180803043208a00000000000036214', 'a0000000000003', 'installIpk', '2018-08-03 16:32:09', null, '-1');
INSERT INTO `t_task` VALUES ('679', '20180803043208a1c8eea63486b88240', 'a1c8eea63486b8', 'installIpk', '2018-08-03 16:32:09', '2018-08-03 16:32:14', '0');
INSERT INTO `t_task` VALUES ('680', '20180803043318a1c8eea63486b85208', 'a1c8eea63486b8', 'removeIpk', '2018-08-03 16:33:18', '2018-08-03 16:33:18', '0');
INSERT INTO `t_task` VALUES ('681', '20180803043349a1c8eea63486b82545', 'a1c8eea63486b8', 'shell', '2018-08-03 16:33:49', null, '0');
INSERT INTO `t_task` VALUES ('682', '20180803043918a1c8eea63486b88290', 'a1c8eea63486b8', 'firmware', '2018-08-03 16:39:18', null, '0');
INSERT INTO `t_task` VALUES ('683', '20180803044353a1c8eea63486b89844', 'a1c8eea63486b8', 'getConfig', '2018-08-03 16:43:54', '2018-08-03 16:43:54', '0');
INSERT INTO `t_task` VALUES ('684', '20180803044653a1c8eea63486b84802', 'a1c8eea63486b8', 'getConfig', '2018-08-03 16:46:53', '2018-08-03 16:46:53', '0');
INSERT INTO `t_task` VALUES ('685', '20180803044710a1c8eea63486b86680', 'a1c8eea63486b8', 'setConfig', '2018-08-03 16:47:10', '2018-08-03 16:47:10', '0');
INSERT INTO `t_task` VALUES ('686', '20180803062552a1c8eea63486b85621', 'a1c8eea63486b8', 'getConfig', '2018-08-03 18:25:52', '2018-08-03 18:25:52', '0');
INSERT INTO `t_task` VALUES ('687', '20180803062643a1c8eea63486b84652', 'a1c8eea63486b8', 'getConfig', '2018-08-03 18:26:43', '2018-08-03 18:26:44', '0');
INSERT INTO `t_task` VALUES ('688', '20180804103803a1c8eea63486b84994', 'a1c8eea63486b8', 'installIpk', '2018-08-04 10:38:03', '2018-08-04 10:38:09', '0');
INSERT INTO `t_task` VALUES ('689', '20180804103837a1c8eea63406b83690', 'a1c8eea63406b8', 'shell', '2018-08-04 10:38:37', null, '-1');
INSERT INTO `t_task` VALUES ('690', '20180804103837a00000000000015544', 'a0000000000001', 'shell', '2018-08-04 10:38:37', null, '-1');
INSERT INTO `t_task` VALUES ('691', '20180804103837a00000000000029538', 'a0000000000002', 'shell', '2018-08-04 10:38:37', null, '-1');
INSERT INTO `t_task` VALUES ('692', '20180804103837a00000000000036295', 'a0000000000003', 'shell', '2018-08-04 10:38:37', null, '-1');
INSERT INTO `t_task` VALUES ('693', '20180804103837a00000000000047314', 'a0000000000004', 'shell', '2018-08-04 10:38:37', null, '-1');
INSERT INTO `t_task` VALUES ('694', '20180804103837a00000000000054041', 'a0000000000005', 'shell', '2018-08-04 10:38:37', null, '-1');
INSERT INTO `t_task` VALUES ('695', '20180804103837A00000000000068013', 'A0000000000006', 'shell', '2018-08-04 10:38:37', null, '-1');
INSERT INTO `t_task` VALUES ('696', '20180804103837A00000000000071291', 'A0000000000007', 'shell', '2018-08-04 10:38:37', null, '-1');
INSERT INTO `t_task` VALUES ('697', '20180804103837A00000000000089002', 'A0000000000008', 'shell', '2018-08-04 10:38:37', null, '-1');
INSERT INTO `t_task` VALUES ('698', '20180804103837A00000000000096926', 'A0000000000009', 'shell', '2018-08-04 10:38:37', null, '-1');
INSERT INTO `t_task` VALUES ('699', '20180804103837A00000000000105652', 'A0000000000010', 'shell', '2018-08-04 10:38:37', null, '-1');
INSERT INTO `t_task` VALUES ('700', '20180804103837A00000000000114977', 'A0000000000011', 'shell', '2018-08-04 10:38:37', null, '-1');
INSERT INTO `t_task` VALUES ('701', '20180804103837A00000000000123887', 'A0000000000012', 'shell', '2018-08-04 10:38:37', null, '-1');
INSERT INTO `t_task` VALUES ('702', '20180804103837A00000000000133629', 'A0000000000013', 'shell', '2018-08-04 10:38:37', null, '-1');
INSERT INTO `t_task` VALUES ('703', '20180804103837A00000000000141996', 'A0000000000014', 'shell', '2018-08-04 10:38:37', null, '-1');
INSERT INTO `t_task` VALUES ('704', '20180804103837A00000000000158958', 'A0000000000015', 'shell', '2018-08-04 10:38:37', null, '-1');
INSERT INTO `t_task` VALUES ('705', '20180804103837A00000000000165405', 'A0000000000016', 'shell', '2018-08-04 10:38:37', null, '-1');
INSERT INTO `t_task` VALUES ('706', '20180804103837A00000000000174399', 'A0000000000017', 'shell', '2018-08-04 10:38:37', null, '-1');
INSERT INTO `t_task` VALUES ('707', '20180804103837A00000000000184183', 'A0000000000018', 'shell', '2018-08-04 10:38:37', null, '-1');
INSERT INTO `t_task` VALUES ('708', '20180804103837A00000000000194770', 'A0000000000019', 'shell', '2018-08-04 10:38:37', null, '-1');
INSERT INTO `t_task` VALUES ('709', '20180804103837A00000000000206979', 'A0000000000020', 'shell', '2018-08-04 10:38:37', null, '-1');
INSERT INTO `t_task` VALUES ('710', '20180804103837A00000000000212652', 'A0000000000021', 'shell', '2018-08-04 10:38:37', null, '-1');
INSERT INTO `t_task` VALUES ('711', '20180804103837A00000000000226387', 'A0000000000022', 'shell', '2018-08-04 10:38:37', null, '-1');
INSERT INTO `t_task` VALUES ('712', '20180804103837A00000000000238124', 'A0000000000023', 'shell', '2018-08-04 10:38:37', null, '-1');
INSERT INTO `t_task` VALUES ('713', '2018080410383718666', '1', 'shell', '2018-08-04 10:38:37', null, '-1');
INSERT INTO `t_task` VALUES ('714', '2018080410383727545', '2', 'shell', '2018-08-04 10:38:37', null, '-1');
INSERT INTO `t_task` VALUES ('715', '2018080410383743598', '4', 'shell', '2018-08-04 10:38:37', null, '-1');
INSERT INTO `t_task` VALUES ('716', '2018080410383756284', '5', 'shell', '2018-08-04 10:38:37', null, '-1');
INSERT INTO `t_task` VALUES ('717', '2018080410383767340', '6', 'shell', '2018-08-04 10:38:37', null, '-1');
INSERT INTO `t_task` VALUES ('718', '2018080410383779574', '7', 'shell', '2018-08-04 10:38:37', null, '-1');
INSERT INTO `t_task` VALUES ('719', '2018080410383784395', '8', 'shell', '2018-08-04 10:38:37', null, '-1');
INSERT INTO `t_task` VALUES ('720', '20180804103837213132131321238075', '21313213132123', 'shell', '2018-08-04 10:38:37', null, '-1');
INSERT INTO `t_task` VALUES ('721', '20180804103837A00000000000246835', 'A0000000000024', 'shell', '2018-08-04 10:38:37', null, '-1');
INSERT INTO `t_task` VALUES ('722', '20180804103837A00000000001001152', 'A0000000000100', 'shell', '2018-08-04 10:38:37', null, '-1');
INSERT INTO `t_task` VALUES ('723', '20180804103837121212xcvc78786157', '121212xcvc7878', 'shell', '2018-08-04 10:38:37', null, '-1');
INSERT INTO `t_task` VALUES ('724', '20180804103837555556666677779278', '55555666667777', 'shell', '2018-08-04 10:38:37', null, '-1');
INSERT INTO `t_task` VALUES ('725', '20180804103837232352642557559400', '23235264255755', 'shell', '2018-08-04 10:38:37', null, '-1');
INSERT INTO `t_task` VALUES ('726', '20180804103837a1c8eea63486b82686', 'a1c8eea63486b8', 'shell', '2018-08-04 10:38:37', null, '0');
INSERT INTO `t_task` VALUES ('727', '20180804103837A00000000001116583', 'A0000000000111', 'shell', '2018-08-04 10:38:37', null, '-1');
INSERT INTO `t_task` VALUES ('728', '20180804103837A00000444400009283', 'A0000044440000', 'shell', '2018-08-04 10:38:37', null, '-1');
INSERT INTO `t_task` VALUES ('729', '20180804103837gf2121321215557023', 'gf212132121555', 'shell', '2018-08-04 10:38:37', null, '-1');
INSERT INTO `t_task` VALUES ('730', '20180804103837gf2121321215569588', 'gf212132121556', 'shell', '2018-08-04 10:38:37', null, '-1');
INSERT INTO `t_task` VALUES ('731', '20180804103837???????5476', '???????', 'shell', '2018-08-04 10:38:37', null, '-1');
INSERT INTO `t_task` VALUES ('732', '20180804103837111111111111as3895', '111111111111as', 'shell', '2018-08-04 10:38:37', null, '-1');
INSERT INTO `t_task` VALUES ('733', '201808041038373344555555553d2410', '3344555555553d', 'shell', '2018-08-04 10:38:37', null, '-1');
INSERT INTO `t_task` VALUES ('734', '20180804103837test12345678903082', 'test1234567890', 'shell', '2018-08-04 10:38:37', null, '-1');
INSERT INTO `t_task` VALUES ('735', '20180804103918a00000000000025504', 'a0000000000002', 'firmware', '2018-08-04 10:39:19', null, '-1');
INSERT INTO `t_task` VALUES ('736', '20180804103918a00000000000037163', 'a0000000000003', 'firmware', '2018-08-04 10:39:19', null, '-1');
INSERT INTO `t_task` VALUES ('737', '20180804103918a1c8eea63486b89908', 'a1c8eea63486b8', 'firmware', '2018-08-04 10:39:19', null, '0');
INSERT INTO `t_task` VALUES ('738', '20180806015047a00000000000027614', 'a0000000000002', 'removeIpk', '2018-08-06 13:50:47', null, '-1');
INSERT INTO `t_task` VALUES ('739', '20180806015047a00000000000036596', 'a0000000000003', 'removeIpk', '2018-08-06 13:50:47', null, '-1');
INSERT INTO `t_task` VALUES ('740', '20180806015047a1c8eea63486b88725', 'a1c8eea63486b8', 'removeIpk', '2018-08-06 13:50:47', '2018-08-06 19:57:03', '0');
INSERT INTO `t_task` VALUES ('741', '20180807013730a1c8eea63486b87896', 'a1c8eea63486b8', 'getConfig', '2018-08-07 13:37:31', '2018-08-07 13:37:31', '0');
INSERT INTO `t_task` VALUES ('742', '20180807013848a1c8eea63486b86633', 'a1c8eea63486b8', 'getConfig', '2018-08-07 13:38:48', '2018-08-07 13:38:48', '0');
INSERT INTO `t_task` VALUES ('743', '20180807013857a1c8eea63486b85561', 'a1c8eea63486b8', 'getConfig', '2018-08-07 13:38:57', '2018-08-07 13:38:57', '0');
INSERT INTO `t_task` VALUES ('744', '20180807015223a1c8eea63486b85453', 'a1c8eea63486b8', 'installIpk', '2018-08-07 13:52:23', '2018-08-07 13:52:28', '0');
INSERT INTO `t_task` VALUES ('745', '20180807015302a1c8eea63486b82797', 'a1c8eea63486b8', 'removeIpk', '2018-08-07 13:53:02', '2018-08-07 13:53:03', '0');
INSERT INTO `t_task` VALUES ('746', '20180807015411a00000000000024890', 'a0000000000002', 'installIpk', '2018-08-07 13:54:12', null, '-1');
INSERT INTO `t_task` VALUES ('747', '20180807015411a00000000000038087', 'a0000000000003', 'installIpk', '2018-08-07 13:54:12', null, '-1');
INSERT INTO `t_task` VALUES ('748', '20180807015411a1c8eea63486b87940', 'a1c8eea63486b8', 'installIpk', '2018-08-07 13:54:12', '2018-08-07 13:54:17', '0');
