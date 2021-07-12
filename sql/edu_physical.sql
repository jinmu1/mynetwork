/*
Navicat MySQL Data Transfer

Source Server         : online
Source Server Version : 50505
Source Host           : 120.77.80.75:3306
Source Database       : glc_edu_dev

Target Server Type    : MYSQL
Target Server Version : 50505
File Encoding         : 65001

Date: 2021-01-10 19:00:41
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for edu_physical
-- ----------------------------
DROP TABLE IF EXISTS `edu_physical`;
CREATE TABLE `edu_physical` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `batch_id` int(11) DEFAULT NULL,
  `physical_date` date DEFAULT NULL COMMENT '日期',
  `goods_code` char(50) DEFAULT NULL COMMENT '货物编码',
  `goods_name` char(50) DEFAULT NULL COMMENT '描述',
  `delivery_total` double DEFAULT NULL COMMENT '总库存',
  `pullet_num` double DEFAULT NULL COMMENT '区域',
  `piece_num` double DEFAULT NULL COMMENT '托盘规格',
  `warm_area` char(10) DEFAULT NULL COMMENT '温区',
  `cellStyleMap` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=30067 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;
