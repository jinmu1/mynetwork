/*
Navicat MySQL Data Transfer

Source Server         : online
Source Server Version : 50505
Source Host           : 120.77.80.75:3306
Source Database       : glc_edu_dev

Target Server Type    : MYSQL
Target Server Version : 50505
File Encoding         : 65001

Date: 2021-01-10 19:00:57
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for edu_equipment
-- ----------------------------
DROP TABLE IF EXISTS `edu_equipment`;
CREATE TABLE `edu_equipment` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `batch_id` bigint(20) DEFAULT '0',
  `equipment_name` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `equipment_num` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `equipment_ability` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `equipment_function` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '功能',
  `equipment_price` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `cellStyleMap` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `id` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=198 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='教育版设备';
