# ************************************************************
# Sequel Pro SQL dump
# Version 4541
#
# http://www.sequelpro.com/
# https://github.com/sequelpro/sequelpro
#
# Host: 192.168.11.13 (MySQL 5.6.26-log)
# Database: tianzhixing-oms-bussiness
# Generation Time: 2018-07-23 03:09:24 +0000
# ************************************************************


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


# Dump of table advertisement_dimension
# ------------------------------------------------------------

DROP TABLE IF EXISTS `advertisement_dimension`;

CREATE TABLE `advertisement_dimension` (
  `id` bigint(11) unsigned NOT NULL AUTO_INCREMENT,
  `version` int(11) NOT NULL,
  `create_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `name` varchar(32) NOT NULL DEFAULT '',
  `advertisement_id` varchar(32) NOT NULL DEFAULT '',
  `enable` bit(1) NOT NULL,
  `begin_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `end_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`id`),
  KEY `advertisement_id` (`advertisement_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;



# Dump of table advertisement_info
# ------------------------------------------------------------

DROP TABLE IF EXISTS `advertisement_info`;

CREATE TABLE `advertisement_info` (
  `id` bigint(11) unsigned NOT NULL AUTO_INCREMENT,
  `version` int(11) NOT NULL,
  `create_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `update_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `create_user` varchar(32) NOT NULL DEFAULT '',
  `update_user` varchar(32) NOT NULL DEFAULT '',
  `advert_name` varchar(32) NOT NULL DEFAULT '',
  `city` varchar(32) DEFAULT NULL,
  `area` varchar(32) NOT NULL DEFAULT '',
  `advert_remark` varchar(32) NOT NULL DEFAULT '',
  `advert_icon` varchar(128) NOT NULL DEFAULT '',
  `advert_pic` varchar(128) NOT NULL DEFAULT '',
  `begin_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `end_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `advert_status` varchar(16) NOT NULL DEFAULT '',
  `advert_type` varchar(16) NOT NULL DEFAULT '',
  `total_count` bigint(20) NOT NULL,
  `total_starpoint` decimal(16,4) NOT NULL,
  `once_starpoint` decimal(16,4) NOT NULL,
  `is_send` bit(1) NOT NULL,
  `advertisement_link` varchar(128) DEFAULT NULL,
  `advertisement_describe` varchar(256) DEFAULT NULL,
  `advert_attribute` varchar(16) NOT NULL DEFAULT '',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;



# Dump of table app_push_records
# ------------------------------------------------------------

DROP TABLE IF EXISTS `app_push_records`;

CREATE TABLE `app_push_records` (
  `id` bigint(11) unsigned NOT NULL AUTO_INCREMENT,
  `version` int(11) NOT NULL,
  `create_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `update_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `oper_user` varchar(32) NOT NULL DEFAULT '',
  `push_type` int(1) NOT NULL,
  `third_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;



# Dump of table app_suspend_info
# ------------------------------------------------------------

DROP TABLE IF EXISTS `app_suspend_info`;

CREATE TABLE `app_suspend_info` (
  `id` bigint(11) unsigned NOT NULL AUTO_INCREMENT,
  `version` int(11) NOT NULL,
  `create_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `update_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `create_user` varchar(32) NOT NULL DEFAULT '',
  `update_user` varchar(32) NOT NULL DEFAULT '',
  `begin_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `end_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `is_send` bit(1) NOT NULL,
  `pic_link` varchar(128) NOT NULL DEFAULT '',
  `pic_url` varchar(128) NOT NULL DEFAULT '',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;



# Dump of table application_dimension
# ------------------------------------------------------------

DROP TABLE IF EXISTS `application_dimension`;

CREATE TABLE `application_dimension` (
  `id` bigint(11) unsigned NOT NULL AUTO_INCREMENT,
  `version` int(11) NOT NULL,
  `create_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `statistics_dimension` varchar(32) NOT NULL DEFAULT '',
  `parent_id` bigint(11) NOT NULL,
  `name` varchar(32) NOT NULL DEFAULT '',
  `value` varchar(32) NOT NULL DEFAULT '',
  `enable` bit(1) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;



# Dump of table application_operation_statistics
# ------------------------------------------------------------

DROP TABLE IF EXISTS `application_operation_statistics`;

CREATE TABLE `application_operation_statistics` (
  `id` bigint(11) unsigned NOT NULL AUTO_INCREMENT,
  `version` int(11) NOT NULL,
  `create_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `statistics_hour` int(11) NOT NULL,
  `statistics_day` int(11) NOT NULL,
  `statistics_month` int(11) NOT NULL,
  `statistics_year` int(11) NOT NULL,
  `client_platform_type` varchar(32) DEFAULT '',
  `platform_from` varchar(32) NOT NULL DEFAULT '',
  `app_start_total_count` int(11) NOT NULL,
  `app_start_diffip_total_count` int(11) NOT NULL,
  `app_down_total_count` int(11) NOT NULL,
  `app_down_diffip_total_count` int(11) NOT NULL,
  `platform_name` varchar(64) NOT NULL DEFAULT '',
  PRIMARY KEY (`id`),
  KEY `statistics_hour` (`statistics_hour`),
  KEY `statistics_day` (`statistics_day`),
  KEY `statistics_month` (`statistics_month`),
  KEY `statistics_year` (`statistics_year`),
  KEY `platform_from` (`platform_from`),
  KEY `client_platform_type` (`client_platform_type`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;



# Dump of table device_checkin_statistics
# ------------------------------------------------------------

DROP TABLE IF EXISTS `device_checkin_statistics`;

CREATE TABLE `device_checkin_statistics` (
  `id` bigint(11) unsigned NOT NULL AUTO_INCREMENT,
  `version` int(11) NOT NULL,
  `create_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `statistics_hour` int(11) NOT NULL,
  `statistics_day` int(11) NOT NULL,
  `statistics_month` int(11) NOT NULL,
  `statistics_year` int(11) NOT NULL,
  `client_platform_type` varchar(32) DEFAULT '',
  `platform_from` varchar(32) NOT NULL DEFAULT '',
  `device_bind_total_count` int(11) NOT NULL,
  `device_unbind_total_count` int(11) NOT NULL,
  `device_type` varchar(32) NOT NULL DEFAULT '',
  `device_type_name` varchar(32) NOT NULL DEFAULT '',
  `platform_name` varchar(64) NOT NULL DEFAULT '',
  PRIMARY KEY (`id`),
  KEY `statistics_hour` (`statistics_hour`),
  KEY `statistics_day` (`statistics_day`),
  KEY `statistics_month` (`statistics_month`),
  KEY `statistics_year` (`statistics_year`),
  KEY `platform_from` (`platform_from`),
  KEY `client_platform_type` (`client_platform_type`),
  KEY `device_type` (`device_type`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;



# Dump of table device_current_online_statistics
# ------------------------------------------------------------

DROP TABLE IF EXISTS `device_current_online_statistics`;

CREATE TABLE `device_current_online_statistics` (
  `id` bigint(11) unsigned NOT NULL AUTO_INCREMENT,
  `version` int(11) NOT NULL,
  `create_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `client_platform_type` varchar(32) DEFAULT '',
  `platform_from` varchar(32) NOT NULL DEFAULT '',
  `device_id` varchar(64) NOT NULL DEFAULT '',
  `status` int(1) NOT NULL,
  `lng` varchar(32) NOT NULL DEFAULT '',
  `lat` varchar(32) NOT NULL DEFAULT '',
  `update_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `device_type` varchar(32) NOT NULL DEFAULT '',
  PRIMARY KEY (`id`),
  KEY `platform_from` (`platform_from`),
  KEY `client_platform_type` (`client_platform_type`),
  KEY `device_id` (`device_id`),
  KEY `device_type` (`device_type`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;



# Dump of table device_dimension
# ------------------------------------------------------------

DROP TABLE IF EXISTS `device_dimension`;

CREATE TABLE `device_dimension` (
  `id` bigint(11) unsigned NOT NULL AUTO_INCREMENT,
  `version` int(11) NOT NULL,
  `create_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `name` varchar(32) NOT NULL DEFAULT '',
  `value` varchar(32) NOT NULL DEFAULT '',
  `enable` bit(1) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;



# Dump of table device_online_status_statistics
# ------------------------------------------------------------

DROP TABLE IF EXISTS `device_online_status_statistics`;

CREATE TABLE `device_online_status_statistics` (
  `id` bigint(11) unsigned NOT NULL AUTO_INCREMENT,
  `version` int(11) NOT NULL,
  `create_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `statistics_hour` int(11) NOT NULL,
  `statistics_day` int(11) NOT NULL,
  `statistics_month` int(11) NOT NULL,
  `statistics_year` int(11) NOT NULL,
  `client_platform_type` varchar(32) DEFAULT '',
  `platform_from` varchar(32) NOT NULL DEFAULT '',
  `bind_device_online_count` int(11) NOT NULL,
  `bind_device_diffid_online_count` int(11) NOT NULL,
  `unbind_device_online_count` int(11) NOT NULL,
  `unbind_device_diffid_online_count` int(11) NOT NULL,
  `platform_name` varchar(64) NOT NULL DEFAULT '',
  `device_type_name` varchar(32) DEFAULT NULL,
  `bind_device_offline_count` int(11) NOT NULL,
  `bind_device_diffid_offline_count` int(11) NOT NULL,
  `unbind_device_offline_count` int(11) NOT NULL,
  `unbind_device_diffid_offline_count` int(11) NOT NULL,
  `unbind_device_current_online_count` int(11) NOT NULL,
  `bind_device_current_online_count` int(11) NOT NULL,
  `device_type` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `statistics_hour` (`statistics_hour`),
  KEY `statistics_day` (`statistics_day`),
  KEY `statistics_month` (`statistics_month`),
  KEY `statistics_year` (`statistics_year`),
  KEY `platform_from` (`platform_from`),
  KEY `client_platform_type` (`client_platform_type`),
  KEY `device_type` (`device_type`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;



# Dump of table device_operation_statistics
# ------------------------------------------------------------

DROP TABLE IF EXISTS `device_operation_statistics`;

CREATE TABLE `device_operation_statistics` (
  `id` bigint(11) unsigned NOT NULL AUTO_INCREMENT,
  `version` int(11) NOT NULL,
  `create_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `statistics_hour` int(11) NOT NULL,
  `statistics_day` int(11) NOT NULL,
  `statistics_month` int(11) NOT NULL,
  `statistics_year` int(11) NOT NULL,
  `client_platform_type` varchar(32) DEFAULT '',
  `platform_from` varchar(32) NOT NULL DEFAULT '',
  `device_bind_count` int(11) NOT NULL,
  `device_unbind_count` int(11) NOT NULL,
  `platform_name` varchar(64) NOT NULL DEFAULT '',
  `device_type_name` varchar(32) NOT NULL DEFAULT '',
  `device_type` varchar(32) NOT NULL DEFAULT '',
  PRIMARY KEY (`id`),
  KEY `statistics_hour` (`statistics_hour`),
  KEY `statistics_day` (`statistics_day`),
  KEY `statistics_month` (`statistics_month`),
  KEY `statistics_year` (`statistics_year`),
  KEY `platform_from` (`platform_from`),
  KEY `client_platform_type` (`client_platform_type`),
  KEY `device_type` (`device_type`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;



# Dump of table pages_dimension
# ------------------------------------------------------------

DROP TABLE IF EXISTS `pages_dimension`;

CREATE TABLE `pages_dimension` (
  `id` bigint(11) unsigned NOT NULL AUTO_INCREMENT,
  `version` int(11) NOT NULL,
  `create_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `name` varchar(32) NOT NULL DEFAULT '',
  `url` varchar(128) NOT NULL DEFAULT '',
  `enable` bit(1) NOT NULL,
  `begin_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `end_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`id`),
  KEY `url` (`url`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;



# Dump of table pages_operation_statistics
# ------------------------------------------------------------

DROP TABLE IF EXISTS `pages_operation_statistics`;

CREATE TABLE `pages_operation_statistics` (
  `id` bigint(11) unsigned NOT NULL AUTO_INCREMENT,
  `version` int(11) NOT NULL,
  `create_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `statistics_hour` int(11) NOT NULL,
  `statistics_day` int(11) NOT NULL,
  `statistics_month` int(11) NOT NULL,
  `statistics_year` int(11) NOT NULL,
  `client_platform_type` varchar(32) DEFAULT '',
  `platform_from` varchar(32) NOT NULL DEFAULT '',
  `pages_operation_count` int(11) NOT NULL,
  `pages_operation_diffip_count` int(11) NOT NULL,
  `platform_name` varchar(64) NOT NULL DEFAULT '',
  `page_name` varchar(32) NOT NULL DEFAULT '',
  `pages_url` varchar(128) NOT NULL DEFAULT '',
  PRIMARY KEY (`id`),
  KEY `statistics_hour` (`statistics_hour`),
  KEY `statistics_day` (`statistics_day`),
  KEY `statistics_month` (`statistics_month`),
  KEY `statistics_year` (`statistics_year`),
  KEY `platform_from` (`platform_from`),
  KEY `client_platform_type` (`client_platform_type`),
  KEY `pages_url` (`pages_url`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;



# Dump of table staff_info
# ------------------------------------------------------------

DROP TABLE IF EXISTS `staff_info`;

CREATE TABLE `staff_info` (
  `id` bigint(11) unsigned NOT NULL AUTO_INCREMENT,
  `version` int(11) NOT NULL,
  `create_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `update_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `account` varchar(32) NOT NULL DEFAULT '',
  `name` varchar(32) NOT NULL DEFAULT '',
  `password` varchar(32) NOT NULL DEFAULT '',
  `mail` varchar(32) DEFAULT NULL,
  `mobile` varchar(32) DEFAULT NULL,
  `last_login_time` timestamp NULL DEFAULT NULL,
  `enable` int(1) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `account` (`account`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;



# Dump of table star_point_consume_dimension
# ------------------------------------------------------------

DROP TABLE IF EXISTS `star_point_consume_dimension`;

CREATE TABLE `star_point_consume_dimension` (
  `id` bigint(11) unsigned NOT NULL AUTO_INCREMENT,
  `version` int(11) NOT NULL,
  `create_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `name` varchar(32) NOT NULL DEFAULT '',
  `value` varchar(32) NOT NULL DEFAULT '',
  `enable` bit(1) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;



# Dump of table system_param
# ------------------------------------------------------------

DROP TABLE IF EXISTS `system_param`;

CREATE TABLE `system_param` (
  `id` bigint(11) unsigned NOT NULL AUTO_INCREMENT,
  `version` int(11) NOT NULL,
  `create_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `update_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `create_user` varchar(32) NOT NULL DEFAULT '',
  `update_user` varchar(32) NOT NULL DEFAULT '',
  `system_value` varchar(32) NOT NULL DEFAULT '',
  `system_param_type` varchar(32) NOT NULL DEFAULT '',
  `need_push` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `system_param_type` (`system_param_type`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;



# Dump of table task_allot
# ------------------------------------------------------------

DROP TABLE IF EXISTS `task_allot`;

CREATE TABLE `task_allot` (
  `id` bigint(11) unsigned NOT NULL AUTO_INCREMENT,
  `version` int(11) DEFAULT NULL,
  `create_time` timestamp NULL DEFAULT NULL,
  `update_time` timestamp NULL DEFAULT NULL,
  `execute_time` timestamp NULL DEFAULT NULL,
  `execute_day` varchar(16) DEFAULT NULL,
  `task_type` int(1) DEFAULT NULL,
  `task_status` int(1) DEFAULT NULL,
  `machine_num` varchar(16) DEFAULT NULL,
  `task_name` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `execute_time` (`execute_time`),
  KEY `execute_day` (`execute_day`),
  KEY `task_type` (`task_type`),
  KEY `task_status` (`task_status`),
  KEY `machine_num` (`machine_num`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;



# Dump of table task_dimension
# ------------------------------------------------------------

DROP TABLE IF EXISTS `task_dimension`;

CREATE TABLE `task_dimension` (
  `id` bigint(11) unsigned NOT NULL AUTO_INCREMENT,
  `version` int(11) NOT NULL,
  `create_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `name` varchar(32) NOT NULL DEFAULT '',
  `task_id` varchar(32) NOT NULL DEFAULT '',
  `enable` bit(1) NOT NULL,
  `begin_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `end_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`id`),
  KEY `task_id` (`task_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;



# Dump of table task_info
# ------------------------------------------------------------

DROP TABLE IF EXISTS `task_info`;

CREATE TABLE `task_info` (
  `id` bigint(11) unsigned NOT NULL AUTO_INCREMENT,
  `version` int(11) NOT NULL,
  `create_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `update_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `create_user` varchar(32) NOT NULL DEFAULT '',
  `update_user` varchar(32) NOT NULL DEFAULT '',
  `task_name` varchar(32) NOT NULL DEFAULT '',
  `city` varchar(32) DEFAULT NULL,
  `area` varchar(32) NOT NULL DEFAULT '',
  `keyword` varchar(32) NOT NULL DEFAULT '',
  `rate` decimal(16,4) NOT NULL,
  `task_status` varchar(16) NOT NULL DEFAULT '',
  `begin_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `end_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `is_send` bit(1) NOT NULL,
  `task_radius` int(11) NOT NULL,
  `task_icon` varchar(128) NOT NULL DEFAULT '',
  `task_award` decimal(16,4) NOT NULL,
  `task_level` int(1) NOT NULL,
  `task_remark` varchar(128) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;



# Dump of table task_location_info
# ------------------------------------------------------------

DROP TABLE IF EXISTS `task_location_info`;

CREATE TABLE `task_location_info` (
  `id` bigint(11) unsigned NOT NULL AUTO_INCREMENT,
  `version` int(11) NOT NULL,
  `create_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `update_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `task_id` bigint(11) NOT NULL,
  `name` varchar(64) NOT NULL DEFAULT '',
  `location` varchar(64) NOT NULL DEFAULT '',
  `address` varchar(128) DEFAULT '',
  `area` varchar(32) DEFAULT '',
  `province` varchar(32) DEFAULT '',
  `city` varchar(32) DEFAULT '',
  `enable` bit(1) NOT NULL,
  `street_id` varchar(128) DEFAULT '',
  `coord_type` varchar(16) NOT NULL DEFAULT '',
  `map_type` varchar(16) NOT NULL DEFAULT '',
  PRIMARY KEY (`id`),
  KEY `task_id` (`task_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;



# Dump of table user_advertisement_statistics
# ------------------------------------------------------------

DROP TABLE IF EXISTS `user_advertisement_statistics`;

CREATE TABLE `user_advertisement_statistics` (
  `id` bigint(11) unsigned NOT NULL AUTO_INCREMENT,
  `version` int(11) NOT NULL,
  `create_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `statistics_hour` int(11) NOT NULL,
  `statistics_day` int(11) NOT NULL,
  `statistics_month` int(11) NOT NULL,
  `statistics_year` int(11) NOT NULL,
  `client_platform_type` varchar(32) DEFAULT '',
  `platform_from` varchar(32) NOT NULL DEFAULT '',
  `advertisement_id` varchar(32) NOT NULL DEFAULT '',
  `advertisement_name` varchar(32) NOT NULL DEFAULT '',
  `access_count` int(11) NOT NULL,
  `access_diffip_count` int(11) NOT NULL,
  `platform_name` varchar(64) NOT NULL DEFAULT '',
  `click_diffip_count` int(11) NOT NULL,
  `click_count` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `statistics_hour` (`statistics_hour`),
  KEY `statistics_day` (`statistics_day`),
  KEY `statistics_month` (`statistics_month`),
  KEY `statistics_year` (`statistics_year`),
  KEY `platform_from` (`platform_from`),
  KEY `client_platform_type` (`client_platform_type`),
  KEY `advertisement_id` (`advertisement_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;



# Dump of table user_auth_dimension
# ------------------------------------------------------------

DROP TABLE IF EXISTS `user_auth_dimension`;

CREATE TABLE `user_auth_dimension` (
  `id` bigint(11) unsigned NOT NULL AUTO_INCREMENT,
  `version` int(11) NOT NULL,
  `create_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `name` varchar(32) NOT NULL DEFAULT '',
  `value` varchar(32) NOT NULL DEFAULT '',
  `enable` bit(1) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;



# Dump of table user_auth_statistics
# ------------------------------------------------------------

DROP TABLE IF EXISTS `user_auth_statistics`;

CREATE TABLE `user_auth_statistics` (
  `id` bigint(11) unsigned NOT NULL AUTO_INCREMENT,
  `version` int(11) NOT NULL,
  `create_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `statistics_hour` int(11) NOT NULL,
  `statistics_day` int(11) NOT NULL,
  `statistics_month` int(11) NOT NULL,
  `statistics_year` int(11) NOT NULL,
  `client_platform_type` varchar(32) DEFAULT '',
  `platform_from` varchar(32) NOT NULL DEFAULT '',
  `failed_auth_count` int(11) NOT NULL,
  `auth_type` varchar(32) NOT NULL DEFAULT '',
  `platform_name` varchar(64) NOT NULL DEFAULT '',
  `auth_type_name` varchar(32) NOT NULL DEFAULT '',
  `suc_auth_count` int(11) NOT NULL,
  `auth_count` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;



# Dump of table user_basic_statistics
# ------------------------------------------------------------

DROP TABLE IF EXISTS `user_basic_statistics`;

CREATE TABLE `user_basic_statistics` (
  `id` bigint(11) unsigned NOT NULL AUTO_INCREMENT,
  `version` int(11) NOT NULL,
  `create_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `statistics_hour` int(11) NOT NULL,
  `statistics_day` int(11) NOT NULL,
  `statistics_month` int(11) NOT NULL,
  `statistics_year` int(11) NOT NULL,
  `client_platform_type` varchar(32) DEFAULT '',
  `platform_from` varchar(32) NOT NULL DEFAULT '',
  `user_count` int(11) NOT NULL,
  `user_from_type` varchar(32) NOT NULL DEFAULT '',
  `platform_name` varchar(64) NOT NULL DEFAULT '',
  `user_from_type_name` varchar(32) NOT NULL DEFAULT '',
  PRIMARY KEY (`id`),
  KEY `statistics_hour` (`statistics_hour`),
  KEY `statistics_day` (`statistics_day`),
  KEY `statistics_month` (`statistics_month`),
  KEY `statistics_year` (`statistics_year`),
  KEY `platform_from` (`platform_from`),
  KEY `client_platform_type` (`client_platform_type`),
  KEY `user_from_type` (`user_from_type`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;



# Dump of table user_channel_dimension
# ------------------------------------------------------------

DROP TABLE IF EXISTS `user_channel_dimension`;

CREATE TABLE `user_channel_dimension` (
  `id` bigint(11) unsigned NOT NULL AUTO_INCREMENT,
  `version` int(11) NOT NULL,
  `create_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `name` varchar(32) NOT NULL DEFAULT '',
  `value` varchar(32) NOT NULL DEFAULT '',
  `enable` bit(1) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;



# Dump of table user_greeter_card_statistics
# ------------------------------------------------------------

DROP TABLE IF EXISTS `user_greeter_card_statistics`;

CREATE TABLE `user_greeter_card_statistics` (
  `id` bigint(11) unsigned NOT NULL AUTO_INCREMENT,
  `version` int(11) NOT NULL,
  `create_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `statistics_hour` int(11) NOT NULL,
  `statistics_day` int(11) NOT NULL,
  `statistics_month` int(11) NOT NULL,
  `statistics_year` int(11) NOT NULL,
  `client_platform_type` varchar(32) DEFAULT '',
  `platform_from` varchar(32) NOT NULL DEFAULT '',
  `create_count` int(11) NOT NULL,
  `share_count` int(11) NOT NULL,
  `platform_name` varchar(64) NOT NULL DEFAULT '',
  PRIMARY KEY (`id`),
  KEY `statistics_hour` (`statistics_hour`),
  KEY `statistics_day` (`statistics_day`),
  KEY `statistics_month` (`statistics_month`),
  KEY `statistics_year` (`statistics_year`),
  KEY `platform_from` (`platform_from`),
  KEY `client_platform_type` (`client_platform_type`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;



# Dump of table user_login_status_statistics
# ------------------------------------------------------------

DROP TABLE IF EXISTS `user_login_status_statistics`;

CREATE TABLE `user_login_status_statistics` (
  `id` bigint(11) unsigned NOT NULL AUTO_INCREMENT,
  `version` int(11) NOT NULL,
  `create_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `statistics_hour` int(11) NOT NULL,
  `statistics_day` int(11) NOT NULL,
  `statistics_month` int(11) NOT NULL,
  `statistics_year` int(11) NOT NULL,
  `client_platform_type` varchar(32) DEFAULT '',
  `platform_from` varchar(32) NOT NULL DEFAULT '',
  `user_login_count` int(11) NOT NULL,
  `user_login_diff_account_count` int(11) NOT NULL,
  `platform_name` varchar(64) NOT NULL DEFAULT '',
  `user_logout_count` int(11) NOT NULL,
  `user_logout_diff_account_count` int(11) NOT NULL,
  `user_online_count` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `statistics_hour` (`statistics_hour`),
  KEY `statistics_day` (`statistics_day`),
  KEY `statistics_month` (`statistics_month`),
  KEY `statistics_year` (`statistics_year`),
  KEY `platform_from` (`platform_from`),
  KEY `client_platform_type` (`client_platform_type`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;



# Dump of table user_post_card_statistics
# ------------------------------------------------------------

DROP TABLE IF EXISTS `user_post_card_statistics`;

CREATE TABLE `user_post_card_statistics` (
  `id` bigint(11) unsigned NOT NULL AUTO_INCREMENT,
  `version` int(11) NOT NULL,
  `create_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `statistics_hour` int(11) NOT NULL,
  `statistics_day` int(11) NOT NULL,
  `statistics_month` int(11) NOT NULL,
  `statistics_year` int(11) NOT NULL,
  `client_platform_type` varchar(32) DEFAULT '',
  `platform_from` varchar(32) NOT NULL DEFAULT '',
  `create_count` int(11) NOT NULL,
  `share_count` int(11) NOT NULL,
  `platform_name` varchar(64) NOT NULL DEFAULT '',
  PRIMARY KEY (`id`),
  KEY `statistics_hour` (`statistics_hour`),
  KEY `statistics_day` (`statistics_day`),
  KEY `statistics_month` (`statistics_month`),
  KEY `statistics_year` (`statistics_year`),
  KEY `platform_from` (`platform_from`),
  KEY `client_platform_type` (`client_platform_type`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;



# Dump of table user_recording_statistics
# ------------------------------------------------------------

DROP TABLE IF EXISTS `user_recording_statistics`;

CREATE TABLE `user_recording_statistics` (
  `id` bigint(11) unsigned NOT NULL AUTO_INCREMENT,
  `version` int(11) NOT NULL,
  `create_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `statistics_hour` int(11) NOT NULL,
  `statistics_day` int(11) NOT NULL,
  `statistics_month` int(11) NOT NULL,
  `statistics_year` int(11) NOT NULL,
  `client_platform_type` varchar(32) DEFAULT '',
  `platform_from` varchar(32) NOT NULL DEFAULT '',
  `recording_count` int(11) NOT NULL,
  `platform_name` varchar(64) NOT NULL DEFAULT '',
  PRIMARY KEY (`id`),
  KEY `statistics_hour` (`statistics_hour`),
  KEY `statistics_day` (`statistics_day`),
  KEY `statistics_month` (`statistics_month`),
  KEY `statistics_year` (`statistics_year`),
  KEY `platform_from` (`platform_from`),
  KEY `client_platform_type` (`client_platform_type`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;



# Dump of table user_starpoint_consume_statistics
# ------------------------------------------------------------

DROP TABLE IF EXISTS `user_starpoint_consume_statistics`;

CREATE TABLE `user_starpoint_consume_statistics` (
  `id` bigint(11) unsigned NOT NULL AUTO_INCREMENT,
  `version` int(11) NOT NULL,
  `create_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `statistics_hour` int(11) NOT NULL,
  `statistics_day` int(11) NOT NULL,
  `statistics_month` int(11) NOT NULL,
  `statistics_year` int(11) NOT NULL,
  `client_platform_type` varchar(32) DEFAULT '',
  `platform_from` varchar(32) NOT NULL DEFAULT '',
  `consume_cause_name` varchar(32) NOT NULL DEFAULT '',
  `consume_count` decimal(16,4) NOT NULL,
  `consume_cause` varchar(11) DEFAULT '',
  `platform_name` varchar(64) NOT NULL DEFAULT '',
  PRIMARY KEY (`id`),
  KEY `statistics_hour` (`statistics_hour`),
  KEY `statistics_day` (`statistics_day`),
  KEY `statistics_month` (`statistics_month`),
  KEY `statistics_year` (`statistics_year`),
  KEY `platform_from` (`platform_from`),
  KEY `client_platform_type` (`client_platform_type`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;



# Dump of table user_starpoint_increment_statistics
# ------------------------------------------------------------

DROP TABLE IF EXISTS `user_starpoint_increment_statistics`;

CREATE TABLE `user_starpoint_increment_statistics` (
  `id` bigint(11) unsigned NOT NULL AUTO_INCREMENT,
  `version` int(11) NOT NULL,
  `create_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `statistics_hour` int(11) NOT NULL,
  `statistics_day` int(11) NOT NULL,
  `statistics_month` int(11) NOT NULL,
  `statistics_year` int(11) NOT NULL,
  `client_platform_type` varchar(32) DEFAULT '',
  `platform_from` varchar(32) NOT NULL DEFAULT '',
  `increment_cause_name` varchar(32) NOT NULL DEFAULT '',
  `increment_count` decimal(16,4) NOT NULL,
  `increment_type` int(1) NOT NULL,
  `increment_cause` varchar(11) DEFAULT '',
  `platform_name` varchar(64) NOT NULL DEFAULT '',
  PRIMARY KEY (`id`),
  KEY `statistics_hour` (`statistics_hour`),
  KEY `statistics_day` (`statistics_day`),
  KEY `statistics_month` (`statistics_month`),
  KEY `statistics_year` (`statistics_year`),
  KEY `platform_from` (`platform_from`),
  KEY `client_platform_type` (`client_platform_type`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;



# Dump of table user_task_statistics
# ------------------------------------------------------------

DROP TABLE IF EXISTS `user_task_statistics`;

CREATE TABLE `user_task_statistics` (
  `id` bigint(11) unsigned NOT NULL AUTO_INCREMENT,
  `version` int(11) NOT NULL,
  `create_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `statistics_hour` int(11) NOT NULL,
  `statistics_day` int(11) NOT NULL,
  `statistics_month` int(11) NOT NULL,
  `statistics_year` int(11) NOT NULL,
  `client_platform_type` varchar(32) DEFAULT '',
  `platform_from` varchar(32) NOT NULL DEFAULT '',
  `task_id` varchar(32) NOT NULL DEFAULT '',
  `task_name` varchar(32) NOT NULL DEFAULT '',
  `accept_count` int(11) NOT NULL,
  `cancel_count` int(11) NOT NULL,
  `platform_name` varchar(64) NOT NULL DEFAULT '',
  PRIMARY KEY (`id`),
  KEY `statistics_hour` (`statistics_hour`),
  KEY `statistics_day` (`statistics_day`),
  KEY `statistics_month` (`statistics_month`),
  KEY `statistics_year` (`statistics_year`),
  KEY `platform_from` (`platform_from`),
  KEY `client_platform_type` (`client_platform_type`),
  KEY `task_id` (`task_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;




/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
