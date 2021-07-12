/*
Navicat MySQL Data Transfer

Source Server         : 本地
Source Server Version : 50650
Source Host           : 127.0.0.1:3306
Source Database       : glc_edu_dev

Target Server Type    : MYSQL
Target Server Version : 50650
File Encoding         : 65001

Date: 2021-01-10 19:17:50
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for facility_data
-- ----------------------------
DROP TABLE IF EXISTS `facility_data`;
CREATE TABLE `facility_data` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `batch_id` int(11) DEFAULT NULL,
  `areas_id` int(11) DEFAULT NULL COMMENT '区域类型(301卸货区,302检验区,303存储区,304分拣区,305包装区,306复核区,307出库区,307办公区)',
  `areas_name` varchar(255) DEFAULT NULL COMMENT '设施功能区名称',
  `areas_function` varchar(255) DEFAULT NULL COMMENT '设施功能',
  `areas_cover` varchar(255) DEFAULT NULL COMMENT '设施功能面积',
  `prue` varchar(255) DEFAULT NULL COMMENT '单价',
  `equipment_id` int(11) DEFAULT NULL COMMENT '设备类型(201木质托盘，202拣货车，203手叉车，204电叉车，205存储设备，206其它物流容器)',
  `equipment_name` varchar(255) DEFAULT NULL COMMENT '设备名称',
  `equipment_num` varchar(255) DEFAULT NULL COMMENT '设备数量',
  `equipment_price` varchar(255) DEFAULT NULL COMMENT '设备单价',
  `operatorId` int(11) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;
