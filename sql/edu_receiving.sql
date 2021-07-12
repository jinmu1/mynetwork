/*
Navicat MySQL Data Transfer

Source Server         : online
Source Server Version : 50505
Source Host           : 120.77.80.75:3306
Source Database       : glc_edu_dev

Target Server Type    : MYSQL
Target Server Version : 50505
File Encoding         : 65001

Date: 2021-01-10 09:59:17
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for edu_receiving
-- ----------------------------
DROP TABLE IF EXISTS `edu_receiving`;
CREATE TABLE `edu_receiving` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '表ID',
  `batch_id` bigint(20) DEFAULT NULL COMMENT '项目号',
  `createDate` date NOT NULL COMMENT '日期',
  `createTime` time DEFAULT NULL COMMENT '时间',
  `shipper_code` varchar(50) DEFAULT NULL COMMENT '供应商',
  `order_code` char(30) DEFAULT NULL COMMENT '订单号',
  `goods_code` char(50) DEFAULT NULL COMMENT '物料编码',
  `goods_name` char(50) DEFAULT NULL COMMENT '商品名称',
  `goods_num` double DEFAULT NULL COMMENT '数量',
  `pullet_num` double DEFAULT NULL COMMENT '托盘数量',
  `piece_num` double DEFAULT NULL,
  `warm_area` char(50) DEFAULT NULL COMMENT '温区',
  `cellStyleMap` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=152352 DEFAULT CHARSET=utf8 COMMENT='入库表\r\n';
