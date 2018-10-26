# ************************************************************
# Sequel Pro SQL dump
# Version 4541
#
# http://www.sequelpro.com/
# https://github.com/sequelpro/sequelpro
#
# Host: 127.0.0.1 (MySQL 5.6.39)
# Database: tianzhixing-authservice
# Generation Time: 2018-06-26 01:55:46 +0000
# ************************************************************


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


# Dump of table auth_idcard_info
# ------------------------------------------------------------

DROP TABLE IF EXISTS `auth_idcard_info`;

CREATE TABLE `auth_idcard_info` (
  `id` bigint(11) unsigned NOT NULL AUTO_INCREMENT,
  `version` int(11) NOT NULL,
  `user_name` varchar(32) NOT NULL DEFAULT '',
  `id_card` varchar(32) NOT NULL DEFAULT '',
  `create_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `update_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `request_from_type` varchar(16) NOT NULL DEFAULT '',
  `auth_token` varchar(64) NOT NULL DEFAULT '',
  `request_status` varchar(16) NOT NULL DEFAULT '',
  `trans_id` varchar(32) DEFAULT NULL,
  `trade_num` varchar(32) DEFAULT NULL,
  `fee_status` varchar(16) DEFAULT NULL,
  `result_data_code` varchar(16) DEFAULT NULL,
  `photo` text,
  `auth_org_type` varchar(16) DEFAULT NULL,
  `enable` bit(1) NOT NULL,
  `status_code` varchar(16) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `auth_token` (`auth_token`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



# Dump of table mobile_validation_code
# ------------------------------------------------------------

DROP TABLE IF EXISTS `mobile_validation_code`;

CREATE TABLE `mobile_validation_code` (
  `id` bigint(11) unsigned NOT NULL AUTO_INCREMENT,
  `version` int(11) NOT NULL,
  `mobile` varchar(32) NOT NULL DEFAULT '',
  `create_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `update_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `request_from_type` varchar(16) NOT NULL DEFAULT '',
  `token` varchar(64) NOT NULL DEFAULT '',
  `status` bit(1) NOT NULL,
  `sender_org` varchar(32) NOT NULL DEFAULT '',
  `message` varchar(32) DEFAULT NULL,
  `result_code` varchar(16) DEFAULT NULL,
  `fee` varchar(16) DEFAULT NULL,
  `sid` varchar(32) DEFAULT '',
  `sms_type` varchar(16) DEFAULT NULL,
  `validation_code` varchar(16) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `auth_token` (`token`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;




/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
