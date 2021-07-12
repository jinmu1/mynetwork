/*
Navicat MySQL Data Transfer

Source Server         : online
Source Server Version : 50505
Source Host           : 120.77.80.75:3306
Source Database       : glc_edu_dev

Target Server Type    : MYSQL
Target Server Version : 50505
File Encoding         : 65001

Date: 2021-01-10 18:55:14
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for edu_replenishment
-- ----------------------------
DROP TABLE IF EXISTS `edu_replenishment`;
CREATE TABLE `edu_replenishment` (
  `id` bigint(50) NOT NULL AUTO_INCREMENT,
  `batch_id` bigint(50) DEFAULT NULL,
  `batch_code` varchar(50) DEFAULT NULL COMMENT '波次号',
  `createDate` varchar(50) DEFAULT NULL COMMENT '任务产生日期',
  `createTime` varchar(50) DEFAULT NULL COMMENT '任务产生时间',
  `storage_location` varchar(50) DEFAULT NULL COMMENT '拣货库位',
  `goods_code` varchar(50) DEFAULT NULL COMMENT '物料编码',
  `goods_name` varchar(50) DEFAULT NULL COMMENT '物料名称',
  `wait_location` varchar(50) DEFAULT NULL COMMENT '待拣货数量',
  `piece_num` varchar(50) DEFAULT NULL COMMENT '规格',
  `pullet_num` varchar(50) DEFAULT NULL COMMENT '托盘规格',
  `warm_area` varchar(50) DEFAULT NULL COMMENT '温区',
  `cellStyleMap` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=323914 DEFAULT CHARSET=utf8;
