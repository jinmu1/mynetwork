/*
Navicat MySQL Data Transfer

Source Server         : online
Source Server Version : 50505
Source Host           : 120.77.80.75:3306
Source Database       : glc_edu_dev

Target Server Type    : MYSQL
Target Server Version : 50505
File Encoding         : 65001

Date: 2021-01-10 18:11:07
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for edu_delivery
-- ----------------------------
DROP TABLE IF EXISTS `edu_delivery`;
CREATE TABLE `edu_delivery` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `batch_id` int(11) DEFAULT NULL COMMENT '批次号',
  `customer_code` char(50) DEFAULT NULL COMMENT '客户码',
  `ship_to_party` char(50) DEFAULT NULL COMMENT '送达方',
  `delivery_date` date DEFAULT NULL COMMENT '交货日期',
  `delivery_time` time DEFAULT NULL COMMENT '交货时间',
  `order_code` char(50) DEFAULT NULL COMMENT '订单号',
  `goods_code` char(50) DEFAULT NULL COMMENT '物料号',
  `goods_name` char(50) DEFAULT NULL COMMENT '物料描述',
  `goods_num` double DEFAULT NULL COMMENT '物料数量',
  `pullet_num` double DEFAULT NULL COMMENT '托盘数量',
  `piece_num` double DEFAULT NULL COMMENT '规格',
  `warm_area` char(50) DEFAULT NULL COMMENT '温区',
  `cellStyleMap` char(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `goods_code` (`goods_code`)
) ENGINE=InnoDB AUTO_INCREMENT=632146 DEFAULT CHARSET=utf8 COMMENT='出库单';
