# ************************************************************
# Sequel Pro SQL dump
# Version 4541
#
# http://www.sequelpro.com/
# https://github.com/sequelpro/sequelpro
#
# Host: 192.168.11.13 (MySQL 5.6.26-log)
# Database: tianzhixing-kernel-platform
# Generation Time: 2018-07-23 03:08:08 +0000
# ************************************************************


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


# Dump of table account_award_records_info
# ------------------------------------------------------------

DROP TABLE IF EXISTS `account_award_records_info`;

CREATE TABLE `account_award_records_info` (
  `id` bigint(11) unsigned NOT NULL AUTO_INCREMENT,
  `version` int(11) unsigned NOT NULL,
  `create_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `update_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `account_id` bigint(11) NOT NULL,
  `award_type` varchar(32) NOT NULL DEFAULT '',
  PRIMARY KEY (`id`),
  KEY `account_id` (`account_id`),
  KEY `award_type` (`award_type`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;



# Dump of table account_info
# ------------------------------------------------------------

DROP TABLE IF EXISTS `account_info`;

CREATE TABLE `account_info` (
  `id` bigint(11) unsigned NOT NULL AUTO_INCREMENT,
  `version` int(11) unsigned NOT NULL,
  `create_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `update_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `reg_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `mobile` varchar(32) NOT NULL DEFAULT '',
  `account_token` varchar(64) NOT NULL DEFAULT '',
  `org` varchar(16) NOT NULL DEFAULT '',
  `third_token` varchar(32) NOT NULL DEFAULT '',
  PRIMARY KEY (`id`),
  UNIQUE KEY `account_token` (`account_token`),
  KEY `mobile` (`mobile`),
  KEY `org` (`org`),
  KEY `third_token` (`third_token`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;



# Dump of table account_starpoint_info
# ------------------------------------------------------------

DROP TABLE IF EXISTS `account_starpoint_info`;

CREATE TABLE `account_starpoint_info` (
  `id` bigint(11) unsigned NOT NULL AUTO_INCREMENT,
  `version` int(11) unsigned NOT NULL,
  `create_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `update_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `account_id` bigint(11) NOT NULL,
  `available_starpoint` decimal(16,4) NOT NULL,
  `frozen_starpoint` decimal(16,4) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `account_id` (`account_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;



# Dump of table account_starpoint_operation_records
# ------------------------------------------------------------

DROP TABLE IF EXISTS `account_starpoint_operation_records`;

CREATE TABLE `account_starpoint_operation_records` (
  `id` bigint(11) unsigned NOT NULL AUTO_INCREMENT,
  `version` int(11) unsigned NOT NULL,
  `create_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `update_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `account_id` bigint(11) NOT NULL,
  `available_starpoint` decimal(16,4) NOT NULL,
  `frozen_starpoint` decimal(16,4) NOT NULL,
  `oper_starpoint` decimal(16,4) NOT NULL,
  `operation_type` int(11) NOT NULL,
  `records_type` int(11) NOT NULL,
  `task_id` bigint(20) DEFAULT NULL,
  `advertisement_id` bigint(11) DEFAULT NULL,
  `remark` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `account_id` (`account_id`),
  KEY `records_type` (`records_type`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;



# Dump of table device_info
# ------------------------------------------------------------

DROP TABLE IF EXISTS `device_info`;

CREATE TABLE `device_info` (
  `id` bigint(11) unsigned NOT NULL AUTO_INCREMENT,
  `version` int(11) unsigned NOT NULL,
  `create_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `update_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `device_id` varchar(64) NOT NULL DEFAULT '',
  `device_type` varchar(32) NOT NULL DEFAULT '',
  `device_mac` varchar(64) NOT NULL DEFAULT '',
  `device_model` varchar(32) NOT NULL DEFAULT '',
  `operation_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `device_status` varchar(16) NOT NULL DEFAULT '',
  `org` varchar(64) NOT NULL DEFAULT '',
  `account_id` bigint(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `device_id` (`device_id`),
  KEY `org` (`org`),
  KEY `account_id` (`account_id`),
  KEY `device_status` (`device_status`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;



# Dump of table task_info
# ------------------------------------------------------------

DROP TABLE IF EXISTS `task_info`;

CREATE TABLE `task_info` (
  `id` bigint(11) unsigned NOT NULL AUTO_INCREMENT,
  `version` int(11) unsigned NOT NULL,
  `create_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `update_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `task_id` varchar(64) NOT NULL DEFAULT '',
  `operation_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `task_status` varchar(16) NOT NULL DEFAULT '',
  `account_id` bigint(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `task_id` (`task_id`),
  KEY `account_id` (`account_id`),
  KEY `task_status` (`task_status`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;




/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
